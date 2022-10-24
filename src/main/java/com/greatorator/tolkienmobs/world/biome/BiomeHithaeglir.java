package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeHithaeglir {
    private static int getSkyColorWithTemperatureModifier(float temp) {
        float lvt_1_1_ = temp / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBiomeHithaeglir(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        spawnInf.setPlayerCanSpawn();
        TTMDefaultBiomeFeatures.HithaeglirSpawns(spawnInf);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.MOUNTAIN);
        TTMDefaultBiomeFeatures.addMithrilOres(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addMountainEdgeTrees(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultGrass(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addExtraEmeralds(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addInfestedStone(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addExtraEmeralds(biomegenerationsettings$builder);

        LOGGER.info("Far over the misty mountains cold...");
        return (new Biome.Builder())
                .precipitation(rainType)
                .biomeCategory(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
                .downfall(rain)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(10518688)
                        .grassColorOverride(2292007)
                        .skyColor(getSkyColorWithTemperatureModifier(0.2F))
                        .foliageColorOverride(2292007)
                        .ambientLoopSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }
}