package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.item.potiontypes.PotionTTMDrowning;
import com.greatorator.tolkienmobs.network.TTMPacketClient;
import com.greatorator.tolkienmobs.network.TTMStoCMessage;
import io.netty.buffer.Unpooled;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TTMServerEvents {

    @SubscribeEvent
    public void onLivingUpdate (LivingUpdateEvent event) {
        if (event.getEntityLiving() == null || !(event.getEntityLiving() instanceof EntityPlayerMP)) {
            return;
        }
        if (PotionTTMDrowning.instance != null && event.getEntityLiving().isPotionActive(PotionTTMDrowning.instance)) {
            if(event.getEntityLiving().isServerWorld() && event.getEntityLiving() instanceof EntityPlayerMP) {
                if(event.getEntityLiving().ticksExisted % 20 == 0) {
                    PacketBuffer out = new PacketBuffer(Unpooled.buffer());

                    out.writeInt(TTMPacketClient.SET_DROWN);
                    out.writeInt(event.getEntity().getEntityData(). getInteger(PotionTTMDrowning.TAG_NAME));

                    TTMStoCMessage packet = new TTMStoCMessage(out);
                    TolkienMobs.networkWrapper.sendTo(packet, (EntityPlayerMP) event.getEntityLiving());
                }
            }
            event.getEntity().getEntityData().setBoolean(PotionTTMDrowning.TAG_BOOLEAN, true);
        }
        else {
            if(event.getEntity().getEntityData().getBoolean(PotionTTMDrowning.TAG_BOOLEAN)) {
                event.getEntity().getEntityData().setInteger(PotionTTMDrowning.TAG_NAME, 300);
            }
        }
    }

    @SubscribeEvent
    public void onEntityLiving(LivingUpdateEvent event){
        EntityLivingBase player = event.getEntityLiving();

        if(player.world.isRemote || !player.isEntityAlive()) return;

        if(player.isInsideOfMaterial(Material.WATER) && player.ticksExisted % 20 == 0 && player instanceof EntityPlayerMP) {
            Biome biome = player.world.getBiome(player.getPosition());

            if(biome == BiomeInit.MIRKWOOD) {

                player.addPotionEffect(new PotionEffect(PotionInit.SLEEPNESIA, 1200, 8));
            }
        }
    }
}
