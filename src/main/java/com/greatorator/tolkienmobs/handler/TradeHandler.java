package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class TradeHandler implements EntityVillager.ITradeList {

    private ItemStack stackToBuy;
    private ItemStack stackToPay1;
    private EntityVillager.PriceInfo price;

    public TradeHandler(ItemStack stackToBuy, ItemStack stackToPay, int priceFrom, int priceTo){
        this.stackToBuy = stackToBuy;
        this.stackToPay1 = stackToPay;
        this.price = new EntityVillager.PriceInfo(priceFrom, priceTo);
    }

    @Override
    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
        int actualPrice = 1;

        if (price != null)
        {
            actualPrice = price.getPrice(random);
        }

        ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);

        recipeList.add(new MerchantRecipe(stackToPay, stackToBuy));
    }

}
