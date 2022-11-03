package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BackpackBlock extends BlockBCore {
   public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

   protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 0.0D, 5.0D, 16.0D, 14.0D, 15.0D);
   protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 12.0D);
   protected static final VoxelShape SHAPE_EAST = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 14.0D, 15.0D);
   protected static final VoxelShape SHAPE_WEST = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 14.0D, 15.0D);
   protected static final VoxelShape SHAPE_COMMON = Block.box(1.0D, 0.0D, 1.0D, 15.0, 14.0D, 15.0D);

   public BackpackBlock(Properties properties) {
      super(properties);
      this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
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
   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
      if (!world.isClientSide) {
         BlockEntity tile = world.getBlockEntity(pos);
         if (tile instanceof BackpackTile) {
            ((BackpackTile) tile).onRightClick(player, hand);
         }
         return InteractionResult.CONSUME;
      }
      return InteractionResult.SUCCESS;
   }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(FACING);
      super.createBlockStateDefinition(builder);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
   }

   @SuppressWarnings("deprecation")
   @Override
   public BlockState rotate(BlockState state, Rotation direction) {
      return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
   }

   @Nullable
   @Override
   public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
      return new BackpackTile(blockPos, blockState);
   }
}