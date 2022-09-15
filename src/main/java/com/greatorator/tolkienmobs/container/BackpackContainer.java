package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.container.slots.SlotCheckValid2;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import com.greatorator.tolkienmobs.lib.CraftingInventoryWrapper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackContainer extends ContainerBCTile<BackpackTile> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<Slot> playerEquipment = new ArrayList<>();
    public List<SlotCheckValid2> mainSlots = new ArrayList<>();
    public List<SlotCheckValid2> upgradeSlots = new ArrayList<>();
    public List<SlotCheckValid2.IInv> craftInputSlots = new ArrayList<>();
    public List<SlotCheckValid2> fluidItemSlots = new ArrayList<>();
    public CraftingResultSlot2 craftResultSlot;
    private CraftingInventoryWrapper craftInventory;
    private final CraftResultInventory resultInventory = new CraftResultInventory();

    public BackpackContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        this(TTMContent.BACKPACK_CONTAINER, windowId, playerInv, getClientTile(extraData));
        //^^ Don't forget this!
    }

    public BackpackContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInv, BackpackTile tile) {
        super(type, windowId, playerInv, tile);

        //Player Inventory
        for (int i = 0; i < playerInv.items.size(); i++) {
            playerSlots.add(addSlot(new SlotCheckValid2.IInv(playerInv, i, 0, 0)));
        }

        //Player Armor
        for (int i = 0; i < playerInv.armor.size(); i++) {
            playerEquipment.add(addSlot(new SlotCheckValid2.IInv(playerInv, i + 36, 0, 0)));
        }

        //Player Off-hand
        playerEquipment.add(addSlot(new SlotCheckValid2.IInv(playerInv, 36 + 4, 0, 0)));

        //Main Inventory
        for (int i = 0; i < tile.mainInventory.getSlots(); i++) {
            mainSlots.add((SlotCheckValid2) addSlot(new SlotCheckValid2(tile.mainInventory, i, 0, 0)));
        }

        //Upgrade Inventory
        for (int i = 0; i < tile.upgradeInventory.getSlots(); i++) {
            upgradeSlots.add((SlotCheckValid2) addSlot(new SlotCheckValid2(tile.upgradeInventory, i, 0, 0)));
        }

        //Crafting Inventory
        craftInventory = new CraftingInventoryWrapper(this, 3, 3, tile.craftingItems);
        this.addSlot(craftResultSlot = new CraftingResultSlot2(playerInv.player, craftInventory, resultInventory, 0, 0, 0));
        for (int i = 0; i < 9; ++i) {
            craftInputSlots.add((SlotCheckValid2.IInv) addSlot(new SlotCheckValid2.IInv(craftInventory, i, 0, 0)));
        }

        //Fluid Item Inventory
        for (int i = 0; i < tile.fluidItems.getSlots(); i++) {
            fluidItemSlots.add((SlotCheckValid2) addSlot(new SlotCheckValid2(tile.fluidItems, i, 0, 0)));
        }

        slotsChanged(playerInv); //<-- Ensures the craft result is updated when you open the gui
    }

    protected void slotChangedCraftingGrid(int containerID, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftResultInventory resultInventory) {
        if (!world.isClientSide && tile.craftUpgrade.get() > 0) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(IRecipeType.CRAFTING, craftingInventory, world);
            if (optional.isPresent()) {
                ICraftingRecipe icraftingrecipe = optional.get();
                if (resultInventory.setRecipeUsed(world, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.assemble(craftingInventory);
                }
            }

            resultInventory.setItem(0, itemstack);
            serverplayerentity.connection.send(new SSetSlotPacket(containerID, craftResultSlot.index, itemstack));
        } else if (!world.isClientSide && tile.craftUpgrade.get() == 0) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
            resultInventory.setItem(0, ItemStack.EMPTY);
            serverplayerentity.connection.send(new SSetSlotPacket(containerID, craftResultSlot.index, ItemStack.EMPTY));
        }
    }

    @Override
    public void slotsChanged(IInventory inventory) {
        slotChangedCraftingGrid(this.containerId, tile.getLevel(), this.player, this.craftInventory, this.resultInventory);
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
                if (!moveItemStackTo(stack, playerSlots, playerSlots + slotsPerUpgrade + (slotsPerUpgrade * tile.sizeUpgrade.get()), false)) {
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

    public static class CraftingResultSlot2 extends CraftingResultSlot {
        public Supplier<Boolean> isActive = null;

        public CraftingResultSlot2(PlayerEntity p_i45790_1_, CraftingInventory p_i45790_2_, IInventory p_i45790_3_, int p_i45790_4_, int p_i45790_5_, int p_i45790_6_) {
            super(p_i45790_1_, p_i45790_2_, p_i45790_3_, p_i45790_4_, p_i45790_5_, p_i45790_6_);
        }

        public CraftingResultSlot2 setIsActive(Supplier<Boolean> isActive) {
            this.isActive = isActive;
            return this;
        }

        @Override
        public boolean isActive() {
            return isActive == null || isActive.get();
        }
    }
}