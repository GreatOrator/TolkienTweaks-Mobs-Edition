package com.greatorator.tolkienmobs.entity;

import codechicken.lib.math.MathHelper;
import com.greatorator.tolkienmobs.entity.item.FellBeastFireballEntity;
import com.greatorator.tolkienmobs.entity.monster.variant.MonsterVariant;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MonsterEntity extends Monster implements RangedAttackMob, IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(MonsterEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> HELD_ITEM = SynchedEntityData.defineId(MonsterEntity.class, EntityDataSerializers.ITEM_STACK);
    private final RangedBowAttackGoal<MonsterEntity> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
        public void stop() {
            super.stop();
            MonsterEntity.this.setAggressive(false);
        }

        public void start() {
            super.start();
            MonsterEntity.this.setAggressive(true);
        }
    };
    private boolean scheduleWeaponGoalUpdate = true;
    protected boolean ranged;

    protected MonsterEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.reassessWeaponGoal();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    /** Set up using weapons **/
    public void setHeldItem(ItemStack heldItem) {
        this.entityData.set(HELD_ITEM, heldItem.copy());
    }

    public ItemStack getHeldItem() {
        return this.entityData.get(HELD_ITEM).copy();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 1);
        this.entityData.define(HELD_ITEM, ItemStack.EMPTY);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance instance) {
        super.populateDefaultEquipmentSlots(instance);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem weaponItem) {
        return true;
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float x) {
        double d0 = entity.getEyeY() - (double)1.1F;
        double d1 = entity.getX() - this.getX();
        double d3 = entity.getZ() - this.getZ();
        FellBeastFireballEntity fireballentity = new FellBeastFireballEntity(this.level, this, d1, d0, d3);
        double d2 = d0 - fireballentity.getY();
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
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_, @Nonnull DamageSource damageSource) {
        return false;
    }

    @Override
    public void setItemSlot(@Nonnull EquipmentSlot slot, @Nonnull ItemStack stack) {
        super.setItemSlot(slot, stack);
        setHeldItem(stack);
        reassessWeaponGoal();
    }

    public void reassessWeaponGoal() {
        if (!this.level.isClientSide) {
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
            this.ranged = true;
        } else {
            this.goalSelector.addGoal(4, this.meleeGoal);
            this.ranged = false;
        }
    }

    public boolean getRanged() {
        return ranged;
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if (!event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
            this.swinging =false;
        }else if (this.getRanged() && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("ranged", false));
            this.ranged = false;
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

    /** VARIANTS */
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        MonsterVariant variant = Util.getRandom(MonsterVariant.values(), this.random);
        setVariant(variant);
        groupData = super.finalizeSpawn(levelAccessor, instance, type, groupData, tag);
        this.populateDefaultEquipmentSlots(instance);
        this.populateDefaultEquipmentEnchantments(instance);
        this.reassessWeaponGoal();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * instance.getSpecialMultiplier());
        if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F) {
                this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }
        return groupData;
    }

    public MonsterVariant getVariant() {
        return MonsterVariant.byId(this.getTypeVariant() & 255);
    }

    protected int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    protected void setVariant(MonsterVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }
}