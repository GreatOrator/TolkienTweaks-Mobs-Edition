package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.TTMMithrilBarrelTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockTTMMithrilBarrel extends Block {
   public static final DirectionProperty FACING = BlockStateProperties.FACING;
   public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

   public BlockTTMMithrilBarrel(Properties properties) {
      super(properties);
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));
   }

   @SuppressWarnings("deprecation")
   @Override
   public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult trace) {
      if (!world.isClientSide) {
         TileEntity tileEntity = world.getBlockEntity(pos);
         boolean flag = state.getValue(BlockTTMMithrilBarrel.OPEN);

         if (tileEntity instanceof TTMMithrilBarrelTile) {
            NetworkHooks.openGui((ServerPlayerEntity) entity, (TTMMithrilBarrelTile) tileEntity, pos);
            PiglinTasks.angerNearbyPiglins(entity, true);
         }
         return ActionResultType.CONSUME;
      }
      return ActionResultType.SUCCESS;
   }

   @SuppressWarnings("deprecation")
   @Override
   public void onRemove(BlockState p_196243_1_, World p_196243_2_, BlockPos p_196243_3_, BlockState p_196243_4_, boolean p_196243_5_) {
      if (!p_196243_1_.is(p_196243_4_.getBlock())) {
         TileEntity tileentity = p_196243_2_.getBlockEntity(p_196243_3_);
         if (tileentity instanceof IInventory) {
            InventoryHelper.dropContents(p_196243_2_, p_196243_3_, (IInventory)tileentity);
            p_196243_2_.updateNeighbourForOutputSignal(p_196243_3_, this);
         }

         super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_);
      }
   }

   @Override
   public boolean hasTileEntity(BlockState state) {
      return true;
   }

   @Override
   protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(FACING, OPEN);
      super.createBlockStateDefinition(builder);
   }

   public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
      TileEntity tileentity = p_225534_2_.getBlockEntity(p_225534_3_);
   }

   @Nullable
   @Override
   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
      return new TTMMithrilBarrelTile();
   }

   public BlockRenderType getRenderShape(BlockState p_149645_1_) {
      return BlockRenderType.MODEL;
   }

   public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
      return true;
   }

   public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
      return Container.getRedstoneSignalFromBlockEntity(p_180641_2_.getBlockEntity(p_180641_3_));
   }

   public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
      return p_185499_1_.setValue(FACING, p_185499_2_.rotate(p_185499_1_.getValue(FACING)));
   }

   public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
      return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
   }

   public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
      return this.defaultBlockState().setValue(FACING, p_196258_1_.getNearestLookingDirection().getOpposite());
   }
}