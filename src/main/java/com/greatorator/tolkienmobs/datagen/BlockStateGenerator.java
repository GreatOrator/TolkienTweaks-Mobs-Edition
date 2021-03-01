package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

/**
 * Created by brandon3055 on 28/2/20.
 */
public class BlockStateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogManager.getLogger();

    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TolkienMobs.MODID, exFileHelper);
    }

    //So this is where blockStates are generated. This is also where you generate simple block models or implement more advanced custom models.

    @Override
    protected void registerStatesAndModels() {
        // Basic - Metals & Gems
        simpleBlock(TTMContent.ORE_MITHRIL.get());
        simpleBlock(TTMContent.ORE_END_MITHRIL.get());
        simpleBlock(TTMContent.ORE_NETHER_MITHRIL.get());
        simpleBlock(TTMContent.BLOCK_MITHRIL.get());
        paneBlock(TTMContent.MITHRIL_BARS.get(), modLoc("block/mithril_bars"), modLoc("block/mithril_bars"));
        doorBlock(TTMContent.DOOR_MITHRIL.get(), "door_mithril", modLoc("block/door_mithril_bottom"), modLoc("block/door_mithril_top"));
        simpleBlock(TTMContent.ORE_MORGULIRON.get());
        simpleBlock(TTMContent.ORE_END_MORGULIRON.get());
        simpleBlock(TTMContent.ORE_NETHER_MORGULIRON.get());
        simpleBlock(TTMContent.BLOCK_MORGULIRON.get());
        paneBlock(TTMContent.MORGULIRON_BARS.get(), modLoc("block/morguliron_bars"), modLoc("block/morguliron_bars"));
        doorBlock(TTMContent.DOOR_MORGULIRON.get(), "door_morguliron", modLoc("block/door_morguliron_bottom"), modLoc("block/door_morguliron_top"));
        simpleBlock(TTMContent.ORE_AMMOLITE.get());
        simpleBlock(TTMContent.ORE_END_AMMOLITE.get());
        simpleBlock(TTMContent.ORE_NETHER_AMMOLITE.get());

        // Basic Wood & Foliage
        logBlock(TTMContent.LOG_MALLORN.get());
        logBlock(TTMContent.LOG_MIRKWOOD.get());
        logBlock(TTMContent.LOG_CULUMALDA.get());
        logBlock(TTMContent.LOG_LEBETHRON.get());
        logBlock(TTMContent.LOG_DEADWOOD.get());
        simpleBlock(TTMContent.PLANKS_MALLORN.get());
        simpleBlock(TTMContent.PLANKS_MIRKWOOD.get());
        simpleBlock(TTMContent.PLANKS_CULUMALDA.get());
        simpleBlock(TTMContent.PLANKS_LEBETHRON.get());
        slabBlock(TTMContent.SLAB_MALLORN.get(), modLoc("block/planks_mallorn"), modLoc("block/planks_mallorn"));
        slabBlock(TTMContent.SLAB_MIRKWOOD.get(), modLoc("block/planks_mirkwood"), modLoc("block/planks_mirkwood"));
        slabBlock(TTMContent.SLAB_CULUMALDA.get(), modLoc("block/planks_culumalda"), modLoc("block/planks_culumalda"));
        slabBlock(TTMContent.SLAB_LEBETHRON.get(), modLoc("block/planks_lebethron"), modLoc("block/planks_lebethron"));
        stairsBlock(TTMContent.STAIRS_MALLORN.get(), modLoc("block/planks_mallorn"));
        stairsBlock(TTMContent.STAIRS_MIRKWOOD.get(), modLoc("block/planks_mirkwood"));
        stairsBlock(TTMContent.STAIRS_CULUMALDA.get(), modLoc("block/planks_culumalda"));
        stairsBlock(TTMContent.STAIRS_LEBETHRON.get(), modLoc("block/planks_lebethron"));
        doorBlock(TTMContent.DOOR_MALLORN.get(), "door_mallorn", modLoc("block/door_mallorn_bottom"), modLoc("block/door_mallorn_top"));
        doorBlock(TTMContent.DOOR_MIRKWOOD.get(), "door_mirkwood", modLoc("block/door_mirkwood_bottom"), modLoc("block/door_mirkwood_top"));
        doorBlock(TTMContent.DOOR_CULUMALDA.get(), "door_culumalda", modLoc("block/door_culumalda_bottom"), modLoc("block/door_culumalda_top"));
        doorBlock(TTMContent.DOOR_LEBETHRON.get(), "door_lebethron", modLoc("block/door_lebethron_bottom"), modLoc("block/door_lebethron_top"));
        fenceGateBlock(TTMContent.FENCE_GATE_MALLORN.get(), "fence_gate_mallorn", modLoc("block/planks_mallorn"));
        fenceGateBlock(TTMContent.FENCE_GATE_MIRKWOOD.get(), "fence_gate_mirkwood", modLoc("block/planks_mirkwood"));
        fenceGateBlock(TTMContent.FENCE_GATE_CULUMALDA.get(), "fence_gate_culumalda", modLoc("block/planks_culumalda"));
        fenceGateBlock(TTMContent.FENCE_GATE_LEBETHRON.get(), "fence_gate_lebethron", modLoc("block/planks_lebethron"));
        fenceBlock(TTMContent.FENCE_MALLORN.get(), "fence_mallorn", modLoc("block/planks_mallorn"));
        fenceBlock(TTMContent.FENCE_MIRKWOOD.get(), "fence_mirkwood", modLoc("block/planks_mirkwood"));
        fenceBlock(TTMContent.FENCE_CULUMALDA.get(), "fence_culumalda", modLoc("block/planks_culumalda"));
        fenceBlock(TTMContent.FENCE_LEBETHRON.get(), "fence_lebethron", modLoc("block/planks_lebethron"));
        simpleBlock(TTMContent.LEAVES_MALLORN.get());
        simpleBlock(TTMContent.LEAVES_MIRKWOOD.get());
        simpleBlock(TTMContent.LEAVES_CULUMALDA.get());
        simpleBlock(TTMContent.LEAVES_LEBETHRON.get());
        simpleBlock(TTMContent.SAPLING_MALLORN.get(), models().cross("sapling_mallorn", modLoc("block/sapling_mallorn")));
        simpleBlock(TTMContent.SAPLING_MIRKWOOD.get(), models().cross("sapling_mirkwood", modLoc("block/sapling_mirkwood")));
        simpleBlock(TTMContent.SAPLING_CULUMALDA.get(), models().cross("sapling_culumalda", modLoc("block/sapling_culumalda")));
        simpleBlock(TTMContent.SAPLING_LEBETHRON.get(), models().cross("sapling_lebethron", modLoc("block/sapling_lebethron")));
        simpleBlock(TTMContent.SAPLING_DEADWOOD.get(), models().cross("sapling_deadwood", modLoc("block/sapling_deadwood")));

        // Basic Plants & Flowers
        simpleBlock(TTMContent.MUSHROOM_DECAY_BLOOM.get(), models().cross("mushroom_decay_bloom", modLoc("block/mushroom_decay_bloom")));
        simpleBlock(TTMContent.MUSHROOM_BLOOM_DECAY.get(), models().cross("mushroom_bloom_decay", modLoc("block/mushroom_bloom_decay")));
        simpleBlock(TTMContent.FLOWER_SIMBELMYNE.get(), models().cross("flower_simbelmyne", modLoc("block/flower_simbelmyne")));
        simpleBlock(TTMContent.FLOWER_MIRKWOOD.get(), models().cross("flower_mirkwood", modLoc("block/flower_mirkwood")));
        simpleBlock(TTMContent.FLOWER_ALFIRIN.get(), models().cross("flower_alfirin", modLoc("block/flower_alfirin")));
        simpleBlock(TTMContent.FLOWER_ATHELAS.get(), models().cross("flower_athelas", modLoc("block/flower_athelas")));
        simpleBlock(TTMContent.FLOWER_NIPHREDIL.get(), models().cross("flower_niphredil", modLoc("block/flower_niphredil")));
        simpleBlock(TTMContent.FLOWER_SWAMPMILKWEED.get(), models().cross("flower_swamp_milkweed", modLoc("block/flower_swamp_milkweed")));
        simpleBlock(TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), models().cross("flower_valley_lilly", modLoc("block/flower_valley_lilly")));

        // Custom
        simpleBlock(TTMContent.BLOCK_HALLOWED.get(), models().cubeBottomTop("block_hallowed", modLoc("block/block_hallowed_side"), modLoc("block/block_hallowed"), modLoc("block/block_hallowed_top")));
        simpleBlock(TTMContent.STONE_PATH.get(), models().getExistingFile(modLoc("block/block_stone_path")));



