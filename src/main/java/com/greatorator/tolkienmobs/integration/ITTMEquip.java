package com.greatorator.tolkienmobs.integration;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

/** Borrowed from Draconic Evolution */
public interface ITTMEquip {

    default void equipmentTick(ItemStack stack, LivingEntity livingEntity) {}

    default List<ITextComponent> getTagsTooltip(ItemStack stack, List<ITextComponent> tagTooltips) {
        return tagTooltips;
    }

    default boolean canRightClickEquip(ItemStack stack) {
        return false;
    }

    default boolean canEquip(LivingEntity livingEntity) {
        return true;
    }
}
