package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class ChestLootGenerator extends ChestLootTables {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> registrar) {
        //Adding stuff to vanilla loot tables
        createInjectPool(registrar, "shipwreck_supply", LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(TTMContent.CRAM.get())
                                .weight(2)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0, 4)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(TTMContent.INSECT.get())
                                .weight(2)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0, 4)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(TTMContent.TREE_ACORN.get())
                                .weight(2)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0, 4))))));

        //New loot tables
        registrar.accept(new ResourceLocation(MODID, "chests/hobbit_grocer"),
            LootTable.builder()
                .addLootPool(LootPool.builder()
                    .rolls(RandomValueRange.of(3, 5))
                    .bonusRolls(1, 2)
                    .addEntry(ItemLootEntry.builder(TTMContent.FOOD_HONEY.get())
                        .weight(18))
                    .addEntry(ItemLootEntry.builder(TTMContent.HONEY_CAKE.get())
                        .weight(12)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
                    .addEntry(ItemLootEntry.builder(Items.MUSIC_DISC_13)
                        .weight(15)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
                    .addEntry(ItemLootEntry.builder(Items.MUSIC_DISC_CAT)
                            .weight(15)
                            .acceptFunction(SetCount.builder(RandomValueRange.of(1, 2))))
                    .addEntry(ItemLootEntry.builder(Items.NAME_TAG)
                            .weight(20)
                            .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
                    .addEntry(ItemLootEntry.builder(Items.GOLDEN_APPLE)
                            .weight(2))
                    .addEntry(ItemLootEntry.builder(Items.BEETROOT_SEEDS)
                            .weight(10)
                            .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))))
                    .addEntry(ItemLootEntry.builder(Items.MELON_SEEDS)
                            .weight(15))
                    .addEntry(ItemLootEntry.builder(Items.PUMPKIN_SEEDS)
                            .weight(10))));

    }

    public void createInjectPool(BiConsumer<ResourceLocation, LootTable.Builder> consumer, String name, LootTable.Builder builder) {
        consumer.accept(new ResourceLocation(MODID, "inject/chests/" + name), builder);
    }
}
