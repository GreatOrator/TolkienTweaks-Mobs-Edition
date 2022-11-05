package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastAttackingSittingPhase extends FellBeastSittingPhase {
//   private int attackingTicks;
//
//   public FellBeastAttackingSittingPhase(FellBeastEntity p_i46787_1_) {
//      super(p_i46787_1_);
//   }
//
//   public void doClientTick() {
//      this.fellbeast.level.playLocalSound(this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ(), SoundGenerator.soundIdleFellBeast.get(), this.fellbeast.getSoundSource(), 2.5F, 0.8F + this.fellbeast.getRandom().nextFloat() * 0.3F, false);
//   }
//
//   public void doServerTick() {
//      if (this.attackingTicks++ >= 40) {
//         this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.SITTING_FLAMING);
//      }
//
//   }
//
//   public void begin() {
//      this.attackingTicks = 0;
//   }
//
//   public FellBeastPhaseType<FellBeastAttackingSittingPhase> getPhase() {
//      return FellBeastPhaseType.SITTING_ATTACKING;
//   }
//}