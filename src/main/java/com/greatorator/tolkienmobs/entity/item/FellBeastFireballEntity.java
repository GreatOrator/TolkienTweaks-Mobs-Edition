package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.handler.TTMParticles;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.List;

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

   @Override
   protected void onHit(RayTraceResult rayTraceResult) {
      super.onHit(rayTraceResult);
      Entity entity = this.getOwner();
      if (rayTraceResult.getType() != RayTraceResult.Type.ENTITY || !((EntityRayTraceResult)rayTraceResult).getEntity().is(entity)) {
         if (!this.level.isClientSide) {
            List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
            AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.level, this.getX(), this.getY(), this.getZ());
            if (entity instanceof LivingEntity) {
               areaeffectcloudentity.setOwner((LivingEntity)entity);
            }

            areaeffectcloudentity.setParticle(TTMParticles.fell_beast_breath);
            areaeffectcloudentity.setRadius(3.0F);
            areaeffectcloudentity.setDuration(600);
            areaeffectcloudentity.setRadiusPerTick((7.0F - areaeffectcloudentity.getRadius()) / (float)areaeffectcloudentity.getDuration());
            areaeffectcloudentity.addEffect(new EffectInstance(Effects.HARM, 1, 1));
            if (!list.isEmpty()) {
               for(LivingEntity livingentity : list) {
                  double d0 = this.distanceToSqr(livingentity);
                  if (d0 < 16.0D) {
                     areaeffectcloudentity.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                     break;
                  }
               }
            }

            this.level.levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
            this.level.addFreshEntity(areaeffectcloudentity);
            this.remove();
         }
      }
   }

   @Override
   public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
      return false;
   }

   @Override
   protected IParticleData getTrailParticle() {
      return TTMParticles.fell_beast_breath;
   }

   @Override
   protected boolean shouldBurn() {
      return false;
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

   @Nonnull
   @Override
   public IPacket<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
