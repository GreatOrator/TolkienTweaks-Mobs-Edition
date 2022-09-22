package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.*;

public class ChameleonBlock<C> extends Block implements IWaterLoggable {
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
    public boolean isSignalSource(BlockState iBlockState)
    {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction directionFromNeighborToThis) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }


    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, IWorld world, BlockPos pos, BlockPos offset) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = world.getFluidState(pos);
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nonnull
    @SuppressWarnings("deprecated")
    @Override
    public BlockRenderType getRenderShape(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

    public static Optional<BlockState> selectBestAdjacentBlock(@Nonnull IBlockDisplayReader world, @Nonnull BlockPos blockPos)
    {
        TreeMap<Direction, BlockState> adjacentSolidBlocks = new TreeMap<Direction, BlockState>();

        HashMap<BlockState, Integer> adjacentBlockCount = new HashMap<BlockState, Integer>();
        for (Direction facing : Direction.values()) {
            BlockPos adjacentPosition = blockPos.offset(facing.getStepX(),
                    facing.getStepY(),
                    facing.getStepZ());
            BlockState adjacentBS = world.getBlockState(adjacentPosition);
            Block adjacentBlock = adjacentBS.getBlock();
            if (!adjacentBlock.isAir(adjacentBS, world, adjacentPosition)) {
                adjacentSolidBlocks.put(facing, adjacentBS);
                if (adjacentBlockCount.containsKey(adjacentBS)) {
                    adjacentBlockCount.put(adjacentBS, 1 + adjacentBlockCount.get(adjacentBS));
                } else if (adjacentBS.getBlock() != TTMContent.CHAMELEON_BLOCK.get()
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
            if (singleAdjacentBlock.getBlock() == TTMContent.CHAMELEON_BLOCK.get()) {
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
                if (oppositeBlock != null && (oppositeBlock == iBlockState || oppositeBlock.getBlock() == TTMContent.CHAMELEON_BLOCK.get()) ) {
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

    public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
        return VoxelShapes.empty();
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState p_220080_1_, IBlockReader p_220080_2_, BlockPos p_220080_3_) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
        return true;
    }
}