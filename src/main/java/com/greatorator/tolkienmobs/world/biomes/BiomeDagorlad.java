package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMMordorOrc;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMUrukHai;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMWarg;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeRuin;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenBiomeRubble;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BiomeDagorlad extends Biome {
    /* The ancient ruin generator. */
    protected static final WorldGenBiomeRuin ANCIENT_RUIN_FEATURE = new WorldGenBiomeRuin(false);
    /* The rubble generator. */
    protected static final WorldGenBiomeRubble RUBBLE_FEATURE = new WorldGenBiomeRubble(false);

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

        this.decorator.treesPerChunk = 1;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.flowersPerChunk = 4;
        this.decorator.grassPerChunk = 10;
        this.decorator.generateFalls = true;

        addFlowers();
        setSpawnables();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? ANCIENT_RUIN_FEATURE : RUBBLE_FEATURE);
    }

    public List<FlowerEntry> getFlowerList()
    {
        return flowers;
    }

    private void addFlowers()
    {
        flowers.clear();
        BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
        BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY), 3);
        addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 3);
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS), 20);
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