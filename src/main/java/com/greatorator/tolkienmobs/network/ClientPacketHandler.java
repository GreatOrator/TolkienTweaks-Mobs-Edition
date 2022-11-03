package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.handler.MilestoneSaveData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;

public class ClientPacketHandler implements ICustomPacketHandler.IClientPacketHandler {
    @Override
    public void handlePacket(PacketCustom packet, Minecraft mc, ClientPacketListener handler) {
        switch (packet.getType()) {
            case TolkienNetwork.C_SEND_MILESTONES: {
                handleMilestoneSync(packet);
                break;
            }
        }
    }

    private static void handleMilestoneSync(PacketCustom packet) {
        MilestoneSaveData.deserialize(packet);
    }
}