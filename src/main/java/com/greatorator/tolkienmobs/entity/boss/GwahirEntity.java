package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GwahirEntity extends BirdEntity {
    private final ServerBossEvent gwahirEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            GwahirEntity.this.setAggressive(false);
        }

        public void start() {
            super.start();
            GwahirEntity.this.setAggressive(true);
        }
    };
    private boolean scheduleWeaponGoalUpdate = true;

    public GwahirEntity(EntityType<? extends BirdEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.5F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 240.0D)
                .add(Attributes.ARMOR, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 21.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.28F)
                .add(Attributes.FLYING_SPEED, (double) 1.6F);
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

            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(MobEffects.WATER_BREATHING)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.nodrown.gwahir").withStyle(ChatFormatting.DARK_BLUE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.onfire.gwahir").withStyle(ChatFormatting.DARK_RED), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.healself.gwahir").withStyle(ChatFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(MobEffects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslatableComponent(MODID + ".msg.speedup.gwahir").withStyle(ChatFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2 * 20, 0));
            }
        }
        return true;
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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
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
            this.gwahirEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        this.gwahirEvent.setName(this.getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
//        if (this.getInvulnerableTicks() > 0) {
//            int k1 = this.getInvulnerableTicks() - 1;
//            this.gwahirEvent.setProgress(1.0F - (float) k1 / 220.0F);
//        }
        this.gwahirEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

//    public void makeInvulnerable() {
//        this.setInvulnerableTicks(220);
//        this.gwahirEvent.setProgress(0.0F);
//        this.setHealth(this.getMaxHealth() / 3.0F);
//    }

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.gwahirEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.gwahirEvent.removePlayer(serverPlayer);
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
//    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.WHITE, BossInfo.Overlay.NOTCHED_12)).setDarkenScreen(true);
//
//    public GwahirEntity(EntityType<? extends BirdEntity> entityIn, World worldIn) {
//        super(entityIn, worldIn);
//    }
//
//    public static AttributeModifierMap.MutableAttribute registerAttributes() {
//        return FlyingEntity.createMobAttributes()
//                .add(Attributes.MAX_HEALTH, 240.0D)
//                .add(Attributes.ARMOR, 30.0D)
//                .add(Attributes.ATTACK_DAMAGE, 28.0D)
//                .add(Attributes.MOVEMENT_SPEED, (double)0.85F)
//                .add(Attributes.FLYING_SPEED, (double) 1.6F);
//    }
//
//    @Override
//    public boolean hurt(DamageSource source, float amount) {
//        if (!super.hurt(source, amount)) {
//            return false;
//        } else if (!(this.level instanceof ServerWorld)) {
//            return false;
//        } else {
//            ServerWorld serverworld = (ServerWorld) this.level;
//            LivingEntity livingentity = this.getTarget();
//            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
//                livingentity = (LivingEntity) source.getEntity();
//            }
//
//            if (livingentity == null) return true;
//
//            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nodrown.gwahir").withStyle(TextFormatting.DARK_BLUE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.WATER_BREATHING, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.onfire.gwahir").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself.gwahir").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
//            }
//
//            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
//                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup.gwahir").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
//                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundNBT compound) {
//        super.addAdditionalSaveData(compound);
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundNBT compound) {
//        super.readAdditionalSaveData(compound);
//        if (this.hasCustomName()) {
//            this.bossInfo.setName(this.getDisplayName());
//        }
//    }
//
//    @Override
//    public void setCustomName(@Nullable ITextComponent name) {
//        super.setCustomName(name);
//        this.bossInfo.setName(this.getDisplayName());
//    }
//
//    @Override
//    public void startSeenByPlayer(ServerPlayerEntity player) {
//        super.startSeenByPlayer(player);
//        this.bossInfo.addPlayer(player);
//    }
//
//    @Override
//    public void stopSeenByPlayer(ServerPlayerEntity player) {
//        super.stopSeenByPlayer(player);
//        this.bossInfo.removePlayer(player);
//    }
//
//    @Override
//    protected void customServerAiStep() {
//        if (this.tickCount % 20 == 0) {
//            this.heal(1.0F);
//        }
//
//        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
//    }
//
//    @Override
//    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
//        return p_213348_2_.height * 0.6F;
//    }
//
//    // Sound Region
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return SoundGenerator.soundCallTMGreatEagle.get();
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundGenerator.soundHurtTMGreatEagle.get();
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return SoundGenerator.soundDeathTMGreatEagle.get();
//    }
//    // End Region
}