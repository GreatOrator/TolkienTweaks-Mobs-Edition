package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class EnchantmentHobbitHarvest extends Enchantment {
    public EnchantmentHobbitHarvest(Rarity rarity, EnumEnchantmentType location, EntityEquipmentSlot...slot) {
        super(rarity, location, slot);
    }

    @Override
    public boolean canApply(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemBook) {
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
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantments.SILK_TOUCH;
    }
}
