package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.TTMCurios;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
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
        tag(TTMTags.items.GEMS).add(TTMContent.GEM_AMMOLITE.get());
        tag(TTMTags.items.MUSHROOMS).add(TTMContent.MUSHROOM_BLOOM_DECAY_ITEM.get(), TTMContent.MUSHROOM_DECAY_BLOOM_ITEM.get());
        tag(TTMTags.items.FENCES_WOODEN).add(TTMContent.FENCE_MALLORN_ITEM.get(), TTMContent.FENCE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_CULUMALDA_ITEM.get(), TTMContent.FENCE_LEBETHRON_ITEM.get());
        tag(TTMTags.items.FENCE_GATES_WOODEN).add(TTMContent.FENCE_GATE_MALLORN_ITEM.get(), TTMContent.FENCE_GATE_MIRKWOOD_ITEM.get(), TTMContent.FENCE_GATE_CULUMALDA_ITEM.get(), TTMContent.FENCE_GATE_LEBETHRON_ITEM.get());
        tag(TTMTags.items.CROP).add(TTMContent.PIPEWEED_ITEM.get());
        tag(TTMTags.items.SEEDS).add(TTMContent.PIPEWEED_SEEDS.get());
        tag(TTMTags.items.SAPLINGS).add(TTMContent.SAPLING_MALLORN_ITEM.get(),TTMContent.SAPLING_MIRKWOOD_ITEM.get(), TTMContent.SAPLING_CULUMALDA_ITEM.get(), TTMContent.SAPLING_LEBETHRON_ITEM.get(), TTMContent.SAPLING_DEADWOOD_ITEM.get(), TTMContent.SAPLING_FANGORNOAK_ITEM.get());
            tag(ItemTags.SAPLINGS).addTag(TTMTags.items.SAPLINGS);
        tag(TTMTags.items.LEAVES).add(TTMContent.LEAVES_MALLORN_ITEM.get(), TTMContent.LEAVES_MIRKWOOD_ITEM.get(), TTMContent.LEAVES_CULUMALDA_ITEM.get(), TTMContent.LEAVES_LEBETHRON_ITEM.get(), TTMContent.LEAVES_FANGORNOAK_ITEM.get());
            tag(ItemTags.LEAVES).addTag(TTMTags.items.LEAVES);
        tag(TTMTags.items.PLANKS).add(TTMContent.PLANKS_MALLORN_ITEM.get(), TTMContent.PLANKS_MIRKWOOD_ITEM.get(), TTMContent.PLANKS_CULUMALDA_ITEM.get(), TTMContent.PLANKS_LEBETHRON_ITEM.get());
            tag(ItemTags.PLANKS).addTag(TTMTags.items.PLANKS);
        tag(TTMTags.items.STAIRS).add(TTMContent.STAIRS_MALLORN_ITEM.get(), TTMContent.STAIRS_MIRKWOOD_ITEM.get(), TTMContent.STAIRS_CULUMALDA_ITEM.get(), TTMContent.STAIRS_LEBETHRON_ITEM.get());
            tag(ItemTags.STAIRS).addTag(TTMTags.items.STAIRS);
        tag(TTMTags.items.SLABS).add(TTMContent.SLAB_MALLORN_ITEM.get(), TTMContent.SLAB_MIRKWOOD_ITEM.get(), TTMContent.SLAB_CULUMALDA_ITEM.get(), TTMContent.SLAB_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_SLABS).addTag(TTMTags.items.SLABS);
        tag(TTMTags.items.LOGS).add(TTMContent.LOG_MALLORN_ITEM.get(), TTMContent.LOG_MIRKWOOD_ITEM.get(), TTMContent.LOG_CULUMALDA_ITEM.get(), TTMContent.LOG_LEBETHRON_ITEM.get());
            tag(ItemTags.LOGS_THAT_BURN).addTag(TTMTags.items.LOGS);
            tag(ItemTags.LOGS).addTag(TTMTags.items.LOGS);
        tag(TTMTags.items.FLOWERS).add(TTMContent.FLOWER_ALFIRIN_ITEM.get(), TTMContent.FLOWER_ATHELAS_ITEM.get(), TTMContent.FLOWER_MIRKWOOD_ITEM.get(), TTMContent.FLOWER_NIPHREDIL_ITEM.get(), TTMContent.FLOWER_SIMBELMYNE_ITEM.get(), TTMContent.FLOWER_LILLYOFTHEVALLEY_ITEM.get(), TTMContent.FLOWER_SWAMPMILKWEED_ITEM.get());
            tag(ItemTags.FLOWERS).addTag(TTMTags.items.FLOWERS);
        tag(TTMTags.items.DOORS_WOODEN).add(TTMContent.DOOR_MALLORN_ITEM.get(), TTMContent.DOOR_MIRKWOOD_ITEM.get(), TTMContent.DOOR_CULUMALDA_ITEM.get(), TTMContent.DOOR_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_DOORS).addTag(TTMTags.items.DOORS_WOODEN);
        tag(TTMTags.items.DOORS_IRON).add(TTMContent.DOOR_MITHRIL_ITEM.get(), TTMContent.DOOR_MORGULIRON_ITEM.get(), TTMContent.DOOR_DURIN_ITEM.get());
            tag(ItemTags.DOORS).addTags(TTMTags.items.DOORS_IRON, TTMTags.items.DOORS_WOODEN);
        tag(TTMTags.items.BARS_IRON).add(TTMContent.MITHRIL_BARS_ITEM.get(), TTMContent.MORGULIRON_BARS_ITEM.get());
        tag(TTMTags.items.TORCHES).add(TTMContent.TORCH_MALLORN_ITEM.get(), TTMContent.TORCH_MIRKWOOD_ITEM.get(), TTMContent.TORCH_CULUMALDA_ITEM.get(), TTMContent.TORCH_LEBETHRON_ITEM.get());
        tag(TTMTags.items.LEATHER).add(TTMContent.MUMAKIL_LEATHER.get());
        tag(TTMTags.items.FEATHER).add(TTMContent.BIRD_FEATHER.get(), TTMContent.CREBAIN_FEATHER.get());
            tag(Tags.Items.FEATHERS).addTag(TTMTags.items.FEATHER);
        tag(TTMTags.items.ARROW).add(TTMContent.GALADHRIM_ARROW.get());
            tag(ItemTags.ARROWS).addTag(TTMTags.items.ARROW);
        tag(TTMTags.items.MUSIC_DISCS).add(TTMContent.RECORD_BOMBADIL.get(), TTMContent.RECORD_EDORAS.get(), TTMContent.RECORD_EREBOR.get(), TTMContent.RECORD_FUMBLE.get(), TTMContent.RECORD_HOBBITS.get(), TTMContent.RECORD_LOTHLORIEN.get(), TTMContent.RECORD_EDORAS.get(), TTMContent.RECORD_MINASTIRITH.get(), TTMContent.RECORD_MURDERFROG.get(), TTMContent.RECORD_REDER.get(), TTMContent.RECORD_RIVENDELL.get(), TTMContent.RECORD_WBATTLE.get(), TTMContent.RECORD_WILLOW.get());
            tag(ItemTags.MUSIC_DISCS).addTag(TTMTags.items.MUSIC_DISCS);
        tag(TTMTags.items.SLEEPING_BAGS).add(TTMContent.SLEEPING_BAG_RED_ITEM.get(), TTMContent.SLEEPING_BAG_BLUE_ITEM.get(), TTMContent.SLEEPING_BAG_BLACK_ITEM.get(), TTMContent.SLEEPING_BAG_BROWN_ITEM.get(), TTMContent.SLEEPING_BAG_CYAN_ITEM.get(), TTMContent.SLEEPING_BAG_GRAY_ITEM.get(), TTMContent.SLEEPING_BAG_GREEN_ITEM.get(), TTMContent.SLEEPING_BAG_LIGHT_BLUE_ITEM.get(), TTMContent.SLEEPING_BAG_LIGHT_GRAY_ITEM.get(), TTMContent.SLEEPING_BAG_LIME_ITEM.get(), TTMContent.SLEEPING_BAG_MAGENTA_ITEM.get(), TTMContent.SLEEPING_BAG_ORANGE_ITEM.get(), TTMContent.SLEEPING_BAG_PINK_ITEM.get(), TTMContent.SLEEPING_BAG_PURPLE_ITEM.get(), TTMContent.SLEEPING_BAG_WHITE_ITEM.get(), TTMContent.SLEEPING_BAG_YELLOW_ITEM.get());
            tag(ItemTags.BEDS).addTag(TTMTags.items.SLEEPING_BAGS);
        tag(TTMTags.items.SIGNS).add(TTMContent.ITEM_PLACARD.get(), TTMContent.MALLORN_SIGN_ITEM_WOOD_TYPE.get(), TTMContent.MIRKWOOD_SIGN_ITEM_WOOD_TYPE.get(), TTMContent.CULUMALDA_SIGN_ITEM_WOOD_TYPE.get(), TTMContent.LEBETHRON_SIGN_ITEM_WOOD_TYPE.get());
            tag(ItemTags.SIGNS).addTag(TTMTags.items.SIGNS);
        tag(TTMTags.items.TRAPDOORS).add(TTMContent.TRAPDOOR_MITHRIL_ITEM.get(), TTMContent.TRAPDOOR_MORGULIRON_ITEM.get());
        tag(TTMTags.items.WOODEN_TRAPDOORS).add(TTMContent.TRAPDOOR_MALLORN_ITEM.get(), TTMContent.TRAPDOOR_MIRKWOOD_ITEM.get(), TTMContent.TRAPDOOR_CULUMALDA_ITEM.get(), TTMContent.TRAPDOOR_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_TRAPDOORS).addTag(TTMTags.items.WOODEN_TRAPDOORS);
            tag(ItemTags.TRAPDOORS).addTags(TTMTags.items.TRAPDOORS, TTMTags.items.WOODEN_TRAPDOORS);
        tag(TTMTags.items.WOODEN_PRESSURE_PLATES).add(TTMContent.PRESSURE_PLATE_MALLORN_ITEM.get(), TTMContent.PRESSURE_PLATE_MIRKWOOD_ITEM.get(), TTMContent.PRESSURE_PLATE_CULUMALDA_ITEM.get(), TTMContent.PRESSURE_PLATE_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_PRESSURE_PLATES).addTag(TTMTags.items.WOODEN_PRESSURE_PLATES);
        tag(TTMTags.items.WOODEN_STAIRS).add(TTMContent.STAIRS_MALLORN_ITEM.get(), TTMContent.STAIRS_MIRKWOOD_ITEM.get(), TTMContent.STAIRS_LEBETHRON_ITEM.get(), TTMContent.STAIRS_CULUMALDA_ITEM.get());
            tag(ItemTags.WOODEN_STAIRS).addTag(TTMTags.items.WOODEN_STAIRS);
        tag(TTMTags.items.WOODEN_SLABS).add(TTMContent.SLAB_MALLORN_ITEM.get(), TTMContent.SLAB_MIRKWOOD_ITEM.get(), TTMContent.SLAB_CULUMALDA_ITEM.get(), TTMContent.SLAB_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_SLABS).addTag(TTMTags.items.WOODEN_SLABS);
        tag(TTMTags.items.BARRELS_WOODEN).add(TTMContent.BARREL_MITHRIL_ITEM.get(), TTMContent.BARREL_MORGULIRON_ITEM.get());
        tag(TTMTags.items.BOTTLE).add(TTMContent.BOTTLE_FANCY.get());
        tag(TTMTags.items.COINS).add(TTMContent.ITEM_COIN_BRONZE.get(), TTMContent.ITEM_COIN_SILVER.get(), TTMContent.ITEM_COIN_GOLD.get(), TTMContent.ITEM_COIN_MITHRIL.get(), TTMContent.ITEM_FACTIONCOIN.get(), TTMContent.ITEM_FACTIONTOKEN.get(), TTMContent.ITEM_DARKSIGIL.get());
        tag(TTMTags.items.TOKENS).add(TTMContent.ITEM_FACTIONTOKEN.get(), TTMContent.ITEM_CAVECOMPLETE.get(), TTMContent.ITEM_DARKSIGIL.get(), TTMContent.ITEM_WATCHERCOMPLETE.get(), TTMContent.ITEM_TOKEN_EASTERN_ALLIANCE.get(), TTMContent.ITEM_TOKEN_WESTERN_ALLIANCE.get());
        tag(TTMTags.items.KEYS).add(TTMContent.BRONZE_KEY.get(), TTMContent.SILVER_KEY.get(), TTMContent.GOLD_KEY.get(), TTMContent.MITHRIL_KEY.get(), TTMContent.MASTER_KEY.get());
        tag(TTMTags.items.FOOD).add(TTMContent.FOOD_HONEY.get(), TTMContent.LEMBAS.get(), TTMContent.HONEY_CAKE.get(), TTMContent.CRAM.get(), TTMContent.MONSTER_FLESH.get(), TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get());
        tag(TTMTags.items.DRINKS).add(TTMContent.MIRUVOR.get(), TTMContent.GROG.get(), TTMContent.DRINK_ENT_DRAUGHT.get(), TTMContent.DRINK_PERSONAL_BLACKSMITH.get(), TTMContent.DRINK_ELF_FLEETFOOT.get(), TTMContent.DRINK_ELF_VITALITY.get(), TTMContent.DRINK_ERU_BLESSING.get());
        tag(TTMTags.items.ACORNS).add(TTMContent.TREE_ACORN.get(), TTMContent.GOLDEN_TREE_ACORN.get());
        tag(TTMTags.items.INSECTS).add(TTMContent.INSECT.get(), TTMContent.GOLDEN_INSECT.get());
        tag(TTMTags.items.FUR).add(TTMContent.MONSTER_FUR.get());
        tag(TTMTags.items.SPAWNEGG).add(EntityGenerator.EGG_TTMELVES.get(), EntityGenerator.EGG_TTMDWARF.get(), EntityGenerator.EGG_TTMHUMAN.get(), EntityGenerator.EGG_TTMTHRUSH.get(), EntityGenerator.EGG_TTMFROG.get(), EntityGenerator.EGG_TTMSWARM.get(), EntityGenerator.EGG_TTMSQUIRREL.get(), EntityGenerator.EGG_TTMRAT.get(), EntityGenerator.EGG_TTMAUROCH.get(), EntityGenerator.EGG_TTMMUMAKIL.get(), EntityGenerator.EGG_TTMGOAT.get(), EntityGenerator.EGG_TTMGOBLIN.get(), EntityGenerator.EGG_TTMGOBLINKING.get(), EntityGenerator.EGG_TTMBARROW.get(), EntityGenerator.EGG_TTMBRIGAND.get(), EntityGenerator.EGG_TTMDEEPCLAW.get(), EntityGenerator.EGG_TTMTREEENT.get(), EntityGenerator.EGG_TTMDUERGAR.get(), EntityGenerator.EGG_TTMFELLSPIRIT.get(), EntityGenerator.EGG_TTMSWAMPHAG.get(), EntityGenerator.EGG_TTMMIRKWOODSPIDER.get(), EntityGenerator.EGG_TTMHARADRIM.get(), EntityGenerator.EGG_TTMTROLL.get(), EntityGenerator.EGG_TTMWARG.get(), EntityGenerator.EGG_TTMMORDORORC.get(), EntityGenerator.EGG_TTMHURON.get(), EntityGenerator.EGG_TTMOATHBREAKER.get(), EntityGenerator.EGG_TTMROMIEWALKER.get(), EntityGenerator.EGG_TTMURUKHAI.get(), EntityGenerator.EGG_TTMELEMENTALGOLEM.get(), EntityGenerator.EGG_TTMMINOTAUR.get(), EntityGenerator.EGG_TTMMIMICCHEST.get(), EntityGenerator.EGG_TTMCREBAIN.get(), EntityGenerator.EGG_TTMSHADOWFAX.get(), EntityGenerator.EGG_TTMGOLLUM.get(), EntityGenerator.EGG_TTMNAZGUL.get(), EntityGenerator.EGG_TTMNAZGULSTEED.get(), EntityGenerator.EGG_TTMMITHRILGOLEM.get(), EntityGenerator.EGG_TTMMORGULIRONGOLEM.get(), EntityGenerator.EGG_TTMDESERTDWELLER.get(), EntityGenerator.EGG_TTMWITCHKING.get(), EntityGenerator.EGG_TTMSHELOB.get(), EntityGenerator.EGG_TTMBALROG.get(), EntityGenerator.EGG_TTMWATCHER.get());
        tag(TTMTags.items.UPGRADES).add(TTMContent.ITEM_BACKPACK_UPGRADE_SIZE.get(), TTMContent.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), TTMContent.ITEM_BACKPACK_UPGRADE_FLUID.get(), TTMContent.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), TTMContent.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get());
        tag(TTMTags.tagkeys.BLACKLISTED_ITEMS).add(TTMContent.ITEM_FANCYSHIELD.get());
        tag(TTMTags.tagkeys.ALLOWED_FLUIDS).add(Items.WATER_BUCKET);

        if (TTMHelper.isCuriosInstalled) {
            TTMCurios.generateTags(this::tag);
        }
    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Item Tags";
    }
}