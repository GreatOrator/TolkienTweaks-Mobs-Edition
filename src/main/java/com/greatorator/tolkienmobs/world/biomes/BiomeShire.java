package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BiomeShire extends Biome {

    public BiomeShire()
    {
        super(new BiomeProperties("The Shire")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.05F)
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

        addFlowers();
        setSpawnables();
    }

    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
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
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 3);
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
            if (TTMConfig.enablePassive) {
                if (TTMConfig.enableHobbits) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMHobbit.class, 12, 1, 3));
                }
                if (TTMConfig.enableFrogs) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMToad.class, 4, 1, 1));
                }
                if (TTMConfig.enableAuroch) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMAuroch.class, 4, 2, 3));
                }
                if (TTMConfig.enableGoats) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMGoat.class, 4, 2, 3));
                }
                if (TTMConfig.enableSquirrels) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMSquirrel.class, 4, 1, 1));
                }
            }
        }
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
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

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}