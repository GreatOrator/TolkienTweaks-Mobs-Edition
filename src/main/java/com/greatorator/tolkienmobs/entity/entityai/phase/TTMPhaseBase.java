package com.greatorator.tolkienmobs.entity.entityai.phase;

import net.minecraft.entity.MultiPartEntityPart;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public abstract class TTMPhaseBase implements ITTMPhase
{
    protected final EntityFellBeast fellbeast;

    public TTMPhaseBase(EntityFellBeast fellbeastIn)
    {
        this.fellbeast = fellbeastIn;
    }

    public boolean getIsStationary()
    {
        return false;
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void doClientRenderEffects()
    {
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
    }

    public void onCrystalDestroyed(EntityEnderCrystal crystal, BlockPos pos, DamageSource dmgSrc, @Nullable EntityPlayer plyr)
    {
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
    }

    public void removeAreaEffect()
    {
    }

    /**
     * Returns the maximum amount fellbeast may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 0.6F;
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return null;
    }

    /**
     * Normally, just returns damage. If fellbeast is sitting and src is an arrow, arrow is enflamed and zero damage
     * returned.
     */
    public float getAdjustedDamage(MultiPartEntityPart pt, DamageSource src, float damage)
    {
        return damage;
    }

    public float getYawFactor()
    {
        float f = MathHelper.sqrt(this.fellbeast.motionX * this.fellbeast.motionX + this.fellbeast.motionZ * this.fellbeast.motionZ) + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return 0.7F / f1 / f;
    }
}