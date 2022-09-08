package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.PlacardTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.AttachFace.*;

public class BlockTTMPlacard extends BlockBCore {
    public static final EnumProperty<AttachFace> ATTACH_FACE =  EnumProperty.create("facing", AttachFace.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE_HANGING_NS = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_HANGING_EW = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_STANDING_NS = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    public static final VoxelShape SHAPE_STANDING_EW = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 5.0D, 2.0D, 16.0D, 13.0D, 0.0D);
    protected static final VoxelShape SHAPE_EAST = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(16.0D, 5.0D, 0.0D, 14.0D, 13.0D, 16.0D);

    public BlockTTMPlacard(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ATTACH_FACE, AttachFace.FLOOR));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(ATTACH_FACE);

        switch(facing) {
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
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ATTACH_FACE);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        Direction clicked = context.getClickedFace();
        return defaultBlockState().setValue(FACING, facing).setValue(ATTACH_FACE, clicked == Direction.UP ? FLOOR : clicked == Direction.DOWN ? CEILING : WALL);
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
}