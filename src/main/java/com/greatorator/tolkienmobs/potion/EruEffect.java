package com.greatorator.tolkienmobs.potion;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class EruEffect extends PotionBaseEffect {
    public static EruEffect instance = null;
    public static int healTime = 20;

    public EruEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        for (MobEffectInstance effect : ImmutableList.copyOf(entity.getActiveEffects())) {
            if (!effect.getEffect().isBeneficial() && !effect.isNoCounter()) {
                entity.removeEffect(effect.getEffect());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % healTime == 0;
    }
}
