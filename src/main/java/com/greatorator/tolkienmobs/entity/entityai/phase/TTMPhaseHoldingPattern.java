package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseHoldingPattern extends TTMPhaseBase
{
    private Path currentPath;
    private Vec3d targetLocation;
    private boolean clockwise;

    public TTMPhaseHoldingPattern(EntityTMFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    public TTMPhaseList<TTMPhaseHoldingPattern> getType()
    {
        return TTMPhaseList.HOLDING_PATTERN;
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        double d0 = this.targetLocation == null ? 0.0D : this.targetLocation.squareDistanceTo(this.fellbeast.posX, this.fellbeast.posY, this.fellbeast.posZ);

        if (d0 < 100.0D || d0 > 22500.0D || this.fellbeast.collidedHorizontally || this.fellbeast.collidedVertically)
        {
            this.findNewTarget();
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.currentPath = null;
        this.targetLocation = null;
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    private void findNewTarget()
    {
        if (this.currentPath != null && this.currentPath.isFinished())
        {
            BlockPos blockpos = this.fellbeast.world.getTopSolidOrLiquidBlock(new BlockPos(-246, 40, 554));
            int i = this.fellbeast.getFellBeastFightManager() == null ? 0 : this.fellbeast.getFellBeastFightManager().getNumAliveCrystals();

            if (this.fellbeast.getRNG().nextInt(i + 3) == 0)
            {
                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.LANDING_APPROACH);
                return;
            }

            double d0 = 64.0D;
            EntityPlayer entityplayer = this.fellbeast.world.getNearestAttackablePlayer(blockpos, d0, d0);

            if (entityplayer != null)
            {
                d0 = entityplayer.getDistanceSqToCenter(blockpos) / 512.0D;
            }

            if (entityplayer != null && (this.fellbeast.getRNG().nextInt(MathHelper.abs((int)d0) + 2) == 0 || this.fellbeast.getRNG().nextInt(i + 2) == 0))
            {
                this.strafePlayer(entityplayer);
                return;
            }
        }

        if (this.currentPath == null || this.currentPath.isFinished())
        {
            int j = this.fellbeast.initPathPoints();
            int k = j;

            if (this.fellbeast.getRNG().nextInt(8) == 0)
            {
                this.clockwise = !this.clockwise;
                k = j + 6;
            }

            if (this.clockwise)
            {
                ++k;
            }
            else
            {
                --k;
            }

            if (this.fellbeast.getFellBeastFightManager() != null && this.fellbeast.getFellBeastFightManager().getNumAliveCrystals() >= 0)
            {
                k = k % 12;

                if (k < 0)
                {
                    k += 12;
                }
            }
            else
            {
                k = k - 12;
                k = k & 7;
                k = k + 12;
            }

            this.currentPath = this.fellbeast.findPath(j, k, (PathPoint)null);

            if (this.currentPath != null)
            {
                this.currentPath.incrementPathIndex();
            }
        }

        this.navigateToNextPathNode();
    }

    private void strafePlayer(EntityPlayer player)
    {
        this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.STRAFE_PLAYER);
        ((TTMPhaseStrafePlayer)this.fellbeast.getFellBeastPhaseManager().getPhase(TTMPhaseList.STRAFE_PLAYER)).setTarget(player);
    }

    private void navigateToNextPathNode()
    {
        if (this.currentPath != null && !this.currentPath.isFinished())
        {
            Vec3d vec3d = this.currentPath.getCurrentPos();
            this.currentPath.incrementPathIndex();
            double d0 = vec3d.x;
            double d1 = vec3d.z;
            double d2;

            while (true)
            {
                d2 = vec3d.y + (double)(this.fellbeast.getRNG().nextFloat() * 20.0F);

                if (d2 >= vec3d.y)
                {
                    break;
                }
            }

            this.targetLocation = new Vec3d(d0, d2, d1);
        }
    }

    public void onCrystalDestroyed(EntityEnderCrystal crystal, BlockPos pos, DamageSource dmgSrc, @Nullable EntityPlayer plyr)
    {
        if (plyr != null && !plyr.capabilities.disableDamage)
        {
            this.strafePlayer(plyr);
        }
    }
}