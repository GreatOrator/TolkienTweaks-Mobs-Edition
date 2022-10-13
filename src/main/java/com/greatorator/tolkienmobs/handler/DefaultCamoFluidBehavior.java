package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.block.CamoFluidBlock;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidPosition;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class DefaultCamoFluidBehavior implements IFluidHelper {
    public final ItemStack dispense(IFluidSource fluidSource, ItemStack stack) {
        ItemStack itemstack = this.execute(fluidSource, stack);
        this.playAnimation(fluidSource, fluidSource.getBlockState().getValue(CamoFluidBlock.FACING));
        return itemstack;
    }

    protected ItemStack execute(IFluidSource fluidSource, ItemStack stack) {
        Direction direction = fluidSource.getBlockState().getValue(CamoFluidBlock.FACING);
        IFluidPosition iFluidPosition = CamoFluidBlock.getDispensePosition(fluidSource);
        ItemStack itemstack = stack.split(1);
        spawnItem(fluidSource.getLevel(), itemstack, 6, direction, iFluidPosition);
        return stack;
    }

    public static void spawnItem(World world, ItemStack stack, int i, Direction direction, IFluidPosition position) {
        double d0 = position.x();
        double d1 = position.y();
        double d2 = position.z();
        if (direction.getAxis() == Direction.Axis.Y) {
            d1 = d1 - 0.125D;
        } else {
            d1 = d1 - 0.15625D;
        }

        ItemEntity itementity = new ItemEntity(world, d0, d1, d2, stack);
        double d3 = world.random.nextDouble() * 0.1D + 0.2D;
        itementity.setDeltaMovement(world.random.nextGaussian() * (double)0.0075F * (double)i + (double)direction.getStepX() * d3, world.random.nextGaussian() * (double)0.0075F * (double)i + (double)0.2F, world.random.nextGaussian() * (double)0.0075F * (double)i + (double)direction.getStepZ() * d3);
        world.addFreshEntity(itementity);
    }

    protected void playAnimation(IFluidSource fluidSource, Direction direction) {
        fluidSource.getLevel().levelEvent(2000, fluidSource.getPos(), direction.get3DDataValue());
    }
}