package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.ai.goal.bird.*;
import com.greatorator.tolkienmobs.handler.interfaces.CommonTraits;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BirdEntity extends Animal implements IAnimatable, CommonTraits {
    protected final AnimationFactory factory = new AnimationFactory(this);
    protected static final EntityDataAccessor<Boolean> PECKING = SynchedEntityData.defineId(BirdEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(BirdEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COLLAR_COLOR = SynchedEntityData.defineId(BirdEntity.class, EntityDataSerializers.INT);

    @Nullable
    protected BlockPos stonePos;
    private boolean orderedToSit;

    protected BirdEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f)
                .add(Attributes.FLYING_SPEED, 0.3f);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        //this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.addGoal(2, new StartFlyingBirdGoal(this, 120));
        this.goalSelector.addGoal(3, new LandBirdGoal(this, 240));
        this.goalSelector.addGoal(4, new LookForwardBirdGoal(this));
        this.goalSelector.addGoal(5, new BirdEntity.BirdPeckGoal(this));
        this.goalSelector.addGoal(5, new FlyRandomlyBirdGoal(this));
        this.goalSelector.addGoal(6, new WanderAroundBirdGoal(this, 1.0));
        this.goalSelector.addGoal(6, new BirdEntity.BirdMoveToStoneGoal(this, 1.0D, 8, 4));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected float getJumpPower() {
        return 0.55F;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    public boolean isTame() {
        return false;
    }

    @Nullable
    public LivingEntity getOwner() {
        return null;
    }

    public void setInSittingPose(boolean sitting) {
    }

    public boolean isInSittingPose() {
        return false;
    }

    public boolean isOrderedToSit() {
        return this.orderedToSit;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 vec3d = this.getDeltaMovement();
        if (!this.getFlying() && !this.onGround && vec3d.y < 0.0) {
            this.setDeltaMovement(new Vec3(vec3d.x, -0.1, vec3d.z));
        }
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    public boolean getFlying() {
        return (boolean)this.entityData.get(FLYING);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel level, @Nonnull AgeableMob mob) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLYING, false);
        this.entityData.define(PECKING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Flying", this.getFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setFlying(nbt.getBoolean("Flying"));
    }

    protected float playFlySound(float volume) {
        this.playSound(TolkienSounds.soundFlappingCrebain.get(), 0.15F, 1.0F);
        return volume / 2.0F;
    }

    /** Animation */
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround && vec3.y < -0.01) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("glide", true));
            return PlayState.CONTINUE;
        }
        if (event.isMoving() || vec3.y > 0.03f) {
            if (this.getFlying()) {
                this.playFlySound(1.5F);
                event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            } else {
                double speed = getMovementSpeed(this);
                if (speed > (double)0.2F) {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("run", true));
                }else {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
                }
                return PlayState.CONTINUE;
            }
            return PlayState.CONTINUE;
        }
        if (isInSittingPose()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", true));
            return PlayState.CONTINUE;
        }
        int rand = RandomUtility.getRandomInteger(100, 1);
        if (rand <= 10 && this.isPecking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle3", true));
        } else if (rand <= 30) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle2", true));
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.isAggressive() && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            if(!this.getFlying()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("attack_ground", false));
            }else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("attack_flight", false));
            }
            this.setAggressive(false);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    /** Goals */
    public void setPecking(boolean pecking) {
        this.entityData.set(PECKING, pecking);
    }

    public boolean isPecking() {
        return this.entityData.get(PECKING);
    }

    static class BirdMoveToStoneGoal extends MoveToBlockGoal {
        private final BirdEntity bird;

        public BirdMoveToStoneGoal(BirdEntity pathfinderMob, double speedModifier, int searchRange, int verticalSearchRange) {
            super(pathfinderMob, speedModifier, searchRange, verticalSearchRange);
            this.bird = pathfinderMob;
        }

        @Override
        public boolean canUse() {
            return !this.bird.isBaby() && !this.bird.isPecking() && this.bird.stonePos == null && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.isReachedTarget() && super.canContinueToUse();
        }

        @Override
        protected boolean isValidTarget(LevelReader level, @Nonnull BlockPos pos) {
            if (level.getBlockState(pos).is(Blocks.GRASS_BLOCK)) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    if (level.getBlockState(pos.relative(direction)).is(Blocks.STONE)) {
                        this.bird.stonePos = pos.relative(direction);
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
            this.bird.setPecking(true);
            super.stop();
        }
    }

    static class BirdPeckGoal extends Goal {
        private final BirdEntity bird;
        private int eatTicks;

        public BirdPeckGoal(BirdEntity bird) {
            this.bird = bird;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.bird.stonePos == null || this.bird.distanceToSqr(Vec3.atCenterOf(this.bird.stonePos)) > 15) {
                this.bird.setPecking(false);
                return false;
            }
            return this.bird.isPecking();
        }

        @Override
        public boolean canContinueToUse() {
            return this.eatTicks > 0 && super.canContinueToUse();
        }

        @Override
        public void start() {
            this.eatTicks = 150;
            if (this.bird.stonePos != null) {
                this.bird.getLookControl().setLookAt(Vec3.atCenterOf(this.bird.stonePos));
            }
        }

        @Override
        public void tick() {
            this.eatTicks--;
            if (this.bird.stonePos != null) {
                this.bird.getLookControl().setLookAt(Vec3.atCenterOf(this.bird.stonePos));
            }
        }

        @Override
        public void stop() {
            this.bird.stonePos = null;
            this.bird.setPecking(false);
        }
    }
}