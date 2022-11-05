package com.greatorator.tolkienmobs.world.gen.feature.config;

//
//public class TTMStructureConfig {
//    /**
//     * Static instance of our structure so we can reference it and add it to biomes easily.
//     */
//    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_ELVEN = StructureGenerator.TTMHOUSE_ELVEN.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_HOBBIT = StructureGenerator.TTMHOUSE_HOBBIT.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_HUMAN = StructureGenerator.TTMHOUSE_HUMAN.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_DWARF = StructureGenerator.TTMHOUSE_DWARF.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_DESERT = StructureGenerator.TTMHOUSE_DESERT.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMBARROW = StructureGenerator.TTMBARROW.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMRUIN_LARGE = StructureGenerator.TTMRUIN_LARGE.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMRUIN_SMALL = StructureGenerator.TTMRUIN_SMALL.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMSWAMP_HAG_HUT = StructureGenerator.TTMSWAMP_HAG_HUT.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMSPIDER_TREE = StructureGenerator.TTMSPIDER_TREE.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMWARG_PIT = StructureGenerator.TTMWARG_PIT.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMMINOTAUR_MAZE = StructureGenerator.TTMMINOTAUR_MAZE.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMSPIDER_CAVE = StructureGenerator.TTMSPIDER_CAVE.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMGOLLUM_CAVE = StructureGenerator.TTMGOLLUM_CAVE.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMDARK_TOWER = StructureGenerator.TTMDARK_TOWER.get().configured(IFeatureConfig.NONE);
//    public static StructureFeature<?, ?> CONFIGURED_TTMINN_DESERT = StructureGenerator.TTMINN_DESERT.get().configured(IFeatureConfig.NONE);
//
//    /**
//     * Registers the configured structure which is what gets added to the biomes.
//     * Noticed we are not using a forge registry because there is none for configured structures.
//     *
//     * We can register configured structures at any time before a world is clicked on and made.
//     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
//     */
//    public static void registerConfiguredStructures() {
//        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_elven"), CONFIGURED_TTMHOUSE_ELVEN);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_hobbit"), CONFIGURED_TTMHOUSE_HOBBIT);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_dwarf"), CONFIGURED_TTMHOUSE_DWARF);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_human"), CONFIGURED_TTMHOUSE_HUMAN);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_desert"), CONFIGURED_TTMHOUSE_DESERT);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmbarrow"), CONFIGURED_TTMBARROW);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmruin_large"), CONFIGURED_TTMRUIN_LARGE);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmruin_small"), CONFIGURED_TTMRUIN_SMALL);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmswamp_hag_hut"), CONFIGURED_TTMSWAMP_HAG_HUT);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmspidertree"), CONFIGURED_TTMSPIDER_TREE);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmwargpit"), CONFIGURED_TTMWARG_PIT);
//        Registry.register(registry, new ResourceLocation(MODID, "maze/configured_ttmminotaurmaze"), CONFIGURED_TTMMINOTAUR_MAZE);
//        Registry.register(registry, new ResourceLocation(MODID, "cave/configured_ttmspider_cave_main"), CONFIGURED_TTMSPIDER_CAVE);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmgollum_cave"), CONFIGURED_TTMGOLLUM_CAVE);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmdark_tower"), CONFIGURED_TTMDARK_TOWER);
//        Registry.register(registry, new ResourceLocation(MODID, "configured_ttminn_desert"), CONFIGURED_TTMINN_DESERT);
//
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_ELVEN.get(), CONFIGURED_TTMHOUSE_ELVEN);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_HOBBIT.get(), CONFIGURED_TTMHOUSE_HOBBIT);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_DWARF.get(), CONFIGURED_TTMHOUSE_DWARF);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_HUMAN.get(), CONFIGURED_TTMHOUSE_HUMAN);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_DESERT.get(), CONFIGURED_TTMHOUSE_DESERT);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMBARROW.get(), CONFIGURED_TTMBARROW);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMRUIN_LARGE.get(), CONFIGURED_TTMRUIN_LARGE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMRUIN_SMALL.get(), CONFIGURED_TTMRUIN_SMALL);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMSWAMP_HAG_HUT.get(), CONFIGURED_TTMSWAMP_HAG_HUT);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMSPIDER_TREE.get(), CONFIGURED_TTMSPIDER_TREE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMWARG_PIT.get(), CONFIGURED_TTMWARG_PIT);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMMINOTAUR_MAZE.get(), CONFIGURED_TTMMINOTAUR_MAZE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMSPIDER_CAVE.get(), CONFIGURED_TTMSPIDER_CAVE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMGOLLUM_CAVE.get(), CONFIGURED_TTMGOLLUM_CAVE);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMDARK_TOWER.get(), CONFIGURED_TTMDARK_TOWER);
//        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMINN_DESERT.get(), CONFIGURED_TTMINN_DESERT);
//    }
//}
