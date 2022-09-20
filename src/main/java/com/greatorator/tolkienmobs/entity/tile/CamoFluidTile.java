package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.lib.IChangeListener;
import com.brandon3055.brandonscore.lib.IInteractTile;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.TTMArea;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
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
        helper.addBlockRange("waterRange", new TTMArea.Rectangle(new BlockPos(0, 1, 0), 1));
        helper.addBlockRange("waterTank", new TTMArea.Rectangle(new BlockPos(0, 1, 0), 1));

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
        TTMArea waterRange = this.getBlockRange("waterRange");

        for (BlockPos newPos : waterRange.getContainedPositions(this.getBlockPos()))
        {
            if (level.canSeeSky(newPos))
            {
                level.setBlockAndUpdate(newPos, Blocks.WATER.defaultBlockState());
            }
        }


        return onBlockActivated(state, player, hand, hit) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
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