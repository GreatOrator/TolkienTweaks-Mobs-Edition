package com.greatorator.tolkienmobs.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.world.gen.TTMTreeFeatures.ConfiguredFeatures.CULUMALDA;

public class TTMCulumaldaTree extends Tree {
    public TTMCulumaldaTree() {
    }

    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return CULUMALDA;
    }

}
