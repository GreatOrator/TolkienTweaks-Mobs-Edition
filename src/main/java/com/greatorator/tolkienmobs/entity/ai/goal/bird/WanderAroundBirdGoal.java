package com.greatorator.tolkienmobs.entity.ai.goal.bird;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class WanderAroundBirdGoal extends WaterAvoidingRandomStrollGoal {
    protected final BirdEntity mob;

    public WanderAroundBirdGoal(BirdEntity bird, double d) {
        super(bird, d);
        this.mob = bird;
    }

    public WanderAroundBirdGoal(BirdEntity bird, double speed, float probability) {
        super(bird, speed, probability);
        this.mob = bird;
    }

    @Override
    public boolean canUse() {
        if (mob.getFlying()) {
            return false;
        }

        return super.canUse();
    }

    @Override
    public void start() {
        this.mob.moveControl = new MoveControl(this.mob);
        super.start();
    }
}
