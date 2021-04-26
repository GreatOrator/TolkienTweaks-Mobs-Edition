package com.greatorator.tolkienmobs.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class TTMDamageSource extends DamageSource {
    public TTMDamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }

    public static DamageSource causeHallowedDamage(Entity source) {
        return (new EntityDamageSource("hallowed", source)).setThorns().setMagic();
    }

}
