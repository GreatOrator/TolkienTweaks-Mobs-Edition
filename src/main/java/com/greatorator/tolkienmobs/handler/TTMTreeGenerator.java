package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.block.BlockLeaf;
import com.greatorator.tolkienmobs.block.BlockLogs;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMSettable;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

/** Borrowed from Twilight Forest */
public abstract class TTMTreeGenerator extends WorldGenAbstractTree implements ITTMSettable {
    protected IBlockState treeState = TTMFeatures.LOGS.getDefaultState();
    protected IBlockState branchState = TTMFeatures.LOGS.getDefaultState().withProperty(BlockLogs.LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(BlockLogs.VARIANT, BlockLogs.EnumType.MIRKWOOD);
    protected IBlockState leafState = TTMFeatures.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, BlockLogs.EnumType.MIRKWOOD);
    protected IBlockState rootState = TTMFeatures.LOGS.getDefaultState();

    protected IPlantable source = (IPlantable)TTMFeatures.SAPLINGS;

    public TTMTreeGenerator() {
        this(false);
    }

    public TTMTreeGenerator(boolean notify) {
        super(notify);
    }

    @Override
    public final void setBlockAndNotify(World world, BlockPos pos, IBlockState state) {
        setBlockAndNotifyAdequately(world, pos, state);
    }

    /**
     * Build a root, but don't let it stick out too far into thin air because that's weird
     */
    protected void buildRoot(World world, BlockPos pos, double offset, int b) {
        BlockPos dest = TTMGenerator.translate(pos.down(b + 2), 5, 0.3 * b + offset, 0.8);

        // go through block by block and stop drawing when we head too far into open air
        BlockPos[] lineArray = TTMGenerator.getBresehnamArrays(pos.down(), dest);
        for (BlockPos coord : lineArray) {
            this.placeRootBlock(world, coord, rootState);
        }
    }

    /**
     * Function used to actually place root blocks if they're not going to break anything important
     */
    protected void placeRootBlock(World world, BlockPos pos, IBlockState state) {
        if (canRootGrowIn(world, pos)) {
            this.setBlockAndNotifyAdequately(world, pos, state);
        }
    }

    public static boolean canRootGrowIn(World world, BlockPos pos) {
        IBlockState blockState = world.getBlockState(pos);
        Block blockID = blockState.getBlock();

        if (blockID.isAir(blockState, world, pos)) {
            // roots can grow through air if they are near a solid block
            return TTMGenerator.isNearSolid(world, pos);
        } else {
            return (blockState.getBlockHardness(world, pos) >= 0)
                    && (blockState.getMaterial() == Material.GRASS || blockState.getMaterial() == Material.GROUND || blockState.getMaterial() == Material.ROCK);
        }
    }

    private void setIfEmpty(World world, BlockPos pos, IBlockState state) {
        if (world.isAirBlock(pos)) {
            this.setBlockAndNotifyAdequately(world, pos, state);
        }
    }
}