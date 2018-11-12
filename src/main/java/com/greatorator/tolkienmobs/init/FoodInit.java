package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.handler.FoodHandler;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FoodInit {
    public static Item lembas;

    public static void init(){
        System.out.println("Putting together tastiness!");
        lembas = new FoodHandler(20, 20, false, new PotionEffect(Potion.getPotionById(10),100,4), new PotionEffect(Potion.getPotionById(22),12000,5));
    }

}
