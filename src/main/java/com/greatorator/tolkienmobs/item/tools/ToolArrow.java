//package com.greatorator.tolkienmobs.item.tools;
//
//import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.item.ItemArrow;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//
//public class ToolArrow extends ItemArrow
//{
//    @Override
//    public EntityGaladhrimArrow createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
//    {
//        EntityGaladhrimArrow entitytippedarrow = new EntityGaladhrimArrow(worldIn, shooter);
//        return entitytippedarrow;
//    }
//
//    @Override
//    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player)
//    {
//        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
//        return enchant <= 0 ? false : this.getClass() == ToolArrow.class;
//    }
//}