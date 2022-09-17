package com.greatorator.tolkienmobs.entity.tile;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class KeyStoneTile extends TileEntity {
    public KeyStoneTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public KeyStoneTile() {
        super(TTMContent.KEY_STONE_TILE.get());
    }

}
