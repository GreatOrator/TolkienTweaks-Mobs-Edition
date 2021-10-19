package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeMordor {
private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
        }

public static Biome makeBiomeMordor(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        spawnInf.setPlayerCanSpawn();
//        TTMBiomeFeatures.ttmSwampSpawns(spawnInf);
//        DefaultBiomeFeatures.commonSpawns(spawnInf);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
        .surfaceBuilder(ConfiguredSurfaceBuilders.SWAMP);
        DefaultBiomeFeatures.addFossilDecoration(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addMorgulironOres(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addDesolation(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
        LOGGER.info("Beware the all-seeing eye...");
        return (new Biome.Builder())
        .precipitation(Biome.RainType.NONE)
        .biomeCategory(Biome.Category.DESERT)
        .depth(0.125F)
        .scale(0.5F)
        .temperature(2.0F)
        .downfall(0.0F)
        .specialEffects((new BiomeAmbience.Builder())
        .skyColor(getSkyColorWithTemperatureModifier(2.0F))
        .foliageColorOverride(6908265)
        .fogColor(0)
        .waterColor(4159204)
        .waterFogColor(329011)
        .fogColor(12638463)
        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
        .build())
        .mobSpawnSettings(spawnInf.build())
        .generationSettings(biomegenerationsettings$builder.build())
        .build();
        }
//    /* The rubble generator. */
//    protected static final WorldGenBiomeRubble SPIKE_FEATURE = new WorldGenBiomeRubble(false);
//    /* The dead tree generator. */
//    protected static final WorldGenTreeDead DEAD_TREE_FEATURE = new WorldGenTreeDead(false);
//    public BiomeMordor()
//    {
//        super(new Biome.BiomeProperties("Mordor")
//                .setBaseHeight(0.125F)
//                .setHeightVariation(0.05F)
//                .setTemperature(2.0F)
//                .setRainfall(0.0F)
//                .setRainDisabled());
//
//        LogHelperTTM.info("Beware the all-seeing eye...");
//        this.topBlock = Blocks.GRASS.getDefaultState();
//        this.fillerBlock = Blocks.STONE.getDefaultState();
//
//        setSpawnables();
//
//        this.decorator = this.createBiomeDecorator();
//        this.decorator.treesPerChunk = 1;
//        this.decorator.extraTreeChance = 0.05F;
//        this.decorator.sandPatchesPerChunk = 6;
//        this.decorator.deadBushPerChunk = 9;
//        this.decorator.reedsPerChunk = 50;
//        this.decorator.cactiPerChunk = 10;
//        this.decorator.generateFalls = true;
//    }
//
//    @Override
//    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
//        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? DEAD_TREE_FEATURE : SPIKE_FEATURE);
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
//            if (TTMConfig_Old.enableMonster) {
//                if (TTMConfig_Old.enableUrukhai) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMUrukHai.class, 10, 1, 1));
//                }
//                if (TTMConfig_Old.enableMordorOrcs) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMMordorOrc.class, 12, 1, 1));
//                }
//                if (TTMConfig_Old.enableWargs) {
//                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMWarg.class, 6, 1, 1));
//                }
//                if (TTMConfig_Old.enableCaveTrolls) {
//                    this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTMTroll.class, 8, 1, 1));
//                }
//                if (TTMConfig_Old.enableTMDuergar) {
//                    this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityTMDuergar.class, 8, 1, 1));
//                }
//            }
//            if (TTMConfig_Old.enableAmbient) {
//                if (TTMConfig_Old.enableMidgeFlies) {
//                    this.spawnableCreatureList.add(new SpawnListEntry(EntityTMMidgeFly.class, 4, 1, 1));
//                }
//            }
//        }
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
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
//        return d0 < -0.1D ? 6908265 : 6908265;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getFoliageColorAtPos(BlockPos pos)
//    {
//        return 6908265;
//    }
}