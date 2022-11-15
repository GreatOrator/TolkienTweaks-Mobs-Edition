package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustomChannelBuilder;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.event.EventNetworkChannel;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 20/08/22.
 */
public class TolkienPacketHandler {
    public static final ResourceLocation CHANNEL = new ResourceLocation(TolkienMobs.MODID + ":network");
    public static EventNetworkChannel netChannel;

    //Server to client
    public static final int C_SEND_MILESTONES =         1;
    public static final int S_UPDATE_PLAYER =           2;

    //Client to server
    public static final int S_UPDATE_SIGN =             1;
    public static final int S_UPDATE_KEY_CODE =         2;
    public static final int S_UPDATE_KEY_USES =         3;

    public static void sendToAttribute(Object msg, ServerPlayer player) {
        PacketCustom packet = new PacketCustom(CHANNEL, S_UPDATE_PLAYER);
        if(!(player instanceof FakePlayer)) {
            packet.sendToPlayer(player);
        }
    }

    public static void sendMilestonesToClients(MilestoneHandler data, @Nullable ServerPlayer player) {
        PacketCustom packet = new PacketCustom(CHANNEL, C_SEND_MILESTONES);
        data.serialize(packet);
        if (player != null) {
            packet.sendToPlayer(player);
        } else {
            packet.sendToClients();
        }
    }

    public static void sendSignUpdate(BlockPos blockPos, String line1, String line2, String line3, String line4) {
        PacketCustom packet = new PacketCustom(CHANNEL, S_UPDATE_SIGN);
        packet.writePos(blockPos);
        packet.writeString(line1);
        packet.writeString(line2);
        packet.writeString(line3);
        packet.writeString(line4);
        packet.sendToServer();

    }

    public static void sendKeyCodeUpdate(String key) {
        PacketCustom packet = new PacketCustom(CHANNEL, S_UPDATE_KEY_CODE);
        packet.writeString(key);
        packet.sendToServer();
    }

    public static void sendKeyUsesUpdate(String uses) {
        PacketCustom packet = new PacketCustom(CHANNEL, S_UPDATE_KEY_USES);
        packet.writeString(uses);
        packet.sendToServer();
    }

    public static void init() {
        netChannel = PacketCustomChannelBuilder.named(CHANNEL)
                .networkProtocolVersion(() -> "1")
                .clientAcceptedVersions(e -> true)
                .serverAcceptedVersions(e -> true)
                .assignClientHandler(() -> ClientPacketHandler::new)
                .assignServerHandler(() -> ServerPacketHandler::new)
                .build();
    }
}
