//package com.greatorator.tolkienmobs.world.world_old.biomes;
//
//import com.greatorator.tolkienmobs.TTMConfig_Old;
//import com.greatorator.tolkienmobs.block.itemblock.BlockFlowers;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMMidgeFly;
//import com.greatorator.tolkienmobs.entity.ambient.EntityTMToad;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMFellSpirit;
//import com.greatorator.tolkienmobs.entity.hostile.EntityTMSwampHag;
//import com.greatorator.tolkienmobs.handler.handler_old.interfaces.IFogyBiome;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import com.greatorator.tolkienmobs.utils.LogHelperTTM;
//import com.greatorator.tolkienmobs.world.world_old.gen.WorldGenCustomFlowers;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.chunk.ChunkPrimer;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//import net.minecraft.world.gen.feature.WorldGenFossils;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Random;
//
//public class BiomeMarshes extends Biome implements IFogyBiome
//{
//    protected static final IBlockState WATER_LILY = Blocks.WATERLILY.getDefaultState();
//    private WorldGenCustomFlowers flowers = new WorldGenCustomFlowers();
//
//    public BiomeMarshes()
//    {
//        super(new BiomeProperties("Nindalf Marsh")
//                .setBaseHeight(-0.2F)
//                .setHeightVariation(0.1F)
//                .setTemperature(0.8F)
//                .setRainfall(0.9F)
//                .setWaterColor(14745540));
//
//        LogHelperTTM.info("Where the Entwash meets the Anduin...");
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 2;
//        this.decorator.flowersPerChunk = 0;
//        this.decorator.deadBushPerChunk = 1;
//        this.decorator.mushroomsPerChunk = 8;
//        this.decorator.reedsPerChunk = 10;
//        this.decorator.clayPerChunk = 1;
//        this.decorator.waterlilyPerChunk = 4;
//        this.decorator.sandPatchesPerChunk = 0;
//        this.decorator.gravelPatchesPerChunk = 0;
//        this.decorator.grassPerChunk = 5;
//    }
//
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
//    {
//        return SWAMP_FEATURE;
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
//            if (TTMConfig_Old.enableMonster) {
//                if (TTMConfig_Old.enableFellSpirit) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMFellSpirit.class, 2, 1, 3));
//                }
//                if (TTMConfig_Old.enableSwampHag) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMSwampHag.class, 2, 1, 3));
//                }
//            }
//            if (TTMConfig_Old.enableAmbient) {
//                if (TTMConfig_Old.enableMidgeFlies) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMMidgeFly.class, 4, 1, 1));
//                }
//            }
//            if (TTMConfig_Old.enablePassive) {
//                if (TTMConfig_Old.enableFrogs) {
//                    this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityTMToad.class, 1, 1, 1));
//                }
//            }
//        }
//    }
//
//    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);
//
//        if (d0 > 0.0D)
//        {
//            int i = x & 15;
//            int j = z & 15;
//
//            for (int k = 255; k >= 0; --k)
//            {
//                if (chunkPrimerIn.getBlockState(j, k, i).getMaterial() != Material.AIR)
//                {
//                    if (k == 62 && chunkPrimerIn.getBlockState(j, k, i).getBlock() != Blocks.WATER)
//                    {
//                        chunkPrimerIn.setBlockState(j, k, i, WATER);
//
//                        if (d0 < 0.12D)
//                        {
//                            chunkPrimerIn.setBlockState(j, k + 1, i, WATER_LILY);
//                        }
//                    }
//
//                    break;
//                }
//            }
//        }
//
//        this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
//    }
//
//    public void decorate(World worldIn, Random rand, BlockPos pos)
//    {
//        super.decorate(worldIn, rand, pos);
//
//        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
//        if (rand.nextInt(64) == 0)
//        {
//            (new WorldGenFossils()).generate(worldIn, rand, pos);
//        }
//
//
//        generateFlowers(worldIn, rand, 3);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
//        return d0 < -0.1D ? 6059598 : 6316071;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getFoliageColorAtPos(BlockPos pos)
//    {
//        return 6316071;
//    }
//
//    @Override
//    public int getFogColour(PlayerEntity player) {
//    return 0x5b5f61;
//}
//
//    @Override
//    public float getFogDensity(PlayerEntity player) {
//        return 0.02F;
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
//            if (y > 0) {
//                int y2 = random.nextInt(y);
//                BlockPos blockpos1 = decorator.chunkPos.add(x, y2, z);
//                flowers.setGenFlowerList(true);
//                flowers.setBiomeFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.SWAMPMILKWEED));
//                flowers.generate(worldIn, random, blockpos1);
//            }
//        }
//    }
//}