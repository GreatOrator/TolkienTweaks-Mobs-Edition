package com.greatorator.tolkienmobs.block;


import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockStonePath extends Block
{
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public BlockStonePath(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.getBlockState(pos.up());
        return !blockstate.getMaterial().isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos()) ? Blocks.COBBLESTONE.getDefaultState() : super.getStateForPlacement(context);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.isValidPosition(worldIn, pos)) {
            turnToCobble(state, worldIn, pos);
        }
    }

    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, Blocks.COBBLESTONE.getDefaultState(), fallDistance, entityIn)) { // Forge: Move logic to Entity#canTrample
            turnToCobble(worldIn.getBlockState(pos), worldIn, pos);
        }

        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }

    public static void turnToCobble(BlockState state, World worldIn, BlockPos pos) {
        worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, Blocks.COBBLESTONE.getDefaultState(), worldIn, pos));
    }
}