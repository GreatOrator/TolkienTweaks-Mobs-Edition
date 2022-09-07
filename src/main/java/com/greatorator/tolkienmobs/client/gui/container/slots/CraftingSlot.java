package com.greatorator.tolkienmobs.client.gui.container.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

public class CraftingSlot extends Slot {
    private final CraftingInventory craftSlots;
    private final PlayerEntity player;
    private int removeCount;

    public CraftingSlot(PlayerEntity player, CraftingInventory input, IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
        this.craftSlots = input;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack remove(int count) {
        if (this.hasItem()) {
            this.removeCount += Math.min(count, this.getItem().getCount());
        }

        return super.remove(count);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int count) {
        this.removeCount += count;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void onSwapCraft(int count) {
        this.removeCount += count;
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        if (this.removeCount > 0) {
            stack.onCraftedBy(this.player.level, this.player, this.removeCount);
            net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
        }

        if (this.container instanceof IRecipeHolder) {
            ((IRecipeHolder)this.container).awardUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        ForgeHooks.setCraftingPlayer(player);
        NonNullList<ItemStack> nonnulllist = player.level.getRecipeManager().getRemainingItemsFor(IRecipeType.CRAFTING, this.craftSlots, player.level);
        ForgeHooks.setCraftingPlayer(null);
        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = this.craftSlots.getItem(i);
            ItemStack itemstack1 = nonnulllist.get(i);
            if (!itemstack.isEmpty()) {
                this.craftSlots.removeItem(i, 1);
                itemstack = this.craftSlots.getItem(i);
            }

            if (!itemstack1.isEmpty()) {
                if (itemstack.isEmpty()) {
                    this.craftSlots.setItem(i, itemstack1);
                } else if (ItemStack.isSame(itemstack, itemstack1) && ItemStack.tagMatches(itemstack, itemstack1)) {
                    itemstack1.grow(itemstack.getCount());
                    this.craftSlots.setItem(i, itemstack1);
                } else if (!this.player.inventory.add(itemstack1)) {
                    this.player.drop(itemstack1, false);
                }
            }
        }

        return stack;
    }
}
