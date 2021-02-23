package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.integration.CuriosTTM;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
    super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(TTMTags.items.DUSTS_MITHRIL).add(TTMContent.DUST_MITHRIL.get());
        getOrCreateBuilder(TTMTags.items.DUSTS_MORGULIRON).add(TTMContent.DUST_MORGULIRON.get());
        getOrCreateBuilder(Tags.Items.DUSTS).addTags(TTMTags.items.DUSTS_MITHRIL, TTMTags.items.DUSTS_MORGULIRON);
        getOrCreateBuilder(TTMTags.items.NUGGETS_MITHRIL).add(TTMContent.NUGGET_MITHRIL.get());
        getOrCreateBuilder(TTMTags.items.NUGGETS_MORGULIRON).add(TTMContent.NUGGET_MORGULIRON.get());
        getOrCreateBuilder(Tags.Items.NUGGETS).addTags(TTMTags.items.NUGGETS_MITHRIL, TTMTags.items.NUGGETS_MORGULIRON);
        getOrCreateBuilder(TTMTags.items.INGOTS_MITHRIL).add(TTMContent.INGOT_MITHRIL.get());
        getOrCreateBuilder(TTMTags.items.INGOTS_MORGULIRON).add(TTMContent.INGOT_MORGULIRON.get());
        getOrCreateBuilder(Tags.Items.INGOTS).addTags(TTMTags.items.INGOTS_MITHRIL, TTMTags.items.INGOTS_MORGULIRON);
        getOrCreateBuilder(TTMTags.items.STORAGE_BLOCKS_MITHRIL).add(TTMContent.BLOCK_MITHRIL_ITEM.get());
        getOrCreateBuilder(TTMTags.items.STORAGE_BLOCKS_MORGULIRON).add(TTMContent.BLOCK_MORGULIRON_ITEM.get());
        getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).addTags(TTMTags.items.STORAGE_BLOCKS_MITHRIL, TTMTags.items.STORAGE_BLOCKS_MORGULIRON);
        getOrCreateBuilder(TTMTags.items.ORES_MITHRIL).add(TTMContent.ORE_MITHRIL_ITEM.get(), TTMContent.ORE_END_MITHRIL_ITEM.get(), TTMContent.ORE_NETHER_MITHRIL_ITEM.get());
        getOrCreateBuilder(TTMTags.items.ORES_MORGULIRON).add(TTMContent.ORE_MORGULIRON_ITEM.get(), TTMContent.ORE_END_MORGULIRON_ITEM.get(), TTMContent.ORE_NETHER_MORGULIRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.ORES_AMMOLITE).add(TTMContent.ORE_AMMOLITE_ITEM.get(), TTMContent.ORE_END_AMMOLITE_ITEM.get(), TTMContent.ORE_NETHER_AMMOLITE_ITEM.get());
        getOrCreateBuilder(Tags.Items.ORES).addTags(TTMTags.items.ORES_MITHRIL, TTMTags.items.ORES_MORGULIRON, TTMTags.items.ORES_AMMOLITE);
        getOrCreateBuilder(TTMTags.items.GEMS).add(TTMContent.GEM_AMMOLITE.get());
        getOrCreateBuilder(Tags.Items.GEMS).addTag(TTMTags.items.GEMS);
        getOrCreateBuilder(TTMTags.items.LEATHER).add(TTMContent.MUMAKIL_LEATHER.get());
        getOrCreateBuilder(Tags.Items.LEATHER).addTag(TTMTags.items.LEATHER);
        getOrCreateBuilder(TTMTags.items.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get());
        getOrCreateBuilder(Tags.Items.MUSHROOMS).addTag(TTMTags.items.MUSHROOMS);
        getOrCreateBuilder(TTMTags.items.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN_ITEM.get(), TTMContent.FENCE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_GATE_CULUMALDA_ITEM.get(), TTMContent.FENCE_LEBETHRON_ITEM.get());
        getOrCreateBuilder(Tags.Items.FENCES_WOODEN).addTag(TTMTags.items.FENCES_WOODEN);
        getOrCreateBuilder(TTMTags.items.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN_ITEM.get(), TTMContent.FENCE_GATE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_GATE_CULUMALDA_ITEM.get(), TTMContent.FENCE_GATE_LEBETHRON_ITEM.get());
        getOrCreateBuilder(Tags.Items.FENCE_GATES_WOODEN).addTag(TTMTags.items.FENCE_GATES_WOODEN);
        getOrCreateBuilder(TTMTags.items.SAPLINGS).add(TTMContent.SAPLING_MALLORN_ITEM.get(),TTMContent.SAPLING_MIRKWOOD_ITEM.get(), TTMContent.SAPLING_CULUMALDA_ITEM.get(), TTMContent.SAPLING_LEBETHRON_ITEM.get());


        if (ModList.get().isLoaded("curios")) {
            CuriosTTM.generateTags(this::getOrCreateBuilder);
        }
    }
}
