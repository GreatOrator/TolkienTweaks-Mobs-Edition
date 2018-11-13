package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.EntityMordorOrc;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIOrcAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityMordorOrc Orc;

    public EntityAIOrcAttack(EntityMordorOrc zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.Orc = zombieIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.Orc.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.Orc.setArmsRaised(true);
        } else {
            this.Orc.setArmsRaised(false);
        }
    }
}
