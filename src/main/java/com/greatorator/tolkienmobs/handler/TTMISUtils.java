package com.greatorator.tolkienmobs.handler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class TTMISUtils {
    public static ItemStack getAndSplit(IItemHandler inventory, int index, int amount)
    {
        return index >= 0 && index < inventory.getSlots() && !inventory.getStackInSlot(index).isEmpty() && amount > 0 ? inventory.getStackInSlot(index).split(amount) : ItemStack.EMPTY;
    }
}
