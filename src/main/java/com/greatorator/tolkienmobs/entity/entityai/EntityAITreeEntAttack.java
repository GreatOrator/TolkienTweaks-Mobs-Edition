package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.EntityTreeEnt;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAITreeEntAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityTreeEnt treeEnt;

    public EntityAITreeEntAttack(EntityTreeEnt zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.treeEnt = zombieIn;
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
        this.treeEnt.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.treeEnt.setArmsRaised(true);
        } else {
            this.treeEnt.setArmsRaised(false);
        }
    }
}
