package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeMarshes {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBiomeMarshes(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        spawnInf.setPlayerCanSpawn();
        TTMDefaultBiomeFeatures.SwampSpawns(spawnInf);
        spawnInf.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 1, 1, 1));

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.SWAMP);
        DefaultBiomeFeatures.addFossilDecoration(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSwampClayDisk(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSwampExtraVegetation(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

        TTMDefaultBiomeFeatures.addMarshVegetation(biomegenerationsettings$builder);
        LOGGER.info("Where the Entwash meets the Anduin...");
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.SWAMP)
                .depth(-0.2F)
                .scale(0.1F)
                .temperature(0.8F)
                .downfall(0.9F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(14745540)
                        .waterFogColor(2302743)
                        .fogColor(5988193)
                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
                        .foliageColorOverride(6316071)
                        .grassColorModifier(BiomeAmbience.GrassColorModifier.SWAMP)
                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }
}