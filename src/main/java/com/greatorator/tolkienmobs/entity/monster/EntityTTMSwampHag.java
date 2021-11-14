package com.greatorator.tolkienmobs.entity.monster;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;

public class EntityTTMSwampHag extends AbstractRaiderEntity implements IRangedAttackMob {
    private static final UUID SPEED_MODIFIER_DRINKING_UUID = UUID.fromString("D220EDA9-3E8C-4E3C-9400-665313E3D0B4");
    private static final AttributeModifier SPEED_MODIFIER_DRINKING;
    private static final DataParameter<Boolean> DATA_USING_ITEM;
    private int usingTime;
    private static final DataParameter<Integer> SWAMPHAG_TYPE = EntityDataManager.defineId(EntityTTMSwampHag.class, DataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/swamphag/swamp_hag1.png"));
        option.put(2, new ResourceLocation(TolkienMobs.MODID, "textures/entity/swamphag/swamp_hag2.png"));
        option.put(3, new ResourceLocation(TolkienMobs.MODID, "textures/entity/swamphag/swamp_hag3.png"));
        option.put(4, new ResourceLocation(TolkienMobs.MODID, "textures/entity/swamphag/swamp_hag4.png"));
    });

    public EntityTTMSwampHag(EntityType<? extends AbstractRaiderEntity> p_i50143_1_, World p_i50143_2_) {
        super(p_i50143_1_, p_i50143_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        ToggleableNearestAttackableTargetGoal<PlayerEntity> attackPlayersGoal = new ToggleableNearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, false, (Predicate) null);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 60, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[]{AbstractRaiderEntity.class}));
        this.targetSelector.addGoal(3, attackPlayersGoal);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 26.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITCH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.WITCH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITCH_DEATH;
    }

    public void setUsingItem(boolean p_82197_1_) {
        this.getEntityData().set(DATA_USING_ITEM, p_82197_1_);
    }

    public boolean isDrinkingPotion() {
        return (Boolean)this.getEntityData().get(DATA_USING_ITEM);
    }

    @Override
    public void aiStep() {
            if (this.isDrinkingPotion()) {
                if (this.usingTime-- <= 0) {
                    this.setUsingItem(false);
                    ItemStack lvt_1_1_ = this.getMainHandItem();
                    this.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                    if (lvt_1_1_.getItem() == Items.POTION) {
                        List<EffectInstance> lvt_2_1_ = PotionUtils.getMobEffects(lvt_1_1_);
                        if (lvt_2_1_ != null) {
                            Iterator var3 = lvt_2_1_.iterator();

                            while(var3.hasNext()) {
                                EffectInstance lvt_4_1_ = (EffectInstance)var3.next();
                                this.addEffect(new EffectInstance(lvt_4_1_));
                            }
                        }
                    }

                    this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_DRINKING);
                }
            } else {
                Potion lvt_1_2_ = null;
                if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(Effects.WATER_BREATHING)) {
                    lvt_1_2_ = Potions.WATER_BREATHING;
                } else if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(Effects.FIRE_RESISTANCE)) {
                    lvt_1_2_ = Potions.FIRE_RESISTANCE;
                } else if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                    lvt_1_2_ = Potions.HEALING;
                } else if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(Effects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                    lvt_1_2_ = Potions.SWIFTNESS;
                }

                if (lvt_1_2_ != null) {
                    this.setItemSlot(EquipmentSlotType.MAINHAND, PotionUtils.setPotion(new ItemStack(Items.POTION), lvt_1_2_));
                    this.usingTime = this.getMainHandItem().getUseDuration();
                    this.setUsingItem(true);
                    if (!this.isSilent()) {
                        this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_DRINK, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
                    }

                    ModifiableAttributeInstance lvt_2_2_ = this.getAttribute(Attributes.MOVEMENT_SPEED);
                    lvt_2_2_.removeModifier(SPEED_MODIFIER_DRINKING);
                    lvt_2_2_.addTransientModifier(SPEED_MODIFIER_DRINKING);
                }
            }

            if (this.random.nextFloat() < 7.5E-4F) {
                this.level.broadcastEntityEvent(this, (byte)15);
            }
        super.aiStep();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 15) {
            for(int lvt_2_1_ = 0; lvt_2_1_ < this.random.nextInt(35) + 10; ++lvt_2_1_) {
                this.level.addParticle(ParticleTypes.WITCH, this.getX() + this.random.nextGaussian() * 0.12999999523162842D, this.getBoundingBox().maxY + 0.5D + this.random.nextGaussian() * 0.12999999523162842D, this.getZ() + this.random.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
            }
        } else {
            super.handleEntityEvent(p_70103_1_);
        }

    }

    @Override
    protected float getDamageAfterMagicAbsorb(DamageSource p_70672_1_, float p_70672_2_) {
        p_70672_2_ = super.getDamageAfterMagicAbsorb(p_70672_1_, p_70672_2_);
        if (p_70672_1_.getEntity() == this) {
            p_70672_2_ = 0.0F;
        }

        if (p_70672_1_.isMagic()) {
            p_70672_2_ = (float)((double)p_70672_2_ * 0.15D);
        }

        return p_70672_2_;
    }

    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        if (!this.isDrinkingPotion()) {
            Vector3d lvt_3_1_ = p_82196_1_.getDeltaMovement();
            double lvt_4_1_ = p_82196_1_.getX() + lvt_3_1_.x - this.getX();
            double lvt_6_1_ = p_82196_1_.getEyeY() - 1.100000023841858D - this.getY();
            double lvt_8_1_ = p_82196_1_.getZ() + lvt_3_1_.z - this.getZ();
            float lvt_10_1_ = MathHelper.sqrt(lvt_4_1_ * lvt_4_1_ + lvt_8_1_ * lvt_8_1_);
            Potion lvt_11_1_ = Potions.HARMING;
            if (lvt_10_1_ >= 8.0F && !p_82196_1_.hasEffect(Effects.MOVEMENT_SLOWDOWN)) {
                lvt_11_1_ = Potions.SLOWNESS;
            } else if (p_82196_1_.getHealth() >= 8.0F && !p_82196_1_.hasEffect(Effects.POISON)) {
                lvt_11_1_ = Potions.POISON;
            } else if (lvt_10_1_ <= 3.0F && !p_82196_1_.hasEffect(Effects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                lvt_11_1_ = Potions.WEAKNESS;
            }

            PotionEntity lvt_12_1_ = new PotionEntity(this.level, this);
            lvt_12_1_.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), lvt_11_1_));
            lvt_12_1_.xRot -= -20.0F;
            lvt_12_1_.shoot(lvt_4_1_, lvt_6_1_ + (double)(lvt_10_1_ * 0.2F), lvt_8_1_, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level.addFreshEntity(lvt_12_1_);
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 1.62F;
    }

    @Override
    public boolean canBeLeader() {
        return false;
    }

    static {
        SPEED_MODIFIER_DRINKING = new AttributeModifier(SPEED_MODIFIER_DRINKING_UUID, "Drinking speed penalty", -0.25D, AttributeModifier.Operation.ADDITION);
        DATA_USING_ITEM = EntityDataManager.defineId(WitchEntity.class, DataSerializers.BOOLEAN);
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

    @Override
    public void applyRaidBuffs(int i, boolean b) {

    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getSwampHagTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getSwampHagType(), TEXTURE_BY_ID.get(1));
    }

    public int getSwampHagType() {
        return this.entityData.get(SWAMPHAG_TYPE);
    }

    public void setSwampHagType(int type) {
        if (type < 0 || type >= 5) {
            type = this.random.nextInt(4);
        }

        this.entityData.set(SWAMPHAG_TYPE, type);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(5, 1);
        this.setSwampHagType(job);
        this.populateDefaultEquipmentSlots(difficultyIn);

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_USING_ITEM, false);
        this.entityData.define(SWAMPHAG_TYPE, 3);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("SwampHagType", this.getSwampHagType());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setSwampHagType(compound.getInt("SwampHagType"));
    }
}