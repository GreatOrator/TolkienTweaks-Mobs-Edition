package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.world.components.structures.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);

    public static final RegistryObject<StructureFeature<?>> TTMBARROW = STRUCTURES.register("ttmbarrow", BarrowStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMDARK_TOWER = STRUCTURES.register("ttmdark_tower", DarkTowerStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMGOLLUM_CAVE = STRUCTURES.register("ttmgollum_cave", GollumCaveStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMHOUSE_DESERT = STRUCTURES.register("ttmhouse_desert", HouseDesertStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMHOUSE_DWARF = STRUCTURES.register("ttmhouse_dwarf", HouseDwarfStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMHOUSE_ELVEN = STRUCTURES.register("ttmhouse_elven", HouseElvenStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMHOUSE_HOBBIT = STRUCTURES.register("ttmhouse_hobbit", HouseHobbitStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMHOUSE_HUMAN = STRUCTURES.register("ttmhouse_human", HouseHumanStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMINN_DESERT = STRUCTURES.register("ttminn_desert", DesertInnStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMMINOTAUR_MAZE = STRUCTURES.register("maze", MinotaurMazeStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMRUIN_LARGE = STRUCTURES.register("ttmruin_large", LargeRuinStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMRUIN_SMALL = STRUCTURES.register("ttmruin_small", SmallRuinStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMSPIDER_CAVE = STRUCTURES.register("cave_main", SpiderCaveStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMSPIDER_TREE = STRUCTURES.register("ttmspidertree", SpiderTreeStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMSWAMP_HAG_HUT = STRUCTURES.register("ttmswamp_hag_hut", SwampHagHutStructure::new);
    public static final RegistryObject<StructureFeature<?>> TTMWARG_PIT = STRUCTURES.register("ttmwargpit", WargPitStructure::new);

    public String getName() {
        return NAME + " - Structures";
    }
}
