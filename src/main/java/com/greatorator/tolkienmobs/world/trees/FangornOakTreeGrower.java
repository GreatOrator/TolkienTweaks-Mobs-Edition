package com.greatorator.tolkienmobs.world.trees;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.world.gen.feature.TreeFeature.FANGORNOAK;

public class FangornOakTreeGrower extends AbstractTreeGrower {
    public FangornOakTreeGrower() {
    }

    @Override
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive) {
            return FANGORNOAK;
    }

}
