package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.EntityCrebain;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/** Borrowed from Jabelar https://github.com/jabelar */
public class ProcessStateCrebain {
    EntityCrebain theCrebain;

    public float yawChangeRate = 1.5F;
    public float pitchChangeRate = 1.5F;
    public float targetPitch = 0.0F;

    public ProcessStateCrebain(EntityCrebain parCrebain)
    {
        theCrebain = parCrebain;
    }

    public void updateAITick()
    {
//        // DEBUG
//        System.out.println("State = "+getState());

        // regen
        if (theCrebain.isTamed())
        {
            if (theCrebain.ticksExisted%50 == 0 && theCrebain.isEntityAlive())
            {
                theCrebain.setHealth(theCrebain.getHealth()+1.0F);
            }
        }

        switch (theCrebain.getState())
        {
            case AIStates.STATE_PERCHED:
                processPerched();
                break;
            case AIStates.STATE_TAKING_OFF:
                processTakingOff();
                break;
            case AIStates.STATE_SOARING:
                processSoaring();
                break;
            case AIStates.STATE_DIVING:
                processDiving();
                break;
            case AIStates.STATE_LANDING:
                processLanding();
                break;
            case AIStates.STATE_TRAVELLING:
                processTravelling();
                break;
            case AIStates.STATE_ATTACKING:
                processAttacking();
                break;
            case AIStates.STATE_SOARING_TAMED:
                processSoaring();
                break;
            case AIStates.STATE_PERCHED_TAMED:
                processPerched();
                break;
            case AIStates.STATE_SEEKING:
                processSeeking();
                break;
            default:
                // DEBUG
                System.out.println("Unknown state");
                break;

        }
    }

    /**
     *
     */
    private void processSeeking()
    {
        updatePitch(0.0F);
        if (theCrebain.getAttackTarget() != null)
        {
            updateYaw(getYawFromVec(new Vec3d(
                    theCrebain.getAttackTarget().posX - theCrebain.posX,
                    theCrebain.getAttackTarget().posY - theCrebain.posY,
                    theCrebain.getAttackTarget().posZ - theCrebain.posZ)));
        }
        moveForward(1.0D);
    }

    /**
     * Process landing.
     */
    protected void processLanding()
    {
        updatePitch(Math.max(0.0F, theCrebain.rotationPitch-pitchChangeRate*3));
        theCrebain.motionX = theCrebain.getAnchor().getX() - theCrebain.posX;
        theCrebain.motionZ = theCrebain.getAnchor().getZ() - theCrebain.posZ;
        theCrebain.motionY = Math.min(-0.2F, theCrebain.motionY+0.2);
    }

    /**
     * Process diving.
     */
    protected void processDiving()
    {
        updatePitch(90.0F);
        theCrebain.motionX = theCrebain.getAnchor().getX() - theCrebain.posX;
        theCrebain.motionZ = theCrebain.getAnchor().getZ() - theCrebain.posZ;
        theCrebain.motionY = -1.0D;
    }

    /**
     * Process taking off.
     */
    protected void processTakingOff()
    {
        updatePitch(0.0F);

        // climb to soaring height
        if (theCrebain.posY < theCrebain.getSoarHeight())
        {
//        	// DEBUG
//        	if (theBird.ticksExisted%20 == 0)
//        	{
//        		System.out.println("processTakingOff setting upwards motion because has not reached soar height for entity ID = "+theBird.getEntityId());
//        	}

            theCrebain.motionY = 0.1D;
        }

        moveForward(1.0D);

        // turn
        if (theCrebain.getSoarClockwise())
        {
            updateYaw(theCrebain.rotationYaw + yawChangeRate);
        }
        else
        {
            updateYaw(theCrebain.rotationYaw - yawChangeRate);
        }
    }

    /**
     * Process perched.
     */
    protected void processPerched()
    {
        updatePitch(0.0F); // although the model is upright, want to make sure look vector and sense of motion preserved.
        processFrictionAndGravity();
//        stopMoving();
    }

    /**
     * Process soaring.
     */
    protected void processSoaring()
    {
        updatePitch(0.0F);

        // drift down slowly
        theCrebain.motionY = -0.01D;

        moveForward(1.0D);

        // turn
        if (theCrebain.isTamed())
        {
            // turn towards owner
            // got the dot product idea from https://github.com/chraft/c-raft/wiki/Vectors,-Location,-Yaw-and-Pitch-in-C%23raft
            Vec3d vecToOwner = new Vec3d(
                    theCrebain.getOwner().posX - theCrebain.posX,
                    0,
                    theCrebain.getOwner().posZ - theCrebain.posZ).normalize();
            if (theCrebain.getLookVec().dotProduct(vecToOwner) > 0.0D)
            {
                updateYaw(theCrebain.rotationYaw - yawChangeRate);
            }
            else
            {
                updateYaw(theCrebain.rotationYaw + yawChangeRate);
            }
        }
        else
        {
            if (theCrebain.getSoarClockwise())
            {
                updateYaw(theCrebain.rotationYaw + yawChangeRate);
            }
            else
            {
                updateYaw(theCrebain.rotationYaw - yawChangeRate);
            }
        }
    }

    /**
     * Process travelling.
     */
    protected void processTravelling()
    {
        updatePitch(0.0F);

        // climb to soaring height
        if (theCrebain.posY < theCrebain.getSoarHeight())
        {
            theCrebain.motionY = 0.1D;
        }

        moveForward(1.0D);
    }

