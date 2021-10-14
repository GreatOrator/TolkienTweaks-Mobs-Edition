package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

//public class BiomeLorinand extends Biome {

//    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();
//
//    public BiomeLorinand()
//    {
//        super(new BiomeProperties("Lorinand")
//                .setBaseHeight(0.125F)
//                .setHeightVariation(0.05F)
//                .setTemperature(0.8F)
//                .setRainfall(0.4F)
//                .setWaterColor(54011));
//
//        LogHelperTTM.info("Making the land beautiful...");
//        topBlock = Blocks.GRASS.getDefaultState();
//        fillerBlock = Blocks.DIRT.getDefaultState();
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 6;
//        this.decorator.waterlilyPerChunk = 3;
//        this.decorator.flowersPerChunk = 0;
//        this.decorator.grassPerChunk = 15;
//        this.decorator.generateFalls = true;
//    }
//
//    @Override
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
//    {
//        return new WorldGenTreeMallorn(false);
//    }
//
//    public void decorate(World worldIn, Random rand, BlockPos pos)
//    {
//        super.decorate(worldIn, rand, pos);
//
//        generateFlowers(worldIn, rand, 20);
//    }
//
//    private void setSpawnables()
//    {
//
//        this.spawnableCaveCreatureList.clear();
//        this.spawnableCreatureList.clear();
//        this.spawnableMonsterList.clear();
//        this.spawnableWaterCreatureList.clear();
//
//        if (TTMConfig_Old.enableNaturalSpawn) {
//            if (TTMConfig_Old.enablePassive) {
//                if (TTMConfig_Old.enableElves) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMElves.class, 12, 1, 3));
//                }
//            }
//            if (TTMConfig_Old.enableAmbient) {
//                if (TTMConfig_Old.enableSquirrels) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMSquirrel.class, 4, 1, 1));
//                }
//            }
//        }
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
//        return d0 < -0.1D ? 7006317 : 7006317;
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
//                flowers.setBiomeFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.NIPHREDIL));
//                flowers.generate(worldIn, random, blockpos1);
//            }
//        }
//    }
//}