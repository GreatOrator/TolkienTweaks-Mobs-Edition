package com.greatorator.tolkienmobs.world.gen.feature.config;

import com.greatorator.tolkienmobs.datagen.StructureGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TTMStructureConfig {
    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_TTMHOUSE_ELVEN = StructureGenerator.TTMHOUSE_ELVEN.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMBARROW = StructureGenerator.TTMBARROW.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMRUIN_LARGE = StructureGenerator.TTMRUIN_LARGE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMRUIN_SMALL = StructureGenerator.TTMRUIN_SMALL.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMHOBBIT_HOUSE = StructureGenerator.TTMHOBBIT_HOUSE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMSWAMP_HAG_HUT = StructureGenerator.TTMSWAMP_HAG_HUT.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMSPIDER_TREE = StructureGenerator.TTMSPIDER_TREE.get().configured(IFeatureConfig.NONE);
    public static StructureFeature<?, ?> CONFIGURED_TTMWARG_PIT = StructureGenerator.TTMWARG_PIT.get().configured(IFeatureConfig.NONE);

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhouse_elven"), CONFIGURED_TTMHOUSE_ELVEN);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmbarrow"), CONFIGURED_TTMBARROW);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmruin_large"), CONFIGURED_TTMRUIN_LARGE);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmruin_small"), CONFIGURED_TTMRUIN_SMALL);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmhobbit_house"), CONFIGURED_TTMHOBBIT_HOUSE);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmswamp_hag_hut"), CONFIGURED_TTMSWAMP_HAG_HUT);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmspidertree"), CONFIGURED_TTMSPIDER_TREE);
        Registry.register(registry, new ResourceLocation(MODID, "configured_ttmwargpit"), CONFIGURED_TTMWARG_PIT);

        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOUSE_ELVEN.get(), CONFIGURED_TTMHOUSE_ELVEN);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMBARROW.get(), CONFIGURED_TTMBARROW);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMRUIN_LARGE.get(), CONFIGURED_TTMRUIN_LARGE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMRUIN_SMALL.get(), CONFIGURED_TTMRUIN_SMALL);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMHOBBIT_HOUSE.get(), CONFIGURED_TTMHOBBIT_HOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMSWAMP_HAG_HUT.get(), CONFIGURED_TTMSWAMP_HAG_HUT);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMSPIDER_TREE.get(), CONFIGURED_TTMSPIDER_TREE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(StructureGenerator.TTMWARG_PIT.get(), CONFIGURED_TTMWARG_PIT);
    }
}
