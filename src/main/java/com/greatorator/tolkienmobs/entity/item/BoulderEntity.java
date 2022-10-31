package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class BoulderEntity extends ProjectileItemEntity {
   public BoulderEntity(EntityType<? extends BoulderEntity> entityType, World world) {
      super(entityType, world);
   }

   public BoulderEntity(World world, LivingEntity livingEntity) {
      super(EntityGenerator.AMMO_BOULDER.get(), livingEntity, world);
   }

   public BoulderEntity(World world, double x, double y, double z) {
      super(EntityGenerator.AMMO_BOULDER.get(), x, y, z, world);
   }

   @Nonnull
   @Override
   public IPacket<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   @Override
   protected Item getDefaultItem() {
      return TTMContent.BOULDER.get();
   }

   @OnlyIn(Dist.CLIENT)
   private IParticleData getParticle() {
      ItemStack itemstack = this.getItemRaw();
      return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
   }

   @OnlyIn(Dist.CLIENT)
   @Override
   public void handleEntityEvent(byte statusID) {
      if (statusID == 3) {
         IParticleData iparticledata = this.getParticle();

         for(int i = 0; i < 8; ++i) {
            this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   @Override
   protected void onHitEntity(EntityRayTraceResult rayTraceResult) {
      super.onHitEntity(rayTraceResult);
      Entity entity = rayTraceResult.getEntity();
      int i = entity instanceof BlazeEntity ? 13 : 10;
      entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
   }

   @Override
   protected void onHit(RayTraceResult traceResult) {
      super.onHit(traceResult);
      if (!this.level.isClientSide) {
         this.level.broadcastEntityEvent(this, (byte)3);
         this.remove();
      }

   }
}