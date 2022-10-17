package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMSwitchCombat;
import com.greatorator.tolkienmobs.entity.ai.goal.TTMThrowandAttack;
import com.greatorator.tolkienmobs.entity.item.EntityFellBeastFireball;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BalrogEntity extends MonsterEntity {
    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(true);
    private static final DataParameter<Byte> DATA_FLAGS_ID;
    private final RangedBowAttackGoal<BalrogEntity> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal = new BalrogEntity.BalrogAttackGoal(this, 1.0D, 20, 15.0F, false);
    private boolean scheduleWeaponGoalUpdate = true;
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public BalrogEntity(EntityType<? extends net.minecraft.entity.monster.MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new TTMSwitchCombat(this, 6.0D, 6.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, net.minecraft.entity.monster.MonsterEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    /** Set up using weapons **/
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        super.populateDefaultEquipmentSlots(p_180481_1_);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(TTMContent.WHIP_FIRE.get()));
    }

    @Override
    public boolean canFireProjectileWeapon(ShootableItem p_230280_1_) {
        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        EntityFellBeastFireball fireballentity = new EntityFellBeastFireball(this.level, this);
        double d0 = p_82196_1_.getEyeY() - (double)1.1F;
        double d1 = p_82196_1_.getX() - this.getX();
        double d2 = d0 - fireballentity.getY();
        double d3 = p_82196_1_.getZ() - this.getZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        fireballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.FIRECHARGE_USE, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(fireballentity);
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    static class BalrogAttackGoal extends TTMThrowandAttack {
        private final BalrogEntity balrog;
        private int raiseArmTicks;
        private int attackStep;
        private int attackTime;

        BalrogAttackGoal(BalrogEntity balrogEntity, double speedAmplifier, int attackInterval, float maxDistance, boolean useLongMemory) {
            super(balrogEntity, speedAmplifier, attackInterval, maxDistance, useLongMemory);
            this.balrog = balrogEntity;
        }

        @Override
        public void start() {
            super.start();
            this.raiseArmTicks = 0;
        }

        @Override
        public void stop() {
            super.stop();
            this.balrog.setCharged(false);
            this.balrog.setAggressive(false);
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity entity = this.balrog.getTarget();
            double following = this.balrog.distanceToSqr(entity);
            boolean entitySpotted = this.balrog.getSensing().canSee(entity);
            ++this.raiseArmTicks;
            if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
                this.balrog.setAggressive(true);
            } else {
                this.balrog.setAggressive(false);
            }

            if (following < this.getFollowDistance() * this.getFollowDistance() && entitySpotted) {
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.balrog.setCharged(true);
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                        this.balrog.setCharged(false);
                    }
                }
            }
        }
        private double getFollowDistance() {
            return this.balrog.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }

    /** End Region **/

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return net.minecraft.entity.monster.MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.40D)
                .add(Attributes.ATTACK_DAMAGE, 17.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 24.0D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!super.hurt(source, amount)) {
            return false;
        } else if (!(this.level instanceof ServerWorld)) {
            return false;
        } else {
            LivingEntity livingentity = this.getTarget();
            if (livingentity == null && source.getEntity() instanceof LivingEntity) {
                livingentity = (LivingEntity) source.getEntity();
            }

            if (livingentity == null) return true;

            if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.nodrown.balrog").withStyle(TextFormatting.DARK_BLUE), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.WATER_BREATHING, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.onfire.balrog").withStyle(TextFormatting.DARK_RED), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.healself.balrog").withStyle(TextFormatting.LIGHT_PURPLE), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.REGENERATION, 2 * 20, 0));
            }

            if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                livingentity.sendMessage(new TranslationTextComponent(MODID + ".msg.speedup.balrog").withStyle(TextFormatting.AQUA), Util.NIL_UUID);
                this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 2 * 20, 0));
            }
        }
        return true;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
                return;
            }

            BlockPos pos = this.blockPosition();
            BlockState blockstate = Blocks.MAGMA_BLOCK.defaultBlockState();
            float f = (float)Math.min(16, 2);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((double)(-f), -1.0D, (double)(-f)), pos.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerThan(this.position(), (double)f)) {
                    blockpos$mutable.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = level.getBlockState(blockpos$mutable);
                    if (blockstate1.isAir(level, blockpos$mutable)) {
                        BlockState blockstate2 = level.getBlockState(blockpos);
                        if (blockstate2.getBlock() == Blocks.GRASS_BLOCK || blockstate2.getBlock() == Blocks.DIRT && level.isUnobstructed(blockstate, blockpos, ISelectionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(this, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos), Direction.DOWN)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.getBlockTicks().scheduleTick(blockpos, Blocks.MAGMA_BLOCK, MathHelper.nextInt(this.getRandom(), 60, 120));
                        }
                    }
                }
            }

        }

        if (this.level.isClientSide) {
            if (this.random.nextInt(24) == 0 && !this.isSilent()) {
                this.level.playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }
            for(int lvt_1_1_ = 0; lvt_1_1_ < 2; ++lvt_1_1_) {
                this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleBalrog.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtBalrog.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathBalrog.get();
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundGenerator.soundStepBalrog.get(), 0.25F, 1.0F);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.populateDefaultEquipmentSlots(difficultyIn);
        reassessWeaponGoal();
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
        reassessWeaponGoal();
    }

    @Override
    public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
        super.setItemSlot(p_184201_1_, p_184201_2_);
        reassessWeaponGoal();
    }

    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            scheduleWeaponGoalUpdate = true;
        }
    }

    @Override
    public void tick() {
        if (scheduleWeaponGoalUpdate) {
            updateWeaponGoal();
        }
        super.tick();
    }

    protected void updateWeaponGoal() {
        scheduleWeaponGoalUpdate = false;
        this.goalSelector.removeGoal(this.meleeGoal);
        this.goalSelector.removeGoal(this.bowGoal);
        ItemStack itemstack = this.getMainHandItem();
        if (itemstack.getItem() == Items.BOW) {
            int i = 20;
            if (this.level.getDifficulty() != Difficulty.HARD) {
                i = 40;
            }

            this.bowGoal.setMinAttackInterval(i);
            this.goalSelector.addGoal(4, this.bowGoal);
        } else {
            this.goalSelector.addGoal(4, this.meleeGoal);
        }
    }

    public boolean isOnFire() {
        return this.isCharged();
    }

    private boolean isCharged() {
        return ((Byte)this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    private void setCharged(boolean p_70844_1_) {
        byte lvt_2_1_ = (Byte)this.entityData.get(DATA_FLAGS_ID);
        if (p_70844_1_) {
            lvt_2_1_ = (byte)(lvt_2_1_ | 1);
        } else {
            lvt_2_1_ &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, lvt_2_1_);
    }

    static {
        DATA_FLAGS_ID = EntityDataManager.defineId(BalrogEntity.class, DataSerializers.BYTE);
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