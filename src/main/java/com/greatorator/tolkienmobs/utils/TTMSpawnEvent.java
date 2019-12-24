package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TTMSpawnEvent {
    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        int willSpawn = TTMSpawnEvent.spawnChance();

        if (willSpawn > 10 && TTMConfig.disableVanilla) {
            if (event.getEntity() instanceof EntitySpider ||
                    event.getEntity() instanceof EntityCaveSpider ||
                    event.getEntity() instanceof EntityWolf ||
                    event.getEntity() instanceof EntityLlama ||
                    event.getEntity() instanceof EntityHorse ||
                    event.getEntity() instanceof EntityCow ||
                    event.getEntity() instanceof EntityRabbit ||
                    event.getEntity() instanceof EntitySheep ||
                    event.getEntity() instanceof EntityPig ||
                    event.getEntity() instanceof EntityParrot ||
                    event.getEntity() instanceof EntityOcelot) {
                event.setCanceled(true);
            }
        }
        if (TTMConfig.disableVanilla) {
            if (event.getEntity() instanceof EntitySkeleton ||
                    event.getEntity() instanceof EntityWitch ||
                    event.getEntity() instanceof EntitySlime ||
                    event.getEntity() instanceof EntityZombie ||
                    event.getEntity() instanceof EntityCreeper ||
                    event.getEntity() instanceof EntityStray ||
                    event.getEntity() instanceof EntityHusk ||
                    event.getEntity() instanceof EntityEnderman) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onMithrilSpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityTMMithrilGolem){
            EntityTMMithrilGolem MithrilGolem = (EntityTMMithrilGolem) event.getEntity();

            if (MithrilGolem.checkEntityCount()) {
                event.setCanceled(true);
            }
        }
    }

    public static int spawnChance()
    {
        int i = TTMRand.getRandomInteger(TTMConfig.mobSpawnChance, 1);
        return i;
    }
}