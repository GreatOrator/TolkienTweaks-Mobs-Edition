package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IChangeListener;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import static net.minecraft.util.Direction.UP;

public class CamoFluidTile extends TileBCore implements IChangeListener, IInteractTile, INamedContainerProvider {
    public static final ContainerSlotLayout.LayoutFactory<CamoFluidTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.mainInventory);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(1);

    public CamoFluidTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CamoFluidTile() {
        super(TTMContent.CAMO_FLUID_TILE.get());
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(mainInventory).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth().syncTile();
    }

    @Override
    public ActionResultType onBlockUse(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!player.level.isClientSide()) {
            openGUI(player, this, worldPosition);
        }
        return ActionResultType.SUCCESS;
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ContainerBCTile<>(TTMContent.CAMO_FLUID_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
    }

    @Override
    public void onNeighborChange(BlockPos neighbor) {
        ItemStack stack = mainInventory.getStackInSlot(0);
        LazyOptional<IFluidHandlerItem> optional = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY);
        if (stack.isEmpty() || !optional.isPresent()) {
            return;
        }

        IFluidHandlerItem handler = optional.orElseThrow(RuntimeException::new);
        Fluid fluid = handler.getFluidInTank(0).getFluid();
        if (fluid == Fluids.EMPTY) {
            return;
        }

        for (Direction facing : Direction.values()) {
            BlockPos side = worldPosition.relative(facing);
            if (level.isEmptyBlock(side)) {
                level.setBlockAndUpdate(side, fluid.defaultFluidState().createLegacyBlock());
            }
        }
    }
}