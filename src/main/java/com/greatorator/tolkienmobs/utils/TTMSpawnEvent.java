package com.greatorator.tolkienmobs.utils;


public class TTMSpawnEvent {
//    @SubscribeEvent
//    public static void onEntitySpawn(EntityJoinWorldEvent event) {
//        int willSpawn = TTMSpawnEvent.spawnChance();
//
//        if (willSpawn >= TTMConfig.spawnChance && TTMConfig.disableVanilla) {
//            if (event.getEntity() instanceof WolfEntity ||
//                    event.getEntity() instanceof LlamaEntity ||
//                    event.getEntity() instanceof HorseEntity ||
//                    event.getEntity() instanceof CowEntity ||
//                    event.getEntity() instanceof RabbitEntity ||
//                    event.getEntity() instanceof SheepEntity ||
//                    event.getEntity() instanceof PigEntity ||
//                    event.getEntity() instanceof ParrotEntity ||
//                    event.getEntity() instanceof OcelotEntity ||
//                    event.getEntity() instanceof SlimeEntity) {
//                event.setCanceled(true);
//            }
//        }
//        if (TTMConfig.disableVanilla) {
//            if (event.getEntity() instanceof SpiderEntity ||
//                    event.getEntity() instanceof CaveSpiderEntity ||
//                    event.getEntity() instanceof SkeletonEntity ||
//                    event.getEntity() instanceof WitchEntity ||
//                    event.getEntity() instanceof SlimeEntity ||
//                    event.getEntity() instanceof ZombieEntity ||
//                    event.getEntity() instanceof CreeperEntity ||
//                    event.getEntity() instanceof StrayEntity ||
//                    event.getEntity() instanceof HuskEntity ||
//                    event.getEntity() instanceof EndermanEntity) {
//                event.setCanceled(true);
//            }
//        }
//
//
//        if (event.getEntity() instanceof EntityTMMithrilGolem){
//            EntityTMMithrilGolem MithrilGolem = (EntityTMMithrilGolem) event.getEntity();
//
//            if (MithrilGolem.checkEntityCount()) {
//                event.setCanceled(true);
//            }
//        }
//    }
//
//    public static int spawnChance()
//    {
//        return TTMRand.getRandomInteger(100, TTMConfig.spawnChance);
//    }
}