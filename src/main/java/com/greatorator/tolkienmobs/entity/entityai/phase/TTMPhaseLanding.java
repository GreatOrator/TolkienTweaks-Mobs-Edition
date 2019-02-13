package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseLanding extends TTMPhaseBase
{
    private Vec3d targetLocation;

    public TTMPhaseLanding(EntityFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void doClientRenderEffects()
    {
        Vec3d vec3d = this.fellbeast.getHeadLookVec(1.0F).normalize();
        vec3d.rotateYaw(-((float)Math.PI / 4F));
        double d0 = this.fellbeast.fellbeastPartHead.posX;
        double d1 = this.fellbeast.fellbeastPartHead.posY + (double)(this.fellbeast.fellbeastPartHead.height / 2.0F);
        double d2 = this.fellbeast.fellbeastPartHead.posZ;

        for (int i = 0; i < 8; ++i)
        {
            double d3 = d0 + this.fellbeast.getRNG().nextGaussian() / 2.0D;
            double d4 = d1 + this.fellbeast.getRNG().nextGaussian() / 2.0D;
            double d5 = d2 + this.fellbeast.getRNG().nextGaussian() / 2.0D;
            this.fellbeast.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3d.x * 0.07999999821186066D + this.fellbeast.motionX, -vec3d.y * 0.30000001192092896D + this.fellbeast.motionY, -vec3d.z * 0.07999999821186066D + this.fellbeast.motionZ);
            vec3d.rotateYaw(0.19634955F);
        }
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.targetLocation == null)
        {
            this.targetLocation = new Vec3d(this.fellbeast.world.getTopSolidOrLiquidBlock(new BlockPos(-246, 40, 554)));
        }

        if (this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ) < 1.0D)
        {
            ((TTMPhaseSittingFlaming)this.fellbeast.getFellBeastPhaseManager().getPhase(TTMPhaseList.SITTING_FLAMING)).resetFlameCount();
            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.SITTING_SCANNING);
        }
    }

    /**
     * Returns the maximum amount fellbeast may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 1.5F;
    }

    public float getYawFactor()
    {
        float f = MathHelper.sqrt(this.fellbeast.motionX * this.fellbeast.motionX + this.fellbeast.motionZ * this.fellbeast.motionZ) + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return f1 / f;
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.targetLocation = null;
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    public TTMPhaseList<TTMPhaseLanding> getType()
    {
        return TTMPhaseList.LANDING;
    }
}