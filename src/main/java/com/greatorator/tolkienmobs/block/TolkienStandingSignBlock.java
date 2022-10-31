package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TolkienSignTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class TolkienStandingSignBlock extends StandingSignBlock {
    public TolkienStandingSignBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TolkienSignTile(blockPos, blockState);
    }
}
