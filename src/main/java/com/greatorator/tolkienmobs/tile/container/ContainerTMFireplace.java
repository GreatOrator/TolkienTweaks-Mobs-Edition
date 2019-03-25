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

	//All this junk can go!
	//	private final TileTMFireplace tileentity;
//	private int cookTime, totalCookTime, burnTime, currentBurnTime;

//	public ContainerTMFireplace(InventoryPlayer player, TileTMFireplace tileentity) {
//		this.tileentity = tileentity;
//		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//
//		this.addSlotToContainer(new SlotItemHandler(handler, 0, 26, 11));
//		this.addSlotToContainer(new SlotItemHandler(handler, 1, 26, 59));
//		this.addSlotToContainer(new SlotItemHandler(handler, 2, 7, 35));
//		this.addSlotToContainer(new SlotItemHandler(handler, 3, 81, 36));
//
//		for (int y = 0; y < 3; y++) {
//			for (int x = 0; x < 9; x++) {
//				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
//			}
//		}
//
//		for (int x = 0; x < 9; x++) {
//			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
//		}
//	}
//
//	@Override
//	public void detectAndSendChanges() {
//		super.detectAndSendChanges();
//
//		for (int i = 0; i < this.listeners.size(); ++i) {
//			IContainerListener listener = (IContainerListener) this.listeners.get(i);
//
//			if (this.cookTime != this.tileentity.getField(2))
//				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
//			if (this.burnTime != this.tileentity.getField(0))
//				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
//			if (this.currentBurnTime != this.tileentity.getField(1))
//				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
//			if (this.totalCookTime != this.tileentity.getField(3))
//				listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
//		}
//
//		this.cookTime = this.tileentity.getField(2);
//		this.burnTime = this.tileentity.getField(0);
//		this.currentBurnTime = this.tileentity.getField(1);
//		this.totalCookTime = this.tileentity.getField(3);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void updateProgressBar(int id, int data) {
//		this.tileentity.setField(id, data);
//	}
//
//	@Override
//	public boolean canInteractWith(EntityPlayer playerIn) {
//		return this.tileentity.isUsableByPlayer(playerIn);
//	}
//
//	@Override
//	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
//		ItemStack stack = ItemStack.EMPTY;
//		Slot slot = (Slot) this.inventorySlots.get(index);
//
//		if (slot != null && slot.getHasStack()) {
//			ItemStack stack1 = slot.getStack();
//			stack = stack1.copy();
//
//			if (index == 3) {
//				if (!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
//				slot.onSlotChange(stack1, stack);
//			} else if (index != 2 && index != 1 && index != 0) {
//				Slot slot1 = (Slot) this.inventorySlots.get(index + 1);
//
//				if (!TMFireplaceRecipes.getInstance().getFireplaceResult(stack1, slot1.getStack()).isEmpty()) {
//					if (!this.mergeItemStack(stack1, 0, 2, false)) {
//						return ItemStack.EMPTY;
//					} else if (TileTMFireplace.isItemFuel(stack1)) {
//						if (!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
//					} else if (TileTMFireplace.isItemFuel(stack1)) {
//						if (!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
//					} else if (TileTMFireplace.isItemFuel(stack1)) {
//						if (!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
//					} else if (index >= 4 && index < 31) {
//						if (!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
//					} else if (index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)) {
//						return ItemStack.EMPTY;
//					}
//				}
//			} else if (!this.mergeItemStack(stack1, 4, 40, false)) {
//				return ItemStack.EMPTY;
//			}
//			if (stack1.isEmpty()) {
//				slot.putStack(ItemStack.EMPTY);
//			} else {
//				slot.onSlotChanged();
//
//			}
//			if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
//			slot.onTake(playerIn, stack1);
//		}
//		return stack;
//	}
}
