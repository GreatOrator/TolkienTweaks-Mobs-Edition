package com.greatorator.tolkienmobs.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;

import javax.annotation.Nullable;

public class SleepEffect extends PotionBaseEffect {
    public static SleepEffect instance = null;
    public static int sleepDuration = 10;
    @Nullable
    private Pose forcedPose;

    public SleepEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % sleepDuration == 0;
    }
}
