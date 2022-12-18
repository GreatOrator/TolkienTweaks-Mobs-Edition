package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class MimicChestEntity extends MonsterEntity {
    private static final EntityDataAccessor<Boolean> ATTACK = SynchedEntityData.defineId(MimicChestEntity.class, EntityDataSerializers.BOOLEAN);
    private long nextAbilityUse = 0L;
    private final static long coolDown = 15000L;

    public MimicChestEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.3125F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 5.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.3F));
    }

    @Override
    public ItemStack getHeldItem() {
        return super.getHeldItem();
    }

    @Override
    public void setHeldItem(ItemStack heldItem) {
    }

    public void setActive(boolean active) {
        this.entityData.set(ATTACK, active);
    }

    public boolean isActive() {
        return this.entityData.get(ATTACK);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK, false);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(ATTACK, tag.getBoolean("canAttack"));
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("canAttack", this.isActive());
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        groupData = super.finalizeSpawn(levelAccessor, instance, type, groupData, tag);
        this.setActive(false);
        this.setNoAi(true);
        this.setCustomName(new TranslatableComponent("block.minecraft.chest"));

        return groupData;
    }

    @Nonnull
    @Override
    public InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        if (!this.isActive() && !this.level.isClientSide) {
            this.setActive(true);
            this.setNoAi(false);
            this.isEffectiveAi();
            this.setCustomName(new TranslatableComponent("entity.tolkienmobs.entityttmmimicchest"));
            return InteractionResult.PASS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        this.playSound(TolkienSounds.soundStepMimic.get(), 0.15F, 1.0F);
    }

    /** Special Attack */
    @Override
    public boolean doHurtTarget(@Nonnull Entity entityIn) {
        long time = System.currentTimeMillis();
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof Player) {
                if (time > nextAbilityUse) {
                    nextAbilityUse = time + coolDown;
                    Player player = (Player) entityIn;
                    int strength = 2;
                    player.addEffect(new MobEffectInstance(TolkienPotions.INVENTORY_CORROSION.get(), strength * 20, 0));
                }
            }
        }
        return true;
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving() && this.isActive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if (!event.isMoving() && this.isActive()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_mimic", true));
            return PlayState.CONTINUE;
        }
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.isActive() && event.getController().getAnimationState().equals(AnimationState.Stopped)){
            event.getController().markNeedsReload();
            this.playSound(TolkienSounds.soundAngryMimic.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", false));
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
}