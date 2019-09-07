package com.greatorator.tolkienmobs.utils;

import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TTMSpawnEvent {
@SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    if(event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityWitch || event.getEntity() instanceof EntityBlaze || event.getEntity() instanceof EntitySlime || event.getEntity() instanceof EntityZombie || event.getEntity() instanceof EntitySpider || event.getEntity() instanceof EntityCreeper || event.getEntity() instanceof EntityEnderman) {
      event.setCanceled(true);
    }
  }
  
@SubscribeEvent
  public void onMithrilSpawn(EntityJoinWorldEvent event) {
	if (event.getEntity() instanceof EntityTMMithrilGolem){
		List entities = getEntityWorld().getEntitiesWithinAABB(EntityTMVillagers.class,getEntityBoundingBox().expand(32,32,32));
        List entitiesMithril = getEntityWorld().getEntitiesWithinAABB(EntityTMMithrilGolem.class,getEntityBoundingBox().expand(32,32,32));
		
		if (entities.size() < 10 || entitiesMithril.size() >= (1 + (entities.size() / 10))){
			event.setCanceled(true);
		}
	}
  }
  
@SubscribeEvent
  public void onKingSpawn(EntityJoinWorldEvent event) {
	if (event.getEntity() instanceof EntityTMGoblinKing){
		List entities = getEntityWorld().getEntitiesWithinAABB(EntityTMGoblin.class,getEntityBoundingBox().expand(32,32,32));
        List entitiesKing = getEntityWorld().getEntitiesWithinAABB(EntityTMGoblinKing.class,getEntityBoundingBox().expand(32,32,32));
		
		if (entities.size() < 16 || entitiesKing.size() >= 1)){
			event.setCanceled(true);
		}
	}
  }
}
