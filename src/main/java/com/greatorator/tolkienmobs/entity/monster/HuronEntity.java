package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.boss.GoblinKingEntity;
import com.greatorator.tolkienmobs.entity.item.EntityBoulder;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class HuronEntity extends MonsterEntity {
    private static final DataParameter<Integer> HURON_TYPE = EntityDataManager.defineId(HuronEntity.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/huron.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/huron.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/huron.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/huron.png"));
    });

    public HuronEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.ARMOR, 6.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GoblinEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GoblinKingEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, UrukHaiEntity.class, true));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        EntityBoulder entityboulder = new EntityBoulder(this.level, this);
        if (!this.isSilent()) {
            this.level.levelEvent((PlayerEntity)null, 1024, this.blockPosition(), 0);
        }

        double d0 = target.position().y + (double)target.getEyeHeight() - 1.100000023841858D;
        double d1 = target.position().x - this.position().x;
        double d2 = d0 - entityboulder.position().y;
        double d3 = target.position().z - this.position().z;
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        entityboulder.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        entityboulder.setOwner(this);

        entityboulder.setPosRaw(d0, d1, d2);
        this.playSound(SoundGenerator.sound_Boulder_Shoot.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(entityboulder);
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getHuronTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getHuronType(), TEXTURE_BY_ID.get(1));
    }

    public int getHuronType() {
        return this.entityData.get(HURON_TYPE);
    }

    public void setHuronType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(HURON_TYPE, type);
    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setHuronType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HURON_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HuronType", this.getHuronType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setHuronType(compound.getInt("HuronType"));
    }
}