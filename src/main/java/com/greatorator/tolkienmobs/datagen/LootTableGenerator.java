package com.greatorator.tolkienmobs.datagen;


import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by brandon3055 on 10/3/20.
 */
public class LootTableGenerator extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTables = ImmutableList.of(Pair.of(BlockLootTables::new, LootParameterSets.BLOCK));

    public LootTableGenerator(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return lootTables;
    }

    public static class BlockLootTables extends net.minecraft.data.loot.BlockLootTables {

        protected void addTables() {
            // Blocks - Metals & Gems
            registerDropSelfLootTable(TTMContent.BLOCK_MITHRIL.get());
            registerDropSelfLootTable(TTMContent.BLOCK_MORGULIRON.get());

            // Blocks - Wood & Foliage
            registerDropSelfLootTable(TTMContent.LOG_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.LOG_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.LOG_MALLORN.get());
            registerDropSelfLootTable(TTMContent.LOG_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.PLANKS_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.PLANKS_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.PLANKS_MALLORN.get());
            registerDropSelfLootTable(TTMContent.PLANKS_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.LEAVES_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.LEAVES_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.LEAVES_MALLORN.get());
            registerDropSelfLootTable(TTMContent.LEAVES_MIRKWOOD.get());

            // Blocks - Custom
            registerDropSelfLootTable(TTMContent.BLOCK_HALLOWED.get());
            registerDropSelfLootTable(TTMContent.STONE_PATH.get());

            //Fortune
            registerLootTable(TTMContent.ORE_MITHRIL.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MITHRIL.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_END_MITHRIL.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MITHRIL.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_NETHER_MITHRIL.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MITHRIL.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_MORGULIRON.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MORGULIRON.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_END_MORGULIRON.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MORGULIRON.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_NETHER_MORGULIRON.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.DUST_MORGULIRON.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_AMMOLITE.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.GEM_AMMOLITE.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_END_AMMOLITE.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.GEM_AMMOLITE.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
            registerLootTable(TTMContent.ORE_NETHER_AMMOLITE.get(), (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(TTMContent.GEM_AMMOLITE.get()).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));


        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
        }

    }
}
