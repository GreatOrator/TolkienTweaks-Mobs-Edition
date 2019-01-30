package com.greatorator.tolkienmobs.client;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.function.Supplier;

public class TTMobsTab extends CreativeTabs {
    private final Supplier<Item> itemSupplier;

    public TTMobsTab(String label, Supplier<Item> itemSupplier) {
        super(TolkienMobs.MODID + ":" + label);
        this.itemSupplier = itemSupplier;
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(itemSupplier.get());
    }

    public static class SpawnTab extends TTMobsTab {
        private ItemStack icon = ItemStack.EMPTY;

        public SpawnTab(String label, Supplier<Item> itemSupplier) {
            super(label, itemSupplier);
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> list) {
            for (EntityList.EntityEggInfo info : EntityList.ENTITY_EGGS.values()) {
                if (info.spawnedID.getResourceDomain().equals(TolkienMobs.MODID)) {
                    ItemStack itemstack = new ItemStack(Items.SPAWN_EGG, 1);
                    ItemMonsterPlacer.applyEntityIdToItemStack(itemstack, info.spawnedID);
                    list.add(itemstack);
                    icon = itemstack;
                }
            }
        }

        @Override
        public ItemStack getTabIconItem() {
            if (!icon.isEmpty()) {
                return icon;
            }
            return super.getTabIconItem();
        }
    }
}