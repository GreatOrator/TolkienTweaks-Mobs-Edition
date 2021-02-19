//package com.greatorator.tolkienmobs.entity.entityai;
//
//import net.minecraft.entity.EntityCreature;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.EntityAIBase;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.pathfinding.Path;
//import net.minecraft.pathfinding.PathPoint;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class EntityAITTMAttack extends EntityAIBase {
//    protected EntityCreature TTMAttacker;
//    World world;
//    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
//    protected int attackTick;
//    /** The speed with which the mob will approach the target */
//    double speedTowardsTarget;
//    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
//    boolean longMemory;
//    /** The PathEntity of our entity. */
//    Path path;
//    private int delayCounter;
//    private double targetX;
//    private double targetY;
//    private double targetZ;
//    protected final int attackInterval = 20;
//    private int failedPathFindingPenalty = 0;
//    private boolean canPenalize = false;
//
//    public EntityAITTMAttack(EntityCreature TTMCreature, double speedIn, boolean longMemoryIn) {
//        this.TTMAttacker = TTMCreature;
//        this.world = TTMCreature.world;
//        this.speedTowardsTarget = speedIn;
//        this.longMemory = longMemoryIn;
//        this.setMutexBits(3);
//    }
//
//    public boolean shouldContinueExecuting()
//    {
//        LivingEntity LivingEntity = this.TTMAttacker.getAttackTarget();
//
//        if (LivingEntity == null)
//        {
//            return false;
//        }
//        else if (!LivingEntity.isEntityAlive())
//        {
//            return false;
//        }
//        else if (!this.longMemory)
//        {
//            return !this.TTMAttacker.getNavigator().noPath();
//        }
//        else if (!this.TTMAttacker.isWithinHomeDistanceFromPosition(new BlockPos(LivingEntity)))
//        {
//            return false;
//        }
//        else
//        {
//            return !(LivingEntity instanceof PlayerEntity) || !((PlayerEntity)LivingEntity).isSpectator() && !((PlayerEntity)LivingEntity).isCreative();
//        }
//    }
//    public boolean shouldExecute()
//    {
//        LivingEntity LivingEntity = this.TTMAttacker.getAttackTarget();
//
//        if (LivingEntity == null)
//        {
//            return false;
//        }
//        else if (!LivingEntity.isEntityAlive())
//        {
//            return false;
//        }
//        else
//        {
//            if (canPenalize)
//            {
//                if (--this.delayCounter <= 0)
//                {
//                    this.path = this.TTMAttacker.getNavigator().getPathToEntityLiving(LivingEntity);
//                    this.delayCounter = 4 + this.TTMAttacker.getRNG().nextInt(7);
//                    return this.path != null;
//                }
//                else
//                {
//                    return true;
//                }
//            }
//            this.path = this.TTMAttacker.getNavigator().getPathToEntityLiving(LivingEntity);
//
//            if (this.path != null)
//            {
//                return true;
//            }
//            else
//            {
//                return this.getAttackReachSqr(LivingEntity) >= this.TTMAttacker.getDistanceSq(LivingEntity.posX, LivingEntity.getEntityBoundingBox().minY, LivingEntity.posZ);
//            }
//        }
//    }
//
//    /**
//     * Execute a one shot task or start executing a continuous task
//     */
//    @Override
//    public void startExecuting() {
//        this.TTMAttacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
//        this.delayCounter = 0;
//    }
//
//    /**
//     * Resets the task
//     */
//    @Override
//    public void resetTask() {
//        LivingEntity LivingEntity = this.TTMAttacker.getAttackTarget();
//
//        if (LivingEntity instanceof PlayerEntity && (((PlayerEntity)LivingEntity).isSpectator() || ((PlayerEntity)LivingEntity).isCreative()))
//        {
//            this.TTMAttacker.setAttackTarget((LivingEntity)null);
//        }
//
//        this.TTMAttacker.getNavigator().clearPath();
//    }
//
//    /**
//     * Updates the task
//     */
//    @Override
//    public void updateTask() {
//        LivingEntity LivingEntity = this.TTMAttacker.getAttackTarget();
//        this.TTMAttacker.getLookHelper().setLookPositionWithEntity(LivingEntity, 30.0F, 30.0F);
//        double d0 = this.TTMAttacker.getDistanceSq(LivingEntity.posX, LivingEntity.getEntityBoundingBox().minY, LivingEntity.posZ);
//        --this.delayCounter;
//
//        if ((this.longMemory || this.TTMAttacker.getEntitySenses().canSee(LivingEntity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || LivingEntity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.TTMAttacker.getRNG().nextFloat() < 0.05F))
//        {
//            this.targetX = LivingEntity.posX;
//            this.targetY = LivingEntity.getEntityBoundingBox().minY;
//            this.targetZ = LivingEntity.posZ;
//            this.delayCounter = 4 + this.TTMAttacker.getRNG().nextInt(7);
//
//            if (this.canPenalize)
//            {
//                this.delayCounter += failedPathFindingPenalty;
//                if (this.TTMAttacker.getNavigator().getPath() != null)
//                {
//                    PathPoint finalPathPoint = this.TTMAttacker.getNavigator().getPath().getFinalPathPoint();
//                    if (finalPathPoint != null && LivingEntity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                        failedPathFindingPenalty = 0;
//                    else
//                        failedPathFindingPenalty += 10;
//                }
//                else
//                {
//                    failedPathFindingPenalty += 10;
//                }
//            }
//
//            if (d0 > 1024.0D)
//            {
//                this.delayCounter += 10;
//            }
//            else if (d0 > 256.0D)
//            {
//                this.delayCounter += 5;
//            }
//
//            if (!this.TTMAttacker.getNavigator().tryMoveToEntityLiving(LivingEntity, this.speedTowardsTarget))
//            {
//                this.delayCounter += 15;
//            }
//        }
//
//        this.attackTick = Math.max(this.attackTick - 1, 0);
//        this.checkAndPerformAttack(LivingEntity, d0);
//    }
//
//    protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_)
//    {
//        double d0 = this.getAttackReachSqr(p_190102_1_);
//
//        if (p_190102_2_ <= d0 && this.attackTick <= 0)
//        {
//            this.attackTick = 20;
//            this.TTMAttacker.swingArm(EnumHand.MAIN_HAND);
//            this.TTMAttacker.attackEntityAsMob(p_190102_1_);
//        }
//    }
//
//    protected double getAttackReachSqr(LivingEntity attackTarget)
//    {
//        return (double)(this.TTMAttacker.width * 2.0F * this.TTMAttacker.width * 2.0F + attackTarget.width);
//    }
//}