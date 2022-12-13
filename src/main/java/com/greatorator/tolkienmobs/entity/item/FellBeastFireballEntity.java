package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes", "removal" })
public class FellBeastFireballEntity extends BaseFireballEntity implements IAnimatable {
   private final AnimationFactory factory = new AnimationFactory(this);
   public int explosionPower = 1;

   public FellBeastFireballEntity(EntityType<? extends FellBeastFireballEntity> entityType, Level world) {
      super(entityType, world);
   }

   @OnlyIn(Dist.CLIENT)
   public FellBeastFireballEntity(Level world, double p_i1768_2_, double p_i1768_4_, double p_i1768_6_, double p_i1768_8_, double p_i1768_10_, double p_i1768_12_) {
      super(TolkienEntities.AMMO_FELLBEAST_FIREBALL.get(), p_i1768_2_, p_i1768_4_, p_i1768_6_, p_i1768_8_, p_i1768_10_, p_i1768_12_, world);
   }

   public FellBeastFireballEntity(Level world, LivingEntity livingEntity, double x, double y, double z) {
      super(TolkienEntities.AMMO_FELLBEAST_FIREBALL.get(), livingEntity, x, y, z, world);
   }

   @Override
   protected void onHit(HitResult rayTraceResult) {
      super.onHit(rayTraceResult);
      Entity entity = this.getOwner();
      if (rayTraceResult.getType() != HitResult.Type.ENTITY || !((EntityHitResult)rayTraceResult).getEntity().is(entity)) {
         if (!this.level.isClientSide) {
            List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
            AreaEffectCloud areaeffectcloudentity = new AreaEffectCloud(this.level, this.getX(), this.getY(), this.getZ());
            if (entity instanceof LivingEntity) {
               areaeffectcloudentity.setOwner((LivingEntity)entity);
            }

            areaeffectcloudentity.setParticle(TolkienParticles.fell_beast_breath);
            areaeffectcloudentity.setRadius(3.0F);
            areaeffectcloudentity.setDuration(600);
            areaeffectcloudentity.setRadiusPerTick((7.0F - areaeffectcloudentity.getRadius()) / (float)areaeffectcloudentity.getDuration());
            areaeffectcloudentity.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
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
            this.discard();
         }
      }
   }

   @Override
   public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
      return false;
   }

   @Override
   protected ParticleOptions getTrailParticle() {
      return TolkienParticles.fell_beast_breath;
   }

   @Override
   protected boolean shouldBurn() {
      return false;
   }

   @Override
   public void addAdditionalSaveData(CompoundTag p_213281_1_) {
      super.addAdditionalSaveData(p_213281_1_);
      p_213281_1_.putInt("ExplosionPower", this.explosionPower);
   }

   @Override
   public void readAdditionalSaveData(CompoundTag p_70037_1_) {
      super.readAdditionalSaveData(p_70037_1_);
      if (p_70037_1_.contains("ExplosionPower", 99)) {
         this.explosionPower = p_70037_1_.getInt("ExplosionPower");
      }
   }

   @Nonnull
   @Override
   public Packet<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
      event.getController().setAnimation(new AnimationBuilder().addAnimation("rotate", ILoopType.EDefaultLoopTypes.LOOP));
      return PlayState.CONTINUE;
   }

   @Override
   public void registerControllers(AnimationData data) {
      data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
   }

   @Override
   public AnimationFactory getFactory() {
      return this.factory;
   }
}
