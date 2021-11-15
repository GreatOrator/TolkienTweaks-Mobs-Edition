package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMMimicChest extends EntityTTMMonsters {
    private static final DataParameter<Integer> MIMIC_TYPE = EntityDataManager.defineId(EntityTTMMimicChest.class, DataSerializers.INT);
    private static final DataParameter<Boolean> MIMIC_STATE = EntityDataManager.defineId(EntityTTMMimicChest.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> MIMIC_ATTACK = EntityDataManager.defineId(EntityTTMMimicChest.class, DataSerializers.BOOLEAN);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest4.png"));
        option.put(5, new ResourceLocation(TolkienMobs.MODID, "textures/entity/mimicchest/mimicchest5.png"));
    });
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public EntityTTMMimicChest(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
    }

    /** Special Attack */
    @Override
    public boolean doHurtTarget(Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof PlayerEntity) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    PlayerEntity player = (PlayerEntity) entityIn;
                    int strength = 2;
                    player.addEffect(new EffectInstance(PotionGenerator.INVENTORY_CORROSION.get(), strength * 20, 0));
                }
            }
        }
        return true;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity playerIn, Hand handIn) {
        if (getMimicChest() && !this.level.isClientSide) {
            this.setMimicChest(!this.getMimicChest());
            this.setMimicAttack(!this.isMimicAttack());
            this.setNoAi(false);
            this.isEffectiveAi();
            return ActionResultType.PASS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void tick() {
        super.tick();
        if (!getMimicChest()) {
            setNoAi(false);
        }
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    public void setMimicChest(boolean chestRender) {
        this.entityData.set(MIMIC_STATE, chestRender);
    }

    public boolean getMimicChest() {
        return this.entityData.get(MIMIC_STATE);
    }

    private boolean isMimicAttack() {
        return this.entityData.get(MIMIC_ATTACK);
    }

    public void setMimicAttack(boolean setAttack) {
        this.entityData.set(MIMIC_ATTACK, setAttack);
    }

    public boolean getMimicAttack() {
        return this.entityData.get(MIMIC_ATTACK);
    }


    /**
     * Region for determining random skin
     */
    public ResourceLocation getMimicChestTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getMimicChestType(), TEXTURE_BY_ID.get(1));
    }

    public int getMimicChestType() {
        return this.entityData.get(MIMIC_TYPE);
    }

    public void setMimicChestType(int type) {
        if (type < 0 || type >= 6) {
            type = this.random.nextInt(5);
        }

        this.entityData.set(MIMIC_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(6, 1);
        this.setMimicChestType(job);
        this.setMimicChest(true);
        this.setMimicAttack(false);
        this.setNoAi(true);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MIMIC_TYPE, 1);
        this.entityData.define(MIMIC_STATE, true);
        this.entityData.define(MIMIC_ATTACK, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("MimicChestType", this.getMimicChestType());
        compound.putBoolean("isChest", getMimicChest());
        compound.putBoolean("canAttack", getMimicAttack());

        if (this.isNoAi()) {
            compound.putBoolean("NoAI", this.isNoAi());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setMimicChestType(compound.getInt("MimicChestType"));
        this.setMimicChest(compound.getBoolean("isChest"));
        this.setMimicAttack(compound.getBoolean("canAttack"));
        this.setNoAi(compound.getBoolean("NoAI"));
    }
}