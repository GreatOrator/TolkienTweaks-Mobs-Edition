//package com.greatorator.tolkienmobs.entity.passive;
//
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import com.greatorator.tolkienmobs.utils.TTMRand;
//import com.greatorator.tolkienmobs.utils.TTMSpawnEvent;
//import net.minecraft.block.SoundType;
//import net.minecraft.entity.EntityAgeable;
//import net.minecraft.entity.IEntityLivingData;
//import net.minecraft.entity.passive.AbstractChestHorse;
//import net.minecraft.entity.passive.EntityAnimal;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.datafix.DataFixer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.EnumDifficulty;
//import net.minecraft.world.World;
//
//import javax.annotation.Nullable;
//
//public class EntityTMGoat extends AbstractChestHorse {
//    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMGoat.class, DataSerializers.VARINT);
//
//    public EntityTMGoat(World worldIn) {
//        super(worldIn);
//        this.setSize(1.3964844F, 1.6F);
//    }
//
//    protected void entityInit() {
//        super.entityInit();
//        this.dataManager.register(SKIN_TYPE, Integer.valueOf(0));
//    }
//
//    @Nullable
//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
//        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
//        this.setEquipmentBasedOnDifficulty(difficulty);
//        int i = this.getRandomMobType();
//
//        if (ientitylivingdata instanceof EntityTMGoat.MobTypeData)
//        {
//            i = ((EntityTMGoat.MobTypeData)livingdata).typeData;
//        }
//        else
//        {
//            ientitylivingdata = new EntityTMGoat.MobTypeData(i);
//        }
//
//        if (TTMRand.getRandomInteger(15, 1) == 2)
//        {
//            EntityTMDwarf entitydwarf = new EntityTMDwarf(this.world);
//            entitydwarf.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
//            entitydwarf.onInitialSpawn(difficulty, (IEntityLivingData)null);
//            this.world.spawnEntity(entitydwarf);
//            entitydwarf.startRiding(this);
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
//    public double getMountedYOffset()
//    {
//        return (double)(this.height * 0.58F);
//    }
//
//    public static void registerFixesGoat(DataFixer fixer)
//    {
//        AbstractChestHorse.registerFixesAbstractChestHorse(fixer, EntityTMGoat.class);
//    }
//
//    @Override
//    protected void playGallopSound(SoundType p_190680_1_)
//    {
//        super.playGallopSound(p_190680_1_);
//
//        if (this.rand.nextInt(10) == 0)
//        {
//            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
//        }
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        super.getAmbientSound();
//        return SoundInit.soundIdleGoat;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        super.getDeathSound();
//        return SoundInit.soundDeathGoat;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        super.getHurtSound(damageSourceIn);
//        return SoundInit.soundHurtGoat;
//    }
//
//    @Override
//    protected SoundEvent getAngrySound()
//    {
//        super.getAngrySound();
//        return SoundInit.soundAngryGoat;
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.GOAT;
//    }
//
//    @Override
//    public int getMaxSpawnedInChunk() {
//        return 4;
//    }
//
//    @Override
//    public boolean getCanSpawnHere()
//    {
//        int i = MathHelper.floor(this.posX);
//        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
//        int k = MathHelper.floor(this.posZ);
//        int willSpawn = TTMSpawnEvent.spawnChance();
//        BlockPos blockpos = new BlockPos(i, j, k);
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.getLight(blockpos) > 8 && willSpawn <= 10;
//    }
//
//    protected boolean isValidLightLevel()
//    {
//        return true;
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound compound) {
//        super.writeEntityToNBT(compound);
//        compound.setInteger("SkinType", this.getMobType());
//    }
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound compound) {
//        super.readEntityFromNBT(compound);
//        this.setMobType(compound.getInteger("SkinType"));
//    }
//
//    public boolean canMateWith(EntityAnimal otherAnimal)
//    {
//        if (otherAnimal == this)
//        {
//            return false;
//        }
//        else if (!(otherAnimal instanceof EntityTMGoat))
//        {
//            return false;
//        }
//        else
//        {
//            return this.canMate();
//        }
//    }
//
//    public EntityAgeable createChild(EntityAgeable ageable)
//    {
//        EntityTMGoat abstractgoat = (EntityTMGoat)(ageable instanceof EntityTMGoat ? new EntityTMGoat(this.world) : new EntityTMGoat(this.world));
//        this.setOffspringAttributes(ageable, abstractgoat);
//        return abstractgoat;
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
//        int i = TTMRand.getRandomInteger(5, 1);
//        return i;
//    }
//}