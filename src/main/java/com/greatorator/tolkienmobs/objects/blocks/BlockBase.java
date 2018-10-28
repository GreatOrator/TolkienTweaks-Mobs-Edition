package com.greatorator.tolkienmobs.objects.blocks;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BlockInit;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {
    public BlockBase(String name, Material material) {
        super(material);
        setUnlocalizedName(Reference.MODID + ":" + name);
        setRegistryName(name);
        setCreativeTab(TolkienMobs.TTMOBS);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}