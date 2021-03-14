package com.greatorator.tolkienmobs.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.world.gen.TTMTreeFeatures.ConfiguredFeatures.DEADTREE;

public class TTMDeadTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return DEADTREE;
    }
}
