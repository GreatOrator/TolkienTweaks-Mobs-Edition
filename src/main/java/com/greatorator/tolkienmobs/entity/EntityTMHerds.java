//package com.greatorator.tolkienmobs.entity;
//
//import com.greatorator.tolkienmobs.handler.interfaces.IModEntity;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.utils.TTMRand;
//import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
//import net.minecraft.block.Block;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.entity.IEntityLivingData;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.ai.*;
//import net.minecraft.entity.passive.EntityAnimal;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.EnumDifficulty;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import javax.annotation.Nullable;
//
///** Borrowed from Jabelar https://github.com/jabelar */
//public class EntityTMHerds extends EntityAnimal implements IModEntity
//{
//    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMHerds.class, DataSerializers.VARINT);
//    protected static final DataParameter<Float> SCALE_FACTOR = EntityDataManager.<Float>createKey(EntityTMHerds.class, DataSerializers.FLOAT);
//    protected static final DataParameter<Integer> REARING_COUNTER = EntityDataManager.<Integer>createKey(EntityTMHerds.class, DataSerializers.VARINT);
//
//    private int rndMax;
//    private int rndMin;
//    protected Block spawnableBlock = Blocks.GRASS;
//
//    protected boolean isHitWithoutResistance = false ;
//
//    public EntityTMHerds(World par1World)
//    {
//        super(par1World);
//    }
//
//    @Override
//    public void entityInit()
//    {
//        super.entityInit();
//        dataManager.register(SKIN_TYPE, Integer.valueOf(0));
//        dataManager.register(SCALE_FACTOR, 1.0F);
//        dataManager.register(REARING_COUNTER, 0);
//    }
//
//    @Override
//    public void initEntityAI()
//    {
//        clearAITasks();
//        tasks.addTask(0, new EntityAISwimming(this));
//        tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
//        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
//        tasks.addTask(5, new EntityAIMate(this, 1.0D));
//        tasks.addTask(6, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
//        tasks.addTask(8, new EntityAIWander(this, 1.0D));
//        tasks.addTask(9, new EntityAIWatchClosest(this, PlayerEntity.class, 6.0F));
//        tasks.addTask(10, new EntityAILookIdle(this));
//        targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
//    }
//
//    @Override
//    public void clearAITasks()
//    {
//        tasks.taskEntries.clear();
//        targetTasks.taskEntries.clear();
//    }
//
//    @Override
//    protected void applyEntityAttributes()
//    {
//        super.applyEntityAttributes();
//        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
//        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
//        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
//        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
//        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
//    }
//
//    @Nullable
//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
//        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
//        this.setEquipmentBasedOnDifficulty(difficulty);
//        int i = this.getRandomMobType();
//
//        if (ientitylivingdata instanceof EntityTMHerds.MobTypeData)
//        {
//            i = ((EntityTMHerds.MobTypeData)livingdata).typeData;
//        }
//        else
//        {
//            ientitylivingdata = new EntityTMHerds.MobTypeData(i);
//        }
//
//        this.setMobType(i);
//        return ientitylivingdata;
//    }
//
//    public static class MobTypeData implements IEntityLivingData
//    {
//        public int typeData;
//
//        public MobTypeData(int type)
//        {
//            this.typeData = type;
//        }
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundAmbientMumakil;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource parDamageSource)
//    {
//        return SoundInit.soundHurtMumakil;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundHurtMumakil;
//    }
//
//    @Override
//    protected float getSoundVolume()
//    {
//        return 0.4F;
//    }
//
//    @Override
//    public void onLivingUpdate()
//    {
//        super.onLivingUpdate();
//    }
//
//    @Override
//    public boolean processInteract(PlayerEntity player, EnumHand hand)
//    {
//        ItemStack itemstack = player.getHeldItem(hand);
//
//        if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild())
//        {
//            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
//            itemstack.shrink(1);
//
//            if (itemstack.isEmpty())
//            {
//                player.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
//            }
//            else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
//            {
//                player.dropItem(new ItemStack(Items.MILK_BUCKET), false);
//            }
//
//            return true;
//        }
//        else
//        {
//            return super.processInteract(player, hand);
//        }
//    }
//
//    @Override
//    public EntityTMHerds createChild(EntityAgeable par1EntityAgeable)
//    {
//        return null;
//    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource par1DamageSource, float parDamageAmount)
//    {
//        return super.attackEntityFrom(par1DamageSource, parDamageAmount);
//    }
//
//    @SideOnly(Side.CLIENT)
//    protected <T> boolean canRenderName(T entity)
//    {
//        return false;
//    }
//
//    @Override
//    public boolean attackEntityAsMob(Entity entityIn)
//    {
//        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));
//
//        if (flag)
//        {
//            this.applyEnchantments(this, entityIn);
//        }
//
//        return flag;
//    }
//
//    @Override
//    public void setScaleFactor(float parScaleFactor)
//    {
//        dataManager.set(SCALE_FACTOR, Math.abs(parScaleFactor));
//    }
//
//    @Override
//    public float getScaleFactor()
//    {
//        return dataManager.get(SCALE_FACTOR);
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound compound)
//    {
//        super.writeEntityToNBT(compound);
//        compound.setInteger("SkinType", this.getMobType());
//        compound.setFloat("scaleFactor", getScaleFactor());
//    }
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//        this.setMobType(compound.getInteger("SkinType"));
//        setScaleFactor(compound.getFloat("scaleFactor"));
//    }
//
//    public int getMobType()
//    {
//        return ((Integer)this.dataManager.get(SKIN_TYPE)).intValue();
//    }
//
//    public void setMobType(int mobTypeId){
//        this.dataManager.set(SKIN_TYPE, Integer.valueOf(mobTypeId));
//    }
//
//    private int getRandomMobType()
//    {
//        int i = TTMRand.getRandomInteger(rndMax, rndMin);
//        return i;
//    }
//
//    public void setRndMinMax(int rndMin, int rndMax) {
//        this.rndMin = rndMin;
//        this.rndMax = rndMax;
//    }
//
//    protected boolean isValidLightLevel() {
//        return true;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
//    }
//
//    public int getTalkInterval()
//    {
//        return 160;
//    }
//}