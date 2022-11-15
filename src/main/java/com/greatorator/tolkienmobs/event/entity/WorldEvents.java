package com.greatorator.tolkienmobs.event.entity;

import com.brandon3055.brandonscore.api.TimeKeeper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class WorldEvents {
    public static int serverTicks = 0;
    private static WeakHashMap<Mob, Long> ttSpawnedMobs = new WeakHashMap<>();
    public static final UUID PLAYER_BASE_HEALTH = UUID.fromString("615a41b8-6456-11ed-81ce-0242ac120002");

    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Biome biome = player.level.getBiome(player.blockPosition()).value();

        if(!(player instanceof ServerPlayer) || !player.isAlive()) return;

        if(player.isInWater() && TimeKeeper.getServerTick() % 20 == 0) {

//            if(biome.getRegistryName().equals(TolkienBiomes.BIOME_MIRKWOOD.getRegistryName())) {
//                player.addEffect(new MobEffectInstance(TolkienPotions.SLEEPNESIA.get(), 1200, 8));
//            }
        }
    }

    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!(event.getEntity() instanceof Player))
        {
            return;
        }

        Player player = (Player) event.getEntity();
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        AttributeModifier baseHealthModifier = new AttributeModifier(PLAYER_BASE_HEALTH, "Base", 20, AttributeModifier.Operation.ADDITION);

        // Add base modifier only if not added yet
        if (!maxHealth.hasModifier(baseHealthModifier))
        {
            maxHealth.addPermanentModifier(baseHealthModifier);
        }
        // Or if config updated and value changed
        else if (maxHealth.getModifier(PLAYER_BASE_HEALTH).getAmount() != baseHealthModifier.getAmount())
        {
            // Remove old instance and apply new one
            maxHealth.removeModifier(PLAYER_BASE_HEALTH);
            maxHealth.addPermanentModifier(baseHealthModifier);
        }

        // Fixing health being higher than max health
        if (player.getHealth() > player.getMaxHealth())
        {
            player.setHealth(player.getMaxHealth());
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

    public static void onMobSpawnedBySpawner(Mob entity) {
        ttSpawnedMobs.put(entity, System.currentTimeMillis());
    }

    private static Method GETCODEC_METHOD;
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
//        if(event.getWorld() instanceof ServerLevel){
//            ServerLevel serverWorld = (ServerLevel)event.getWorld();
//
//            try {
//                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
//                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
//                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
//            }
//            catch(Exception e){
//                LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
//            }
//
//            if(serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
//                    serverWorld.dimension().equals(Level.OVERWORLD)){
//                return;
//            }
//
//            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
//
//            tempMap.putIfAbsent(TolkienStructures.TTMHOUSE_ELVEN.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMHOUSE_ELVEN.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMHOUSE_HOBBIT.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMHOUSE_HOBBIT.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMHOUSE_HUMAN.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMHOUSE_HUMAN.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMHOUSE_DWARF.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMHOUSE_DWARF.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMHOUSE_DESERT.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMHOUSE_DESERT.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMBARROW.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMBARROW.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMRUIN_LARGE.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMRUIN_LARGE.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMRUIN_SMALL.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMRUIN_SMALL.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMSWAMP_HAG_HUT.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMSWAMP_HAG_HUT.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMSPIDER_TREE.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMSPIDER_TREE.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMWARG_PIT.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMWARG_PIT.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMMINOTAUR_MAZE.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMMINOTAUR_MAZE.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMGOLLUM_CAVE.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMGOLLUM_CAVE.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMDARK_TOWER.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMDARK_TOWER.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMINN_DESERT.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMINN_DESERT.get()));
//            tempMap.putIfAbsent(TolkienStructures.TTMSPIDER_CAVE.get(), DimensionStructuresSettings.DEFAULTS.get(TolkienStructures.TTMSPIDER_CAVE.get()));
//
//            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
//        }
    }

//    public static void biomeModification(final BiomeLoadingEvent event) {
//
//        if (event.getName().equals(TolkienBiomes.BIOME_LORINAND)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_ELVEN);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_BARROWDOWNS)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMBARROW);
//            event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, STONE_SPIKE_CONFIG);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_MARSHES)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSWAMP_HAG_HUT);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_SHIRE)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_HOBBIT);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_MIRKWOOD)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSPIDER_TREE);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_MORDOR)) {
//            int i = TTMRand.getRandomInteger(100, 1);
//
//            if(i<=25){
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMDARK_TOWER);
//            }else if(i<=50){
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMSPIDER_CAVE);
//
//            }else{
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMWARG_PIT);
//            }
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_IRONHILLS)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_DWARF);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_FANGORN)) {
//            event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, SMALL_LOG_CONFIG);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_GLADDEN)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_HUMAN);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_DAGORLAD)) {
//            event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RANDOM_RUBBLE_CONFIG);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_OLDFOREST)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMMINOTAUR_MAZE);
//            event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, BLEAK_LAND_CONFIG);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_HITHAEGLIR)) {
//            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMGOLLUM_CAVE);
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_HARADWAITH)) {
//            int i = TTMRand.getRandomInteger(100, 1);
//
//            if(i<=50){
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMHOUSE_DESERT);
//            }else {
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMINN_DESERT);
//            }
//        }
//        if (event.getName().equals(TolkienBiomes.BIOME_DAGORLAD)) {
//            int i = TTMRand.getRandomInteger(100, 1);
//
//            if(i<=25){
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMRUIN_LARGE);
//            }else {
//                event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMRUIN_SMALL);
//            }
//        }

        /* Used to test for structure generation */
//        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
////            event.getGeneration().getStructures().add(() -> TTMStructureConfig.CONFIGURED_TTMWARG_PIT);
//        }
//    }
}
