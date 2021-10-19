package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeLorinand {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }


    public static Biome makeBiomeLorinand(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        TTMDefaultBiomeFeatures.passiveAnimals(spawnInf);
        TTMDefaultBiomeFeatures.elvishSpawns(spawnInf);
        spawnInf.setPlayerCanSpawn();

        // Biome Settings
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        TTMDefaultBiomeFeatures.addWaterLakes(builder);
        TTMDefaultBiomeFeatures.addLorinandFlowers(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        TTMDefaultBiomeFeatures.addAmmoliteOres(builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        TTMDefaultBiomeFeatures.addMallornLeafPiles(builder);
        DefaultBiomeFeatures.addFerns(builder);
        TTMDefaultBiomeFeatures.addMallornTrees(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        LOGGER.info("Making the land beautiful...");
        // Let's set the mood
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(54011)
                        .grassColorOverride(7006317)
                        .waterFogColor(5541815)
                        .fogColor(14412287)
                        .foliageColorOverride(8640564)
                        .skyColor(getSkyColorWithTemperatureModifier(0.6F))
                        .ambientLoopSound(SoundGenerator.thelightoflothlorien.get())
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(builder.build())
                .build();
    }
}