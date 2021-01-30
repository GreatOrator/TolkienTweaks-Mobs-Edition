//package com.greatorator.tolkienmobs.item.potiontypes;
//
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.math.MathHelper;
//
//public class PotionTTMBurning extends TTMPotion {
//    public static final String NAME = "elemental_burning";
//    public static PotionTTMBurning instance = null;
//
//    public static float fireDuration = 10;
//
//    public PotionTTMBurning() {
//        super(NAME, true, 15545365, 5);
//        instance = this;
//    }
//
//    @Override
//    public boolean isInstant() {
//        return true;
//    }
//
//    @Override
//    public void affectEntity(Entity thrownPotion, Entity thrower, LivingEntity entity, int amplifier, double potency) {
//
//        int duration = MathHelper.ceil((double)(amplifier + 1) * (double)fireDuration * potency);
//
//        entity.setFire(duration);
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier) {
//
//        //10 seconds of fire for each level
//        int duration = MathHelper.ceil((float)(amplifier+1) * fireDuration);
//
//        entity.setFire(duration);
//    }
//}
