package com.greatorator.tolkienmobs.world.gen.feature.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.TolkienFeatures;
import com.greatorator.tolkienmobs.world.gen.placers.BranchingLargeTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.BranchingTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.SpheroidFoliagePlacer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TreeFeatureConfig implements FeatureConfiguration {
    public static final Codec<TreeFeatureConfig> codecTFTreeConfig = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((obj) -> obj.trunkProvider),
                    BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter((obj) -> obj.leavesProvider),
                    BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter((obj) -> obj.branchProvider),
                    BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter((obj) -> obj.rootsProvider),
                    Codec.INT.fieldOf("minimum_size").orElse(20).forGetter((obj) -> obj.minHeight),
                    Codec.INT.fieldOf("add_first_five_chance").orElse(1).forGetter((obj) -> obj.chanceAddFiveFirst),
                    Codec.INT.fieldOf("add_second_five_chance").orElse(1).forGetter((obj) -> obj.chanceAddFiveSecond),
                    Codec.BOOL.fieldOf("has_leaves").orElse(true).forGetter((obj) -> obj.hasLeaves),
                    Codec.BOOL.fieldOf("check_water").orElse(false).forGetter((obj) -> obj.checkWater),
                    BlockStateProvider.CODEC.fieldOf("sapling").orElse(BlockStateProvider.simple(Blocks.OAK_SAPLING.defaultBlockState())).forGetter((obj) -> obj.sapling))
                    .apply(instance, TreeFeatureConfig::new));

    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider leavesProvider;
    public final BlockStateProvider branchProvider;
    public final BlockStateProvider rootsProvider;
    public final int minHeight;
    public final int chanceAddFiveFirst;
    public final int chanceAddFiveSecond;
    public final boolean hasLeaves;
    public final boolean checkWater;
    public final BlockStateProvider sapling;
    public transient boolean forcePlacement;
    public final List<TreeDecorator> decorators = Lists.newArrayList();

    public TreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, BlockStateProvider branch, BlockStateProvider roots, int height, int chanceFiveFirst, int chanceFiveSecond, boolean hasLeaves, boolean checkWater, BlockStateProvider sapling) {
        this.trunkProvider = trunk;
        this.leavesProvider = leaves;
        this.branchProvider = branch;
        this.rootsProvider = roots;
        this.minHeight = height;
        // For some dumb reason this keeps getting -1 so you get `Math.max(x, 0)` for punishment
        this.chanceAddFiveFirst = Math.max(chanceFiveFirst, 1);
        this.chanceAddFiveSecond = Math.max(chanceFiveSecond, 1);
        this.hasLeaves = hasLeaves;
        this.checkWater = checkWater;
        this.sapling = sapling;
    }

    private static final List<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = new ArrayList<>();

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_REGISTER = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, MODID);

    private final static int LEAF_SHAG_FACTOR = 24;
    private static final int canopyDistancing = 5;

    public static final TrunkPlacerType<BranchingTrunkPlacer> TRUNK_BRANCHING = registerTrunk(TolkienMobs.prefix("branching_trunk_placer"), BranchingTrunkPlacer.CODEC);
    public static final TrunkPlacerType<BranchingLargeTrunkPlacer> TRUNK_BRANCHING_LARGE = registerTrunk(TolkienMobs.prefix("branching_large_trunk_placer"), BranchingLargeTrunkPlacer.CODEC);
    public static final RegistryObject<FoliagePlacerType<SpheroidFoliagePlacer>> FOLIAGE_SPHEROID = FOLIAGE_PLACER_REGISTER.register("spheroid_foliage_placer", () -> new FoliagePlacerType<>(SpheroidFoliagePlacer.CODEC));

    public void forcePlacement() {
        this.forcePlacement = true;
    }

    public IPlantable getSapling(Random rand, BlockPos pos) {
        return (IPlantable) sapling.getState(rand, pos).getBlock();
    }

    public static final class TreeConfigurations {
        public static final TreeConfiguration MIRKWOOD = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.MIRKWOOD_LOG),
                new BranchingTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.MIRKWOOD_LEAVES),
                new SpheroidFoliagePlacer(4.5f, 2.25f, ConstantInt.of(0), 1, 0, 0.45f, (int) (LEAF_SHAG_FACTOR * 1.5f)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .build();

        public static final TreeConfiguration MALLORN = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.MALLORN_LOG),
                new BranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.MALLORN_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
                new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
                .ignoreVines()
                .build();

        public static final TreeConfiguration CULUMALDA = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.CULUMALDA_LOG),
                new StraightTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(TolkienFeatures.States.CULUMALDA_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .build();

        public static final TreeConfiguration LEBETHRON = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.LEBETHRON_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(TolkienFeatures.States.LEBETHRON_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines()
                .build();

        public static final TreeConfiguration CULUMALDA_FIRIEN = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.CULUMALDA_LOG),
                new BranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.CULUMALDA_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
                new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
                .ignoreVines()
                .build();

        public static final TreeConfiguration LEBETHRON_FIRIEN = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.LEBETHRON_LOG),
                new BranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.LEBETHRON_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
                new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
                .ignoreVines()
                .build();

        public static final TreeConfiguration DEADTREE = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.DEADWOOD_LOG),
                new FancyTrunkPlacer(3, 11, 0),
                BlockStateProvider.simple(TolkienFeatures.States.AIR),
                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines()
                .build();

        public static final TreeConfiguration FANGORNOAK = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.FANGORNOAK_LOG),
                new BranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.FANGORNOAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE))
                .build();

        public static final TreeConfiguration OLDFORESTOAK = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.DARK_OAK_LOGS),
                new BranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                BlockStateProvider.simple(TolkienFeatures.States.DARK_OAK_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(ImmutableList.of(LeaveVineDecorator.INSTANCE))
                .build();

        public static final TreeConfiguration MUSHROOM_BLOOM_DECAY = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.MUSHROOM_STEM),
                new BranchingTrunkPlacer(3, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                BlockStateProvider.simple(TolkienFeatures.States.CAP_BLOOM_DECAY),
                new SpheroidFoliagePlacer(4.25f, 0f, ConstantInt.of(1), 1, 0, 0f, 0),
                new TwoLayersFeatureSize(11, 0, canopyDistancing))
                .ignoreVines()
                .build();

        public static final TreeConfiguration MUSHROOM_DECAY_BLOOM = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TolkienFeatures.States.MUSHROOM_STEM),
                new BranchingTrunkPlacer(4, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                BlockStateProvider.simple(TolkienFeatures.States.CAP_DECAY_BLOOM),
                new SpheroidFoliagePlacer(4.25f, 1.75f, ConstantInt.of(1), 0, 0, -0.45f, 0),
                new TwoLayersFeatureSize(11, 0, canopyDistancing))
                .ignoreVines()
                .build();
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(ResourceLocation name, Codec<P> codec) {
        FoliagePlacerType<P> type = new FoliagePlacerType<>(codec);
        type.setRegistryName(name);
        FOLIAGE_PLACER_TYPES.add(type);
        return type;
    }

    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunk(ResourceLocation name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, name, new TrunkPlacerType<>(codec));
    }
}
