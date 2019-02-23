package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.world.biomes.BiomeDagorlad;
import com.greatorator.tolkienmobs.world.biomes.BiomeFirien;
import com.greatorator.tolkienmobs.world.biomes.BiomeLorinand;
import com.greatorator.tolkienmobs.world.biomes.BiomeMirkwood;
import com.greatorator.tolkienmobs.world.gen.generators.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldGenCustomTrees implements IWorldGenerator
{
    private final WorldGenerator MALLORN = new WorldGenMallornTree(false);
    private final WorldGenerator MIRKWOOD = new WorldGenMirkwoodTree(false);
    private final WorldGenerator CULUMALDA = new WorldGenCulumaldaTree(false);
    private final WorldGenerator LEBETHRON = new WorldGenLebethronTree(false);
    private final WorldGenerator TESTTREE = new WorldGenTestTree(false);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case 1:

                break;

            case 0:

                runGenerator(MALLORN, world, random, chunkX, chunkZ, 3, -1, 0, BiomeLorinand.class);
                runGenerator(MIRKWOOD, world, random, chunkX, chunkZ, 3, -1, 0, BiomeMirkwood.class);
                runGenerator(CULUMALDA, world, random, chunkX, chunkZ, 3, -1, 0, BiomeFirien.class);
                runGenerator(LEBETHRON, world, random, chunkX, chunkZ, 3, -1, 0, BiomeFirien.class);
                runGenerator(TESTTREE, world, random, chunkX, chunkZ, 3, -1, 0, BiomeDagorlad.class);

                break;

            case -1:

        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, double chancesToSpawn, int minHeight, int maxHeight, Class<?>... classes)
    {
        if(chancesToSpawn < 1)
        {
            if(random.nextDouble() < chancesToSpawn) chancesToSpawn = 1;
            else chancesToSpawn = 0;
        }

        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chancesToSpawn; i++)
        {
            BlockPos pos = new BlockPos(chunkX * 16 + 10 + random.nextInt(15), minHeight + random.nextInt(heightDiff), chunkZ * 16 + 10 + random.nextInt(15));
            if(minHeight < 0) pos = world.getHeight(pos);
            Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

            if(world.getWorldType() != WorldType.FLAT)
            {
                if(classesList.contains(biome) || classes.length == 0) generator.generate(world, random, pos);
            }
        }
    }
}