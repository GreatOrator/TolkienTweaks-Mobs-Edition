package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;

public class EnchantmentGondorResolve extends Enchantment {

    public EnchantmentGondorResolve(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_LEGS, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem() instanceof BookItem;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
    }
//    public EnchantmentGondorianResolve(Rarity rarity, EnumEnchantmentType location, EntityEquipmentSlot...slot) {
//        super(rarity, location, slot);
//    }
//
//    @Override
//    public boolean canApplyAtEnchantingTable(ItemStack stack)
//    {
//        return stack.getItem() instanceof ItemBook;
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
//        return 5;
//    }
//
//    @Override
//    protected boolean canApplyTogether(Enchantment enchantment) {
//        return super.canApplyTogether(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
//    }
}