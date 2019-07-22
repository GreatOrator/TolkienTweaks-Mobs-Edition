package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.handler.TTMFluidHandler;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {

    public static final Fluid ENCHANTED_WATER = new TTMFluidHandler(TolkienMobs.MODID + ":enchanted_water", new ResourceLocation(TolkienMobs.MODID + ":blocks/fluids/enchanted_water_still"), new ResourceLocation(TolkienMobs.MODID + ":blocks/fluids/enchanted_water_flow"));

    public static void registerFluids()
    {
        registerFluid(ENCHANTED_WATER);
    }

    public static void registerFluid(Fluid fluid)
    {
        LogHelperTTM.info("May the waters of the Anduin flow forever...");

        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);

        LogHelperTTM.info("...journey to the birth of the Anduin complete!");
    }
}
