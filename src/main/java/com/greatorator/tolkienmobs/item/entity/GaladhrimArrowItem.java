package com.greatorator.tolkienmobs.item.entity;

import com.greatorator.tolkienmobs.entity.projectiles.GaladhrimArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GaladhrimArrowItem extends ArrowItem {
    public GaladhrimArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public GaladhrimArrowEntity createArrow(Level world, ItemStack stack, LivingEntity entity) {
        GaladhrimArrowEntity gladhrimarrowentity = new GaladhrimArrowEntity(entity, world);
        gladhrimarrowentity.setEffectsFromItem(stack);
        return gladhrimarrowentity;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == GaladhrimArrowItem.class;
    }
}
