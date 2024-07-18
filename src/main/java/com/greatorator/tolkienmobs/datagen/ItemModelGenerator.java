package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienEntities;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
        super(generator.getPackOutput(), TolkienMobs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Block Items - Metals & Gems
            // Mithril
        blockItem(TolkienBlocks.ORE_MITHRIL);
        blockItem(TolkienBlocks.ORE_END_MITHRIL);
        blockItem(TolkienBlocks.ORE_NETHER_MITHRIL);
        blockItem(TolkienBlocks.ORE_DEEPSLATE_MITHRIL);
        blockItem(TolkienBlocks.BLOCK_MITHRIL);
        blockItem(TolkienBlocks.RAW_MITHRIL_BLOCK);
        simpleItem(TolkienItems.MITHRIL_BARS_ITEM);
        simpleItem(TolkienItems.PANE_AMMOLITE_ITEM);
        blockItem(TolkienBlocks.WALL_MITHRIL, modLoc("block/wall_mithril_inventory"));
        simpleItem(TolkienItems.DOOR_MITHRIL_ITEM);
        blockItem(TolkienBlocks.TRAPDOOR_MITHRIL, modLoc("block/mithril_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MITHRIL, modLoc("block/pressure_plate_mithril"));
        simpleItem(TolkienItems.MITHRIL_FLUID_BUCKET);

            // MorgulIron
        blockItem(TolkienBlocks.ORE_MORGULIRON);
        blockItem(TolkienBlocks.ORE_END_MORGULIRON);
        blockItem(TolkienBlocks.ORE_NETHER_MORGULIRON);
        blockItem(TolkienBlocks.ORE_DEEPSLATE_MORGULIRON);
        blockItem(TolkienBlocks.BLOCK_MORGULIRON);
        blockItem(TolkienBlocks.RAW_MORGULIRON_BLOCK);
        simpleItem(TolkienItems.MORGULIRON_BARS_ITEM);
        blockItem(TolkienBlocks.WALL_MORGULIRON, modLoc("block/wall_morguliron_inventory"));
        simpleItem(TolkienItems.DOOR_MORGULIRON_ITEM);
        blockItem(TolkienBlocks.TRAPDOOR_MORGULIRON, modLoc("block/morguliron_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MORGULIRON, modLoc("block/pressure_plate_morguliron"));
        simpleItem(TolkienItems.MORGULIRON_FLUID_BUCKET);

            // Ammolite
        blockItem(TolkienBlocks.ORE_AMMOLITE);
        blockItem(TolkienBlocks.ORE_END_AMMOLITE);
        blockItem(TolkienBlocks.ORE_NETHER_AMMOLITE);
        blockItem(TolkienBlocks.ORE_DEEPSLATE_AMMOLITE);
        blockItem(TolkienBlocks.BLOCK_AMMOLITE);
        simpleItem(TolkienItems.DOOR_DURIN_ITEM);

        // Block Items - Wood & Foliage
        // Mallorn
        blockItem(TolkienBlocks.LOG_MALLORN);
        blockItem(TolkienBlocks.STRIPPED_MALLORN_LOG);
        blockItem(TolkienBlocks.WOOD_MALLORN);
        blockItem(TolkienBlocks.STRIPPED_MALLORN_WOOD);
        blockItem(TolkienBlocks.PLANKS_MALLORN);
        blockItem(TolkienBlocks.STAIRS_MALLORN);
        blockItem(TolkienBlocks.SLAB_MALLORN);
        simpleItem(TolkienItems.DOOR_MALLORN_ITEM);
        blockItem(TolkienBlocks.FENCE_GATE_MALLORN, modLoc("block/mallorn_fence_gate"));
        registerFence(TolkienBlocks.FENCE_MALLORN.get(), "planks_mallorn");
        blockItem(TolkienBlocks.TRAPDOOR_MALLORN, modLoc("block/mallorn_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MALLORN, modLoc("block/pressure_plate_mallorn"));
        blockItem(TolkienBlocks.TORCH_MALLORN, modLoc("block/torch_mallorn"));
        blockItem(TolkienBlocks.LEAVES_MALLORN);
        blockItem(TolkienBlocks.LEAFPILE_MALLORN, modLoc("block/leafpile_mallorn"));
        blockItem(TolkienBlocks.SAPLING_MALLORN);
        simpleItem(TolkienItems.MALLORN_SIGN_ITEM);
        simpleItem(TolkienItems.MALLORN_BOAT);
        blockItem(TolkienBlocks.BARREL_MALLORN, modLoc("block/barrel_mallorn"));

        // Mirkwood
        blockItem(TolkienBlocks.LOG_MIRKWOOD);
        blockItem(TolkienBlocks.STRIPPED_MIRKWOOD_LOG);
        blockItem(TolkienBlocks.WOOD_MIRKWOOD);
        blockItem(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD);
        blockItem(TolkienBlocks.PLANKS_MIRKWOOD);
        blockItem(TolkienBlocks.STAIRS_MIRKWOOD);
        blockItem(TolkienBlocks.SLAB_MIRKWOOD);
        simpleItem(TolkienItems.DOOR_MIRKWOOD_ITEM);
        blockItem(TolkienBlocks.FENCE_GATE_MIRKWOOD, modLoc("block/mirkwood_fence_gate"));
        registerFence(TolkienBlocks.FENCE_MIRKWOOD.get(), "planks_mirkwood");
        blockItem(TolkienBlocks.TRAPDOOR_MIRKWOOD, modLoc("block/mirkwood_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD, modLoc("block/pressure_plate_mirkwood"));
        blockItem(TolkienBlocks.TORCH_MIRKWOOD, modLoc("block/torch_mirkwood"));
        blockItem(TolkienBlocks.LEAVES_MIRKWOOD);
        blockItem(TolkienBlocks.LEAFPILE_MIRKWOOD, modLoc("block/leafpile_mirkwood"));
        blockItem(TolkienBlocks.SAPLING_MIRKWOOD);
        simpleItem(TolkienItems.MIRKWOOD_SIGN_ITEM);
        simpleItem(TolkienItems.MIRKWOOD_BOAT);
        blockItem(TolkienBlocks.BARREL_MIRKWOOD, modLoc("block/barrel_mirkwood"));

        // Culumalda
        blockItem(TolkienBlocks.LOG_CULUMALDA);
        blockItem(TolkienBlocks.STRIPPED_CULUMALDA_LOG);
        blockItem(TolkienBlocks.WOOD_CULUMALDA);
        blockItem(TolkienBlocks.STRIPPED_CULUMALDA_WOOD);
        blockItem(TolkienBlocks.PLANKS_CULUMALDA);
        blockItem(TolkienBlocks.STAIRS_CULUMALDA);
        blockItem(TolkienBlocks.SLAB_CULUMALDA);
        simpleItem(TolkienItems.DOOR_CULUMALDA_ITEM);
        blockItem(TolkienBlocks.FENCE_GATE_CULUMALDA, modLoc("block/culumalda_fence_gate"));
        registerFence(TolkienBlocks.FENCE_CULUMALDA.get(), "planks_culumalda");
        blockItem(TolkienBlocks.TRAPDOOR_CULUMALDA, modLoc("block/culumalda_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_CULUMALDA, modLoc("block/pressure_plate_culumalda"));
        blockItem(TolkienBlocks.TORCH_CULUMALDA, modLoc("block/torch_culumalda"));
        blockItem(TolkienBlocks.LEAVES_CULUMALDA);
        blockItem(TolkienBlocks.LEAFPILE_CULUMALDA, modLoc("block/leafpile_culumalda"));
        blockItem(TolkienBlocks.SAPLING_CULUMALDA);
        simpleItem(TolkienItems.CULUMALDA_SIGN_ITEM);
        simpleItem(TolkienItems.CULUMALDA_BOAT);
        blockItem(TolkienBlocks.BARREL_CULUMALDA, modLoc("block/barrel_culumalda"));

        // Lebethron
        blockItem(TolkienBlocks.FENCE_GATE_LEBETHRON, modLoc("block/lebethron_fence_gate"));
        registerFence(TolkienBlocks.FENCE_LEBETHRON.get(), "planks_lebethron");
        blockItem(TolkienBlocks.TRAPDOOR_LEBETHRON, modLoc("block/lebethron_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_LEBETHRON, modLoc("block/pressure_plate_lebethron"));
        blockItem(TolkienBlocks.TORCH_LEBETHRON, modLoc("block/torch_lebethron"));
        blockItem(TolkienBlocks.LOG_LEBETHRON);
        blockItem(TolkienBlocks.LOG_DEADWOOD);
        blockItem(TolkienBlocks.STRIPPED_LEBETHRON_LOG);
        blockItem(TolkienBlocks.WOOD_LEBETHRON);
        blockItem(TolkienBlocks.STRIPPED_LEBETHRON_WOOD);
        blockItem(TolkienBlocks.PLANKS_LEBETHRON);
        blockItem(TolkienBlocks.STAIRS_LEBETHRON);
        blockItem(TolkienBlocks.SLAB_LEBETHRON);
        simpleItem(TolkienItems.DOOR_LEBETHRON_ITEM);
        blockItem(TolkienBlocks.LEAVES_LEBETHRON);
        blockItem(TolkienBlocks.LEAFPILE_LEBETHRON, modLoc("block/leafpile_lebethron"));
        blockItem(TolkienBlocks.SAPLING_LEBETHRON);
        simpleItem(TolkienItems.LEBETHRON_SIGN_ITEM);
        simpleItem(TolkienItems.LEBETHRON_BOAT);
        blockItem(TolkienBlocks.BARREL_LEBETHRON, modLoc("block/barrel_lebethron"));

        // Deadwood
        blockItem(TolkienBlocks.LOG_DEADWOOD);
        blockItem(TolkienBlocks.STRIPPED_DEADWOOD_LOG);
        blockItem(TolkienBlocks.WOOD_DEADWOOD);
        blockItem(TolkienBlocks.STRIPPED_DEADWOOD_WOOD);
        blockItem(TolkienBlocks.PLANKS_DEADWOOD);
        blockItem(TolkienBlocks.STAIRS_DEADWOOD);
        blockItem(TolkienBlocks.SLAB_DEADWOOD);
        simpleItem(TolkienItems.DOOR_DEADWOOD_ITEM);
        blockItem(TolkienBlocks.FENCE_GATE_DEADWOOD, modLoc("block/deadwood_fence_gate"));
        registerFence(TolkienBlocks.FENCE_DEADWOOD.get(), "planks_deadwood");
        blockItem(TolkienBlocks.TRAPDOOR_DEADWOOD, modLoc("block/deadwood_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_DEADWOOD, modLoc("block/pressure_plate_deadwood"));
        blockItem(TolkienBlocks.TORCH_DEADWOOD, modLoc("block/torch_deadwood"));
        simpleItem(TolkienItems.DEADWOOD_SIGN_ITEM);
        simpleItem(TolkienItems.DEADWOOD_BOAT);
        blockItem(TolkienBlocks.SAPLING_DEADWOOD);
        blockItem(TolkienBlocks.BARREL_DEADWOOD, modLoc("block/barrel_deadwood"));

        // Fangornoak
        blockItem(TolkienBlocks.LOG_FANGORNOAK);
        blockItem(TolkienBlocks.STRIPPED_FANGORNOAK_LOG);
        blockItem(TolkienBlocks.WOOD_FANGORNOAK);
        blockItem(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD);
        blockItem(TolkienBlocks.PLANKS_FANGORNOAK);
        blockItem(TolkienBlocks.STAIRS_FANGORNOAK);
        blockItem(TolkienBlocks.SLAB_FANGORNOAK);
        simpleItem(TolkienItems.DOOR_FANGORNOAK_ITEM);
        blockItem(TolkienBlocks.FENCE_GATE_FANGORNOAK, modLoc("block/fangornoak_fence_gate"));
        registerFence(TolkienBlocks.FENCE_FANGORNOAK.get(), "planks_fangornoak");
        blockItem(TolkienBlocks.TRAPDOOR_FANGORNOAK, modLoc("block/fangornoak_trapdoor_bottom"));
        blockItem(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK, modLoc("block/pressure_plate_fangornoak"));
        blockItem(TolkienBlocks.TORCH_FANGORNOAK, modLoc("block/torch_fangornoak"));
        simpleItem(TolkienItems.FANGORNOAK_SIGN_ITEM);
        simpleItem(TolkienItems.FANGORNOAK_BOAT);
        blockItem(TolkienBlocks.LEAVES_FANGORNOAK);
        blockItem(TolkienBlocks.LEAFPILE_FANGORNOAK, modLoc("block/leafpile_fangornoak"));
        blockItem(TolkienBlocks.SAPLING_FANGORNOAK);
        blockItem(TolkienBlocks.BARREL_FANGORNOAK, modLoc("block/barrel_fangornoak"));

        // Block Items - Plants & Flowers
        blockItem(TolkienBlocks.MUSHROOM_DECAY_BLOOM);
        blockItem(TolkienBlocks.MUSHROOM_BLOOM_DECAY);
        blockItem(TolkienBlocks.BLOCK_BLOOM_DECAY, modLoc("block/block_decay_bloom_inventory"));
        blockItem(TolkienBlocks.BLOCK_DECAY_BLOOM, modLoc("block/block_bloom_decay_inventory"));
        blockItem(TolkienBlocks.FLOWER_SIMBELMYNE);
        blockItem(TolkienBlocks.FLOWER_MIRKWOOD);
        blockItem(TolkienBlocks.FLOWER_ALFIRIN);
        blockItem(TolkienBlocks.FLOWER_ATHELAS);
        blockItem(TolkienBlocks.FLOWER_NIPHREDIL);
        blockItem(TolkienBlocks.FLOWER_SWAMPMILKWEED);
        blockItem(TolkienBlocks.FLOWER_LILLYOFTHEVALLEY);

        // Block Items - Custom Blocks
        blockItem(TolkienBlocks.BLOCK_HALLOWED);
        blockItem(TolkienBlocks.STONE_PATH);
        blockItem(TolkienBlocks.TTMFIREPLACE, modLoc("block/fireplace_active"));
        blockItem(TolkienBlocks.PIGGYBANK, modLoc("block/block_piggybank"));
        blockItem(TolkienBlocks.TRINKET_TABLE, modLoc("block/trinkettable"));
        blockItem(TolkienBlocks.BARREL_MITHRIL, modLoc("block/barrel_mithril"));
        blockItem(TolkienBlocks.BARREL_MORGULIRON, modLoc("block/barrel_morguliron"));
        blockItem(TolkienBlocks.BACKPACK, modLoc("block/container_backpack"));
        getBuilder(TolkienItems.ITEM_PLACARD.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/placard_wall_empty")));
        blockItem(TolkienBlocks.CHAMELEON_BLOCK);
        blockItem(TolkienBlocks.KEY_STONE_BLOCK);
        blockItem(TolkienBlocks.CAMO_GLOWSTONE_BLOCK);
        blockItem(TolkienBlocks.CAMO_SMOKER_BLOCK);
        blockItem(TolkienBlocks.CAMO_FLUID_BLOCK);
        blockItem(TolkienBlocks.CAMO_CHEST_BLOCK);
        blockItem(TolkienBlocks.CAMO_SPAWNER_BLOCK);
        blockItem(TolkienBlocks.MILESTONE_BLOCK);
        blockItem(TolkienBlocks.ROCKPILE);
        blockItem(TolkienBlocks.LIGHTNINGBUG_BLOCK);

        // Dev Tools
        simpleItem(TolkienItems.ITEM_DEV_TOOL);
        simpleItem(TolkienItems.ITEM_DEV_DEBUG_TOOL);
        simpleItem(TolkienItems.HOBBIT_RING);

        // Sleeping Bags
        for (String color : sleepingBagTypes) {
            simpleMod(itemGenerated, "item/sleeping_bag_"+color);
        }

        // Keys
        for (String keyType : keyTypes) {
            simpleMod(itemGenerated, "item/" + keyType + "_key");
        }


        // Metals & Gems Items
        simpleItem(TolkienItems.RAW_MITHRIL);
        simpleItem(TolkienItems.DUST_MITHRIL);
        simpleItem(TolkienItems.NUGGET_MITHRIL);
        simpleItem(TolkienItems.INGOT_MITHRIL);
        simpleItem(TolkienItems.RAW_MORGULIRON);
        simpleItem(TolkienItems.DUST_MORGULIRON);
        simpleItem(TolkienItems.NUGGET_MORGULIRON);
        simpleItem(TolkienItems.INGOT_MORGULIRON);
        simpleItem(TolkienItems.GEM_AMMOLITE);

        // Mob Drop Items
        simpleItem(TolkienItems.CREBAIN_FEATHER);
        simpleItem(TolkienItems.BIRD_FEATHER);
        simpleItem(TolkienItems.MUMAKIL_LEATHER);
        simpleItem(TolkienItems.MONSTER_FUR);
        getBuilder(TolkienItems.BOTTLE_FANCY.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_empty")));
        simpleItem(TolkienItems.GOLEM_STONE);
        simpleItem(TolkienItems.GOLEM_STONE_EARTH);
        simpleItem(TolkienItems.GOLEM_STONE_AIR);
        simpleItem(TolkienItems.GOLEM_STONE_FIRE);
        simpleItem(TolkienItems.GOLEM_STONE_WATER);
        simpleItem(TolkienItems.GOLEM_STONE_SUMMON);

        // region Quest Items
        simpleItem(TolkienItems.ITEM_BERYL);
        simpleItem(TolkienItems.ITEM_FORTRESSMAP);
        simpleItem(TolkienItems.ITEM_WATCHERHEART);
        simpleItem(TolkienItems.ITEM_WATCHERHEART_CRACKED);
        simpleItem(TolkienItems.ITEM_KEYSTONE);
        simpleItem(TolkienItems.ITEM_DARKSADDLE);
        simpleItem(TolkienItems.ITEM_ARTIFACT);
        simpleItem(TolkienItems.ITEM_BLANKPAPER);
        simpleItem(TolkienItems.ITEM_FANCYARMOR);
        simpleItem(TolkienItems.ITEM_FANCYCLOTH);
        simpleItem(TolkienItems.ITEM_FANCYHAMMER);
        simpleItem(TolkienItems.ITEM_FANCYHELM);
        simpleItem(TolkienItems.ITEM_FANCYKEY);
        simpleItem(TolkienItems.ITEM_FANCYPICK);
        simpleItem(TolkienItems.ITEM_FANCYSHIELD);
        simpleItem(TolkienItems.ITEM_FANCYSHIELD2);
        simpleItem(TolkienItems.ITEM_FANCYSWORD);
        simpleItem(TolkienItems.ITEM_FANCYSWORD2);
        simpleItem(TolkienItems.ITEM_LETTER);
        simpleItem(TolkienItems.ITEM_SCROLL);
        simpleItem(TolkienItems.ITEM_SCROLL2);
        simpleItem(TolkienItems.ITEM_SPECIALFLOWER);
        simpleItem(TolkienItems.ITEM_STORYBOOK);
        simpleItem(TolkienItems.ITEM_STORYBOOK2);
        simpleItem(TolkienItems.ITEM_STORYBOOK3);
        simpleItem(TolkienItems.ITEM_STORYBOOK4);
        simpleItem(TolkienItems.ITEM_WORNARMOR);
        simpleItem(TolkienItems.ITEM_WORNHELM);
        simpleItem(TolkienItems.ITEM_WORNKEY);
        simpleItem(TolkienItems.ITEM_WORNPICK);
        simpleItem(TolkienItems.ITEM_WORNSHIELD);
        simpleItem(TolkienItems.ITEM_WORNSHIELD2);
        simpleItem(TolkienItems.ITEM_WORNSWORD);
        simpleItem(TolkienItems.ITEM_WOVENBASKET);
        simpleItem(TolkienItems.ITEM_WRITTENPAPER);
        simpleItem(TolkienItems.ITEM_PUNGENTHERB);
        simpleItem(TolkienItems.ITEM_LOCKPICK);
        simpleItem(TolkienItems.ITEM_BROKENSWORD);
        simpleItem(TolkienItems.ITEM_REFORGEDSWORD);
        simpleItem(TolkienItems.ITEM_MAGIC_CLOTH);
        simpleItem(TolkienItems.ITEM_KEYFRAGMENT);
        simpleItem(TolkienItems.ITEM_OILYKEY);
        simpleItem(TolkienItems.ITEM_MITHRILNUGGET);
        simpleItem(TolkienItems.ITEM_REMAINS);
        simpleItem(TolkienItems.ITEM_RUNE_STONE);
        simpleItem(TolkienItems.HYPE_HORN);
        simpleItem(TolkienItems.MORGUL_CRYSTAL);

        //region Backpack Upgrades
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE);
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE);
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID);
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING);
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING);
        simpleItem(TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE);

        //region Coin & Token Items
        simpleItem(TolkienItems.ITEM_COIN_BRONZE);
        simpleItem(TolkienItems.ITEM_COIN_SILVER);
        simpleItem(TolkienItems.ITEM_COIN_GOLD);
        simpleItem(TolkienItems.ITEM_COIN_MITHRIL);
        simpleItem(TolkienItems.ITEM_DARKSIGIL);
        simpleItem(TolkienItems.ITEM_FACTIONCOIN);
        simpleItem(TolkienItems.ITEM_FACTIONTOKEN);
        simpleItem(TolkienItems.ITEM_CAVECOMPLETE);
        simpleItem(TolkienItems.ITEM_WATCHERCOMPLETE);
        simpleItem(TolkienItems.ITEM_TOKEN_EASTERN_ALLIANCE);
        simpleItem(TolkienItems.ITEM_TOKEN_WESTERN_ALLIANCE);

        //region Potions & Food
        getBuilder(TolkienItems.DRINK_ENT_DRAUGHT.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_ent_draught")));
        getBuilder(TolkienItems.DRINK_PERSONAL_BLACKSMITH.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_personal_blacksmith")));
        getBuilder(TolkienItems.DRINK_ELF_FLEETFOOT.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_blessing")));
        getBuilder(TolkienItems.DRINK_ELF_VITALITY.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_vitality")));
        getBuilder(TolkienItems.DRINK_ERU_BLESSING.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_eru_blessing")));
        getBuilder(TolkienItems.MIRUVOR.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_miruvor")));
        getBuilder(TolkienItems.GROG.getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_grog")));
        simpleItem(TolkienItems.LEMBAS);
        simpleItem(TolkienItems.HONEY_CAKE);
        simpleItem(TolkienItems.CRAM);
        simpleItem(TolkienItems.MONSTER_FLESH);
        simpleItem(TolkienItems.INSECT);
        simpleItem(TolkienItems.GOLDEN_INSECT);
        simpleItem(TolkienItems.TREE_ACORN);
        simpleItem(TolkienItems.GOLDEN_TREE_ACORN);
        simpleItem(TolkienItems.FOOD_HONEY);
        simpleItem(TolkienItems.PIPEWEED_SEEDS);
        simpleItem(TolkienItems.PIPEWEED_ITEM);

        //region Music Discs
        simpleItem(TolkienItems.RECORD_RIVENDELL);
        simpleItem(TolkienItems.RECORD_LOTHLORIEN);
        simpleItem(TolkienItems.RECORD_EREBOR);
        simpleItem(TolkienItems.RECORD_WILLOW);
        simpleItem(TolkienItems.RECORD_MINASTIRITH);
        simpleItem(TolkienItems.RECORD_EDORAS);
        simpleItem(TolkienItems.RECORD_WBATTLE);
        simpleItem(TolkienItems.RECORD_MURDERFROG);
        simpleItem(TolkienItems.RECORD_REDER);
        simpleItem(TolkienItems.RECORD_FUMBLE);
        simpleItem(TolkienItems.RECORD_BOMBADIL);
        simpleItem(TolkienItems.RECORD_HOBBITS);

        //region Tools & Armor
        simpleItem(TolkienItems.HELMET_MITHRIL);
        simpleItem(TolkienItems.CHESTPLATE_MITHRIL);
        simpleItem(TolkienItems.LEGGINGS_MITHRIL);
        simpleItem(TolkienItems.BOOTS_MITHRIL);
        simpleItem(TolkienItems.MITHRIL_HORSE_ARMOR);
        simpleItem(TolkienItems.HELMET_MORGULIRON);
        simpleItem(TolkienItems.CHESTPLATE_MORGULIRON);
        simpleItem(TolkienItems.LEGGINGS_MORGULIRON);
        simpleItem(TolkienItems.BOOTS_MORGULIRON);
        simpleItem(TolkienItems.MORGULIRON_HORSE_ARMOR);

        handheldItem(TolkienItems.AXE_MITHRIL);
        handheldItem(TolkienItems.HOE_MITHRIL);
        handheldItem(TolkienItems.PICKAXE_MITHRIL);
        handheldItem(TolkienItems.SHOVEL_MITHRIL);
        handheldItem(TolkienItems.SWORD_MITHRIL);
        handheldItem(TolkienItems.AXE_MORGULIRON);
        handheldItem(TolkienItems.HOE_MORGULIRON);
        handheldItem(TolkienItems.PICKAXE_MORGULIRON);
        handheldItem(TolkienItems.SHOVEL_MORGULIRON);
        handheldItem(TolkienItems.SWORD_MORGULIRON);
        handheldItem(TolkienItems.SWORD_WITCHKING);
        handheldItem(TolkienItems.SWORD_URUK);
        simpleItem(TolkienItems.GALADHRIM_ARROW);
        simpleItem(TolkienItems.UTUMNO_ARROW);

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
        eggItem(TolkienEntities.EGG_TTMRAT);
        eggItem(TolkienEntities.EGG_TTMSQUIRREL);
        eggItem(TolkienEntities.EGG_TTMFROG);
        eggItem(TolkienEntities.EGG_TTMSWARM);
        eggItem(TolkienEntities.EGG_TTMTHRUSH);
        eggItem(TolkienEntities.EGG_TTMCREBAIN);

      // Merchants
        eggItem(TolkienEntities.EGG_TTMHUMAN);
        eggItem(TolkienEntities.EGG_TTMDWARF);
        eggItem(TolkienEntities.EGG_TTMDESERTDWELLER);
        eggItem(TolkienEntities.EGG_TTMELVES);
        eggItem(TolkienEntities.EGG_TTMHOBBIT);

        // Monster
        eggItem(TolkienEntities.EGG_TTMOATHBREAKER);
        eggItem(TolkienEntities.EGG_TTMBARROW);
        eggItem(TolkienEntities.EGG_TTMFELLSPIRIT);
        eggItem(TolkienEntities.EGG_TTMBRIGAND);
        eggItem(TolkienEntities.EGG_TTMHARADRIM);
        eggItem(TolkienEntities.EGG_TTMROMIEWALKER);
        eggItem(TolkienEntities.EGG_TTMDEEPCLAW);
        eggItem(TolkienEntities.EGG_TTMTREEENT);
        eggItem(TolkienEntities.EGG_TTMMIMICCHEST);
        eggItem(TolkienEntities.EGG_TTMDUERGAR);
        eggItem(TolkienEntities.EGG_TTMELEMENTALGOLEM);
        eggItem(TolkienEntities.EGG_TTMGOBLIN);
        eggItem(TolkienEntities.EGG_TTMHURON);
        eggItem(TolkienEntities.EGG_TTMMINOTAUR);
        eggItem(TolkienEntities.EGG_TTMMORDORORC);
        eggItem(TolkienEntities.EGG_TTMTROLL);
        eggItem(TolkienEntities.EGG_TTMWARG);
        eggItem(TolkienEntities.EGG_TTMURUKHAI);
        eggItem(TolkienEntities.EGG_TTMSWAMPHAG);
        eggItem(TolkienEntities.EGG_TTMMIRKWOODSPIDER);
        eggItem(TolkienEntities.EGG_TTMROCKGOLEM);

        // Boss
        eggItem(TolkienEntities.EGG_TTMWATCHER);
        eggItem(TolkienEntities.EGG_TTMBALROG);
        eggItem(TolkienEntities.EGG_TTMWITCHKING);
        eggItem(TolkienEntities.EGG_TTMGWAHIR);
        eggItem(TolkienEntities.EGG_TTMGOBLINKING);
        eggItem(TolkienEntities.EGG_TTMMITHRILGOLEM);
        eggItem(TolkienEntities.EGG_TTMMORGULIRONGOLEM);
        eggItem(TolkienEntities.EGG_TTMSHELOB);
        eggItem(TolkienEntities.EGG_TTMFELLBEAST);

        // Passive
        eggItem(TolkienEntities.EGG_TTMAUROCH);
        eggItem(TolkienEntities.EGG_TTMMUMAKIL);
        eggItem(TolkienEntities.EGG_TTMGOAT);

        // Special
        eggItem(TolkienEntities.EGG_TTMSHADOWFAX);
        eggItem(TolkienEntities.EGG_TTMGOLLUM);
        eggItem(TolkienEntities.EGG_TTMNAZGUL);
        eggItem(TolkienEntities.EGG_TTMNAZGULSTEED);
        eggItem(TolkienEntities.EGG_TTMGREATEAGLE);
        eggItem(TolkienEntities.EGG_TTMISTARI);
    }

    private ItemModelBuilder simpleMod(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", modLoc(name));
    }

    private void registerFence(Block block, String texture)
    {
        getBuilder(BuiltInRegistries.BLOCK.getKey(block).getPath())
                .parent(getExistingFile(mcLoc("block/fence_inventory")))
                .texture("texture", "block/wood/planks_" + texture + "_0");
    }

    private void simpleItem(RegistryObject<? extends Item> item) {
        simpleItem(item, "item");
    }

    @SuppressWarnings("ConstantConditions")
    private void simpleItem(RegistryObject<? extends Item> item, String textureFolder) {
        ResourceLocation reg = item.getId();
        simpleItem(item, new ResourceLocation(reg.getNamespace(), textureFolder + "/" + reg.getPath()));
    }

    @SuppressWarnings("ConstantConditions")
    private void simpleItem(RegistryObject<? extends Item> item, ResourceLocation texture) {
        ResourceLocation reg = item.getId();
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture);
    }

    public void handheldItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath()));
    }

    private void trinketItem(Item item, ResourceLocation texture, ResourceLocation overlay) {
        ResourceLocation reg = ForgeRegistries.ITEMS.getKey(item);
        getBuilder(reg.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", texture)
                .texture("layer1", overlay);
    }

    public void eggItem(Supplier<? extends Item> item) {
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
    }

    private void blockItem(RegistryObject<? extends Block> block) {
        if (block == null) return;
        ResourceLocation reg = block.getId();
        blockItem(block, new ResourceLocation(reg.getNamespace(), "block/" + reg.getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> block, ResourceLocation blockModel) {
        if (block == null) return;
        ResourceLocation reg = block.getId();
        getBuilder(reg.getPath()).parent(new ModelFile.UncheckedModelFile(blockModel));
    }

    private void dummyBlock(RegistryObject<? extends Block> block) {
        getBuilder(block.getId().getPath())//
                .parent(new ModelFile.UncheckedModelFile("builtin/generated"));
    }

    private void dummyItem(RegistryObject<? extends Item> item) {
        getBuilder(item.getId().getPath())//
                .parent(new ModelFile.UncheckedModelFile("builtin/generated"));
    }

    @Override
    public String getName() {
        return NAME + " - Item Models";
    }
}
