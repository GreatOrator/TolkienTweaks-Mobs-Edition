package com.greatorator.tolkienmobs.entity.entityai.phase;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.boss.EntityTMFellBeast;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class TTMPhaseTakeoff extends TTMPhaseBase
{
    private boolean firstTick;
    private Path currentPath;
    private Vec3d targetLocation;

    public TTMPhaseTakeoff(EntityTMFellBeast fellbeastIn)
    {
        super(fellbeastIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by fellbeast's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (!this.firstTick && this.currentPath != null)
        {
            BlockPos blockpos = this.fellbeast.world.getTopSolidOrLiquidBlock(new BlockPos(TTMConfig.FellBeastX, TTMConfig.FellBeastY, TTMConfig.FellBeastZ));
            double d0 = this.fellbeast.getDistanceSqToCenter(blockpos);

            if (d0 > 100.0D)
            {
                this.fellbeast.getFellBeastPhaseManager().setPhase(TTMPhaseList.HOLDING_PATTERN);
            }
        }
        else
        {
            this.firstTick = false;
            this.findNewTarget();
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.firstTick = true;
        this.currentPath = null;
        this.targetLocation = null;
    }

    private void findNewTarget()
    {
        int i = this.fellbeast.initPathPoints();
        Vec3d vec3d = this.fellbeast.getHeadLookVec(1.0F);
        int j = this.fellbeast.getNearestPpIdx(-vec3d.x * 40.0D, 105.0D, -vec3d.z * 40.0D);

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
            this.navigateToNextPathNode();
        }
    }

    private void navigateToNextPathNode()
    {
        Vec3d vec3d = this.currentPath.getCurrentPos();
        this.currentPath.incrementPathIndex();
        double d0;

        while (true)
        {
            d0 = vec3d.y + (double)(this.fellbeast.getRNG().nextFloat() * 20.0F);

            if (d0 >= vec3d.y)
            {
                break;
            }
        }

        this.targetLocation = new Vec3d(vec3d.x, d0, vec3d.z);
    }

    /**
     * Returns the location the fellbeast is flying toward
     */
    @Nullable
    public Vec3d getTargetLocation()
    {
        return this.targetLocation;
    }

    public TTMPhaseList<TTMPhaseTakeoff> getType()
    {
        return TTMPhaseList.TAKEOFF;
    }
}