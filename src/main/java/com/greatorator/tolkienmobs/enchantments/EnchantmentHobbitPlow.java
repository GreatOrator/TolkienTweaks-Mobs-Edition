package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentHobbitPlow extends Enchantment {

    public EnchantmentHobbitPlow(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.DIGGER, slots);
    }
}
