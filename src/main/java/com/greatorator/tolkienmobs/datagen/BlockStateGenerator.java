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
        trapdoorBlock(TTMContent.TRAPDOOR_MALLORN.get(), "mallorn", modLoc("block/trapdoor_mallorn"), true);
        trapdoorBlock(TTMContent.TRAPDOOR_MIRKWOOD.get(), "mirkwood", modLoc("block/trapdoor_mirkwood"), true);
        trapdoorBlock(TTMContent.TRAPDOOR_CULUMALDA.get(), "culumalda", modLoc("block/trapdoor_culumalda"), true);
        trapdoorBlock(TTMContent.TRAPDOOR_LEBETHRON.get(), "lebethron", modLoc("block/trapdoor_lebethron"), true);
        simpleBlock(TTMContent.TORCH_MALLORN.get(), models().getExistingFile(modLoc("block/torch_mallorn")));
        simpleBlock(TTMContent.TORCH_MIRKWOOD.get(), models().getExistingFile(modLoc("block/torch_mirkwood")));
        simpleBlock(TTMContent.TORCH_CULUMALDA.get(), models().getExistingFile(modLoc("block/torch_culumalda")));
        simpleBlock(TTMContent.TORCH_LEBETHRON.get(), models().getExistingFile(modLoc("block/torch_lebethron")));
        simpleBlock(TTMContent.WALL_TORCH_MALLORN.get(), models().getExistingFile(modLoc("block/wall_torch_mallorn")));
        simpleBlock(TTMContent.WALL_TORCH_MIRKWOOD.get(), models().getExistingFile(modLoc("block/wall_torch_mirkwood")));
        simpleBlock(TTMContent.WALL_TORCH_CULUMALDA.get(), models().getExistingFile(modLoc("block/wall_torch_culumalda")));
        simpleBlock(TTMContent.WALL_TORCH_LEBETHRON.get(), models().getExistingFile(modLoc("block/wall_torch_lebethron")));
        simpleBlock(TTMContent.LEAVES_MALLORN.get());
        simpleBlock(TTMContent.LEAVES_MIRKWOOD.get());
        simpleBlock(TTMContent.LEAVES_CULUMALDA.get());
        simpleBlock(TTMContent.LEAVES_LEBETHRON.get());
        simpleBlock(TTMContent.LEAVES_FANGORNOAK.get());
        simpleBlock(TTMContent.LEAFPILE_MALLORN.get(), models().getExistingFile(modLoc("block/leafpile_mallorn")));
        simpleBlock(TTMContent.LEAFPILE_MIRKWOOD.get(), models().getExistingFile(modLoc("block/leafpile_mirkwood")));
        simpleBlock(TTMContent.LEAFPILE_CULUMALDA.get(), models().getExistingFile(modLoc("block/leafpile_culumalda")));
        simpleBlock(TTMContent.LEAFPILE_LEBETHRON.get(), models().getExistingFile(modLoc("block/leafpile_lebethron")));
        simpleBlock(TTMContent.LEAFPILE_FANGORNOAK.get(), models().getExistingFile(modLoc("block/leafpile_fangornoak")));
        simpleBlock(TTMContent.SAPLING_MALLORN.get(), models().cross("sapling_mallorn", modLoc("block/sapling_mallorn")));
        simpleBlock(TTMContent.SAPLING_MIRKWOOD.get(), models().cross("sapling_mirkwood", modLoc("block/sapling_mirkwood")));
        simpleBlock(TTMContent.SAPLING_CULUMALDA.get(), models().cross("sapling_culumalda", modLoc("block/sapling_culumalda")));
        simpleBlock(TTMContent.SAPLING_LEBETHRON.get(), models().cross("sapling_lebethron", modLoc("block/sapling_lebethron")));
        simpleBlock(TTMContent.SAPLING_DEADWOOD.get(), models().cross("sapling_deadwood", modLoc("block/sapling_deadwood")));
        simpleBlock(TTMContent.SAPLING_FANGORNOAK.get(), models().cross("sapling_fangornoak", modLoc("block/sapling_fangornoak")));

        // Basic Plants & Flowers
        simpleBlock(TTMContent.MUSHROOM_DECAY_BLOOM.get(), models().cross("mushroom_decay_bloom", modLoc("block/mushroom_decay_bloom")));
        simpleBlock(TTMContent.MUSHROOM_BLOOM_DECAY.get(), models().cross("mushroom_bloom_decay", modLoc("block/mushroom_bloom_decay")));
        simpleBlock(TTMContent.BLOCK_BLOOM_DECAY.get(), models().getExistingFile(modLoc("block/block_bloom_decay")));
        simpleBlock(TTMContent.BLOCK_DECAY_BLOOM.get(), models().getExistingFile(modLoc("block/block_decay_bloom")));
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


        //Bellow is some extra example stuff I left in for reference
        if (true) return;


        // Unnecessarily complicated example to showcase how manual building works
        ModelFile birchFenceGate = models().fenceGate("birch_fence_gate", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateOpen = models().fenceGateOpen("birch_fence_gate_open", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateWall = models().fenceGateWall("birch_fence_gate_wall", mcLoc("block/birch_planks"));
        ModelFile birchFenceGateWallOpen = models().fenceGateWallOpen("birch_fence_gate_wall_open", mcLoc("block/birch_planks"));
        ModelFile invisbleModel = new ModelFile.UncheckedModelFile(new ResourceLocation("builtin/generated"));
        VariantBlockStateBuilder builder = getVariantBuilder(Blocks.BIRCH_FENCE_GATE);
        for (Direction dir : FenceGateBlock.FACING.getPossibleValues()) {
            int angle = (int) dir.toYRot();
            builder
                    .partialState()
                    .with(FenceGateBlock.FACING, dir)
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
                    .with(FenceGateBlock.FACING, dir)
                    .with(FenceGateBlock.IN_WALL, false)
                    .with(FenceGateBlock.OPEN, true)
                    .modelForState()
                    .modelFile(birchFenceGateOpen)
                    .rotationY(angle)
                    .uvLock(true)
                    .addModel()
                    .partialState()
                    .with(FenceGateBlock.FACING, dir)
                    .with(FenceGateBlock.IN_WALL, true)
                    .with(FenceGateBlock.OPEN, false)
                    .modelForState()
                    .modelFile(birchFenceGateWall)
                    .rotationY(angle)
                    .uvLock(true)
                    .addModel()
                    .partialState()
                    .with(FenceGateBlock.FACING, dir)
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
                .allFaces((dir, face) -> face.texture("#" + dir.getSerializedName()).cullface(dir));


        ModelFile furnace = models().orientable("furnace", mcLoc("block/furnace_side"), mcLoc("block/furnace_front"), mcLoc("block/furnace_top"));
        ModelFile furnaceLit = models().orientable("furnace_on", mcLoc("block/furnace_side"), mcLoc("block/furnace_front_on"), mcLoc("block/furnace_top"));

        getVariantBuilder(Blocks.FURNACE)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(state.getValue(FurnaceBlock.LIT) ? furnaceLit : furnace)
                        .rotationY((int) state.getValue(FurnaceBlock.FACING).getOpposite().toYRot())
                        .build()
                );

        ModelFile barrel = models().cubeBottomTop("barrel", mcLoc("block/barrel_side"), mcLoc("block/barrel_bottom"), mcLoc("block/barrel_top"));
        ModelFile barrelOpen = models().cubeBottomTop("barrel_open", mcLoc("block/barrel_side"), mcLoc("block/barrel_bottom"), mcLoc("block/barrel_top_open"));
        directionalBlock(Blocks.BARREL, state -> state.getValue(BarrelBlock.OPEN) ? barrelOpen : barrel); // Testing custom state interpreter

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
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir == Direction.DOWN ? 90 : dir == Direction.UP ? -90 : 0)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                            .build();
                });
    }

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Blockstates";
    }
}
