package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class EntityTTMMinotaur extends EntityTTMMonsters {
    private static final DataParameter<Integer> MINOTAUR_TYPE = EntityDataManager.defineId(EntityTTMMinotaur.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/minotaur.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/minotaur.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/minotaur.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/minotaur.png"));
    });
    public EntityTTMMinotaur(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityTTMGoblin.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityTTMMordorOrc.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityTTMGoblinKing.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityTTMUrukHai.class, true));
    }

    public static boolean checkMinotaurSpawn(EntityType<EntityTTMMinotaur> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleMinotaur.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtMinotaur.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathMinotaur.get();
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundGenerator.soundStepMinotaur.get(), 0.25F, 1.0F);
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getMinotaurTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getMinotaurType(), TEXTURE_BY_ID.get(0));
    }

    public int getMinotaurType() {
        return this.entityData.get(MINOTAUR_TYPE);
    }

    public void setMinotaurType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(MINOTAUR_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setMinotaurType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MINOTAUR_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("MinotaurType", this.getMinotaurType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setMinotaurType(compound.getInt("MinotaurType"));
    }

}
//    public EntityTMMinotaur(World worldIn) {
//        super(worldIn);
//        this.setSize(2.3F, 6.9F);
//        this.setWeaponType(TTMFeatures.AXE_MORGULIRON);
//        this.setLootTable(LootInit.MINOTAUR);
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && this.posY < 64.0D && !this.world.canSeeSky(new BlockPos(this)) && willSpawn <= 10;
//    }
//
//    protected SoundEvent getAmbientSound()
//    {
//        return SoundInit.soundIdleMinotaur;
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundInit.soundHurtMinotaur;
//    }
//
//    protected SoundEvent getDeathSound()
//    {
//        return SoundInit.soundDeathMinotaur;
//    }
//
//    @Override
//    protected void playStepSound(BlockPos pos, Block blockIn)
//    {
//        this.playSound(SoundInit.soundStepMinotaur, 0.25F, 1.0F);
//    }
//
//    @Override
//    public double getAttackDamage() {
//        return 3.0D;
//    }
//
//    @Override
//    public double getArmorStrength() {
//        return 5.0D;
//    }
//
//    @Override
//    public double getHealthLevel() {
//        return 25.0D;
//    }
//}