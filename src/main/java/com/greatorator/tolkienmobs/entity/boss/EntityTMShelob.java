//package com.greatorator.tolkienmobs.entity.boss;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.init.LootInit;
//import com.greatorator.tolkienmobs.utils.TTMRand;
//import net.minecraft.block.Block;
//import net.minecraft.entity.monster.EntityMob;
//import net.minecraft.entity.monster.EntitySkeleton;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.init.MobEffects;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.pathfinding.PathNavigate;
//import net.minecraft.pathfinding.PathNavigateClimber;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.datafix.DataFixer;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.*;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//
//public class EntityTMShelob extends EntityMob {
//    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
//
//    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityTMShelob.class, DataSerializers.BYTE);
//
//    public EntityTMShelob(World worldIn) {
//        super(worldIn);
//        this.setSize(1.4F, 0.9F);
//        this.experienceValue = 250;
//    }
//
//    public static void registerFixesMirkwoodSpider(DataFixer fixer) {
//        EntityLiving.registerFixesMob(fixer, EntityTMShelob.class);
//    }
//
//    protected void initEntityAI() {
//        this.tasks.addTask(1, new EntityAISwimming(this));
//        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
//        this.tasks.addTask(4, new EntityTMShelob.AISpiderAttack(this));
//        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
//        this.tasks.addTask(6, new EntityAIWatchClosest(this, PlayerEntity.class, 8.0F));
//        this.tasks.addTask(6, new EntityAILookIdle(this));
//        this.applyEntityAI();
//    }
//
//    private void applyEntityAI() {
//        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
//        this.targetTasks.addTask(2, new EntityTMShelob.AISpiderTarget(this, PlayerEntity.class));
//        this.targetTasks.addTask(3, new EntityTMShelob.AISpiderTarget(this, EntityLiving.class));
//    }
//
//    protected void entityInit() {
//        super.entityInit();
//        this.dataManager.register(CLIMBING, Byte.valueOf((byte) 0));
//    }
//
//    protected void applyEntityAttributes() {
//        super.applyEntityAttributes();
//        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(240.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
//    }
//
//    public void addTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.addTrackingPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    public void removeTrackingPlayer(ServerPlayerEntity player)
//    {
//        super.removeTrackingPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    protected void updateAITasks()
//    {
//        super.updateAITasks();
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
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
//            livingdata = new EntityTMShelob.GroupData();
//
//            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty()) {
//                ((EntityTMShelob.GroupData) livingdata).setRandomEffect(this.world.rand);
//            }
//        }
//
//        if (livingdata instanceof EntityTMShelob.GroupData) {
//            Potion potion = ((EntityTMShelob.GroupData) livingdata).effect;
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
//        public AISpiderAttack(EntityTMShelob spider) {
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
//        public AISpiderTarget(EntityTMShelob spider, Class<T> classTarget) {
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
//        return LootInit.TMSHELOB;
//    }
//
//    @Override
//    public int getMaxSpawnedInChunk() {
//        return 2;
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = this.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere() && willSpawn <= 10;
//    }
//
//    private int spawnChance()
//    {
//        int i = TTMRand.getRandomInteger(TTMConfig_Old.mobSpawnChance, 1);
//        return i;
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
//
//    public void readEntityFromNBT(NBTTagCompound compound)
//    {
//        super.readEntityFromNBT(compound);
//
//        if (this.hasCustomName())
//        {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//    }
//
//    public void setCustomNameTag(String name)
//    {
//        super.setCustomNameTag(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    protected boolean canDespawn()
//    {
//        return true;
//    }
//}