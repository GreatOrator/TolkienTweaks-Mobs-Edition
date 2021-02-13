package com.greatorator.tolkienmobs;

import codechicken.lib.gui.SimpleItemGroup;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.greatorator.tolkienmobs.block.BlockStonePath;
import com.greatorator.tolkienmobs.block.BlockTMHallowed;
import com.greatorator.tolkienmobs.handler.TTMLore;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Created by brandon3055 on 31/1/21
 */
public class TTMContent {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    public static ItemGroup toolsGroup = new SimpleItemGroup("tolkienmobs.tools", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup matsGroup = new SimpleItemGroup("tolkienmobs.mats", () -> new ItemStack(TTMContent.INGOT_MITHRIL.get()));
    public static ItemGroup spawnGroup = new SimpleItemGroup("tolkienmobs.spawn", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup foodGroup = new SimpleItemGroup("tolkienmobs.food", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));
    public static ItemGroup questGroup = new SimpleItemGroup("tolkienmobs.quest", () -> new ItemStack(TTMContent.ITEM_FORTRESSMAP.get()));
    public static ItemGroup signsGroup = new SimpleItemGroup("tolkienmobs.signs", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITY.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //#################################################################
    // Blocks
    //#################################################################
    // Basic - Metals & Gems
    public static RegistryObject<Block> ORE_MITHRIL = BLOCKS.register("ore_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_MITHRIL = BLOCKS.register("ore_end_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_MITHRIL = BLOCKS.register("ore_nether_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> BLOCK_MITHRIL = BLOCKS.register("block_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_MORGULIRON = BLOCKS.register("ore_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_MORGULIRON = BLOCKS.register("ore_end_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_MORGULIRON = BLOCKS.register("ore_nether_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> BLOCK_MORGULIRON = BLOCKS.register("block_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_AMMOLITE = BLOCKS.register("ore_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_AMMOLITE = BLOCKS.register("ore_end_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_AMMOLITE = BLOCKS.register("ore_nether_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));

    // Basic - Wood & Foliage
    public static RegistryObject<RotatedPillarBlock> LOG_CULUMALDA = BLOCKS.register("log_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN));
    public static RegistryObject<RotatedPillarBlock> LOG_LEBETHRON = BLOCKS.register("log_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN));
    public static RegistryObject<RotatedPillarBlock> LOG_MALLORN = BLOCKS.register("log_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN));
    public static RegistryObject<RotatedPillarBlock> LOG_MIRKWOOD = BLOCKS.register("log_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN));
    public static RegistryObject<Block> PLANKS_CULUMALDA = BLOCKS.register("planks_culumalda", () -> new Block(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<Block> PLANKS_LEBETHRON = BLOCKS.register("planks_lebethron", () -> new Block(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<Block> PLANKS_MALLORN = BLOCKS.register("planks_mallorn", () -> new Block(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<Block> PLANKS_MIRKWOOD = BLOCKS.register("planks_mirkwood", () -> new Block(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<Block> LEAVES_CULUMALDA = BLOCKS.register("leaves_culumalda", () -> new Block(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)));
    public static RegistryObject<Block> LEAVES_LEBETHRON = BLOCKS.register("leaves_lebethron", () -> new Block(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)));
    public static RegistryObject<Block> LEAVES_MALLORN = BLOCKS.register("leaves_mallorn", () -> new Block(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)));
    public static RegistryObject<Block> LEAVES_MIRKWOOD = BLOCKS.register("leaves_mirkwood", () -> new Block(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)));

    // Custom
    public static RegistryObject<Block> BLOCK_HALLOWED = BLOCKS.register("block_hallowed", () -> new BlockTMHallowed(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).tickRandomly()));
    public static RegistryObject<Block> STONE_PATH = BLOCKS.register("block_stone_path", () -> new BlockStonePath(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).setBlocksVision(TTMContent::needsPostProcessing).setSuffocates(TTMContent::needsPostProcessing)));

    //#################################################################
    // Quest Items
    //#################################################################
    public static RegistryObject<Item> ITEM_BERYL = ITEMS.register("item_beryl", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FORTRESSMAP = ITEMS.register("item_fortressmap", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART = ITEMS.register("item_watcherheart", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART_CRACKED = ITEMS.register("item_watcherheart_cracked", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_KEYSTONE = ITEMS.register("item_keystone", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSADDLE = ITEMS.register("item_darksaddle", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_ARTIFACT = ITEMS.register("item_artifact", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_BLANKPAPER = ITEMS.register("item_blankpaper", () -> new TTMLore(new Item.Properties().maxStackSize(12).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYARMOR = ITEMS.register("item_fancyarmor", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYCLOTH = ITEMS.register("item_fancycloth", () -> new TTMLore(new Item.Properties().maxStackSize(3).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHAMMER = ITEMS.register("item_fancyhammer", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHELM = ITEMS.register("item_fancyhelm", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYKEY = ITEMS.register("item_fancykey", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYPICK = ITEMS.register("item_fancypick", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD = ITEMS.register("item_fancyshield", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD2 = ITEMS.register("item_fancyshield2", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD = ITEMS.register("item_fancysword", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD2 = ITEMS.register("item_fancysword2", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LETTER = ITEMS.register("item_letter", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL = ITEMS.register("item_scroll", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL2 = ITEMS.register("item_scroll2", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_SPECIALFLOWER = ITEMS.register("item_specialflower", () -> new TTMLore(new Item.Properties().maxStackSize(12).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK = ITEMS.register("item_storybook", () -> new TTMLore(new Item.Properties().maxStackSize(12).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK2 = ITEMS.register("item_storybook2", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK3 = ITEMS.register("item_storybook3", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK4 = ITEMS.register("item_storybook4", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNARMOR = ITEMS.register("item_wornarmor", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNHELM = ITEMS.register("item_wornhelm", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNKEY = ITEMS.register("item_wornkey", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNPICK = ITEMS.register("item_wornpick", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD = ITEMS.register("item_wornshield", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD2 = ITEMS.register("item_wornshield2", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSWORD = ITEMS.register("item_wornsword", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WOVENBASKET = ITEMS.register("item_wovenbasket", () -> new TTMLore(new Item.Properties().maxStackSize(3).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WRITTENPAPER = ITEMS.register("item_writtenpaper", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_PUNGENTHERB = ITEMS.register("item_pungentherb", () -> new TTMLore(new Item.Properties().maxStackSize(3).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LOCKPICK = ITEMS.register("item_lockpick", () -> new TTMLore(new Item.Properties().maxStackSize(16).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BROKENSWORD = ITEMS.register("item_brokensword", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_REFORGEDSWORD = ITEMS.register("item_reforgedsword", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_MAGIC_CLOTH = ITEMS.register("item_magic_cloth", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_KEYFRAGMENT = ITEMS.register("item_keyfragment", () -> new TTMLore(new Item.Properties().maxStackSize(2).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_OILYKEY = ITEMS.register("item_oilykey", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_MITHRILNUGGET = ITEMS.register("item_mithrilnugget", () -> new TTMLore(new Item.Properties().maxStackSize(1).group(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_REMAINS = ITEMS.register("item_remains", () -> new TTMLore(new Item.Properties().maxStackSize(16).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_RUNE_STONE = ITEMS.register("item_rune_stone", () -> new TTMLore(new Item.Properties().maxStackSize(8).group(questGroup)).setEffectOverride().setHasLore());

    //#################################################################
    // Items
    //#################################################################
    // Blocks now require you to register their item separately. The item for a block should have the same registry name.
    // Blocks - Metals & Gems
    public static RegistryObject<Item> ORE_MITHRIL_ITEM = ITEMS.register("ore_mithril", () -> new ItemBlockBCore(ORE_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_MITHRIL_ITEM = ITEMS.register("ore_end_mithril", () -> new ItemBlockBCore(ORE_END_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MITHRIL_ITEM = ITEMS.register("ore_nether_mithril", () -> new ItemBlockBCore(ORE_NETHER_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BLOCK_MITHRIL_ITEM = ITEMS.register("block_mithril", () -> new ItemBlockBCore(BLOCK_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_MORGULIRON_ITEM = ITEMS.register("ore_morguliron", () -> new ItemBlockBCore(ORE_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_MORGULIRON_ITEM = ITEMS.register("ore_end_morguliron", () -> new ItemBlockBCore(ORE_END_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MORGULIRON_ITEM = ITEMS.register("ore_nether_morguliron", () -> new ItemBlockBCore(ORE_NETHER_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BLOCK_MORGULIRON_ITEM = ITEMS.register("block_morguliron", () -> new ItemBlockBCore(BLOCK_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_AMMOLITE_ITEM = ITEMS.register("ore_ammolite", () -> new ItemBlockBCore(ORE_AMMOLITE.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_AMMOLITE_ITEM = ITEMS.register("ore_end_ammolite", () -> new ItemBlockBCore(ORE_END_AMMOLITE.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_AMMOLITE_ITEM = ITEMS.register("ore_nether_ammolite", () -> new ItemBlockBCore(ORE_NETHER_AMMOLITE.get(), new Item.Properties().group(matsGroup)));

    // Blocks - Wood & Foliage
    public static RegistryObject<Item> LOG_CULUMALDA_ITEM = ITEMS.register("log_culumalda", () -> new ItemBlockBCore(LOG_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_LEBETHRON_ITEM = ITEMS.register("log_lebethron", () -> new ItemBlockBCore(LOG_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_MALLORN_ITEM = ITEMS.register("log_mallorn", () -> new ItemBlockBCore(LOG_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_MIRKWOOD_ITEM = ITEMS.register("log_mirkwood", () -> new ItemBlockBCore(LOG_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_CULUMALDA_ITEM = ITEMS.register("planks_culumalda", () -> new ItemBlockBCore(PLANKS_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_LEBETHRON_ITEM = ITEMS.register("planks_lebethron", () -> new ItemBlockBCore(PLANKS_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_MALLORN_ITEM = ITEMS.register("planks_mallorn", () -> new ItemBlockBCore(PLANKS_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_MIRKWOOD_ITEM = ITEMS.register("planks_mirkwood", () -> new ItemBlockBCore(PLANKS_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_CULUMALDA_ITEM = ITEMS.register("leaves_culumalda", () -> new ItemBlockBCore(LEAVES_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_LEBETHRON_ITEM = ITEMS.register("leaves_lebethron", () -> new ItemBlockBCore(LEAVES_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_MALLORN_ITEM = ITEMS.register("leaves_mallorn", () -> new ItemBlockBCore(LEAVES_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_MIRKWOOD_ITEM = ITEMS.register("leaves_mirkwood", () -> new ItemBlockBCore(LEAVES_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));

    // Blocks - Custom
    public static RegistryObject<Item> BLOCK_HALLOWED_ITEM = ITEMS.register("block_hallowed", () -> new ItemBlockBCore(BLOCK_HALLOWED.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STONE_PATH_ITEM = ITEMS.register("block_stone_path", () -> new ItemBlockBCore(STONE_PATH.get(), new Item.Properties().group(matsGroup)));

    // Metals & Gems
    public static RegistryObject<Item> DUST_MITHRIL = ITEMS.register("dust_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> NUGGET_MITHRIL = ITEMS.register("nugget_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> INGOT_MITHRIL = ITEMS.register("ingot_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DUST_MORGULIRON = ITEMS.register("dust_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> NUGGET_MORGULIRON = ITEMS.register("nugget_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> INGOT_MORGULIRON = ITEMS.register("ingot_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> GEM_AMMOLITE = ITEMS.register("gem_ammolite", () -> new TTMLore(new Item.Properties().maxStackSize(16).group(matsGroup)).setEffectOverride());

    // Coins & Tokens
    public static RegistryObject<Item> ITEM_COIN_BRONZE = ITEMS.register("item_coin_bronze", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_SILVER = ITEMS.register("item_coin_silver", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_GOLD = ITEMS.register("item_coin_gold", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_MITHRIL = ITEMS.register("item_coin_mithril", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSIGIL = ITEMS.register("item_darksigil", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONCOIN = ITEMS.register("item_coin1", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONTOKEN = ITEMS.register("item_coin2", () -> new TTMLore(new Item.Properties().group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_CAVECOMPLETE = ITEMS.register("item_cavecomplete", () -> new TTMLore(new Item.Properties().maxStackSize(3).group(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERCOMPLETE = ITEMS.register("item_watchercomplete", () -> new TTMLore(new Item.Properties().maxStackSize(3).group(questGroup)).setHasLore());

    // Mob Drops
    public static RegistryObject<Item> CREBAIN_FEATHER = ITEMS.register("feather_crebain", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BIRD_FEATHER = ITEMS.register("feather_bird", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> MUMAKIL_LEATHER = ITEMS.register("leather_mumakil", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> MONSTER_FUR = ITEMS.register("monster_fur", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BOTTLE_FANCY = ITEMS.register("bottle_fancy", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE = ITEMS.register("item_golem_stone", () -> new Item(new Item.Properties().maxStackSize(16).group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_EARTH = ITEMS.register("item_golem_stone_earth", () -> new Item(new Item.Properties().maxStackSize(16).group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_AIR = ITEMS.register("item_golem_stone_air", () -> new Item(new Item.Properties().maxStackSize(16).group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_FIRE = ITEMS.register("item_golem_stone_fire", () -> new Item(new Item.Properties().maxStackSize(16).group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_WATER = ITEMS.register("item_golem_stone_water", () -> new Item(new Item.Properties().maxStackSize(16).group(matsGroup)));
    public static RegistryObject<Item> GOLEM_STONE_SUMMON = ITEMS.register("item_golem_stone_summon", () -> new TTMLore(new Item.Properties().maxStackSize(16).group(matsGroup)).setEffectOverride().setHasLore());

    //#################################################################
    // Tile Entity Types
    //#################################################################

    //public static RegistryObject<TileEntityType<ExampleTile>> EXAMPLE_TILE = TILE.register("example_tile", () -> TileEntityType.Builder.create(ExampleTile::new, EXAMPLE_BLOCK.get()).build(null));

    //#################################################################
    // Containers
    //#################################################################

    //TODO Will get back to this when its needed. I need to figure out a better way to do this.
//    public static RegistryObject<ContainerType<ContainerBCTile<ExampleTile>>> EXAMPLE_CONTAINER = CONTAINER.register("example_container", (windowId, inv, data) -> new ContainerBCTile<ExampleTile>(EXAMPLE_CONTAINER.get(), windowId, inv, data, TRANSFUSER_LAYOUT));

    //#################################################################
    // Entities
    //#################################################################

    //public static RegistryObject<EntityType<PigEntity>> EXAMPLE_ENTITY = ENTITY.register("example_entity", () -> EntityType.Builder.create(PigEntity::new, EntityClassification.CREATURE).size(0.9F, 0.9F).trackingRange(10).build("example_entity"));



    //For demonstration purposes only
    //public static class ExampleTile extends TileBCore {
    //    public ExampleTile() {
    //        super(EXAMPLE_TILE.get());
    //    }
    //}

}
