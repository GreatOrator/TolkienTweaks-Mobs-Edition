package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.EntityTMBirds;
import com.greatorator.tolkienmobs.utils.TTMUtilities;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class UpdateStateBirds {
    public EntityTMBirds theBird;
    public World theWorld;

    // defines area on ground that bird looks for prey in
    // region is double this size as this gives dimensions in each direction
    public double attackRegionSize = 5.0D;

    // the "one in" chance per tick that it will decide to perch if over a perchable block
    public final int PERCH_CHANCE_BASE = 1;
    // the percent chance per tick that when perched it will decide to take off
    public final int TAKE_OFF_CHANCE_BASE = 2400;

    public UpdateStateBirds(EntityTMBirds parBirdOfPrey)
    {
        theBird = parBirdOfPrey;
        theWorld = theBird.world;
    }

    public void updateAIState()
    {
        switch (theBird.getState())
        {
            case AIStates.STATE_PERCHED:
            {
                updateStatePerched();
                break;
            }
            case AIStates.STATE_TAKING_OFF:
            {
                updateStateTakingOff();
                break;
            }
            case AIStates.STATE_SOARING:
            {
                updateStateSoaring();
                break;
            }
            case AIStates.STATE_DIVING:
            {
                updateStateDiving();
                break;
            }
            case AIStates.STATE_LANDING:
            {
                updateStateLanding();
                break;
            }
            case AIStates.STATE_TRAVELLING:
            {
                updateStateTravelling();
                break;
            }
            case AIStates.STATE_ATTACKING:
            {
                updateStateAttacking();
                break;
            }
            case AIStates.STATE_SOARING_TAMED:
            {
                updateStateSoaringTamed();
                break;
            }
            case AIStates.STATE_PERCHED_TAMED:
            {
                updateStatePerchedTamed();
                break;
            }
            case AIStates.STATE_SEEKING:
            {
                updateStateSeeking();
                break;
            }
            default:
            {
                break;
            }
        }
    }

    private void updateStateSeeking()
    {
        if (theBird.isTamed())
        {
            processOwnerAttack();

            if (theBird.getAttackTarget() != null)
            {
                theBird.setState(AIStates.STATE_ATTACKING);
            }
            else
            {
                theBird.setState(AIStates.STATE_TRAVELLING);
            }
        }
        else
        {
        }
    }

    /**
     *
     */
    private void updateStatePerchedTamed()
    {
        if (!hasLanded())
        {
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else // still solidly perched
        {
            // can occasionally adjust or flap, look around, or play sound to create variety
            if (theBird.getRNG().nextInt(getTakeOffChance()) == 0)
            {
                theBird.setState(AIStates.STATE_TAKING_OFF);
                // rotationYawHead = rand.nextInt(360);
            }
        }
    }

    private void updateStateSoaringTamed()
    {
        // climb again if drifting too low
        if (theBird.posY < theBird.getSoarHeight()*0.9D)
        {
            // point towards owner
            theBird.rotationYaw = TTMUtilities.getYawFromVec(new Vec3d(
                    theBird.getOwner().posX - theBird.posX,
                    theBird.getOwner().posY - theBird.posY,
                    theBird.getOwner().posZ - theBird.posZ));
            theBird.setState(AIStates.STATE_TRAVELLING);
        }

        considerAttacking();

        if (theBird.getAttackTarget() == null)
        {
            considerPerching();
        }
        else
        {
            theBird.setState(AIStates.STATE_SEEKING);
        }
    }

    private void updateStateAttacking()
    {
        EntityLivingBase target = theBird.getAttackTarget();
        // check if target has been killed or despawned
        if (target == null)
        {
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (target.isDead)
        {
            theBird.setAttackTarget(null);
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (!TTMUtilities.isCourseTraversable(theBird, target.posX, target.posY, target.posZ))
        {
            // theBird.setAttackTarget(null);
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        // check for hitting target
        else if (theBird.getDistance(theBird.getAttackTarget())<2.0F)
        {
            theBird.getAttackTarget().attackEntityFrom(
                    DamageSource.causeMobDamage(theBird),
                    (float) theBird.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
    }

    private void updateStateTravelling()
    {
        if (theBird.posY >= theBird.getSoarHeight())
        {
            if (theBird.isTamed())
            {
                theBird.setState(AIStates.STATE_SOARING_TAMED);
            }
            else
            {
                theBird.setState(AIStates.STATE_SOARING);
            }
        }
    }

    private void updateStateLanding()
    {
        if (hasLanded())
        {
            if (theBird.isTamed())
            {
                theBird.setState(AIStates.STATE_PERCHED_TAMED);
            }
            else
            {
                theBird.setState(AIStates.STATE_PERCHED);
            }
        }
    }

    private void updateStateDiving()
    {
        BlockPos anchorPos = theBird.getAnchor();

        if (theBird.getDistanceSq(
                anchorPos.getX(),
                anchorPos.getY(),
                anchorPos.getZ())<25)
        {
            theBird.setState(AIStates.STATE_LANDING);
        }
        // see if made it to perch
        else if (hasLanded())
        {
            if (theBird.isTamed())
            {
                theBird.setState(AIStates.STATE_PERCHED_TAMED);
                stopMoving();
            }
            else
            {
                theBird.setState(AIStates.STATE_PERCHED);
                stopMoving();
            }
        }
    }

    private boolean hasLanded()
    {
        AxisAlignedBB entityBoundingBox = (TTMUtilities.copyBoundingBox(theBird.getEntityBoundingBox())).offset(0.0D, -0.5D, 0.0D);

        if (!theWorld.getCollisionBoxes(theBird, entityBoundingBox).isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void stopMoving()
    {
        theBird.motionX = 0;
        theBird.motionY = 0;
        theBird.motionZ = 0;
    }

    private void updateStateSoaring()
    {
        if (theBird.isTamed())
        {
            theBird.setState(AIStates.STATE_SOARING_TAMED);
        }
        else
        {
            // climb again if drifting too low
            if (theBird.posY < theBird.getSoarHeight()*0.9D)
            {
                theBird.setState(AIStates.STATE_TRAVELLING);
            }

            considerAttacking();

            if (theBird.getAttackTarget() == null)
            {
                considerPerching();
            }
            else
            {
                theBird.setState(AIStates.STATE_ATTACKING);
            }
        }
    }

    private void updateStateTakingOff()
    {
        if (theBird.posY >= theBird.getSoarHeight())
        {

            if (theBird.isTamed())
            {
                theBird.setState(AIStates.STATE_SOARING_TAMED);
            }
            else
            {
                theBird.setState(AIStates.STATE_SOARING);
            }
        }
    }

    private void updateStatePerched()
    {
        if (theBird.isTamed())
        {
            theBird.setState(AIStates.STATE_PERCHED_TAMED);
        }
        else if (!hasLanded())
        {
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else // still solidly perched
        {
            // can occasionally adjust or flap, look around, or play sound to create variety
            if (theBird.getRNG().nextInt(getTakeOffChance()) == 0)
            {
                theBird.setState(AIStates.STATE_TAKING_OFF);
                // rotationYawHead = rand.nextInt(360);
            }

            // entity can get scared if player gets too close
            EntityPlayer closestPlayer = theWorld.getClosestPlayerToEntity(theBird, 4.0D);
            if (closestPlayer != null)
            {
                ItemStack theHeldItemStack = closestPlayer.inventory.getCurrentItem();
                if (theHeldItemStack != null)
                {
                    // if not holding taming food, bird will get spooked
                    if (!theBird.isTamingFood(theHeldItemStack))
                    {
                        theBird.setState(AIStates.STATE_TAKING_OFF);
                    }
                }
            }
        }
    }

    public void considerPerching()
    {
        if (theBird.isTamed())
        {
            return;
        }
        else
        {
            // always try to perch starting at dusk
            if (theBird.getRNG().nextInt(getPerchChance()) == 0)
            {
                if (theWorld.getBlockState(theWorld.getTopSolidOrLiquidBlock(theBird.getPosition())) instanceof BlockLeaves)
                {
                    if (TTMUtilities.isCourseTraversable(
                            theBird,
                            theBird.posX,
                            theWorld.getHeight(
                                    (int)theBird.posX,
                                    (int)theBird.posZ),
                            theBird.posZ))
                    {
                        theBird.setState(AIStates.STATE_DIVING);
                        theBird.setAnchor(new BlockPos(
                                theBird.posX,
                                theWorld.getHeight(
                                        (int)theBird.posX,
                                        (int)theBird.posZ),
                                theBird.posZ));
                    }
                }
            }
        }
    }

    public int getPerchChance()
    {
        if (theWorld.isRaining())
        {
            return 1;
        }

        if (theBird.isNocturnal())
        {
            if (theWorld.isDaytime())
            {
                return PERCH_CHANCE_BASE;
            }
            else
            {
                return PERCH_CHANCE_BASE * 10000;
            }
        }
        else
        {
            if (theWorld.isDaytime())
            {
                return PERCH_CHANCE_BASE * 10000;
            }
            else
            {
                return PERCH_CHANCE_BASE;
            }
        }
    }

    public int getTakeOffChance()
    {
        if (theWorld.isRaining())
        {
            return TAKE_OFF_CHANCE_BASE * 1000;
        }

        if (theBird.isNocturnal())
        {
            if (!theWorld.isDaytime())
            {
                return TAKE_OFF_CHANCE_BASE;
            }
            else
            {
                return TAKE_OFF_CHANCE_BASE * 100;
            }
        }
        else
        {
            if (!theWorld.isDaytime())
            {
                return TAKE_OFF_CHANCE_BASE * 100;
            }
            else
            {
                return TAKE_OFF_CHANCE_BASE;
            }
        }
    }

    public void considerAttacking()
    {
        // DEBUG
        //if (theBird.getAttackTarget() != null) System.out.println("Attack target = "+theBird.getAttackTarget());

        // handle case where previous target becomes unsuitable
        if (TTMUtilities.isSuitableTarget(theBird, theBird.getAttackTarget(), false))
        {
            theBird.setAttackTarget(null);
        }

        if (theBird.isTamed())
        {
            processOwnerAttack();
        }
        else
        {
            processNaturalAttack(); // go for its natural prey
        }

        // check for revenge targets (the getAITarget() method really gives a revenge target)
        if (theBird.getAttackTarget() == null && theBird.getRevengeTarget() != null)
        {
            theBird.setAttackTarget(theBird.getRevengeTarget());
        }
    }

    public void processOwnerAttack()
    {
        EntityLivingBase theOwner = theBird.getOwner();

        if (theOwner == null)
        {
            return;
        }
        else
        {
            EntityLivingBase possibleTarget = theOwner.getLastAttackedEntity(); // note the get last attacker actually returns last attacked
            if (TTMUtilities.isSuitableTarget(theOwner, possibleTarget, true) &&
                    TTMUtilities.isCourseTraversable(theBird, possibleTarget.posX, possibleTarget.posY, possibleTarget.posZ))
            {
                theBird.setAttackTarget(possibleTarget);
            }
        }
    }

    public void processNaturalAttack()
    {
        // find target on ground
        AxisAlignedBB attackRegion = new AxisAlignedBB(
                theBird.posX - attackRegionSize,
                theWorld.getHeight((int)theBird.posX, (int)theBird.posZ) - attackRegionSize,
                theBird.posZ - attackRegionSize,
                theBird.posX + attackRegionSize,
                theWorld.getHeight((int)theBird.posX, (int)theBird.posZ) + attackRegionSize,
                theBird.posZ + attackRegionSize);

        for (int i=0; i<theBird.getPreyArray().length; i++)
        {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List possibleTargetEntities = theWorld.getEntitiesWithinAABB(theBird.getPreyArray()[i], attackRegion);
            @SuppressWarnings("unchecked")
            Iterator<Object> targetIterator = possibleTargetEntities.iterator();
            while (targetIterator.hasNext())
            {
                EntityLivingBase possibleTarget = (EntityLivingBase)(targetIterator.next());
                if (TTMUtilities.isCourseTraversable(theBird, possibleTarget.posX, possibleTarget.posY, possibleTarget.posZ))
                {
                    theBird.setAttackTarget(possibleTarget);
                }
            }
        }
    }
}