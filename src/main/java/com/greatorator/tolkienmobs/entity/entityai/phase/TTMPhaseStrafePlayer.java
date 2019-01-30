package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.EntityLivingBase;
import com.greatorator.tolkienmobs.entity.boss.EntityFellBeast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseStrafePlayer extends TTMPhaseBase
{
    private int fireballCharge;
    private Path currentPath;
    private Vec3d targetLocation;
    private EntityLivingBase attackTarget;
    private boolean holdingPatternClockwise;

    public TTMPhaseStrafePlayer(EntityFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.attackTarget == null)
        {
            LogHelperTTM.warn("Skipping player strafe phase because no player was found");
            this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
        }
        else
        {
            if (this.currentPath != null && this.currentPath.isFinished())
            {
                double d0 = this.attackTarget.posX;
                double d1 = this.attackTarget.posZ;
                double d2 = d0 - this.fellbeast.posX;
                double d3 = d1 - this.fellbeast.posZ;
                double d4 = (double)MathHelper.sqrt(d2 * d2 + d3 * d3);
                double d5 = Math.min(0.4000000059604645D + d4 / 80.0D - 1.0D, 10.0D);
                this.targetLocation = new Vec3d(d0, this.attackTarget.posY + d5, d1);
            }

            double d12 = this.targetLocation == null ? 0.0D : this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);

            if (d12 < 100.0D || d12 > 22500.0D)
            {
                this.findNewTarget();
            }

            double d13 = 64.0D;

            if (this.attackTarget.getDistanceSq(this.fellbeast) < 4096.0D)
            {
                if (this.fellbeast.canEntityBeSeen(this.attackTarget))
                {
                    ++this.fireballCharge;
                    Vec3d vec3d1 = (new Vec3d(this.attackTarget.posX - this.fellbeast.posX, 0.0D, this.attackTarget.posZ - this.fellbeast.posZ)).normalize();
                    Vec3d vec3d = (new Vec3d((double)MathHelper.sin(this.fellbeast.rotationYaw * 0.017453292F), 0.0D, (double)(-MathHelper.cos(this.fellbeast.rotationYaw * 0.017453292F)))).normalize();
                    float f1 = (float)vec3d.dotProduct(vec3d1);
                    float f = (float)(Math.acos((double)f1) * (180D / Math.PI));
                    f = f + 0.5F;

                    if (this.fireballCharge >= 5 && f >= 0.0F && f < 10.0F)
                    {
                        double d14 = 1.0D;
                        Vec3d vec3d2 = this.fellbeast.getLook(1.0F);
                        double d6 = this.fellbeast.fellbeastPartHead.posX - vec3d2.x * 1.0D;
                        double d7 = this.fellbeast.fellbeastPartHead.posY + (double)(this.fellbeast.fellbeastPartHead.height / 2.0F) + 0.5D;
                        double d8 = this.fellbeast.fellbeastPartHead.posZ - vec3d2.z * 1.0D;
                        double d9 = this.attackTarget.posX - d6;
                        double d10 = this.attackTarget.posY + (double)(this.attackTarget.height / 2.0F) - (d7 + (double)(this.fellbeast.fellbeastPartHead.height / 2.0F));
                        double d11 = this.attackTarget.posZ - d8;
                        this.fellbeast.world.playEvent((EntityPlayer)null, 1017, new BlockPos(this.fellbeast), 0);
                        EntityFellBeastFireball entityfellbeastfireball = new EntityFellBeastFireball(this.fellbeast.world, this.fellbeast, d9, d10, d11);
                        entityfellbeastfireball.setLocationAndAngles(d6, d7, d8, 0.0F, 0.0F);
                        this.fellbeast.world.spawnEntity(entityfellbeastfireball);
                        this.fireballCharge = 0;

                        if (this.currentPath != null)
                        {
                            while (!this.currentPath.isFinished())
                            {
                                this.currentPath.incrementPathIndex();
                            }
                        }

                        this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
                    }
                }
                else if (this.fireballCharge > 0)
                {
                    --this.fireballCharge;
                }
            }
            else if (this.fireballCharge > 0)
            {
                --this.fireballCharge;
            }
        }
    }

    private void findNewTarget()
    {
        if (this.currentPath == null || this.currentPath.isFinished())
        {
            int i = this.fellbeast.initPathPoints();
            int j = i;

            if (this.fellbeast.getRNG().nextInt(8) == 0)
            {
                this.holdingPatternClockwise = !this.holdingPatternClockwise;
                j = i + 6;
            }

            if (this.holdingPatternClockwise)
            {
                ++j;
            }
            else
            {
                --j;
            }

            if (this.fellbeast.getFellBeastFightManager() != null && this.fellbeast.getFellBeastFightManager().getNumAliveCrystals() > 0)
            {
                j = j % 12;

                if (j < 0)
                {
                    j += 12;
                }
            }
            else
            {
                j = j - 12;
                j = j & 7;
                j = j + 12;
            }

            this.currentPath = this.fellbeast.findPath(i, j, (PathPoint)null);

            if (this.currentPath != null)
            {
                this.currentPath.incrementPathIndex();
            }
        }

        this.navigateToNextPathNode();
    }

    private void navigateToNextPathNode()
    {
        if (this.currentPath != null && !this.currentPath.isFinished())
        {
            Vec3d vec3d = this.currentPath.getCurrentPos();
            this.currentPath.incrementPathIndex();
            double d0 = vec3d.x;
            double d2 = vec3d.z;
            double d1;

            while (true)
            {
                d1 = vec3d.y + (double)(this.fellbeast.getRNG().nextFloat() * 20.0F);

                if (d1 >= vec3d.y)
                {
                    break;
                }
            }

            this.targetLocation = new Vec3d(d0, d1, d2);
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.fireballCharge = 0;
        this.targetLocation = null;
        this.currentPath = null;
        this.attackTarget = null;
    }

    public void setTarget(EntityLivingBase p_188686_1_)
    {
        this.attackTarget = p_188686_1_;
        int i = this.fellbeast.initPathPoints();
        int j = this.fellbeast.getNearestPpIdx(this.attackTarget.posX, this.attackTarget.posY, this.attackTarget.posZ);
        int k = MathHelper.floor(this.attackTarget.posX);
        int l = MathHelper.floor(this.attackTarget.posZ);
        double d0 = (double)k - this.fellbeast.posX;
        double d1 = (double)l - this.fellbeast.posZ;
        double d2 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1);
        double d3 = Math.min(0.4000000059604645D + d2 / 80.0D - 1.0D, 10.0D);
        int i1 = MathHelper.floor(this.attackTarget.posY + d3);
        PathPoint pathpoint = new PathPoint(k, i1, l);
        this.currentPath = this.fellbeast.findPath(i, j, pathpoint);

        if (this.currentPath != null)
        {
            this.currentPath.incrementPathIndex();
            this.navigateToNextPathNode();
        }
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    public TTMPhaseList<TTMPhaseStrafePlayer> getType()
    {
        return TTMPhaseList.STRAFE_PLAYER;
    }
}