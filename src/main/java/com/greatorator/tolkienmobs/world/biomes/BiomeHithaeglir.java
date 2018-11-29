package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.monster.EntityGoblin;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeHithaeglir extends Biome implements IFogyBiome {

    public BiomeHithaeglir()
    {

        super(new BiomeProperties("Misty Mountains")
                .setBaseHeight(1.0F)
                .setHeightVariation(.5F)
                .setTemperature(0.1F)
                .setRainfall(0.3F)
                .setSnowEnabled()
                .setWaterColor(3091811));

        LogHelperTTM.info("Far over the misty mountains cold...");
        topBlock = Blocks.SNOW.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.generateFalls = false;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityGoblin.class, 100, 1, 3));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return new WorldGenTaiga2(false);
    }

    public float getSpawningChance()
    {
        return 0.07F;
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 15921906 : 15921906;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 14614494;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0xe6e6e6;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.1F;
    }
}
