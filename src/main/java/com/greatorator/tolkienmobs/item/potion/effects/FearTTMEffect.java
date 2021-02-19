package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class FearTTMEffect extends TTMEffectBase {
    public static FearTTMEffect instance = null;
    public static int fearDuration = 10;

    public FearTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        entity.addPotionEffect((new EffectInstance(Effects.WITHER, 40, amplifier, true, false, false, null)));
        entity.addPotionEffect((new EffectInstance(Effects.BLINDNESS, 40, amplifier, true, false, false, null)));
        entity.addPotionEffect((new EffectInstance(Effects.WEAKNESS, 40, amplifier, true, false, false, null)));
        entity.addPotionEffect((new EffectInstance(Effects.SLOWNESS, 40, amplifier, true, false, false, null)));
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % fearDuration == 0;
    }
}