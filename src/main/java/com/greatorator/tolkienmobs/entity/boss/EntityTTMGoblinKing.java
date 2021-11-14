package com.greatorator.tolkienmobs.entity.boss;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import com.greatorator.tolkienmobs.event.TTMEventFactory;
import com.greatorator.tolkienmobs.event.entity.living.TTMGoblinEvent;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMGoblinKing extends EntityTTMGoblin implements IRangedAttackMob {

    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);
    private static final DataParameter<Integer> GOBLINKING_TYPE = EntityDataManager.defineId(EntityTTMGoblinKing.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
    });

    public EntityTTMGoblinKing(EntityType<? extends EntityTTMGoblinKing> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(EntityTTMGoblin.class));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 100.0D);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        SnowballEntity snowballentity = new SnowballEntity(this.level, this);
        double d0 = target.getEyeY() - (double)1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = target.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(snowballentity);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!super.hurt(source, amount)) {
            return false;
        } else if (!(this.level instanceof ServerWorld)) {
            return false;
        } else {
            ServerWorld serverworld = (ServerWorld)this.level;
            LivingEntity livingentity = this.getTarget();
            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
                livingentity = (LivingEntity)source.getEntity();
            }

            int xPos = MathHelper.floor(this.getX());
            int yPos = MathHelper.floor(this.getY());
            int zPos = MathHelper.floor(this.getZ());

            TTMGoblinEvent.SummonAidEvent event = TTMEventFactory.fireGoblinSummonAid(this, level, xPos, yPos, zPos, livingentity, this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());
            if (event.getResult() == Event.Result.DENY) return true;
            if (event.getResult() == Event.Result.ALLOW  ||
                    livingentity != null && this.level.getDifficulty() == Difficulty.HARD && (double)this.random.nextFloat() < this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                EntityTTMGoblin goblinentity = event.getCustomSummonedAid() != null && event.getResult() == Event.Result.ALLOW ? event.getCustomSummonedAid() : EntityGenerator.ENTITY_TTM_GOBLIN.get().create(this.level);

                for(int l = 0; l < 50; ++l) {
                    int spawnX = xPos + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int spawnY = yPos + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int spawnZ = zPos + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    BlockPos blockpos = new BlockPos(spawnX, spawnY, spawnZ);
                    EntityType<?> entitytype = goblinentity.getType();
                    EntitySpawnPlacementRegistry.PlacementType entityspawnplacementregistry$placementtype = EntitySpawnPlacementRegistry.getPlacementType(entitytype);
                    if (WorldEntitySpawner.isSpawnPositionOk(entityspawnplacementregistry$placementtype, this.level, blockpos, entitytype) && EntitySpawnPlacementRegistry.checkSpawnRules(entitytype, serverworld, SpawnReason.REINFORCEMENT, blockpos, this.level.random)) {
                        goblinentity.setPos((double)spawnX, (double)spawnY, (double)spawnZ);
                        if (!this.level.hasNearbyAlivePlayer((double)spawnX, (double)spawnY, (double)spawnZ, 7.0D) && this.level.isUnobstructed(goblinentity) && this.level.noCollision(goblinentity) && !this.level.containsAnyLiquid(goblinentity.getBoundingBox())) {
                            if (livingentity != null)
                                goblinentity.setTarget(livingentity);
                            goblinentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(goblinentity.blockPosition()), SpawnReason.REINFORCEMENT, (ILivingEntityData)null, (CompoundNBT)null);
                            serverworld.addFreshEntityWithPassengers(goblinentity);
                            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Goblin reinforcement caller charge", (double)-0.05F, AttributeModifier.Operation.ADDITION));
                            goblinentity.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Goblin reinforcement callee charge", (double)-0.05F, AttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleGoblin.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtGoblin.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathGoblin.get();
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getGoblinKingTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getGoblinKingType(), TEXTURE_BY_ID.get(0));
    }

    public int getGoblinKingType() {
        return this.entityData.get(GOBLINKING_TYPE);
    }

    public void setGoblinKingType(int type) {
        if (type < 0 || type >= 3) {
            type = this.random.nextInt(2);
        }

        this.entityData.set(GOBLINKING_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 1);
        this.setGoblinKingType(job);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GOBLINKING_TYPE, 1);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("GoblinKingType", this.getGoblinKingType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setGoblinKingType(compound.getInt("GoblinKingType"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    protected void customServerAiStep() {
        if (this.tickCount % 20 == 0) {
            this.heal(1.0F);
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}