//        simpleBlock(DEContent.block_draconium);
//        simpleBlock(DEContent.block_draconium_awakened, models().cubeBottomTop("awakened_draconium_block", modLoc("block/awakened_draconium_block_side"), modLoc("block/awakened_draconium_block"), modLoc("block/awakened_draconium_block")));
//        simpleBlock(DEContent.ore_draconium_end);
//        simpleBlock(DEContent.ore_draconium_nether);
//        simpleBlock(DEContent.ore_draconium_overworld);
//        simpleBlock(DEContent.infused_obsidian);
//        simpleBlock(DEContent.energy_core);
//        simpleBlock(DEContent.energy_core_stabilizer, models().getExistingFile(modLoc("block/energy_core_stabilizer")));
//        simpleBlock(DEContent.creative_op_capacitor);
//        simpleBlock(DEContent.stabilized_spawner, models().getExistingFile(modLoc("block/stabilized_spawner")));
//        simpleBlock(DEContent.particle_generator, models().getExistingFile(modLoc("block/particle_generator")));
//        simpleBlock(DEContent.crafting_core, models().getExistingFile(modLoc("block/crafting/fusion_crafting_core")));
//
//        simpleBlock(DEContent.dislocation_inhibitor, models().cubeBottomTop("dislocation_inhibitor", modLoc("block/dislocation_inhibitor"), modLoc("block/parts/machine_top"), modLoc("block/parts/machine_top")));
//
//        directionalBlock(DEContent.crafting_injector_basic, models().getExistingFile(modLoc("block/crafting/crafting_injector_draconium")));
//        directionalBlock(DEContent.crafting_injector_wyvern, models().getExistingFile(modLoc("block/crafting/crafting_injector_wyvern")));
//        directionalBlock(DEContent.crafting_injector_awakened, models().getExistingFile(modLoc("block/crafting/crafting_injector_draconic")));
//        directionalBlock(DEContent.crafting_injector_chaotic, models().getExistingFile(modLoc("block/crafting/crafting_injector_chaotic")));
//
//        directionalFromNorth(DEContent.fluid_gate, models().getExistingFile(modLoc("block/fluid_gate")));
//        directionalFromNorth(DEContent.flux_gate, models().getExistingFile(modLoc("block/flux_gate")));
//
//        directionalBlock(DEContent.potentiometer, models().getExistingFile(modLoc("block/potentiometer")));
//
//        simpleBlock(DEContent.energy_transfuser, models().getExistingFile(modLoc("block/energy_transfuser")));
//
//        dummyBlock(DEContent.crystal_io_basic);
//        dummyBlock(DEContent.crystal_io_wyvern);
//        dummyBlock(DEContent.crystal_io_draconic);
////        dummyBlock(DEContent.  crystal_io_chaotic);
//        dummyBlock(DEContent.crystal_relay_basic);
//        dummyBlock(DEContent.crystal_relay_wyvern);
//        dummyBlock(DEContent.crystal_relay_draconic);
////        dummyBlock(DEContent.  crystal_relay_chaotic);
//        dummyBlock(DEContent.crystal_wireless_basic);
//        dummyBlock(DEContent.crystal_wireless_wyvern);
//        dummyBlock(DEContent.crystal_wireless_draconic);
////        dummyBlock(DEContent.  crystal_wireless_chaotic);
//        dummyBlock(DEContent.energy_core_structure);
//        dummyBlock(DEContent.chaos_crystal);
//        dummyBlock(DEContent.chaos_crystal_part);
//
//
//        getVariantBuilder(DEContent.energy_pylon).forAllStates(state -> ConfiguredModel.builder().modelFile(models().cubeBottomTop(state.get(EnergyPylon.OUTPUT) ? "energy_pylon_output" : "energy_pylon_input", modLoc("block/energy_pylon/energy_pylon_" + (state.get(EnergyPylon.OUTPUT) ? "output" : "input")), modLoc("block/energy_pylon/energy_pylon_active_face"), modLoc("block/energy_pylon/energy_pylon_active_face"))).build());
//
//
//        //Generator
//        ModelFile modelGenerator = models().getExistingFile(modLoc("block/generator/generator"));
//        ModelFile modelGeneratorFlame = models().getExistingFile(modLoc("block/generator/generator_flame"));
//        MultiPartBlockStateBuilder generatorBuilder = getMultipartBuilder(DEContent.generator);
//        for (Direction dir : FenceGateBlock.HORIZONTAL_FACING.getAllowedValues()) {
//            int angle = (int) dir.getOpposite().getHorizontalAngle();
//            generatorBuilder.part().modelFile(modelGenerator).rotationY(angle).addModel().condition(Generator.FACING, dir).end()
//                    .part().modelFile(modelGeneratorFlame).rotationY(angle).addModel().condition(Generator.FACING, dir).condition(Generator.ACTIVE, true).end();
//        }
//
//        //Grinder
//        ModelFile modelGrinder = models().getExistingFile(modLoc("block/grinder/grinder"));
//        ModelFile modelGrinderActive = models().getExistingFile(modLoc("block/grinder/grinder_eyes"));
//        MultiPartBlockStateBuilder grinderBuilder = getMultipartBuilder(DEContent.grinder);
//        for (Direction dir : Direction.BY_HORIZONTAL_INDEX) {
//            int angle = (int) dir.getOpposite().getHorizontalAngle();
//            grinderBuilder.part().modelFile(modelGrinder).rotationY(angle).addModel().condition(Grinder.FACING, dir).end()
//                    .part().modelFile(modelGrinderActive).rotationY(angle).addModel().condition(Grinder.FACING, dir).condition(Grinder.ACTIVE, true).end();
//        }



        //Bellow is some extra example stuff i left in for reference
        if (true) return;


        // Unnecessarily complicated example to showcase how manual building works
        ModelFile birchFenceGate = models().fenceGate("birch_fence_gate", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateOpen = models().fenceGateOpen("birch_fence_gate_open", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateWall = models().fenceGateWall("birch_fence_gate_wall", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateWallOpen = models().fenceGateWallOpen("birch_fence_gate_wall_open", mcLoc("block/birch_planks"));
        ModelFile invisbleModel = new ModelFile.UncheckedModelFile(new ResourceLocation("builtin/generated"));
        VariantBlockStateBuilder builder = getVariantBuilder(Blocks.BIRCH_FENCE_GATE);
        for (Direction dir : FenceGateBlock.HORIZONTAL_FACING.getAllowedValues()) {
            int angle = (int) dir.getHorizontalAngle();
            builder
                    .partialState()
                    .with(FenceGateBlock.HORIZONTAL_FACING, dir)
                    .with(FenceGateBlock.IN_WALL, false)
                    .with(FenceGateBlock.OPEN, false)
                    .modelForState()
                    .modelFile(invisbleModel)
                    .nextModel()
                    .modelFile(birchFenceGate)
                    .rotationY(angle)
                    .uvLock(true)
                    .weight(100)
                    .addModel()
                    .partialState()
                    .with(FenceGateBlock.HORIZONTAL_FACING, dir)
                    .with(FenceGateBlock.IN_WALL, false)
                    .with(FenceGateBlock.OPEN, true)
                    .modelForState()
                    .modelFile(birchFenceGateOpen)
                    .rotationY(angle)
                    .uvLock(true)
                    .addModel()
                    .partialState()
                    .with(FenceGateBlock.HORIZONTAL_FACING, dir)
                    .with(FenceGateBlock.IN_WALL, true)
                    .with(FenceGateBlock.OPEN, false)
                    .modelForState()
                    .modelFile(birchFenceGateWall)
                    .rotationY(angle)
                    .uvLock(true)
                    .addModel()
                    .partialState()
                    .with(FenceGateBlock.HORIZONTAL_FACING, dir)
                    .with(FenceGateBlock.IN_WALL, true)
                    .with(FenceGateBlock.OPEN, true)
                    .modelForState()
                    .modelFile(birchFenceGateWallOpen)
                    .rotationY(angle)
                    .uvLock(true)
                    .addModel();
        }

        // Realistic examples using helpers
//            simpleBlock(Blocks.STONE, model -> ObjectArrays.concat(
//                    ConfiguredModel.allYRotations(model, 0, false),
//                    ConfiguredModel.allYRotations(model, 180, false),
//                    ConfiguredModel.class));

        // From here on, models are 1-to-1 copies of vanilla (except for model locations) and will be tested as such below
        ModelFile block = models().getBuilder("block").transforms()
                .transform(ModelBuilder.Perspective.GUI)
                .rotation(30, 225, 0)
                .scale(0.625f)
                .end()
                .transform(ModelBuilder.Perspective.GROUND)
                .translation(0, 3, 0)
                .scale(0.25f)
                .end()
                .transform(ModelBuilder.Perspective.FIXED)
                .scale(0.5f)
                .end()
                .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
                .rotation(75, 45, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
                .rotation(0, 45, 0)
                .scale(0.4f)
                .end()
                .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
                .rotation(0, 225, 0)
                .scale(0.4f)
                .end()
                .end();

        models().getBuilder("cube")
                .parent(block)
                .element()
                .allFaces((dir, face) -> face.texture("#" + dir.getString()).cullface(dir));


        ModelFile furnace = models().orientable("furnace", mcLoc("block/furnace_side"), mcLoc("block/furnace_front"), mcLoc("block/furnace_top"));
        ModelFile furnaceLit = models().orientable("furnace_on", mcLoc("block/furnace_side"), mcLoc("block/furnace_front_on"), mcLoc("block/furnace_top"));

        getVariantBuilder(Blocks.FURNACE)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(state.get(FurnaceBlock.LIT) ? furnaceLit : furnace)
                        .rotationY((int) state.get(FurnaceBlock.FACING).getOpposite().getHorizontalAngle())
                        .build()
                );

        ModelFile barrel = models().cubeBottomTop("barrel", mcLoc("block/barrel_side"), mcLoc("block/barrel_bottom"), mcLoc("block/barrel_top"));
        ModelFile barrelOpen = models().cubeBottomTop("barrel_open", mcLoc("block/barrel_side"), mcLoc("block/barrel_bottom"), mcLoc("block/barrel_top_open"));
        directionalBlock(Blocks.BARREL, state -> state.get(BarrelBlock.PROPERTY_OPEN) ? barrelOpen : barrel); // Testing custom state interpreter

//        logBlock((LogBlock) Blocks.ACACIA_LOG);

        stairsBlock((StairsBlock) Blocks.ACACIA_STAIRS, "acacia", mcLoc("block/acacia_planks"));
        slabBlock((SlabBlock) Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS.getRegistryName(), mcLoc("block/acacia_planks"));

        fenceBlock((FenceBlock) Blocks.ACACIA_FENCE, "acacia", mcLoc("block/acacia_planks"));
        fenceGateBlock((FenceGateBlock) Blocks.ACACIA_FENCE_GATE, "acacia", mcLoc("block/acacia_planks"));

        wallBlock((WallBlock) Blocks.COBBLESTONE_WALL, "cobblestone", mcLoc("block/cobblestone"));

        paneBlock((PaneBlock) Blocks.GLASS_PANE, "glass", mcLoc("block/glass"), mcLoc("block/glass_pane_top"));

        doorBlock((DoorBlock) Blocks.ACACIA_DOOR, "acacia", mcLoc("block/acacia_door_bottom"), mcLoc("block/acacia_door_top"));
        trapdoorBlock((TrapDoorBlock) Blocks.ACACIA_TRAPDOOR, "acacia", mcLoc("block/acacia_trapdoor"), true);
        trapdoorBlock((TrapDoorBlock) Blocks.OAK_TRAPDOOR, "oak", mcLoc("block/oak_trapdoor"), false); // Test a non-orientable trapdoor

        simpleBlock(Blocks.TORCH, models().torch("torch", mcLoc("block/torch")));
        horizontalBlock(Blocks.WALL_TORCH, models().torchWall("wall_torch", mcLoc("block/torch")), 90);
    }


    private void dummyBlock(Block block) {
        ModelFile model = models()//
                .withExistingParent("dummy", "block")//
                .texture("particle", "minecraft:block/glass");
        simpleBlock(block, model);
    }

    public void directionalFromNorth(Block block, ModelFile model) {
        directionalFromNorth(block, model, 180);
    }

    public void directionalFromNorth(Block block, ModelFile model, int angleOffset) {
        directionalFromNorth(block, $ -> model, angleOffset);
    }

    public void directionalFromNorth(Block block, Function<BlockState, ModelFile> modelFunc) {
        directionalFromNorth(block, modelFunc, 180);
    }

    public void directionalFromNorth(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.get(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir == Direction.DOWN ? 90 : dir == Direction.UP ? -90 : 0)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.getHorizontalAngle()) + angleOffset) % 360)
                            .build();
                });
    }

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Blockstates";
    }
}
