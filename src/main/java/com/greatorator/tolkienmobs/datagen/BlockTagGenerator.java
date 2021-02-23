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
    }
}
