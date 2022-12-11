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

public class StartFlyingBirdGoal extends Goal {
    private final BirdEntity bird;
    private final int chance;

    private Vec3 target;


    public StartFlyingBirdGoal(BirdEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.bird.getFlying()) {
            return false;
        }

        MoveControl moveControl = this.bird.getMoveControl();
        if (moveControl.hasWanted()) {
            return false;
        }

        if (this.bird.getRandom().nextInt(reducedTickDelay(this.chance)) != 0) {
            return false;
        }

        Vec3 end = this.bird.getEyePosition();
        for (int i = 0; i <= 360; i += 45) {
            double yaw = i * 0.0174533;
            end = new Vec3(end.x + Math.cos(yaw) * 15f , end.y + 8f,  end.z + Math.sin(yaw) * 15f);
            BlockHitResult result = this.bird.level.clip(new ClipContext(this.bird.getEyePosition(), end, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, this.bird));
            if (result.getType() != HitResult.Type.MISS) continue;

            target = end;
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        this.bird.moveControl = new FreeFlightMoveControl(this.bird);
        this.bird.setFlying(true);
        this.bird.getMoveControl().setWantedPosition(target.x, target.y, target.z, 0.15);
    }

    @Override
    public boolean canContinueToUse() {
        MoveControl control = this.bird.getMoveControl();
        double d = Math.pow(control.getWantedX() - this.bird.getX(), 2) + Math.pow(control.getWantedY() - this.bird.getY(), 2) + Math.pow(control.getWantedZ() - this.bird.getZ(), 2);
        return d > 1 && d < 36000 && !this.bird.isOnGround();
    }
}
