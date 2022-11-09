package com.greatorator.tolkienmobs.datagen;

import com.google.gson.JsonElement;
import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

/**
 * Created by brandon3055 on 28/2/20.
 */
public class BlockStateGenerator extends BlockStateProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput = null;
    private final ItemModelProvider provider;
    List<String> sleepingBagTypes = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
    List<String> woodTypes = Arrays.asList("mallorn", "mirkwood", "culumalda", "lebethron", "deadwood", "fangornoak");
    List<String> metalTypes = Arrays.asList("mithril", "morguliron");


    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MODID, exFileHelper);
        this.provider = this.itemModels();
    }

    //So this is where blockStates are generated. This is also where you generate simple block models or implement more advanced custom models.

    @Override
    protected void registerStatesAndModels() {
        // Metals & Gems
        simpleBlock(TolkienBlocks.ORE_MITHRIL.get());
        simpleBlock(TolkienBlocks.ORE_END_MITHRIL.get());
        simpleBlock(TolkienBlocks.ORE_NETHER_MITHRIL.get());
        simpleBlock(TolkienBlocks.BLOCK_MITHRIL.get());
        simpleBlock(TolkienBlocks.RAW_MITHRIL_BLOCK.get());
        paneBlock(TolkienBlocks.MITHRIL_BARS.get(), modLoc("block/mithril_bars"), modLoc("block/mithril_bars"));
        doorBlock(TolkienBlocks.DOOR_MITHRIL.get(), "door_mithril", modLoc("block/door_mithril_bottom"), modLoc("block/door_mithril_top"));
        trapdoorBlock(TolkienBlocks.TRAPDOOR_MITHRIL.get(), "mithril", modLoc("block/trapdoor_mithril"), true);
        weightedPressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(),
                models().withExistingParent("pressure_plate_mithril", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/block_mithril"),
                models().withExistingParent("pressure_plate_mithril_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/block_mithril"));
        simpleBlock(TolkienBlocks.ORE_MORGULIRON.get());
        simpleBlock(TolkienBlocks.ORE_END_MORGULIRON.get());
        simpleBlock(TolkienBlocks.ORE_NETHER_MORGULIRON.get());
        simpleBlock(TolkienBlocks.BLOCK_MORGULIRON.get());
        simpleBlock(TolkienBlocks.RAW_MORGULIRON_BLOCK.get());
        paneBlock(TolkienBlocks.MORGULIRON_BARS.get(), modLoc("block/morguliron_bars"), modLoc("block/morguliron_bars"));
        doorBlock(TolkienBlocks.DOOR_MORGULIRON.get(), "door_morguliron", modLoc("block/door_morguliron_bottom"), modLoc("block/door_morguliron_top"));
        trapdoorBlock(TolkienBlocks.TRAPDOOR_MORGULIRON.get(), "morguliron", modLoc("block/trapdoor_morguliron"), true);
        weightedPressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get(),
                models().withExistingParent("pressure_plate_morguliron", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/block_morguliron"),
                models().withExistingParent("pressure_plate_morguliron_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/block_morguliron"));
        simpleBlock(TolkienBlocks.ORE_AMMOLITE.get());
        simpleBlock(TolkienBlocks.ORE_END_AMMOLITE.get());
        simpleBlock(TolkienBlocks.ORE_NETHER_AMMOLITE.get());
        simpleBlock(TolkienBlocks.CHAMELEON_BLOCK.get());
        simpleBlock(TolkienBlocks.BLOCK_AMMOLITE.get());
        doorBlock(TolkienBlocks.DOOR_DURIN.get(), "door_durin", modLoc("block/door_durin_bottom"), modLoc("block/door_durin_top"));

        // Wood & Foliage
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_MALLORN.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_MIRKWOOD.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_CULUMALDA.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_LEBETHRON.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_DEADWOOD.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.LOG_FANGORNOAK.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_MALLORN_LOG.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_CULUMALDA_LOG.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_LEBETHRON_LOG.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_DEADWOOD_LOG.get());
        logBlock((RotatedPillarBlock) TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get());
        simpleBlock(TolkienBlocks.WOOD_MALLORN.get());
        simpleBlock(TolkienBlocks.WOOD_MIRKWOOD.get());
        simpleBlock(TolkienBlocks.WOOD_CULUMALDA.get());
        simpleBlock(TolkienBlocks.WOOD_LEBETHRON.get());
        simpleBlock(TolkienBlocks.WOOD_DEADWOOD.get());
        simpleBlock(TolkienBlocks.WOOD_FANGORNOAK.get());
        simpleBlock(TolkienBlocks.STRIPPED_MALLORN_WOOD.get());
        simpleBlock(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get());
        simpleBlock(TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get());
        simpleBlock(TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get());
        simpleBlock(TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get());
        simpleBlock(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get());
        simpleBlock(TolkienBlocks.PLANKS_MALLORN.get());
        simpleBlock(TolkienBlocks.PLANKS_MIRKWOOD.get());
        simpleBlock(TolkienBlocks.PLANKS_CULUMALDA.get());
        simpleBlock(TolkienBlocks.PLANKS_LEBETHRON.get());
        simpleBlock(TolkienBlocks.PLANKS_DEADWOOD.get());
        simpleBlock(TolkienBlocks.PLANKS_FANGORNOAK.get());
        slabBlock(TolkienBlocks.SLAB_MALLORN.get(), modLoc("block/planks_mallorn"), modLoc("block/planks_mallorn"));
        slabBlock(TolkienBlocks.SLAB_MIRKWOOD.get(), modLoc("block/planks_mirkwood"), modLoc("block/planks_mirkwood"));
        slabBlock(TolkienBlocks.SLAB_CULUMALDA.get(), modLoc("block/planks_culumalda"), modLoc("block/planks_culumalda"));
        slabBlock(TolkienBlocks.SLAB_LEBETHRON.get(), modLoc("block/planks_lebethron"), modLoc("block/planks_lebethron"));
        slabBlock(TolkienBlocks.SLAB_DEADWOOD.get(), modLoc("block/planks_deadwood"), modLoc("block/planks_deadwood"));
        slabBlock(TolkienBlocks.SLAB_FANGORNOAK.get(), modLoc("block/planks_fangornoak"), modLoc("block/planks_fangornoak"));
        stairsBlock(TolkienBlocks.STAIRS_MALLORN.get(), modLoc("block/planks_mallorn"));
        stairsBlock(TolkienBlocks.STAIRS_MIRKWOOD.get(), modLoc("block/planks_mirkwood"));
        stairsBlock(TolkienBlocks.STAIRS_CULUMALDA.get(), modLoc("block/planks_culumalda"));
        stairsBlock(TolkienBlocks.STAIRS_LEBETHRON.get(), modLoc("block/planks_lebethron"));
        stairsBlock(TolkienBlocks.STAIRS_DEADWOOD.get(), modLoc("block/planks_deadwood"));
        stairsBlock(TolkienBlocks.STAIRS_FANGORNOAK.get(), modLoc("block/planks_fangornoak"));
        doorBlock(TolkienBlocks.DOOR_MALLORN.get(), "door_mallorn", modLoc("block/door_mallorn_bottom"), modLoc("block/door_mallorn_top"));
        doorBlock(TolkienBlocks.DOOR_MIRKWOOD.get(), "door_mirkwood", modLoc("block/door_mirkwood_bottom"), modLoc("block/door_mirkwood_top"));
        doorBlock(TolkienBlocks.DOOR_CULUMALDA.get(), "door_culumalda", modLoc("block/door_culumalda_bottom"), modLoc("block/door_culumalda_top"));
        doorBlock(TolkienBlocks.DOOR_LEBETHRON.get(), "door_lebethron", modLoc("block/door_lebethron_bottom"), modLoc("block/door_lebethron_top"));
        doorBlock(TolkienBlocks.DOOR_DEADWOOD.get(), "door_deadwood", modLoc("block/door_deadwood_bottom"), modLoc("block/door_deadwood_top"));
        doorBlock(TolkienBlocks.DOOR_FANGORNOAK.get(), "door_fangornoak", modLoc("block/door_fangornoak_bottom"), modLoc("block/door_fangornoak_top"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_MALLORN.get(), "fence_gate_mallorn", modLoc("block/planks_mallorn"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), "fence_gate_mirkwood", modLoc("block/planks_mirkwood"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_CULUMALDA.get(), "fence_gate_culumalda", modLoc("block/planks_culumalda"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_LEBETHRON.get(), "fence_gate_lebethron", modLoc("block/planks_lebethron"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_DEADWOOD.get(), "fence_gate_deadwood", modLoc("block/planks_deadwood"));
        fenceGateBlock(TolkienBlocks.FENCE_GATE_FANGORNOAK.get(), "fence_gate_fangornoak", modLoc("block/planks_fangornoak"));
        fenceBlock(TolkienBlocks.FENCE_MALLORN.get(), "fence_mallorn", modLoc("block/planks_mallorn"));
        fenceBlock(TolkienBlocks.FENCE_MIRKWOOD.get(), "fence_mirkwood", modLoc("block/planks_mirkwood"));
        fenceBlock(TolkienBlocks.FENCE_CULUMALDA.get(), "fence_culumalda", modLoc("block/planks_culumalda"));
        fenceBlock(TolkienBlocks.FENCE_LEBETHRON.get(), "fence_lebethron", modLoc("block/planks_lebethron"));
        fenceBlock(TolkienBlocks.FENCE_DEADWOOD.get(), "fence_deadwood", modLoc("block/planks_deadwood"));
        fenceBlock(TolkienBlocks.FENCE_FANGORNOAK.get(), "fence_fangornoak", modLoc("block/planks_fangornoak"));
        trapdoorBlock(TolkienBlocks.TRAPDOOR_MALLORN.get(), "mallorn", modLoc("block/trapdoor_mallorn"), true);
        trapdoorBlock(TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), "mirkwood", modLoc("block/trapdoor_mirkwood"), true);
        trapdoorBlock(TolkienBlocks.TRAPDOOR_CULUMALDA.get(), "culumalda", modLoc("block/trapdoor_culumalda"), true);
        trapdoorBlock(TolkienBlocks.TRAPDOOR_LEBETHRON.get(), "lebethron", modLoc("block/trapdoor_lebethron"), true);
        trapdoorBlock(TolkienBlocks.TRAPDOOR_DEADWOOD.get(), "deadwood", modLoc("block/trapdoor_deadwood"), true);
        trapdoorBlock(TolkienBlocks.TRAPDOOR_FANGORNOAK.get(), "fangornoak", modLoc("block/trapdoor_fangornoak"), true);
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(),
                models().withExistingParent("pressure_plate_mallorn", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_mallorn"),
                models().withExistingParent("pressure_plate_mallorn_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_mallorn"));
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(),
                models().withExistingParent("pressure_plate_mirkwood", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_mirkwood"),
                models().withExistingParent("pressure_plate_mirkwood_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_mirkwood"));
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(),
                models().withExistingParent("pressure_plate_culumalda", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_culumalda"),
                models().withExistingParent("pressure_plate_culumalda_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_culumalda"));
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(),
                models().withExistingParent("pressure_plate_lebethron", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_lebethron"),
                models().withExistingParent("pressure_plate_lebethron_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_lebethron"));
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get(),
                models().withExistingParent("pressure_plate_deadwood", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_deadwood"),
                models().withExistingParent("pressure_plate_deadwood_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_deadwood"));
        pressurePlateBlock(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get(),
                models().withExistingParent("pressure_plate_fangornoak", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/planks_fangornoak"),
                models().withExistingParent("pressure_plate_fangornoak_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/planks_fangornoak"));
        simpleBlock(TolkienBlocks.TORCH_MALLORN.get(), models().getExistingFile(modLoc("block/torch_mallorn")));
        simpleBlock(TolkienBlocks.TORCH_MIRKWOOD.get(), models().getExistingFile(modLoc("block/torch_mirkwood")));
        simpleBlock(TolkienBlocks.TORCH_CULUMALDA.get(), models().getExistingFile(modLoc("block/torch_culumalda")));
        simpleBlock(TolkienBlocks.TORCH_LEBETHRON.get(), models().getExistingFile(modLoc("block/torch_lebethron")));
        simpleBlock(TolkienBlocks.TORCH_DEADWOOD.get(), models().getExistingFile(modLoc("block/torch_deadwood")));
        simpleBlock(TolkienBlocks.TORCH_FANGORNOAK.get(), models().getExistingFile(modLoc("block/torch_fangornoak")));
        simpleBlock(TolkienBlocks.WALL_TORCH_MALLORN.get(), models().getExistingFile(modLoc("block/wall_torch_mallorn")));
        simpleBlock(TolkienBlocks.WALL_TORCH_MIRKWOOD.get(), models().getExistingFile(modLoc("block/wall_torch_mirkwood")));
        simpleBlock(TolkienBlocks.WALL_TORCH_CULUMALDA.get(), models().getExistingFile(modLoc("block/wall_torch_culumalda")));
        simpleBlock(TolkienBlocks.WALL_TORCH_LEBETHRON.get(), models().getExistingFile(modLoc("block/wall_torch_lebethron")));
        simpleBlock(TolkienBlocks.WALL_TORCH_DEADWOOD.get(), models().getExistingFile(modLoc("block/wall_torch_deadwood")));
        simpleBlock(TolkienBlocks.WALL_TORCH_FANGORNOAK.get(), models().getExistingFile(modLoc("block/wall_torch_fangornoak")));
        simpleBlock(TolkienBlocks.LEAVES_MALLORN.get());
        simpleBlock(TolkienBlocks.LEAVES_MIRKWOOD.get());
        simpleBlock(TolkienBlocks.LEAVES_CULUMALDA.get());
        simpleBlock(TolkienBlocks.LEAVES_LEBETHRON.get());
        simpleBlock(TolkienBlocks.LEAVES_FANGORNOAK.get());
        simpleBlock(TolkienBlocks.LEAFPILE_MALLORN.get(), models().getExistingFile(modLoc("block/leafpile_mallorn")));
        simpleBlock(TolkienBlocks.LEAFPILE_MIRKWOOD.get(), models().getExistingFile(modLoc("block/leafpile_mirkwood")));
        simpleBlock(TolkienBlocks.LEAFPILE_CULUMALDA.get(), models().getExistingFile(modLoc("block/leafpile_culumalda")));
        simpleBlock(TolkienBlocks.LEAFPILE_LEBETHRON.get(), models().getExistingFile(modLoc("block/leafpile_lebethron")));
        simpleBlock(TolkienBlocks.LEAFPILE_FANGORNOAK.get(), models().getExistingFile(modLoc("block/leafpile_fangornoak")));
        simpleBlock(TolkienBlocks.SAPLING_MALLORN.get(), models().cross("sapling_mallorn", modLoc("block/sapling_mallorn")));
        simpleBlock(TolkienBlocks.SAPLING_MIRKWOOD.get(), models().cross("sapling_mirkwood", modLoc("block/sapling_mirkwood")));
        simpleBlock(TolkienBlocks.SAPLING_CULUMALDA.get(), models().cross("sapling_culumalda", modLoc("block/sapling_culumalda")));
        simpleBlock(TolkienBlocks.SAPLING_LEBETHRON.get(), models().cross("sapling_lebethron", modLoc("block/sapling_lebethron")));
        simpleBlock(TolkienBlocks.SAPLING_DEADWOOD.get(), models().cross("sapling_deadwood", modLoc("block/sapling_deadwood")));
        simpleBlock(TolkienBlocks.SAPLING_FANGORNOAK.get(), models().cross("sapling_fangornoak", modLoc("block/sapling_fangornoak")));
        buttonBlock((ButtonBlock) TolkienBlocks.MALLORN_BUTTON.get(),
                models().withExistingParent("mallorn_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_mallorn"),
                models().withExistingParent("mallorn_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_mallorn"));
        provider.buttonInventory("mallorn_button", modLoc("block/planks_mallorn"));
        buttonBlock((ButtonBlock) TolkienBlocks.MIRKWOOD_BUTTON.get(),
                models().withExistingParent("mirkwood_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_mirkwood"),
                models().withExistingParent("mirkwood_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_mirkwood"));
        provider.buttonInventory("mirkwood_button", modLoc("block/planks_mirkwood"));
        buttonBlock((ButtonBlock) TolkienBlocks.CULUMALDA_BUTTON.get(),
                models().withExistingParent("culumalda_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_culumalda"),
                models().withExistingParent("culumalda_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_culumalda"));
        provider.buttonInventory("culumalda_button", modLoc("block/planks_culumalda"));
        buttonBlock((ButtonBlock) TolkienBlocks.LEBETHRON_BUTTON.get(),
                models().withExistingParent("lebethron_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_lebethron"),
                models().withExistingParent("lebethron_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_lebethron"));
        provider.buttonInventory("lebethron_button", modLoc("block/planks_lebethron"));
        buttonBlock((ButtonBlock) TolkienBlocks.DEADWOOD_BUTTON.get(),
                models().withExistingParent("deadwood_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_deadwood"),
                models().withExistingParent("deadwood_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_deadwood"));
        provider.buttonInventory("deadwood_button", modLoc("block/planks_deadwood"));
        buttonBlock((ButtonBlock) TolkienBlocks.FANGORNOAK_BUTTON.get(),
                models().withExistingParent("fangornoak_button", mcLoc("block/button"))
                        .texture("texture", "block/planks_fangornoak"),
                models().withExistingParent("fangornoak_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/planks_fangornoak"));
        provider.buttonInventory("fangornoak_button", modLoc("block/planks_fangornoak"));

        // Plants & Flowers
        simpleBlock(TolkienBlocks.MUSHROOM_DECAY_BLOOM.get(), models().cross("mushroom_decay_bloom", modLoc("block/mushroom_decay_bloom")));
        simpleBlock(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get(), models().cross("mushroom_bloom_decay", modLoc("block/mushroom_bloom_decay")));
        simpleBlock(TolkienBlocks.FLOWER_SIMBELMYNE.get(), models().cross("flower_simbelmyne", modLoc("block/flower_simbelmyne")));
        simpleBlock(TolkienBlocks.FLOWER_MIRKWOOD.get(), models().cross("flower_mirkwood", modLoc("block/flower_mirkwood")));
        simpleBlock(TolkienBlocks.FLOWER_ALFIRIN.get(), models().cross("flower_alfirin", modLoc("block/flower_alfirin")));
        simpleBlock(TolkienBlocks.FLOWER_ATHELAS.get(), models().cross("flower_athelas", modLoc("block/flower_athelas")));
        simpleBlock(TolkienBlocks.FLOWER_NIPHREDIL.get(), models().cross("flower_niphredil", modLoc("block/flower_niphredil")));
        simpleBlock(TolkienBlocks.FLOWER_SWAMPMILKWEED.get(), models().cross("flower_swamp_milkweed", modLoc("block/flower_swamp_milkweed")));
        simpleBlock(TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get(), models().cross("flower_valley_lilly", modLoc("block/flower_valley_lilly")));
        cropsBlock(TolkienBlocks.PIPEWEED.get(), CropsBlock.AGE, "pipeweed_stage");
        simpleBlock(TolkienBlocks.POTTED_SAPLING_MALLORN.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_MALLORN.get())));
        simpleBlock(TolkienBlocks.POTTED_SAPLING_MIRKWOOD.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_MIRKWOOD.get())));
        simpleBlock(TolkienBlocks.POTTED_SAPLING_CULUMALDA.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_CULUMALDA.get())));
        simpleBlock(TolkienBlocks.POTTED_SAPLING_LEBETHRON.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_LEBETHRON.get())));
        simpleBlock(TolkienBlocks.POTTED_SAPLING_DEADWOOD.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_DEADWOOD.get())));
        simpleBlock(TolkienBlocks.POTTED_SAPLING_FANGORNOAK.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_SAPLING_FANGORNOAK.get())));
        simpleBlock(TolkienBlocks.POTTED_MUSHROOM_DECAY_BLOOM.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_MUSHROOM_DECAY_BLOOM.get())));
        simpleBlock(TolkienBlocks.POTTED_MUSHROOM_BLOOM_DECAY.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_MUSHROOM_BLOOM_DECAY.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_SIMBELMYNE.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_SIMBELMYNE.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_MIRKWOOD.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_MIRKWOOD.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_ALFIRIN.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_ALFIRIN.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_ATHELAS.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_ATHELAS.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_NIPHREDIL.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_NIPHREDIL.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_SWAMPMILKWEED.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_SWAMPMILKWEED.get())));
        simpleBlock(TolkienBlocks.POTTED_FLOWER_LILLYOFTHEVALLEY.get(), flowerPotCross(normalizeBlockPath(TolkienBlocks.POTTED_FLOWER_LILLYOFTHEVALLEY.get())));

        // Custom
        simpleBlock(TolkienBlocks.BLOCK_HALLOWED.get(), models().cubeBottomTop("block_hallowed", modLoc("block/block_hallowed_side"), modLoc("block/block_hallowed"), modLoc("block/block_hallowed_top")));
        simpleBlock(TolkienBlocks.STONE_PATH.get(), models().getExistingFile(modLoc("block/block_stone_path")));
        simpleBlock(TolkienBlocks.CAMO_GLOWSTONE_BLOCK.get());
        simpleBlock(TolkienBlocks.CAMO_SMOKER_BLOCK.get());
        simpleBlock(TolkienBlocks.CAMO_FLUID_BLOCK.get());
        simpleBlock(TolkienBlocks.CAMO_CHEST_BLOCK.get(), models().getExistingFile(modLoc("block/block_camo_chest")));
        simpleBlock(TolkienBlocks.CAMO_SPAWNER_BLOCK.get());
        horizontalBlock(TolkienBlocks.LOCKABLE_CHEST_BLOCK.get(), models().getExistingFile(modLoc("block/lockable_chest_block")), 180);
        horizontalBlock(TolkienBlocks.LOCKABLE_TREASURE_CHEST_BLOCK.get(), models().getExistingFile(modLoc("block/lockable_treasure_chest_block")), 180);
        horizontalBlock(TolkienBlocks.LOCKABLE_DOUBLE_CHEST_BLOCK.get(), models().getExistingFile(modLoc("block/lockable_double_chest_block")), 180);
        horizontalBlock(TolkienBlocks.LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get(), models().getExistingFile(modLoc("block/lockable_double_treasure_chest_block")), 180);
        horizontalBlock(TolkienBlocks.BACKPACK.get(), models().getExistingFile(modLoc("block/container_backpack")), 0);

        signBlock((TolkienStandingSignBlock) TolkienBlocks.MALLORN_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.MALLORN_WALL_SIGN.get(), modLoc("block/planks_mallorn"));
        signBlock((TolkienStandingSignBlock) TolkienBlocks.MIRKWOOD_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.MIRKWOOD_WALL_SIGN.get(), modLoc("block/planks_mirkwood"));
        signBlock((TolkienStandingSignBlock) TolkienBlocks.CULUMALDA_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.CULUMALDA_WALL_SIGN.get(), modLoc("block/planks_culumalda"));
        signBlock((TolkienStandingSignBlock) TolkienBlocks.LEBETHRON_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.LEBETHRON_WALL_SIGN.get(), modLoc("block/planks_lebethron"));
        signBlock((TolkienStandingSignBlock) TolkienBlocks.DEADWOOD_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.DEADWOOD_WALL_SIGN.get(), modLoc("block/planks_deadwood"));
        signBlock((TolkienStandingSignBlock) TolkienBlocks.FANGORNOAK_SIGN.get(), (TolkienWallSignBlock) TolkienBlocks.FANGORNOAK_WALL_SIGN.get(), modLoc("block/planks_fangornoak"));

        ModelFile barrelMithril = models().cubeBottomTop("barrel_mithril", modLoc("block/barrel_mithril_side"), modLoc("block/barrel_mithril_bottom"), modLoc("block/barrel_mithril_top"));
        ModelFile barrelMithrilOpen = models().cubeBottomTop("barrel_mithril_open", modLoc("block/barrel_mithril_side"), modLoc("block/barrel_mithril_bottom"), modLoc("block/barrel_mithril_top_open"));
        directionalBlock(TolkienBlocks.BARREL_MITHRIL.get(), state -> state.getValue(MithrilBarrelBlock.OPEN) ? barrelMithrilOpen : barrelMithril);

        ModelFile barrelMorgulIron = models().cubeBottomTop("barrel_morguliron", modLoc("block/barrel_morguliron_side"), modLoc("block/barrel_morguliron_bottom"), modLoc("block/barrel_morguliron_top"));
        ModelFile barrelMorgulIronOpen = models().cubeBottomTop("barrel_morguliron_open", modLoc("block/barrel_morguliron_side"), modLoc("block/barrel_morguliron_bottom"), modLoc("block/barrel_morguliron_top_open"));
        directionalBlock(TolkienBlocks.BARREL_MORGULIRON.get(), state -> state.getValue(MorgulironBarrelBlock.OPEN) ? barrelMorgulIronOpen : barrelMorgulIron);

        ModelFile bankFull = models().getExistingFile(modLoc("block/block_piggybank_full"));
        ModelFile bankEmpty = models().getExistingFile(modLoc("block/block_piggybank"));
        directionalFromNorthHoz(TolkienBlocks.PIGGYBANK.get(), e -> e.getValue(PiggyBankBlock.FULL) ? bankFull : bankEmpty, 180);

        ModelFile fireActive = models().getExistingFile(modLoc("block/fireplace_active"));
        ModelFile fireInactive = models().getExistingFile(modLoc("block/fireplace_inactive"));
        directionalFromNorthHoz(TolkienBlocks.TTMFIREPLACE.get(), e -> e.getValue(FireplaceBlock.LIT) ? fireActive : fireInactive, 180);

        ModelFile keyActive = models().getExistingFile(modLoc("block/block_key_stone_active"));
        ModelFile keyInactive = models().getExistingFile(modLoc("block/block_key_stone"));
        horizontalBlock(TolkienBlocks.KEY_STONE_BLOCK.get(), e -> e.getValue(CamoKeyStoneBlock.ACTIVE) ? keyActive : keyInactive, 180);

        ModelFile mileActive = models().getExistingFile(modLoc("block/milestone_block_active"));
        ModelFile mileInactive = models().getExistingFile(modLoc("block/milestone_block"));
        horizontalBlock(TolkienBlocks.MILESTONE_BLOCK.get(), e -> e.getValue(MilestoneBlock.LIT) ? mileActive : mileInactive, 180);

        sleepingBagModels();

        //Placards
        ModelFile placardWallModel = models().getExistingFile(modLoc("block/placard_wall"));
        ModelFile placardStandingModel = models().getExistingFile(modLoc("block/placard_standing"));
        ModelFile placardHangingModel = models().getExistingFile(modLoc("block/placard_hanging"));

        VariantBlockStateBuilder placardBuilder = getVariantBuilder(TolkienBlocks.PLACARD.get());
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
    }

    public void sleepingBagModels() {
        new ConfiguredModel(models().withExistingParent("block/sleeping_bag_head", mcLoc("block/thin_block"))
                .element()
                .from(0, 0, 0).to(16, 2, 16)
                .face(Direction.EAST).uvs(12, 1.5f, 16, 2).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.SOUTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.SOUTH).end()
                .face(Direction.WEST).uvs(12, 1, 16, 1.5f).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 0, 4, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 0, 8, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());
        new ConfiguredModel(models().withExistingParent("block/sleeping_bag_foot", mcLoc("block/thin_block"))
                .element()
                .from(0, 0, 0).to(16, 2, 16)
                .face(Direction.EAST).uvs(8, 1, 12, 1.5f).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.NORTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.NORTH).end()
                .face(Direction.WEST).uvs(8, 1.5f, 12, 2).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 4, 4, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 4, 8, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());

        for (String color : sleepingBagTypes) {
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag_" + color + "_head", modLoc("block/sleeping_bag_head")).texture("sleeping_bag", modLoc("block/sleepingbag/sleeping_bag_" + color)).texture("particle", modLoc("block/sleepingbag/sleeping_bag_" + color)));
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag_" + color + "_foot", modLoc("block/sleeping_bag_foot")).texture("sleeping_bag", modLoc("block/sleepingbag/sleeping_bag_" + color)).texture("particle", modLoc("block/sleepingbag/sleeping_bag_" + color)));
        }
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

    private String normalizeBlockPath(Block block) {
        return MODID + ":block/"+ block.toString().replace("Block{", "").replace("}", "")
                .replace(MODID+ ":", "");
    }

    public ModelFile flowerPotCross(String name) {
        return models().withExistingParent(name, "flower_pot_cross").texture("plant",
                new ResourceLocation(name.replace("potted_", "")));
    }

    public void pressurePlateBlock(Block block, ModelFile normal,  ModelFile powered) {
        getVariantBuilder(block)
                .partialState().with(PressurePlateBlock.POWERED, Boolean.FALSE).addModels(new ConfiguredModel(normal))
                .partialState().with(PressurePlateBlock.POWERED, Boolean.TRUE).addModels(new ConfiguredModel(powered));
    }

    public void weightedPressurePlateBlock(Block block, ModelFile normal,  ModelFile powered) {
        getVariantBuilder(block)
                .partialState().with(WeightedPressurePlateBlock.POWER, 0).addModels(new ConfiguredModel(normal));
        for (int i = 1; i <= 15; i++) {
            getVariantBuilder(block)
                    .partialState().with(WeightedPressurePlateBlock.POWER, i).setModels(new ConfiguredModel(powered));
        }
    }

    public void signBlock(TolkienStandingSignBlock signBlock, TolkienWallSignBlock wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        signBlock(signBlock, wallSignBlock, sign);
    }

    public void signBlock(TolkienStandingSignBlock signBlock, TolkienWallSignBlock wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
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

    public void buttonBlock(Block block, Function<BlockState, ModelFile> normal,  Function<BlockState, ModelFile> pressed, int angleOffset) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Boolean powered = state.getValue(BlockStateProperties.POWERED);
                    if (powered) {
                        return ConfiguredModel.builder()
                                .modelFile(pressed.apply(state))
                                .rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal() * 90)
                                .rotationY((((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) + (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
                                .build();
                    } else {
                        return ConfiguredModel.builder()
                                .modelFile(normal.apply(state))
                                .rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal() * 90)
                                .rotationY((((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) + (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
                                .build();
                    }
                });
    }

    private void cropsBlock(Block block, IntegerProperty ageProp, String name) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(ageProp);
            if (age == 0 || age == 1) {
                return ConfiguredModel.builder().modelFile(models().withExistingParent(name+0, mcLoc("block/crop")).texture("crop", "block/"+name+0)).build();
            } else if (age == 2 || age == 3) {
                return ConfiguredModel.builder().modelFile(models().withExistingParent(name+1, mcLoc("block/crop")).texture("crop", "block/"+name+1)).build();
            } else if (age == 4 || age == 5 || age == 6) {
                return ConfiguredModel.builder().modelFile(models().withExistingParent(name+2, mcLoc("block/crop")).texture("crop", "block/"+name+2)).build();
            } else {
                return ConfiguredModel.builder().modelFile(models().withExistingParent(name+3, mcLoc("block/crop")).texture("crop", "block/"+name+3)).build();
            }
        });

    }

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Blockstates";
    }
}
