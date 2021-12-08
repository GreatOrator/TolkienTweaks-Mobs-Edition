package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.world.gen.TTMFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockTTMMushrooms extends MushroomBlock
{
    public BlockTTMMushrooms(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.is(TTMTags.blocks.DECAY_GROW_BLOCK)) {
            return true;
        } else {
            return worldIn.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
        }
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature;
        if (this == TTMContent.MUSHROOM_BLOOM_DECAY.get()) {
            configuredfeature = TTMFeatures.MUSHROOM_BLOOM_DECAY;
        } else {
            if (this != TTMContent.MUSHROOM_DECAY_BLOOM.get()) {
                world.setBlock(pos, state, 3);
                return false;
            }

            configuredfeature = TTMFeatures.MUSHROOM_DECAY_BLOOM;
        }

        if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos)) {
            return true;
        } else {
            world.setBlock(pos, state, 3);
            return false;
        }
    }
}