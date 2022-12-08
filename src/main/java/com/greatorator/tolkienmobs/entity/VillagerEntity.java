package com.greatorator.tolkienmobs.entity;


import com.greatorator.tolkienmobs.entity.merchant.variant.MerchantVariant;
import com.greatorator.tolkienmobs.init.TolkienProfessions;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class VillagerEntity extends Villager {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(VillagerEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<VillagerData> DATA_MERCHANT_VARIANT = SynchedEntityData.defineId(VillagerEntity.class, EntityDataSerializers.VILLAGER_DATA);

    public VillagerEntity(EntityType<? extends VillagerEntity> type, Level level) {
        super(type, level);
    }

    @Nonnull
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
        this.entityData.define(DATA_MERCHANT_VARIANT, new VillagerData(VillagerType.PLAINS, TolkienProfessions.UNEMPLOYED_PROFESSION.get(), 1));
    }

    public void setProfession() {
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) <= 0 || this.entityData.get(DATA_ID_TYPE_VARIANT) > 16) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.UNEMPLOYED_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 1 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 9) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.COIN_TRADER_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 2 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 10) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.GROCERY_STORE_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 3 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 11) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.PET_MERCHANT_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 4 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 12) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.JUNK_TRADER_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 5 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 13) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.TRINKET_SMITH_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 6 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 14) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.TRINKET_TAILOR_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 7 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 15) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.UNEMPLOYED_PROFESSION.get()));
        }
        if (this.entityData.get(DATA_ID_TYPE_VARIANT) == 8 || this.entityData.get(DATA_ID_TYPE_VARIANT) == 16) {
            this.setVillagerData(this.getVillagerData().setProfession(TolkienProfessions.UNEMPLOYED_PROFESSION.get()));
        }
    }

    /** VARIANTS */
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor accessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        MerchantVariant variant = Util.getRandom(MerchantVariant.values(), this.random);
        setVariant(variant);
        setProfession();
        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    public MerchantVariant getVariant() {
        return MerchantVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(MerchantVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Nonnull
    @Override
    public VillagerData getVillagerData() {
        return this.entityData.get(DATA_MERCHANT_VARIANT);
    }

    @Override
    public void setVillagerData(VillagerData data) {
        VillagerData villagerdata = this.getVillagerData();
        if (villagerdata.getProfession() != data.getProfession()) {
            this.offers = null;
        }

        this.entityData.set(DATA_MERCHANT_VARIANT, data);
    }
}