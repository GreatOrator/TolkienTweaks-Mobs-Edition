package com.greatorator.tolkienmobs.api.gui.container.inventory;

import com.greatorator.tolkienmobs.api.gui.container.slots.TTMItemSlot;
import com.greatorator.tolkienmobs.api.handler.TTMGroupedItemHandler;
import com.greatorator.tolkienmobs.api.handler.TTMItemHandler;
import com.greatorator.tolkienmobs.api.handler.TTMItemMultiHandler;
import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import com.greatorator.tolkienmobs.api.handler.intface.TTMSlotGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TTMInventory extends TTMItemHandler implements IInventory {
    protected List<TTMItemSlot> inputSlots = new ArrayList<>();
    protected List<TTMItemSlot> outputSlots = new ArrayList<>();

    protected IItemHandler handler;

    public TTMInventory(@Nullable ITTMInventoryCallback tile) {
        this(tile, Collections.emptyList());
    }

    public TTMInventory(@Nullable ITTMInventoryCallback tile, @Nonnull List<TTMItemSlot> slots) {
        super(tile, slots);
    }

    public void addSlot(TTMSlotGroup group, TTMItemSlot slot) {
        if (handler != null) {
            return;
        }

        slots.add(slot);
        switch (group) {
            case INPUT:
                inputSlots.add(slot);
                break;
            case OUTPUT:
                outputSlots.add(slot);
                break;
            default:
        }
    }

    public void addSlots(TTMSlotGroup group, int amount) {
        for (int i = 0; i < amount; ++i) {
            addSlot(group, new TTMItemSlot());
        }
    }

    public void addSlots(TTMSlotGroup group, List<TTMItemSlot> slots) {
        for (TTMItemSlot slot : slots) {
            addSlot(group, slot);
        }
    }

    protected void optimize() {
        ((ArrayList<TTMItemSlot>) slots).trimToSize();
        ((ArrayList<TTMItemSlot>) inputSlots).trimToSize();
        ((ArrayList<TTMItemSlot>) outputSlots).trimToSize();
    }

    public void initHandler() {
        optimize();

        handler = new TTMItemMultiHandler(
                new TTMGroupedItemHandler(TTMSlotGroup.INPUT, tile, inputSlots),
                new TTMGroupedItemHandler(TTMSlotGroup.OUTPUT, tile, outputSlots)
        );
    }

    @Nonnull
    public ItemStack get(int slot) {
        return slots.get(slot).getItemStack();
    }

    public void set(int slot, @Nonnull ItemStack stack) {
        slots.get(slot).setItemStack(stack);
    }

    public void clear() {
        for (TTMItemSlot slot : slots) {
            slot.setItemStack(ItemStack.EMPTY);
        }
    }

    public boolean hasInputSlots() {
        return inputSlots.size() > 0;
    }

    public boolean hasOutputSlots() {
        return outputSlots.size() > 0;
    }

    public boolean hasAccessibleSlots() {
        return hasInputSlots() || hasOutputSlots();
    }

    public TTMItemSlot getSlot(int slot) {
        return slots.get(slot);
    }

    public List<TTMItemSlot> getInputSlots() {
        return inputSlots;
    }

    public List<TTMItemSlot> getOutputSlots() {
        return outputSlots;
    }

    public TTMInventory getInputInventory() {
        return new TTMInventory(tile, getInputSlots());
    }

    public TTMInventory getOutputInventory() {
        return new TTMInventory(tile, getOutputSlots());
    }

    public IItemHandler getHandler() {
        if (handler == null) {
            initHandler();
        }
        return handler;
    }

    public TTMInventory read(CompoundNBT tag) {
        for (TTMItemSlot slot : slots) {
            slot.setItemStack(ItemStack.EMPTY);
        }
        ListNBT list = tag.getList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT slotTag = list.getCompound(i);
            int slot = slotTag.getByte("Slot");
            if (slot >= 0 && slot < slots.size()) {
                slots.get(slot).read(slotTag);
            }
        }
        return this;
    }

    public CompoundNBT write(CompoundNBT tag) {
        if (slots.size() <= 0) {
            return tag;
        }
        ListNBT list = new ListNBT();
        for (int i = 0; i < slots.size(); ++i) {
            if (!slots.get(i).isEmpty()) {
                CompoundNBT slotTag = new CompoundNBT();
                slotTag.putByte("Slot", (byte) i);
                slots.get(i).write(slotTag);
                list.add(slotTag);
            }
        }
        if (!list.isEmpty()) {
            tag.put("Inventory", list);
        }
        return tag;
    }

    @Override
    public int getContainerSize() {
        return slots.size();
    }

    @Nonnull
    @Override
    public ItemStack getItem(int slot) {
        return get(slot);
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int slot, int count) {
        return slots.get(slot).extractItem(0, count, false);
    }

    @Nonnull
    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        slots.get(slot).setItemStack(ItemStack.EMPTY);
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int slot, @Nonnull ItemStack stack) {
        slots.get(slot).setItemStack(stack);
    }

    @Override
    public void setChanged() {
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return true;
    }

    @Override
    public void clearContent() {
        clear();
    }
}