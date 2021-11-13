package com.greatorator.tolkienmobs.entity.ambient;

import com.google.common.collect.Sets;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.Set;

public class EntityTTMThrush extends ParrotEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.defineId(EntityTTMThrush.class, DataSerializers.INT);
    private static final Item DEADLY_ITEM = Items.COOKIE;
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private boolean partyParrot;
    private BlockPos jukeboxPosition;

    public EntityTTMThrush(EntityType<? extends EntityTTMThrush> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new FlyingMovementController(this, 10, false);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.COCOA, -1.0F);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setVariant(this.random.nextInt(5));
        if (spawnDataIn == null) {
            spawnDataIn = new AgeableEntity.AgeableData(false);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isBaby() {
        return false;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LandOnOwnersShoulderGoal(this));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.FLYING_SPEED, (double) 0.4F)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.2F);
    }

    /**
     * Returns new PathNavigateGround instance
     */
    protected PathNavigator createNavigation(World worldIn) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.6F;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void aiStep() {
        if (this.jukeboxPosition == null || !this.jukeboxPosition.closerThan(this.position(), 3.46D) || !this.level.getBlockState(this.jukeboxPosition).is(Blocks.JUKEBOX)) {
            this.partyParrot = false;
            this.jukeboxPosition = null;
        }

        if (this.level.random.nextInt(400) == 0) {
            playMimicSound(this.level, this);
        }

        super.aiStep();
        this.calculateFlapping();
    }

    /**
     * Called when a record starts or stops playing. Used to make parrots start or stop partying.
     */
    @OnlyIn(Dist.CLIENT)
    public void setRecordPlayingNearby(BlockPos pos, boolean isPartying) {
        this.jukeboxPosition = pos;
        this.partyParrot = isPartying;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isPartyParrot() {
        return this.partyParrot;
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
        this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float) ((double) this.flapping * 0.9D);
        Vector3d vector3d = this.getDeltaMovement();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }

    public static boolean playMimicSound(World worldIn, Entity parrotIn) {
        return false;
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        if (!this.isTame() && TAME_ITEMS.contains(itemstack.getItem())) {
            if (!p_230254_1_.abilities.instabuild) {
                itemstack.shrink(1);
            }

            if (!this.isSilent()) {
                this.level.playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.level.isClientSide) {
                if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_230254_1_)) {
                    this.tame(p_230254_1_);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else if (itemstack.getItem() == DEADLY_ITEM) {
            if (!p_230254_1_.abilities.instabuild) {
                itemstack.shrink(1);
            }

            this.addEffect(new EffectInstance(Effects.POISON, 900));
            if (p_230254_1_.isCreative() || !this.isInvulnerable()) {
                this.hurt(DamageSource.playerAttack(p_230254_1_), Float.MAX_VALUE);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else if (!this.isFlying() && this.isTame() && this.isOwnedBy(p_230254_1_)) {
            if (!this.level.isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(p_230254_1_, p_230254_2_);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isFood(ItemStack stack) {
        return false;
    }

    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMate(AnimalEntity otherAnimal) {
        return false;
    }

    @Nullable
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    public boolean doHurtTarget(Entity entityIn) {
        return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
    }

    @Nullable
    public SoundEvent getAmbientSound() {
        return getAmbient(this.level, this.level.random);
    }

    public static SoundEvent getAmbient(World p_234212_0_, Random p_234212_1_) {
        return SoundGenerator.soundIdleTMThrush.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtTMThrush.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathTMThrush.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    protected float playFlySound(float volume) {
        this.playSound(SoundGenerator.soundFlappingCrebain.get(), 0.15F, 1.0F);
        return volume + this.flapSpeed / 2.0F;
    }

    protected boolean makeFlySound() {
        return true;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getVoicePitch() {
        return getPitch(this.random);
    }

    public static float getPitch(Random random) {
        return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
    }

    public SoundCategory getSoundSource() {
        return SoundCategory.NEUTRAL;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean isPushable() {
        return true;
    }

    protected void doPush(Entity entityIn) {
        if (!(entityIn instanceof PlayerEntity)) {
            super.doPush(entityIn);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.setOrderedToSit(false);
            return super.hurt(source, amount);
        }
    }

    public int getVariant() {
        return MathHelper.clamp(this.entityData.get(VARIANT), 0, 4);
    }

    public void setVariant(int variantIn) {
        this.entityData.set(VARIANT, variantIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
    }

    public boolean isFlying() {
        return !this.onGround;
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double) (0.5F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
    }

    public static int spawnChance()
    {
        int i = TTMRand.getRandomInteger(100, 1);
        return i;
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.getX());
        int j = MathHelper.floor(this.getBoundingBox().minY);
        int k = MathHelper.floor(this.getZ());
        BlockPos blockpos = new BlockPos(i, j, k);

        return this.level.getDifficulty() != Difficulty.PEACEFUL && this.isValidLightLevel() && spawnChance()<5;
    }

    public static boolean checkThrushSpawn(EntityType<EntityTTMThrush> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
    }
}