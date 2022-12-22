package com.greatorator.tolkienmobs.world.gen.feature.placement;

import com.greatorator.tolkienmobs.world.gen.feature.OreFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.Arrays;
import java.util.List;

public class OrePlacement {
    public static final Holder<PlacedFeature> ORE_AMMOLITE = PlacementUtils.register("ore_ammolite", OreFeature.ORE_AMMOLITE, rareOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    public static final Holder<PlacedFeature> ORE_MITHRIL = PlacementUtils.register("ore_mithril", OreFeature.ORE_MITHRIL, rareOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
    public static final Holder<PlacedFeature> ORE_MORGULIRON = PlacementUtils.register("ore_morguliron", OreFeature.ORE_MORGULIRON, rareOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier1) {
        return Arrays.asList(modifier, InSquarePlacement.spread(), modifier1, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int number, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(number), modifier);
    }

    private static List<PlacementModifier> rareOrePlacement(int number, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(number), modifier);
    }
}
