package com.greatorator.tolkienmobs.entity.projectiles;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public class BoulderEntity extends ThrowableItemProjectile {
   public BoulderEntity(EntityType<? extends BoulderEntity> entityType, Level world) {
      super(entityType, world);
   }

   public BoulderEntity(Level world, LivingEntity livingEntity) {
      super(TolkienEntities.AMMO_BOULDER.get(), livingEntity, world);
   }

   public BoulderEntity(Level world, double x, double y, double z) {
      super(TolkienEntities.AMMO_BOULDER.get(), x, y, z, world);
   }

   @Nonnull
   @Override
   public Packet<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   @Override
   protected Item getDefaultItem() {
      return TolkienItems.BOULDER.get();
   }

   @OnlyIn(Dist.CLIENT)
   private ParticleOptions getParticle() {
      ItemStack itemstack = this.getItemRaw();
      return (ParticleOptions) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
   }

   @OnlyIn(Dist.CLIENT)
   @Override
   public void handleEntityEvent(byte statusID) {
      if (statusID == 3) {
         ParticleOptions iparticledata = this.getParticle();

         for(int i = 0; i < 8; ++i) {
            this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   @Override
   protected void onHitEntity(EntityHitResult rayTraceResult) {
      super.onHitEntity(rayTraceResult);
      Entity entity = rayTraceResult.getEntity();
      int i = entity instanceof Blaze ? 13 : 10;
      entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
   }

   @Override
   protected void onHit(HitResult traceResult) {
      super.onHit(traceResult);
      if (!this.level.isClientSide) {
         this.level.broadcastEntityEvent(this, (byte)3);
         this.discard();
      }

   }
}