package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.monster.EntityTMCrebain;
import com.greatorator.tolkienmobs.entity.monster.EntityTMGoblin;
import com.greatorator.tolkienmobs.entity.monster.EntityTMTroll;
import com.greatorator.tolkienmobs.handler.interfaces.IFogyBiome;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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

        setSpawnables();
    }

    private void setSpawnables()
    {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableGoblins) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMGoblin.class, 12, 1, 3));
                }
                if (TTMConfig.enableCaveTrolls) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMTroll.class, 8, 1, 1));
                }
                if (TTMConfig.enableCrebain) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMCrebain.class, 1, 1, 1));
                }
            }
        }
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

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}