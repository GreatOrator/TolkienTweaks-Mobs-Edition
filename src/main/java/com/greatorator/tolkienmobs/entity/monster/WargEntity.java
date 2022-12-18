package com.greatorator.tolkienmobs.entity.monster;

import com.greatorator.tolkienmobs.entity.MonsterEntity;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.utils.MathUtility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class WargEntity extends MonsterEntity {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean isWet;

    public WargEntity(EntityType<? extends MonsterEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.875F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D);
    }

    @Override
    public double getPassengersRidingOffset() {
        return super.getPassengersRidingOffset() - 0.675D;
    }

    @Override
    public ItemStack getHeldItem() {
        return super.getHeldItem();
    }

    @Override
    public void setHeldItem(ItemStack heldItem) {
    }

    @Override
    public void tick(){
        super.tick();
        if (this.isInWaterRainOrBubble()) {
            this.isWet = true;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance instance, @Nonnull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        groupData = super.finalizeSpawn(levelAccessor, instance, type, groupData, tag);

        if (MathUtility.getRandomInteger(15, 1) == 2) {
            MordorOrcEntity entitymordororc = TolkienEntities.ENTITY_TTM_MORDORORC.get().create(this.level);
            entitymordororc.moveTo(this.getX(), this.getY(), this.getZ(), this.yRotO, 0.0F);
            entitymordororc.finalizeSpawn(levelAccessor, instance, type, (SpawnGroupData)null, (CompoundTag)null);
            entitymordororc.startRiding(this);
        }

        return groupData;
    }

    /** Animation region */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }else if (this.isWet) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("shake", false));
            this.isWet = false;
            return PlayState.CONTINUE;
        }else if (!event.isMoving()){
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
}