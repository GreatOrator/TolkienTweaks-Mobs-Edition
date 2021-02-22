package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class TerrorTTMEffect extends TTMEffectBase {
    public static FearTTMEffect instance = null;
    public static int terrorDuration = 10;

    public TerrorTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % terrorDuration == 0;
    }
}
