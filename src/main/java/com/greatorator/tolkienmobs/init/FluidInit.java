package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.block.BlockFluids;
import com.greatorator.tolkienmobs.fluid.FluidEnchantedWater;
import net.minecraft.block.material.Material;

public class FluidInit {
    public static FluidEnchantedWater fluidEnchantedWater;
    public static BlockFluids blockEnchantedWater;

    public static void registerFluids() {
        fluidEnchantedWater = new FluidEnchantedWater();
        blockEnchantedWater = new BlockFluids("enchanted_water", fluidEnchantedWater, Material.WATER);
    }

    public static void renderFluids() {
        blockEnchantedWater.render();
    }
}
