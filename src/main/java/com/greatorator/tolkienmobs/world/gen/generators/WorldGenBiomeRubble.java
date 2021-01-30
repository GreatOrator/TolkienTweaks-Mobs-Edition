//package com.greatorator.tolkienmobs.world.gen.generators;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.material.Material;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//
//import java.util.Random;
//
//public class WorldGenBiomeRubble extends WorldGenAbstractTree {
//
//    public WorldGenBiomeRubble(boolean notify) {
//        super(notify);
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos position) {
//        if (world.getBlockState(position.down()).getBlock() == Blocks.GRASS && !world.getBlockState(position.up()).getMaterial().isSolid() && !world.getBlockState(position).getMaterial().isSolid() && !world.getBlockState(position.south()).getMaterial().isSolid() && !world.getBlockState(position.east()).getMaterial().isSolid() && !world.getBlockState(position.west()).getMaterial().isSolid() && !world.getBlockState(position.north()).getMaterial().isSolid() && world.isAirBlock(position) && world.isAirBlock(position.up())) {
//            for (int j1 = 0; j1 < 75; ++j1) {
//                BlockPos randomPos = position.add(random.nextInt(8), random.nextInt(4), random.nextInt(8));
//                Material material6 = world.getBlockState(randomPos.down()).getMaterial();
//                if(random.nextInt(10) == 0) {
//                    if (world.isAirBlock(randomPos) && material6.isSolid()) {
//                        Block block = Blocks.COBBLESTONE;
//                        int chance = random.nextInt(31);
//                        if (chance < 10) {
//                            block = Blocks.COBBLESTONE;
//                        } else if (chance >= 10 && chance < 20) {
//                            block = Blocks.MOSSY_COBBLESTONE;
//                        } else if (chance >= 20 && chance < 30) {
//                            block = Blocks.PLANKS;
//                        } else {
//                            block = Blocks.BRICK_BLOCK;
//                        }
//
//                        world.setBlockState(randomPos, block.getDefaultState());
//                    }
//                }
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
//}