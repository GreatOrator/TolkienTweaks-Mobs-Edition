package com.greatorator.tolkienmobs.entity.merchant;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMVillager;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerData;
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

public class EntityTTMHobbit extends EntityTTMVillager {

private static final DataParameter<Integer> HOBBIT_TYPE = EntityDataManager.defineId(EntityTTMHobbit.class, DataSerializers.INT);
private static final DataParameter<VillagerData> HOBBIT_DATA = EntityDataManager.defineId(EntityTTMHobbit.class, DataSerializers.VILLAGER_DATA);
public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit4.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit5.png"));
        option.put(6, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit6.png"));
        option.put(7, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit7.png"));
        option.put(8, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit8.png"));
        option.put(9, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit9.png"));
        option.put(10, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit10.png"));
        option.put(11, new ResourceLocation(TolkienMobs.MODID, "textures/entity/hobbit/hobbit11.png"));
        });

public EntityTTMHobbit(EntityType<? extends EntityTTMVillager> type, World worldIn) {
        super(type, worldIn);
        this.setRndMinMax(1,11);
        }

/** Region for determining random skin */
public ResourceLocation getHobbitTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getHobbitType(), TEXTURE_BY_ID.get(0));
        }

public int getHobbitType() {
        return this.entityData.get(HOBBIT_TYPE);
        }

public void setHobbitType(int type) {
        if (type < 0 || type >= 12) {
        type = this.random.nextInt(11);
        }

        this.entityData.set(HOBBIT_TYPE, type);
        }

@Nullable
public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 11);
        this.setHobbitType(job);

        if (job == 0 || job == 4 || job == 8) {
        this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.COIN_TRADER_PROFESSION.get()));
        }
        if (job == 1 || job == 5 || job == 9) {
        this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.GROCERY_STORE_PROFESSION.get()));
        }
        if (job == 2 || job == 6 || job == 10) {
        this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.JUNK_TRADER_PROFESSION.get()));
        }
        if (job == 3 || job == 7 || job == 11) {
        this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.PET_MERCHANT_PROFESSION.get()));
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        }

protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOBBIT_TYPE, 10);
        this.entityData.define(HOBBIT_DATA, new VillagerData(VillagerType.PLAINS, ProfessionGenerator.UNEMPLOYED_PROFESSION.get(), 1));
        }

public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HobbitType", this.getHobbitType());
        }

public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setHobbitType(compound.getInt("HobbitType"));
        }
}