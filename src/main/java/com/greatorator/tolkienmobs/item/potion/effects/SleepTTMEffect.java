package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class SleepTTMEffect extends TTMEffectBase {
    public static SleepTTMEffect instance = null;
    public static int sleepDuration = 10;
    @Nullable
    private Pose forcedPose;

    public SleepTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % sleepDuration == 0;
    }
}
