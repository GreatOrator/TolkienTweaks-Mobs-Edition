package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.integration.TTMCurios;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
    super(dataGenerator, blockTagProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TTMTags.items.DUSTS_MITHRIL).add(TTMContent.DUST_MITHRIL.get());
        tag(TTMTags.items.DUSTS_MORGULIRON).add(TTMContent.DUST_MORGULIRON.get());
        tag(Tags.Items.DUSTS).addTags(TTMTags.items.DUSTS_MITHRIL, TTMTags.items.DUSTS_MORGULIRON);
        tag(TTMTags.items.NUGGETS_MITHRIL).add(TTMContent.NUGGET_MITHRIL.get());
        tag(TTMTags.items.NUGGETS_MORGULIRON).add(TTMContent.NUGGET_MORGULIRON.get());
        tag(Tags.Items.NUGGETS).addTags(TTMTags.items.NUGGETS_MITHRIL, TTMTags.items.NUGGETS_MORGULIRON);
        tag(TTMTags.items.INGOTS_MITHRIL).add(TTMContent.INGOT_MITHRIL.get());
        tag(TTMTags.items.INGOTS_MORGULIRON).add(TTMContent.INGOT_MORGULIRON.get());
        tag(Tags.Items.INGOTS).addTags(TTMTags.items.INGOTS_MITHRIL, TTMTags.items.INGOTS_MORGULIRON);
        tag(TTMTags.items.STORAGE_BLOCKS_MITHRIL).add(TTMContent.BLOCK_MITHRIL_ITEM.get());
        tag(TTMTags.items.STORAGE_BLOCKS_MORGULIRON).add(TTMContent.BLOCK_MORGULIRON_ITEM.get());
        tag(Tags.Items.STORAGE_BLOCKS).addTags(TTMTags.items.STORAGE_BLOCKS_MITHRIL, TTMTags.items.STORAGE_BLOCKS_MORGULIRON);
        tag(TTMTags.items.ORES_MITHRIL).add(TTMContent.ORE_MITHRIL_ITEM.get(), TTMContent.ORE_END_MITHRIL_ITEM.get(), TTMContent.ORE_NETHER_MITHRIL_ITEM.get());
        tag(TTMTags.items.ORES_MORGULIRON).add(TTMContent.ORE_MORGULIRON_ITEM.get(), TTMContent.ORE_END_MORGULIRON_ITEM.get(), TTMContent.ORE_NETHER_MORGULIRON_ITEM.get());
        tag(TTMTags.items.ORES_AMMOLITE).add(TTMContent.ORE_AMMOLITE_ITEM.get(), TTMContent.ORE_END_AMMOLITE_ITEM.get(), TTMContent.ORE_NETHER_AMMOLITE_ITEM.get());
        tag(Tags.Items.ORES).addTags(TTMTags.items.ORES_MITHRIL, TTMTags.items.ORES_MORGULIRON, TTMTags.items.ORES_AMMOLITE);
