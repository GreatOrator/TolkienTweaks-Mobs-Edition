package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastScanningSittingPhase extends FellBeastSittingPhase {
//   private static final EntityPredicate CHARGE_TARGETING = (new EntityPredicate()).range(150.0D);
//   private final EntityPredicate scanTargeting;
//   private int scanningTime;
//
//   public FellBeastScanningSittingPhase(FellBeastEntity p_i46785_1_) {
//      super(p_i46785_1_);
//      this.scanTargeting = (new EntityPredicate()).range(20.0D).selector((p_221114_1_) -> {
//         return Math.abs(p_221114_1_.getY() - p_i46785_1_.getY()) <= 10.0D;
//      });
//   }
//
//   public void doServerTick() {
//      ++this.scanningTime;
//      LivingEntity livingentity = this.fellbeast.level.getNearestPlayer(this.scanTargeting, this.fellbeast, this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ());
//      if (livingentity != null) {
//         if (this.scanningTime > 25) {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.SITTING_ATTACKING);
//         } else {
//            Vector3d vector3d = (new Vector3d(livingentity.getX() - this.fellbeast.getX(), 0.0D, livingentity.getZ() - this.fellbeast.getZ())).normalize();
//            Vector3d vector3d1 = (new Vector3d((double)MathHelper.sin(this.fellbeast.yRot * ((float)Math.PI / 180F)), 0.0D, (double)(-MathHelper.cos(this.fellbeast.yRot * ((float)Math.PI / 180F))))).normalize();
//            float f = (float)vector3d1.dot(vector3d);
//            float f1 = (float)(Math.acos((double)f) * (double)(180F / (float)Math.PI)) + 0.5F;
//            if (f1 < 0.0F || f1 > 10.0F) {
//               double d0 = livingentity.getX() - this.fellbeast.head.getX();
//               double d1 = livingentity.getZ() - this.fellbeast.head.getZ();
//               double d2 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d0, d1) * (double)(180F / (float)Math.PI) - (double)this.fellbeast.yRot), -100.0D, 100.0D);
//               this.fellbeast.yRotA *= 0.8F;
//               float f2 = MathHelper.sqrt(d0 * d0 + d1 * d1) + 1.0F;
//               float f3 = f2;
//               if (f2 > 40.0F) {
//                  f2 = 40.0F;
//               }
//
//               this.fellbeast.yRotA = (float)((double)this.fellbeast.yRotA + d2 * (double)(0.7F / f2 / f3));
//               this.fellbeast.yRot += this.fellbeast.yRotA;
//            }
//         }
//      } else if (this.scanningTime >= 100) {
//         livingentity = this.fellbeast.level.getNearestPlayer(CHARGE_TARGETING, this.fellbeast, this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ());
//         this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.TAKEOFF);
//         if (livingentity != null) {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.CHARGING_PLAYER);
//            this.fellbeast.getFellBeastPhaseManager().getPhase(FellBeastPhaseType.CHARGING_PLAYER).setTarget(new Vector3d(livingentity.getX(), livingentity.getY(), livingentity.getZ()));
//         }
//      }
//
//   }
//
//   public void begin() {
//      this.scanningTime = 0;
//   }
//
//   public FellBeastPhaseType<FellBeastScanningSittingPhase> getPhase() {
//      return FellBeastPhaseType.SITTING_SCANNING;
//   }
//}