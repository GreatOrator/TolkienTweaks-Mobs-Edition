package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.tileentity.TileEntityType;

public class KeyStoneTile extends TileBCore {
    public KeyStoneTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public KeyStoneTile() {
        super(TTMContent.KEY_STONE_TILE.get());
    }

}
