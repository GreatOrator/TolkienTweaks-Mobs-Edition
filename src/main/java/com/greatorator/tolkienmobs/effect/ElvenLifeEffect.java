package com.greatorator.tolkienmobs.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ElvenLifeEffect extends BasePotionEffect {
    public static ElvenLifeEffect instance = null;
    public static int damageTime = 20;

    public ElvenLifeEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
        instance = this;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity.level.isClientSide)
            return;

        entity.addEffect((new MobEffectInstance(MobEffects.ABSORPTION, 160, 6 + amplifier, false, false, false)));
        entity.addEffect((new MobEffectInstance(MobEffects.REGENERATION, 900, 6 + amplifier, false, false, false)));

    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % damageTime == 0;
    }

}
