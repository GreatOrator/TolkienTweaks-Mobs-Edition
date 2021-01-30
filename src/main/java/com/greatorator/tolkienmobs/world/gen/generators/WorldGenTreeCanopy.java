//package com.greatorator.tolkienmobs.world.gen.generators;
//
//import com.google.common.collect.Lists;
//import com.greatorator.tolkienmobs.block.BlockLeaf;
//import com.greatorator.tolkienmobs.block.BlockLogs;
//import com.greatorator.tolkienmobs.handler.TTMGenerator;
//import com.greatorator.tolkienmobs.handler.TTMTreeGenerator;
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
//public class WorldGenTreeCanopy extends TTMTreeGenerator {
//
//    protected int minHeight = 20;
//    protected int chanceAddFirstFive = 3;
//    protected int chanceAddSecondFive = 8;
//
//    private List<BlockPos> leaves = Lists.newArrayList();
//
//    public WorldGenTreeCanopy() {
//        this(false);
//    }
//
//    public WorldGenTreeCanopy(boolean notify) {
//        super(notify);
//        treeState = TTMFeatures.LOGS.getDefaultState().withProperty(BlockLogs.VARIANT, BlockLogs.EnumType.MALLORN);
//        branchState = treeState.withProperty(BlockLogs.LOG_AXIS, BlockLog.EnumAxis.NONE);
//        leafState = TTMFeatures.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, BlockLogs.EnumType.MALLORN).withProperty(BlockLeaves.CHECK_DECAY, false);
//        rootState = TTMFeatures.LOGS.getDefaultState();
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos pos) {
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
//        leaves.clear();
//
//        //okay build a tree!  Go up to the height
//        buildBranch(world, pos, 0, treeHeight, 0, 0, true, random);
//
//        // make 3-4 branches
//        int numBranches = 3 + random.nextInt(2);
//        float offset = random.nextFloat();
//        for (int b = 0; b < numBranches; b++) {
//            buildBranch(world, pos, treeHeight - 10 + b, 9, 0.3 * b + offset, 0.2, false, random);
//        }
//
//        // add the actual leaves
//        for (BlockPos leafPos : leaves) {
//            makeLeafBlob(world, leafPos);
//        }
//
//        // root bulb
//        if (TTMGenerator.hasAirAround(world, pos.down())) {
//            this.setBlockAndNotifyAdequately(world, pos.down(), treeState);
//        } else {
//            this.setBlockAndNotifyAdequately(world, pos.down(), rootState);
//        }
//
//        // roots!
//        int numRoots = 3 + random.nextInt(2);
//        offset = random.nextFloat();
//        for (int b = 0; b < numRoots; b++) {
//            buildRoot(world, pos, offset, b);
//        }
//
//
//        return true;
//    }
//
//    private void makeLeafBlob(World world, BlockPos leafPos)
//    {
//        TTMGenerator.makeLeafCircle(this, world, leafPos.down(), 3, leafState, true);
//        TTMGenerator.makeLeafCircle(this, world, leafPos, 4, leafState, true);
//        TTMGenerator.makeLeafCircle(this, world, leafPos.up(), 2, leafState, true);
//    }
//
//    /**
//     * Build a branch with a flat blob of leaves at the end.
//     */
//    public void buildBranch(World world, BlockPos pos, int height, double length, double angle, double tilt, boolean trunk, Random treeRNG) {
//        BlockPos src = pos.up(height);
//        BlockPos dest = TTMGenerator.translate(src, length, angle, tilt);
//
//        // only actually draw the branch if it's not going to load new chunks
//        if (world.isAreaLoaded(dest, 5)) {
//
//            TTMGenerator.drawBresehnam(this, world, src, dest, trunk ? treeState : branchState);
//
//            setBlockAndNotifyAdequately(world, dest.east(), branchState);
//            setBlockAndNotifyAdequately(world, dest.west(), branchState);
//            setBlockAndNotifyAdequately(world, dest.south(), branchState);
//            setBlockAndNotifyAdequately(world, dest.north(), branchState);
//
//            // save leaf position for later
//            this.leaves.add(dest);
//        }
//    }
//}