package com.greatorator.tolkienmobs.objects.blocks;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.BlockInit;
import com.greatorator.tolkienmobs.init.ItemInit;
import com.greatorator.tolkienmobs.objects.blocks.item.ItemBlockBase;
import com.greatorator.tolkienmobs.util.Reference;
import com.greatorator.tolkienmobs.util.interfaces.IHasModel;
import com.greatorator.tolkienmobs.util.interfaces.IMetaName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLogs extends BlockLog implements IHasModel, IMetaName {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLogs(String name) {
        setUnlocalizedName(Reference.MODID + ":" + name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN).withProperty(LOG_AXIS, EnumAxis.Y));
        setCreativeTab(TolkienMobs.TTMOBS);

        //This is not an idea solution. Things get harder when you dont use the base block.
        BlockInit.BLOCKS.add(this);
        ItemBlockBase item = new ItemBlockBase(this);
        item.setRegistryName(this.getRegistryName());
        for (EnumType type : EnumType.values()) {
            item.addName(type.meta, "log_" + type.name);
        }
        ItemInit.ITEMS.add(item);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType customblockplanks$enumtype : EnumType.values()) {
            items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata((meta & 1) % 2));

        switch (meta & 6) {
            case 0:
                state = state.withProperty(LOG_AXIS, EnumAxis.Y);
                break;

            case 2:
                state = state.withProperty(LOG_AXIS, EnumAxis.X);
                break;

            case 4:
                state = state.withProperty(LOG_AXIS, EnumAxis.Z);
                break;

            default:
                state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return state;
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();

        switch (state.getValue(LOG_AXIS)) {
            case X:
                i |= 2;
                break;

            case Y:
                i |= 4;
                break;

            case Z:
                i |= 6;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT, LOG_AXIS);
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

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis())).withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public void registerModels() {
        for (EnumType type : EnumType.values()) {
            TolkienMobs.proxy.registerItemRenderer(Item.getItemFromBlock(this), type.meta, "log", "axis=y,variant=" + type.name);
        }
    }

    public enum EnumType implements IStringSerializable {
        MALLORN(0, "mallorn"),
        MIRKWOOD(1, "mirkwood");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        public final int meta;
        public final String name;

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
