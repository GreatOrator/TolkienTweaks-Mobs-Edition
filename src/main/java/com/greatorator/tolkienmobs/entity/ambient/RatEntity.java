package com.greatorator.tolkienmobs.entity.ambient;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.AmbientEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class RatEntity extends AmbientEntity {
    private static final DataParameter<Integer> RAT_TYPE = EntityDataManager.defineId(RatEntity.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat0.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat3.png"));
    });

    public RatEntity(EntityType<? extends RatEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RatEntity.AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.3F));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, PlayerEntity.class, 2.0F, 0.8F, 1.4F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundGenerator.soundIdleTMRat.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtTMRat.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathTMRat.get();
    }

    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final RatEntity ttmRat;

        public AvoidEntityGoal(RatEntity ttmRat, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
            super(ttmRat, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.ttmRat = ttmRat;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        @Override
        public boolean canUse() {
            return this.ttmRat.getRatType() != 99 && super.canUse();
        }
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    /** Region for determining random skin */
    public ResourceLocation getRatTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getRatType(), TEXTURE_BY_ID.get(0));
    }

    public int getRatType() {
        return this.entityData.get(RAT_TYPE);
    }

    public void setRatType(int type) {
        if (type < 0 || type >= 4) {
            type = this.random.nextInt(3);
        }

        this.entityData.set(RAT_TYPE, type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RAT_TYPE, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("RatType", this.getRatType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setRatType(compound.getInt("RatType"));
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}