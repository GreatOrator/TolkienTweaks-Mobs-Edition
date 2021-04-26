package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class TTMFood extends Item {
    public boolean hasEffectOverride = false;
    public boolean hasDrinkAction = false;

    public TTMFood(Properties properties) {
        super(properties);
    }

    public TTMFood setEffectOverride() {
        this.hasEffectOverride = true;
        return this;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return hasEffectOverride || super.isFoil(stack);
    }

    public TTMFood setItemUseAction(boolean getUseAction) {
        this.hasDrinkAction = getUseAction;
        return this;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return hasDrinkAction ? UseAction.DRINK : UseAction.EAT;
    }

    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.finishUsingItem(stack, worldIn, entityLiving);

        return entityLiving instanceof PlayerEntity && ((PlayerEntity)entityLiving).abilities.instabuild ? itemstack : new ItemStack(TTMContent.BOTTLE_FANCY.get());
    }
}