    /**
     * Process attacking.
     */
    protected void processAttacking()
    {
        if (theCrebain.getAttackTarget() != null)
        {
            theCrebain.motionY = -2.0D;
            double ticksToHitTarget = (theCrebain.posY - theCrebain.getAttackTarget().posY) / Math.abs(theCrebain.motionY);
            theCrebain.motionX = (theCrebain.getAttackTarget().posX - theCrebain.posX) / ticksToHitTarget;
            theCrebain.motionZ = (theCrebain.getAttackTarget().posZ - theCrebain.posZ) / ticksToHitTarget;
            updatePitch(getPitchFromVec(new Vec3d(
                    theCrebain.motionX,
                    theCrebain.motionY,
                    theCrebain.motionZ)));
        }
    }

    public static float getYawFromVec(Vec3d parVec)
    {
        // The coordinate system for Minecraft is a bit backwards as explained
        // at https://github.com/chraft/c-raft/wiki/Vectors,-Location,-Yaw-and-Pitch-in-C%23raft
        return (float) -Math.toDegrees(Math.atan2(parVec.x, parVec.z));
    }

    /**
     * Gets the pitch from vec.
     *
     * @param parVec the par vec
     * @return the pitch from vec
     */
    public static float getPitchFromVec(Vec3d parVec)
    {
        // The coordinate system for Minecraft is a bit backwards as explained
        // at https://github.com/chraft/c-raft/wiki/Vectors,-Location,-Yaw-and-Pitch-in-C%23raft
        Vec3d theVec = parVec.normalize();
        return (float) Math.toDegrees(Math.asin(theVec.y));
    }

    public void moveForward(double parSpeedFactor)
    {
        theCrebain.motionX = theCrebain.getLookVec().x * parSpeedFactor * theCrebain.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        theCrebain.motionZ = theCrebain.getLookVec().z * parSpeedFactor * theCrebain.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }

    protected void stopMoving()
    {
        theCrebain.motionX = 0;
        theCrebain.motionY = 0;
        theCrebain.motionZ = 0;
    }

    protected void processFrictionAndGravity()
    {
        theCrebain.motionX *= 0.93D;
        theCrebain.motionZ *= 0.93D;
        theCrebain.motionY -= 0.04D;
    }

    protected void updateYaw(float parYaw)
    {
        float angleDiff = parYaw - theCrebain.rotationYaw;
        // handle possibility that shortest path is to rotate the other way
        if (angleDiff > 0.0001F)
        {
            if (angleDiff > 180.0F)
            {
                angleDiff -= 360.0F;
            }

            theCrebain.rotationYaw += yawChangeRate;
        }
        else if (angleDiff < -0.0001F)
        {
            if (angleDiff < -180.0F)
            {
                angleDiff += 360.0F;
            }

            theCrebain.rotationYaw -= yawChangeRate;
        }

        // clamp to avoid oscillation around target
        if (Math.abs(angleDiff) < yawChangeRate)
        {
            theCrebain.rotationYaw = parYaw;
        }
    }

    protected void updatePitch(float parPitch)
    {
        float angleDiff = parPitch - theCrebain.rotationPitch;
        // handle possibility that shortest path is to rotate the other way
        if (angleDiff > 0.0001F)
        {
            if (angleDiff > 180.0F)
            {
                angleDiff -= 360.0F;
            }

            theCrebain.rotationPitch += pitchChangeRate;
        }
        else if (angleDiff < -0.0001F)
        {
            if (angleDiff < -180.0F)
            {
                angleDiff += 360.0F;
            }

            theCrebain.rotationPitch -= pitchChangeRate;
        }
        if (Math.abs(angleDiff) < pitchChangeRate)
        {
            theCrebain.rotationPitch = parPitch;
        }
    }

    public static AxisAlignedBB copyBoundingBox(AxisAlignedBB aabbIn)
    {
        return new AxisAlignedBB(aabbIn.minX, aabbIn.minY, aabbIn.minZ, aabbIn.maxX, aabbIn.maxY, aabbIn.maxZ);
    }

    public static boolean isSuitableTarget(EntityLivingBase theAttackerEntity,
                                           EntityLivingBase parPossibleTargetEntity,
                                           boolean parShouldCheckSight)
    {
        if (parPossibleTargetEntity == null)
        {
            return false;
        }
        else if (parPossibleTargetEntity == theAttackerEntity)
        {
            return false;
        }
        else if (!parPossibleTargetEntity.isEntityAlive())
        {
            return false;
        }
        else if (theAttackerEntity.isOnSameTeam(parPossibleTargetEntity))
        {
            return false;
        }
        else if (theAttackerEntity instanceof EntityLiving && parShouldCheckSight)
        {
            return ((EntityLiving)theAttackerEntity).getEntitySenses().canSee(parPossibleTargetEntity);
        }
        else
        {
            return true;
        }
    }

    public static boolean isCourseTraversable(Entity parEntity, double parX, double parY, double parZ)
    {
        double theDistance = MathHelper.sqrt(parX * parX + parY * parY + parZ * parZ);

        double incrementX = (parX - parEntity.posX) / theDistance;
        double incrementY = (parY - parEntity.posY) / theDistance;
        double incrementZ = (parZ - parEntity.posZ) / theDistance;
        AxisAlignedBB entityBoundingBox = copyBoundingBox(parEntity.getEntityBoundingBox());

        for (int i = 1; i < theDistance; ++i)
        {
            entityBoundingBox.offset(incrementX, incrementY, incrementZ);

            if (!parEntity.world.getCollisionBoxes(parEntity, entityBoundingBox).isEmpty())
            {
                return false;
            }
        }

        return true;
    }
}