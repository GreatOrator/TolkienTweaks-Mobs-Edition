package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.monster.EntityMumakil;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeHaradwaith extends Biome {
    public BiomeHaradwaith()
    {
        super(new Biome.BiomeProperties("Haradwaith")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.05F)
                .setTemperature(2.0F)
                .setRainfall(0.0F)
                .setRainDisabled());

        LogHelperTTM.info("In the land where the stars are strange...");
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 5;
        this.decorator.reedsPerChunk = 50;
        this.decorator.cactiPerChunk = 10;
        this.decorator.generateFalls = true;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMumakil.class, 100, 1, 1));


    }
}
