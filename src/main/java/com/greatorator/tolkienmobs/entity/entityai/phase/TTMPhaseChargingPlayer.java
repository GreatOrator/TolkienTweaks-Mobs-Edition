package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class TTMPhaseChargingPlayer extends TTMPhaseBase
{
    private static final Logger LOGGER = LogManager.getLogger();
    private Vec3d targetLocation;
    private int timeSinceCharge;

    public TTMPhaseChargingPlayer(EntityTMFellBeast fellbeastIn)
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
            LOGGER.warn("Aborting charge player as no target was set.");
            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
        }
        else if (this.timeSinceCharge > 0 && this.timeSinceCharge++ >= 10)
        {
            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
        }
        else
        {
            double d0 = this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);

            if (d0 < 100.0D || d0 > 22500.0D || this.fellbeast.collidedHorizontally || this.fellbeast.collidedVertically)
            {
                ++this.timeSinceCharge;
            }
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.targetLocation = null;
        this.timeSinceCharge = 0;
    }

    public void setTarget(Vec3d p_188668_1_)
    {
        this.targetLocation = p_188668_1_;
    }

    /**
     * Returns the maximum amount fellbeast may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 3.0F;
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    public TTMPhaseList<TTMPhaseChargingPlayer> getType()
    {
        return TTMPhaseList.CHARGING_PLAYER;
    }
}