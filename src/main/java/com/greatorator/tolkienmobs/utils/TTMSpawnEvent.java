package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.TTMConfig_Old;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;


public class TTMSpawnEvent {

    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        int willSpawn = TTMSpawnEvent.spawnChance();

        if (willSpawn > 10 && TTMConfig_Old.disableVanilla) {
            if (event.getEntity() instanceof SpiderEntity ||
                    event.getEntity() instanceof CaveSpiderEntity ||
                    event.getEntity() instanceof WolfEntity ||
                    event.getEntity() instanceof LlamaEntity ||
                    event.getEntity() instanceof HorseEntity ||
                    event.getEntity() instanceof CowEntity ||
                    event.getEntity() instanceof RabbitEntity ||
                    event.getEntity() instanceof SheepEntity ||
                    event.getEntity() instanceof PigEntity ||
                    event.getEntity() instanceof ParrotEntity ||
                    event.getEntity() instanceof OcelotEntity) {
                event.setCanceled(true);
            }
        }
        if (TTMConfig_Old.disableVanilla) {
            if (event.getEntity() instanceof SkeletonEntity ||
                    event.getEntity() instanceof WitchEntity ||
                    event.getEntity() instanceof SlimeEntity ||
                    event.getEntity() instanceof ZombieEntity ||
                    event.getEntity() instanceof CreeperEntity ||
                    event.getEntity() instanceof StrayEntity ||
                    event.getEntity() instanceof HuskEntity ||
                    event.getEntity() instanceof EndermanEntity) {
                event.setCanceled(true);
            }
        }


//        if (event.getEntity() instanceof EntityTMMithrilGolem){
//            EntityTMMithrilGolem MithrilGolem = (EntityTMMithrilGolem) event.getEntity();
//
//            if (MithrilGolem.checkEntityCount()) {
//                event.setCanceled(true);
//            }
//        }
    }

    public static int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig_Old.mobSpawnChance, 1);
        return i;
    }
}