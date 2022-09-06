package com.greatorator.tolkienmobs.api.handler;

import com.greatorator.tolkienmobs.api.gui.container.slots.TTMItemSlot;
import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TTMItemHandler implements IItemHandlerModifiable {
    @Nullable
    protected ITTMInventoryCallback tile;
    protected List<TTMItemSlot> slots;

    public TTMItemHandler() {
        this(null);
    }

    public TTMItemHandler(@Nullable ITTMInventoryCallback tile) {
        this.tile = tile;
        this.slots = new ArrayList<>();
    }

    public TTMItemHandler(@Nullable ITTMInventoryCallback tile, @Nonnull List<TTMItemSlot> slots) {
        this.tile = tile;
        this.slots = new ArrayList<>(slots);
    }

    public boolean hasSlots() {
        return slots.size() > 0;
    }

    public boolean isEmpty() {
        for (TTMItemSlot slot : slots) {
            if (!slot.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void onInventoryChange(int slot) {
        if (tile == null) {
            return;
        }
        tile.onInventoryChange(slot);
    }

    @Override
    public int getSlots() {
        return slots.size();
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= getSlots()) {
            return ItemStack.EMPTY;
        }
        return slots.get(slot).getItemStack();
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= getSlots()) {
            return;
        }
        slots.get(slot).setItemStack(stack);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot < 0 || slot >= getSlots()) {
            return stack;
        }

        ItemStack ret = slots.get(slot).insertItem(slot, stack, simulate);
        if (!simulate) {
            onInventoryChange(slot);
        }
        return ret;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot < 0 || slot >= getSlots()) {
            return ItemStack.EMPTY;
        }
        ItemStack ret = slots.get(slot).extractItem(slot, amount, simulate);
        if (!simulate) {
            onInventoryChange(slot);
        }
        return ret;
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot < 0 || slot >= getSlots()) {
            return 0;
        }
        return slots.get(slot).getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= getSlots()) {
            return false;
        }
        return slots.get(slot).isItemValid(stack);
    }
}