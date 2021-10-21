package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

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
        tag(TTMTags.blocks.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN.get(), TTMContent.FENCE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_LEBETHRON.get());
        tag(BlockTags.WOODEN_FENCES).addTag(TTMTags.blocks.FENCES_WOODEN);
        tag(TTMTags.blocks.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN.get(), TTMContent.FENCE_GATE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_GATE_LEBETHRON.get());
        tag(BlockTags.FENCE_GATES).addTag(TTMTags.blocks.FENCE_GATES_WOODEN);
        tag(TTMTags.blocks.SAPLINGS).add(TTMContent.SAPLING_MALLORN.get(),TTMContent.SAPLING_MIRKWOOD.get(), TTMContent.SAPLING_CULUMALDA.get(), TTMContent.SAPLING_LEBETHRON.get());
        tag(BlockTags.SAPLINGS).addTag(TTMTags.blocks.SAPLINGS);
        tag(TTMTags.blocks.LEAVES).add(TTMContent.LEAVES_MALLORN.get(), TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.LEAVES_CULUMALDA.get(), TTMContent.LEAVES_LEBETHRON.get());
        tag(BlockTags.LEAVES).addTag(TTMTags.blocks.LEAVES);
        tag(TTMTags.blocks.PLANKS).add(TTMContent.PLANKS_MALLORN.get(), TTMContent.PLANKS_MIRKWOOD.get(), TTMContent.PLANKS_CULUMALDA.get(), TTMContent.PLANKS_LEBETHRON.get());
        tag(BlockTags.PLANKS).addTag(TTMTags.blocks.PLANKS);
        tag(TTMTags.blocks.TORCHES).add(TTMContent.TORCH_MALLORN.get(), TTMContent.TORCH_MIRKWOOD.get(), TTMContent.TORCH_CULUMALDA.get(), TTMContent.TORCH_LEBETHRON.get());
        tag(TTMTags.blocks.STAIRS).add(TTMContent.STAIRS_MALLORN.get(), TTMContent.STAIRS_MIRKWOOD.get(), TTMContent.STAIRS_CULUMALDA.get(), TTMContent.STAIRS_LEBETHRON.get());
        tag(BlockTags.STAIRS).addTag(TTMTags.blocks.STAIRS);
        tag(TTMTags.blocks.SLABS).add(TTMContent.SLAB_MALLORN.get(), TTMContent.SLAB_MIRKWOOD.get(), TTMContent.SLAB_CULUMALDA.get(), TTMContent.SLAB_LEBETHRON.get());
        tag(TTMTags.blocks.LOGS).add(TTMContent.LOG_MALLORN.get(), TTMContent.LOG_MIRKWOOD.get(), TTMContent.LOG_CULUMALDA.get(), TTMContent.LOG_LEBETHRON.get(), TTMContent.LOG_DEADWOOD.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(TTMTags.blocks.LOGS);
        tag(TTMTags.blocks.DOORS_WOODEN).add(TTMContent.DOOR_MALLORN.get(), TTMContent.DOOR_MIRKWOOD.get(), TTMContent.DOOR_CULUMALDA.get(), TTMContent.DOOR_LEBETHRON.get());
        tag(BlockTags.WOODEN_DOORS).addTag(TTMTags.blocks.DOORS_WOODEN);
        tag(TTMTags.blocks.DOORS_IRON).add(TTMContent.DOOR_MITHRIL.get(), TTMContent.DOOR_MORGULIRON.get());
        tag(BlockTags.DOORS).addTag(TTMTags.blocks.DOORS_IRON);
        tag(TTMTags.blocks.BARS_IRON).add(TTMContent.MITHRIL_BARS.get(), TTMContent.MORGULIRON_BARS.get());
        tag(TTMTags.blocks.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY.get(), TTMContent.MUSHROOM_DECAY_BLOOM.get());
        tag(TTMTags.blocks.FLOWERS).add(TTMContent.FLOWER_ALFIRIN.get(), TTMContent.FLOWER_ATHELAS.get(), TTMContent.FLOWER_MIRKWOOD.get(), TTMContent.FLOWER_NIPHREDIL.get(), TTMContent.FLOWER_SIMBELMYNE.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), TTMContent.FLOWER_SWAMPMILKWEED.get());
        tag(BlockTags.FLOWERS).addTag(TTMTags.blocks.FLOWERS);
        tag(TTMTags.blocks.DECAY_GROW_BLOCK).add(Blocks.COBBLESTONE).add(Blocks.STONE).add(Blocks.MYCELIUM).add(Blocks.PODZOL).add(Blocks.CRIMSON_NYLIUM).add(Blocks.WARPED_NYLIUM);
    }
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Block Tags";
    }
}