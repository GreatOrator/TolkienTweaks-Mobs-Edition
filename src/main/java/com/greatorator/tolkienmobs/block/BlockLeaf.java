package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.greatorator.tolkienmobs.block.BlockLogs.EnumType;

public class BlockLeaf extends BlockLeaves implements IBCoreBlock {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLeaf() {
        setSoundType(SoundType.PLANT);
        leavesFancy = true;
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta % 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(VARIANT).getMeta();

        if (!state.getValue(DECAYABLE)) {
            i |= 2;
        }

        if (!state.getValue(CHECK_DECAY)) {
            i |= 4;
        }

        return i;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType type : EnumType.values()) {
            items.add(new ItemStack(this, 1, type.getMeta()));
        }
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT).getMeta());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(TTMFeatures.SAPLINGS);
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
    }

    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return 30;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT, DECAYABLE, CHECK_DECAY);
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return EnumType.LEAF_NAME_LOOKUP;
    }
}