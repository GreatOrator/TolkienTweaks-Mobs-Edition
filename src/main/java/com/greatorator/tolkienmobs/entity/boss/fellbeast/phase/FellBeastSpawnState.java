package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public enum FellBeastSpawnState {
//   START {
//      public void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_) {
//         BlockPos blockpos = new BlockPos(0, 128, 0);
//
//         for(MorgulCrystalEntity endercrystalentity : p_186079_3_) {
//            endercrystalentity.setBeamTarget(blockpos);
//         }
//
//         p_186079_2_.setRespawnStage(PREPARING_TO_SUMMON_PILLARS);
//      }
//   },
//   PREPARING_TO_SUMMON_PILLARS {
//      public void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_) {
//         if (p_186079_4_ < 100) {
//            if (p_186079_4_ == 0 || p_186079_4_ == 50 || p_186079_4_ == 51 || p_186079_4_ == 52 || p_186079_4_ >= 95) {
//               p_186079_1_.levelEvent(3001, new BlockPos(0, 128, 0), 0);
//            }
//         } else {
//            p_186079_2_.setRespawnStage(SUMMONING_PILLARS);
//         }
//
//      }
//   },
//   SUMMONING_PILLARS {
//      public void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_) {
//         int i = 40;
//         boolean flag = p_186079_4_ % 40 == 0;
//         boolean flag1 = p_186079_4_ % 40 == 39;
//         if (flag || flag1) {
//            List<EndSpikeFeature.EndSpike> list = EndSpikeFeature.getSpikesForLevel(p_186079_1_);
//            int j = p_186079_4_ / 40;
//            if (j < list.size()) {
//               EndSpikeFeature.EndSpike endspikefeature$endspike = list.get(j);
//               if (flag) {
//                  for(MorgulCrystalEntity endercrystalentity : p_186079_3_) {
//                     endercrystalentity.setBeamTarget(new BlockPos(endspikefeature$endspike.getCenterX(), endspikefeature$endspike.getHeight() + 1, endspikefeature$endspike.getCenterZ()));
//                  }
//               } else {
//                  int k = 10;
//
//                  for(BlockPos blockpos : BlockPos.betweenClosed(new BlockPos(endspikefeature$endspike.getCenterX() - 10, endspikefeature$endspike.getHeight() - 10, endspikefeature$endspike.getCenterZ() - 10), new BlockPos(endspikefeature$endspike.getCenterX() + 10, endspikefeature$endspike.getHeight() + 10, endspikefeature$endspike.getCenterZ() + 10))) {
//                     p_186079_1_.removeBlock(blockpos, false);
//                  }
//
//                  p_186079_1_.explode((Entity)null, (double)((float)endspikefeature$endspike.getCenterX() + 0.5F), (double)endspikefeature$endspike.getHeight(), (double)((float)endspikefeature$endspike.getCenterZ() + 0.5F), 5.0F, Explosion.Mode.DESTROY);
//                  EndSpikeFeatureConfig endspikefeatureconfig = new EndSpikeFeatureConfig(true, ImmutableList.of(endspikefeature$endspike), new BlockPos(0, 128, 0));
//                  Feature.END_SPIKE.configured(endspikefeatureconfig).place(p_186079_1_, p_186079_1_.getChunkSource().getGenerator(), new Random(), new BlockPos(endspikefeature$endspike.getCenterX(), 45, endspikefeature$endspike.getCenterZ()));
//               }
//            } else if (flag) {
//               p_186079_2_.setRespawnStage(SUMMONING_DRAGON);
//            }
//         }
//
//      }
//   },
//   SUMMONING_DRAGON {
//      public void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_) {
//         if (p_186079_4_ >= 100) {
//            p_186079_2_.setRespawnStage(END);
//            p_186079_2_.resetSpikeCrystals();
//
//            for(MorgulCrystalEntity endercrystalentity : p_186079_3_) {
//               endercrystalentity.setBeamTarget((BlockPos)null);
//               p_186079_1_.explode(endercrystalentity, endercrystalentity.getX(), endercrystalentity.getY(), endercrystalentity.getZ(), 6.0F, Explosion.Mode.NONE);
//               endercrystalentity.remove();
//            }
//         } else if (p_186079_4_ >= 80) {
//            p_186079_1_.levelEvent(3001, new BlockPos(0, 128, 0), 0);
//         } else if (p_186079_4_ == 0) {
//            for(MorgulCrystalEntity endercrystalentity1 : p_186079_3_) {
//               endercrystalentity1.setBeamTarget(new BlockPos(0, 128, 0));
//            }
//         } else if (p_186079_4_ < 5) {
//            p_186079_1_.levelEvent(3001, new BlockPos(0, 128, 0), 0);
//         }
//
//      }
//   },
//   END {
//      public void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_) {
//      }
//   };
//
//   private FellBeastSpawnState() {
//   }
//
//   public abstract void tick(ServerWorld p_186079_1_, FellBeastFightManager p_186079_2_, List<MorgulCrystalEntity> p_186079_3_, int p_186079_4_, BlockPos p_186079_5_);
//}