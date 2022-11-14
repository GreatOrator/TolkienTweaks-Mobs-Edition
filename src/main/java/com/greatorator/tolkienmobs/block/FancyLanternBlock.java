package com.greatorator.tolkienmobs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FancyLanternBlock extends Block {
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final VoxelShape NON_HANGING_SHAPE = Shapes.or(
            Block.box(5.299999999999999, 0, 5.299999999999999, 10.7, 1, 10.7),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6.499999999999998, 1.4999999999999996, 6.499999999999998, 9.499999999999998, 2.4999999999999996, 9.499999999999998),
            Block.box(5.299999999999999, 2, 5.299999999999999, 10.700000000000001, 8, 10.700000000000001),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(5.299999999999999, 9, 5.299999999999999, 10.7, 10, 10.7),
            Block.box(6.299999999999999, 10, 6.299999999999999, 9.7, 11, 9.7)
    );
    private static final VoxelShape HANGING_SHAPE = Shapes.or(
            Block.box(5.299999999999999, 0, 5.299999999999999, 10.7, 1, 10.7),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(6.499999999999998, 1.4999999999999996, 6.499999999999998, 9.499999999999998, 2.4999999999999996, 9.499999999999998),
            Block.box(5.299999999999999, 2, 5.299999999999999, 10.700000000000001, 8, 10.700000000000001),
            Block.box(5, 8, 5, 11, 9, 11),
            Block.box(5.299999999999999, 9, 5.299999999999999, 10.7, 10, 10.7),
            Block.box(6.299999999999999, 10, 6.299999999999999, 9.7, 11, 9.7),
            Block.box(6.299999999999999, 15, 6.299999999999999, 9.7, 15.999999999999996, 9.7),
            Block.box(7, 11, 8, 9, 15, 8),
            Block.box(8, 11, 7, 8, 15, 9)
    );

    public FancyLanternBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(stateDefinition.any().setValue(HANGING, false).setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HANGING, LIT);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                return blockstate;
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            boolean currentState = state.getValue(LIT);
            boolean newState = !currentState;
            world.setBlockAndUpdate(pos, state.setValue(LIT, newState));
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return state.getValue(HANGING) ? HANGING_SHAPE : NON_HANGING_SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState p_153479_, LevelReader p_153480_, BlockPos p_153481_) {
        Direction direction = getConnectedDirection(p_153479_).getOpposite();
        return Block.canSupportCenter(p_153480_, p_153481_.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState p_153496_) {
        return p_153496_.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @SuppressWarnings("deprecation")
    @Override
    public PushReaction getPistonPushReaction(BlockState p_153494_) {
        return PushReaction.DESTROY;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState p_153483_, Direction p_153484_, BlockState p_153485_, LevelAccessor p_153486_, BlockPos p_153487_, BlockPos p_153488_) {
        return getConnectedDirection(p_153483_).getOpposite() == p_153484_ && !p_153483_.canSurvive(p_153486_, p_153487_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_153483_, p_153484_, p_153485_, p_153486_, p_153487_, p_153488_);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isPathfindable(BlockState p_153469_, BlockGetter p_153470_, BlockPos p_153471_, PathComputationType p_153472_) {
        return false;
    }
}
