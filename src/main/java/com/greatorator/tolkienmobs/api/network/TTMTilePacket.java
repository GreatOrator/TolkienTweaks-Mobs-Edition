package com.greatorator.tolkienmobs.api.network;

import com.greatorator.tolkienmobs.api.gui.container.TTMContainer;
import com.greatorator.tolkienmobs.api.gui.container.inventory.TTMFluidInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TTMTilePacket {
    public List<FluidStack> fluids = new ArrayList<>();
    public int energy;
    public int progress;
    public int totalProgress;
    protected BlockPos pos;

    public TTMTilePacket() {}

    public TTMTilePacket(BlockPos pos, TTMFluidInventory tankInventory, int progress, int totalProgress) {
        this.pos = pos;
        for (int i = 0; i < tankInventory.getTanks(); i++) {
            fluids.add(tankInventory.get(i));
        }

        this.progress = progress;
        this.totalProgress = totalProgress;
    }

    public static void encode(TTMTilePacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeInt(packet.fluids.size());
        for (FluidStack fluid : packet.fluids) {
            buffer.writeFluidStack(fluid);
        }

        buffer.writeInt(packet.energy);
        buffer.writeInt(packet.progress);
        buffer.writeInt(packet.totalProgress);
    }

    public static TTMTilePacket decode(PacketBuffer buffer) {
        TTMTilePacket packet = new TTMTilePacket();

        packet.pos = buffer.readBlockPos();
        int fluidTanks = buffer.readInt();
        for (int i = 0; i < fluidTanks; i++) {
            packet.fluids.add(buffer.readFluidStack());
        }

        packet.energy = buffer.readInt();
        packet.progress = buffer.readInt();
        packet.totalProgress = buffer.readInt();

        return packet;
    }

    public static void consume(TTMTilePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            Container container = player.containerMenu;

            if (container instanceof TTMContainer<?>) {
                if (((TTMContainer<?>) container).tile != null) {
                    ((TTMContainer<?>) container).tile.handleGuiPacket(packet);
                }
            }

            ctx.get().setPacketHandled(true);
        });
    }
}