package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.EntityLivingBase;

public class PotionTTMSleep extends TTMPotion {
    public static PotionTTMSleep instance = null;

    public static float sleepDuration = 10;


    public PotionTTMSleep(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
        super(name, isBadEffectIn, liquidColorIn, iconIndex);
        instance = this;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {}
}
