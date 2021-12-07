package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.tileentity.TTMFireplaceTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Locale;

public class BlockTTMFireplace extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    protected static final VoxelShape SHAPE_NORTH = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_EAST = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_WEST = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SHAPE_COMMON = Block.box(1.0, 0.0D, 1.0, 15.0, 14.0, 15.0);

    public BlockTTMFireplace(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.getValue(FACING)) {
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case EAST:
                return SHAPE_EAST;
            case WEST:
                return SHAPE_WEST;
            default:
                return SHAPE_COMMON;
        }    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isClientSide) {
            TileEntity tileEntity = world.getBlockEntity(pos);

            if (tileEntity instanceof TTMFireplaceTile) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (TTMFireplaceTile) tileEntity, pos);
            }
        }
        return super.use(state, world, pos, player, hand, trace);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ACTIVE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TTMFireplaceTile();
    }

    public static boolean isStackValid(ItemStack stack) {
        if (stack.getItem() == Item.byBlock(TTMContent.TTMFIREPLACE.get())) {
            return false;
        }
        else if (!stack.isEmpty()) {
            String name = stack.getDescriptionId().toLowerCase(Locale.ENGLISH);
            if (name.contains("pouch") || name.contains("bag") || name.contains("strongbox") || name.contains("shulker_box")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(ACTIVE) ? 15 : 0;
    }
//
//    @Override
//    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
//        if (!worldIn.isRemote) {
//            IBlockState north = worldIn.getBlockState(pos.north());
//            IBlockState south = worldIn.getBlockState(pos.south());
//            IBlockState west = worldIn.getBlockState(pos.west());
//            IBlockState east = worldIn.getBlockState(pos.east());
//            EnumFacing face = (EnumFacing) state.getValue(FACING);
//
//            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
//            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
//            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
//            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
//            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
//        }
//    }
//
//    @Override
//    public boolean hasTileEntity(IBlockState state) {
//        return true;
//    }
//
//    @Override
//    public TileEntity createTileEntity(World world, IBlockState state) {
//        return new TileTMFireplace();
//    }
//
//    @Override
//    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer, EnumHand hand) {
//        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, LivingEntity placer, ItemStack stack) {
//        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
//    }
//
//
//    @Override
//    public IBlockState withRotation(IBlockState state, Rotation rot) {
//        return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
//    }
//
//    @Override
//    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
//        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState() {
//        return new BlockStateContainer(this, BURNING, FACING);
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta) {
//        EnumFacing facing = EnumFacing.getFront(meta);
//        if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
//        return this.getDefaultState().withProperty(FACING, facing);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state) {
//        return ((EnumFacing) state.getValue(FACING)).getIndex();
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public BlockRenderLayer getBlockLayer() {
//        return BlockRenderLayer.CUTOUT;
//    }
//
//    @Override
//    @Deprecated
//    public boolean isOpaqueCube(IBlockState state) {
//        return false;
//    }
//
//    @Override
//    @Deprecated
//    public boolean isFullCube(IBlockState state) {
//        return false;
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void registerRenderer(Feature feature) {
//        ClientRegistry.bindTileEntitySpecialRenderer(TileTMFireplace.class, new RenderTileFireplace());
//    }
//
//    @Override
//    public boolean registerNormal(Feature feature) {
//        return true;
//    }
}