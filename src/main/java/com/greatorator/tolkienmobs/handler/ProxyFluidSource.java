package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.handler.interfaces.IFluidSource;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class ProxyFluidSource implements IFluidSource {
   private final ServerWorld level;
   private final BlockPos pos;

   public ProxyFluidSource(ServerWorld p_i242071_1_, BlockPos p_i242071_2_) {
      this.level = p_i242071_1_;
      this.pos = p_i242071_2_;
   }

   @Override
   public ServerWorld getLevel() {
      return this.level;
   }

   @Override
   public double x() {
      return (double)this.pos.getX() + 0.5D;
   }

   @Override
   public double y() {
      return (double)this.pos.getY() + 0.5D;
   }

   @Override
   public double z() {
      return (double)this.pos.getZ() + 0.5D;
   }

   @Override
   public BlockPos getPos() {
      return this.pos;
   }

   @Override
   public BlockState getBlockState() {
      return this.level.getBlockState(this.pos);
   }

   @Override
   public <T extends TileEntity> T getEntity() {
      return (T)this.level.getBlockEntity(this.pos);
   }
}