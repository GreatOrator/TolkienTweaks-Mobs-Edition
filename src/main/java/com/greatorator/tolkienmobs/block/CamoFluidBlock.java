package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
import net.minecraftforge.fluids.FluidStack;

public class CamoFluidBlock extends ChameleonBlock<CamoFluidTile>{
    private FluidStack fluidStack = null;

    public CamoFluidBlock(Properties properties) {
        super(properties);
    }
}
