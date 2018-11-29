package com.greatorator.tolkienmobs.world.gen.providers;

import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.world.gen.layer.GenLayerArda;
import com.greatorator.tolkienmobs.world.gen.layer.GenLayerBiomesArda;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class BiomeProviderTTM extends BiomeProvider {

    public BiomeProviderTTM(World world) {
        super(world.getWorldInfo());
        allowedBiomes.clear();
        allowedBiomes.add(BiomeInit.MIRKWOOD);
        allowedBiomes.add(BiomeInit.LORINAND);
        allowedBiomes.add(BiomeInit.HITHAEGLIR);
        allowedBiomes.add(BiomeInit.BARROW_DOWNS);

        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(BiomeInit.LORINAND);
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        GenLayer biomes = new GenLayerBiomesArda(1);

        biomes = new GenLayerBiomesArda(1000, biomes);
        biomes = new GenLayerZoom(1000, biomes);
        biomes = new GenLayerZoom(1001, biomes);
        biomes = new GenLayerZoom(1002, biomes);
        biomes = new GenLayerZoom(1003, biomes);
        biomes = new GenLayerZoom(1004, biomes);

        GenLayer biomeIndexLayer = new GenLayerVoronoiZoom(10L, biomes);
        biomeIndexLayer.initWorldGenSeed(seed);

        return new GenLayer[]{
                biomes,
                biomeIndexLayer
        };
    }
}

