package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;

public class BurningTTMEffect extends TTMEffectBase {
    public static BurningTTMEffect instance = null;
    public static float fireDuration = 10;

    public BurningTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void affectEntity(Entity thrownPotion, Entity thrower, LivingEntity entity, int amplifier, double potency) {

        int duration = MathHelper.ceil((double)(amplifier + 1) * (double)fireDuration * potency);

        entity.setFire(duration);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {

        //10 seconds of fire for each level
        int duration = MathHelper.ceil((float)(amplifier+1) * fireDuration);

        entity.setFire(duration);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % fireDuration == 0;
    }
}
