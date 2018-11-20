package com.greatorator.tolkienmobs.entity;


import com.google.common.base.Optional;

import com.greatorator.tolkienmobs.entity.entityai.AIStates;
import com.greatorator.tolkienmobs.entity.entityai.IModEntity;
import com.greatorator.tolkienmobs.entity.entityai.ProcessStateCrebain;
import com.greatorator.tolkienmobs.entity.entityai.UpdateStateCrebain;
import com.greatorator.tolkienmobs.events.CrebainTameEvent;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

/** Borrowed from Jabelar https://github.com/jabelar */
public class EntityCrebain extends EntityFlying implements IModEntity {
    protected static final DataParameter<Float> SCALE_FACTOR = EntityDataManager.<Float>createKey(EntityCrebain.class, DataSerializers.FLOAT);
    protected static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityCrebain.class, DataSerializers.VARINT);
    protected static final DataParameter<Boolean> SOAR_CLOCKWISE = EntityDataManager.<Boolean>createKey(EntityCrebain.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Float> SOAR_HEIGHT = EntityDataManager.<Float>createKey(EntityCrebain.class, DataSerializers.FLOAT);
    protected static final DataParameter<BlockPos> ANCHOR_POS = EntityDataManager.<BlockPos>createKey(EntityCrebain.class, DataSerializers.BLOCK_POS);
    protected static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.<Optional<UUID>>createKey(EntityCrebain.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Integer> LEG_BAND_COLOR = EntityDataManager.<Integer>createKey(EntityCrebain.class, DataSerializers.VARINT);

    protected final AttributeModifier TAMED_MODIFIER = new AttributeModifier("Tamed health and attack boost", 2.0D, 0);

    public ProcessStateCrebain aiProcessState;
    public UpdateStateCrebain aiUpdateState;
    protected int randFactor;

    @SuppressWarnings("rawtypes")
    private Class[] preyArray = new Class[] {EntityChicken.class, EntityBat.class, EntityRabbit.class, EntityParrot.class};

    private final double TAMED_HEALTH = 30.0D;

    public EntityCrebain(World parWorld)
    {
        super(parWorld);
        randFactor = rand.nextInt(10);
        setSize(1.0F, 1.0F);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        dataManager.register(SCALE_FACTOR, 1.0F);
        dataManager.register(STATE, AIStates.STATE_TAKING_OFF);
        dataManager.register(SOAR_CLOCKWISE, world.rand.nextBoolean());
        dataManager.register(SOAR_HEIGHT, (float)(126-randFactor));
        dataManager.register(ANCHOR_POS, new BlockPos(posX, posY, posZ));
        dataManager.register(OWNER_UUID, Optional.absent());
        dataManager.register(LEG_BAND_COLOR, Integer.valueOf(EnumDyeColor.RED.getDyeDamage()));
    }

    @Override
    public void clearAITasks()
    {
        tasks.taskEntries.clear();
        targetTasks.taskEntries.clear();
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks(); // clear any tasks assigned in super classes
        aiProcessState = new ProcessStateCrebain(this);
        aiUpdateState = new UpdateStateCrebain(this);
    }

    // you don't have to call this as it is called automatically during entityLiving subclass creation
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        // need to register any additional attributes
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();

        aiProcessState.updateAITick();
        aiUpdateState.updateAIState();

    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double parDistance)
    {
        return true; // need to see them even when far away or high above
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void fall(float parDistance, float parDamageMultiplier)
    {
        // do nothing since bird cannot fall
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
        // do nothing since birds cannot fall
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer parPlayer)
    {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean canAttackClass(Class parClass)
    {
        return true;
    }

    @Override
    public Item getDropItem()
    {
        return Items.FEATHER;
    }

    @Override
    protected void dropFewItems(boolean parRecentlyHitByPlayer, int parLootLevel)
    {
        dropItem(Items.FEATHER, parLootLevel+1);
        return;
    }

    @Override
    public boolean isNoDespawnRequired()
    {
        return isTamed();
    }

    @Override
    public void setAttackTarget(EntityLivingBase theTargetEntity)
    {
        super.setAttackTarget(theTargetEntity);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getState() == AIStates.STATE_TAKING_OFF || getState() == AIStates.STATE_TRAVELLING)
        {
            return SoundInit.soundFlappingCrebain;
        }
        else
        {
            return SoundInit.soundCallCrebain;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource parSource)
    {
        return SoundInit.soundHurtCrebain;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathCrebain;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.3F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getTrueSource();

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    public boolean processInteract(EntityPlayer parPlayer, EnumHand parHand)
    {
        if (parHand == EnumHand.OFF_HAND)
        {
            return super.processInteract(parPlayer, parHand);
        }

        ItemStack itemStackInHand = parPlayer.getHeldItem(parHand);

        if (!itemStackInHand.isEmpty()) // something in hand
        {
            if (isTamed())
            {
                if (itemStackInHand.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)itemStackInHand.getItem();

                    if (itemfood == Items.FISH && getHealth() < getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue());
                    {
                        if (!parPlayer.capabilities.isCreativeMode)
                        {
                            decrementStackInHand(parPlayer, itemStackInHand);
                        }

                        heal(itemfood.getHealAmount(itemStackInHand));
                        return true;
                    }
                }

                if (itemStackInHand.getItem() == Items.DYE)
                {
                    EnumDyeColor dyeColor = EnumDyeColor.byDyeDamage(itemStackInHand.getMetadata());

                    if (dyeColor != getLegBandColor())
                    {
                        setLegBandColor(dyeColor);

                        if (!parPlayer.capabilities.isCreativeMode)
                        {
                            decrementStackInHand(parPlayer, itemStackInHand);
                        }

                        return true;
                    }
                }
            }
            else // not tamed
            {
                // check if raw salmon
                // DEBUG
                System.out.println("Is taming food = "+isTamingFood(itemStackInHand));

                if (isTamingFood(itemStackInHand))
                {
                    if (!parPlayer.capabilities.isCreativeMode)
                    {
                        decrementStackInHand(parPlayer, itemStackInHand);
                    }

                    // Try taming
                    if (!world.isRemote)
                    {
                        if (rand.nextInt(3) == 0) // && onAnimalTame(this, parPlayer))
                        {
                            setTamedBy(parPlayer);
                            navigator.clearPath();
                            setAttackTarget((EntityLivingBase)null);
                            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TAMED_HEALTH);
                            playTameEffect(true);
                            world.setEntityState(this, (byte)7);
                        }
                        else
                        {
                            playTameEffect(false);
                            world.setEntityState(this, (byte)6);
                        }
                    }
                }
            }
        }
        else // nothing in hand
        {
            // do nothing
        }

        return super.processInteract(parPlayer, parHand);
    }

    protected void decrementStackInHand(EntityPlayer parPlayer, ItemStack itemStackInHand)
    {
        itemStackInHand.shrink(1);
        if (!parPlayer.capabilities.isCreativeMode && itemStackInHand.getCount() <= 0)
        {
            parPlayer.inventory.setInventorySlotContents(parPlayer.inventory.currentItem, ItemStack.EMPTY);
        }
    }

    public static boolean onAnimalTame(EntityCrebain animal, EntityPlayer tamer)
    {
        boolean success = MinecraftForge.EVENT_BUS.post(new CrebainTameEvent(animal, tamer));

        return success;
    }

    public boolean isTamingFood(ItemStack parItemStack)
    {
        // check for raw salmon
        return (parItemStack.getItem() == Items.FISH && parItemStack.getMetadata() == 1);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    protected <T> boolean canRenderName(T entity)
    {
        return false;
    }

    @Override
    public void setDead()
    {

        super.setDead();
    }

    @Override
    public Team getTeam()
    {
        if (getOwner() != null)
        {
            EntityLivingBase entityLivingBase = getOwner();

            if (entityLivingBase != null)
            {
                return entityLivingBase.getTeam();
            }
        }

        return super.getTeam();
    }

    @Override
    public boolean isOnSameTeam(Entity parEntity)
    {
        if (getOwner() != null)
        {
            EntityLivingBase entityOwner = getOwner();

            if (parEntity == entityOwner)
            {
                return true;
            }

            if (entityOwner != null)
            {
                return entityOwner.isOnSameTeam(parEntity);
            }
        }

        return super.isOnSameTeam(parEntity);
    }

    protected void playTameEffect(boolean shouldSpawnHearts)
    {
        if (world.isRemote)
        {
            return;
        }

        EnumParticleTypes particleType = EnumParticleTypes.HEART;

        if (!shouldSpawnHearts)
        {
            particleType = EnumParticleTypes.SMOKE_NORMAL;
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            world.spawnParticle(particleType, posX + rand.nextFloat() * width * 2.0F - width, posY + 0.5D + rand.nextFloat() * height, posZ + rand.nextFloat() * width * 2.0F - width, d0, d1, d2);
        }
    }

    public boolean isNocturnal()
    {
        return false;
    }

    public boolean isOwlType()
    {
        return false;
    }

    public boolean isTamed()
    {
        return (getOwner() != null);
    }

    @Override
    public void setScaleFactor(float parScaleFactor)
    {
        dataManager.set(SCALE_FACTOR, Math.abs(parScaleFactor));
    }

    @Override
    public float getScaleFactor()
    {
        return dataManager.get(SCALE_FACTOR);
    }

    public void setOwnerId(UUID parUUID)
    {
        dataManager.set(OWNER_UUID, Optional.fromNullable(parUUID));
    }

    public UUID getOwnerId()
    {
        return dataManager.get(OWNER_UUID).orNull();
    }

    public void setState(int parState)
    {

        dataManager.set(STATE, parState);
    }

    public int getState()
    {
        return dataManager.get(STATE);
    }

    public void setAnchor(BlockPos parPos)
    {
        dataManager.set(ANCHOR_POS, parPos);
    }

    public BlockPos getAnchor()
    {
        return dataManager.get(ANCHOR_POS);
    }

    public EntityPlayer getOwner()
    {
        try
        {
            UUID uuid = getOwnerId();
            return uuid == null ? null : world.getPlayerEntityByUUID(uuid);
        }
        catch (IllegalArgumentException parIAE)
        {
            return null;
        }
    }

    public boolean setTamedBy(EntityLivingBase parNewOwner)
    {
        if (getOwner() != null)
        {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(TAMED_MODIFIER);
            getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(TAMED_MODIFIER);
            setHealth(Math.min(getHealth(), (float) getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue()));
            return false;
        }
        else if (parNewOwner == null)
        {
            setOwnerId(null);
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(TAMED_MODIFIER);
            getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(TAMED_MODIFIER);
            setHealth(Math.min(getHealth(), (float) getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue()));
            return false;
        }
        else
        {
            setAttackTarget(null);
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(TAMED_MODIFIER);
            getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(TAMED_MODIFIER);
            setHealth((float) getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue());
            setOwnerId(parNewOwner.getUniqueID());

            return true;
        }
    }

    public boolean isOwner(EntityLivingBase parEntity)
    {
        return parEntity == getOwner();
    }

    public void setSoarClockwise(boolean parClockwise)
    {
        dataManager.set(SOAR_CLOCKWISE, parClockwise);
    }

    public boolean getSoarClockwise()
    {
        return dataManager.get(SOAR_CLOCKWISE);
    }

    public void setSoarHeight(float parHeight)
    {
        dataManager.set(SOAR_HEIGHT, parHeight);
    }

    public float getSoarHeight()
    {
        return dataManager.get(SOAR_HEIGHT);
    }

    public int getRandFactor()
    {
        return randFactor;
    }

    @SuppressWarnings("rawtypes")
    public Class[] getPreyArray()
    {
        return preyArray;
    }

    @SuppressWarnings("rawtypes")
    public void setPreyArray(Class[] parPreyArray)
    {
        preyArray = parPreyArray;
    }

    public EnumDyeColor getLegBandColor()
    {
        return EnumDyeColor.byMetadata(dataManager.get(LEG_BAND_COLOR));
    }

    public void setLegBandColor(EnumDyeColor parLegBandColor)
    {
        dataManager.set(LEG_BAND_COLOR, parLegBandColor.getMetadata());
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setFloat("scaleFactor", getScaleFactor());
        compound.setInteger("state", getState());
        compound.setBoolean("soaringClockwise", getSoarClockwise());
        compound.setFloat("soarHeight", getSoarHeight());
        compound.setDouble("anchorX", getAnchor().getX());
        compound.setDouble("anchorY", getAnchor().getY());
        compound.setDouble("anchorZ", getAnchor().getZ());
        if (this.getOwnerId() == null)
        {
            compound.setString("OwnerUUID", "");
        }
        else
        {
            compound.setString("OwnerUUID", this.getOwnerId().toString());
        }
        compound.setInteger("legBandColor", getLegBandColor().getDyeDamage());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setScaleFactor(compound.getFloat("scaleFactor"));
        setState(compound.getInteger("state"));
        setSoarClockwise(compound.getBoolean("soaringClockwise"));
        setSoarHeight(compound.getFloat("soarHeight"));
        setAnchor(new BlockPos(
                compound.getDouble("anchorX"),
                compound.getDouble("anchorY"),
                compound.getDouble("anchorZ")
        ));
        String s = compound.getString("OwnerUUID");
        if (!s.isEmpty())
        {
            try
            {
                setOwnerId(UUID.fromString(s));
            }
            catch (Throwable var4)
            {
                setOwnerId(null);
            }
        }
        else
        {
            setOwnerId(null);
        }
        setLegBandColor(EnumDyeColor.byDyeDamage(compound.getInteger("legBandColor")));
    }
}