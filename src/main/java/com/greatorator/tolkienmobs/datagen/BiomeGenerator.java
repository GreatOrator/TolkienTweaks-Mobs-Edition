package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.world.biome.*;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BiomeGenerator {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MODID);

    // Creating the Biomes
    public static final RegistryObject<Biome> BIOME_LORINAND = BIOMES.register("biome_lorinand",()-> BiomeLorinand.makeBiomeLorinand(0.4f,0.6f));
    public static final RegistryObject<Biome> BIOME_MIRKWOOD = BIOMES.register("biome_mirkwood",()-> BiomeMirkwood.makeBiomeMirkwood(0.2F,0.2F));
    public static final RegistryObject<Biome> BIOME_MARSHES = BIOMES.register("biome_marshes",()-> BiomeMarshes.makeBiomeMarshes(-0.2F, 0.1F));
    public static final RegistryObject<Biome> BIOME_MORDOR = BIOMES.register("biome_mordor",()-> BiomeMordor.makeBiomeMordor(0.125F, 0.5F));
    public static final RegistryObject<Biome> BIOME_BARROWDOWNS = BIOMES.register("biome_barrowdowns",()-> BiomeBarrowDowns.makeBiomeBarrowDowns(0.2F,0.2F));
    public static final RegistryObject<Biome> BIOME_DAGORLAD = BIOMES.register("biome_dagorlad",()-> BiomeDagorlad.makeBiomeDagorlad(0.2F,0.2F));
    public static final RegistryObject<Biome> BIOME_SHIRE = BIOMES.register("biome_shire",()-> BiomeShire.makeBiomeShire(0.125F,0.5F));
    public static final RegistryObject<Biome> BIOME_FANGORN = BIOMES.register("biome_fangorn",()-> BiomeFangorn.makeBiomeFangorn(0.125F,0.5F));
    public static final RegistryObject<Biome> BIOME_HARADWAITH = BIOMES.register("biome_haradwaith",()-> BiomeHaradwaith.makeBiomeHaradwaith(0.125F,0.05F));
    public static final RegistryObject<Biome> BIOME_OLDFOREST = BIOMES.register("biome_oldforest",()-> BiomeOldForest.makeBiomeOldForest(0.125F,0.05F));
    public static final RegistryObject<Biome> BIOME_GLADDEN = BIOMES.register("biome_gladden",()-> BiomeGladden.makeBiomeGladden(0.125F,0.05F));
    public static final RegistryObject<Biome> BIOME_FIRIEN = BIOMES.register("biome_firien",()-> BiomeFirien.makeBiomeFirien(-0.5F,0.25F));
    public static final RegistryObject<Biome> BIOME_HITHAEGLIR = BIOMES.register("biome_hithaeglir",()-> BiomeHithaeglir.makeBiomeHithaeglir(3.0F,0.75F));
    public static final RegistryObject<Biome> BIOME_IRONHILLS = BIOMES.register("biome_ironhills",()-> BiomeIronHills.makeBiomeIronHills(3.0F,0.75F));

    // Setting the biome keys
    public static final RegistryKey<Biome> BIOME_LORINAND_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_lorinand"));
    public static final RegistryKey<Biome> BIOME_MIRKWOOD_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_mirkwood"));
    public static final RegistryKey<Biome> BIOME_MARSHES_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_marshes"));
    public static final RegistryKey<Biome> BIOME_MORDOR_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_mordor"));
    public static final RegistryKey<Biome> BIOME_BARROWDOWNS_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_barrowdowns"));
    public static final RegistryKey<Biome> BIOME_DAGORLAD_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_dagorlad"));
    public static final RegistryKey<Biome> BIOME_SHIRE_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_shire"));
    public static final RegistryKey<Biome> BIOME_FANGORN_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_fangorn"));
    public static final RegistryKey<Biome> BIOME_HARADWAITH_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_haradwaith"));
    public static final RegistryKey<Biome> BIOME_OLDFOREST_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_oldforest"));
    public static final RegistryKey<Biome> BIOME_GLADDEN_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_gladden"));
    public static final RegistryKey<Biome> BIOME_FIRIEN_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_firien"));
    public static final RegistryKey<Biome> BIOME_HITHAEGLIR_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_hithaeglir"));
    public static final RegistryKey<Biome> BIOME_IRONHILLS_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_ironhills"));

    // Register the Biomes
    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_LORINAND_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_MIRKWOOD_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_MARSHES_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_MORDOR_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_BARROWDOWNS_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_DAGORLAD_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_SHIRE_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_FANGORN_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(BIOME_HARADWAITH_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_OLDFOREST_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_GLADDEN_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_FIRIEN_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_HITHAEGLIR_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_IRONHILLS_KEY, 1));
    }
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biomes";
    }
}
