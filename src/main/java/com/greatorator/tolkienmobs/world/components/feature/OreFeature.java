package com.greatorator.tolkienmobs.world.components.feature;

import com.greatorator.tolkienmobs.init.TolkienStates;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.List;

public class OreFeature {
     static RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
     static RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
     static RuleTest NETHER_ORE_REPLACEABLES = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
     static RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);

    static List<OreConfiguration.TargetBlockState> AMMOLITE_ORE_TARGET_LIST = Arrays.asList(OreConfiguration.target(STONE_ORE_REPLACEABLES, TolkienStates.States.AMMOLITE_ORE), OreConfiguration.target(NETHER_ORE_REPLACEABLES, TolkienStates.States.AMMOLITE_NETHER_ORE), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, TolkienStates.States.AMMOLITE_DEEPSLATE_ORE), OreConfiguration.target(IN_ENDSTONE, TolkienStates.States.AMMOLITE_END_ORE));
    static List<OreConfiguration.TargetBlockState> MITHRIL_ORE_TARGET_LIST = Arrays.asList(OreConfiguration.target(STONE_ORE_REPLACEABLES, TolkienStates.States.MITHRIL_ORE), OreConfiguration.target(NETHER_ORE_REPLACEABLES, TolkienStates.States.MITHRIL_NETHER_ORE), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, TolkienStates.States.MITHRIL_DEEPSLATE_ORE), OreConfiguration.target(IN_ENDSTONE, TolkienStates.States.MITHRIL_END_ORE));
    static List<OreConfiguration.TargetBlockState> MORGULIRON_ORE_TARGET_LIST = Arrays.asList(OreConfiguration.target(STONE_ORE_REPLACEABLES, TolkienStates.States.MORGULIRON_ORE), OreConfiguration.target(NETHER_ORE_REPLACEABLES, TolkienStates.States.MORGULIRON_NETHER_ORE), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, TolkienStates.States.MORGULIRON_DEEPSLATE_ORE), OreConfiguration.target(IN_ENDSTONE, TolkienStates.States.MORGULIRON_END_ORE));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_AMMOLITE = FeatureUtils.register("ore_ammolite", Feature.ORE, new OreConfiguration(AMMOLITE_ORE_TARGET_LIST, 8));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MITHRIL = FeatureUtils.register("ore_mithril", Feature.ORE, (new OreConfiguration(MITHRIL_ORE_TARGET_LIST, 8)));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MORGULIRON = FeatureUtils.register("ore_morguliron", Feature.ORE, (new OreConfiguration(MORGULIRON_ORE_TARGET_LIST, 8)));
}