package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generatorIn, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(TTMTags.blocks.STORAGE_BLOCKS_MITHRIL).add(TTMContent.BLOCK_MITHRIL.get());
        getOrCreateBuilder(TTMTags.blocks.STORAGE_BLOCKS_MORGULIRON).add(TTMContent.BLOCK_MORGULIRON.get());
        getOrCreateBuilder(Tags.Blocks.STORAGE_BLOCKS).add(TTMContent.BLOCK_MITHRIL.get(), TTMContent.BLOCK_MORGULIRON.get());
        getOrCreateBuilder(TTMTags.blocks.ORES_MITHRIL).add(TTMContent.ORE_MITHRIL.get(), TTMContent.ORE_END_MITHRIL.get(), TTMContent.ORE_NETHER_MITHRIL.get());
        getOrCreateBuilder(TTMTags.blocks.ORES_MORGULIRON).add(TTMContent.ORE_MORGULIRON.get(), TTMContent.ORE_END_MORGULIRON.get(), TTMContent.ORE_NETHER_MORGULIRON.get());
        getOrCreateBuilder(TTMTags.blocks.ORES_AMMOLITE).add(TTMContent.ORE_AMMOLITE.get(), TTMContent.ORE_END_AMMOLITE.get(), TTMContent.ORE_NETHER_AMMOLITE.get());
        getOrCreateBuilder(Tags.Blocks.ORES).add(TTMContent.ORE_MITHRIL.get(), TTMContent.ORE_END_MITHRIL.get(), TTMContent.ORE_NETHER_MITHRIL.get(), TTMContent.ORE_MORGULIRON.get(), TTMContent.ORE_END_MORGULIRON.get(), TTMContent.ORE_NETHER_MORGULIRON.get(), TTMContent.ORE_AMMOLITE.get(), TTMContent.ORE_END_AMMOLITE.get(), TTMContent.ORE_NETHER_AMMOLITE.get());
        getOrCreateBuilder(TTMTags.blocks.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN.get(), TTMContent.FENCE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_LEBETHRON.get());
        getOrCreateBuilder(Tags.Blocks.FENCES_WOODEN).addTag(TTMTags.blocks.FENCES_WOODEN);
        getOrCreateBuilder(TTMTags.blocks.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN.get(), TTMContent.FENCE_GATE_MIRKWOOD.get(), TTMContent.FENCE_GATE_CULUMALDA.get(), TTMContent.FENCE_GATE_LEBETHRON.get());
        getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).addTag(TTMTags.blocks.FENCE_GATES_WOODEN);
        getOrCreateBuilder(TTMTags.blocks.SAPLINGS).add(TTMContent.SAPLING_MALLORN.get(),TTMContent.SAPLING_MIRKWOOD.get(), TTMContent.SAPLING_CULUMALDA.get(), TTMContent.SAPLING_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.LEAVES).add(TTMContent.LEAVES_MALLORN.get(), TTMContent.LEAVES_MIRKWOOD.get(), TTMContent.LEAVES_CULUMALDA.get(), TTMContent.LEAVES_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.PLANKS).add(TTMContent.PLANKS_MALLORN.get(), TTMContent.PLANKS_MIRKWOOD.get(), TTMContent.PLANKS_CULUMALDA.get(), TTMContent.PLANKS_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.STAIRS).add(TTMContent.STAIRS_MALLORN.get(), TTMContent.STAIRS_MIRKWOOD.get(), TTMContent.STAIRS_CULUMALDA.get(), TTMContent.STAIRS_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.SLABS).add(TTMContent.SLAB_MALLORN.get(), TTMContent.SLAB_MIRKWOOD.get(), TTMContent.SLAB_CULUMALDA.get(), TTMContent.SLAB_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.LOGS).add(TTMContent.LOG_MALLORN.get(), TTMContent.LOG_MIRKWOOD.get(), TTMContent.LOG_CULUMALDA.get(), TTMContent.LOG_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.DOORS_WOODEN).add(TTMContent.DOOR_MALLORN.get(), TTMContent.DOOR_MIRKWOOD.get(), TTMContent.DOOR_CULUMALDA.get(), TTMContent.DOOR_LEBETHRON.get());
        getOrCreateBuilder(TTMTags.blocks.DOORS_IRON).add(TTMContent.DOOR_MITHRIL.get(), TTMContent.DOOR_MORGULIRON.get());
        getOrCreateBuilder(TTMTags.blocks.BARS_IRON).add(TTMContent.MITHRIL_BARS.get(), TTMContent.MORGULIRON_BARS.get());
        getOrCreateBuilder(TTMTags.blocks.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY.get(), TTMContent.MUSHROOM_DECAY_BLOOM.get());
        getOrCreateBuilder(TTMTags.blocks.FLOWERS).add(TTMContent.FLOWER_ALFIRIN.get(), TTMContent.FLOWER_ATHELAS.get(), TTMContent.FLOWER_MIRKWOOD.get(), TTMContent.FLOWER_NIPHREDIL.get(), TTMContent.FLOWER_SIMBELMYNE.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), TTMContent.FLOWER_SWAMPMILKWEED.get());
    }
}
