package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.world.biomes.BiomeBindbole;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
    public static final Biome BINDBOLE = new BiomeBindbole();

    public static void registerBiomes()
    {
        initBiome(BINDBOLE, "Bindbole", BiomeType.WARM, Type.SWAMP, Type.PLAINS, Type.SPOOKY);
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        System.out.println("Making new discoveries possible!");
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
        System.out.println("Let the discoveries begin!");
        return biome;
    }
}
