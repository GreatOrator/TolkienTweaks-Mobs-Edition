package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.TradeHandler;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;

import static com.brandon3055.tolkientweaks.TTFeatures.brons_coin;
import static com.brandon3055.tolkientweaks.TTFeatures.gold_coin;
import static com.brandon3055.tolkientweaks.TTFeatures.silver_coin;

@GameRegistry.ObjectHolder(TolkienMobs.MODID)
public class ProfessionInit {
    /** Professions */
    public static VillagerProfession coin_trader;
    public static VillagerProfession grocery_store;

    /** Careers */
    public static VillagerCareer coin_banker;
    public static VillagerCareer store_clerk;

    /** Let's make it so people have a life choice to make. */
    @Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerProfession> event)
        {
            final IForgeRegistry<VillagerProfession> registry = event.getRegistry();

            // DEBUG
            LogHelperTTM.info("Putting out now hiring signs...");

            coin_trader = new VillagerRegistry.VillagerProfession(TolkienMobs.MODID+":coin_trader",
                    TolkienMobs.MODID+":textures/entity/profession/coin_trader.png",
                    TolkienMobs.MODID+":textures/entity/profession/coin_trader.png");
            registry.register(coin_trader);

            grocery_store = new VillagerRegistry.VillagerProfession(TolkienMobs.MODID+":grocery_store",
                    TolkienMobs.MODID+":textures/entity/profession/grocery_store.png",
                    TolkienMobs.MODID+":textures/entity/profession/grocery_store.png");
            registry.register(grocery_store);
        }
    }

    /** Now that we have Professions, what are they going to do with their lives? */
    public static void associateCareersAndTrades()
    {
        // DEBUG
        LogHelperTTM.info("Taking in applications for employment...");

        coin_banker = (new VillagerCareer(coin_trader, "coin_banker"))
                .addTrade(1, new TradeHandler(new ItemStack(silver_coin), new ItemStack(brons_coin),64, 64))
                .addTrade(1, new TradeHandler(new ItemStack(gold_coin), new ItemStack(silver_coin),64, 64))
                .addTrade(2, new TradeHandler(new ItemStack(silver_coin,64), new ItemStack(gold_coin),1, 1))
                .addTrade(2, new TradeHandler(new ItemStack(brons_coin,64), new ItemStack(silver_coin),1, 1));

        store_clerk = (new VillagerCareer(grocery_store, "store_clerk"))
                .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.LEMBAS), new ItemStack(brons_coin),5, 36));

        // DEBUG
        LogHelperTTM.info("All positions are now filled, thank you for applying.");
    }

    /** Here's where we make specific Professions available for entities */
    public static VillagerRegistry.VillagerProfession getCoinBanker() {
        return coin_trader;
    }

    public static VillagerRegistry.VillagerProfession getGroceryStore() {
        return grocery_store;
    }
}