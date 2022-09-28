package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoSpawnerTile;
import net.minecraft.block.BlockState;

public class CamoSpawnerBlock extends ChameleonBlock<CamoSpawnerTile> {

    public CamoSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}