package com.greatorator.tolkienmobs.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class RockPileBlock extends BushBlock {
    public static VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 12.0D, 2.0D, 12.0D);

    public RockPileBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        Vector3d vector3d = state.getOffset(reader, pos);
        return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.is(Blocks.GRASS_BLOCK) || p_200014_1_.is(Blocks.DIRT) || p_200014_1_.is(Blocks.COARSE_DIRT) || p_200014_1_.is(Blocks.PODZOL) || p_200014_1_.is(Blocks.FARMLAND);
    }
}
