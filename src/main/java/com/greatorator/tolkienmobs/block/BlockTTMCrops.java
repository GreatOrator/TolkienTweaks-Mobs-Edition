package com.greatorator.tolkienmobs.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockTTMCrops extends CropsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public BlockTTMCrops(Properties builder) {
        super(builder);
        this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(0));
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof FarmlandBlock;
    }

    protected IItemProvider getSeedsItem() {
        return this.asItem();
    }


    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }
}
