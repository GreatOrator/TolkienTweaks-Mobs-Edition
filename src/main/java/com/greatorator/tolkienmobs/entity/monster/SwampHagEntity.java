package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.entity.ai.target.SwampHagNearestAttackableTargetGoal;
import com.greatorator.tolkienmobs.entity.ai.target.SwampHagNearestHealableRaiderTargetGoal;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class SwampHagEntity extends MonsterEntity implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Boolean> DATA_HEALING = SynchedEntityData.defineId(SwampHagEntity.class, EntityDataSerializers.BOOLEAN);
    private SwampHagNearestHealableRaiderTargetGoal<MonsterEntity> healRaidersGoal;
    private SwampHagNearestAttackableTargetGoal<Player> attackPlayersGoal;
    private int healingTime;

    public SwampHagEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.4375F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.attackPlayersGoal = new SwampHagNearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (Predicate<LivingEntity>)null);
        this.healRaidersGoal = new SwampHagNearestHealableRaiderTargetGoal<>(this, MonsterEntity.class, true, (entity) -> {
            return entity != null  && entity.getType() != TolkienEntities.ENTITY_TTM_SWAMPHAG.get();
        });
        this.targetSelector.addGoal(2, this.healRaidersGoal);
        this.targetSelector.addGoal(3, this.attackPlayersGoal);
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive()) {
            this.healRaidersGoal.decrementCooldown();
            if (this.healRaidersGoal.getCooldown() <= 0) {
                this.attackPlayersGoal.setCanAttack(true);
            } else {
                this.attackPlayersGoal.setCanAttack(false);
            }

            if (this.isHealing()) {
                if (this.healingTime-- <= 0) {
                    this.setHealing(false);
                    ItemStack itemstack = this.getMainHandItem();
                    this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    if (itemstack.is(Items.POTION)) {
                        List<MobEffectInstance> list = PotionUtils.getMobEffects(itemstack);
                        if (list != null) {
                            for(MobEffectInstance mobeffectinstance : list) {
                                this.addEffect(new MobEffectInstance(mobeffectinstance));
                            }
                        }
                    }
                }
            } else {
                Potion potion = null;
                if (this.random.nextFloat() < 0.15F && this.isEyeInFluid(FluidTags.WATER) && !this.hasEffect(MobEffects.WATER_BREATHING)) {
                    potion = Potions.WATER_BREATHING;
                } else if (this.random.nextFloat() < 0.15F && (this.isOnFire() || this.getLastDamageSource() != null && this.getLastDamageSource().isFire()) && !this.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                    potion = Potions.FIRE_RESISTANCE;
                } else if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                    potion = Potions.HEALING;
                } else if (this.random.nextFloat() < 0.5F && this.getTarget() != null && !this.hasEffect(MobEffects.MOVEMENT_SPEED) && this.getTarget().distanceToSqr(this) > 121.0D) {
                    potion = Potions.SWIFTNESS;
                }

                if (potion != null) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, PotionUtils.setPotion(new ItemStack(Items.POTION), potion));
                    this.healingTime = this.getMainHandItem().getUseDuration();
                    this.setHealing(true);
                    if (!this.isSilent()) {
                        this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_DRINK, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
                    }
                }
            }

            if (this.random.nextFloat() < 7.5E-4F) {
                this.level.broadcastEntityEvent(this, (byte)15);
            }
        }

        super.aiStep();
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float p_34144_) {
        if (!this.isHealing()) {
            Vec3 vec3 = entity.getDeltaMovement();
            double d0 = entity.getX() + vec3.x - this.getX();
            double d1 = entity.getEyeY() - (double)1.1F - this.getY();
            double d2 = entity.getZ() + vec3.z - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            Potion potion = Potions.HARMING;
            if (entity instanceof MonsterEntity) {
                if (entity.getHealth() <= 4.0F) {
                    potion = Potions.HEALING;
                } else {
                    potion = Potions.REGENERATION;
                }
                this.setTarget((LivingEntity)null);
            } else if (d3 >= 8.0D && !entity.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                potion = Potions.SLOWNESS;
            } else if (entity.getHealth() >= 8.0F && !entity.hasEffect(MobEffects.POISON)) {
                potion = Potions.POISON;
            } else if (d3 <= 3.0D && !entity.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                potion = Potions.WEAKNESS;
            }

            ThrownPotion thrownpotion = new ThrownPotion(this.level, this);
            thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
            thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
            thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level.addFreshEntity(thrownpotion);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_HEALING, false);
    }

    public void setHealing(boolean healing) {
        this.getEntityData().set(DATA_HEALING, healing);
    }

    public boolean isHealing() {
        return this.getEntityData().get(DATA_HEALING);
    }

    @Override
    public void handleEntityEvent(byte p_34138_) {
        if (p_34138_ == 15) {
            for(int i = 0; i < this.random.nextInt(35) + 10; ++i) {
                this.level.addParticle(ParticleTypes.WITCH, this.getX() + this.random.nextGaussian() * (double)0.13F, this.getBoundingBox().maxY + 0.5D + this.random.nextGaussian() * (double)0.13F, this.getZ() + this.random.nextGaussian() * (double)0.13F, 0.0D, 0.0D, 0.0D);
            }
        } else {
            super.handleEntityEvent(p_34138_);
        }
    }

    @Override
    protected float getDamageAfterMagicAbsorb(DamageSource p_34149_, float p_34150_) {
        p_34150_ = super.getDamageAfterMagicAbsorb(p_34149_, p_34150_);
        if (p_34149_.getEntity() == this) {
            p_34150_ = 0.0F;
        }

        if (p_34149_.isMagic()) {
            p_34150_ *= 0.15F;
        }

        return p_34150_;
    }

    @Override
    public ItemStack getHeldItem() {
        return super.getHeldItem();
    }

    @Override
    public void setHeldItem(ItemStack heldItem) {
    }

    /** Animation */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.getRanged() && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            this.ranged = false;
        }else if (this.isHealing() && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack_heal", false));
            this.setHealing(false);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Sounds */
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITCH_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_34154_) {
        return SoundEvents.WITCH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WITCH_DEATH;
    }
}