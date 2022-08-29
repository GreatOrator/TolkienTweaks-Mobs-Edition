package com.greatorator.tolkienmobs.client.gui.container;

//import com.greatorator.tolkienmobs.TTMContent;
//import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.IWorldPosCallable;
//
//import java.util.Objects;
//
//public class ContainerTTMBackpack extends Container {
//    public final TTMBackpackTile tile;
//    private final IWorldPosCallable callable;
//
//    public ContainerTTMBackpack(final int windowID, final PlayerInventory playerInv, final TTMBackpackTile tile) {
//        super(TTMContent.BACKPACK_CONTAINER.get(), windowID);
//        this.tile = tile;
//        this.callable = IWorldPosCallable.create(tile.getLevel(), tile.getBlockPos());
//
//        // Backpack Inventory
//        for (int row = 0; row < 6; row++) {
//            for (int col = 0; col < 9; col++) {
//                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 75, 6 - (4 - row) * 75 - 10));
//            }
//        }
//
//        // Crafting Inventory
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 6, 6 - (4 - row) * 18 - 10));
//            }
//        }
//
//        // Crafting Output
//        this.addSlot(new Slot(tile, 0, 6, 62));
//
//        // Tank Input
//        this.addSlot(new Slot(tile, 0, 6, 96));
//
//        // Tank Output
//        this.addSlot(new Slot(tile, 0, 6, 135));
//
//
////        // Tank Inventory
////        for (int row = 0; row < 3; row++) {
////            for (int col = 0; col < 9; col++) {
////                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
////            }
////        }
//
//        // Player Inventory
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 9; col++) {
//                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 75, 127 - (4 - row) * 75 - 10));
//            }
//        }
//
//        // Player Hotbar
//        for (int col = 0; col < 9; col++) {
//            this.addSlot(new Slot(playerInv, col, 8 + col * 75, 184));
//        }
//    }
//    public ContainerTTMBackpack(final int windowID, final PlayerInventory playerInv, final PacketBuffer data) {
//        this(windowID, playerInv, getTileEntity(playerInv, data));
//    }
//
//    private static TTMBackpackTile getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
//        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
//        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
//        final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
//        if (te instanceof TTMBackpackTile) {
//            return (TTMBackpackTile) te;
//        }
//        throw new IllegalStateException("Tile Entity Is Not Correct");
//    }
//
////    @Override
////    public boolean canInteractWith(PlayerEntity playerIn) {
////        return isWithinUsableDistance(callable, playerIn, TTMContent.BACKPACK.get());
////    }
//
//    @Override
//    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
//        ItemStack stack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(index);
//        if (slot != null && slot.hasItem()) {
//            ItemStack stack1 = slot.getItem();
//            stack = stack1.copy();
//            if (index < TTMBackpackTile.slots
//                    && !this.moveItemStackTo(stack1, TTMBackpackTile.slots, this.slots.size(), true)) {
//                return ItemStack.EMPTY;
//            }
//            if (!this.moveItemStackTo(stack1, 0, TTMBackpackTile.slots, false)) {
//                return ItemStack.EMPTY;
//            }
//
//            if (stack1.isEmpty()) {
//                slot.set(ItemStack.EMPTY);
//            } else {
//                slot.setChanged();
//            }
//        }
//        return stack;
//    }
//
//    @Override
//    public boolean stillValid(PlayerEntity p_75145_1_) {
//        return false;
//    }
//}
