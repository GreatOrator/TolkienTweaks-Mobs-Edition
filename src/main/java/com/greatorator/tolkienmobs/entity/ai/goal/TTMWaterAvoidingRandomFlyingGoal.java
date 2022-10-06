package com.greatorator.tolkienmobs.entity.ai.goal;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class TTMWaterAvoidingRandomFlyingGoal extends TTMWaterAvoidingRandomWalkingGoal {
   public TTMWaterAvoidingRandomFlyingGoal(BirdEntity p_i47413_1_, double p_i47413_2_) {
      super(p_i47413_1_, p_i47413_2_);
   }

   @Nullable
   protected Vector3d getPosition() {
      Vector3d vector3d = null;
      if (this.mob.isInWater()) {
         vector3d = TTMRandomPositionGenerator.getLandPos(this.mob, 15, 15);
      }

      if (this.mob.getRandom().nextFloat() >= this.probability) {
         vector3d = this.getTreePos();
      }

      return vector3d == null ? super.getPosition() : vector3d;
   }

   @Nullable
   private Vector3d getTreePos() {
      BlockPos blockpos = this.mob.blockPosition();
      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
      BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

      for(BlockPos blockpos1 : BlockPos.betweenClosed(MathHelper.floor(this.mob.getX() - 3.0D), MathHelper.floor(this.mob.getY() - 6.0D), MathHelper.floor(this.mob.getZ() - 3.0D), MathHelper.floor(this.mob.getX() + 3.0D), MathHelper.floor(this.mob.getY() + 6.0D), MathHelper.floor(this.mob.getZ() + 3.0D))) {
         if (!blockpos.equals(blockpos1)) {
            Block block = this.mob.level.getBlockState(blockpos$mutable1.setWithOffset(blockpos1, Direction.DOWN)).getBlock();
            boolean flag = block instanceof LeavesBlock || block.is(BlockTags.LOGS);
            if (flag && this.mob.level.isEmptyBlock(blockpos1) && this.mob.level.isEmptyBlock(blockpos$mutable.setWithOffset(blockpos1, Direction.UP))) {
               return Vector3d.atBottomCenterOf(blockpos1);
            }
         }
      }

      return null;
   }
}