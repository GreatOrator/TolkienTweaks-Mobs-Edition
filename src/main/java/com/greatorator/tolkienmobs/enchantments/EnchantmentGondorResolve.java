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
    public int getMinCost(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
    }
}