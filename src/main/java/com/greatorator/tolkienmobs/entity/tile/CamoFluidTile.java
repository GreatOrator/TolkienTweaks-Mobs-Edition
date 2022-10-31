package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IChangeListener;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

import static net.minecraft.core.Direction.UP;

public class CamoFluidTile extends TileBCore implements IChangeListener, MenuProvider, IInteractTile {
    public static final ContainerSlotLayout.LayoutFactory<CamoFluidTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.mainInventory);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(1);

    public CamoFluidTile(BlockPos pos, BlockState state) {
        super(TolkienTiles.CAMO_FLUID_TILE.get(), pos, state);
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(mainInventory).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth().syncTile();
    }

    @Override
    public InteractionResult onBlockUse(BlockState state, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.level.isClientSide()) {
            NetworkHooks.openGui((ServerPlayer) player, this, worldPosition);
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player playerEntity) {
        return new ContainerBCTile<>(TolkienContainers.CAMO_FLUID_CONTAINER, windowID, playerInventory, this, SLOT_LAYOUT);
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