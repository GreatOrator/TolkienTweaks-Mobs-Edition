package com.greatorator.tolkienmobs.entity.merchant;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMVillager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.World;

import java.util.Map;

public class EntityTTMHuman extends EntityTTMVillager {
    private static final DataParameter<Integer> HUMAN_TYPE = EntityDataManager.createKey(EntityTTMHuman.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/human/human1.png"));
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

    public EntityTTMHuman(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
        this.setRndMinMax(1,16);
    }

    public EntityTTMHuman(EntityType<? extends VillagerEntity> type, World worldIn, VillagerType villagerType) {
        super(type, worldIn, villagerType);
    }

    /** Region for determining random skin */
    public ResourceLocation getHumanTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getHumanType(), TEXTURE_BY_ID.get(0));
    }

    public int getHumanType() {
        return this.dataManager.get(HUMAN_TYPE);
    }

    public void setHumanType(int type) {
        if (type < 0 || type >= 17) {
            type = this.rand.nextInt(16);
        }

        this.dataManager.set(HUMAN_TYPE, type);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(HUMAN_TYPE, 1);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("HumanType", this.getHumanType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setHumanType(compound.getInt("HumanType"));
    }
}