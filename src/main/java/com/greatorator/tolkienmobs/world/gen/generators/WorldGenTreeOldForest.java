//package com.greatorator.tolkienmobs.world.gen.generators;
//
//import com.google.common.collect.Lists;
//import com.greatorator.tolkienmobs.block.BlockLogs;
//import com.greatorator.tolkienmobs.handler.TTMGenerator;
//import com.greatorator.tolkienmobs.handler.TTMTreeGenerator;
//import net.minecraft.block.*;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyBool;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//import java.util.List;
//import java.util.Random;
//
//public class WorldGenTreeOldForest extends TTMTreeGenerator {
//
//    protected int minHeight = 15;
//    protected int chanceAddFirstFive = 3;
//    protected int chanceAddSecondFive = 8;
//
//    private List<BlockPos> leaves = Lists.newArrayList();
//
//    public WorldGenTreeOldForest() {
//        this(false);
//    }
//
//    public WorldGenTreeOldForest(boolean notify) {
//        super(notify);
//        treeState = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
//        branchState = treeState.withProperty(BlockLogs.LOG_AXIS, BlockLog.EnumAxis.NONE);
//        leafState = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
//        rootState = Blocks.LOG.getDefaultState();
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos pos) {
//        int i;
//        i = random.nextInt(4) + 5;
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
//        leaves.clear();
//
//        //okay build a tree!  Go up to the height
//        buildTrunk(world, pos, treeHeight);
//
//        //okay build a tree!  Go up to the height
//        buildBranch(world, pos, 0, treeHeight, 0, 0, true, random);
//
//        // make 12 - 31 branches
//        int numBranches = 12 + random.nextInt(20);
//        float bangle = random.nextFloat();
//        for (int b = 0; b < numBranches; b++) {
//            float btilt = 0.15F + (random.nextFloat() * 0.35F);
//            buildBranch(world, pos, treeHeight - 8 + (b / 2), 7, bangle, btilt, false, random);
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
//
//            for (int i2 = pos.getY() - 3 + i; i2 <= pos.getY() + i; ++i2)
//            {
//                int k2 = i2 - (pos.getY() + i);
//                int i3 = 2 - k2 / 2;
//                BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();
//
//                for (int l3 = pos.getX() - i3; l3 <= pos.getX() + i3; ++l3)
//                {
//                    for (int j4 = pos.getZ() - i3; j4 <= pos.getZ() + i3; ++j4)
//                    {
//                        blockpos$mutableblockpos1.setPos(l3, i2, j4);
//
//                        if (world.getBlockState(blockpos$mutableblockpos1).getMaterial() == Material.LEAVES)
//                        {
//                            BlockPos blockpos3 = blockpos$mutableblockpos1.west();
//                            BlockPos blockpos4 = blockpos$mutableblockpos1.east();
//                            BlockPos blockpos1 = blockpos$mutableblockpos1.north();
//                            BlockPos blockpos2 = blockpos$mutableblockpos1.south();
//
//                            if (random.nextInt(4) == 0 && world.isAirBlock(blockpos3))
//                            {
//                                this.addVine(world, blockpos3, BlockVine.EAST);
//                            }
//
//                            if (random.nextInt(4) == 0 && world.isAirBlock(blockpos4))
//                            {
//                                this.addVine(world, blockpos4, BlockVine.WEST);
//                            }
//
//                            if (random.nextInt(4) == 0 && world.isAirBlock(blockpos1))
//                            {
//                                this.addVine(world, blockpos1, BlockVine.SOUTH);
//                            }
//
//                            if (random.nextInt(4) == 0 && world.isAirBlock(blockpos2))
//                            {
//                                this.addVine(world, blockpos2, BlockVine.NORTH);
//                            }
//                        }
//                    }
//                }
//            }
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
//        bangle = random.nextFloat();
//        for (int b = 0; b < numRoots; b++) {
//            buildRoot(world, pos, bangle, b);
//        }
//        return true;
//    }
//
//    private void addVine(World worldIn, BlockPos pos, PropertyBool prop)
//    {
//        IBlockState iblockstate = Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true));
//        this.setBlockAndNotifyAdequately(worldIn, pos, iblockstate);
//        int i = 4;
//
//        for (BlockPos blockpos = pos.down(); worldIn.isAirBlock(blockpos) && i > 0; --i)
//        {
//            this.setBlockAndNotifyAdequately(worldIn, blockpos, iblockstate);
//            blockpos = blockpos.down();
//        }
//    }
//
//    private void makeLeafBlob(World world, BlockPos leafPos)
//    {
//        TTMGenerator.drawLeafBlob(this, world, leafPos.down(), 3, leafState);
//        TTMGenerator.drawLeafBlob(this, world, leafPos, 4, leafState);
//        TTMGenerator.drawLeafBlob(this, world, leafPos.up(), 2, leafState);
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
//}