package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.entity.boss.FellBeastEntity;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MorgulCrystalEntity extends Entity {
   private static final EntityDataAccessor<Optional<BlockPos>> DATA_BEAM_TARGET = SynchedEntityData.defineId(MorgulCrystalEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
   private static final EntityDataAccessor<Boolean> DATA_SHOW_BOTTOM = SynchedEntityData.defineId(MorgulCrystalEntity.class, EntityDataSerializers.BOOLEAN);
   public int time;

   public MorgulCrystalEntity(EntityType<? extends MorgulCrystalEntity> type, Level level) {
      super(type, level);
      this.blocksBuilding = true;
      this.time = this.random.nextInt(100000);
   }

   public MorgulCrystalEntity(Level level, double x, double y, double z) {
      this(TolkienEntities.MORGUL_CRYSTAL.get(), level);
      this.setPos(x, y, z);
   }

   @Override
   protected Entity.MovementEmission getMovementEmission() {
      return Entity.MovementEmission.NONE;
   }

   @Override
   protected void defineSynchedData() {
      this.getEntityData().define(DATA_BEAM_TARGET, Optional.empty());
      this.getEntityData().define(DATA_SHOW_BOTTOM, true);
   }

   @Override
   public void tick() {
      ++this.time;
      if (this.level instanceof ServerLevel) {
         BlockPos blockpos = this.blockPosition();
         if (this.level.getBlockState(blockpos).isAir()) {
            this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
         }
      }
   }

   @Override
   protected void addAdditionalSaveData(CompoundTag tag) {
      if (this.getBeamTarget() != null) {
         tag.put("BeamTarget", NbtUtils.writeBlockPos(this.getBeamTarget()));
      }

      tag.putBoolean("ShowBottom", this.showsBottom());
   }

   @Override
   protected void readAdditionalSaveData(CompoundTag tag) {
      if (tag.contains("BeamTarget", 10)) {
         this.setBeamTarget(NbtUtils.readBlockPos(tag.getCompound("BeamTarget")));
      }

      if (tag.contains("ShowBottom", 1)) {
         this.setShowBottom(tag.getBoolean("ShowBottom"));
      }

   }

   @Override
   public boolean isPickable() {
      return true;
   }

   @Override
   public boolean hurt(DamageSource source, float amount) {
      if (this.isInvulnerableTo(source)) {
         return false;
      } else if (source.getEntity() instanceof FellBeastEntity) {
         return false;
      } else {
         if (!this.isRemoved() && !this.level.isClientSide) {
            this.remove(Entity.RemovalReason.KILLED);
            if (!source.isExplosion()) {
               this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), 6.0F, Explosion.BlockInteraction.DESTROY);
            }
            this.onDestroyedBy(source);
         }
         return true;
      }
   }

   @Override
   public void kill() {
      this.onDestroyedBy(DamageSource.GENERIC);
      super.kill();
   }

   private void onDestroyedBy(DamageSource source) {
      if (this.level instanceof ServerLevel) {
//         FellBeastFightManager fellbeastfightmanager = ((TolkienServerLevel)this.level).fellbeastFight();
//         if (fellbeastfightmanager != null) {
//            fellbeastfightmanager.onCrystalDestroyed(this, source);
//         }
      }
   }

   public void setBeamTarget(@Nullable BlockPos blockPos) {
      this.getEntityData().set(DATA_BEAM_TARGET, Optional.ofNullable(blockPos));
   }

   @Nullable
   public BlockPos getBeamTarget() {
      return this.getEntityData().get(DATA_BEAM_TARGET).orElse((BlockPos)null);
   }

   public void setShowBottom(boolean show) {
      this.getEntityData().set(DATA_SHOW_BOTTOM, show);
   }

   public boolean showsBottom() {
      return this.getEntityData().get(DATA_SHOW_BOTTOM);
   }

   @OnlyIn(Dist.CLIENT)
   @Override
   public boolean shouldRenderAtSqrDistance(double distance) {
      return super.shouldRenderAtSqrDistance(distance) || this.getBeamTarget() != null;
   }

   @Nonnull
   @Override
   public Packet<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}