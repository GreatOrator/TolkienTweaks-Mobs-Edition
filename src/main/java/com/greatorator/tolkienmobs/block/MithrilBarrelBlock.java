package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.MithrilBarrelTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class MithrilBarrelBlock extends BaseEntityBlock {
   public static final DirectionProperty FACING = BlockStateProperties.FACING;
   public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

   public MithrilBarrelBlock(Properties properties) {
      super(properties);
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));
   }

   @SuppressWarnings("deprecation")
   @Override
   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult trace) {
      if (!world.isClientSide) {
         BlockEntity tileEntity = world.getBlockEntity(pos);

         if (tileEntity instanceof MithrilBarrelTile) {
            state.setValue(OPEN, true);
            world.playSound((Player)null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            NetworkHooks.openGui((ServerPlayer) entity, (MithrilBarrelTile) tileEntity, pos);
            PiglinAi.angerNearbyPiglins(entity, true);
         }
         return InteractionResult.CONSUME;
      }
      return InteractionResult.SUCCESS;
   }

   @SuppressWarnings("deprecation")
   @Override
   public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
      if (!blockState.is(blockState1.getBlock())) {
         BlockEntity tileentity = level.getBlockEntity(blockPos);
         if (tileentity instanceof Container) {
            Containers.dropContents(level, blockPos, (Container)tileentity);
            level.updateNeighbourForOutputSignal(blockPos, this);
         }

         super.onRemove(blockState, level, blockPos, blockState1, b);
      }
   }

   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(FACING, OPEN);
      super.createBlockStateDefinition(builder);
   }

   @SuppressWarnings("deprecation")
   @Override
   public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
      BlockEntity tileentity = level.getBlockEntity(blockPos);
   }

   @Nullable
   public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
      return new MithrilBarrelTile(blockPos, blockState);
   }

   @SuppressWarnings("deprecation")
   @Override
   public RenderShape getRenderShape(BlockState blockState) {
      return RenderShape.MODEL;
   }

   @SuppressWarnings("deprecation")
   @Override
   public boolean hasAnalogOutputSignal(BlockState blockState) {
      return true;
   }

   @SuppressWarnings("deprecation")
   @Override
   public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
      return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
   }

   @SuppressWarnings("deprecation")
   @Override
   public BlockState rotate(BlockState blockState, Rotation rotation) {
      return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
   }

   @SuppressWarnings("deprecation")
   @Override
   public BlockState mirror(BlockState blockState, Mirror mirror) {
      return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
   }
}