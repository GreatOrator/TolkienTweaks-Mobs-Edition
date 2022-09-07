package com.greatorator.tolkienmobs.container;

import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.SlotCheckValid;
import com.greatorator.tolkienmobs.TTMContent;
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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Overhauled by brandon3055 on 07/09/2022
 */
public class BackpackContainer extends ContainerBCTile<BackpackTile> {
    public List<Slot> playerSlots = new ArrayList<>();
    public List<Slot> playerEquipment = new ArrayList<>();
    public List<Slot> mainSlots = new ArrayList<>();
    public List<Slot> craftInputSlots = new ArrayList<>();
    public List<Slot> fluidItemSlots = new ArrayList<>();
    public Slot craftResultSlot;
    private CraftingInventoryWrapper craftInventory;
    private final CraftResultInventory resultInventory = new CraftResultInventory();

    public BackpackContainer(int windowId, PlayerInventory playerInv, PacketBuffer extraData) {
        this(TTMContent.BACKPACK_CONTAINER, windowId, playerInv, getClientTile(extraData));
        //^^ Dont forget this!
    }

    public BackpackContainer(@Nullable ContainerType<?> type, int windowId, PlayerInventory playerInv, BackpackTile tile) {
        super(type, windowId, playerInv, tile);

        //Player Inventory
        for (int i = 0; i < playerInv.items.size(); i++) {
            playerSlots.add(addSlot(new SlotCheckValid.IInv(playerInv, i, 0, 0)));
        }

        //Player Armor
        for (int i = 0; i < playerInv.armor.size(); i++) {
            playerEquipment.add(addSlot(new SlotCheckValid.IInv(playerInv, i + 36, 0, 0)));
        }

        //Player Off-hand
        playerEquipment.add(addSlot(new SlotCheckValid.IInv(playerInv, 36 + 4, 0, 0)));

        //Main Inventory
        for (int i = 0; i < tile.mainInventory.getSlots(); i++) {
            mainSlots.add(addSlot(new SlotCheckValid(tile.mainInventory, i, 0, 0)));
        }

        //Crafting Inventory
        craftInventory = new CraftingInventoryWrapper(this, 3, 3, tile.craftingItems);
        this.addSlot(craftResultSlot = new CraftingResultSlot(playerInv.player, craftInventory, resultInventory, 0, 0, 0));
        for (int i = 0; i < 9; ++i) {
            craftInputSlots.add(addSlot(new Slot(craftInventory, i, 0, 0)));
        }

        //Fluid Item Inventory
        for (int i = 0; i < tile.fluidItems.getSlots(); i++) {
            fluidItemSlots.add(addSlot(new SlotCheckValid(tile.fluidItems, i, 0, 0)));
        }

        slotsChanged(playerInv); //<-- Ensures the craft result is updated when you open the gui
    }

    protected void slotChangedCraftingGrid(int containerID, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftResultInventory resultInventory) {
        if (!world.isClientSide) {
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
        }
    }

    @Override
    public void slotsChanged(IInventory inventory) {
        slotChangedCraftingGrid(this.containerId, tile.getLevel(), this.player, this.craftInventory, this.resultInventory);
    }
}