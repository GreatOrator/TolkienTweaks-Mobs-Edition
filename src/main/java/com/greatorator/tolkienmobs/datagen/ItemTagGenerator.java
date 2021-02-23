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
        getOrCreateBuilder(TTMTags.items.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get());
        getOrCreateBuilder(Tags.Items.MUSHROOMS).addTag(TTMTags.items.MUSHROOMS);
        getOrCreateBuilder(TTMTags.items.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN_ITEM.get(), TTMContent.FENCE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_CULUMALDA_ITEM.get(), TTMContent.FENCE_LEBETHRON_ITEM.get());
        getOrCreateBuilder(Tags.Items.FENCES_WOODEN).addTag(TTMTags.items.FENCES_WOODEN);
        getOrCreateBuilder(TTMTags.items.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN_ITEM.get(), TTMContent.FENCE_GATE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_GATE_CULUMALDA_ITEM.get(), TTMContent.FENCE_GATE_LEBETHRON_ITEM.get());
        getOrCreateBuilder(Tags.Items.FENCE_GATES_WOODEN).addTag(TTMTags.items.FENCE_GATES_WOODEN);
        getOrCreateBuilder(TTMTags.items.SAPLINGS).add(TTMContent.SAPLING_MALLORN_ITEM.get(),TTMContent.SAPLING_MIRKWOOD_ITEM.get(), TTMContent.SAPLING_CULUMALDA_ITEM.get(), TTMContent.SAPLING_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.LEAVES).add(TTMContent.LEAVES_MALLORN_ITEM.get(), TTMContent.LEAVES_MIRKWOOD_ITEM.get(), TTMContent.LEAVES_CULUMALDA_ITEM.get(), TTMContent.LEAVES_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.PLANKS).add(TTMContent.PLANKS_MALLORN_ITEM.get(), TTMContent.PLANKS_MIRKWOOD_ITEM.get(), TTMContent.PLANKS_CULUMALDA_ITEM.get(), TTMContent.PLANKS_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.STAIRS).add(TTMContent.STAIRS_MALLORN_ITEM.get(), TTMContent.STAIRS_MIRKWOOD_ITEM.get(), TTMContent.STAIRS_CULUMALDA_ITEM.get(), TTMContent.STAIRS_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.SLABS).add(TTMContent.SLAB_MALLORN_ITEM.get(), TTMContent.SLAB_MIRKWOOD_ITEM.get(), TTMContent.SLAB_CULUMALDA_ITEM.get(), TTMContent.SLAB_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.LOGS).add(TTMContent.LOG_MALLORN_ITEM.get(), TTMContent.LOG_MIRKWOOD_ITEM.get(), TTMContent.LOG_CULUMALDA_ITEM.get(), TTMContent.LOG_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.FLOWERS).add(TTMContent.FLOWER_ALFIRIN_ITEM.get(), TTMContent.FLOWER_ATHELAS_ITEM.get(), TTMContent.FLOWER_MIRKWOOD_ITEM.get(), TTMContent.FLOWER_NIPHREDIL_ITEM.get(), TTMContent.FLOWER_SIMBELMYNE_ITEM.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY_ITEM.get(), TTMContent.FLOWER_SWAMPMILKWEED_ITEM.get());
        getOrCreateBuilder(TTMTags.items.DOORS_WOODEN).add(TTMContent.DOOR_MALLORN_ITEM.get(), TTMContent.DOOR_MIRKWOOD_ITEM.get(), TTMContent.DOOR_CULUMALDA_ITEM.get(), TTMContent.DOOR_LEBETHRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.DOORS_IRON).add(TTMContent.DOOR_MITHRIL_ITEM.get(), TTMContent.DOOR_MORGULIRON_ITEM.get());
        getOrCreateBuilder(TTMTags.items.BARS_IRON).add(TTMContent.MITHRIL_BARS_ITEM.get(), TTMContent.MORGULIRON_BARS_ITEM.get());
        getOrCreateBuilder(TTMTags.items.GEMS).add(TTMContent.GEM_AMMOLITE.get());
        getOrCreateBuilder(Tags.Items.GEMS).addTag(TTMTags.items.GEMS);
        getOrCreateBuilder(TTMTags.items.LEATHER).add(TTMContent.MUMAKIL_LEATHER.get());
        getOrCreateBuilder(Tags.Items.LEATHER).addTag(TTMTags.items.LEATHER);
        getOrCreateBuilder(TTMTags.items.FEATHER).add(TTMContent.BIRD_FEATHER.get(), TTMContent.CREBAIN_FEATHER.get());
        getOrCreateBuilder(Tags.Items.FEATHERS).addTag(TTMTags.items.FEATHER);
        getOrCreateBuilder(TTMTags.items.BOTTLE).add(TTMContent.BOTTLE_FANCY.get());
        getOrCreateBuilder(TTMTags.items.COINS).add(TTMContent.ITEM_COIN_BRONZE.get(), TTMContent.ITEM_COIN_BRONZE.get(), TTMContent.ITEM_COIN_SILVER.get(), TTMContent.ITEM_COIN_GOLD.get(), TTMContent.ITEM_COIN_MITHRIL.get(), TTMContent.ITEM_FACTIONCOIN.get());
        getOrCreateBuilder(TTMTags.items.TOKENS).add(TTMContent.ITEM_FACTIONTOKEN.get(), TTMContent.ITEM_CAVECOMPLETE.get(), TTMContent.ITEM_DARKSIGIL.get(), TTMContent.ITEM_WATCHERCOMPLETE.get());
        getOrCreateBuilder(TTMTags.items.FOOD).add(TTMContent.FOOD_HONEY.get(), TTMContent.LEMBAS.get(), TTMContent.HONEY_CAKE.get(), TTMContent.CRAM.get(), TTMContent.MONSTER_FLESH.get(), TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get(), TTMContent.TREE_ACORN.get(), TTMContent.GOLDEN_TREE_ACORN.get());
        getOrCreateBuilder(TTMTags.items.DRINKS).add(TTMContent.MIRUVOR.get(), TTMContent.GROG.get(), TTMContent.DRINK_ENT_DRAUGHT.get(), TTMContent.DRINK_PERSONAL_BLACKSMITH.get(), TTMContent.DRINK_ELF_FLEETFOOT.get(), TTMContent.DRINK_ELF_VITALITY.get(), TTMContent.DRINK_ERU_BLESSING.get());
        getOrCreateBuilder(TTMTags.items.FUR).add(TTMContent.MONSTER_FUR.get());


        if (ModList.get().isLoaded("curios")) {
            CuriosTTM.generateTags(this::getOrCreateBuilder);
        }
    }
}
