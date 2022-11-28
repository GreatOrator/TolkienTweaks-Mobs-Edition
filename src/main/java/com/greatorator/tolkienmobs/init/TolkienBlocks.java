package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.world.gen.feature.TreeFeature;
import com.greatorator.tolkienmobs.world.trees.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;
import static com.greatorator.tolkienmobs.init.TolkienWoodTypes.*;

public class TolkienBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    // Metals & Gems
        // Mithril
    public static RegistryObject<Block> ORE_MITHRIL = BLOCKS.register("ore_mithril", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_END_MITHRIL = BLOCKS.register("ore_end_mithril", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_NETHER_MITHRIL = BLOCKS.register("ore_nether_mithril", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_DEEPSLATE_MITHRIL = BLOCKS.register("ore_deepslate_mithril", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(7.5F, 9.0F).sound(SoundType.DEEPSLATE)));
    public static RegistryObject<Block> BLOCK_MITHRIL = BLOCKS.register("block_mithril", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<Block> RAW_MITHRIL_BLOCK = BLOCKS.register("raw_mithril_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<IronBarsBlock> MITHRIL_BARS = BLOCKS.register("mithril_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<Block> WALL_MITHRIL = BLOCKS.register("wall_mithril", () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_MITHRIL = BLOCKS.register("door_mithril", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MITHRIL = BLOCKS.register("trapdoor_mithril", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(TolkienBlocks::never)));

        // MorgulIron
    public static RegistryObject<Block> PRESSURE_PLATE_MITHRIL = BLOCKS.register("pressure_plate_mithril", () -> new WeightedPressurePlateBlock(15, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> ORE_MORGULIRON = BLOCKS.register("ore_morguliron", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_END_MORGULIRON = BLOCKS.register("ore_end_morguliron", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_NETHER_MORGULIRON = BLOCKS.register("ore_nether_morguliron", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_DEEPSLATE_MORGULIRON = BLOCKS.register("ore_deepslate_morguliron", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(7.5F, 9.0F).sound(SoundType.DEEPSLATE)));
    public static RegistryObject<Block> BLOCK_MORGULIRON = BLOCKS.register("block_morguliron", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<Block> RAW_MORGULIRON_BLOCK = BLOCKS.register("raw_morguliron_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static RegistryObject<IronBarsBlock> MORGULIRON_BARS = BLOCKS.register("morguliron_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<Block> WALL_MORGULIRON = BLOCKS.register("wall_morguliron", () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_MORGULIRON = BLOCKS.register("door_morguliron", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MORGULIRON = BLOCKS.register("trapdoor_morguliron", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<Block> PRESSURE_PLATE_MORGULIRON = BLOCKS.register("pressure_plate_morguliron", () -> new WeightedPressurePlateBlock(150, BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().noCollission().strength(0.5F).sound(SoundType.WOOD)));

        // Ammolite
    public static RegistryObject<Block> ORE_AMMOLITE = BLOCKS.register("ore_ammolite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_END_AMMOLITE = BLOCKS.register("ore_end_ammolite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_NETHER_AMMOLITE = BLOCKS.register("ore_nether_ammolite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).sound(SoundType.STONE)));
    public static RegistryObject<Block> ORE_DEEPSLATE_AMMOLITE = BLOCKS.register("ore_deepslate_ammolite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DEEPSLATE).requiresCorrectToolForDrops().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static RegistryObject<Block> BLOCK_AMMOLITE = BLOCKS.register("block_ammolite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_LIGHT_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GLASS)));
    public static RegistryObject<IronBarsBlock> PANE_AMMOLITE = BLOCKS.register("pane_ammolite", () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.TERRACOTTA_LIGHT_GREEN).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.GLASS).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_DURIN = BLOCKS.register("door_durin", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));

    /* Wood & Foliage */
    // Mallorn
    public static RegistryObject<Block> LOG_MALLORN = BLOCKS.register("log_mallorn", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<Block> WOOD_MALLORN = BLOCKS.register("wood_mallorn", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_MALLORN_LOG = BLOCKS.register("stripped_log_mallorn", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_WHITE));
    public static RegistryObject<Block> STRIPPED_MALLORN_WOOD = BLOCKS.register("stripped_wood_mallorn", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MALLORN = BLOCKS.register("planks_mallorn", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairBlock> STAIRS_MALLORN = BLOCKS.register("stairs_mallorn", () -> new StairBlock(PLANKS_MALLORN.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_MALLORN.get())));
    public static RegistryObject<SlabBlock> SLAB_MALLORN = BLOCKS.register("slab_mallorn", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_MALLORN = BLOCKS.register("door_mallorn", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MALLORN = BLOCKS.register("fence_gate_mallorn", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MALLORN = BLOCKS.register("fence_mallorn", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MALLORN = BLOCKS.register("trapdoor_mallorn", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_MALLORN = BLOCKS.register("torch_mallorn", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.mallorn_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_MALLORN = BLOCKS.register("wall_torch_mallorn", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.mallorn_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_MALLORN = BLOCKS.register("pressure_plate_mallorn", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> MALLORN_SIGN = BLOCKS.register("sign_mallorn", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), MALLORN));
    public static RegistryObject<Block> MALLORN_WALL_SIGN = BLOCKS.register("wall_sign_mallorn", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(MALLORN_SIGN.get()), MALLORN));
    public static RegistryObject<Block> MALLORN_BUTTON = BLOCKS.register("mallorn_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_MALLORN = BLOCKS.register("sapling_mallorn", () -> new SaplingBlock(new MallornTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAVES_MALLORN = BLOCKS.register("leaves_mallorn", () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TolkienBlocks::ocelotOrParrot).isSuffocating(TolkienBlocks::never).isViewBlocking(TolkienBlocks::never)));
    public static RegistryObject<Block> LEAFPILE_MALLORN = BLOCKS.register("leafpile_mallorn", () -> new LeafPileBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_MALLORN = BLOCKS.register("block_barrel_mallorn", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Mirkwood
    public static RegistryObject<Block> LOG_MIRKWOOD = BLOCKS.register("log_mirkwood", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<Block> WOOD_MIRKWOOD = BLOCKS.register("wood_mirkwood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_MIRKWOOD_LOG = BLOCKS.register("stripped_log_mirkwood", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_BLACK));
    public static RegistryObject<Block> STRIPPED_MIRKWOOD_WOOD = BLOCKS.register("stripped_wood_mirkwood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_MIRKWOOD = BLOCKS.register("planks_mirkwood", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairBlock> STAIRS_MIRKWOOD = BLOCKS.register("stairs_mirkwood", () -> new StairBlock(PLANKS_MIRKWOOD.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_MIRKWOOD.get())));
    public static RegistryObject<SlabBlock> SLAB_MIRKWOOD = BLOCKS.register("slab_mirkwood", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_MIRKWOOD = BLOCKS.register("door_mirkwood", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_MIRKWOOD = BLOCKS.register("fence_gate_mirkwood", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_MIRKWOOD = BLOCKS.register("fence_mirkwood", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MIRKWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_MIRKWOOD = BLOCKS.register("trapdoor_mirkwood", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_MIRKWOOD = BLOCKS.register("torch_mirkwood", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.mirkwood_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_MIRKWOOD = BLOCKS.register("wall_torch_mirkwood", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.mirkwood_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_MIRKWOOD = BLOCKS.register("pressure_plate_mirkwood", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> MIRKWOOD_SIGN = BLOCKS.register("sign_mirkwood", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), MIRKWOOD));
    public static RegistryObject<Block> MIRKWOOD_WALL_SIGN = BLOCKS.register("wall_sign_mirkwood", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(MIRKWOOD_SIGN.get()), MIRKWOOD));
    public static RegistryObject<Block> MIRKWOOD_BUTTON = BLOCKS.register("mirkwood_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_MIRKWOOD = BLOCKS.register("sapling_mirkwood", () -> new SaplingBlock(new MirkwoodTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAVES_MIRKWOOD = BLOCKS.register("leaves_mirkwood", () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TolkienBlocks::ocelotOrParrot).isSuffocating(TolkienBlocks::never).isViewBlocking(TolkienBlocks::never)));
    public static RegistryObject<Block> LEAFPILE_MIRKWOOD = BLOCKS.register("leafpile_mirkwood", () -> new LeafPileBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_BLACK).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_MIRKWOOD = BLOCKS.register("block_barrel_mirkwood", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLACK).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Culumalda
    public static RegistryObject<Block> LOG_CULUMALDA = BLOCKS.register("log_culumalda", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<Block> WOOD_CULUMALDA = BLOCKS.register("wood_culumalda", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_CULUMALDA_LOG = BLOCKS.register("stripped_log_culumalda", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_GRAY));
    public static RegistryObject<Block> STRIPPED_CULUMALDA_WOOD = BLOCKS.register("stripped_wood_culumalda", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_CULUMALDA = BLOCKS.register("planks_culumalda", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairBlock> STAIRS_CULUMALDA = BLOCKS.register("stairs_culumalda", () -> new StairBlock(PLANKS_CULUMALDA.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_CULUMALDA.get())));
    public static RegistryObject<SlabBlock> SLAB_CULUMALDA = BLOCKS.register("slab_culumalda", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_CULUMALDA = BLOCKS.register("door_culumalda", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_CULUMALDA = BLOCKS.register("fence_gate_culumalda", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_CULUMALDA = BLOCKS.register("fence_culumalda", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_CULUMALDA.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_CULUMALDA = BLOCKS.register("trapdoor_culumalda", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_CULUMALDA = BLOCKS.register("torch_culumalda", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.culumalda_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_CULUMALDA = BLOCKS.register("wall_torch_culumalda", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.culumalda_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_CULUMALDA = BLOCKS.register("pressure_plate_culumalda", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> CULUMALDA_SIGN = BLOCKS.register("sign_culumalda", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), CULUMALDA));
    public static RegistryObject<Block> CULUMALDA_WALL_SIGN = BLOCKS.register("wall_sign_culumalda", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CULUMALDA_SIGN.get()), CULUMALDA));
    public static RegistryObject<Block> CULUMALDA_BUTTON = BLOCKS.register("culumalda_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_CULUMALDA = BLOCKS.register("sapling_culumalda", () -> new SaplingBlock(new CulumaldaTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAVES_CULUMALDA = BLOCKS.register("leaves_culumalda", () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TolkienBlocks::ocelotOrParrot).isSuffocating(TolkienBlocks::never).isViewBlocking(TolkienBlocks::never)));
    public static RegistryObject<Block> LEAFPILE_CULUMALDA = BLOCKS.register("leafpile_culumalda", () -> new LeafPileBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_CULUMALDA = BLOCKS.register("block_barrel_culumalda", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Lebethron
    public static RegistryObject<Block> LOG_LEBETHRON = BLOCKS.register("log_lebethron", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<Block> WOOD_LEBETHRON = BLOCKS.register("wood_lebethron", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_LEBETHRON_LOG = BLOCKS.register("stripped_log_lebethron", () -> log(MaterialColor.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY));
    public static RegistryObject<Block> STRIPPED_LEBETHRON_WOOD = BLOCKS.register("stripped_wood_lebethron", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_LEBETHRON = BLOCKS.register("planks_lebethron", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairBlock> STAIRS_LEBETHRON = BLOCKS.register("stairs_lebethron", () -> new StairBlock(PLANKS_LEBETHRON.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_LEBETHRON.get())));
    public static RegistryObject<SlabBlock> SLAB_LEBETHRON = BLOCKS.register("slab_lebethron", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_LEBETHRON = BLOCKS.register("door_lebethron", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_LEBETHRON = BLOCKS.register("fence_gate_lebethron", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_LEBETHRON = BLOCKS.register("fence_lebethron", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_LEBETHRON.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_LEBETHRON = BLOCKS.register("trapdoor_lebethron", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_LEBETHRON = BLOCKS.register("torch_lebethron", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.lebethron_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_LEBETHRON = BLOCKS.register("wall_torch_lebethron", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.lebethron_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_LEBETHRON = BLOCKS.register("pressure_plate_lebethron", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> LEBETHRON_SIGN = BLOCKS.register("sign_lebethron", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), LEBETHRON));
    public static RegistryObject<Block> LEBETHRON_WALL_SIGN = BLOCKS.register("wall_sign_lebethron", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(LEBETHRON_SIGN.get()), LEBETHRON));
    public static RegistryObject<Block> LEBETHRON_BUTTON = BLOCKS.register("lebethron_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_LEBETHRON = BLOCKS.register("sapling_lebethron", () -> new SaplingBlock(new LebethronTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAVES_LEBETHRON = BLOCKS.register("leaves_lebethron", () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TolkienBlocks::ocelotOrParrot).isSuffocating(TolkienBlocks::never).isViewBlocking(TolkienBlocks::never)));
    public static RegistryObject<Block> LEAFPILE_LEBETHRON = BLOCKS.register("leafpile_lebethron", () -> new LeafPileBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_LEBETHRON = BLOCKS.register("block_barrel_lebethron", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Deadwood
    public static RegistryObject<Block> LOG_DEADWOOD = BLOCKS.register("log_deadwood", () -> log(MaterialColor.WOOD, MaterialColor.COLOR_ORANGE));
    public static RegistryObject<Block> WOOD_DEADWOOD = BLOCKS.register("wood_deadwood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_DEADWOOD_LOG = BLOCKS.register("stripped_log_deadwood", () -> log(MaterialColor.WOOD, MaterialColor.COLOR_ORANGE));
    public static RegistryObject<Block> STRIPPED_DEADWOOD_WOOD = BLOCKS.register("stripped_wood_deadwood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_DEADWOOD = BLOCKS.register("planks_deadwood", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<StairBlock> STAIRS_DEADWOOD = BLOCKS.register("stairs_deadwood", () -> new StairBlock(PLANKS_DEADWOOD.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_DEADWOOD.get())));
    public static RegistryObject<SlabBlock> SLAB_DEADWOOD = BLOCKS.register("slab_deadwood", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD).noOcclusion()));
    public static RegistryObject<DoorBlock> DOOR_DEADWOOD = BLOCKS.register("door_deadwood", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_DEADWOOD.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_DEADWOOD = BLOCKS.register("fence_gate_deadwood", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_DEADWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_DEADWOOD = BLOCKS.register("fence_deadwood", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_DEADWOOD.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_DEADWOOD = BLOCKS.register("trapdoor_deadwood", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_DEADWOOD = BLOCKS.register("torch_deadwood", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 9;
    }).sound(SoundType.WOOD), TolkienParticles.deadwood_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_DEADWOOD = BLOCKS.register("wall_torch_deadwood", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 9;
    }).sound(SoundType.WOOD), TolkienParticles.deadwood_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_DEADWOOD = BLOCKS.register("pressure_plate_deadwood", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> DEADWOOD_SIGN = BLOCKS.register("sign_deadwood", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), DEADWOOD));
    public static RegistryObject<Block> DEADWOOD_WALL_SIGN = BLOCKS.register("wall_sign_deadwood", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(DEADWOOD_SIGN.get()), DEADWOOD));
    public static RegistryObject<Block> DEADWOOD_BUTTON = BLOCKS.register("deadwood_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_DEADWOOD = BLOCKS.register("sapling_deadwood", () -> new SaplingBlock(new DeadTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_DEADWOOD = BLOCKS.register("block_barrel_deadwood", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Fangorn Oak
    public static RegistryObject<Block> LOG_FANGORNOAK = BLOCKS.register("log_fangornoak", () -> log(MaterialColor.WOOD, MaterialColor.COLOR_LIGHT_GREEN));
    public static RegistryObject<Block> WOOD_FANGORNOAK = BLOCKS.register("wood_fangornoak", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> STRIPPED_FANGORNOAK_LOG = BLOCKS.register("stripped_log_fangornoak", () -> log(MaterialColor.WOOD, MaterialColor.COLOR_LIGHT_GREEN));
    public static RegistryObject<Block> STRIPPED_FANGORNOAK_WOOD = BLOCKS.register("stripped_wood_fangornoak", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> PLANKS_FANGORNOAK = BLOCKS.register("planks_fangornoak", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<StairBlock> STAIRS_FANGORNOAK = BLOCKS.register("stairs_fangornoak", () -> new StairBlock(PLANKS_FANGORNOAK.get().defaultBlockState(), BlockBehaviour.Properties.copy(PLANKS_FANGORNOAK.get())));
    public static RegistryObject<SlabBlock> SLAB_FANGORNOAK = BLOCKS.register("slab_fangornoak", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD)));
    public static RegistryObject<DoorBlock> DOOR_FANGORNOAK = BLOCKS.register("door_fangornoak", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_FANGORNOAK.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static RegistryObject<FenceGateBlock> FENCE_GATE_FANGORNOAK = BLOCKS.register("fence_gate_fangornoak", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_FANGORNOAK.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<FenceBlock> FENCE_FANGORNOAK = BLOCKS.register("fence_fangornoak", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PLANKS_FANGORNOAK.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static RegistryObject<TrapDoorBlock> TRAPDOOR_FANGORNOAK = BLOCKS.register("trapdoor_fangornoak", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(TolkienBlocks::never)));
    public static RegistryObject<TorchBlock> TORCH_FANGORNOAK = BLOCKS.register("torch_fangornoak", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.fangornoak_flame));
    public static RegistryObject<TorchBlock> WALL_TORCH_FANGORNOAK = BLOCKS.register("wall_torch_fangornoak", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_235470_0_) -> {
        return 14;
    }).sound(SoundType.WOOD), TolkienParticles.fangornoak_flame));
    public static RegistryObject<Block> PRESSURE_PLATE_FANGORNOAK = BLOCKS.register("pressure_plate_fangornoak", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PLANKS_MALLORN.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> FANGORNOAK_SIGN = BLOCKS.register("sign_fangornoak", () -> new TolkienStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD), FANGORNOAK));
    public static RegistryObject<Block> FANGORNOAK_WALL_SIGN = BLOCKS.register("wall_sign_fangornoak", () -> new TolkienWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(FANGORNOAK_SIGN.get()), FANGORNOAK));
    public static RegistryObject<Block> FANGORNOAK_BUTTON = BLOCKS.register("fangornoak_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).noCollission().strength(1.0F).sound(SoundType.WOOD)));
    public static RegistryObject<SaplingBlock> SAPLING_FANGORNOAK = BLOCKS.register("sapling_fangornoak", () -> new SaplingBlock(new FangornOakTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> LEAVES_FANGORNOAK = BLOCKS.register("leaves_fangornoak", () -> new LeafBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TolkienBlocks::ocelotOrParrot).isSuffocating(TolkienBlocks::never).isViewBlocking(TolkienBlocks::never)));
    public static RegistryObject<Block> LEAFPILE_FANGORNOAK = BLOCKS.register("leafpile_fangornoak", () -> new LeafPileBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_LIGHT_GREEN).strength(2.0F, 3.0F).noOcclusion().sound(SoundType.GRASS)));
    public static RegistryObject<Block> BARREL_FANGORNOAK = BLOCKS.register("block_barrel_fangornoak", () -> new BaseBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GREEN).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));

    // Plants & Flowers
    public static RegistryObject<Block> PIPEWEED = BLOCKS.register("pipeweed", () -> new CropsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_GREEN).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static RegistryObject<Block> MUSHROOM_DECAY_BLOOM = BLOCKS.register("mushroom_decay_bloom", () -> new MushroomsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> {
        return 1;
    }).hasPostProcess(TolkienBlocks::needsPostProcessing), () -> {
        return TreeFeature.MUSHROOM_DECAY_BLOOM;
    }));
    public static RegistryObject<Block> MUSHROOM_BLOOM_DECAY = BLOCKS.register("mushroom_bloom_decay", () -> new MushroomsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PURPLE).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> {
        return 1;
    }).hasPostProcess(TolkienBlocks::needsPostProcessing), () -> {
        return TreeFeature.MUSHROOM_BLOOM_DECAY;
    }));
    public static RegistryObject<Block> BLOCK_DECAY_BLOOM = BLOCKS.register("block_decay_bloom", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_MAGENTA).strength(0.2F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> BLOCK_BLOOM_DECAY = BLOCKS.register("block_bloom_decay", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_MAGENTA).strength(0.2F).sound(SoundType.WOOD)));
    public static RegistryObject<Block> FLOWER_SIMBELMYNE = BLOCKS.register("flower_simbelmyne", () -> new FlowerBlock(MobEffects.HERO_OF_THE_VILLAGE, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_MIRKWOOD = BLOCKS.register("flower_mirkwood", () -> new FlowerBlock(MobEffects.BAD_OMEN, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_ALFIRIN = BLOCKS.register("flower_alfirin", () -> new FlowerBlock(MobEffects.SATURATION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_ATHELAS = BLOCKS.register("flower_athelas", () -> new FlowerBlock(MobEffects.REGENERATION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_NIPHREDIL = BLOCKS.register("flower_niphredil", () -> new FlowerBlock(MobEffects.SATURATION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_SWAMPMILKWEED = BLOCKS.register("flower_swamp_milkweed", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static RegistryObject<Block> FLOWER_LILLYOFTHEVALLEY = BLOCKS.register("flower_valley_lilly", () -> new FlowerBlock(MobEffects.HEAL, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POTTED_MUSHROOM_DECAY_BLOOM = registerBlockWithoutBlockItem("potted_mushroom_decay_bloom", () -> new FlowerPotBlock(null, MUSHROOM_DECAY_BLOOM, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_MUSHROOM_BLOOM_DECAY = registerBlockWithoutBlockItem("potted_mushroom_bloom_decay", () -> new FlowerPotBlock(null, MUSHROOM_BLOOM_DECAY, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_SIMBELMYNE = registerBlockWithoutBlockItem("potted_flower_simbelmyne", () -> new FlowerPotBlock(null, FLOWER_SIMBELMYNE, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_MIRKWOOD = registerBlockWithoutBlockItem("potted_flower_mirkwood", () -> new FlowerPotBlock(null, FLOWER_MIRKWOOD, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_ALFIRIN = registerBlockWithoutBlockItem("potted_flower_alfirin", () -> new FlowerPotBlock(null, FLOWER_ALFIRIN, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_ATHELAS = registerBlockWithoutBlockItem("potted_flower_athelas", () -> new FlowerPotBlock(null, FLOWER_ATHELAS, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_NIPHREDIL = registerBlockWithoutBlockItem("potted_flower_niphredil", () -> new FlowerPotBlock(null, FLOWER_NIPHREDIL, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_SWAMPMILKWEED = registerBlockWithoutBlockItem("potted_flower_swamp_milkweed", () -> new FlowerPotBlock(null, FLOWER_SWAMPMILKWEED, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_FLOWER_LILLYOFTHEVALLEY = registerBlockWithoutBlockItem("potted_flower_valley_lilly", () -> new FlowerPotBlock(null, FLOWER_LILLYOFTHEVALLEY, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_MALLORN = registerBlockWithoutBlockItem("potted_sapling_mallorn", () -> new FlowerPotBlock(null, SAPLING_MALLORN, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_MIRKWOOD = registerBlockWithoutBlockItem("potted_sapling_mirkwood", () -> new FlowerPotBlock(null, SAPLING_MIRKWOOD, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_CULUMALDA = registerBlockWithoutBlockItem("potted_sapling_culumalda", () -> new FlowerPotBlock(null, SAPLING_CULUMALDA, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_LEBETHRON = registerBlockWithoutBlockItem("potted_sapling_lebethron", () -> new FlowerPotBlock(null, SAPLING_LEBETHRON, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_DEADWOOD = registerBlockWithoutBlockItem("potted_sapling_deadwood", () -> new FlowerPotBlock(null, SAPLING_DEADWOOD, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));
    public static final RegistryObject<Block> POTTED_SAPLING_FANGORNOAK = registerBlockWithoutBlockItem("potted_sapling_fangornoak", () -> new FlowerPotBlock(null, SAPLING_FANGORNOAK, BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()));

    // Custom
    public static RegistryObject<Block> BLOCK_HALLOWED = BLOCKS.register("block_hallowed", () -> new HallowedBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.GRAVEL).randomTicks()));
    public static RegistryObject<Block> STONE_PATH = BLOCKS.register("block_stone_path", () -> new StonePathBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GREEN).sound(SoundType.STONE).isViewBlocking(TolkienBlocks::needsPostProcessing).isSuffocating(TolkienBlocks::needsPostProcessing)));
    public static RegistryObject<Block> TTMFIREPLACE = BLOCKS.register("block_tmfireplace", () -> new FireplaceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f).lightLevel(litBlockEmission(15))));
    public static RegistryObject<Block> PIGGYBANK = BLOCKS.register("block_piggybank", () -> new PiggyBankBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));
    public static RegistryObject<Block> BARREL_MITHRIL = BLOCKS.register("block_barrel_mithril", () -> new MithrilBarrelBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));
    public static RegistryObject<Block> BARREL_MORGULIRON = BLOCKS.register("block_barrel_morguliron", () -> new MorgulironBarrelBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().noOcclusion().strength(5f, 6f)));
    public static RegistryObject<Block> BACKPACK = BLOCKS.register("backpack", () -> new BackpackBlock(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().noOcclusion().strength(1f, 1f)));
    public static RegistryObject<Block> PLACARD = BLOCKS.register("placard", () -> new PlacardBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).requiresCorrectToolForDrops().noOcclusion().strength(1f, 1f)));
    public static RegistryObject<Block> CHAMELEON_BLOCK = BLOCKS.register("chameleon_block", () -> new ChameleonBlock<>(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().noOcclusion()));
    public static RegistryObject<Block> KEY_STONE_BLOCK = BLOCKS.register("block_key_stone", () -> new CamoKeyStoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_GLOWSTONE_BLOCK = BLOCKS.register("block_camo_glowstone", () -> new CamoGlowstoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().lightLevel((state) -> 15)));
    public static RegistryObject<Block> LIGHTNINGBUG_BLOCK = BLOCKS.register("lightningbug", () -> new LightningBugBlock(BlockBehaviour.Properties.of(new Material.Builder(MaterialColor.GRASS).noCollider().nonSolid().build()).lightLevel((state) -> 15).sound(SoundType.SLIME_BLOCK).instabreak().noCollission()));
    public static RegistryObject<Block> CAMO_SMOKER_BLOCK = BLOCKS.register("block_camo_smoker", () -> new CamoSmokerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_FLUID_BLOCK = BLOCKS.register("block_camo_fluid", () -> new CamoFluidBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_CHEST_BLOCK = BLOCKS.register("block_camo_chest", () -> new CamoChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> CAMO_SPAWNER_BLOCK = BLOCKS.register("block_camo_spawner", () -> new CamoSpawnerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> MILESTONE_BLOCK = BLOCKS.register("milestone_block", () -> new MilestoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion().lightLevel(litBlockEmission(4))));
    public static RegistryObject<Block> LOCKABLE_CHEST_BLOCK = BLOCKS.register("lockable_chest_block", () -> new LockableChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_TREASURE_CHEST_BLOCK = BLOCKS.register("lockable_treasure_chest_block", () -> new LockableTreasureChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_DOUBLE_CHEST_BLOCK = BLOCKS.register("lockable_double_chest_block", () -> new LockableDoubleChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK = BLOCKS.register("lockable_double_treasure_chest_block", () -> new LockableDoubleTreasureChestBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion()));
    public static RegistryObject<Block> ROCKPILE = BLOCKS.register("rockpile", () -> new RockPileBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(5f, 6f).dynamicShape()));
    public static RegistryObject<Block> ELVEN_LANTERN = BLOCKS.register("elven_lantern", () -> new FancyLanternBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).sound(SoundType.LANTERN).requiresCorrectToolForDrops().strength(3.5F, 3.5F).noOcclusion().lightLevel(litBlockEmission(15))));
    public static RegistryObject<Block> MORGUL_LANTERN = BLOCKS.register("morgul_lantern", () -> new FancyLanternBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.TERRACOTTA_GRAY).sound(SoundType.LANTERN).requiresCorrectToolForDrops().strength(3.5F, 3.5F).noOcclusion().lightLevel(litBlockEmission(10))));
    public static RegistryObject<Block> TRINKET_TABLE = BLOCKS.register("trinket_table", () -> new TrinketTableBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.5F, 3.5F).noOcclusion()));
    public static RegistryObject<Block> ARDA_PORTAL = registerBlockWithoutBlockItem("arda_portal", () -> new ArdaPortalBlock(BlockBehaviour.Properties.of(Material.PORTAL).strength(-1F).noCollission().lightLevel((state) -> 10).noDrops()));

    // Custom - Sleeping Bags
    public static RegistryObject<Block> SLEEPING_BAG_BLUE = BLOCKS.register("sleeping_bag_blue", () -> new SleepingBagBlock(DyeColor.BLUE, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BLUE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_RED = BLOCKS.register("sleeping_bag_red", () -> new SleepingBagBlock(DyeColor.RED, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_RED : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_BLACK = BLOCKS.register("sleeping_bag_black", () -> new SleepingBagBlock(DyeColor.BLACK, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BLACK : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_BROWN = BLOCKS.register("sleeping_bag_brown", () -> new SleepingBagBlock(DyeColor.BROWN, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_BROWN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_CYAN = BLOCKS.register("sleeping_bag_cyan", () -> new SleepingBagBlock(DyeColor.CYAN, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_CYAN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_GRAY = BLOCKS.register("sleeping_bag_gray", () -> new SleepingBagBlock(DyeColor.GRAY, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_GRAY : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_GREEN = BLOCKS.register("sleeping_bag_green", () -> new SleepingBagBlock(DyeColor.GREEN, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_GREEN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIGHT_BLUE = BLOCKS.register("sleeping_bag_light_blue", () -> new SleepingBagBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_BLUE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIGHT_GRAY = BLOCKS.register("sleeping_bag_light_gray", () -> new SleepingBagBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_GRAY : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_LIME = BLOCKS.register("sleeping_bag_lime", () -> new SleepingBagBlock(DyeColor.LIME, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_LIGHT_GREEN : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_MAGENTA = BLOCKS.register("sleeping_bag_magenta", () -> new SleepingBagBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_MAGENTA : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_ORANGE = BLOCKS.register("sleeping_bag_orange", () -> new SleepingBagBlock(DyeColor.ORANGE, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_ORANGE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_PINK = BLOCKS.register("sleeping_bag_pink", () -> new SleepingBagBlock(DyeColor.PINK, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_PINK : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_PURPLE = BLOCKS.register("sleeping_bag_purple", () -> new SleepingBagBlock(DyeColor.PURPLE, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_PURPLE : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_WHITE = BLOCKS.register("sleeping_bag_white", () -> new SleepingBagBlock(DyeColor.WHITE, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.SNOW : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));
    public static RegistryObject<Block> SLEEPING_BAG_YELLOW = BLOCKS.register("sleeping_bag_yellow", () -> new SleepingBagBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
        return state.getValue(BedBlock.PART) == BedPart.FOOT ? MaterialColor.COLOR_RED : MaterialColor.WOOL;
    }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission()));



    private static boolean needsPostProcessing(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }

    private static RotatedPillarBlock log(MaterialColor materialColor1, MaterialColor materialColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? materialColor1 : materialColor2;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    private static boolean never(BlockState blockState, BlockGetter getter, BlockPos pos) {
        return false;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter getter, BlockPos pos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;// || entityType == TolkienEntities.ENTITY_TTM_SQUIRREL.get();
    }

    public String getName() {
        return NAME + " - Blocks";
    }
}
