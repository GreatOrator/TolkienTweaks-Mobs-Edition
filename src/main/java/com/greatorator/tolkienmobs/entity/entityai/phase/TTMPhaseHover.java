package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseHover extends TTMPhaseBase
{
    private Vec3d targetLocation;

    public TTMPhaseHover(EntityTMFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.targetLocation == null)
        {
            this.targetLocation = new Vec3d(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);
        }
    }

    public boolean getIsStationary()
    {
        return true;
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.targetLocation = null;
    }

    /**
     * Returns the maximum amount fellbeast may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 1.0F;
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    public TTMPhaseList<TTMPhaseHover> getType()
    {
        return TTMPhaseList.HOVER;
    }
}