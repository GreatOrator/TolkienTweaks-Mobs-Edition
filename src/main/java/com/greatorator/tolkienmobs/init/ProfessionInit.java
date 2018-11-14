package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;

import static com.brandon3055.tolkientweaks.TTFeatures.brons_coin;
import static com.brandon3055.tolkientweaks.TTFeatures.gold_coin;
import static com.brandon3055.tolkientweaks.TTFeatures.silver_coin;

@GameRegistry.ObjectHolder(TolkienMobs.MODID)
public class ProfessionInit {
    public final static VillagerProfession coin_trader = null;

    public static VillagerCareer coin_banker;

    @Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerProfession> event)
        {
            final IForgeRegistry<VillagerProfession> registry = event.getRegistry();

            // DEBUG
            System.out.println("Putting out now hiring signs...");

            registry.register(new VillagerProfession(
                    TolkienMobs.MODID+":coin_banker",
                    TolkienMobs.MODID+":textures/entities/coin_banker.png",
                    TolkienMobs.MODID+":textures/entities/coin_banker.png"
                    )
            );
        }
    }

    public static void associateCareersAndTrades()
    {
        // DEBUG
        System.out.println("Taking in applications for employment...");

        coin_banker = (new VillagerCareer(coin_trader, "coin_banker"))
                .addTrade(1, new TradeUpBronzeCoins())
                .addTrade(2, new TradeUpSilverCoins())
                .addTrade(3, new TradeDownSilverCoins())
                .addTrade(4, new TradeDownBronzeCoins());
    }

    public static class TradeUpBronzeCoins implements ITradeList
    {
        /** The  item stack to buy */
        public ItemStack stack;
        /** The price info determining the amount of emeralds to trade in for the enchanted item */
        public EntityVillager.PriceInfo priceInfo;

        /**
         * Instantiates a new trade bronze coins for silver coins.
         */
        public TradeUpBronzeCoins()
        {
            stack = new ItemStack(silver_coin);
            priceInfo = new PriceInfo(64, 64);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 1;

            if (priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(brons_coin, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));

            // DEBUG
            System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
        }
    }

    public static class TradeUpSilverCoins implements ITradeList
    {
        /** The  item stack to buy */
        public ItemStack stack;
        /** The price info determining the amount of emeralds to trade in for the enchanted item */
        public EntityVillager.PriceInfo priceInfo;

        /**
         * Instantiates a new trade bronze coins for silver coins.
         */
        public TradeUpSilverCoins()
        {
            stack = new ItemStack(gold_coin);
            priceInfo = new PriceInfo(64, 64);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 1;

            if (priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(silver_coin, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));

            // DEBUG
            System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
        }
    }

    public static class TradeDownSilverCoins implements ITradeList
    {
        /** The  item stack to buy */
        public ItemStack stack;
        /** The price info determining the amount of emeralds to trade in for the enchanted item */
        public EntityVillager.PriceInfo priceInfo;

        /**
         * Instantiates a new trade bronze coins for silver coins.
         */
        public TradeDownSilverCoins()
        {
            stack = new ItemStack(silver_coin);
            priceInfo = new PriceInfo(1, 1);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 64;

            if (priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(gold_coin, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));

            // DEBUG
            System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
        }
    }

    public static class TradeDownBronzeCoins implements ITradeList
    {
        /** The  item stack to buy */
        public ItemStack stack;
        /** The price info determining the amount of emeralds to trade in for the enchanted item */
        public EntityVillager.PriceInfo priceInfo;

        /**
         * Instantiates a new trade bronze coins for silver coins.
         */
        public TradeDownBronzeCoins()
        {
            stack = new ItemStack(brons_coin);
            priceInfo = new PriceInfo(64, 64);
        }

        @Override
        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int actualPrice = 64;

            if (priceInfo != null)
            {
                actualPrice = priceInfo.getPrice(random);
            }

            ItemStack stackToPay = new ItemStack(silver_coin, actualPrice, 0);
            recipeList.add(new MerchantRecipe(stackToPay, stack));

            // DEBUG
            System.out.println("Merchant recipe list = "+recipeList.getRecipiesAsTags());
        }
    }
}