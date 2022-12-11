package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.world.entity.LivingEntity;

public interface CommonTraits {
    default double getMovementSpeed(LivingEntity of){
        return Math.sqrt(Math.pow(of.getX() - of.xo, 2) + Math.pow(of.getZ() - of.zo, 2));
    }
}
