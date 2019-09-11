package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class TTMDamageSource {
    public static class DamageSourceFear extends EntityDamageSource {
        public DamageSourceFear(final Entity entity) {
            super("fearEvil", entity);
            this.setMagicDamage();
            this.setDamageIsAbsolute();
            this.setDamageBypassesArmor();
            this.setDamageAllowedInCreativeMode();
        }
    }

}
