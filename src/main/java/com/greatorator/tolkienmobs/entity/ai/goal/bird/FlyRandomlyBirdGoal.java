package com.greatorator.tolkienmobs.entity.ai.goal.bird;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import com.greatorator.tolkienmobs.entity.ai.control.FreeFlightMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public class FlyRandomlyBirdGoal extends Goal {
    private final BirdEntity bird;

    public FlyRandomlyBirdGoal(BirdEntity bird) {
        this.bird = bird;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.bird.getFlying()) {
            return false;
        }

        double f;
        double e;
        MoveControl moveControl = this.bird.getMoveControl();
        if (!moveControl.hasWanted()) {
            return true;
        }
        double d = moveControl.getWantedX() - this.bird.getX();
        double g = d * d + (e = moveControl.getWantedY() - this.bird.getY()) * e + (f = moveControl.getWantedZ() - this.bird.getZ()) * f;
        return g < 1.0 || g > 3600.0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.bird.moveControl = new FreeFlightMoveControl(this.bird);

        double currentHeight = -1;
        Vec3 end = this.bird.getEyePosition();
        end = new Vec3(end.x, 0, end.z);
        BlockHitResult result = this.bird.level.clip(new ClipContext(this.bird.getEyePosition(), end, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, this.bird));
        if (result.getType() == HitResult.Type.BLOCK) {
            currentHeight = this.bird.getY() - result.getBlockPos().getY();
        }

        Random random = (Random) this.bird.getRandom();
        double d = this.bird.getX() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 50.0f);
        double e = this.bird.getY() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 2 + currentHeight != -1 && currentHeight < 6 ? 6 : 0);
        double f = this.bird.getZ() + (double) ((random.nextFloat() * 2.0f - 1.0f) * 50.0f);
        this.bird.getMoveControl().setWantedPosition(d, e, f, 1.0);
    }
}