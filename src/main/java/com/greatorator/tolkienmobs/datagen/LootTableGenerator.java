package com.greatorator.tolkienmobs.datagen;


import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.*;
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
            registerDropSelfLootTable(TTMContent.BLOCK_MITHRIL.get());


            //Special Stuff
//            registerLootTable(DEContent.chaos_crystal, block -> dropping(DEContent.chaos_shard).acceptFunction(SetCount.builder(ConstantRange.of(5))));
//            registerLootTable(DEContent.chaos_crystal_part, blockNoDrop());
//
//            //Fortune
//            registerLootTable(DEContent.ore_draconium_overworld, (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(DEContent.dust_draconium).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 4.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
//            registerLootTable(DEContent.ore_draconium_nether, (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(DEContent.dust_draconium).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 4.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
//            registerLootTable(DEContent.ore_draconium_end, (block) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(DEContent.dust_draconium).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 4.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));


        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
        }

    }
}
