package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.world.gen.feature.TTMBiomeFeatures;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class BiomeMirkwood {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }


    public static Biome makeBiomeMirkwood(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        TTMBiomeFeatures.mirkwoodSpawns(spawnInf);
        spawnInf.setPlayerCanSpawn();

        // Biome Settings
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        TTMBiomeFeatures.addMirkwoodTrees(builder);
        TTMBiomeFeatures.addMirkwoodFlowers(builder);
        TTMBiomeFeatures.addWaterLakes(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addFerns(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);

        // Let's set the mood
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(0.2F)
                .scale(0.2F)
                .temperature(0.25F)
                .downfall(0.8F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(0)
                        .grassColorOverride(738353)
                        .waterFogColor(5541815)
                        .fogColor(5988193)
                        .foliageColorOverride(738353)
                        .skyColor(getSkyColorWithTemperatureModifier(0.6F))
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(builder.build())
                .build();
    }
}