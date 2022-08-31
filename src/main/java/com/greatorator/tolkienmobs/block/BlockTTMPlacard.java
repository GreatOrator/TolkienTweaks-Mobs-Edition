//package com.greatorator.tolkienmobs.block;
//
//import net.minecraft.block.WallSignBlock;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.math.AxisAlignedBB;
//
//import java.util.Arrays;
//
//public class BlockTTMPlacard extends WallSignBlock {
//    public static final AxisAlignedBB BLOCK_SIGN_Z = new AxisAlignedBB(0.45D, 0.0D, 0D, 0.55D, 0.5D, 1D);
//    public static final AxisAlignedBB BLOCK_SIGN_X = new AxisAlignedBB(0D, 0.0D, 0.45D, 1D, 0.5D, 0.55D);
//    public static final AxisAlignedBB SIGN_EAST_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 0.125D, 0.78125D, 1.0D);
//    public static final AxisAlignedBB SIGN_WEST_AABB = new AxisAlignedBB(0.875D, 0.28125D, 0.0D, 1.0D, 0.78125D, 1.0D);
//    public static final AxisAlignedBB SIGN_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.0D, 1.0D, 0.78125D, 0.125D);
//    public static final AxisAlignedBB SIGN_NORTH_AABB = new AxisAlignedBB(0.0D, 0.28125D, 0.875D, 1.0D, 0.78125D, 1.0D);
//
//    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("type", EnumType.class);
//    public static final PropertyDirection FACING = PropertyDirection.create("facing", Arrays.asList(EnumFacing.HORIZONTALS));
//    public static final PropertyBool WALL = PropertyBool.create("wall");
//
//    public BlockTTMPlacard(Properties properties) {
//        super(properties);
//        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.EMPTY).withProperty(FACING, EnumFacing.NORTH).withProperty(WALL, false));
//        this.setHarvestLevel("axe", 1);
//        this.setHasSubItemTypes(true);
//    }
//
//    @Override
//    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
//        for (EnumType type : EnumType.values()) {
//            items.add(new ItemStack(this, 1, type.meta));
//        }
//    }
//
//    //region BlockState
//
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        return getDefaultState();
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return 0;
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, VARIANT, FACING, WALL);
//    }
//
//    @Override
//    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
//        TileEntity tile = world.getTileEntity(pos);
//        if (tile instanceof TileSign) {
//            return state.withProperty(WALL, ((TileSign) tile).WALL.value).withProperty(FACING, ((TileSign) tile).ROTATION.value).withProperty(VARIANT, ((TileSign) tile).TYPE.value);
//        }
//        return super.getActualState(state, world, pos);
//    }
//
//    //endregion
//
//    //region render
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void registerRenderer(Feature feature) {
//        for (EnumType type : EnumType.values()) {
//            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), type.meta, new ModelResourceLocation(feature.getRegistryName(), "type=" + type.name));
//        }
//    }
//
//    @Override
//    public boolean registerNormal(Feature feature) {
//        return false;
//    }
//
//    @Override
//    public boolean uberIsBlockFullCube() {
//        return false;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public BlockRenderLayer getBlockLayer() {
//        return BlockRenderLayer.CUTOUT_MIPPED;
//    }
//
//    //endregion
//
//    @Nullable
//    @Override
//    public TileEntity createNewTileEntity(World worldIn, int meta) {
//        return new TileSign();
//    }
//
//    public void harvestBlock(World world, PlayerEntity player, BlockPos pos, IBlockState state, TileEntity te, ItemStack heldStack) {
//        if (te instanceof TileSign) {
//            ItemStack stack = new ItemStack(this, 1, ((TileSign) te).TYPE.value.getMeta());
//            spawnAsEntity(world, pos, stack);
//            world.removeTileEntity(pos);
//        }
//        else {
//            super.harvestBlock(world, player, pos, state, te, heldStack);
//        }
//    }
//
//    @Override
//    public boolean hasSubItemTypes() {
//        return true;
//    }
//
//    @Override
//    public Map<Integer, String> getNameOverrides() {
//        return EnumType.SIGN_TYPE_LOOKUP;
//    }
//
//    public enum EnumType implements IStringSerializable {
//        EMPTY(0, "empty"),
//        ARCANE1(1, "arcane1"),
//        ARCANE2(2, "arcane2"),
//        ARCANE3(3, "arcane3"),
//        ARMORY(4, "armory"),
//        BANK(5, "bank"),
//        BARRACKS1(6, "barracks1"),
//        BARRACKS2(7, "barracks2"),
//        BLACKSMITH(8, "blacksmith"),
//        BOOKS(9, "books"),
//        BUTCHER1(10, "butcher1"),
//        FARMING(11, "farming"),
//        GARDEN(12, "garden"),
//        GROCER(13, "grocer"),
//        INN(14, "inn"),
//        MAGICAL(15, "magical_plants"),
//        POTIONS(16, "potions"),
//        PUB(17, "pub"),
//        STABLE(18, "stable"),
//        LUMBER(19, "lumberjack"),
//        POSTMAN(20, "postman");
//
//        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
//        public static final Map<Integer, String> SIGN_TYPE_LOOKUP = new LinkedHashMap<>();
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
//                SIGN_TYPE_LOOKUP.put(type.meta, "sign_" + type.name);
//            }
//        }
//    }
//
//    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
//        TileEntity te = source.getTileEntity(pos);
//        if (te instanceof TileSign) {
//            if (!((TileSign) te).WALL.value) {
//                return ((TileSign) te).ROTATION.value.getAxis() != EnumFacing.Axis.X ? BLOCK_SIGN_X : BLOCK_SIGN_Z;
//            }
//
//            switch (((TileSign) te).ROTATION.value.getOpposite()) {
//                case NORTH:
//                default:
//                    return SIGN_NORTH_AABB;
//                case SOUTH:
//                    return SIGN_SOUTH_AABB;
//                case WEST:
//                    return SIGN_WEST_AABB;
//                case EAST:
//                    return SIGN_EAST_AABB;
//            }
//        }
//        return FULL_BLOCK_AABB;
//    }
//
//    @Nullable
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
//        return NULL_AABB;
//    }
//
//    @Override
//    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, PlayerEntity player) {
//        TileEntity te = world.getTileEntity(pos);
//        if (te instanceof TileSign) {
//            return new ItemStack(this, 1, ((TileSign) te).TYPE.value.getMeta());
//        }
//        else {
//            return super.getPickBlock(state, target, world, pos, player);
//        }
//    }
//
//}