package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.init.SoundInit;

public class TTMPhaseSittingAttacking extends TTMPhaseSittingBase
{
    private int attackingTicks;

    public TTMPhaseSittingAttacking(EntityFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by fellbeast's onLivingUpdate. Only used when worldObj.isRemote.
     */
    public void doClientRenderEffects()
    {
        this.fellbeast.world.playSound(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ, SoundInit.soundIdleFellBeast, this.fellbeast.getSoundCategory(), 2.5F, 0.8F + this.fellbeast.getRNG().nextFloat() * 0.3F, false);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.attackingTicks++ >= 40)
        {
            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.SITTING_FLAMING);
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.attackingTicks = 0;
    }

    public TTMPhaseList<TTMPhaseSittingAttacking> getType()
    {
        return TTMPhaseList.SITTING_ATTACKING;
    }
}