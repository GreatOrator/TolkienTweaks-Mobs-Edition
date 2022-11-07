package com.greatorator.tolkienmobs.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class TornadoEffect extends PotionBaseEffect {
    public static final String TAG_PITCH = "elemental_tornado_pitch";
    public static final String TAG_YAW = "elemental_tornado_yaw";

    public static float rotationSpeed = 2.0f;


    public TornadoEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        float maxRotation = 16;
        float maxRand = rotationSpeed * (amplifier + 1);

        float pitch = entity.getPersistentData().getFloat(TAG_PITCH);
        float yaw = entity.getPersistentData().getFloat(TAG_YAW);

        float randPitch = (entity.getRandom().nextFloat() * maxRand * 2) - maxRand;
        float randYaw = (entity.getRandom().nextFloat() * maxRand * 2) - maxRand;

        if (Mth.abs(pitch + randPitch) > maxRotation) {
            pitch += -randPitch;
        } else {
            pitch += randPitch;
        }

        if (Mth.abs(yaw + randYaw) > maxRotation) {
            yaw += -randYaw;
        } else {
            yaw += randYaw;
        }

        entity.xRotO += pitch;
        entity.yRotO += yaw;

        entity.getPersistentData().putFloat(TAG_PITCH, pitch);
        entity.getPersistentData().putFloat(TAG_YAW, yaw);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
