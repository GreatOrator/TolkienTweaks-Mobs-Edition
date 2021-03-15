package com.greatorator.tolkienmobs.entity.passive;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.SoundType;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMGoat extends AbstractChestedHorseEntity {
    private static final DataParameter<Boolean> DATA_ID_CHEST = EntityDataManager.createKey(AbstractChestedHorseEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> GOAT_TYPE = EntityDataManager.createKey(EntityTTMGoat.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat1.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat2.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat3.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goat/goat4.png"));
    });

    public EntityTTMGoat(EntityType<? extends AbstractChestedHorseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.HORSE_JUMP_STRENGTH, 2.5D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.ARMOR, 4.0D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.8D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new RunAroundLikeCrazyGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D, EntityTTMGoat.class));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.initExtraAI();
    }

    @Override
    public boolean hasChest() {
        return this.dataManager.get(DATA_ID_CHEST);
    }

    @Override
    public double getMountedYOffset()
    {
        return super.getMountedYOffset() * 0.58F;
    }

    @Override
    protected void playGallopSound(SoundType p_190680_1_)
    {
        super.playGallopSound(p_190680_1_);

        if (this.rand.nextInt(10) == 0)
        {
            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundGenerator.soundIdleGoat.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundGenerator.soundDeathGoat.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return SoundGenerator.soundHurtGoat.get();
    }

    @Override
    protected SoundEvent getAngrySound()
    {
        super.getAngrySound();
        return SoundGenerator.soundAngryGoat.get();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 4;
    }

    protected boolean isValidLightLevel()
    {
        return true;
    }

    public EntityTTMGoat func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityGenerator.ENTITY_TTM_GOAT.get().create(p_241840_1_);
    }

    /** Region for determining random skin */
    public ResourceLocation getGoatTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getGoatType(), TEXTURE_BY_ID.get(0));
    }

    public int getGoatType() {
        return this.dataManager.get(GOAT_TYPE);
    }

    public void setGoatType(int type) {
        if (type < 0 || type >= 5) {
            type = this.rand.nextInt(4);
        }

        this.dataManager.set(GOAT_TYPE, type);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setGoatType(job);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GOAT_TYPE, 1);
        this.dataManager.register(DATA_ID_CHEST, false);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("GoatType", this.getGoatType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setGoatType(compound.getInt("GoatType"));
    }
}