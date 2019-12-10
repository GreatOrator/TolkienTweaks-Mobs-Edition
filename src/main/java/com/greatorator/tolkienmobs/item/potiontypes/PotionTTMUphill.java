package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.handler.TTMPotion;
import net.minecraft.entity.EntityLivingBase;

public class PotionTTMUphill extends TTMPotion {
    public static PotionTTMUphill instance = null;
    public static int stepTime = 20;

    public PotionTTMUphill(String name, Boolean isBadEffectIn, int liquidColorIn, int iconIndex) {
        super(name, isBadEffectIn, liquidColorIn, iconIndex);
        instance = this;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if(amplifier <= 1) {
            if(entity.ticksExisted % stepTime == 0) {
                entity.stepHeight = 0.6F;
            }else
                entity.stepHeight = 1.001F;
        }else if (amplifier > 2) {
            if(entity.ticksExisted % stepTime == 0) {
                entity.stepHeight = 0.6F;
            }else
                entity.stepHeight = 1.200F;
        }
    }
}
