package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.*;

public class ChameleonBlock<C> extends BlockBCore implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    protected static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0D, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0, 0.0D, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_EAST = Block.box(0.0, 0.0D, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_WEST = Block.box(0.0, 0.0D, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SHAPE_COMMON = Block.box(0.0, 0.0D, 0.0, 16.0, 16.0, 16.0);

    public ChameleonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));

    }

    @SuppressWarnings("deprecation")
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
    public boolean isSignalSource(BlockState iBlockState)
    {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction directionFromNeighborToThis) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }


    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, LevelAccessor world, BlockPos pos, BlockPos offset) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = context.getLevel().getFluidState(pos);
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nonnull
    @SuppressWarnings("deprecated")
    @Override
    public RenderShape getRenderShape(BlockState iBlockState) {
        return RenderShape.MODEL;
    }

    public Optional<BlockState> selectBestAdjacentBlock(@Nonnull BlockAndTintGetter world, @Nonnull BlockPos blockPos)
    {
        TreeMap<Direction, BlockState> adjacentSolidBlocks = new TreeMap<Direction, BlockState>();

        HashMap<BlockState, Integer> adjacentBlockCount = new HashMap<BlockState, Integer>();
        for (Direction facing : Direction.values()) {
            BlockPos adjacentPosition = blockPos.offset(facing.getStepX(),
                    facing.getStepY(),
                    facing.getStepZ());
            BlockState adjacentBS = world.getBlockState(adjacentPosition);
            if (!adjacentBS.isAir()) {
                adjacentSolidBlocks.put(facing, adjacentBS);
                if (adjacentBlockCount.containsKey(adjacentBS)) {
                    adjacentBlockCount.put(adjacentBS, 1 + adjacentBlockCount.get(adjacentBS));
                } else if (adjacentBS.getBlock() != TolkienBlocks.CHAMELEON_BLOCK.get()
                        && adjacentBS.getBlock() != Blocks.GRASS_BLOCK) {
                    adjacentBlockCount.put(adjacentBS, 1);
                }
            }
        }

        if (adjacentBlockCount.isEmpty()) {
            return Optional.empty();
        }

        if (adjacentSolidBlocks.size() == 1) {
            BlockState singleAdjacentBlock = adjacentSolidBlocks.firstEntry().getValue();
            if (singleAdjacentBlock.getBlock() == TolkienBlocks.CHAMELEON_BLOCK.get()) {
                return Optional.empty();
            } else {
                return Optional.of(singleAdjacentBlock);
            }
        }

        // 2) multiple choices. Look for the one(s) present on the most sides.

        int maxCount = 0;
        ArrayList<BlockState> maxCountIBlockStates = new ArrayList<BlockState>();
        for (Map.Entry<BlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) { // a tie
                maxCountIBlockStates.add(entry.getKey());
            }
        }

        if (maxCountIBlockStates.isEmpty()) throw new AssertionError("maxCountIBlockStates.isEmpty()");
        if (maxCountIBlockStates.size() == 1) {               // one clear winner
            return Optional.of(maxCountIBlockStates.get(0));
        }

        // for each block which has a match on the opposite side, add 10 to its count.
        // exact matches are counted twice --> +20, match with BlockCamouflage only counted once -> +10
        for (Map.Entry<Direction, BlockState> entry : adjacentSolidBlocks.entrySet()) {
            BlockState iBlockState = entry.getValue();
            if (maxCountIBlockStates.contains(iBlockState)) {
                Direction oppositeSide = entry.getKey().getOpposite();
                BlockState oppositeBlock = adjacentSolidBlocks.get(oppositeSide);
                if (oppositeBlock != null && (oppositeBlock == iBlockState || oppositeBlock.getBlock() == TolkienBlocks.CHAMELEON_BLOCK.get()) ) {
                    adjacentBlockCount.put(iBlockState, 10 + adjacentBlockCount.get(iBlockState));
                }
            }
        }

        maxCount = 0;
        maxCountIBlockStates.clear();
        for (Map.Entry<BlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                maxCountIBlockStates.add(entry.getKey());
            }
        }
        if (maxCountIBlockStates.isEmpty()) throw new AssertionError("maxCountIBlockStates.isEmpty()");
        if (maxCountIBlockStates.size() == 1) {  // one clear winner
            return Optional.of(maxCountIBlockStates.get(0));
        }

        Direction[] orderOfPreference = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.EAST,
                Direction.WEST, Direction.DOWN, Direction.UP};

        for (Direction testFace : orderOfPreference) {
            if (adjacentSolidBlocks.containsKey(testFace) &&
                    maxCountIBlockStates.contains(adjacentSolidBlocks.get(testFace))) {
                return Optional.of(adjacentSolidBlocks.get(testFace));
            }
        }
        throw new AssertionError("unreachable code");
    }

    @Override
    public VoxelShape getVisualShape(BlockState p_230322_1_, BlockGetter p_230322_2_, BlockPos p_230322_3_, CollisionContext p_230322_4_) {
        return Shapes.empty();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public float getShadeBrightness(BlockState p_220080_1_, BlockGetter p_220080_2_, BlockPos p_220080_3_) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_200123_1_, BlockGetter p_200123_2_, BlockPos p_200123_3_) {
        return true;
    }
}