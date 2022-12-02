package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;


/**
 * Created by brandon3055 on 28/2/20.
 */
public class ItemModelGenerator extends ItemModelProvider {
    List<String> sleepingBagTypes = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
    List<String> keyTypes = Arrays.asList("bronze", "silver", "gold", "mithril", "master");
    List<String> woodTypes = Arrays.asList("mallorn", "mirkwood", "culumalda", "lebethron", "deadwood", "fangornoak");
    List<String> metalTypes = Arrays.asList("mithril", "morguliron");
    ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TolkienMobs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Block Items - Metals & Gems
            // Mithril
        blockItem(TolkienBlocks.ORE_MITHRIL.get());
        blockItem(TolkienBlocks.ORE_END_MITHRIL.get());
        blockItem(TolkienBlocks.ORE_NETHER_MITHRIL.get());
        blockItem(TolkienBlocks.ORE_DEEPSLATE_MITHRIL.get());
        blockItem(TolkienBlocks.BLOCK_MITHRIL.get());
        blockItem(TolkienBlocks.RAW_MITHRIL_BLOCK.get());
        simpleItem(TolkienItems.MITHRIL_BARS_ITEM.get());
        simpleItem(TolkienItems.PANE_AMMOLITE_ITEM.get());
        blockItem(TolkienBlocks.WALL_MITHRIL.get(), modLoc("block/wall_mithril_inventory"));
        simpleItem(TolkienItems.DOOR_MITHRIL_ITEM.get());
        blockItem(TolkienBlocks.TRAPDOOR_MITHRIL.get(), modLoc("block/mithril_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), modLoc("block/pressure_plate_mithril"));
        simpleItem(TolkienItems.MITHRIL_FLUID_BUCKET.get());

            // MorgulIron
        blockItem(TolkienBlocks.ORE_MORGULIRON.get());
        blockItem(TolkienBlocks.ORE_END_MORGULIRON.get());
        blockItem(TolkienBlocks.ORE_NETHER_MORGULIRON.get());
        blockItem(TolkienBlocks.ORE_DEEPSLATE_MORGULIRON.get());
        blockItem(TolkienBlocks.BLOCK_MORGULIRON.get());
        blockItem(TolkienBlocks.RAW_MORGULIRON_BLOCK.get());
        simpleItem(TolkienItems.MORGULIRON_BARS_ITEM.get());
        blockItem(TolkienBlocks.WALL_MORGULIRON.get(), modLoc("block/wall_morguliron_inventory"));
        simpleItem(TolkienItems.DOOR_MORGULIRON_ITEM.get());
        blockItem(TolkienBlocks.TRAPDOOR_MORGULIRON.get(), modLoc("block/morguliron_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get(), modLoc("block/pressure_plate_morguliron"));
        simpleItem(TolkienItems.MORGULIRON_FLUID_BUCKET.get());

            // Ammolite
        blockItem(TolkienBlocks.ORE_AMMOLITE.get());
        blockItem(TolkienBlocks.ORE_END_AMMOLITE.get());
        blockItem(TolkienBlocks.ORE_NETHER_AMMOLITE.get());
        blockItem(TolkienBlocks.ORE_DEEPSLATE_AMMOLITE.get());
        blockItem(TolkienBlocks.BLOCK_AMMOLITE.get());
        simpleItem(TolkienItems.DOOR_DURIN_ITEM.get());

        // Block Items - Wood & Foliage
        // Mallorn
        blockItem(TolkienBlocks.LOG_MALLORN.get());
        blockItem(TolkienBlocks.STRIPPED_MALLORN_LOG.get());
        blockItem(TolkienBlocks.WOOD_MALLORN.get());
        blockItem(TolkienBlocks.STRIPPED_MALLORN_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_MALLORN.get());
        blockItem(TolkienBlocks.STAIRS_MALLORN.get());
        blockItem(TolkienBlocks.SLAB_MALLORN.get());
        simpleItem(TolkienItems.DOOR_MALLORN_ITEM.get());
        blockItem(TolkienBlocks.FENCE_GATE_MALLORN.get(), modLoc("block/mallorn_fence_gate"));
        registerFence(TolkienBlocks.FENCE_MALLORN.get(), "planks_mallorn");
        blockItem(TolkienBlocks.TRAPDOOR_MALLORN.get(), modLoc("block/mallorn_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(), modLoc("block/pressure_plate_mallorn"));
        blockItem(TolkienBlocks.TORCH_MALLORN.get(), modLoc("block/torch_mallorn"));
        blockItem(TolkienBlocks.LEAVES_MALLORN.get());
        blockItem(TolkienBlocks.LEAFPILE_MALLORN.get(), modLoc("block/leafpile_mallorn"));
        blockItem(TolkienBlocks.SAPLING_MALLORN.get());
        simpleItem(TolkienItems.MALLORN_SIGN_ITEM.get());
        simpleItem(TolkienItems.MALLORN_BOAT.get());
        blockItem(TolkienBlocks.BARREL_MALLORN.get(), modLoc("block/barrel_mallorn"));

        // Mirkwood
        blockItem(TolkienBlocks.LOG_MIRKWOOD.get());
        blockItem(TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get());
        blockItem(TolkienBlocks.WOOD_MIRKWOOD.get());
        blockItem(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_MIRKWOOD.get());
        blockItem(TolkienBlocks.STAIRS_MIRKWOOD.get());
        blockItem(TolkienBlocks.SLAB_MIRKWOOD.get());
        simpleItem(TolkienItems.DOOR_MIRKWOOD_ITEM.get());
        blockItem(TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), modLoc("block/mirkwood_fence_gate"));
        registerFence(TolkienBlocks.FENCE_MIRKWOOD.get(), "planks_mirkwood");
        blockItem(TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), modLoc("block/mirkwood_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(), modLoc("block/pressure_plate_mirkwood"));
        blockItem(TolkienBlocks.TORCH_MIRKWOOD.get(), modLoc("block/torch_mirkwood"));
        blockItem(TolkienBlocks.LEAVES_MIRKWOOD.get());
        blockItem(TolkienBlocks.LEAFPILE_MIRKWOOD.get(), modLoc("block/leafpile_mirkwood"));
        blockItem(TolkienBlocks.SAPLING_MIRKWOOD.get());
        simpleItem(TolkienItems.MIRKWOOD_SIGN_ITEM.get());
        simpleItem(TolkienItems.MIRKWOOD_BOAT.get());
        blockItem(TolkienBlocks.BARREL_MIRKWOOD.get(), modLoc("block/barrel_mirkwood"));

        // Culumalda
        blockItem(TolkienBlocks.LOG_CULUMALDA.get());
        blockItem(TolkienBlocks.STRIPPED_CULUMALDA_LOG.get());
        blockItem(TolkienBlocks.WOOD_CULUMALDA.get());
        blockItem(TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_CULUMALDA.get());
        blockItem(TolkienBlocks.STAIRS_CULUMALDA.get());
        blockItem(TolkienBlocks.SLAB_CULUMALDA.get());
        simpleItem(TolkienItems.DOOR_CULUMALDA_ITEM.get());
        blockItem(TolkienBlocks.FENCE_GATE_CULUMALDA.get(), modLoc("block/culumalda_fence_gate"));
        registerFence(TolkienBlocks.FENCE_CULUMALDA.get(), "planks_culumalda");
        blockItem(TolkienBlocks.TRAPDOOR_CULUMALDA.get(), modLoc("block/culumalda_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(), modLoc("block/pressure_plate_culumalda"));
        blockItem(TolkienBlocks.TORCH_CULUMALDA.get(), modLoc("block/torch_culumalda"));
        blockItem(TolkienBlocks.LEAVES_CULUMALDA.get());
        blockItem(TolkienBlocks.LEAFPILE_CULUMALDA.get(), modLoc("block/leafpile_culumalda"));
        blockItem(TolkienBlocks.SAPLING_CULUMALDA.get());
        simpleItem(TolkienItems.CULUMALDA_SIGN_ITEM.get());
        simpleItem(TolkienItems.CULUMALDA_BOAT.get());
        blockItem(TolkienBlocks.BARREL_CULUMALDA.get(), modLoc("block/barrel_culumalda"));

        // Lebethron
        blockItem(TolkienBlocks.FENCE_GATE_LEBETHRON.get(), modLoc("block/lebethron_fence_gate"));
        registerFence(TolkienBlocks.FENCE_LEBETHRON.get(), "planks_lebethron");
        blockItem(TolkienBlocks.TRAPDOOR_LEBETHRON.get(), modLoc("block/lebethron_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(), modLoc("block/pressure_plate_lebethron"));
        blockItem(TolkienBlocks.TORCH_LEBETHRON.get(), modLoc("block/torch_lebethron"));
        blockItem(TolkienBlocks.LOG_LEBETHRON.get());
        blockItem(TolkienBlocks.LOG_DEADWOOD.get());
        blockItem(TolkienBlocks.STRIPPED_LEBETHRON_LOG.get());
        blockItem(TolkienBlocks.WOOD_LEBETHRON.get());
        blockItem(TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_LEBETHRON.get());
        blockItem(TolkienBlocks.STAIRS_LEBETHRON.get());
        blockItem(TolkienBlocks.SLAB_LEBETHRON.get());
        simpleItem(TolkienItems.DOOR_LEBETHRON_ITEM.get());
        blockItem(TolkienBlocks.LEAVES_LEBETHRON.get());
        blockItem(TolkienBlocks.LEAFPILE_LEBETHRON.get(), modLoc("block/leafpile_lebethron"));
        blockItem(TolkienBlocks.SAPLING_LEBETHRON.get());
        simpleItem(TolkienItems.LEBETHRON_SIGN_ITEM.get());
        simpleItem(TolkienItems.LEBETHRON_BOAT.get());
        blockItem(TolkienBlocks.BARREL_LEBETHRON.get(), modLoc("block/barrel_lebethron"));

        // Deadwood
        blockItem(TolkienBlocks.LOG_DEADWOOD.get());
        blockItem(TolkienBlocks.STRIPPED_DEADWOOD_LOG.get());
        blockItem(TolkienBlocks.WOOD_DEADWOOD.get());
        blockItem(TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_DEADWOOD.get());
        blockItem(TolkienBlocks.STAIRS_DEADWOOD.get());
        blockItem(TolkienBlocks.SLAB_DEADWOOD.get());
        simpleItem(TolkienItems.DOOR_DEADWOOD_ITEM.get());
        blockItem(TolkienBlocks.FENCE_GATE_DEADWOOD.get(), modLoc("block/deadwood_fence_gate"));
        registerFence(TolkienBlocks.FENCE_DEADWOOD.get(), "planks_deadwood");
        blockItem(TolkienBlocks.TRAPDOOR_DEADWOOD.get(), modLoc("block/deadwood_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get(), modLoc("block/pressure_plate_deadwood"));
        blockItem(TolkienBlocks.TORCH_DEADWOOD.get(), modLoc("block/torch_deadwood"));
        simpleItem(TolkienItems.DEADWOOD_SIGN_ITEM.get());
        simpleItem(TolkienItems.DEADWOOD_BOAT.get());
        blockItem(TolkienBlocks.SAPLING_DEADWOOD.get());
        blockItem(TolkienBlocks.BARREL_DEADWOOD.get(), modLoc("block/barrel_deadwood"));

        // Fangornoak
        blockItem(TolkienBlocks.LOG_FANGORNOAK.get());
        blockItem(TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get());
        blockItem(TolkienBlocks.WOOD_FANGORNOAK.get());
        blockItem(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get());
        blockItem(TolkienBlocks.PLANKS_FANGORNOAK.get());
        blockItem(TolkienBlocks.STAIRS_FANGORNOAK.get());
        blockItem(TolkienBlocks.SLAB_FANGORNOAK.get());
        simpleItem(TolkienItems.DOOR_FANGORNOAK_ITEM.get());
        blockItem(TolkienBlocks.FENCE_GATE_FANGORNOAK.get(), modLoc("block/fangornoak_fence_gate"));
        registerFence(TolkienBlocks.FENCE_FANGORNOAK.get(), "planks_fangornoak");
        blockItem(TolkienBlocks.TRAPDOOR_FANGORNOAK.get(), modLoc("block/fangornoak_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get(), modLoc("block/pressure_plate_fangornoak"));
        blockItem(TolkienBlocks.TORCH_FANGORNOAK.get(), modLoc("block/torch_fangornoak"));
        simpleItem(TolkienItems.FANGORNOAK_SIGN_ITEM.get());
        simpleItem(TolkienItems.FANGORNOAK_BOAT.get());
        blockItem(TolkienBlocks.LEAVES_FANGORNOAK.get());
        blockItem(TolkienBlocks.LEAFPILE_FANGORNOAK.get(), modLoc("block/leafpile_fangornoak"));
        blockItem(TolkienBlocks.SAPLING_FANGORNOAK.get());
        blockItem(TolkienBlocks.BARREL_FANGORNOAK.get(), modLoc("block/barrel_fangornoak"));

        // Block Items - Plants & Flowers
        blockItem(TolkienBlocks.MUSHROOM_DECAY_BLOOM.get());
        blockItem(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get());
        blockItem(TolkienBlocks.BLOCK_BLOOM_DECAY.get(), modLoc("block/block_decay_bloom_inventory"));
        blockItem(TolkienBlocks.BLOCK_DECAY_BLOOM.get(), modLoc("block/block_bloom_decay_inventory"));
        blockItem(TolkienBlocks.FLOWER_SIMBELMYNE.get());
        blockItem(TolkienBlocks.FLOWER_MIRKWOOD.get());
        blockItem(TolkienBlocks.FLOWER_ALFIRIN.get());
        blockItem(TolkienBlocks.FLOWER_ATHELAS.get());
        blockItem(TolkienBlocks.FLOWER_NIPHREDIL.get());
        blockItem(TolkienBlocks.FLOWER_SWAMPMILKWEED.get());
        blockItem(TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get());

        // Block Items - Custom Blocks
        blockItem(TolkienBlocks.BLOCK_HALLOWED.get());
        blockItem(TolkienBlocks.STONE_PATH.get());
        blockItem(TolkienBlocks.TTMFIREPLACE.get(), modLoc("block/fireplace_active"));
        blockItem(TolkienBlocks.PIGGYBANK.get(), modLoc("block/block_piggybank"));
        blockItem(TolkienBlocks.TRINKET_TABLE.get(), modLoc("block/trinkettable"));
        blockItem(TolkienBlocks.BARREL_MITHRIL.get(), modLoc("block/barrel_mithril"));
        blockItem(TolkienBlocks.BARREL_MORGULIRON.get(), modLoc("block/barrel_morguliron"));
        blockItem(TolkienBlocks.BACKPACK.get(), modLoc("block/container_backpack"));
        getBuilder(TolkienItems.ITEM_PLACARD.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/placard_wall_empty")));
        blockItem(TolkienBlocks.CHAMELEON_BLOCK.get());
        blockItem(TolkienBlocks.KEY_STONE_BLOCK.get());
        blockItem(TolkienBlocks.CAMO_GLOWSTONE_BLOCK.get());
        blockItem(TolkienBlocks.CAMO_SMOKER_BLOCK.get());
        blockItem(TolkienBlocks.CAMO_FLUID_BLOCK.get());
        blockItem(TolkienBlocks.CAMO_CHEST_BLOCK.get());
        blockItem(TolkienBlocks.CAMO_SPAWNER_BLOCK.get());
        blockItem(TolkienBlocks.MILESTONE_BLOCK.get());
        blockItem(TolkienBlocks.LOCKABLE_CHEST_BLOCK.get());
        blockItem(TolkienBlocks.LOCKABLE_TREASURE_CHEST_BLOCK.get());
        blockItem(TolkienBlocks.LOCKABLE_DOUBLE_CHEST_BLOCK.get());
        blockItem(TolkienBlocks.LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get());
        blockItem(TolkienBlocks.ROCKPILE.get());
        blockItem(TolkienBlocks.LIGHTNINGBUG_BLOCK.get());

        // Dev Tools
        simpleItem(TolkienItems.ITEM_DEV_TOOL.get());
        simpleItem(TolkienItems.ITEM_DEV_DEBUG_TOOL.get());
        simpleItem(TolkienItems.HOBBIT_RING.get());

        // Sleeping Bags
        for (String color : sleepingBagTypes) {
            simpleMod(itemGenerated, "item/sleeping_bag_"+color);
        }

        // Keys
        for (String keyType : keyTypes) {
            simpleMod(itemGenerated, "item/" + keyType + "_key");
        }


        // Metals & Gems Items
        simpleItem(TolkienItems.RAW_MITHRIL.get());
        simpleItem(TolkienItems.DUST_MITHRIL.get());
        simpleItem(TolkienItems.NUGGET_MITHRIL.get());
        simpleItem(TolkienItems.INGOT_MITHRIL.get());
        simpleItem(TolkienItems.RAW_MORGULIRON.get());
        simpleItem(TolkienItems.DUST_MORGULIRON.get());
        simpleItem(TolkienItems.NUGGET_MORGULIRON.get());
        simpleItem(TolkienItems.INGOT_MORGULIRON.get());
        simpleItem(TolkienItems.GEM_AMMOLITE.get());

        // Mob Drop Items
        simpleItem(TolkienItems.CREBAIN_FEATHER.get());
        simpleItem(TolkienItems.BIRD_FEATHER.get());
        simpleItem(TolkienItems.MUMAKIL_LEATHER.get());
        simpleItem(TolkienItems.MONSTER_FUR.get());
        getBuilder(TolkienItems.BOTTLE_FANCY.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_empty")));
        simpleItem(TolkienItems.GOLEM_STONE.get());
        simpleItem(TolkienItems.GOLEM_STONE_EARTH.get());
        simpleItem(TolkienItems.GOLEM_STONE_AIR.get());
        simpleItem(TolkienItems.GOLEM_STONE_FIRE.get());
        simpleItem(TolkienItems.GOLEM_STONE_WATER.get());
        simpleItem(TolkienItems.GOLEM_STONE_SUMMON.get());

        // region Quest Items
        simpleItem(TolkienItems.ITEM_BERYL.get());
        simpleItem(TolkienItems.ITEM_FORTRESSMAP.get());
        simpleItem(TolkienItems.ITEM_WATCHERHEART.get());
        simpleItem(TolkienItems.ITEM_WATCHERHEART_CRACKED.get());
        simpleItem(TolkienItems.ITEM_KEYSTONE.get());
        simpleItem(TolkienItems.ITEM_DARKSADDLE.get());
        simpleItem(TolkienItems.ITEM_ARTIFACT.get());
        simpleItem(TolkienItems.ITEM_BLANKPAPER.get());
        simpleItem(TolkienItems.ITEM_FANCYARMOR.get());
        simpleItem(TolkienItems.ITEM_FANCYCLOTH.get());
        simpleItem(TolkienItems.ITEM_FANCYHAMMER.get());
        simpleItem(TolkienItems.ITEM_FANCYHELM.get());
        simpleItem(TolkienItems.ITEM_FANCYKEY.get());
        simpleItem(TolkienItems.ITEM_FANCYPICK.get());
        simpleItem(TolkienItems.ITEM_FANCYSHIELD.get());
        simpleItem(TolkienItems.ITEM_FANCYSHIELD2.get());
        simpleItem(TolkienItems.ITEM_FANCYSWORD.get());
        simpleItem(TolkienItems.ITEM_FANCYSWORD2.get());
        simpleItem(TolkienItems.ITEM_LETTER.get());
        simpleItem(TolkienItems.ITEM_SCROLL.get());
        simpleItem(TolkienItems.ITEM_SCROLL2.get());
        simpleItem(TolkienItems.ITEM_SPECIALFLOWER.get());
        simpleItem(TolkienItems.ITEM_STORYBOOK.get());
        simpleItem(TolkienItems.ITEM_STORYBOOK2.get());
        simpleItem(TolkienItems.ITEM_STORYBOOK3.get());
        simpleItem(TolkienItems.ITEM_STORYBOOK4.get());
        simpleItem(TolkienItems.ITEM_WORNARMOR.get());
        simpleItem(TolkienItems.ITEM_WORNHELM.get());
        simpleItem(TolkienItems.ITEM_WORNKEY.get());
        simpleItem(TolkienItems.ITEM_WORNPICK.get());
        simpleItem(TolkienItems.ITEM_WORNSHIELD.get());
        simpleItem(TolkienItems.ITEM_WORNSHIELD2.get());
        simpleItem(TolkienItems.ITEM_WORNSWORD.get());
        simpleItem(TolkienItems.ITEM_WOVENBASKET.get());
        simpleItem(TolkienItems.ITEM_WRITTENPAPER.get());
        simpleItem(TolkienItems.ITEM_PUNGENTHERB.get());
        simpleItem(TolkienItems.ITEM_LOCKPICK.get());
        simpleItem(TolkienItems.ITEM_BROKENSWORD.get());
        simpleItem(TolkienItems.ITEM_REFORGEDSWORD.get());
        simpleItem(TolkienItems.ITEM_MAGIC_CLOTH.get());
        simpleItem(TolkienItems.ITEM_KEYFRAGMENT.get());
        simpleItem(TolkienItems.ITEM_OILYKEY.get());
        simpleItem(TolkienItems.ITEM_MITHRILNUGGET.get());
        simpleItem(TolkienItems.ITEM_REMAINS.get());
        simpleItem(TolkienItems.ITEM_RUNE_STONE.get());
        simpleItem(TolkienItems.HYPE_HORN.get());
        simpleItem(TolkienItems.MORGUL_CRYSTAL.get());

        //region Backpack Upgrades
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get());
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE.get());
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID.get());
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING.get());
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get());
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get());

        //region Coin & Token Items
        simpleItem(TolkienItems.ITEM_COIN_BRONZE.get());
        simpleItem(TolkienItems.ITEM_COIN_SILVER.get());
        simpleItem(TolkienItems.ITEM_COIN_GOLD.get());
        simpleItem(TolkienItems.ITEM_COIN_MITHRIL.get());
        simpleItem(TolkienItems.ITEM_DARKSIGIL.get());
        simpleItem(TolkienItems.ITEM_FACTIONCOIN.get());
        simpleItem(TolkienItems.ITEM_FACTIONTOKEN.get());
        simpleItem(TolkienItems.ITEM_CAVECOMPLETE.get());
        simpleItem(TolkienItems.ITEM_WATCHERCOMPLETE.get());
        simpleItem(TolkienItems.ITEM_TOKEN_EASTERN_ALLIANCE.get());
        simpleItem(TolkienItems.ITEM_TOKEN_WESTERN_ALLIANCE.get());

        //region Potions & Food
        getBuilder(TolkienItems.DRINK_ENT_DRAUGHT.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_ent_draught")));
        getBuilder(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_personal_blacksmith")));
        getBuilder(TolkienItems.DRINK_ELF_FLEETFOOT.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_blessing")));
        getBuilder(TolkienItems.DRINK_ELF_VITALITY.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_vitality")));
        getBuilder(TolkienItems.DRINK_ERU_BLESSING.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_eru_blessing")));
        getBuilder(TolkienItems.MIRUVOR.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_miruvor")));
        getBuilder(TolkienItems.GROG.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_grog")));
        simpleItem(TolkienItems.LEMBAS.get());
        simpleItem(TolkienItems.HONEY_CAKE.get());
        simpleItem(TolkienItems.CRAM.get());
        simpleItem(TolkienItems.MONSTER_FLESH.get());
        simpleItem(TolkienItems.INSECT.get());
        simpleItem(TolkienItems.GOLDEN_INSECT.get());
        simpleItem(TolkienItems.TREE_ACORN.get());
        simpleItem(TolkienItems.GOLDEN_TREE_ACORN.get());
        simpleItem(TolkienItems.FOOD_HONEY.get());
        simpleItem(TolkienItems.PIPEWEED_SEEDS.get());
        simpleItem(TolkienItems.PIPEWEED_ITEM.get());

        //region Music Discs
        simpleItem(TolkienItems.RECORD_RIVENDELL.get());
        simpleItem(TolkienItems.RECORD_LOTHLORIEN.get());
        simpleItem(TolkienItems.RECORD_EREBOR.get());
        simpleItem(TolkienItems.RECORD_WILLOW.get());
        simpleItem(TolkienItems.RECORD_MINASTIRITH.get());
        simpleItem(TolkienItems.RECORD_EDORAS.get());
        simpleItem(TolkienItems.RECORD_WBATTLE.get());
        simpleItem(TolkienItems.RECORD_MURDERFROG.get());
        simpleItem(TolkienItems.RECORD_REDER.get());
        simpleItem(TolkienItems.RECORD_FUMBLE.get());
        simpleItem(TolkienItems.RECORD_BOMBADIL.get());
        simpleItem(TolkienItems.RECORD_HOBBITS.get());

        //region Tools & Armor
        simpleItem(TolkienItems.HELMET_MITHRIL.get());
        simpleItem(TolkienItems.CHESTPLATE_MITHRIL.get());
        simpleItem(TolkienItems.LEGGINGS_MITHRIL.get());
        simpleItem(TolkienItems.BOOTS_MITHRIL.get());
        simpleItem(TolkienItems.MITHRIL_HORSE_ARMOR.get());
        simpleItem(TolkienItems.HELMET_MORGULIRON.get());
        simpleItem(TolkienItems.CHESTPLATE_MORGULIRON.get());
        simpleItem(TolkienItems.LEGGINGS_MORGULIRON.get());
        simpleItem(TolkienItems.BOOTS_MORGULIRON.get());
        simpleItem(TolkienItems.MORGULIRON_HORSE_ARMOR.get());

        handheldItem(TolkienItems.AXE_MITHRIL.get());
        handheldItem(TolkienItems.HOE_MITHRIL.get());
        handheldItem(TolkienItems.PICKAXE_MITHRIL.get());
        handheldItem(TolkienItems.SHOVEL_MITHRIL.get());
        handheldItem(TolkienItems.SWORD_MITHRIL.get());
        handheldItem(TolkienItems.AXE_MORGULIRON.get());
        handheldItem(TolkienItems.HOE_MORGULIRON.get());
        handheldItem(TolkienItems.PICKAXE_MORGULIRON.get());
        handheldItem(TolkienItems.SHOVEL_MORGULIRON.get());
        handheldItem(TolkienItems.SWORD_MORGULIRON.get());
        handheldItem(TolkienItems.SWORD_WITCHKING.get());
        handheldItem(TolkienItems.SWORD_URUK.get());
        simpleItem(TolkienItems.GALADHRIM_ARROW.get());
        simpleItem(TolkienItems.UTUMNO_ARROW.get());

        // region Fluids
//        simpleItem(TolkienItems.MITHRIL_FLUID_BUCKET.get());
//        simpleItem(TolkienItems.MORGULIRON_FLUID_BUCKET.get());


        //region Trinkets
        trinketItem(TolkienItems.TRINKET_AMULET.get(), modLoc("item/trinket_amulet"), modLoc("item/trinket_amulet"));
        trinketItem(TolkienItems.TRINKET_BELT.get(), modLoc("item/trinket_belt"), modLoc("item/trinket_belt"));
        trinketItem(TolkienItems.TRINKET_CHARM.get(), modLoc("item/trinket_charm"), modLoc("item/trinket_charm"));
        trinketItem(TolkienItems.TRINKET_RING.get(), modLoc("item/trinket_ring"), modLoc("item/trinket_ring"));
        trinketItem(TolkienItems.TRINKET_GLOVE.get(), modLoc("item/trinket_glove"), modLoc("item/trinket_glove"));
        trinketItem(TolkienItems.TRINKET_HAT.get(), modLoc("item/trinket_hat"), modLoc("item/trinket_hat"));
        trinketItem(TolkienItems.TRINKET_CLOAK.get(), modLoc("item/trinket_cloak"), modLoc("item/trinket_cloak"));

        //region Spawn Eggs
            // Ambient
//        eggItem(TolkienEntities.EGG_TTMRAT.get());
//        eggItem(TolkienEntities.EGG_TTMSQUIRREL.get());
//        eggItem(TolkienEntities.EGG_TTMFROG.get());
//        eggItem(TolkienEntities.EGG_TTMSWARM.get());
//        eggItem(TolkienEntities.EGG_TTMTHRUSH.get());
//        eggItem(TolkienEntities.EGG_TTMCREBAIN.get());
//
//        // Merchants
        eggItem(TolkienEntities.EGG_TTMHUMAN.get());
        eggItem(TolkienEntities.EGG_TTMDWARF.get());
        eggItem(TolkienEntities.EGG_TTMDESERTDWELLER.get());
        eggItem(TolkienEntities.EGG_TTMELVES.get());
        eggItem(TolkienEntities.EGG_TTMHOBBIT.get());
//
//        // Monster
//        eggItem(TolkienEntities.EGG_TTMGOBLIN.get());
//        eggItem(TolkienEntities.EGG_TTMBARROW.get());
//        eggItem(TolkienEntities.EGG_TTMBRIGAND.get());
//        eggItem(TolkienEntities.EGG_TTMDEEPCLAW.get());
//        eggItem(TolkienEntities.EGG_TTMTREEENT.get());
//        eggItem(TolkienEntities.EGG_TTMDUERGAR.get());
//        eggItem(TolkienEntities.EGG_TTMFELLSPIRIT.get());
//        eggItem(TolkienEntities.EGG_TTMSWAMPHAG.get());
//        eggItem(TolkienEntities.EGG_TTMMIRKWOODSPIDER.get());
//        eggItem(TolkienEntities.EGG_TTMHARADRIM.get());
//        eggItem(TolkienEntities.EGG_TTMTROLL.get());
//        eggItem(TolkienEntities.EGG_TTMWARG.get());
//        eggItem(TolkienEntities.EGG_TTMMORDORORC.get());
//        eggItem(TolkienEntities.EGG_TTMHURON.get());
//        eggItem(TolkienEntities.EGG_TTMOATHBREAKER.get());
//        eggItem(TolkienEntities.EGG_TTMROMIEWALKER.get());
//        eggItem(TolkienEntities.EGG_TTMURUKHAI.get());
//        eggItem(TolkienEntities.EGG_TTMELEMENTALGOLEM.get());
//        eggItem(TolkienEntities.EGG_TTMMINOTAUR.get());
//        eggItem(TolkienEntities.EGG_TTMMIMICCHEST.get());
//
//        // Boss
//        eggItem(TolkienEntities.EGG_TTMGOBLINKING.get());
//        eggItem(TolkienEntities.EGG_TTMMITHRILGOLEM.get());
//        eggItem(TolkienEntities.EGG_TTMMORGULIRONGOLEM.get());
//        eggItem(TolkienEntities.EGG_TTMWITCHKING.get());
//        eggItem(TolkienEntities.EGG_TTMSHELOB.get());
//        eggItem(TolkienEntities.EGG_TTMBALROG.get());
//        eggItem(TolkienEntities.EGG_TTMWATCHER.get());
//        eggItem(TolkienEntities.EGG_TTMGWAHIR.get());
//        eggItem(TolkienEntities.EGG_TTMFELLBEAST.get());
//
//        // Passive
//        eggItem(TolkienEntities.EGG_TTMAUROCH.get());
//        eggItem(TolkienEntities.EGG_TTMMUMAKIL.get());
//        eggItem(TolkienEntities.EGG_TTMGOAT.get());
//
//        // Special
//        eggItem(TolkienEntities.EGG_TTMSHADOWFAX.get());
//        eggItem(TolkienEntities.EGG_TTMGOLLUM.get());
//        eggItem(TolkienEntities.EGG_TTMNAZGUL.get());
//        eggItem(TolkienEntities.EGG_TTMNAZGULSTEED.get());
//        eggItem(TolkienEntities.EGG_TTMGREATEAGLE.get());
    }

    private ItemModelBuilder simpleMod(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", modLoc(name));
    }

    private void registerFence(Block block, String texture)
    {
        String path = block.getRegistryName().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path + "_inventory")));
        singleTexture(path, mcLoc("block/fence_inventory"), "texture", modLoc("block/"+texture));
    }

    private void simpleItem(Item item) {
        simpleItem(item, "item");
    }

    @SuppressWarnings("ConstantConditions")
    private void simpleItem(Item item, String textureFolder) {
        ResourceLocation reg = item.getRegistryName();
        simpleItem(item, new ResourceLocation(reg.getNamespace(), textureFolder + "/" + reg.getPath()));
    }

    @SuppressWarnings("ConstantConditions")
    private void simpleItem(Item item, ResourceLocation texture) {
        ResourceLocation reg = item.getRegistryName();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    private void handheldItem(Item item) {
        handheldItem(item, "item");
    }

    @SuppressWarnings("ConstantConditions")
    private void handheldItem(Item item, String textureFolder) {
        ResourceLocation reg = item.getRegistryName();
        handheldItem(item, new ResourceLocation(reg.getNamespace(), textureFolder + "/" + reg.getPath()));
    }

    @SuppressWarnings("ConstantConditions")
    private void handheldItem(Item item, ResourceLocation texture) {
        ResourceLocation reg = item.getRegistryName();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", texture);
    }

    @SuppressWarnings("ConstantConditions")
    private void trinketItem(Item item, ResourceLocation texture1, ResourceLocation texture2) {
        ResourceLocation reg = item.getRegistryName();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture1)
                .texture("layer1", texture2 + "_gem");
    }

    @SuppressWarnings("ConstantConditions")
    private void eggItem(Item item) {
        ResourceLocation reg = item.getRegistryName();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("minecraft:item/template_spawn_egg"));
    }

    private void blockItem(Block block) {
        if (block == null) return;
        ResourceLocation reg = block.getRegistryName();
        blockItem(block, new ResourceLocation(reg.getNamespace(), "block/" + reg.getPath()));
    }

    private void blockItem(Block block, ResourceLocation blockModel) {
        if (block == null) return;
        ResourceLocation reg = block.getRegistryName();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile(blockModel));
    }

    private void dummyModel(Block block) {
        dummyModel(block.asItem());
    }

    private void dummyModel(Item item) {
        getBuilder(item.getRegistryName().getPath())
                .parent(new ModelFile.UncheckedModelFile("builtin/generated"));
    }

    @Override
    public String getName() {
        return NAME + " - Item Models";
    }
}
