package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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

   @SuppressWarnings("deprecation")
   @Override
   public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
      if (!world.isClientSide) {
         TileEntity tile = world.getBlockEntity(pos);
         if (tile instanceof BackpackTile) {
            ((BackpackTile) tile).onRightClick(player, hand);
         }
         return ActionResultType.CONSUME;
      }
      return ActionResultType.SUCCESS;
   }

   @Override
   public boolean hasTileEntity(BlockState state) {
      return true;
   }

   @Override
   protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(FACING);
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
      return new BackpackTile();
   }
}