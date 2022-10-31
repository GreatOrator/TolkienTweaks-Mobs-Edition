package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.TTMTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TTMTags.blocks.STORAGE_BLOCKS_MITHRIL).add(TTMContent.BLOCK_MITHRIL.get());
        tag(TTMTags.blocks.STORAGE_BLOCKS_MORGULIRON).add(TTMContent.BLOCK_MORGULIRON.get());
            tag(Tags.Blocks.STORAGE_BLOCKS).add(TTMContent.BLOCK_MITHRIL.get(), TTMContent.BLOCK_MORGULIRON.get());
        tag(TTMTags.blocks.ORES_MITHRIL).add(TTMContent.ORE_MITHRIL.get(), TTMContent.ORE_END_MITHRIL.get(), TTMContent.ORE_NETHER_MITHRIL.get());
        tag(TTMTags.blocks.ORES_MORGULIRON).add(TTMContent.ORE_MORGULIRON.get(), TTMContent.ORE_END_MORGULIRON.get(), TTMContent.ORE_NETHER_MORGULIRON.get());
        tag(TTMTags.blocks.ORES_AMMOLITE).add(TTMContent.ORE_AMMOLITE.get(), TTMContent.ORE_END_AMMOLITE.get(), TTMContent.ORE_NETHER_AMMOLITE.get());
            tag(Tags.Blocks.ORES).add(TTMContent.ORE_MITHRIL.get(), TTMContent.ORE_END_MITHRIL.get(), TTMContent.ORE_NETHER_MITHRIL.get(), TTMContent.ORE_MORGULIRON.get(), TTMContent.ORE_END_MORGULIRON.get(), TTMContent.ORE_NETHER_MORGULIRON.get(), TTMContent.ORE_AMMOLITE.get(), TTMContent.ORE_END_AMMOLITE.get(), TTMContent.ORE_NETHER_AMMOLITE.get());
        tag(TTMTags.blocks.WOODEN_FENCES).add(TTMContent.FENCE_MALLORN.get(), TTMContent.FENCE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_LEBETHRON.get());
            tag(BlockTags.WOODEN_FENCES).addTag(TTMTags.blocks.WOODEN_FENCES);
            tag(BlockTags.FENCES).addTag(TTMTags.blocks.WOODEN_FENCES);
        tag(TTMTags.blocks.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN.get(), TTMContent.FENCE_GATE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_GATE_LEBETHRON.get());
            tag(BlockTags.FENCE_GATES).addTag(TTMTags.blocks.FENCE_GATES_WOODEN);
            tag(BlockTags.FENCES).addTag(TTMTags.blocks.FENCE_GATES_WOODEN);
        tag(TTMTags.blocks.SAPLINGS).add(TTMContent.SAPLING_MALLORN.get(),TTMContent.SAPLING_MIRKWOOD.get(), TTMContent.SAPLING_CULUMALDA.get(), TTMContent.SAPLING_LEBETHRON.get(), TTMContent.SAPLING_FANGORNOAK.get());
            tag(BlockTags.SAPLINGS).addTag(TTMTags.blocks.SAPLINGS);
        tag(TTMTags.blocks.LEAVES).add(TTMContent.LEAVES_MALLORN.get(), TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.LEAVES_CULUMALDA.get(), TTMContent.LEAVES_LEBETHRON.get(), TTMContent.LEAVES_FANGORNOAK.get());
            tag(BlockTags.LEAVES).addTag(TTMTags.blocks.LEAVES);
        tag(TTMTags.blocks.PLANKS).add(TTMContent.PLANKS_MALLORN.get(), TTMContent.PLANKS_MIRKWOOD.get(), TTMContent.PLANKS_CULUMALDA.get(), TTMContent.PLANKS_LEBETHRON.get());
            tag(BlockTags.PLANKS).addTag(TTMTags.blocks.PLANKS);
        tag(TTMTags.blocks.WOODEN_STAIRS).add(TTMContent.STAIRS_MALLORN.get(), TTMContent.STAIRS_MIRKWOOD.get(), TTMContent.STAIRS_CULUMALDA.get(), TTMContent.STAIRS_LEBETHRON.get());
            tag(BlockTags.WOODEN_STAIRS).addTag(TTMTags.blocks.WOODEN_STAIRS);
            tag(BlockTags.STAIRS).addTag(TTMTags.blocks.WOODEN_STAIRS);
        tag(TTMTags.blocks.PRESSURE_PLATES).add(TTMContent.PRESSURE_PLATE_MITHRIL.get(), TTMContent.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.PRESSURE_PLATES).addTag(TTMTags.blocks.PRESSURE_PLATES);
        tag(TTMTags.blocks.WOODEN_PRESSURE_PLATES).add(TTMContent.PRESSURE_PLATE_MALLORN.get(), TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), TTMContent.PRESSURE_PLATE_CULUMALDA.get(), TTMContent.PRESSURE_PLATE_LEBETHRON.get(), TTMContent.PRESSURE_PLATE_MITHRIL.get(), TTMContent.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.WOODEN_PRESSURE_PLATES).addTag(TTMTags.blocks.WOODEN_PRESSURE_PLATES);
            tag(BlockTags.PRESSURE_PLATES).addTags(TTMTags.blocks.WOODEN_PRESSURE_PLATES, TTMTags.blocks.PRESSURE_PLATES);
        tag(TTMTags.blocks.WOODEN_SLABS).add(TTMContent.SLAB_MALLORN.get(), TTMContent.SLAB_MIRKWOOD.get(), TTMContent.SLAB_CULUMALDA.get(), TTMContent.SLAB_LEBETHRON.get());
            tag(BlockTags.WOODEN_SLABS).addTag(TTMTags.blocks.WOODEN_SLABS);
            tag(BlockTags.SLABS).addTag(TTMTags.blocks.WOODEN_SLABS);
        tag(TTMTags.blocks.LOGS).add(TTMContent.LOG_MALLORN.get(), TTMContent.LOG_MIRKWOOD.get(), TTMContent.LOG_CULUMALDA.get(), TTMContent.LOG_LEBETHRON.get(), TTMContent.LOG_DEADWOOD.get(), TTMContent.STRIPPED_MALLORN_LOG.get(), TTMContent.STRIPPED_MIRKWOOD_LOG.get(), TTMContent.STRIPPED_CULUMALDA_LOG.get(), TTMContent.STRIPPED_LEBETHRON_LOG.get(), TTMContent.STRIPPED_MALLORN_WOOD.get(), TTMContent.STRIPPED_MIRKWOOD_WOOD.get(), TTMContent.STRIPPED_CULUMALDA_WOOD.get(), TTMContent.STRIPPED_LEBETHRON_WOOD.get(), TTMContent.WOOD_MALLORN.get(), TTMContent.WOOD_MIRKWOOD.get(), TTMContent.WOOD_CULUMALDA.get(), TTMContent.WOOD_LEBETHRON.get());
            tag(BlockTags.LOGS_THAT_BURN).addTag(TTMTags.blocks.LOGS);
            tag(BlockTags.LOGS).addTag(TTMTags.blocks.LOGS);
        tag(TTMTags.blocks.WOODEN_DOORS).add(TTMContent.DOOR_MALLORN.get(), TTMContent.DOOR_MIRKWOOD.get(), TTMContent.DOOR_CULUMALDA.get(), TTMContent.DOOR_LEBETHRON.get());
            tag(BlockTags.WOODEN_DOORS).addTag(TTMTags.blocks.WOODEN_DOORS);
        tag(TTMTags.blocks.DOORS).add(TTMContent.DOOR_MITHRIL.get(), TTMContent.DOOR_MORGULIRON.get(), TTMContent.DOOR_DURIN.get());
            tag(BlockTags.DOORS).addTag(TTMTags.blocks.DOORS);
            tag(BlockTags.DOORS).addTags(TTMTags.blocks.WOODEN_DOORS, TTMTags.blocks.DOORS);
        tag(TTMTags.blocks.TRAPDOORS).add(TTMContent.TRAPDOOR_MITHRIL.get(), TTMContent.TRAPDOOR_MORGULIRON.get());
            tag(BlockTags.TRAPDOORS).addTag(TTMTags.blocks.TRAPDOORS);
        tag(TTMTags.blocks.WOODEN_TRAPDOORS).add(TTMContent.TRAPDOOR_MALLORN.get(), TTMContent.TRAPDOOR_MIRKWOOD.get(), TTMContent.TRAPDOOR_CULUMALDA.get(), TTMContent.TRAPDOOR_LEBETHRON.get());
            tag(BlockTags.WOODEN_TRAPDOORS).addTag(TTMTags.blocks.WOODEN_TRAPDOORS);
            tag(BlockTags.DOORS).addTags(TTMTags.blocks.WOODEN_TRAPDOORS, TTMTags.blocks.TRAPDOORS);
        tag(TTMTags.blocks.MUSHROOM_GROW_BLOCK).add(TTMContent.MUSHROOM_BLOOM_DECAY.get(), TTMContent.MUSHROOM_DECAY_BLOOM.get());
            tag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(TTMTags.blocks.MUSHROOM_GROW_BLOCK);
        tag(TTMTags.blocks.FLOWERS).add(TTMContent.FLOWER_ALFIRIN.get(), TTMContent.FLOWER_ATHELAS.get(), TTMContent.FLOWER_MIRKWOOD.get(), TTMContent.FLOWER_NIPHREDIL.get(), TTMContent.FLOWER_SIMBELMYNE.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), TTMContent.FLOWER_SWAMPMILKWEED.get());
            tag(BlockTags.FLOWERS).addTag(TTMTags.blocks.FLOWERS);
        tag(TTMTags.blocks.SIGNS).add(TTMContent.PLACARD.get(), TTMContent.MALLORN_SIGN.get(), TTMContent.MIRKWOOD_SIGN.get(), TTMContent.CULUMALDA_SIGN.get(), TTMContent.LEBETHRON_SIGN.get());
            tag(BlockTags.STANDING_SIGNS).addTag(TTMTags.blocks.SIGNS);
            tag(BlockTags.WALL_SIGNS).addTag(TTMTags.blocks.SIGNS);
            tag(BlockTags.SIGNS).addTag(TTMTags.blocks.SIGNS);
        tag(TTMTags.blocks.SLEEPING_BAGS).add(TTMContent.SLEEPING_BAG_RED.get(), TTMContent.SLEEPING_BAG_BLUE.get(), TTMContent.SLEEPING_BAG_BLACK.get(), TTMContent.SLEEPING_BAG_BROWN.get(), TTMContent.SLEEPING_BAG_CYAN.get(), TTMContent.SLEEPING_BAG_GRAY.get(), TTMContent.SLEEPING_BAG_GREEN.get(), TTMContent.SLEEPING_BAG_LIGHT_BLUE.get(), TTMContent.SLEEPING_BAG_LIGHT_GRAY.get(), TTMContent.SLEEPING_BAG_LIME.get(), TTMContent.SLEEPING_BAG_MAGENTA.get(), TTMContent.SLEEPING_BAG_ORANGE.get(), TTMContent.SLEEPING_BAG_PINK.get(), TTMContent.SLEEPING_BAG_PURPLE.get(), TTMContent.SLEEPING_BAG_WHITE.get(), TTMContent.SLEEPING_BAG_YELLOW.get());
            tag(BlockTags.BEDS).addTag(TTMTags.blocks.SLEEPING_BAGS);
        tag(TTMTags.blocks.BARRELS_WOODEN).add(TTMContent.BARREL_MITHRIL.get(), TTMContent.BARREL_MORGULIRON.get());
        tag(TTMTags.blocks.CROPS).add(TTMContent.PIPEWEED.get());
            tag(BlockTags.CROPS).addTag(TTMTags.blocks.CROPS);
        tag(TTMTags.blocks.TORCHES).add(TTMContent.TORCH_MALLORN.get(), TTMContent.TORCH_MIRKWOOD.get(), TTMContent.TORCH_CULUMALDA.get(), TTMContent.TORCH_LEBETHRON.get());
        tag(TTMTags.blocks.BARS_IRON).add(TTMContent.MITHRIL_BARS.get(), TTMContent.MORGULIRON_BARS.get());
        tag(TTMTags.blocks.DECAY_GROW_BLOCK).add(Blocks.COBBLESTONE).add(Blocks.STONE).add(Blocks.MYCELIUM).add(Blocks.PODZOL).add(Blocks.CRIMSON_NYLIUM).add(Blocks.WARPED_NYLIUM);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Block Tags";
    }
}