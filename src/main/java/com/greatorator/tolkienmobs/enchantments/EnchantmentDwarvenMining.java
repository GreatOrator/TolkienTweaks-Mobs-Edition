package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class EnchantmentDwarvenMining extends Enchantment {
    public EnchantmentDwarvenMining(Rarity rarity, EnumEnchantmentType location, EntityEquipmentSlot...slot) {
        super(rarity, location, slot);
    }

    @Override
    public boolean canApply(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemBook) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment);
    }
}
