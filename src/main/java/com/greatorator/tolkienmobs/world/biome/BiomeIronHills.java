package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeIronHills {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBiomeIronHills(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        spawnInf.setPlayerCanSpawn();
        TTMDefaultBiomeFeatures.ironHillsSpawns(spawnInf);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRAVELLY_MOUNTAIN);
        TTMDefaultBiomeFeatures.addMithrilOres(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addRockPiles(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addMountainEdgeTrees(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultFlowers(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultGrass(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultExtraVegetation(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addExtraEmeralds(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addInfestedStone(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addExtraEmeralds(biomegenerationsettings$builder);

        LOGGER.info("Realm of the Dwarves of Durin's Folk...");
        return (new Biome.Builder())
                .precipitation(Biome.RainType.SNOW)
                .biomeCategory(Biome.Category.EXTREME_HILLS)
                .depth(3.0F)
                .scale(0.75F)
                .temperature(0.2F)
                .downfall(0.3F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(10518688)
                        .grassColorOverride(2292007)
                        .skyColor(getSkyColorWithTemperatureModifier(0.2F))
                        .foliageColorOverride(2292007)
                        .ambientLoopSound(SoundGenerator.allthatglittersinerebor.get())
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }
}
