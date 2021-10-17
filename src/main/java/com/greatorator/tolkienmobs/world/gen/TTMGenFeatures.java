package com.greatorator.tolkienmobs.world.gen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.feature.TTMFeatures;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMBranchesConfig;
import com.greatorator.tolkienmobs.world.gen.placers.TTMBranchingTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.TTMSpheroidFoliagePlacer;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunkplacer.*;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TTMGenFeatures {
    private static final List<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = new ArrayList<>();

    private final static int LEAF_SHAG_FACTOR = 24;
    private static final int canopyDistancing = 5;

    public static final TrunkPlacerType<TTMBranchingTrunkPlacer> TRUNK_BRANCHING = registerTrunk(TolkienMobs.prefix("branching_trunk_placer"), TTMBranchingTrunkPlacer.CODEC);

    public static final FoliagePlacerType<TTMSpheroidFoliagePlacer> FOLIAGE_SPHEROID = registerFoliage(TolkienMobs.prefix("spheroid_foliage_placer"), TTMSpheroidFoliagePlacer.CODEC);


    public static final class States {
        protected static final BlockState MALLORN_LOG = TTMContent.LOG_MALLORN.get().defaultBlockState();
        protected static final BlockState MALLORN_LEAVES = TTMContent.LEAVES_MALLORN.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState MIRKWOOD_LOG = TTMContent.LOG_MIRKWOOD.get().defaultBlockState();
        protected static final BlockState MIRKWOOD_LEAVES = TTMContent.LEAVES_MIRKWOOD.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState CULUMALDA_LOG = TTMContent.LOG_CULUMALDA.get().defaultBlockState();
        protected static final BlockState CULUMALDA_LEAVES = TTMContent.LEAVES_CULUMALDA.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState LEBETHRON_LOG = TTMContent.LOG_LEBETHRON.get().defaultBlockState();
        protected static final BlockState LEBETHRON_LEAVES = TTMContent.LEAVES_LEBETHRON.get().defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState DEADWOOD_LOG = TTMContent.LOG_DEADWOOD.get().defaultBlockState();
        protected static final BlockState CAP_DECAY_BLOOM = TTMContent.BLOCK_DECAY_BLOOM.get().defaultBlockState();
        protected static final BlockState CAP_BLOOM_DECAY = TTMContent.BLOCK_BLOOM_DECAY.get().defaultBlockState();
        protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.defaultBlockState();
        protected static final BlockState AIR = Blocks.AIR.defaultBlockState();
        protected static final BlockState AMMOLITE_ORE = TTMContent.ORE_AMMOLITE.get().defaultBlockState();
        protected static final BlockState MITHRIL_ORE = TTMContent.ORE_MITHRIL.get().defaultBlockState();
        protected static final BlockState MORGULIRON_ORE = TTMContent.ORE_MORGULIRON.get().defaultBlockState();
        protected static final BlockState FLOWER_ALFIRIN = TTMContent.FLOWER_ALFIRIN.get().defaultBlockState();
        protected static final BlockState DANDELION = Blocks.DANDELION.defaultBlockState();

    }

    public static final class TreeConfigurations {
        public static final BaseTreeFeatureConfig MIRKWOOD = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MIRKWOOD_LOG),
                new SimpleBlockStateProvider(States.MIRKWOOD_LEAVES),
                new TTMSpheroidFoliagePlacer(4.5f, 2.25f, FeatureSpread.fixed(0), 1, 0, 0.45f, (int) (LEAF_SHAG_FACTOR * 1.5f)),
                new TTMBranchingTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new TwoLayerFeature(1, 0, 1))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig MALLORN = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MALLORN_LOG),
                new SimpleBlockStateProvider(States.MALLORN_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.fixed(4), FeatureSpread.fixed(4), 4),
                new TTMBranchingTrunkPlacer(9, 2, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new ThreeLayerFeature(5, 1, 0, 1, 2, OptionalInt.empty())).maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig CULUMALDA = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.CULUMALDA_LOG),
                new SimpleBlockStateProvider(States.CULUMALDA_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                new StraightTrunkPlacer(5, 2, 0),
                new TwoLayerFeature(1, 0, 1))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig LEBETHRON = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.LEBETHRON_LOG),
                new SimpleBlockStateProvider(States.LEBETHRON_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig DEADTREE = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.DEADWOOD_LOG),
                new SimpleBlockStateProvider(States.AIR),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_BLOOM_DECAY = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(States.CAP_BLOOM_DECAY),
                new TTMSpheroidFoliagePlacer(4.25f, 0f, FeatureSpread.fixed(1), 1, 0, 0f, 0),
                new TTMBranchingTrunkPlacer(3, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_DECAY_BLOOM = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(States.CAP_DECAY_BLOOM),
                new TTMSpheroidFoliagePlacer(4.25f, 1.75f, FeatureSpread.fixed(1), 0, 0, -0.45f, 0),
                new TTMBranchingTrunkPlacer(4, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .ignoreVines()
                .build();
    }

    public static final class ConfiguredFeatures {
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MIRKWOOD = registerWorldFeature(TolkienMobs.prefix("mirkwood"), Feature.TREE.configured(TreeConfigurations.MIRKWOOD));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MALLORN = registerWorldFeature(TolkienMobs.prefix("mallorn"), Feature.TREE.configured(TreeConfigurations.MALLORN));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> CULUMALDA = registerWorldFeature(TolkienMobs.prefix("culumalda"), Feature.TREE.configured(TreeConfigurations.CULUMALDA));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> LEBETHRON = registerWorldFeature(TolkienMobs.prefix("lebethron"), Feature.TREE.configured(TreeConfigurations.LEBETHRON));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> DEADTREE = registerWorldFeature(TolkienMobs.prefix("deadtree"), Feature.TREE.configured(TreeConfigurations.DEADTREE));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_BLOOM_DECAY = registerWorldFeature(TolkienMobs.prefix("bloomdecay"), Feature.TREE.configured(TreeConfigurations.MUSHROOM_BLOOM_DECAY));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_DECAY_BLOOM = registerWorldFeature(TolkienMobs.prefix("decaybloom"), Feature.TREE.configured(TreeConfigurations.MUSHROOM_DECAY_BLOOM));

        // Biome placement
        public static final ConfiguredFeature<?, ? extends Feature<?>> TREES_LORINAND = register("trees_lorinand", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(CULUMALDA.weighted(0.1F), MALLORN.weighted(0.5F), MALLORN.weighted(0.33333334F)), MALLORN)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));
        public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_AMMOLITE = register("ore_ammolite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.AMMOLITE_ORE, 8)).range(16).squared());
        public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_MITHRIL = register("ore_mithril", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.MITHRIL_ORE, 8)).range(16).squared());
        public static final ConfiguredFeature<?, ? extends Feature<?>> ORE_MORGULIRON = register("ore_morguliron", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, States.MORGULIRON_ORE, 8)).range(16).squared());
        public static final ConfiguredFeature<?, ? extends Feature<?>> FLOWER_LORINAND = register("flower_default", Feature.FLOWER.configured(TTMConfigs.DEFAULT_LORINAND_CONFIG).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(2));

    }

    public static final class TTMConfigs {
        public static final BlockClusterFeatureConfig DEFAULT_LORINAND_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(States.FLOWER_ALFIRIN, 2).add(States.DANDELION, 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_, ConfiguredFeature<FC, ?> p_243968_1_) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(ResourceLocation name, Codec<P> codec) {
        FoliagePlacerType<P> type = new FoliagePlacerType<>(codec);
        type.setRegistryName(name);
        FOLIAGE_PLACER_TYPES.add(type);
        return type;
    }

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunk(ResourceLocation name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, name, new TrunkPlacerType<>(codec));
    }

    protected static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerWorldFeature(ResourceLocation rl, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, rl, feature);
    }
}
