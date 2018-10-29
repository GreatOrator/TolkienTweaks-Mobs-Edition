package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_DAEMONIUM = new BlockBase("block_daemonium", Material.IRON);
    public static final Block ORE = new BlockOres("ore");

    public static final Block PLANKS = new BlockPlank("planks");
    public static final Block LOGS = new BlockLogs("log");
    public static final Block LEAVES = new BlockLeaf("leaves");
    public static final Block SAPLINGS = new BlockSaplings("sapling");
}
