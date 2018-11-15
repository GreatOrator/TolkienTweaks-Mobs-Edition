package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class TradeHandler implements EntityVillager.ITradeList {

    private ItemStack stackToBuy;
    private ItemStack stackToPay;
    private EntityVillager.PriceInfo price;

    public TradeHandler(ItemStack stackToBuy, ItemStack stackToPay, int priceFrom, int priceTo){
        this.stackToBuy = stackToBuy;
        this.stackToPay = stackToPay;
        this.price = new EntityVillager.PriceInfo(priceFrom, priceTo);
    }

    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
        int actualPrice = 1;

        if (price != null)
        {
            actualPrice = price.getPrice(random);
        }

        ItemStack buyItemStack = this.stackToBuy.copy();
        ItemStack sellItemStack = this.stackToPay.copy();
        sellItemStack.setCount(actualPrice);

        recipeList.add(new MerchantRecipe(sellItemStack, buyItemStack));
    }

}
