package com.greatorator.tolkienmobs.entity.special;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import com.greatorator.tolkienmobs.entity.passive.AurochEntity;
import com.greatorator.tolkienmobs.entity.passive.GoatEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GreatEagleEntity extends BirdEntity {
    private final ServerBossEvent eagleEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            GreatEagleEntity.this.setAggressive(false);
        }

        public void start() {
            super.start();
            GreatEagleEntity.this.setAggressive(true);
        }
    };
    private boolean scheduleWeaponGoalUpdate = true;

    public GreatEagleEntity(EntityType<? extends BirdEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.3125F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.ARMOR, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 21.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.75F)
                .add(Attributes.FLYING_SPEED, (double) 1.4F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AurochEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, GoatEntity.class, true));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Override
    public void tick() {
        if (scheduleWeaponGoalUpdate) {
            updateWeaponGoal();
        }
        super.tick();
    }

    protected void updateWeaponGoal() {
        scheduleWeaponGoalUpdate = false;
        this.goalSelector.removeGoal(this.meleeGoal);
        this.goalSelector.addGoal(4, this.meleeGoal);
    }

    /** Boss Section */
//    public int getInvulnerableTicks() {
//        return this.entityData.get(DATA_ID_INV);
//    }

//    public void setInvulnerableTicks(int invulnerableTicks) {
//        this.entityData.set(DATA_ID_INV, invulnerableTicks);
//    }

    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
//        tag.putInt("Invul", this.getInvulnerableTicks());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
//        this.setInvulnerableTicks(tag.getInt("Invul"));
        if (this.hasCustomName()) {
            this.eagleEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.eagleEvent.setName(this.getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
//        if (this.getInvulnerableTicks() > 0) {
//            int k1 = this.getInvulnerableTicks() - 1;
//            this.eagleEvent.setProgress(1.0F - (float) k1 / 220.0F);
//        }
        this.eagleEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

//    public void makeInvulnerable() {
//        this.setInvulnerableTicks(220);
//        this.eagleEvent.setProgress(0.0F);
//        this.setHealth(this.getMaxHealth() / 3.0F);
//    }

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.eagleEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.eagleEvent.removePlayer(serverPlayer);
    }

    /** Sound Region */
    @Override
    protected SoundEvent getAmbientSound() {
        return TolkienSounds.soundCallTMGreatEagle.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtTMGreatEagle.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathTMGreatEagle.get();
    }
}