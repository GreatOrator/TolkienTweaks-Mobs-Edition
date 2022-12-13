package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.BossEntity;
import com.greatorator.tolkienmobs.event.entity.SpiderEvent;
import com.greatorator.tolkienmobs.event.server.ServerEvents;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.utils.MathUtility;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.Event;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.Objects;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class ShelobEntity extends BossEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(ShelobEntity.class, EntityDataSerializers.BYTE);
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public ShelobEntity(EntityType<? extends BossEntity> type, Level level) {
        super(type, level);
        this.setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.ATTACK_DAMAGE, 17.0D)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.MOVEMENT_SPEED, 0.40D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 24.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 100.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new ShelobEntity.ShelobAttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new ShelobEntity.ShelobTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new ShelobEntity.ShelobTargetGoal<>(this, IronGolem.class));
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.WHITE;
    }

    @Nonnull
    @Override
    protected PathNavigation createNavigation(@Nonnull Level level) {
        return new WallClimberNavigation(this, level);
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

    }

    @Override
    public boolean hurt(@Nonnull DamageSource source, float amount) {
        if (!super.hurt(source, amount)) {
            return false;
        } else if (!(this.level instanceof ServerLevel serverworld)) {
            return false;
        } else {
            LivingEntity livingentity = this.getTarget();
            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
                livingentity = (LivingEntity) source.getEntity();
            }

            if (livingentity == null) return true;

            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(MobEffects.WATER_BREATHING)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.nodrown.shelob").withStyle(ChatFormatting.DARK_BLUE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.onfire.shelob").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.healself.shelob").withStyle(ChatFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(MobEffects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.speedup.shelob").withStyle(ChatFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0));
            }

            int xPos = Mth.floor(this.getX());
            int yPos = Mth.floor(this.getY());
            int zPos = Mth.floor(this.getZ());

            SpiderEvent.SummonAidEvent event = ServerEvents.fireSpiderSummonAid(this, level, xPos, yPos, zPos, livingentity, this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());

            if (event.getResult() == Event.Result.DENY) {

                if (livingentity instanceof Player) {
                    livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.nohelp.shelob").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                }
                return true;
            }
            if (event.getResult() == Event.Result.ALLOW || (double) this.random.nextFloat() < this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {

                if (livingentity instanceof Player) {
                    livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.helpcomming.shelob").withStyle(ChatFormatting.DARK_GREEN), Util.NIL_UUID);
                }

                CaveSpider spiderentity = event.getCustomSummonedAid() != null && event.getResult() == Event.Result.ALLOW ? event.getCustomSummonedAid() : EntityType.CAVE_SPIDER.create(this.level);

                for (int l = 0; l < 50; ++l) {
                    int spawnX = xPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    int spawnY = yPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    int spawnZ = zPos + Mth.nextInt(this.random, 7, 10) * Mth.nextInt(this.random, -1, 1);
                    BlockPos blockpos = new BlockPos(spawnX, spawnY, spawnZ);
                    EntityType<?> entitytype = spiderentity.getType();
                    SpawnPlacements.Type entityspawnplacementregistry$placementtype = SpawnPlacements.getPlacementType(entitytype);
                    if (NaturalSpawner.isSpawnPositionOk(entityspawnplacementregistry$placementtype, this.level, blockpos, entitytype) && SpawnPlacements.checkSpawnRules(entitytype, serverworld, MobSpawnType.REINFORCEMENT, blockpos, this.level.random)) {
                        spiderentity.setPos((double) spawnX, (double) spawnY, (double) spawnZ);
                        if (!this.level.hasNearbyAlivePlayer((double) spawnX, (double) spawnY, (double) spawnZ, 7.0D) && this.level.isUnobstructed(spiderentity) && this.level.noCollision(spiderentity) && !this.level.containsAnyLiquid(spiderentity.getBoundingBox())) {
                            spiderentity.setTarget(livingentity);
                            spiderentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(spiderentity.blockPosition()), MobSpawnType.REINFORCEMENT, (SpawnGroupData) null, (CompoundTag) null);
                            serverworld.addFreshEntityWithPassengers(spiderentity);
                            Objects.requireNonNull(this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE)).addPermanentModifier(new AttributeModifier("Spider reinforcement caller charge", (double) -0.05F, AttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    private float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        float f = this.getAttackDamage();
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, 0.4F, 0.0D));
            this.doEnchantDamageEffects(this, entityIn);
            long time = System.currentTimeMillis();
            if (super.doHurtTarget(entityIn)) {
                if (entityIn instanceof Player) {
                    if (time > nextAbilityUse) {
                        nextAbilityUse = time + coolDown;
                        Player player = (Player) entityIn;
                        int strength = 2;
                        if (MathUtility.getRandom(10) <= 3) {
                            player.addEffect(new MobEffectInstance(TolkienPotions.WATCHER_FEAR.get(), strength * 20, 0));
                        } else {
                            player.addEffect(new MobEffectInstance(TolkienPotions.SLEEPNESIA.get(), strength * 20, 0));
                        }
                    }
                }
            }
        }

        this.playSound(SoundEvents.SPIDER_HURT, 1.0F, 1.0F);
        return flag;
    }

    @Nonnull
    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean p_33820_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_33820_) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("move", true));
            return PlayState.CONTINUE;
        }else if(!event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }else if (this.swinging && !this.getRanged()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            return PlayState.CONTINUE;
        }else if (this.isAggressive() && this.getRanged()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("shooting", false));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Goals */
    static class ShelobAttackGoal extends MeleeAttackGoal {
        public ShelobAttackGoal(ShelobEntity entity) {
            super(entity, 1.0D, true);
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
        protected double getAttackReachSqr(LivingEntity p_33825_) {
            return (double)(4.0F + p_33825_.getBbWidth());
        }
    }

    static class ShelobTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public ShelobTargetGoal(ShelobEntity entity, Class<T> entity2) {
            super(entity, entity2, true);
        }

        @Override
        public boolean canUse() {
            float f = this.mob.getBrightness();
            return f >= 0.5F ? false : super.canUse();
        }
    }

    /** Sounds */
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }
}