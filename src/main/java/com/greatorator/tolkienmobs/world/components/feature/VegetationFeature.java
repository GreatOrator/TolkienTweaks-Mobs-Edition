package com.greatorator.tolkienmobs.world.components.feature;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomBooleanFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class VegetationFeature {
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_LORINAND = FeatureUtils.register("flower_lorinand", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TolkienBlocks.FLOWER_ALFIRIN.get().defaultBlockState()))));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_MIRKWOOD = FeatureUtils.register("flower_mirkwood", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TolkienBlocks.FLOWER_MIRKWOOD.get().defaultBlockState()))));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FLOWER_MARSH = FeatureUtils.register("flower_marsh", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TolkienBlocks.FLOWER_SWAMPMILKWEED.get().defaultBlockState()))));
    public static final Holder<ConfiguredFeature<RandomBooleanFeatureConfiguration, ?>> MUSHROOM_DECAY = FeatureUtils.register("mushroom_decay", Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfiguration(PlacementUtils.inlinePlaced(TreeFeature.MUSHROOM_DECAY_BLOOM), PlacementUtils.inlinePlaced(TreeFeature.MUSHROOM_BLOOM_DECAY)));
//    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MALLORN_LEAFPILES = FeatureUtils.register("patch_mallorn_leafpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.MALLORN_LEAFPILES_CONFIG));
//    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MIRKWOOD_LEAFPILES = FeatureUtils.register("patch_mirkwood_leafpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.MIRKWOOD_LEAFPILES_CONFIG));
//    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CULUMALDA_LEAFPILES = FeatureUtils.register("patch_culumalda_leafpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.CULUMALDA_LEAFPILES_CONFIG));
//    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_LEBETHRON_LEAFPILES = FeatureUtils.register("patch_lebethron_leafpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.LEBETHRON_LEAFPILES_CONFIG));
//    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_FANGORNOAK_LEAFPILES = FeatureUtils.register("patch_fangornoak_leafpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.FANGORNOAK_LEAFPILES_CONFIG));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_MALLORN_LEAFPILES_SPARSE = FeatureUtils.register("patch_mallorn_leafpiles_sparse", PATCH_MALLORN_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_MIRKWOOD_LEAFPILES_SPARSE = FeatureUtils.register("patch_mirkwood_leafpiles_sparse", PATCH_MIRKWOOD_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_CULUMALDA_LEAFPILES_SPARSE = FeatureUtils.register("patch_culumalda_leafpiles_sparse", PATCH_CULUMALDA_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_LEBETHRON_LEAFPILES_SPARSE = FeatureUtils.register("patch_lebethron_leafpiles_sparse", PATCH_LEBETHRON_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PATCH_FANGORNOAK_LEAFPILES_SPARSE = FeatureUtils.register("patch_fangornoak_leafpiles_sparse", PATCH_FANGORNOAK_LEAFPILES.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
}