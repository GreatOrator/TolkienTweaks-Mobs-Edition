package com.greatorator.tolkienmobs.common.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Supplier;

public class NetworkHelper {
    private final SimpleChannel packetChannel;

    private final HashSet<Class<? extends IPacket>> registeredClasses;

    @SafeVarargs
    public NetworkHelper(String channelName, Class<? extends IPacket>... handledPacketClasses) {

        packetChannel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(channelName)).
                clientAcceptedVersions(a -> true).
                serverAcceptedVersions(a -> true).
                networkProtocolVersion(() -> "1.0.0")
                .simpleChannel();
        registeredClasses = new HashSet<>(handledPacketClasses.length);
        registeredClasses.addAll(Arrays.asList(handledPacketClasses));

        int runningIndex = 0;
        for (Class<? extends IPacket> packetClass : handledPacketClasses) {
            try {
                IPacket instance = packetClass.newInstance();
                packetChannel.registerMessage(runningIndex++, packetClass, instance::encode, instance::decode, instance::handle);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendPacketToServer(IPacket packet) {
        checkClass(packet.getClass());
        packetChannel.sendToServer(packet);
    }

    public void sendPacketToPlayer(IPacket packet, ServerPlayerEntity player) {
        checkClass(packet.getClass());
        packetChannel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public void sendPacketToAllPlayers(IPacket packet) {
        checkClass(packet.getClass());
        packetChannel.send(PacketDistributor.ALL.noArg(), packet);
    }

    public void sendPacketToAllAroundPoint(IPacket packet, PacketDistributor.TargetPoint tp) {
        checkClass(packet.getClass());
        packetChannel.send(PacketDistributor.NEAR.with(() -> tp), packet);
    }

    public void sendPacketToAllInDimension(IPacket packet, RegistryKey<World> dimension) {
        checkClass(packet.getClass());
        packetChannel.send(PacketDistributor.DIMENSION.with(() -> dimension), packet);
    }

    private void checkClass(Class<? extends IPacket> clazz) {
        if (!registeredClasses.contains(clazz)) {
            throw new RuntimeException("NetworkHelper got unknown Packet type " + clazz + " to send, critical error");
        }
    }

    /**
     * Packets only need to implement this and offer a constructor with no args,
     * unless you don't have constructors with >0 args. The class MUST also be
     * statically accessible, else you will suffer an InstantiationException!
     * Note Packets don't distinguish between being sent from client to server or
     * the other way around, so be careful using them bidirectional or avoid
     * doing that altogether.
     */
    public interface IPacket {

        void encode(Object msg, PacketBuffer packetBuffer);

        <MSG> MSG decode(PacketBuffer packetBuffer);

        void handle(Object msg, Supplier<NetworkEvent.Context> contextSupplier);
    }
}
