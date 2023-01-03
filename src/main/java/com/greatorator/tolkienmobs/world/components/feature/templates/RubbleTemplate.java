package com.greatorator.tolkienmobs.world.components.feature.templates;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

public class RubbleTemplate extends Feature<NoneFeatureConfiguration> {
    public RubbleTemplate(Codec<NoneFeatureConfiguration> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        Level world = context.level().getLevel();

        if (world.getBlockState(context.origin().below()).getBlock() == Blocks.GRASS_BLOCK) {
            for (int j1 = 0; j1 < 75; ++j1) {
                BlockPos randomPos = context.origin().offset(context.random().nextInt(8), context.random().nextInt(4), context.random().nextInt(8));
                Material material6 = world.getBlockState(randomPos.below()).getMaterial();
                if(context.random().nextInt(10) == 0) {
                    if (world.isEmptyBlock(randomPos) && material6.isSolid()) {
                        Block block;
                        int chance = context.random().nextInt(31);
                        if (chance < 10) {
                            block = Blocks.COBBLESTONE;
                        } else if (chance > 10 && chance < 20) {
                            block = Blocks.MOSSY_COBBLESTONE;
                        } else if (chance > 20 && chance < 30) {
                            block = Blocks.GRANITE;
                        } else {
                            block = Blocks.CRACKED_STONE_BRICKS;
                        }
                        world.setBlock(randomPos, block.defaultBlockState(), 3);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}