package com.greatorator.tolkienmobs.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class BreezeUtility {
    private static final Logger LOGGER = LogManager.getLogger();
    public static float windX;
    public static float windZ;
    protected static Random rng = new Random();
    protected static float TAU = (float) (2 * Math.PI);
    protected static SmoothNoise velocityNoise;
    protected static SmoothNoise directionTrendNoise;
    protected static SmoothNoise directionNoise;
    protected static boolean wasRaining;
    protected static boolean wasThundering;
    protected static State state;
    protected static State originalState;
    protected static int stateDuration; // ticks

    public static void init() {

        wasRaining = false;
        wasThundering = false;
        state = State.CALM;
        stateDuration = 0;

        windX = windZ = 0;

        velocityNoise = new SmoothNoise(2 * 20, 0, (old) -> {
            return state.velocityDistribution.sample();
        });
        directionTrendNoise = new SmoothNoise(30 * 60 * 20, rng.nextFloat() * TAU, (old) -> {
            return rng.nextFloat() * TAU;
        });
        directionNoise = new SmoothNoise(10 * 20, 0, (old) -> {
            return (2f * rng.nextFloat() - 1f) * TAU / 8f;
        });
    }

    protected static void tickState(Level world) {
        --stateDuration;

//        ResourceLocation dimension = world.dimension().location();
//        if (dimension.toString() == TTMConfig.dimensionArray) {
//            // override state to calm when there is no wind
//            originalState = state;
//            state = State.CALM;
//            return;
//        }

        // restore overridden state
        if (originalState != null) {
            state = originalState;
            originalState = null;
        }

        boolean isRaining = world.getLevelData().isRaining();
        boolean isThundering = world.isThundering();
        boolean weatherChanged = wasRaining != isRaining || wasThundering != isThundering;

        if (weatherChanged || stateDuration <= 0) {
            if (isThundering) {
                state = State.STORMY;
            } else {
                // windy and stormy when raining, calm and windy otherwise
                int index = rng.nextInt(2);
                state = State.values()[(isRaining ? index + 1 : index)];
            }

            stateDuration = 6 * 60 * 20; // change state every 6 minutes
            LOGGER.debug("new wind state {}", state);
        }

        wasRaining = isRaining;
        wasThundering = isThundering;
    }

    public static void tick(Level world) {
        tickState(world);

        velocityNoise.tick();
        directionTrendNoise.tick();
        directionNoise.tick();

        float strength = velocityNoise.getNoise();
        float direction = directionTrendNoise.getLerp() + directionNoise.getNoise();

        // calculate wind velocity (in blocks / tick)
        windX = strength * Mth.cos(direction);
        windZ = strength * Mth.sin(direction);
    }

    protected enum State {
        CALM(0.05f, 0.05f, 0.2f),
        WINDY(0.05f, 0.3f, 0.7f),
        STORMY(0.05f, 0.6f, 1.1f);

        public TriangularDistributionUtility velocityDistribution;

        State(float minSpeed, float likelySpeed, float maxSpeed) {
            this.velocityDistribution = new TriangularDistributionUtility(minSpeed, maxSpeed, likelySpeed, rng);
        }
    }
}