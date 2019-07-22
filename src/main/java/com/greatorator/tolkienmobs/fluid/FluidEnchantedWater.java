package com.greatorator.tolkienmobs.fluid;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidEnchantedWater extends Fluid {
    public FluidEnchantedWater() {
        super("enchanted_water", new ResourceLocation(TolkienMobs.MODID, "fluids/enchanted_water_still"), new ResourceLocation(TolkienMobs.MODID, "fluids/enchanted_water_flow"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}
