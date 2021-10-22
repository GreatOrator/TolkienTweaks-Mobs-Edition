package com.greatorator.tolkienmobs.world.biome;

import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.world.gen.TTMFeatures;
import com.greatorator.tolkienmobs.world.gen.TTMFeature;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;

public class TTMDefaultBiomeFeatures {

    public static void addMallornTrees(BiomeGenerationSettings.Builder p_243689_0_) {
        p_243689_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.TREES_LORINAND);
    }

    public static void addMirkwoodTrees(BiomeGenerationSettings.Builder p_243763_0_) {
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.TREES_MIRKWOOD);
    }

    public static void addDesolation(BiomeGenerationSettings.Builder p_243763_0_) {
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.OAK_BADLANDS);
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.FOREST_ROCK);
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.GENERAL_ROCK);
    }

    public static void addDeadTrees(BiomeGenerationSettings.Builder p_243763_0_) {
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.TREES_DEADWOOD);
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.FOREST_ROCK);
        p_243763_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.GENERAL_ROCK);
    }

    public static void addAmmoliteOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMFeatures.ORE_AMMOLITE);
    }

    public static void addMithrilOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMFeatures.ORE_MITHRIL);
    }

    public static void addMorgulironOres(BiomeGenerationSettings.Builder p_243750_0_) {
        p_243750_0_.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, TTMFeatures.ORE_MORGULIRON);
    }

    public static void addLorinandFlowers(BiomeGenerationSettings.Builder p_243707_0_) {
        p_243707_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.FLOWER_LORINAND);
    }

    public static void addMirkwoodFlowers(BiomeGenerationSettings.Builder p_243707_0_) {
        p_243707_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.FLOWER_MIRKWOOD);
    }

    public static void addWaterLakes(BiomeGenerationSettings.Builder p_243742_0_) {
        p_243742_0_.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER);
    }

    public static void addLavaLakes(BiomeGenerationSettings.Builder p_243742_0_) {
        p_243742_0_.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_LAVA);
    }

    public static void addMallornLeafPiles(BiomeGenerationSettings.Builder p_243758_0_) {
        p_243758_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.PATCH_MALLORN_LEAFPILES_SPARSE);
    }

    public static void addMirkwoodLeafPiles(BiomeGenerationSettings.Builder p_243758_0_) {
        p_243758_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.PATCH_MIRKWOOD_LEAFPILES_SPARSE);
    }

    public static void addElvenHomes(BiomeGenerationSettings.Builder p_243758_0_) {
    }

    public static void addCulumaldaLeafPiles(BiomeGenerationSettings.Builder p_243758_0_) {
        p_243758_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.PATCH_CULUMALDA_LEAFPILES_SPARSE);
    }

    public static void addLebethronLeafPiles(BiomeGenerationSettings.Builder p_243758_0_) {
        p_243758_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.PATCH_LEBETHRON_LEAFPILES_SPARSE);
    }

    public static void addMarshVegetation(BiomeGenerationSettings.Builder p_243702_0_) {
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SWAMP_TREE);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_SWAMP);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TTMFeatures.FLOWER_MARSH);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_NORMAL);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_SWAMP);
        p_243702_0_.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_SWAMP);
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
