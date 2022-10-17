package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.item.GaladhrimArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GaladhrimArrowItem extends ArrowItem {
    public GaladhrimArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public GaladhrimArrowEntity createArrow(World world, ItemStack stack, LivingEntity entity) {
        GaladhrimArrowEntity gladhrimarrowentity = new GaladhrimArrowEntity(world, entity);
        gladhrimarrowentity.setEffectsFromItem(stack);
        return gladhrimarrowentity;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == GaladhrimArrowItem.class;
    }
}
