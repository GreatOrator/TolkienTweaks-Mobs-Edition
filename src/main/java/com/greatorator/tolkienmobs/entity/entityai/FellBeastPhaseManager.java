package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseList;

public class FellBeastPhaseManager {
    private final EntityFellBeast fellbeast;
    private final IPhase[] phases = new IPhase[PhaseList.getTotalPhases()];
    private IPhase phase;

    public FellBeastPhaseManager(EntityFellBeast fellbeastIn)
    {
        this.fellbeast = fellbeastIn;
        this.setPhase(PhaseList.HOVER);
    }

    public void setPhase(PhaseList<?> phaseIn)
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

            LogHelperTTM.debug("Dragon is now in phase {} on the {}", phaseIn, this.fellbeast.world.isRemote ? "client" : "server");
            this.phase.initPhase();
        }
    }

    public IPhase getCurrentPhase()
    {
        return this.phase;
    }

    public <T extends IPhase> T getPhase(PhaseList<T> phaseIn)
    {
        int i = phaseIn.getId();

        if (this.phases[i] == null)
        {
            this.phases[i] = phaseIn.createPhase(this.fellbeast);
        }

        return (T)this.phases[i];
    }
}
