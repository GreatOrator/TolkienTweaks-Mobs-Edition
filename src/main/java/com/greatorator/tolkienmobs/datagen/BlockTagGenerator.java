package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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
        tag(TolkienTags.blocks.STORAGE_BLOCKS_MITHRIL).add(TolkienBlocks.BLOCK_MITHRIL.get());
        tag(TolkienTags.blocks.STORAGE_BLOCKS_MORGULIRON).add(TolkienBlocks.BLOCK_MORGULIRON.get());
            tag(Tags.Blocks.STORAGE_BLOCKS).add(TolkienBlocks.BLOCK_MITHRIL.get(), TolkienBlocks.BLOCK_MORGULIRON.get());
        tag(TolkienTags.blocks.ORES_MITHRIL).add(TolkienBlocks.ORE_MITHRIL.get(), TolkienBlocks.ORE_END_MITHRIL.get(), TolkienBlocks.ORE_NETHER_MITHRIL.get());
        tag(TolkienTags.blocks.ORES_MORGULIRON).add(TolkienBlocks.ORE_MORGULIRON.get(), TolkienBlocks.ORE_END_MORGULIRON.get(), TolkienBlocks.ORE_NETHER_MORGULIRON.get());
        tag(TolkienTags.blocks.ORES_AMMOLITE).add(TolkienBlocks.ORE_AMMOLITE.get(), TolkienBlocks.ORE_END_AMMOLITE.get(), TolkienBlocks.ORE_NETHER_AMMOLITE.get());
            tag(Tags.Blocks.ORES).add(TolkienBlocks.ORE_MITHRIL.get(), TolkienBlocks.ORE_END_MITHRIL.get(), TolkienBlocks.ORE_NETHER_MITHRIL.get(), TolkienBlocks.ORE_MORGULIRON.get(), TolkienBlocks.ORE_END_MORGULIRON.get(), TolkienBlocks.ORE_NETHER_MORGULIRON.get(), TolkienBlocks.ORE_AMMOLITE.get(), TolkienBlocks.ORE_END_AMMOLITE.get(), TolkienBlocks.ORE_NETHER_AMMOLITE.get());
        tag(TolkienTags.blocks.WOODEN_FENCES).add(TolkienBlocks.FENCE_MALLORN.get(), TolkienBlocks.FENCE_MIRKWOOD.get(), TolkienBlocks.FENCE_GATE_CULUMALDA.get(), TolkienBlocks.FENCE_LEBETHRON.get());
            tag(BlockTags.WOODEN_FENCES).addTag(TolkienTags.blocks.WOODEN_FENCES);
            tag(BlockTags.FENCES).addTag(TolkienTags.blocks.WOODEN_FENCES);
        tag(TolkienTags.blocks.FENCE_GATES_WOODEN).add(TolkienBlocks.FENCE_GATE_MALLORN.get(), TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), TolkienBlocks.FENCE_GATE_CULUMALDA.get(), TolkienBlocks.FENCE_GATE_LEBETHRON.get());
            tag(BlockTags.FENCE_GATES).addTag(TolkienTags.blocks.FENCE_GATES_WOODEN);
            tag(BlockTags.FENCES).addTag(TolkienTags.blocks.FENCE_GATES_WOODEN);
        tag(TolkienTags.blocks.SAPLINGS).add(TolkienBlocks.SAPLING_MALLORN.get(),TolkienBlocks.SAPLING_MIRKWOOD.get(), TolkienBlocks.SAPLING_CULUMALDA.get(), TolkienBlocks.SAPLING_LEBETHRON.get(), TolkienBlocks.SAPLING_FANGORNOAK.get());
            tag(BlockTags.SAPLINGS).addTag(TolkienTags.blocks.SAPLINGS);
        tag(TolkienTags.blocks.LEAVES).add(TolkienBlocks.LEAVES_MALLORN.get(), TolkienBlocks.LEAVES_MIRKWOOD.get(), TolkienBlocks.LEAVES_CULUMALDA.get(), TolkienBlocks.LEAVES_LEBETHRON.get(), TolkienBlocks.LEAVES_FANGORNOAK.get());
            tag(BlockTags.LEAVES).addTag(TolkienTags.blocks.LEAVES);
        tag(TolkienTags.blocks.PLANKS).add(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.PLANKS_LEBETHRON.get());
            tag(BlockTags.PLANKS).addTag(TolkienTags.blocks.PLANKS);
        tag(TolkienTags.blocks.WOODEN_STAIRS).add(TolkienBlocks.STAIRS_MALLORN.get(), TolkienBlocks.STAIRS_MIRKWOOD.get(), TolkienBlocks.STAIRS_CULUMALDA.get(), TolkienBlocks.STAIRS_LEBETHRON.get());
            tag(BlockTags.WOODEN_STAIRS).addTag(TolkienTags.blocks.WOODEN_STAIRS);
            tag(BlockTags.STAIRS).addTag(TolkienTags.blocks.WOODEN_STAIRS);
        tag(TolkienTags.blocks.PRESSURE_PLATES).add(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.PRESSURE_PLATES).addTag(TolkienTags.blocks.PRESSURE_PLATES);
        tag(TolkienTags.blocks.WOODEN_PRESSURE_PLATES).add(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(), TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(), TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(), TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(), TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get());
            tag(BlockTags.WOODEN_PRESSURE_PLATES).addTag(TolkienTags.blocks.WOODEN_PRESSURE_PLATES);
            tag(BlockTags.PRESSURE_PLATES).addTags(TolkienTags.blocks.WOODEN_PRESSURE_PLATES, TolkienTags.blocks.PRESSURE_PLATES);
        tag(TolkienTags.blocks.WOODEN_SLABS).add(TolkienBlocks.SLAB_MALLORN.get(), TolkienBlocks.SLAB_MIRKWOOD.get(), TolkienBlocks.SLAB_CULUMALDA.get(), TolkienBlocks.SLAB_LEBETHRON.get());
            tag(BlockTags.WOODEN_SLABS).addTag(TolkienTags.blocks.WOODEN_SLABS);
            tag(BlockTags.SLABS).addTag(TolkienTags.blocks.WOODEN_SLABS);
        tag(TolkienTags.blocks.LOGS).add(TolkienBlocks.LOG_MALLORN.get(), TolkienBlocks.LOG_MIRKWOOD.get(), TolkienBlocks.LOG_CULUMALDA.get(), TolkienBlocks.LOG_LEBETHRON.get(), TolkienBlocks.LOG_DEADWOOD.get(), TolkienBlocks.STRIPPED_MALLORN_LOG.get(), TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get(), TolkienBlocks.STRIPPED_CULUMALDA_LOG.get(), TolkienBlocks.STRIPPED_LEBETHRON_LOG.get(), TolkienBlocks.STRIPPED_MALLORN_WOOD.get(), TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get(), TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get(), TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get(), TolkienBlocks.WOOD_MALLORN.get(), TolkienBlocks.WOOD_MIRKWOOD.get(), TolkienBlocks.WOOD_CULUMALDA.get(), TolkienBlocks.WOOD_LEBETHRON.get());
            tag(BlockTags.LOGS_THAT_BURN).addTag(TolkienTags.blocks.LOGS);
            tag(BlockTags.LOGS).addTag(TolkienTags.blocks.LOGS);
        tag(TolkienTags.blocks.WOODEN_DOORS).add(TolkienBlocks.DOOR_MALLORN.get(), TolkienBlocks.DOOR_MIRKWOOD.get(), TolkienBlocks.DOOR_CULUMALDA.get(), TolkienBlocks.DOOR_LEBETHRON.get());
            tag(BlockTags.WOODEN_DOORS).addTag(TolkienTags.blocks.WOODEN_DOORS);
        tag(TolkienTags.blocks.DOORS).add(TolkienBlocks.DOOR_MITHRIL.get(), TolkienBlocks.DOOR_MORGULIRON.get(), TolkienBlocks.DOOR_DURIN.get());
            tag(BlockTags.DOORS).addTag(TolkienTags.blocks.DOORS);
            tag(BlockTags.DOORS).addTags(TolkienTags.blocks.WOODEN_DOORS, TolkienTags.blocks.DOORS);
        tag(TolkienTags.blocks.TRAPDOORS).add(TolkienBlocks.TRAPDOOR_MITHRIL.get(), TolkienBlocks.TRAPDOOR_MORGULIRON.get());
            tag(BlockTags.TRAPDOORS).addTag(TolkienTags.blocks.TRAPDOORS);
        tag(TolkienTags.blocks.WOODEN_TRAPDOORS).add(TolkienBlocks.TRAPDOOR_MALLORN.get(), TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), TolkienBlocks.TRAPDOOR_CULUMALDA.get(), TolkienBlocks.TRAPDOOR_LEBETHRON.get());
            tag(BlockTags.WOODEN_TRAPDOORS).addTag(TolkienTags.blocks.WOODEN_TRAPDOORS);
            tag(BlockTags.DOORS).addTags(TolkienTags.blocks.WOODEN_TRAPDOORS, TolkienTags.blocks.TRAPDOORS);
        tag(TolkienTags.blocks.MUSHROOM_GROW_BLOCK).add(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get(), TolkienBlocks.MUSHROOM_DECAY_BLOOM.get());
            tag(BlockTags.MUSHROOM_GROW_BLOCK).addTag(TolkienTags.blocks.MUSHROOM_GROW_BLOCK);
        tag(TolkienTags.blocks.FLOWERS).add(TolkienBlocks.FLOWER_ALFIRIN.get(), TolkienBlocks.FLOWER_ATHELAS.get(), TolkienBlocks.FLOWER_MIRKWOOD.get(), TolkienBlocks.FLOWER_NIPHREDIL.get(), TolkienBlocks.FLOWER_SIMBELMYNE.get(), TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get(), TolkienBlocks.FLOWER_SWAMPMILKWEED.get());
            tag(BlockTags.FLOWERS).addTag(TolkienTags.blocks.FLOWERS);
        tag(TolkienTags.blocks.SIGNS).add(TolkienBlocks.PLACARD.get(), TolkienBlocks.MALLORN_SIGN.get(), TolkienBlocks.MIRKWOOD_SIGN.get(), TolkienBlocks.CULUMALDA_SIGN.get(), TolkienBlocks.LEBETHRON_SIGN.get());
            tag(BlockTags.STANDING_SIGNS).addTag(TolkienTags.blocks.SIGNS);
            tag(BlockTags.WALL_SIGNS).addTag(TolkienTags.blocks.SIGNS);
            tag(BlockTags.SIGNS).addTag(TolkienTags.blocks.SIGNS);
        tag(TolkienTags.blocks.SLEEPING_BAGS).add(TolkienBlocks.SLEEPING_BAG_RED.get(), TolkienBlocks.SLEEPING_BAG_BLUE.get(), TolkienBlocks.SLEEPING_BAG_BLACK.get(), TolkienBlocks.SLEEPING_BAG_BROWN.get(), TolkienBlocks.SLEEPING_BAG_CYAN.get(), TolkienBlocks.SLEEPING_BAG_GRAY.get(), TolkienBlocks.SLEEPING_BAG_GREEN.get(), TolkienBlocks.SLEEPING_BAG_LIGHT_BLUE.get(), TolkienBlocks.SLEEPING_BAG_LIGHT_GRAY.get(), TolkienBlocks.SLEEPING_BAG_LIME.get(), TolkienBlocks.SLEEPING_BAG_MAGENTA.get(), TolkienBlocks.SLEEPING_BAG_ORANGE.get(), TolkienBlocks.SLEEPING_BAG_PINK.get(), TolkienBlocks.SLEEPING_BAG_PURPLE.get(), TolkienBlocks.SLEEPING_BAG_WHITE.get(), TolkienBlocks.SLEEPING_BAG_YELLOW.get());
            tag(BlockTags.BEDS).addTag(TolkienTags.blocks.SLEEPING_BAGS);
        tag(TolkienTags.blocks.BARRELS_WOODEN).add(TolkienBlocks.BARREL_MITHRIL.get(), TolkienBlocks.BARREL_MORGULIRON.get());
        tag(TolkienTags.blocks.CROPS).add(TolkienBlocks.PIPEWEED.get());
            tag(BlockTags.CROPS).addTag(TolkienTags.blocks.CROPS);
        tag(TolkienTags.blocks.TORCHES).add(TolkienBlocks.TORCH_MALLORN.get(), TolkienBlocks.TORCH_MIRKWOOD.get(), TolkienBlocks.TORCH_CULUMALDA.get(), TolkienBlocks.TORCH_LEBETHRON.get());
        tag(TolkienTags.blocks.BARS_IRON).add(TolkienBlocks.MITHRIL_BARS.get(), TolkienBlocks.MORGULIRON_BARS.get());
        tag(TolkienTags.blocks.DECAY_GROW_BLOCK).add(Blocks.COBBLESTONE).add(Blocks.STONE).add(Blocks.MYCELIUM).add(Blocks.PODZOL).add(Blocks.CRIMSON_NYLIUM).add(Blocks.WARPED_NYLIUM);
    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Block Tags";
    }
}