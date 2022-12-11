package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.ai.goal.WatcherAttackGoal;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class WatcherEntity extends WaterAnimal implements IAnimatable {
    public static final EntityDataAccessor<Integer> DATA_ID_STATE = SynchedEntityData.defineId(WatcherEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_INV = SynchedEntityData.defineId(WatcherEntity.class, EntityDataSerializers.INT);
    private final ServerBossEvent watcherEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private final AnimationFactory factory = new AnimationFactory(this);
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;
    private float tx;
    private float ty;
    private float tz;
    public float xBodyRotO;
    private boolean drowning;
    private boolean sleeping;

    public WatcherEntity(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 1, 0.35F, 0.01F, true);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
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
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WatcherAttackGoal(this, 1, 0));
        this.goalSelector.addGoal(1, new WatcherRandomMovementGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!super.hurt(source, amount)) {
            return false;
        } else if (!(this.level instanceof ServerLevel)) {
            return false;
        } else {
            LivingEntity livingentity = this.getTarget();
            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
                livingentity = (LivingEntity) source.getEntity();
            }

            if (livingentity == null) return true;

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.healself.watcher").withStyle(ChatFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(MobEffects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.speedup.watcher").withStyle(ChatFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0));
            }
        }
        return true;
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
                        int strength = 3;
                        if (RandomUtility.getRandom(10) <= 3) {
                            this.drowning = true;
                            player.addEffect(new MobEffectInstance(TolkienPotions.ELEMENTAL_DROWNING.get(), strength * 20, 0));
                        } else {
                            this.sleeping = true;
                            this.spawnInk();
                            player.addEffect(new MobEffectInstance(TolkienPotions.SLEEPNESIA.get(), strength * 20, 0));
                        }
                    }
                }
            }
        }
        this.playSound(TolkienSounds.soundIdleWatcher.get(), 1.0F, 1.0F);
        return flag;
    }

    private boolean getSleeping() {
        return sleeping;
    }

    private boolean getDrowning() {
        return drowning;
    }

    private void spawnInk() {
        this.playSound(this.getSquirtSound(), this.getSoundVolume(), this.getVoicePitch());
        Vec3 vec3 = this.rotateVector(new Vec3(0.0D, -1.0D, 0.0D)).add(this.getX(), this.getY(), this.getZ());

        for(int i = 0; i < 30; ++i) {
            Vec3 vec31 = this.rotateVector(new Vec3((double)this.random.nextFloat() * 0.6D - 0.3D, -1.0D, (double)this.random.nextFloat() * 0.6D - 0.3D));
            Vec3 vec32 = vec31.scale(0.3D + (double)(this.random.nextFloat() * 2.0F));
            ((ServerLevel)this.level).sendParticles(this.getInkParticle(), vec3.x, vec3.y + 0.5D, vec3.z, 0, vec32.x, vec32.y, vec32.z, (double)0.1F);
        }
    }

    private Vec3 rotateVector(Vec3 p_29986_) {
        Vec3 vec3 = p_29986_.xRot(this.xBodyRotO * ((float)Math.PI / 180F));
        return vec3.yRot(-this.yBodyRotO * ((float)Math.PI / 180F));
    }

    protected ParticleOptions getInkParticle() {
        return ParticleTypes.SQUID_INK;
    }

    private float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public void tryAttack(LivingEntity target) {
        boolean bl = target.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (bl) {
            this.canAttack(target);
        }
    }

    protected float getStandingEyeHeight(@Nonnull Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.2f;
    }

    @Override
    protected int getExperienceReward(@Nonnull Player player) {
        return 200 + this.level.random.nextInt(400);
    }

    @Override
    public void travel(@Nonnull Vec3 vec3) {
        if (!this.isImmobile() && this.isInWater()) {
            this.updateSwimming();
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.8D, 0.8D, 0.8D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(vec3);
        }
    }

    @Nonnull
    @Override
    protected PathNavigation createNavigation(@Nonnull Level world) {
        return new WaterBoundPathNavigation(this, world);
    }

    public void setMovementVector(float p_29959_, float p_29960_, float p_29961_) {
        this.tx = p_29959_;
        this.ty = p_29960_;
        this.tz = p_29961_;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_STATE, 0);
        this.entityData.define(DATA_ID_INV, 0);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundIdleWatcher.get();
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource source) {
        return SoundEvents.DROWNED_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathWatcher.get();
    }

    protected SoundEvent getSquirtSound() {
        return SoundEvents.SQUID_SQUIRT;
    }

    public void setAttackingState(int time) {
        this.entityData.set(DATA_ID_STATE, time);
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && !this.isAggressive()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if(!event.isMoving() && !this.isAggressive()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }else if (this.isAggressive()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swing", false));
            return PlayState.CONTINUE;
        }else if (this.isAggressive() && this.getDrowning()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("whirlpool", false));
            this.drowning = false;
            return PlayState.CONTINUE;
        }else if (this.isAggressive() && this.getSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("bite", false));
            this.sleeping = false;
            return PlayState.CONTINUE;
        }else if (this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Goal Section */
    static class WatcherRandomMovementGoal extends Goal {
        private final WatcherEntity squid;

        public WatcherRandomMovementGoal(WatcherEntity p_30004_) {
            this.squid = p_30004_;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick() {
            int i = this.squid.getNoActionTime();
            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.wasTouchingWater || !this.squid.hasMovementVector()) {
                float f = this.squid.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float f1 = Mth.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                float f3 = Mth.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }

        }
    }

    /** Boss Section */
    public int getInvulnerableTicks() {
        return this.entityData.get(DATA_ID_INV);
    }

    public void setInvulnerableTicks(int invulnerableTicks) {
        this.entityData.set(DATA_ID_INV, invulnerableTicks);
    }

    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Invul", this.getInvulnerableTicks());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setInvulnerableTicks(tag.getInt("Invul"));
        if (this.hasCustomName()) {
            this.watcherEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.watcherEvent.setName(this.getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
        if (this.getInvulnerableTicks() > 0) {
            int k1 = this.getInvulnerableTicks() - 1;
            this.watcherEvent.setProgress(1.0F - (float) k1 / 220.0F);
        }
        this.watcherEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public void makeInvulnerable() {
        this.setInvulnerableTicks(220);
        this.watcherEvent.setProgress(0.0F);
        this.setHealth(this.getMaxHealth() / 3.0F);
    }

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.watcherEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.watcherEvent.removePlayer(serverPlayer);
    }
}