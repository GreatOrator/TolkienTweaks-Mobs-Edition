package com.greatorator.tolkienmobs.lib;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.function.Predicate;

/**
 * Created by brandon3055 on 07/09/2022
 */
public class TileFluidHandler<T extends INBTSerializable<CompoundTag>> extends FluidTank implements INBTSerializable<CompoundTag> {
    private T capability;

    public TileFluidHandler(int capacity) {
        super(capacity);
    }

    public TileFluidHandler(int capacity, Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    public TileFluidHandler(int capacity, T capability) {
        super(capacity);
        this.capability = capability;
    }

    @Override
    public CompoundTag serializeNBT() {
        return capability.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        readFromNBT(nbt);
    }
}
