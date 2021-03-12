package com.greatorator.tolkienmobs.handler;

import net.minecraft.item.ArrowItem;

public class TTMArrow extends ArrowItem {
    public TTMArrow(Properties builder) {
        super(builder);
    }

//    @Override
//    public EntityGaladhrimArrow createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
//        return new EntityGaladhrimArrow(worldIn, shooter);
//    }
//
//    @Override
//    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
//        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
//        return enchant > 0 && this.getClass() == TTMArrow.class;
//    }
}
