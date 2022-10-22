package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.ArdaWorldType;
import com.greatorator.tolkienmobs.world.biome.*;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.ForgeWorldType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeGenerator {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);
    public static final DeferredRegister<ForgeWorldType> FORGE_WORLD_TYPES = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MODID);

    public static final RegistryKey<Biome> BIOME_LORINAND = makeBiomeKey("biome_lorinand");
    public static final RegistryKey<Biome> BIOME_MIRKWOOD = makeBiomeKey("biome_mirkwood");
    public static final RegistryKey<Biome> BIOME_MARSHES = makeBiomeKey("biome_marshes");
    public static final RegistryKey<Biome> BIOME_MORDOR = makeBiomeKey("biome_mordor");
    public static final RegistryKey<Biome> BIOME_BARROWDOWNS = makeBiomeKey("biome_barrowdowns");
    public static final RegistryKey<Biome> BIOME_DAGORLAD = makeBiomeKey("biome_dagorlad");
    public static final RegistryKey<Biome> BIOME_SHIRE = makeBiomeKey("biome_shire");
    public static final RegistryKey<Biome> BIOME_FANGORN = makeBiomeKey("biome_fangorn");
    public static final RegistryKey<Biome> BIOME_HARADWAITH = makeBiomeKey("biome_haradwaith");
    public static final RegistryKey<Biome> BIOME_OLDFOREST = makeBiomeKey("biome_oldforest");
    public static final RegistryKey<Biome> BIOME_GLADDEN = makeBiomeKey("biome_gladden");
    public static final RegistryKey<Biome> BIOME_FIRIEN = makeBiomeKey("biome_firien");
    public static final RegistryKey<Biome> BIOME_HITHAEGLIR = makeBiomeKey("biome_hithaeglir");
    public static final RegistryKey<Biome> BIOME_IRONHILLS = makeBiomeKey("biome_ironhills");


    // Creating the Biomes
    static {
        BIOMES.register("biome_lorinand", () -> BiomeLorinand.makeBiomeLorinand(0.4f, 0.6f));
        BIOMES.register("biome_mirkwood", () -> BiomeMirkwood.makeBiomeMirkwood(0.2F, 0.2F));
        BIOMES.register("biome_marshes", () -> BiomeMarshes.makeBiomeMarshes(-0.2F, 0.1F));
        BIOMES.register("biome_mordor", () -> BiomeMordor.makeBiomeMordor(0.125F, 0.5F));
        BIOMES.register("biome_barrowdowns", () -> BiomeBarrowDowns.makeBiomeBarrowDowns(0.2F, 0.2F));
        BIOMES.register("biome_dagorlad", () -> BiomeDagorlad.makeBiomeDagorlad(0.2F, 0.2F));
        BIOMES.register("biome_shire", () -> BiomeShire.makeBiomeShire(0.125F, 0.5F));
        BIOMES.register("biome_fangorn", () -> BiomeFangorn.makeBiomeFangorn(0.125F, 0.5F));
        BIOMES.register("biome_haradwaith", () -> BiomeHaradwaith.makeBiomeHaradwaith(0.125F, 0.05F));
        BIOMES.register("biome_oldforest", () -> BiomeOldForest.makeBiomeOldForest(0.125F, 0.05F));
        BIOMES.register("biome_gladden", () -> BiomeGladden.makeBiomeGladden(0.125F, 0.05F));
        BIOMES.register("biome_firien", () -> BiomeFirien.makeBiomeFirien(-0.5F, 0.25F));
        BIOMES.register("biome_hithaeglir", () -> BiomeHithaeglir.makeBiomeHithaeglir(3.0F, 0.75F));
        BIOMES.register("biome_ironhills", () -> BiomeIronHills.makeBiomeIronHills(3.0F, 0.75F));
    }
    // World Types
    public static RegistryObject<ForgeWorldType> ARDA = FORGE_WORLD_TYPES.register("arda", ArdaWorldType::new);

    // Register the Biomes
    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_LORINAND, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_MIRKWOOD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_MARSHES, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_MORDOR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_BARROWDOWNS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_DAGORLAD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_SHIRE, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_FANGORN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_HARADWAITH, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_OLDFOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_GLADDEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_FIRIEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_HITHAEGLIR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_IRONHILLS, 1));
    }

    public static void addTypes() {
        BiomeDictionary.addTypes(BIOME_LORINAND, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MIRKWOOD, Type.FOREST, Type.SPOOKY, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MARSHES, Type.SWAMP, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MORDOR, Type.WASTELAND, Type.CONIFEROUS, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_BARROWDOWNS, Type.SPOOKY, Type.DEAD, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_DAGORLAD, Type.SPARSE, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_SHIRE, Type.PLAINS, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_FANGORN, Type.FOREST, Type.MAGICAL, Type.CONIFEROUS, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_HARADWAITH, Type.SANDY, Type.CONIFEROUS, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_OLDFOREST, Type.FOREST, Type.MAGICAL, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_GLADDEN, Type.PLAINS, Type.CONIFEROUS, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_FIRIEN, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_HITHAEGLIR, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.SNOWY, Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_IRONHILLS, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.OVERWORLD);
    }

    public static RegistryKey<Biome> makeBiomeKey(String name) {
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TolkienMobs.MODID, name));
    }

    public static Collection<RegistryObject<Biome>> getBiomes() {
        return BIOMES.getEntries();
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biomes";
    }
}
