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

@GameRegistry.ObjectHolder(TolkienMobs.MODID)
public class ProfessionInit {
    /** Professions */
    public static VillagerProfession COIN_TRADER;
    public static VillagerProfession GROCERY_STORE;
    public static VillagerProfession PET_MERCHANT;
    public static VillagerProfession JUNK_TRADER;

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
                    TolkienMobs.MODID+":textures/entity/profession/zombie_coin_trader.png");
            registry.register(COIN_TRADER);

            GROCERY_STORE = new VillagerRegistry.VillagerProfession(
                    TolkienMobs.MODID+":grocery_store",
                    TolkienMobs.MODID+":textures/entity/profession/grocery_store.png",
                    TolkienMobs.MODID+":textures/entity/profession/zombie_grocery_store.png");
            registry.register(GROCERY_STORE);

            PET_MERCHANT = new VillagerRegistry.VillagerProfession(
                    TolkienMobs.MODID+":pet_merchant",
                    TolkienMobs.MODID+":textures/entity/profession/pet_merchant.png",
                    TolkienMobs.MODID+":textures/entity/profession/zombie_pet_merchant.png");
            registry.register(PET_MERCHANT);

            JUNK_TRADER = new VillagerRegistry.VillagerProfession(
                    TolkienMobs.MODID+":junk_dealer",
                    TolkienMobs.MODID+":textures/entity/profession/junk_trader.png",
                    TolkienMobs.MODID+":textures/entity/profession/zombie_junk_trader.png");
            registry.register(JUNK_TRADER);
        }
    }

    /** Now that we have Professions, what are they going to do with their lives? */
    public static void associateCareersAndTrades()
    {
        // DEBUG
        LogHelperTTM.info("Taking in applications for employment...");

        VillagerRegistry.VillagerCareer coin_banker = new VillagerCareer(COIN_TRADER, "coin_banker");
         coin_banker.addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),64, 64))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),64, 64))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_MITHRIL), new ItemStack(TTMFeatures.ITEM_COIN_GOLD),64, 64))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_BRONZE,64), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),1, 1))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER,64), new ItemStack(TTMFeatures.ITEM_COIN_GOLD),1, 1))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD,64), new ItemStack(TTMFeatures.ITEM_COIN_MITHRIL),1, 1));

        VillagerRegistry.VillagerCareer store_clerk = new VillagerCareer(GROCERY_STORE, "store_clerk");
         store_clerk.addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.HONEY_CAKE), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),3, 26))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.CRAM), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),1, 16))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.FOOD_HONEY), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),1, 12))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.BLESSINGS_OF_ERU), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),2, 14))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.LEMBAS), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),4, 16))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.MIRUVOR), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),2, 6))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.ENT_DRAUGHT), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),1, 5))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.BLESSINGS_OF_THE_ELF), new ItemStack(TTMFeatures.ITEM_COIN_SILVER),3, 9));

        VillagerRegistry.VillagerCareer pet_seller = new VillagerCareer(PET_MERCHANT, "pet_seller");
          pet_seller.addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.TREE_ACORN, 3), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),9, 48))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.INSECT, 4), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),5, 36))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.GOLDEN_TREE_ACORN, 1), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),18, 61))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.GOLDEN_INSECT, 2), new ItemStack(TTMFeatures.ITEM_COIN_BRONZE),10, 52));

        VillagerRegistry.VillagerCareer junk_dealer = new VillagerCareer(JUNK_TRADER, "junk_dealer");
         junk_dealer.addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_BRONZE, 1), new ItemStack(TTMFeatures.BOTTLE_FANCY),1, 5))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_BRONZE, 2), new ItemStack(TTMFeatures.MONSTER_FUR),3, 12))
                    .addTrade(1, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_BRONZE, 3), new ItemStack(TTMFeatures.MONSTER_FLESH),2, 11))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER, 1), new ItemStack(TTMFeatures.MUMAKIL_LEATHER),1, 7))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER, 1), new ItemStack(TTMFeatures.CREBAIN_FEATHER),1, 21))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER, 2), new ItemStack(TTMFeatures.BIRD_FEATHER),1, 13))
                    .addTrade(2, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_SILVER, 8), new ItemStack(TTMFeatures.GOLEM_STONE),1, 16))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD, 1), new ItemStack(TTMFeatures.GOLEM_STONE_AIR),1, 16))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD, 1), new ItemStack(TTMFeatures.GOLEM_STONE_EARTH),1, 16))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD, 1), new ItemStack(TTMFeatures.GOLEM_STONE_FIRE),1, 16))
                    .addTrade(3, new TradeHandler(new ItemStack(TTMFeatures.ITEM_COIN_GOLD, 1), new ItemStack(TTMFeatures.GOLEM_STONE_WATER),1, 16));

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

    public static VillagerRegistry.VillagerProfession getPetSupplies() {
        return PET_MERCHANT;
    }

    public static VillagerRegistry.VillagerProfession getJunkSeller() {
        return JUNK_TRADER;
    }
}