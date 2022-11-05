package com.greatorator.tolkienmobs.world.biome;

public class BiomeBarrowDowns {

//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//    public static Biome makeBiomeBarrowDowns(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        TTMDefaultBiomeFeatures.barrowDownsSpawns(spawnInf);
//        spawnInf.setPlayerCanSpawn();
//
//        // Biome Settings
//        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
//        TTMDefaultBiomeFeatures.addRockPiles(builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//        DefaultBiomeFeatures.addDefaultSoftDisks(builder);
//        DefaultBiomeFeatures.addForestGrass(builder);
//        DefaultBiomeFeatures.addFerns(builder);
//        DefaultBiomeFeatures.addDefaultMushrooms(builder);
//
//        TTMDefaultBiomeFeatures.addDeadTrees(builder);
//        TTMDefaultBiomeFeatures.addWaterLakes(builder);
//        LOGGER.info("Laying the dead to rest...");
//        // Let's set the mood
//        return (new Biome.Builder())
//                .precipitation(rainType)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(rain)
//                .specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(808080)
//                        .grassColorOverride(14481884)
//                        .waterFogColor(5541815)
//                        .fogColor(11119017)
//                        .foliageColorOverride(14481884)
//                        .skyColor(getSkyColorWithTemperatureModifier(0.6F))
//                        .ambientParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.118093334F))
//                        .ambientLoopSound(SoundGenerator.paths_of_the_dead.get())
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(builder.build())
//                .build();
//    }
//
}