package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyFollowParentGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyHurtByTargetGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyNearPlayerGoal;
import com.greatorator.tolkienmobs.entity.ai.goal.mumakil.BabyPanicGoal;
import com.greatorator.tolkienmobs.entity.merchant.DwarfEntity;
import com.greatorator.tolkienmobs.entity.passive.variant.PassiveVariant;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.SoundType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GoatEntity extends AbstractChestedHorse implements IAnimatable {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(GoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(GoatEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimationFactory factory = new AnimationFactory(this);
    private int warningSoundTicks;

    public GoatEntity(EntityType<? extends AbstractChestedHorse> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return TolkienEntities.ENTITY_TTM_GOAT.get().create(serverLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Bee.class, 8.0f, 1.5, 1.5));
        this.goalSelector.addGoal(1, new GoatEntity.GoatEntityMeleeAttackGoal());
        this.goalSelector.addGoal(3, new BabyPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(TolkienItems.PIPEWEED_ITEM.get()), false));
        this.goalSelector.addGoal(4, new BabyFollowParentGoal(this, 1.25D, 24.0D, 6.0D, 12.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new BabyHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new BabyNearPlayerGoal(this, 0.5F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.JUMP_STRENGTH)
                .add(Attributes.ATTACK_DAMAGE, 16.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 8.0D);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() + 0.015D;
    }

    @Override
    protected void randomizeAttributes() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)this.generateRandomMaxHealth());
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.generateRandomSpeed());
        this.getAttribute(Attributes.JUMP_STRENGTH).setBaseValue((double)this.generateRandomJumpStrength());
    }

    @Override
    protected float generateRandomMaxHealth() {
        return 20.0F + (float)this.random.nextInt(8) + (float)this.random.nextInt(9);
    }

    @Override
    protected double generateRandomJumpStrength() {
        return (double)0.6F + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;
    }

    @Override
    protected double generateRandomSpeed() {
        return ((double)0.55F + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
    }

    /** Sounds */
    protected void playGallopSound(SoundType p_30709_) {
        super.playGallopSound(p_30709_);
        if (this.random.nextInt(10) == 0) {
            this.playSound(SoundEvents.HORSE_BREATHE, p_30709_.getVolume() * 0.6F, p_30709_.getPitch());
        }

        ItemStack stack = this.inventory.getItem(1);
        if (isArmor(stack)) stack.onHorseArmorTick(level, this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return TolkienSounds.soundIdleGoat.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return TolkienSounds.soundDeathGoat.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return TolkienSounds.soundHurtGoat.get();
    }

    @Override
    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return TolkienSounds.soundAngryGoat.get();
    }

    @Nullable
    protected SoundEvent getEatingSound() {
        return SoundEvents.HORSE_EAT;
    }

    protected void playWarningSound() {
        if (this.warningSoundTicks <= 0) {
            this.playSound(TolkienSounds.soundScreamGoat.get(), 1.0F, this.getVoicePitch());
            this.warningSoundTicks = 40;
        }

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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** Variant */
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor accessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        PassiveVariant variant = Util.getRandom(PassiveVariant.values(), this.random);
        setVariant(variant);
        this.randomizeAttributes();
        AgeableMobGroupData ageableMobGroupData;
        if (data == null) {
            data = new AgeableMobGroupData(true);
        }
        if ((ageableMobGroupData = (AgeableMobGroupData)data).getGroupSize() > 1) {
            this.setAge(-24000);
        }
        ageableMobGroupData.increaseGroupSizeByOne();
        Random random = level.getRandom();
        this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random spawn bonus", random.nextInt(3), AttributeModifier.Operation.MULTIPLY_BASE));

        if (accessor.getRandom().nextInt(100) == 0) {
            DwarfEntity dwarfentity = TolkienEntities.ENTITY_TTM_DWARF.get().create(this.level);
            dwarfentity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
            dwarfentity.finalizeSpawn(accessor, instance, type, (SpawnGroupData)null, (CompoundTag)null);
            this.setTamed(true);
            dwarfentity.startRiding(this);
        }

        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setChest(tag.getBoolean("ChestedGoat"));
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag listtag = tag.getList("Items", 10);

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.of(compoundtag));
                }
            }
        }
        this.updateContainerEquipment();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("ChestedGoat", this.hasChest());
        tag.putInt("Variant", this.getTypeVariant());
        if (this.hasChest()) {
            ListTag listtag = new ListTag();

            for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)i);
                    itemstack.save(compoundtag);
                    listtag.add(compoundtag);
                }
            }
            tag.put("Items", listtag);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
        this.entityData.define(DATA_ID_CHEST, false);
    }

    public PassiveVariant getVariant() {
        return PassiveVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(PassiveVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    /** Goals */
    class GoatEntityMeleeAttackGoal extends MeleeAttackGoal {
        public GoatEntityMeleeAttackGoal() {
            super(GoatEntity.this, 1.25D, true);
        }

        protected void checkAndPerformAttack(LivingEntity p_29589_, double p_29590_) {
            double d0 = this.getAttackReachSqr(p_29589_);
            if (p_29590_ <= d0 && this.isTimeToAttack()) {
                this.resetAttackCooldown();
                this.mob.doHurtTarget(p_29589_);
                GoatEntity.this.setStanding(false);
            } else if (p_29590_ <= d0 * 2.0D) {
                if (this.isTimeToAttack()) {
                    GoatEntity.this.setStanding(false);
                    this.resetAttackCooldown();
                }

                if (this.getTicksUntilNextAttack() <= 10) {
                    GoatEntity.this.setStanding(true);
                    GoatEntity.this.playWarningSound();
                }
            } else {
                this.resetAttackCooldown();
                GoatEntity.this.setStanding(false);
            }

        }

        public void stop() {
            GoatEntity.this.setStanding(false);
            super.stop();
        }

        protected double getAttackReachSqr(LivingEntity p_29587_) {
            return (double)(4.0F + p_29587_.getBbWidth());
        }
    }
}