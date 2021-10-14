package com.greatorator.tolkienmobs.world.biome;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.block.itemblock.BlockFlowers;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMMidgeFly;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMToad;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMHuron;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMMirkwoodSpider;
//import com.greatorator.tolkienmobs.handler.handler_old.interfaces.IFogyBiome;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.LogHelperTTM;
//import com.greatorator.tolkienmobs.world.world_old.gen.WorldGenCustomFlowers;
//import com.greatorator.tolkienmobs.world.world_old.gen.generators.WorldGenTreeMirkwood;
//import net.minecraft.block.BlockFlower;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Random;
//
//public class BiomeMirkwood extends Biome implements IFogyBiome {
//    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();
//
//    public BiomeMirkwood()
//    {
//        super(new BiomeProperties("Mirkwood")
//                .setBaseHeight(0.2F)
//                .setHeightVariation(0.2F)
//                .setTemperature(0.25F)
//                .setRainfall(0.8F)
//                .setWaterColor(0));
//
//        LogHelperTTM.info("Preparing for the necromancer's return...");
//        topBlock = Blocks.GRASS.getDefaultState();
//        fillerBlock = Blocks.DIRT.getDefaultState();
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 8;
//        this.decorator.deadBushPerChunk = 10;
//        this.decorator.grassPerChunk = 5;
//        this.decorator.mushroomsPerChunk = 24;
//        this.decorator.bigMushroomsPerChunk = 1;
//        this.decorator.generateFalls = false;
//        this.decorator.flowersPerChunk = 0;
//    }
//
//    @Override
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
//    {
//        return new WorldGenTreeMirkwood(false);
//    }
//
//    public void decorate(World worldIn, Random rand, BlockPos pos)
//    {
//        super.decorate(worldIn, rand, pos);
//
//        generateFlowers(worldIn, rand, 6);
//    }
//
//    private void setSpawnables()
//    {
//        this.spawnableCaveCreatureList.clear();
//        this.spawnableCreatureList.clear();
//        this.spawnableMonsterList.clear();
//        this.spawnableWaterCreatureList.clear();
//
//        if (TTMConfig_Old.enableNaturalSpawn) {
//            if (TTMConfig_Old.enableMonster) {
//                if (TTMConfig_Old.enableMirkwoodSpiders) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMMirkwoodSpider.class, 10, 1, 1));
//                }
//                if (TTMConfig_Old.enableHurons) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMHuron.class, 8, 1, 2));
//                }
//            }
//            if (TTMConfig_Old.enableAmbient) {
//                if (TTMConfig_Old.enableMidgeFlies) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMMidgeFly.class, 4, 1, 1));
//                }
//                if (TTMConfig_Old.enableFrogs) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMToad.class, 1, 1, 1));
//                }
//            }
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
//        return d0 < -0.1D ? 738353 : 738353;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getFoliageColorAtPos(BlockPos pos)
//    {
//        return 738353;
//    }
//
//    @Override
//    public int getFogColour(PlayerEntity player) {
//        return 0x5b5f61;
//    }
//
//    @Override
//    public float getFogDensity(PlayerEntity player) {
//        return 0.2F;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getSkyColorByTemp(float currentTemperature)
//    {
//        currentTemperature = currentTemperature / 3.0F;
//        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
//        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
//    }
//
//    private void generateFlowers(World worldIn, Random random, int cnt) {
//        for (int i = 0; i < cnt; ++i) {
//            int x = random.nextInt(16) + 8;
//            int z = random.nextInt(16) + 8;
//            int y = worldIn.getHeight(decorator.chunkPos.add(x, 0, z)).getY() + 32;
//
//            BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
//            BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;
//
//            if (y > 0) {
//                int y2 = random.nextInt(y);
//                BlockPos blockpos1 = decorator.chunkPos.add(x, y2, z);
//                flowers.setGenFlowerList(true);
//                flowers.setBiomeFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.MIRKWOOD));
//                flowers.generate(worldIn, random, blockpos1);
//            }
//        }
//    }
//}