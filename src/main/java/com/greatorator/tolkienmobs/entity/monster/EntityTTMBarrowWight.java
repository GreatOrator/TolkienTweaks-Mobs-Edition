package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
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

public class EntityTTMBarrowWight extends MonsterEntity {
    private static final DataParameter<Integer> BARROW_TYPE = EntityDataManager.createKey(EntityTTMBarrowWight.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/barrowwight/barrowwight4.png"));
    });

    public EntityTTMBarrowWight(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 16.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
                .createMutableAttribute(Attributes.ARMOR, 5.0D);
    }

    public void livingTick() {
        if (this.isAlive()) {
            boolean flag = this.shouldBurnInDay() && this.isInDaylight();
            if (flag) {
                ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageable()) {
                        itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
                        if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
                            this.sendBreakAnimation(EquipmentSlotType.HEAD);
                            this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setFire(8);
                }
            }
        }

        super.livingTick();
    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    public CreatureAttribute getCreatureAttribute()
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
        return TEXTURE_BY_ID.getOrDefault(this.getBarrowType(), TEXTURE_BY_ID.get(0));
    }

    public int getBarrowType() {
        return this.dataManager.get(BARROW_TYPE);
    }

    public void setBarrowType(int type) {
        if (type < 0 || type >= 5) {
            type = this.rand.nextInt(4);
        }

        this.dataManager.set(BARROW_TYPE, type);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 4);
        this.setBarrowType(job);
        this.setEquipmentBasedOnDifficulty(difficultyIn);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(BARROW_TYPE, 3);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("BarrowType", this.getBarrowType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setBarrowType(compound.getInt("BarrowType"));
    }
}