package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentHobbitHarvest extends Enchantment {

    public EnchantmentHobbitHarvest(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }
}
