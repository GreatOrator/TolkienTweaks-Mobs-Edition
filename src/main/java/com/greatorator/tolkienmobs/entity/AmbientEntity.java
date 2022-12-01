package com.greatorator.tolkienmobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

public class AmbientEntity extends AmbientCreature {
    protected AmbientEntity(EntityType<? extends AmbientCreature> type, Level level) {
        super(type, level);
    }
}