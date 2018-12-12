package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.world.biomes.*;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenCustomStructures implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 2:

                break;

            case 1:

                break;

            case 0:

                generateStructure(new WorldGenStructure("mallorntree"), world, random, chunkX, chunkZ, 40, Blocks.GRASS, BiomeLorinand.class);
                generateStructure(new WorldGenStructure("mirkwoodtree"), world, random, chunkX, chunkZ, 10, Blocks.GRASS, BiomeMirkwood.class);
                generateStructure(new WorldGenStructure("greatbarrow"), world, random, chunkX, chunkZ, 20, Blocks.GRASS, BiomeBarrowDowns.class);
                generateStructure(new WorldGenStructure("stonespike"), world, random, chunkX, chunkZ, 5, Blocks.GRASS, BiomeBarrowDowns.class);
                generateStructure(new WorldGenStructure("deadtree"), world, random, chunkX, chunkZ, 5, Blocks.GRASS, BiomeBarrowDowns.class, BiomeMordor.class);
                generateStructure(new WorldGenStructure("fangorn_tree"), world, random, chunkX, chunkZ, 1, Blocks.GRASS, BiomeFangorn.class);
                //generateStructure(new WorldGenStructure("hobbithouse1"), world, random, chunkX, chunkZ, 30, Blocks.GRASS, BiomeShire.class);
                //generateStructure(new WorldGenStructure("hobbithouse2"), world, random, chunkX, chunkZ, 30, Blocks.GRASS, BiomeShire.class);

                break;

            case -1:

        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes) {
        List<Class<?>> classesList = Arrays.asList(classes);

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x, y, z);

        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if (world.getWorldType() != WorldType.FLAT) {
            if (classesList.contains(biome)) {
                if (random.nextInt(chance) == 0) {
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
        int y = world.getHeight();
        boolean foundGround = false;

        while (!foundGround && y-- >= 0) {
            Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround = block == topBlock;
        }

        return y;
    }
}
