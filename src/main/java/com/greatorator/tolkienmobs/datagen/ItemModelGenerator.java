package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;


/**
 * Created by brandon3055 on 28/2/20.
 */
public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TolkienMobs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Block Items - Metals & Gems
        blockItem(TTMContent.ORE_MITHRIL.get());
        blockItem(TTMContent.ORE_END_MITHRIL.get());
        blockItem(TTMContent.ORE_NETHER_MITHRIL.get());
        blockItem(TTMContent.BLOCK_MITHRIL.get());
        simpleItem(TTMContent.MITHRIL_BARS_ITEM.get());
        simpleItem(TTMContent.DOOR_MITHRIL_ITEM.get());
        blockItem(TTMContent.TRAPDOOR_MITHRIL.get(), modLoc("block/mithril_trapdoor_bottom"));
        blockItem(TTMContent.PRESSURE_PLATE_MITHRIL.get(), modLoc("block/pressure_plate_mithril"));
        blockItem(TTMContent.ORE_MORGULIRON.get());
        blockItem(TTMContent.ORE_END_MORGULIRON.get());
        blockItem(TTMContent.ORE_NETHER_MORGULIRON.get());
        blockItem(TTMContent.BLOCK_MORGULIRON.get());
        simpleItem(TTMContent.MORGULIRON_BARS_ITEM.get());
        simpleItem(TTMContent.DOOR_MORGULIRON_ITEM.get());
        blockItem(TTMContent.TRAPDOOR_MORGULIRON.get(), modLoc("block/morguliron_trapdoor_bottom"));
        blockItem(TTMContent.PRESSURE_PLATE_MORGULIRON.get(), modLoc("block/pressure_plate_morguliron"));
        blockItem(TTMContent.ORE_AMMOLITE.get());
        blockItem(TTMContent.ORE_END_AMMOLITE.get());
        blockItem(TTMContent.ORE_NETHER_AMMOLITE.get());
        simpleItem(TTMContent.DOOR_DURIN_ITEM.get());

        // Block Items - Wood & Foliage
        blockItem(TTMContent.LOG_MALLORN.get());
        blockItem(TTMContent.LOG_MIRKWOOD.get());
        blockItem(TTMContent.LOG_CULUMALDA.get());
        blockItem(TTMContent.LOG_LEBETHRON.get());
        blockItem(TTMContent.LOG_DEADWOOD.get());
        blockItem(TTMContent.PLANKS_MALLORN.get());
        blockItem(TTMContent.PLANKS_MIRKWOOD.get());
        blockItem(TTMContent.PLANKS_CULUMALDA.get());
        blockItem(TTMContent.PLANKS_LEBETHRON.get());
        blockItem(TTMContent.STAIRS_MALLORN.get());
        blockItem(TTMContent.STAIRS_MIRKWOOD.get());
        blockItem(TTMContent.STAIRS_CULUMALDA.get());
        blockItem(TTMContent.STAIRS_LEBETHRON.get());
        blockItem(TTMContent.SLAB_MALLORN.get());
        blockItem(TTMContent.SLAB_MIRKWOOD.get());
        blockItem(TTMContent.SLAB_LEBETHRON.get());
        blockItem(TTMContent.SLAB_CULUMALDA.get());
        simpleItem(TTMContent.DOOR_MALLORN_ITEM.get());
        simpleItem(TTMContent.DOOR_MIRKWOOD_ITEM.get());
        simpleItem(TTMContent.DOOR_CULUMALDA_ITEM.get());
        simpleItem(TTMContent.DOOR_LEBETHRON_ITEM.get());
        blockItem(TTMContent.FENCE_GATE_MALLORN.get(), modLoc("block/fence_gate_mallorn_fence_gate"));
        blockItem(TTMContent.FENCE_GATE_MIRKWOOD.get(), modLoc("block/fence_gate_mirkwood_fence_gate"));
        blockItem(TTMContent.FENCE_GATE_CULUMALDA.get(), modLoc("block/fence_gate_culumalda_fence_gate"));
        blockItem(TTMContent.FENCE_GATE_LEBETHRON.get(), modLoc("block/fence_gate_lebethron_fence_gate"));
        blockItem(TTMContent.FENCE_MALLORN.get(), modLoc("block/mallorn_fence_inventory"));
        blockItem(TTMContent.FENCE_MIRKWOOD.get(), modLoc("block/mirkwood_fence_inventory"));
        blockItem(TTMContent.FENCE_CULUMALDA.get(), modLoc("block/culumalda_fence_inventory"));
        blockItem(TTMContent.FENCE_LEBETHRON.get(), modLoc("block/lebethron_fence_inventory"));
        blockItem(TTMContent.TRAPDOOR_MALLORN.get(), modLoc("block/mallorn_trapdoor_bottom"));
        blockItem(TTMContent.TRAPDOOR_MIRKWOOD.get(), modLoc("block/mirkwood_trapdoor_bottom"));
        blockItem(TTMContent.TRAPDOOR_CULUMALDA.get(), modLoc("block/culumalda_trapdoor_bottom"));
        blockItem(TTMContent.TRAPDOOR_LEBETHRON.get(), modLoc("block/lebethron_trapdoor_bottom"));
        blockItem(TTMContent.PRESSURE_PLATE_MALLORN.get(), modLoc("block/pressure_plate_mallorn"));
        blockItem(TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), modLoc("block/pressure_plate_mirkwood"));
        blockItem(TTMContent.PRESSURE_PLATE_CULUMALDA.get(), modLoc("block/pressure_plate_culumalda"));
        blockItem(TTMContent.PRESSURE_PLATE_LEBETHRON.get(), modLoc("block/pressure_plate_lebethron"));
        blockItem(TTMContent.TORCH_MALLORN.get(), modLoc("block/torch_mallorn"));
        blockItem(TTMContent.TORCH_MIRKWOOD.get(), modLoc("block/torch_mirkwood"));
        blockItem(TTMContent.TORCH_CULUMALDA.get(), modLoc("block/torch_culumalda"));
        blockItem(TTMContent.TORCH_LEBETHRON.get(), modLoc("block/torch_lebethron"));
        blockItem(TTMContent.LEAVES_MALLORN.get());
        blockItem(TTMContent.LEAVES_MIRKWOOD.get());
        blockItem(TTMContent.LEAVES_CULUMALDA.get());
        blockItem(TTMContent.LEAVES_LEBETHRON.get());
        blockItem(TTMContent.LEAVES_FANGORNOAK.get());
        blockItem(TTMContent.LEAFPILE_MALLORN.get(), modLoc("block/leafpile_mallorn"));
        blockItem(TTMContent.LEAFPILE_MIRKWOOD.get(), modLoc("block/leafpile_mirkwood"));
        blockItem(TTMContent.LEAFPILE_CULUMALDA.get(), modLoc("block/leafpile_culumalda"));
        blockItem(TTMContent.LEAFPILE_LEBETHRON.get(), modLoc("block/leafpile_lebethron"));
        blockItem(TTMContent.LEAFPILE_FANGORNOAK.get(), modLoc("block/leafpile_fangornoak"));
        blockItem(TTMContent.SAPLING_MALLORN.get());
        blockItem(TTMContent.SAPLING_MIRKWOOD.get());
        blockItem(TTMContent.SAPLING_CULUMALDA.get());
        blockItem(TTMContent.SAPLING_LEBETHRON.get());
        blockItem(TTMContent.SAPLING_DEADWOOD.get());
        blockItem(TTMContent.SAPLING_FANGORNOAK.get());

        // Block Items - Plants & Flowers
        blockItem(TTMContent.MUSHROOM_DECAY_BLOOM.get());
        blockItem(TTMContent.MUSHROOM_BLOOM_DECAY.get());
        blockItem(TTMContent.BLOCK_BLOOM_DECAY.get(), modLoc("block/block_decay_bloom_inventory"));
        blockItem(TTMContent.BLOCK_DECAY_BLOOM.get(), modLoc("block/block_bloom_decay_inventory"));
        blockItem(TTMContent.FLOWER_SIMBELMYNE.get());
        blockItem(TTMContent.FLOWER_MIRKWOOD.get());
        blockItem(TTMContent.FLOWER_ALFIRIN.get());
        blockItem(TTMContent.FLOWER_ATHELAS.get());
        blockItem(TTMContent.FLOWER_NIPHREDIL.get());
        blockItem(TTMContent.FLOWER_SWAMPMILKWEED.get());
        blockItem(TTMContent.FLOWER_LILLYOFTHEVALLEY.get());

        // Block Items - Custom Blocks
        blockItem(TTMContent.BLOCK_HALLOWED.get());
        blockItem(TTMContent.STONE_PATH.get());
        blockItem(TTMContent.TTMFIREPLACE.get(), modLoc("block/fireplace_active"));
        blockItem(TTMContent.PIGGYBANK.get(), modLoc("block/block_piggybank"));
        blockItem(TTMContent.BARREL_MITHRIL.get(), modLoc("block/barrel_mithril"));
        blockItem(TTMContent.BARREL_MORGULIRON.get(), modLoc("block/barrel_morguliron"));
        blockItem(TTMContent.BACKPACK.get(), modLoc("block/container_backpack"));
        getBuilder(TTMContent.ITEM_PLACARD.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/placard_wall_empty")));
        blockItem(TTMContent.CHAMELEON_BLOCK.get());

        // Sleeping Bags
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        List<String> sleepingBags = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
        for (String color : sleepingBags) {
            simpleMod(itemGenerated, "item/sleeping_bag_"+color);
        }

        // Keys
        List<String> keys = Arrays.asList("bronze", "silver", "gold", "mithril", "master");
        for (String keyType : keys) {
            simpleMod(itemGenerated, "item/" + keyType + "_key");
        }


        // Metals & Gems Items
        simpleItem(TTMContent.DUST_MITHRIL.get());
        simpleItem(TTMContent.NUGGET_MITHRIL.get());
        simpleItem(TTMContent.INGOT_MITHRIL.get());
        simpleItem(TTMContent.DUST_MORGULIRON.get());
        simpleItem(TTMContent.NUGGET_MORGULIRON.get());
        simpleItem(TTMContent.INGOT_MORGULIRON.get());
        simpleItem(TTMContent.GEM_AMMOLITE.get());

        // Mob Drop Items
        simpleItem(TTMContent.CREBAIN_FEATHER.get());
        simpleItem(TTMContent.BIRD_FEATHER.get());
        simpleItem(TTMContent.MUMAKIL_LEATHER.get());
        simpleItem(TTMContent.MONSTER_FUR.get());
        getBuilder(TTMContent.BOTTLE_FANCY.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_empty")));
        simpleItem(TTMContent.GOLEM_STONE.get());
        simpleItem(TTMContent.GOLEM_STONE_EARTH.get());
        simpleItem(TTMContent.GOLEM_STONE_AIR.get());
        simpleItem(TTMContent.GOLEM_STONE_FIRE.get());
        simpleItem(TTMContent.GOLEM_STONE_WATER.get());
        simpleItem(TTMContent.GOLEM_STONE_SUMMON.get());

        // region Quest Items
        simpleItem(TTMContent.ITEM_BERYL.get());
        simpleItem(TTMContent.ITEM_FORTRESSMAP.get());
        simpleItem(TTMContent.ITEM_WATCHERHEART.get());
        simpleItem(TTMContent.ITEM_WATCHERHEART_CRACKED.get());
        simpleItem(TTMContent.ITEM_KEYSTONE.get());
        simpleItem(TTMContent.ITEM_DARKSADDLE.get());
        simpleItem(TTMContent.ITEM_ARTIFACT.get());
        simpleItem(TTMContent.ITEM_BLANKPAPER.get());
        simpleItem(TTMContent.ITEM_FANCYARMOR.get());
        simpleItem(TTMContent.ITEM_FANCYCLOTH.get());
        simpleItem(TTMContent.ITEM_FANCYHAMMER.get());
        simpleItem(TTMContent.ITEM_FANCYHELM.get());
        simpleItem(TTMContent.ITEM_FANCYKEY.get());
        simpleItem(TTMContent.ITEM_FANCYPICK.get());
        simpleItem(TTMContent.ITEM_FANCYSHIELD.get());
        simpleItem(TTMContent.ITEM_FANCYSHIELD2.get());
        simpleItem(TTMContent.ITEM_FANCYSWORD.get());
        simpleItem(TTMContent.ITEM_FANCYSWORD2.get());
        simpleItem(TTMContent.ITEM_LETTER.get());
        simpleItem(TTMContent.ITEM_SCROLL.get());
        simpleItem(TTMContent.ITEM_SCROLL2.get());
        simpleItem(TTMContent.ITEM_SPECIALFLOWER.get());
        simpleItem(TTMContent.ITEM_STORYBOOK.get());
        simpleItem(TTMContent.ITEM_STORYBOOK2.get());
        simpleItem(TTMContent.ITEM_STORYBOOK3.get());
        simpleItem(TTMContent.ITEM_STORYBOOK4.get());
        simpleItem(TTMContent.ITEM_WORNARMOR.get());
        simpleItem(TTMContent.ITEM_WORNHELM.get());
        simpleItem(TTMContent.ITEM_WORNKEY.get());
        simpleItem(TTMContent.ITEM_WORNPICK.get());
        simpleItem(TTMContent.ITEM_WORNSHIELD.get());
        simpleItem(TTMContent.ITEM_WORNSHIELD2.get());
        simpleItem(TTMContent.ITEM_WORNSWORD.get());
        simpleItem(TTMContent.ITEM_WOVENBASKET.get());
        simpleItem(TTMContent.ITEM_WRITTENPAPER.get());
        simpleItem(TTMContent.ITEM_PUNGENTHERB.get());
        simpleItem(TTMContent.ITEM_LOCKPICK.get());
        simpleItem(TTMContent.ITEM_BROKENSWORD.get());
        simpleItem(TTMContent.ITEM_REFORGEDSWORD.get());
        simpleItem(TTMContent.ITEM_MAGIC_CLOTH.get());
        simpleItem(TTMContent.ITEM_KEYFRAGMENT.get());
        simpleItem(TTMContent.ITEM_OILYKEY.get());
        simpleItem(TTMContent.ITEM_MITHRILNUGGET.get());
        simpleItem(TTMContent.ITEM_REMAINS.get());
        simpleItem(TTMContent.ITEM_RUNE_STONE.get());
        simpleItem(TTMContent.HYPE_HORN.get());

        //region Backpack Upgrades
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get());
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_SIZE.get());
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_FLUID.get());
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_CRAFTING.get());
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_SLEEPING.get());
        simpleItem(TTMContent.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get());

        //region Coin & Token Items
        simpleItem(TTMContent.ITEM_COIN_BRONZE.get());
        simpleItem(TTMContent.ITEM_COIN_SILVER.get());
        simpleItem(TTMContent.ITEM_COIN_GOLD.get());
        simpleItem(TTMContent.ITEM_COIN_MITHRIL.get());
        simpleItem(TTMContent.ITEM_DARKSIGIL.get());
        simpleItem(TTMContent.ITEM_FACTIONCOIN.get());
        simpleItem(TTMContent.ITEM_FACTIONTOKEN.get());
        simpleItem(TTMContent.ITEM_CAVECOMPLETE.get());
        simpleItem(TTMContent.ITEM_WATCHERCOMPLETE.get());
        simpleItem(TTMContent.ITEM_TOKEN_EASTERN_ALLIANCE.get());
        simpleItem(TTMContent.ITEM_TOKEN_WESTERN_ALLIANCE.get());

        //region Potions & Food
        getBuilder(TTMContent.DRINK_ENT_DRAUGHT.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_ent_draught")));
        getBuilder(TTMContent.DRINK_PERSONAL_BLACKSMITH.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_personal_blacksmith")));
        getBuilder(TTMContent.DRINK_ELF_FLEETFOOT.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_blessing")));
        getBuilder(TTMContent.DRINK_ELF_VITALITY.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_elf_vitality")));
        getBuilder(TTMContent.DRINK_ERU_BLESSING.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_eru_blessing")));
        getBuilder(TTMContent.MIRUVOR.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_miruvor")));
        getBuilder(TTMContent.GROG.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("item/bottle_grog")));
        simpleItem(TTMContent.LEMBAS.get());
        simpleItem(TTMContent.HONEY_CAKE.get());
        simpleItem(TTMContent.CRAM.get());
        simpleItem(TTMContent.MONSTER_FLESH.get());
        simpleItem(TTMContent.INSECT.get());
        simpleItem(TTMContent.GOLDEN_INSECT.get());
        simpleItem(TTMContent.TREE_ACORN.get());
        simpleItem(TTMContent.GOLDEN_TREE_ACORN.get());
        simpleItem(TTMContent.FOOD_HONEY.get());
        simpleItem(TTMContent.PIPEWEED_SEEDS.get());
        simpleItem(TTMContent.PIPEWEED_ITEM.get());

        //region Music Discs
        simpleItem(TTMContent.RECORD_RIVENDELL.get());
        simpleItem(TTMContent.RECORD_LOTHLORIEN.get());
        simpleItem(TTMContent.RECORD_EREBOR.get());
        simpleItem(TTMContent.RECORD_WILLOW.get());
        simpleItem(TTMContent.RECORD_MINASTIRITH.get());
        simpleItem(TTMContent.RECORD_EDORAS.get());
        simpleItem(TTMContent.RECORD_WBATTLE.get());
        simpleItem(TTMContent.RECORD_MURDERFROG.get());
        simpleItem(TTMContent.RECORD_REDER.get());
        simpleItem(TTMContent.RECORD_FUMBLE.get());
        simpleItem(TTMContent.RECORD_BOMBADIL.get());
        simpleItem(TTMContent.RECORD_HOBBITS.get());

        //region Tools & Armor
        simpleItem(TTMContent.HELMET_MITHRIL.get());
        simpleItem(TTMContent.CHESTPLATE_MITHRIL.get());
        simpleItem(TTMContent.LEGGINGS_MITHRIL.get());
        simpleItem(TTMContent.BOOTS_MITHRIL.get());
        simpleItem(TTMContent.HELMET_MORGULIRON.get());
        simpleItem(TTMContent.CHESTPLATE_MORGULIRON.get());
        simpleItem(TTMContent.LEGGINGS_MORGULIRON.get());
        simpleItem(TTMContent.BOOTS_MORGULIRON.get());

        handheldItem(TTMContent.AXE_MITHRIL.get());
        handheldItem(TTMContent.HOE_MITHRIL.get());
        handheldItem(TTMContent.PICKAXE_MITHRIL.get());
        handheldItem(TTMContent.SHOVEL_MITHRIL.get());
        handheldItem(TTMContent.SWORD_MITHRIL.get());
        handheldItem(TTMContent.AXE_MORGULIRON.get());
        handheldItem(TTMContent.HOE_MORGULIRON.get());
        handheldItem(TTMContent.PICKAXE_MORGULIRON.get());
        handheldItem(TTMContent.SHOVEL_MORGULIRON.get());
        handheldItem(TTMContent.SWORD_MORGULIRON.get());
        handheldItem(TTMContent.SWORD_WITCHKING.get());
        handheldItem(TTMContent.SWORD_URUK.get());
        simpleItem(TTMContent.GALADHRIM_ARROW.get());
        simpleItem(TTMContent.FELLBEAST_FIREBALL.get());
        simpleItem(TTMContent.BOULDER.get());

        //region Trinkets
        trinketItem(TTMContent.TRINKET_AMULET.get(), modLoc("item/trinket_amulet"), modLoc("item/trinket_amulet"));
        trinketItem(TTMContent.TRINKET_BELT.get(), modLoc("item/trinket_belt"), modLoc("item/trinket_belt"));
        trinketItem(TTMContent.TRINKET_CHARM.get(), modLoc("item/trinket_charm"), modLoc("item/trinket_charm"));
        trinketItem(TTMContent.TRINKET_RING.get(), modLoc("item/trinket_ring"), modLoc("item/trinket_ring"));
        trinketItem(TTMContent.TRINKET_GLOVE.get(), modLoc("item/trinket_glove"), modLoc("item/trinket_glove"));
        trinketItem(TTMContent.TRINKET_HAT.get(), modLoc("item/trinket_hat"), modLoc("item/trinket_hat"));
        trinketItem(TTMContent.TRINKET_CLOAK.get(), modLoc("item/trinket_cloak"), modLoc("item/trinket_cloak"));

        //region Spawn Eggs
            // Ambient
        eggItem(EntityGenerator.EGG_TTMRAT.get());
        eggItem(EntityGenerator.EGG_TTMSQUIRREL.get());
        eggItem(EntityGenerator.EGG_TTMFROG.get());
        eggItem(EntityGenerator.EGG_TTMSWARM.get());
        eggItem(EntityGenerator.EGG_TTMTHRUSH.get());
        eggItem(EntityGenerator.EGG_TTMCREBAIN.get());

            // Merchants
        eggItem(EntityGenerator.EGG_TTMHUMAN.get());
        eggItem(EntityGenerator.EGG_TTMDWARF.get());
        eggItem(EntityGenerator.EGG_TTMELVES.get());
        eggItem(EntityGenerator.EGG_TTMHOBBIT.get());
        eggItem(EntityGenerator.EGG_TTMDESERTDWELLER.get());

            // Monster
        eggItem(EntityGenerator.EGG_TTMGOBLIN.get());
        eggItem(EntityGenerator.EGG_TTMBARROW.get());
        eggItem(EntityGenerator.EGG_TTMBRIGAND.get());
        eggItem(EntityGenerator.EGG_TTMDEEPCLAW.get());
        eggItem(EntityGenerator.EGG_TTMTREEENT.get());
        eggItem(EntityGenerator.EGG_TTMDUERGAR.get());
        eggItem(EntityGenerator.EGG_TTMFELLSPIRIT.get());
        eggItem(EntityGenerator.EGG_TTMSWAMPHAG.get());
        eggItem(EntityGenerator.EGG_TTMMIRKWOODSPIDER.get());
        eggItem(EntityGenerator.EGG_TTMHARADRIM.get());
        eggItem(EntityGenerator.EGG_TTMTROLL.get());
        eggItem(EntityGenerator.EGG_TTMWARG.get());
        eggItem(EntityGenerator.EGG_TTMMORDORORC.get());
        eggItem(EntityGenerator.EGG_TTMHURON.get());
        eggItem(EntityGenerator.EGG_TTMOATHBREAKER.get());
        eggItem(EntityGenerator.EGG_TTMROMIEWALKER.get());
        eggItem(EntityGenerator.EGG_TTMURUKHAI.get());
        eggItem(EntityGenerator.EGG_TTMELEMENTALGOLEM.get());
        eggItem(EntityGenerator.EGG_TTMMINOTAUR.get());
        eggItem(EntityGenerator.EGG_TTMMIMICCHEST.get());

            // Boss
        eggItem(EntityGenerator.EGG_TTMGOBLINKING.get());
        eggItem(EntityGenerator.EGG_TTMMITHRILGOLEM.get());
        eggItem(EntityGenerator.EGG_TTMMORGULIRONGOLEM.get());
        eggItem(EntityGenerator.EGG_TTMWITCHKING.get());
        eggItem(EntityGenerator.EGG_TTMSHELOB.get());
        eggItem(EntityGenerator.EGG_TTMBALROG.get());
        eggItem(EntityGenerator.EGG_TTMWATCHER.get());

            // Passive
        eggItem(EntityGenerator.EGG_TTMAUROCH.get());
        eggItem(EntityGenerator.EGG_TTMMUMAKIL.get());
        eggItem(EntityGenerator.EGG_TTMGOAT.get());

            // Special
        eggItem(EntityGenerator.EGG_TTMSHADOWFAX.get());
        eggItem(EntityGenerator.EGG_TTMGOLLUM.get());
        eggItem(EntityGenerator.EGG_TTMNAZGUL.get());
        eggItem(EntityGenerator.EGG_TTMNAZGULSTEED.get());

            //Signs
        simpleItem(TTMContent.MALLORN_SIGN_ITEM_WOOD_TYPE.get());
        simpleItem(TTMContent.MIRKWOOD_SIGN_ITEM_WOOD_TYPE.get());
        simpleItem(TTMContent.CULUMALDA_SIGN_ITEM_WOOD_TYPE.get());
        simpleItem(TTMContent.LEBETHRON_SIGN_ITEM_WOOD_TYPE.get());
    }

    private ItemModelBuilder simpleMod(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", modLoc(name));
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
        return "Tolkien Tweaks - Mobs Edition Item Models";
    }
}
