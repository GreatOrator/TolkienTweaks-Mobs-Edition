package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockOres extends BlockBCore {
    public static final PropertyEnum<EnumType> ORE_TYPE = PropertyEnum.create("type", EnumType.class);

    public BlockOres() {
        super(Material.ROCK);
        this.setHarvestLevel("pickaxe", 3);
        setDefaultState(this.blockState.getBaseState().withProperty(ORE_TYPE, EnumType.MITHRIL));

        //Thanks to BlockBase this is all you have to do to add sub types
        for (EnumType type : EnumType.values()) {
            addName(type.meta, "ore_" + type.name);
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (EnumType enumType : EnumType.values()) {
            list.add(new ItemStack(this, 1, enumType.getMeta()));
        }
    }

    //region BlockState
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ORE_TYPE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ORE_TYPE).getMeta();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(ORE_TYPE, EnumType.byMetadata(meta));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(ORE_TYPE, EnumType.byMetadata(stack.getItemDamage())));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(ORE_TYPE).getMeta());
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        int oreTypeDrop = world.getBlockState(pos).getValue(ORE_TYPE).getMeta();

        if(oreTypeDrop == 6 || oreTypeDrop == 7 || oreTypeDrop == 8) {
            return new ItemStack(TTMFeatures.GEM_AMMOLITE, 1);
        }
        else {
            return new ItemStack(this, 1, oreTypeDrop);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    //endregion


    //This does not need to be off in its own helper class. It belongs to this block so it should be in this block's class
    public enum EnumType implements IStringSerializable {
        MITHRIL(0, "mithril"),
        MORGULIRON(1, "morguliron"),
        NETHER_MITHRIL(2, "nether_mithril"),
        NETHER_MORGULIRON(3, "nether_morguliron"),
        ENDER_MITHRIL(4, "ender_mithril"),
        ENDER_MORGULIRON(5, "ender_morguliron"),
        AMMOLITE(6, "ammolite"),
        NETHER_AMMOLITE(7, "nether_ammolite"),
        ENDER_AMMOLITE(8, "ender_ammolite");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name;

        EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public int getMeta() {
            return meta;
        }

        public static EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.name;
        }

        static {
            for (EnumType type : values()) {
                META_LOOKUP[type.getMeta()] = type;
            }
        }
    }
}