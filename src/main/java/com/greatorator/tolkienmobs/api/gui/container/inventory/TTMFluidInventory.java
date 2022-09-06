package com.greatorator.tolkienmobs.api.gui.container.inventory;

import com.greatorator.tolkienmobs.api.handler.TTMFluidHandler;
import com.greatorator.tolkienmobs.api.handler.TTMFluidMultiHandler;
import com.greatorator.tolkienmobs.api.handler.TTMFluidTank;
import com.greatorator.tolkienmobs.api.handler.TTMGroupedFluidHandler;
import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import com.greatorator.tolkienmobs.api.handler.intface.TTMGroupTank;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.fluids.FluidStack.EMPTY;

public class TTMFluidInventory extends TTMFluidHandler {
    protected List<TTMFluidTank> inputTanks = new ArrayList<>();
    protected List<TTMFluidTank> outputTanks = new ArrayList<>();
    protected List<TTMFluidTank> fuelTanks = new ArrayList<>();

    protected IFluidHandler handler;

    public TTMFluidInventory(ITTMInventoryCallback tile) {
        super(tile);
    }

    public TTMFluidInventory(@Nullable ITTMInventoryCallback tile, @Nonnull List<TTMFluidTank> tanks) {
        super(tile, tanks);
    }

    public void addTank(TTMGroupTank group, TTMFluidTank tank) {
        if (handler != null) {
            return;
        }

        tanks.add(tank);
        switch (group) {
            case INPUT:
                inputTanks.add(tank);
                break;
            case OUTPUT:
                outputTanks.add(tank);
                break;
            case FUEL:
                fuelTanks.add(tank);
                break;
            default:
        }
    }

    public void addTanks(TTMGroupTank group, int amount, int capacity) {
        for (int i = 0; i < amount; ++i) {
            addTank(group, new TTMFluidTank(capacity));
        }
    }

    public void addTanks(TTMGroupTank group, List<TTMFluidTank> tanks) {
        for (TTMFluidTank tank : tanks) {
            addTank(group, tank);
        }
    }

    protected void optimize() {
        ((ArrayList<TTMFluidTank>) tanks).trimToSize();
        ((ArrayList<TTMFluidTank>) inputTanks).trimToSize();
        ((ArrayList<TTMFluidTank>) outputTanks).trimToSize();
    }

    public void initHandler() {
        optimize();

        handler = new TTMFluidMultiHandler(
                new TTMGroupedFluidHandler(TTMGroupTank.INPUT, tile, inputTanks),
                new TTMGroupedFluidHandler(TTMGroupTank.OUTPUT, tile, outputTanks)
        );
    }

    @Nonnull
    public FluidStack get(int tank) {
        return tanks.get(tank).getFluidStack();
    }

    public void set(int tank, @Nonnull FluidStack stack) {
        tanks.get(tank).setFluidStack(stack);
    }

    public void clear() {
        for (TTMFluidTank tank : tanks) {
            tank.setFluidStack(EMPTY);
        }
    }

    public boolean hasInputTanks() {
        return inputTanks.size() > 0;
    }

    public boolean hasOutputTanks() {
        return outputTanks.size() > 0;
    }

    public boolean hasFuelTanks() {
        return fuelTanks.size() > 0;
    }

    public boolean hasAccessibleTanks() {
        return hasInputTanks() || hasOutputTanks() || hasFuelTanks();
    }

    public TTMFluidTank getTank(int tank) {
        return tanks.get(tank);
    }

    public List<TTMFluidTank> getInputTanks() {
        return inputTanks;
    }

    public List<TTMFluidTank> getOutputTanks() {
        return outputTanks;
    }

    public List<TTMFluidTank> getFuelTanks() {
        return fuelTanks;
    }

    public TTMFluidInventory getInputInventory() {
        return new TTMFluidInventory(tile, getInputTanks());
    }

    public TTMFluidInventory getOutputInventory() {
        return new TTMFluidInventory(tile, getOutputTanks());
    }

    public TTMFluidInventory getFuelInventory() {
        return new TTMFluidInventory(tile, getFuelTanks());
    }

    public IFluidHandler getHandler() {
        if (handler == null) {
            initHandler();
        }
        return handler;
    }

    public TTMFluidInventory read(CompoundNBT tag) {
        for (TTMFluidTank tank : tanks) {
            tank.setFluidStack(EMPTY);
        }
        ListNBT list = tag.getList("TankInventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.size(); ++i) {
            CompoundNBT tankTag = list.getCompound(i);
            int tank = tankTag.getByte("Tank");
            if (tank >= 0 && tank < tanks.size()) {
                tanks.get(tank).read(tankTag);
            }
        }
        return this;
    }

    public CompoundNBT write(CompoundNBT tag) {
        if (tanks.size() <= 0) {
            return tag;
        }
        ListNBT list = new ListNBT();
        for (int i = 0; i < tanks.size(); ++i) {
            if (!tanks.get(i).isEmpty()) {
                CompoundNBT tankTag = new CompoundNBT();
                tankTag.putByte("Tank", (byte) i);
                tanks.get(i).write(tankTag);
                list.add(tankTag);
            }
        }
        if (!list.isEmpty()) {
            tag.put("TankInventory", list);
        }
        return tag;
    }

    public int getContainerSize() {
        return tanks.size();
    }

    @Nonnull
    public FluidStack getFluid(int tank) {
        return get(tank);
    }

    @Nonnull
    public FluidStack removeFluid(int tank, int amount) {
        return tanks.get(tank).drain(amount, FluidAction.EXECUTE);
    }

    @Nonnull
    public FluidStack removeFluidNoUpdate(int tank) {
        tanks.get(tank).setFluidStack(EMPTY);
        return EMPTY;
    }

    public void setChanged() {
    }

    public boolean stillValid(@Nonnull PlayerEntity player) {
        return true;
    }

    public void clearContent() {
        clear();
    }
}
