package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.EnchantRandomly;
import net.minecraft.loot.functions.EnchantWithLevels;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ChestLootGenerator extends ChestLootTables {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> registrar) {
        //Adding stuff to vanilla loot tables
        createInjectPool(registrar, "shipwreck_supply", LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(TTMContent.CRAM.get())
                                .setWeight(2)
                                .apply(SetCount.setCount(RandomValueRange.between(0, 4)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(TTMContent.INSECT.get())
                                .setWeight(2)
                                .apply(SetCount.setCount(RandomValueRange.between(0, 4)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(TTMContent.TREE_ACORN.get())
                                .setWeight(2)
                                .apply(SetCount.setCount(RandomValueRange.between(0, 4))))));

        //New loot tables
        registrar.accept(new ResourceLocation(MODID, "chests/hobbit_grocer"),
            LootTable.lootTable()
                .withPool(LootPool.lootPool()
                    .setRolls(RandomValueRange.between(3, 5))
                    .bonusRolls(1, 2)
                    .add(ItemLootEntry.lootTableItem(TTMContent.FOOD_HONEY.get())
                        .setWeight(18))
                    .add(ItemLootEntry.lootTableItem(TTMContent.HONEY_CAKE.get())
                        .setWeight(12)
                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                    .add(ItemLootEntry.lootTableItem(Items.MUSIC_DISC_13)
                        .setWeight(15)
                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                    .add(ItemLootEntry.lootTableItem(Items.MUSIC_DISC_CAT)
                            .setWeight(15)
                            .apply(SetCount.setCount(RandomValueRange.between(1, 2))))
                    .add(ItemLootEntry.lootTableItem(Items.NAME_TAG)
                            .setWeight(20)
                            .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                    .add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
                            .setWeight(2))
                    .add(ItemLootEntry.lootTableItem(Items.BEETROOT_SEEDS)
                            .setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                    .add(ItemLootEntry.lootTableItem(Items.MELON_SEEDS)
                            .setWeight(15))
                    .add(ItemLootEntry.lootTableItem(Items.PUMPKIN_SEEDS)
                            .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/hobbit_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(3, 5))
                                .bonusRolls(1, 2)
                                .add(ItemLootEntry.lootTableItem(TTMContent.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(ItemLootEntry.lootTableItem(TTMContent.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.STONE_AXE)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.STONE_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 2))))
                                .add(ItemLootEntry.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.APPLE)
                                        .setWeight(2))
                                .add(ItemLootEntry.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(ItemLootEntry.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/elven_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(3, 5))
                                .bonusRolls(1, 2)
                                .add(ItemLootEntry.lootTableItem(TTMContent.DRINK_ELF_VITALITY.get())
                                        .setWeight(18))
                                .add(ItemLootEntry.lootTableItem(TTMContent.LEMBAS.get())
                                        .setWeight(12)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_AXE)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 2))))
                                .add(ItemLootEntry.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(2))
                                .add(ItemLootEntry.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(ItemLootEntry.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/barrow_chest"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(3, 5))
                                .bonusRolls(1, 2)
                                .add(ItemLootEntry.lootTableItem(TTMContent.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(ItemLootEntry.lootTableItem(TTMContent.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.BOOK)
                                        .setWeight(10)
                                        .apply(EnchantRandomly.randomApplicableEnchantment())
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 1))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_HORSE_ARMOR)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 2))))
                                .add(ItemLootEntry.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.MUSIC_DISC_CAT)
                                        .setWeight(2))
                                .add(ItemLootEntry.lootTableItem(Items.MUSIC_DISC_13)
                                        .setWeight(10)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(15))
                                .add(ItemLootEntry.lootTableItem(Items.SADDLE)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/barrow_grave"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(RandomValueRange.between(3, 5))
                                .bonusRolls(1, 2)
                                .add(ItemLootEntry.lootTableItem(TTMContent.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(ItemLootEntry.lootTableItem(Items.ROTTEN_FLESH)
                                        .setWeight(12)
                                        .apply(SetCount.setCount(RandomValueRange.between(3, 7))))
                                .add(ItemLootEntry.lootTableItem(Items.BOOK)
                                        .setWeight(10)
                                        .apply(EnchantRandomly.randomApplicableEnchantment())
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 1))))
                                .add(ItemLootEntry.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetCount.setCount(RandomValueRange.between(4, 8))))
                                .add(ItemLootEntry.lootTableItem(Items.EMERALD)
                                        .setWeight(20)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT)
                                        .setWeight(2))
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3)))
                                .add(ItemLootEntry.lootTableItem(Items.IRON_INGOT)
                                        .setWeight(10)
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3))))
                                .add(ItemLootEntry.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(15))
                                .add(ItemLootEntry.lootTableItem(Items.DIAMOND)
                                        .setWeight(10))
                                        .apply(SetCount.setCount(RandomValueRange.between(1, 3)))));

    }

    public void createInjectPool(BiConsumer<ResourceLocation, LootTable.Builder> consumer, String name, LootTable.Builder builder) {
        consumer.accept(new ResourceLocation(MODID, "inject/chests/" + name), builder);
    }
}
