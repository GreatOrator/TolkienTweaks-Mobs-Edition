package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class TornadoTTMEffect extends TTMEffectBase {
    public static TornadoTTMEffect instance = null;
    public static int tornadoDuration = 10;

    public TornadoTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void performEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % tornadoDuration == 0;
    }
}
