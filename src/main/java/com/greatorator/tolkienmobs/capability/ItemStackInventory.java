package com.greatorator.tolkienmobs.capability;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by brandon3055 on 14/09/2022
 */
public class ItemStackInventory implements IItemHandlerModifiable, ICapabilityProvider {

    private final ItemStack stack;
    private int slots;
    private final LazyOptional<IItemHandler> holder = LazyOptional.of(() -> this);

    private CompoundNBT cachedTag;
    private NonNullList<ItemStack> itemStacksCache;
    private BiPredicate<Integer, ItemStack> stackValidator = null;

    public ItemStackInventory(ItemStack stack, int slots) {
        this.stack = stack;
        this.slots = slots;
    }

    public ItemStackInventory setStackValidator(BiPredicate<Integer, ItemStack> stackValidator) {
        this.stackValidator = stackValidator;
        return this;
    }

    public ItemStackInventory setStackValidator(Predicate<ItemStack> stackValidator) {
        this.stackValidator = (integer, stack) -> stackValidator.test(stack);
        return this;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        validateSlotIndex(slot);
        return getItemList().get(slot);
    }

    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (!isItemValid(slot, stack)) {
            return stack;
        }

        validateSlotIndex(slot);

        NonNullList<ItemStack> itemStacks = getItemList();

        ItemStack existing = itemStacks.get(slot);

        int limit = Math.min(getSlotLimit(slot), stack.getMaxStackSize());

        if (!existing.isEmpty()) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (existing.isEmpty()) {
                itemStacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            setItemList(itemStacks);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        NonNullList<ItemStack> itemStacks = getItemList();
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = itemStacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                itemStacks.set(slot, ItemStack.EMPTY);
                setItemList(itemStacks);
                return existing;
            } else {
                return existing.copy();
            }
        } else {
            if (!simulate) {
                itemStacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                setItemList(itemStacks);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    private void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= getSlots())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + getSlots() + ")");
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stackValidator == null || stack.isEmpty() || stackValidator.test(slot, stack);
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        validateSlotIndex(slot);
        if (!isItemValid(slot, stack)) throw new RuntimeException("Invalid stack " + stack + " for slot " + slot + ")");
        NonNullList<ItemStack> itemStacks = getItemList();
        itemStacks.set(slot, stack);
        setItemList(itemStacks);
    }

    private NonNullList<ItemStack> getItemList() {
        CompoundNBT rootTag = stack.getOrCreateTagElement("item_inv");
        if (cachedTag == null || !cachedTag.equals(rootTag)) {
            itemStacksCache = refreshItemList(rootTag);
        }
        return itemStacksCache;
    }

    private NonNullList<ItemStack> refreshItemList(CompoundNBT rootTag) {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(getSlots(), ItemStack.EMPTY);
        if (rootTag != null && rootTag.contains("Items", 9)) {
            ItemStackHelper.loadAllItems(rootTag, itemStacks);
        }
        cachedTag = rootTag;
        return itemStacks;
    }

    private void setItemList(NonNullList<ItemStack> itemStacks) {
        CompoundNBT existing = stack.getOrCreateTagElement("item_inv");
        cachedTag = ItemStackHelper.saveAllItems(existing, itemStacks);
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, this.holder);
    }

}
