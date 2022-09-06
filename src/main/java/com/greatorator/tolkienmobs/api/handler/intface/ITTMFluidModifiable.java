package com.greatorator.tolkienmobs.api.handler.intface;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public interface ITTMFluidModifiable extends IFluidHandler {
    void setFluidInTank(int tank, @Nonnull FluidStack stack);
}
