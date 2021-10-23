package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonster;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMFlockToSameKind;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMPanicOnFlockDeath;
import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMGoblin extends EntityTTMMonster {
    private static final DataParameter<Boolean> PANICKED = EntityDataManager.defineId(EntityTTMGoblin.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GOBLIN_TYPE = EntityDataManager.defineId(EntityTTMGoblin.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblin1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblin2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblin3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblin4.png"));
    });

    public EntityTTMGoblin(EntityType<? extends EntityTTMGoblin> type, World worldIn) {
        super(type, worldIn);

        this.xpReward = 5;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TTMPanicOnFlockDeath(this, 2.0F));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(4, new EntityTTMGoblin.AttackTurtleEggGoal(this, 1.0D, 3));
        this.goalSelector.addGoal(4, new TTMFlockToSameKind(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(TTMRand.getRandomInteger(1, 10) > 10 ? EntityTTMGoblin.class : EntityTTMGoblinKing.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
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

                ((LivingEntity) entity).addEffect(new EffectInstance(Effects.HUNGER, duration * 20, 0));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleGoblin.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtGoblin.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathGoblin.get();
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

    class AttackTurtleEggGoal extends BreakBlockGoal {
        AttackTurtleEggGoal(CreatureEntity creatureIn, double speed, int yMax) {
            super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
        }

        public void playDestroyProgressSound(IWorld worldIn, BlockPos pos) {
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundCategory.HOSTILE, 0.5F, 0.9F + EntityTTMGoblin.this.random.nextFloat() * 0.2F);
        }

        public void playBreakSound(World worldIn, BlockPos pos) {
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + worldIn.random.nextFloat() * 0.2F);
        }

        public double acceptedDistance() {
            return 1.14D;
        }
    }

//    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
//        super.setEquipmentBasedOnDifficulty(difficulty);
//        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(TTMContent.SWORD_MORGULIRON.get()));
//    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 8;
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getGoblinTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getGoblinType(), TEXTURE_BY_ID.get(0));
    }

    public int getGoblinType() {
        return this.entityData.get(GOBLIN_TYPE);
    }

    public void setGoblinType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(GOBLIN_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setGoblinType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GOBLIN_TYPE, 3);
        entityData.define(PANICKED, false);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("GoblinType", this.getGoblinType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setGoblinType(compound.getInt("GoblinType"));
    }
}