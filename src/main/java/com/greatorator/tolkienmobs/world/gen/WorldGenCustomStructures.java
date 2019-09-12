package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.world.biomes.BiomeBarrowDowns;
import com.greatorator.tolkienmobs.world.biomes.BiomeGladden;
import com.greatorator.tolkienmobs.world.biomes.BiomeLorinand;
import com.greatorator.tolkienmobs.world.biomes.BiomeShire;
import com.greatorator.tolkienmobs.world.gen.generators.*;
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
    private WorldGenBiomeBarrow BARROW = new WorldGenBiomeBarrow();
    private WorldGenBiomeHobbitHouse HOBBITHOUSE = new WorldGenBiomeHobbitHouse();
    private WorldGenBiomeHobbitGrocer HOBBITGROCER = new WorldGenBiomeHobbitGrocer();
    private WorldGenBiomeElvenHouse ELVENHOUSE = new WorldGenBiomeElvenHouse();
    private WorldGenBiomeHumanHouse HUMANHOUSE = new WorldGenBiomeHumanHouse();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 1:

                break;

            case 0:
                generateStructure(BARROW, world, random, chunkX, chunkZ, 20, Blocks.GRASS, BiomeBarrowDowns.class);
                generateStructure(HOBBITHOUSE, world, random, chunkX, chunkZ, 20, Blocks.GRASS, BiomeShire.class);
                generateStructure(HOBBITGROCER, world, random, chunkX, chunkZ, 20, Blocks.GRASS, BiomeShire.class);
                generateStructure(ELVENHOUSE, world, random, chunkX, chunkZ, 60, Blocks.GRASS, BiomeLorinand.class);
                generateStructure(HUMANHOUSE, world, random, chunkX, chunkZ, 60, Blocks.GRASS, BiomeGladden.class);

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

        /* Make sure it is safe to spawn */
        if (world.getWorldType() != WorldType.FLAT && world.isAreaLoaded(pos, 5)) {
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