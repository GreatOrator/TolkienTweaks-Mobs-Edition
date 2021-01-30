package com.greatorator.tolkienmobs.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class TTMServerEvents {

    public static void onLivingUpdate (LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof ServerPlayerEntity) {
//            if (PotionTTMDrowning.instance != null && event.getEntityLiving().isPotionActive(PotionTTMDrowning.instance)) {
//                if (event.getEntityLiving().isServerWorld() && event.getEntityLiving() instanceof ServerPlayerEntity) {
//                    if (event.getEntityLiving().ticksExisted % 20 == 0) {
//                        PacketBuffer out = new PacketBuffer(Unpooled.buffer());
//
//                        out.writeInt(TTMPacketClient.SET_DROWN);
//                        out.writeInt(event.getEntity().getPersistentData().getInt(PotionTTMDrowning.TAG_NAME));
//
////                        TTMStoCMessage packet = new TTMStoCMessage(out);
//                        //TODO Networking
////                    TolkienMobs.networkWrapper.sendTo(packet, (ServerPlayerEntity) event.getEntityLiving());
//                    }
//                }
//                event.getEntity().getPersistentData().putBoolean(PotionTTMDrowning.TAG_BOOLEAN, true);
//            } else {
//                if (event.getEntity().getPersistentData().getBoolean(PotionTTMDrowning.TAG_BOOLEAN)) {
//                    event.getEntity().getPersistentData().putInt(PotionTTMDrowning.TAG_NAME, 300);
//                }
//            }
        }


        LivingEntity entity = event.getEntityLiving();

        if(entity.world.isRemote || !entity.isAlive()) {
            return;
        }

        if(entity.isInWater() && entity.ticksExisted % 20 == 0 && entity instanceof ServerPlayerEntity) {
            Biome biome = entity.world.getBiome(entity.getPosition());

//            if(biome == BiomeInit.MIRKWOOD) {
                //TODO Effects
//                entity.addPotionEffect(new EffectInstance(PotionInit.SLEEPNESIA, 1200, 8));
//            }
        }
    }
}
