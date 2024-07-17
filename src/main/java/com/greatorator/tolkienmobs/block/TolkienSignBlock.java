package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TolkienSignTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 20/08/2022
 */
public class TolkienSignBlock extends SignBlock {
    private final WoodType type;

    public TolkienSignBlock(BlockBehaviour.Properties properties, WoodType woodType) {
        super(properties, woodType);
        this.type = woodType;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TolkienSignTile(blockPos, blockState);
    }

    @Override
    public float getYRotationDegrees(BlockState blockState) {
        return 0;
    }

    @Override
    public WoodType type() {
        return this.type;
    }
}
