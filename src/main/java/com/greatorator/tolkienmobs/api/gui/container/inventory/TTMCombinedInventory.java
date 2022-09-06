package com.greatorator.tolkienmobs.api.gui.container.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class TTMCombinedInventory implements IInventory {
    public final TTMInventory inventory;
    public final TTMFluidInventory tankInventory;

    public TTMCombinedInventory(TTMInventory inventory, TTMFluidInventory tankInventory) {
        this.inventory = inventory;
        this.tankInventory = tankInventory;
    }

    @Override
    public int getContainerSize() {
        return inventory.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty() && tankInventory.isEmpty();
    }

    @Nonnull
    @Override
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    @Override
    public void setItem(int slot, @Nonnull ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int count) {
        return inventory.removeItem(slot, count);
    }

    @Nonnull
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setChanged() {
        inventory.setChanged();
        tankInventory.setChanged();
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return inventory.stillValid(player);
    }

    @Override
    public void clearContent() {
        inventory.clearContent();
        tankInventory.clearContent();
    }
}