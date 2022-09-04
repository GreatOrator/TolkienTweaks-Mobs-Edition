package com.greatorator.tolkienmobs.client.gui.container.slots;

import com.greatorator.tolkienmobs.client.gui.container.ContainerTTMBackpack;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMBackpackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.SlotItemHandler;

public class SlotTTMFluid extends SlotItemHandler {
    private final int index;
    private final ITTMBackpackInventory inventory;

    public SlotTTMFluid(ITTMBackpackInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory.getInventory(), index, xPosition, yPosition);
        this.index = index;
        this.inventory = inventory;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        LazyOptional<IFluidHandlerItem> container = FluidUtil.getFluidHandler(stack);

        if(index == ContainerTTMBackpack.getFluidOutput())
        {
            return false;
        }

        if(stack.getItem() == Items.BUCKET || stack.getItem() == Items.GLASS_BOTTLE)
        {
            return true;
        }

        return container.isPresent();
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        inventory.updateTankSlots();
    }
}
