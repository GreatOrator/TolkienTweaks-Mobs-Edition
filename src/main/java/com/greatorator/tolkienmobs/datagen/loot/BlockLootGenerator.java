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
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();

    @Override
    protected void registerLootTable(Block block, LootTable.Builder builder) {
        super.registerLootTable(block, builder);
        knownBlocks.add(block);
    }

    @Override
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
        registerLootTable(TTMContent.LOG_DEADWOOD.get(), ChancesAndSticks(TTMContent.LOG_DEADWOOD.get(), TTMContent.LOG_DEADWOOD.get(), DEFAULT_SAPLING_DROP_RATES));
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
        registerDropSelfLootTable(TTMContent.SAPLING_DEADWOOD.get());
        registerLootTable(TTMContent.LEAVES_MALLORN.get(), SticksAndAcorns(TTMContent.LEAVES_MALLORN.get(), TTMContent.SAPLING_MALLORN.get(), RARE_SAPLING_DROP_RATES));
        registerLootTable(TTMContent.LEAVES_MIRKWOOD.get(), droppingWithChancesAndSticks(TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.SAPLING_MIRKWOOD.get(), DEFAULT_SAPLING_DROP_RATES));
        registerLootTable(TTMContent.LEAVES_CULUMALDA.get(), droppingWithChancesSticksAndApples(TTMContent.LEAVES_CULUMALDA.get(), TTMContent.SAPLING_CULUMALDA.get(), DEFAULT_SAPLING_DROP_RATES));
        registerLootTable(TTMContent.LEAVES_LEBETHRON.get(), droppingWithChancesSticksAndApples(TTMContent.LEAVES_LEBETHRON.get(), TTMContent.SAPLING_LEBETHRON.get(), DEFAULT_SAPLING_DROP_RATES));

        // Blocks - Plants & Flowers
        registerDropSelfLootTable(TTMContent.MUSHROOM_DECAY_BLOOM.get());
        registerDropSelfLootTable(TTMContent.MUSHROOM_BLOOM_DECAY.get());
        registerLootTable(TTMContent.BLOCK_BLOOM_DECAY.get(), droppingItemRarely(TTMContent.MUSHROOM_BLOOM_DECAY.get(), TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get()));
        registerLootTable(TTMContent.BLOCK_DECAY_BLOOM.get(), droppingItemRarely(TTMContent.MUSHROOM_DECAY_BLOOM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get()));
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

    protected static LootTable.Builder ChancesAndSticks(Block block, Block log, float... chances) {
        return droppingWithSilkTouch(block, withSurvivesExplosion(block, ItemLootEntry.builder(log)).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(NOT_SILK_TOUCH_OR_SHEARS).addEntry(withExplosionDecay(block, ItemLootEntry.builder(Items.STICK).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder droppingItemRarely(Block block, IItemProvider item) {
        return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(item).acceptFunction(SetCount.builder(RandomValueRange.of(-6.0F, 2.0F))).acceptFunction(LimitCount.func_215911_a(IntClamper.func_215848_a(0)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Loot Tables - Blocks";
    }
}
