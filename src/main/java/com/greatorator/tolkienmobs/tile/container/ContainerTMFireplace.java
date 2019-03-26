package com.greatorator.tolkienmobs.tile.container;

import com.brandon3055.brandonscore.inventory.ContainerBCBase;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.tile.TileTMFireplace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ContainerTMFireplace extends ContainerBCBase<TileTMFireplace> {

	public ContainerTMFireplace(EntityPlayer player, TileTMFireplace tile) {
		super(player, tile);

		addPlayerSlots(8, 84); //Add one to these positions to account for the border around the slots

		addSlotToContainer(new SlotCheckValid(tile, 0, 39, 16));// Input 1
		addSlotToContainer(new SlotCheckValid(tile, 1, 61, 16));// Input 2
		addSlotToContainer(new SlotCheckValid(tile, 2, 50, 56));// Fuel
		addSlotToContainer(new OutputSlot(tile, 3, 117, 36));// Output
	}

	public class OutputSlot extends Slot {

		public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean isItemValid(@Nullable ItemStack stack) {
			return false; //Dont let the player place anything in this slot
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();

			//From furnace to inventory
			if (i >= 36) {
				if (!mergeItemStack(stack, 0, 36, false)) {
					return ItemStack.EMPTY;
				}
			}
			else if (tile.isItemValidForSlot(0, stack) && mergeItemStack(stack, 36, 36 + 2, false)) {
				return ItemStack.EMPTY;
			}
			else if (tile.isItemValidForSlot(2, stack) && mergeItemStack(stack, 36 + 2, 36 + 3, false)) {
				return ItemStack.EMPTY;
			}

			if (stack.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}

			slot.onTake(player, stack);

			return result;
		}

		return ItemStack.EMPTY;
	}
}
