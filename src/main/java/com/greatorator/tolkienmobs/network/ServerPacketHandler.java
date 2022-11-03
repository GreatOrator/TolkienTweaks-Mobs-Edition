package com.greatorator.tolkienmobs.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import com.greatorator.tolkienmobs.entity.tile.TolkienSignTile;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;

public class ServerPacketHandler implements ICustomPacketHandler.IServerPacketHandler {
    public static final Logger LOGGER = LogManager.getLogger(ServerPacketHandler.class);


    @Override
    public void handlePacket(PacketCustom packet, ServerPlayer sender, ServerGamePacketListenerImpl handler) {
        switch (packet.getType()) {
            case TolkienNetwork.S_UPDATE_SIGN:
                handleSignUpdate(packet, sender);
                break;
            case TolkienNetwork.S_UPDATE_KEY_CODE:
                handleKeyCodeUpdate(packet, sender);
                break;
            case TolkienNetwork.S_UPDATE_KEY_USES:
                handleKeyUsesUpdate(packet, sender);
                break;
        }
    }

    private void handleSignUpdate(PacketCustom packet, ServerPlayer sender) {
        BlockPos blockpos = packet.readPos();
        String line1 = packet.readString();
        String line2 = packet.readString();
        String line3 = packet.readString();
        String line4 = packet.readString();

        sender.resetLastActionTime();
        ServerLevel serverworld = sender.getLevel();

        if (serverworld.hasChunkAt(blockpos)) {
            BlockState blockstate = serverworld.getBlockState(blockpos);
            BlockEntity tileentity = serverworld.getBlockEntity(blockpos);
            if (!(tileentity instanceof TolkienSignTile)) {
                return;
            }

            TolkienSignTile signtileentity = (TolkienSignTile)tileentity;
            if (!signtileentity.isEditable()) {
                LOGGER.warn("Player {} just tried to change non-editable sign", (Object)sender.getName().getString());
                return;
            }

            signtileentity.setMessage(0, new TextComponent(line1));
            signtileentity.setMessage(1, new TextComponent(line2));
            signtileentity.setMessage(2, new TextComponent(line3));
            signtileentity.setMessage(3, new TextComponent(line4));

            signtileentity.setChanged();

            serverworld.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
        }
    }

    private void handleKeyCodeUpdate(PacketCustom packet, ServerPlayer sender) {
        String newCode = packet.readString();
        ItemStack keyStack = getItem(sender, item -> item instanceof KeyBaseItem);
        KeyBaseItem.setCode(keyStack, newCode);
    }

    private void handleKeyUsesUpdate(PacketCustom packet, ServerPlayer sender) {
        String newUses = packet.readString();
        ItemStack keyStack = getItem(sender, item -> item instanceof KeyBaseItem);
        try {
            KeyBaseItem.setUses(keyStack, Integer.parseInt(newUses));
        } catch (NumberFormatException e){
            LOGGER.error("Key uses has to be either -1 or a number and cannot be blank");
        }
    }

    public static ItemStack getItem(Player player, Predicate<Item> matcher) {
        if (!player.getMainHandItem().isEmpty() && matcher.test(player.getMainHandItem().getItem())) {
            return player.getMainHandItem();
        }
        else if (!player.getOffhandItem().isEmpty() && matcher.test(player.getOffhandItem().getItem())) {
            return player.getOffhandItem();
        }
        return ItemStack.EMPTY;
    }
}