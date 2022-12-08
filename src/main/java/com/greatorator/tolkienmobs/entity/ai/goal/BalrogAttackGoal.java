package com.greatorator.tolkienmobs.entity.ai.goal;

import com.greatorator.tolkienmobs.entity.boss.BalrogEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BalrogAttackGoal extends ThrowAndAttackGoal {
    private final BalrogEntity balrog;
    private int raiseArmTicks;
    private int attackStep;
    private int attackTime;

    public BalrogAttackGoal(BalrogEntity balrogEntity, double speedAmplifier, int attackInterval, float maxDistance, boolean useLongMemory) {
        super(balrogEntity, speedAmplifier, attackInterval, maxDistance, useLongMemory);
        this.balrog = balrogEntity;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.balrog.setCharged(false);
        this.balrog.setAggressive(false);
    }

    @Override
    public void tick() {
        LivingEntity entity = this.balrog.getTarget();
        double following = this.balrog.distanceToSqr(entity);
        boolean entitySpotted = this.balrog.getSensing().hasLineOfSight(entity);
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.balrog.setAggressive(true);
        } else {
            this.balrog.setAggressive(false);
        }

        if (following < this.getFollowDistance() * this.getFollowDistance() && entitySpotted) {
            if (this.attackTime <= 0) {
                ++this.attackStep;
                if (this.attackStep == 1) {
                    this.attackTime = 60;
                    this.balrog.setCharged(true);
                } else if (this.attackStep <= 4) {
                    this.attackTime = 6;
                } else {
                    this.attackTime = 100;
                    this.attackStep = 0;
                    this.balrog.setCharged(false);
                }
            }
        }
    }
    private double getFollowDistance() {
        return this.balrog.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}
