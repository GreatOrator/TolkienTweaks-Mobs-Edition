package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMirkwoodTree;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeMirkwood extends Biome {

    protected static final WorldGenAbstractTree TREE = new WorldGenMirkwoodTree();

    public BiomeMirkwood()
    {
        super(new BiomeProperties("Mirkwood")
                .setBaseHeight(.15F)
                .setHeightVariation(.1F)
                .setTemperature(1.0F)
                .setWaterColor(3091822));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 6;

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

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 733213 : 733213;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 733213;
    }
}
