package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.EntityTTMMonsters;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.boss.EntityTTMGoblinKing;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class EntityTTMTreeEnt extends EntityTTMMonsters {
    private static final DataParameter<Integer> TREEENT_TYPE = EntityDataManager.defineId(EntityTTMTreeEnt.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/treeent/treeent4.png"));
    });

    public EntityTTMTreeEnt(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 45.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ARMOR, 9.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityTTMGoblin.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityTTMMordorOrc.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityTTMGoblinKing.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityTTMUrukHai.class, true));
    }

    public static boolean checkTreeEntSpawn(EntityType<EntityTTMTreeEnt> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        int chance = 200; //1 in x
        return random.nextInt(chance) == 0 && checkMobSpawnRules(type, world, reason, pos, random);
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
    public ResourceLocation getTreeEntTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getTreeEntType(), TEXTURE_BY_ID.get(0));
    }

    public int getTreeEntType() {
        return this.entityData.get(TREEENT_TYPE);
    }

    public void setTreeEntType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(TREEENT_TYPE, type);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setTreeEntType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TREEENT_TYPE, 3);
    }

    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("TreeEntType", this.getTreeEntType());
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setTreeEntType(compound.getInt("TreeEntType"));
    }
}