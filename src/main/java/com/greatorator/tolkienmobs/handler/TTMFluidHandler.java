package com.greatorator.tolkienmobs.handler;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TTMFluidHandler extends Fluid
{
    public TTMFluidHandler(String name, ResourceLocation still, ResourceLocation flow)
    {
        super(name, still, flow);
        this.setUnlocalizedName(name);
    }
}
