package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TolkienSignTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import javax.annotation.Nullable;

public class TolkienWallSignBlock extends WallSignBlock {
    public TolkienWallSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TolkienSignTile(blockPos, blockState);
    }
}
