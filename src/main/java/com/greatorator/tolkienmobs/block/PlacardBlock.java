package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.PlacardTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.AttachFace.*;

public class PlacardBlock extends BlockBCore {
    public static final EnumProperty<AttachFace> ATTACH_FACE = EnumProperty.create("attach_face", AttachFace.class);
    public static final EnumProperty<PlacardType> PLACARD_TYPE = EnumProperty.create("placard_type", PlacardType.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE_HANGING_NS = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_HANGING_EW = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_STANDING_NS = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_STANDING_EW = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 5.0D, 2.0D, 16.0D, 13.0D, 0.0D);
    protected static final VoxelShape SHAPE_EAST = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(16.0D, 5.0D, 0.0D, 14.0D, 13.0D, 16.0D);

    public PlacardBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ATTACH_FACE, AttachFace.FLOOR).setValue(PLACARD_TYPE, PlacardType.EMPTY));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(ATTACH_FACE);

        switch (facing) {
            case NORTH:
                return face == FLOOR ? SHAPE_STANDING_NS : face == CEILING ? SHAPE_HANGING_NS : SHAPE_NORTH;
            case SOUTH:
                return face == FLOOR ? SHAPE_STANDING_NS : face == CEILING ? SHAPE_HANGING_NS : SHAPE_SOUTH;
            case EAST:
                return face == FLOOR ? SHAPE_STANDING_EW : face == CEILING ? SHAPE_HANGING_EW : SHAPE_EAST;
            case WEST:
                return face == FLOOR ? SHAPE_STANDING_EW : face == CEILING ? SHAPE_HANGING_EW : SHAPE_WEST;
            default:
                return SHAPE_NORTH; //This should never be reached, so it does not matter what is returned.
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide() && player.isShiftKeyDown()) {
            world.setBlockAndUpdate(pos, state.setValue(PLACARD_TYPE, state.getValue(PLACARD_TYPE).next()));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        Direction clicked = context.getClickedFace();
        return defaultBlockState().setValue(FACING, facing).setValue(ATTACH_FACE, clicked == Direction.UP ? FLOOR : clicked == Direction.DOWN ? CEILING : WALL);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ATTACH_FACE).add(PLACARD_TYPE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlacardTile();
    }

    public enum PlacardType implements IStringSerializable {
        EMPTY(0, "empty"),
        ARCANE1(1, "arcane1"),
        ARCANE2(2, "arcane2"),
        ARCANE3(3, "arcane3"),
        ARMORY(4, "armory"),
        BANK(5, "bank"),
        BARRACKS1(6, "barracks1"),
        BARRACKS2(7, "barracks2"),
        BLACKSMITH(8, "blacksmith"),
        BOOKS(9, "books"),
        BUTCHER1(10, "butcher1"),
        FARMING(11, "farming"),
        GARDEN(12, "garden"),
        GROCER(13, "grocer"),
        INN(14, "inn"),
        MAGICAL(15, "magical_plants"),
        POTIONS(16, "potions"),
        PUB(17, "pub"),
        STABLE(18, "stable"),
        LUMBER(19, "lumberjack"),
        POSTMAN(20, "postman");

        private final String name;
        private final int index;

        PlacardType(int index, String name) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

        public PlacardType next() {
            return values()[(index + 1) % values().length];
        }
    }
}