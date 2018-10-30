package com.greatorator.tolkienmobs.objects.blocks;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BlockInit;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.objects.blocks.item.ItemBlockBase;
import com.greatorator.tolkienmobs.util.Reference;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import com.greatorator.tolkienmobs.util.interfaces.IMetaName;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

import static com.greatorator.tolkienmobs.objects.blocks.BlockLogs.*;

public class BlockLeaf extends BlockLeaves implements IMetaName, IHasModel {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLeaf(String name) {
        setUnlocalizedName(Reference.MODID + ":" + name);
        setRegistryName(name);
        setSoundType(SoundType.PLANT);
        leavesFancy = true;
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
        setCreativeTab(TolkienMobs.TTMOBS);

        //This is not an idea solution. Things get harder when you dont use the base block.
        BlockInit.BLOCKS.add(this);
        ItemBlockBase item = new ItemBlockBase(this);
        item.setRegistryName(this.getRegistryName());
        for (EnumType type : EnumType.values()) {
            item.addName(type.meta, "leaves_" + type.name);
        }
        ItemInit.ITEMS.add(item);
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

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumType.values()[stack.getItemDamage()].getName();
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
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void registerModels() {
        for (EnumType type : EnumType.values()) {
            TolkienMobs.proxy.registerItemRenderer(Item.getItemFromBlock(this), type.meta, "check_decay=false,decayable=false,variant=" + type.name);
        }
    }

}
