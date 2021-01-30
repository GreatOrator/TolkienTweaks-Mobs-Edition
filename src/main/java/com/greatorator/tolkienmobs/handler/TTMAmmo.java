//package com.greatorator.tolkienmobs.handler;
//
//import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
//import com.greatorator.tolkienmobs.init.SoundInit;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.stats.StatList;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.EnumActionResult;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.world.World;
//
//public class TTMAmmo extends Item {
//
//    public TTMAmmo(int maxStackSize) {
//        this.maxStackSize = maxStackSize;
//    }
//
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, EnumHand handIn)
//    {
//        ItemStack itemstack = playerIn.getHeldItem(handIn);
//        playerIn.addStat(StatList.getObjectUseStats(this));
//
//        if (!playerIn.capabilities.isCreativeMode)
//        {
//            itemstack.shrink(1);
//        }
//
//        worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundInit.soundBoulderShoot, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//
//        if (!worldIn.isRemote)
//        {
//            EntityBoulder entityboulder = new EntityBoulder(worldIn, playerIn);
//            entityboulder.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
//            worldIn.spawnEntity(entityboulder);
//        }
//
//        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
//    }
//}