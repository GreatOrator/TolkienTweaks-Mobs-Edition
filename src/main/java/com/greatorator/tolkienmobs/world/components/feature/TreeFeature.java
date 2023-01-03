package com.greatorator.tolkienmobs.world.components.feature;

import com.greatorator.tolkienmobs.init.TolkienBiomeFeatures;
import com.greatorator.tolkienmobs.world.components.config.RootConfig;
import com.greatorator.tolkienmobs.world.components.config.TreeConfigurations;
import com.greatorator.tolkienmobs.world.components.feature.trees.decorators.TreeDecorator;
import com.greatorator.tolkienmobs.world.components.feature.trees.placers.TreePlacement;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Arrays;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TreeFeature {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MIRKWOOD = FeatureUtils.register((MODID + "mirkwood"), Feature.TREE, (TreeConfigurations.MIRKWOOD));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MALLORN = FeatureUtils.register((MODID + "mallorn"), Feature.TREE, (TreeConfigurations.MALLORN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CULUMALDA = FeatureUtils.register((MODID + "culumalda"), Feature.TREE, (TreeConfigurations.CULUMALDA));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LEBETHRON = FeatureUtils.register((MODID + "lebethron"), Feature.TREE, (TreeConfigurations.LEBETHRON));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CULUMALDA_FIRIEN = FeatureUtils.register((MODID + "culumalda_firien"), Feature.TREE,(TreeConfigurations.CULUMALDA_FIRIEN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LEBETHRON_FIRIEN = FeatureUtils.register((MODID + "lebethron_firien"), Feature.TREE, (TreeConfigurations.LEBETHRON_FIRIEN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> DEADTREE = FeatureUtils.register((MODID + "deadtree"), Feature.TREE, (TreeConfigurations.DEADTREE));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANGORNOAK = FeatureUtils.register((MODID + "fangornoak"), Feature.TREE, (TreeConfigurations.FANGORNOAK));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLDFORESTOAK = FeatureUtils.register((MODID + "oldforestoak"), Feature.TREE, (TreeConfigurations.OLDFORESTOAK));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MUSHROOM_BLOOM_DECAY = FeatureUtils.register((MODID + "bloomdecay"), Feature.TREE, (TreeConfigurations.MUSHROOM_BLOOM_DECAY));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MUSHROOM_DECAY_BLOOM = FeatureUtils.register((MODID + "decaybloom"), Feature.TREE, (TreeConfigurations.MUSHROOM_DECAY_BLOOM));
    public static final Holder<ConfiguredFeature<RootConfig, ?>> ROOTS_SPREAD = FeatureUtils.register("roots_spread", TolkienBiomeFeatures.ROOTS.get(), new RootConfig(TreeDecorator.ROOT_BLEND_PROVIDER, BlockStateProvider.simple(Blocks.OAK_WOOD)));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_LORINAND = FeatureUtils.register("trees_lorinand", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.MALLORN_CHECKED, 0.9F)), TreePlacement.CULUMALDA_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_MIRKWOOD = FeatureUtils.register("trees_mirkwood", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.MIRKWOOD_CHECKED, 0.8F)), TreePlacement.MIRKWOOD_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_DEADWOOD = FeatureUtils.register("trees_deadtree", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.DEADWOOD_CHECKED, 0.8F)), TreePlacement.DEADWOOD_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_CULUMALDA = FeatureUtils.register("trees_culumalda", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.CULUMALDA_CHECKED, 0.8F)), TreePlacement.CULUMALDA_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_CULUMALDA_FIRIEN = FeatureUtils.register("trees_culumalda_firien", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.CULUMALDA_FIRIEN_CHECKED, 0.8F)), TreePlacement.CULUMALDA_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_LEBETHRON = FeatureUtils.register("trees_lebethron", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.LEBETHRON_CHECKED, 0.8F)), TreePlacement.LEBETHRON_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_LEBETHRON_FIRIEN = FeatureUtils.register("trees_lebethron_firien", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.LEBETHRON_FIRIEN_CHECKED, 0.8F)), TreePlacement.LEBETHRON_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_FANGORNOAK = FeatureUtils.register("trees_fangornoak", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.FANGORNOAK_CHECKED, 0.8F)), TreePlacement.FANGORNOAK_CHECKED));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_OLDFORESTOAK = FeatureUtils.register("trees_oldforestoak", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(Arrays.asList(new WeightedPlacedFeature(TreePlacement.OLDFOREST_CHECKED, 0.8F)), TreePlacement.OLDFOREST_CHECKED));
}
