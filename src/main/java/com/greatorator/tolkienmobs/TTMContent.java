package com.greatorator.tolkienmobs;

import codechicken.lib.gui.SimpleItemGroup;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.google.common.collect.Sets;
import com.greatorator.tolkienmobs.block.CropsBlock;
import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.container.*;
import com.greatorator.tolkienmobs.datagen.*;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import com.greatorator.tolkienmobs.entity.tile.*;
import com.greatorator.tolkienmobs.handler.*;
import com.greatorator.tolkienmobs.handler.enums.TTMArmorTier;
import com.greatorator.tolkienmobs.item.armor.MithrilArmor;
import com.greatorator.tolkienmobs.item.armor.MithrilHorseArmor;
import com.greatorator.tolkienmobs.item.armor.MorgulironArmor;
import com.greatorator.tolkienmobs.item.armor.MorgulironHorseArmor;
import com.greatorator.tolkienmobs.item.signs.CulumaldaSignItem;
import com.greatorator.tolkienmobs.item.signs.LebethronSignItem;
import com.greatorator.tolkienmobs.item.signs.MallornSignItem;
import com.greatorator.tolkienmobs.item.signs.MirkwoodSignItem;
import com.greatorator.tolkienmobs.item.tools.*;
import com.greatorator.tolkienmobs.world.trees.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;
import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.datagen.SoundGenerator.*;
import static com.greatorator.tolkienmobs.handler.TTMWoodTypes.*;

/**
 * Created by brandon3055 on 31/1/21
 */
public class TTMContent {
    public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(MODID, "block/fluid_still");
    public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(MODID, "block/fluid_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(MODID, "block/fluid_overlay");


    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static ItemGroup toolsGroup = new SimpleItemGroup("tolkienmobs.tools", () -> new ItemStack(TTMContent.PICKAXE_MITHRIL.get()));
    public static ItemGroup matsGroup = new SimpleItemGroup("tolkienmobs.mats", () -> new ItemStack(TTMContent.INGOT_MITHRIL.get()));
    public static ItemGroup decoGroup = new SimpleItemGroup("tolkienmobs.deco", () -> new ItemStack(TTMContent.PIGGYBANK_ITEM.get()));
    public static ItemGroup spawnGroup = new SimpleItemGroup("tolkienmobs.spawn", () -> new ItemStack(TTMContent.GOLEM_STONE_SUMMON.get()));
    public static ItemGroup foodGroup = new SimpleItemGroup("tolkienmobs.food", () -> new ItemStack(TTMContent.LEMBAS.get()));
    public static ItemGroup questGroup = new SimpleItemGroup("tolkienmobs.quest", () -> new ItemStack(TTMContent.ITEM_FORTRESSMAP.get()));

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
        FLUIDS.register(modBus);
        TILE.register(modBus);
        CONTAINER.register(modBus);
        RECIPE_SERIALIZER.register(modBus);
        LOGGER.info("Populating the peoples of Middle-earth...");
        EntityGenerator.ENTITY.register(modBus);
        EntityGenerator.SPAWN_EGGS.register(modBus);
        LOGGER.info("Setting the task master to work...");
        ProfessionGenerator.PROFESSION.register(modBus);
        ProfessionGenerator.POIT.register(modBus);
        LOGGER.info("Time to create the land...");
        BiomeGenerator.BIOMES.register(modBus);
        BiomeGenerator.FORGE_WORLD_TYPES.register(modBus);
        StructureGenerator.STRUCTURES.register(modBus);
        modBus.addGenericListener(ContainerType.class, TTMContent::registerContainers);
    }

