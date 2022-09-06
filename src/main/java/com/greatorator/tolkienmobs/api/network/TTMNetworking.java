package com.greatorator.tolkienmobs.api.network;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class TTMNetworking {
    private static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(TolkienMobs.MODID, "network"),
                () -> "1.0",
                s -> true,
                s -> true);

        INSTANCE.messageBuilder(TTMTilePacket.class, nextID())
                .encoder(TTMTilePacket::encode)
                .decoder(TTMTilePacket::decode)
                .consumer(TTMTilePacket::consume)
                .add();
    }

    public static void sendToClient(TTMTilePacket packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(TTMTilePacket packet) {
        INSTANCE.sendToServer(packet);
    }
}