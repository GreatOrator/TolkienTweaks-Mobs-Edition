package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;


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
        blockItem(TTMContent.ORE_MORGULIRON.get());
        blockItem(TTMContent.ORE_END_MORGULIRON.get());
        blockItem(TTMContent.ORE_NETHER_MORGULIRON.get());
        blockItem(TTMContent.BLOCK_MORGULIRON.get());
        blockItem(TTMContent.ORE_AMMOLITE.get());
        blockItem(TTMContent.ORE_END_AMMOLITE.get());
        blockItem(TTMContent.ORE_NETHER_AMMOLITE.get());

        // Block Items - Wood & Foliage
        blockItem(TTMContent.LOG_MALLORN.get());
        blockItem(TTMContent.LOG_MIRKWOOD.get());
        blockItem(TTMContent.LOG_CULUMALDA.get());
        blockItem(TTMContent.LOG_LEBETHRON.get());
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
        blockItem(TTMContent.LEAVES_MALLORN.get());
        blockItem(TTMContent.LEAVES_MIRKWOOD.get());
        blockItem(TTMContent.LEAVES_CULUMALDA.get());
        blockItem(TTMContent.LEAVES_LEBETHRON.get());
        blockItem(TTMContent.SAPLING_MALLORN.get());
        blockItem(TTMContent.SAPLING_MIRKWOOD.get());
        blockItem(TTMContent.SAPLING_CULUMALDA.get());
        blockItem(TTMContent.SAPLING_LEBETHRON.get());

        // Block Items - Plants & Flowers
        blockItem(TTMContent.MUSHROOM_DECAY_BLOOM.get());
        blockItem(TTMContent.MUSHROOM_BLOOM_DECAY.get());
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
        simpleItem(TTMContent.BOTTLE_FANCY.get());
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

        //region Potions & Food
        simpleItem(TTMContent.DRINK_ENT_DRAUGHT.get());
        simpleItem(TTMContent.DRINK_PERSONAL_BLACKSMITH.get());
        simpleItem(TTMContent.DRINK_ELF_FLEETFOOT.get());
        simpleItem(TTMContent.DRINK_ELF_VITALITY.get());
        simpleItem(TTMContent.DRINK_ERU_BLESSING.get());
        simpleItem(TTMContent.MIRUVOR.get());
        simpleItem(TTMContent.GROG.get());
        simpleItem(TTMContent.LEMBAS.get());
        simpleItem(TTMContent.HONEY_CAKE.get());
        simpleItem(TTMContent.CRAM.get());
        simpleItem(TTMContent.MONSTER_FLESH.get());
        simpleItem(TTMContent.INSECT.get());
        simpleItem(TTMContent.GOLDEN_INSECT.get());
        simpleItem(TTMContent.TREE_ACORN.get());
        simpleItem(TTMContent.GOLDEN_TREE_ACORN.get());
        simpleItem(TTMContent.FOOD_HONEY.get());

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





