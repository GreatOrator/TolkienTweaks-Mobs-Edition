//package com.greatorator.tolkienmobs.entity.entityai.phase;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.pathfinding.Path;
//import net.minecraft.pathfinding.PathPoint;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Vec3d;
//
//import javax.annotation.Nullable;
//
//public class TTMPhaseLandingApproach extends TTMPhaseBase
//{
//    private Path currentPath;
//    private Vec3d targetLocation;
//
//    public TTMPhaseLandingApproach(EntityTMFellBeast fellbeastIn)
//    {
//        super(fellbeastIn);
//    }
//
//    public TTMPhaseList<TTMPhaseLandingApproach> getType()
//    {
//        return TTMPhaseList.LANDING_APPROACH;
//    }
//
//    /**
//     * Called when this phase is set to active
//     */
//    public void initPhase()
//    {
//        this.currentPath = null;
//        this.targetLocation = null;
//    }
//
//    /**
//     * Gives the phase a chance to update its status.
//     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
//     */
//    public void doLocalUpdate()
//    {
//        double d0 = this.targetLocation == null ? 0.0D : this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);
//
//        if (d0 < 100.0D || d0 > 22500.0D || this.fellbeast.collidedHorizontally || this.fellbeast.collidedVertically)
//        {
//            this.findNewTarget();
//        }
//    }
//
//    /**
//     * Returns the location the fellbeast is flying toward
//     */
//    @Nullable
//    public Vec3d getTargetLocation()
//    {
//        return this.targetLocation;
//    }
//
//    private void findNewTarget()
//    {
//        if (this.currentPath == null || this.currentPath.isFinished())
//        {
//            int i = this.fellbeast.initPathPoints();
//            BlockPos blockpos = this.fellbeast.world.getTopSolidOrLiquidBlock(new BlockPos(TTMConfig_Old.FellBeastX, TTMConfig_Old.FellBeastY, TTMConfig_Old.FellBeastZ));
//            PlayerEntity entityplayer = this.fellbeast.world.getNearestAttackablePlayer(blockpos, 128.0D, 128.0D);
//            int j;
//
//            if (entityplayer != null)
//            {
//                Vec3d vec3d = (new Vec3d(entityplayer.posX, 0.0D, entityplayer.posZ)).normalize();
//                j = this.fellbeast.getNearestPpIdx(-vec3d.x * 40.0D, 105.0D, -vec3d.z * 40.0D);
//            }
//            else
//            {
//                j = this.fellbeast.getNearestPpIdx(40.0D, (double)blockpos.getY(), 0.0D);
//            }
//
//            PathPoint pathpoint = new PathPoint(blockpos.getX(), blockpos.getY(), blockpos.getZ());
//            this.currentPath = this.fellbeast.findPath(i, j, pathpoint);
//
//            if (this.currentPath != null)
//            {
//                this.currentPath.incrementPathIndex();
//            }
//        }
//
//        this.navigateToNextPathNode();
//
//        if (this.currentPath != null && this.currentPath.isFinished())
//        {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.LANDING);
//        }
//    }
//
//    private void navigateToNextPathNode()
//    {
//        if (this.currentPath != null && !this.currentPath.isFinished())
//        {
//            Vec3d vec3d = this.currentPath.getCurrentPos();
//            this.currentPath.incrementPathIndex();
//            double d0 = vec3d.x;
//            double d1 = vec3d.z;
//            double d2;
//
//            while (true)
//            {
//                d2 = vec3d.y + (double)(this.fellbeast.getRNG().nextFloat() * 20.0F);
//
//                if (d2 >= vec3d.y)
//                {
//                    break;
//                }
//            }
//
//            this.targetLocation = new Vec3d(d0, d2, d1);
//        }
//    }
//}