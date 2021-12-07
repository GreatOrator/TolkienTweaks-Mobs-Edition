package com.greatorator.tolkienmobs.tileentity.container;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.tileentity.TTMFireplaceTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Objects;

public class ContainerTTMFireplace extends Container {
    public final TTMFireplaceTile tileEntity;
    private final IIntArray data;
    private final IWorldPosCallable canInteractWithCallable;

    public ContainerTTMFireplace(int windowId, PlayerInventory playerInventory, TTMFireplaceTile tileEntity, IIntArray data) {
        super(TTMContent.TMFIREPLACE_CONTAINER.get(), windowId);
        this.data = data;
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        this.addSlot(new InputSlot((IInventory) tileEntity, 0, 39, 16)); // Input 1
        this.addSlot(new InputSlot((IInventory) tileEntity, 1, 61, 16)); // Input 1
        this.addSlot(new FuelSlot((IInventory) tileEntity, 2, 50, 56)); // Fuel
        this.addSlot(new OutputSlot((IInventory) tileEntity, 3, 117, 36)); // Output

        // Main Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0 ; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 168 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 144));
        }

        addDataSlots(this.data);
    }

    public ContainerTTMFireplace(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data), new IntArray(4));
    }

    private static TTMFireplaceTile getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv,"Player inv cannot be null");
        Objects.requireNonNull(data,"Pack buffer cannot be null");
        final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());

        if (te instanceof TTMFireplaceTile) {
            return (TTMFireplaceTile) te;
        }
        throw new IllegalStateException("Tile entity is null");
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return stillValid(canInteractWithCallable, playerIn, TTMContent.TTMFIREPLACE.get());
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return true;
    }

    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.getSlot(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < TTMFireplaceTile.ContainerSize
                    && !this.moveItemStackTo(stack1, TTMFireplaceTile.ContainerSize, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, TTMFireplaceTile.ContainerSize, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }

    public static class InputSlot extends Slot {

        public InputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@Nullable ItemStack stack) {
            return stack.getItem().isEdible() || !isFuel(stack);
        }

        public static boolean isFuel(ItemStack stack) {
            return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
        }
    }

    private static class FuelSlot extends Slot {
        public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@Nullable ItemStack stack) {
            return super.mayPlace(stack) && isFuel(stack);
        }

        @Override
        public int getMaxStackSize(ItemStack stack) {
            return super.getMaxStackSize(stack);
        }

        public static boolean isFuel(ItemStack stack) {
            return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
        }

        @Override
        public void setChanged() {
            super.setChanged();
        }
    }

    public static class OutputSlot extends Slot {

        public OutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@Nullable ItemStack stack) {
            return false; //Don't let the player place anything in this slot
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getProcess() {
        int process = data.get(0);
        int maxTick = data.get(1);
        return maxTick != 0 && process != 0 ? process * 32 / maxTick : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getFuel() {
        int fuel = data.get(2);
        int maxFuel = data.get(3);
        return maxFuel != 0 && fuel != 0 ? fuel * 17 / maxFuel : 0;
    }
}