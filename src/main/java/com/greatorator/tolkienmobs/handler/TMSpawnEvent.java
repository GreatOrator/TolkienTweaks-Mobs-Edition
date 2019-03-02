package com.greatorator.tolkienmobs.handler;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class TMSpawnEvent {
@ForgeSubscribe
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    if(event.entity instanceof EntitySkeleton || event.entity instanceof EntityZombie || event.entity instanceof EntitySpider || event.entity instance of EntityCreeper || event.entity instance of EntityEnderman) {
      event.setCanceled(true);
    }
  }
}
