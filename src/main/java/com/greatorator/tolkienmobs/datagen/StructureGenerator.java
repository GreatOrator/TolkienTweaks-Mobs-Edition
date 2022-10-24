package com.greatorator.tolkienmobs.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.greatorator.tolkienmobs.world.gen.structures.*;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class StructureGenerator {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);

    public static final RegistryObject<Structure<NoFeatureConfig>> TTMHOUSE_ELVEN = STRUCTURES.register("ttmhouse_elven", () -> (new TTMHouseElvenStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMHOUSE_HOBBIT = STRUCTURES.register("ttmhouse_hobbit", () -> (new TTMHouseHobbitStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMHOUSE_DWARF = STRUCTURES.register("ttmhouse_dwarf", () -> (new TTMHouseDwarfStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMHOUSE_HUMAN = STRUCTURES.register("ttmhouse_human", () -> (new TTMHouseHumanStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMHOUSE_DESERT = STRUCTURES.register("ttmhouse_desert", () -> (new TTMHouseDesertStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMBARROW = STRUCTURES.register("ttmbarrow", () -> (new TTMBarrowStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMRUIN_LARGE = STRUCTURES.register("ttmruin_large", () -> (new TTMLargeRuinStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMRUIN_SMALL = STRUCTURES.register("ttmruin_small", () -> (new TTMSmallRuinStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMSWAMP_HAG_HUT = STRUCTURES.register("ttmswamp_hag_hut", () -> (new TTMSwampHagHutStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMSPIDER_TREE = STRUCTURES.register("ttmspidertree", () -> (new TTMSpiderTreeStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMWARG_PIT = STRUCTURES.register("ttmwargpit", () -> (new TTMWargPitStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMMINOTAUR_MAZE = STRUCTURES.register("ttmminotaurmaze", () -> (new TTMMinotaurMazeStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMSPIDER_CAVE = STRUCTURES.register("ttmspider_cave_main", () -> (new TTMSpiderCaveStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMGOLLUM_CAVE = STRUCTURES.register("ttmgollum_cave", () -> (new TTMGollumCaveStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMDARK_TOWER = STRUCTURES.register("ttmdark_tower", () -> (new TTMDarkTowerStructure(NoFeatureConfig.CODEC)));
    public static final RegistryObject<Structure<NoFeatureConfig>> TTMINN_DESERT = STRUCTURES.register("ttminn_desert", () -> (new TTMDesertInnStructure(NoFeatureConfig.CODEC)));

    public static void setupStructures() {
        setupMapSpacingAndLand(
                TTMHOUSE_ELVEN.get(), /* The instance of the structure */
                new StructureSeparationSettings(10 /* average distance apart in chunks between spawn attempts */,
                        5 /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/,
                        1234567890 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);

        setupMapSpacingAndLand(TTMHOUSE_HOBBIT.get(), new StructureSeparationSettings(10, 5, 385132341), true);
        setupMapSpacingAndLand(TTMHOUSE_DWARF.get(), new StructureSeparationSettings(10, 5, 985637739), true);
        setupMapSpacingAndLand(TTMHOUSE_HUMAN.get(), new StructureSeparationSettings(10, 5, 264205014), true);
        setupMapSpacingAndLand(TTMHOUSE_DESERT.get(), new StructureSeparationSettings(25, 20, 629453018), true);
        setupMapSpacingAndLand(TTMBARROW.get(), new StructureSeparationSettings(10, 5, 987654321), true);
        setupMapSpacingAndLand(TTMRUIN_LARGE.get(), new StructureSeparationSettings(10, 5, 249904528), true);
        setupMapSpacingAndLand(TTMRUIN_SMALL.get(), new StructureSeparationSettings(10, 5, 672963780), true);
        setupMapSpacingAndLand(TTMSWAMP_HAG_HUT.get(), new StructureSeparationSettings(20, 10,481284127),true);
        setupMapSpacingAndLand(TTMSPIDER_TREE.get(), new StructureSeparationSettings(30,10,481284127),true);
        setupMapSpacingAndLand(TTMWARG_PIT.get(), new StructureSeparationSettings(10,5,188808256),true);
        setupMapSpacingAndLand(TTMMINOTAUR_MAZE.get(), new StructureSeparationSettings(40,35,138448349), true);
        setupMapSpacingAndLand(TTMGOLLUM_CAVE.get(), new StructureSeparationSettings(40,35, 62108679),true);
        setupMapSpacingAndLand(TTMDARK_TOWER.get(), new StructureSeparationSettings(40,35,211506422),true);
        setupMapSpacingAndLand(TTMINN_DESERT.get(), new StructureSeparationSettings(35,30,244749734),true);
        setupMapSpacingAndLand(TTMSPIDER_CAVE.get(), new StructureSeparationSettings(40,35,482507941), true);
    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if(transformSurroundingLand){
            Structure.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<Structure<?>>builder()
                            .addAll(Structure.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();

        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig = tempMap;
            }else{
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Structures";
    }
}
