package com.greatorator.tolkienmobs.item.entity;

import com.greatorator.tolkienmobs.entity.projectiles.UtumnoArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class UtumnoArrowItem extends ArrowItem {
    public UtumnoArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.POISON);
    }

    @Override
    public UtumnoArrowEntity createArrow(Level world, ItemStack stack, LivingEntity entity) {
        UtumnoArrowEntity utumnoArrowEntity = new UtumnoArrowEntity(entity, world);
        utumnoArrowEntity.setEffectsFromItem(stack);
        return utumnoArrowEntity;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == UtumnoArrowItem.class;
    }
}
