package com.greatorator.tolkienmobs.entity.boss.fellbeast;

//
//public class FellBeastPartEntity extends PartEntity<FellBeastEntity> {
//   public final FellBeastEntity parentMob;
//   public final String name;
//   private final EntitySize size;
//
//   public FellBeastPartEntity(FellBeastEntity p_i50232_1_, String p_i50232_2_, float p_i50232_3_, float p_i50232_4_) {
//      super(p_i50232_1_);
//      this.size = EntitySize.scalable(p_i50232_3_, p_i50232_4_);
//      this.refreshDimensions();
//      this.parentMob = p_i50232_1_;
//      this.name = p_i50232_2_;
//   }
//
//   @Override
//   protected void defineSynchedData() {
//   }
//
//   @Override
//   protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
//   }
//
//   @Override
//   protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
//   }
//
//   @Override
//   public boolean isPickable() {
//      return true;
//   }
//
//   @Override
//   public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
//      return this.isInvulnerableTo(p_70097_1_) ? false : this.parentMob.hurt(this, p_70097_1_, p_70097_2_);
//   }
//
//   @Override
//   public boolean is(Entity p_70028_1_) {
//      return this == p_70028_1_ || this.parentMob == p_70028_1_;
//   }
//
//   @Override
//   public IPacket<?> getAddEntityPacket() {
//      throw new UnsupportedOperationException();
//   }
//
//   @Override
//   public EntitySize getDimensions(Pose p_213305_1_) {
//      return this.size;
//   }
//}
