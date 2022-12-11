package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.AmbientEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;

public class RatEntity extends AmbientEntity implements IAnimatable {
    protected final AnimationFactory factory = new AnimationFactory(this);

    public RatEntity(EntityType<? extends AmbientEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RatEntity.RatPanicGoal(this, 2.2D));
        this.goalSelector.addGoal(4, new RatEntity.RatAvoidEntityGoal<>(this, Player.class, 8.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new RatEntity.RatAvoidEntityGoal<>(this, Wolf.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(4, new RatEntity.RatAvoidEntityGoal<>(this, Monster.class, 4.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    /** Animation */
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    /** Goals */
    static class RatAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        public RatAvoidEntityGoal(RatEntity entity, Class<T> tClass, float p_29745_, double p_29746_, double p_29747_) {
            super(entity, tClass, p_29745_, p_29746_, p_29747_);
        }

        @Override
        public boolean canUse() {
            return super.canUse();
        }
    }

    public void setSpeedModifier(double p_29726_) {
        this.getNavigation().setSpeedModifier(p_29726_);
        this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_29726_);
    }

    static class RatPanicGoal extends PanicGoal {
        private final RatEntity rat;

        public RatPanicGoal(RatEntity p_29775_, double p_29776_) {
            super(p_29775_, p_29776_);
            this.rat = p_29775_;
        }

        @Override
        public void tick() {
            super.tick();
            this.rat.setSpeedModifier(this.speedModifier);
        }
    }

    /** Sounds */
    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundIdleTMRat.get();
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtTMRat.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathTMRat.get();
    }
}