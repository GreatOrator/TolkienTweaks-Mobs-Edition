package com.greatorator.tolkienmobs.api.handler;

import com.greatorator.tolkienmobs.api.handler.intface.ITTMInventoryCallback;
import com.greatorator.tolkienmobs.api.handler.intface.TTMGroupTank;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TTMGroupedFluidHandler extends TTMFluidHandler{
    @Nonnull
    private final TTMGroupTank group;

    public TTMGroupedFluidHandler(@Nonnull TTMGroupTank group) {
        super();
        this.group = group;
    }

    public TTMGroupedFluidHandler(@Nonnull TTMGroupTank group, @Nullable ITTMInventoryCallback tile) {
        super(tile);
        this.group = group;
    }

    public TTMGroupedFluidHandler(@Nonnull TTMGroupTank group, @Nullable ITTMInventoryCallback tile, @Nonnull List<TTMFluidTank> tanks) {
        super(tile, tanks);
        this.group = group;
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (group == TTMGroupTank.OUTPUT) {
            return 0;
        }
        return super.fill(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (group == TTMGroupTank.OUTPUT) {
            return FluidStack.EMPTY;
        }
        return super.drain(resource, action);
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (group == TTMGroupTank.OUTPUT) {
            return FluidStack.EMPTY;
        }
        return super.drain(maxDrain, action);
    }
}
