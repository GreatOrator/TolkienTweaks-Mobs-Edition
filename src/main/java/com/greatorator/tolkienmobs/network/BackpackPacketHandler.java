package com.greatorator.tolkienmobs.network;

import com.greatorator.tolkienmobs.entity.tile.inventory.ITTMBackpack;
import com.greatorator.tolkienmobs.handler.TTMCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BackpackPacketHandler {
    private final CompoundNBT compound;
    private final int entityID;

    public BackpackPacketHandler(CompoundNBT compound, int entityID)
    {
        this.compound = compound;
        this.entityID = entityID;
    }

    public static BackpackPacketHandler decode(final PacketBuffer buffer)
    {
        final CompoundNBT compound = buffer.readNbt();
        final int entityID = buffer.readInt();

        return new BackpackPacketHandler(compound, entityID);
    }

    public static void encode(final BackpackPacketHandler message, final PacketBuffer buffer)
    {
        buffer.writeNbt(message.compound);
        buffer.writeInt(message.entityID);
    }

    public static void handle(final BackpackPacketHandler message, final Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

            final PlayerEntity playerEntity = (PlayerEntity) Minecraft.getInstance().player.level.getEntity(message.entityID);
            ITTMBackpack cap = TTMCapabilities.getCapability(playerEntity).orElseThrow(() -> new RuntimeException("No player capability found!"));
            if(cap != null) {
                cap.setWearable(ItemStack.of(message.compound));
                cap.setContents(ItemStack.of(message.compound));
            }
        }));

        ctx.get().setPacketHandled(true);
    }
}
