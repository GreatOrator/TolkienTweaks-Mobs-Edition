package com.greatorator.tolkienmobs.world.gen.feature.config;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.init.TolkienStates;
import com.greatorator.tolkienmobs.world.gen.placers.BranchingLargeTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.BranchingTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.SpheroidFoliagePlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.OptionalInt;

import static com.greatorator.tolkienmobs.world.gen.feature.MiscFeature.*;

public class TreeConfigurations {
    private final static int LEAF_SHAG_FACTOR = 24;
    private static final int canopyDistancing = 5;

    public static final TreeConfiguration MIRKWOOD = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.MIRKWOOD_LOG),
            new ForkingTrunkPlacer(9, 3, 3),
            BlockStateProvider.simple(TolkienStates.States.MIRKWOOD_LEAVES),
            new SpheroidFoliagePlacer(4.5f, 2.25f, ConstantInt.of(0), 1, 0, 0.45f, (int) (LEAF_SHAG_FACTOR * 1.5f)),
            new TwoLayersFeatureSize(1, 0, 1))
            .decorators(ImmutableList.of(MIRKWOOD_ROOTS))
            .ignoreVines()
            .build();

    public static final TreeConfiguration MALLORN = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.MALLORN_LOG),
            new BranchingLargeTrunkPlacer(6, 3, 3, 5, new BranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
            BlockStateProvider.simple(TolkienStates.States.MALLORN_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
            new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
            .decorators(ImmutableList.of(LIGHTNINGBUG, MALLORN_ROOTS))
            .ignoreVines()
            .build();

    public static final TreeConfiguration FANGORNOAK = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.FANGORNOAK_LOG),
            new BranchingLargeTrunkPlacer(6, 3, 3, 5, new BranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
            BlockStateProvider.simple(TolkienStates.States.FANGORNOAK_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(1, 0, 1))
            .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE, FANGORNOAK_ROOTS))
            .build();

    public static final TreeConfiguration OLDFORESTOAK = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.DARK_OAK_LOGS),
            new BranchingLargeTrunkPlacer(6, 3, 3, 5, new BranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
            BlockStateProvider.simple(TolkienStates.States.DARK_OAK_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(1, 0, 1))
            .decorators(ImmutableList.of(LeaveVineDecorator.INSTANCE, OLDFOREST_ROOTS))
            .build();

    public static final TreeConfiguration CULUMALDA = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.CULUMALDA_LOG),
            new StraightTrunkPlacer(5, 2, 0),
            BlockStateProvider.simple(TolkienStates.States.CULUMALDA_LEAVES),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
            new TwoLayersFeatureSize(1, 0, 1))
            .ignoreVines()
            .build();

    public static final TreeConfiguration LEBETHRON = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.LEBETHRON_LOG),
            new FancyTrunkPlacer(5, 11, 0),
            BlockStateProvider.simple(TolkienStates.States.LEBETHRON_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
            .ignoreVines()
            .build();

    public static final TreeConfiguration CULUMALDA_FIRIEN = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.CULUMALDA_LOG),
            new BranchingLargeTrunkPlacer(6, 3, 3, 5, new BranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
            BlockStateProvider.simple(TolkienStates.States.CULUMALDA_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
            new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
            .decorators(ImmutableList.of(CULUMALDA_ROOTS))
            .ignoreVines()
            .build();

    public static final TreeConfiguration LEBETHRON_FIRIEN = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.LEBETHRON_LOG),
            new BranchingLargeTrunkPlacer(6, 3, 3, 5, new BranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
            BlockStateProvider.simple(TolkienStates.States.LEBETHRON_LEAVES),
            new FancyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(4), 4),
            new ThreeLayersFeatureSize(5, 1, 0, 1, 2, OptionalInt.empty()))
            .decorators(ImmutableList.of(LEBETHRON_ROOTS))
            .ignoreVines()
            .build();

    public static final TreeConfiguration DEADTREE = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.DEADWOOD_LOG),
            new FancyTrunkPlacer(3, 11, 0),
            BlockStateProvider.simple(TolkienStates.States.AIR),
            new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
            .ignoreVines()
            .build();

    public static final TreeConfiguration MUSHROOM_BLOOM_DECAY = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.MUSHROOM_STEM),
            new BranchingTrunkPlacer(6, 5, 4, 6, new BranchesConfig(5, 1, 9, 1, 0.3, 0.2), true),
            BlockStateProvider.simple(TolkienStates.States.CAP_BLOOM_DECAY),
            new SpheroidFoliagePlacer(4.25f, 0f, ConstantInt.of(1), 1, 0, 0f, 0),
            new TwoLayersFeatureSize(11, 0, canopyDistancing))
            .ignoreVines()
            .build();

    public static final TreeConfiguration MUSHROOM_DECAY_BLOOM = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(TolkienStates.States.MUSHROOM_STEM),
            new BranchingTrunkPlacer(6, 5, 4, 8, new BranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
            BlockStateProvider.simple(TolkienStates.States.CAP_DECAY_BLOOM),
            new SpheroidFoliagePlacer(4.25f, 1.75f, ConstantInt.of(1), 0, 0, -0.45f, 0),
            new TwoLayersFeatureSize(11, 0, canopyDistancing))
            .ignoreVines()
            .build();
}

