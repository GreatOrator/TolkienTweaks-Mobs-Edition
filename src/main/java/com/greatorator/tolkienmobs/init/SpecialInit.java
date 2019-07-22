package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.block.fluidBlock.BlockFluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SpecialInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final List<Item> ITEMS = new ArrayList<Item>();

    /* Fluids */
    public static final Block ENCHANTED_WATER_BLOCK = new BlockFluids("enchanted_water", FluidInit.ENCHANTED_WATER, Material.WATER);
}
