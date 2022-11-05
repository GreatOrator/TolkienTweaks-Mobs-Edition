package com.greatorator.tolkienmobs.entity.ai.goal;

//
//public class TTMSitGoal extends Goal {
//   private final BirdEntity mob;
//
//   public TTMSitGoal(BirdEntity p_i1654_1_) {
//      this.mob = p_i1654_1_;
//      this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
//   }
//
//   public boolean canContinueToUse() {
//      return this.mob.isOrderedToSit();
//   }
//
//   public boolean canUse() {
//      if (!this.mob.isTame()) {
//         return false;
//      } else if (this.mob.isInWaterOrBubble()) {
//         return false;
//      } else if (!this.mob.isOnGround()) {
//         return false;
//      } else {
//         LivingEntity livingentity = this.mob.getOwner();
//         if (livingentity == null) {
//            return true;
//         } else {
//            return this.mob.distanceToSqr(livingentity) < 144.0D && livingentity.getLastHurtByMob() != null ? false : this.mob.isOrderedToSit();
//         }
//      }
//   }
//
//   public void start() {
//      this.mob.getNavigation().stop();
//      this.mob.setInSittingPose(true);
//   }
//
//   public void stop() {
//      this.mob.setInSittingPose(false);
//   }
//}