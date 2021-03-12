package com.greatorator.tolkienmobs;

import codechicken.lib.gui.SimpleItemGroup;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.greatorator.tolkienmobs.block.BlockMushrooms;
import com.greatorator.tolkienmobs.block.BlockStonePath;
import com.greatorator.tolkienmobs.block.BlockTMHallowed;
import com.greatorator.tolkienmobs.block.trees.*;
import com.greatorator.tolkienmobs.datagen.*;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.item.trinket.TrinketAmulet;
import com.greatorator.tolkienmobs.item.trinket.TrinketBelt;
import com.greatorator.tolkienmobs.item.trinket.TrinketCharm;
import com.greatorator.tolkienmobs.item.trinket.TrinketRing;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.datagen.SoundGenerator.*;

/**
 * Created by brandon3055 on 31/1/21
 */
public class TTMContent {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static ItemGroup toolsGroup = new SimpleItemGroup("tolkienmobs.tools", () -> new ItemStack(TTMContent.PICKAXE_MITHRIL.get()));
    public static ItemGroup matsGroup = new SimpleItemGroup("tolkienmobs.mats", () -> new ItemStack(TTMContent.INGOT_MITHRIL.get()));
    public static ItemGroup spawnGroup = new SimpleItemGroup("tolkienmobs.spawn", () -> new ItemStack(TTMContent.GOLEM_STONE_SUMMON.get()));
    public static ItemGroup foodGroup = new SimpleItemGroup("tolkienmobs.food", () -> new ItemStack(TTMContent.LEMBAS.get()));
    public static ItemGroup questGroup = new SimpleItemGroup("tolkienmobs.quest", () -> new ItemStack(TTMContent.ITEM_FORTRESSMAP.get()));
    public static ItemGroup signsGroup = new SimpleItemGroup("tolkienmobs.signs", () -> new ItemStack(TTMContent.BLOCK_MITHRIL.get()));

