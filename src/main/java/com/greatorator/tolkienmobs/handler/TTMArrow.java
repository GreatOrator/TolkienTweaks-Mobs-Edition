package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TTMArrow extends ArrowItem {
    public TTMArrow(Properties builder) {
        super(builder);
    }

    @Override
    public EntityGaladhrimArrow createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new EntityGaladhrimArrow(worldIn, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
        return enchant > 0 && this.getClass() == TTMArrow.class;
    }
}
