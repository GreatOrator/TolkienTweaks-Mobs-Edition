package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.AmbientEntity;
import com.greatorator.tolkienmobs.entity.ambient.variant.AmbientVariant;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.init.TolkienTags;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FrogEntity extends AmbientEntity implements IAnimatable {
    protected final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> INSECT = SynchedEntityData.defineId(FrogEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(FrogEntity.class, EntityDataSerializers.INT);

    public FrogEntity(EntityType<? extends AmbientEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FrogEntity.FrogPanicGoal(this, 2.2D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(TolkienTags.items.INSECTS), true));
        this.goalSelector.addGoal(4, new FrogEntity.FrogAvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new FrogEntity.FrogAvoidEntityGoal<>(this, Wolf.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new FrogEntity.FrogAvoidEntityGoal<>(this, Monster.class, 4.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    /** Animation */
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        int rand = RandomUtility.getRandomInteger(100, 1);
        if (event.isMoving()) {
            this.spawnSprintParticle();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        } else if(this.isInWater()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", true));
            return PlayState.CONTINUE;
        } else if (rand <= 50) {
            this.playSound(TolkienSounds.soundIdleToaddle.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        } else if(rand <= 80){
            setCatching(true);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle2", false));
            setCatching(false);
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle3", true));
            return PlayState.CONTINUE;
        }
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.isAggressive() && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));

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

    /** VARIANTS */
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        AmbientVariant variant = Util.getRandom(AmbientVariant.values(), this.random);
        setVariant(variant);
        setFrogType(variant.getId());
        groupData = super.finalizeSpawn(levelAccessor, instance, type, groupData, tag);
        return groupData;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(INSECT, false);
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("FrogType", this.getFrogType());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setFrogType(tag.getInt("FrogType"));
    }

    private void setFrogType(int frogType) {
        if (frogType == 8) {
            Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).setBaseValue(8.0D);
            Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.6D);
            Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(15.0D);
            Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(15.0D);
            this.goalSelector.addGoal(4, new FrogEntity.EvilFrogAttackGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Wolf.class, true));
            if (!this.hasCustomName()) {
                this.setCustomName(new TranslatableComponent("entity.tolkienmobs.entityttmfrog.murder"));
            }
        }
        this.entityData.set(DATA_TYPE_ID, frogType);
    }

    private int getFrogType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public void setCatching(boolean catching) {
        this.entityData.set(INSECT, catching);
    }

    public boolean isCatching() {
        return this.entityData.get(INSECT);
    }

    /** Goals */
    static class EvilFrogAttackGoal extends MeleeAttackGoal {
        public EvilFrogAttackGoal(FrogEntity entity) {
            super(entity, 1.4D, true);
        }

        @Override
        protected double getAttackReachSqr(LivingEntity livingEntity) {
            return (double)(4.0F + livingEntity.getBbWidth());
        }
    }

    static class FrogAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        private final FrogEntity frog;

        public FrogAvoidEntityGoal(FrogEntity entity, Class<T> tClass, float p_29745_, double p_29746_, double p_29747_) {
            super(entity, tClass, p_29745_, p_29746_, p_29747_);
            this.frog = entity;
        }

        @Override
        public boolean canUse() {
            return this.frog.getFrogType() != 8 && super.canUse();
        }
    }

    public void setSpeedModifier(double p_29726_) {
        this.getNavigation().setSpeedModifier(p_29726_);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_29726_);
    }

    static class FrogPanicGoal extends PanicGoal {
        private final FrogEntity frog;

        public FrogPanicGoal(FrogEntity p_29775_, double p_29776_) {
            super(p_29775_, p_29776_);
            this.frog = p_29775_;
        }

        @Override
        public void tick() {
            super.tick();
            this.frog.setSpeedModifier(this.speedModifier);
        }
    }

    /** Sounds */
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtToaddle.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathToaddle.get();
    }

    @Nonnull
    @Override
    public SoundSource getSoundSource() {
        return this.getFrogType() == 8 ? SoundSource.HOSTILE : SoundSource.NEUTRAL;
    }

    @Override
    public boolean doHurtTarget(@Nonnull Entity entityIn) {
        if (this.getFrogType() == 8) {
            this.playSound(TolkienSounds.soundAngryToaddle.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            return entityIn.hurt(DamageSource.mobAttack(this), 8.0F);
        }
        else {
            return entityIn.hurt(DamageSource.mobAttack(this), 3.0F);
        }
    }

    public static boolean checkFrogSpawnRules(EntityType<FrogEntity> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        BlockState blockstate = accessor.getBlockState(pos.below());
        int chance = 100; //1 in x
        return random.nextInt(chance) == 0 && (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.SNOW) || blockstate.is(Blocks.SAND)) && pos.getY() < accessor.getSeaLevel() + 4 && accessor.getRawBrightness(pos, 0) > 8;
    }
}