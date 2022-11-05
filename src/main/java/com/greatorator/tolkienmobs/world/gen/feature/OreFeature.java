package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.world.gen.TolkienFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OreFeature {
    public static final RuleTest NATURAL_STONE = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_AMMOLITE = FeatureUtils.register("ore_ammolite", Feature.ORE, (new OreConfiguration(NATURAL_STONE, TolkienFeatures.States.AMMOLITE_ORE, 8)));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MITHRIL = FeatureUtils.register("ore_mithril", Feature.ORE, (new OreConfiguration(NATURAL_STONE, TolkienFeatures.States.MITHRIL_ORE, 8)));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MORGULIRON = FeatureUtils.register("ore_morguliron", Feature.ORE, (new OreConfiguration(NATURAL_STONE, TolkienFeatures.States.MORGULIRON_ORE, 8)));
}
