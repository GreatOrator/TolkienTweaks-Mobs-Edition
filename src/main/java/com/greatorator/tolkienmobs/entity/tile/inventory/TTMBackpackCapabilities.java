package com.greatorator.tolkienmobs.entity.tile.inventory;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.SerializableCapabilityProvider;
import com.greatorator.tolkienmobs.handler.TTMWearable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TTMBackpackCapabilities {
    @CapabilityInject(ITTMBackpack.class)
    public static final Capability<ITTMBackpack> TRAVELERS_BACKPACK_CAPABILITY = null;

    public static final Direction DEFAULT_FACING = null;

    public static final ResourceLocation ID = new ResourceLocation(TolkienMobs.MODID, "travelers_backpack");

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ITTMBackpack.class, new Capability.IStorage<ITTMBackpack>()
        {
            @Override
            public INBT writeNBT(final Capability<ITTMBackpack> capability, final ITTMBackpack instance, final Direction side)
            {
                CompoundNBT compound = new CompoundNBT();

                if(instance.hasWearable())
                {
                    ItemStack wearable = instance.getWearable();
                    wearable.save(compound);
                }
                if(!instance.hasWearable())
                {
                    ItemStack wearable = ItemStack.EMPTY;
                    wearable.save(compound);
                }
                return compound;
            }

            @Override
            public void readNBT(final Capability<ITTMBackpack> capability, final ITTMBackpack instance, final Direction side, final INBT nbt)
            {
                CompoundNBT stackCompound = (CompoundNBT)nbt;
                ItemStack wearable = ItemStack.of(stackCompound);

                instance.setWearable(wearable);
                instance.setContents(wearable);
            }
        }, () -> new TTMWearable(null));
    }

    public static ICapabilityProvider createProvider(final ITTMBackpack backpack)
    {
        return new SerializableCapabilityProvider<>(TRAVELERS_BACKPACK_CAPABILITY, DEFAULT_FACING, backpack);
    }

}
