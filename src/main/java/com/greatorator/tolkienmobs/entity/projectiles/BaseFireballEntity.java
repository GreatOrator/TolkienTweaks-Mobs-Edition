package com.greatorator.tolkienmobs.entity.projectiles;

import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;

public abstract class BaseFireballEntity extends AbstractHurtingProjectile implements ItemSupplier {
   private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(BaseFireballEntity.class, EntityDataSerializers.ITEM_STACK);

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, Level world) {
      super(entityType, world);
   }

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, double p_i50167_2_, double p_i50167_4_, double p_i50167_6_, double p_i50167_8_, double p_i50167_10_, double p_i50167_12_, Level world) {
      super(entityType, p_i50167_2_, p_i50167_4_, p_i50167_6_, p_i50167_8_, p_i50167_10_, p_i50167_12_, world);
   }

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, LivingEntity livingEntity, double x, double y, double z, Level world) {
      super(entityType, livingEntity, x, y, z, world);
   }

   @Nonnull
   @Override
   public Packet<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   public void setItem(ItemStack stack) {
      if (stack.getItem() != TolkienItems.FELLBEAST_FIREBALL.get() || stack.hasTag()) {
         this.getEntityData().set(DATA_ITEM_STACK, Util.make(stack.copy(), (p_213897_0_) -> {
            p_213897_0_.setCount(1);
         }));
      }

   }

   protected ItemStack getItemRaw() {
      return this.getEntityData().get(DATA_ITEM_STACK);
   }

   @OnlyIn(Dist.CLIENT)
   public ItemStack getItem() {
      ItemStack itemstack = this.getItemRaw();
      return itemstack.isEmpty() ? new ItemStack(TolkienItems.FELLBEAST_FIREBALL.get()) : itemstack;
   }

   protected void defineSynchedData() {
      this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
   }

   public void addAdditionalSaveData(CompoundTag p_213281_1_) {
      super.addAdditionalSaveData(p_213281_1_);
      ItemStack itemstack = this.getItemRaw();
      if (!itemstack.isEmpty()) {
         p_213281_1_.put("Item", itemstack.save(new CompoundTag()));
      }

   }

   public void readAdditionalSaveData(CompoundTag p_70037_1_) {
      super.readAdditionalSaveData(p_70037_1_);
      ItemStack itemstack = ItemStack.of(p_70037_1_.getCompound("Item"));
      this.setItem(itemstack);
   }
}