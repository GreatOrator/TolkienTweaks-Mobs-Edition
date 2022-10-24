package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class NimbleEffect extends PotionBaseEffect {
    public static NimbleEffect instance = null;

    public NimbleEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {}

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return false;
    }
}
