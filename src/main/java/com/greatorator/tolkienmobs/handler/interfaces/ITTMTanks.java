package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public interface ITTMTanks {
    FluidTank getWaterTank();

    void saveTank(CompoundNBT compound);

    void loadTank(CompoundNBT compound);

    boolean updateTankSlots();
}
