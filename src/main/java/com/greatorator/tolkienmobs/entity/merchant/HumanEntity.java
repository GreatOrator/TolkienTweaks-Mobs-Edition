package com.greatorator.tolkienmobs.entity.merchant;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.VillagerEntity;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class HumanEntity extends VillagerEntity {
    private static final DataParameter<Integer> HUMAN_TYPE = EntityDataManager.defineId(HumanEntity.class, DataSerializers.INT);
    private static final DataParameter<VillagerData> HUMAN_DATA = EntityDataManager.defineId(HumanEntity.class, DataSerializers.VILLAGER_DATA);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human4.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human5.png"));
        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human6.png"));
        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human7.png"));
        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human8.png"));
        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human9.png"));
        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human10.png"));
        option.put(11, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human11.png"));
        option.put(12, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human12.png"));
        option.put(13, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human13.png"));
        option.put(14, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human14.png"));
        option.put(15, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human15.png"));
        option.put(16, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human16.png"));
    });

    public HumanEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
        this.setRndMinMax(1,16);
    }

    /** Region for determining random skin */
    public ResourceLocation getHumanTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getHumanType(), TEXTURE_BY_ID.get(1));
    }

    public int getHumanType() {
        return this.entityData.get(HUMAN_TYPE);
    }

    public void setHumanType(int type) {
        if (type < 0 || type >= 17) {
            type = this.random.nextInt(16);
        }

        this.entityData.set(HUMAN_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 16);
        this.setHumanType(job);

        if (job == 1 || job == 11) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.COIN_TRADER_PROFESSION.get()));
        }
        if (job == 2 || job == 12) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.GROCERY_STORE_PROFESSION.get()));
        }
        if (job == 3 || job == 13) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.JUNK_TRADER_PROFESSION.get()));
        }
        if (job == 4 || job == 14) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.PET_MERCHANT_PROFESSION.get()));
        }
        if (job == 5 || job == 15) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.TRINKET_TAILOR_PROFESSION.get()));
        }
        if (job == 6 || job == 16) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.TRINKET_SMITH_PROFESSION.get()));
        }
        if (job == 7) {
            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FARMER));
        }
        if (job == 8) {
            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.FISHERMAN));
        }
        if (job == 9) {
            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.MASON));
        }
        if (job == 10) {
            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.NONE));
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HUMAN_TYPE, 16);
        this.entityData.define(HUMAN_DATA, new VillagerData(VillagerType.PLAINS, ProfessionGenerator.UNEMPLOYED_PROFESSION.get(), 1));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HumanType", this.getHumanType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setHumanType(compound.getInt("HumanType"));
    }
}