package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.PotionInit;
import com.greatorator.tolkienmobs.item.potiontypes.PotionElementalDrowning;
import com.greatorator.tolkienmobs.network.TTMPacketClient;
import com.greatorator.tolkienmobs.network.TTMStoCMessage;
import io.netty.buffer.Unpooled;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TTMServerEvents {

    @SubscribeEvent
    public void onLivingUpdate (LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() == null || !(event.getEntityLiving() instanceof EntityPlayerMP)) {
            return;
        }
        if (PotionElementalDrowning.instance != null && event.getEntityLiving().isPotionActive(PotionElementalDrowning.instance)) {
            if(event.getEntityLiving().isServerWorld() && event.getEntityLiving() instanceof EntityPlayerMP) {
                if(event.getEntityLiving().ticksExisted % 20 == 0) {
                    PacketBuffer out = new PacketBuffer(Unpooled.buffer());

                    out.writeInt(TTMPacketClient.SET_DROWN);
                    out.writeInt(event.getEntity().getEntityData(). getInteger(PotionElementalDrowning.TAG_NAME));

                    TTMStoCMessage packet = new TTMStoCMessage(out);
                    TolkienMobs.networkWrapper.sendTo(packet, (EntityPlayerMP) event.getEntityLiving());
                }
            }
            event.getEntity().getEntityData().setBoolean(PotionElementalDrowning.TAG_BOOLEAN, true);
        }
        else {
            if(event.getEntity().getEntityData().getBoolean(PotionElementalDrowning.TAG_BOOLEAN)) {
                event.getEntity().getEntityData().setInteger(PotionElementalDrowning.TAG_NAME, 300);
            }
        }
    }

    @SubscribeEvent
    public void onEntityLiving(LivingEvent event){
        if(event.getEntityLiving() == null || event.getEntityLiving().world.isRemote || !event.getEntityLiving().isEntityAlive()) return;

        if(event.getEntityLiving().ticksExisted % 20 == 0 && event.getEntityLiving().isInsideOfMaterial(Material.WATER)) {
            Biome biome = event.getEntityLiving().world.getBiome(event.getEntityLiving().getPosition());

            if(biome == BiomeInit.MIRKWOOD && event.getEntityLiving() instanceof EntityPlayer) {
                event.getEntityLiving().addPotionEffect(new PotionEffect(PotionInit.ENT_STANCE, 1200, 1));
            }
        }
    }
}
