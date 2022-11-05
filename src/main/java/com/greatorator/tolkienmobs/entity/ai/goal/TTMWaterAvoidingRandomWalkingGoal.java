package com.greatorator.tolkienmobs.entity.ai.goal;

//
//public class TTMWaterAvoidingRandomWalkingGoal extends TTMRandomWalkingGoal {
//   protected final float probability;
//
//   public TTMWaterAvoidingRandomWalkingGoal(BirdEntity p_i47301_1_, double p_i47301_2_) {
//      this(p_i47301_1_, p_i47301_2_, 0.001F);
//   }
//
//   public TTMWaterAvoidingRandomWalkingGoal(BirdEntity p_i47302_1_, double p_i47302_2_, float p_i47302_4_) {
//      super(p_i47302_1_, p_i47302_2_);
//      this.probability = p_i47302_4_;
//   }
//
//   @Nullable
//   protected Vector3d getPosition() {
//      if (this.mob.isInWaterOrBubble()) {
//         Vector3d vector3d = TTMRandomPositionGenerator.getLandPos(this.mob, 15, 7);
//         return vector3d == null ? super.getPosition() : vector3d;
//      } else {
//         return this.mob.getRandom().nextFloat() >= this.probability ? TTMRandomPositionGenerator.getLandPos(this.mob, 10, 7) : super.getPosition();
//      }
//   }
//}