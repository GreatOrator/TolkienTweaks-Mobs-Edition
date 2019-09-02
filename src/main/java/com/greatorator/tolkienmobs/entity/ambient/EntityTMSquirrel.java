package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.init.SoundInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.TTMRand;
import com.greatorator.tolkienmobs.world.biomes.BiomeFirien;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityTMSquirrel extends EntityCreature implements IAnimals {
    protected Block spawnableBlock = Blocks.LEAVES;
    private static final DataParameter<Integer> SOSQUIRREL_TYPE = EntityDataManager.<Integer>createKey(EntityTMSquirrel.class, DataSerializers.VARINT);

    public EntityTMSquirrel(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.8F);
    }

    protected void initEntityAI()
    {
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 2.2D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, TTMFeatures.TREE_ACORN, false));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, TTMFeatures.GOLDEN_TREE_ACORN, false));
        this.tasks.addTask(4, new EntityTMSquirrel.AIAvoidEntity(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EntityTMSquirrel.AIAvoidEntity(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWander(this, 1.25F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SOSQUIRREL_TYPE, Integer.valueOf(0));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("SOSquirrelType", this.getSOSquirrelType());
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setSOSquirrelType(compound.getInteger("SOSquirrelType"));
    }

    protected SoundEvent getJumpSound()
    {
        return SoundInit.soundStepSOSquirrel;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundInit.soundIdleSOSquirrel;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundInit.soundHurtSOSquirrel;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundInit.soundDeathSOSquirrel;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (this.getSOSquirrelType() == 99)
        {
            this.playSound(SoundInit.soundAngrySOSquirrel, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
        }
        else
        {
            return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
        }
    }

    public SoundCategory getSoundCategory()
    {
        return this.getSOSquirrelType() == 99 ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    @Override
    public void fall(float distance, float multiplier) {
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
    }

    private boolean isSOSquirrelBreedingItem(Item itemIn)
    {
        return itemIn == TTMFeatures.TREE_ACORN || itemIn == TTMFeatures.GOLDEN_TREE_ACORN;
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return this.isSOSquirrelBreedingItem(stack.getItem());
    }

    public int getSOSquirrelType()
    {
        return ((Integer)this.dataManager.get(SOSQUIRREL_TYPE)).intValue();
    }

    public void setSOSquirrelType(int sosquirrelTypeId)
    {
        if (sosquirrelTypeId == 99)
        {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
            this.tasks.addTask(4, new EntityTMSquirrel.AIEvilAttack(this));
            this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
            this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));

            if (!this.hasCustomName())
            {
                this.setCustomNameTag(I18n.translateToLocal("entity.sosquirrel.name"));
            }
        }

        this.dataManager.set(SOSQUIRREL_TYPE, Integer.valueOf(sosquirrelTypeId));
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        // prefer standing on leaves
        Material underMaterial = this.world.getBlockState(pos.down()).getMaterial();
        if (underMaterial == Material.LEAVES) {
            return 12.0F;
        }
        if (underMaterial == Material.WOOD) {
            return 15.0F;
        }
        if (underMaterial == Material.GRASS) {
            return 10.0F;
        }

        return this.world.getLightBrightness(pos) - 0.5F;
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i = this.getRandomSOSquirrelType();
        boolean flag = false;

        if (livingdata instanceof EntityTMSquirrel.SOSquirrelTypeData)
        {
            i = ((EntityTMSquirrel.SOSquirrelTypeData)livingdata).typeData;
            flag = true;
        }
        else
        {
            livingdata = new EntityTMSquirrel.SOSquirrelTypeData(i);
        }

        this.setSOSquirrelType(i);
        return livingdata;
    }

    private int getRandomSOSquirrelType()
    {
        Biome biome = this.world.getBiome(new BlockPos(this));
        int i = this.rand.nextInt(100);

        if (biome.isSnowyBiome())
        {
            return i < 80 ? 1 : 3;
        }
        else if (biome instanceof BiomeFirien)
        {
            return 4;
        }
        else
        {
            return i < 50 ? 0 : (i < 90 ? 5 : 2);
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

    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T>
    {
        private final EntityTMSquirrel sosquirrel;

        public AIAvoidEntity(EntityTMSquirrel sosquirrel, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
        {
            super(sosquirrel, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.sosquirrel = sosquirrel;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return this.sosquirrel.getSOSquirrelType() != 99 && super.shouldExecute();
        }
    }

    static class AIEvilAttack extends EntityAIAttackMelee
    {
        public AIEvilAttack(EntityTMSquirrel sosquirrel)
        {
            super(sosquirrel, 1.4D, true);
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(4.0F + attackTarget.width);
        }
    }

    public static class SOSquirrelTypeData implements IEntityLivingData
    {
        public int typeData;

        public SOSquirrelTypeData(int type)
        {
            this.typeData = type;
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootInit.SOSQUIRREL;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        int willSpawn = this.spawnChance();
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.world.getBlockState(blockpos.down()).getBlock() == this.spawnableBlock && this.world.getLight(blockpos) > 8 && willSpawn <= 10 && super.getCanSpawnHere();
    }

    protected int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }
}