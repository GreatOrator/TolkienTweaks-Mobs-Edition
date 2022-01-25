package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.ItemHandlerIOControl;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.brandon3055.brandonscore.lib.datamanager.ManagedBool;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BlockTTMPiggyBank;
import com.greatorator.tolkienmobs.crafting.IFireplaceInventory;
import com.greatorator.tolkienmobs.init.TTMTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

import static com.brandon3055.brandonscore.lib.datamanager.DataFlags.SAVE_BOTH_SYNC_TILE;
import static net.minecraft.util.Direction.UP;

public class TTMPiggyBankTile extends TileBCore implements ITickableTileEntity, INamedContainerProvider, IRSSwitchable, IFireplaceInventory {
    public static final ContainerSlotLayout.LayoutFactory<TTMPiggyBankTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
    public TileItemStackHandler itemHandler = new TileItemStackHandler(63);
    public final ManagedBool isfull = register(new ManagedBool("is_full", false, SAVE_BOTH_SYNC_TILE));

    public TTMPiggyBankTile() {
        super(TTMContent.PIGGYBANK_TILE.get());
        //Exposed item capability by also wrapping it in a handler that lets us control IO
        capManager.set(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new ItemHandlerIOControl(itemHandler).setInsertCheck((slot, stack) -> slot > 0), UP, null);
        //Install item handler capability but don't expose it to other tiles.
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
        //Set up slot input validation.
        itemHandler.setStackValidator((slot, stack) -> slot >= 0 && isInput(stack));
        //Add inventory change listener
        itemHandler.setContentsChangeListener(i -> inventoryChange());
    }

    private boolean isInput(ItemStack stack) {
        Item coin = stack.getItem();
        return coin.is(TTMTags.items.COINS);
    }

    private void inventoryChange() {

    }

    @Override
    public void tick() {
        super.tick();
        for (int i = 0; i <= 62; i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(BlockTTMPiggyBank.FULL, isfull.get()));
            }
        }
    }

    @Override
    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new ContainerBCTile<>(TTMContent.PIGGYBANK_CONTAINER, id, playerInventory, this, SLOT_LAYOUT);
    }
}
