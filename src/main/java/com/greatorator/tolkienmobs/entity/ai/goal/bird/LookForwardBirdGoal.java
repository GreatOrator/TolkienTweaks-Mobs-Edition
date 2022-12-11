package com.greatorator.tolkienmobs.entity.ai.goal.bird;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class LookForwardBirdGoal extends Goal {
    private final BirdEntity bird;

    public LookForwardBirdGoal(BirdEntity bird) {
        this.bird = bird;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.bird.getFlying()) return false;

        return true;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        Vec3 vec3d = this.bird.getDeltaMovement();
        this.bird.setYRot(-((float) Mth.atan2(vec3d.x, vec3d.z)) * 57.295776f);
        this.bird.yBodyRotO = this.bird.getYRot();
        this.bird.setXRot(40);
    }
}