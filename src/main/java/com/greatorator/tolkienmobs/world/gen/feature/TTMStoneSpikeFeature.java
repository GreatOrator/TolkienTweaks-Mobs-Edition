package com.greatorator.tolkienmobs.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static slimeknights.tconstruct.TConstruct.random;

public class TTMStoneSpikeFeature extends Feature<NoFeatureConfig> {
   public TTMStoneSpikeFeature(Codec<NoFeatureConfig> p_i231962_1_) {
      super(p_i231962_1_);
   }

   @Override
   public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
      while (world.isEmptyBlock(pos) && pos.getY() > 2) {
         pos = pos.below();
      }

      if (world.getBlockState(pos).getBlock() != Blocks.GRASS) {
         return false;
      } else {
         pos = pos.above(random.nextInt(4));
         int i = random.nextInt(4) + 7;
         int j = i / 4 + random.nextInt(2);

         if (j > 1 && random.nextInt(60) == 0) {
            pos = pos.above(10 + random.nextInt(30));
         }

         for (int k = 0; k < i; ++k) {
            float f = (1.0F - (float) k / (float) i) * (float) j;
            int l = MathHelper.ceil(f);

            for (int i1 = -l; i1 <= l; ++i1) {
               float f1 = (float) MathHelper.abs(i1) - 0.25F;

               for (int j1 = -l; j1 <= l; ++j1) {
                  float f2 = (float) MathHelper.abs(j1) - 0.25F;

                  if ((i1 == 0 && j1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (i1 != -l && i1 != l && j1 != -l && j1 != l || random.nextFloat() <= 0.75F)) {
                     BlockState blockstate = world.getBlockState(pos.offset(i1, k, j1));
                     Block block = blockstate.getBlock();

                     if (blockstate.getBlock().isAir(blockstate, world, pos.offset(i1, k, j1)) || block == Blocks.DIRT || block == Blocks.GRASS) {
                        this.setBlock(world, pos.offset(i1, k, j1), Blocks.STONE.defaultBlockState());
                     }

                     if (k != 0 && l > 1) {
                        blockstate = world.getBlockState(pos.offset(i1, -k, j1));
                        block = blockstate.getBlock();

                        if (blockstate.getBlock().isAir(blockstate, world, pos.offset(i1, -k, j1)) || block == Blocks.DIRT || block == Blocks.GRASS) {
                           this.setBlock(world, pos.offset(i1, -k, j1), Blocks.STONE.defaultBlockState());
                        }
                     }
                  }
               }
            }
         }

         int k1 = j - 1;

         if (k1 < 0) {
            k1 = 0;
         } else if (k1 > 1) {
            k1 = 1;
         }

         for (int l1 = -k1; l1 <= k1; ++l1) {
            for (int i2 = -k1; i2 <= k1; ++i2) {
               BlockPos blockpos = pos.offset(l1, -1, i2);
               int j2 = 50;

               if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                  j2 = random.nextInt(5);
               }

               while (blockpos.getY() > 50) {
                  BlockState blockstate1 = world.getBlockState(blockpos);
                  Block block1 = blockstate1.getBlock();

                  if (!blockstate1.getBlock().isAir(blockstate1, world, blockpos) && block1 != Blocks.DIRT && block1 != Blocks.GRASS) {
                     break;
                  }

                  this.setBlock(world, blockpos, Blocks.STONE.defaultBlockState());
                  blockpos = blockpos.below();
                  --j2;

                  if (j2 <= 0) {
                     blockpos = blockpos.below(random.nextInt(5) + 1);
                     j2 = random.nextInt(5);
                  }
               }
            }
         }
      }
      return true;
//      while(p_241855_1_.isEmptyBlock(p_241855_4_) && p_241855_4_.getY() > 2) {
//         p_241855_4_ = p_241855_4_.below();
//      }
//
//      if (!p_241855_1_.getBlockState(p_241855_4_).is(Blocks.GRASS_BLOCK)) {
//         return false;
//      } else {
//         p_241855_4_ = p_241855_4_.above(p_241855_3_.nextInt(4));
//         int i = p_241855_3_.nextInt(4) + 7;
//         int j = i / 4 + p_241855_3_.nextInt(2);
//         if (j > 1 && p_241855_3_.nextInt(60) == 0) {
//            p_241855_4_ = p_241855_4_.above(10 + p_241855_3_.nextInt(30));
//         }
//
//         for(int k = 0; k < i; ++k) {
//            float f = (1.0F - (float)k / (float)i) * (float)j;
//            int l = MathHelper.ceil(f);
//
//            for(int i1 = -l; i1 <= l; ++i1) {
//               float f1 = (float)MathHelper.abs(i1) - 0.25F;
//
//               for(int j1 = -l; j1 <= l; ++j1) {
//                  float f2 = (float)MathHelper.abs(j1) - 0.25F;
//                  if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(p_241855_3_.nextFloat() > 0.75F))) {
//                     BlockState blockstate = p_241855_1_.getBlockState(p_241855_4_.offset(i1, k, j1));
//                     Block block = blockstate.getBlock();
//                     if (blockstate.isAir(p_241855_1_, p_241855_4_.offset(i1, k, j1)) || isDirt(block) || block == Blocks.COBBLESTONE || block == Blocks.STONE) {
//                        this.setBlock(p_241855_1_, p_241855_4_.offset(i1, k, j1), Blocks.STONE.defaultBlockState());
//                     }
//
//                     if (k != 0 && l > 1) {
//                        blockstate = p_241855_1_.getBlockState(p_241855_4_.offset(i1, -k, j1));
//                        block = blockstate.getBlock();
//                        if (blockstate.isAir(p_241855_1_, p_241855_4_.offset(i1, -k, j1)) || isDirt(block) || block == Blocks.COBBLESTONE || block == Blocks.STONE) {
//                           this.setBlock(p_241855_1_, p_241855_4_.offset(i1, -k, j1), Blocks.STONE.defaultBlockState());
//                        }
//                     }
//                  }
//               }
//            }
//         }
//
//         int k1 = j - 1;
//         if (k1 < 0) {
//            k1 = 0;
//         } else if (k1 > 1) {
//            k1 = 1;
//         }
//
//         for(int l1 = -k1; l1 <= k1; ++l1) {
//            for(int i2 = -k1; i2 <= k1; ++i2) {
//               BlockPos blockpos = p_241855_4_.offset(l1, -1, i2);
//               int j2 = 50;
//               if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
//                  j2 = p_241855_3_.nextInt(5);
//               }
//
//               while(blockpos.getY() > 50) {
//                  BlockState blockstate1 = p_241855_1_.getBlockState(blockpos);
//                  Block block1 = blockstate1.getBlock();
//                  if (!blockstate1.isAir(p_241855_1_, blockpos) && !isDirt(block1) && block1 != Blocks.COBBLESTONE && block1 != Blocks.STONE) {
//                     break;
//                  }
//
//                  this.setBlock(p_241855_1_, blockpos, Blocks.STONE.defaultBlockState());
//                  blockpos = blockpos.below();
//                  --j2;
//                  if (j2 <= 0) {
//                     blockpos = blockpos.below(p_241855_3_.nextInt(5) + 1);
//                     j2 = p_241855_3_.nextInt(5);
//                  }
//               }
//            }
//         }
//
//         return true;
//      }
   }
}
