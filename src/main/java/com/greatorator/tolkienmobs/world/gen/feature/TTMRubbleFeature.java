package com.greatorator.tolkienmobs.world.gen.feature;

//
//public class TTMRubbleFeature extends Feature<NoFeatureConfig> {
//    public TTMRubbleFeature(Codec<NoFeatureConfig> configIn) {
//        super(configIn);
//    }
//
//    @Override
//    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
//        if (world.getBlockState(pos.below()).getBlock() == Blocks.GRASS_BLOCK) {
//            for (int j1 = 0; j1 < 75; ++j1) {
//                BlockPos randomPos = pos.offset(rand.nextInt(8), rand.nextInt(4), rand.nextInt(8));
//                Material material6 = world.getBlockState(randomPos.below()).getMaterial();
//                if(rand.nextInt(10) == 0) {
//                    if (world.isEmptyBlock(randomPos) && material6.isSolid()) {
//                        Block block = Blocks.COBBLESTONE;
//                        int chance = rand.nextInt(31);
//                        if (chance < 10) {
//                            block = Blocks.COBBLESTONE;
//                        } else if (chance >= 10 && chance < 20) {
//                            block = Blocks.MOSSY_COBBLESTONE;
//                        } else if (chance >= 20 && chance < 30) {
//                            block = Blocks.GRANITE;
//                        } else {
//                            block = Blocks.CRACKED_STONE_BRICKS;
//                        }
//
//                        world.setBlock(randomPos, block.defaultBlockState(), 3);
//                    }
//                }
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
