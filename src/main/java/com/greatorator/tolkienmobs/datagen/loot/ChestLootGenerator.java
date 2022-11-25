package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class ChestLootGenerator extends ChestLoot {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> registrar) {
        //Adding stuff to vanilla chest loot tables
        createInjectPool(registrar, "shipwreck_supply", LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(TolkienItems.INSECT.get())
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(TolkienItems.TREE_ACORN.get())
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 4))))));

        //New loot tables
        registrar.accept(new ResourceLocation(MODID, "chests/hobbit_grocer"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.HONEY_CAKE.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.MUSIC_DISC_13)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.MELON_SEEDS)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/hobbit_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.STONE_AXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.STONE_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.APPLE)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/elven_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_ELF_VITALITY.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.LEMBAS.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_AXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/dwarf_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.IRON_AXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.IRON_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/human_house"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.HONEY_CAKE.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.DIAMOND_AXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.DIAMOND_PICKAXE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.BREAD)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.COOKED_SALMON)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/barrow_chest"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(10)
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.MUSIC_DISC_CAT)
                                        .setWeight(2))
                                .add(LootItem.lootTableItem(Items.MUSIC_DISC_13)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.SADDLE)
                                        .setWeight(10))));

        registrar.accept(new ResourceLocation(MODID, "chests/barrow_grave"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.FOOD_HONEY.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(10)
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8))))
                                .add(LootItem.lootTableItem(Items.EMERALD)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .setWeight(2))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.DIAMOND)
                                        .setWeight(10))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))));

        registrar.accept(new ResourceLocation(MODID, "chests/desolate_ruins"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1, 2))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(Items.DIAMOND)
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .setWeight(10)
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 6))))
                                .add(LootItem.lootTableItem(Items.EMERALD)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .setWeight(2))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
                                .add(LootItem.lootTableItem(Items.SADDLE)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.GOLDEN_HORSE_ARMOR)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR)
                                        .setWeight(15))
                                .add(LootItem.lootTableItem(Items.IRON_HORSE_ARMOR)
                                        .setWeight(15))));
        registrar.accept(new ResourceLocation(MODID, "chests/swamp_hag_hut"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_BRONZE.get())
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.REDSTONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BLAZE_POWDER)
                                        .setWeight(10)))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));
        registrar.accept(new ResourceLocation(MODID, "chests/spider_tree"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_BRONZE.get())
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.REDSTONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BLAZE_POWDER)
                                        .setWeight(10)))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));
        registrar.accept(new ResourceLocation(MODID, "chests/warg_pit"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_BRONZE.get())
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MORGULIRON.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_SILVER.get())
                                        .setWeight(10)))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));
        registrar.accept(new ResourceLocation(MODID, "chests/minotaur_chest"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_SILVER.get())
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_GOLD.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MORGULIRON.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MITHRIL.get())
                                        .setWeight(10)))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));
        registrar.accept(new ResourceLocation(MODID, "chests/maze_chest"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 1))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get())
                                        .setWeight(18)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.DRINK_ENT_DRAUGHT.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1))))
                                .add(LootItem.lootTableItem(TolkienItems.GEM_AMMOLITE.get())
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MORGULIRON.get())
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.INGOT_MITHRIL.get())
                                        .setWeight(10)))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))));

        registrar.accept(new ResourceLocation(MODID, "chests/gollum_cave"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.SALMON)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.PUFFERFISH)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.COD)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.TROPICAL_FISH)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 7))))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_GOLD.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));

        registrar.accept(new ResourceLocation(MODID, "chests/tower_chest"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(3, 5))
                                .setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(TolkienItems.GROG.get())
                                        .setWeight(18))
                                .add(LootItem.lootTableItem(TolkienItems.CRAM.get())
                                        .setWeight(12)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.NAME_TAG)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(Items.COOKIE)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                                .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(20)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1))))
                                .add(LootItem.lootTableItem(TolkienItems.MONSTER_FLESH.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
                                .add(LootItem.lootTableItem(TolkienItems.NUGGET_MORGULIRON.get())
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 7))))
                                .add(LootItem.lootTableItem(TolkienItems.ITEM_COIN_GOLD.get())
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
    }

    public void createInjectPool(BiConsumer<ResourceLocation, LootTable.Builder> consumer, String name, LootTable.Builder builder) {
        consumer.accept(new ResourceLocation(MODID, "inject/chests/" + name), builder);
    }

    public String getName() {
        return NAME + " - Loot Tables - Chests";
    }
}
