package com.greatorator.tolkienmobs.network;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.item.potiontypes.PotionElementalDrowning;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/** Borrowed from Tmtravlr - PotionCore*/
public class TTMPacketClient implements IMessageHandler<TTMMessage, IMessage> {
    public static final int SET_DROWN = 1;
    public static final int LIFT_UP = 2;
    public static final int TBD_1 = 3;
    public static final int TBD_2 = 4;

    public IMessage onMessage(TTMMessage packet, MessageContext context)
    {
        PacketBuffer buff = new PacketBuffer(Unpooled.wrappedBuffer(packet.getData()));

        EntityPlayer player = TolkienMobs.proxy.getPlayer();
        World world = null;
        if(player != null) {
            world = player.world;
        }

        int type = buff.readInt();

        switch(type) {
            case SET_DROWN: {
                int air = buff.readInt();

                if(player != null) {
                    player.getEntityData().setInteger(PotionElementalDrowning.TAG_NAME, air);
                }
                break;
            }
            case LIFT_UP: {
                int amount = buff.readInt();

                if(player != null) {
                    player.motionY = amount;
                }
                break;
            }
            case TBD_1: {
                break;
            }
            case TBD_2: {
            }
            default:
        }
        return null;
    }
}