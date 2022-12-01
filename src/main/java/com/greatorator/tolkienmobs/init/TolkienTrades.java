package com.greatorator.tolkienmobs.init;


import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.entity.WanderingEntity;
import com.greatorator.tolkienmobs.handler.TradeHandler;
import com.greatorator.tolkienmobs.item.basic.TrinketItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TolkienTrades {
    private static TolkienTrades instance = null;
    private VillagerTrades.ItemListing[] musicTradesArray = null;
    public List<VillagerTrades.ItemListing> musicTrades = new ArrayList<VillagerTrades.ItemListing>(30);
    public List<VillagerTrades.ItemListing> potionTrades = new ArrayList<>(3);
    public List<VillagerTrades.ItemListing> betterPotionTrades = new ArrayList<>(3);
    public List<VillagerTrades.ItemListing> highEndTrades = new ArrayList<>(1);
    public List<VillagerTrades.ItemListing> betterHighEndTrades = new ArrayList<>(1);
    public List<VillagerTrades.ItemListing> foodTrades = new ArrayList<>(3);
    public List<VillagerTrades.ItemListing> materialTrades = new ArrayList<>(6);

    private Random random = new Random();
    private Item currencyItem = null;
    protected Function<Random, ItemStack> forSale;

    public static void onVillagerTradesEvent(VillagerTradesEvent event){
        if (event.getType() == TolkienProfessions.COIN_TRADER_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 64,64)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 1, 1)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 25, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 64,64)
                            .setForSale(TolkienItems.ITEM_COIN_GOLD.get(), 1, 1)
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 25, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_GOLD.get(), 64,64)
                            .setForSale(TolkienItems.ITEM_COIN_MITHRIL.get(), 1, 1)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 1,1)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 64, 64)
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_GOLD.get(), 1,1)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 64, 64)
                            .build()));
            event.getTrades().get(4)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,1)
                            .setForSale(TolkienItems.ITEM_COIN_GOLD.get(), 64, 64)
                            .build()));
        }
        if (event.getType() == TolkienProfessions.GROCERY_STORE_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 3,26)
                            .setForSale(TolkienItems.HONEY_CAKE.get(), 1, 3)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 1,16)
                            .setForSale(TolkienItems.CRAM.get(), 1, 3)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 1,12)
                            .setForSale(TolkienItems.FOOD_HONEY.get(), 1, 2)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 4,16)
                            .setForSale(TolkienItems.LEMBAS.get(), 1, 2)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 2,6)
                            .setForSale(TolkienItems.MIRUVOR.get(), 1, 2)
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_GOLD.get(), 1,4)
                            .setForSale(TolkienItems.DRINK_ERU_BLESSING.get(), 1, 1)
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_GOLD.get(), 1,6)
                            .setForSale(TolkienItems.DRINK_ELF_VITALITY.get(), 1, 1)
                            .build()));
            event.getTrades().get(4)
                    .add((new TradeHandler(16, 100, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,3)
                            .setForSale(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get(), 1, 2)
                            .build()));
            event.getTrades().get(4)
                    .add((new TradeHandler(16, 100, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,2)
                            .setForSale(TolkienItems.DRINK_ENT_DRAUGHT.get(), 1, 2)
                            .build()));
        }
        if (event.getType() == TolkienProfessions.PET_MERCHANT_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 9,48)
                            .setForSale(TolkienItems.TREE_ACORN.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_BRONZE.get(), 5,36)
                            .setForSale(TolkienItems.INSECT.get(), 1, 4)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 2,5)
                            .setForSale(TolkienItems.GOLDEN_TREE_ACORN.get(), 1, 6)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_SILVER.get(), 2,7)
                            .setForSale(TolkienItems.GOLDEN_INSECT.get(), 1, 4)
                            .build()));
        }
        if (event.getType() == TolkienProfessions.JUNK_TRADER_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.BOTTLE_FANCY.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.MONSTER_FUR.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 25, 0.05F)
                            .setPrice(TolkienItems.MONSTER_FLESH.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.MUMAKIL_LEATHER.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.CREBAIN_FEATHER.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.BIRD_FEATHER.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.GOLEM_STONE.get(), 1,12)
                            .setForSale(TolkienItems.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.GOLEM_STONE_AIR.get(), 1,6)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.GOLEM_STONE_EARTH.get(), 1,6)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.GOLEM_STONE_FIRE.get(), 1,6)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 75, 0.05F)
                            .setPrice(TolkienItems.GOLEM_STONE_WATER.get(), 1,6)
                            .setForSale(TolkienItems.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
        }
        if (event.getType() == TolkienProfessions.TRINKET_SMITH_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_RING.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_AMULET.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_CHARM.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_RING.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_RING.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_RING.get())
                            .build()));
        }
        if (event.getType() == TolkienProfessions.TRINKET_TAILOR_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_HAT.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CLOAK.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_BELT.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_GLOVE.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_HAT.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_HAT.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TradeHandler(16, 50, 0.0F)
                            .setPrice(TolkienItems.TRINKET_CLOAK.get(), 1,1)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CLOAK.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_HAT.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TradeHandler(16, 50, 0.05F)
                            .setPrice(TolkienItems.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TolkienItems.TRINKET_CLOAK.get())
                            .build()));
        }
    }
    private TolkienTrades() {
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_RIVENDELL.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_LOTHLORIEN.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_EREBOR.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_MINASTIRITH.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_EDORAS.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_WILLOW.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_WBATTLE.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_MURDERFROG.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_REDER.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_FUMBLE.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_BOMBADIL.get()), 1, 15, 1));
        musicTrades.add(new BasicItemListing(getCost(2), new ItemStack(TolkienItems.RECORD_HOBBITS.get()), 1, 15, 1));

        potionTrades.add(new BasicItemListing(getCost(8), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.ELF_FLEETFOOT.get()), 3, 10, 1));
        potionTrades.add(new BasicItemListing(getCost(8), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.ENT_DRAUGHT.get()), 3, 10, 1));
        potionTrades.add(new BasicItemListing(getCost(8), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.DEEP_SLEEP.get()), 3, 10, 1));
        potionTrades.add(new BasicItemListing(getCost(8), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.DREAD_AURA.get()), 3, 10, 1));
        potionTrades.add(new BasicItemListing(getCost(8), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.DECAYING_INVENTORY.get()), 3, 10, 1));

        betterPotionTrades.add(new BasicItemListing(getCost(16), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.PORTABLE_REPAIR.get()), 3, 10, 1));
        betterPotionTrades.add(new BasicItemListing(getCost(16), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.BLESSING_OF_ERU.get()), 3, 10, 1));
        betterPotionTrades.add(new BasicItemListing(getCost(16), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.ELVISH_LIFE.get()), 3, 10, 1));
        betterPotionTrades.add(new BasicItemListing(getCost(16), PotionUtils.setPotion(new ItemStack(Items.POTION), TolkienPotions.CRIPPLING_TERROR.get()), 3, 10, 1));

        highEndTrades.add(new BasicItemListing(getCost(24), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_HAT.get().getDefaultInstance()), 3, 10, 1));
        highEndTrades.add(new BasicItemListing(getCost(24), (new ItemStack(TolkienItems.HOBBIT_RING.get())), 3, 10, 1));
        highEndTrades.add(new BasicItemListing(getCost(24), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_BELT.get().getDefaultInstance()), 3, 10, 1));
        highEndTrades.add(new BasicItemListing(getCost(24), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_CLOAK.get().getDefaultInstance()), 3, 10, 1));

        betterHighEndTrades.add(new BasicItemListing(getCost(32), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_RING.get().getDefaultInstance()), 3, 10, 1));
        betterHighEndTrades.add(new BasicItemListing(getCost(32), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_CHARM.get().getDefaultInstance()), 3, 10, 1));
        betterHighEndTrades.add(new BasicItemListing(getCost(32), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_AMULET.get().getDefaultInstance()), 3, 10, 1));
        betterHighEndTrades.add(new BasicItemListing(getCost(32), TrinketItem.getRandomTrinketEffect(TolkienItems.TRINKET_GLOVE.get().getDefaultInstance()), 3, 10, 1));

        materialTrades.add(new BasicItemListing(getCost(24), new ItemStack(TolkienItems.GEM_AMMOLITE.get(), 8), 2, 10, 1));
        materialTrades.add(new BasicItemListing(getCost(8), new ItemStack(TolkienItems.INGOT_MITHRIL.get(), 8), 2, 10, 1));
        materialTrades.add(new BasicItemListing(getCost(8), new ItemStack(TolkienItems.INGOT_MORGULIRON.get(), 8), 2, 10, 1));
        materialTrades.add(new BasicItemListing(getCost(6), new ItemStack(TolkienItems.MUMAKIL_LEATHER.get(), 8), 2, 10, 1));

        foodTrades.add(new BasicItemListing(getCost(14), new ItemStack(TolkienItems.LEMBAS.get(), 2), 2, 10, 1));
        foodTrades.add(new BasicItemListing(getCost(7), new ItemStack(TolkienItems.CRAM.get(), 2), 2, 10, 1));
        foodTrades.add(new BasicItemListing(getCost(9), new ItemStack(TolkienItems.HONEY_CAKE.get(), 2), 2, 10, 1));
        foodTrades.add(new BasicItemListing(getCost(5), new ItemStack(TolkienItems.FOOD_HONEY.get(), 2), 2, 10, 1));
    }

    public static TolkienTrades getInstance() {
        if (instance == null) {
            instance = new TolkienTrades();
        }
        return instance;
    }

    public void addToTrader(WanderingEntity trader, MerchantOffers offers) {
        // slots 1 & 2: Music Disks
        trader.addOffers(offers, makeArrayFrom(musicTrades), 2);

        // slots 3 & 4: Potions
        int roll = this.random.nextInt(4);
        offers.add(potionTrades.get(roll % 3).getOffer(trader, this.random));
        offers.add(betterPotionTrades.get(roll % 3).getOffer(trader, this.random));

        // slots 5 & 6: Trinkets
        offers.add(highEndTrades.get(0).getOffer(trader, this.random));
        offers.add(betterHighEndTrades.get(0).getOffer(trader, this.random));

        // slot 7: Enchanted book
        BasicItemListing book = this.tryEnchantedBook();
        offers.add(book.getOffer(trader, this.random));

        // slot 8: Materials
        roll = this.random.nextInt(materialTrades.size());
        offers.add(materialTrades.get(roll).getOffer(trader, this.random));

        // slot 9: Foods
        roll = this.random.nextInt(foodTrades.size());
        offers.add(foodTrades.get(roll).getOffer(trader, this.random));
    }

    private BasicItemListing tryEnchantedBook() {
        List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
        Enchantment enchantment = list.get(random.nextInt(list.size()));
        int i = Mth.nextInt(random, enchantment.getMinLevel(), enchantment.getMaxLevel());
        ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
        int j = 2 + random.nextInt(5 + i * 10) + 3 * i;
        if (enchantment.isTreasureOnly()) {
            j *= 2;
        }
        if (j > 64) {
            j = 64;
        }
            return new BasicItemListing(getCost(15), new ItemStack(Items.BOOK), itemstack, 1, 25, 1);
    }

    private VillagerTrades.ItemListing[] makeArrayFrom(List<VillagerTrades.ItemListing> musicTrade) {
        if (musicTradesArray != null && musicTradesArray.length == musicTrades.size()) {
            return musicTradesArray;
        }
        musicTradesArray = new VillagerTrades.ItemListing[musicTrades.size()];
        for (int i = 0; i < musicTrades.size(); i++) {
            musicTradesArray[i] = musicTrades.get(i);
        }
        return musicTradesArray;
    }

    public ItemStack getCost(int baseCost)
    {
        int cost = (int) Math.ceil(TolkienConfig.traderCostMultiplier * baseCost);
        cost = Math.min(cost, 64);
        return new ItemStack(this.getCostItem(), cost);
    }

    private Item getCostItem()
    {
        if (this.currencyItem == null)
        {
            this.currencyItem = Registry.ITEM.get(new ResourceLocation(TolkienConfig.traderCostItem));
            if (this.currencyItem.equals(Items.AIR))
            {
                this.currencyItem = Items.GOLD_INGOT;
            }
        }
        return this.currencyItem;
    }

}