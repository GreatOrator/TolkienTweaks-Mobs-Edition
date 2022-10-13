package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IChangeListener;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.ProxyFluidSource;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.block.CamoFluidBlock.DISPENSER_REGISTRY;
import static net.minecraft.util.Direction.UP;

public class CamoFluidTile extends TileBCore implements IChangeListener, IInteractTile, INamedContainerProvider, ITickableTileEntity {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static final ContainerSlotLayout.LayoutFactory<CamoFluidTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.mainInventory);
    public TileItemStackHandler mainInventory = new TileItemStackHandler(1);
    private FluidStack fluidStack = null;
    public int timing = 0;
    public CamoFluidTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CamoFluidTile() {
        super(TTMContent.CAMO_FLUID_TILE.get());
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(mainInventory).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventory).saveBoth().syncTile();
    }

    public void onRightClick(PlayerEntity playerEntity, Hand hand) {
        if (!playerEntity.level.isClientSide()) {
            openGUI(playerEntity, this, worldPosition);
        }
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
    public ActionResultType onBlockUse(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack stackIn = player.getItemInHand(hand);
        return null;
    }

    @Override
    public void writeExtraNBT(CompoundNBT compound) {
        super.writeExtraNBT(compound);
        if (fluidStack != null) {
            CompoundNBT tag = new CompoundNBT();
            fluidStack.writeToNBT(tag);
            compound.put("Fluid", tag);
        }
    }

    @Override
    public void readExtraNBT(CompoundNBT compound) {
        super.readExtraNBT(compound);
        if (compound.contains("Fluid")) {
            fluidStack = FluidStack.loadFluidStackFromNBT(compound.getCompound("Fluid"));
        }
    }

    @Override
    public void onNeighborChange(BlockPos neighbor) {
        if (fluidStack == null || fluidStack.getFluid() == null) {
            return;
        }
        Fluid fluid = fluidStack.getFluid();
        for (Direction facing : Direction.values()) {
            BlockPos side = neighbor.relative(facing);
            if (level.canSeeSky(side)) {
                level.setBlockAndUpdate(side, fluid.defaultFluidState().createLegacyBlock());
                level.neighborChanged(side, fluid.defaultFluidState().createLegacyBlock().getBlock(), neighbor);
            }
        }
    }

    public int addItem(ItemStack stack) {
        for(int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).isEmpty()) {
                this.setItem(i, stack);
                return i;
            }
        }
        return 0;
    }

    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
        this.getItems().set(p_70299_1_, p_70299_2_);
        this.setChanged();
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public ItemStack getItem(int i) {
        return this.getItems().get(i);
    }

    @Override
    public void tick() {
        if (!(this.level instanceof ServerWorld)) {
        return;
        } else {
            if (timing <= 20) {
                timing++;
            } else {
                ServerWorld serverworld = (ServerWorld) this.level;
                this.dispenseFrom(serverworld, worldPosition);
                timing=0;
            }
        }
    }

    public void dispenseFrom(ServerWorld worldIn, BlockPos blockPos) {
        ProxyFluidSource proxyfluidsource = new ProxyFluidSource(worldIn, blockPos);
        CamoFluidTile fluidtileentity = proxyfluidsource.getEntity();
        ItemStack itemstack = fluidtileentity.getItem(0);
        IFluidHelper ifluidhelperbehavior = this.getDispenseMethod(itemstack);
        if (ifluidhelperbehavior != IFluidHelper.NOOP) {
            fluidtileentity.setItem(0, ifluidhelperbehavior.dispense(proxyfluidsource, itemstack));
        }
    }

    private IFluidHelper getDispenseMethod(ItemStack itemstack) {
        return DISPENSER_REGISTRY.get(itemstack.getItem());
    }
}