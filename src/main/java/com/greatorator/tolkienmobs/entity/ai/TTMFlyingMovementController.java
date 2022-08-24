package com.greatorator.tolkienmobs.entity.ai;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.util.math.MathHelper;

public class TTMFlyingMovementController extends FlyingMovementController {
    private int maxTurn = 0;
    private boolean hoversInPlace = false;
    public TTMFlyingMovementController(MobEntity mobEntity, int turnRadius, boolean canHover)
    {
        super(mobEntity, turnRadius, canHover);
        this.maxTurn = turnRadius;
        this.hoversInPlace = canHover;
    }

    @Override
    public void tick()
    {
        if (this.operation == Action.MOVE_TO)
        {
            this.operation = Action.WAIT;
            this.mob.setNoGravity(true);
            double d0 = this.wantedX - this.mob.getX();
            double d1 = this.wantedY - this.mob.getY();
            double d2 = this.wantedZ - this.mob.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 < (double)2.5000003E-7F)
            {
                this.mob.setYya(2.0F);
                this.mob.setZza(2.0F);
                return;
            }

            float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.mob.yRot = this.rotlerp(this.mob.yRot, f, 90.0F);
            float f1;
            if (this.mob.isOnGround())
            {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            }
            else
            {
                f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
            }

            this.mob.setSpeed(f1);
            double d4 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            float f2 = (float)(-(MathHelper.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
            this.mob.xRot = this.rotlerp(this.mob.xRot, f2, (float)this.maxTurn);
            this.mob.setYya(d1 > 0.0D ? f1 : -f1);
        }
        else
        {
            if (!this.hoversInPlace)
            {
                this.mob.setNoGravity(false);
            }

            this.mob.setYya(2.0F);
            this.mob.setZza(2.0F);
        }
    }
}
