//package com.greatorator.tolkienmobs.world.gen.generators;
//
//import com.greatorator.tolkienmobs.block.BlockLeaf;
//import com.greatorator.tolkienmobs.block.BlockLogs;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//
//import java.util.Random;
//
//public class WorldGenTestTree extends WorldGenAbstractTree {
//    public static final IBlockState LOG = TTMFeatures.LOGS.getDefaultState().withProperty(BlockLogs.VARIANT, BlockLogs.EnumType.MALLORN);
//    public static final IBlockState LEAF = TTMFeatures.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, BlockLogs.EnumType.MALLORN).withProperty(BlockLeaf.CHECK_DECAY, false);
//    private final int minTreeHeight = 16;
//    private BlockPos basePos = BlockPos.ORIGIN;
//
//    public WorldGenTestTree(boolean notify) {
//        super(notify);
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos position) {
//        if (position.getY() < 10 || position.getY() > 240) return false;
//        if (world.getBlockState(position.down()).getBlock() != Blocks.GRASS) return false;
//        for (int y = position.getY(); y < position.getY() + (minTreeHeight + 7); y++) {
//            if (world.isChunkGeneratedAt(position.getX() >> 4, position.getZ() >> 4))
//                this.setBlockAndNotifyAdequately(world, new BlockPos(position.getX(),y,position.getZ()), LOG);
//        }
//        return true;
//    }
//}