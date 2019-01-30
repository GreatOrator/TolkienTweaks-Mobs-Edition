package com.greatorator.tolkienmobs.entity.entityai.phase;

import net.minecraft.entity.EntityAreaEffectCloud;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class TTMPhaseSittingFlaming extends TTMPhaseSittingBase
{
    private int flameTicks;
    private int flameCount;
    private EntityAreaEffectCloud areaEffectCloud;

    public TTMPhaseSittingFlaming(EntityFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void doClientRenderEffects()
    {
        ++this.flameTicks;

        if (this.flameTicks % 2 == 0 && this.flameTicks < 10)
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

                for (int j = 0; j < 6; ++j)
                {
                    this.fellbeast.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3d.x * 0.07999999821186066D * (double)j, -vec3d.y * 0.6000000238418579D, -vec3d.z * 0.07999999821186066D * (double)j);
                }

                vec3d.rotateYaw(0.19634955F);
            }
        }
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        ++this.flameTicks;

        if (this.flameTicks >= 200)
        {
            if (this.flameCount >= 4)
            {
                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.TAKEOFF);
            }
            else
            {
                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.SITTING_SCANNING);
            }
        }
        else if (this.flameTicks == 10)
        {
            Vec3d vec3d = (new Vec3d(this.fellbeast.fellbeastPartHead.posX - this.fellbeast.posX, 0.0D, this.fellbeast.fellbeastPartHead.posZ - this.fellbeast.posZ)).normalize();
            float f = 5.0F;
            double d0 = this.fellbeast.fellbeastPartHead.posX + vec3d.x * 5.0D / 2.0D;
            double d1 = this.fellbeast.fellbeastPartHead.posZ + vec3d.z * 5.0D / 2.0D;
            double d2 = this.fellbeast.fellbeastPartHead.posY + (double)(this.fellbeast.fellbeastPartHead.height / 2.0F);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(d0), MathHelper.floor(d2), MathHelper.floor(d1));

            while (this.fellbeast.world.isAirBlock(blockpos$mutableblockpos) && d2 >= 0) //Forge: Fix infinite loop if ground is missing.
            {
                --d2;
                blockpos$mutableblockpos.setPos(MathHelper.floor(d0), MathHelper.floor(d2), MathHelper.floor(d1));
            }

            d2 = (double)(MathHelper.floor(d2) + 1);
            this.areaEffectCloud = new EntityAreaEffectCloud(this.fellbeast.world, d0, d2, d1);
            this.areaEffectCloud.setOwner(this.fellbeast);
            this.areaEffectCloud.setRadius(5.0F);
            this.areaEffectCloud.setDuration(200);
            this.areaEffectCloud.setParticle(EnumParticleTypes.DRAGON_BREATH);
            this.areaEffectCloud.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE));
            this.fellbeast.world.spawnEntity(this.areaEffectCloud);
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.flameTicks = 0;
        ++this.flameCount;
    }

    public void removeAreaEffect()
    {
        if (this.areaEffectCloud != null)
        {
            this.areaEffectCloud.setDead();
            this.areaEffectCloud = null;
        }
    }

    public TTMPhaseList<TTMPhaseSittingFlaming> getType()
    {
        return TTMPhaseList.SITTING_FLAMING;
    }

    public void resetFlameCount()
    {
        this.flameCount = 0;
    }
}