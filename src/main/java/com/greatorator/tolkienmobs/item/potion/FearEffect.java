package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class FearEffect extends PotionBaseEffect {
    public static FearEffect instance = null;
    public static int fearDuration = 10;

    public FearEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect((new MobEffectInstance(MobEffects.WITHER, 40, amplifier, true, false, false, null)));
        entity.addEffect((new MobEffectInstance(MobEffects.BLINDNESS, 40, amplifier, true, false, false, null)));
        entity.addEffect((new MobEffectInstance(MobEffects.WEAKNESS, 40, amplifier, true, false, false, null)));
        entity.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, amplifier, true, false, false, null)));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % fearDuration == 0;
    }
}