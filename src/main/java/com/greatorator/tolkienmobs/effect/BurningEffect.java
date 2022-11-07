package com.greatorator.tolkienmobs.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BurningEffect extends PotionBaseEffect {
    public static BurningEffect instance = null;
    public static float fireDuration = 10;

    public BurningEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity thrownPotion, Entity thrower, LivingEntity entity, int amplifier, double potency) {

        int duration = Mth.ceil((double)(amplifier + 1) * (double)fireDuration * potency);

        entity.setSecondsOnFire(duration);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {

        //10 seconds of fire for each level
        int duration = Mth.ceil((float)(amplifier+1) * fireDuration);

        entity.setSecondsOnFire(duration);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % fireDuration == 0;
    }
}
