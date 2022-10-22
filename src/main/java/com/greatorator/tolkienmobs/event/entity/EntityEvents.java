package com.greatorator.tolkienmobs.event.entity;

import com.brandon3055.brandonscore.api.TimeKeeper;
import com.greatorator.tolkienmobs.datagen.BiomeGenerator;
import com.greatorator.tolkienmobs.datagen.PotionGenerator;
import com.greatorator.tolkienmobs.datagen.StructureGenerator;
import com.greatorator.tolkienmobs.utils.TTMRand;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMStructureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.*;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class EntityEvents {
    public static int serverTicks = 0;
    private static WeakHashMap<MobEntity, Long> ttSpawnedMobs = new WeakHashMap<>();

    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;
        Biome biome = player.level.getBiome(player.blockPosition());

        if(!(player instanceof ServerPlayerEntity) || !player.isAlive()) return;

        if(player.isInWater() && TimeKeeper.getServerTick() % 20 == 0) {

            if(biome.getRegistryName().equals(BiomeGenerator.BIOME_MIRKWOOD.getRegistryName())) {
                player.addEffect(new EffectInstance(PotionGenerator.SLEEPNESIA.get(), 1200, 8));
            }
        }
    }

    @SubscribeEvent
    public void serverTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            serverTicks++;

            if (!ttSpawnedMobs.isEmpty()) {
                List<LivingEntity> toRemove = new ArrayList<>();
                long time = System.currentTimeMillis();

                ttSpawnedMobs.forEach((entity, aLong) -> {
                    if (time - aLong > 30000) {
                        entity.persistenceRequired = false;
                        toRemove.add(entity);
                    }
                });

                toRemove.forEach(entity -> ttSpawnedMobs.remove(entity));
            }
        }
    }

    public static void onMobSpawnedBySpawner(MobEntity entity) {
        ttSpawnedMobs.put(entity, System.currentTimeMillis());
    }

    private static Method GETCODEC_METHOD;
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)){
                return;
            }

            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());

            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_ELVEN.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_ELVEN.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_HOBBIT.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_HOBBIT.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_HUMAN.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_HUMAN.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_DWARF.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_DWARF.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMHOUSE_DESERT.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMHOUSE_DESERT.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMBARROW.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMBARROW.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMRUIN_LARGE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMRUIN_LARGE.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMRUIN_SMALL.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMRUIN_SMALL.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMSWAMP_HAG_HUT.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMSWAMP_HAG_HUT.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMSPIDER_TREE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMSPIDER_TREE.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMWARG_PIT.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMWARG_PIT.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMMINOTAUR_MAZE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMMINOTAUR_MAZE.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMGOLLUM_CAVE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMGOLLUM_CAVE.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMDARK_TOWER.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMDARK_TOWER.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMINN_DESERT.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMINN_DESERT.get()));
            tempMap.putIfAbsent(StructureGenerator.TTMSPIDER_CAVE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureGenerator.TTMSPIDER_CAVE.get()));

            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }

    public static void biomeModification(final BiomeLoadingEvent event) {

        if (event.getName().equals(BiomeGenerator.BIOME_LORINAND)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_ELVEN);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_BARROWDOWNS)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMBARROW);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_MARSHES)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSWAMP_HAG_HUT);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_SHIRE)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_HOBBIT);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_MIRKWOOD)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSPIDER_TREE);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_MORDOR)) {
            int i = TTMRand.getRandomInteger(100, 1);

            if(i<=25){
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMDARK_TOWER);
            }else if(i<=50){
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSPIDER_CAVE);

            }else{
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMWARG_PIT);
            }
        }
        if (event.getName().equals(BiomeGenerator.BIOME_IRONHILLS)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_DWARF);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_GLADDEN)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_HUMAN);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_OLDFOREST)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMMINOTAUR_MAZE);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_HITHAEGLIR)) {
            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMGOLLUM_CAVE);
        }
        if (event.getName().equals(BiomeGenerator.BIOME_HARADWAITH)) {
            int i = TTMRand.getRandomInteger(100, 1);

            if(i<=50){
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_DESERT);
            }else {
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMINN_DESERT);
            }
        }
        if (event.getName().equals(BiomeGenerator.BIOME_DAGORLAD)) {
            int i = TTMRand.getRandomInteger(100, 1);

            if(i<=25){
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMRUIN_LARGE);
            }else {
                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMRUIN_SMALL);
            }
        }

        /* Used to test for structure generation */
        if (event.getCategory() == Biome.Category.PLAINS) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMWARG_PIT);
        }
    }
}
