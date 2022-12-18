package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.HerdEntity;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Random;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class MumakilEntity extends HerdEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> DRINKING = SynchedEntityData.defineId(MumakilEntity.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    protected BlockPos waterPos;
    protected static Block spawnableBlock = Blocks.SAND;

    public MumakilEntity(EntityType<? extends HerdEntity> type, Level level) {
        super(type, level);
        this.maxUpStep = 1.0f;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 2.5F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MumakilMeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(5, new MumakilDrinkWaterGoal(this));
        this.goalSelector.addGoal(6, new MumakilMoveToWaterGoal(this, 1.0D, 8, 4));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 16.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
                .add(Attributes.FOLLOW_RANGE, 20.0D);
    }

    @Nullable
    @Override
    public MumakilEntity getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return TolkienEntities.ENTITY_TTM_MUMAKIL.get().create(serverLevel);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Override
    public int getMaxHeadYRot() {
        return 35;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return TolkienSounds.soundHurtMumakil.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundAmbientMumakil.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundHurtMumakil.get();
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean shouldHurt = target.hurt(DamageSource.mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (shouldHurt && target instanceof LivingEntity livingEntity) {
            Vec3 knockbackDirection = new Vec3(this.blockPosition().getX() - target.getX(), 0.0, this.blockPosition().getZ() - target.getZ()).normalize();
            float shieldBlockModifier = livingEntity.isDamageSourceBlocked(DamageSource.mobAttack(this)) ? 0.5f : 1.0f;
            livingEntity.knockback(shieldBlockModifier * 3.0D, knockbackDirection.x(), knockbackDirection.z());
            double knockbackResistance = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().add(0.0, 0.5f * knockbackResistance, 0.0));
        }
        this.playSound(SoundEvents.RAVAGER_ATTACK, 1.0f, 1.0f);
        return shouldHurt;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
        this.entityData.define(DRINKING, false);
    }

    public void setDrinking(boolean drinking) {
        this.entityData.set(DRINKING, drinking);
    }

    public boolean isDrinking() {
        return this.entityData.get(DRINKING);
    }

    /** Animation */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            if (this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walking_water", true));
            }else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("walking", true));
            }
            return PlayState.CONTINUE;
        }else if (this.isDrinking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("drink", true));
            return PlayState.CONTINUE;
        }else {
            if (this.isInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_water", true));
            }else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            }
            return PlayState.CONTINUE;
        }
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                10, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Goals */
    static class MumakilMeleeAttackGoal extends MeleeAttackGoal {
        public MumakilMeleeAttackGoal(PathfinderMob pathfinderMob, double speedMultiplier, boolean followingTargetEvenIfNotSeen) {
            super(pathfinderMob, speedMultiplier, followingTargetEvenIfNotSeen);
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return Mth.square(this.mob.getBbWidth());
        }
    }

    static class MumakilMoveToWaterGoal extends MoveToBlockGoal {
        private final MumakilEntity mumakil;

        public MumakilMoveToWaterGoal(MumakilEntity pathfinderMob, double speedModifier, int searchRange, int verticalSearchRange) {
            super(pathfinderMob, speedModifier, searchRange, verticalSearchRange);
            this.mumakil = pathfinderMob;
        }

        @Override
        public boolean canUse() {
            return !this.mumakil.isBaby() && !this.mumakil.isDrinking() && this.mumakil.waterPos == null && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.isReachedTarget() && super.canContinueToUse();
        }

        @Override
        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            if (level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (level.getFluidState(pos.relative(direction)).is(Fluids.WATER)) {
                        this.mumakil.waterPos = pos.relative(direction);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public double acceptedDistance() {
            return 2.5D;
        }

        @Override
        public void stop() {
            this.mumakil.setDrinking(true);
            super.stop();
        }
    }

    static class MumakilDrinkWaterGoal extends Goal {
        private final MumakilEntity mumakil;
        private int drinkTicks;

        public MumakilDrinkWaterGoal(MumakilEntity mumakil) {
            this.mumakil = mumakil;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.mumakil.waterPos == null || this.mumakil.distanceToSqr(Vec3.atCenterOf(this.mumakil.waterPos)) > 15) {
                this.mumakil.setDrinking(false);
                return false;
            }
            return this.mumakil.isDrinking();
        }

        @Override
        public boolean canContinueToUse() {
            return this.drinkTicks > 0 && super.canContinueToUse();
        }

        @Override
        public void start() {
            this.drinkTicks = 150;
            if (this.mumakil.waterPos != null) {
                this.mumakil.getLookControl().setLookAt(Vec3.atCenterOf(this.mumakil.waterPos));
            }
        }

        @Override
        public void tick() {
            this.drinkTicks--;
            if (this.mumakil.waterPos != null) {
                this.mumakil.getLookControl().setLookAt(Vec3.atCenterOf(this.mumakil.waterPos));
            }
        }

        @Override
        public void stop() {
            this.mumakil.waterPos = null;
            this.mumakil.setDrinking(false);
        }
    }

    public static boolean checkMumakilSpawn(EntityType<? extends Animal> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(entityType, accessor, spawnType, pos, random) && accessor.getBlockState(pos.below()).is(spawnableBlock);
    }
}