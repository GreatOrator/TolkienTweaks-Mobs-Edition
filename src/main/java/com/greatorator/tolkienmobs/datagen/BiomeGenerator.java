package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.world.biome.BiomeLorinand;
import com.greatorator.tolkienmobs.world.biome.BiomeMirkwood;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class BiomeGenerator {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS
            = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, MODID);

    // Creating the Biomes
    public static final RegistryObject<Biome> BIOME_LORINAND = BIOMES.register("biome_lorinand",()-> BiomeLorinand.makeBiomeLorinand(0.4f,0.6f));
    public static final RegistryObject<Biome> BIOME_MIRKWOOD = BIOMES.register("biome_mirkwood",()-> BiomeMirkwood.makeBiomeMirkwood(0.4f,0.6f));

    // Setting the biome keys
    public static final RegistryKey<Biome> BIOME_LORINAND_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_lorinand"));
    public static final RegistryKey<Biome> BIOME_MIRKWOOD_KEY = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("biome.tolkienmobs.biome_mirkwood"));

    // Register the Biomes
    public static void addBiomesToOverworld() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(BIOME_LORINAND_KEY, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(BIOME_MIRKWOOD_KEY, 1));
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Biomes";
    }
}
