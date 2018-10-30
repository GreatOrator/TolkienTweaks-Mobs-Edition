package com.greatorator.tolkienmobs.objects.blocks;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BlockInit;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.objects.blocks.item.ItemBlockBase;
import com.greatorator.tolkienmobs.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import java.util.HashMap;
import java.util.Map;

public class BlockBase extends Block {

    private ItemBlock item;
    public Map<Integer, String> nameOverrides = new HashMap<>();

    public BlockBase(String name) {
        this(name, Material.ROCK);
    }

    @SuppressWarnings("ConstantConditions")
    public BlockBase(String name, Material material) {
        super(material);
        this.setHardness(1.5F);   //Set default values that can be overridden if needed
        this.setResistance(10F);

        setRegistryName(name);
        setCreativeTab(TolkienMobs.TTMOBS);
        setUnlocalizedName(Reference.MODID + ":" + name);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(item = (ItemBlock) new ItemBlockBase(this).setRegistryName(this.getRegistryName()));
    }

    /**
     * Adds a name mapping for the given metadata.
     * The overridden unlocalized name will be as follows.<br>
     * tile.[modid]:[nameAddedByThisMethod].name<br>
     * This also sets hasSubTypes to true.
     */
    public BlockBase addName(int meta, String name) {
        nameOverrides.put(meta, name);
        item.setHasSubtypes(true);
        return this;
    }
}