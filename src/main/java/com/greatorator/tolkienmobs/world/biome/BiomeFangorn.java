package com.greatorator.tolkienmobs.world.biome;

public class BiomeFangorn {
//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//
//    public static Biome makeBiomeFangorn(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        TTMDefaultBiomeFeatures.fangornSpawns(spawnInf);
//        spawnInf.setPlayerCanSpawn();
//
//        // Biome Settings
//        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(TTMConfiguredSurfaceBuilder.FANGORN_SURFACE);
//
//        DefaultBiomeFeatures.addFossilDecoration(builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(builder);
//        DefaultBiomeFeatures.addDefaultSprings(builder);
//        DefaultBiomeFeatures.addSurfaceFreezing(builder);
//        DefaultBiomeFeatures.addForestGrass(builder);
//        DefaultBiomeFeatures.addFerns(builder);
//
//        TTMDefaultBiomeFeatures.addFangornLeafPiles(builder);
//        TTMDefaultBiomeFeatures.addFangornTrees(builder);
//        TTMDefaultBiomeFeatures.addWaterLakes(builder);
//        TTMDefaultBiomeFeatures.addLorinandFlowers(builder);
//        LOGGER.info("Last remnant of the great forests of Eriador...");
//        // Let's set the mood
//        return (new Biome.Builder())
//                .precipitation(rainType)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(rain)
//                .specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(54011)
//                        .grassColorOverride(5156174)
//                        .waterFogColor(5541815)
//                        .fogColor(5988193)
//                        .foliageColorOverride(5156174)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
//                        .ambientParticle(new ParticleEffectAmbience(TolkienParticles.falling_leaves, 0.00625F))
//                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(builder.build())
//                .build();
//    }
//
}