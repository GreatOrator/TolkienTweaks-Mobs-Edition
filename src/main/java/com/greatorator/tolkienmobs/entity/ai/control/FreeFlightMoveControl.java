package com.greatorator.tolkienmobs.entity.ai.control;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

public class FreeFlightMoveControl extends MoveControl {
    private final BirdEntity bird;
    private int collisionCheckCooldown;

    public FreeFlightMoveControl(BirdEntity bird) {
        super(bird);
        this.bird = bird;
    }

    @Override
    public void tick() {
        if (this.operation != MoveControl.Operation.MOVE_TO) {
            this.bird.setNoGravity(false);
            return;
        }
        this.bird.setNoGravity(true);
        if (this.collisionCheckCooldown-- <= 0) {
            this.collisionCheckCooldown += this.bird.getRandom().nextInt(5) + 2;
            Vec3 vec3d = new Vec3(this.wantedX - this.bird.getX(), this.wantedY - this.bird.getY(), this.wantedZ - this.bird.getZ());
            double d = vec3d.length();
            if (this.willCollide(vec3d = vec3d.normalize(), Mth.ceil(d))) {
                this.bird.setDeltaMovement(this.bird.getDeltaMovement().add(vec3d.multiply(0.1D, 0.1D, 0.1D)));
            } else {
                this.operation = MoveControl.Operation.WAIT;
            }
        }
    }

    private boolean willCollide(Vec3 direction, int steps) {
        for (int i = 1; i < steps; ++i) {
            if (this.bird.level.isUnobstructed(this.bird)) continue;
            return false;
        }
        return true;
    }
}
