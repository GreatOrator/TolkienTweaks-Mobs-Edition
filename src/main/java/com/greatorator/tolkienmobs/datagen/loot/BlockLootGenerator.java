package com.greatorator.tolkienmobs.datagen.loot;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.CropsBlock;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class BlockLootGenerator extends BlockLoot {
    private static final LootItemCondition.Builder CROP_DROP = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TolkienBlocks.PIPEWEED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, 7));

    protected void addTables() {
        // Blocks - Metals & Gems
        dropSelf(TolkienBlocks.BLOCK_MITHRIL.get());
        dropSelf(TolkienBlocks.RAW_MITHRIL_BLOCK.get());
        dropSelf(TolkienBlocks.MITHRIL_BARS.get());
        dropSelf(TolkienBlocks.DOOR_MITHRIL.get());
        dropSelf(TolkienBlocks.TRAPDOOR_MITHRIL.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get());
        dropSelf(TolkienBlocks.RAW_MORGULIRON_BLOCK.get());
        dropSelf(TolkienBlocks.BLOCK_MORGULIRON.get());
        dropSelf(TolkienBlocks.MORGULIRON_BARS.get());
        dropSelf(TolkienBlocks.DOOR_MORGULIRON.get());
        dropSelf(TolkienBlocks.TRAPDOOR_MORGULIRON.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get());
        dropSelf(TolkienBlocks.DOOR_DURIN.get());

        // Blocks - Wood & Foliage
        dropSelf(TolkienBlocks.LOG_MALLORN.get());
        dropSelf(TolkienBlocks.LOG_MIRKWOOD.get());
        dropSelf(TolkienBlocks.LOG_CULUMALDA.get());
        dropSelf(TolkienBlocks.LOG_LEBETHRON.get());
        add(TolkienBlocks.LOG_DEADWOOD.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropSelf(TolkienBlocks.LOG_FANGORNOAK.get());
        dropSelf(TolkienBlocks.STRIPPED_MALLORN_LOG.get());
        dropSelf(TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get());
        dropSelf(TolkienBlocks.STRIPPED_CULUMALDA_LOG.get());
        dropSelf(TolkienBlocks.STRIPPED_LEBETHRON_LOG.get());
        add(TolkienBlocks.STRIPPED_DEADWOOD_LOG.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropSelf(TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get());
        dropSelf(TolkienBlocks.WOOD_MALLORN.get());
        dropSelf(TolkienBlocks.WOOD_MIRKWOOD.get());
        dropSelf(TolkienBlocks.WOOD_CULUMALDA.get());
        dropSelf(TolkienBlocks.WOOD_LEBETHRON.get());
        add(TolkienBlocks.WOOD_DEADWOOD.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropSelf(TolkienBlocks.WOOD_FANGORNOAK.get());
        dropSelf(TolkienBlocks.STRIPPED_MALLORN_WOOD.get());
        dropSelf(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get());
        dropSelf(TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get());
        dropSelf(TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get());
        add(TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        dropSelf(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get());
        dropOther(TolkienBlocks.MALLORN_BUTTON.get(), TolkienItems.MALLORN_BUTTON_ITEM.get());
        dropOther(TolkienBlocks.MIRKWOOD_BUTTON.get(), TolkienItems.MIRKWOOD_BUTTON_ITEM.get());
        dropOther(TolkienBlocks.CULUMALDA_BUTTON.get(), TolkienItems.CULUMALDA_BUTTON_ITEM.get());
        dropOther(TolkienBlocks.LEBETHRON_BUTTON.get(), TolkienItems.LEBETHRON_BUTTON_ITEM.get());
        dropOther(TolkienBlocks.DEADWOOD_BUTTON.get(), TolkienItems.DEADWOOD_BUTTON_ITEM.get());
        dropOther(TolkienBlocks.FANGORNOAK_BUTTON.get(), TolkienItems.FANGORNOAK_BUTTON_ITEM.get());
        dropSelf(TolkienBlocks.PLANKS_MALLORN.get());
        dropSelf(TolkienBlocks.PLANKS_MIRKWOOD.get());
        dropSelf(TolkienBlocks.PLANKS_CULUMALDA.get());
        dropSelf(TolkienBlocks.PLANKS_LEBETHRON.get());
        dropSelf(TolkienBlocks.PLANKS_DEADWOOD.get());
        dropSelf(TolkienBlocks.PLANKS_FANGORNOAK.get());
        dropSelf(TolkienBlocks.STAIRS_MALLORN.get());
        dropSelf(TolkienBlocks.STAIRS_MIRKWOOD.get());
        dropSelf(TolkienBlocks.STAIRS_CULUMALDA.get());
        dropSelf(TolkienBlocks.STAIRS_LEBETHRON.get());
        dropSelf(TolkienBlocks.STAIRS_DEADWOOD.get());
        dropSelf(TolkienBlocks.STAIRS_FANGORNOAK.get());
        dropSelf(TolkienBlocks.SLAB_MALLORN.get());
        dropSelf(TolkienBlocks.SLAB_MIRKWOOD.get());
        dropSelf(TolkienBlocks.SLAB_LEBETHRON.get());
        dropSelf(TolkienBlocks.SLAB_CULUMALDA.get());
        dropSelf(TolkienBlocks.SLAB_DEADWOOD.get());
        dropSelf(TolkienBlocks.SLAB_FANGORNOAK.get());
        dropSelf(TolkienBlocks.DOOR_MALLORN.get());
        dropSelf(TolkienBlocks.DOOR_MIRKWOOD.get());
        dropSelf(TolkienBlocks.DOOR_CULUMALDA.get());
        dropSelf(TolkienBlocks.DOOR_LEBETHRON.get());
        dropSelf(TolkienBlocks.DOOR_DEADWOOD.get());
        dropSelf(TolkienBlocks.DOOR_FANGORNOAK.get());
        dropSelf(TolkienBlocks.FENCE_GATE_MALLORN.get());
        dropSelf(TolkienBlocks.FENCE_GATE_MIRKWOOD.get());
        dropSelf(TolkienBlocks.FENCE_GATE_CULUMALDA.get());
        dropSelf(TolkienBlocks.FENCE_GATE_LEBETHRON.get());
        dropSelf(TolkienBlocks.FENCE_GATE_DEADWOOD.get());
        dropSelf(TolkienBlocks.FENCE_GATE_FANGORNOAK.get());
        dropSelf(TolkienBlocks.FENCE_MALLORN.get());
        dropSelf(TolkienBlocks.FENCE_MIRKWOOD.get());
        dropSelf(TolkienBlocks.FENCE_CULUMALDA.get());
        dropSelf(TolkienBlocks.FENCE_LEBETHRON.get());
        dropSelf(TolkienBlocks.FENCE_DEADWOOD.get());
        dropSelf(TolkienBlocks.FENCE_FANGORNOAK.get());
        dropSelf(TolkienBlocks.TRAPDOOR_MALLORN.get());
        dropSelf(TolkienBlocks.TRAPDOOR_MIRKWOOD.get());
        dropSelf(TolkienBlocks.TRAPDOOR_CULUMALDA.get());
        dropSelf(TolkienBlocks.TRAPDOOR_LEBETHRON.get());
        dropSelf(TolkienBlocks.TRAPDOOR_DEADWOOD.get());
        dropSelf(TolkienBlocks.TRAPDOOR_FANGORNOAK.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_MALLORN.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get());
        dropSelf(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get());
        dropSelf(TolkienBlocks.TORCH_MALLORN.get());
        dropSelf(TolkienBlocks.TORCH_MIRKWOOD.get());
        dropSelf(TolkienBlocks.TORCH_CULUMALDA.get());
        dropSelf(TolkienBlocks.TORCH_LEBETHRON.get());
        dropSelf(TolkienBlocks.TORCH_DEADWOOD.get());
        dropSelf(TolkienBlocks.TORCH_FANGORNOAK.get());
        add(TolkienBlocks.WALL_TORCH_MALLORN.get(), createSingleItemTable(TolkienBlocks.TORCH_MALLORN.get(), UniformGenerator.between(1.0F, 1.0F)));
        add(TolkienBlocks.WALL_TORCH_MIRKWOOD.get(), createSingleItemTable(TolkienBlocks.TORCH_MIRKWOOD.get(), UniformGenerator.between(1.0F, 1.0F)));
        add(TolkienBlocks.WALL_TORCH_CULUMALDA.get(), createSingleItemTable(TolkienBlocks.TORCH_CULUMALDA.get(), UniformGenerator.between(1.0F, 1.0F)));
        add(TolkienBlocks.WALL_TORCH_LEBETHRON.get(), createSingleItemTable(TolkienBlocks.TORCH_LEBETHRON.get(), UniformGenerator.between(1.0F, 1.0F)));
        add(TolkienBlocks.WALL_TORCH_DEADWOOD.get(), createSingleItemTable(TolkienBlocks.TORCH_DEADWOOD.get(), UniformGenerator.between(1.0F, 1.0F)));
        add(TolkienBlocks.WALL_TORCH_FANGORNOAK.get(), createSingleItemTable(TolkienBlocks.TORCH_FANGORNOAK.get(), UniformGenerator.between(1.0F, 1.0F)));
        dropSelf(TolkienBlocks.SAPLING_MALLORN.get());
        dropSelf(TolkienBlocks.SAPLING_MIRKWOOD.get());
        dropSelf(TolkienBlocks.SAPLING_CULUMALDA.get());
        dropSelf(TolkienBlocks.SAPLING_LEBETHRON.get());
        dropSelf(TolkienBlocks.SAPLING_DEADWOOD.get());
        dropSelf(TolkienBlocks.SAPLING_FANGORNOAK.get());
        add(TolkienBlocks.LEAVES_MALLORN.get(), (block) -> createLeavesDrops(block, TolkienBlocks.SAPLING_MALLORN.get(), .05f, .0625f, .083333336f, .1f));
        add(TolkienBlocks.LEAVES_MIRKWOOD.get(), (block) -> createLeavesDrops(block, TolkienBlocks.SAPLING_MIRKWOOD.get(), .05f, .0625f, .083333336f, .1f));
        add(TolkienBlocks.LEAVES_CULUMALDA.get(), (block) -> createLeavesDrops(block, TolkienBlocks.SAPLING_CULUMALDA.get(), .05f, .0625f, .083333336f, .1f));
        add(TolkienBlocks.LEAVES_LEBETHRON.get(), (block) -> createLeavesDrops(block, TolkienBlocks.SAPLING_LEBETHRON.get(), .05f, .0625f, .083333336f, .1f));
        add(TolkienBlocks.LEAVES_FANGORNOAK.get(), (block) -> createLeavesDrops(block, TolkienBlocks.SAPLING_FANGORNOAK.get(), .05f, .0625f, .083333336f, .1f));
        dropSelf(TolkienBlocks.LEAFPILE_MALLORN.get());
        dropSelf(TolkienBlocks.LEAFPILE_MIRKWOOD.get());
        dropSelf(TolkienBlocks.LEAFPILE_CULUMALDA.get());
        dropSelf(TolkienBlocks.LEAFPILE_LEBETHRON.get());
        dropSelf(TolkienBlocks.LEAFPILE_FANGORNOAK.get());

        // Blocks - Plants & Flowers
        dropSelf(TolkienBlocks.MUSHROOM_DECAY_BLOOM.get());
        dropSelf(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get());
        add(TolkienBlocks.BLOCK_BLOOM_DECAY.get(), (block) -> {
            return createMushroomBlockDrop(block, TolkienBlocks.MUSHROOM_BLOOM_DECAY.get());
        });
        add(TolkienBlocks.BLOCK_DECAY_BLOOM.get(), (block) -> {
            return createMushroomBlockDrop(block, TolkienBlocks.MUSHROOM_DECAY_BLOOM.get());
        });
        dropSelf(TolkienBlocks.FLOWER_SIMBELMYNE.get());
        dropSelf(TolkienBlocks.FLOWER_MIRKWOOD.get());
        dropSelf(TolkienBlocks.FLOWER_ALFIRIN.get());
        dropSelf(TolkienBlocks.FLOWER_ATHELAS.get());
        dropSelf(TolkienBlocks.FLOWER_NIPHREDIL.get());
        dropSelf(TolkienBlocks.FLOWER_SWAMPMILKWEED.get());
        dropSelf(TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get());
        add(TolkienBlocks.PIPEWEED.get(), createCropDrops(TolkienBlocks.PIPEWEED.get(), TolkienItems.PIPEWEED_ITEM.get(), TolkienItems.PIPEWEED_SEEDS.get(), CROP_DROP));

        // Blocks - Potted
        dropPottedContents(TolkienBlocks.POTTED_MUSHROOM_DECAY_BLOOM.get());
        dropPottedContents(TolkienBlocks.POTTED_MUSHROOM_BLOOM_DECAY.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_SIMBELMYNE.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_MIRKWOOD.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_ALFIRIN.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_ATHELAS.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_NIPHREDIL.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_SWAMPMILKWEED.get());
        dropPottedContents(TolkienBlocks.POTTED_FLOWER_LILLYOFTHEVALLEY.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_MALLORN.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_MIRKWOOD.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_CULUMALDA.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_LEBETHRON.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_DEADWOOD.get());
        dropPottedContents(TolkienBlocks.POTTED_SAPLING_FANGORNOAK.get());

        // Blocks - Custom
        dropSelf(TolkienBlocks.BLOCK_HALLOWED.get());
        dropSelf(TolkienBlocks.STONE_PATH.get());
        dropSelf(TolkienBlocks.TTMFIREPLACE.get());
        dropSelf(TolkienBlocks.PIGGYBANK.get());
        dropSelf(TolkienBlocks.BARREL_MORGULIRON.get());
        dropSelf(TolkienBlocks.BARREL_MITHRIL.get());
        dropSelf(TolkienBlocks.BACKPACK.get());
        dropSelf(TolkienBlocks.CHAMELEON_BLOCK.get());
        dropSelf(TolkienBlocks.ROCKPILE.get());
        dropSelf(TolkienBlocks.PLACARD.get());

        // Blocks - Sleeping Bags
        add(TolkienBlocks.SLEEPING_BAG_RED.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_BLUE.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_BLACK.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_BROWN.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_CYAN.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_GRAY.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_GREEN.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_LIGHT_BLUE.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_LIGHT_GRAY.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_LIME.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_MAGENTA.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_ORANGE.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_PINK.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_PURPLE.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_WHITE.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
        add(TolkienBlocks.SLEEPING_BAG_YELLOW.get(), block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));

        //Fortune
        add(TolkienBlocks.ORE_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MITHRIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_END_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MITHRIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_NETHER_MITHRIL.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MITHRIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MORGULIRON.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_END_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MORGULIRON.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_NETHER_MORGULIRON.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.RAW_MORGULIRON.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.GEM_AMMOLITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_END_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.GEM_AMMOLITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.ORE_NETHER_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.GEM_AMMOLITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
        add(TolkienBlocks.BLOCK_AMMOLITE.get(), (block) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(TolkienItems.GEM_AMMOLITE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.range(1, 4))))));


        //Signs, You will need these for every type you add
        dropOther(TolkienBlocks.MALLORN_SIGN.get(), TolkienItems.MALLORN_SIGN_ITEM.get());
        dropOther(TolkienBlocks.MALLORN_WALL_SIGN.get(), TolkienItems.MALLORN_SIGN_ITEM.get());
        dropOther(TolkienBlocks.MIRKWOOD_SIGN.get(), TolkienItems.MIRKWOOD_SIGN_ITEM.get());
        dropOther(TolkienBlocks.MIRKWOOD_WALL_SIGN.get(), TolkienItems.MIRKWOOD_SIGN_ITEM.get());
        dropOther(TolkienBlocks.CULUMALDA_SIGN.get(), TolkienItems.CULUMALDA_SIGN_ITEM.get());
        dropOther(TolkienBlocks.CULUMALDA_WALL_SIGN.get(), TolkienItems.CULUMALDA_SIGN_ITEM.get());
        dropOther(TolkienBlocks.LEBETHRON_SIGN.get(), TolkienItems.LEBETHRON_SIGN_ITEM.get());
        dropOther(TolkienBlocks.LEBETHRON_WALL_SIGN.get(), TolkienItems.LEBETHRON_SIGN_ITEM.get());
        dropOther(TolkienBlocks.DEADWOOD_SIGN.get(), TolkienItems.DEADWOOD_SIGN_ITEM.get());
        dropOther(TolkienBlocks.DEADWOOD_WALL_SIGN.get(), TolkienItems.DEADWOOD_SIGN_ITEM.get());
        dropOther(TolkienBlocks.FANGORNOAK_SIGN.get(), TolkienItems.FANGORNOAK_SIGN_ITEM.get());
        dropOther(TolkienBlocks.FANGORNOAK_WALL_SIGN.get(), TolkienItems.FANGORNOAK_SIGN_ITEM.get());
    }

    protected static LootTable.Builder createMushroomBlockDrop(Block block, ItemLike like) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(like).apply(SetItemCountFunction.setCount(UniformGenerator.between(-6.0F, 2.0F))).apply(LimitCount.limitCount(IntRange.lowerBound(0)))));
    }

    protected Iterable<Block> getKnownBlocks() {
        return Registry.BLOCK.stream().filter(block -> Objects.requireNonNull(block.getRegistryName()).getNamespace().equals(TolkienMobs.MODID)).collect(Collectors.toList());
    }

    public String getName() {
        return NAME + " - Loot Tables - Blocks";
    }
}
