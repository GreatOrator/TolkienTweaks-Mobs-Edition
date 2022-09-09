package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class UpgradeContainer extends ContainerBCTile<BackpackTile> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<Slot> mainSlots = new ArrayList<>();

    public UpgradeContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        this(TTMContent.UPGRADE_CONTAINER, windowId, playerInv, getClientTile(extraData));
        //^^ Don't forget this!
    }

    public UpgradeContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInv, BackpackTile tile) {
        super(type, windowId, playerInv, tile);

        //Player Inventory
        for (int i = 0; i < playerInv.items.size(); i++) {
            playerSlots.add(addSlot(new SlotCheckValid.IInv(playerInv, i, 0, 0)));
        }

        //Upgrade Inventory
        for (int i = 0; i < tile.upgradeInventory.getSlots(); i++) {
            mainSlots.add(addSlot(new SlotCheckValid(tile.upgradeInventory, i, 0, 0)));
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int i) {
        int playerSlots = 36;
        IItemHandler handler = tile.upgradeInventory;
        Slot slot = getSlot(i);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack result = stack.copy();

            //Transferring from tile to player
            if (i >= playerSlots) {
                if (!moveItemStackTo(stack, 0, playerSlots, false)) {
                    return ItemStack.EMPTY; //Return if failed to merge
                }
            } else {
                //Transferring from player to tile
                if (!moveItemStackTo(stack, playerSlots, playerSlots + handler.getSlots(), false)) {
                    return ItemStack.EMPTY;  //Return if failed to merge
                }
            }

            if (stack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, stack);

            return result;
        }
        return ItemStack.EMPTY;
    }
}