package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.init.BlockInit;
import com.greatorator.tolkienmobs.objects.blocks.BlockOres;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator
{
    private WorldGenerator ore_nether_daemonium, ore_overworld_daemonium, ore_end_daemonium;
    private WorldGenerator ore_nether_aluminium, ore_overworld_aluminium, ore_end_aluminium;

    public WorldGenCustomOres()
    {
        ore_nether_daemonium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.NETHER_DAEMONIUM), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_daemonium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.DAEMONIUM), 9, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_daemonium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ENDER_DAEMONIUM), 9, BlockMatcher.forBlock(Blocks.END_STONE));

        ore_nether_aluminium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.NETHER_ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_aluminium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_aluminium = new WorldGenMinable(BlockInit.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ENDER_ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:

                runGenerator(ore_nether_aluminium, world, random, chunkX, chunkZ, 50, 0, 100);
                runGenerator(ore_nether_daemonium, world, random, chunkX, chunkZ, 50, 0, 100);

                break;

            case 0:

                runGenerator(ore_overworld_aluminium, world, random, chunkX, chunkZ, 50, 0, 100);
                runGenerator(ore_overworld_daemonium, world, random, chunkX, chunkZ, 50, 0, 100);

                break;

            case 1:

                runGenerator(ore_end_aluminium, world, random, chunkX, chunkZ, 50, 0, 256);
                runGenerator(ore_end_daemonium, world, random, chunkX, chunkZ, 50, 0, 256);
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
    {
        if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chance; i++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x,y,z));
        }
    }
}