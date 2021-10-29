package com.greatorator.tolkienmobs.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static slimeknights.tconstruct.TConstruct.random;

public class TTMRubbleFeature extends Feature<NoFeatureConfig> {
    public TTMRubbleFeature(Codec<NoFeatureConfig> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (world.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
            for (int j1 = 0; j1 < 75; ++j1) {
                BlockPos randomPos = pos.offset(random.nextInt(8), random.nextInt(4), random.nextInt(8));
                Material material6 = world.getBlockState(randomPos.below()).getMaterial();
                if(random.nextInt(10) == 0) {
                    if (world.isEmptyBlock(randomPos) && material6.isSolid()) {
                        Block block = Blocks.COBBLESTONE;
                        int chance = random.nextInt(31);
                        if (chance < 10) {
                            block = Blocks.COBBLESTONE;
                        } else if (chance >= 10 && chance < 20) {
                            block = Blocks.MOSSY_COBBLESTONE;
                        } else if (chance >= 20 && chance < 30) {
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
