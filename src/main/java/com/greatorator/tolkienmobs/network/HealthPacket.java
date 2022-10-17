package com.greatorator.tolkienmobs.network;

import com.greatorator.tolkienmobs.network.NetworkHelper.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class HealthPacket implements IPacket {
    @Override
    public void encode(Object msg, PacketBuffer packetBuffer) {

    }

    @Override
    public <MSG> MSG decode(PacketBuffer packetBuffer) {
        return null;
    }

    @Override
    public void handle(Object msg, Supplier<NetworkEvent.Context> contextSupplier) {

    }
}
