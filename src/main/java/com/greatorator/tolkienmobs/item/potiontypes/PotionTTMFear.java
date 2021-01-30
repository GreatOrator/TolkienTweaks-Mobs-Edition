//package com.greatorator.tolkienmobs.item.potiontypes;
//
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.init.MobEffects;
//import net.minecraft.potion.PotionEffect;
//
//public class PotionTTMFear extends TTMPotion {
//    public static final String NAME = "dread_aura";
//    public static PotionTTMFear instance = null;
//
//    public PotionTTMFear(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
//        super(name, isBadEffectIn, liquidColorIn, iconIndex);
//        instance = this;
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier) {
//        entity.addPotionEffect((new PotionEffect(MobEffects.WITHER, 40, amplifier, true, false)));
//        entity.addPotionEffect((new PotionEffect(MobEffects.BLINDNESS, 40, amplifier, true, false)));
//        entity.addPotionEffect((new PotionEffect(MobEffects.WEAKNESS, 40, amplifier, true, false)));
//        entity.addPotionEffect((new PotionEffect(MobEffects.SLOWNESS, 40, amplifier, true, false)));
//    }
//}