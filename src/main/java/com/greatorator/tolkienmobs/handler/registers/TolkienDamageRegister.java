package com.greatorator.tolkienmobs.handler.registers;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class TolkienDamageRegister extends DamageSource {
    public TolkienDamageRegister(String damageTypeIn) {
        super(damageTypeIn);
    }

    public static DamageSource causeHallowedDamage(Entity source) {
        return (new EntityDamageSource("hallowed", source)).setThorns().setMagic();
    }

    public static DamageSource causeFellBeastDamage(Entity source) {
        return (new EntityDamageSource("fellbeast", source)).setIsFire().setProjectile().setMagic();
    }

}
