package com.greatorator.tolkienmobs.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BirdEntity extends Animal implements FlyingAnimal {
    protected BirdEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel level, @Nonnull AgeableMob mob) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }
}