package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public class CamoFluidTile extends TileBCore implements INamedContainerProvider, ITickableTileEntity {
    public CamoFluidTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public CamoFluidTile() {
        super(TTMContent.CAMO_FLUID_TILE.get());
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity playerIn) {
        return null;
    }
}
