package com.greatorator.tolkienmobs.world;

//
//public class ArdaWorldType extends ForgeWorldType {
//    public ArdaWorldType() {
//        super(null);
//    }
//
//    @Override
//    public ChunkGenerator createChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed, String generatorSettings) {
//        return new NoiseChunkGenerator(new TTMBiomeProvider(seed,false, true, biomeRegistry), seed,
//                () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.OVERWORLD));
//    }
//
//    @Override
//    public DimensionGeneratorSettings createSettings(DynamicRegistries dynamicRegistries, long seed, boolean generateStructures, boolean generateLoot, String generatorSettings) {
//        return new DimensionGeneratorSettings(seed, true, false,
//                DimensionGeneratorSettings.withOverworld(dynamicRegistries.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY),
//                        new SimpleRegistry<Dimension>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental()),
//                        createChunkGenerator(dynamicRegistries.registryOrThrow(Registry.BIOME_REGISTRY),
//                                dynamicRegistries.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), seed, "")));
//    }
//}
