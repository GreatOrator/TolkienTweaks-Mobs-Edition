package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TMSpawnEvent {
@SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    if(event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityWitch || event.getEntity() instanceof EntitySlime || event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySpider || event.getEntity() instanceof EntityCreeper || event.getEntity() instanceof EntityEnderman) {
      event.setCanceled(true);
    }
  }
}
