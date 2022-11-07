package com.greatorator.tolkienmobs.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class FlyingEffect extends PotionBaseEffect {
    public static FlyingEffect instance = null;
    public static int flyDuration = 10;

    public FlyingEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect((new MobEffectInstance(MobEffects.LEVITATION, 40, amplifier, true, false, false, null)));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % flyDuration == 0;
    }
}
