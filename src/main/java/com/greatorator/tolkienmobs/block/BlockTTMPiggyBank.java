package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TTMPiggyBankTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockTTMPiggyBank extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty FULL = BooleanProperty.create("full");

    protected static final VoxelShape SHAPE_NORTH = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_EAST = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_WEST = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_COMMON = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);

    public BlockTTMPiggyBank(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FULL, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.getValue(FACING)) {
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_COMMON;
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, FULL);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TTMPiggyBankTile();
    }
}
