package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.world.gen.TTMTreeFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockMushrooms extends MushroomBlock
{
    public BlockMushrooms(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.isIn(TTMTags.blocks.DECAY_GROW_BLOCK)) {
            return true;
        } else {
            return worldIn.getLightSubtracted(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean grow(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature;
        if (this == TTMContent.MUSHROOM_BLOOM_DECAY.get()) {
            configuredfeature = TTMTreeFeatures.ConfiguredFeatures.MUSHROOM_BLOOM_DECAY;
        } else {
            if (this != TTMContent.MUSHROOM_DECAY_BLOOM.get()) {
                world.setBlockState(pos, state, 3);
                return false;
            }

            configuredfeature = TTMTreeFeatures.ConfiguredFeatures.MUSHROOM_DECAY_BLOOM;
        }

        if (configuredfeature.generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos)) {
            return true;
        } else {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }
}