package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.entity.ambient.variant.AmbientVariant;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public abstract class AmbientEntity extends PathfinderMob {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(AmbientEntity.class, EntityDataSerializers.INT);
    protected AmbientEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }


    /** VARIANTS */
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        AmbientVariant variant = Util.getRandom(AmbientVariant.values(), this.random);
        setVariant(variant);
        groupData = super.finalizeSpawn(levelAccessor, instance, type, groupData, tag);
        return groupData;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    public AmbientVariant getVariant() {
        return AmbientVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(AmbientVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    public static boolean checkAmbientSpawn(EntityType<? extends AmbientEntity> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, Random random) {
        int chance = 100; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(entityType, accessor, spawnType, pos, random);
    }
}