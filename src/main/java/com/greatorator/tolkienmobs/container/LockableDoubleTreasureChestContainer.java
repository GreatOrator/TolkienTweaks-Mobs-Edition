package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.container.slots.SlotCheckValid2;
import com.greatorator.tolkienmobs.entity.tile.LockableDoubleTreasureChestTile;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LockableDoubleTreasureChestContainer extends ContainerBCTile<LockableDoubleTreasureChestTile> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<SlotCheckValid2> mainSlots = new ArrayList<>();

    public LockableDoubleTreasureChestContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        this(TTMContent.LOCKABLE_DOUBLE_TREASURE_CHEST_CONTAINER, windowId, playerInv, getClientTile(extraData));
        //^^ Don't forget this!
    }

    public LockableDoubleTreasureChestContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInv, LockableDoubleTreasureChestTile tile) {
        super(type, windowId, playerInv, tile);
        ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);

        if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getKey(stack).equals(tile.keyCode.get()))) {
            //Player Inventory
            for (int i = 0; i < playerInv.items.size(); i++) {
                playerSlots.add(addSlot(new SlotCheckValid2.IInv(playerInv, i, 0, 0)));
            }

            //Main Inventory
            for (int i = 0; i < tile.mainInventory.getSlots(); i++) {
                mainSlots.add((SlotCheckValid2) addSlot(new SlotCheckValid2(tile.mainInventory, i, 0, 0)));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int i) {
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