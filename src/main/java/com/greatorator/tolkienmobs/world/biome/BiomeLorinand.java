package com.greatorator.tolkienmobs.world.biome;

public class BiomeLorinand {
//
//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//
//    public static Biome makeBiomeLorinand(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        TTMDefaultBiomeFeatures.passiveAnimals(spawnInf);
//        TTMDefaultBiomeFeatures.elvishSpawns(spawnInf);
//        spawnInf.setPlayerCanSpawn();
//
//        // Biome Settings
//        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(builder);
//        DefaultBiomeFeatures.addDefaultSoftDisks(builder);
//        DefaultBiomeFeatures.addForestGrass(builder);
//        DefaultBiomeFeatures.addFerns(builder);
//
//        TTMDefaultBiomeFeatures.addMallornLeafPiles(builder);
//        TTMDefaultBiomeFeatures.addAmmoliteOres(builder);
//        TTMDefaultBiomeFeatures.addMallornTrees(builder);
//        TTMDefaultBiomeFeatures.addWaterLakes(builder);
//        TTMDefaultBiomeFeatures.addLorinandFlowers(builder);
//        LOGGER.info("Making the land beautiful...");
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
//                        .grassColorOverride(7006317)
//                        .waterFogColor(5541815)
//                        .fogColor(14412287)
//                        .foliageColorOverride(15591305)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.6F))
//                        .ambientParticle(new ParticleEffectAmbience(TolkienParticles.falling_leaves, 0.00625F))
//                        .ambientLoopSound(SoundGenerator.thelightoflothlorien.get())
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(builder.build())
//                .build();
//    }
}