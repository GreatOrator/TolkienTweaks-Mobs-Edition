package com.greatorator.tolkienmobs.entity.ai.goal.bird;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class LandBirdGoal extends Goal {
    private final BirdEntity bird;
    private final int chance;

    private BlockPos pos;


    public LandBirdGoal(BirdEntity bird, int chance) {
        this.bird = bird;
        this.chance = chance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.bird.getFlying()) {
            return false;
        }

        if (this.bird.getRandom().nextInt(reducedTickDelay(this.chance)) != 0) {
            return false;
        }

        Vec3 end = this.bird.getEyePosition();
        end = new Vec3(end.x, 0, end.z);
        BlockHitResult result = this.bird.level.clip(new ClipContext(this.bird.getEyePosition(), end, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, this.bird));
        if (result.getType() != HitResult.Type.BLOCK) {
            return false;
        }
        BlockState block = this.bird.level.getBlockState(result.getBlockPos());
        if (!block.getMaterial().isSolid() || block.getMaterial().isLiquid()) {
            return false;
        }

        pos = result.getBlockPos();
        return true;
    }

    @Override
    public void start() {
        this.bird.getMoveControl().setWantedPosition(pos.getX(), pos.getY() + 1, pos.getZ(), 1.0f);
    }

    @Override
    public boolean canContinueToUse() {
        MoveControl control = this.bird.getMoveControl();
        double d = Math.pow(control.getWantedX() - this.bird.getX(), 2) + Math.pow(control.getWantedY() - this.bird.getY(), 2) + Math.pow(control.getWantedZ() - this.bird.getZ(), 2);
        return d > 16 && d < 36000;
    }

    @Override
    public void stop() {
        this.bird.moveControl = new MoveControl(this.bird);
        this.bird.setNoGravity(false);
        this.bird.setFlying(false);
    }
}
