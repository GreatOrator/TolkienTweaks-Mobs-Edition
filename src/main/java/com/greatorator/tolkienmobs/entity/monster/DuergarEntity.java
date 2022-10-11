package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.projectile.ProjectileHelper;
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

public class DuergarEntity extends MonsterEntity {
    private static final DataParameter<Integer> DUERGAR_TYPE = EntityDataManager.defineId(DuergarEntity.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmduergar/tmduergar1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmduergar/tmduergar2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmduergar/tmduergar3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/tmduergar/tmduergar4.png"));
    });

    /** Set up using weapons **/
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        @Override
        public void stop() {
            super.stop();
            DuergarEntity.this.setAggressive(false);
        }

        @Override
        public void start() {
            super.start();
            DuergarEntity.this.setAggressive(true);
        }
    };
    /** End Region **/

    public DuergarEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.ARMOR, 6.0D);
    }

    /** Set up using weapons **/
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(TTMContent.AXE_MORGULIRON.get()));
    }

    @Override
    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, TTMContent.AXE_MORGULIRON.get()));
            if (itemstack.getItem() == TTMContent.AXE_MORGULIRON.get()) {
                this.goalSelector.addGoal(4, this.meleeGoal);
            }
        }
    }

    @Override
    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
        super.setItemSlot(p_184201_1_, p_184201_2_);
        if (!this.level.isClientSide) {
            this.reassessWeaponGoal();
        }

    }
    /** End Region **/

    @Override
    public CreatureAttribute getMobType()
    {
        return CreatureAttribute.ILLAGER;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleDwarf.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtDwarf.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathDwarf.get();
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getDuergarTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getDuergarType(), TEXTURE_BY_ID.get(1));
    }

    public int getDuergarType() {
        return this.entityData.get(DUERGAR_TYPE);
    }

    public void setDuergarType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(DUERGAR_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setDuergarType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DUERGAR_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("DuergarType", this.getDuergarType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setDuergarType(compound.getInt("DuergarType"));
    }
}