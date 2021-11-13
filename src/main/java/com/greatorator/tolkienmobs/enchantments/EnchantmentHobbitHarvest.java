package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

public class EnchantmentHobbitHarvest extends Enchantment {

    public EnchantmentHobbitHarvest(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 10 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return super.getMinCost(enchantmentLevel) + 10;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if(stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE))
        {
            return (stack.getItem() instanceof HoeItem || stack.getToolTypes().contains(ToolType.HOE));
        }

        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}
