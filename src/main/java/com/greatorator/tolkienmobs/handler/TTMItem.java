package com.greatorator.tolkienmobs.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TTMItem extends Item {
    public boolean hasEffectOverride = false;

    public TTMItem(Properties properties) {
        super(properties);
    }

    public TTMItem setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }

}
