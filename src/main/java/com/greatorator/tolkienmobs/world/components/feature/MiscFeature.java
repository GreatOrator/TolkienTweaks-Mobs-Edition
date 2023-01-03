package com.greatorator.tolkienmobs.world.components.feature;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienStates;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class MiscFeature {
    public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> FOREST_ROCK = FeatureUtils.register("forest_rock", Feature.FOREST_ROCK, new BlockStateConfiguration(TolkienStates.States.MOSSY_COBBLESTONE));
    public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> GENERAL_ROCK = FeatureUtils.register("general_rock", Feature.FOREST_ROCK, new BlockStateConfiguration(TolkienStates.States.STONE));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ROCKPILES = FeatureUtils.register("patch_rockpiles", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TolkienBlocks.ROCKPILE.get()))));
}
