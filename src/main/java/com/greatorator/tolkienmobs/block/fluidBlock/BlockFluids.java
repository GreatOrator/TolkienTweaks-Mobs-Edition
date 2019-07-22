package com.greatorator.tolkienmobs.block.fluidBlock;

import com.greatorator.tolkienmobs.init.SpecialInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluids extends BlockFluidClassic {
    public BlockFluids(String name, Fluid fluid, Material material)
    {
        super(fluid, material);
        setUnlocalizedName(name);
        setRegistryName(name);

        SpecialInit.BLOCKS.add(this);
        SpecialInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}