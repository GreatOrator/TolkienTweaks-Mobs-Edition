package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class DrownTTMEffect extends TTMEffectBase {
    public static DrownTTMEffect instance = null;
    public static int drownDuration = 10;

    public DrownTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % drownDuration == 0;
    }
}
