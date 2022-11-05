package com.greatorator.tolkienmobs.entity.ai.goal;

//
//public class TTMRomieWalkerAttackGoal extends MeleeAttackGoal {
//    private final RomieWalkerEntity walker;
//    private int raiseArmTicks;
//
//    public TTMRomieWalkerAttackGoal(RomieWalkerEntity p_i46803_1_, double p_i46803_2_, boolean p_i46803_4_) {
//        super(p_i46803_1_, p_i46803_2_, p_i46803_4_);
//        this.walker = p_i46803_1_;
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        this.raiseArmTicks = 0;
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        this.walker.setAggressive(false);
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        ++this.raiseArmTicks;
//        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
//            this.walker.setAggressive(true);
//        } else {
//            this.walker.setAggressive(false);
//        }
//
//    }
//}
