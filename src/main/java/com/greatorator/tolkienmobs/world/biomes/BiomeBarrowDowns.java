package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.entity.monster.EntityBarrowWight;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeBarrowDowns extends Biome implements IFogyBiome {
    public BiomeBarrowDowns()
    {

        super(new Biome.BiomeProperties("Tyrn Gorthad")
                .setBaseHeight(0.2F)
                .setHeightVariation(0.2F)
                .setTemperature(0.25F)
                .setRainfall(0.8F)
                .setWaterColor(808080));

        LogHelperTTM.info("Laying the dead to rest...");
        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.grassPerChunk = 10;
        this.decorator.generateFalls = false;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityBarrowWight.class, 100, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 14481884 : 14481884;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 14481884;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0xA9A9A9;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.05F;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}
