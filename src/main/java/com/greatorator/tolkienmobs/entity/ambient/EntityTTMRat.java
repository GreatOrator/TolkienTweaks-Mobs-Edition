package com.greatorator.tolkienmobs.entity.ambient;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMAmbients;
import net.minecraft.entity.*;
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

public class EntityTTMRat extends EntityTTMAmbients {
    private static final DataParameter<Integer> RAT_TYPE = EntityDataManager.createKey(EntityTTMRat.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat0.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/entityttmrat/entityttmrat3.png"));
    });

    public EntityTTMRat(EntityType<? extends EntityTTMRat> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new EntityTTMRat.AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.3F));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, PlayerEntity.class, 2.0F, 0.8F, 1.4F));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    protected SoundEvent getAmbientSound() {
        return SoundGenerator.soundIdleTMRat.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundGenerator.soundHurtTMRat.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundGenerator.soundDeathTMRat.get();
    }

    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final EntityTTMRat ttmRat;

        public AvoidEntityGoal(EntityTTMRat ttmRat, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
            super(ttmRat, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.ttmRat = ttmRat;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.ttmRat.getRatType() != 99 && super.shouldExecute();
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return null;
    }

    /** Region for determining random skin */
    public ResourceLocation getRatTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getRatType(), TEXTURE_BY_ID.get(0));
    }

    public int getRatType() {
        return this.dataManager.get(RAT_TYPE);
    }

    public void setRatType(int type) {
        if (type < 0 || type >= 4) {
            type = this.rand.nextInt(3);
        }

        this.dataManager.set(RAT_TYPE, type);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(RAT_TYPE, 1);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("RatType", this.getRatType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setRatType(compound.getInt("RatType"));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}