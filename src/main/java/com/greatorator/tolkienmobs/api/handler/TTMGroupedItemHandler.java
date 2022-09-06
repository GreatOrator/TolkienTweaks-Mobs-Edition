package com.greatorator.tolkienmobs.api.handler;

import com.greatorator.tolkienmobs.api.gui.container.slots.TTMItemSlot;
import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import com.greatorator.tolkienmobs.api.handler.intface.TTMSlotGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.item.ItemStack.EMPTY;

public class TTMGroupedItemHandler extends TTMItemHandler{
    @Nonnull
    private final TTMSlotGroup group;

    public TTMGroupedItemHandler(@Nonnull TTMSlotGroup group) {
        super();
        this.group = group;
    }

    public TTMGroupedItemHandler(@Nonnull TTMSlotGroup group, @Nullable ITTMInventoryCallback tile) {
        super(tile);
        this.group = group;
    }

    public TTMGroupedItemHandler(@Nonnull TTMSlotGroup group, @Nullable ITTMInventoryCallback tile, @Nonnull List<TTMItemSlot> slots) {
        super(tile, slots);
        this.group = group;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (group == TTMSlotGroup.OUTPUT) {
            return stack;
        }
        return super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (group == TTMSlotGroup.INPUT) {
            return EMPTY;
        }
        return super.extractItem(slot, amount, simulate);
    }
}