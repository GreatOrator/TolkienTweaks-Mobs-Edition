package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface FluidSource extends FluidPosition {
   @Override
   double x();

   @Override
   double y();

   @Override
   double z();

   BlockPos getPos();

   BlockState getBlockState();

   <T extends BlockEntity> T getEntity();

   ServerLevel getLevel();
}