package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMMordorOrc;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMUrukHai;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMWarg;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomFlowers;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeRubble;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeRuin;
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
    /* The ancient ruin generator. */
    protected static final WorldGenBiomeRuin ANCIENT_RUIN_FEATURE = new WorldGenBiomeRuin(false);
    /* The rubble generator. */
    protected static final WorldGenBiomeRubble RUBBLE_FEATURE = new WorldGenBiomeRubble(false);
    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();

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

        setSpawnables();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 1;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 10;
        this.decorator.generateFalls = true;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? ANCIENT_RUIN_FEATURE : RUBBLE_FEATURE);
    }

    private void setSpawnables()
    {
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableUrukhai) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMUrukHai.class, 5, 1, 1));
                }
                if (TTMConfig.enableMordorOrcs) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMMordorOrc.class, 5, 1, 1));
                }
                if (TTMConfig.enableWargs) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMWarg.class, 1, 1, 1));
                }
            }
        }
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        generateFlowers(worldIn, rand, 3);
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
                flowers.setBiomeFlower(random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS) : random.nextInt(10) == 0 ? yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION) : random.nextInt(10) == 0 ? red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY) : red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA));
                flowers.generate(worldIn, random, blockpos1);
            }
        }
    }
}