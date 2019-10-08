package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityTMRat extends EntityCreature {
    private static final DataParameter<Integer> SKIN_TYPE = EntityDataManager.<Integer>createKey(EntityTMRat.class, DataSerializers.VARINT);

    public EntityTMRat(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.5F);
    }

    protected void initEntityAI()
    {
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.2D));
        this.tasks.addTask(4, new EntityTMRat.AIAvoidEntity(this, EntityOcelot.class, 10.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EntityTMRat.AIAvoidEntity(this, EntityPlayer.class, 10.0F, 2.2D, 2.2D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWander(this, 1.25F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SKIN_TYPE, Integer.valueOf(0));
    }

    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
    {
        private final EntityTMRat tmRat;

        public AIAvoidEntity(EntityTMRat tmRat, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
        {
            super(tmRat, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.tmRat = tmRat;
        }
        public boolean shouldExecute()
        {
            return super.shouldExecute();
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 1)
        {
            this.createRunningParticles();
        }
        else
        {
            super.handleStatusUpdate(id);
        }
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        IEntityLivingData ientitylivingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        int i = this.getRandomMobType();

        if (ientitylivingdata instanceof EntityTMRat.MobTypeData)
        {
            i = ((EntityTMRat.MobTypeData)livingdata).typeData;
        }
        else
        {
            ientitylivingdata = new EntityTMRat.MobTypeData(i);
        }

        this.setMobType(i);
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

    public int getMobType()
    {
        return ((Integer)this.dataManager.get(SKIN_TYPE)).intValue();
    }

    public void setMobType(int mobTypeId){
        this.dataManager.set(SKIN_TYPE, Integer.valueOf(mobTypeId));
    }

    private int getRandomMobType()
    {
        int i = TTMRand.getRandomInteger(5, 1);
        return i;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleTMRat;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtTMRat;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathTMRat;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("SkinType", this.getMobType());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setMobType(compound.getInteger("SkinType"));
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        int willSpawn = this.spawnChance();

        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && !this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
    }

    private int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }
}
