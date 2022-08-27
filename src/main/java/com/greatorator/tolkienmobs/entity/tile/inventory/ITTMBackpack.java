package com.greatorator.tolkienmobs.entity.tile.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public interface ITTMBackpack {
    boolean hasWearable();

    ItemStack getWearable();

    void setWearable(ItemStack stack);

    void removeWearable();

    TTMBackpackInventory getInventory();

    void setContents(ItemStack stack);

    void synchronise();

    void synchroniseToOthers(PlayerEntity player);

    CompoundNBT saveTag();

    void loadTag(CompoundNBT compoundTag);
}
