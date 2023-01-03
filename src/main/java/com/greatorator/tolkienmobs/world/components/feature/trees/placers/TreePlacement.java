package com.greatorator.tolkienmobs.world.components.feature.trees.placers;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.world.components.feature.TreeFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TreePlacement {
    public static final Holder<PlacedFeature> MALLORN_CHECKED = PlacementUtils.register("mallorn_checked", TreeFeature.MALLORN, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_MALLORN.get()));
    public static final Holder<PlacedFeature> MIRKWOOD_CHECKED = PlacementUtils.register("mirkwood_checked", TreeFeature.MIRKWOOD, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_MIRKWOOD.get()));
    public static final Holder<PlacedFeature> CULUMALDA_CHECKED = PlacementUtils.register("culumalda_checked", TreeFeature.CULUMALDA, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_CULUMALDA.get()));
    public static final Holder<PlacedFeature> CULUMALDA_FIRIEN_CHECKED = PlacementUtils.register("culumalda_firien_checked", TreeFeature.CULUMALDA_FIRIEN, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_CULUMALDA.get()));
    public static final Holder<PlacedFeature> LEBETHRON_CHECKED = PlacementUtils.register("lebethron_checked", TreeFeature.LEBETHRON, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_LEBETHRON.get()));
    public static final Holder<PlacedFeature> LEBETHRON_FIRIEN_CHECKED = PlacementUtils.register("lebethron_firien_checked", TreeFeature.LEBETHRON_FIRIEN, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_LEBETHRON.get()));
    public static final Holder<PlacedFeature> FANGORNOAK_CHECKED = PlacementUtils.register("fangornoak_checked", TreeFeature.FANGORNOAK, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_FANGORNOAK.get()));
    public static final Holder<PlacedFeature> DEADWOOD_CHECKED = PlacementUtils.register("deadwood_checked", TreeFeature.DEADTREE, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_DEADWOOD.get()));
    public static final Holder<PlacedFeature> OLDFOREST_CHECKED = PlacementUtils.register("oldforest_checked", TreeFeature.OLDFORESTOAK, PlacementUtils.filteredByBlockSurvival(TolkienBlocks.SAPLING_FANGORNOAK.get()));
}
