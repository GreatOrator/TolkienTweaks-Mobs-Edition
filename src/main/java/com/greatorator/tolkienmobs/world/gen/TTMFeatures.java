package com.greatorator.tolkienmobs.world.gen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.feature.TTMStoneSpikeFeature;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static net.minecraft.block.LeavesBlock.PERSISTENT;

public final class TTMFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MIRKWOOD = registerWorldFeature(TolkienMobs.prefix("mirkwood"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.MIRKWOOD));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MALLORN = registerWorldFeature(TolkienMobs.prefix("mallorn"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.MALLORN));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> CULUMALDA = registerWorldFeature(TolkienMobs.prefix("culumalda"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.CULUMALDA));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> LEBETHRON = registerWorldFeature(TolkienMobs.prefix("lebethron"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.LEBETHRON));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> DEADTREE = registerWorldFeature(TolkienMobs.prefix("deadtree"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.DEADTREE));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_BLOOM_DECAY = registerWorldFeature(TolkienMobs.prefix("bloomdecay"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.MUSHROOM_BLOOM_DECAY));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_DECAY_BLOOM = registerWorldFeature(TolkienMobs.prefix("decaybloom"), Feature.TREE.configured(TTMTreeFeatureConfig.TreeConfigurations.MUSHROOM_DECAY_BLOOM));

    // Biome placement
    public static ConfiguredFeature<?, ? extends Feature<?>> STONE_SPIKE;
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_MALLORN_LEAFPILES = register("patch_mallorn_leafpiles", Feature.RANDOM_PATCH.configured(TTMConfigs.MALLORN_LEAFPILES_CONFIG));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_MIRKWOOD_LEAFPILES = register("patch_mirkwood_leafpiles", Feature.RANDOM_PATCH.configured(TTMConfigs.MIRKWOOD_LEAFPILES_CONFIG));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_CULUMALDA_LEAFPILES = register("patch_culumalda_leafpiles", Feature.RANDOM_PATCH.configured(TTMConfigs.CULUMALDA_LEAFPILES_CONFIG));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_LEBETHRON_LEAFPILES = register("patch_lebethron_leafpiles", Feature.RANDOM_PATCH.configured(TTMConfigs.LEBETHRON_LEAFPILES_CONFIG));
    public static final ConfiguredFeature<?, ? extends Feature<?>> TREES_LORINAND = register("trees_lorinand", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CULUMALDA.weighted(0.1F), MALLORN.weighted(0.5F), MALLORN.weighted(0.33333334F)), MALLORN)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));
    public static final ConfiguredFeature<?, ? extends Feature<?>> TREES_MIRKWOOD = register("trees_mirkwood", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(DEADTREE.weighted(0.1F), MIRKWOOD.weighted(0.5F), MIRKWOOD.weighted(0.33333334F)), MIRKWOOD)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));
    //        public static final ConfiguredFeature<?, ? extends Feature<?>> TREES_MIRKWOOD = register("trees_mirkwood", MIRKWOOD.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
    public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_AMMOLITE = register("ore_ammolite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.AMMOLITE_ORE, 8)).range(16).squared());
    public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_MITHRIL = register("ore_mithril", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.MITHRIL_ORE, 8)).range(16).squared());
    public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_MORGULIRON = register("ore_morguliron", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.MORGULIRON_ORE, 8)).range(16).squared());
    public static final ConfiguredFeature<?, ? extends Feature<?>> FLOWER_LORINAND = register("flower_lorinand", Feature.FLOWER.configured(TTMConfigs.DEFAULT_LORINAND_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2));
    public static final ConfiguredFeature<?, ? extends Feature<?>> FLOWER_MIRKWOOD = register("flower_mirkwood", Feature.FLOWER.configured(TTMConfigs.DEFAULT_MIRKWOOD_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2));
    public static final ConfiguredFeature<?, ? extends Feature<?>> FLOWER_MARSH = register("flower_marsh", Feature.FLOWER.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.FLOWER_SWAMPMILKWEED), SimpleBlockPlacer.INSTANCE)).tries(64).build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_MALLORN_LEAFPILES_SPARSE = register("patch_mallorn_leafpiles_sparse", PATCH_MALLORN_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_MIRKWOOD_LEAFPILES_SPARSE = register("patch_mirkwood_leafpiles_sparse", PATCH_MIRKWOOD_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_CULUMALDA_LEAFPILES_SPARSE = register("patch_culumalda_leafpiles_sparse", PATCH_CULUMALDA_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ? extends Feature<?>> PATCH_LEBETHRON_LEAFPILES_SPARSE = register("patch_lebethron_leafpiles_sparse", PATCH_LEBETHRON_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static ConfiguredFeature<?, ? extends Feature<?>> BLEAK_LAND;

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, TolkienMobs.MODID + ":" + name, feature);
    }

    private static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerWorldFeature(ResourceLocation rl, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, rl, feature);
    }

    public static final class TTMConfigs {
        public static final BlockClusterFeatureConfig DEFAULT_LORINAND_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(States.FLOWER_ALFIRIN, 2).add(States.DANDELION, 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
        public static final BlockClusterFeatureConfig DEFAULT_MIRKWOOD_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(States.FLOWER_MIRKWOOD, 2).add(States.DEAD_BUSH, 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
        public static final BlockClusterFeatureConfig MALLORN_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_MALLORN), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
        public static final BlockClusterFeatureConfig MIRKWOOD_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_MIRKWOOD), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
        public static final BlockClusterFeatureConfig CULUMALDA_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_CULUMALDA), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
        public static final BlockClusterFeatureConfig LEBETHRON_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_LEBETHRON), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
    }

    public static final class States {
        public static final BlockState MALLORN_LOG = TTMContent.LOG_MALLORN.get().defaultBlockState();
        public static final BlockState MALLORN_LEAVES = TTMContent.LEAVES_MALLORN.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        public static final BlockState MIRKWOOD_LOG = TTMContent.LOG_MIRKWOOD.get().defaultBlockState();
        public static final BlockState MIRKWOOD_LEAVES = TTMContent.LEAVES_MIRKWOOD.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        public static final BlockState CULUMALDA_LOG = TTMContent.LOG_CULUMALDA.get().defaultBlockState();
        public static final BlockState CULUMALDA_LEAVES = TTMContent.LEAVES_CULUMALDA.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        public static final BlockState LEBETHRON_LOG = TTMContent.LOG_LEBETHRON.get().defaultBlockState();
        public static final BlockState LEBETHRON_LEAVES = TTMContent.LEAVES_LEBETHRON.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        public static final BlockState DEADWOOD_LOG = TTMContent.LOG_DEADWOOD.get().defaultBlockState();
        public static final BlockState CAP_DECAY_BLOOM = TTMContent.BLOCK_DECAY_BLOOM.get().defaultBlockState();
        public static final BlockState CAP_BLOOM_DECAY = TTMContent.BLOCK_BLOOM_DECAY.get().defaultBlockState();
        public static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.defaultBlockState();
        public static final BlockState AIR = Blocks.AIR.defaultBlockState();
        public static final BlockState AMMOLITE_ORE = TTMContent.ORE_AMMOLITE.get().defaultBlockState();
        public static final BlockState MITHRIL_ORE = TTMContent.ORE_MITHRIL.get().defaultBlockState();
        public static final BlockState MORGULIRON_ORE = TTMContent.ORE_MORGULIRON.get().defaultBlockState();
        public static final BlockState FLOWER_ALFIRIN = TTMContent.FLOWER_ALFIRIN.get().defaultBlockState();
        public static final BlockState FLOWER_MIRKWOOD = TTMContent.FLOWER_MIRKWOOD.get().defaultBlockState();
        public static final BlockState FLOWER_SWAMPMILKWEED = TTMContent.FLOWER_SWAMPMILKWEED.get().defaultBlockState();
        public static final BlockState LEAFPILE_MIRKWOOD = TTMContent.LEAFPILE_MIRKWOOD.get().defaultBlockState();
        public static final BlockState LEAFPILE_MALLORN = TTMContent.LEAFPILE_MALLORN.get().defaultBlockState();
        public static final BlockState LEAFPILE_CULUMALDA = TTMContent.LEAFPILE_CULUMALDA.get().defaultBlockState();
        public static final BlockState LEAFPILE_LEBETHRON = TTMContent.LEAFPILE_LEBETHRON.get().defaultBlockState();
        public static final BlockState DANDELION = Blocks.DANDELION.defaultBlockState();
        public static final BlockState DEAD_BUSH = Blocks.DEAD_BUSH.defaultBlockState();
        public static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();

    }

    public static class FeaturesStore { // Every better name is taken xD
        public static Feature<NoFeatureConfig> STONE_SPIKE = new TTMStoneSpikeFeature(NoFeatureConfig.CODEC);
    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(FeaturesStore.STONE_SPIKE.setRegistryName("stone_spike"));


        //Register Dependents.
        STONE_SPIKE = register("stone_spike", FeaturesStore.STONE_SPIKE.configured(IFeatureConfig.NONE).count(3));
        BLEAK_LAND = register("bleak_land", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(DEADTREE.weighted(0.1F), STONE_SPIKE.weighted(0.1F)), STONE_SPIKE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));

    }
}
