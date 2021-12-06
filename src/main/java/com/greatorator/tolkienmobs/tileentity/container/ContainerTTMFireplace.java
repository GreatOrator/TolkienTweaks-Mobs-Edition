package com.greatorator.tolkienmobs.tileentity.container;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ContainerTTMFireplace extends Container {
    private final TileEntity tileEntity;
    private final PlayerEntity playerEntity;
    private final IItemHandler playerInventory;

    public ContainerTTMFireplace(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(TTMContent.TMFIREPLACE_CONTAINER.get(), windowId);
        this.tileEntity = world.getBlockEntity(pos);
        playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        layoutPlayerInventorySlots(8, 86);

        if(tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 39, 16)); // Input 1
                addSlot(new SlotItemHandler(h, 1, 61, 16)); // Input 2
                addSlot(new SlotItemHandler(h, 2, 50, 56)); // Fuel
                addSlot(new SlotItemHandler(h, 3, 117, 36)); // Output
            });
        }
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, TTMContent.TTMFIREPLACE.get());    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // Set number of tile entity slots

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        Slot sourceSlot = this.slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }
//
//	public ContainerTMFireplace(PlayerEntity player, TileTMFireplace tile) {
//		super(player, tile);
//
//		addPlayerSlots(8, 84); //Add one to these positions to account for the border around the slots
//
//		addSlotToContainer(new SlotCheckValid(tile, 0, 39, 16));// Input 1
//		addSlotToContainer(new SlotCheckValid(tile, 1, 61, 16));// Input 2
//		addSlotToContainer(new SlotCheckValid(tile, 2, 50, 56));// Fuel
//		addSlotToContainer(new OutputSlot(tile, 3, 117, 36));// Output
//	}
//
//	public class OutputSlot extends Slot {
//
//		public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
//			super(inventoryIn, index, xPosition, yPosition);
//		}
//
//		@Override
//		public boolean isItemValid(@Nullable ItemStack stack) {
//			return false; //Dont let the player place anything in this slot
//		}
//	}
//
//	@Override
//	public ItemStack transferStackInSlot(PlayerEntity player, int i) {
//		Slot slot = getSlot(i);
//
//		if (slot != null && slot.getHasStack()) {
//			ItemStack stack = slot.getStack();
//			ItemStack result = stack.copy();
//
//			//From furnace to inventory
//			if (i >= 36) {
//				if (!mergeItemStack(stack, 0, 36, false)) {
//					return ItemStack.EMPTY;
//				}
//			}
//			else if ((tile.isItemValidForSlot(0, stack) || tile.isItemValidForSlot(1, stack)) && !mergeItemStack(stack, 36, 36 + 2, false)) {
//				return ItemStack.EMPTY;
//			}
//			else if (tile.isItemValidForSlot(2, stack) && !mergeItemStack(stack, 36 + 2, 36 + 3, false)) {
//				return ItemStack.EMPTY;
//			}
//			else {
//				return ItemStack.EMPTY;
//			}
//
//			if (stack.getCount() == 0) {
//				slot.putStack(ItemStack.EMPTY);
//			}
//			else {
//				slot.onSlotChanged();
//			}
//
//			slot.onTake(player, stack);
//
//			return result;
//		}
//
//		return ItemStack.EMPTY;
//	}
}
