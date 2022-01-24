package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.brandon3055.brandonscore.lib.IRSSwitchable;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.crafting.IFireplaceInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class TTMPiggyBankTile extends TileBCore implements ITickableTileEntity, INamedContainerProvider, IRSSwitchable, IFireplaceInventory {
    public TileItemStackHandler itemHandler = new TileItemStackHandler(4);

    public TTMPiggyBankTile() {
        super(TTMContent.PIGGYBANK_TILE.get());
        //Install item handler capability but don't expose it to other tiles.
        capManager.setInternalManaged("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler).saveBoth().syncTile();
    }

    @Override
    public IItemHandler getItemHandler() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }
}
