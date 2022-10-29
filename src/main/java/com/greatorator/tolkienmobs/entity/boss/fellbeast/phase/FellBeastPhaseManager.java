package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FellBeastPhaseManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private final FellBeastEntity fellbeast;
    private final IFellBeastPhase[] phases = new IFellBeastPhase[FellBeastPhaseType.getCount()];
    private IFellBeastPhase currentPhase;

    public FellBeastPhaseManager(FellBeastEntity entity) {
        this.fellbeast = entity;
        this.setPhase(FellBeastPhaseType.HOVERING);
    }

    public void setPhase(FellBeastPhaseType<?> p_188758_1_) {
        if (this.currentPhase == null || p_188758_1_ != this.currentPhase.getPhase()) {
            if (this.currentPhase != null) {
                this.currentPhase.end();
            }

            this.currentPhase = this.getPhase(p_188758_1_);
            if (!this.fellbeast.level.isClientSide) {
                this.fellbeast.getEntityData().set(FellBeastEntity.DATA_PHASE, p_188758_1_.getId());
            }

            LOGGER.debug("Fell Beast is now in phase {} on the {}", p_188758_1_, this.fellbeast.level.isClientSide ? "client" : "server");
            this.currentPhase.begin();
        }
    }

    public IFellBeastPhase getCurrentPhase() {
        return this.currentPhase;
    }

    public <T extends IFellBeastPhase> T getPhase(FellBeastPhaseType<T> phaseType) {
        int i = phaseType.getId();
        if (this.phases[i] == null) {
            this.phases[i] = phaseType.createInstance(this.fellbeast);
        }

        return (T)this.phases[i];
    }
}
