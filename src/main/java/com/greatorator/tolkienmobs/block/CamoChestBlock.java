package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoChestTile;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CamoChestBlock extends ChameleonBlock<CamoChestTile> implements EntityBlock {
    protected static final VoxelShape SHAPE_NORTH = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape SHAPE_EAST = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape SHAPE_COMMON = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public CamoChestBlock(Properties properties) {
        super(properties);
        setBlockEntity(() -> TolkienTiles.CAMO_CHEST_TILE.get(), true); //<-- The boolean (true) specifies that this tile needs to tick. If your tile implemented ITickableTileEntity in 1.16 then this needs to be true
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
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
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (!world.isClientSide) {
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof CamoChestTile) {
                ((CamoChestTile) tile).onRightClick(player, hand);
                world.playSound((Player)null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.3F, 0.5F);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
