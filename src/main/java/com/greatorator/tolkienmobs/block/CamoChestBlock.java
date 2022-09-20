package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoChestTile;
import net.minecraft.block.BlockState;

public class CamoChestBlock extends ChameleonBlock<CamoChestTile> {
    public CamoChestBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
