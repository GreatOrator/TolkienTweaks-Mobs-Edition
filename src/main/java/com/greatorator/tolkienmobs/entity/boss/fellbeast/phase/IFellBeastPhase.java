package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.item.MorgulCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public interface IFellBeastPhase {
   boolean isSitting();

   void doClientTick();

   void doServerTick();

   void onCrystalDestroyed(MorgulCrystalEntity p_188655_1_, BlockPos p_188655_2_, DamageSource p_188655_3_, @Nullable PlayerEntity p_188655_4_);

   void begin();

   void end();

   float getFlySpeed();

   float getTurnSpeed();

   FellBeastPhaseType<? extends IFellBeastPhase> getPhase();

   @Nullable
   Vector3d getFlyTargetLocation();

   float onHurt(DamageSource p_221113_1_, float p_221113_2_);
}