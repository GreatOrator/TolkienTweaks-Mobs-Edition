package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.tileentity.ITickableTileEntity;

/**
 * Created by brandon3055 on 6/12/18.
 */
public class PlacardTile extends TileBCore implements ITickableTileEntity {

    public PlacardTile() {
        super(TTMContent.PLACARD_TILE.get());
    }
}