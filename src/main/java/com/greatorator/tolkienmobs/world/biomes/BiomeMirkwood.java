package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.monster.EntityGoblin;
import com.greatorator.tolkienmobs.entity.monster.EntityMirkwoodSpider;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenMirkwoodTree;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeMirkwood extends Biome implements IFogyBiome {

    protected static final WorldGenAbstractTree TREE = new WorldGenMirkwoodTree(false);

    public BiomeMirkwood()
    {

        super(new BiomeProperties("Mirkwood")
                .setBaseHeight(0.2F)
                .setHeightVariation(0.2F)
                .setTemperature(0.25F)
                .setRainfall(0.8F)
                .setWaterColor(3091811));

        LogHelperTTM.info("Preparing for the necromancer's return...");
        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 8;
        this.decorator.deadBushPerChunk = 10;
        this.decorator.grassPerChunk = 5;
        this.decorator.mushroomsPerChunk = 24;
        this.decorator.bigMushroomsPerChunk = 1;
        this.decorator.generateFalls = false;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityMirkwoodSpider.class, 100, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGoblin.class, 100, 3, 6));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return TREE;
    }

    @Override
    public void addDefaultFlowers() {
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.MIRKWOOD), 10);
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

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0x5b5f61;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.2F;
    }
}
