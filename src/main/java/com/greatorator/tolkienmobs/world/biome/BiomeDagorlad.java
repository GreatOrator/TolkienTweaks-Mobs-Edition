package com.greatorator.tolkienmobs.world.biome;

public class BiomeDagorlad {
//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//    public static Biome makeBiomeDagorlad(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        spawnInf.setPlayerCanSpawn();
//        TTMDefaultBiomeFeatures.DagorladSpawns(spawnInf);
//        TTMDefaultBiomeFeatures.AmbientSpawns(spawnInf);
//        spawnInf.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 1, 1, 1));
//
//        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
//
//        DefaultBiomeFeatures.addFossilDecoration(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addBadlandExtraVegetation(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
//        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
//
//        TTMDefaultBiomeFeatures.addDesolation(biomegenerationsettings$builder);
//        TTMDefaultBiomeFeatures.addRockPiles(biomegenerationsettings$builder);
//        LOGGER.info("Where have the Entwives gone...");
//        return (new Biome.Builder())
//                .precipitation(rainType)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(rain)
//                .specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(14596231)
//                        .waterFogColor(2302743)
//                        .fogColor(12638463)
//                        .grassColorOverride(14596231)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
//                        .foliageColorOverride(14596231)
//                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD)
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(biomegenerationsettings$builder.build())
//                .build();
//    }
//
}