//package com.greatorator.tolkienmobs.item.potiontypes;
//
//import com.greatorator.tolkienmobs.handler.TTMPotion;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.util.math.MathHelper;
//
//public class PotionTTMTornado extends TTMPotion {
//    public static final String NAME = "elemental_tornado";
//    public static final String TAG_PITCH = "elemental_tornado_pitch";
//    public static final String TAG_YAW = "elemental_tornado_yaw";
//    public static PotionTTMTornado instance = null;
//
//    public static float rotationSpeed = 2.0f;
//
//    public PotionTTMTornado() {
//        super(NAME, true, 11914084, 6);
//        instance = this;
//    }
//
//    @Override
//    public void performEffect(LivingEntity entity, int amplifier) {
//        float maxRotation = 16;
//        float maxRand = rotationSpeed * (amplifier+1);
//
//        float pitch = entity.getEntityData().getFloat(TAG_PITCH);
//        float yaw = entity.getEntityData().getFloat(TAG_YAW);
//
//        float randPitch = (entity.getRNG().nextFloat() * maxRand * 2) - maxRand;
//        float randYaw = (entity.getRNG().nextFloat() * maxRand * 2) - maxRand;
//
//        if(MathHelper.abs(pitch + randPitch) > maxRotation) {
//            pitch += -randPitch;
//        }
//        else {
//            pitch += randPitch;
//        }
//
//        if(MathHelper.abs(yaw + randYaw) > maxRotation) {
//            yaw += -randYaw;
//        }
//        else {
//            yaw += randYaw;
//        }
//
//        entity.rotationPitch += pitch;
//        entity.rotationYaw += yaw;
//
//        entity.getEntityData().setFloat(TAG_PITCH, pitch);
//        entity.getEntityData().setFloat(TAG_YAW, yaw);
//    }
//}