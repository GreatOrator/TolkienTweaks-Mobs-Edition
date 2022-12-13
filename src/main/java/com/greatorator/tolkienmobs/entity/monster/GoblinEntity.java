package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.GoblinFlockToSameKindGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.GoblinPanicOnFlockDeathGoal;
import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class GoblinEntity extends MonsterEntity {
    private static final EntityDataAccessor<Boolean> PANICKED = SynchedEntityData.defineId(GoblinEntity.class, EntityDataSerializers.BOOLEAN);

    public GoblinEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new GoblinPanicOnFlockDeathGoal(this, 2.0F));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(4, new GoblinEntity.AttackTurtleEggGoal(this, 1.0D, 3));
        this.goalSelector.addGoal(4, new GoblinFlockToSameKindGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(RandomUtility.getRandomInteger(1, 10) > 10 ? GoblinEntity.class : GoblinKingEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            if (entity instanceof LivingEntity) {
                int duration;
                switch (level.getDifficulty()) {
                    case EASY:
                        duration = 7;
                        break;
                    default:
                    case NORMAL:
                        duration = 15;
                        break;
                    case HARD:
                        duration = 30;
                        break;
                }

                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, duration * 20, 0));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return TolkienSounds.soundIdleGoblin.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return TolkienSounds.soundHurtGoblin.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return TolkienSounds.soundDeathGoblin.get();
    }

    public boolean isPanicked() {
        return entityData.get(PANICKED);
    }

    public void setPanicked(boolean flag) {
        entityData.set(PANICKED, flag);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (level.isClientSide && isPanicked()) {
            for (int i = 0; i < 2; i++) {
                this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (this.random.nextDouble() - 0.5D) * this.getBbWidth() * 0.5, this.getY() + this.getEyeHeight(), this.getZ() + (this.random.nextDouble() - 0.5D) * this.getBbWidth() * 0.5, 0, 0, 0);
            }
        }
    }

    class AttackTurtleEggGoal extends RemoveBlockGoal {
        AttackTurtleEggGoal(PathfinderMob creatureIn, double speed, int yMax) {
            super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
        }

        @Override
        public void playDestroyProgressSound(LevelAccessor worldIn, BlockPos pos) {
            worldIn.playSound((Player)null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + GoblinEntity.this.random.nextFloat() * 0.2F);
        }

        @Override
        public void playBreakSound(Level worldIn, BlockPos pos) {
            worldIn.playSound((Player)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + worldIn.random.nextFloat() * 0.2F);
        }

        @Override
        public double acceptedDistance() {
            return 1.14D;
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }
}