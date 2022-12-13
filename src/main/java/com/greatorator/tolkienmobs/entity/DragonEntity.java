package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.entity.ai.control.*;
import com.greatorator.tolkienmobs.entity.ai.goal.dragon.AnimatedGoal;
import com.greatorator.tolkienmobs.entity.ai.navigation.BetterPathNavigator;
import com.greatorator.tolkienmobs.entity.ai.navigation.DragonSwimmingNavigator;
import com.greatorator.tolkienmobs.entity.ai.navigation.FlyerPathNavigator;
import com.greatorator.tolkienmobs.entity.boss.WitchKingEntity;
import com.greatorator.tolkienmobs.handler.EntityHandler;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.particles.FlyingSound;
import com.greatorator.tolkienmobs.utils.MathUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import static net.minecraft.world.BossEvent.BossBarOverlay.PROGRESS;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public abstract class DragonEntity extends MonsterEntity implements IAnimatable {
    private final ServerBossEvent bossEvent;
    private int sleepCooldown;
    public float prevYRot;
    public float deltaYRot;
    public float adjustYaw;
    public float adjustmentYaw;
    public float prevSetYaw;
    public float setYaw;
    public float deltaPitch;
    public float adjustedPitch;
    public float deltaPitchLimit;
    public float targetPitchRadians;
    public float currentPitchRadians;
    public float deltaPitchExtremities;
    public float pitchExtremities;
    public float pitchExtremitiesRadians;
    public float prevSetExtremityPitch;
    public float setExtremityPitch;
    public float adjustExtremityPitch;
    public float adjustmentExtremityPitch;
    public Vec3 debugTarget;


    public enum NavigationType {
        GROUND,
        FLYING,
        SWIMMING
    }

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public static final EntityHandler<DragonEntity> SERIALIZER = EntityHandler.builder(b -> b
            .track(EntityHandler.POS.optional(), "HomePos", t -> Optional.ofNullable(t.getHomePos()), (d, v) -> d.setHomePos(v.orElse(null)))
            .track(EntityHandler.BOOL, "Sleeping", DragonEntity::isSleeping, DragonEntity::setSleeping));

    public static final byte HEAL_PARTICLES_EVENT_ID = 8;

    public static final EntityDataAccessor<Float> BREACH_ATTACK_COOLDOWN = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<Boolean> BREACHING = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.BLOCK_POS);
    public static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.BOOLEAN);
    protected static int IDLE_ANIMATION_VARIANTS;
    protected static int ATTACK_ANIMATION_VARIANTS;
    protected static int SITTING_ANIMATION_TIME;
    protected static int SLEEPING_ANIMATION_TIME;
    protected static final boolean ATTACK_ANIMATION_MOVES = true;

    private static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> ANIMATION_TYPE = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_TIME = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOVING_STATE = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> MANUAL_ANIMATION_CALL = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_MOVING_ANIMATION = SynchedEntityData.defineId(DragonEntity.class, EntityDataSerializers.BOOLEAN);

    protected DragonEntity(EntityType<? extends MonsterEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.bossEvent = ((ServerBossEvent)new ServerBossEvent(this.getDisplayName(), getBossNameColour(), PROGRESS).setDarkenScreen(false));
        maxUpStep = 1;
        this.noCulling = true;
    }

    /** Animation */
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "generalController", 0, this::generalPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public <E extends IAnimatable> PlayState generalPredicate(AnimationEvent<E> event) {
        String animation = this.getAnimation();
        if (!animation.equals("base")) {
            int animationType = this.getAnimationType();

            if (this.getIsMovingAnimation()) {
                if (this.getDeltaMovement().length() !=0 && !this.isAggressive()) {
                    NavigationType navigationType = this.getNavigationType();
                    switch (navigationType) {
                        case GROUND -> animation = "walk_" + animation;
                        case FLYING -> animation = "fly_" + animation;
                        case SWIMMING -> animation = "swim_" + animation;
                    }
                    event.getController().setAnimation(new AnimationBuilder().addAnimation(animation, ILoopType.EDefaultLoopTypes.LOOP));
                    return PlayState.CONTINUE;
                }
                if (this.getDeltaMovement().length() !=0 && !this.isAggressive()) {
                    NavigationType navigationType = this.getNavigationType();
                    animation = switch (navigationType) {
                        case GROUND -> "walk_fast_" + animation;
                        case FLYING -> "fly_fast_" + animation;
                        case SWIMMING -> "swim_fast_" + animation;
                    };
                    event.getController().setAnimation(new AnimationBuilder().addAnimation(animation, ILoopType.EDefaultLoopTypes.LOOP));
                    return PlayState.CONTINUE;
                }
            }
            ILoopType loopType;
            switch (animationType) {
                case 1: loopType = ILoopType.EDefaultLoopTypes.LOOP;
                    break;
                case 2: loopType = ILoopType.EDefaultLoopTypes.PLAY_ONCE;
                    break;
                case 3: loopType = ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME;
                    break;
                default:
                    return PlayState.STOP;
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation(animation, loopType));
            return PlayState.CONTINUE;
        }
        //TODO: Custom Death Animations
        /*
        //Death
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
            return PlayState.CONTINUE;
        }
        */
        NavigationType navigationType = this.getNavigationType();
        if (this.getDeltaMovement().length() !=0 && this.isAggressive()) {
            switch (navigationType) {
                case GROUND -> event.getController().setAnimation(new AnimationBuilder().addAnimation("walk_fast", ILoopType.EDefaultLoopTypes.LOOP));
                case FLYING -> event.getController().setAnimation(new AnimationBuilder().addAnimation("fly_fast", ILoopType.EDefaultLoopTypes.LOOP));
                case SWIMMING-> event.getController().setAnimation(new AnimationBuilder().addAnimation("swim_fast", ILoopType.EDefaultLoopTypes.LOOP));
            }
            return PlayState.CONTINUE;
        }
        if (this.getDeltaMovement().length() !=0  && !this.isAggressive()) {
            switch (navigationType) {
                case GROUND -> event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
                case FLYING -> event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
                case SWIMMING -> event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", ILoopType.EDefaultLoopTypes.LOOP));
            }
            return PlayState.CONTINUE;
        }
        if (this.getRandom().nextDouble() < 0.001) {
            int idleVariant = this.random.nextInt(IDLE_ANIMATION_VARIANTS)+1;
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle"+idleVariant, ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (this.isUsingSwimmingNavigator()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle0", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    /** Entity Data */
    @Override
    protected void defineSynchedData()
    {
        this.entityData.define(ANIMATION, "base");
        this.entityData.define(ANIMATION_TYPE, 1);
        this.entityData.define(MOVING_STATE, 0);
        this.entityData.define(ANIMATION_TIME, 0);
        this.entityData.define(MANUAL_ANIMATION_CALL, false);
        this.entityData.define(IS_MOVING_ANIMATION, false);
        entityData.define(HOME_POS, BlockPos.ZERO);
        entityData.define(BREACHING, false);
        entityData.define(BREACH_ATTACK_COOLDOWN, 600F);
        entityData.define(SLEEPING, false);
        super.defineSynchedData();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        //        tag.putInt("Invul", this.getInvulnerableTicks());

        ((EntityHandler<DragonEntity>) getSerializer()).serialize(this, nbt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        //        this.setInvulnerableTicks(tag.getInt("Invul"));
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
        ((EntityHandler<DragonEntity>) getSerializer()).deserialize(this, nbt);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag dataTag) {

        WitchKingEntity entitywitchking = TolkienEntities.ENTITY_TTM_WITCHKING.get().create(this.level);
        entitywitchking.moveTo(this.getX(), this.getY(), this.getZ(), this.yRotO, 0.0F);
        entitywitchking.finalizeSpawn(level, difficulty, reason, (SpawnGroupData)null, (CompoundTag)null);
        entitywitchking.startRiding(this);

        return super.finalizeSpawn(level, difficulty, reason, data, dataTag);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (key.equals(SLEEPING) || isUsingFlyingNavigator()) {
            refreshDimensions();
            if (level.isClientSide  && isUsingFlyingNavigator() && canBeControlledByRider())
                FlyingSound.play(this);
        } else super.onSyncedDataUpdated(key);
    }


    public EntityHandler<? extends DragonEntity> getSerializer() {
        return SERIALIZER;
    }

    public String getAnimation() {
        return entityData.get(ANIMATION);
    }

    public void setAnimation(String animation) {
        entityData.set(ANIMATION, animation);
    }

    public int getAnimationType() {
        return entityData.get(ANIMATION_TYPE);
    }

    public void setAnimationType(int animation) {
        entityData.set(ANIMATION_TYPE, animation);
    }

    public int getAnimationTime() {
        return entityData.get(ANIMATION_TIME);
    }

    public void setAnimationTime(int animationTime) {
        entityData.set(ANIMATION_TIME, animationTime);
    }

    public boolean getManualAnimationCall() {
        return entityData.get(MANUAL_ANIMATION_CALL);
    }

    public void setManualAnimationCall(boolean manualAnimationCall) {
        entityData.set(MANUAL_ANIMATION_CALL, manualAnimationCall);
    }

    public boolean getIsMovingAnimation() {
        return entityData.get(IS_MOVING_ANIMATION);
    }

    public void setIsMovingAnimation(boolean movingAnimation) {
        entityData.set(IS_MOVING_ANIMATION, movingAnimation);
    }

    public int getMovingState() {
        return entityData.get(MOVING_STATE);
    }

    public void setMovingState(int movingState) {
        entityData.set(MOVING_STATE, movingState);
    }

    public void setBreaching(boolean breaching) {
        entityData.set(BREACHING, breaching);
    }

    public boolean getBreaching() {
        return entityData.get(BREACHING);
    }

    public void setBreachAttackCooldown(float breachCooldown) {
        entityData.set(BREACH_ATTACK_COOLDOWN, breachCooldown);

    }

    public float getBreachAttackCooldown() {
        return entityData.get(BREACH_ATTACK_COOLDOWN);

    }

    public boolean hasEntityDataAccessor(EntityDataAccessor<?> param) {
        return entityData.itemsById.containsKey(param.getId());
    }

    public Attribute[] getScaledAttributes() {
        return new Attribute[]{MAX_HEALTH, ATTACK_DAMAGE};
    }

    public float getTravelSpeed() {
        return (isUsingFlyingNavigator() && getAttributes().hasAttribute(FLYING_SPEED))? (float) getAttributeValue(FLYING_SPEED)
                : (float) getAttributeValue(MOVEMENT_SPEED);
    }

    public static boolean canFlyerSpawn(EntityType<? extends DragonEntity> entityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        return serverLevelAccessor.getBlockState(pos.below()).getFluidState().isEmpty();
    }

    /** Home location */
    public boolean isAtHome() {
        return hasRestriction() && isWithinRestriction();
    }

    public boolean trySafeTeleport(BlockPos pos) {
        if (level.noCollision(this, getBoundingBox().move(pos.subtract(blockPosition()))))
        {
            moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, getYRot(), getXRot());
            return true;
        }
        return false;
    }

    @Override
    public BlockPos getRestrictCenter() {
        BlockPos pos = getHomePos();
        return pos == null? BlockPos.ZERO : pos;
    }

    @javax.annotation.Nullable
    public BlockPos getHomePos() {
        BlockPos pos = entityData.get(HOME_POS);
        return pos == BlockPos.ZERO? null : pos;
    }

    public void setHomePos(@javax.annotation.Nullable BlockPos pos) {
        entityData.set(HOME_POS, pos == null? BlockPos.ZERO : pos);
    }

    public void clearHome() {
        setHomePos(null);
    }

    @Override
    public boolean hasRestriction() {
        return getHomePos() != null;
    }

    @Override
    public float getRestrictRadius() {
        return TolkienConfig.homeRadius * TolkienConfig.homeRadius;
    }

    @Override
    public void restrictTo(BlockPos pos, int distance) {
        setHomePos(pos);
    }

    @Override
    public boolean isWithinRestriction() {
        return isWithinRestriction(blockPosition());
    }

    @Override
    public boolean isWithinRestriction(BlockPos pos) {
        BlockPos home = getHomePos();
        return home == null || home.distSqr(pos) <= getRestrictRadius();
    }

    public boolean defendsHome() {
        return false;
    }

    /** Sleeping */
    @Override
    public boolean isSleeping() {
        return hasEntityDataAccessor(SLEEPING) && entityData.get(SLEEPING);
    }

    public void setSleeping(boolean sleep) {
        if (isSleeping() == sleep) return;

        entityData.set(SLEEPING, sleep);
        if (!level.isClientSide)
        {
            if (sleep)
            {
                setAnimation("sleeping");
                setAnimationTime(SLEEPING_ANIMATION_TIME);
                setAnimationType(3);
                setManualAnimationCall(true);
                setIsMovingAnimation(false);
                clearAI();
                setXRot(0);
            }
            else sleepCooldown = 350;
        }
    }

    public boolean shouldSleep() {
        if (sleepCooldown > 0) return false;
        if (level.isDay()) return false;
        if (!isIdling()) return false;

        return getRandom().nextDouble() < 0.0065;
    }


    public boolean shouldWakeUp() {
        return level.isDay() && getRandom().nextDouble() < 0.0065;
    }

    /** Miscellaneous */
    @Override
    public EntityDimensions getDimensions(Pose pose) {
        EntityDimensions size = getType().getDimensions().scale(getScale());
        if (isSleeping()) size = size.scale(1, 0.5f);
        return size;
    }

    @Override
    protected int getExperienceReward(Player player) {
        return Math.max((int) ((getBbWidth() * getBbHeight()) * 0.25) + getRandom().nextInt(3), super.getExperienceReward(player));
    }


    public Vec3 getApproximateMouthPos() {
        Vec3 position = getEyePosition(1).subtract(0, 0.75d, 0);
        double dist = (getBbWidth() / 2) + 0.75d;
        return position.add(calculateViewVector(getXRot(), yHeadRot).scale(dist));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(SpawnEggItem.byId(getType()));
    }

    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

//        if (this.getInvulnerableTicks() > 0) {
//            int k1 = this.getInvulnerableTicks() - 1;
//            this.balrogEvent.setProgress(1.0F - (float) k1 / 220.0F);
//        }


        NavigationType properNavigator = getProperNavigator();
        if (properNavigator != this.getNavigationType()) {
            setNavigator(properNavigator);
        }

        LivingEntity target = getTarget();
        if (target != null && (!target.isAlive() || !canAttack(target)))
            setTarget(null);

        if (sleepCooldown > 0) --sleepCooldown;
        if (isSleeping()) {
            if (getLookControl() instanceof DragonLookController) ((DragonLookController) getLookControl()).stopLooking();
            if (getHealth() < getMaxHealth() && getRandom().nextDouble() < 0.005) heal(1);

            if (shouldWakeUp()) {
                setSleeping(false);
            }
        } else if (shouldSleep()) {
            setSleeping(true);
        }

        if (this.isSleeping()) {
            this.setAnimation("sleep");
            this.setAnimationType(1);
            this.setAnimationTime(20);
            this.setManualAnimationCall(true);
            setIsMovingAnimation(false);
        }

        if (isUsingSwimmingNavigator() && level.isClientSide){
            deltaYRot = this.yRotO - prevYRot;
            prevYRot = this.yRotO;

            prevSetYaw = setYaw;

            if (adjustYaw > deltaYRot) {
                adjustYaw = adjustYaw - adjustmentYaw;
                adjustYaw = Math.max(adjustYaw, deltaYRot);
            } else if (adjustYaw < deltaYRot) {
                adjustYaw = adjustYaw + adjustmentYaw;
                adjustYaw = Math.min(adjustYaw, deltaYRot);
            }
            setYaw = (adjustYaw * (Mth.PI / 180.0F));

            if (!this.getBreaching()) {
                deltaPitch = this.xRotO - adjustedPitch;
                currentPitchRadians = adjustedPitch * (Mth.PI / 180.0F);
                if (Mth.abs(deltaPitch) > deltaPitchLimit) {
                    if (deltaPitch > 0) {
                        adjustedPitch = adjustedPitch + deltaPitchLimit;
                        deltaPitchExtremities = 30;
                    }
                    if (deltaPitch < 0) {
                        adjustedPitch = adjustedPitch - deltaPitchLimit;
                        deltaPitchExtremities = -30;
                    }
                }
                else {
                    adjustedPitch = xRotO;
                    deltaPitchExtremities = 0;
                }
                pitchExtremities = adjustedPitch + deltaPitchExtremities;

                targetPitchRadians = (adjustedPitch * (Mth.PI / 180.0F));
                pitchExtremitiesRadians = (pitchExtremities * (Mth.PI / 180.0F));

                prevSetExtremityPitch = setExtremityPitch;
                if (adjustExtremityPitch  > deltaPitch) {
                    adjustExtremityPitch = adjustExtremityPitch - adjustmentExtremityPitch;
                    adjustExtremityPitch = Math.max(adjustExtremityPitch, deltaPitch);
                } else if (adjustExtremityPitch  < deltaPitch) {
                    adjustExtremityPitch = adjustExtremityPitch + adjustmentExtremityPitch;
                    adjustExtremityPitch = Math.min(adjustExtremityPitch, deltaPitch);
                }
                setExtremityPitch = (adjustExtremityPitch * (Mth.PI / 180.0F));

            } else {
                targetPitchRadians = (float) -((Mth.atan2((this.getDeltaMovement().y), Mth.sqrt((float) ((this.getDeltaMovement().x) * (this.getDeltaMovement().x) + (this.getDeltaMovement().z) * (this.getDeltaMovement().z))))));
                currentPitchRadians = targetPitchRadians;
            }
        }
    }

    public void clearAI() {
        jumping = false;
        navigation.stop();
        setTarget(null);
        setSpeed(0);
        setYya(0);
    }

    public void attackInBox(AABB box) {
        attackInBox(box, 0);
    }

    public void attackInBox(AABB box, int disabledShieldTime) {
        List<LivingEntity> attackables = level.getEntitiesOfClass(LivingEntity.class, box, entity -> entity != this && !hasPassenger(entity));
        for (LivingEntity attacking : attackables) {
            doHurtTarget(attacking);
            if (disabledShieldTime > 0 && attacking instanceof Player) {
                Player player = ((Player) attacking);
                if (player.isUsingItem() && player.getUseItem().is(Items.SHIELD)) {
                    player.getCooldowns().addCooldown(Items.SHIELD, disabledShieldTime);
                    player.stopUsingItem();
                    level.broadcastEntityEvent(player, (byte) 9);
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isImmuneToArrows() && source.getDirectEntity() != null)
        {
            EntityType<?> attackSource = source.getDirectEntity().getType();
            if (attackSource == EntityType.ARROW) return false;
        }

        setSleeping(false);
        return super.hurt(source, amount);
    }

    @Deprecated
    public boolean isImmuneToArrows() {
        return false;
    }

    public List<LivingEntity> getEntitiesNearby(double radius, Predicate<LivingEntity> filter) {
        return level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(radius), filter.and(e -> e != this));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return !isAlliedTo(entity) && super.doHurtTarget(entity);
    }

    public boolean wantsToAttack(LivingEntity target, @Nullable LivingEntity owner) {
        return !isAlliedTo(target);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !canBeControlledByRider() && super.canAttack(target);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (isRiding() && source == DamageSource.IN_WALL) return true;
        if (isImmuneToArrows() && source == DamageSource.CACTUS) return true;
        return super.isInvulnerableTo(source);
    }

    @Override
    protected PathNavigation createNavigation(Level levelIn) {
        return new BetterPathNavigator(this);
    }

    public void setNavigator(NavigationType navigator) {
        if (navigator == getNavigationType()) return;
        switch (navigator) {
            case GROUND -> {
                this.moveControl = new MoveControl(this);
                this.lookControl = new DragonLookController(this);
                this.navigation = new BetterPathNavigator(this);
                this.setMovingState(0);
            }
            case FLYING -> {
                this.moveControl = new FlyerMoveController(this);
                this.lookControl = new DragonLookController(this);
                this.navigation = new FlyerPathNavigator(this);
                this.setMovingState(1);
                setSleeping(false);
            }
            case SWIMMING -> {
                this.moveControl = new SwimmingMoveControl(this);
                this.lookControl = new SwimmingLookControl(this, 10);
                this.navigation = new DragonSwimmingNavigator(this);
                this.setMovingState(2);
            }
        }
    }

    public NavigationType getProperNavigator() {
        boolean shouldUseFlyingNavigator = false;
        boolean shouldUseSwimmingNavigator = false;
        NavigationType navigationType = getNavigationType();
        boolean isUsingSwimmingNavigator = (navigationType == NavigationType.SWIMMING);
        boolean isUsingFlyingNavigator = (navigationType == NavigationType.FLYING);

        if (speciesCanSwim()) {
            shouldUseSwimmingNavigator = shouldUseSwimmingNavigator();
            if (shouldUseSwimmingNavigator != isUsingSwimmingNavigator) {
                return NavigationType.SWIMMING;
            }
        }
        if (speciesCanFly()) {
            shouldUseFlyingNavigator = shouldUseFlyingNavigator();
            if (shouldUseFlyingNavigator != isUsingFlyingNavigator) {
                return NavigationType.FLYING;
            }
        }
        if (speciesCanWalk()) {
            if (!shouldUseFlyingNavigator && !shouldUseSwimmingNavigator) {
                return NavigationType.GROUND;
            }
        }
        return navigationType;
    }

    public NavigationType getNavigationType() {
        PathNavigation navigation = this.getNavigation();
        if (navigation instanceof DragonSwimmingNavigator) {
            return NavigationType.SWIMMING;
        }
        else if (navigation instanceof FlyerPathNavigator) {
            return NavigationType.FLYING;
        }
        else if (navigation instanceof BetterPathNavigator) {
            return NavigationType.GROUND;
        }
        return NavigationType.GROUND;
    }

    public boolean isLandNavigator() {
        return this.getNavigation() instanceof GroundPathNavigation;
    }

    @Override
    protected BodyRotationControl createBodyControl() {
        return new DragonBodyController(this);
    }

    @Override
    public void travel(Vec3 vec3d){
        if (this.isAlive()) {
            if (this.isVehicle() && this.canBeControlledByRider()) {
                LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float groundX = livingentity.xxa * 0.5F;
                float groundZ = livingentity.zza;
                if (groundZ <= 0.0F) {
                    groundZ *= 0.25F;
                }

                this.flyingSpeed = this.getSpeed() * 0.1F;
                if (this.isControlledByLocalInstance()) {
                    float speed = getTravelSpeed();
                    if (isUsingFlyingNavigator())
                    {
                        if (getAltitude() <= getFlightThreshold()) setNavigator(NavigationType.GROUND);
                        this.setSpeed(speed * (25.0f/3.0f));
                        double moveX = livingentity.xxa;
                        double moveZ = livingentity.zza;
                        double moveY = -livingentity.getXRot() * (Math.PI / 180);
                        super.travel(new Vec3(moveX, moveY, moveZ));
                    } else {
                        if (dragonCanFly() && getAltitude() > getFlightThreshold()) setNavigator(NavigationType.FLYING);
                        else {
                            this.setSpeed(speed);
                            super.travel(new Vec3(groundX, vec3d.y, groundZ));
                        }
                    }
                }
                this.calculateEntityAnimation(this, isUsingFlyingNavigator());
                this.tryCheckInsideBlocks();
            } else {
                this.flyingSpeed = getTravelSpeed();
                super.travel(vec3d);
            }
        }
    }

    @Override
    public boolean isNoGravity() {
        return isUsingFlyingNavigator();
    }

    public boolean isIdling() {
        return getNavigation().isDone() && getTarget() == null && !isVehicle() && !isInWaterOrBubble() && !isUsingFlyingNavigator();
    }

    public AABB getOffsetBox(float offset) {
        return getBoundingBox().move(Vec3.directionFromRotation(0, yBodyRot).scale(offset));
    }

    public void setRotation(float yaw, float pitch) {
        this.setYRot(yaw % 360.0F);
        this.setXRot(pitch % 360.0F);
    }

    public boolean isRiding() {
        return getVehicle() != null;
    }


    @Override
    public boolean isSuppressingSlidingDownLadder() {
        return false;
    }


    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 0.0F;
    }

    public abstract boolean speciesCanWalk();

    public abstract boolean speciesCanFly();

    public boolean shouldUseFlyingNavigator() {
        if (getAltitude() >1) {
            if (!speciesCanSwim() && isUnderWater()) {
                return false;
            }
            return canLiftOff();
        }
        return false;
    }

    public boolean canLiftOff() {
        if (!dragonCanFly()) {
            return false;
        }

        if (!onGround) {
            return true;
        }

        int heightDiff = level.getHeight(Heightmap.Types.MOTION_BLOCKING, (int) getX(), (int) getZ()) - (int) getY();
        if (heightDiff > 0 && heightDiff <= getFlightThreshold())
            return false;
        return true;
    }

    public boolean dragonCanFly() {
        return !isLeashed() && speciesCanFly();
    }

    public boolean isUsingFlyingNavigator() {
        return getNavigation() instanceof FlyerPathNavigator;
    }

    public double getAltitude() {
        BlockPos.MutableBlockPos pos = blockPosition().mutable();

        while (pos.getY() > 0 && !level.getBlockState(pos.move(Direction.DOWN)).getMaterial().isSolid());
        return getY() - pos.getY();
    }

    public int getFlightThreshold() {
        return (int) getBbHeight();
    }

    public int getYawRotationSpeed() {
        return isUsingFlyingNavigator()? 6 : 75;
    }

    public void flapWings() {
        playSound(TolkienSounds.soundFlapFellBeast.get(), 3, 1, false);
        setDeltaMovement(getDeltaMovement().add(0, 1.285, 0));
    }

    @Override
    protected float getJumpPower() {
        return dragonCanFly()? (getBbHeight() * getBlockJumpFactor()) * 0.6f : super.getJumpPower();
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return !dragonCanFly() && super.causeFallDamage(distance - (int) (getBbHeight() * 0.8), damageMultiplier, source);
    }

    public abstract boolean speciesCanSwim();

    public boolean isUsingSwimmingNavigator() {
        return getNavigation() instanceof DragonSwimmingNavigator;
    }

    public boolean shouldUseSwimmingNavigator() {
        if (this.isInWater() && this.level.getBlockState(blockPosition().below()).canOcclude() && this.level.getBlockState(blockPosition().above()).is(Blocks.AIR)) {
            return false;
        }
        if (!this.isInWater() && this.isOnGround()) {
            return false;
        }
        if (!this.isInWater() && this.level.getFluidState(blockPosition().below()).is(FluidTags.WATER)) {
            return true;
        }
        if (!speciesCanFly() && !this.isInWater() && !this.isOnGround() && this.level.getBlockState(new BlockPos(position())).is(Blocks.AIR)) {
            return true;
        }
        if (this.isInWater() && !this.level.getBlockState(blockPosition().below()).canOcclude()) {
            return true;
        }
        return false;
    }

    public abstract boolean speciesCanBeRidden();

    public int getMaxPassengers(){
        return 1;
    }

    @Override
    public void rideTick() {
        super.rideTick();

        Entity entity = getVehicle();

        if (entity == null || !entity.isAlive()) {
            stopRiding();
            return;
        }

        setDeltaMovement(Vec3.ZERO);
        clearAI();

        if (entity instanceof Player) {
            Player player = (Player) entity;

            int index = player.getPassengers().indexOf(this);
            if ((player.isShiftKeyDown() && !player.getAbilities().flying) || isInWater() || index > 2) {
                stopRiding();
                return;
            }

            setXRot(player.getXRot() / 2);
            yHeadRot = yBodyRot = yRotO  = player.getYRot();
            setYRot(yHeadRot);
            setRotation(player.yHeadRot, player.getXRot());

            Vec3 vec3d = getRidingPosOffset(index);
            if (player.isFallFlying()) {
                if (!dragonCanFly()) {
                    stopRiding();
                    return;
                }

                vec3d = vec3d.scale(1.5);
                setNavigator(NavigationType.FLYING);
            }
            Vec3 pos = MathUtility.getYawVec(player.yBodyRot, vec3d.x, vec3d.z).add(player.getX(), player.getY() + vec3d.y, player.getZ());
            setPos(pos.x, pos.y, pos.z);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public Vec3 getRidingPosOffset(int passengerIndex) {
        double x = getBbWidth() * 0.5d + getVehicle().getBbWidth() * 0.5d;
        switch (passengerIndex)
        {
            default:
            case 0:
                return new Vec3(0, 1.81, 0);
            case 1:
                return new Vec3(x, 1.38d, 0);
        }
    }

    @Override
    public void positionRider(Entity passenger) {
        Vec3 offset = getPassengerPosOffset(passenger, getPassengers().indexOf(passenger));
        Vec3 pos = MathUtility.getYawVec(yBodyRot, offset.x, offset.z).add(getX(), getY() + offset.y + passenger.getMyRidingOffset(), getZ());
        passenger.setPos(pos.x, pos.y, pos.z);
    }

    public Vec3 getPassengerPosOffset(Entity entity, int index) {
        return new Vec3(0, getPassengersRidingOffset(), 0);
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (getControllingPassenger() == passenger) {
            clearAI();
            clearHome();
            if (isLeashed()) dropLeash(true, true);
        }
    }

    @Nullable
    public WitchKingEntity getControllingPlayer() {
        Entity passenger = getControllingPassenger();
        return passenger instanceof WitchKingEntity? (WitchKingEntity) passenger : null;
    }

    public void setMountCameraAngles(boolean backView, EntityViewRenderEvent.CameraSetup event) {
    }

    public void recievePassengerKeybind(int key, int mods, boolean pressed) {
    }

    @Override
    public boolean canBeRiddenInWater(Entity rider) {
        return false;
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() || isSleeping() || isRiding();
    }


    @Override
    public boolean canBeControlledByRider() {
        Entity entity = getControllingPassenger();
        return entity instanceof WitchKingEntity;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        List<Entity> passengers = getPassengers();
        return passengers.isEmpty()? null : passengers.get(0);
    }

    @Override
    protected boolean canAddPassenger(Entity entityIn) {
        return speciesCanBeRidden() && getPassengers().size() < getMaxPassengers();
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public boolean isAlliedTo(Entity entity) {
        if (entity == this) return true;
        if (entity instanceof LivingEntity) return true;
        return entity.isAlliedTo(getTeam());
    }

    @Override
    public boolean isPickable() {
        return super.isPickable() && !isRiding();
    }

    public InteractionResult playerInteraction(Player player, InteractionHand hand, ItemStack stack) {
        final InteractionResult SUCCESS = InteractionResult.sidedSuccess(level.isClientSide);

        if (canAddPassenger(player) && !player.isShiftKeyDown()) {
            if (!level.isClientSide) player.startRiding(this);
            return SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        InteractionResult result = stack.interactLivingEntity(player, this, hand);
        if (!result.consumesAction()) result = playerInteraction(player, hand, stack);
        if (result.consumesAction()) setSleeping(false);
        return result;
    }

    @Override
    public void dropLeash(boolean sendPacket, boolean dropLead) {
        super.dropLeash(sendPacket, dropLead);
        clearHome();
    }

    @Override
    public Component getDisplayName() {
        return super.getDisplayName();
    }

    public void doSpecialEffects() {
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == HEAL_PARTICLES_EVENT_ID) {
            for (int i = 0; i < getBbWidth() * getBbHeight(); ++i) {
                double x = getX() + MathUtility.nextDouble(getRandom()) * getBbWidth() + 0.4d;
                double y = getY() + getRandom().nextDouble() * getBbHeight();
                double z = getZ() + MathUtility.nextDouble(getRandom()) * getBbWidth() + 0.4d;
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, 0, 0, 0);
            }
        }
        else super.handleEntityEvent(id);
    }

    /** Sounds */
    @Override
    public void playSound(SoundEvent soundIn, float volume, float pitch) {
        playSound(soundIn, volume, pitch, false);
    }

    public void playSound(SoundEvent sound, float volume, float pitch, boolean local) {
        if (isSilent()) return;

        volume *= getSoundVolume();
        pitch *= getVoicePitch();

        if (local) level.playLocalSound(getX(), getY(), getZ(), sound, getSoundSource(), volume, pitch, false);
        else level.playSound(null, getX(), getY(), getZ(), sound, getSoundSource(), volume, pitch);
    }

    @Override
    public float getSoundVolume() {
        return getScale();
    }

    @Override
    public float getVoicePitch() {
        return ((random.nextFloat() - random.nextFloat()) * 0.2f + 1) * 2;
    }

    @Override
    public void playAmbientSound() {
        if (!isSleeping()) super.playAmbientSound();
    }


    @Override
    public void setYRot(float yRot){
        this.yRotO = yRot;
    }

    /** Goals */
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0,new AnimatedGoal(this,this.getAnimation(),this.getAnimationType(),this.getAnimationTime()));
    }

    /** Boss Info */
    public abstract BossEvent.BossBarColor getBossNameColour();

//    public int getInvulnerableTicks() {
//        return this.entityData.get(DATA_ID_INV);
//    }

//    public void setInvulnerableTicks(int invulnerableTicks) {
//        this.entityData.set(DATA_ID_INV, invulnerableTicks);
//    }

    @Override
    public void setCustomName(@org.jetbrains.annotations.Nullable net.minecraft.network.chat.Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

//    public void makeInvulnerable() {
//        this.setInvulnerableTicks(220);
//        this.balrogEvent.setProgress(0.0F);
//        this.setHealth(this.getMaxHealth() / 3.0F);
//    }
}
