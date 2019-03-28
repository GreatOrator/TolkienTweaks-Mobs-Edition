package com.greatorator.tolkienmobs.item.potiontypes;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.TTMPotion;
import com.greatorator.tolkienmobs.network.SToCMessage;
import com.greatorator.tolkienmobs.network.TTMPacketClient;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;

public class PotionElementalFlying extends TTMPotion {
    public static final String NAME = "elemental_flight";
    public static PotionElementalFlying instance = null;

    public static double launchSpeed = 1.0;

    public PotionElementalFlying() {
        super(NAME, true, 6498056, 4);
        instance = this;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {

        entity.motionY = (amplifier+1) * launchSpeed;

        if(entity instanceof EntityPlayerMP) {
            PacketBuffer out = new PacketBuffer(Unpooled.buffer());

            out.writeInt(TTMPacketClient.LIFT_UP);
            out.writeInt(amplifier+1);

            SToCMessage packet = new SToCMessage(out);
            TolkienMobs.networkWrapper.sendTo(packet, (EntityPlayerMP) entity);
        }
    }
}
