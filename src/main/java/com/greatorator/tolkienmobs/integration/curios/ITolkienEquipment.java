package com.greatorator.tolkienmobs.integration.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/** Borrowed from Draconic Evolution */
public interface ITolkienEquipment {

    default void equipmentTick(ItemStack stack, LivingEntity livingEntity) {}

    default List<Component> getTagsTooltip(ItemStack stack, List<Component> tagTooltips) {
        return tagTooltips;
    }

    default boolean canRightClickEquip(ItemStack stack) {
        return false;
    }

    default boolean canEquip(LivingEntity livingEntity) {
        return true;
    }
}
