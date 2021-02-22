package com.greatorator.tolkienmobs.datagen;


import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
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
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();

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
            registerDropSelfLootTable(TTMContent.MITHRIL_BARS.get());
            registerDropSelfLootTable(TTMContent.DOOR_MITHRIL.get());
            registerDropSelfLootTable(TTMContent.BLOCK_MORGULIRON.get());
            registerDropSelfLootTable(TTMContent.MORGULIRON_BARS.get());
            registerDropSelfLootTable(TTMContent.DOOR_MORGULIRON.get());

            // Blocks - Wood & Foliage
            registerDropSelfLootTable(TTMContent.LOG_MALLORN.get());
            registerDropSelfLootTable(TTMContent.LOG_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.LOG_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.LOG_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.PLANKS_MALLORN.get());
            registerDropSelfLootTable(TTMContent.PLANKS_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.PLANKS_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.PLANKS_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.STAIRS_MALLORN.get());
            registerDropSelfLootTable(TTMContent.STAIRS_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.STAIRS_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.STAIRS_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.SLAB_MALLORN.get());
            registerDropSelfLootTable(TTMContent.SLAB_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.SLAB_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.SLAB_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.DOOR_MALLORN.get());
            registerDropSelfLootTable(TTMContent.DOOR_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.DOOR_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.DOOR_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.FENCE_GATE_MALLORN.get());
            registerDropSelfLootTable(TTMContent.FENCE_GATE_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.FENCE_GATE_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.FENCE_GATE_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.FENCE_MALLORN.get());
            registerDropSelfLootTable(TTMContent.FENCE_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.FENCE_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.FENCE_LEBETHRON.get());
            registerDropSelfLootTable(TTMContent.SAPLING_MALLORN.get());
            registerDropSelfLootTable(TTMContent.SAPLING_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.SAPLING_CULUMALDA.get());
            registerDropSelfLootTable(TTMContent.SAPLING_LEBETHRON.get());
            registerLootTable(TTMContent.LEAVES_MALLORN.get(), SticksAndAcorns(TTMContent.LEAVES_MALLORN.get(), TTMContent.SAPLING_MALLORN.get(), RARE_SAPLING_DROP_RATES));
            registerLootTable(TTMContent.LEAVES_MIRKWOOD.get(), droppingWithChancesAndSticks(TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.SAPLING_MIRKWOOD.get(), DEFAULT_SAPLING_DROP_RATES));
            registerLootTable(TTMContent.LEAVES_CULUMALDA.get(), droppingWithChancesSticksAndApples(TTMContent.LEAVES_CULUMALDA.get(), TTMContent.SAPLING_CULUMALDA.get(), DEFAULT_SAPLING_DROP_RATES));
            registerLootTable(TTMContent.LEAVES_LEBETHRON.get(), droppingWithChancesSticksAndApples(TTMContent.LEAVES_LEBETHRON.get(), TTMContent.SAPLING_LEBETHRON.get(), DEFAULT_SAPLING_DROP_RATES));

            // Blocks - Plants & Flowers
            registerDropSelfLootTable(TTMContent.MUSHROOM_DECAY_BLOOM.get());
            registerDropSelfLootTable(TTMContent.MUSHROOM_BLOOM_DECAY.get());
            registerDropSelfLootTable(TTMContent.FLOWER_SIMBELMYNE.get());
            registerDropSelfLootTable(TTMContent.FLOWER_MIRKWOOD.get());
            registerDropSelfLootTable(TTMContent.FLOWER_ALFIRIN.get());
            registerDropSelfLootTable(TTMContent.FLOWER_ATHELAS.get());
            registerDropSelfLootTable(TTMContent.FLOWER_NIPHREDIL.get());
            registerDropSelfLootTable(TTMContent.FLOWER_SWAMPMILKWEED.get());
            registerDropSelfLootTable(TTMContent.FLOWER_LILLYOFTHEVALLEY.get());

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
        protected static LootTable.Builder SticksAndAcorns(Block block, Block sapling, float... chances) {
            return droppingWithChancesAndSticks(block, sapling, chances).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(NOT_SILK_TOUCH_OR_SHEARS).addEntry(withSurvivesExplosion(block, ItemLootEntry.builder(TTMContent.TREE_ACORN.get())).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
        }

    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Loot Tables";
    }
}
