package com.greatorator.tolkienmobs.entity.merchant.villager;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.Entity;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;

import javax.annotation.Nullable;
import java.util.Random;

public class TTMVillagerTrades {
    public static void onVillagerTradesEvent(VillagerTradesEvent event){
        if (event.getType() == ProfessionGenerator.COIN_TRADER_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 64,64)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 1, 1)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 64,64)
                            .setForSale(TTMContent.ITEM_COIN_GOLD.get(), 1, 1)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_GOLD.get(), 64,64)
                            .setForSale(TTMContent.ITEM_COIN_MITHRIL.get(), 1, 1)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 1,1)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 64, 64)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_GOLD.get(), 1,1)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 64, 64)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,1)
                            .setForSale(TTMContent.ITEM_COIN_GOLD.get(), 64, 64)
                            .build()));
        }
        if (event.getType() == ProfessionGenerator.GROCERY_STORE_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 3,26)
                            .setForSale(TTMContent.HONEY_CAKE.get(), 1, 3)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 1,16)
                            .setForSale(TTMContent.CRAM.get(), 1, 3)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 1,12)
                            .setForSale(TTMContent.FOOD_HONEY.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 4,16)
                            .setForSale(TTMContent.LEMBAS.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 2,6)
                            .setForSale(TTMContent.MIRUVOR.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_GOLD.get(), 1,4)
                            .setForSale(TTMContent.DRINK_ERU_BLESSING.get(), 1, 1)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_GOLD.get(), 1,6)
                            .setForSale(TTMContent.DRINK_ELF_VITALITY.get(), 1, 1)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 100, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,3)
                            .setForSale(TTMContent.DRINK_PERSONAL_BLACKSMITH.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 100, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,2)
                            .setForSale(TTMContent.DRINK_ENT_DRAUGHT.get(), 1, 2)
                            .build()));
        }
        if (event.getType() == ProfessionGenerator.PET_MERCHANT_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 9,48)
                            .setForSale(TTMContent.TREE_ACORN.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_BRONZE.get(), 5,36)
                            .setForSale(TTMContent.INSECT.get(), 1, 4)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 2,5)
                            .setForSale(TTMContent.GOLDEN_TREE_ACORN.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_SILVER.get(), 2,7)
                            .setForSale(TTMContent.GOLDEN_INSECT.get(), 1, 4)
                            .build()));
        }
        if (event.getType() == ProfessionGenerator.JUNK_TRADER_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.BOTTLE_FANCY.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.MONSTER_FUR.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 25, 0.05F)
                            .setPrice(TTMContent.MONSTER_FLESH.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.MUMAKIL_LEATHER.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.CREBAIN_FEATHER.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.BIRD_FEATHER.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.GOLEM_STONE.get(), 1,12)
                            .setForSale(TTMContent.ITEM_COIN_SILVER.get(), 1, 6)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.GOLEM_STONE_AIR.get(), 1,6)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.GOLEM_STONE_EARTH.get(), 1,6)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.GOLEM_STONE_FIRE.get(), 1,6)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 75, 0.05F)
                            .setPrice(TTMContent.GOLEM_STONE_WATER.get(), 1,6)
                            .setForSale(TTMContent.ITEM_COIN_BRONZE.get(), 1, 2)
                            .build()));
        }
        if (event.getType() == ProfessionGenerator.TRINKET_SMITH_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_RING.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_AMULET.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_CHARM.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_RING.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_RING.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_AMULET.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_CHARM.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_RING.get())
                            .build()));
        }

        if (event.getType() == ProfessionGenerator.TRINKET_TAILOR_PROFESSION.get()){
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(1)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_HAT.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_BELT.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_GLOVE.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(2)
                    .add((new TTMTradeBuilder(16, 50, 0.0F)
                            .setPrice(TTMContent.TRINKET_HAT.get(), 1,1)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_HAT.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_BELT.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_GLOVE.get())
                            .build()));
            event.getTrades().get(3)
                    .add((new TTMTradeBuilder(16, 50, 0.05F)
                            .setPrice(TTMContent.ITEM_COIN_MITHRIL.get(), 1,5)
                            .setTrinketForSaleWithPotion(TTMContent.TRINKET_HAT.get())
                            .build()));
        }
    }

    public interface ITrade {
        @Nullable
        MerchantOffer getOffer(Entity entity, Random rand);
    }
}