package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienTags;
import com.greatorator.tolkienmobs.world.components.feature.TreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MushroomsBlock extends MushroomBlock {
    private final ResourceKey<ConfiguredFeature<?, ?>> featureSupplier;

    public MushroomsBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> supplier) {
        super(properties, supplier);
        this.featureSupplier = supplier;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.is(TolkienTags.blocks.DECAY_GROW_BLOCK)) {
            return true;
        } else {
            return worldIn.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.core.Direction.UP, this);
        }
    }

    public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand) {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> configuredfeature;
        if (this == TolkienBlocks.MUSHROOM_BLOOM_DECAY.get()) {
            configuredfeature = TreeFeature.MUSHROOM_BLOOM_DECAY.value();
        } else {
            if (this != TolkienBlocks.MUSHROOM_DECAY_BLOOM.get()) {
                world.setBlock(pos, state, 3);
                return false;
            }

            configuredfeature = TreeFeature.MUSHROOM_DECAY_BLOOM.value();
        }

        if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos)) {
            return true;
        } else {
            world.setBlock(pos, state, 3);
            return false;
        }
    }
}