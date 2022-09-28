package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
import net.minecraft.block.BlockState;

public class CamoFluidBlock extends ChameleonBlock<CamoFluidTile> {
    public CamoFluidBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}