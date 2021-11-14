package com.greatorator.tolkienmobs.entity.ammo;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityFellBeastFireball extends ProjectileItemEntity {
   public int explosionPower = 4;

   public EntityFellBeastFireball(World worldIn, double x, double y, double z) {
      super((EntityType<? extends EntityFellBeastFireball>) EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), x, y, z,worldIn);
   }

   public EntityFellBeastFireball(World worldIn, LivingEntity shooter) {
      super((EntityType<? extends EntityFellBeastFireball>) EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(),shooter, worldIn);
   }

   public EntityFellBeastFireball(EntityType<Entity> entityEntityType, World world) {
      super((EntityType<? extends EntityFellBeastFireball>) EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), world);

   }

   public EntityFellBeastFireball(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
      super((EntityType<? extends SnowballEntity>) EntityGenerator.AMMO_FELLBEAST_FIREBALL.get(), world);
   }

   protected Item getDefaultItem() {
      return TTMContent.FELLBEAST_FIREBALL.get();
   }

   @OnlyIn(Dist.CLIENT)
   private IParticleData makeParticle() {
      ItemStack itemstack = this.getItemRaw();
      return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.LARGE_SMOKE : new ItemParticleData(ParticleTypes.ITEM, itemstack));
   }

   @OnlyIn(Dist.CLIENT)
   @Override
   public void handleEntityEvent(byte id) {
      if (id == 3) {
         IParticleData iparticledata = this.makeParticle();

         for(int i = 0; i < 8; ++i) {
            this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
         }
      }

   }

   /**
    * Called when the arrow hits an entity
    */
   @Override
   protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
      super.onHitEntity(p_213868_1_);
      Entity entity = p_213868_1_.getEntity();
      int i = 10;
      entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
   }

   /**
    * Called when this EntityFireball hits a block or entity.
    */
   @Override
   protected void onHit(RayTraceResult result) {
      super.onHit(result);
      if (!this.level.isClientSide) {
         boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());
         this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
         this.remove();
      }
   }

   @Override
   public void addAdditionalSaveData(CompoundNBT compound) {
      super.addAdditionalSaveData(compound);
      compound.putInt("ExplosionPower", this.explosionPower);
   }

   /**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   @Override
   public void readAdditionalSaveData(CompoundNBT compound) {
      super.readAdditionalSaveData(compound);
      if (compound.contains("ExplosionPower", 99)) {
         this.explosionPower = compound.getInt("ExplosionPower");
      }

   }
}
