package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.monster.EntityMordorOrc;
import com.greatorator.tolkienmobs.entity.monster.EntityTroll;
import com.greatorator.tolkienmobs.entity.monster.EntityUrukHai;
import com.greatorator.tolkienmobs.entity.monster.EntityWarg;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeMordor extends Biome {
    public BiomeMordor()
    {
        super(new Biome.BiomeProperties("Mordor")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.05F)
                .setTemperature(2.0F)
                .setRainfall(0.0F)
                .setRainDisabled());

        LogHelperTTM.info("Beware the all-seeing eye...");
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = -999;
        this.decorator.sandPatchesPerChunk = 6;
        this.decorator.deadBushPerChunk = 9;
        this.decorator.reedsPerChunk = 50;
        this.decorator.cactiPerChunk = 10;
        this.decorator.generateFalls = true;

        setSpawnables();
    }

    private void setSpawnables()
    {

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableUrukhai) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityUrukHai.class, 10, 1, 1));
                }
                if (TTMConfig.enableMordorOrcs) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityMordorOrc.class, 12, 1, 1));
                }
                if (TTMConfig.enableWargs) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityWarg.class, 6, 1, 1));
                }
                if (TTMConfig.enableCaveTrolls) {
                    this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTroll.class, 8, 1, 1));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 6908265 : 6908265;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 6908265;
    }
}