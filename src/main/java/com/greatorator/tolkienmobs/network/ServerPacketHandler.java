package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.entity.tile.TTMSignBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerPacketHandler implements ICustomPacketHandler.IServerPacketHandler {
    public static final Logger LOGGER = LogManager.getLogger(ServerPacketHandler.class);


    @Override
    public void handlePacket(PacketCustom packet, ServerPlayerEntity sender, IServerPlayNetHandler handler) {
        switch (packet.getType()) {
            case TTMNetwork.S_UPDATE_SIGN:
                handleSignUpdate(packet, sender, handler);
                break;
        }
    }

    private void handleSignUpdate(PacketCustom packet, ServerPlayerEntity sender, IServerPlayNetHandler handler) {
        BlockPos blockpos = packet.readPos();
        String line1 = packet.readString();
        String line2 = packet.readString();
        String line3 = packet.readString();
        String line4 = packet.readString();

        sender.resetLastActionTime();
        ServerWorld serverworld = sender.getLevel();

        if (serverworld.hasChunkAt(blockpos)) {
            BlockState blockstate = serverworld.getBlockState(blockpos);
            TileEntity tileentity = serverworld.getBlockEntity(blockpos);
            if (!(tileentity instanceof TTMSignBlockEntity)) {
                return;
            }

            TTMSignBlockEntity signtileentity = (TTMSignBlockEntity)tileentity;
            if (!signtileentity.isEditable() || signtileentity.getPlayerWhoMayEdit() != sender) {
                LOGGER.warn("Player {} just tried to change non-editable sign", (Object)sender.getName().getString());
                return;
            }

            signtileentity.setMessage(0, new StringTextComponent(line1));
            signtileentity.setMessage(1, new StringTextComponent(line2));
            signtileentity.setMessage(2, new StringTextComponent(line3));
            signtileentity.setMessage(3, new StringTextComponent(line4));

            signtileentity.setChanged();
            serverworld.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
        }

    }
}