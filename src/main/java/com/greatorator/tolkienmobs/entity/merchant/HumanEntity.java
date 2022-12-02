package com.greatorator.tolkienmobs.entity.merchant;

import com.greatorator.tolkienmobs.entity.VillagerEntity;
import com.greatorator.tolkienmobs.entity.merchant.variant.EntityVariant;
import com.greatorator.tolkienmobs.utils.RandomUtility;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class HumanEntity extends VillagerEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public HumanEntity(EntityType<? extends VillagerEntity> type, Level level) {
        super(type, level);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.human.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.human.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    /** VARIANTS */
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor accessor, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag compoundTag) {
        EntityVariant variant = EntityVariant.byId(RandomUtility.getRandomInteger(10, 0));
        setVariant(variant);
        setProfession();
        return super.finalizeSpawn(accessor, instance, type, data, compoundTag);
    }

    @Override
    public EntityVariant getVariant() {
        return EntityVariant.byId(this.getTypeVariant() & 255);
    }
}