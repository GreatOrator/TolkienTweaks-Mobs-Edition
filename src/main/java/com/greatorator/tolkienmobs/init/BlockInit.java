package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_DAEMONIUM = new BlockBase("block_daemonium", Material.IRON);
}
