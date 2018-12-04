package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
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

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockLogs extends BlockLog implements IBCoreBlock {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

    public BlockLogs() {
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MALLORN).withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumType customblockplanks$enumtype : EnumType.values()) {
            items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();

        switch (state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
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

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis())).withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return EnumType.LOG_NAME_LOOKUP;
    }

    public enum EnumType implements IStringSerializable {
        MALLORN(0, "mallorn"),
        MIRKWOOD(1, "mirkwood"),
        CULUMALDA(2, "culumalda"),
        LEBETHRON(3, "lebethron");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        public static final Map<Integer, String> LOG_NAME_LOOKUP = new LinkedHashMap<>();
        public static final Map<Integer, String> LEAF_NAME_LOOKUP = new LinkedHashMap<>();
        public static final Map<Integer, String> SAPLING_NAME_LOOKUP = new LinkedHashMap<>();

        EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public final int meta;
        public final String name;

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
                LOG_NAME_LOOKUP.put(type.meta, "log_" + type.name);
                LEAF_NAME_LOOKUP.put(type.meta, "leaves_" + type.name);
                SAPLING_NAME_LOOKUP.put(type.meta, "sapling_" + type.name);
            }
        }
    }
}
