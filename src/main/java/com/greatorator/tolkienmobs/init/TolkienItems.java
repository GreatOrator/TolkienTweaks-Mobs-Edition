package com.greatorator.tolkienmobs.init;

import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.handler.enums.TTMArmorTier;
import com.greatorator.tolkienmobs.item.armor.MithrilArmor;
import com.greatorator.tolkienmobs.item.armor.MithrilHorseArmor;
import com.greatorator.tolkienmobs.item.armor.MorgulironArmor;
import com.greatorator.tolkienmobs.item.armor.MorgulironHorseArmor;
import com.greatorator.tolkienmobs.item.tools.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.init.TolkienBlocks.*;
import static com.greatorator.tolkienmobs.init.TolkienSounds.*;
import static com.greatorator.tolkienmobs.init.TolkienTabs.*;

public class TolkienItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Blocks - Metals & Gems
    public static RegistryObject<Item> ORE_MITHRIL_ITEM = ITEMS.register("ore_mithril", () -> new ItemBlockBCore(ORE_MITHRIL.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_END_MITHRIL_ITEM = ITEMS.register("ore_end_mithril", () -> new ItemBlockBCore(ORE_END_MITHRIL.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MITHRIL_ITEM = ITEMS.register("ore_nether_mithril", () -> new ItemBlockBCore(ORE_NETHER_MITHRIL.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BLOCK_MITHRIL_ITEM = ITEMS.register("block_mithril", () -> new ItemBlockBCore(BLOCK_MITHRIL.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MITHRIL_BARS_ITEM = ITEMS.register("mithril_bars", () -> new ItemBlockBCore(MITHRIL_BARS.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_MITHRIL_ITEM = ITEMS.register("door_mithril", () -> new ItemBlockBCore(DOOR_MITHRIL.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_MITHRIL_ITEM = ITEMS.register("trapdoor_mithril", () -> new ItemBlockBCore(TRAPDOOR_MITHRIL.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_MITHRIL_ITEM = ITEMS.register("pressure_plate_mithril", () -> new ItemBlockBCore(PRESSURE_PLATE_MITHRIL.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> ORE_MORGULIRON_ITEM = ITEMS.register("ore_morguliron", () -> new ItemBlockBCore(ORE_MORGULIRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_END_MORGULIRON_ITEM = ITEMS.register("ore_end_morguliron", () -> new ItemBlockBCore(ORE_END_MORGULIRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MORGULIRON_ITEM = ITEMS.register("ore_nether_morguliron", () -> new ItemBlockBCore(ORE_NETHER_MORGULIRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BLOCK_MORGULIRON_ITEM = ITEMS.register("block_morguliron", () -> new ItemBlockBCore(BLOCK_MORGULIRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MORGULIRON_BARS_ITEM = ITEMS.register("morguliron_bars", () -> new ItemBlockBCore(MORGULIRON_BARS.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_MORGULIRON_ITEM = ITEMS.register("door_morguliron", () -> new ItemBlockBCore(DOOR_MORGULIRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_MORGULIRON_ITEM = ITEMS.register("trapdoor_morguliron", () -> new ItemBlockBCore(TRAPDOOR_MORGULIRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_MORGULIRON_ITEM = ITEMS.register("pressure_plate_morguliron", () -> new ItemBlockBCore(PRESSURE_PLATE_MORGULIRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> ORE_AMMOLITE_ITEM = ITEMS.register("ore_ammolite", () -> new ItemBlockBCore(ORE_AMMOLITE.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_END_AMMOLITE_ITEM = ITEMS.register("ore_end_ammolite", () -> new ItemBlockBCore(ORE_END_AMMOLITE.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_AMMOLITE_ITEM = ITEMS.register("ore_nether_ammolite", () -> new ItemBlockBCore(ORE_NETHER_AMMOLITE.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BLOCK_AMMOLITE_ITEM = ITEMS.register("block_ammolite", () -> new ItemBlockBCore(BLOCK_AMMOLITE.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_DURIN_ITEM = ITEMS.register("door_durin", () -> new ItemBlockBCore(DOOR_DURIN.get(), new Item.Properties().tab(decoGroup)));

    // Blocks - Wood & Foliage
    public static RegistryObject<Item> LOG_MALLORN_ITEM = ITEMS.register("log_mallorn", () -> new ItemBlockBCore(LOG_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LOG_MIRKWOOD_ITEM = ITEMS.register("log_mirkwood", () -> new ItemBlockBCore(LOG_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LOG_CULUMALDA_ITEM = ITEMS.register("log_culumalda", () -> new ItemBlockBCore(LOG_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LOG_LEBETHRON_ITEM = ITEMS.register("log_lebethron", () -> new ItemBlockBCore(LOG_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LOG_DEADWOOD_ITEM = ITEMS.register("log_deadwood", () -> new ItemBlockBCore(LOG_DEADWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> WOOD_MALLORN_ITEM = ITEMS.register("wood_mallorn", () -> new ItemBlockBCore(WOOD_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> WOOD_MIRKWOOD_ITEM = ITEMS.register("wood_mirkwood", () -> new ItemBlockBCore(WOOD_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> WOOD_CULUMALDA_ITEM = ITEMS.register("wood_culumalda", () -> new ItemBlockBCore(WOOD_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> WOOD_LEBETHRON_ITEM = ITEMS.register("wood_lebethron", () -> new ItemBlockBCore(WOOD_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_MALLORN_LOG_ITEM = ITEMS.register("stripped_log_mallorn", () -> new ItemBlockBCore(STRIPPED_MALLORN_LOG.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_MIRKWOOD_LOG_ITEM = ITEMS.register("stripped_log_mirkwood", () -> new ItemBlockBCore(STRIPPED_MIRKWOOD_LOG.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_CULUMALDA_LOG_ITEM = ITEMS.register("stripped_log_culumalda", () -> new ItemBlockBCore(STRIPPED_CULUMALDA_LOG.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_LEBETHRON_LOG_ITEM = ITEMS.register("stripped_log_lebethron", () -> new ItemBlockBCore(STRIPPED_LEBETHRON_LOG.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_MALLORN_WOOD_ITEM = ITEMS.register("stripped_wood_mallorn", () -> new ItemBlockBCore(STRIPPED_MALLORN_WOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_MIRKWOOD_WOOD_ITEM = ITEMS.register("stripped_wood_mirkwood", () -> new ItemBlockBCore(STRIPPED_MIRKWOOD_WOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_CULUMALDA_WOOD_ITEM = ITEMS.register("stripped_wood_culumalda", () -> new ItemBlockBCore(STRIPPED_CULUMALDA_WOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STRIPPED_LEBETHRON_WOOD_ITEM = ITEMS.register("stripped_wood_lebethron", () -> new ItemBlockBCore(STRIPPED_LEBETHRON_WOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MALLORN_BUTTON_ITEM = ITEMS.register("mallorn_button", () -> new ItemBlockBCore(MALLORN_BUTTON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> MIRKWOOD_BUTTON_ITEM = ITEMS.register("mirkwood_button", () -> new ItemBlockBCore(MIRKWOOD_BUTTON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CULUMALDA_BUTTON_ITEM = ITEMS.register("culumalda_button", () -> new ItemBlockBCore(CULUMALDA_BUTTON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LEBETHRON_BUTTON_ITEM = ITEMS.register("lebethron_button", () -> new ItemBlockBCore(LEBETHRON_BUTTON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PLANKS_CULUMALDA_ITEM = ITEMS.register("planks_culumalda", () -> new ItemBlockBCore(PLANKS_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> PLANKS_LEBETHRON_ITEM = ITEMS.register("planks_lebethron", () -> new ItemBlockBCore(PLANKS_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> PLANKS_MALLORN_ITEM = ITEMS.register("planks_mallorn", () -> new ItemBlockBCore(PLANKS_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> PLANKS_MIRKWOOD_ITEM = ITEMS.register("planks_mirkwood", () -> new ItemBlockBCore(PLANKS_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> STAIRS_MALLORN_ITEM = ITEMS.register("stairs_mallorn", () -> new ItemBlockBCore(STAIRS_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> STAIRS_MIRKWOOD_ITEM = ITEMS.register("stairs_mirkwood", () -> new ItemBlockBCore(STAIRS_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> STAIRS_LEBETHRON_ITEM = ITEMS.register("stairs_lebethron", () -> new ItemBlockBCore(STAIRS_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> STAIRS_CULUMALDA_ITEM = ITEMS.register("stairs_culumalda", () -> new ItemBlockBCore(STAIRS_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> SLAB_MALLORN_ITEM = ITEMS.register("slab_mallorn", () -> new ItemBlockBCore(SLAB_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> SLAB_MIRKWOOD_ITEM = ITEMS.register("slab_mirkwood", () -> new ItemBlockBCore(SLAB_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> SLAB_CULUMALDA_ITEM = ITEMS.register("slab_culumalda", () -> new ItemBlockBCore(SLAB_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> SLAB_LEBETHRON_ITEM = ITEMS.register("slab_lebethron", () -> new ItemBlockBCore(SLAB_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_MALLORN_ITEM = ITEMS.register("door_mallorn", () -> new ItemBlockBCore(DOOR_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_MIRKWOOD_ITEM = ITEMS.register("door_mirkwood", () -> new ItemBlockBCore(DOOR_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_CULUMALDA_ITEM = ITEMS.register("door_culumalda", () -> new ItemBlockBCore(DOOR_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> DOOR_LEBETHRON_ITEM = ITEMS.register("door_lebethron", () -> new ItemBlockBCore(DOOR_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_GATE_MALLORN_ITEM = ITEMS.register("fence_gate_mallorn", () -> new ItemBlockBCore(FENCE_GATE_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_GATE_MIRKWOOD_ITEM = ITEMS.register("fence_gate_mirkwood", () -> new ItemBlockBCore(FENCE_GATE_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_GATE_CULUMALDA_ITEM = ITEMS.register("fence_gate_culumalda", () -> new ItemBlockBCore(FENCE_GATE_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_GATE_LEBETHRON_ITEM = ITEMS.register("fence_gate_lebethron", () -> new ItemBlockBCore(FENCE_GATE_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_MALLORN_ITEM = ITEMS.register("fence_mallorn", () -> new ItemBlockBCore(FENCE_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_MIRKWOOD_ITEM = ITEMS.register("fence_mirkwood", () -> new ItemBlockBCore(FENCE_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_CULUMALDA_ITEM = ITEMS.register("fence_culumalda", () -> new ItemBlockBCore(FENCE_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> FENCE_LEBETHRON_ITEM = ITEMS.register("fence_lebethron", () -> new ItemBlockBCore(FENCE_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_MALLORN_ITEM = ITEMS.register("trapdoor_mallorn", () -> new ItemBlockBCore(TRAPDOOR_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_MIRKWOOD_ITEM = ITEMS.register("trapdoor_mirkwood", () -> new ItemBlockBCore(TRAPDOOR_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_CULUMALDA_ITEM = ITEMS.register("trapdoor_culumalda", () -> new ItemBlockBCore(TRAPDOOR_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TRAPDOOR_LEBETHRON_ITEM = ITEMS.register("trapdoor_lebethron", () -> new ItemBlockBCore(TRAPDOOR_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_MALLORN_ITEM = ITEMS.register("pressure_plate_mallorn", () -> new ItemBlockBCore(PRESSURE_PLATE_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_MIRKWOOD_ITEM = ITEMS.register("pressure_plate_mirkwood", () -> new ItemBlockBCore(PRESSURE_PLATE_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_CULUMALDA_ITEM = ITEMS.register("pressure_plate_culumalda", () -> new ItemBlockBCore(PRESSURE_PLATE_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PRESSURE_PLATE_LEBETHRON_ITEM = ITEMS.register("pressure_plate_lebethron", () -> new ItemBlockBCore(PRESSURE_PLATE_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_MALLORN_ITEM = ITEMS.register("torch_mallorn", () -> new StandingAndWallBlockItem(TORCH_MALLORN.get(), WALL_TORCH_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_MIRKWOOD_ITEM = ITEMS.register("torch_mirkwood", () -> new StandingAndWallBlockItem(TORCH_MIRKWOOD.get(), WALL_TORCH_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_CULUMALDA_ITEM = ITEMS.register("torch_culumalda", () -> new StandingAndWallBlockItem(TORCH_CULUMALDA.get(), WALL_TORCH_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_LEBETHRON_ITEM = ITEMS.register("torch_lebethron", () -> new StandingAndWallBlockItem(TORCH_LEBETHRON.get(), WALL_TORCH_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LEAVES_CULUMALDA_ITEM = ITEMS.register("leaves_culumalda", () -> new ItemBlockBCore(LEAVES_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MALLORN_SIGN_ITEM = ITEMS.register("sign_mallorn", () -> new TolkienSignItem(new Item.Properties().tab(decoGroup), MALLORN_SIGN.get(), MALLORN_WALL_SIGN.get()));
    public static RegistryObject<Item> MIRKWOOD_SIGN_ITEM = ITEMS.register("sign_mirkwood", () -> new TolkienSignItem(new Item.Properties().tab(decoGroup), MIRKWOOD_SIGN.get(), MIRKWOOD_WALL_SIGN.get()));
    public static RegistryObject<Item> CULUMALDA_SIGN_ITEM = ITEMS.register("sign_culumalda", () -> new TolkienSignItem(new Item.Properties().tab(decoGroup), CULUMALDA_SIGN.get(), CULUMALDA_WALL_SIGN.get()));
    public static RegistryObject<Item> LEBETHRON_SIGN_ITEM = ITEMS.register("sign_lebethron", () -> new TolkienSignItem(new Item.Properties().tab(decoGroup), LEBETHRON_SIGN.get(), LEBETHRON_WALL_SIGN.get()));
    public static RegistryObject<Item> LEAVES_LEBETHRON_ITEM = ITEMS.register("leaves_lebethron", () -> new ItemBlockBCore(LEAVES_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAVES_MALLORN_ITEM = ITEMS.register("leaves_mallorn", () -> new ItemBlockBCore(LEAVES_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAVES_MIRKWOOD_ITEM = ITEMS.register("leaves_mirkwood", () -> new ItemBlockBCore(LEAVES_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAVES_FANGORNOAK_ITEM = ITEMS.register("leaves_fangornoak", () -> new ItemBlockBCore(LEAVES_FANGORNOAK.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAFPILE_MALLORN_ITEM = ITEMS.register("leafpile_mallorn", () -> new ItemBlockBCore(LEAFPILE_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAFPILE_MIRKWOOD_ITEM = ITEMS.register("leafpile_mirkwood", () -> new ItemBlockBCore(LEAFPILE_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAFPILE_CULUMALDA_ITEM = ITEMS.register("leafpile_culumalda", () -> new ItemBlockBCore(LEAFPILE_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAFPILE_LEBETHRON_ITEM = ITEMS.register("leafpile_lebethron", () -> new ItemBlockBCore(LEAFPILE_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> LEAFPILE_FANGORNOAK_ITEM = ITEMS.register("leafpile_fangornoak", () -> new ItemBlockBCore(LEAFPILE_FANGORNOAK.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_CULUMALDA_ITEM = ITEMS.register("sapling_culumalda", () -> new ItemBlockBCore(SAPLING_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_LEBETHRON_ITEM = ITEMS.register("sapling_lebethron", () -> new ItemBlockBCore(SAPLING_LEBETHRON.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_MALLORN_ITEM = ITEMS.register("sapling_mallorn", () -> new ItemBlockBCore(SAPLING_MALLORN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_MIRKWOOD_ITEM = ITEMS.register("sapling_mirkwood", () -> new ItemBlockBCore(SAPLING_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_DEADWOOD_ITEM = ITEMS.register("sapling_deadwood", () -> new ItemBlockBCore(SAPLING_DEADWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> SAPLING_FANGORNOAK_ITEM = ITEMS.register("sapling_fangornoak", () -> new ItemBlockBCore(SAPLING_FANGORNOAK.get(), new Item.Properties().tab(matsGroup)));

    // Blocks - Plants & Flowers
    public static RegistryObject<Item> MUSHROOM_DECAY_BLOOM_ITEM = ITEMS.register("mushroom_decay_bloom", () -> new ItemBlockBCore(MUSHROOM_DECAY_BLOOM.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MUSHROOM_BLOOM_DECAY_ITEM = ITEMS.register("mushroom_bloom_decay", () -> new ItemBlockBCore(MUSHROOM_BLOOM_DECAY.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BLOCK_DECAY_BLOOM_ITEM = ITEMS.register("block_decay_bloom", () -> new ItemBlockBCore(BLOCK_DECAY_BLOOM.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BLOCK_BLOOM_DECAY_ITEM = ITEMS.register("block_bloom_decay", () -> new ItemBlockBCore(BLOCK_BLOOM_DECAY.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_SIMBELMYNE_ITEM = ITEMS.register("flower_simbelmyne", () -> new ItemBlockBCore(FLOWER_SIMBELMYNE.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_MIRKWOOD_ITEM = ITEMS.register("flower_mirkwood", () -> new ItemBlockBCore(FLOWER_MIRKWOOD.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_ALFIRIN_ITEM = ITEMS.register("flower_alfirin", () -> new ItemBlockBCore(FLOWER_ALFIRIN.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_ATHELAS_ITEM = ITEMS.register("flower_athelas", () -> new ItemBlockBCore(FLOWER_ATHELAS.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_NIPHREDIL_ITEM = ITEMS.register("flower_niphredil", () -> new ItemBlockBCore(FLOWER_NIPHREDIL.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_SWAMPMILKWEED_ITEM = ITEMS.register("flower_swamp_milkweed", () -> new ItemBlockBCore(FLOWER_SWAMPMILKWEED.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> FLOWER_LILLYOFTHEVALLEY_ITEM = ITEMS.register("flower_valley_lilly", () -> new ItemBlockBCore(FLOWER_LILLYOFTHEVALLEY.get(), new Item.Properties().tab(matsGroup)));

    // Blocks - Custom
    public static RegistryObject<Item> BLOCK_HALLOWED_ITEM = ITEMS.register("block_hallowed", () -> new ItemBlockBCore(BLOCK_HALLOWED.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> STONE_PATH_ITEM = ITEMS.register("block_stone_path", () -> new ItemBlockBCore(STONE_PATH.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TTMFIREPLACE_ITEM = ITEMS.register("block_tmfireplace", () -> new ItemBlockBCore(TTMFIREPLACE.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> PIGGYBANK_ITEM = ITEMS.register("block_piggybank", () -> new ItemBlockBCore(PIGGYBANK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> BARREL_MITHRIL_ITEM = ITEMS.register("block_barrel_mithril", () -> new ItemBlockBCore(BARREL_MITHRIL.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> BARREL_MORGULIRON_ITEM = ITEMS.register("block_barrel_morguliron", () -> new ItemBlockBCore(BARREL_MORGULIRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> ITEM_PLACARD = ITEMS.register("item_placard", () -> new LoreBlock(TolkienBlocks.PLACARD.get(), (new Item.Properties()).stacksTo(1).tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> CHAMELEON_BLOCK_ITEM = ITEMS.register("chameleon_block", () -> new ItemBlockBCore(CHAMELEON_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> KEY_STONE_BLOCK_ITEM = ITEMS.register("block_key_stone", () -> new ItemBlockBCore(KEY_STONE_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CAMO_GLOWSTONE_BLOCK_ITEM = ITEMS.register("block_camo_glowstone", () -> new ItemBlockBCore(CAMO_GLOWSTONE_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CAMO_SMOKER_BLOCK_ITEM = ITEMS.register("block_camo_smoker", () -> new ItemBlockBCore(CAMO_SMOKER_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CAMO_FLUID_BLOCK_ITEM = ITEMS.register("block_camo_fluid", () -> new ItemBlockBCore(CAMO_FLUID_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CAMO_CHEST_BLOCK_ITEM = ITEMS.register("block_camo_chest", () -> new ItemBlockBCore(CAMO_CHEST_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> CAMO_SPAWNER_BLOCK_ITEM = ITEMS.register("block_camo_spawner", () -> new ItemBlockBCore(CAMO_SPAWNER_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> MILESTONE_BLOCK_ITEM = ITEMS.register("milestone_block", () -> new ItemBlockBCore(MILESTONE_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LOCKABLE_CHEST_BLOCK_ITEM = ITEMS.register("lockable_chest_block", () -> new ItemBlockBCore(LOCKABLE_CHEST_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LOCKABLE_TREASURE_CHEST_BLOCK_ITEM = ITEMS.register("lockable_treasure_chest_block", () -> new ItemBlockBCore(LOCKABLE_TREASURE_CHEST_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LOCKABLE_DOUBLE_CHEST_BLOCK_ITEM = ITEMS.register("lockable_double_chest_block", () -> new ItemBlockBCore(LOCKABLE_DOUBLE_CHEST_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK_ITEM = ITEMS.register("lockable_double_treasure_chest_block", () -> new ItemBlockBCore(LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> ROCKPILE_ITEM = ITEMS.register("rockpile", () -> new ItemBlockBCore(ROCKPILE.get(), new Item.Properties().tab(decoGroup)));

    // Blocks - Sleeping Bags
    public static RegistryObject<Item> SLEEPING_BAG_BLUE_ITEM = ITEMS.register("sleeping_bag_blue", () -> new LoreBlock(SLEEPING_BAG_BLUE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_RED_ITEM = ITEMS.register("sleeping_bag_red", () -> new LoreBlock(SLEEPING_BAG_RED.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_BLACK_ITEM = ITEMS.register("sleeping_bag_black", () -> new LoreBlock(SLEEPING_BAG_BLACK.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_BROWN_ITEM = ITEMS.register("sleeping_bag_brown", () -> new LoreBlock(SLEEPING_BAG_BROWN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_CYAN_ITEM = ITEMS.register("sleeping_bag_cyan", () -> new LoreBlock(SLEEPING_BAG_CYAN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_GRAY_ITEM = ITEMS.register("sleeping_bag_gray", () -> new LoreBlock(SLEEPING_BAG_GRAY.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_GREEN_ITEM = ITEMS.register("sleeping_bag_green", () -> new LoreBlock(SLEEPING_BAG_GREEN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIGHT_BLUE_ITEM = ITEMS.register("sleeping_bag_light_blue", () -> new LoreBlock(SLEEPING_BAG_LIGHT_BLUE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIGHT_GRAY_ITEM = ITEMS.register("sleeping_bag_light_gray", () -> new LoreBlock(SLEEPING_BAG_LIGHT_GRAY.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIME_ITEM = ITEMS.register("sleeping_bag_lime", () -> new LoreBlock(SLEEPING_BAG_LIME.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_MAGENTA_ITEM = ITEMS.register("sleeping_bag_magenta", () -> new LoreBlock(SLEEPING_BAG_MAGENTA.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_ORANGE_ITEM = ITEMS.register("sleeping_bag_orange", () -> new LoreBlock(SLEEPING_BAG_ORANGE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_PINK_ITEM = ITEMS.register("sleeping_bag_pink", () -> new LoreBlock(SLEEPING_BAG_PINK.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_PURPLE_ITEM = ITEMS.register("sleeping_bag_purple", () -> new LoreBlock(SLEEPING_BAG_PURPLE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_WHITE_ITEM = ITEMS.register("sleeping_bag_white", () -> new LoreBlock(SLEEPING_BAG_WHITE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_YELLOW_ITEM = ITEMS.register("sleeping_bag_yellow", () -> new LoreBlock(SLEEPING_BAG_YELLOW.get(), new Item.Properties().tab(decoGroup)).setHasLore());

    // Quest
    public static RegistryObject<Item> ITEM_BERYL = ITEMS.register("item_beryl", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FORTRESSMAP = ITEMS.register("item_fortressmap", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART = ITEMS.register("item_watcherheart", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART_CRACKED = ITEMS.register("item_watcherheart_cracked", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_KEYSTONE = ITEMS.register("item_keystone", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSADDLE = ITEMS.register("item_darksaddle", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_ARTIFACT = ITEMS.register("item_artifact", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_BLANKPAPER = ITEMS.register("item_blankpaper", () -> new LoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYARMOR = ITEMS.register("item_fancyarmor", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYCLOTH = ITEMS.register("item_fancycloth", () -> new LoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHAMMER = ITEMS.register("item_fancyhammer", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHELM = ITEMS.register("item_fancyhelm", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYKEY = ITEMS.register("item_fancykey", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYPICK = ITEMS.register("item_fancypick", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD = ITEMS.register("item_fancyshield", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD2 = ITEMS.register("item_fancyshield2", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD = ITEMS.register("item_fancysword", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD2 = ITEMS.register("item_fancysword2", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LETTER = ITEMS.register("item_letter", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL = ITEMS.register("item_scroll", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL2 = ITEMS.register("item_scroll2", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_SPECIALFLOWER = ITEMS.register("item_specialflower", () -> new LoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK = ITEMS.register("item_storybook", () -> new LoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK2 = ITEMS.register("item_storybook2", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK3 = ITEMS.register("item_storybook3", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK4 = ITEMS.register("item_storybook4", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNARMOR = ITEMS.register("item_wornarmor", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNHELM = ITEMS.register("item_wornhelm", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNKEY = ITEMS.register("item_wornkey", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNPICK = ITEMS.register("item_wornpick", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD = ITEMS.register("item_wornshield", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD2 = ITEMS.register("item_wornshield2", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSWORD = ITEMS.register("item_wornsword", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WOVENBASKET = ITEMS.register("item_wovenbasket", () -> new LoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WRITTENPAPER = ITEMS.register("item_writtenpaper", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_PUNGENTHERB = ITEMS.register("item_pungentherb", () -> new LoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LOCKPICK = ITEMS.register("item_lockpick", () -> new LoreItem(new Item.Properties().stacksTo(16).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BROKENSWORD = ITEMS.register("item_brokensword", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_REFORGEDSWORD = ITEMS.register("item_reforgedsword", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_MAGIC_CLOTH = ITEMS.register("item_magic_cloth", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_KEYFRAGMENT = ITEMS.register("item_keyfragment", () -> new LoreItem(new Item.Properties().stacksTo(2).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_OILYKEY = ITEMS.register("item_oilykey", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_MITHRILNUGGET = ITEMS.register("item_mithrilnugget", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_REMAINS = ITEMS.register("item_remains", () -> new LoreItem(new Item.Properties().stacksTo(16).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_RUNE_STONE = ITEMS.register("item_rune_stone", () -> new LoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());

    // Dev Tools
    public static RegistryObject<Item> ITEM_DEV_TOOL = ITEMS.register("item_dev_tool", () -> new LoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_DEV_DEBUG_TOOL = ITEMS.register("item_dev_debug_tool", () -> new LoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());

    // Backpack Upgrades
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_BASE = ITEMS.register("upgrade_item_backpack_upgrade_base", () -> new LoreItem(new Item.Properties().stacksTo(5).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_SIZE = ITEMS.register("item_backpack_upgrade_size", () -> new LoreItem(new Item.Properties().stacksTo(2).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_FLUID = ITEMS.register("item_backpack_upgrade_fluid", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_CRAFTING = ITEMS.register("item_backpack_upgrade_crafting", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_SLEEPING = ITEMS.register("item_backpack_upgrade_sleeping", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_CAMPFIRE = ITEMS.register("item_backpack_upgrade_campfire", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());

    // Metals & Gems
    public static RegistryObject<Item> DUST_MITHRIL = ITEMS.register("dust_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> NUGGET_MITHRIL = ITEMS.register("nugget_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> INGOT_MITHRIL = ITEMS.register("ingot_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> DUST_MORGULIRON = ITEMS.register("dust_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> NUGGET_MORGULIRON = ITEMS.register("nugget_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> INGOT_MORGULIRON = ITEMS.register("ingot_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> GEM_AMMOLITE = ITEMS.register("gem_ammolite", () -> new LoreItem(new Item.Properties().stacksTo(16).tab(matsGroup)).setEffectOverride());

    // Equipment & Armor
    public static RegistryObject<ArmorItem> HELMET_MITHRIL = ITEMS.register("helmet_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlot.HEAD, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> CHESTPLATE_MITHRIL = ITEMS.register("chestplate_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlot.CHEST, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> LEGGINGS_MITHRIL = ITEMS.register("leggings_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlot.LEGS, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> BOOTS_MITHRIL = ITEMS.register("boots_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlot.FEET, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> MITHRIL_HORSE_ARMOR = ITEMS.register("mithril_horse_armor", () -> new MithrilHorseArmor(15, "mithril", (new Item.Properties()).stacksTo(1).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> HELMET_MORGULIRON = ITEMS.register("helmet_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlot.HEAD, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> CHESTPLATE_MORGULIRON = ITEMS.register("chestplate_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlot.CHEST, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> LEGGINGS_MORGULIRON = ITEMS.register("leggings_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlot.LEGS, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> BOOTS_MORGULIRON = ITEMS.register("boots_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlot.FEET, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> MORGULIRON_HORSE_ARMOR = ITEMS.register("morguliron_horse_armor", () -> new MorgulironHorseArmor(13, "morguliron", (new Item.Properties()).stacksTo(1).tab(toolsGroup)));

    public static RegistryObject<AxeItem> AXE_MITHRIL = ITEMS.register("axe_mithril", () -> new AxeItem(TTMItemTier.MITHRIL, 8.0F, -1.5F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<HoeItem> HOE_MITHRIL = ITEMS.register("hoe_mithril", () -> new HoeItem(TTMItemTier.MITHRIL, 1, 0.8F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<PickaxeItem> PICKAXE_MITHRIL = ITEMS.register("pickaxe_mithril", () -> new PickaxeItem(TTMItemTier.MITHRIL, 1, -1.0F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ShovelItem> SHOVEL_MITHRIL = ITEMS.register("shovel_mithril", () -> new ShovelItem(TTMItemTier.MITHRIL, 2.8F, -1.0F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_MITHRIL = ITEMS.register("sword_mithril", () -> new SwordItem(TTMItemTier.MITHRIL, 7, -1.0F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<AxeItem> AXE_MORGULIRON = ITEMS.register("axe_morguliron", () -> new AxeItem(TTMItemTier.MORGULIRON, 8.0F, -1.5F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<HoeItem> HOE_MORGULIRON = ITEMS.register("hoe_morguliron", () -> new HoeItem(TTMItemTier.MORGULIRON, 1, 0.8F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<PickaxeItem> PICKAXE_MORGULIRON = ITEMS.register("pickaxe_morguliron", () -> new PickaxeItem(TTMItemTier.MORGULIRON, 1, -1.5F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ShovelItem> SHOVEL_MORGULIRON = ITEMS.register("shovel_morguliron", () -> new ShovelItem(TTMItemTier.MORGULIRON, 2.8F, -1.5F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_MORGULIRON = ITEMS.register("sword_morguliron", () -> new SwordItem(TTMItemTier.MORGULIRON, 8, -2.3F, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_WITCHKING = ITEMS.register("sword_witchking", () -> new TTMSword(TTMItemTier.MORGULIRON, 9, -0.5F, (new Item.Properties()).tab(toolsGroup)).setEffectOverride());
    public static RegistryObject<SwordItem> SWORD_URUK = ITEMS.register("sword_uruk", () -> new TTMSword(TTMItemTier.MORGULIRON, 11, -0.5F, (new Item.Properties()).tab(toolsGroup)).setEffectOverride());
    public static RegistryObject<SwordItem> WHIP_FIRE = ITEMS.register("whip_fire", () -> new TTMSword(TTMItemTier.MORGULIRON, 15, -0.5F, (new Item.Properties()).tab(toolsGroup)).setEffectOverride());
    public static RegistryObject<SwordItem> CLUB_WOODEN = ITEMS.register("club_wooden", () -> new TTMSword(TTMItemTier.MORGULIRON, 9, -0.5F, (new Item.Properties()).tab(toolsGroup)).setEffectOverride());
    public static RegistryObject<BlockItem> ITEM_BACKPACK = ITEMS.register("item_backpack", () -> new BackpackItem(BACKPACK.get(), (new Item.Properties()).stacksTo(1).tab(toolsGroup)));
    public static RegistryObject<BowItem> ELVEN_BOW = ITEMS.register("elven_bow", () -> new BowItem(new Item.Properties().stacksTo(1).tab(toolsGroup)));
    public static RegistryObject<BowItem> URUK_BOW = ITEMS.register("uruk_bow", () -> new BowItem(new Item.Properties().stacksTo(1).tab(toolsGroup)));
    public static RegistryObject<Item> COIN_POUCH = ITEMS.register("coin_pouch", () -> new CoinPouchItem(new Item.Properties().stacksTo(1).tab(toolsGroup)).setItemHasUse().setHasLore());
    public static RegistryObject<Item> KEY_RING = ITEMS.register("key_ring", () -> new KeyRingItem(new Item.Properties().stacksTo(1).tab(toolsGroup)).setItemHasUse().setHasLore());
    public static RegistryObject<Item> HYPE_HORN = ITEMS.register("hype_horn", () -> new HypeHornItem(new Item.Properties().stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> BRONZE_KEY = ITEMS.register("bronze_key", () -> new BronzeKeyItem(new Item.Properties().stacksTo(1).tab(questGroup), -1));
    public static RegistryObject<Item> SILVER_KEY = ITEMS.register("silver_key", () -> new SilverKeyItem(new Item.Properties().stacksTo(1).tab(questGroup), -1));
    public static RegistryObject<Item> GOLD_KEY = ITEMS.register("gold_key", () -> new GoldKeyItem(new Item.Properties().stacksTo(1).tab(questGroup), -1));
    public static RegistryObject<Item> MITHRIL_KEY = ITEMS.register("mithril_key", () -> new MithrilKeyItem(new Item.Properties().stacksTo(1).tab(questGroup), -1));
    public static RegistryObject<Item> MASTER_KEY = ITEMS.register("master_key", () -> new MasterKeyItem(new Item.Properties().stacksTo(1).tab(questGroup), -1));
    public static RegistryObject<Item> MORGUL_CRYSTAL = ITEMS.register("morgul_crystal", () -> new MorgulCrystalItem(new Item.Properties().stacksTo(1).tab(decoGroup)));

    // Trinkets
    public static RegistryObject<Item> TRINKET_AMULET = ITEMS.register("trinket_amulet", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_BELT = ITEMS.register("trinket_belt", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_CHARM = ITEMS.register("trinket_charm", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_RING = ITEMS.register("trinket_ring", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_GLOVE = ITEMS.register("trinket_glove", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_HAT = ITEMS.register("trinket_hat", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));
    public static RegistryObject<Item> TRINKET_CLOAK = ITEMS.register("trinket_cloak", () -> new TrinketItem(new Item.Properties().tab(toolsGroup)));

    // Projectiles
    public static RegistryObject<ArrowItem> GALADHRIM_ARROW = ITEMS.register("ammo_galadhrim_arrow", () -> new GaladhrimArrowItem((new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArrowItem> UTUMNO_ARROW = ITEMS.register("ammo_utumno_arrow", () -> new UtumnoArrowItem((new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> BOULDER = ITEMS.register("ammo_boulder", () -> new BoulderItem((new Item.Properties()).stacksTo(16).tab(toolsGroup)));
    public static RegistryObject<Item> FELLBEAST_FIREBALL = ITEMS.register("ammo_fellbeast_fireball", () -> new FellBeastFireballItem((new Item.Properties()).stacksTo(16).tab(toolsGroup)));

    // Boats
    public static RegistryObject<Item> MALLORN_BOAT = ITEMS.register("boat_mallorn", () -> new TolkienBoatItem(TolkienBoatEntity.Type.MALLORN, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> MIRKWOOD_BOAT = ITEMS.register("boat_mirkwood", () -> new TolkienBoatItem(TolkienBoatEntity.Type.MIRKWOOD, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> CULUMALDA_BOAT = ITEMS.register("boat_culumalda", () -> new TolkienBoatItem(TolkienBoatEntity.Type.CULUMALDA, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> LEBETHRON_BOAT = ITEMS.register("boat_lebethron", () -> new TolkienBoatItem(TolkienBoatEntity.Type.LEBETHRON, (new Item.Properties()).tab(toolsGroup)));

    // Coins & Tokens
    public static RegistryObject<Item> ITEM_COIN_BRONZE = ITEMS.register("item_coin_bronze", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_SILVER = ITEMS.register("item_coin_silver", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_GOLD = ITEMS.register("item_coin_gold", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_MITHRIL = ITEMS.register("item_coin_mithril", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSIGIL = ITEMS.register("item_darksigil", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONCOIN = ITEMS.register("item_coin1", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONTOKEN = ITEMS.register("item_coin2", () -> new LoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_CAVECOMPLETE = ITEMS.register("item_cavecomplete", () -> new LoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERCOMPLETE = ITEMS.register("item_watchercomplete", () -> new LoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_TOKEN_EASTERN_ALLIANCE = ITEMS.register("item_token_eastern_alliance", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_TOKEN_WESTERN_ALLIANCE = ITEMS.register("item_token_western_alliance", () -> new LoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());

    // Mob Drops
    public static RegistryObject<Item> CREBAIN_FEATHER = ITEMS.register("feather_crebain", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BIRD_FEATHER = ITEMS.register("feather_bird", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MUMAKIL_LEATHER = ITEMS.register("leather_mumakil", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MONSTER_FUR = ITEMS.register("monster_fur", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> BOTTLE_FANCY = ITEMS.register("bottle_fancy", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE = ITEMS.register("item_golem_stone", () -> new Item(new Item.Properties().stacksTo(16).tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_EARTH = ITEMS.register("item_golem_stone_earth", () -> new Item(new Item.Properties().stacksTo(16).tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_AIR = ITEMS.register("item_golem_stone_air", () -> new Item(new Item.Properties().stacksTo(16).tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_FIRE = ITEMS.register("item_golem_stone_fire", () -> new Item(new Item.Properties().stacksTo(16).tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_WATER = ITEMS.register("item_golem_stone_water", () -> new Item(new Item.Properties().stacksTo(16).tab(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_SUMMON = ITEMS.register("item_golem_stone_summon", () -> new LoreItem(new Item.Properties().stacksTo(16).tab(spawnGroup)).setEffectOverride().setHasLore().setItemHasUse().setSpawnInfo());

    //#################################################################
    // Record Items
    //#################################################################
    // Sounds - Music Disc
    public static RegistryObject<Item> RECORD_RIVENDELL = ITEMS.register("record_rivendell", () -> new TTMRecord(1, ridersofrivendell, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_LOTHLORIEN = ITEMS.register("record_lothlorien", () -> new TTMRecord(2, thelightoflothlorien, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_EREBOR = ITEMS.register("record_erebor", () -> new TTMRecord(3, allthatglittersinerebor, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_WILLOW = ITEMS.register("record_willow", () -> new TTMRecord(4, willowsong, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_MINASTIRITH = ITEMS.register("record_minastirith", () -> new TTMRecord(5, minastirith, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_EDORAS = ITEMS.register("record_edoras", () -> new TTMRecord(6, wakeofedoras, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_WBATTLE = ITEMS.register("record_wbattle", () -> new TTMRecord(7, witchbattle, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_MURDERFROG = ITEMS.register("record_murderfrog", () -> new TTMRecord(8, murderfrog, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_REDER = ITEMS.register("record_reder", () -> new TTMRecord(9, rederssong, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_FUMBLE = ITEMS.register("record_fumble", () -> new TTMRecord(10, trollfumble, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_BOMBADIL = ITEMS.register("record_bombadil", () -> new TTMRecord(11, mysteryoftombombadil, (new Item.Properties()).stacksTo(1).tab(questGroup)));
    public static RegistryObject<Item> RECORD_HOBBITS = ITEMS.register("record_hobbits", () -> new TTMRecord(12, concerninghobbits, (new Item.Properties()).stacksTo(1).tab(questGroup)));

    //#################################################################
    // Food & Drink Items
    //#################################################################
    // Drinks
    public static RegistryObject<Item> DRINK_ENT_DRAUGHT = ITEMS.register("drink_ent_draught", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.DRINK_ENT_DRAUGHT)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_PERSONAL_BLACKSMITH = ITEMS.register("drink_personal_blacksmith", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.DRINK_PERSONAL_BLACKSMITH)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ELF_FLEETFOOT = ITEMS.register("drink_elf_blessing", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.DRINK_ELF_NIMBLENESS)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ELF_VITALITY = ITEMS.register("drink_elf_vitality", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.DRINK_ELF_VITALITY)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ERU_BLESSING = ITEMS.register("drink_eru_blessing", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.DRINK_ERU_BLESSING)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> MIRUVOR = ITEMS.register("drink_miruvor", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.MIRUVOR)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> GROG = ITEMS.register("drink_grog", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(16).food(TTMFoods.GROG)).setEffectOverride().setItemUseAction(true));

    // Food
    public static RegistryObject<Item> LEMBAS = ITEMS.register("food_lembas", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.LEMBAS)).setEffectOverride());
    public static RegistryObject<Item> HONEY_CAKE = ITEMS.register("food_honeycake", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.HONEY_CAKE)));
    public static RegistryObject<Item> CRAM = ITEMS.register("food_cram", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.CRAM)));
    public static RegistryObject<Item> MONSTER_FLESH = ITEMS.register("monster_flesh", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.MONSTER_FLESH)));
    public static RegistryObject<Item> INSECT = ITEMS.register("food_insect", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.INSECT)));
    public static RegistryObject<Item> GOLDEN_INSECT = ITEMS.register("food_golden_insect", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.GOLDEN_INSECT)).setEffectOverride());
    public static RegistryObject<Item> TREE_ACORN = ITEMS.register("food_tree_acorn", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.TREE_ACORN)));
    public static RegistryObject<Item> GOLDEN_TREE_ACORN = ITEMS.register("food_golden_tree_acorn", () -> new TTMFood(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.GOLDEN_TREE_ACORN)).setEffectOverride());
    public static RegistryObject<Item> FOOD_HONEY = ITEMS.register("food_honey", () -> new Item(new Item.Properties().tab(foodGroup).stacksTo(64).food(TTMFoods.FOOD_HONEY)));

    // Crops
    public static RegistryObject<Item> PIPEWEED_ITEM = ITEMS.register("pipeweed", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> PIPEWEED_SEEDS = ITEMS.register("pipeweed_seeds", () -> new ItemBlockBCore(PIPEWEED.get(), new Item.Properties().tab(matsGroup)));

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Items";
    }
}
