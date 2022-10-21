package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class CropsBlock extends net.minecraft.block.CropsBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public CropsBlock(Properties builder) {
        super(builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(Blocks.FARMLAND);
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return TTMContent.PIPEWEED_SEEDS.get();
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }
}
