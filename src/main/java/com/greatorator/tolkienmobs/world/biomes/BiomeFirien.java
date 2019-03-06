package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.passive.EntityTMElves;
import com.greatorator.tolkienmobs.entity.passive.EntityTMSquirrel;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeCulumalda;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeLebethron;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BiomeFirien extends Biome {
    /* The Culumalda generator. */
    protected static final WorldGenTreeCulumalda CULUMALDA_FEATURE = new WorldGenTreeCulumalda(false);
    /* The Lebethron generator. */
    protected static final WorldGenTreeLebethron LEBETHRON_FEATURE = new WorldGenTreeLebethron(false);

    public BiomeFirien()
    {
        super(new BiomeProperties("Firien Wood")
                .setTemperature(0.7F)
                .setRainfall(0.8F));

        LogHelperTTM.info("The Last Homely House East of the Sea...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 10;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.grassPerChunk = 2;
        this.decorator.mushroomsPerChunk = 12;
        this.decorator.bigMushroomsPerChunk = 1;
        this.decorator.waterlilyPerChunk = 4;
        this.decorator.flowersPerChunk = 10;
        this.decorator.generateFalls = true;

        addFlowers();
        setSpawnables();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? CULUMALDA_FEATURE : LEBETHRON_FEATURE);
    }

    public List<FlowerEntry> getFlowerList()
    {
        return flowers;
    }

    private void addFlowers()
    {
        flowers.clear();
        BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 3);
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.NIPHREDIL), 20);
    }

    private void setSpawnables() {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enablePassive) {
                if (TTMConfig.enableElves) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMElves.class, 12, 1, 3));
                }
                if (TTMConfig.enableSquirrels) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMSquirrel.class, 4, 1, 1));
                }
            }
        }
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}