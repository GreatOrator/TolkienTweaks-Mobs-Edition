package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.handler.interfaces.ITTMBackpackInventory;
import com.greatorator.tolkienmobs.utils.TTMReference;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TTMSlotManager {
    protected final ITTMBackpackInventory inventory;
    protected List<Integer> unsortableSlots = new ArrayList<>();
    protected boolean isActive = false;

    private final String UNSORTABLE_SLOTS = "UnsortableSlots";

    public TTMSlotManager(ITTMBackpackInventory inventory)
    {
        this.inventory = inventory;
    }

    public List<Integer> getUnsortableSlots()
    {
        return this.unsortableSlots;
    }

    public boolean hasSlot(int slot)
    {
        return unsortableSlots.contains(slot);
    }

    public void setUnsortableSlots(int[] slots, boolean isFinal)
    {
        if(isActive())
        {
            unsortableSlots = Arrays.stream(slots).boxed().collect(Collectors.toList());

            if(isFinal)
            {
                setChanged();
            }
        }
    }

    public void setUnsortableSlot(int slot)
    {
        if(isActive())
        {
            if(slot <= 38)
            {
                if(hasSlot(slot))
                {
                    unsortableSlots.remove((Object)slot);
                }
                else
                {
                    unsortableSlots.add(slot);
                }
            }
        }
    }

    public void setChanged()
    {
        if(inventory.getScreenID() != TTMReference.TILE_SCREEN_ID)
        {
            inventory.setDataChanged(ITTMBackpackInventory.SLOT_DATA);
        }
        else
        {
            inventory.setChanged();
        }
    }

    public void clearSlots()
    {
        unsortableSlots = new ArrayList<>();
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    public void setActive(boolean bool)
    {
        this.isActive = bool;
    }

    public void saveUnsortableSlots(CompoundNBT compound)
    {
        compound.putIntArray(UNSORTABLE_SLOTS, getUnsortableSlots().stream().mapToInt(i -> i).toArray());
    }

    public void loadUnsortableSlots(CompoundNBT compound)
    {
        this.unsortableSlots = Arrays.stream(compound.getIntArray(UNSORTABLE_SLOTS)).boxed().collect(Collectors.toList());
    }
}
