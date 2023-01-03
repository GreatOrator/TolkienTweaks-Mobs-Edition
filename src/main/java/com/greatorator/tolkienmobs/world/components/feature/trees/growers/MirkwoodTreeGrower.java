package com.greatorator.tolkienmobs.world.components.feature.trees.growers;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.world.components.feature.TreeFeature.MIRKWOOD;

public class MirkwoodTreeGrower extends AbstractTreeGrower {
    public MirkwoodTreeGrower() {
    }

    @Override
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean largeHive) {
            return MIRKWOOD;
    }

}