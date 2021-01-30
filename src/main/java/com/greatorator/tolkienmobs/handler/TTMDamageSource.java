//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
//import net.minecraft.entity.Entity;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntityDamageSource;
//import net.minecraft.util.EntityDamageSourceIndirect;
//
//import javax.annotation.Nullable;
//
//public class TTMDamageSource {
//    public static class DamageSourceFear extends EntityDamageSource {
//        public DamageSourceFear(final Entity entity) {
//            super("fearEvil", entity);
//            this.setMagicDamage();
//            this.setDamageIsAbsolute();
//            this.setDamageBypassesArmor();
//            this.setDamageAllowedInCreativeMode();
//        }
//    }
//
//    public static DamageSource causeArrowDamage(EntityGaladhrimArrow arrow, @Nullable Entity indirectEntityIn)
//    {
//        return (new EntityDamageSourceIndirect("galadhrim_arrow", arrow, indirectEntityIn)).setProjectile();
//    }
//
//}
