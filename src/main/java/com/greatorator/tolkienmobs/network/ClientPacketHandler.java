package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.handler.MilestoneHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class ClientPacketHandler implements ICustomPacketHandler.IClientPacketHandler {
    @Override
    public void handlePacket(PacketCustom packet, Minecraft mc, ClientPacketListener handler) {
        switch (packet.getType()) {
            case TolkienPacketHandler.C_SEND_MILESTONES: {
                handleMilestoneSync(packet);
                break;
            }
        }
    }

    private static void handleMilestoneSync(PacketCustom packet) {
        MilestoneHandler.deserialize(packet);
    }
}