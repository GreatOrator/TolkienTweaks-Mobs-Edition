package com.greatorator.tolkienmobs.entity.ai.goal;

import com.greatorator.tolkienmobs.entity.monster.RomieWalkerEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class RomieWalkerAttackGoal extends MeleeAttackGoal {
    private final RomieWalkerEntity walker;
    private int raiseArmTicks;

    public RomieWalkerAttackGoal(RomieWalkerEntity entity, double speedAmplifier, boolean useLongMemory) {
        super(entity, speedAmplifier, useLongMemory);
        this.walker = entity;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.walker.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.walker.setAggressive(true);
        } else {
            this.walker.setAggressive(false);
        }

    }
}