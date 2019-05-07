package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMTreeEnt;
import com.greatorator.tolkienmobs.handler.interfaces.IFogyBiome;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomFlowers;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeFangorn;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeSmFangorn;
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

public class BiomeFangorn extends Biome implements IFogyBiome {
    /* The Culumalda generator. */
    protected static final WorldGenTreeSmFangorn SMALL_FANGORN_FEATURE = new WorldGenTreeSmFangorn(false);
    /* The Lebethron generator. */
    protected static final WorldGenTreeFangorn FANGORN_FEATURE = new WorldGenTreeFangorn(false);
    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();

    public BiomeFangorn()
    {
        super(new BiomeProperties("Fangorn Forest")
                .setTemperature(0.7F)
                .setRainfall(0.8F));

        LogHelperTTM.info("Last remnant of the great forests of Eriador...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();

        setSpawnables();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 5;
        this.decorator.extraTreeChance = 0.05F;
        this.decorator.grassPerChunk = 6;
        this.decorator.mushroomsPerChunk = 12;
        this.decorator.waterlilyPerChunk = 4;
        this.decorator.flowersPerChunk = 0;
        this.decorator.generateFalls = true;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? SMALL_FANGORN_FEATURE : FANGORN_FEATURE);
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
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableTreeEnts) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMTreeEnt.class, 15, 1, 1));
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
                flowers.setBiomeFlower(random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ALFIRIN) : random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS) : red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY));
                flowers.generate(worldIn, random, blockpos1);
            }
        }
    }
}