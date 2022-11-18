package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class CamoSpawnerBlock extends ChameleonBlock<CamoSpawnerTile> implements EntityBlock {

    public CamoSpawnerBlock(Properties properties) {
        super(properties);
        setBlockEntity(() -> TolkienTiles.CAMO_SPAWNER_TILE.get(), true); //<-- The boolean (true) specifies that this tile needs to tick. If your tile implemented ITickableTileEntity in 1.16 then this needs to be true
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        BlockEntity tile = world.getBlockEntity(pos);
        CamoSpawnerTile camoSpawner = (CamoSpawnerTile) tile;
        if (!world.isClientSide) {
            if (tile != null) {
                camoSpawner.onRightClick(state, player, hand);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction side) {
        return true;
    }
}