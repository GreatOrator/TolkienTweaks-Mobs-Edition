package com.greatorator.tolkienmobs.block.trees;

import com.greatorator.tolkienmobs.world.gen.feature.TTMFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class TTMLebethronTree extends Tree {
    public TTMLebethronTree() {
    }

    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
            return TTMFeatures.LEBETHRON;
    }

}