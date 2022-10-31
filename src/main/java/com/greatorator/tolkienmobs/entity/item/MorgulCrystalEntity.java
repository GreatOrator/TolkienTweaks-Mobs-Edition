package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.entity.boss.fellbeast.FellBeastEntity;
import com.greatorator.tolkienmobs.entity.boss.fellbeast.phase.FellBeastFightManager;
import com.greatorator.tolkienmobs.world.server.TTMServerWorld;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MorgulCrystalEntity extends Entity {
   private static final DataParameter<Optional<BlockPos>> DATA_BEAM_TARGET = EntityDataManager.defineId(MorgulCrystalEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
   private static final DataParameter<Boolean> DATA_SHOW_BOTTOM = EntityDataManager.defineId(MorgulCrystalEntity.class, DataSerializers.BOOLEAN);
   public int time;

   public MorgulCrystalEntity(EntityType<? extends MorgulCrystalEntity> p_i50231_1_, World p_i50231_2_) {
      super(p_i50231_1_, p_i50231_2_);
      this.blocksBuilding = true;
      this.time = this.random.nextInt(100000);
   }

   public MorgulCrystalEntity(World p_i1699_1_, double p_i1699_2_, double p_i1699_4_, double p_i1699_6_) {
      this(EntityGenerator.MORGUL_CRYSTAL.get(), p_i1699_1_);
      this.setPos(p_i1699_2_, p_i1699_4_, p_i1699_6_);
   }

   @Override
   protected boolean isMovementNoisy() {
      return false;
   }

   @Override
   protected void defineSynchedData() {
      this.getEntityData().define(DATA_BEAM_TARGET, Optional.empty());
      this.getEntityData().define(DATA_SHOW_BOTTOM, true);
   }

   @Override
   public void tick() {
      ++this.time;
      if (this.level instanceof TTMServerWorld) {
         BlockPos blockpos = this.blockPosition();
         if (((TTMServerWorld)this.level).fellbeastFight() != null && this.level.getBlockState(blockpos).isAir()) {
            this.level.setBlockAndUpdate(blockpos, AbstractFireBlock.getState(this.level, blockpos));
         }
      }

   }

   @Override
   protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
      if (this.getBeamTarget() != null) {
         p_213281_1_.put("BeamTarget", NBTUtil.writeBlockPos(this.getBeamTarget()));
      }

      p_213281_1_.putBoolean("ShowBottom", this.showsBottom());
   }

   @Override
   protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
      if (p_70037_1_.contains("BeamTarget", 10)) {
         this.setBeamTarget(NBTUtil.readBlockPos(p_70037_1_.getCompound("BeamTarget")));
      }

      if (p_70037_1_.contains("ShowBottom", 1)) {
         this.setShowBottom(p_70037_1_.getBoolean("ShowBottom"));
      }

   }

   @Override
   public boolean isPickable() {
      return true;
   }

   @Override
   public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
      if (this.isInvulnerableTo(p_70097_1_)) {
         return false;
      } else if (p_70097_1_.getEntity() instanceof FellBeastEntity) {
         return false;
      } else {
         if (!this.removed && !this.level.isClientSide) {
            this.remove();
            if (!p_70097_1_.isExplosion()) {
               this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 6.0F, Explosion.Mode.DESTROY);
            }

            this.onDestroyedBy(p_70097_1_);
         }

         return true;
      }
   }

   @Override
   public void kill() {
      this.onDestroyedBy(DamageSource.GENERIC);
      super.kill();
   }

   private void onDestroyedBy(DamageSource p_184519_1_) {
      if (this.level instanceof TTMServerWorld) {
         FellBeastFightManager fellbeastfightmanager = ((TTMServerWorld)this.level).fellbeastFight();
         if (fellbeastfightmanager != null) {
            fellbeastfightmanager.onCrystalDestroyed(this, p_184519_1_);
         }
      }

   }

   public void setBeamTarget(@Nullable BlockPos p_184516_1_) {
      this.getEntityData().set(DATA_BEAM_TARGET, Optional.ofNullable(p_184516_1_));
   }

   @Nullable
   public BlockPos getBeamTarget() {
      return this.getEntityData().get(DATA_BEAM_TARGET).orElse((BlockPos)null);
   }

   public void setShowBottom(boolean p_184517_1_) {
      this.getEntityData().set(DATA_SHOW_BOTTOM, p_184517_1_);
   }

   public boolean showsBottom() {
      return this.getEntityData().get(DATA_SHOW_BOTTOM);
   }

   @OnlyIn(Dist.CLIENT)
   @Override
   public boolean shouldRenderAtSqrDistance(double p_70112_1_) {
      return super.shouldRenderAtSqrDistance(p_70112_1_) || this.getBeamTarget() != null;
   }

   @Nonnull
   @Override
   public IPacket<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}