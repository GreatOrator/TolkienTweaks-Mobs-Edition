package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class NimbleTTMEffect extends TTMEffectBase {
    public static NimbleTTMEffect instance = null;

    public NimbleTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {}

    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    }
}
