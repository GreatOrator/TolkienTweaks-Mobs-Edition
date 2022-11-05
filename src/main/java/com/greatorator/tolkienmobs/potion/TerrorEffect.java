package com.greatorator.tolkienmobs.potion;

import com.greatorator.tolkienmobs.init.TolkienEnchants;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class TerrorEffect extends PotionBaseEffect {
    public static FearEffect instance = null;
    public static int terrorDuration = 10;

    public TerrorEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int level = EnchantmentHelper.getEnchantmentLevel(TolkienEnchants.GONDOR_RESOLVE.get(), entity);

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
