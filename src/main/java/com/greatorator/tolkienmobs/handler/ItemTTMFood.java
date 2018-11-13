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

    public ItemTTMFood(int amount, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount, isWolfFood);
        this.effects = potionEffects;
    }

    public ItemTTMFood(int amount, float saturation, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount, saturation, isWolfFood);
        this.effects = potionEffects;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player){
        for(PotionEffect effect : effects){
            player.addPotionEffect(effect);
        }
    }
}
