package com.greatorator.tolkienmobs.datagen.tags;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienTags.blocks.*;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(STORAGE_BLOCKS_MITHRIL).add(TolkienBlocks.BLOCK_MITHRIL.get(), TolkienBlocks.RAW_MITHRIL_BLOCK.get());
        tag(STORAGE_BLOCKS_MORGULIRON).add(TolkienBlocks.BLOCK_MORGULIRON.get(), TolkienBlocks.RAW_MORGULIRON_BLOCK.get());
            tag(Tags.Blocks.STORAGE_BLOCKS).addTags(STORAGE_BLOCKS_MITHRIL, STORAGE_BLOCKS_MORGULIRON);
        tag(ORES_MITHRIL).add(TolkienBlocks.ORE_MITHRIL.get(), TolkienBlocks.ORE_END_MITHRIL.get(), TolkienBlocks.ORE_NETHER_MITHRIL.get(), TolkienBlocks.ORE_DEEPSLATE_MITHRIL.get());
        tag(ORES_MORGULIRON).add(TolkienBlocks.ORE_MORGULIRON.get(), TolkienBlocks.ORE_END_MORGULIRON.get(), TolkienBlocks.ORE_NETHER_MORGULIRON.get(), TolkienBlocks.ORE_DEEPSLATE_MORGULIRON.get());
        tag(ORES_AMMOLITE).add(TolkienBlocks.ORE_AMMOLITE.get(), TolkienBlocks.ORE_END_AMMOLITE.get(), TolkienBlocks.ORE_NETHER_AMMOLITE.get(), TolkienBlocks.ORE_DEEPSLATE_AMMOLITE.get());
            tag(Tags.Blocks.ORES).addTags(ORES_MITHRIL, ORES_MORGULIRON, ORES_AMMOLITE);
        tag(WOODEN_FENCES).add(TolkienBlocks.FENCE_MALLORN.get(), TolkienBlocks.FENCE_MIRKWOOD.get(), TolkienBlocks.FENCE_CULUMALDA.get(), TolkienBlocks.FENCE_LEBETHRON.get(), TolkienBlocks.FENCE_DEADWOOD.get(), TolkienBlocks.FENCE_FANGORNOAK.get(), TolkienBlocks.FENCE_GATE_MALLORN.get(), TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), TolkienBlocks.FENCE_GATE_CULUMALDA.get(), TolkienBlocks.FENCE_GATE_LEBETHRON.get(), TolkienBlocks.FENCE_GATE_DEADWOOD.get(), TolkienBlocks.FENCE_GATE_FANGORNOAK.get());
        tag(BlockTags.WOODEN_FENCES).addTag(WOODEN_FENCES);
        tag(BlockTags.FENCES).addTag(WOODEN_FENCES);
        tag(FENCE_GATES_WOODEN).add(TolkienBlocks.FENCE_GATE_MALLORN.get(), TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), TolkienBlocks.FENCE_GATE_CULUMALDA.get(), TolkienBlocks.FENCE_GATE_LEBETHRON.get(), TolkienBlocks.FENCE_GATE_DEADWOOD.get(), TolkienBlocks.FENCE_GATE_FANGORNOAK.get());
            tag(BlockTags.FENCE_GATES).addTag(FENCE_GATES_WOODEN);
            tag(BlockTags.FENCES).addTag(FENCE_GATES_WOODEN);
        tag(WALLS).add(TolkienBlocks.WALL_MITHRIL.get(), TolkienBlocks.WALL_MORGULIRON.get());
            tag(BlockTags.WALLS).addTag(WALLS);
        tag(SAPLINGS).add(TolkienBlocks.SAPLING_MALLORN.get(),TolkienBlocks.SAPLING_MIRKWOOD.get(), TolkienBlocks.SAPLING_CULUMALDA.get(), TolkienBlocks.SAPLING_LEBETHRON.get(), TolkienBlocks.SAPLING_DEADWOOD.get(), TolkienBlocks.SAPLING_FANGORNOAK.get());
            tag(BlockTags.SAPLINGS).addTag(SAPLINGS);
        tag(LEAVES).add(TolkienBlocks.LEAVES_MALLORN.get(), TolkienBlocks.LEAVES_MIRKWOOD.get(), TolkienBlocks.LEAVES_CULUMALDA.get(), TolkienBlocks.LEAVES_LEBETHRON.get(), TolkienBlocks.LEAVES_FANGORNOAK.get(), TolkienBlocks.LEAFPILE_MALLORN.get(), TolkienBlocks.LEAFPILE_MIRKWOOD.get(), TolkienBlocks.LEAFPILE_CULUMALDA.get(), TolkienBlocks.LEAFPILE_LEBETHRON.get(), TolkienBlocks.LEAFPILE_FANGORNOAK.get());
            tag(BlockTags.LEAVES).addTag(LEAVES);
        tag(PLANKS).add(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), TolkienBlocks.PLANKS_FANGORNOAK.get());
            tag(BlockTags.PLANKS).addTag(PLANKS);
        tag(WOODEN_STAIRS).add(TolkienBlocks.STAIRS_MALLORN.get(), TolkienBlocks.STAIRS_MIRKWOOD.get(), TolkienBlocks.STAIRS_CULUMALDA.get(), TolkienBlocks.STAIRS_LEBETHRON.get(), TolkienBlocks.STAIRS_DEADWOOD.get(), TolkienBlocks.STAIRS_FANGORNOAK.get());
            tag(BlockTags.WOODEN_STAIRS).addTag(WOODEN_STAIRS);
            tag(BlockTags.STAIRS).addTag(WOODEN_STAIRS);
        tag(PRESSURE_PLATES).add(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.PRESSURE_PLATES).addTag(PRESSURE_PLATES);
        tag(WOODEN_PRESSURE_PLATES).add(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(), TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(), TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(), TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get(), TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get(), TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(), TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.WOODEN_PRESSURE_PLATES).addTag(WOODEN_PRESSURE_PLATES);
            tag(BlockTags.PRESSURE_PLATES).addTags(WOODEN_PRESSURE_PLATES, PRESSURE_PLATES);
        tag(WOODEN_SLABS).add(TolkienBlocks.SLAB_MALLORN.get(), TolkienBlocks.SLAB_MIRKWOOD.get(), TolkienBlocks.SLAB_CULUMALDA.get(), TolkienBlocks.SLAB_LEBETHRON.get(), TolkienBlocks.SLAB_DEADWOOD.get(), TolkienBlocks.SLAB_FANGORNOAK.get());
            tag(BlockTags.WOODEN_SLABS).addTag(WOODEN_SLABS);
            tag(BlockTags.SLABS).addTag(WOODEN_SLABS);
        tag(LOGS).add(TolkienBlocks.LOG_MALLORN.get(), TolkienBlocks.LOG_MIRKWOOD.get(), TolkienBlocks.LOG_CULUMALDA.get(), TolkienBlocks.LOG_LEBETHRON.get(), TolkienBlocks.LOG_DEADWOOD.get(), TolkienBlocks.LOG_FANGORNOAK.get(), TolkienBlocks.STRIPPED_MALLORN_LOG.get(), TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get(), TolkienBlocks.STRIPPED_CULUMALDA_LOG.get(), TolkienBlocks.STRIPPED_LEBETHRON_LOG.get(), TolkienBlocks.STRIPPED_DEADWOOD_LOG.get(), TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get(), TolkienBlocks.STRIPPED_MALLORN_WOOD.get(), TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get(), TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get(), TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get(), TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get(), TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get(), TolkienBlocks.WOOD_MALLORN.get(), TolkienBlocks.WOOD_MIRKWOOD.get(), TolkienBlocks.WOOD_CULUMALDA.get(), TolkienBlocks.WOOD_LEBETHRON.get(), TolkienBlocks.WOOD_DEADWOOD.get(), TolkienBlocks.WOOD_FANGORNOAK.get());
            tag(BlockTags.LOGS_THAT_BURN).addTag(LOGS);
            tag(BlockTags.LOGS).addTag(LOGS);
        tag(WOODEN_DOORS).add(TolkienBlocks.DOOR_MALLORN.get(), TolkienBlocks.DOOR_MIRKWOOD.get(), TolkienBlocks.DOOR_CULUMALDA.get(), TolkienBlocks.DOOR_LEBETHRON.get(), TolkienBlocks.DOOR_DEADWOOD.get(), TolkienBlocks.DOOR_FANGORNOAK.get());
            tag(BlockTags.WOODEN_DOORS).addTag(WOODEN_DOORS);
        tag(DOORS).add(TolkienBlocks.DOOR_MITHRIL.get(), TolkienBlocks.DOOR_MORGULIRON.get(), TolkienBlocks.DOOR_DURIN.get());
            tag(BlockTags.DOORS).addTag(DOORS);
            tag(BlockTags.DOORS).addTags(WOODEN_DOORS, DOORS);
        tag(TRAPDOORS).add(TolkienBlocks.TRAPDOOR_MITHRIL.get(), TolkienBlocks.TRAPDOOR_MORGULIRON.get());
            tag(BlockTags.TRAPDOORS).addTag(TRAPDOORS);
        tag(WOODEN_TRAPDOORS).add(TolkienBlocks.TRAPDOOR_MALLORN.get(), TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), TolkienBlocks.TRAPDOOR_CULUMALDA.get(), TolkienBlocks.TRAPDOOR_LEBETHRON.get(), TolkienBlocks.TRAPDOOR_DEADWOOD.get(), TolkienBlocks.TRAPDOOR_FANGORNOAK.get());
            tag(BlockTags.WOODEN_TRAPDOORS).addTag(WOODEN_TRAPDOORS);
            tag(BlockTags.DOORS).addTags(WOODEN_TRAPDOORS, TRAPDOORS);
        tag(MUSHROOM_GROW_BLOCK).add(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get(), TolkienBlocks.MUSHROOM_DECAY_BLOOM.get());
            tag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(MUSHROOM_GROW_BLOCK);
        tag(FLOWERS).add(TolkienBlocks.FLOWER_ALFIRIN.get(), TolkienBlocks.FLOWER_ATHELAS.get(), TolkienBlocks.FLOWER_MIRKWOOD.get(), TolkienBlocks.FLOWER_NIPHREDIL.get(), TolkienBlocks.FLOWER_SIMBELMYNE.get(), TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get(), TolkienBlocks.FLOWER_SWAMPMILKWEED.get());
            tag(BlockTags.FLOWERS).addTag(FLOWERS);
        tag(SIGNS).add(TolkienBlocks.PLACARD.get(), TolkienBlocks.MALLORN_SIGN.get(), TolkienBlocks.MIRKWOOD_SIGN.get(), TolkienBlocks.CULUMALDA_SIGN.get(), TolkienBlocks.LEBETHRON_SIGN.get(), TolkienBlocks.DEADWOOD_SIGN.get(), TolkienBlocks.FANGORNOAK_SIGN.get());
        tag(WALL_SIGNS).add(TolkienBlocks.MALLORN_WALL_SIGN.get(), TolkienBlocks.MIRKWOOD_WALL_SIGN.get(), TolkienBlocks.CULUMALDA_WALL_SIGN.get(), TolkienBlocks.LEBETHRON_WALL_SIGN.get(), TolkienBlocks.DEADWOOD_WALL_SIGN.get(), TolkienBlocks.FANGORNOAK_WALL_SIGN.get());
            tag(BlockTags.WALL_SIGNS).addTag(WALL_SIGNS);
            tag(BlockTags.SIGNS).addTag(SIGNS);
        tag(SLEEPING_BAGS).add(TolkienBlocks.SLEEPING_BAG_RED.get(), TolkienBlocks.SLEEPING_BAG_BLUE.get(), TolkienBlocks.SLEEPING_BAG_BLACK.get(), TolkienBlocks.SLEEPING_BAG_BROWN.get(), TolkienBlocks.SLEEPING_BAG_CYAN.get(), TolkienBlocks.SLEEPING_BAG_GRAY.get(), TolkienBlocks.SLEEPING_BAG_GREEN.get(), TolkienBlocks.SLEEPING_BAG_LIGHT_BLUE.get(), TolkienBlocks.SLEEPING_BAG_LIGHT_GRAY.get(), TolkienBlocks.SLEEPING_BAG_LIME.get(), TolkienBlocks.SLEEPING_BAG_MAGENTA.get(), TolkienBlocks.SLEEPING_BAG_ORANGE.get(), TolkienBlocks.SLEEPING_BAG_PINK.get(), TolkienBlocks.SLEEPING_BAG_PURPLE.get(), TolkienBlocks.SLEEPING_BAG_WHITE.get(), TolkienBlocks.SLEEPING_BAG_YELLOW.get());
            tag(BlockTags.BEDS).addTag(SLEEPING_BAGS);
        tag(BARRELS_WOODEN).add(TolkienBlocks.BARREL_MITHRIL.get(), TolkienBlocks.BARREL_MORGULIRON.get(), TolkienBlocks.BARREL_MALLORN.get(), TolkienBlocks.BARREL_MIRKWOOD.get(), TolkienBlocks.BARREL_CULUMALDA.get(), TolkienBlocks.BARREL_LEBETHRON.get(), TolkienBlocks.BARREL_DEADWOOD.get(), TolkienBlocks.BARREL_FANGORNOAK.get());
        tag(CROPS).add(TolkienBlocks.PIPEWEED.get());
            tag(BlockTags.CROPS).addTag(CROPS);
        tag(TORCHES).add(TolkienBlocks.TORCH_MALLORN.get(), TolkienBlocks.TORCH_MIRKWOOD.get(), TolkienBlocks.TORCH_CULUMALDA.get(), TolkienBlocks.TORCH_LEBETHRON.get(), TolkienBlocks.TORCH_DEADWOOD.get(), TolkienBlocks.TORCH_FANGORNOAK.get());
            tag(BlockTags.WALL_POST_OVERRIDE).addTag(TORCHES);
        tag(BARS_IRON).add(TolkienBlocks.MITHRIL_BARS.get(), TolkienBlocks.MORGULIRON_BARS.get());
        tag(BARS_GLASS).add(TolkienBlocks.PANE_AMMOLITE.get());
            tag(Tags.Blocks.GLASS_PANES).addTag(BARS_GLASS);
        tag(DECAY_GROW_BLOCK).add(Blocks.COBBLESTONE).add(Blocks.STONE).add(Blocks.MYCELIUM).add(Blocks.PODZOL).add(Blocks.CRIMSON_NYLIUM).add(Blocks.WARPED_NYLIUM);

        // Mineable tools
        tag(MINEABLE_WITH_AXE).addTags(LOGS, FENCE_GATES_WOODEN, WOODEN_FENCES, MUSHROOM_GROW_BLOCK, WOODEN_TRAPDOORS, WOODEN_DOORS, WOODEN_PRESSURE_PLATES, WOODEN_STAIRS, WOODEN_SLABS, PLANKS);
        tag(MINEABLE_WITH_HOE).addTag(LEAVES);
        tag(MINEABLE_WITH_PICKAXE).add(TolkienBlocks.BLOCK_MITHRIL.get(), TolkienBlocks.RAW_MITHRIL_BLOCK.get(), TolkienBlocks.BLOCK_MORGULIRON.get(), TolkienBlocks.RAW_MORGULIRON_BLOCK.get(), TolkienBlocks.ORE_MITHRIL.get(), TolkienBlocks.ORE_END_MITHRIL.get(), TolkienBlocks.ORE_NETHER_MITHRIL.get(), TolkienBlocks.ORE_DEEPSLATE_MITHRIL.get(), TolkienBlocks.ORE_MORGULIRON.get(), TolkienBlocks.ORE_END_MORGULIRON.get(), TolkienBlocks.ORE_NETHER_MORGULIRON.get(), TolkienBlocks.ORE_DEEPSLATE_MORGULIRON.get(), TolkienBlocks.ORE_AMMOLITE.get(), TolkienBlocks.ORE_END_AMMOLITE.get(), TolkienBlocks.ORE_NETHER_AMMOLITE.get(), TolkienBlocks.ORE_DEEPSLATE_AMMOLITE.get(), TolkienBlocks.STONE_PATH.get(), TolkienBlocks.TTMFIREPLACE.get(), TolkienBlocks.PIGGYBANK.get(), TolkienBlocks.MITHRIL_BARS.get(), TolkienBlocks.MORGULIRON_BARS.get(), TolkienBlocks.TRAPDOOR_MITHRIL.get(), TolkienBlocks.TRAPDOOR_MORGULIRON.get(), TolkienBlocks.DOOR_MITHRIL.get(), TolkienBlocks.DOOR_MORGULIRON.get(), TolkienBlocks.DOOR_DURIN.get());
        tag(MINEABLE_WITH_SHOVEL).add(TolkienBlocks.BLOCK_HALLOWED.get());
        tag(NEEDS_DIAMOND_TOOL).addTags(ORES_MITHRIL, ORES_MORGULIRON, ORES_AMMOLITE);
        tag(NEEDS_IRON_TOOL).add(TolkienBlocks.BLOCK_HALLOWED.get(), TolkienBlocks.BLOCK_MITHRIL.get(), TolkienBlocks.RAW_MITHRIL_BLOCK.get(), TolkienBlocks.BLOCK_MORGULIRON.get(), TolkienBlocks.RAW_MORGULIRON_BLOCK.get());
        tag(NEEDS_STONE_TOOL);
    }

    @Nonnull
    @Override
    public String getName() {
        return NAME + " - Block Tags";
    }
}