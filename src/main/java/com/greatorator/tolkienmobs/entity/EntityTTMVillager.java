package com.greatorator.tolkienmobs.entity;

import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.world.World;

public class EntityTTMVillager extends VillagerEntity {
    private int texture_index;
    private int rndMax;
    private int rndMin;

    public EntityTTMVillager(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
        this.texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
    }

    public EntityTTMVillager(EntityType<? extends VillagerEntity> type, World worldIn, VillagerType villagerType) {
        super(type, worldIn, villagerType);
        this.texture_index = TTMRand.getRandomInteger(rndMax, rndMin);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    public void setRndMinMax(int rndMin, int rndMax) {
        this.rndMin = rndMin;
        this.rndMax = rndMax;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D).createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
    }
}