//        blockItem(DEContent.generator, modLoc("block/generator/generator"));
//        blockItem(DEContent.grinder, modLoc("block/grinder/grinder"));
//        blockItem(DEContent.energy_pylon, modLoc("block/energy_pylon_input"));
//        blockItem(DEContent.crafting_injector_basic);
//        blockItem(DEContent.crafting_injector_wyvern);
//        blockItem(DEContent.crafting_injector_awakened);
//        blockItem(DEContent.crafting_injector_chaotic);
//        blockItem(DEContent.crafting_core, modLoc("block/crafting/fusion_crafting_core"));
//        blockItem(DEContent.crafting_injector_basic, modLoc("block/crafting/crafting_injector_draconium"));
//        blockItem(DEContent.crafting_injector_wyvern, modLoc("block/crafting/crafting_injector_wyvern"));
//        blockItem(DEContent.crafting_injector_awakened, modLoc("block/crafting/crafting_injector_draconic"));
//        blockItem(DEContent.crafting_injector_chaotic, modLoc("block/crafting/crafting_injector_chaotic"));
//
//        blockItem(DEContent.fluid_gate);
//        blockItem(DEContent.flux_gate);
//        blockItem(DEContent.potentiometer);
//
//
//        blockItem(DEContent.disenchanter);
//        blockItem(DEContent.energy_transfuser, modLoc("block/energy_transfuser"));
//        blockItem(DEContent.dislocator_pedestal);
//        blockItem(DEContent.dislocator_receptacle);
//        blockItem(DEContent.creative_op_capacitor);
//        blockItem(DEContent.entity_detector);
//        blockItem(DEContent.entity_detector_advanced);
//        blockItem(DEContent.stabilized_spawner);
//        blockItem(DEContent.celestial_manipulator);
//        blockItem(DEContent.draconium_chest);
//        blockItem(DEContent.particle_generator);
//        blockItem(DEContent.placed_item);
//        blockItem(DEContent.portal);
//        blockItem(DEContent.chaos_crystal);
//        blockItem(DEContent.energy_core);
//        blockItem(DEContent.energy_core_stabilizer);
//        blockItem(DEContent.energy_core_structure);
//        blockItem(DEContent.reactor_core);
//        blockItem(DEContent.reactor_stabilizer);
//        blockItem(DEContent.reactor_injector);
//        blockItem(DEContent.rain_sensor);
//        blockItem(DEContent.dislocation_inhibitor);
//        blockItem(DEContent.ore_draconium_overworld);
//        blockItem(DEContent.ore_draconium_nether);
//        blockItem(DEContent.ore_draconium_end);
//        blockItem(DEContent.block_draconium);
//        blockItem(DEContent.block_draconium_awakened);
//        blockItem(DEContent.infused_obsidian);
//        dummyModel(DEContent.crystal_io_basic);
//        dummyModel(DEContent.crystal_io_wyvern);
//        dummyModel(DEContent.crystal_io_draconic);
////      dummyModel(DEContent.  crystal_io_chaotic);
//        dummyModel(DEContent.crystal_relay_basic);
//        dummyModel(DEContent.crystal_relay_wyvern);
//        dummyModel(DEContent.crystal_relay_draconic);
////      dummyModel(DEContent.  crystal_relay_chaotic);
//        dummyModel(DEContent.crystal_wireless_basic);
//        dummyModel(DEContent.crystal_wireless_wyvern);
//        dummyModel(DEContent.crystal_wireless_draconic);
////      dummyModel(DEContent.  crystal_wireless_chaotic);
//        //endregion
//
//        //region Components
//        simpleItem(DEContent.dust_draconium, "item/components");
//        simpleItem(DEContent.dust_draconium_awakened, "item/components");
//        simpleItem(DEContent.ingot_draconium, "item/components");
//        simpleItem(DEContent.ingot_draconium_awakened, "item/components");
//        simpleItem(DEContent.nugget_draconium, "item/components");
//        simpleItem(DEContent.nugget_draconium_awakened, "item/components");
//        simpleItem(DEContent.core_draconium, "item/components");
//        simpleItem(DEContent.core_wyvern, "item/components");
//        simpleItem(DEContent.core_awakened, "item/components");
//        simpleItem(DEContent.core_chaotic, "item/components");
//        simpleItem(DEContent.energy_core_wyvern, "item/components");
//        simpleItem(DEContent.energy_core_draconic, "item/components");
//        simpleItem(DEContent.energy_core_chaotic, "item/components");
//        simpleItem(DEContent.dragon_heart, "item/components");
//        simpleItem(DEContent.module_core, "item/components");
//        dummyModel(DEContent.chaos_shard);
//        dummyModel(DEContent.chaos_frag_small);
//        dummyModel(DEContent.chaos_frag_medium);
//        dummyModel(DEContent.chaos_frag_large);
//        dummyModel(DEContent.reactor_prt_stab_frame);
//        dummyModel(DEContent.reactor_prt_in_rotor);
//        dummyModel(DEContent.reactor_prt_out_rotor);
//        dummyModel(DEContent.reactor_prt_rotor_full);
//        dummyModel(DEContent.reactor_prt_focus_ring);
//        //endregion
//
//        //region Misc
//        dummyModel(DEContent.mob_soul);
//        simpleItem(DEContent.magnet);
//        simpleItem(DEContent.magnet_advanced);
//        simpleItem(DEContent.dislocator);
//        simpleItem(DEContent.dislocator_advanced);
//        simpleItem(DEContent.dislocator_p2p, modLoc("item/bound_dislocator"));
//        simpleItem(DEContent.dislocator_player, modLoc("item/bound_dislocator"));
//        simpleItem(DEContent.crystal_binder);
//        simpleItem(DEContent.info_tablet);
//        //endregion
//
//
//        //region Modular Tools
//        simpleItem(DEContent.capacitor_wyvern, "item/tools");
//        simpleItem(DEContent.capacitor_draconic, "item/tools");
//        simpleItem(DEContent.capacitor_chaotic, "item/tools");
//        simpleItem(DEContent.capacitor_creative, "item/tools");
//        simpleItem(DEContent.shovel_wyvern, "item/tools");
//        simpleItem(DEContent.shovel_draconic, "item/tools");
//        simpleItem(DEContent.shovel_chaotic, "item/tools");
//        simpleItem(DEContent.pickaxe_wyvern, "item/tools");
//        simpleItem(DEContent.pickaxe_draconic, "item/tools");
//        simpleItem(DEContent.pickaxe_chaotic, "item/tools");
//        simpleItem(DEContent.axe_wyvern, "item/tools");
//        simpleItem(DEContent.axe_draconic, "item/tools");
//        simpleItem(DEContent.axe_chaotic, "item/tools");
//        simpleItem(DEContent.bow_wyvern, "item/tools");
//        simpleItem(DEContent.bow_draconic, "item/tools");
//        simpleItem(DEContent.bow_chaotic, "item/tools");
//        simpleItem(DEContent.sword_wyvern, "item/tools");
//        simpleItem(DEContent.sword_draconic, "item/tools");
//        simpleItem(DEContent.sword_chaotic, "item/tools");
//        simpleItem(DEContent.staff_draconic, "item/tools");
//        simpleItem(DEContent.staff_chaotic, "item/tools");
//        simpleItem(DEContent.chestpiece_wyvern, "item/tools");
//        simpleItem(DEContent.chestpiece_draconic, "item/tools");
//        simpleItem(DEContent.chestpiece_chaotic, "item/tools");
        //endregion
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
        getBuilder(item.getRegistryName().getPath())//
                .parent(new ModelFile.UncheckedModelFile("builtin/generated"));
    }

    @Override
    public String getName() {
        return "TolkienMobs Item Models";
    }
}
