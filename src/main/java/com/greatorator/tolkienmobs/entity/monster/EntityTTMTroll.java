package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMTroll extends EntityTTMMonsters {
    private static final DataParameter<Integer> TROLL_TYPE = EntityDataManager.defineId(EntityTTMTroll.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/troll/cave_troll4.png"));
    });

    public EntityTTMTroll(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 11.0D)
                .add(Attributes.ARMOR, 9.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleTroll.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource parDamageSource)
    {
        return SoundGenerator.soundHurtTroll.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathTroll.get();
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundGenerator.soundStepTroll.get(), 0.25F, 1.0F);
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getTrollTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getTrollType(), TEXTURE_BY_ID.get(1));
    }

    public int getTrollType() {
        return this.entityData.get(TROLL_TYPE);
    }

    public void setTrollType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(TROLL_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setTrollType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TROLL_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("BTrollType", this.getTrollType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setTrollType(compound.getInt("TrollType"));
    }
}