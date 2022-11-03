package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

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
    public UseAnim getUseAnimation(ItemStack stack) {
        return hasDrinkAction ? UseAnim.DRINK : UseAnim.EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        ItemStack itemstack = super.finishUsingItem(stack, worldIn, entityLiving);

        return entityLiving instanceof Player && ((Player) entityLiving).getAbilities().instabuild ? itemstack : new ItemStack(TolkienItems.BOTTLE_FANCY.get());
    }
}
