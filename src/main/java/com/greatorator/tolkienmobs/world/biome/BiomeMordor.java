package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeMordor {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBiomeMordor(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        TTMDefaultBiomeFeatures.mordorSpawns(spawnInf);

        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DefaultBiomeFeatures.addFossilDecoration(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        TTMDefaultBiomeFeatures.addMorgulironOres(builder);
        TTMDefaultBiomeFeatures.addDeadTrees(builder);
        TTMDefaultBiomeFeatures.addLavaLakes(builder);
        TTMDefaultBiomeFeatures.addRockPiles(builder);

        LOGGER.info("Beware the all-seeing eye...");
        return (new Biome.Builder())
                .precipitation(Biome.RainType.NONE)
                .biomeCategory(Biome.Category.DESERT)
                .depth(0.125F)
                .scale(0.5F)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects((new BiomeAmbience.Builder())
                        .skyColor(getSkyColorWithTemperatureModifier(2.0F))
                        .foliageColorOverride(6908265)
                        .fogColor(0)
                        .waterColor(0)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(builder.build())
                .build();
    }
}