package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.world.gen.feature.config.TreeConfigurations;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TreeFeature {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MIRKWOOD = FeatureUtils.register((MODID + "mirkwood"), Feature.TREE, (TreeConfigurations.MIRKWOOD));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MALLORN = FeatureUtils.register((MODID + "mallorn"), Feature.TREE, (TreeConfigurations.MALLORN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CULUMALDA = FeatureUtils.register((MODID + "culumalda"), Feature.TREE, (TreeConfigurations.CULUMALDA));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CULUMALDA_FIRIEN = FeatureUtils.register((MODID + "culumalda_firien"), Feature.TREE,(TreeConfigurations.CULUMALDA_FIRIEN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LEBETHRON = FeatureUtils.register((MODID + "lebethron"), Feature.TREE, (TreeConfigurations.LEBETHRON));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> LEBETHRON_FIRIEN = FeatureUtils.register((MODID + "lebethron_firien"), Feature.TREE, (TreeConfigurations.LEBETHRON_FIRIEN));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> DEADTREE = FeatureUtils.register((MODID + "deadtree"), Feature.TREE, (TreeConfigurations.DEADTREE));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANGORNOAK = FeatureUtils.register((MODID + "fangornoak"), Feature.TREE, (TreeConfigurations.FANGORNOAK));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> OLDFORESTOAK = FeatureUtils.register((MODID + "oldforestoak"), Feature.TREE, (TreeConfigurations.OLDFORESTOAK));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MUSHROOM_BLOOM_DECAY = FeatureUtils.register((MODID + "bloomdecay"), Feature.TREE, (TreeConfigurations.MUSHROOM_BLOOM_DECAY));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MUSHROOM_DECAY_BLOOM = FeatureUtils.register((MODID + "decaybloom"), Feature.TREE, (TreeConfigurations.MUSHROOM_DECAY_BLOOM));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_LORINAND = FeatureUtils.register("trees_lorinand", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(CULUMALDA, 0.1F), new WeightedPlacedFeature(MALLORN, 0.5F), new WeightedPlacedFeature(MALLORN, 0.33333334F)))) MALLORN)).decorated(PlacementUtils.HEIGHTMAP).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_MIRKWOOD = FeatureUtils.register("trees_mirkwood", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(DEADTREE.weighted(0.1F), MIRKWOOD.weighted(0.5F), MIRKWOOD.weighted(0.33333334F)), MIRKWOOD)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(50, 0.1F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_DEADWOOD = FeatureUtils.register("trees_deadtree", DEADTREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1 , 0.1F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_CULUMALDA = FeatureUtils.register("trees_culumalda", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(CULUMALDA.weighted(0.1F), Features.OAK_BEES_002.weighted(0.5F), LEBETHRON.weighted(0.33333334F)), CULUMALDA)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_CULUMALDA_FIRIEN = FeatureUtils.register("trees_culumalda_firien", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(CULUMALDA_FIRIEN.weighted(0.1F), Features.OAK_BEES_002.weighted(0.5F), LEBETHRON_FIRIEN.weighted(0.33333334F)), CULUMALDA_FIRIEN)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.5F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_FANGORNOAK = FeatureUtils.register("trees_fangornoak", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(FANGORNOAK.weighted(0.1F), Features.OAK_BEES_002.weighted(0.5F), FANGORNOAK.weighted(0.33333334F)), FANGORNOAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(35, 0.5F, 1))));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_OLDFORESTOAK = FeatureUtils.register("trees_oldforestoak", Feature.RANDOM_SELECTOR, (new RandomFeatureConfiguration(ImmutableList.of(OLDFORESTOAK.weighted(0.1F), Features.OAK_BEES_005.weighted(0.5F), OLDFORESTOAK.weighted(0.33333334F)), FANGORNOAK)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(35, 0.5F, 1))));
}
