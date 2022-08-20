package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.entity.tile.TTMCulumaldaSignTile;
import com.greatorator.tolkienmobs.entity.tile.TTMLebethronSignTile;
import com.greatorator.tolkienmobs.entity.tile.TTMMallornSignTile;
import com.greatorator.tolkienmobs.entity.tile.TTMMirkwoodSignTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
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
            if (!(tileentity instanceof TTMMallornSignTile) || !(tileentity instanceof TTMMirkwoodSignTile) || !(tileentity instanceof TTMCulumaldaSignTile) || !(tileentity instanceof TTMLebethronSignTile)) {
                return;
            }

            TTMMallornSignTile signtileentity1 = (TTMMallornSignTile)tileentity;
            TTMMirkwoodSignTile signtileentity2 = (TTMMirkwoodSignTile)tileentity;
            TTMCulumaldaSignTile signtileentity3 = (TTMCulumaldaSignTile)tileentity;
            TTMLebethronSignTile signtileentity4 = (TTMLebethronSignTile)tileentity;
            if (!signtileentity1.isEditable() || signtileentity1.getPlayerWhoMayEdit() != sender || !signtileentity2.isEditable() || signtileentity2.getPlayerWhoMayEdit() != sender || !signtileentity3.isEditable() || signtileentity3.getPlayerWhoMayEdit() != sender || !signtileentity4.isEditable() || signtileentity4.getPlayerWhoMayEdit() != sender) {
                LOGGER.warn("Player {} just tried to change non-editable sign", (Object)sender.getName().getString());
                return;
            }

            signtileentity1.setMessage(0, new StringTextComponent(line1));
            signtileentity1.setMessage(1, new StringTextComponent(line2));
            signtileentity1.setMessage(2, new StringTextComponent(line3));
            signtileentity1.setMessage(3, new StringTextComponent(line4));

            signtileentity1.setChanged();

            signtileentity2.setMessage(0, new StringTextComponent(line1));
            signtileentity2.setMessage(1, new StringTextComponent(line2));
            signtileentity2.setMessage(2, new StringTextComponent(line3));
            signtileentity2.setMessage(3, new StringTextComponent(line4));

            signtileentity2.setChanged();

            signtileentity3.setMessage(0, new StringTextComponent(line1));
            signtileentity3.setMessage(1, new StringTextComponent(line2));
            signtileentity3.setMessage(2, new StringTextComponent(line3));
            signtileentity3.setMessage(3, new StringTextComponent(line4));

            signtileentity3.setChanged();

            signtileentity4.setMessage(0, new StringTextComponent(line1));
            signtileentity4.setMessage(1, new StringTextComponent(line2));
            signtileentity4.setMessage(2, new StringTextComponent(line3));
            signtileentity4.setMessage(3, new StringTextComponent(line4));

            signtileentity4.setChanged();
            serverworld.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
        }

    }
}