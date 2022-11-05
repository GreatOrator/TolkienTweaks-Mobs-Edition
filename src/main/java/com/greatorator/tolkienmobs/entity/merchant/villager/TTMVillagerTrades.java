package com.greatorator.tolkienmobs.entity.merchant.villager;

//
//public class TTMVillagerTrades {
//    public static void onVillagerTradesEvent(VillagerTradesEvent event){
//        if (event.getType() == ProfessionGenerator.COIN_TRADER_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 64,64)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 1, 1)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 64,64)
//                            .setForSale(TolkienContent.ITEM_COIN_GOLD.get(), 1, 1)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_GOLD.get(), 64,64)
//                            .setForSale(TolkienContent.ITEM_COIN_MITHRIL.get(), 1, 1)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 1,1)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 64, 64)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_GOLD.get(), 1,1)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 64, 64)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,1)
//                            .setForSale(TolkienContent.ITEM_COIN_GOLD.get(), 64, 64)
//                            .build()));
//        }
//        if (event.getType() == ProfessionGenerator.GROCERY_STORE_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 3,26)
//                            .setForSale(TolkienContent.HONEY_CAKE.get(), 1, 3)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 1,16)
//                            .setForSale(TolkienContent.CRAM.get(), 1, 3)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 1,12)
//                            .setForSale(TolkienContent.FOOD_HONEY.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 4,16)
//                            .setForSale(TolkienContent.LEMBAS.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 2,6)
//                            .setForSale(TolkienContent.MIRUVOR.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_GOLD.get(), 1,4)
//                            .setForSale(TolkienContent.DRINK_ERU_BLESSING.get(), 1, 1)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_GOLD.get(), 1,6)
//                            .setForSale(TolkienContent.DRINK_ELF_VITALITY.get(), 1, 1)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 100, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,3)
//                            .setForSale(TolkienContent.DRINK_PERSONAL_BLACKSMITH.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 100, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,2)
//                            .setForSale(TolkienContent.DRINK_ENT_DRAUGHT.get(), 1, 2)
//                            .build()));
//        }
//        if (event.getType() == ProfessionGenerator.PET_MERCHANT_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 9,48)
//                            .setForSale(TolkienContent.TREE_ACORN.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_BRONZE.get(), 5,36)
//                            .setForSale(TolkienContent.INSECT.get(), 1, 4)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 2,5)
//                            .setForSale(TolkienContent.GOLDEN_TREE_ACORN.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_SILVER.get(), 2,7)
//                            .setForSale(TolkienContent.GOLDEN_INSECT.get(), 1, 4)
//                            .build()));
//        }
//        if (event.getType() == ProfessionGenerator.JUNK_TRADER_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.BOTTLE_FANCY.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.MONSTER_FUR.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 25, 0.05F)
//                            .setPrice(TolkienContent.MONSTER_FLESH.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.MUMAKIL_LEATHER.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.CREBAIN_FEATHER.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.BIRD_FEATHER.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.GOLEM_STONE.get(), 1,12)
//                            .setForSale(TolkienContent.ITEM_COIN_SILVER.get(), 1, 6)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.GOLEM_STONE_AIR.get(), 1,6)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.GOLEM_STONE_EARTH.get(), 1,6)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.GOLEM_STONE_FIRE.get(), 1,6)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 2)
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 75, 0.05F)
//                            .setPrice(TolkienContent.GOLEM_STONE_WATER.get(), 1,6)
//                            .setForSale(TolkienContent.ITEM_COIN_BRONZE.get(), 1, 2)
//                            .build()));
//        }
//        if (event.getType() == ProfessionGenerator.TRINKET_SMITH_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_AMULET.get())
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_CHARM.get())
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_RING.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_AMULET.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_AMULET.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_CHARM.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_CHARM.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_RING.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_RING.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_AMULET.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_CHARM.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_RING.get())
//                            .build()));
//        }
//
//        if (event.getType() == ProfessionGenerator.TRINKET_TAILOR_PROFESSION.get()){
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_BELT.get())
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_GLOVE.get())
//                            .build()));
//            event.getTrades().get(1)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_HAT.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_BELT.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_BELT.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_GLOVE.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_GLOVE.get())
//                            .build()));
//            event.getTrades().get(2)
//                    .add((new TTMTradeBuilder(16, 50, 0.0F)
//                            .setPrice(TolkienContent.TRINKET_HAT.get(), 1,1)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_HAT.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_BELT.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_GLOVE.get())
//                            .build()));
//            event.getTrades().get(3)
//                    .add((new TTMTradeBuilder(16, 50, 0.05F)
//                            .setPrice(TolkienContent.ITEM_COIN_MITHRIL.get(), 1,5)
//                            .setTrinketForSaleWithPotion(TolkienContent.TRINKET_HAT.get())
//                            .build()));
//        }
//    }
//
//    public interface ITrade {
//        @Nullable
//        MerchantOffer getOffer(Entity entity, Random rand);
//    }
//}