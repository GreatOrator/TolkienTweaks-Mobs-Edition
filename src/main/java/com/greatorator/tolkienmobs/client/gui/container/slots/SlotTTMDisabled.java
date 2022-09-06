package com.greatorator.tolkienmobs.client.gui.container.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class SlotTTMDisabled extends Slot {
    public SlotTTMDisabled(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(PlayerEntity playerIn)
    {
        return false;
    }
}