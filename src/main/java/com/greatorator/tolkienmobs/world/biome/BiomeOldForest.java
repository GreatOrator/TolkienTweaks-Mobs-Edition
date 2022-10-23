package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeOldForest {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }


    public static Biome makeBiomeOldForest(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        TTMDefaultBiomeFeatures.oldForestSpawns(spawnInf);
        TTMDefaultBiomeFeatures.AmbientSpawns(spawnInf);
        spawnInf.setPlayerCanSpawn();

        // Biome Settings
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DefaultBiomeFeatures.addFossilDecoration(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
        DefaultBiomeFeatures.addForestGrass(builder);
        DefaultBiomeFeatures.addFerns(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);

        TTMDefaultBiomeFeatures.addOldForestTrees(builder);
        TTMDefaultBiomeFeatures.addWaterLakes(builder);
        LOGGER.info("Realm of Tom Bombadil & the Withywindle...");
        // Let's set the mood
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.FOREST)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.7F)
                .downfall(0.8F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(54011)
                        .grassColorOverride(5163086)
                        .waterFogColor(5541815)
                        .fogColor(5988193)
                        .foliageColorOverride(5163086)
                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
                        .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(builder.build())
                .build();
    }
}