package com.greatorator.tolkienmobs.item.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;

public class TornadoEffect extends PotionBaseEffect {
    public static final String TAG_PITCH = "elemental_tornado_pitch";
    public static final String TAG_YAW = "elemental_tornado_yaw";

    public static float rotationSpeed = 2.0f;


    public TornadoEffect(EffectType typeIn, int liquidColorIn) {
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

        if (MathHelper.abs(pitch + randPitch) > maxRotation) {
            pitch += -randPitch;
        } else {
            pitch += randPitch;
        }

        if (MathHelper.abs(yaw + randYaw) > maxRotation) {
            yaw += -randYaw;
        } else {
            yaw += randYaw;
        }

        entity.xRot += pitch;
        entity.yRot += yaw;

        entity.getPersistentData().putFloat(TAG_PITCH, pitch);
        entity.getPersistentData().putFloat(TAG_YAW, yaw);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
