package com.greatorator.tolkienmobs.entity.passive;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMHerds;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMAuroch extends EntityTTMHerds {
    private static final DataParameter<Integer> AUROCH_TYPE = EntityDataManager.defineId(EntityTTMAuroch.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch2.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch3.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/auroch/auroch4.png"));
    });

    public EntityTTMAuroch(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.COW_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public EntityTTMAuroch getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityGenerator.ENTITY_TTM_AUROCH.get().create(p_241840_1_);
    }

    /** Region for determining random skin */
    public ResourceLocation getAurochTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getAurochType(), TEXTURE_BY_ID.get(0));
    }

    public int getAurochType() {
        return this.entityData.get(AUROCH_TYPE);
    }

    public void setAurochType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(AUROCH_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setAurochType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AUROCH_TYPE, 1);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("AurochType", this.getAurochType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setAurochType(compound.getInt("AurochType"));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}