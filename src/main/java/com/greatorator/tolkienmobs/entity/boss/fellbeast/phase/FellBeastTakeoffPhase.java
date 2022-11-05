package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastTakeoffPhase extends FellBeastPhase {
//   private boolean firstTick;
//   private Path currentPath;
//   private Vector3d targetLocation;
//
//   public FellBeastTakeoffPhase(FellBeastEntity p_i46783_1_) {
//      super(p_i46783_1_);
//   }
//
//   public void doServerTick() {
//      if (!this.firstTick && this.currentPath != null) {
//         BlockPos blockpos = this.fellbeast.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
//         if (!blockpos.closerThan(this.fellbeast.position(), 10.0D)) {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.HOLDING_PATTERN);
//         }
//      } else {
//         this.firstTick = false;
//         this.findNewTarget();
//      }
//
//   }
//
//   public void begin() {
//      this.firstTick = true;
//      this.currentPath = null;
//      this.targetLocation = null;
//   }
//
//   private void findNewTarget() {
//      int i = this.fellbeast.findClosestNode();
//      Vector3d vector3d = this.fellbeast.getHeadLookVector(1.0F);
//      int j = this.fellbeast.findClosestNode(-vector3d.x * 40.0D, 105.0D, -vector3d.z * 40.0D);
//      if (this.fellbeast.getFellBeastFight() != null && this.fellbeast.getFellBeastFight().getCrystalsAlive() > 0) {
//         j = j % 12;
//         if (j < 0) {
//            j += 12;
//         }
//      } else {
//         j = j - 12;
//         j = j & 7;
//         j = j + 12;
//      }
//
//      this.currentPath = this.fellbeast.findPath(i, j, (PathPoint)null);
//      this.navigateToNextPathNode();
//   }
//
//   private void navigateToNextPathNode() {
//      if (this.currentPath != null) {
//         this.currentPath.advance();
//         if (!this.currentPath.isDone()) {
//            Vector3i vector3i = this.currentPath.getNextNodePos();
//            this.currentPath.advance();
//
//            double d0;
//            do {
//               d0 = (double)((float)vector3i.getY() + this.fellbeast.getRandom().nextFloat() * 20.0F);
//            } while(d0 < (double)vector3i.getY());
//
//            this.targetLocation = new Vector3d((double)vector3i.getX(), d0, (double)vector3i.getZ());
//         }
//      }
//
//   }
//
//   @Nullable
//   public Vector3d getFlyTargetLocation() {
//      return this.targetLocation;
//   }
//
//   public FellBeastPhaseType<FellBeastTakeoffPhase> getPhase() {
//      return FellBeastPhaseType.TAKEOFF;
//   }
//}