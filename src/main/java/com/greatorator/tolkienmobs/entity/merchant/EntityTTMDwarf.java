package com.greatorator.tolkienmobs.entity.merchant;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.ProfessionGenerator;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMVillagers;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMDwarf extends EntityTTMVillagers {
    private static final DataParameter<Integer> DWARF_TYPE = EntityDataManager.defineId(EntityTTMDwarf.class, DataSerializers.INT);
    private static final DataParameter<VillagerData> DWARF_DATA = EntityDataManager.defineId(EntityTTMDwarf.class, DataSerializers.VILLAGER_DATA);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/dwarf/dwarf1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/dwarf/dwarf1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/dwarf/dwarf2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/dwarf/dwarf3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/dwarf/dwarf4.png"));
    });

    public EntityTTMDwarf(EntityType<? extends EntityTTMVillagers> type, World worldIn) {
        super(type, worldIn);
        this.setRndMinMax(1, 4);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, ZombieEntity.class, true));
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getDwarfTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getDwarfType(), TEXTURE_BY_ID.get(1));
    }

    public int getDwarfType() {
        return this.entityData.get(DWARF_TYPE);
    }

    public void setDwarfType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(DWARF_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setDwarfType(job);

        if (job == 0) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.COIN_TRADER_PROFESSION.get()));
        }
        if (job == 1) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.GROCERY_STORE_PROFESSION.get()));
        }
        if (job == 2) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.JUNK_TRADER_PROFESSION.get()));
        }
        if (job == 3) {
            this.setVillagerData(this.getVillagerData().setProfession(ProfessionGenerator.PET_MERCHANT_PROFESSION.get()));
        }
        if (job == 4) {
            this.setVillagerData(this.getVillagerData().setProfession(VillagerProfession.WEAPONSMITH));
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isSleeping()) {
            return null;
        } else {
            return this.isTrading() ? SoundEvents.VILLAGER_TRADE : SoundGenerator.soundIdleDwarf.get();
        }
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtDwarf.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathDwarf.get();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DWARF_TYPE, 3);
        this.entityData.define(DWARF_DATA, new VillagerData(VillagerType.PLAINS, ProfessionGenerator.UNEMPLOYED_PROFESSION.get(), 1));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("DwarfType", this.getDwarfType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setDwarfType(compound.getInt("DwarfType"));
    }
}