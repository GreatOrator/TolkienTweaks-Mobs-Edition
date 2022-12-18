package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.RangedWebAttackGoal;
import com.greatorator.tolkienmobs.handler.interfaces.GoalSelectorAccessor;
import com.greatorator.tolkienmobs.handler.interfaces.TrapsTarget;
import com.greatorator.tolkienmobs.handler.interfaces.WebShooter;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class MirkwoodSpiderEntity extends MonsterEntity implements IAnimatable, WebShooter {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Boolean> WEB_SHOOTING = SynchedEntityData.defineId(MirkwoodSpiderEntity.class, EntityDataSerializers.BOOLEAN);
    private RangedWebAttackGoal<?> rangedWebAttackGoal;
    private LeapAtTargetGoal leapAtTargetGoal;
    private MeleeAttackGoal meleeAttackGoal;
    public int targetTrappedCounter = 0;

    public MirkwoodSpiderEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.53125F;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        ((GoalSelectorAccessor) this.goalSelector)
                .getAvailableGoals()
                .stream()
                .filter(pg -> pg.getPriority() == 3 && pg.getGoal() instanceof LeapAtTargetGoal)
                .findFirst()
                .ifPresent(pg -> {
                    this.leapAtTargetGoal = (LeapAtTargetGoal) pg.getGoal();
                });
        this.goalSelector.addGoal(4, new MirkwoodSpiderEntity.MirkwoodSpiderEntityAttackGoal(this));
        ((GoalSelectorAccessor) this.goalSelector)
                .getAvailableGoals()
                .stream()
                .filter(pg -> pg.getPriority() == 4 && pg.getGoal() instanceof MeleeAttackGoal)
                .findFirst()
                .ifPresent(pg -> {
                    this.meleeAttackGoal = (MeleeAttackGoal) pg.getGoal();
                });
        this.rangedWebAttackGoal = new RangedWebAttackGoal<>(this, 1.0D, 60, 20.0F);
        this.targetSelector.addGoal(2, new MirkwoodSpiderEntity.MirkwoodSpiderEntityTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new MirkwoodSpiderEntity.MirkwoodSpiderEntityTargetGoal<>(this, IronGolem.class));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if (!event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (!this.isWebShooting() && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            this.swinging =false;
        }else if (this.isWebShooting() && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack_web", false));
            this.entityData.set(WEB_SHOOTING, false);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Goals */
    static class MirkwoodSpiderEntityAttackGoal extends MeleeAttackGoal {
        public MirkwoodSpiderEntityAttackGoal(MirkwoodSpiderEntity p_33822_) {
            super(p_33822_, 1.0D, true);
        }

        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        public boolean canContinueToUse() {
            float f = this.mob.getBrightness();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity p_33825_) {
            return (double)(4.0F + p_33825_.getBbWidth());
        }
    }

    static class MirkwoodSpiderEntityTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public MirkwoodSpiderEntityTargetGoal(MirkwoodSpiderEntity p_33832_, Class<T> p_33833_) {
            super(p_33832_, p_33833_, true);
        }

        public boolean canUse() {
            float f = this.mob.getBrightness();
            return f >= 0.5F ? false : super.canUse();
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.reassessAttackGoals();
    }

    private void reassessAttackGoals() {
        LivingEntity target = this.getTarget();
        if(this.meleeAttackGoal != null
                && this.rangedWebAttackGoal != null
                && target != null){
            if(!this.isTargetTrapped()){
                this.goalSelector.removeGoal(this.meleeAttackGoal);
                if(this.leapAtTargetGoal != null){
                    this.goalSelector.removeGoal(this.leapAtTargetGoal);
                }
                this.goalSelector.addGoal(4, (Goal) this.rangedWebAttackGoal);
            } else{
                this.goalSelector.removeGoal((Goal) this.rangedWebAttackGoal);
                if(this.leapAtTargetGoal != null){
                    this.goalSelector.addGoal(3, this.leapAtTargetGoal);
                }
                this.goalSelector.addGoal(4, this.meleeAttackGoal);
            }
        }
    }

    @Override
    public boolean isTargetTrapped() {
        return this.targetTrappedCounter > 0;
    }

    @Override
    public void setWebShooting(boolean webShooting) {
        this.playSound(TolkienSounds.spiderShoot.get(), this.getSoundVolume(), this.getVoicePitch());
        this.entityData.set(WEB_SHOOTING, webShooting);
    }

    @Override
    public boolean isWebShooting() {
        return this.entityData.get(WEB_SHOOTING);
    }

    @Override
    public void setTargetTrapped(boolean trapped, boolean notifyOthers) {
        TargetingConditions spiderTargeting = TargetingConditions.forCombat().range(10.0D).ignoreInvisibilityTesting();

        if (notifyOthers) {
            List<Spider> spiders = this.level.getNearbyEntities(Spider.class, spiderTargeting, this, this.getBoundingBox().inflate(10.0D));

            for(Spider spider : spiders) {
                if (spider instanceof TrapsTarget && this.getTarget() != null && spider.getTarget() != null && spider.getTarget() == this.getTarget()) {
                    ((TrapsTarget)spider).setTargetTrapped(trapped, false);
                }
            }
        }
        if (trapped) {
            this.targetTrappedCounter = 20;
        } else {
            this.targetTrappedCounter = 0;
        }
    }


    /** Miscellaneous */
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        data = super.finalizeSpawn(accessor, instance, type, data, tag);

        if (accessor.getRandom().nextInt(100) == 0) {
            Skeleton skeleton = EntityType.SKELETON.create(this.level);
            skeleton.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            skeleton.finalizeSpawn(accessor, instance, type, (SpawnGroupData)null, (CompoundTag)null);
            skeleton.startRiding(this);
        }

        return data;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.targetTrappedCounter > 0) {
            this.targetTrappedCounter --;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WEB_SHOOTING, false);
    }

    @Override
    public ItemStack getHeldItem() {
        return super.getHeldItem();
    }

    @Override
    public void setHeldItem(ItemStack heldItem) {
    }
}