    public static void init() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("Creating the light of the Valar in the land of Arda...");
        PotionGenerator.EFFECTS.register(modBus);
        PotionGenerator.POTIONS.register(modBus);
        EnchantmentGenerator.ENCHANTS.register(modBus);
        LOGGER.info("Asking the Ainur to sing the music of Eru Iluvatar...");
        SoundGenerator.SOUND_EVENTS.register(modBus);
        LOGGER.info("Preparing the Dwarves...");
        BLOCKS.register(modBus);
        LOGGER.info("Stocking the markets...");
        ITEMS.register(modBus);
        TILE.register(modBus);
        CONTAINER.register(modBus);
        LOGGER.info("Populating the peoples of Middle-earth...");
        EntityGenerator.ENTITY.register(modBus);
        EntityGenerator.SPAWN_EGGS.register(modBus);
        LOGGER.info("Setting the task master to work...");
        ProfessionGenerator.PROFESSION.register(modBus);
        ProfessionGenerator.POIT.register(modBus);
    }

    //#################################################################
    // Blocks
    //#################################################################
    // Basic - Metals & Gems
    public static RegistryObject<Block> ORE_MITHRIL = BLOCKS.register("ore_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_MITHRIL = BLOCKS.register("ore_end_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_MITHRIL = BLOCKS.register("ore_nether_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> BLOCK_MITHRIL = BLOCKS.register("block_mithril", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<PaneBlock> MITHRIL_BARS = BLOCKS.register("mithril_bars", () -> new PaneBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY_TERRACOTTA).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
    public static RegistryObject<DoorBlock> DOOR_MITHRIL = BLOCKS.register("door_mithril", () -> new DoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY_TERRACOTTA).setRequiresTool().hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
    public static RegistryObject<Block> ORE_MORGULIRON = BLOCKS.register("ore_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_MORGULIRON = BLOCKS.register("ore_end_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_MORGULIRON = BLOCKS.register("ore_nether_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> BLOCK_MORGULIRON = BLOCKS.register("block_morguliron", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<PaneBlock> MORGULIRON_BARS = BLOCKS.register("morguliron_bars", () -> new PaneBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.AIR).setRequiresTool().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
    public static RegistryObject<DoorBlock> DOOR_MORGULIRON = BLOCKS.register("door_morguliron", () -> new DoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK_TERRACOTTA).setRequiresTool().hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
    public static RegistryObject<Block> ORE_AMMOLITE = BLOCKS.register("ore_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_END_AMMOLITE = BLOCKS.register("ore_end_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));
    public static RegistryObject<Block> ORE_NETHER_AMMOLITE = BLOCKS.register("ore_nether_ammolite", () -> new Block(AbstractBlock.Properties.create(Material.IRON)));

    // Basic - Wood & Foliage
    public static RegistryObject<RotatedPillarBlock> LOG_CULUMALDA = BLOCKS.register("log_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.GRAY_TERRACOTTA));
    public static RegistryObject<RotatedPillarBlock> LOG_LEBETHRON = BLOCKS.register("log_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA));
    public static RegistryObject<RotatedPillarBlock> LOG_MALLORN = BLOCKS.register("log_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.WHITE_TERRACOTTA));
    public static RegistryObject<RotatedPillarBlock> LOG_MIRKWOOD = BLOCKS.register("log_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.BLACK_TERRACOTTA));
    public static RegistryObject<RotatedPillarBlock> LOG_DEADWOOD = BLOCKS.register("log_deadwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.ADOBE));
    public static RegistryObject<Block> PLANKS_CULUMALDA = BLOCKS.register("planks_culumalda", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_LEBETHRON = BLOCKS.register("planks_lebethron", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MALLORN = BLOCKS.register("planks_mallorn", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MIRKWOOD = BLOCKS.register("planks_mirkwood", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairsBlock> STAIRS_CULUMALDA = BLOCKS.register("stairs_culumalda", () -> new StairsBlock(PLANKS_CULUMALDA.get().getDefaultState(), AbstractBlock.Properties.from(PLANKS_CULUMALDA.get())));
    public static RegistryObject<StairsBlock> STAIRS_LEBETHRON = BLOCKS.register("stairs_lebethron", () -> new StairsBlock(PLANKS_LEBETHRON.get().getDefaultState(), AbstractBlock.Properties.from(PLANKS_LEBETHRON.get())));
    public static RegistryObject<StairsBlock> STAIRS_MIRKWOOD = BLOCKS.register("stairs_mirkwood", () -> new StairsBlock(PLANKS_MIRKWOOD.get().getDefaultState(), AbstractBlock.Properties.from(PLANKS_MIRKWOOD.get())));
    public static RegistryObject<StairsBlock> STAIRS_MALLORN = BLOCKS.register("stairs_mallorn", () -> new StairsBlock(PLANKS_MALLORN.get().getDefaultState(), AbstractBlock.Properties.from(PLANKS_MALLORN.get())));
    public static RegistryObject<SlabBlock> SLAB_MALLORN = BLOCKS.register("slab_mallorn", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_MIRKWOOD = BLOCKS.register("slab_mirkwood", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_LEBETHRON = BLOCKS.register("slab_lebethron", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_CULUMALDA = BLOCKS.register("slab_culumalda", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_MALLORN = BLOCKS.register("door_mallorn", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MALLORN.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
    public static RegistryObject<DoorBlock> DOOR_MIRKWOOD = BLOCKS.register("door_mirkwood", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MIRKWOOD.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
    public static RegistryObject<DoorBlock> DOOR_CULUMALDA = BLOCKS.register("door_culumalda", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_CULUMALDA.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
    public static RegistryObject<DoorBlock> DOOR_LEBETHRON = BLOCKS.register("door_lebethron", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_LEBETHRON.get().getMaterialColor()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MALLORN = BLOCKS.register("fence_gate_mallorn", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MALLORN.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MIRKWOOD = BLOCKS.register("fence_gate_mirkwood", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MIRKWOOD.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_CULUMALDA = BLOCKS.register("fence_gate_culumalda", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_CULUMALDA.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_LEBETHRON = BLOCKS.register("fence_gate_lebethron", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_LEBETHRON.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MALLORN = BLOCKS.register("fence_mallorn", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MALLORN.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MIRKWOOD = BLOCKS.register("fence_mirkwood", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_MIRKWOOD.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_CULUMALDA = BLOCKS.register("fence_culumalda", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_CULUMALDA.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_LEBETHRON = BLOCKS.register("fence_lebethron", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, PLANKS_LEBETHRON.get().getMaterialColor()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<LeavesBlock> LEAVES_CULUMALDA = BLOCKS.register("leaves_culumalda", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_LEBETHRON = BLOCKS.register("leaves_lebethron", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_MALLORN = BLOCKS.register("leaves_mallorn", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_MIRKWOOD = BLOCKS.register("leaves_mirkwood", TTMContent::createLeavesBlock);
    public static RegistryObject<SaplingBlock> SAPLING_MALLORN = BLOCKS.register("sapling_mallorn", () -> new SaplingBlock(new TTMMallornTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<SaplingBlock> SAPLING_MIRKWOOD = BLOCKS.register("sapling_mirkwood", () -> new SaplingBlock(new TTMMirkwoodTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<SaplingBlock> SAPLING_CULUMALDA = BLOCKS.register("sapling_culumalda", () -> new SaplingBlock(new TTMCulumaldaTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<SaplingBlock> SAPLING_LEBETHRON = BLOCKS.register("sapling_lebethron", () -> new SaplingBlock(new TTMLebethronTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<SaplingBlock> SAPLING_DEADWOOD = BLOCKS.register("sapling_deadwood", () -> new SaplingBlock(new TTMDeadTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));

    // Blocks - Plants & Flowers
    public static RegistryObject<Block> MUSHROOM_DECAY_BLOOM = BLOCKS.register("mushroom_decay_bloom", () -> new BlockMushrooms(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setLightLevel((state) -> {
        return 1;
    }).setNeedsPostProcessing(TTMContent::needsPostProcessing)));
    public static RegistryObject<Block> MUSHROOM_BLOOM_DECAY = BLOCKS.register("mushroom_bloom_decay", () -> new BlockMushrooms(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setLightLevel((state) -> {
        return 1;
    }).setNeedsPostProcessing(TTMContent::needsPostProcessing)));
    public static RegistryObject<Block> FLOWER_SIMBELMYNE = BLOCKS.register("flower_simbelmyne", () -> new FlowerBlock(Effects.HERO_OF_THE_VILLAGE, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_MIRKWOOD = BLOCKS.register("flower_mirkwood", () -> new FlowerBlock(Effects.BAD_OMEN, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_ALFIRIN = BLOCKS.register("flower_alfirin", () -> new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_ATHELAS = BLOCKS.register("flower_athelas", () -> new FlowerBlock(Effects.REGENERATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_NIPHREDIL = BLOCKS.register("flower_niphredil", () -> new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_SWAMPMILKWEED = BLOCKS.register("flower_swamp_milkweed", () -> new FlowerBlock(Effects.SLOWNESS, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static RegistryObject<Block> FLOWER_LILLYOFTHEVALLEY = BLOCKS.register("flower_valley_lilly", () -> new FlowerBlock(Effects.INSTANT_HEALTH, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));

    // Custom
    public static RegistryObject<Block> BLOCK_HALLOWED = BLOCKS.register("block_hallowed", () -> new BlockTMHallowed(AbstractBlock.Properties.create(Material.EARTH).sound(SoundType.GROUND).tickRandomly()));
    public static RegistryObject<Block> STONE_PATH = BLOCKS.register("block_stone_path", () -> new BlockStonePath(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).setBlocksVision(TTMContent::needsPostProcessing).setSuffocates(TTMContent::needsPostProcessing)));

    //#################################################################
    // Items
    //#################################################################
    // Blocks now require you to register their item separately. The item for a block should have the same registry name.
    // Blocks - Metals & Gems
    public static RegistryObject<Item> ORE_MITHRIL_ITEM = ITEMS.register("ore_mithril", () -> new ItemBlockBCore(ORE_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_MITHRIL_ITEM = ITEMS.register("ore_end_mithril", () -> new ItemBlockBCore(ORE_END_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MITHRIL_ITEM = ITEMS.register("ore_nether_mithril", () -> new ItemBlockBCore(ORE_NETHER_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BLOCK_MITHRIL_ITEM = ITEMS.register("block_mithril", () -> new ItemBlockBCore(BLOCK_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> MITHRIL_BARS_ITEM = ITEMS.register("mithril_bars", () -> new ItemBlockBCore(MITHRIL_BARS.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_MITHRIL_ITEM = ITEMS.register("door_mithril", () -> new ItemBlockBCore(DOOR_MITHRIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_MORGULIRON_ITEM = ITEMS.register("ore_morguliron", () -> new ItemBlockBCore(ORE_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_MORGULIRON_ITEM = ITEMS.register("ore_end_morguliron", () -> new ItemBlockBCore(ORE_END_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_MORGULIRON_ITEM = ITEMS.register("ore_nether_morguliron", () -> new ItemBlockBCore(ORE_NETHER_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> BLOCK_MORGULIRON_ITEM = ITEMS.register("block_morguliron", () -> new ItemBlockBCore(BLOCK_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> MORGULIRON_BARS_ITEM = ITEMS.register("morguliron_bars", () -> new ItemBlockBCore(MORGULIRON_BARS.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_MORGULIRON_ITEM = ITEMS.register("door_morguliron", () -> new ItemBlockBCore(DOOR_MORGULIRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_AMMOLITE_ITEM = ITEMS.register("ore_ammolite", () -> new ItemBlockBCore(ORE_AMMOLITE.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_END_AMMOLITE_ITEM = ITEMS.register("ore_end_ammolite", () -> new ItemBlockBCore(ORE_END_AMMOLITE.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> ORE_NETHER_AMMOLITE_ITEM = ITEMS.register("ore_nether_ammolite", () -> new ItemBlockBCore(ORE_NETHER_AMMOLITE.get(), new Item.Properties().group(matsGroup)));

    // Blocks - Wood & Foliage
    public static RegistryObject<Item> LOG_CULUMALDA_ITEM = ITEMS.register("log_culumalda", () -> new ItemBlockBCore(LOG_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_LEBETHRON_ITEM = ITEMS.register("log_lebethron", () -> new ItemBlockBCore(LOG_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_MALLORN_ITEM = ITEMS.register("log_mallorn", () -> new ItemBlockBCore(LOG_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_MIRKWOOD_ITEM = ITEMS.register("log_mirkwood", () -> new ItemBlockBCore(LOG_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LOG_DEADWOOD_ITEM = ITEMS.register("log_deadwood", () -> new ItemBlockBCore(LOG_DEADWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_CULUMALDA_ITEM = ITEMS.register("planks_culumalda", () -> new ItemBlockBCore(PLANKS_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_LEBETHRON_ITEM = ITEMS.register("planks_lebethron", () -> new ItemBlockBCore(PLANKS_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_MALLORN_ITEM = ITEMS.register("planks_mallorn", () -> new ItemBlockBCore(PLANKS_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> PLANKS_MIRKWOOD_ITEM = ITEMS.register("planks_mirkwood", () -> new ItemBlockBCore(PLANKS_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STAIRS_MALLORN_ITEM = ITEMS.register("stairs_mallorn", () -> new ItemBlockBCore(STAIRS_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STAIRS_MIRKWOOD_ITEM = ITEMS.register("stairs_mirkwood", () -> new ItemBlockBCore(STAIRS_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STAIRS_LEBETHRON_ITEM = ITEMS.register("stairs_lebethron", () -> new ItemBlockBCore(STAIRS_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STAIRS_CULUMALDA_ITEM = ITEMS.register("stairs_culumalda", () -> new ItemBlockBCore(STAIRS_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SLAB_MALLORN_ITEM = ITEMS.register("slab_mallorn", () -> new ItemBlockBCore(SLAB_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SLAB_MIRKWOOD_ITEM = ITEMS.register("slab_mirkwood", () -> new ItemBlockBCore(SLAB_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SLAB_CULUMALDA_ITEM = ITEMS.register("slab_culumalda", () -> new ItemBlockBCore(SLAB_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SLAB_LEBETHRON_ITEM = ITEMS.register("slab_lebethron", () -> new ItemBlockBCore(SLAB_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_MALLORN_ITEM = ITEMS.register("door_mallorn", () -> new ItemBlockBCore(DOOR_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_MIRKWOOD_ITEM = ITEMS.register("door_mirkwood", () -> new ItemBlockBCore(DOOR_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_CULUMALDA_ITEM = ITEMS.register("door_culumalda", () -> new ItemBlockBCore(DOOR_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DOOR_LEBETHRON_ITEM = ITEMS.register("door_lebethron", () -> new ItemBlockBCore(DOOR_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_GATE_MALLORN_ITEM = ITEMS.register("fence_gate_mallorn", () -> new ItemBlockBCore(FENCE_GATE_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_GATE_MIRKWOOD_ITEM = ITEMS.register("fence_gate_mirkwood", () -> new ItemBlockBCore(FENCE_GATE_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_GATE_CULUMALDA_ITEM = ITEMS.register("fence_gate_culumalda", () -> new ItemBlockBCore(FENCE_GATE_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_GATE_LEBETHRON_ITEM = ITEMS.register("fence_gate_lebethron", () -> new ItemBlockBCore(FENCE_GATE_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_MALLORN_ITEM = ITEMS.register("fence_mallorn", () -> new ItemBlockBCore(FENCE_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_MIRKWOOD_ITEM = ITEMS.register("fence_mirkwood", () -> new ItemBlockBCore(FENCE_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_CULUMALDA_ITEM = ITEMS.register("fence_culumalda", () -> new ItemBlockBCore(FENCE_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FENCE_LEBETHRON_ITEM = ITEMS.register("fence_lebethron", () -> new ItemBlockBCore(FENCE_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_CULUMALDA_ITEM = ITEMS.register("leaves_culumalda", () -> new ItemBlockBCore(LEAVES_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_LEBETHRON_ITEM = ITEMS.register("leaves_lebethron", () -> new ItemBlockBCore(LEAVES_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_MALLORN_ITEM = ITEMS.register("leaves_mallorn", () -> new ItemBlockBCore(LEAVES_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> LEAVES_MIRKWOOD_ITEM = ITEMS.register("leaves_mirkwood", () -> new ItemBlockBCore(LEAVES_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SAPLING_CULUMALDA_ITEM = ITEMS.register("sapling_culumalda", () -> new ItemBlockBCore(SAPLING_CULUMALDA.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SAPLING_LEBETHRON_ITEM = ITEMS.register("sapling_lebethron", () -> new ItemBlockBCore(SAPLING_LEBETHRON.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SAPLING_MALLORN_ITEM = ITEMS.register("sapling_mallorn", () -> new ItemBlockBCore(SAPLING_MALLORN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SAPLING_MIRKWOOD_ITEM = ITEMS.register("sapling_mirkwood", () -> new ItemBlockBCore(SAPLING_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> SAPLING_DEADWOOD_ITEM = ITEMS.register("sapling_deadwood", () -> new ItemBlockBCore(SAPLING_DEADWOOD.get(), new Item.Properties().group(matsGroup)));

    // Blocks - Plants & Flowers
    public static RegistryObject<Item> MUSHROOM_DECAY_BLOOM_ITEM = ITEMS.register("mushroom_decay_bloom", () -> new ItemBlockBCore(MUSHROOM_DECAY_BLOOM.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> MUSHROOM_BLOOM_DECAY_ITEM = ITEMS.register("mushroom_bloom_decay", () -> new ItemBlockBCore(MUSHROOM_BLOOM_DECAY.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_SIMBELMYNE_ITEM = ITEMS.register("flower_simbelmyne", () -> new ItemBlockBCore(FLOWER_SIMBELMYNE.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_MIRKWOOD_ITEM = ITEMS.register("flower_mirkwood", () -> new ItemBlockBCore(FLOWER_MIRKWOOD.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_ALFIRIN_ITEM = ITEMS.register("flower_alfirin", () -> new ItemBlockBCore(FLOWER_ALFIRIN.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_ATHELAS_ITEM = ITEMS.register("flower_athelas", () -> new ItemBlockBCore(FLOWER_ATHELAS.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_NIPHREDIL_ITEM = ITEMS.register("flower_niphredil", () -> new ItemBlockBCore(FLOWER_NIPHREDIL.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_SWAMPMILKWEED_ITEM = ITEMS.register("flower_swamp_milkweed", () -> new ItemBlockBCore(FLOWER_SWAMPMILKWEED.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> FLOWER_LILLYOFTHEVALLEY_ITEM = ITEMS.register("flower_valley_lilly", () -> new ItemBlockBCore(FLOWER_LILLYOFTHEVALLEY.get(), new Item.Properties().group(matsGroup)));

    // Blocks - Custom
    public static RegistryObject<Item> BLOCK_HALLOWED_ITEM = ITEMS.register("block_hallowed", () -> new ItemBlockBCore(BLOCK_HALLOWED.get(), new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> STONE_PATH_ITEM = ITEMS.register("block_stone_path", () -> new ItemBlockBCore(STONE_PATH.get(), new Item.Properties().group(matsGroup)));

    // Quest
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

    // Metals & Gems
    public static RegistryObject<Item> DUST_MITHRIL = ITEMS.register("dust_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> NUGGET_MITHRIL = ITEMS.register("nugget_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> INGOT_MITHRIL = ITEMS.register("ingot_mithril", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> DUST_MORGULIRON = ITEMS.register("dust_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> NUGGET_MORGULIRON = ITEMS.register("nugget_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> INGOT_MORGULIRON = ITEMS.register("ingot_morguliron", () -> new Item(new Item.Properties().group(matsGroup)));
    public static RegistryObject<Item> GEM_AMMOLITE = ITEMS.register("gem_ammolite", () -> new TTMLore(new Item.Properties().maxStackSize(16).group(matsGroup)).setEffectOverride());

    // Equipment & Armor
    public static RegistryObject<AxeItem> AXE_MITHRIL = ITEMS.register("axe_mithril", () -> new AxeItem(TTMItemTier.MITHRIL, 8.0F, -1.5F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<HoeItem> HOE_MITHRIL = ITEMS.register("hoe_mithril", () -> new HoeItem(TTMItemTier.MITHRIL, 1, 0.8F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<PickaxeItem> PICKAXE_MITHRIL = ITEMS.register("pickaxe_mithril", () -> new PickaxeItem(TTMItemTier.MITHRIL, 1, -1.0F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<ShovelItem> SHOVEL_MITHRIL = ITEMS.register("shovel_mithril", () -> new ShovelItem(TTMItemTier.MITHRIL, 2.8F, -1.0F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_MITHRIL = ITEMS.register("sword_mithril", () -> new SwordItem(TTMItemTier.MITHRIL, 7, -1.0F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<AxeItem> AXE_MORGULIRON = ITEMS.register("axe_morguliron", () -> new AxeItem(TTMItemTier.MORGULIRON, 8.0F, -1.5F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<HoeItem> HOE_MORGULIRON = ITEMS.register("hoe_morguliron", () -> new HoeItem(TTMItemTier.MORGULIRON, 1, 0.8F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<PickaxeItem> PICKAXE_MORGULIRON = ITEMS.register("pickaxe_morguliron", () -> new PickaxeItem(TTMItemTier.MORGULIRON, 1, -1.5F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<ShovelItem> SHOVEL_MORGULIRON = ITEMS.register("shovel_morguliron", () -> new ShovelItem(TTMItemTier.MORGULIRON, 2.8F, -1.5F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_MORGULIRON = ITEMS.register("sword_morguliron", () -> new SwordItem(TTMItemTier.MORGULIRON, 8, -2.3F, (new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<SwordItem> SWORD_WITCHKING = ITEMS.register("sword_witchking", () -> new TTMSword(TTMItemTier.MORGULIRON, 9, -0.5F, (new Item.Properties()).group(toolsGroup)).setEffectOverride());
    public static RegistryObject<SwordItem> WHIP_FIRE = ITEMS.register("whip_fire", () -> new TTM3DObj(TTMItemTier.MORGULIRON, 9, -0.5F, (new Item.Properties()).group(toolsGroup), "whip_fire.obj", "whip_fire.png").setEffectOverride());
    public static RegistryObject<SwordItem> CLUB_WOODEN = ITEMS.register("club_wooden", () -> new TTM3DObj(TTMItemTier.MORGULIRON, 9, -0.5F, (new Item.Properties()).group(toolsGroup), "club_wooden.obj", "club_wooden.png").setEffectOverride());

    //Trinkets
    public static RegistryObject<Item> TRINKET_AMULET = ITEMS.register("trinket_amulet", () -> new TrinketAmulet(new Item.Properties().group(toolsGroup)).setHasLore());
    public static RegistryObject<Item> TRINKET_BELT = ITEMS.register("trinket_belt", () -> new TrinketBelt(new Item.Properties().group(toolsGroup)).setHasLore());
    public static RegistryObject<Item> TRINKET_CHARM = ITEMS.register("trinket_charm", () -> new TrinketCharm(new Item.Properties().group(toolsGroup)).setHasLore());
    public static RegistryObject<Item> TRINKET_RING = ITEMS.register("trinket_ring", () -> new TrinketRing(new Item.Properties().group(toolsGroup)).setHasLore());

    //Projectiles
    public static RegistryObject<ArrowItem> GALADHRIM_ARROW = ITEMS.register("ammo_galadhrim_arrow", () -> new TTMArrow((new Item.Properties()).group(toolsGroup)));
    public static RegistryObject<Item> BOULDER = ITEMS.register("ammo_boulder", () -> new TTMAmmo((new Item.Properties()).maxStackSize(16).group(toolsGroup)));
    public static RegistryObject<Item> FELLBEAST_FIREBALL = ITEMS.register("ammo_fellbeast_fireball", () -> new TTMAmmo((new Item.Properties()).maxStackSize(16).group(toolsGroup)));

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
    // Record Items
    //#################################################################
    // Sounds - Music Disc
    public static RegistryObject<Item> RECORD_RIVENDELL = ITEMS.register("record_rivendell", () -> new TTMRecord(1, ridersofrivendell, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_LOTHLORIEN = ITEMS.register("record_lothlorien", () -> new TTMRecord(2, thelightoflothlorien, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_EREBOR = ITEMS.register("record_erebor", () -> new TTMRecord(3, allthatglittersinerebor, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_WILLOW = ITEMS.register("record_willow", () -> new TTMRecord(4, willowsong, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_MINASTIRITH = ITEMS.register("record_minastirith", () -> new TTMRecord(5, minastirith, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_EDORAS = ITEMS.register("record_edoras", () -> new TTMRecord(6, wakeofedoras, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_WBATTLE = ITEMS.register("record_wbattle", () -> new TTMRecord(7, witchbattle, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_MURDERFROG = ITEMS.register("record_murderfrog", () -> new TTMRecord(8, murderfrog, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_REDER = ITEMS.register("record_reder", () -> new TTMRecord(9, rederssong, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_FUMBLE = ITEMS.register("record_fumble", () -> new TTMRecord(10, trollfumble, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_BOMBADIL = ITEMS.register("record_bombadil", () -> new TTMRecord(11, mysteryoftombombadil, (new Item.Properties()).maxStackSize(1).group(questGroup)));
    public static RegistryObject<Item> RECORD_HOBBITS = ITEMS.register("record_hobbits", () -> new TTMRecord(12, concerninghobbits, (new Item.Properties()).maxStackSize(1).group(questGroup)));

    //#################################################################
    // Food & Drink Items
    //#################################################################
    // Drinks
    public static RegistryObject<Item> DRINK_ENT_DRAUGHT = ITEMS.register("drink_ent_draught", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.DRINK_ENT_DRAUGHT)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_PERSONAL_BLACKSMITH = ITEMS.register("drink_personal_blacksmith", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.DRINK_PERSONAL_BLACKSMITH)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ELF_FLEETFOOT = ITEMS.register("drink_elf_blessing", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.DRINK_ELF_NIMBLENESS)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ELF_VITALITY = ITEMS.register("drink_elf_vitality", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.DRINK_ELF_VITALITY)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> DRINK_ERU_BLESSING = ITEMS.register("drink_eru_blessing", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.DRINK_ERU_BLESSING)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> MIRUVOR = ITEMS.register("drink_miruvor", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.MIRUVOR)).setEffectOverride().setItemUseAction(true));
    public static RegistryObject<Item> GROG = ITEMS.register("drink_grog", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(16).food(TTMFoods.GROG)).setEffectOverride().setItemUseAction(true));

    // Food
    public static RegistryObject<Item> LEMBAS = ITEMS.register("food_lembas", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.LEMBAS)).setEffectOverride());
    public static RegistryObject<Item> HONEY_CAKE = ITEMS.register("food_honeycake", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.HONEY_CAKE)));
    public static RegistryObject<Item> CRAM = ITEMS.register("food_cram", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.CRAM)));
    public static RegistryObject<Item> MONSTER_FLESH = ITEMS.register("monster_flesh", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.MONSTER_FLESH)));
    public static RegistryObject<Item> INSECT = ITEMS.register("food_insect", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.INSECT)));
    public static RegistryObject<Item> GOLDEN_INSECT = ITEMS.register("food_golden_insect", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.GOLDEN_INSECT)).setEffectOverride());
    public static RegistryObject<Item> TREE_ACORN = ITEMS.register("food_tree_acorn", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.TREE_ACORN)));
    public static RegistryObject<Item> GOLDEN_TREE_ACORN = ITEMS.register("food_golden_tree_acorn", () -> new TTMFood(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.GOLDEN_TREE_ACORN)).setEffectOverride());
    public static RegistryObject<Item> FOOD_HONEY = ITEMS.register("food_honey", () -> new Item(new Item.Properties().group(foodGroup).maxStackSize(64).food(TTMFoods.FOOD_HONEY)));

    //#################################################################
    // Tile Entity Types
    //#################################################################
    //public static RegistryObject<TileEntityType<ExampleTile>> EXAMPLE_TILE = TILE.register("example_tile", () -> TileEntityType.Builder.create(ExampleTile::new, EXAMPLE_BLOCK.get()).build(null));

    //#################################################################
    // Containers
    //#################################################################
    //TODO Will get back to this when its needed. I need to figure out a better way to do this.
    //public static RegistryObject<ContainerType<ContainerBCTile<ExampleTile>>> EXAMPLE_CONTAINER = CONTAINER.register("example_container", (windowId, inv, data) -> new ContainerBCTile<ExampleTile>(EXAMPLE_CONTAINER.get(), windowId, inv, data, TRANSFUSER_LAYOUT));

    //For demonstration purposes only
    //public static class ExampleTile extends TileBCore {
    //    public ExampleTile() {
    //        super(EXAMPLE_TILE.get());
    //    }
    //}

    private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn(TTMContent::allowsSpawnOnLeaves).setSuffocates(TTMContent::isntSolid).setBlocksVision(TTMContent::isntSolid));
    }

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> {
            return state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }
}
