package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.objects.blocks.BlockBase;
import com.greatorator.tolkienmobs.objects.blocks.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_DAEMONIUM = new BlockBase("block_daemonium", Material.IRON);

    public static final Block ORE_END = new BlockOres("ore_end", "end");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
}
