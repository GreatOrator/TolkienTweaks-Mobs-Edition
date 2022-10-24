package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;

public class BurningEffect extends PotionBaseEffect {
    public static BurningEffect instance = null;
    public static float fireDuration = 10;

    public BurningEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity thrownPotion, Entity thrower, LivingEntity entity, int amplifier, double potency) {

        int duration = MathHelper.ceil((double)(amplifier + 1) * (double)fireDuration * potency);

        entity.setSecondsOnFire(duration);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

        //10 seconds of fire for each level
        int duration = MathHelper.ceil((float)(amplifier+1) * fireDuration);

        entity.setSecondsOnFire(duration);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % fireDuration == 0;
    }
}
