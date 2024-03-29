package com.greatorator.tolkienmobs;


import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.datagen.loot.BlockLootGenerator;
import com.greatorator.tolkienmobs.datagen.loot.ChestLootGenerator;
import com.greatorator.tolkienmobs.datagen.loot.EntityLootGenerator;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

/**
 * Created by brandon3055 on 10/3/20.
 */
public class TolkienLootHandler extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(
            Pair.of(BlockLootGenerator::new, LootContextParamSets.BLOCK),
            Pair.of(EntityLootGenerator::new, LootContextParamSets.ENTITY),
            Pair.of(ChestLootGenerator::new, LootContextParamSets.CHEST));

    public TolkienLootHandler(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        //map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return lootTables;
    }

    @Override
    public String getName() {
        return NAME + " - Loot Tables";
    }
}
