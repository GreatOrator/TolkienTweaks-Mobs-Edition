package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.lib.IChangeListener;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.TTMArea;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.HashMap;
import java.util.Map;

public class CamoFluidTile extends TileBCore implements IChangeListener, IInteractTile {
    protected final Map<String, TTMArea> modableRangeMap = new HashMap<>();
    private FluidStack fluidStack = null;
    private IFluidHelper helper;

    public CamoFluidTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CamoFluidTile() {
        super(TTMContent.CAMO_FLUID_TILE.get());
        helper.addBlockRange("fluidRange", new TTMArea.Rectangle(new BlockPos(0, 1, 0), 1));
        helper.addBlockRange("fluidTank", new TTMArea.Rectangle(new BlockPos(0, 1, 0), 1));

    }

    @Override
    public ActionResultType onBlockUse(BlockState state, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack stackIn = player.getItemInHand(hand);

        if (!player.isCreative() || stackIn.isEmpty()) {
            return null;
        }
        LazyOptional<IFluidHandler> capability = this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);

        if (capability.isPresent()) {
            IFluidHandler handler = capability.resolve().get();
            handler.fill(new FluidStack(fluidStack.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
        }
        TTMArea fluidRange = this.getBlockRange("fluidRange");

        for (BlockPos newPos : fluidRange.getContainedPositions(this.getBlockPos()))
        {
            if (level.canSeeSky(newPos))
            {
                onNeighborChange(newPos);
            }
        }


        return onBlockActivated(state, player, hand, hit) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
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

    public TTMArea getBlockRange(String range)
    {
        if (modableRangeMap.containsKey(range))
        {
            return modableRangeMap.get(range);
        }
        return null;
    }
}