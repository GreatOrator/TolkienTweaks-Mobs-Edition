package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
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

public class EntityTTMDeepClaw extends EntityTTMMonsters {
    private static final DataParameter<Integer> DEEPCLAW_TYPE = EntityDataManager.defineId(EntityTTMDeepClaw.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmdeepclaw/tmdeepclaw4.png"));
    });

    public EntityTTMDeepClaw(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.ARMOR, 10.0D);
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getDeepclawTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getDeepclawType(), TEXTURE_BY_ID.get(0));
    }

    public int getDeepclawType() {
        return this.entityData.get(DEEPCLAW_TYPE);
    }

    public void setDeepclawType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(DEEPCLAW_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 10);
        this.setDeepclawType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DEEPCLAW_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("DeepclawType", this.getDeepclawType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setDeepclawType(compound.getInt("DeepclawType"));
    }
}
