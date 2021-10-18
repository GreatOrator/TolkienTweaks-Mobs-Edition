package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.LimitCount;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.registry.Registry;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BlockLootGenerator extends BlockLootTables {
    private final Set<Block> knownBlocks = new HashSet<>();

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        knownBlocks.add(block);
    }

    @Override
    protected void addTables() {
        // Blocks - Metals & Gems
        dropSelf(TTMContent.BLOCK_MITHRIL.get());
        dropSelf(TTMContent.MITHRIL_BARS.get());
        dropSelf(TTMContent.DOOR_MITHRIL.get());
        dropSelf(TTMContent.BLOCK_MORGULIRON.get());
        dropSelf(TTMContent.MORGULIRON_BARS.get());
        dropSelf(TTMContent.DOOR_MORGULIRON.get());

        // Blocks - Wood & Foliage
        dropSelf(TTMContent.LOG_MALLORN.get());
        dropSelf(TTMContent.LOG_MIRKWOOD.get());
        dropSelf(TTMContent.LOG_CULUMALDA.get());
        dropSelf(TTMContent.LOG_LEBETHRON.get());
        add(TTMContent.LOG_DEADWOOD.get(), ChancesAndSticks(TTMContent.LOG_DEADWOOD.get(), TTMContent.LOG_DEADWOOD.get(), DEFAULT_SAPLING_DROP_RATES));
        dropSelf(TTMContent.PLANKS_MALLORN.get());
        dropSelf(TTMContent.PLANKS_MIRKWOOD.get());
        dropSelf(TTMContent.PLANKS_CULUMALDA.get());
        dropSelf(TTMContent.PLANKS_LEBETHRON.get());
        dropSelf(TTMContent.STAIRS_MALLORN.get());
        dropSelf(TTMContent.STAIRS_MIRKWOOD.get());
        dropSelf(TTMContent.STAIRS_CULUMALDA.get());
        dropSelf(TTMContent.STAIRS_LEBETHRON.get());
        dropSelf(TTMContent.SLAB_MALLORN.get());
        dropSelf(TTMContent.SLAB_MIRKWOOD.get());
        dropSelf(TTMContent.SLAB_LEBETHRON.get());
        dropSelf(TTMContent.SLAB_CULUMALDA.get());
        dropSelf(TTMContent.DOOR_MALLORN.get());
        dropSelf(TTMContent.DOOR_MIRKWOOD.get());
        dropSelf(TTMContent.DOOR_CULUMALDA.get());
        dropSelf(TTMContent.DOOR_LEBETHRON.get());
        dropSelf(TTMContent.FENCE_GATE_MALLORN.get());
        dropSelf(TTMContent.FENCE_GATE_MIRKWOOD.get());
        dropSelf(TTMContent.FENCE_GATE_CULUMALDA.get());
        dropSelf(TTMContent.FENCE_GATE_LEBETHRON.get());
        dropSelf(TTMContent.FENCE_MALLORN.get());
        dropSelf(TTMContent.FENCE_MIRKWOOD.get());
        dropSelf(TTMContent.FENCE_CULUMALDA.get());
        dropSelf(TTMContent.FENCE_LEBETHRON.get());
        dropSelf(TTMContent.SAPLING_MALLORN.get());
        dropSelf(TTMContent.SAPLING_MIRKWOOD.get());
        dropSelf(TTMContent.SAPLING_CULUMALDA.get());
        dropSelf(TTMContent.SAPLING_LEBETHRON.get());
        dropSelf(TTMContent.SAPLING_DEADWOOD.get());
        add(TTMContent.LEAVES_MALLORN.get(), SticksAndAcorns(TTMContent.LEAVES_MALLORN.get(), TTMContent.SAPLING_MALLORN.get(), RARE_SAPLING_DROP_RATES));
        add(TTMContent.LEAVES_MIRKWOOD.get(), createLeavesDrops(TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.SAPLING_MIRKWOOD.get(), DEFAULT_SAPLING_DROP_RATES));
        add(TTMContent.LEAVES_CULUMALDA.get(), createOakLeavesDrops(TTMContent.LEAVES_CULUMALDA.get(), TTMContent.SAPLING_CULUMALDA.get(), DEFAULT_SAPLING_DROP_RATES));
        add(TTMContent.LEAVES_LEBETHRON.get(), createOakLeavesDrops(TTMContent.LEAVES_LEBETHRON.get(), TTMContent.SAPLING_LEBETHRON.get(), DEFAULT_SAPLING_DROP_RATES));
        dropSelf(TTMContent.LEAFPILE_MALLORN.get());
        dropSelf(TTMContent.LEAFPILE_MIRKWOOD.get());
        dropSelf(TTMContent.LEAFPILE_CULUMALDA.get());
        dropSelf(TTMContent.LEAFPILE_LEBETHRON.get());

        // Blocks - Plants & Flowers
        dropSelf(TTMContent.MUSHROOM_DECAY_BLOOM.get());
        dropSelf(TTMContent.MUSHROOM_BLOOM_DECAY.get());
        add(TTMContent.BLOCK_BLOOM_DECAY.get(), droppingItemRarely(TTMContent.MUSHROOM_BLOOM_DECAY.get(), TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get()));
        add(TTMContent.BLOCK_DECAY_BLOOM.get(), droppingItemRarely(TTMContent.MUSHROOM_DECAY_BLOOM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get()));
        dropSelf(TTMContent.FLOWER_SIMBELMYNE.get());
        dropSelf(TTMContent.FLOWER_MIRKWOOD.get());
        dropSelf(TTMContent.FLOWER_ALFIRIN.get());
        dropSelf(TTMContent.FLOWER_ATHELAS.get());
        dropSelf(TTMContent.FLOWER_NIPHREDIL.get());
        dropSelf(TTMContent.FLOWER_SWAMPMILKWEED.get());
        dropSelf(TTMContent.FLOWER_LILLYOFTHEVALLEY.get());

        // Blocks - Custom
        dropSelf(TTMContent.BLOCK_HALLOWED.get());
        dropSelf(TTMContent.STONE_PATH.get());

        //Fortune
        add(TTMContent.ORE_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MITHRIL.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_END_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MITHRIL.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_NETHER_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MITHRIL.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MORGULIRON.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_END_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MORGULIRON.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_NETHER_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.DUST_MORGULIRON.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.GEM_AMMOLITE.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_END_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.GEM_AMMOLITE.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TTMContent.ORE_NETHER_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(TTMContent.GEM_AMMOLITE.get()).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

    }

    protected static LootTable.Builder SticksAndAcorns(Block block, Block sapling, float... chances) {
        return createLeavesDrops(block, sapling, chances).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionCondition(block, ItemLootEntry.lootTableItem(TTMContent.TREE_ACORN.get())).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    protected static LootTable.Builder ChancesAndSticks(Block block, Block log, float... chances) {
        return createSilkTouchDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(log)).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, ItemLootEntry.lootTableItem(Items.STICK).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder droppingItemRarely(Block block, IItemProvider item) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(item).apply(SetCount.setCount(RandomValueRange.between(-6.0F, 2.0F))).apply(LimitCount.limitCount(IntClamper.lowerBound(0)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Loot Tables - Blocks";
    }
}
