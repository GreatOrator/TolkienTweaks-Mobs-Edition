package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.init.TolkienParticles;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class FellBeastLandingPhase extends FellBeastPhase {
   private Vector3d targetLocation;

   public FellBeastLandingPhase(FellBeastEntity p_i46788_1_) {
      super(p_i46788_1_);
   }

   public void doClientTick() {
      Vector3d vector3d = this.fellbeast.getHeadLookVector(1.0F).normalize();
      vector3d.yRot((-(float)Math.PI / 4F));
      double d0 = this.fellbeast.head.getX();
      double d1 = this.fellbeast.head.getY(0.5D);
      double d2 = this.fellbeast.head.getZ();

      for(int i = 0; i < 8; ++i) {
         Random random = this.fellbeast.getRandom();
         double d3 = d0 + random.nextGaussian() / 2.0D;
         double d4 = d1 + random.nextGaussian() / 2.0D;
         double d5 = d2 + random.nextGaussian() / 2.0D;
         Vector3d vector3d1 = this.fellbeast.getDeltaMovement();
         this.fellbeast.level.addParticle(TolkienParticles.fell_beast_breath, d3, d4, d5, -vector3d.x * (double)0.08F + vector3d1.x, -vector3d.y * (double)0.3F + vector3d1.y, -vector3d.z * (double)0.08F + vector3d1.z);
         vector3d.yRot(0.19634955F);
      }

   }

   public void doServerTick() {
      if (this.targetLocation == null) {
         this.targetLocation = Vector3d.atBottomCenterOf(this.fellbeast.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION));
      }

      if (this.targetLocation.distanceToSqr(this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ()) < 1.0D) {
         this.fellbeast.getFellBeastPhaseManager().getPhase(FellBeastPhaseType.SITTING_FLAMING).resetFlameCount();
         this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.SITTING_SCANNING);
      }

   }

   public float getFlySpeed() {
      return 1.5F;
   }

   public float getTurnSpeed() {
      float f = MathHelper.sqrt(Entity.getHorizontalDistanceSqr(this.fellbeast.getDeltaMovement())) + 1.0F;
      float f1 = Math.min(f, 40.0F);
      return f1 / f;
   }

   public void begin() {
      this.targetLocation = null;
   }

   @Nullable
   public Vector3d getFlyTargetLocation() {
      return this.targetLocation;
   }

   public FellBeastPhaseType<FellBeastLandingPhase> getPhase() {
      return FellBeastPhaseType.LANDING;
   }
}