package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.entity.special.EntityTMMithrilGolem;
import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TTMSpawnEvent {
@SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    if(event.getEntity() instanceof EntitySkeleton ||
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
  @SubscribeEvent
  public void onMithrilSpawn(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof EntityTMMithrilGolem){
      EntityTMMithrilGolem MithrilGolem = (EntityTMMithrilGolem) event.getEntity();

      if (MithrilGolem.checkEntityCount()) {
        event.setCanceled(true);
      }
    }
  }

}
