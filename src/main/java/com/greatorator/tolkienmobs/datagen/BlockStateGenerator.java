package com.greatorator.tolkienmobs.datagen;

import com.google.gson.JsonElement;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.*;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by brandon3055 on 28/2/20.
 */
public class BlockStateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput = null;


    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TolkienMobs.MODID, exFileHelper);
    }

    //So this is where blockStates are generated. This is also where you generate simple block models or implement more advanced custom models.

    @Override
    protected void registerStatesAndModels() {
        // Metals & Gems
        simpleBlock(TTMContent.ORE_MITHRIL.get());
        simpleBlock(TTMContent.ORE_END_MITHRIL.get());
        simpleBlock(TTMContent.ORE_NETHER_MITHRIL.get());
        simpleBlock(TTMContent.BLOCK_MITHRIL.get());
        paneBlock(TTMContent.MITHRIL_BARS.get(), modLoc("block/mithril_bars"), modLoc("block/mithril_bars"));
        doorBlock(TTMContent.DOOR_MITHRIL.get(), "door_mithril", modLoc("block/door_mithril_bottom"), modLoc("block/door_mithril_top"));
        trapdoorBlock(TTMContent.TRAPDOOR_MITHRIL.get(), "mithril", modLoc("block/trapdoor_mithril"), true);
        simpleBlock(TTMContent.PRESSURE_PLATE_MITHRIL.get(), models().getExistingFile(modLoc("block/pressure_plate_mithril")));
        simpleBlock(TTMContent.ORE_MORGULIRON.get());
        simpleBlock(TTMContent.ORE_END_MORGULIRON.get());
        simpleBlock(TTMContent.ORE_NETHER_MORGULIRON.get());
        simpleBlock(TTMContent.BLOCK_MORGULIRON.get());
        paneBlock(TTMContent.MORGULIRON_BARS.get(), modLoc("block/morguliron_bars"), modLoc("block/morguliron_bars"));
        doorBlock(TTMContent.DOOR_MORGULIRON.get(), "door_morguliron", modLoc("block/door_morguliron_bottom"), modLoc("block/door_morguliron_top"));
        trapdoorBlock(TTMContent.TRAPDOOR_MORGULIRON.get(), "morguliron", modLoc("block/trapdoor_morguliron"), true);
        simpleBlock(TTMContent.PRESSURE_PLATE_MORGULIRON.get(), models().getExistingFile(modLoc("block/pressure_plate_morguliron")));
        simpleBlock(TTMContent.ORE_AMMOLITE.get());
        simpleBlock(TTMContent.ORE_END_AMMOLITE.get());
        simpleBlock(TTMContent.ORE_NETHER_AMMOLITE.get());
        doorBlock(TTMContent.DOOR_DURIN.get(), "door_durin", modLoc("block/door_durin_bottom"), modLoc("block/door_durin_top"));

        // Wood & Foliage
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
        simpleBlock(TTMContent.PRESSURE_PLATE_MALLORN.get(), models().getExistingFile(modLoc("block/pressure_plate_mallorn")));
        simpleBlock(TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), models().getExistingFile(modLoc("block/pressure_plate_mirkwood")));
        simpleBlock(TTMContent.PRESSURE_PLATE_CULUMALDA.get(), models().getExistingFile(modLoc("block/pressure_plate_culumalda")));
        simpleBlock(TTMContent.PRESSURE_PLATE_LEBETHRON.get(), models().getExistingFile(modLoc("block/pressure_plate_lebethron")));
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

        // Plants & Flowers
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
        horizontalBlock(TTMContent.BACKPACK.get(), models().getExistingFile(modLoc("block/container_backpack")), 0);

        ModelFile barrelMithril = models().cubeBottomTop("barrel_mithril", modLoc("block/barrel_mithril_side"), modLoc("block/barrel_mithril_bottom"), modLoc("block/barrel_mithril_top"));
        ModelFile barrelMithrilOpen = models().cubeBottomTop("barrel_mithril_open", modLoc("block/barrel_mithril_side"), modLoc("block/barrel_mithril_bottom"), modLoc("block/barrel_mithril_top_open"));
        directionalBlock(TTMContent.BARREL_MITHRIL.get(), state -> state.getValue(BlockTTMMithrilBarrel.OPEN) ? barrelMithrilOpen : barrelMithril);

        ModelFile barrelMorgulIron = models().cubeBottomTop("barrel_morguliron", modLoc("block/barrel_morguliron_side"), modLoc("block/barrel_morguliron_bottom"), modLoc("block/barrel_morguliron_top"));
        ModelFile barrelMorgulIronOpen = models().cubeBottomTop("barrel_morguliron_open", modLoc("block/barrel_morguliron_side"), modLoc("block/barrel_morguliron_bottom"), modLoc("block/barrel_morguliron_top_open"));
        directionalBlock(TTMContent.BARREL_MORGULIRON.get(), state -> state.getValue(BlockTTMMorgulironBarrel.OPEN) ? barrelMorgulIronOpen : barrelMorgulIron);

        ModelFile bankFull = models().getExistingFile(modLoc("block/block_piggybank_full"));
        ModelFile bankEmpty = models().getExistingFile(modLoc("block/block_piggybank"));
        directionalFromNorthHoz(TTMContent.PIGGYBANK.get(), e -> e.getValue(BlockTTMPiggyBank.FULL) ? bankFull : bankEmpty, 180);

        ModelFile fireActive = models().getExistingFile(modLoc("block/fireplace_active"));
        ModelFile fireInactive = models().getExistingFile(modLoc("block/fireplace_inactive"));
        directionalFromNorthHoz(TTMContent.TTMFIREPLACE.get(), e -> e.getValue(BlockTTMFireplace.ACTIVE) ? fireActive : fireInactive, 180);


        signBlock(TTMContent.MALLORN_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_mallorn");
        signBlock(TTMContent.MALLORN_WALL_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_mallorn");
        signBlock(TTMContent.MIRKWOOD_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_mirkwood");
        signBlock(TTMContent.MIRKWOOD_WALL_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_mirkwood");
        signBlock(TTMContent.CULUMALDA_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_culumalda");
        signBlock(TTMContent.CULUMALDA_WALL_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_culumalda");
        signBlock(TTMContent.LEBETHRON_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_lebethron");
        signBlock(TTMContent.LEBETHRON_WALL_SIGN_WOOD_TYPE.get(), TolkienMobs.MODID + ":block/planks_lebethron");

        sleepingBagModels();


        //Placards
        ModelFile placardWallModel = models().getExistingFile(modLoc("block/placard_wall"));
        ModelFile placardStandingModel = models().getExistingFile(modLoc("block/placard_standing"));
        ModelFile placardHangingModel = models().getExistingFile(modLoc("block/placard_hanging"));

        VariantBlockStateBuilder placardBuilder = getVariantBuilder(TTMContent.PLACARD.get());
        for (AttachFace face : PlacardBlock.ATTACH_FACE.getPossibleValues()) {
            for (PlacardBlock.PlacardType type : PlacardBlock.PlacardType.values()) {
                ModelFile baseModel = face == AttachFace.FLOOR ? placardStandingModel : face == AttachFace.CEILING ? placardHangingModel : placardWallModel;
                ModelFile model = models().getBuilder("placard_" + face.getSerializedName() + "_" + type.getName()).parent(baseModel).texture("tex", "tolkienmobs:block/signs/placard_" + type.getName());
                for (Direction dir : PlacardBlock.FACING.getPossibleValues()) {

                    int angle = (int) dir.toYRot();
                    placardBuilder.partialState()
                            .with(PlacardBlock.FACING, dir)
                            .with(PlacardBlock.ATTACH_FACE, face)
                            .with(PlacardBlock.PLACARD_TYPE, type)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(angle)
                            .addModel();
                }
            }
        }


//            horizontalBlock(TTMContent.PLACARD.get(), models().getExistingFile(modLoc("block/placard_wall")),  0);

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

    public void sleepingBag(String color, ResourceLocation head, ResourceLocation foot) {
        ModelFile headModel = models().getExistingFile(head);
        ModelFile footModel = models().getExistingFile(foot);
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(TolkienMobs.MODID, "sleeping_bag_" + color));
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int yRot = ((int) state.getValue(SleepingBagBlock.FACING).toYRot());
            return ConfiguredModel.builder().modelFile(state.getValue(SleepingBagBlock.PART) == BedPart.FOOT ? footModel : headModel)
                    .rotationY(yRot)
                    .build();
        });
    }

    public void sleepingBagModels() {
        new ConfiguredModel(models().withExistingParent("block/sleeping_bag_foot", mcLoc("block/thin_block"))
                .element()
                .from(0, 0, 0).to(16, 2, 16)
                .face(Direction.EAST).uvs(8, 1, 12, 1.5f).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.NORTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.NORTH).end()
                .face(Direction.WEST).uvs(8, 1.5f, 12, 2).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 4, 4, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 4, 8, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());

        new ConfiguredModel(models().withExistingParent("block/sleeping_bag_head", mcLoc("block/thin_block"))
                .element()
                .from(0, 0, 0).to(16, 2, 16)
                .face(Direction.EAST).uvs(12, 1.5f, 16, 2).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.SOUTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.SOUTH).end()
                .face(Direction.WEST).uvs(12, 1, 16, 1.5f).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 0, 4, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 0, 8, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());

        List<String> sleepingBags = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
        for (String color : sleepingBags) {
            sleepingBag(color, modLoc("block/sleeping_bag_" + color + "_head"), modLoc("block/sleeping_bag_" + color + "_foot"));
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag_" + color + "_head", modLoc("block/sleeping_bag_head")).texture("sleeping_bag", modLoc("block/sleepingbag/sleeping_bag_" + color)).texture("particle", modLoc("block/sleepingbag/sleeping_bag_" + color)));
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag_" + color + "_foot", modLoc("block/sleeping_bag_foot")).texture("sleeping_bag", modLoc("block/sleepingbag/sleeping_bag_" + color)).texture("particle", modLoc("block/sleepingbag/sleeping_bag_" + color)));
        }
    }


    private void signBlock(Block block, String particleTexture) {
        ModelFile model = models()
                .withExistingParent("dummy", "block")
                .texture("particle", particleTexture);
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

    public void directionalFromNorthHoz(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir == Direction.DOWN ? 90 : dir == Direction.UP ? -90 : 0)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                            .build();
                });
    }

    private void cropsBlock(Block block, String name, int age) {
        String path = block.getRegistryName().getPath();
        //Integer cropAge = BlockTTMCrops.AGE;

        for (int i = 0; i < age; i++) {
            ResourceLocation resource = new ResourceLocation(block.getRegistryName().getNamespace(), "block/" + name + "_stage" + i);
            ModelFile model = models().crop(path + "_stage" + i, resource);
            simpleBlock(block, models().withExistingParent(path, mcLoc("block/crop")).texture("plant", resource));
//            getVariantBuilder(block)
//                .partialState().with(cropAge, i)
//                .modelForState().modelFile(path + "_stage" +  i).addModel();
        }

    }

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Blockstates";
    }
}
