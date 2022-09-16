package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCore;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.item.tools.KeyItem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nullable;

public class BronzeKeyAccessContainer extends ContainerBCore<KeyItem> {


    public BronzeKeyAccessContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        super(TTMContent.BRONZE_KEY_CONTAINER, windowId, playerInv, extraData);
        onContainerOpen();
    }

    public BronzeKeyAccessContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        super(type, windowId, playerInv, extraData);
    }

    private void onContainerOpen() {
    }
}
