package com.greatorator.tolkienmobs.client.gui.container;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import com.greatorator.tolkienmobs.handler.TTMISUtils;
import com.greatorator.tolkienmobs.handler.TTMLockable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ContainerTTMBackpack extends Container {
    public final TTMBackpackTile te;
    private UUID uuid;

    public ContainerTTMBackpack(final int windowId, final PlayerInventory playerInv, final TTMBackpackTile te) {
        super(TTMContent.BACKPACK_CONTAINER_TYPE.get(), windowId);
        this.te = te;
        addPlayerSlots(playerInv);
        // Tile Entity
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new SlotItemHandler(te.getInventory(), col + row * 9 + 9, 75, 6));
            }
        }
    }

    public ContainerTTMBackpack(final int windowId, final PlayerInventory playerInv, final PacketBuffer data, UUID uuidIn) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
        this.uuid = uuidIn;
    }

    private static TTMBackpackTile getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
        if (te instanceof TTMBackpackTile) {
            return (TTMBackpackTile) te;
        }
        throw new IllegalStateException("Tile Entity Is Not Correct");
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < TTMBackpackTile.slots
                    && !this.moveItemStackTo(stack1, TTMBackpackTile.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, TTMBackpackTile.slots, false)) {
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

    private void addPlayerSlots(PlayerInventory playerInventory) {
        //Hotbar
        for (int col = 0; col < 9; col++) {
            int x = 75 + col * 18;
            int y = 185 + 58;
            Optional<UUID> uuidOptional = TTMISUtils.getUUID(playerInventory.items.get(col));
            boolean lockMe = uuidOptional.map(id -> id.compareTo(this.uuid) == 0).orElse(false);
            this.addSlot(new TTMLockable(playerInventory, col, x+1, y+1, lockMe));
        }

        //Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = 75 + col * 18;
                int y = 127 + row * 18;
                int index = (col + row * 9) + 9;
                Optional<UUID> uuidOptional = TTMISUtils.getUUID(playerInventory.items.get(index));
                boolean lockMe = uuidOptional.map(id -> id.compareTo(this.uuid) == 0).orElse(false);
                this.addSlot(new TTMLockable(playerInventory, index, x+1, y+1, lockMe));
            }
        }
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return true;
    }
}