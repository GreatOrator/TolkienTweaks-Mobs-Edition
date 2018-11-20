package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.EntityTroll;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAITrollAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityTroll Troll;

    public EntityAITrollAttack(EntityTroll zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.Troll = zombieIn;
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
        this.Troll.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.Troll.setArmsRaised(true);
        } else {
            this.Troll.setArmsRaised(false);
        }
    }
}
