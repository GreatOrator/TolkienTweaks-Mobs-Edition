package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.DragonEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.dragon.*;
import com.greatorator.tolkienmobs.entity.item.FireBreathEntity;
import com.greatorator.tolkienmobs.event.client.ClientEvents;
import com.greatorator.tolkienmobs.handler.EntityHandler;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.utils.LerpedFloatUtility;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.EnumSet;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

@SuppressWarnings({"removal" })
public class FellBeastEntity extends DragonEntity {
    static {
        IDLE_ANIMATION_VARIANTS = 1;
        ATTACK_ANIMATION_VARIANTS = 2;
        SITTING_ANIMATION_TIME = 60;
        SLEEPING_ANIMATION_TIME = 60;
    }

    public static final EntityDataAccessor<Boolean> BREATHING_FIRE = SynchedEntityData.defineId(FellBeastEntity.class, EntityDataSerializers.BOOLEAN);

    public static final String ROAR_ANIMATION = "roar";
    public static final int ROAR_ANIMATION_TYPE = 2;
    public static final int ROAR_ANIMATION_TIME = 80;
    public static final boolean ROAR_ANIMATION_MOVES = true;

    public static final String FIRE_ANIMATION = "fire";
    public static final int FIRE_ANIMATION_TYPE = 1;
    public static final int FIRE_ANIMATION_TIME = 80;
    public static final boolean FIRE_ANIMATION_MOVES = false;

    public static final String ATTACK_ANIMATION = "attack";
    public static final int ATTACK_ANIMATION_TYPE = 2;
    public static final int ATTACK_ANIMATION_TIME_1 = 20;
    public static final int ATTACK_ANIMATION_TIME_2 = 13;
    public static final int ATTACK_ANIMATION_TIME_3 = 35;
    public static final int ATTACK_QUEUE_TIME_1 = 9;
    public static final int ATTACK_QUEUE_TIME_2 = 9;
    public static final int ATTACK_QUEUE_TIME_3 = 25;

    public final LerpedFloatUtility flightTimer = LerpedFloatUtility.unit();
    public final LerpedFloatUtility breathTimer = LerpedFloatUtility.unit();

    public FellBeastEntity(EntityType<? extends DragonEntity> type, Level worldIn) {
        super(type, worldIn);
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 3.5625F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(BREATHING_FIRE, false);

    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (level.isClientSide && key.equals(BREATHING_FIRE) && getBreathingFire()) ;
        else super.onSyncedDataUpdated(key);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(MAX_HEALTH, 130)
                .add(MOVEMENT_SPEED, 0.22)
                .add(KNOCKBACK_RESISTANCE, 1)
                .add(FOLLOW_RANGE, 60)
                .add(ATTACK_KNOCKBACK, 4)
                .add(ATTACK_DAMAGE, 12)
                .add(FLYING_SPEED, 0.121);
    }

    @Override
    public EntityHandler<DragonEntity> getSerializer() {
        return SERIALIZER;
    }

    public void setBreathingFire(boolean breathingFire) {
        if (!level.isClientSide) entityData.set(BREATHING_FIRE, breathingFire);
    }


    public boolean getBreathingFire() {
        return entityData.get(BREATHING_FIRE);
    }

    @Override
    public boolean defendsHome() {
        return true;
    }

    @Override
    public boolean shouldSleep() {
        return super.shouldSleep();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        EntityDimensions size = getType().getDimensions().scale(getScale());
        if (isSleeping()) size = size.scale(1, 0.5f);
        return size;
    }

    @Override
    public Vec3 getApproximateMouthPos() {
        Vec3 rotVector = calculateViewVector(getXRot() * 0.65f, yHeadRot);
        Vec3 position = getEyePosition(1).subtract(0, 0.9, 0);
        position = position.add(rotVector.scale(getBbWidth() + 1.3));
        return position;
    }

