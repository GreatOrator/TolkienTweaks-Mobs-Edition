package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.lib.IBCoreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockTMMushroom extends BlockBush implements IBCoreBlock
{
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);

    public BlockTMMushroom()
    {
        setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockTMMushroom.EnumType.DECAY_BLOOM));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
    }

    //Variants
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(BlockTMMushroom.EnumType type : BlockTMMushroom.EnumType.values())
        {
            items.add(new ItemStack(this, 1, type.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockTMMushroom.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(VARIANT).getMeta();
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.MYCELIUM || state.getBlock() == Blocks.SOUL_SAND;
    }

    @Override
    public boolean hasSubItemTypes() {
        return true;
    }

    @Override
    public Map<Integer, String> getNameOverrides() {
        return BlockTMMushroom.EnumType.MUSHROOM_NAME_LOOKUP;
    }

    public enum EnumType implements IStringSerializable {
        DECAY_BLOOM(0,"decay_bloom"),
        BLOOM_DECAY(1,"bloom_decay");

        private static final BlockTMMushroom.EnumType[] META_LOOKUP = new BlockTMMushroom.EnumType[values().length];
        public static final Map<Integer, String> MUSHROOM_NAME_LOOKUP = new LinkedHashMap<>();

        EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public final int meta;
        public final String name;

        public int getMeta() {
            return meta;
        }

        public static BlockTMMushroom.EnumType byMetadata(int meta) {
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
            for (BlockTMMushroom.EnumType type : values()) {
                META_LOOKUP[type.getMeta()] = type;
                MUSHROOM_NAME_LOOKUP.put(type.meta, "mushroom_" + type.name);
            }
        }
    }

    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
}