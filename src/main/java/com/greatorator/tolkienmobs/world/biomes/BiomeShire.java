//package com.greatorator.tolkienmobs.world.biomes;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.block.BlockFlowers;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMSquirrel;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMToad;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMAuroch;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMGoat;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMHobbit;
//import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.LogHelperTTM;
//import com.greatorator.tolkienmobs.world.gen.WorldGenCustomFlowers;
//import net.minecraft.block.BlockFlower;
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
//public class BiomeShire extends Biome {
//    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();
//
//    public BiomeShire()
//    {
//        super(new BiomeProperties("The Shire")
//                .setBaseHeight(0.125F)
//                .setHeightVariation(0.05F)
//                .setTemperature(0.8F)
//                .setRainfall(0.4F));
//
//        LogHelperTTM.info("And that means comfort...");
//        this.topBlock = Blocks.GRASS.getDefaultState();
//        this.fillerBlock = Blocks.DIRT.getDefaultState();
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 5;
//        this.decorator.extraTreeChance = 0.05F;
//        this.decorator.flowersPerChunk = 0;
//        this.decorator.grassPerChunk = 15;
//        this.decorator.generateFalls = true;
//    }
//
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
//    {
//        return (WorldGenAbstractTree)(rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
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
//            if (TTMConfig_Old.enablePassive) {
//                if (TTMConfig_Old.enableHobbits) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMHobbit.class, 12, 1, 3));
//                }
//                if (TTMConfig_Old.enableAuroch) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMAuroch.class, 4, 2, 3));
//                }
//                if (TTMConfig_Old.enableGoats) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMGoat.class, 4, 2, 3));
//                }
//            }
//            if (TTMConfig_Old.enableAmbient) {
//                if (TTMConfig_Old.enableSquirrels) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMSquirrel.class, 4, 1, 1));
//                }
//                if (TTMConfig_Old.enableFrogs) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMToad.class, 4, 1, 1));
//                }
//            }
//            if (TTMConfig_Old.enableSpecial) {
//                if (TTMConfig_Old.enableMithrilGolem) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMMithrilGolem.class, 12, 1, 1));
//                }
//            }
//        }
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityDonkey.class, 1, 1, 3));
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
//        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
//        return d0 < -0.1D ? 2292007 : 2292007;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getFoliageColorAtPos(BlockPos pos)
//    {
//        return 2292007;
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
//                flowers.setBiomeFlower(random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.ATHELAS) : random.nextInt(10) == 0 ? yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION) : red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP));
//                flowers.generate(worldIn, random, blockpos1);
//            }
//        }
//    }
//}