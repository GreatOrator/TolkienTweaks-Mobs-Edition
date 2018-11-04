package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMirkwoodTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeMirkwood extends Biome {

    protected static final WorldGenAbstractTree TREE = new WorldGenMirkwoodTree(false);

    public BiomeMirkwood()
    {
        super(new BiomeProperties("Mirkwood")
                .setBaseHeight(.15F)
                .setHeightVariation(.3F)
                .setTemperature(0.6F)
                .setWaterColor(3091811));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 8;
        this.decorator.deadBushPerChunk = 10;
        this.decorator.flowersPerChunk = 5;
        this.decorator.grassPerChunk = 10;
        this.decorator.mushroomsPerChunk = 24;
        this.decorator.bigMushroomsPerChunk = 1;
        this.decorator.generateFalls = false;
        
        //this.skyColor = 5988193;
        this.fogColor = 5988193;
        this.fogDensity = 0.5F;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySpider.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 95, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 5, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWitch.class, 5, 1, 1));
        this.spawnableCaveCreatureList.add(new Biome.SpawnListEntry(EntityBat.class, 10, 8, 8));
    }
    
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return TREE;
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        return BlockFlower.EnumFlowerType.BLUE_ORCHID;
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 738353 : 738353;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 738353;
    }
}
