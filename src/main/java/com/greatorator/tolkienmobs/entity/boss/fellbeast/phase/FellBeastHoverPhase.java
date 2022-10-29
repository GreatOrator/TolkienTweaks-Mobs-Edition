package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class FellBeastHoverPhase extends FellBeastPhase {
   private Vector3d targetLocation;

   public FellBeastHoverPhase(FellBeastEntity p_i46790_1_) {
      super(p_i46790_1_);
   }

   public void doServerTick() {
      if (this.targetLocation == null) {
         this.targetLocation = this.fellbeast.position();
      }

   }

   public boolean isSitting() {
      return true;
   }

   public void begin() {
      this.targetLocation = null;
   }

   public float getFlySpeed() {
      return 1.0F;
   }

   @Nullable
   public Vector3d getFlyTargetLocation() {
      return this.targetLocation;
   }

   public FellBeastPhaseType<FellBeastHoverPhase> getPhase() {
      return FellBeastPhaseType.HOVERING;
   }
}