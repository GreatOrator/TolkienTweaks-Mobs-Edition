package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by brandon3055 on 09/12/2021
 */
public interface IFireplaceInventory extends IInventory {

    IItemHandler getItemHandler();

    //@formatter:off
    @Override default int getContainerSize() { return 0; }
    @Override default boolean isEmpty() { return false; }
    @Override default ItemStack getItem(int index) { return getItemHandler().getStackInSlot(index); }
    @Override default ItemStack removeItem(int index, int count) { return ItemStack.EMPTY; }
    @Override default ItemStack removeItemNoUpdate(int index) { return ItemStack.EMPTY; }
    @Override default void setItem(int index, ItemStack stack) { }
    @Override default void setChanged() { }
    @Override default boolean stillValid(PlayerEntity player) { return false; }
    @Override default void clearContent() { }
    //@formatter:on
}
