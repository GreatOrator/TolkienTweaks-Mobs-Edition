package com.greatorator.tolkienmobs.item.tools;

import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.entity.ammo.GaladhrimArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GaladhrimArrowItem extends ArrowItem {
    public GaladhrimArrowItem(Properties builder) {
        super(builder);
    }

    @Override
    public GaladhrimArrowEntity createArrow(World world, ItemStack ammoStack, LivingEntity shooter) {
        return new GaladhrimArrowEntity(EntityGenerator.AMMO_ARROW_GALADHRIM.get(), shooter, world);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == GaladhrimArrowItem.class;
    }
}
