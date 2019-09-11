package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.entityai.EntityAITTMAttack;
import com.greatorator.tolkienmobs.entity.passive.EntityTMDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityTMElves;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHobbit;
import com.greatorator.tolkienmobs.entity.passive.EntityTMHuman;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class EntityTMHostiles extends EntityMob implements IRangedAttackMob {
    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMHostiles.class, DataSerializers.VARINT);
    private ResourceLocation lootTable;
    private Item weaponType;
    private boolean setRandomWeapon;
    private Potion ttmEffect;
    private SoundEvent angrySound;
    private int ttmDuration;
    private int rndMax;
    private int rndMin;
    private boolean burnState;
    private boolean ttmAttack;
    private boolean groupAttack;
    private boolean madeBoss;
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    /** Above zero if this Entity is Angry. */
    private int angerLevel;
    /** A random delay until this Entity next makes a sound. */
    private int randomSoundDelay;
    private UUID angerTargetUUID;

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityTMHostiles.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityTMHostiles.class, DataSerializers.BOOLEAN);
    private final EntityAIAttackRangedBow<EntityTMHostiles> aiArrowAttack = new EntityAIAttackRangedBow<EntityTMHostiles>(this, 1.0D, 20, 15.0F);
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
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.55D));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityTMHostiles.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTMHobbit.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTMHuman.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTMDwarf.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTMElves.class, true));

        if(groupAttack) {
            this.targetTasks.addTask(1, new EntityTMHostiles.AIHurtByAggressor(this));
            this.targetTasks.addTask(2, new EntityTMHostiles.AITargetAggressor(this));
            this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityTMHostiles.class, true));
        }
    }

    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        if (this.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemBow)
            entityarrow = ((net.minecraft.item.ItemBow) this.getHeldItemMainhand().getItem()).customizeArrow(entityarrow);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
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
        if(setRandomWeapon) {
            int i = TTMRand.getRandomInteger(1,10);
            if (i <= 5) {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(TTMFeatures.SWORD_MORGULIRON));
                setWeaponType(Items.IRON_SWORD);
            } else {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
                setWeaponType(Items.BOW);
            }
        }
        else {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weaponType));
        }
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
            this.tasks.removeTask(this.aiArrowAttack);
            ItemStack itemstack = this.getHeldItemMainhand();

            if (itemstack != null && weaponType == Items.BOW)
            {
                this.tasks.addTask(4, this.aiArrowAttack);
            }
            else
            {
                this.tasks.addTask(4, this.aiAttackOnCollide);
                int i = 20;

                if (this.world.getDifficulty() != EnumDifficulty.HARD)
                {
                    i = 40;
                }
            }
        }
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 160;
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
                long time = System.currentTimeMillis();

                if(ttmDuration > 0) {
                    if (time > nextAbilityUse) {
                        nextAbilityUse = time + coolDown;
                        this.playSound(angrySound, this.getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                        ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(ttmEffect, ttmDuration));
                    }
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
        return 1;
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) < 8 && willSpawn <= 10 && super.getCanSpawnHere();
    }

    protected int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
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

    public void setRandomWeapon(boolean setRandomWeapon) {
        this.setRandomWeapon = setRandomWeapon;
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