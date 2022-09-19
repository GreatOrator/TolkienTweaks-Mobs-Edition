package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.entity.tile.*;
import com.greatorator.tolkienmobs.item.tools.BronzeKeyItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class ServerPacketHandler implements ICustomPacketHandler.IServerPacketHandler {
    public static final Logger LOGGER = LogManager.getLogger(ServerPacketHandler.class);


    @Override
    public void handlePacket(PacketCustom packet, ServerPlayerEntity sender, IServerPlayNetHandler handler) {
        switch (packet.getType()) {
            case TolkienNetwork.S_UPDATE_SIGN:
                handleSignUpdate(packet, sender, handler);
                break;
            case TolkienNetwork.S_UPDATE_KEY_CODE:
                handleKeyCodeUpdate(packet, sender, handler);
                break;
            case TolkienNetwork.S_UPDATE_MILESTONE_NAME:
                handleMilestoneUpdate(packet, sender, handler);
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
            if (!(tileentity instanceof MallornSignTile) || !(tileentity instanceof MirkwoodSignTile) || !(tileentity instanceof CulumaldaSignTile) || !(tileentity instanceof LebethronSignTile)) {
                return;
            }

            MallornSignTile signtileentity1 = (MallornSignTile)tileentity;
            MirkwoodSignTile signtileentity2 = (MirkwoodSignTile)tileentity;
            CulumaldaSignTile signtileentity3 = (CulumaldaSignTile)tileentity;
            LebethronSignTile signtileentity4 = (LebethronSignTile)tileentity;
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

    private void handleKeyCodeUpdate(PacketCustom packet, ServerPlayerEntity sender, IServerPlayNetHandler handler) {
        String newCode = packet.readString();
        ItemStack keyStack = getItem(sender, item -> item instanceof BronzeKeyItem);
        ((BronzeKeyItem) keyStack.getItem()).setKey(keyStack, newCode);
    }

    private void handleMilestoneUpdate(PacketCustom packet, ServerPlayerEntity sender, IServerPlayNetHandler handler) {
        BlockPos blockpos = packet.readPos();
        String newName = packet.readString();
        sender.resetLastActionTime();
        ServerWorld serverworld = sender.getLevel();
        if (serverworld.hasChunkAt(blockpos)) {
            BlockState blockstate = serverworld.getBlockState(blockpos);
            TileEntity tileentity = serverworld.getBlockEntity(blockpos);
            MilestoneTile milestoneTile = (MilestoneTile)tileentity;

            milestoneTile.setMilestoneName(newName);

            milestoneTile.setChanged();
            serverworld.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
        }
    }

    public static ItemStack getItem(PlayerEntity player, Predicate<Item> matcher) {
        if (!player.getMainHandItem().isEmpty() && matcher.test(player.getMainHandItem().getItem())) {
            return player.getMainHandItem();
        }
        else if (!player.getOffhandItem().isEmpty() && matcher.test(player.getOffhandItem().getItem())) {
            return player.getOffhandItem();
        }
        return ItemStack.EMPTY;
    }

}