package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienTags;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.CuriosIntegration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
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
        tag(TolkienTags.items.DUSTS_MITHRIL).add(TolkienItems.DUST_MITHRIL.get());
        tag(TolkienTags.items.DUSTS_MORGULIRON).add(TolkienItems.DUST_MORGULIRON.get());
            tag(Tags.Items.DUSTS).addTags(TolkienTags.items.DUSTS_MITHRIL, TolkienTags.items.DUSTS_MORGULIRON);
        tag(TolkienTags.items.NUGGETS_MITHRIL).add(TolkienItems.NUGGET_MITHRIL.get());
        tag(TolkienTags.items.NUGGETS_MORGULIRON).add(TolkienItems.NUGGET_MORGULIRON.get());
            tag(Tags.Items.NUGGETS).addTags(TolkienTags.items.NUGGETS_MITHRIL, TolkienTags.items.NUGGETS_MORGULIRON);
        tag(TolkienTags.items.INGOTS_MITHRIL).add(TolkienItems.INGOT_MITHRIL.get());
        tag(TolkienTags.items.INGOTS_MORGULIRON).add(TolkienItems.INGOT_MORGULIRON.get());
            tag(Tags.Items.INGOTS).addTags(TolkienTags.items.INGOTS_MITHRIL, TolkienTags.items.INGOTS_MORGULIRON);
        tag(TolkienTags.items.STORAGE_BLOCKS_MITHRIL).add(TolkienItems.BLOCK_MITHRIL_ITEM.get());
        tag(TolkienTags.items.STORAGE_BLOCKS_MORGULIRON).add(TolkienItems.BLOCK_MORGULIRON_ITEM.get());
            tag(Tags.Items.STORAGE_BLOCKS).addTags(TolkienTags.items.STORAGE_BLOCKS_MITHRIL, TolkienTags.items.STORAGE_BLOCKS_MORGULIRON);
        tag(TolkienTags.items.ORES_MITHRIL).add(TolkienItems.ORE_MITHRIL_ITEM.get(), TolkienItems.ORE_END_MITHRIL_ITEM.get(), TolkienItems.ORE_NETHER_MITHRIL_ITEM.get());
        tag(TolkienTags.items.ORES_MORGULIRON).add(TolkienItems.ORE_MORGULIRON_ITEM.get(), TolkienItems.ORE_END_MORGULIRON_ITEM.get(), TolkienItems.ORE_NETHER_MORGULIRON_ITEM.get());
        tag(TolkienTags.items.ORES_AMMOLITE).add(TolkienItems.ORE_AMMOLITE_ITEM.get(), TolkienItems.ORE_END_AMMOLITE_ITEM.get(), TolkienItems.ORE_NETHER_AMMOLITE_ITEM.get());
            tag(Tags.Items.ORES).addTags(TolkienTags.items.ORES_MITHRIL, TolkienTags.items.ORES_MORGULIRON, TolkienTags.items.ORES_AMMOLITE);
        tag(TolkienTags.items.GEMS).add(TolkienItems.GEM_AMMOLITE.get());
        tag(TolkienTags.items.MUSHROOMS).add(TolkienItems.MUSHROOM_BLOOM_DECAY_ITEM.get(), TolkienItems.MUSHROOM_DECAY_BLOOM_ITEM.get());
        tag(TolkienTags.items.FENCES_WOODEN).add(TolkienItems.FENCE_MALLORN_ITEM.get(), TolkienItems.FENCE_MIRKWOOD_ITEM.get(), TolkienItems.FENCE_CULUMALDA_ITEM.get(), TolkienItems.FENCE_LEBETHRON_ITEM.get());
        tag(TolkienTags.items.FENCE_GATES_WOODEN).add(TolkienItems.FENCE_GATE_MALLORN_ITEM.get(), TolkienItems.FENCE_GATE_MIRKWOOD_ITEM.get(), TolkienItems.FENCE_GATE_CULUMALDA_ITEM.get(), TolkienItems.FENCE_GATE_LEBETHRON_ITEM.get());
        tag(TolkienTags.items.CROP).add(TolkienItems.PIPEWEED_ITEM.get());
        tag(TolkienTags.items.SEEDS).add(TolkienItems.PIPEWEED_SEEDS.get());
        tag(TolkienTags.items.SAPLINGS).add(TolkienItems.SAPLING_MALLORN_ITEM.get(),TolkienItems.SAPLING_MIRKWOOD_ITEM.get(), TolkienItems.SAPLING_CULUMALDA_ITEM.get(), TolkienItems.SAPLING_LEBETHRON_ITEM.get(), TolkienItems.SAPLING_DEADWOOD_ITEM.get(), TolkienItems.SAPLING_FANGORNOAK_ITEM.get());
            tag(ItemTags.SAPLINGS).addTag(TolkienTags.items.SAPLINGS);
        tag(TolkienTags.items.LEAVES).add(TolkienItems.LEAVES_MALLORN_ITEM.get(), TolkienItems.LEAVES_MIRKWOOD_ITEM.get(), TolkienItems.LEAVES_CULUMALDA_ITEM.get(), TolkienItems.LEAVES_LEBETHRON_ITEM.get(), TolkienItems.LEAVES_FANGORNOAK_ITEM.get());
            tag(ItemTags.LEAVES).addTag(TolkienTags.items.LEAVES);
        tag(TolkienTags.items.PLANKS).add(TolkienItems.PLANKS_MALLORN_ITEM.get(), TolkienItems.PLANKS_MIRKWOOD_ITEM.get(), TolkienItems.PLANKS_CULUMALDA_ITEM.get(), TolkienItems.PLANKS_LEBETHRON_ITEM.get());
            tag(ItemTags.PLANKS).addTag(TolkienTags.items.PLANKS);
        tag(TolkienTags.items.STAIRS).add(TolkienItems.STAIRS_MALLORN_ITEM.get(), TolkienItems.STAIRS_MIRKWOOD_ITEM.get(), TolkienItems.STAIRS_CULUMALDA_ITEM.get(), TolkienItems.STAIRS_LEBETHRON_ITEM.get());
            tag(ItemTags.STAIRS).addTag(TolkienTags.items.STAIRS);
        tag(TolkienTags.items.SLABS).add(TolkienItems.SLAB_MALLORN_ITEM.get(), TolkienItems.SLAB_MIRKWOOD_ITEM.get(), TolkienItems.SLAB_CULUMALDA_ITEM.get(), TolkienItems.SLAB_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_SLABS).addTag(TolkienTags.items.SLABS);
        tag(TolkienTags.items.LOGS).add(TolkienItems.LOG_MALLORN_ITEM.get(), TolkienItems.LOG_MIRKWOOD_ITEM.get(), TolkienItems.LOG_CULUMALDA_ITEM.get(), TolkienItems.LOG_LEBETHRON_ITEM.get(), TolkienItems.STRIPPED_MALLORN_LOG_ITEM.get(), TolkienItems.STRIPPED_MIRKWOOD_LOG_ITEM.get(), TolkienItems.STRIPPED_CULUMALDA_LOG_ITEM.get(), TolkienItems.STRIPPED_LEBETHRON_LOG_ITEM.get(), TolkienItems.STRIPPED_MALLORN_WOOD_ITEM.get(), TolkienItems.STRIPPED_MIRKWOOD_WOOD_ITEM.get(), TolkienItems.STRIPPED_CULUMALDA_WOOD_ITEM.get(), TolkienItems.STRIPPED_LEBETHRON_WOOD_ITEM.get(), TolkienItems.WOOD_MALLORN_ITEM.get(), TolkienItems.WOOD_MIRKWOOD_ITEM.get(), TolkienItems.WOOD_CULUMALDA_ITEM.get(), TolkienItems.WOOD_LEBETHRON_ITEM.get());
            tag(ItemTags.LOGS_THAT_BURN).addTag(TolkienTags.items.LOGS);
            tag(ItemTags.LOGS).addTag(TolkienTags.items.LOGS);
        tag(TolkienTags.items.FLOWERS).add(TolkienItems.FLOWER_ALFIRIN_ITEM.get(), TolkienItems.FLOWER_ATHELAS_ITEM.get(), TolkienItems.FLOWER_MIRKWOOD_ITEM.get(), TolkienItems.FLOWER_NIPHREDIL_ITEM.get(), TolkienItems.FLOWER_SIMBELMYNE_ITEM.get(), TolkienItems.FLOWER_LILLYOFTHEVALLEY_ITEM.get(), TolkienItems.FLOWER_SWAMPMILKWEED_ITEM.get());
            tag(ItemTags.FLOWERS).addTag(TolkienTags.items.FLOWERS);
        tag(TolkienTags.items.DOORS_WOODEN).add(TolkienItems.DOOR_MALLORN_ITEM.get(), TolkienItems.DOOR_MIRKWOOD_ITEM.get(), TolkienItems.DOOR_CULUMALDA_ITEM.get(), TolkienItems.DOOR_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_DOORS).addTag(TolkienTags.items.DOORS_WOODEN);
        tag(TolkienTags.items.DOORS_IRON).add(TolkienItems.DOOR_MITHRIL_ITEM.get(), TolkienItems.DOOR_MORGULIRON_ITEM.get(), TolkienItems.DOOR_DURIN_ITEM.get());
            tag(ItemTags.DOORS).addTags(TolkienTags.items.DOORS_IRON, TolkienTags.items.DOORS_WOODEN);
        tag(TolkienTags.items.BARS_IRON).add(TolkienItems.MITHRIL_BARS_ITEM.get(), TolkienItems.MORGULIRON_BARS_ITEM.get());
        tag(TolkienTags.items.TORCHES).add(TolkienItems.TORCH_MALLORN_ITEM.get(), TolkienItems.TORCH_MIRKWOOD_ITEM.get(), TolkienItems.TORCH_CULUMALDA_ITEM.get(), TolkienItems.TORCH_LEBETHRON_ITEM.get());
        tag(TolkienTags.items.LEATHER).add(TolkienItems.MUMAKIL_LEATHER.get());
        tag(TolkienTags.items.FEATHER).add(TolkienItems.BIRD_FEATHER.get(), TolkienItems.CREBAIN_FEATHER.get());
            tag(Tags.Items.FEATHERS).addTag(TolkienTags.items.FEATHER);
        tag(TolkienTags.items.ARROW).add(TolkienItems.GALADHRIM_ARROW.get(), TolkienItems.UTUMNO_ARROW.get());
            tag(ItemTags.ARROWS).addTag(TolkienTags.items.ARROW);
        tag(TolkienTags.items.MUSIC_DISCS).add(TolkienItems.RECORD_BOMBADIL.get(), TolkienItems.RECORD_EDORAS.get(), TolkienItems.RECORD_EREBOR.get(), TolkienItems.RECORD_FUMBLE.get(), TolkienItems.RECORD_HOBBITS.get(), TolkienItems.RECORD_LOTHLORIEN.get(), TolkienItems.RECORD_EDORAS.get(), TolkienItems.RECORD_MINASTIRITH.get(), TolkienItems.RECORD_MURDERFROG.get(), TolkienItems.RECORD_REDER.get(), TolkienItems.RECORD_RIVENDELL.get(), TolkienItems.RECORD_WBATTLE.get(), TolkienItems.RECORD_WILLOW.get());
            tag(ItemTags.MUSIC_DISCS).addTag(TolkienTags.items.MUSIC_DISCS);
        tag(TolkienTags.items.SLEEPING_BAGS).add(TolkienItems.SLEEPING_BAG_RED_ITEM.get(), TolkienItems.SLEEPING_BAG_BLUE_ITEM.get(), TolkienItems.SLEEPING_BAG_BLACK_ITEM.get(), TolkienItems.SLEEPING_BAG_BROWN_ITEM.get(), TolkienItems.SLEEPING_BAG_CYAN_ITEM.get(), TolkienItems.SLEEPING_BAG_GRAY_ITEM.get(), TolkienItems.SLEEPING_BAG_GREEN_ITEM.get(), TolkienItems.SLEEPING_BAG_LIGHT_BLUE_ITEM.get(), TolkienItems.SLEEPING_BAG_LIGHT_GRAY_ITEM.get(), TolkienItems.SLEEPING_BAG_LIME_ITEM.get(), TolkienItems.SLEEPING_BAG_MAGENTA_ITEM.get(), TolkienItems.SLEEPING_BAG_ORANGE_ITEM.get(), TolkienItems.SLEEPING_BAG_PINK_ITEM.get(), TolkienItems.SLEEPING_BAG_PURPLE_ITEM.get(), TolkienItems.SLEEPING_BAG_WHITE_ITEM.get(), TolkienItems.SLEEPING_BAG_YELLOW_ITEM.get());
            tag(ItemTags.BEDS).addTag(TolkienTags.items.SLEEPING_BAGS);
        tag(TolkienTags.items.SIGNS).add(TolkienItems.ITEM_PLACARD.get(), TolkienItems.MALLORN_SIGN_ITEM.get(), TolkienItems.MIRKWOOD_SIGN_ITEM.get(), TolkienItems.CULUMALDA_SIGN_ITEM.get(), TolkienItems.LEBETHRON_SIGN_ITEM.get());
            tag(ItemTags.SIGNS).addTag(TolkienTags.items.SIGNS);
        tag(TolkienTags.items.TRAPDOORS).add(TolkienItems.TRAPDOOR_MITHRIL_ITEM.get(), TolkienItems.TRAPDOOR_MORGULIRON_ITEM.get());
        tag(TolkienTags.items.WOODEN_TRAPDOORS).add(TolkienItems.TRAPDOOR_MALLORN_ITEM.get(), TolkienItems.TRAPDOOR_MIRKWOOD_ITEM.get(), TolkienItems.TRAPDOOR_CULUMALDA_ITEM.get(), TolkienItems.TRAPDOOR_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_TRAPDOORS).addTag(TolkienTags.items.WOODEN_TRAPDOORS);
            tag(ItemTags.TRAPDOORS).addTags(TolkienTags.items.TRAPDOORS, TolkienTags.items.WOODEN_TRAPDOORS);
        tag(TolkienTags.items.WOODEN_PRESSURE_PLATES).add(TolkienItems.PRESSURE_PLATE_MALLORN_ITEM.get(), TolkienItems.PRESSURE_PLATE_MIRKWOOD_ITEM.get(), TolkienItems.PRESSURE_PLATE_CULUMALDA_ITEM.get(), TolkienItems.PRESSURE_PLATE_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_PRESSURE_PLATES).addTag(TolkienTags.items.WOODEN_PRESSURE_PLATES);
        tag(TolkienTags.items.WOODEN_STAIRS).add(TolkienItems.STAIRS_MALLORN_ITEM.get(), TolkienItems.STAIRS_MIRKWOOD_ITEM.get(), TolkienItems.STAIRS_LEBETHRON_ITEM.get(), TolkienItems.STAIRS_CULUMALDA_ITEM.get());
            tag(ItemTags.WOODEN_STAIRS).addTag(TolkienTags.items.WOODEN_STAIRS);
        tag(TolkienTags.items.WOODEN_SLABS).add(TolkienItems.SLAB_MALLORN_ITEM.get(), TolkienItems.SLAB_MIRKWOOD_ITEM.get(), TolkienItems.SLAB_CULUMALDA_ITEM.get(), TolkienItems.SLAB_LEBETHRON_ITEM.get());
            tag(ItemTags.WOODEN_SLABS).addTag(TolkienTags.items.WOODEN_SLABS);
        tag(TolkienTags.items.BARRELS_WOODEN).add(TolkienItems.BARREL_MITHRIL_ITEM.get(), TolkienItems.BARREL_MORGULIRON_ITEM.get());
        tag(TolkienTags.items.BOTTLE).add(TolkienItems.BOTTLE_FANCY.get());
        tag(TolkienTags.items.COINS).add(TolkienItems.ITEM_COIN_BRONZE.get(), TolkienItems.ITEM_COIN_SILVER.get(), TolkienItems.ITEM_COIN_GOLD.get(), TolkienItems.ITEM_COIN_MITHRIL.get(), TolkienItems.ITEM_FACTIONCOIN.get(), TolkienItems.ITEM_FACTIONTOKEN.get(), TolkienItems.ITEM_DARKSIGIL.get());
        tag(TolkienTags.items.TOKENS).add(TolkienItems.ITEM_FACTIONTOKEN.get(), TolkienItems.ITEM_CAVECOMPLETE.get(), TolkienItems.ITEM_DARKSIGIL.get(), TolkienItems.ITEM_WATCHERCOMPLETE.get(), TolkienItems.ITEM_TOKEN_EASTERN_ALLIANCE.get(), TolkienItems.ITEM_TOKEN_WESTERN_ALLIANCE.get());
        tag(TolkienTags.items.KEYS).add(TolkienItems.BRONZE_KEY.get(), TolkienItems.SILVER_KEY.get(), TolkienItems.GOLD_KEY.get(), TolkienItems.MITHRIL_KEY.get(), TolkienItems.MASTER_KEY.get());
        tag(TolkienTags.items.FOOD).add(TolkienItems.FOOD_HONEY.get(), TolkienItems.LEMBAS.get(), TolkienItems.HONEY_CAKE.get(), TolkienItems.CRAM.get(), TolkienItems.MONSTER_FLESH.get(), TolkienItems.INSECT.get(), TolkienItems.GOLDEN_INSECT.get());
        tag(TolkienTags.items.DRINKS).add(TolkienItems.MIRUVOR.get(), TolkienItems.GROG.get(), TolkienItems.DRINK_ENT_DRAUGHT.get(), TolkienItems.DRINK_PERSONAL_BLACKSMITH.get(), TolkienItems.DRINK_ELF_FLEETFOOT.get(), TolkienItems.DRINK_ELF_VITALITY.get(), TolkienItems.DRINK_ERU_BLESSING.get());
        tag(TolkienTags.items.ACORNS).add(TolkienItems.TREE_ACORN.get(), TolkienItems.GOLDEN_TREE_ACORN.get());
        tag(TolkienTags.items.INSECTS).add(TolkienItems.INSECT.get(), TolkienItems.GOLDEN_INSECT.get());
        tag(TolkienTags.items.FUR).add(TolkienItems.MONSTER_FUR.get());
        tag(TolkienTags.items.SPAWNEGG).add(TolkienEntities.EGG_TTMELVES.get(), TolkienEntities.EGG_TTMDWARF.get(), TolkienEntities.EGG_TTMHUMAN.get(), TolkienEntities.EGG_TTMTHRUSH.get(), TolkienEntities.EGG_TTMFROG.get(), TolkienEntities.EGG_TTMSWARM.get(), TolkienEntities.EGG_TTMSQUIRREL.get(), TolkienEntities.EGG_TTMRAT.get(), TolkienEntities.EGG_TTMAUROCH.get(), TolkienEntities.EGG_TTMMUMAKIL.get(), TolkienEntities.EGG_TTMGOAT.get(), TolkienEntities.EGG_TTMGOBLIN.get(), TolkienEntities.EGG_TTMGOBLINKING.get(), TolkienEntities.EGG_TTMBARROW.get(), TolkienEntities.EGG_TTMBRIGAND.get(), TolkienEntities.EGG_TTMDEEPCLAW.get(), TolkienEntities.EGG_TTMTREEENT.get(), TolkienEntities.EGG_TTMDUERGAR.get(), TolkienEntities.EGG_TTMFELLSPIRIT.get(), TolkienEntities.EGG_TTMSWAMPHAG.get(), TolkienEntities.EGG_TTMMIRKWOODSPIDER.get(), TolkienEntities.EGG_TTMHARADRIM.get(), TolkienEntities.EGG_TTMTROLL.get(), TolkienEntities.EGG_TTMWARG.get(), TolkienEntities.EGG_TTMMORDORORC.get(), TolkienEntities.EGG_TTMHURON.get(), TolkienEntities.EGG_TTMOATHBREAKER.get(), TolkienEntities.EGG_TTMROMIEWALKER.get(), TolkienEntities.EGG_TTMURUKHAI.get(), TolkienEntities.EGG_TTMELEMENTALGOLEM.get(), TolkienEntities.EGG_TTMMINOTAUR.get(), TolkienEntities.EGG_TTMMIMICCHEST.get(), TolkienEntities.EGG_TTMCREBAIN.get(), TolkienEntities.EGG_TTMSHADOWFAX.get(), TolkienEntities.EGG_TTMGOLLUM.get(), TolkienEntities.EGG_TTMNAZGUL.get(), TolkienEntities.EGG_TTMNAZGULSTEED.get(), TolkienEntities.EGG_TTMMITHRILGOLEM.get(), TolkienEntities.EGG_TTMMORGULIRONGOLEM.get(), TolkienEntities.EGG_TTMDESERTDWELLER.get(), TolkienEntities.EGG_TTMWITCHKING.get(), TolkienEntities.EGG_TTMSHELOB.get(), TolkienEntities.EGG_TTMBALROG.get(), TolkienEntities.EGG_TTMWATCHER.get(), TolkienEntities.EGG_TTMGREATEAGLE.get(), TolkienEntities.EGG_TTMGWAHIR.get(), TolkienEntities.EGG_TTMFELLBEAST.get());
        tag(TolkienTags.items.UPGRADES).add(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get());
        tag(TolkienTags.items.TRINKET).add(TolkienItems.TRINKET_AMULET.get(), TolkienItems.TRINKET_BELT.get(), TolkienItems.TRINKET_CHARM.get(), TolkienItems.TRINKET_CLOAK.get(), TolkienItems.TRINKET_GLOVE.get(), TolkienItems.TRINKET_HAT.get(), TolkienItems.TRINKET_RING.get());
        tag(TolkienTags.tagkeys.BLACKLISTED_ITEMS).add(TolkienItems.ITEM_FANCYSHIELD.get());
        tag(TolkienTags.tagkeys.ALLOWED_FLUIDS).add(Items.WATER_BUCKET);

        if (TTMHelper.isCuriosInstalled) {
            CuriosIntegration.generateTags(this::tag);
        }
    }
    @Nonnull
    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Item Tags";
    }
}