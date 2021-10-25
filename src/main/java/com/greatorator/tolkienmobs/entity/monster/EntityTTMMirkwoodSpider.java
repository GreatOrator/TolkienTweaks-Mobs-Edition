package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class EntityTTMMirkwoodSpider extends EntityTTMMonsters {
    private static final DataParameter<Integer> MIRKWOODSPIDER_TYPE = EntityDataManager.defineId(EntityTTMMirkwoodSpider.class, DataSerializers.INT);
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(SpiderEntity.class, DataSerializers.BYTE);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mirkwoodspider.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mirkwoodspider.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mirkwoodspider.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mirkwoodspider.png"));
    });

    public EntityTTMMirkwoodSpider(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new EntityTTMMirkwoodSpider.AttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new EntityTTMMirkwoodSpider.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(3, new EntityTTMMirkwoodSpider.TargetGoal<>(this, IronGolemEntity.class));
    }

    @Override
    public double getPassengersRidingOffset() {
        return (double)(this.getBbHeight() * 0.5F);
    }

    @Override
    protected PathNavigator createNavigation(World p_175447_1_) {
        return new ClimberPathNavigator(this, p_175447_1_);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    public void makeStuckInBlock(BlockState p_213295_1_, Vector3d p_213295_2_) {
        if (!p_213295_1_.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(p_213295_1_, p_213295_2_);
        }

    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBeAffected(EffectInstance p_70687_1_) {
        if (p_70687_1_.getEffect() == Effects.POISON) {
            net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, p_70687_1_);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        }
        return super.canBeAffected(p_70687_1_);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean p_70839_1_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_70839_1_) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.65F;
    }

    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(EntityTTMMirkwoodSpider p_i46676_1_) {
            super(p_i46676_1_, 1.0D, true);
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        @Override
        public boolean canContinueToUse() {
            float f = this.mob.getBrightness();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        @Override
        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(4.0F + p_179512_1_.getBbWidth());
        }
    }

    public static class GroupData implements ILivingEntityData {
        public Effect effect;

        public void setRandomEffect(Random p_111104_1_) {
            int i = p_111104_1_.nextInt(5);
            if (i <= 1) {
                this.effect = Effects.MOVEMENT_SPEED;
            } else if (i <= 2) {
                this.effect = Effects.DAMAGE_BOOST;
            } else if (i <= 3) {
                this.effect = Effects.REGENERATION;
            } else if (i <= 4) {
                this.effect = Effects.INVISIBILITY;
            }

        }
    }

    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(EntityTTMMirkwoodSpider p_i45818_1_, Class<T> p_i45818_2_) {
            super(p_i45818_1_, p_i45818_2_, true);
        }

        @Override
        public boolean canUse() {
            float f = this.mob.getBrightness();
            return f >= 0.5F ? false : super.canUse();
        }
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getMirkwoodSpiderTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getMirkwoodSpiderType(), TEXTURE_BY_ID.get(0));
    }

    public int getMirkwoodSpiderType() {
        return this.entityData.get(MIRKWOODSPIDER_TYPE);
    }

    public void setMirkwoodSpiderType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(MIRKWOODSPIDER_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setMirkwoodSpiderType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (worldIn.getRandom().nextInt(100) == 0) {
            SkeletonEntity skeletonentity = EntityType.SKELETON.create(this.level);
            skeletonentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
            skeletonentity.finalizeSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT)null);
            skeletonentity.startRiding(this);
        }

        if (spawnDataIn == null) {
            spawnDataIn = new EntityTTMMirkwoodSpider.GroupData();
            if (worldIn.getDifficulty() == Difficulty.HARD && worldIn.getRandom().nextFloat() < 0.1F * difficultyIn.getSpecialMultiplier()) {
                ((EntityTTMMirkwoodSpider.GroupData)spawnDataIn).setRandomEffect(worldIn.getRandom());
            }
        }

        if (spawnDataIn instanceof EntityTTMMirkwoodSpider.GroupData) {
            Effect effect = ((EntityTTMMirkwoodSpider.GroupData)spawnDataIn).effect;
            if (effect != null) {
                this.addEffect(new EffectInstance(effect, Integer.MAX_VALUE));
            }
        }

        return spawnDataIn;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(MIRKWOODSPIDER_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("MirkwoodSpiderType", this.getMirkwoodSpiderType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setMirkwoodSpiderType(compound.getInt("MirkwoodSpiderType"));
    }

//
//    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityTMMirkwoodSpider.class, DataSerializers.BYTE);
//
//    public EntityTMMirkwoodSpider(World worldIn) {
//        super(worldIn);
//        this.setSize(1.4F, 0.9F);
//    }
//
//    public static void registerFixesMirkwoodSpider(DataFixer fixer) {
//        EntityLiving.registerFixesMob(fixer, EntityTMMirkwoodSpider.class);
//    }
//
//    protected void initEntityAI() {
//        this.tasks.addTask(1, new EntityAISwimming(this));
//        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
//        this.tasks.addTask(4, new EntityTMMirkwoodSpider.AISpiderAttack(this));
//        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
//        this.tasks.addTask(6, new EntityAIWatchClosest(this, PlayerEntity.class, 8.0F));
//        this.tasks.addTask(6, new EntityAILookIdle(this));
//        this.applyEntityAI();
//    }
//
//    private void applyEntityAI() {
//        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//        this.targetTasks.addTask(2, new EntityTMMirkwoodSpider.AISpiderTarget(this, PlayerEntity.class));
//        this.targetTasks.addTask(3, new EntityTMMirkwoodSpider.AISpiderTarget(this, EntityIronGolem.class));
//    }
//
//    protected void entityInit() {
//        super.entityInit();
//        this.dataManager.register(CLIMBING, Byte.valueOf((byte) 0));
//    }
//
//    protected void applyEntityAttributes() {
//        super.applyEntityAttributes();
//        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
//    }
//
//    /**
//     * Returns the Y offset from the entity's position for any entity riding this one.
//     */
//    public double getMountedYOffset() {
//        return (double) (this.height * 0.5F);
//    }
//
//    /**
//     * Returns new PathNavigateGround instance
//     */
//    protected PathNavigate createNavigator(World worldIn) {
//        return new PathNavigateClimber(this, worldIn);
//    }
//
//    /**
//     * Called to update the entity's position/logic.
//     */
//    public void onUpdate() {
//        super.onUpdate();
//
//        if (!this.world.isRemote) {
//            this.setBesideClimbableBlock(this.collidedHorizontally);
//        }
//    }
//
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_SPIDER_AMBIENT;
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundEvents.ENTITY_SPIDER_HURT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.ENTITY_SPIDER_DEATH;
//    }
//
//    protected void playStepSound(BlockPos pos, Block blockIn) {
//        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
//    }
//
//    /**
//     * Returns true if this entity should move as if it were on a ladder (either because it's actually on a ladder, or
//     * for AI reasons)
//     */
//    public boolean isOnLadder() {
//        return this.isBesideClimbableBlock();
//    }
//
//    /**
//     * Sets the Entity inside a web block.
//     */
//    public void setInWeb() {
//    }
//
//    /**
//     * Get this Entity's EnumCreatureAttribute
//     */
//    public EnumCreatureAttribute getCreatureAttribute() {
//        return EnumCreatureAttribute.ARTHROPOD;
//    }
//
//    public boolean isPotionApplicable(PotionEffect potioneffectIn) {
//        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
//    }
//
//    /**
//     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
//     * setBesideClimbableBlock.
//     */
//    public boolean isBesideClimbableBlock() {
//        return (((Byte) this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
//    }
//
//    /**
//     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
//     * false.
//     */
//    public void setBesideClimbableBlock(boolean climbing) {
//        byte b0 = ((Byte) this.dataManager.get(CLIMBING)).byteValue();
//
//        if (climbing) {
//            b0 = (byte) (b0 | 1);
//        } else {
//            b0 = (byte) (b0 & -2);
//        }
//
//        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
//    }
//
//    /**
//     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
//     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
//     */
//    @Nullable
//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
//        livingdata = super.onInitialSpawn(difficulty, livingdata);
//
//        if (this.world.rand.nextInt(100) == 0) {
//            EntitySkeleton entityskeleton = new EntitySkeleton(this.world);
//            entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
//            entityskeleton.onInitialSpawn(difficulty, (IEntityLivingData) null);
//            this.world.spawnEntity(entityskeleton);
//            entityskeleton.startRiding(this);
//        }
//
//        if (livingdata == null) {
//            livingdata = new EntityTMMirkwoodSpider.GroupData();
//
//            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty()) {
//                ((EntityTMMirkwoodSpider.GroupData) livingdata).setRandomEffect(this.world.rand);
//            }
//        }
//
//        if (livingdata instanceof EntityTMMirkwoodSpider.GroupData) {
//            Potion potion = ((EntityTMMirkwoodSpider.GroupData) livingdata).effect;
//
//            if (potion != null) {
//                this.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE));
//            }
//        }
//
//        return livingdata;
//    }
//
//    public float getEyeHeight() {
//        return 0.65F;
//    }
//
//    static class AISpiderAttack extends EntityAIAttackMelee {
//        public AISpiderAttack(EntityTMMirkwoodSpider spider) {
//            super(spider, 1.0D, true);
//        }
//
//        /**
//         * Returns whether an in-progress EntityAIBase should continue executing
//         */
//        public boolean shouldContinueExecuting() {
//            float f = this.attacker.getBrightness();
//
//            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
//                this.attacker.setAttackTarget((LivingEntity) null);
//                return false;
//            } else {
//                return super.shouldContinueExecuting();
//            }
//        }
//
//        protected double getAttackReachSqr(LivingEntity attackTarget) {
//            return (double) (4.0F + attackTarget.width);
//        }
//    }
//
//    static class AISpiderTarget<T extends LivingEntity> extends EntityAINearestAttackableTarget<T> {
//        public AISpiderTarget(EntityTMMirkwoodSpider spider, Class<T> classTarget) {
//            super(spider, classTarget, true);
//        }
//
//        /**
//         * Returns whether the EntityAIBase should begin execution.
//         */
//        public boolean shouldExecute() {
//            float f = this.taskOwner.getBrightness();
//            return f >= 0.5F ? false : super.shouldExecute();
//        }
//    }
//
//    @Override
//    @Nullable
//    protected ResourceLocation getLootTable() {
//        return LootInit.MSPIDER;
//    }
//
//    @Override
//    public int getMaxSpawnedInChunk() {
//        return 2;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && willSpawn <= 10;
//    }
//
//    public static class GroupData implements IEntityLivingData {
//        public Potion effect;
//
//        public void setRandomEffect(Random rand) {
//            int i = rand.nextInt(5);
//
//            if (i <= 1) {
//                this.effect = MobEffects.SPEED;
//            } else if (i <= 2) {
//                this.effect = MobEffects.STRENGTH;
//            } else if (i <= 3) {
//                this.effect = MobEffects.REGENERATION;
//            } else if (i <= 4) {
//                this.effect = MobEffects.INVISIBILITY;
//            }
//        }
//    }
}