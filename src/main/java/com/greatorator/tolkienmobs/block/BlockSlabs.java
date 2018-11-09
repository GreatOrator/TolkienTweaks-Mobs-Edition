package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.BlockLogs.EnumType;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Random;

public class BlockSlabs extends BlockSlab implements IBCoreBlock {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);


    public BlockSlabs() {
        super(Material.WOOD);
        setSoundType(SoundType.WOOD);
        this.fullBlock = this.isDouble();

        IBlockState iblockstate = this.blockState.getBaseState();
        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumType.MALLORN));
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return "tile." + TolkienMobs.MODID + ":slab_" + EnumType.byMetadata(meta).name + ".name";
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return EnumType.byMetadata(stack.getMetadata() % 2);
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return EnumType.LOG_NAME_LOOKUP; //TODO add a slab name lookup
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(TTMFeatures.HALF_SLAB);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(TTMFeatures.HALF_SLAB, 1, state.getValue(VARIANT).getMeta());
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        if (!isDouble()) {
            for (EnumType type : EnumType.values()) {
                items.add(new ItemStack(this, 1, type.getMeta()));
            }
        }
    }

    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 7));

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();

        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMeta();
    }
}