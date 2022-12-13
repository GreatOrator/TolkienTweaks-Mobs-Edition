package com.greatorator.tolkienmobs.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class MathUtility {
    private MathUtility(){}

    public static final float PI = (float) Math.PI;

    public static int getRandom(int max){
        return (int) (Math.random()*max);
    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

    public static double nextDouble(Random rand) {
        return 2 * rand.nextDouble() - 1;
    }

    public static int nextInt(Random random, int minimum, int maximum) {
        return minimum >= maximum ? minimum : random.nextInt(maximum - minimum + 1) + minimum;
    }

    public static float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }

    public static Vec3 getYawVec(float yaw, double xOffset, double zOffset) {
        return new Vec3(xOffset, 0, zOffset).yRot(-yaw * (PI / 180f));
    }

    public static double getAngle(double sourceX, double sourceZ, double targetX, double targetZ) {
        return Mth.atan2(targetZ - sourceZ, targetX - sourceX) * 180 / Math.PI + 180;
    }

    public static double getAngle(Entity source, Entity target) {
        return Mth.atan2(target.getZ() - source.getZ(), target.getX() - source.getX()) * (180 / Math.PI) + 180;
    }

    public static float linTerp(float a, float b, float x) {
        if (x <= 0) return a;
        if (x >= 1) return b;
        return a + x * (b - a);
    }

    @Nullable
    public static EntityHitResult clipEntities(Entity shooter, double range, @Nullable Predicate<Entity> filter) {
        return clipEntities(shooter, range, 0, filter);
    }

    @Nullable
    public static EntityHitResult clipEntities(Entity shooter, double range, double hitRadius, @Nullable Predicate<Entity> filter) {
        Vec3 eyes = shooter.getEyePosition(1f);
        Vec3 end = eyes.add(shooter.getLookAngle().multiply(range, range, range));

        Entity result = null;
        double distance = range * range;
        for (Entity entity : shooter.level.getEntities(shooter, shooter.getBoundingBox().inflate(range), filter))
        {
            Optional<Vec3> opt = entity.getBoundingBox().inflate(hitRadius).clip(eyes, end);
            if (opt.isPresent())
            {
                double dist = eyes.distanceToSqr(opt.get());
                if (dist < distance)
                {
                    result = entity;
                    distance = dist;
                }
            }
        }
        return result == null? null : new EntityHitResult(result);
    }
}