//        tag(TTMTags.items.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get());
//        tag(Tags.Items.MUSHROOMS).addTag(TTMTags.items.MUSHROOMS);
        tag(TTMTags.items.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN_ITEM.get(), TTMContent.FENCE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_CULUMALDA_ITEM.get(), TTMContent.FENCE_LEBETHRON_ITEM.get());
        tag(ItemTags.WOODEN_FENCES).addTag(TTMTags.items.FENCES_WOODEN);
        tag(TTMTags.items.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN_ITEM.get(), TTMContent.FENCE_GATE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_GATE_CULUMALDA_ITEM.get(), TTMContent.FENCE_GATE_LEBETHRON_ITEM.get());
        tag(Tags.Items.FENCE_GATES).addTag(TTMTags.items.FENCE_GATES_WOODEN);
        tag(TTMTags.items.SAPLINGS).add(TTMContent.SAPLING_MALLORN_ITEM.get(),TTMContent.SAPLING_MIRKWOOD_ITEM.get(), TTMContent.SAPLING_CULUMALDA_ITEM.get(), TTMContent.SAPLING_LEBETHRON_ITEM.get(), TTMContent.SAPLING_DEADWOOD_ITEM.get());
        tag(ItemTags.SAPLINGS).addTag(TTMTags.items.SAPLINGS);
        tag(TTMTags.items.LEAVES).add(TTMContent.LEAVES_MALLORN_ITEM.get(), TTMContent.LEAVES_MIRKWOOD_ITEM.get(), TTMContent.LEAVES_CULUMALDA_ITEM.get(), TTMContent.LEAVES_LEBETHRON_ITEM.get());
        tag(ItemTags.LEAVES).addTag(TTMTags.items.LEAVES);
        tag(TTMTags.items.PLANKS).add(TTMContent.PLANKS_MALLORN_ITEM.get(), TTMContent.PLANKS_MIRKWOOD_ITEM.get(), TTMContent.PLANKS_CULUMALDA_ITEM.get(), TTMContent.PLANKS_LEBETHRON_ITEM.get());
        tag(ItemTags.PLANKS).addTag(TTMTags.items.PLANKS);
        tag(TTMTags.items.STAIRS).add(TTMContent.STAIRS_MALLORN_ITEM.get(), TTMContent.STAIRS_MIRKWOOD_ITEM.get(), TTMContent.STAIRS_CULUMALDA_ITEM.get(), TTMContent.STAIRS_LEBETHRON_ITEM.get());
        tag(ItemTags.STAIRS).addTag(TTMTags.items.STAIRS);
        tag(TTMTags.items.SLABS).add(TTMContent.SLAB_MALLORN_ITEM.get(), TTMContent.SLAB_MIRKWOOD_ITEM.get(), TTMContent.SLAB_CULUMALDA_ITEM.get(), TTMContent.SLAB_LEBETHRON_ITEM.get());
        tag(ItemTags.WOODEN_SLABS).addTag(TTMTags.items.SLABS);
        tag(TTMTags.items.LOGS).add(TTMContent.LOG_MALLORN_ITEM.get(), TTMContent.LOG_MIRKWOOD_ITEM.get(), TTMContent.LOG_CULUMALDA_ITEM.get(), TTMContent.LOG_LEBETHRON_ITEM.get());
        tag(ItemTags.LOGS_THAT_BURN).addTag(TTMTags.items.LOGS);
        tag(TTMTags.items.FLOWERS).add(TTMContent.FLOWER_ALFIRIN_ITEM.get(), TTMContent.FLOWER_ATHELAS_ITEM.get(), TTMContent.FLOWER_MIRKWOOD_ITEM.get(), TTMContent.FLOWER_NIPHREDIL_ITEM.get(), TTMContent.FLOWER_SIMBELMYNE_ITEM.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY_ITEM.get(), TTMContent.FLOWER_SWAMPMILKWEED_ITEM.get());
        tag(ItemTags.FLOWERS).addTag(TTMTags.items.FLOWERS);
        tag(TTMTags.items.DOORS_WOODEN).add(TTMContent.DOOR_MALLORN_ITEM.get(), TTMContent.DOOR_MIRKWOOD_ITEM.get(), TTMContent.DOOR_CULUMALDA_ITEM.get(), TTMContent.DOOR_LEBETHRON_ITEM.get());
        tag(ItemTags.WOODEN_DOORS).addTag(TTMTags.items.DOORS_WOODEN);
        tag(TTMTags.items.DOORS_IRON).add(TTMContent.DOOR_MITHRIL_ITEM.get(), TTMContent.DOOR_MORGULIRON_ITEM.get());
        tag(ItemTags.DOORS).addTag(TTMTags.items.DOORS_IRON);
        tag(TTMTags.items.BARS_IRON).add(TTMContent.MITHRIL_BARS_ITEM.get(), TTMContent.MORGULIRON_BARS_ITEM.get());
        tag(TTMTags.items.TORCHES).add(TTMContent.TORCH_MALLORN_ITEM.get(), TTMContent.TORCH_MIRKWOOD_ITEM.get(), TTMContent.TORCH_CULUMALDA_ITEM.get(), TTMContent.TORCH_LEBETHRON_ITEM.get());
        tag(TTMTags.items.GEMS).add(TTMContent.GEM_AMMOLITE.get());
