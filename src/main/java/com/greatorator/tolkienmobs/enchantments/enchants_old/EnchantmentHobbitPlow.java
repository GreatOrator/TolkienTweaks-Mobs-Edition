//package com.greatorator.tolkienmobs.enchantments;
//
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnumEnchantmentType;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.item.ItemBook;
//import net.minecraft.item.ItemHoe;
//import net.minecraft.item.ItemStack;
//
//public class EnchantmentHobbitPlow extends Enchantment {
//
//    public EnchantmentHobbitPlow(Rarity rarity, EnumEnchantmentType location, EntityEquipmentSlot...slot) {
//        super(rarity, location, slot);
//    }
//
//    @Override
//    public boolean canApply(ItemStack stack)
//    {
//        if(stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemBook) {
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack)
//    {
//        return stack.getItem() instanceof ItemHoe;
//    }
//
//    @Override
//    public int getMinEnchantability(int enchantmentLevel) {
//        return 20 * enchantmentLevel;
//    }
//
//    @Override
//    public int getMaxEnchantability(int enchantmentLevel) {
//        return this.getMinEnchantability(enchantmentLevel) + 10;
//    }
//
//    @Override
//    public int getMaxLevel() {
//        return 4;
//    }
//
//    @Override
//    protected boolean canApplyTogether(Enchantment enchantment) {
//        return super.canApplyTogether(enchantment);
//    }
//}
