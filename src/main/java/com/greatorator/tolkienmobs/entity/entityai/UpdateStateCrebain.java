package com.greatorator.tolkienmobs.entity.entityai;

import com.greatorator.tolkienmobs.entity.monster.EntityCrebain;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
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

/** Borrowed from Jabelar https://github.com/jabelar */
public class UpdateStateCrebain {
    public EntityCrebain theCrebain;
    public World theWorld;

    public double attackRegionSize = 5.0D;

    public final int PERCH_CHANCE_BASE = 1;

    public final int TAKE_OFF_CHANCE_BASE = 2400;

    public UpdateStateCrebain(EntityCrebain parCrebain)
    {
        theCrebain = parCrebain;
        theWorld = theCrebain.world;
    }

    public void updateAIState()
    {
//    	// DEBUG
//    	if (theBird.ticksExisted%20 == 0)
//    	{
//    		System.out.println("update ai state where state = "+theBird.getState()+" for entity id = "+theBird.getEntityId());
//    	}

        switch (theCrebain.getState())
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

    /**
     *
     */
    private void updateStateSeeking()
    {
        if (theCrebain.isTamed())
        {
            processOwnerAttack();

            if (theCrebain.getAttackTarget() != null)
            {
                theCrebain.setState(AIStates.STATE_ATTACKING);
            }
            else
            {
                theCrebain.setState(AIStates.STATE_TRAVELLING);
            }
        }
        else
        {
            // DEBUG
            LogHelperTTM.info("Seeking but isn't tamed");
        }
    }

    /**
     *
     */
    private void updateStatePerchedTamed()
    {
        // check if block perched upon has disappeared
//            // DEBUG
//            System.out.println("Block underneath = "+worldObj.getBlock(MathHelper.floor_double(posX), (int)posY - 1, MathHelper.floor_double(posZ)).getUnlocalizedName());
        if (!hasLanded())
        {
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
        else // still solidly perched
        {
            // can occasionally adjust or flap, look around, or play sound to create variety
            if (theCrebain.getRNG().nextInt(getTakeOffChance()) == 0)
            {
                theCrebain.setState(AIStates.STATE_TAKING_OFF);
                // rotationYawHead = rand.nextInt(360);
            }
        }
    }

    /**
     *
     */
    private void updateStateSoaringTamed()
    {
        // climb again if drifting too low
        if (theCrebain.posY < theCrebain.getSoarHeight()*0.9D)
        {
            // point towards owner
            theCrebain.rotationYaw = ProcessStateCrebain.getYawFromVec(new Vec3d(
                    theCrebain.getOwner().posX - theCrebain.posX,
                    theCrebain.getOwner().posY - theCrebain.posY,
                    theCrebain.getOwner().posZ - theCrebain.posZ));
            theCrebain.setState(AIStates.STATE_TRAVELLING);
        }

        considerAttacking();

        if (theCrebain.getAttackTarget() == null)
        {
            considerPerching();
        }
        else
        {
            theCrebain.setState(AIStates.STATE_SEEKING);
        }
    }

    /**
     *
     */
    private void updateStateAttacking()
    {
        EntityLivingBase target = theCrebain.getAttackTarget();
        // check if target has been killed or despawned
        if (target == null)
        {
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (target.isDead)
        {
            theCrebain.setAttackTarget(null);
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (!ProcessStateCrebain.isCourseTraversable(theCrebain, target.posX, target.posY, target.posZ))
        {
            // theBird.setAttackTarget(null);
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
        // check for hitting target
        else if (theCrebain.getDistance(theCrebain.getAttackTarget())<2.0F)
        {
            theCrebain.getAttackTarget().attackEntityFrom(
                    DamageSource.causeMobDamage(theCrebain),
                    (float) theCrebain.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
    }

    /**
     *
     */
    private void updateStateTravelling()
    {
        if (theCrebain.posY >= theCrebain.getSoarHeight())
        {
//            // DEBUG
//            System.out.println("State changed to soaring");
            if (theCrebain.isTamed())
            {
                theCrebain.setState(AIStates.STATE_SOARING_TAMED);
            }
            else
            {
                theCrebain.setState(AIStates.STATE_SOARING);
            }
        }
    }

    /**
     *
     */
    private void updateStateLanding()
    {
        if (hasLanded())
        {
            if (theCrebain.isTamed())
            {
                theCrebain.setState(AIStates.STATE_PERCHED_TAMED);
            }
            else
            {
                theCrebain.setState(AIStates.STATE_PERCHED);
            }
        }
    }

    private void updateStateDiving()
    {
        // check if should start landing
        BlockPos anchorPos = theCrebain.getAnchor();

        if (theCrebain.getDistanceSq(
                anchorPos.getX(),
                anchorPos.getY(),
                anchorPos.getZ())<25)
        {
            theCrebain.setState(AIStates.STATE_LANDING);
        }
        // see if made it to perch
        else if (hasLanded())
        {
            if (theCrebain.isTamed())
            {
                theCrebain.setState(AIStates.STATE_PERCHED_TAMED);
                stopMoving();
            }
            else
            {
                theCrebain.setState(AIStates.STATE_PERCHED);
                stopMoving();
            }
        }
    }

    private boolean hasLanded()
    {
        AxisAlignedBB entityBoundingBox = (ProcessStateCrebain.copyBoundingBox(theCrebain.getEntityBoundingBox())).offset(0.0D, -0.5D, 0.0D);

        if (!theWorld.getCollisionBoxes(theCrebain, entityBoundingBox).isEmpty())
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
        theCrebain.motionX = 0;
        theCrebain.motionY = 0;
        theCrebain.motionZ = 0;
    }

    private void updateStateSoaring()
    {
        if (theCrebain.isTamed())
        {
            theCrebain.setState(AIStates.STATE_SOARING_TAMED);
        }
        else
        {
            // climb again if drifting too low
            if (theCrebain.posY < theCrebain.getSoarHeight()*0.9D)
            {
                theCrebain.setState(AIStates.STATE_TRAVELLING);
            }

            considerAttacking();

            if (theCrebain.getAttackTarget() == null)
            {
                considerPerching();
            }
            else
            {
                theCrebain.setState(AIStates.STATE_ATTACKING);
            }
        }
    }

    private void updateStateTakingOff()
    {
        if (theCrebain.posY >= theCrebain.getSoarHeight())
        {

            if (theCrebain.isTamed())
            {
                theCrebain.setState(AIStates.STATE_SOARING_TAMED);
            }
            else
            {
                theCrebain.setState(AIStates.STATE_SOARING);
            }
        }
    }

    private void updateStatePerched()
    {
        if (theCrebain.isTamed())
        {
            theCrebain.setState(AIStates.STATE_PERCHED_TAMED);
        }
        else if (!hasLanded())
        {
            theCrebain.setState(AIStates.STATE_TAKING_OFF);
        }
        else // still solidly perched
        {
            // can occasionally adjust or flap, look around, or play sound to create variety
            if (theCrebain.getRNG().nextInt(getTakeOffChance()) == 0)
            {
                theCrebain.setState(AIStates.STATE_TAKING_OFF);
                // rotationYawHead = rand.nextInt(360);
            }

            // entity can get scared if player gets too close
            EntityPlayer closestPlayer = theWorld.getClosestPlayerToEntity(theCrebain, 4.0D);
            if (closestPlayer != null)
            {
                ItemStack theHeldItemStack = closestPlayer.inventory.getCurrentItem();
                if (theHeldItemStack != null)
                {
                    // if not holding taming food, bird will get spooked
                    if (!theCrebain.isTamingFood(theHeldItemStack))
                    {
                        theCrebain.setState(AIStates.STATE_TAKING_OFF);
                    }
                }
            }
        }
    }

    public void considerPerching()
    {
        if (theCrebain.isTamed())
        {
            return;
        }
        else
        {
            // always try to perch starting at dusk
            if (theCrebain.getRNG().nextInt(getPerchChance()) == 0)
            {
                if (theWorld.getBlockState(theWorld.getTopSolidOrLiquidBlock(theCrebain.getPosition())) instanceof BlockLeaves)
                {
                    if (ProcessStateCrebain.isCourseTraversable(
                            theCrebain,
                            theCrebain.posX,
                            theWorld.getHeight(
                                    (int)theCrebain.posX,
                                    (int)theCrebain.posZ),
                            theCrebain.posZ))
                    {
                        theCrebain.setState(AIStates.STATE_DIVING);
                        theCrebain.setAnchor(new BlockPos(
                                theCrebain.posX,
                                theWorld.getHeight(
                                        (int)theCrebain.posX,
                                        (int)theCrebain.posZ),
                                theCrebain.posZ));
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

        if (theCrebain.isNocturnal())
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

        if (theCrebain.isNocturnal())
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
        if (theCrebain.getAttackTarget() != null);

        // handle case where previous target becomes unsuitable
        if (ProcessStateCrebain.isSuitableTarget(theCrebain, theCrebain.getAttackTarget(), false))
        {
            theCrebain.setAttackTarget(null);
        }

        if (theCrebain.isTamed())
        {
            processOwnerAttack();
        }
        else
        {
            processNaturalAttack(); // go for its natural prey
        }

        // check for revenge targets (the getAITarget() method really gives a revenge target)
        if (theCrebain.getAttackTarget() == null && theCrebain.getRevengeTarget() != null)
        {
            theCrebain.setAttackTarget(theCrebain.getRevengeTarget());
        }
    }

    // detect if owner has attacked something, if so set attack target to owner's target
    public void processOwnerAttack()
    {
        EntityLivingBase theOwner = theCrebain.getOwner();

        if (theOwner == null)
        {
            return;
        }
        else
        {
            EntityLivingBase possibleTarget = theOwner.getLastAttackedEntity(); // note the get last attacker actually returns last attacked
            if (ProcessStateCrebain.isSuitableTarget(theOwner, possibleTarget, true) &&
                    ProcessStateCrebain.isCourseTraversable(theCrebain, possibleTarget.posX, possibleTarget.posY, possibleTarget.posZ))
            {
                theCrebain.setAttackTarget(possibleTarget);
            }
        }
    }

    // detect if there is an attack target in region on ground directly below eagle
    public void processNaturalAttack()
    {
        // find target on ground
        AxisAlignedBB attackRegion = new AxisAlignedBB(
                theCrebain.posX - attackRegionSize,
                theWorld.getHeight((int)theCrebain.posX, (int)theCrebain.posZ) - attackRegionSize,
                theCrebain.posZ - attackRegionSize,
                theCrebain.posX + attackRegionSize,
                theWorld.getHeight((int)theCrebain.posX, (int)theCrebain.posZ) + attackRegionSize,
                theCrebain.posZ + attackRegionSize);

        for (int i=0; i<theCrebain.getPreyArray().length; i++)
        {
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List possibleTargetEntities = theWorld.getEntitiesWithinAABB(theCrebain.getPreyArray()[i], attackRegion);
            @SuppressWarnings("unchecked")
            Iterator<Object> targetIterator = possibleTargetEntities.iterator();
            while (targetIterator.hasNext())
            {
                EntityLivingBase possibleTarget = (EntityLivingBase)(targetIterator.next());
                if (ProcessStateCrebain.isCourseTraversable(theCrebain, possibleTarget.posX, possibleTarget.posY, possibleTarget.posZ))
                {
                    theCrebain.setAttackTarget(possibleTarget);
                }
            }
        }
    }
}