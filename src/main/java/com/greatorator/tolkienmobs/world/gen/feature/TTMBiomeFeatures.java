package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.world.gen.TTMGenFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class TTMBiomeFeatures {

    public static void addMallornTrees(BiomeGenerationSettings.Builder p_243689_0_) {
        p_243689_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMGenFeatures.TREES_LORINAND);
    }

    public static void addMirkwoodTrees(BiomeGenerationSettings.Builder p_243763_0_) {
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMGenFeatures.TREES_MIRKWOOD);
    }

    public static void addAmmoliteOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMGenFeatures.ORE_AMMOLITE);
    }

    public static void addMithrilOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMGenFeatures.ORE_MITHRIL);
    }

    public static void addMorgulironOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMGenFeatures.ORE_MORGULIRON);
    }

    public static void addLorinandFlowers(BiomeGenerationSettings.Builder p_243707_0_) {
        p_243707_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMGenFeatures.FLOWER_LORINAND);
    }

    public static void addMirkwoodFlowers(BiomeGenerationSettings.Builder p_243707_0_) {
        p_243707_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMGenFeatures.FLOWER_MIRKWOOD);
    }

    public static void addWaterLakes(BiomeGenerationSettings.Builder p_243742_0_) {
        p_243742_0_.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER);
    }

    public static void ttmAmbientSpawns(MobSpawnInfo.Builder p_243734_0_) {
        p_243734_0_.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_SQUIRREL.get(), 2, 1, 1));
        p_243734_0_.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_FROG.get(), 2, 1, 1));
        p_243734_0_.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_THRUSH.get(), 2, 1, 1));
    }

    public static void ttmSwampSpawns(MobSpawnInfo.Builder p_243734_0_) {
        p_243734_0_.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_SWARM.get(), 2, 1, 1));
    }

    public static void elvishSpawns(MobSpawnInfo.Builder p_243737_0_) {
        ttmAmbientSpawns(p_243737_0_);
        p_243737_0_.addSpawn(EntityClassification.MISC, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_ELVES.get(), 80, 4, 4));
    }

    public static void mirkwoodSpawns(MobSpawnInfo.Builder p_243737_0_) {
        ttmSwampSpawns(p_243737_0_);
        p_243737_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_GOBLIN.get(), 80, 4, 4));
    }

    public static void passiveAnimals(MobSpawnInfo.Builder p_243714_0_) {
        p_243714_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        p_243714_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        p_243714_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        p_243714_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        p_243714_0_.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityGenerator.ENTITY_TTM_AUROCH.get(), 8, 4, 4));
    }
}
