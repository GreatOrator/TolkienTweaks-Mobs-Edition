package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public abstract class FellBeastPhase implements IFellBeastPhase {
   protected final FellBeastEntity fellbeast;

   public FellBeastPhase(FellBeastEntity p_i46795_1_) {
      this.fellbeast = p_i46795_1_;
   }

   public boolean isSitting() {
      return false;
   }

   public void doClientTick() {
   }

   public void doServerTick() {
   }

   public void onCrystalDestroyed(MorgulCrystalEntity p_188655_1_, BlockPos p_188655_2_, DamageSource p_188655_3_, @Nullable PlayerEntity p_188655_4_) {
   }

   public void begin() {
   }

   public void end() {
   }

   public float getFlySpeed() {
      return 0.6F;
   }

   @Nullable
   public Vector3d getFlyTargetLocation() {
      return null;
   }

   public float onHurt(DamageSource p_221113_1_, float p_221113_2_) {
      return p_221113_2_;
   }

   public float getTurnSpeed() {
      float f = MathHelper.sqrt(Entity.getHorizontalDistanceSqr(this.fellbeast.getDeltaMovement())) + 1.0F;
      float f1 = Math.min(f, 40.0F);
      return 0.7F / f1 / f;
   }
}