package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class SleepEffect extends PotionBaseEffect {
    public static SleepEffect instance = null;
    public static int sleepDuration = 10;
    @Nullable
    private Pose forcedPose;

    public SleepEffect(EffectType typeIn, int liquidColorIn) {
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
