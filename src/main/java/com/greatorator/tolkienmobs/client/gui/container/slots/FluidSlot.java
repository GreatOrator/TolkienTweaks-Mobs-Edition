package com.greatorator.tolkienmobs.client.gui.container.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class FluidSlot extends Slot {

    public FluidSlot(PlayerEntity player, CraftingInventory input, IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
}
