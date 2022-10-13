package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public interface IFluidSource extends IFluidPosition {
   @Override
   double x();

   @Override
   double y();

   @Override
   double z();

   BlockPos getPos();

   BlockState getBlockState();

   <T extends TileEntity> T getEntity();

   ServerWorld getLevel();
}