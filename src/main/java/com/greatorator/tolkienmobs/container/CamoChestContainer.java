package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.entity.tile.CamoChestTile;
import com.greatorator.tolkienmobs.init.TolkienContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CamoChestContainer extends ContainerBCTile<CamoChestTile> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<Slot> mainSlots = new ArrayList<>();

    public CamoChestContainer(int windowId, Inventory playerInv, FriendlyByteBuf extraData) {
        this(TolkienContainers.CAMO_CHEST_CONTAINER, windowId, playerInv, getClientTile(extraData));
        //^^ Don't forget this!
    }

    public CamoChestContainer(@Nullable MenuType<?> type, int windowId, Inventory playerInv, CamoChestTile tile) {
        super(type, windowId, playerInv, tile);

        //Player Inventory
        for (int i = 0; i < playerInv.items.size(); i++) {
            playerSlots.add(addSlot(new SlotCheckValid.IInv(playerInv, i, 0, 0)));
        }

        //Main Inventory
        for (int i = 0; i < tile.mainInventory.getSlots(); i++) {
            mainSlots.add(addSlot(new SlotCheckValid(tile.mainInventory, i, 0, 0)));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        int playerSlots = 36 + 5;
        IItemHandler handler = tile.mainInventory;
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
                int slotsPerUpgrade = mainSlots.size() / 3;
                if (!moveItemStackTo(stack, playerSlots, playerSlots + slotsPerUpgrade + (slotsPerUpgrade), false)) {
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