package com.greatorator.tolkienmobs.world.biome;

public class BiomeMordor {
//
//    private static int getSkyColorWithTemperatureModifier(float temp) {
//        float lvt_1_1_ = temp / 3.0F;
//        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
//        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
//    }
//
//    public static Biome makeBiomeMordor(float depth, float scale, float temp, float rain, Biome.RainType rainType, Biome.Category category) {
//        // Spawn Settings
//        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
//        TTMDefaultBiomeFeatures.mordorSpawns(spawnInf);
//
//        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder())
//                .surfaceBuilder(TTMConfiguredSurfaceBuilder.MORDOR_SURFACE);
//
//        DefaultBiomeFeatures.addFossilDecoration(builder);
//        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//
//        TTMDefaultBiomeFeatures.addMorgulironOres(builder);
//        TTMDefaultBiomeFeatures.addDeadTrees(builder);
//        TTMDefaultBiomeFeatures.addLavaLakes(builder);
//        TTMDefaultBiomeFeatures.addRockPiles(builder);
//
//        LOGGER.info("Beware the all-seeing eye...");
//        return (new Biome.Builder())
//                .precipitation(rainType)
//                .biomeCategory(category)
//                .depth(depth)
//                .scale(scale)
//                .temperature(temp)
//                .downfall(rain)
//                .specialEffects((new BiomeAmbience.Builder())
//                        .waterColor(0)
//                        .waterFogColor(329011)
//                        .fogColor(12638463)
//                        .grassColorOverride(2329)
//                        .skyColor(getSkyColorWithTemperatureModifier(2.0F))
//                        .foliageColorOverride(2329)
//                        .ambientLoopSound(SoundGenerator.khazaddum.get())
//                        .ambientParticle(new ParticleEffectAmbience(ParticleTypes.ASH, 0.00625F))
//                        .build())
//                .mobSpawnSettings(spawnInf.build())
//                .generationSettings(builder.build())
//                .build();
//    }
}