package com.greatorator.tolkienmobs.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockMushrooms extends MushroomBlock
{
    public BlockMushrooms(Properties properties) {
        super(properties);
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.isIn(Blocks.COBBLESTONE) || state.isIn(Blocks.STONE) || state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }
}