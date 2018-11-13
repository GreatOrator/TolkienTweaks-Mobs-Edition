package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemTTMFood extends ItemFood {
    private PotionEffect[] effects;
    public int[] hungerValues;
    public float[] saturationModifiers;

    public ItemTTMFood(int[] hungerValues, boolean isWolfFood, PotionEffect...potionEffects) {
        super(0, false);
        this.hungerValues = hungerValues;
        this.effects = potionEffects;
    }

    public ItemTTMFood(int[] hungerValues, float[] saturationModifiers, boolean isWolfFood, PotionEffect...potionEffects) {
        super(0, 0, false);
        this.hungerValues = hungerValues;
        this.saturationModifiers = saturationModifiers;
        this.effects = potionEffects;
    }

    @Override
    public int getHealAmount(ItemStack itemStack)
    {
        return hungerValues[itemStack.getItemDamage()];
    }

    @Override
    public float getSaturationModifier(ItemStack itemStack)
    {
        return saturationModifiers[itemStack.getItemDamage()];
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        for(PotionEffect effect : effects){
            player.addPotionEffect(effect);
        }
    }
}
