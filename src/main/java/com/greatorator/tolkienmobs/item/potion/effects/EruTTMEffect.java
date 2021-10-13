package com.greatorator.tolkienmobs.item.potion.effects;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class EruTTMEffect extends TTMEffectBase {
    public static EruTTMEffect instance = null;
    public static int healTime = 20;

    public EruTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        for (EffectInstance effect : ImmutableList.copyOf(entity.getActiveEffects())) {
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
