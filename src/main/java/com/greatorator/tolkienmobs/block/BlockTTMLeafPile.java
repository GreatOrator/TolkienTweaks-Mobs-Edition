package com.greatorator.tolkienmobs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import java.util.stream.Stream;

public class BlockTTMLeafPile extends Block {
    public static Block.Properties PROPERTIES = Block.Properties.of(Material.LEAVES)
            .sound(SoundType.GRASS)
            .harvestLevel(1)
            .harvestTool(ToolType.HOE);

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(1, 1, 2, 2, 11, 15),
            Block.box(14, 1, 1, 15, 11, 15),
            Block.box(2, 1, 14, 14, 11, 15),
            Block.box(1, 1, 1, 14, 11, 2),
            Block.box(2, 11, 2, 14, 12, 14),
            Block.box(1, 0, 1, 15, 1, 15)
    ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();

    public BlockTTMLeafPile(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    }

    /**Change the block shadow -- Lower return values = more shadow*/
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6F;
    }
}
