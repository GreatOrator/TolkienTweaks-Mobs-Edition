package com.greatorator.tolkienmobs.entity.boss.fellbeast.phase;

//
//public class FellBeastHoldingPatternPhase extends FellBeastPhase {
//   private static final EntityPredicate NEW_TARGET_TARGETING = (new EntityPredicate()).range(64.0D);
//   private Path currentPath;
//   private Vector3d targetLocation;
//   private boolean clockwise;
//
//   public FellBeastHoldingPatternPhase(FellBeastEntity p_i46791_1_) {
//      super(p_i46791_1_);
//   }
//
//   public FellBeastPhaseType<FellBeastHoldingPatternPhase> getPhase() {
//      return FellBeastPhaseType.HOLDING_PATTERN;
//   }
//
//   public void doServerTick() {
//      double d0 = this.targetLocation == null ? 0.0D : this.targetLocation.distanceToSqr(this.fellbeast.getX(), this.fellbeast.getY(), this.fellbeast.getZ());
//      if (d0 < 100.0D || d0 > 22500.0D || this.fellbeast.horizontalCollision || this.fellbeast.verticalCollision) {
//         this.findNewTarget();
//      }
//
//   }
//
//   public void begin() {
//      this.currentPath = null;
//      this.targetLocation = null;
//   }
//
//   @Nullable
//   public Vector3d getFlyTargetLocation() {
//      return this.targetLocation;
//   }
//
//   private void findNewTarget() {
//      if (this.currentPath != null && this.currentPath.isDone()) {
//         BlockPos blockpos = this.fellbeast.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(EndPodiumFeature.END_PODIUM_LOCATION));
//         int i = this.fellbeast.getFellBeastFight() == null ? 0 : this.fellbeast.getFellBeastFight().getCrystalsAlive();
//         if (this.fellbeast.getRandom().nextInt(i + 3) == 0) {
//            this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.LANDING_APPROACH);
//            return;
//         }
//
//         double d0 = 64.0D;
//         PlayerEntity playerentity = this.fellbeast.level.getNearestPlayer(NEW_TARGET_TARGETING, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
//         if (playerentity != null) {
//            d0 = blockpos.distSqr(playerentity.position(), true) / 512.0D;
//         }
//
//         if (playerentity != null && !playerentity.abilities.invulnerable && (this.fellbeast.getRandom().nextInt(MathHelper.abs((int)d0) + 2) == 0 || this.fellbeast.getRandom().nextInt(i + 2) == 0)) {
//            this.strafePlayer(playerentity);
//            return;
//         }
//      }
//
//      if (this.currentPath == null || this.currentPath.isDone()) {
//         int j = this.fellbeast.findClosestNode();
//         int k = j;
//         if (this.fellbeast.getRandom().nextInt(8) == 0) {
//            this.clockwise = !this.clockwise;
//            k = j + 6;
//         }
//
//         if (this.clockwise) {
//            ++k;
//         } else {
//            --k;
//         }
//
//         if (this.fellbeast.getFellBeastFight() != null && this.fellbeast.getFellBeastFight().getCrystalsAlive() >= 0) {
//            k = k % 12;
//            if (k < 0) {
//               k += 12;
//            }
//         } else {
//            k = k - 12;
//            k = k & 7;
//            k = k + 12;
//         }
//
//         this.currentPath = this.fellbeast.findPath(j, k, (PathPoint)null);
//         if (this.currentPath != null) {
//            this.currentPath.advance();
//         }
//      }
//
//      this.navigateToNextPathNode();
//   }
//
//   private void strafePlayer(PlayerEntity p_188674_1_) {
//      this.fellbeast.getFellBeastPhaseManager().setPhase(FellBeastPhaseType.STRAFE_PLAYER);
//      this.fellbeast.getFellBeastPhaseManager().getPhase(FellBeastPhaseType.STRAFE_PLAYER).setTarget(p_188674_1_);
//   }
//
//   private void navigateToNextPathNode() {
//      if (this.currentPath != null && !this.currentPath.isDone()) {
//         Vector3i vector3i = this.currentPath.getNextNodePos();
//         this.currentPath.advance();
//         double d0 = (double)vector3i.getX();
//         double d1 = (double)vector3i.getZ();
//
//         double d2;
//         do {
//            d2 = (double)((float)vector3i.getY() + this.fellbeast.getRandom().nextFloat() * 20.0F);
//         } while(d2 < (double)vector3i.getY());
//
//         this.targetLocation = new Vector3d(d0, d2, d1);
//      }
//
//   }
//
//   public void onCrystalDestroyed(MorgulCrystalEntity p_188655_1_, BlockPos p_188655_2_, DamageSource p_188655_3_, @Nullable PlayerEntity p_188655_4_) {
//      if (p_188655_4_ != null && !p_188655_4_.abilities.invulnerable) {
//         this.strafePlayer(p_188655_4_);
//      }
//
//   }
//}