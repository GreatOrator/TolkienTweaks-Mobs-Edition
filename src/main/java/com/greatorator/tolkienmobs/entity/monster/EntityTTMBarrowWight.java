package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMBarrowWight extends EntityTTMMonsters {
    private static final DataParameter<Integer> BARROW_TYPE = EntityDataManager.defineId(EntityTTMBarrowWight.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight4.png"));
    });

    public EntityTTMBarrowWight(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    public void aiStep() {
        if (this.isAlive()) {
            boolean flag = this.shouldBurnInDay() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlotType.HEAD);
                            this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }

        super.aiStep();
    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    @Override
    public CreatureAttribute getMobType()
    {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleBarrowWight.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtBarrowWight.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundHurtBarrowWight.get();
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getBarrowTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getBarrowType(), TEXTURE_BY_ID.get(1)); //There was no id '0' which was causing a null pointer
    }

    public int getBarrowType() {
        return this.entityData.get(BARROW_TYPE);
    }

    public void setBarrowType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(BARROW_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setBarrowType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BARROW_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("BarrowType", this.getBarrowType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setBarrowType(compound.getInt("BarrowType"));
    }
}