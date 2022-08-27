package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
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
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockTTMBackpack extends Block {
   public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
   public static final BooleanProperty UPGRADE1 = BooleanProperty.create("upgrade1");
   public static final BooleanProperty UPGRADE2 = BooleanProperty.create("upgrade2");

   protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 0.0D, 8.0D, 15.0, 16.0, 16.0D);
   protected static final VoxelShape SHAPE_SOUTH = Block.box(1.0, 0.0D, 1.0, 15.0, 16.0, 9.0);
   protected static final VoxelShape SHAPE_EAST = Block.box(0.0D, 0.0D, 0.0D, 9.0, 16.0, 15.0);
   protected static final VoxelShape SHAPE_WEST = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0, 15.0);
   protected static final VoxelShape SHAPE_COMMON = Block.box(1.0, 0.0D, 1.0, 15.0, 16.0, 9.0);

   public BlockTTMBackpack(Properties properties) {
      super(properties);
      this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(UPGRADE1, false).setValue(UPGRADE2, false));
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
         TileEntity tileEntity = world.getBlockEntity(pos);

         if (tileEntity instanceof TTMBackpackTile) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (TTMBackpackTile) tileEntity, pos);
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
      builder.add(FACING, UPGRADE1, UPGRADE2);
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
      return new TTMBackpackTile();
   }
}