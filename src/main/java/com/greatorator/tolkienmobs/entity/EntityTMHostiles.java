package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMMordorOrc;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMTreeEnt;
import com.greatorator.tolkienmobs.entity.passive.EntityTMDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityTMElves;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHobbit;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHuman;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class EntityTMHostiles extends EntityMob {
    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMHostiles.class, DataSerializers.VARINT);
    private ResourceLocation lootTable;
    private Item weaponType;
    private Potion ttmEffect;
    private SoundEvent angrySound;
    private int ttmDuration;
    private int rndMax;
    private int rndMin;
    private boolean burnState;
    private boolean ttmAttack;
    private boolean groupAttack;
    private boolean madeBoss;

    /** Above zero if this Entity is Angry. */
    private int angerLevel;
    /** A random delay until this Entity next makes a sound. */
    private int randomSoundDelay;
    private UUID angerTargetUUID;

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityTMHostiles.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityTMHostiles.class, DataSerializers.BOOLEAN);
    private static final UUID ATTACK_SPEED_BOOST_MODIFIER_UUID = UUID.fromString("3D1772EA-23CC-11E9-AB14-D663BD873D93");
    private static final AttributeModifier ATTACK_SPEED_BOOST_MODIFIER = (new AttributeModifier(ATTACK_SPEED_BOOST_MODIFIER_UUID, "Attacking speed boost", 0.05D, 0)).setSaved(false);
    private final EntityAITTMAttack aiAttackOnCollide = new EntityAITTMAttack(this, 1.2D, false)
    {
        public void resetTask()
        {
            super.resetTask();
            EntityTMHostiles.this.setSwingingArms(false);
        }

        public void startExecuting()
        {
            super.startExecuting();
            EntityTMHostiles.this.setSwingingArms(true);
        }
    };

    public EntityTMHostiles(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAITTMAttack(this, 0.75D, false));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAIWander(this, 0.55D));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityTMMordorOrc.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMHobbit.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMHuman.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMDwarf.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMElves.class, true));
        if(groupAttack) {
            this.targetTasks.addTask(1, new EntityTMHostiles.AIHurtByAggressor(this));
            this.targetTasks.addTask(2, new EntityTMHostiles.AITargetAggressor(this));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityTMTreeEnt.class, true));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(17.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackDamage());
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getArmorStrength());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getHealthLevel());
    }

    public void updateRidden()
    {
        super.updateRidden();

        if (this.getRidingEntity() instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.getRidingEntity();
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weaponType));
    }

    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack)
    {
        super.setItemStackToSlot(slotIn, stack);

        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND)
        {
            this.setCombatTask();
        }
    }

    public void setCombatTask()
    {
        if (this.world != null && !this.world.isRemote)
        {
            this.tasks.removeTask(this.aiAttackOnCollide);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack.getItem() == weaponType)
            {
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
            else
            {
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD)
                {
                    i = 40;
                }
            }
        }
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!super.attackEntityAsMob(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof EntityLivingBase)
            {
                if(ttmDuration > 0) {
                    ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ttmEffect, ttmDuration));
                }
            }
            return true;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SKIN_TYPE, Integer.valueOf(0));
        this.dataManager.register(SWINGING_ARMS, Boolean.valueOf(true));
        if(ttmAttack) {
            this.dataManager.register(ATTACKING, Boolean.valueOf(false));
        }
    }

    @Override
    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected float getSoundVolume()
    {
        return 1.5F;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return lootTable;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSwingingArms()
    {
        return ((Boolean)this.dataManager.get(SWINGING_ARMS)).booleanValue();
    }

    public void setSwingingArms(boolean swingingArms)
    {
        this.dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }

    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    }

    public void onLivingUpdate()
    {
        if (this.world.isDaytime() && !this.world.isRemote && !this.isChild() && this.shouldBurnInDay())
        {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

                if (!itemstack.isEmpty())
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                        if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        super.onLivingUpdate();
    }

    protected boolean shouldBurnInDay()
    {
        return burnState;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomMobType();

        if (ientitylivingdata instanceof EntityTMHostiles.MobTypeData)
        {
            i = ((EntityTMHostiles.MobTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMHostiles.MobTypeData(i);
        }

        this.setMobType(i);
        this.setCombatTask();
        return ientitylivingdata;
    }

    public static class MobTypeData implements IEntityLivingData
    {
        public int typeData;

        public MobTypeData(int type)
        {
            this.typeData = type;
        }
    }

    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    protected void updateAITasks()
    {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
        if(groupAttack) {
            if (this.isAngry()) {
                if (!this.isChild() && !iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
                    iattributeinstance.applyModifier(ATTACK_SPEED_BOOST_MODIFIER);
                }

                --this.angerLevel;
            } else if (iattributeinstance.hasModifier(ATTACK_SPEED_BOOST_MODIFIER)) {
                iattributeinstance.removeModifier(ATTACK_SPEED_BOOST_MODIFIER);
            }

            if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0) {
                this.playSound(angrySound, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
            }

            if (this.angerLevel > 0 && this.angerTargetUUID != null && this.getRevengeTarget() == null) {
                EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
                this.setRevengeTarget(entityplayer);
                this.attackingPlayer = entityplayer;
                this.recentlyHit = this.getRevengeTimer();
            }
        }
        super.updateAITasks();
    }

    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);

        if (p_70835_1_ instanceof EntityLivingBase)
        {
            this.setRevengeTarget((EntityLivingBase)p_70835_1_);
        }
    }

    public boolean isAngry()
    {
        return this.angerLevel > 0;
    }

    static class AIHurtByAggressor extends EntityAIHurtByTarget
    {
        public AIHurtByAggressor(EntityTMHostiles p_i45828_1_)
        {
            super(p_i45828_1_, true);
        }

        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
        {
            super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);

            if (creatureIn instanceof EntityTMHostiles)
            {
                ((EntityTMHostiles)creatureIn).becomeAngryAt(entityLivingBaseIn);
            }
        }
    }

    static class AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AITargetAggressor(EntityTMHostiles p_i45829_1_)
        {
            super(p_i45829_1_, EntityPlayer.class, true);
        }

        public boolean shouldExecute()
        {
            return ((EntityTMHostiles)this.taskOwner).isAngry() && super.shouldExecute();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("SkinType", this.getMobType());
        if(groupAttack) {
            compound.setShort("Anger", (short) this.angerLevel);

            if (this.angerTargetUUID != null) {
                compound.setString("HurtBy", this.angerTargetUUID.toString());
            } else {
                compound.setString("HurtBy", "");
            }
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setMobType(compound.getInteger("SkinType"));
        if(groupAttack) {
            this.angerLevel = compound.getShort("Anger");
            String s = compound.getString("HurtBy");

            if (!s.isEmpty()) {
                this.angerTargetUUID = UUID.fromString(s);
                EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(this.angerTargetUUID);
                this.setRevengeTarget(entityplayer);

                if (entityplayer != null) {
                    this.attackingPlayer = entityplayer;
                    this.recentlyHit = this.getRevengeTimer();
                }
            }
        }
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    public boolean isNonBoss()
    {
        return !madeBoss;
    }

    protected boolean canDespawn()
    {
        return !madeBoss;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    public int getMobType()
    {
        return ((Integer)this.dataManager.get(SKIN_TYPE)).intValue();
    }

    public void setMobType(int mobTypeId){
        this.dataManager.set(SKIN_TYPE, Integer.valueOf(mobTypeId));
    }

    private int getRandomMobType()
    {
        int i = TTMRand.getRandomInteger(rndMax, rndMin);
            return i;
    }

    public void setBurnState(boolean burnState) {
        this.burnState = burnState;
    }

    public void setWeaponType(Item weaponType) {
        this.weaponType = weaponType;
    }

    public void setTtmEffect(Potion ttmEffect) {
        this.ttmEffect = ttmEffect;
    }

    public void setTtmDuration(int ttmDuration) {
        this.ttmDuration = ttmDuration;
    }

    public void setTtmAttack(boolean ttmAttack) {
        this.ttmAttack = ttmAttack;
    }

    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    public void setMobMentality(boolean mobMentality,SoundEvent angrySound) {
        this.groupAttack = mobMentality;
        this.angrySound = angrySound;
    }

    public void setMadeBoss(boolean madeBoss) {
        this.madeBoss = madeBoss;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public abstract double getAttackDamage();{
    }

    public abstract double getArmorStrength();{
    }

    public abstract double getHealthLevel();{
    }
}