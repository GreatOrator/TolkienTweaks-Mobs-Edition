package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.biome.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TolkienBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

    public static final ResourceKey<Biome> BIOME_LORINAND = makeBiomeKey("biome_lorinand");
    public static final ResourceKey<Biome> BIOME_MIRKWOOD = makeBiomeKey("biome_mirkwood");
    public static final ResourceKey<Biome> BIOME_MARSHES = makeBiomeKey("biome_marshes");
    public static final ResourceKey<Biome> BIOME_MORDOR = makeBiomeKey("biome_mordor");
    public static final ResourceKey<Biome> BIOME_BARROWDOWNS = makeBiomeKey("biome_barrowdowns");
    public static final ResourceKey<Biome> BIOME_DAGORLAD = makeBiomeKey("biome_dagorlad");
    public static final ResourceKey<Biome> BIOME_SHIRE = makeBiomeKey("biome_shire");
    public static final ResourceKey<Biome> BIOME_FANGORN = makeBiomeKey("biome_fangorn");
    public static final ResourceKey<Biome> BIOME_HARADWAITH = makeBiomeKey("biome_haradwaith");
    public static final ResourceKey<Biome> BIOME_OLDFOREST = makeBiomeKey("biome_oldforest");
    public static final ResourceKey<Biome> BIOME_GLADDEN = makeBiomeKey("biome_gladden");
    public static final ResourceKey<Biome> BIOME_FIRIEN = makeBiomeKey("biome_firien");
    public static final ResourceKey<Biome> BIOME_HITHAEGLIR = makeBiomeKey("biome_hithaeglir");
    public static final ResourceKey<Biome> BIOME_IRONHILLS = makeBiomeKey("biome_ironhills");


    // Creating the Biomes
    static {
        BIOMES.register("biome_barrowdowns", () -> BiomeBarrowDowns.makeBiomeBarrowDowns(0.2F, 0.2F, 0.25F, 0.8F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_hithaeglir", () -> BiomeHithaeglir.makeBiomeHithaeglir(3.0F, 0.75F, 0.2F, 0.3F, Biome.Precipitation.SNOW, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_ironhills", () -> BiomeIronHills.makeBiomeIronHills(3.0F, 0.75F, 0.2F, 0.3F, Biome.Precipitation.SNOW, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_firien", () -> BiomeFirien.makeBiomeFirien(-0.5F, 0.25F, 0.7F, 0.8F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_oldforest", () -> BiomeOldForest.makeBiomeOldForest(0.125F, 0.05F, 0.7F, 0.8F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_dagorlad", () -> BiomeDagorlad.makeBiomeDagorlad(0.125F, 0.05F, 0.8F, 0.4F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_gladden", () -> BiomeGladden.makeBiomeGladden(0.125F, 0.05F, 0.8F, 0.4F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_shire", () -> BiomeShire.makeBiomeShire(0.125F, 0.05F, 0.8F, 0.4F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_lorinand", () -> BiomeLorinand.makeBiomeLorinand(0.125F, 0.05F, 0.8F, 0.4F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_mirkwood", () -> BiomeMirkwood.makeBiomeMirkwood(0.2F, 0.2F, 0.25F, 0.8F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_fangorn", () -> BiomeFangorn.makeBiomeFangorn(0.125F, 0.05F, 0.7F, 0.8F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_mordor", () -> BiomeMordor.makeBiomeMordor(0.125F, 0.5F, 2.0F, 0.0F, Biome.Precipitation.NONE, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_haradwaith", () -> BiomeHaradwaith.makeBiomeHaradwaith(0.125F, 0.05F, 2.0F, 0.0F, Biome.Precipitation.NONE, Biome.BiomeCategory.NONE));
        BIOMES.register("biome_marshes", () -> BiomeMarshes.makeBiomeMarshes(-0.2F, 0.1F, 0.8F, 0.9F, Biome.Precipitation.RAIN, Biome.BiomeCategory.NONE));
    }

    // Register the Biomes
    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_BARROWDOWNS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(BIOME_HITHAEGLIR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_IRONHILLS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_FIRIEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_OLDFOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_DAGORLAD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_GLADDEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_SHIRE, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_LORINAND, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_MIRKWOOD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_FANGORN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_MORDOR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_HARADWAITH, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_MARSHES, 1));
    }


    public static void addTypes() {
        BiomeDictionary.addTypes(BIOME_LORINAND, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MIRKWOOD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MARSHES, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_MORDOR, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_BARROWDOWNS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_DAGORLAD, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_SHIRE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_FANGORN, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_HARADWAITH, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.HOT, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_OLDFOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_GLADDEN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_FIRIEN, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_HITHAEGLIR, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(BIOME_IRONHILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.COLD, BiomeDictionary.Type.OVERWORLD);
    }

    public static ResourceKey<Biome> makeBiomeKey(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TolkienMobs.MODID, name));
    }

    public static Collection<RegistryObject<Biome>> getBiomes() {
        return BIOMES.getEntries();
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biomes";
    }
}