    //#################################################################
    // Blocks
    //#################################################################
    // Metals & Gems
    public static RegistryObject<Block> ORE_MITHRIL = BLOCKS.register("ore_mithril", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_END_MITHRIL = BLOCKS.register("ore_end_mithril", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_NETHER_MITHRIL = BLOCKS.register("ore_nether_mithril", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> BLOCK_MITHRIL = BLOCKS.register("block_mithril", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<PaneBlock> MITHRIL_BARS = BLOCKS.register("mithril_bars", () -> new PaneBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_MITHRIL = BLOCKS.register("door_mithril", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MITHRIL = BLOCKS.register("trapdoor_mithril", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.METAL).strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<Block> PRESSURE_PLATE_MITHRIL = BLOCKS.register("pressure_plate_mithril", () -> new WeightedPressurePlateBlock(15, AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> ORE_MORGULIRON = BLOCKS.register("ore_morguliron", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_END_MORGULIRON = BLOCKS.register("ore_end_morguliron", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_NETHER_MORGULIRON = BLOCKS.register("ore_nether_morguliron", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> BLOCK_MORGULIRON = BLOCKS.register("block_morguliron", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<PaneBlock> MORGULIRON_BARS = BLOCKS.register("morguliron_bars", () -> new PaneBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_MORGULIRON = BLOCKS.register("door_morguliron", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MORGULIRON = BLOCKS.register("trapdoor_morguliron", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.METAL).strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<Block> PRESSURE_PLATE_MORGULIRON = BLOCKS.register("pressure_plate_morguliron", () -> new WeightedPressurePlateBlock(150, AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> ORE_AMMOLITE = BLOCKS.register("ore_ammolite", () -> new Block(AbstractBlock.Properties.of(Material.METAL)));
    public static RegistryObject<Block> ORE_END_AMMOLITE = BLOCKS.register("ore_end_ammolite", () -> new Block(AbstractBlock.Properties.of(Material.METAL)));
    public static RegistryObject<Block> ORE_NETHER_AMMOLITE = BLOCKS.register("ore_nether_ammolite", () -> new Block(AbstractBlock.Properties.of(Material.METAL)));
    public static RegistryObject<Block> BLOCK_AMMOLITE = BLOCKS.register("block_ammolite", () -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_LIGHT_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GLASS)));
    public static RegistryObject<DoorBlock> DOOR_DURIN = BLOCKS.register("door_durin", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));

    // Wood & Foliage
    public static RegistryObject<RotatedPillarBlock> LOG_MALLORN = BLOCKS.register("log_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<RotatedPillarBlock> LOG_MIRKWOOD = BLOCKS.register("log_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<RotatedPillarBlock> LOG_CULUMALDA = BLOCKS.register("log_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<RotatedPillarBlock> LOG_LEBETHRON = BLOCKS.register("log_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<RotatedPillarBlock> LOG_DEADWOOD = BLOCKS.register("log_deadwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.COLOR_ORANGE));
    public static RegistryObject<RotatedPillarBlock> WOOD_MALLORN = BLOCKS.register("wood_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<RotatedPillarBlock> WOOD_MIRKWOOD = BLOCKS.register("wood_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<RotatedPillarBlock> WOOD_CULUMALDA = BLOCKS.register("wood_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<RotatedPillarBlock> WOOD_LEBETHRON = BLOCKS.register("wood_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_MALLORN_LOG = BLOCKS.register("stripped_log_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_MIRKWOOD_LOG = BLOCKS.register("stripped_log_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_CULUMALDA_LOG = BLOCKS.register("stripped_log_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_LEBETHRON_LOG = BLOCKS.register("stripped_log_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_MALLORN_WOOD = BLOCKS.register("stripped_wood_mallorn", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_MIRKWOOD_WOOD = BLOCKS.register("stripped_wood_mirkwood", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_CULUMALDA_WOOD = BLOCKS.register("stripped_wood_culumalda", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<RotatedPillarBlock> STRIPPED_LEBETHRON_WOOD = BLOCKS.register("stripped_wood_lebethron", () -> createLogBlock(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<Block> PLANKS_CULUMALDA = BLOCKS.register("planks_culumalda", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_LEBETHRON = BLOCKS.register("planks_lebethron", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MALLORN = BLOCKS.register("planks_mallorn", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MIRKWOOD = BLOCKS.register("planks_mirkwood", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairsBlock> STAIRS_CULUMALDA = BLOCKS.register("stairs_culumalda", () -> new StairsBlock(PLANKS_CULUMALDA.get().defaultBlockState(), AbstractBlock.Properties.copy(PLANKS_CULUMALDA.get())));
    public static RegistryObject<StairsBlock> STAIRS_LEBETHRON = BLOCKS.register("stairs_lebethron", () -> new StairsBlock(PLANKS_LEBETHRON.get().defaultBlockState(), AbstractBlock.Properties.copy(PLANKS_LEBETHRON.get())));
    public static RegistryObject<StairsBlock> STAIRS_MIRKWOOD = BLOCKS.register("stairs_mirkwood", () -> new StairsBlock(PLANKS_MIRKWOOD.get().defaultBlockState(), AbstractBlock.Properties.copy(PLANKS_MIRKWOOD.get())));
    public static RegistryObject<StairsBlock> STAIRS_MALLORN = BLOCKS.register("stairs_mallorn", () -> new StairsBlock(PLANKS_MALLORN.get().defaultBlockState(), AbstractBlock.Properties.copy(PLANKS_MALLORN.get())));
    public static RegistryObject<SlabBlock> SLAB_MALLORN = BLOCKS.register("slab_mallorn", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_MIRKWOOD = BLOCKS.register("slab_mirkwood", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_LEBETHRON = BLOCKS.register("slab_lebethron", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD)));
    public static RegistryObject<SlabBlock> SLAB_CULUMALDA = BLOCKS.register("slab_culumalda", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_MALLORN = BLOCKS.register("door_mallorn", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_MIRKWOOD = BLOCKS.register("door_mirkwood", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_CULUMALDA = BLOCKS.register("door_culumalda", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_LEBETHRON = BLOCKS.register("door_lebethron", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MALLORN = BLOCKS.register("fence_gate_mallorn", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MIRKWOOD = BLOCKS.register("fence_gate_mirkwood", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_CULUMALDA = BLOCKS.register("fence_gate_culumalda", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_LEBETHRON = BLOCKS.register("fence_gate_lebethron", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MALLORN = BLOCKS.register("fence_mallorn", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MIRKWOOD = BLOCKS.register("fence_mirkwood", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_CULUMALDA = BLOCKS.register("fence_culumalda", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_LEBETHRON = BLOCKS.register("fence_lebethron", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MALLORN = BLOCKS.register("trapdoor_mallorn", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MIRKWOOD = BLOCKS.register("trapdoor_mirkwood", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_CULUMALDA = BLOCKS.register("trapdoor_culumalda", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_LEBETHRON = BLOCKS.register("trapdoor_lebethron", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TTMContent::never)));
    public static RegistryObject<Block> PRESSURE_PLATE_MALLORN = BLOCKS.register("pressure_plate_mallorn", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PRESSURE_PLATE_MIRKWOOD = BLOCKS.register("pressure_plate_mirkwood", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PRESSURE_PLATE_CULUMALDA = BLOCKS.register("pressure_plate_culumalda", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PRESSURE_PLATE_LEBETHRON = BLOCKS.register("pressure_plate_lebethron", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<TorchBlock> TORCH_MALLORN = BLOCKS.register("torch_mallorn", () -> new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    public static RegistryObject<TorchBlock> TORCH_MIRKWOOD = BLOCKS.register("torch_mirkwood", () -> new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.mirkwood_flame));
    public static RegistryObject<TorchBlock> TORCH_CULUMALDA = BLOCKS.register("torch_culumalda", () -> new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.culumalda_flame));
    public static RegistryObject<TorchBlock> TORCH_LEBETHRON = BLOCKS.register("torch_lebethron", () -> new TorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.lebethron_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_MALLORN = BLOCKS.register("wall_torch_mallorn", () -> new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));
    public static RegistryObject<TorchBlock> WALL_TORCH_MIRKWOOD = BLOCKS.register("wall_torch_mirkwood", () -> new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.mirkwood_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_CULUMALDA = BLOCKS.register("wall_torch_culumalda", () -> new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.culumalda_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_LEBETHRON = BLOCKS.register("wall_torch_lebethron", () -> new WallTorchBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TTMParticles.lebethron_flame));

    public static RegistryObject<Block> MALLORN_SIGN = BLOCKS.register("sign_mallorn", () -> new MallornStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), MALLORN));
    public static RegistryObject<Block> MIRKWOOD_SIGN = BLOCKS.register("sign_mirkwood", () -> new MirkwoodStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), MIRKWOOD));
    public static RegistryObject<Block> CULUMALDA_SIGN = BLOCKS.register("sign_culumalda", () -> new CulumaldaStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), CULUMALDA));
    public static RegistryObject<Block> LEBETHRON_SIGN = BLOCKS.register("sign_lebethron", () -> new LebethronStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), LEBETHRON));
    public static RegistryObject<Block> MALLORN_BUTTON = BLOCKS.register("mallorn_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> MIRKWOOD_BUTTON = BLOCKS.register("mirkwood_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> CULUMALDA_BUTTON = BLOCKS.register("culumalda_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> LEBETHRON_BUTTON = BLOCKS.register("lebethron_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> MALLORN_WALL_SIGN = BLOCKS.register("wall_sign_mallorn", () -> new MallornSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(MALLORN_SIGN.get()), MALLORN));
    public static RegistryObject<Block> MIRKWOOD_WALL_SIGN = BLOCKS.register("wall_sign_mirkwood", () -> new MirkwoodSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(MIRKWOOD_SIGN.get()), MIRKWOOD));
    public static RegistryObject<Block> CULUMALDA_WALL_SIGN = BLOCKS.register("wall_sign_culumalda", () -> new CulumaldaSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CULUMALDA_SIGN.get()), CULUMALDA));
    public static RegistryObject<Block> LEBETHRON_WALL_SIGN = BLOCKS.register("wall_sign_lebethron", () -> new LebethronSignBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(LEBETHRON_SIGN.get()), LEBETHRON));
    public static RegistryObject<LeavesBlock> LEAVES_CULUMALDA = BLOCKS.register("leaves_culumalda", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_LEBETHRON = BLOCKS.register("leaves_lebethron", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_MALLORN = BLOCKS.register("leaves_mallorn", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_MIRKWOOD = BLOCKS.register("leaves_mirkwood", TTMContent::createLeavesBlock);
    public static RegistryObject<LeavesBlock> LEAVES_FANGORNOAK = BLOCKS.register("leaves_fangornoak", TTMContent::createLeavesBlock);
    public static RegistryObject<Block> LEAFPILE_MALLORN = BLOCKS.register("leafpile_mallorn", () -> new LeafPileBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAFPILE_MIRKWOOD = BLOCKS.register("leafpile_mirkwood", () -> new LeafPileBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAFPILE_CULUMALDA = BLOCKS.register("leafpile_culumalda", () -> new LeafPileBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAFPILE_LEBETHRON = BLOCKS.register("leafpile_lebethron", () -> new LeafPileBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAFPILE_FANGORNOAK = BLOCKS.register("leafpile_fangornoak", () -> new LeafPileBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_GREEN).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_MALLORN = BLOCKS.register("sapling_mallorn", () -> new SaplingBlock(new TTMMallornTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_MIRKWOOD = BLOCKS.register("sapling_mirkwood", () -> new SaplingBlock(new TTMMirkwoodTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_CULUMALDA = BLOCKS.register("sapling_culumalda", () -> new SaplingBlock(new TTMCulumaldaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_LEBETHRON = BLOCKS.register("sapling_lebethron", () -> new SaplingBlock(new TTMLebethronTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_DEADWOOD = BLOCKS.register("sapling_deadwood", () -> new SaplingBlock(new TTMDeadTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<SaplingBlock> SAPLING_FANGORNOAK = BLOCKS.register("sapling_fangornoak", () -> new SaplingBlock(new TTMFangornOakTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    // Plants & Flowers
    public static RegistryObject<Block> PIPEWEED = BLOCKS.register("pipeweed", () -> new CropsBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static RegistryObject<Block> MUSHROOM_DECAY_BLOOM = BLOCKS.register("mushroom_decay_bloom", () -> new MushroomsBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> {
        return 1;
    }).hasPostProcess(TTMContent::needsPostProcessing)));
    public static RegistryObject<Block> MUSHROOM_BLOOM_DECAY = BLOCKS.register("mushroom_bloom_decay", () -> new MushroomsBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> {
        return 1;
    }).hasPostProcess(TTMContent::needsPostProcessing)));
    public static RegistryObject<Block> BLOCK_DECAY_BLOOM = BLOCKS.register("block_decay_bloom", () -> new HugeMushroomBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_MAGENTA).strength(0.2F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> BLOCK_BLOOM_DECAY = BLOCKS.register("block_bloom_decay", () -> new HugeMushroomBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_MAGENTA).strength(0.2F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> FLOWER_SIMBELMYNE = BLOCKS.register("flower_simbelmyne", () -> new FlowerBlock(Effects.HERO_OF_THE_VILLAGE, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_MIRKWOOD = BLOCKS.register("flower_mirkwood", () -> new FlowerBlock(Effects.BAD_OMEN, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_ALFIRIN = BLOCKS.register("flower_alfirin", () -> new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_ATHELAS = BLOCKS.register("flower_athelas", () -> new FlowerBlock(Effects.REGENERATION, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_NIPHREDIL = BLOCKS.register("flower_niphredil", () -> new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_SWAMPMILKWEED = BLOCKS.register("flower_swamp_milkweed", () -> new FlowerBlock(Effects.MOVEMENT_SLOWDOWN, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_LILLYOFTHEVALLEY = BLOCKS.register("flower_valley_lilly", () -> new FlowerBlock(Effects.HEAL, 7, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));

    // Custom
    public static RegistryObject<Block> BLOCK_HALLOWED = BLOCKS.register("block_hallowed", () -> new HallowedBlock(AbstractBlock.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.GRAVEL).randomTicks()));
    public static RegistryObject<Block> STONE_PATH = BLOCKS.register("block_stone_path", () -> new StonePathBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GREEN).sound(SoundType.STONE).isViewBlocking(TTMContent::needsPostProcessing).isSuffocating(TTMContent::needsPostProcessing)));
    public static RegistryObject<Block> TTMFIREPLACE = BLOCKS.register("block_tmfireplace", () -> new FireplaceBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).harvestTool(ToolType.PICKAXE).noOcclusion().harvestLevel(2).strength(5f, 6f)));
    public static RegistryObject<Block> PIGGYBANK = BLOCKS.register("block_piggybank", () -> new PiggyBankBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).harvestTool(ToolType.PICKAXE).noOcclusion().harvestLevel(2).strength(5f, 6f)));
    public static RegistryObject<Block> BARREL_MITHRIL = BLOCKS.register("block_barrel_mithril", () -> new MithrilBarrelBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).harvestTool(ToolType.PICKAXE).noOcclusion().harvestLevel(2).strength(5f, 6f)));
    public static RegistryObject<Block> BARREL_MORGULIRON = BLOCKS.register("block_barrel_morguliron", () -> new MorgulironBarrelBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).harvestTool(ToolType.PICKAXE).noOcclusion().harvestLevel(2).strength(5f, 6f)));
    public static RegistryObject<Block> BACKPACK = BLOCKS.register("backpack", () -> new BackpackBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GRAY).harvestTool(ToolType.AXE).noOcclusion().harvestLevel(1).strength(1f, 1f)));
    public static RegistryObject<Block> PLACARD = BLOCKS.register("placard", () -> new PlacardBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).harvestTool(ToolType.AXE).noOcclusion().harvestLevel(1).strength(1f, 1f)));
    public static RegistryObject<Block> CHAMELEON_BLOCK = BLOCKS.register("chameleon_block", () -> new ChameleonBlock<>(AbstractBlock.Properties.of(Material.DECORATION).noCollission().noOcclusion()));
    public static RegistryObject<Block> KEY_STONE_BLOCK = BLOCKS.register("block_key_stone", () -> new CamoKeyStoneBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_GLOWSTONE_BLOCK = BLOCKS.register("block_camo_glowstone", () -> new CamoGlowstoneBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().lightLevel((p_235464_0_) -> {
        return 15;
    })));
    public static RegistryObject<Block> CAMO_SMOKER_BLOCK = BLOCKS.register("block_camo_smoker", () -> new CamoSmokerBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_FLUID_BLOCK = BLOCKS.register("block_camo_fluid", () -> new CamoFluidBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_CHEST_BLOCK = BLOCKS.register("block_camo_chest", () -> new CamoChestBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_SPAWNER_BLOCK = BLOCKS.register("block_camo_spawner", () -> new CamoSpawnerBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> MILESTONE_BLOCK = BLOCKS.register("milestone_block", () -> new MilestoneBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_CHEST_BLOCK = BLOCKS.register("lockable_chest_block", () -> new LockableChestBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_TREASURE_CHEST_BLOCK = BLOCKS.register("lockable_treasure_chest_block", () -> new LockableTreasureChestBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_DOUBLE_CHEST_BLOCK = BLOCKS.register("lockable_double_chest_block", () -> new LockableDoubleChestBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK = BLOCKS.register("lockable_double_treasure_chest_block", () -> new LockableDoubleTreasureChestBlock(AbstractBlock.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> ROCKPILE = BLOCKS.register("rockpile", () -> new RockPileBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).strength(5f, 6f)));

    // Custom - Sleeping Bags
    public static RegistryObject<Block> SLEEPING_BAG_BLUE = BLOCKS.register("sleeping_bag_blue", () -> new SleepingBagBlock(DyeColor.BLUE, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BLUE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_RED = BLOCKS.register("sleeping_bag_red", () -> new SleepingBagBlock(DyeColor.RED, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_RED : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_BLACK = BLOCKS.register("sleeping_bag_black", () -> new SleepingBagBlock(DyeColor.BLACK, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BLACK : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_BROWN = BLOCKS.register("sleeping_bag_brown", () -> new SleepingBagBlock(DyeColor.BROWN, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BROWN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_CYAN = BLOCKS.register("sleeping_bag_cyan", () -> new SleepingBagBlock(DyeColor.CYAN, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_CYAN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_GRAY = BLOCKS.register("sleeping_bag_gray", () -> new SleepingBagBlock(DyeColor.GRAY, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_GRAY : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_GREEN = BLOCKS.register("sleeping_bag_green", () -> new SleepingBagBlock(DyeColor.GREEN, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_GREEN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIGHT_BLUE = BLOCKS.register("sleeping_bag_light_blue", () -> new SleepingBagBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_BLUE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIGHT_GRAY = BLOCKS.register("sleeping_bag_light_gray", () -> new SleepingBagBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_GRAY : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIME = BLOCKS.register("sleeping_bag_lime", () -> new SleepingBagBlock(DyeColor.LIME, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_GREEN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_MAGENTA = BLOCKS.register("sleeping_bag_magenta", () -> new SleepingBagBlock(DyeColor.MAGENTA, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_MAGENTA : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_ORANGE = BLOCKS.register("sleeping_bag_orange", () -> new SleepingBagBlock(DyeColor.ORANGE, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_ORANGE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_PINK = BLOCKS.register("sleeping_bag_pink", () -> new SleepingBagBlock(DyeColor.PINK, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_PINK : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_PURPLE = BLOCKS.register("sleeping_bag_purple", () -> new SleepingBagBlock(DyeColor.PURPLE, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_PURPLE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_WHITE = BLOCKS.register("sleeping_bag_white", () -> new SleepingBagBlock(DyeColor.WHITE, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.SNOW : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_YELLOW = BLOCKS.register("sleeping_bag_yellow", () -> new SleepingBagBlock(DyeColor.YELLOW, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_RED : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));

    //#################################################################
    // Items
    //#################################################################
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
    public static RegistryObject<Item> TORCH_MALLORN_ITEM = ITEMS.register("torch_mallorn", () -> new WallOrFloorItem(TORCH_MALLORN.get(), WALL_TORCH_MALLORN.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_MIRKWOOD_ITEM = ITEMS.register("torch_mirkwood", () -> new WallOrFloorItem(TORCH_MIRKWOOD.get(), WALL_TORCH_MIRKWOOD.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_CULUMALDA_ITEM = ITEMS.register("torch_culumalda", () -> new WallOrFloorItem(TORCH_CULUMALDA.get(), WALL_TORCH_CULUMALDA.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> TORCH_LEBETHRON_ITEM = ITEMS.register("torch_lebethron", () -> new WallOrFloorItem(TORCH_LEBETHRON.get(), WALL_TORCH_LEBETHRON.get(), new Item.Properties().tab(decoGroup)));
    public static RegistryObject<Item> LEAVES_CULUMALDA_ITEM = ITEMS.register("leaves_culumalda", () -> new ItemBlockBCore(LEAVES_CULUMALDA.get(), new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> MALLORN_SIGN_ITEM = ITEMS.register("sign_mallorn", () -> new MallornSignItem(new Item.Properties().tab(decoGroup), MALLORN_SIGN.get(), MALLORN_WALL_SIGN.get()));
    public static RegistryObject<Item> MIRKWOOD_SIGN_ITEM = ITEMS.register("sign_mirkwood", () -> new MirkwoodSignItem(new Item.Properties().tab(decoGroup), MIRKWOOD_SIGN.get(), MIRKWOOD_WALL_SIGN.get()));
    public static RegistryObject<Item> CULUMALDA_SIGN_ITEM = ITEMS.register("sign_culumalda", () -> new CulumaldaSignItem(new Item.Properties().tab(decoGroup), CULUMALDA_SIGN.get(), CULUMALDA_WALL_SIGN.get()));
    public static RegistryObject<Item> LEBETHRON_SIGN_ITEM = ITEMS.register("sign_lebethron", () -> new LebethronSignItem(new Item.Properties().tab(decoGroup), LEBETHRON_SIGN.get(), LEBETHRON_WALL_SIGN.get()));
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
    public static RegistryObject<Item> ITEM_PLACARD = ITEMS.register("item_placard", () -> new TTMLoreBlock(TTMContent.PLACARD.get(), (new Item.Properties()).stacksTo(1).tab(decoGroup)).setHasLore());
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
    public static RegistryObject<Item> SLEEPING_BAG_BLUE_ITEM = ITEMS.register("sleeping_bag_blue", () -> new TTMLoreBlock(SLEEPING_BAG_BLUE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_RED_ITEM = ITEMS.register("sleeping_bag_red", () -> new TTMLoreBlock(SLEEPING_BAG_RED.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_BLACK_ITEM = ITEMS.register("sleeping_bag_black", () -> new TTMLoreBlock(SLEEPING_BAG_BLACK.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_BROWN_ITEM = ITEMS.register("sleeping_bag_brown", () -> new TTMLoreBlock(SLEEPING_BAG_BROWN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_CYAN_ITEM = ITEMS.register("sleeping_bag_cyan", () -> new TTMLoreBlock(SLEEPING_BAG_CYAN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_GRAY_ITEM = ITEMS.register("sleeping_bag_gray", () -> new TTMLoreBlock(SLEEPING_BAG_GRAY.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_GREEN_ITEM = ITEMS.register("sleeping_bag_green", () -> new TTMLoreBlock(SLEEPING_BAG_GREEN.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIGHT_BLUE_ITEM = ITEMS.register("sleeping_bag_light_blue", () -> new TTMLoreBlock(SLEEPING_BAG_LIGHT_BLUE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIGHT_GRAY_ITEM = ITEMS.register("sleeping_bag_light_gray", () -> new TTMLoreBlock(SLEEPING_BAG_LIGHT_GRAY.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_LIME_ITEM = ITEMS.register("sleeping_bag_lime", () -> new TTMLoreBlock(SLEEPING_BAG_LIME.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_MAGENTA_ITEM = ITEMS.register("sleeping_bag_magenta", () -> new TTMLoreBlock(SLEEPING_BAG_MAGENTA.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_ORANGE_ITEM = ITEMS.register("sleeping_bag_orange", () -> new TTMLoreBlock(SLEEPING_BAG_ORANGE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_PINK_ITEM = ITEMS.register("sleeping_bag_pink", () -> new TTMLoreBlock(SLEEPING_BAG_PINK.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_PURPLE_ITEM = ITEMS.register("sleeping_bag_purple", () -> new TTMLoreBlock(SLEEPING_BAG_PURPLE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_WHITE_ITEM = ITEMS.register("sleeping_bag_white", () -> new TTMLoreBlock(SLEEPING_BAG_WHITE.get(), new Item.Properties().tab(decoGroup)).setHasLore());
    public static RegistryObject<Item> SLEEPING_BAG_YELLOW_ITEM = ITEMS.register("sleeping_bag_yellow", () -> new TTMLoreBlock(SLEEPING_BAG_YELLOW.get(), new Item.Properties().tab(decoGroup)).setHasLore());

    // Quest
    public static RegistryObject<Item> ITEM_BERYL = ITEMS.register("item_beryl", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FORTRESSMAP = ITEMS.register("item_fortressmap", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART = ITEMS.register("item_watcherheart", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERHEART_CRACKED = ITEMS.register("item_watcherheart_cracked", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_KEYSTONE = ITEMS.register("item_keystone", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSADDLE = ITEMS.register("item_darksaddle", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_ARTIFACT = ITEMS.register("item_artifact", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_BLANKPAPER = ITEMS.register("item_blankpaper", () -> new TTMLoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYARMOR = ITEMS.register("item_fancyarmor", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYCLOTH = ITEMS.register("item_fancycloth", () -> new TTMLoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHAMMER = ITEMS.register("item_fancyhammer", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYHELM = ITEMS.register("item_fancyhelm", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYKEY = ITEMS.register("item_fancykey", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYPICK = ITEMS.register("item_fancypick", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD = ITEMS.register("item_fancyshield", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSHIELD2 = ITEMS.register("item_fancyshield2", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD = ITEMS.register("item_fancysword", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FANCYSWORD2 = ITEMS.register("item_fancysword2", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LETTER = ITEMS.register("item_letter", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL = ITEMS.register("item_scroll", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_SCROLL2 = ITEMS.register("item_scroll2", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_SPECIALFLOWER = ITEMS.register("item_specialflower", () -> new TTMLoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK = ITEMS.register("item_storybook", () -> new TTMLoreItem(new Item.Properties().stacksTo(12).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK2 = ITEMS.register("item_storybook2", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK3 = ITEMS.register("item_storybook3", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_STORYBOOK4 = ITEMS.register("item_storybook4", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNARMOR = ITEMS.register("item_wornarmor", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNHELM = ITEMS.register("item_wornhelm", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNKEY = ITEMS.register("item_wornkey", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNPICK = ITEMS.register("item_wornpick", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD = ITEMS.register("item_wornshield", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSHIELD2 = ITEMS.register("item_wornshield2", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WORNSWORD = ITEMS.register("item_wornsword", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WOVENBASKET = ITEMS.register("item_wovenbasket", () -> new TTMLoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WRITTENPAPER = ITEMS.register("item_writtenpaper", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_PUNGENTHERB = ITEMS.register("item_pungentherb", () -> new TTMLoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_LOCKPICK = ITEMS.register("item_lockpick", () -> new TTMLoreItem(new Item.Properties().stacksTo(16).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BROKENSWORD = ITEMS.register("item_brokensword", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_REFORGEDSWORD = ITEMS.register("item_reforgedsword", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_MAGIC_CLOTH = ITEMS.register("item_magic_cloth", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_KEYFRAGMENT = ITEMS.register("item_keyfragment", () -> new TTMLoreItem(new Item.Properties().stacksTo(2).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_OILYKEY = ITEMS.register("item_oilykey", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_MITHRILNUGGET = ITEMS.register("item_mithrilnugget", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_REMAINS = ITEMS.register("item_remains", () -> new TTMLoreItem(new Item.Properties().stacksTo(16).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_RUNE_STONE = ITEMS.register("item_rune_stone", () -> new TTMLoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());

    // Dev Tools
    public static RegistryObject<Item> ITEM_DEV_TOOL = ITEMS.register("item_dev_tool", () -> new TTMLoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());
    public static RegistryObject<Item> ITEM_DEV_DEBUG_TOOL = ITEMS.register("item_dev_debug_tool", () -> new TTMLoreItem(new Item.Properties().stacksTo(8).tab(questGroup)).setEffectOverride().setHasLore());

    // Backpack Upgrades
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_BASE = ITEMS.register("upgrade_item_backpack_upgrade_base", () -> new TTMLoreItem(new Item.Properties().stacksTo(5).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_SIZE = ITEMS.register("item_backpack_upgrade_size", () -> new TTMLoreItem(new Item.Properties().stacksTo(2).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_FLUID = ITEMS.register("item_backpack_upgrade_fluid", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_CRAFTING = ITEMS.register("item_backpack_upgrade_crafting", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_SLEEPING = ITEMS.register("item_backpack_upgrade_sleeping", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_BACKPACK_UPGRADE_CAMPFIRE = ITEMS.register("item_backpack_upgrade_campfire", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(matsGroup)).setHasLore());

    // Metals & Gems
    public static RegistryObject<Item> DUST_MITHRIL = ITEMS.register("dust_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> NUGGET_MITHRIL = ITEMS.register("nugget_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> INGOT_MITHRIL = ITEMS.register("ingot_mithril", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> DUST_MORGULIRON = ITEMS.register("dust_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> NUGGET_MORGULIRON = ITEMS.register("nugget_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> INGOT_MORGULIRON = ITEMS.register("ingot_morguliron", () -> new Item(new Item.Properties().tab(matsGroup)));
    public static RegistryObject<Item> GEM_AMMOLITE = ITEMS.register("gem_ammolite", () -> new TTMLoreItem(new Item.Properties().stacksTo(16).tab(matsGroup)).setEffectOverride());

    // Equipment & Armor
    public static RegistryObject<ArmorItem> HELMET_MITHRIL = ITEMS.register("helmet_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlotType.HEAD, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> CHESTPLATE_MITHRIL = ITEMS.register("chestplate_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlotType.CHEST, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> LEGGINGS_MITHRIL = ITEMS.register("leggings_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlotType.LEGS, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> BOOTS_MITHRIL = ITEMS.register("boots_mithril", () -> new MithrilArmor(TTMArmorTier.MITHRIL, EquipmentSlotType.FEET, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<Item> MITHRIL_HORSE_ARMOR = ITEMS.register("mithril_horse_armor", () -> new MithrilHorseArmor(15, "mithril", (new Item.Properties()).stacksTo(1).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> HELMET_MORGULIRON = ITEMS.register("helmet_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlotType.HEAD, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> CHESTPLATE_MORGULIRON = ITEMS.register("chestplate_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlotType.CHEST, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> LEGGINGS_MORGULIRON = ITEMS.register("leggings_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlotType.LEGS, (new Item.Properties()).tab(toolsGroup)));
    public static RegistryObject<ArmorItem> BOOTS_MORGULIRON = ITEMS.register("boots_morguliron", () -> new MorgulironArmor(TTMArmorTier.MORGULIRON, EquipmentSlotType.FEET, (new Item.Properties()).tab(toolsGroup)));
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
    public static RegistryObject<BlockItem> ITEM_BACKPACK = ITEMS.register("item_backpack", () -> new BackpackItem(TTMContent.BACKPACK.get(), (new Item.Properties()).stacksTo(1).tab(toolsGroup)));
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
    public static RegistryObject<Item> ITEM_COIN_BRONZE = ITEMS.register("item_coin_bronze", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_SILVER = ITEMS.register("item_coin_silver", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_GOLD = ITEMS.register("item_coin_gold", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_COIN_MITHRIL = ITEMS.register("item_coin_mithril", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_DARKSIGIL = ITEMS.register("item_darksigil", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONCOIN = ITEMS.register("item_coin1", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_FACTIONTOKEN = ITEMS.register("item_coin2", () -> new TTMLoreItem(new Item.Properties().tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_CAVECOMPLETE = ITEMS.register("item_cavecomplete", () -> new TTMLoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_WATCHERCOMPLETE = ITEMS.register("item_watchercomplete", () -> new TTMLoreItem(new Item.Properties().stacksTo(3).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_TOKEN_EASTERN_ALLIANCE = ITEMS.register("item_token_eastern_alliance", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());
    public static RegistryObject<Item> ITEM_TOKEN_WESTERN_ALLIANCE = ITEMS.register("item_token_western_alliance", () -> new TTMLoreItem(new Item.Properties().stacksTo(1).tab(questGroup)).setHasLore());

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
    public static RegistryObject<Item> GOLEM_STONE_SUMMON = ITEMS.register("item_golem_stone_summon", () -> new TTMLoreItem(new Item.Properties().stacksTo(16).tab(spawnGroup)).setEffectOverride().setHasLore().setItemHasUse().setSpawnInfo());

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

    //#################################################################
    // Fluids
    //#################################################################
    public static RegistryObject<FlowingFluid> MITHRIL_FLUID = FLUIDS.register("mithril_fluid", () -> new ForgeFlowingFluid.Source(TTMContent.MITHRIL_PROPERTIES));
    public static RegistryObject<FlowingFluid> MITHRIL_FLOWING = FLUIDS.register("mithril_flowing", () -> new ForgeFlowingFluid.Flowing(TTMContent.MITHRIL_PROPERTIES));
    public static final RegistryObject<FlowingFluidBlock> MITHRIL_FLUID_BLOCK = BLOCKS.register("mithril_fluid_source", () -> new FlowingFluidBlock(() -> MITHRIL_FLUID.get(), AbstractBlock.Properties.of(Material.LAVA).noOcclusion().strength(100f).noDrops()));
    public static RegistryObject<Item> MITHRIL_FLUID_BUCKET = ITEMS.register("mithril_fluid_bucket", () -> new BucketItem(() -> MITHRIL_FLUID.get(), new Item.Properties().stacksTo(1).tab(matsGroup)));
    public static final ForgeFlowingFluid.Properties MITHRIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MITHRIL_FLUID.get(), () -> MITHRIL_FLOWING.get(), FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0xAAA9ADD0)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TTMContent.MITHRIL_FLUID_BLOCK.get()).bucket(() -> TTMContent.MITHRIL_FLUID_BUCKET.get());

    public static RegistryObject<FlowingFluid> MORGULIRON_FLUID = FLUIDS.register("morguliron_fluid", () -> new ForgeFlowingFluid.Source(TTMContent.MORGULIRON_PROPERTIES));
    public static RegistryObject<FlowingFluid> MORGULIRON_FLOWING = FLUIDS.register("morguliron_flowing", () -> new ForgeFlowingFluid.Flowing(TTMContent.MORGULIRON_PROPERTIES));
    public static final RegistryObject<FlowingFluidBlock> MORGULIRON_FLUID_BLOCK = BLOCKS.register("morguliron_fluid_source", () -> new FlowingFluidBlock(() -> MITHRIL_FLUID.get(), AbstractBlock.Properties.of(Material.LAVA).noOcclusion().strength(100f).noDrops()));
    public static RegistryObject<Item> MORGULIRON_FLUID_BUCKET = ITEMS.register("morguliron_fluid_bucket", () -> new BucketItem(() -> MITHRIL_FLUID.get(), new Item.Properties().stacksTo(1).tab(matsGroup)));
    public static final ForgeFlowingFluid.Properties MORGULIRON_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MORGULIRON_FLUID.get(), () -> MORGULIRON_FLOWING.get(), FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.LAVA_AMBIENT).overlay(FLUID_OVERLAY_RL)
            .color(0x353F2AD0)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(() -> TTMContent.MORGULIRON_FLUID_BLOCK.get()).bucket(() -> TTMContent.MORGULIRON_FLUID_BUCKET.get());

    //#################################################################
    // Tile Entity Types
    //#################################################################
    public static RegistryObject<TileEntityType<FireplaceTile>> TMFIREPLACE_TILE = TILE.register("tmfireplace_tile", () -> TileEntityType.Builder.of(FireplaceTile::new, TTMFIREPLACE.get()).build(null));
    public static RegistryObject<TileEntityType<PiggyBankTile>> PIGGYBANK_TILE = TILE.register("piggybank_tile", () -> TileEntityType.Builder.of(PiggyBankTile::new, PIGGYBANK.get()).build(null));
    public static RegistryObject<TileEntityType<MithrilBarrelTile>> BARREL_MITHRIL_TILE = TILE.register("barrel_mithril_tile", () -> TileEntityType.Builder.of(MithrilBarrelTile::new, BARREL_MITHRIL.get()).build(null));
    public static RegistryObject<TileEntityType<MorgulironBarrelTile>> BARREL_MORGULIRON_TILE = TILE.register("barrel_morguliron_tile", () -> TileEntityType.Builder.of(MorgulironBarrelTile::new, BARREL_MORGULIRON.get()).build(null));
    public static RegistryObject<TileEntityType<BackpackTile>> BACKPACK_TILE = TILE.register("backpack_tile", () -> TileEntityType.Builder.of(BackpackTile::new, BACKPACK.get()).build(null));
    public static RegistryObject<TileEntityType<SleepingBagTile>> SLEEPING_BAG_TILE = TILE.register("sleeping_bag", () -> new TileEntityType<>(SleepingBagTile::new, Sets.newHashSet(TTMContent.SLEEPING_BAG_RED.get(), TTMContent.SLEEPING_BAG_BLUE.get(), TTMContent.SLEEPING_BAG_BLACK.get(), TTMContent.SLEEPING_BAG_BROWN.get(), TTMContent.SLEEPING_BAG_CYAN.get(), TTMContent.SLEEPING_BAG_GRAY.get(), TTMContent.SLEEPING_BAG_GREEN.get(), TTMContent.SLEEPING_BAG_LIGHT_BLUE.get(), TTMContent.SLEEPING_BAG_LIGHT_GRAY.get(), TTMContent.SLEEPING_BAG_LIME.get(), TTMContent.SLEEPING_BAG_MAGENTA.get(), TTMContent.SLEEPING_BAG_ORANGE.get(), TTMContent.SLEEPING_BAG_PINK.get(), TTMContent.SLEEPING_BAG_PURPLE.get(), TTMContent.SLEEPING_BAG_WHITE.get(), TTMContent.SLEEPING_BAG_YELLOW.get()), null));
    public static RegistryObject<TileEntityType<PlacardTile>> PLACARD_TILE = TILE.register("placard_tile", () -> TileEntityType.Builder.of(PlacardTile::new, PLACARD.get()).build(null));
    public static RegistryObject<TileEntityType<CamoKeyStoneTile>> KEY_STONE_TILE = TILE.register("key_stone_tile", () -> TileEntityType.Builder.of(CamoKeyStoneTile::new, KEY_STONE_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<CamoFluidTile>> CAMO_FLUID_TILE = TILE.register("camo_fluid_tile", () -> TileEntityType.Builder.of(CamoFluidTile::new, CAMO_FLUID_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<CamoChestTile>> CAMO_CHEST_TILE = TILE.register("camo_chest_tile", () -> TileEntityType.Builder.of(CamoChestTile::new, CAMO_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<CamoSpawnerTile>> CAMO_SPAWNER_TILE = TILE.register("camo_spawner_tile", () -> TileEntityType.Builder.of(CamoSpawnerTile::new, CAMO_SPAWNER_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<MilestoneTile>> MILESTONE_TILE = TILE.register("milestone_tile", () -> TileEntityType.Builder.of(MilestoneTile::new, MILESTONE_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<LockableChestTile>> LOCKABLE_CHEST_TILE = TILE.register("lockable_chest_tile", () -> TileEntityType.Builder.of(LockableChestTile::new, LOCKABLE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<LockableTreasureChestTile>> LOCKABLE_TREASURE_CHEST_TILE = TILE.register("lockable_treasure_chest_tile", () -> TileEntityType.Builder.of(LockableTreasureChestTile::new, LOCKABLE_TREASURE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<LockableDoubleChestTile>> LOCKABLE_DOUBLE_CHEST_TILE = TILE.register("lockable_double_chest_tile", () -> TileEntityType.Builder.of(LockableDoubleChestTile::new, LOCKABLE_DOUBLE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<LockableDoubleTreasureChestTile>> LOCKABLE_DOUBLE_TREASURE_CHEST_TILE = TILE.register("lockable_double_treasure_chest_tile", () -> TileEntityType.Builder.of(LockableDoubleTreasureChestTile::new, LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get()).build(null));
    public static RegistryObject<TileEntityType<MallornSignTile>> MALLORN_SIGN_TILE = TILE.register("mallorn_sign", () -> TileEntityType.Builder.of(MallornSignTile::new, MALLORN_SIGN.get(), MALLORN_WALL_SIGN.get()).build(null));
    public static RegistryObject<TileEntityType<MirkwoodSignTile>> MIRKWOOD_SIGN_TILE = TILE.register("mirkwood_sign", () -> TileEntityType.Builder.of(MirkwoodSignTile::new, MIRKWOOD_SIGN.get(), MIRKWOOD_WALL_SIGN.get()).build(null));
    public static RegistryObject<TileEntityType<CulumaldaSignTile>> CULUMALDA_SIGN_TILE = TILE.register("culumalda_sign", () -> TileEntityType.Builder.of(CulumaldaSignTile::new, CULUMALDA_SIGN.get(), CULUMALDA_WALL_SIGN.get()).build(null));
    public static RegistryObject<TileEntityType<LebethronSignTile>> LEBETHRON_SIGN_TILE = TILE.register("lebethron_sign", () -> TileEntityType.Builder.of(LebethronSignTile::new, LEBETHRON_SIGN.get(), LEBETHRON_WALL_SIGN.get()).build(null));

    //#################################################################
    // Containers
    //#################################################################
    public static ContainerType<ContainerBCTile<FireplaceTile>> TMFIREPLACE_CONTAINER;
    public static ContainerType<ContainerBCTile<PiggyBankTile>> PIGGYBANK_CONTAINER;
    public static ContainerType<ContainerBCTile<MithrilBarrelTile>> BARREL_MITHRIL_CONTAINER;
    public static ContainerType<ContainerBCTile<MorgulironBarrelTile>> BARREL_MORGULIRON_CONTAINER;
    public static ContainerType<BackpackContainer> BACKPACK_CONTAINER;
    public static ContainerType<CoinPouchContainer> COIN_POUCH_CONTAINER;
    public static ContainerType<KeyRingContainer> KEY_RING_CONTAINER;
    public static ContainerType<ContainerBCTile<MilestoneTile>> MILESTONE_CONTAINER;
    public static ContainerType<ContainerBCTile<CamoKeyStoneTile>> KEY_STONE_CONTAINER;
    public static ContainerType<CamoChestContainer> CAMO_CHEST_CONTAINER;
    public static ContainerType<CamoFluidContainer> CAMO_FLUID_CONTAINER;
    public static ContainerType<ContainerBCTile<CamoSpawnerTile>> CAMO_SPAWNER_CONTAINER;
    public static ContainerType<LockableChestContainer> LOCKABLE_CHEST_CONTAINER;
    public static ContainerType<LockableTreasureChestContainer> LOCKABLE_TREASURE_CHEST_CONTAINER;
    public static ContainerType<LockableDoubleChestContainer> LOCKABLE_DOUBLE_CHEST_CONTAINER;
    public static ContainerType<LockableDoubleTreasureChestContainer> LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER;

    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().register(TMFIREPLACE_CONTAINER = (ContainerType<ContainerBCTile<FireplaceTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(TMFIREPLACE_CONTAINER, id, playerInv, extraData, FireplaceTile.SLOT_LAYOUT)).setRegistryName("tmfireplace_container"));
        event.getRegistry().register(PIGGYBANK_CONTAINER = (ContainerType<ContainerBCTile<PiggyBankTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(PIGGYBANK_CONTAINER, id, playerInv, extraData, PiggyBankTile.SLOT_LAYOUT)).setRegistryName("piggybank_container"));
        event.getRegistry().register(BARREL_MITHRIL_CONTAINER = (ContainerType<ContainerBCTile<MithrilBarrelTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(BARREL_MITHRIL_CONTAINER, id, playerInv, extraData, MithrilBarrelTile.SLOT_LAYOUT)).setRegistryName("barrel_mithril_container"));
        event.getRegistry().register(BARREL_MORGULIRON_CONTAINER = (ContainerType<ContainerBCTile<MorgulironBarrelTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(BARREL_MORGULIRON_CONTAINER, id, playerInv, extraData, MorgulironBarrelTile.SLOT_LAYOUT)).setRegistryName("barrel_morguliron_container"));
        event.getRegistry().register(BACKPACK_CONTAINER = (ContainerType<BackpackContainer>) IForgeContainerType.create(BackpackContainer::new).setRegistryName("backpack_container"));
        event.getRegistry().register(COIN_POUCH_CONTAINER = (ContainerType<CoinPouchContainer>) IForgeContainerType.create(CoinPouchContainer::new).setRegistryName("coin_pouch_container"));
        event.getRegistry().register(KEY_RING_CONTAINER = (ContainerType<KeyRingContainer>) IForgeContainerType.create(KeyRingContainer::new).setRegistryName("key_ring_container"));
        event.getRegistry().register(MILESTONE_CONTAINER = ((ContainerType<ContainerBCTile<MilestoneTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(MILESTONE_CONTAINER, id, playerInv, extraData)).setRegistryName("milestone_container")));
        event.getRegistry().register(KEY_STONE_CONTAINER = ((ContainerType<ContainerBCTile<CamoKeyStoneTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(KEY_STONE_CONTAINER, id, playerInv, extraData)).setRegistryName("key_stone_container")));
        event.getRegistry().register(CAMO_SPAWNER_CONTAINER = ((ContainerType<ContainerBCTile<CamoSpawnerTile>>) IForgeContainerType.create((id, playerInv, extraData) -> new ContainerBCTile<>(CAMO_SPAWNER_CONTAINER, id, playerInv, extraData)).setRegistryName("camo_spawner_container")));
        event.getRegistry().register(CAMO_CHEST_CONTAINER = (ContainerType<CamoChestContainer>) IForgeContainerType.create(CamoChestContainer::new).setRegistryName("camo_chest_container"));
        event.getRegistry().register(CAMO_FLUID_CONTAINER = (ContainerType<CamoFluidContainer>) IForgeContainerType.create(CamoFluidContainer::new).setRegistryName("camo_fluid_container"));
        event.getRegistry().register(LOCKABLE_CHEST_CONTAINER = (ContainerType<LockableChestContainer>) IForgeContainerType.create(LockableChestContainer::new).setRegistryName("lockable_chest_container"));
        event.getRegistry().register(LOCKABLE_TREASURE_CHEST_CONTAINER = (ContainerType<LockableTreasureChestContainer>) IForgeContainerType.create(LockableTreasureChestContainer::new).setRegistryName("lockable_treasure_chest_container"));
        event.getRegistry().register(LOCKABLE_DOUBLE_CHEST_CONTAINER = (ContainerType<LockableDoubleChestContainer>) IForgeContainerType.create(LockableDoubleChestContainer::new).setRegistryName("lockable_double_chest_container"));
        event.getRegistry().register(LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER = (ContainerType<LockableDoubleTreasureChestContainer>) IForgeContainerType.create(LockableDoubleTreasureChestContainer::new).setRegistryName("lockable_double_treasure_chest_container"));
    }

    //#################################################################
    // Recipe Serializers
    //#################################################################
    public static final RegistryObject<FireplaceRecipe.Serializer> TMFIREPLACE_SERIALIZER = RECIPE_SERIALIZER.register("tmfireplace", FireplaceRecipe.Serializer::new);

    private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    private static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    private static ToIntFunction<BlockState> litBlockEmission(int level) {
        return (p_235421_1_) -> {
            return p_235421_1_.getValue(BlockStateProperties.LIT) ? level : 0;
        };
    }

    private static boolean never(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TTMContent::allowsSpawnOnLeaves).isSuffocating(TTMContent::isntSolid).isViewBlocking(TTMContent::isntSolid));
    }

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, (state) -> {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).strength(2.0F).sound(SoundType.WOOD));
    }
}