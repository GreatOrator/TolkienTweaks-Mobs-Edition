package com.greatorator.tolkienmobs.entity.merchant;

import com.greatorator.tolkienmobs.entity.WanderingEntity;
import com.greatorator.tolkienmobs.entity.merchant.variant.EntityVariant;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class DesertDwellerEntity extends WanderingEntity {
    public DesertDwellerEntity(EntityType<? extends DesertDwellerEntity> type, Level level) {
        super(type, level);
    }

    /** VARIANTS */
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        EntityVariant variant = EntityVariant.byId(RandomUtility.getRandomInteger(10, 0));
        setVariant(variant);
    return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
}

    @Override
    public EntityVariant getVariant() {
        return EntityVariant.byId(this.getTypeVariant() & 255);
    }
}