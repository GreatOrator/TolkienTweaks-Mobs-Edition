package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.monster.EntityTreeEnt;
import com.greatorator.tolkienmobs.handler.interfaces.IFogyBiome;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BiomeFangorn extends Biome implements IFogyBiome {

    public BiomeFangorn()
    {
        super(new BiomeProperties("Fangorn Forest")
                .setTemperature(0.7F)
                .setRainfall(0.8F));

        LogHelperTTM.info("Last remnant of the great forests of Eriador...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.grassPerChunk = 6;
        this.decorator.mushroomsPerChunk = 12;
        this.decorator.waterlilyPerChunk = 4;
        this.decorator.flowersPerChunk = 10;
        this.decorator.generateFalls = true;

        addFlowers();
        setSpawnables();
    }

    public List<FlowerEntry> getFlowerList()
    {
        return flowers;
    }

    private void addFlowers()
    {
        flowers.clear();
        BlockFlower red = Blocks.RED_FLOWER;
        BlockFlower yel = Blocks.YELLOW_FLOWER;
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.PINK_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 3);
        addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY), 3);
        addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 3);
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS), 20);
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ALFIRIN), 20);
    }

    private void setSpawnables()
    {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableTreeEnts) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTreeEnt.class, 15, 1, 1));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 5156174 : 5156174;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 5156174;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0x5b5f61;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.02F;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}