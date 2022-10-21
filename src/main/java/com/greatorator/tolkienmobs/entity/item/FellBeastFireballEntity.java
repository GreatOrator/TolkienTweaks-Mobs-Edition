package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.utils.TTMDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class FellBeastFireballEntity extends BaseFireballEntity {
   public int explosionPower = 1;

   public FellBeastFireballEntity(EntityType<? extends FellBeastFireballEntity> entityType, World world) {
      super(entityType, world);
   }

   @OnlyIn(Dist.CLIENT)
   public FellBeastFireballEntity(World world, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
      super(EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_, world);
   }

   public FellBeastFireballEntity(World world, LivingEntity livingEntity, double x, double y, double z) {
      super(EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), livingEntity, x, y, z, world);
   }

   @Nonnull
   @Override
   public IPacket<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   @Override
   protected void onHit(RayTraceResult rayTraceResult) {
      super.onHit(rayTraceResult);
      if (!this.level.isClientSide) {
         boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
         this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, Explosion.Mode.NONE);
         this.remove();
      }
   }

   @Override
   protected void onHitEntity(EntityRayTraceResult entityRayTraceResult) {
      super.onHitEntity(entityRayTraceResult);
      if (!this.level.isClientSide) {
         Entity entity = entityRayTraceResult.getEntity();
         Entity entity1 = this.getOwner();
         entity.hurt(TTMDamageSource.causeFellBeastDamage(this), 6.0F);
         if (entity1 instanceof LivingEntity) {
            this.doEnchantDamageEffects((LivingEntity)entity1, entity);
         }
      }
   }

   @Override
   public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
      super.addAdditionalSaveData(p_213281_1_);
      p_213281_1_.putInt("ExplosionPower", this.explosionPower);
   }

   @Override
   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
      super.readAdditionalSaveData(p_70037_1_);
      if (p_70037_1_.contains("ExplosionPower", 99)) {
         this.explosionPower = p_70037_1_.getInt("ExplosionPower");
      }
   }
}
