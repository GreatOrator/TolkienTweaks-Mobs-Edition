package com.greatorator.tolkienmobs.entity.item;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

@OnlyIn(
   value = Dist.CLIENT,
   _interface = IRendersAsItem.class
)
public abstract class BaseFireballEntity extends DamagingProjectileEntity implements IRendersAsItem {
   private static final DataParameter<ItemStack> DATA_ITEM_STACK = EntityDataManager.defineId(BaseFireballEntity.class, DataSerializers.ITEM_STACK);

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, World world) {
      super(entityType, world);
   }

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, double p_i50167_2_, double p_i50167_4_, double p_i50167_6_, double p_i50167_8_, double p_i50167_10_, double p_i50167_12_, World world) {
      super(entityType, p_i50167_2_, p_i50167_4_, p_i50167_6_, p_i50167_8_, p_i50167_10_, p_i50167_12_, world);
   }

   public BaseFireballEntity(EntityType<? extends BaseFireballEntity> entityType, LivingEntity livingEntity, double x, double y, double z, World world) {
      super(entityType, livingEntity, x, y, z, world);
   }

   @Nonnull
   @Override
   public IPacket<?> getAddEntityPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   public void setItem(ItemStack stack) {
      if (stack.getItem() != TTMContent.FELLBEAST_FIREBALL.get() || stack.hasTag()) {
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
      return itemstack.isEmpty() ? new ItemStack(TTMContent.FELLBEAST_FIREBALL.get()) : itemstack;
   }

   protected void defineSynchedData() {
      this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
   }

   public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
      super.addAdditionalSaveData(p_213281_1_);
      ItemStack itemstack = this.getItemRaw();
      if (!itemstack.isEmpty()) {
         p_213281_1_.put("Item", itemstack.save(new CompoundNBT()));
      }

   }

   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
      super.readAdditionalSaveData(p_70037_1_);
      ItemStack itemstack = ItemStack.of(p_70037_1_.getCompound("Item"));
      this.setItem(itemstack);
   }
}