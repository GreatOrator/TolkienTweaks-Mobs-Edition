package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.entity.item.UtumnoArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.world.World;

public class UtumnoArrowItem extends ArrowItem {
    public UtumnoArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack getDefaultInstance() {
        return PotionUtils.setPotion(super.getDefaultInstance(), Potions.POISON);
    }

    @Override
    public UtumnoArrowEntity createArrow(World world, ItemStack stack, LivingEntity entity) {
        UtumnoArrowEntity utumnoArrowEntity = new UtumnoArrowEntity(world, entity);
        utumnoArrowEntity.setEffectsFromItem(stack);
        return utumnoArrowEntity;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == UtumnoArrowItem.class;
    }
}
