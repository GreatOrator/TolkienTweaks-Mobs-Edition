package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMSquirrel;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMHuron;
import com.greatorator.tolkienmobs.handler.interfaces.IFogyBiome;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomFlowers;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeOldForest;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeOldForest extends Biome implements IFogyBiome {
    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();

    public BiomeOldForest()
    {
        super(new BiomeProperties("Old Forest")
                .setTemperature(0.7F)
                .setRainfall(0.8F));

        LogHelperTTM.info("Realm of Tom Bombadil & the Withywindle");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();

        this.decorator.waterlilyPerChunk = 4;
        this.decorator.treesPerChunk = 7;
        this.decorator.extraTreeChance = 0.5F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 6;
        this.decorator.mushroomsPerChunk = 12;
        this.decorator.generateFalls = true;

        setSpawnables();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return new WorldGenTreeOldForest(false);
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        generateFlowers(worldIn, rand, 10);
    }

    private void setSpawnables()
    {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableAmbient) {
                if (TTMConfig.enableSquirrels) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMSquirrel.class, 1, 1, 1));
                }
                if (TTMConfig.enableHurons) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMHuron.class, 1, 1, 2));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 5163086 : 5156174;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 5163086;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0x707071;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.01F;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }

    private void generateFlowers(World worldIn, Random random, int cnt) {
        for (int i = 0; i < cnt; ++i) {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = worldIn.getHeight(decorator.chunkPos.add(x, 0, z)).getY() + 32;

            BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
            BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;

            if (y > 0) {
                int y2 = random.nextInt(y);
                BlockPos blockpos1 = decorator.chunkPos.add(x, y2, z);
                flowers.setGenFlowerList(true);
                flowers.setBiomeFlower(random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ALFIRIN) : random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS) : Blocks.RED_FLOWER.getDefaultState().withProperty(Blocks.RED_FLOWER.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY));
                flowers.generate(worldIn, random, blockpos1);
            }
        }
    }
}