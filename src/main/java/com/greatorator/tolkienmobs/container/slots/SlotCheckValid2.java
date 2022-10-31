package com.greatorator.tolkienmobs.container.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Supplier;


public class SlotCheckValid2 extends SlotItemHandler {
    public Supplier<Boolean> isActive = null;

    public SlotCheckValid2(IItemHandler itemHandler, int id, int x, int y) {
        super(itemHandler, id, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return getItemHandler().isItemValid(getSlotIndex(), stack);
    }

    public void setIsActive(Supplier<Boolean> isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean isActive() {
        return isActive == null || isActive.get();
    }

    public static class IInv extends Slot {
        public Supplier<Boolean> isActive = null;

        public IInv(Container itemHandler, int id, int x, int y) {
            super(itemHandler, id, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return container.canPlaceItem(getSlotIndex(), stack);
        }

        public void setIsActive(Supplier<Boolean> isActive) {
            this.isActive = isActive;
        }

        @Override
        public boolean isActive() {
            return isActive == null || isActive.get();
        }
    }
}