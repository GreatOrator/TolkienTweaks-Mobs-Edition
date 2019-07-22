package com.greatorator.tolkienmobs.init;

import com.google.common.collect.ImmutableSet;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.BlockFluids;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Set;

public class FluidInit {
    public static final BlockFluids ENCHANTED_WATER = (BlockFluids) new BlockFluids(
            "enchanted_water",
            new ResourceLocation(TolkienMobs.MODID,"fluids/enchanted_water_still"),
            new ResourceLocation(TolkienMobs.MODID, "fluids/enchanted_water_flow")
    )
            .setHasBucket(true)
            .setDensity(1100)
            .setGaseous(false)
            .setLuminosity(9)
            .setViscosity(25000)
            .setTemperature(300);

    public static final Set<BlockFluids> SET_FLUIDS = ImmutableSet.of(
            ENCHANTED_WATER);


    public static void registerFluids()
    {
        // DEBUG
        System.out.println("Registering fluids");
        for (final BlockFluids fluid : SET_FLUIDS)
        {
            FluidRegistry.registerFluid(fluid);
            if (fluid.isBucketEnabled())
            {
                FluidRegistry.addBucketForFluid(fluid);
            }
            // DEBUG
            System.out.println("Registering fluid: " + fluid.getName()+" with bucket = "+fluid.isBucketEnabled());
        }
    }
}
