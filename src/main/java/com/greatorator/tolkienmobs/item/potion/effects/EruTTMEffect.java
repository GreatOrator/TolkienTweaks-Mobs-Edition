package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectType;

public class EruTTMEffect extends TTMEffectBase {
    public static EruTTMEffect instance = null;
    public static int healTime = 20;

    public EruTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        entity.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % healTime == 0;
    }
}
