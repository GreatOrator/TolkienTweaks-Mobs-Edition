package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.event.entity.SpiderEvent;
import com.greatorator.tolkienmobs.event.server.ServerEvents;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ShelobEntity extends MonsterEntity {
    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(true);
    private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(ShelobEntity.class, DataSerializers.BYTE);
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public ShelobEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new ShelobEntity.AttackGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new ShelobEntity.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(3, new ShelobEntity.TargetGoal<>(this, IronGolemEntity.class));
    }

    @Override
    public double getPassengersRidingOffset() {
        return (double)(this.getBbHeight() * 0.5F);
    }

    @Override
    protected PathNavigator createNavigation(World p_175447_1_) {
        return new ClimberPathNavigator(this, p_175447_1_);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }

    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 240.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ARMOR, 20.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SPIDER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.SPIDER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SPIDER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    public void makeStuckInBlock(BlockState p_213295_1_, Vector3d p_213295_2_) {
        if (!p_213295_1_.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(p_213295_1_, p_213295_2_);
        }

    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean canBeAffected(EffectInstance p_70687_1_) {
        if (p_70687_1_.getEffect() == Effects.POISON) {
            net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, p_70687_1_);
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
            return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
        }
        return super.canBeAffected(p_70687_1_);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    public void setClimbing(boolean p_70839_1_) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if (p_70839_1_) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.65F;
    }

    static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(ShelobEntity p_i46676_1_) {
            super(p_i46676_1_, 1.0D, true);
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        @Override
        public boolean canContinueToUse() {
            float f = this.mob.getBrightness();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }

        @Override
        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(4.0F + p_179512_1_.getBbWidth());
        }
    }

    public static class GroupData implements ILivingEntityData {
        public Effect effect;

        public void setRandomEffect(Random p_111104_1_) {
            int i = p_111104_1_.nextInt(5);
            if (i <= 1) {
                this.effect = Effects.MOVEMENT_SPEED;
            } else if (i <= 2) {
                this.effect = Effects.DAMAGE_BOOST;
            } else if (i <= 3) {
                this.effect = Effects.REGENERATION;
            } else if (i <= 4) {
                this.effect = Effects.INVISIBILITY;
            }

        }
    }

    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(ShelobEntity p_i45818_1_, Class<T> p_i45818_2_) {
            super(p_i45818_1_, p_i45818_2_, true);
        }

        @Override
        public boolean canUse() {
            float f = this.mob.getBrightness();
            return !(f >= 0.5F) && super.canUse();
        }
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
                livingentity = (LivingEntity) source.getEntity();
            }

            if (livingentity == null) return true;

            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nodrown.shelob").withStyle(TextFormatting.DARK_BLUE), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.WATER_BREATHING, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.onfire.shelob").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself.shelob").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup.shelob").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
            }

            int xPos = MathHelper.floor(this.getX());
            int yPos = MathHelper.floor(this.getY());
            int zPos = MathHelper.floor(this.getZ());

            SpiderEvent.SummonAidEvent event = ServerEvents.fireSpiderSummonAid(this, level, xPos, yPos, zPos, livingentity, this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue());

            if (event.getResult() == Event.Result.DENY) {

                if (livingentity instanceof PlayerEntity) {
                    livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nohelp.shelob").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
                }
                return true;
            }
            if (event.getResult() == Event.Result.ALLOW ||
                    livingentity != null && (double) this.random.nextFloat() < this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).getValue() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {

                if (livingentity instanceof PlayerEntity) {
                    livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.helpcomming.shelob").withStyle(TextFormatting.DARK_GREEN), Util.NIL_UUID);
                }

                CaveSpiderEntity spiderentity = event.getCustomSummonedAid() != null && event.getResult() == Event.Result.ALLOW ? event.getCustomSummonedAid() : EntityType.CAVE_SPIDER.create(this.level);

                for (int l = 0; l < 50; ++l) {
                    int spawnX = xPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                    int spawnY = yPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                    int spawnZ = zPos + MathHelper.nextInt(this.random, 7, 10) * MathHelper.nextInt(this.random, -1, 1);
                    BlockPos blockpos = new BlockPos(spawnX, spawnY, spawnZ);
                    EntityType<?> entitytype = spiderentity.getType();
                    EntitySpawnPlacementRegistry.PlacementType entityspawnplacementregistry$placementtype = EntitySpawnPlacementRegistry.getPlacementType(entitytype);
                    if (WorldEntitySpawner.isSpawnPositionOk(entityspawnplacementregistry$placementtype, this.level, blockpos, entitytype) && EntitySpawnPlacementRegistry.checkSpawnRules(entitytype, serverworld, SpawnReason.REINFORCEMENT, blockpos, this.level.random)) {
                        spiderentity.setPos((double) spawnX, (double) spawnY, (double) spawnZ);
                        if (!this.level.hasNearbyAlivePlayer((double) spawnX, (double) spawnY, (double) spawnZ, 7.0D) && this.level.isUnobstructed(spiderentity) && this.level.noCollision(spiderentity) && !this.level.containsAnyLiquid(spiderentity.getBoundingBox())) {
                            if (livingentity != null)
                                spiderentity.setTarget(livingentity);
                            spiderentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(spiderentity.blockPosition()), SpawnReason.REINFORCEMENT, (ILivingEntityData) null, (CompoundNBT) null);
                            serverworld.addFreshEntityWithPassengers(spiderentity);
                            this.getAttribute(Attributes.SPAWN_REINFORCEMENTS_CHANCE).addPermanentModifier(new AttributeModifier("Spider reinforcement caller charge", (double) -0.05F, AttributeModifier.Operation.ADDITION));
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    private float getAttackDamage() {
        return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.level.broadcastEntityEvent(this, (byte) 4);
        float f = this.getAttackDamage();
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);
        if (flag) {
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, 0.4F, 0.0D));
            this.doEnchantDamageEffects(this, entityIn);
            long time = System.currentTimeMillis();
            if (super.doHurtTarget(entityIn)) {
                if (entityIn instanceof PlayerEntity) {
                    if (time > nextAbilityUse) {
                        nextAbilityUse = time + coolDown;
                        PlayerEntity player = (PlayerEntity) entityIn;
                        int strength = 2;
                        if (TTMRand.getRandom(10) <= 3) {
                            player.addEffect(new EffectInstance(PotionGenerator.WATCHER_FEAR.get(), strength * 20, 0));
                        } else {
                            player.addEffect(new EffectInstance(PotionGenerator.SLEEPNESIA.get(), strength * 20, 0));
                        }
                    }
                }
            }
        }

        this.playSound(SoundEvents.SPIDER_HURT, 1.0F, 1.0F);
        return flag;
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.populateDefaultEquipmentSlots(difficultyIn);
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (worldIn.getRandom().nextInt(100) == 0) {
            SkeletonEntity skeletonentity = EntityType.SKELETON.create(this.level);
            skeletonentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
            skeletonentity.finalizeSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT)null);
            skeletonentity.startRiding(this);
        }

        if (spawnDataIn == null) {
            spawnDataIn = new ShelobEntity.GroupData();
            if (worldIn.getDifficulty() == Difficulty.HARD && worldIn.getRandom().nextFloat() < 0.1F * difficultyIn.getSpecialMultiplier()) {
                ((ShelobEntity.GroupData)spawnDataIn).setRandomEffect(worldIn.getRandom());
            }
        }

        if (spawnDataIn instanceof ShelobEntity.GroupData) {
            Effect effect = ((ShelobEntity.GroupData)spawnDataIn).effect;
            if (effect != null) {
                this.addEffect(new EffectInstance(effect, Integer.MAX_VALUE));
            }
        }

        return spawnDataIn;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
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
}