    @Override
    public Attribute[] getScaledAttributes() {
        return ArrayUtils.addAll(super.getScaledAttributes(), ATTACK_KNOCKBACK);
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        flightTimer.add(isUsingFlyingNavigator() ? 0.1f : -0.085f);
        breathTimer.add(getBreathingFire() ? 0.15f : -0.2f);

        if (!level.isClientSide) {

            if (getBreathingFire() && getControllingPlayer() == null && getTarget() == null)
                setBreathingFire(false);


            if (breathTimer.get() == 1) {
                level.addFreshEntity(new FireBreathEntity(this));
            }
        }
    }

    private boolean shouldBreatheFire() {
        LivingEntity target = this.getTarget();
        if (target != null && target.isAlive()) {
            double distFromTarget = distanceToSqr(target);
            double degrees = Math.atan2(target.getZ() - getZ(), target.getX() - getX()) * (180 / Math.PI) - 90;
            double headAngle = Math.abs(Mth.wrapDegrees(degrees - yHeadRot));
            return (!isAtHome() && (distFromTarget > 100 || target.getY() - getY() > 3 || isUsingFlyingNavigator()) && headAngle < 30 && canBreatheFire());
        }
        return false;
    }

    public boolean canBreatheFire() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source == DamageSource.IN_WALL || super.isInvulnerableTo(source);
    }

    @Override
    public boolean isImmuneToArrows() {
        return false;
    }

    public boolean speciesCanWalk() {
        return true;
    }

    @Override
    public boolean speciesCanFly() {
        return true;
    }


    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return super.causeFallDamage(distance, damageMultiplier, source);
    }

    @Override
    public boolean dragonCanFly() {
        return super.dragonCanFly();
    }

    @Override
    public int getYawRotationSpeed() {
        return isUsingFlyingNavigator() ? 5 : 7;
    }

    @Override
    public boolean speciesCanBeRidden() {
        return true;
    }

    @Override
    public void setMountCameraAngles(boolean backView, EntityViewRenderEvent.CameraSetup event) {
        if (backView)
            event.getCamera().move(ClientEvents.getViewCollision(-8.5, this), 0, 0);
        else
            event.getCamera().move(ClientEvents.getViewCollision(-5, this), -0.75, 0);
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return super.canAddPassenger(passenger);
    }

    @Override
    public int getMaxPassengers() {
        return 1;
    }

    @Override
    public Vec3 getPassengerPosOffset(Entity entity, int index) {
        return new Vec3(0, getBbHeight() * 1.15, index == 0 ? 1.75f : 1.0);
    }

    public boolean speciesCanSwim() {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundIdleFellBeast.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }

    @Override
    public float getSoundVolume() {
        return 1.5f * getScale();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        goalSelector.addGoal(4, new MoveToHomeGoal(this));
        goalSelector.addGoal(5, new DragonAttackGoal(this));
        goalSelector.addGoal(9, new FlyerWanderGoal(this, 1));
        goalSelector.addGoal(10, new LookAtPlayerGoal(this, LivingEntity.class, 10f));
        goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        goalSelector.addGoal(3, new RunWhenLosingGoal(this, 0.2f, 0.001f, 20f, 1.15f, 1f));
        targetSelector.addGoal(3, new DefendHomeGoal(this));
        targetSelector.addGoal(4, new HurtByTargetGoal(this));
        targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false,
                e -> e.getType() == EntityType.PLAYER || e instanceof Animal || e instanceof AbstractVillager));
    }

    class DragonAttackGoal extends AnimatedGoal {
        private FellBeastEntity entity;

        boolean animationPlaying;
        int ticksUntilNextAttack;
        private boolean attackIsQueued;
        private int queuedAttackTimer;
        private int attackQueueTimer = 0;
        double inflateValue;
        int disableShieldTime;

        public DragonAttackGoal(FellBeastEntity entity) {
            super(entity);
            setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            this.entity = entity;
        }

        @Override
        public void start() {
            float test;
        }

        @Override
        public boolean canUse() {
            LivingEntity target = getTarget();
            if (target != null && target.isAlive()) {
                if (!isWithinRestriction(target.blockPosition())) return false;
                return TargetingConditions.forCombat().test(null, target);
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = getTarget();
            if (target != null && target.isAlive()) {
                if (!isWithinRestriction(target.blockPosition())) {
                    return false;
                }
                return TargetingConditions.forCombat().test(null, target);
            }
            return false;
        }

        @Override
        public void tick() {
            LivingEntity target = getTarget();
            double distFromTarget = distanceToSqr(target);
            boolean isBreathingFire = getBreathingFire();
            boolean canSeeTarget = getSensing().hasLineOfSight(target);
            if (attackIsQueued) {
                if (this.attackQueueTimer == queuedAttackTimer) {
                    attackQueueTimer = 0;
                    attackIsQueued = false;
                    attackInBox(getOffsetBox(getBbWidth()).inflate(inflateValue), disableShieldTime);
                } else {
                    attackQueueTimer++;
                }
            } else {

                if (animationPlaying) {
                    if (super.canContinueToUse()) {
                        super.tick();
                    } else {
                        super.stop();
                        animationPlaying = false;
                    }
                }

                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

                getLookControl().setLookAt(target, 90, 90);

                if (getRandom().nextDouble() < 0.001 || distFromTarget > 900) {
                    setNavigator(NavigationType.FLYING);
                }

                if (entity.shouldBreatheFire() != isBreathingFire) {
                    if (!animationPlaying) {
                        setBreathingFire(entity.shouldBreatheFire());
                        animationPlaying = true;
                        super.start(FIRE_ANIMATION, FIRE_ANIMATION_TYPE, FIRE_ANIMATION_TIME, FIRE_ANIMATION_MOVES);
                    }
                }
                else if (distFromTarget <= 24 && !isBreathingFire && canSeeTarget && !isUsingFlyingNavigator()) {
                    this.checkAndPerformAttack();
                }
            }

            if (getNavigation().isDone()) {
                boolean isFlyingTarget = target instanceof DragonEntity && ((DragonEntity) target).isUsingFlyingNavigator();
                double y = target.getY() + (!isFlyingTarget && getRandom().nextDouble() > 0.1 ? 8 : 0);
                getNavigation().moveTo(target.getX(), y, target.getZ(), !isUsingFlyingNavigator() && isBreathingFire ? 0.8d : 1.3d);
            }

        }

        @Override
        public void stop() {
            LivingEntity livingentity = this.entity.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.entity.setTarget((LivingEntity) null);
            }
            this.entity.setAggressive(false);
            this.entity.getNavigation().stop();
            this.entity.setBreathingFire(false);
            super.stop();
        }

        protected void checkAndPerformAttack() {
            LivingEntity target = getTarget();
            if (this.ticksUntilNextAttack <= 0) {
                int attackVariant = 1+entity.random.nextInt(ATTACK_ANIMATION_VARIANTS) /*+ 1*/;
                String attackAnimation = ATTACK_ANIMATION + attackVariant;
                int attackAnimationTime = 0;

                switch (attackVariant) {
                    case 1:
                        attackAnimationTime = ATTACK_ANIMATION_TIME_1;
                        this.ticksUntilNextAttack = (int) ATTACK_ANIMATION_TIME_1;
                        inflateValue = 0.2;
                        disableShieldTime = 50;
                        this.queuedAttackTimer = ATTACK_QUEUE_TIME_1;
                        break;
                    case 2:
                        attackAnimationTime = ATTACK_ANIMATION_TIME_2;
                        this.ticksUntilNextAttack = (int) ATTACK_ANIMATION_TIME_2;
                        inflateValue = 0.2;
                        disableShieldTime = 50;
                        this.queuedAttackTimer = ATTACK_QUEUE_TIME_2;

                        break;

                    case 3:
                        attackAnimationTime = ATTACK_ANIMATION_TIME_3;
                        this.ticksUntilNextAttack = (int) ATTACK_ANIMATION_TIME_3;
                        inflateValue = 0.2;
                        disableShieldTime = 50;
                        this.queuedAttackTimer = ATTACK_QUEUE_TIME_3;
                        break;
                }

                if (!animationPlaying) {
                    animationPlaying = true;
                    super.start(attackAnimation, ATTACK_ANIMATION_TYPE, attackAnimationTime, ATTACK_ANIMATION_MOVES);
                    this.attackIsQueued = true;
                }
            }
        }
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.BLUE;
    }
}