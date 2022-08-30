package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.item.tools.ItemTTMBackpack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

public class TTMISUtils {
    public static ItemStack getAndSplit(IItemHandler inventory, int index, int amount)
    {
        return index >= 0 && index < inventory.getSlots() && !inventory.getStackInSlot(index).isEmpty() && amount > 0 ? inventory.getStackInSlot(index).split(amount) : ItemStack.EMPTY;
    }

    @Nonnull
    public static Optional<UUID> getUUID(@Nonnull ItemStack stack) {
        if (stack.getItem() instanceof ItemTTMBackpack && stack.hasTag() && stack.getTag().contains("UUID"))
            return Optional.of(stack.getTag().getUUID("UUID"));
        else
            return Optional.empty();
    }
}
