package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.EntityTreeEnt;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMallornTree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeLorinand extends Biome implements IFogyBiome {

    protected static final WorldGenAbstractTree TREE = new WorldGenMallornTree(false);

    public BiomeLorinand()
    {
        super(new BiomeProperties("Lorinand")
                .setBaseHeight(.15F)
                .setHeightVariation(.1F)
                .setTemperature(1.0F)
                .setWaterColor(54011));

        System.out.println("Making the land beautiful...");
        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 6;
        this.decorator.waterlilyPerChunk = 3;
        this.decorator.flowersPerChunk = 20;
        this.decorator.grassPerChunk = 15;
        this.decorator.generateFalls = true;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTreeEnt.class, 100, 1, 1));
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
        return d0 < -0.1D ? 7006317 : 7006317;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 7006317;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0x8bFF8b;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.03F;
    }
}
