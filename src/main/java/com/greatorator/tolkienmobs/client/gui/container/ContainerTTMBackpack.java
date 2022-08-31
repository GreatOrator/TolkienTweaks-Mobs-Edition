package com.greatorator.tolkienmobs.client.gui.container;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.tile.TTMBackpackTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Objects;
import java.util.UUID;

public class ContainerTTMBackpack extends Container {
    public final TTMBackpackTile te;
    private static final EquipmentSlotType[] PLAYER_EQUIPMENT = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
    private UUID uuid;

    public ContainerTTMBackpack(final int windowId, final PlayerInventory playerInv, final TTMBackpackTile te) {
        super(TTMContent.BACKPACK_CONTAINER_TYPE.get(), windowId);
        this.te = te;
        this.addPlayerSlots(playerInv);
//        this.addCraftMatrix();
        // Tile Entity
        for(int j = 0; j < 6; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(te.getInventory(), k * j, 90 + k * 18, 6 + j * 18));
            }
        }

        // Crafting Grid
        for(int j = 0; j < 3; ++j) {
            for(int k = 0; k < 3; ++k) {
                this.addSlot(new SlotItemHandler(te.getInventory(), (k * j) + 53, 6 + k * 18, 6 + j * 18));
            }
        }
        // Crafting Output
        this.addSlot(new SlotItemHandler(te.getInventory(), 63, 6, 62));

        // Water Input
        this.addSlot(new SlotItemHandler(te.getInventory(), 64, 6, 96));

        // Water Output
        this.addSlot(new SlotItemHandler(te.getInventory(), 65, 6, 135));

        // Water Tank
        this.addSlot(new SlotItemHandler(te.getInventory(), 66, 37, 91));



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
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 6 * 9) {
                if (!this.moveItemStackTo(itemstack1, 6 * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 6 * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    private void addPlayerSlots(PlayerInventory playerInventory) {

        //Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = 89 + col * 18;
                int y = 126 + row * 18;
                int index = (col + row * 9) + 9;
                this.addSlot(new Slot(playerInventory, index, x+1, y+1));
            }
        }

        //Hotbar
        for (int col = 0; col < 9; col++) {
            int x = 89 + col * 18;
            int y = 184;
            this.addSlot(new Slot(playerInventory, col, x+1, y+1));
        }

        // Armor
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return true;
    }
}