//        tag(Tags.Items.GEMS).addTag(TTMTags.items.GEMS);
        tag(TTMTags.items.LEATHER).add(TTMContent.MUMAKIL_LEATHER.get());
//        tag(Tags.Items.LEATHER).addTag(TTMTags.items.LEATHER);
        tag(TTMTags.items.FEATHER).add(TTMContent.BIRD_FEATHER.get(), TTMContent.CREBAIN_FEATHER.get());
        tag(Tags.Items.FEATHERS).addTag(TTMTags.items.FEATHER);
        tag(TTMTags.items.BOTTLE).add(TTMContent.BOTTLE_FANCY.get());
        tag(TTMTags.items.COINS).add(TTMContent.ITEM_COIN_BRONZE.get(), TTMContent.ITEM_COIN_BRONZE.get(), TTMContent.ITEM_COIN_SILVER.get(), TTMContent.ITEM_COIN_GOLD.get(), TTMContent.ITEM_COIN_MITHRIL.get(), TTMContent.ITEM_FACTIONCOIN.get());
        tag(TTMTags.items.TOKENS).add(TTMContent.ITEM_FACTIONTOKEN.get(), TTMContent.ITEM_CAVECOMPLETE.get(), TTMContent.ITEM_DARKSIGIL.get(), TTMContent.ITEM_WATCHERCOMPLETE.get());
        tag(TTMTags.items.FOOD).add(TTMContent.FOOD_HONEY.get(), TTMContent.LEMBAS.get(), TTMContent.HONEY_CAKE.get(), TTMContent.CRAM.get(), TTMContent.MONSTER_FLESH.get(), TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get());
        tag(TTMTags.items.DRINKS).add(TTMContent.MIRUVOR.get(), TTMContent.GROG.get(), TTMContent.DRINK_ENT_DRAUGHT.get(), TTMContent.DRINK_PERSONAL_BLACKSMITH.get(), TTMContent.DRINK_ELF_FLEETFOOT.get(), TTMContent.DRINK_ELF_VITALITY.get(), TTMContent.DRINK_ERU_BLESSING.get());
        tag(TTMTags.items.ACORNS).add(TTMContent.TREE_ACORN.get(), TTMContent.GOLDEN_TREE_ACORN.get());
        tag(TTMTags.items.INSECTS).add(TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get());
        tag(TTMTags.items.FUR).add(TTMContent.MONSTER_FUR.get());
        tag(TTMTags.items.ARROW).add(TTMContent.GALADHRIM_ARROW.get());
        tag(ItemTags.ARROWS).addTag(TTMTags.items.ARROW);
        tag(TTMTags.items.SPAWNEGG).add(EntityGenerator.EGG_TTMELVES.get(), EntityGenerator.EGG_TTMDWARF.get(), EntityGenerator.EGG_TTMHUMAN.get(), EntityGenerator.EGG_TTMTHRUSH.get(), EntityGenerator.EGG_TTMFROG.get(), EntityGenerator.EGG_TTMSWARM.get(), EntityGenerator.EGG_TTMSQUIRREL.get(), EntityGenerator.EGG_TTMRAT.get(), EntityGenerator.EGG_TTMAUROCH.get(), EntityGenerator.EGG_TTMMUMAKIL.get(), EntityGenerator.EGG_TTMGOAT.get(), EntityGenerator.EGG_TTMGOBLIN.get(), EntityGenerator.EGG_TTMGOBLINKING.get(), EntityGenerator.EGG_TTMBARROW.get(), EntityGenerator.EGG_TTMBRIGAND.get(), EntityGenerator.EGG_TTMDEEPCLAW.get(), EntityGenerator.EGG_TTMTREEENT.get(), EntityGenerator.EGG_TTMDUERGAR.get(), EntityGenerator.EGG_TTMFELLSPIRIT.get(), EntityGenerator.EGG_TTMSWAMPHAG.get());

        if (ModList.get().isLoaded("curios")) {
            TTMCurios.generateTags(this::tag);
        }
    }
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Item Tags";
    }
}