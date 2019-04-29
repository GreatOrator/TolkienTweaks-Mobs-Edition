package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseDying extends TTMPhaseBase
{
    private Vec3d targetLocation;
    private int time;

    public TTMPhaseDying(EntityTMFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void doClientRenderEffects()
    {
        if (this.time++ % 10 == 0)
        {
            float f = (this.fellbeast.getRNG().nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.fellbeast.getRNG().nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.fellbeast.getRNG().nextFloat() - 0.5F) * 8.0F;
            this.fellbeast.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.fellbeast.posX + (double)f, this.fellbeast.posY + 2.0D + (double)f1, this.fellbeast.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        ++this.time;

        if (this.targetLocation == null)
        {
            BlockPos blockpos = this.fellbeast.world.getHeight(new BlockPos(TTMConfig.FellBeastX, TTMConfig.FellBeastY, TTMConfig.FellBeastZ));
            this.targetLocation = new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
        }

        double d0 = this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);

        if (d0 >= 100.0D && d0 <= 22500.0D && !this.fellbeast.collidedHorizontally && !this.fellbeast.collidedVertically)
        {
            this.fellbeast.setHealth(1.0F);
        }
        else
        {
            this.fellbeast.setHealth(0.0F);
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.targetLocation = null;
        this.time = 0;
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

    public TTMPhaseList<TTMPhaseDying> getType()
    {
        return TTMPhaseList.DYING;
    }
}