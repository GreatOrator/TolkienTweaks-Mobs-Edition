package com.greatorator.tolkienmobs.world.biome;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.block.itemblock.BlockFlowers;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMSquirrel;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMElves;
//import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.LogHelperTTM;
//import com.greatorator.tolkienmobs.world.world_old.gen.WorldGenCustomFlowers;
//import com.greatorator.tolkienmobs.world.world_old.gen.generators.WorldGenTreeCulumalda;
//import com.greatorator.tolkienmobs.world.world_old.gen.generators.WorldGenTreeLebethron;
//import net.minecraft.block.BlockFlower;
//import net.minecraft.entity.passive.EntityWolf;
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
//public class BiomeFirien extends Biome {
//    /* The Culumalda generator. */
//    protected static final WorldGenTreeCulumalda CULUMALDA_FEATURE = new WorldGenTreeCulumalda(false);
//    /* The Lebethron generator. */
//    protected static final WorldGenTreeLebethron LEBETHRON_FEATURE = new WorldGenTreeLebethron(false);
//    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();
//
//    public BiomeFirien()
//    {
//        super(new BiomeProperties("Firien Wood")
//                .setTemperature(0.7F)
//                .setRainfall(0.8F));
//
//        LogHelperTTM.info("The Last Homely House East of the Sea...");
//        this.topBlock = Blocks.GRASS.getDefaultState();
//        this.fillerBlock = Blocks.DIRT.getDefaultState();
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 10;
//        this.decorator.extraTreeChance = 0.05F;
//        this.decorator.grassPerChunk = 2;
//        this.decorator.mushroomsPerChunk = 12;
//        this.decorator.bigMushroomsPerChunk = 1;
//        this.decorator.waterlilyPerChunk = 4;
//        this.decorator.flowersPerChunk = 0;
//        this.decorator.generateFalls = true;
//    }
//
//    @Override
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
//        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? CULUMALDA_FEATURE : LEBETHRON_FEATURE);
//    }
//
//    public void decorate(World worldIn, Random rand, BlockPos pos)
//    {
//        super.decorate(worldIn, rand, pos);
//
//        generateFlowers(worldIn, rand, 10);
//    }
//
//    private void setSpawnables() {
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
//            if (TTMConfig_Old.enableSpecial) {
//                if (TTMConfig_Old.enableMithrilGolem) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMMithrilGolem.class, 12, 1, 1));
//                }
//            }
//        }
//        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
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
//
//            if (y > 0) {
//                int y2 = random.nextInt(y);
//                BlockPos blockpos1 = decorator.chunkPos.add(x, y2, z);
//                flowers.setGenFlowerList(true);
//                flowers.setBiomeFlower(random.nextInt(10) == 0 ? TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.NIPHREDIL) : random.nextInt(10) == 0 ? red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP) : red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP));
//                flowers.generate(worldIn, random, blockpos1);
//            }
//        }
//    }
//}