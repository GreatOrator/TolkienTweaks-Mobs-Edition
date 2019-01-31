package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;

public class TTMPhaseManager
{
    private final EntityFellBeast fellbeast;
    private final ITTMPhase[] phases = new ITTMPhase[TTMPhaseList.getTotalPhases()];
    private ITTMPhase phase;

    public TTMPhaseManager(EntityFellBeast fellbeastIn)
    {
        this.fellbeast = fellbeastIn;
        this.setPhase(TTMPhaseList.HOLDING_PATTERN);
    }

    public void setPhase(TTMPhaseList<?> phaseIn)
    {
        if (this.phase == null || phaseIn != this.phase.getType())
        {
            if (this.phase != null)
            {
                this.phase.removeAreaEffect();
            }

            this.phase = this.getPhase(phaseIn);

            if (!this.fellbeast.world.isRemote)
            {
                this.fellbeast.getDataManager().set(EntityFellBeast.PHASE, Integer.valueOf(phaseIn.getId()));
            }

            LogHelperTTM.debug("FellBeast is now in phase {} on the {}", phaseIn, this.fellbeast.world.isRemote ? "client" : "server");
            this.phase.initPhase();
        }
    }

    public ITTMPhase getCurrentPhase()
    {
        return this.phase;
    }

    public <T extends ITTMPhase> T getPhase(TTMPhaseList<T> phaseIn)
    {
        int i = phaseIn.getId();

        if (this.phases[i] == null)
        {
            this.phases[i] = phaseIn.createPhase(this.fellbeast);
        }

        return (T)this.phases[i];
    }
}