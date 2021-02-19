//package com.greatorator.tolkienmobs.entity.entityai;
//
//import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.EntityAITarget;
//import net.minecraft.entity.monster.EntityCreeper;
//import net.minecraft.village.Village;
//
//public class EntityAIMithrilDefendVillage extends EntityAITarget
//{
//    EntityTMMithrilGolem irongolem;
//    /** The aggressor of the iron golem's village which is now the golem's attack target. */
//    LivingEntity villageAgressorTarget;
//
//    public EntityAIMithrilDefendVillage(EntityTMMithrilGolem mithrilGolemIn)
//    {
//        super(mithrilGolemIn, false, true);
//        this.irongolem = mithrilGolemIn;
//        this.setMutexBits(1);
//    }
//
//    /**
//     * Returns whether the EntityAIBase should begin execution.
//     */
//    public boolean shouldExecute()
//    {
//        Village village = this.irongolem.getVillage();
//
//        if (village == null)
//        {
//            return false;
//        }
//        else
//        {
//            this.villageAgressorTarget = village.findNearestVillageAggressor(this.irongolem);
//
//            if (this.villageAgressorTarget instanceof EntityCreeper)
//            {
//                return false;
//            }
//            else if (this.isSuitableTarget(this.villageAgressorTarget, false))
//            {
//                return true;
//            }
//            else if (this.taskOwner.getRNG().nextInt(20) == 0)
//            {
//                this.villageAgressorTarget = village.getNearestTargetPlayer(this.irongolem);
//                return this.isSuitableTarget(this.villageAgressorTarget, false);
//            }
//            else
//            {
//                return false;
//            }
//        }
//    }
//
//    /**
//     * Execute a one shot task or start executing a continuous task
//     */
//    public void startExecuting()
//    {
//        this.irongolem.setAttackTarget(this.villageAgressorTarget);
//        super.startExecuting();
//    }
//}