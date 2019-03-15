package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.passive.EntityTMDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityTMGoat;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMThrush;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeIronHills extends Biome {
    private final WorldGenerator silverfishSpawner = new WorldGenMinable(Blocks.MONSTER_EGG.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9);

    public BiomeIronHills()
    {

        super(new BiomeProperties("Iron Hills")
                .setBaseHeight(1.0F)
                .setHeightVariation(.5F)
                .setTemperature(0.2F)
                .setRainfall(0.3F)
                .setSnowEnabled());

        LogHelperTTM.info("Realm of the Dwarves of Durin's Folk...");
        topBlock = Blocks.STONE.getDefaultState();
        fillerBlock = Blocks.STONE.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.gravelPatchesPerChunk = 1;
        this.decorator.gravelGen  = new WorldGenSand(Blocks.COBBLESTONE, 6);
        this.decorator.generateFalls = false;

        setSpawnables();
    }

    private void setSpawnables()
    {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enablePassive) {
                if (TTMConfig.enableDwarves) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMDwarf.class, 12, 1, 3));
                }
                if (TTMConfig.enableGoats) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMGoat.class, 12, 2, 3));
                }
            if (TTMConfig.enableAmbient) {
                if (TTMConfig.enableThrush) {
                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMThrush.class, 12, 1, 1));
                }
            }
            }
        }
        this.spawnableMonsterList.add(new SpawnListEntry(EntityLlama.class, 12, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
    }

    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Pre(worldIn, rand, pos));
        WorldGenerator emeralds = new BiomeIronHills.EmeraldGenerator();
        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, emeralds, pos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.EMERALD))
            emeralds.generate(worldIn, rand, pos);

        for (int j1 = 0; j1 < 7; ++j1)
        {
            int k1 = rand.nextInt(16);
            int l1 = rand.nextInt(64);
            int i2 = rand.nextInt(16);
            if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(worldIn, rand, silverfishSpawner, pos.add(j1, k1, l1), net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.SILVERFISH))
                this.silverfishSpawner.generate(worldIn, rand, pos.add(k1, l1, i2));
        }
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new net.minecraftforge.event.terraingen.OreGenEvent.Post(worldIn, rand, pos));
    }

    private static class EmeraldGenerator extends WorldGenerator
    {
        @Override
        public boolean generate(World worldIn, Random rand, BlockPos pos)
        {
            int count = 3 + rand.nextInt(6);
            for (int i = 0; i < count; i++)
            {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-114332
                BlockPos blockpos = pos.add(rand.nextInt(16) + offset, rand.nextInt(28) + 4, rand.nextInt(16) + offset);

                net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
                if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, net.minecraft.block.state.pattern.BlockMatcher.forBlock(Blocks.STONE)))
                {
                    worldIn.setBlockState(blockpos, Blocks.EMERALD_ORE.getDefaultState(), 16 | 2);
                }
            }
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}
