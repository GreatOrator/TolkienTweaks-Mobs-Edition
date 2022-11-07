package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.PlacardTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.level.block.state.properties.AttachFace.*;

public class PlacardBlock extends Block implements EntityBlock {
    public static final EnumProperty<AttachFace> ATTACH_FACE = EnumProperty.create("attach_face", AttachFace.class);
    public static final EnumProperty<PlacardType> PLACARD_TYPE = EnumProperty.create("placard_type", PlacardType.class);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE_STANDING_NS = Shapes.or(
            Block.box(12.8, -0.1, 7.3, 14.2, 0.9, 8.7),
            Block.box(1, 7, 7.5, 15, 13, 8.5),
            Block.box(0, 6, 7, 16, 7, 9),
            Block.box(0, 7, 7, 1, 13, 9),
            Block.box(0, 13, 7, 16, 14, 9),
            Block.box(15, 7, 7, 16, 13, 9),
            Block.box(2, 0.4, 7.5, 3, 7.4, 8.5),
            Block.box(13, 0.4, 7.5, 14, 7.4, 8.5),
            Block.box(1.8, -0.1, 7.3, 3.2, 0.9, 8.7)
    );
    public static final VoxelShape SHAPE_STANDING_EW = Shapes.or(
            Block.box(7.300000000000001, -0.1, 12.8, 8.7, 0.9, 14.2),
            Block.box(7.5, 7, 1, 8.5, 13, 15),
            Block.box(7, 6, 0, 9, 7, 16),
            Block.box(7, 7, 0, 9, 13, 1),
            Block.box(7, 13, 0, 9, 14, 16),
            Block.box(7, 7, 15, 9, 13, 16),
            Block.box(7.5, 0.4, 2, 8.5, 7.4, 3),
            Block.box(7.5, 0.4, 13, 8.5, 7.4, 14),
            Block.box(7.300000000000001, -0.1, 1.8, 8.7, 0.9, 3.2)
    );
    public static final VoxelShape SHAPE_HANGING_NS = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 8.0D, 9.0D);
    public static final VoxelShape SHAPE_HANGING_EW = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 8.0D, 16.0D);
    protected static final VoxelShape SHAPE_NORTH = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 5.0D, 14.0D, 16.0D, 13.0D, 16.0D);
//    protected static final VoxelShape SHAPE_SOUTH = Block.box(0.0D, 5.0D, 2.0D, 16.0D, 13.0D, 0.0D);
    protected static final VoxelShape SHAPE_EAST = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
    protected static final VoxelShape SHAPE_WEST = Block.box(0.0D, 5.0D, 0.0D, 2.0D, 13.0D, 16.0D);
//    protected static final VoxelShape SHAPE_WEST = Block.box(16.0D, 5.0D, 0.0D, 14.0D, 13.0D, 16.0D);

    public PlacardBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ATTACH_FACE, FLOOR).setValue(PLACARD_TYPE, PlacardType.EMPTY));
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
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
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide() && player.isShiftKeyDown()) {
            world.setBlockAndUpdate(pos, state.setValue(PLACARD_TYPE, state.getValue(PLACARD_TYPE).next()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        Direction clicked = context.getClickedFace();
        return defaultBlockState().setValue(FACING, facing).setValue(ATTACH_FACE, clicked == Direction.UP ? FLOOR : clicked == Direction.DOWN ? CEILING : WALL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ATTACH_FACE).add(PLACARD_TYPE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PlacardTile(blockPos, blockState);
    }

    public enum PlacardType implements StringRepresentable {
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