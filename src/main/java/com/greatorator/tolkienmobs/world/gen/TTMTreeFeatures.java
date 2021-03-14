package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
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
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.*;
import net.minecraft.world.gen.trunkplacer.*;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TTMTreeFeatures {
    private static final List<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = new ArrayList<>();

    private final static int LEAF_SHAG_FACTOR = 24;
    private static final int canopyDistancing = 5;

    public static final TrunkPlacerType<TTMBranchingTrunkPlacer> TRUNK_BRANCHING = registerTrunk(TolkienMobs.prefix("branching_trunk_placer"), TTMBranchingTrunkPlacer.CODEC);

    public static final FoliagePlacerType<TTMSpheroidFoliagePlacer> FOLIAGE_SPHEROID = registerFoliage(TolkienMobs.prefix("spheroid_foliage_placer"), TTMSpheroidFoliagePlacer.CODEC);


    public static final class States {
        protected static final BlockState MALLORN_LOG = TTMContent.LOG_MALLORN.get().getDefaultState();
        protected static final BlockState MALLORN_LEAVES = TTMContent.LEAVES_MALLORN.get().getDefaultState().with(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState MIRKWOOD_LOG = TTMContent.LOG_MIRKWOOD.get().getDefaultState();
        protected static final BlockState MIRKWOOD_LEAVES = TTMContent.LEAVES_MIRKWOOD.get().getDefaultState().with(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState CULUMALDA_LOG = TTMContent.LOG_CULUMALDA.get().getDefaultState();
        protected static final BlockState CULUMALDA_LEAVES = TTMContent.LEAVES_CULUMALDA.get().getDefaultState().with(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState LEBETHRON_LOG = TTMContent.LOG_LEBETHRON.get().getDefaultState();
        protected static final BlockState LEBETHRON_LEAVES = TTMContent.LEAVES_LEBETHRON.get().getDefaultState().with(PERSISTENT, Boolean.valueOf(true));
        protected static final BlockState DEADWOOD_LOG = TTMContent.LOG_DEADWOOD.get().getDefaultState();
        protected static final BlockState CAP_DECAY_BLOOM = TTMContent.BLOCK_DECAY_BLOOM.get().getDefaultState();
        protected static final BlockState CAP_BLOOM_DECAY = TTMContent.BLOCK_BLOOM_DECAY.get().getDefaultState();
        protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState();
        protected static final BlockState AIR = Blocks.AIR.getDefaultState();
    }

    public static final class TreeConfigurations {
        public static final BaseTreeFeatureConfig MIRKWOOD = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MIRKWOOD_LOG),
                new SimpleBlockStateProvider(States.MIRKWOOD_LEAVES),
                new TTMSpheroidFoliagePlacer(4.5f, 2.25f, FeatureSpread.func_242252_a(0), 1, 0, 0.45f, (int) (LEAF_SHAG_FACTOR * 1.5f)),
                new TTMBranchingTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new TwoLayerFeature(1, 0, 1))
                .setIgnoreVines()
                .build();

        public static final BaseTreeFeatureConfig MALLORN = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MALLORN_LOG),
                new SimpleBlockStateProvider(States.MALLORN_LEAVES),
                new DarkOakFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0)),
                new DarkOakTrunkPlacer(6, 2, 1),
                new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty())).setMaxWaterDepth(Integer.MAX_VALUE).func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
                .setIgnoreVines()
                .build();

        public static final BaseTreeFeatureConfig CULUMALDA = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.CULUMALDA_LOG),
                new SimpleBlockStateProvider(States.CULUMALDA_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
                new StraightTrunkPlacer(5, 2, 0),
                new TwoLayerFeature(1, 0, 1))
                .setIgnoreVines()
                .build();

        public static final BaseTreeFeatureConfig LEBETHRON = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.LEBETHRON_LOG),
                new SimpleBlockStateProvider(States.LEBETHRON_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig DEADTREE = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.DEADWOOD_LOG),
                new SimpleBlockStateProvider(States.AIR),
                new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_BLOOM_DECAY = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(States.CAP_BLOOM_DECAY),
                new TTMSpheroidFoliagePlacer(4.25f, 0f, FeatureSpread.func_242252_a(1), 1, 0, 0f, 0),
                new TTMBranchingTrunkPlacer(3, 5, 5, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .setIgnoreVines()
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_DECAY_BLOOM = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(States.CAP_DECAY_BLOOM),
                new TTMSpheroidFoliagePlacer(4.25f, 1.75f, FeatureSpread.func_242252_a(1), 0, 0, -0.45f, 0),
                new TTMBranchingTrunkPlacer(4, 5, 5, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .setIgnoreVines()
                .build();
    }

    public static final class ConfiguredFeatures {
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MIRKWOOD = registerWorldFeature(TolkienMobs.prefix("mirkwood"), Feature.TREE.withConfiguration(TreeConfigurations.MIRKWOOD));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MALLORN = registerWorldFeature(TolkienMobs.prefix("mallorn"), Feature.TREE.withConfiguration(TreeConfigurations.MALLORN));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> CULUMALDA = registerWorldFeature(TolkienMobs.prefix("culumalda"), Feature.TREE.withConfiguration(TreeConfigurations.CULUMALDA));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> LEBETHRON = registerWorldFeature(TolkienMobs.prefix("lebethron"), Feature.TREE.withConfiguration(TreeConfigurations.LEBETHRON));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> DEADTREE = registerWorldFeature(TolkienMobs.prefix("deadtree"), Feature.TREE.withConfiguration(TreeConfigurations.DEADTREE));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_BLOOM_DECAY = registerWorldFeature(TolkienMobs.prefix("bloomdecay"), Feature.TREE.withConfiguration(TreeConfigurations.MUSHROOM_BLOOM_DECAY));
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ? extends Feature<?>> MUSHROOM_DECAY_BLOOM = registerWorldFeature(TolkienMobs.prefix("decaybloom"), Feature.TREE.withConfiguration(TreeConfigurations.MUSHROOM_DECAY_BLOOM));
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(ResourceLocation name, Codec<P> codec) {
        FoliagePlacerType<P> type = new FoliagePlacerType<>(codec);
        type.setRegistryName(name);
        FOLIAGE_PLACER_TYPES.add(type);
        return type;
    }

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunk(ResourceLocation name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, name, new TrunkPlacerType<>(codec));
    }

    protected static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerWorldFeature(ResourceLocation rl, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, rl, feature);
    }
}
