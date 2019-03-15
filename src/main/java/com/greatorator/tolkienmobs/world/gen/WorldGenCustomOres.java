package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockOres;
import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator
{
    private WorldGenerator ore_nether_mithril, ore_overworld_mithril, ore_end_mithril;
    private WorldGenerator ore_nether_morguliron, ore_overworld_morguliron, ore_end_morguliron;
    private WorldGenerator ore_nether_ammolite, ore_overworld_ammolite, ore_end_ammolite;

    public WorldGenCustomOres()
    {
        ore_nether_mithril = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.NETHER_MITHRIL), 4, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_mithril = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.MITHRIL), 4, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_mithril = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ENDER_MITHRIL), 4, BlockMatcher.forBlock(Blocks.END_STONE));

        ore_nether_morguliron = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.NETHER_MORGULIRON), 4, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_morguliron = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.MORGULIRON), 4, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_morguliron = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ENDER_MORGULIRON), 4, BlockMatcher.forBlock(Blocks.END_STONE));

        ore_nether_ammolite = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.NETHER_AMMOLITE), 3, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_ammolite = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.AMMOLITE), 3, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_ammolite = new WorldGenMinable(TTMFeatures.ORE.getDefaultState().withProperty(BlockOres.ORE_TYPE, BlockOres.EnumType.ENDER_AMMOLITE), 3, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                generateNether(world, random, chunkX, chunkZ);
                break;

            case 0:
                generateOverworld(world, random, chunkX, chunkZ);
                break;

            case 1:
                generateEnd(world, random, chunkX, chunkZ);
        }
    }

    private void generateOverworld(World world, Random random, int worldX, int worldZ) {
        int XX = worldX* 16;
        int ZZ = worldZ* 16;
        BlockPos pos = new BlockPos(XX, 70, ZZ);
        Biome biome = world.getBiomeForCoordsBody(pos);

        if(!TTMConfig.disableOreGen) {
            if (biome == BiomeInit.MORDOR) {
                runGenerator(ore_overworld_morguliron, world, random, worldX, worldZ, 25, 0, 45);
            }

            if (biome == BiomeInit.IRON_HILLS) {
                runGenerator(ore_overworld_mithril, world, random, worldX, worldZ, 25, 70, 155);
            }

            if (biome == BiomeInit.LORINAND) {
                runGenerator(ore_overworld_ammolite, world, random, worldX, worldZ, 25, 70, 155);
            }
        }
    }

    private void generateNether(World world, Random random, int worldX, int worldZ) {
        if(!TTMConfig.disableOreGen) {
            runGenerator(ore_nether_morguliron, world, random, worldX, worldZ, 25, 0, 100);
            runGenerator(ore_nether_mithril, world, random, worldX, worldZ, 25, 0, 100);
            runGenerator(ore_nether_ammolite, world, random, worldX, worldZ, 25, 0, 100);
        }
    }

    private void generateEnd(World world, Random random, int worldX, int worldZ){
        if(!TTMConfig.disableOreGen) {
            runGenerator(ore_end_morguliron, world, random, worldX, worldZ, 25, 0, 256);
            runGenerator(ore_end_mithril, world, random, worldX, worldZ, 25, 0, 256);
            runGenerator(ore_end_ammolite, world, random, worldX, worldZ, 25, 0, 256);
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