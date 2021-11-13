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
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class EntityBoulder extends ProjectileItemEntity {

   public EntityBoulder(World worldIn, double x, double y, double z) {
      super((EntityType<? extends EntityBoulder>) EntityGenerator.AMMO_BOULDER.get(), x, y, z,worldIn);
   }

   public EntityBoulder(World worldIn, LivingEntity shooter) {
      super((EntityType<? extends EntityBoulder>) EntityGenerator.AMMO_BOULDER.get(),shooter, worldIn);
   }

   public EntityBoulder(EntityType<Entity> entityEntityType, World world) {
      super((EntityType<? extends EntityBoulder>) EntityGenerator.AMMO_BOULDER.get(), world);

   }

   public EntityBoulder(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
      super((EntityType<? extends SnowballEntity>) EntityGenerator.AMMO_BOULDER.get(), world);
   }

   protected Item getDefaultItem() {
      return TTMContent.BOULDER.get();
   }

   @OnlyIn(Dist.CLIENT)
   private IParticleData makeParticle() {
      ItemStack itemstack = this.getItemRaw();
      return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.SMOKE : new ItemParticleData(ParticleTypes.ITEM, itemstack));
   }

   @OnlyIn(Dist.CLIENT)
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
   protected void onHitEntity(EntityRayTraceResult p_213868_1_) {
      super.onHitEntity(p_213868_1_);
      Entity entity = p_213868_1_.getEntity();
      int i = 6;
      entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
   }

   /**
    * Called when this EntityFireball hits a block or entity.
    */
   protected void onHit(RayTraceResult result) {
      super.onHit(result);
      if (!this.level.isClientSide) {
         this.level.broadcastEntityEvent(this, (byte)3);
         this.remove();
      }

   }
}
