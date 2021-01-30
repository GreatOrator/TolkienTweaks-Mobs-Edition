//package com.greatorator.tolkienmobs.world.gen.generators;
//
//import com.google.common.collect.Lists;
//import com.greatorator.tolkienmobs.block.BlockLeaf;
//import com.greatorator.tolkienmobs.block.BlockLogs;
//import com.greatorator.tolkienmobs.handler.TTMGenerator;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.BlockLeaves;
//import net.minecraft.block.BlockLog;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//import java.util.List;
//import java.util.Random;
//
///** Borrowed from Twilight Forest */
//public class WorldGenTreeMallorn extends WorldGenTreeCanopy {
//
//    private List<BlockPos> leaves = Lists.newArrayList();
//
//    public WorldGenTreeMallorn() {
//        this(false);
//    }
//
//    public WorldGenTreeMallorn(boolean notify) {
//        super(notify);
//        treeState = TTMFeatures.LOGS.getDefaultState().withProperty(BlockLogs.VARIANT, BlockLogs.EnumType.MALLORN);
//        branchState = treeState.withProperty(BlockLogs.LOG_AXIS, BlockLog.EnumAxis.NONE);
//        leafState = TTMFeatures.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, BlockLogs.EnumType.MALLORN).withProperty(BlockLeaves.CHECK_DECAY, false);
//        rootState = TTMFeatures.LOGS.getDefaultState();
//
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos pos) {
//
//        // determine a height
//        int treeHeight = minHeight;
//        if (random.nextInt(chanceAddFirstFive) == 0) {
//            treeHeight += random.nextInt(5);
//
//            if (random.nextInt(chanceAddSecondFive) == 0) {
//                treeHeight += random.nextInt(5);
//            }
//        }
//
//        if (pos.getY() >= 256 - treeHeight) {
//            return false;
//        }
//
//        // check if we're on dirt or grass
//        IBlockState state = world.getBlockState(pos.down());
//        if (!state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, source)) {
//            return false;
//        }
//
//        this.leaves.clear();
//
//        //okay build a tree!  Go up to the height
//        buildTrunk(world, pos, treeHeight);
//
//        // make 12 - 20 branches
//        int numBranches = 12 + random.nextInt(9);
//        float bangle = random.nextFloat();
//        for (int b = 0; b < numBranches; b++) {
//            float btilt = 0.15F + (random.nextFloat() * 0.35F);
//            buildBranch(world, pos, treeHeight - 10 + (b / 2), 5, bangle, btilt, false, random);
//
//            bangle += (random.nextFloat() * 0.4F);
//            if (bangle > 1.0F) {
//                bangle -= 1.0F;
//            }
//        }
//
//        // add the actual leaves
//        for (BlockPos leafPos : leaves) {
//            makeLeafBlob(world, leafPos);
//        }
//
//        makeRoots(world, random, pos);
//        makeRoots(world, random, pos.east());
//        makeRoots(world, random, pos.south());
//        makeRoots(world, random, pos.east().south());
//
//        return true;
//    }
//
//    private void makeLeafBlob(World world, BlockPos leafPos)
//    {
//        TTMGenerator.drawLeafBlob(this, world, leafPos, 2, leafState);
//    }
//
//    private void makeRoots(World world, Random random, BlockPos pos) {
//        // root bulb
//        if (TTMGenerator.hasAirAround(world, pos.down())) {
//            this.setBlockAndNotifyAdequately(world, pos.down(), treeState);
//        } else {
//            this.setBlockAndNotifyAdequately(world, pos.down(), rootState);
//        }
//
//        // roots!
//        int numRoots = 1 + random.nextInt(2);
//        float offset = random.nextFloat();
//        for (int b = 0; b < numRoots; b++) {
//            buildRoot(world, pos, offset, b);
//        }
//    }
//
//    private void buildTrunk(World world, BlockPos pos, int treeHeight) {
//        for (int dy = 0; dy < treeHeight; dy++) {
//            this.setBlockAndNotifyAdequately(world, pos.add(0, dy, 0), treeState);
//            this.setBlockAndNotifyAdequately(world, pos.add(1, dy, 0), treeState);
//            this.setBlockAndNotifyAdequately(world, pos.add(0, dy, 1), treeState);
//            this.setBlockAndNotifyAdequately(world, pos.add(1, dy, 1), treeState);
//        }
//
//        this.leaves.add(pos.add(0, treeHeight, 0));
//    }
//
//    /**
//     * Build a branch with a flat blob of leaves at the end.
//     */
//    @Override
//    public void buildBranch(World world, BlockPos pos, int height, double length, double angle, double tilt, boolean trunk, Random treeRNG) {
//        BlockPos src = pos.up(height);
//        BlockPos dest = TTMGenerator.translate(src, length, angle, tilt);
//
//        // constrain branch spread
//        int limit = 5;
//        if ((dest.getX() - pos.getX()) < -limit) {
//            dest = new BlockPos(pos.getX() - limit, dest.getY(), dest.getZ());
//        }
//        if ((dest.getX() - pos.getX()) > limit) {
//            dest = new BlockPos(pos.getX() + limit, dest.getY(), dest.getZ());
//        }
//        if ((dest.getZ() - pos.getZ()) < -limit) {
//            dest = new BlockPos(dest.getX(), dest.getY(), pos.getZ() - limit);
//        }
//        if ((dest.getZ() - pos.getZ()) > limit) {
//            dest = new BlockPos(dest.getX(), dest.getY(), pos.getZ() + limit);
//        }
//
//        TTMGenerator.drawBresehnam(this, world, src, dest, trunk ? treeState : branchState);
//
//        setBlockAndNotifyAdequately(world, dest.east(), branchState);
//        setBlockAndNotifyAdequately(world, dest.west(), branchState);
//        setBlockAndNotifyAdequately(world, dest.north(), branchState);
//        setBlockAndNotifyAdequately(world, dest.south(), branchState);
//
//        this.leaves.add(dest);
//    }
//}