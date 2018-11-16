package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.biomes.BiomeLorinand;
import com.greatorator.tolkienmobs.world.biomes.BiomeMirkwood;
import com.greatorator.tolkienmobs.world.biomes.BiomeHithaeglir;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
    public static final Biome LORINAND = new BiomeLorinand();
    public static final Biome MIRKWOOD = new BiomeMirkwood();
    public static final Biome HITHAEGLIR = new BiomeHithaeglir();

    public static void registerBiomes()
    {
        LogHelperTTM.info("Making new discoveries possible!");
        initBiome(LORINAND, "Lorinand", BiomeType.WARM, Type.PLAINS, Type.FOREST, Type.MAGICAL);
        initBiome(MIRKWOOD, "Mirkwood", BiomeType.COOL, Type.SWAMP, Type.FOREST, Type.SPOOKY);
        initBiome(HITHAEGLIR, "Hithaeglir ", BiomeType.ICY, Type.MOUNTAIN, Type.DEAD, Type.SPARSE);
        LogHelperTTM.info("New lands to explore get!");
    }

    private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}
