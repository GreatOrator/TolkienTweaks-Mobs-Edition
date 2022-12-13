package com.greatorator.tolkienmobs.entity.ambient;

import com.greatorator.tolkienmobs.entity.BirdEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({"removal" })
public class CrebainEntity extends BirdEntity {
    public CrebainEntity(EntityType<? extends BirdEntity> entityType, Level world) {
        super(entityType, world);
    }
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            CrebainEntity.this.setAggressive(false);
        }

        public void start() {
            super.start();
            CrebainEntity.this.setAggressive(true);
        }
    };
    private boolean scheduleWeaponGoalUpdate = true;

    @Override
    public boolean isOrderedToSit() {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Bat.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Parrot.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, RatEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, FrogEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, SquirrelEntity.class, true));
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
        this.goalSelector.addGoal(2, this.meleeGoal);
    }

    public static boolean checkCrebainSpawn(EntityType<CrebainEntity> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(entityType, accessor, spawnType, pos, random);
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return getAmbient(this.level, this.level.random);
    }

    public static SoundEvent getAmbient(Level level, Random random) {
        return TolkienSounds.soundCallCrebain.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TolkienSounds.soundHurtCrebain.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TolkienSounds.soundDeathCrebain.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
    }

    @Override
    public float getVoicePitch() {
        return getPitch(this.random);
    }

    public static float getPitch(Random random) {
        return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }
}