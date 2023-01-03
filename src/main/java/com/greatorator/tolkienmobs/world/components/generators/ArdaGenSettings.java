package com.greatorator.tolkienmobs.world.components.generators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ArdaGenSettings {
    public static final Codec<ArdaGenSettings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.LONG.fieldOf("seed").forGetter(ArdaGenSettings::getSeed),
                    Codec.STRING.fieldOf("randomized").forGetter(ArdaGenSettings::getRandomized)
            ).apply(instance, ArdaGenSettings::new));

    private final long seed;
    private final String randomized;

    public ArdaGenSettings(long seed, String randomized) {
        this.seed = seed;
        this.randomized = randomized;
    }

    public long getSeed() {
        return seed;
    }

    public String getRandomized() {
        return randomized;
    }
}
