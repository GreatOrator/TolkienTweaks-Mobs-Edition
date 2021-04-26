package com.greatorator.tolkienmobs.item.potion.effects;

import com.greatorator.tolkienmobs.datagen.EnchantmentGenerator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;

public class TerrorTTMEffect extends TTMEffectBase {
    public static FearTTMEffect instance = null;
    public static int terrorDuration = 10;

    public TerrorTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentGenerator.GONDOR_RESOLVE.get(), entity);

        if (level > 0) {
            entity.removeEffect(this);
        }else{
            return;
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % terrorDuration == 0;
    }
}
