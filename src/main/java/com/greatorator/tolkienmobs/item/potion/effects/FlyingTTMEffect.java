package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class FlyingTTMEffect extends TTMEffectBase {
    public static FlyingTTMEffect instance = null;
    public static int flyDuration = 10;

    public FlyingTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect((new EffectInstance(Effects.LEVITATION, 40, amplifier, true, false, false, null)));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % flyDuration == 0;
    }
}
