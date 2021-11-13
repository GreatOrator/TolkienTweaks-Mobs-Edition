package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BookItem;
import net.minecraft.item.ItemStack;

public class EnchantmentDwarvenEndurance extends Enchantment {
    public EnchantmentDwarvenEndurance(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.ARMOR_HEAD, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem() instanceof BookItem;
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment);
    }
}
