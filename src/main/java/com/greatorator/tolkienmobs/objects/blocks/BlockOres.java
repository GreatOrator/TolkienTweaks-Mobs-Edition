package com.greatorator.tolkienmobs.objects.blocks;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockOres extends BlockBase implements IHasModel {
    public static final PropertyEnum<EnumType> ORE_TYPE = PropertyEnum.create("type", EnumType.class);

    public BlockOres(String name) {
        super(name, Material.ROCK);
        this.setHarvestLevel("pickaxe", 1);
        setDefaultState(this.blockState.getBaseState().withProperty(ORE_TYPE, EnumType.DAEMONIUM));

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
        return new ItemStack(this, 1, world.getBlockState(pos).getValue(ORE_TYPE).getMeta());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    //endregion

    @Override
    public void registerModels() {
        for (EnumType type : EnumType.values()) {
            addName(type.meta, "ore_" + type.name);
            TolkienMobs.proxy.registerItemRenderer(Item.getItemFromBlock(this), type.meta, "type=" + type.name);
        }
    }

    //This does not need to be off in its own helper class. It belongs to this block so it should be in this block's class
    public enum EnumType implements IStringSerializable {
        DAEMONIUM(0, "daemonium"),
        ALUMINIUM(1, "aluminium"),
        NETHER_DAEMONIUM(2, "nether_daemonium"),
        NETHER_ALUMINIUM(3, "nether_aluminium"),
        ENDER_DAEMONIUM(4, "ender_daemonium"),
        ENDER_ALUMINIUM(5, "ender_aluminium");

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