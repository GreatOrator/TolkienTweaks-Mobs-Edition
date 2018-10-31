package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.EntityTreeEnt;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMallornTree;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeBindbole extends Biome {

    protected static final WorldGenAbstractTree TREE = new WorldGenMallornTree();

    public BiomeBindbole()
    {
        super(new BiomeProperties("Bindbole")
                .setBaseHeight(.15F)
                .setHeightVariation(.1F)
                .setTemperature(1.0F)
                .setWaterColor(1747497));

        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 6;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTreeEnt.class, 100, 4, 4));
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
}
