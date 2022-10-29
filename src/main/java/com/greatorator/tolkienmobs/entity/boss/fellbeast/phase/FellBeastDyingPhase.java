package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;

import javax.annotation.Nullable;

public class FellBeastDyingPhase extends FellBeastPhase {
   private Vector3d targetLocation;
   private int time;

   public FellBeastDyingPhase(FellBeastEntity p_i46792_1_) {
      super(p_i46792_1_);
   }

   public void doClientTick() {
      if (this.time++ % 10 == 0) {
         float f = (this.fellbeast.getRandom().nextFloat() - 0.5F) * 8.0F;
         float f1 = (this.fellbeast.getRandom().nextFloat() - 0.5F) * 4.0F;
         float f2 = (this.fellbeast.getRandom().nextFloat() - 0.5F) * 8.0F;
         this.fellbeast.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.fellbeast.getX() + (double)f, this.fellbeast.getY() + 2.0D + (double)f1, this.fellbeast.getZ() + (double)f2, 0.0D, 0.0D, 0.0D);
      }

   }

   public void doServerTick() {
      ++this.time;
      if (this.targetLocation == null) {
         BlockPos blockpos = this.fellbeast.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
         this.targetLocation = Vector3d.atBottomCenterOf(blockpos);
      }

      double d0 = this.targetLocation.distanceToSqr(this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ());
      if (!(d0 < 100.0D) && !(d0 > 22500.0D) && !this.fellbeast.horizontalCollision && !this.fellbeast.verticalCollision) {
         this.fellbeast.setHealth(1.0F);
      } else {
         this.fellbeast.setHealth(0.0F);
      }

   }

   public void begin() {
      this.targetLocation = null;
      this.time = 0;
   }

   public float getFlySpeed() {
      return 3.0F;
   }

   @Nullable
   public Vector3d getFlyTargetLocation() {
      return this.targetLocation;
   }

   public FellBeastPhaseType<FellBeastDyingPhase> getPhase() {
      return FellBeastPhaseType.DYING;
   }
}