package com.greatorator.tolkienmobs.api.handler.intface;

public interface ITTMInventoryCallback {
    default void onInventoryChange(int slot) {}

    default void onTankChange(int tank) {}

    default boolean clearSlot(int slot) {
        return false;
    }

    default boolean clearTank(int tank) {
        return false;
    }
}
