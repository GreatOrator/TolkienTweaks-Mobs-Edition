package com.greatorator.tolkienmobs.lib;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.function.Predicate;

/**
 * Created by brandon3055 on 07/09/2022
 */
@Deprecated //This will be added to BCore at some point
public class TileFluidHandler extends FluidTank implements INBTSerializable<CompoundNBT> {

    public TileFluidHandler(int capacity) {
        super(capacity);
    }

    public TileFluidHandler(int capacity, Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return writeToNBT(new CompoundNBT());
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        readFromNBT(nbt);
    }
}
