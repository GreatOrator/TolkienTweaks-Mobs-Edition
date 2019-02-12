package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class TTMFood extends ItemFood {
    private PotionEffect[] effects;
    public boolean hasEffectOverride = false;

    public TTMFood(int amount, PotionEffect...potionEffects) {
        super(amount, false);
        this.effects = potionEffects;
    }

    public TTMFood(int amount, float saturation, PotionEffect...potionEffects) {
        super(amount, saturation, false);
        this.effects = potionEffects;
    }

    public TTMFood setEffectOverride(boolean hasEffectOverride) {
        this.hasEffectOverride = hasEffectOverride;
        return this;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return hasEffectOverride || super.hasEffect(stack);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        for(PotionEffect effect : effects){
            player.addPotionEffect(effect);
        }
    }
}