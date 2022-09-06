package com.greatorator.tolkienmobs.api.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.item.ItemStack.EMPTY;

public class TTMItemMultiHandler implements IItemHandlerModifiable {
    List<TTMGroupedItemHandler> handlers = new ArrayList<>();
    List<Integer> startIndexes = new ArrayList<>();

    public TTMItemMultiHandler(TTMGroupedItemHandler... handlers) {
        int index = 0;
        for (TTMGroupedItemHandler handler : handlers) {
            this.handlers.add(handler);
            startIndexes.add(index);
            index += handler.getSlots();
        }
        startIndexes.add(index);
    }

    @Override
    public int getSlots() {
        return startIndexes.get(startIndexes.size() - 1);
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= getSlots()) {
            return EMPTY;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        return handler.getB().getStackInSlot(slot - startIndexes.get(handler.getA()));
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= getSlots()) {
            return;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        handler.getB().setStackInSlot(slot - startIndexes.get(handler.getA()), stack);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot < 0 || slot >= getSlots()) {
            return stack;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        return handler.getB().insertItem(slot - startIndexes.get(handler.getA()), stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot < 0 || slot >= getSlots()) {
            return EMPTY;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        return handler.getB().extractItem(slot - startIndexes.get(handler.getA()), amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot < 0 || slot > getSlots()) {
            return 0;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        return handler.getB().getSlotLimit(slot - startIndexes.get(handler.getA()));
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot > getSlots()) {
            return false;
        }

        Tuple<Integer, TTMGroupedItemHandler> handler = getHandler(slot);
        return handler.getB().isItemValid(slot - startIndexes.get(handler.getA()), stack);
    }

    private Tuple<Integer, TTMGroupedItemHandler> getHandler(int slot) {
        int index = -1;
        for (int i = 0; i < startIndexes.size() - 1; i++) {
            if (startIndexes.get(i + 1) > slot) {
                index = i;
                break;
            }
        }

        return new Tuple<>(index, handlers.get(index));
    }
}
