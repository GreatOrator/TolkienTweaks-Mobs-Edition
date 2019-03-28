package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.item.potiontypes.PotionElementalDrowning;
import com.greatorator.tolkienmobs.network.SToCMessage;
import com.greatorator.tolkienmobs.network.TTMPacketClient;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventInit {

    @SubscribeEvent
    public void onLivingUpdate (LivingEvent.LivingUpdateEvent event) {
        if (PotionElementalDrowning.instance != null && event.getEntityLiving().isPotionActive(PotionElementalDrowning.instance)) {
            if(event.getEntityLiving().isServerWorld() && event.getEntityLiving() instanceof EntityPlayerMP) {
                if(event.getEntityLiving().ticksExisted % 20 == 0) {
                    PacketBuffer out = new PacketBuffer(Unpooled.buffer());

                    out.writeInt(TTMPacketClient.SET_DROWN);
                    out.writeInt(event.getEntity().getEntityData(). getInteger(PotionElementalDrowning.TAG_NAME));

                    SToCMessage packet = new SToCMessage(out);
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
}
