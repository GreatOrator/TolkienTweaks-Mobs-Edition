package com.greatorator.tolkienmobs.entity.ai.goal;

import com.greatorator.tolkienmobs.entity.boss.WatcherEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class WatcherAttackGoal extends Goal {
    private final int statecheck;
    private final WatcherEntity entity;
    private int moveSpeedAmp = 1;
    private int attackTime = -1;

    public WatcherAttackGoal(WatcherEntity mob, int moveSpeedAmpIn, int state) {
        this.entity = mob;
        this.moveSpeedAmp = moveSpeedAmpIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.statecheck = state;
    }

    public boolean canStart() {
        return this.entity.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canStart();
    }

    @Override
    public boolean canUse() {
        return this.canStart();
    }

    @Override
    public void start() {
        super.start();
        this.entity.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.setAggressive(false);
        this.entity.setAttackingState(0);
        this.attackTime = -1;
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity != null) {
            boolean inLineOfSight = this.entity.canBeSeenAsEnemy();
            this.attackTime++;
            this.entity.lookAt(livingentity, 80.0F, 80.0F);
            double d0 = this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            double d1 = this.getAttackReachSqr(livingentity);
            if (inLineOfSight) {
                if (this.entity.distanceTo(livingentity) >= 6.0D) {
                    this.entity.getNavigation().createPath(livingentity, this.moveSpeedAmp);
                } else {
                    if (this.attackTime == 4) {
                        this.entity.getNavigation().createPath(livingentity, this.moveSpeedAmp);
                        if (d0 <= d1) {
                            this.entity.tryAttack(livingentity);
                            this.entity.setAttackingState(statecheck);
                        }
                    }
                    if (this.attackTime == 8) {
                        this.attackTime = -5;
                        this.entity.setAttackingState(0);
                        livingentity.hurt(DamageSource.mobAttack(this.entity), 5.0f);
                    }
                }
            }
        }
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return 5.0f;
    }
}