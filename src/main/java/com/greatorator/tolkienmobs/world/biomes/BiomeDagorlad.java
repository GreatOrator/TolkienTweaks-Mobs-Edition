package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.monster.EntityMordorOrc;
import com.greatorator.tolkienmobs.entity.monster.EntityMumakil;
import com.greatorator.tolkienmobs.entity.monster.EntityUrukHai;
import com.greatorator.tolkienmobs.entity.monster.EntityWarg;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeDagorlad extends Biome {

    public BiomeDagorlad()
    {
        super(new BiomeProperties("The Brown Lands")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.05F)
                .setTemperature(0.8F)
                .setRainfall(0.4F));

        LogHelperTTM.info("Where have the Entwives gone...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();

        this.decorator.treesPerChunk = 0;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.generateFalls = true;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityUrukHai.class, 100, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMordorOrc.class, 100, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWarg.class, 100, 1, 1));
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
        BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
        BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;
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
        return d0 < -0.1D ? 14596231 : 14596231;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 14596231;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}
