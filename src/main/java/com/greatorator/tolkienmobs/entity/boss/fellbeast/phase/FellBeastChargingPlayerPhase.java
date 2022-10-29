package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import net.minecraft.util.math.vector.Vector3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class FellBeastChargingPlayerPhase extends FellBeastPhase {
   private static final Logger LOGGER = LogManager.getLogger();
   private Vector3d targetLocation;
   private int timeSinceCharge;

   public FellBeastChargingPlayerPhase(FellBeastEntity p_i46793_1_) {
      super(p_i46793_1_);
   }

   public void doServerTick() {
      if (this.targetLocation == null) {
         LOGGER.warn("Aborting charge player as no target was set.");
         this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.HOLDING_PATTERN);
      } else if (this.timeSinceCharge > 0 && this.timeSinceCharge++ >= 10) {
         this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.HOLDING_PATTERN);
      } else {
         double d0 = this.targetLocation.distanceToSqr(this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ());
         if (d0 < 100.0D || d0 > 22500.0D || this.fellbeast.horizontalCollision || this.fellbeast.verticalCollision) {
            ++this.timeSinceCharge;
         }

      }
   }

   public void begin() {
      this.targetLocation = null;
      this.timeSinceCharge = 0;
   }

   public void setTarget(Vector3d p_188668_1_) {
      this.targetLocation = p_188668_1_;
   }

   public float getFlySpeed() {
      return 3.0F;
   }

   @Nullable
   public Vector3d getFlyTargetLocation() {
      return this.targetLocation;
   }

   public FellBeastPhaseType<FellBeastChargingPlayerPhase> getPhase() {
      return FellBeastPhaseType.CHARGING_PLAYER;
   }
}