package com.greatorator.tolkienmobs.datagen;

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

    // World Types
    public static RegistryObject<ForgeWorldType> ARDA = FORGE_WORLD_TYPES.register("arda", ArdaWorldType::new);

    // Setting the biome keys
    public static class Keys {
        public static final RegistryKey<Biome> BIOME_LORINAND = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_lorinand"));
        public static final RegistryKey<Biome> BIOME_MIRKWOOD = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_mirkwood"));
        public static final RegistryKey<Biome> BIOME_MARSHES = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_marshes"));
        public static final RegistryKey<Biome> BIOME_MORDOR = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_mordor"));
        public static final RegistryKey<Biome> BIOME_BARROWDOWNS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_barrowdowns"));
        public static final RegistryKey<Biome> BIOME_DAGORLAD = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_dagorlad"));
        public static final RegistryKey<Biome> BIOME_SHIRE = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_shire"));
        public static final RegistryKey<Biome> BIOME_FANGORN = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_fangorn"));
        public static final RegistryKey<Biome> BIOME_HARADWAITH = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_haradwaith"));
        public static final RegistryKey<Biome> BIOME_OLDFOREST = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_oldforest"));
        public static final RegistryKey<Biome> BIOME_GLADDEN = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_gladden"));
        public static final RegistryKey<Biome> BIOME_FIRIEN = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_firien"));
        public static final RegistryKey<Biome> BIOME_HITHAEGLIR = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_hithaeglir"));
        public static final RegistryKey<Biome> BIOME_IRONHILLS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_ironhills"));
    }

    // Register the Biomes
    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_LORINAND, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_MIRKWOOD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_MARSHES, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(Keys.BIOME_MORDOR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_BARROWDOWNS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_DAGORLAD, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_SHIRE, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_FANGORN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(Keys.BIOME_HARADWAITH, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_OLDFOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_GLADDEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(Keys.BIOME_FIRIEN, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_HITHAEGLIR, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(Keys.BIOME_IRONHILLS, 1));
    }

    public static void addTypes() {
        BiomeDictionary.addTypes(Keys.BIOME_LORINAND, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_MIRKWOOD, Type.FOREST, Type.SPOOKY, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_MARSHES, Type.SWAMP, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_MORDOR, Type.WASTELAND, Type.CONIFEROUS, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_BARROWDOWNS, Type.SPOOKY, Type.DEAD, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_DAGORLAD, Type.SPARSE, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_SHIRE, Type.PLAINS, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_FANGORN, Type.FOREST, Type.MAGICAL, Type.CONIFEROUS, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_HARADWAITH, Type.SANDY, Type.CONIFEROUS, Type.HOT, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_OLDFOREST, Type.FOREST, Type.MAGICAL, Type.COLD, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_GLADDEN, Type.PLAINS, Type.CONIFEROUS, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_FIRIEN, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_HITHAEGLIR, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.SNOWY, Type.OVERWORLD);
        BiomeDictionary.addTypes(Keys.BIOME_IRONHILLS, Type.MOUNTAIN, Type.SPARSE, Type.COLD, Type.OVERWORLD);
    }

    public static Collection<RegistryObject<Biome>> getBiomes() {
        return BIOMES.getEntries();
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biomes";
    }
}
