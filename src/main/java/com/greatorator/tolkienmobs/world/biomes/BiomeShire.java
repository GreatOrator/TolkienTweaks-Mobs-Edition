package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeShire extends Biome {

    public BiomeShire()
    {
        super(new BiomeProperties("The Shire")
                .setBaseHeight(0.45F)
                .setHeightVariation(0.3F)
                .setTemperature(0.8F)
                .setRainfall(0.4F));

        LogHelperTTM.info("And that means comfort...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();

        this.decorator.treesPerChunk = 5;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 6;
        this.decorator.grassPerChunk = 15;
        this.decorator.generateFalls = true;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityHobbit.class, 100, 1, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityDonkey.class, 1, 1, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCow.class, 8, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }

    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() / 200.0D, (double)pos.getZ() / 200.0D);

        if (d0 < -0.8D)
        {
            int j = rand.nextInt(4);

            switch (j)
            {
                case 0:
                    return BlockFlower.EnumFlowerType.ORANGE_TULIP;
                case 1:
                    return BlockFlower.EnumFlowerType.RED_TULIP;
                case 2:
                    return BlockFlower.EnumFlowerType.PINK_TULIP;
                case 3:
                default:
                    return BlockFlower.EnumFlowerType.WHITE_TULIP;
            }
        }
        else if (rand.nextInt(3) > 0)
        {
            int i = rand.nextInt(3);

            if (i == 0)
            {
                return BlockFlower.EnumFlowerType.POPPY;
            }
            else
            {
                return i == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY;
            }
        }
        else
        {
            return BlockFlower.EnumFlowerType.DANDELION;
        }
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)(pos.getX() + 8) / 200.0D, (double)(pos.getZ() + 8) / 200.0D);

        if (d0 < -0.8D)
        {
            this.decorator.flowersPerChunk = 15;
            this.decorator.grassPerChunk = 5;
        }
        else
        {
            this.decorator.flowersPerChunk = 4;
            this.decorator.grassPerChunk = 10;
            DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

            if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
                for (int i = 0; i < 7; ++i)
                {
                    int j = rand.nextInt(16) + 8;
                    int k = rand.nextInt(16) + 8;
                    int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
                    DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
                }
        }
        super.decorate(worldIn, rand, pos);
    }

    @Override
    public void addDefaultFlowers()
    {
        BlockFlower red = Blocks.RED_FLOWER;
        BlockFlower yel = Blocks.YELLOW_FLOWER;
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.PINK_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY), 20);
        addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 2292007 : 2292007;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 2292007;
    }
}
