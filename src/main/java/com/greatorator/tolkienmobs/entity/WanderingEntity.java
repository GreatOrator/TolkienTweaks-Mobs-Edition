package com.greatorator.tolkienmobs.entity;


import com.greatorator.tolkienmobs.entity.merchant.variant.EntityVariant;
import com.greatorator.tolkienmobs.init.TolkienProfessions;
import com.greatorator.tolkienmobs.init.TolkienTrades;
import com.greatorator.tolkienmobs.utils.RandomUtility;
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
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;

public class WanderingEntity extends WanderingTrader {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(WanderingEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<VillagerData> DATA_MERCHANT_VARIANT = SynchedEntityData.defineId(WanderingEntity.class, EntityDataSerializers.VILLAGER_DATA);

    public WanderingEntity(EntityType<? extends WanderingEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.50D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(DATA_MERCHANT_VARIANT, new VillagerData(VillagerType.PLAINS, TolkienProfessions.UNEMPLOYED_PROFESSION.get(), 1));
    }

    /** VARIANTS */
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        EntityVariant variant = EntityVariant.byId(RandomUtility.getRandomInteger(16, 0));
        setVariant(variant);
        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    public EntityVariant getVariant() {
        return EntityVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(EntityVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    protected void updateTrades() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }
        this.offers.clear();
        TolkienTrades.getInstance().addToTrader(this, this.offers);
    }

    public void addOffers(MerchantOffers passedOffers, VillagerTrades.ItemListing[] trades, int count) {
        this.addOffersFromItemListings(passedOffers, trades, count);
    }
}