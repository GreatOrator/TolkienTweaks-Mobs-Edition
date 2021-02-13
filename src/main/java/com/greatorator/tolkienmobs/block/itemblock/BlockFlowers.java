//package com.greatorator.tolkienmobs.block;
//
//import com.brandon3055.brandonscore.lib.IBCoreBlock;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockBush;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.properties.PropertyEnum;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.init.Blocks;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.IStringSerializable;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IBlockAccess;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class BlockFlowers extends BlockBush implements IBCoreBlock {
//
//    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);
//
//    public BlockFlowers()
//    {
//        setSoundType(SoundType.PLANT);
//        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.MIRKWOOD));
//    }
//
//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
//    {
//        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
//    }
//
//    @Override
//    public boolean isOpaqueCube(IBlockState state)
//    {
//        return false;
//    }
//
//    @Override
//    public boolean isFullCube(IBlockState state)
//    {
//        return false;
//    }
//
//    @Override
//    public int damageDropped(IBlockState state)
//    {
//        return state.getValue(VARIANT).getMeta();
//    }
//
//    //Variants
//    @Override
//    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
//    {
//        for(EnumType type : EnumType.values())
//        {
//            items.add(new ItemStack(this, 1, type.getMeta()));
//        }
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta)
//    {
//        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state)
//    {
//        int i = 0;
//        i = i | state.getValue(VARIANT).getMeta();
//        return i;
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState()
//    {
//        return new BlockStateContainer(this, VARIANT);
//    }
//
//    @Override
//    protected boolean canSustainBush(IBlockState state)
//    {
//        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
//    }
//
//    @Override
//    public boolean hasSubItemTypes() {
//        return true;
//    }
//
//    @Override
//    public Map<Integer, String> getNameOverrides() {
//        return EnumType.FLOWER_NAME_LOOKUP;
//    }
//
//    public enum EnumType implements IStringSerializable {
//        SIMBELMYNE(0,"simbelmyne"),
//        MIRKWOOD(1, "mirkwood"),
//        ALFIRIN(2, "alfirin"),
//        ATHELAS(3, "athelas"),
//        NIPHREDIL(4, "niphredil"),
//        SWAMPMILKWEED(5, "swamp_milkweed"),
//        LILLYOFTHEVALLEY(6, "valley_lilly");
//
//        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
//        public static final Map<Integer, String> FLOWER_NAME_LOOKUP = new LinkedHashMap<>();
//
//        EnumType(int meta, String name) {
//            this.meta = meta;
//            this.name = name;
//        }
//
//        public final int meta;
//        public final String name;
//
//        public int getMeta() {
//            return meta;
//        }
//
//        public static EnumType byMetadata(int meta) {
//            if (meta < 0 || meta >= META_LOOKUP.length) {
//                meta = 0;
//            }
//
//            return META_LOOKUP[meta];
//        }
//
//        @Override
//        public String getName() {
//            return this.name;
//        }
//
//        static {
//            for (EnumType type : values()) {
//                META_LOOKUP[type.getMeta()] = type;
//                FLOWER_NAME_LOOKUP.put(type.meta, "flower_" + type.name);
//            }
//        }
//    }
//
//    public Block.EnumOffsetType getOffsetType()
//    {
//        return Block.EnumOffsetType.XZ;
//    }
//}