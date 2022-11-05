package com.greatorator.tolkienmobs.world.biome;

public class BiomeGladden {
//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//    public static Biome makeBiomeGladden(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        spawnInf.setPlayerCanSpawn();
//        TTMDefaultBiomeFeatures.gladdenSpawns(spawnInf);
//        TTMDefaultBiomeFeatures.passiveAnimals(spawnInf);
//        TTMDefaultBiomeFeatures.AmbientSpawns(spawnInf);
//
//        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
//
//        TTMDefaultBiomeFeatures.addWaterLakes(biomegenerationsettings$builder);
//
//        DefaultBiomeFeatures.addBirchTrees(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addFossilDecoration(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addExtraEmeralds(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addBerryBushes(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addForestGrass(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addFerns(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultFlowers(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addPlainVegetation(biomegenerationsettings$builder);
//
//        LOGGER.info("Realm of man...");
//        return (new Biome.Builder())
//                .precipitation(rainType)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(rain)
//                .specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(4159204)
//                        .waterFogColor(329011)
//                        .fogColor(10518688)
//                        .grassColorOverride(14596231)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
//                        .foliageColorOverride(14596231)
//                        .ambientParticle(new ParticleEffectAmbience(TolkienParticles.falling_leaves, 0.00625F))
//                        .ambientLoopSound(SoundGenerator.medieval_city.get())
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(biomegenerationsettings$builder.build())
//                .build();
//    }
//
}