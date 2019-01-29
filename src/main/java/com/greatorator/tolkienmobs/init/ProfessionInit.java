package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.TradeHandler;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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
    public static VillagerProfession COIN_TRADER;
    public static VillagerProfession GROCERY_STORE;

    /** Let's make it so people have a life choice to make. */
    @Mod.EventBusSubscriber(modid = TolkienMobs.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<VillagerProfession> event)
        {
            final IForgeRegistry<VillagerProfession> registry = event.getRegistry();

            // DEBUG
            LogHelperTTM.info("Putting out now hiring signs...");

            COIN_TRADER = new VillagerRegistry.VillagerProfession(
                    TolkienMobs.MODID+":coin_trader",
                    TolkienMobs.MODID+":textures/entity/profession/coin_trader.png",
                    TolkienMobs.MODID+":textures/entity/profession/coin_trader.png");
            registry.register(COIN_TRADER);

            GROCERY_STORE = new VillagerRegistry.VillagerProfession(
                    TolkienMobs.MODID+":grocery_store",
                    TolkienMobs.MODID+":textures/entity/profession/grocery_store.png",
                    TolkienMobs.MODID+":textures/entity/profession/grocery_store.png");
            registry.register(GROCERY_STORE);
        }
    }

    /** Now that we have Professions, what are they going to do with their lives? */
    public static void associateCareersAndTrades()
    {
        // DEBUG
        LogHelperTTM.info("Taking in applications for employment...");

        VillagerRegistry.VillagerCareer coin_banker = new VillagerCareer(COIN_TRADER, "coin_banker");
         coin_banker.addTrade(1, new TradeHandler(new ItemStack(silver_coin), new ItemStack(brons_coin),64, 64))
                    .addTrade(1, new TradeHandler(new ItemStack(gold_coin), new ItemStack(silver_coin),64, 64))
                    .addTrade(2, new TradeHandler(new ItemStack(silver_coin,64), new ItemStack(gold_coin),1, 1))
                    .addTrade(2, new TradeHandler(new ItemStack(brons_coin,64), new ItemStack(silver_coin),1, 1));

        VillagerRegistry.VillagerCareer store_clerk = new VillagerCareer(GROCERY_STORE, "store_clerk");
         store_clerk.addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.LEMBAS), new ItemStack(brons_coin),5, 36))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.HONEY_CAKE), new ItemStack(brons_coin),3, 26))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.CRAM), new ItemStack(brons_coin),1, 16))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ENT_DRAUGHT), new ItemStack(silver_coin),1, 5))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.MIRUVOR), new ItemStack(silver_coin),2, 6));

        // DEBUG
        LogHelperTTM.info("All positions are now filled, thank you for applying.");
    }

    /** Here's where we make specific Professions available for entities */
    public static VillagerRegistry.VillagerProfession getCoinBanker() {
        return COIN_TRADER;
    }

    public static VillagerRegistry.VillagerProfession getGroceryStore() {
        return GROCERY_STORE;
    }
}