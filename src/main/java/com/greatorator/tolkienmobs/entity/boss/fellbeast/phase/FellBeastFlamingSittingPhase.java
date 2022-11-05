package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastFlamingSittingPhase extends FellBeastSittingPhase {
//   private int flameTicks;
//   private int flameCount;
//   private AreaEffectCloudEntity flame;
//
//   public FellBeastFlamingSittingPhase(FellBeastEntity p_i46786_1_) {
//      super(p_i46786_1_);
//   }
//
//   public void doClientTick() {
//      ++this.flameTicks;
//      if (this.flameTicks % 2 == 0 && this.flameTicks < 10) {
//         Vector3d vector3d = this.fellbeast.getHeadLookVector(1.0F).normalize();
//         vector3d.yRot((-(float)Math.PI / 4F));
//         double d0 = this.fellbeast.head.getX();
//         double d1 = this.fellbeast.head.getY(0.5D);
//         double d2 = this.fellbeast.head.getZ();
//
//         for(int i = 0; i < 8; ++i) {
//            double d3 = d0 + this.fellbeast.getRandom().nextGaussian() / 2.0D;
//            double d4 = d1 + this.fellbeast.getRandom().nextGaussian() / 2.0D;
//            double d5 = d2 + this.fellbeast.getRandom().nextGaussian() / 2.0D;
//
//            for(int j = 0; j < 6; ++j) {
//               this.fellbeast.level.addParticle(TolkienParticles.fell_beast_breath, d3, d4, d5, -vector3d.x * (double)0.08F * (double)j, -vector3d.y * (double)0.6F, -vector3d.z * (double)0.08F * (double)j);
//            }
//
//            vector3d.yRot(0.19634955F);
//         }
//      }
//
//   }
//
//   public void doServerTick() {
//      ++this.flameTicks;
//      if (this.flameTicks >= 200) {
//         if (this.flameCount >= 4) {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.TAKEOFF);
//         } else {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.SITTING_SCANNING);
//         }
//      } else if (this.flameTicks == 10) {
//         Vector3d vector3d = (new Vector3d(this.fellbeast.head.getX() - this.fellbeast.getX(), 0.0D, this.fellbeast.head.getZ() - this.fellbeast.getZ())).normalize();
//         float f = 5.0F;
//         double d0 = this.fellbeast.head.getX() + vector3d.x * 5.0D / 2.0D;
//         double d1 = this.fellbeast.head.getZ() + vector3d.z * 5.0D / 2.0D;
//         double d2 = this.fellbeast.head.getY(0.5D);
//         double d3 = d2;
//         BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(d0, d2, d1);
//
//         while(this.fellbeast.level.isEmptyBlock(blockpos$mutable)) {
//            --d3;
//            if (d3 < 0.0D) {
//               d3 = d2;
//               break;
//            }
//
//            blockpos$mutable.set(d0, d3, d1);
//         }
//
//         d3 = (double)(MathHelper.floor(d3) + 1);
//         this.flame = new AreaEffectCloudEntity(this.fellbeast.level, d0, d3, d1);
//         this.flame.setOwner(this.fellbeast);
//         this.flame.setRadius(5.0F);
//         this.flame.setDuration(200);
//         this.flame.setParticle(TolkienParticles.fell_beast_breath);
//         this.flame.addEffect(new EffectInstance(Effects.HARM));
//         this.fellbeast.level.addFreshEntity(this.flame);
//      }
//
//   }
//
//   public void begin() {
//      this.flameTicks = 0;
//      ++this.flameCount;
//   }
//
//   public void end() {
//      if (this.flame != null) {
//         this.flame.remove();
//         this.flame = null;
//      }
//
//   }
//
//   public FellBeastPhaseType<FellBeastFlamingSittingPhase> getPhase() {
//      return FellBeastPhaseType.SITTING_FLAMING;
//   }
//
//   public void resetFlameCount() {
//      this.flameCount = 0;
//   }
//}