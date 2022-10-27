package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.LockableDoubleTreasureChestTile;
import com.greatorator.tolkienmobs.item.tools.KeyBaseItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableDoubleTreasureChestBlock extends BlockBCore {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape LOCKABLE_SHAPE_N = Block.box(-15.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape LOCKABLE_SHAPE_S = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 31.0D);
    protected static final VoxelShape LOCKABLE_SHAPE_E = Block.box(1.0D, 0.0D, -15.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape LOCKABLE_SHAPE_W = Block.box(1.0D, 0.0D, 1.0D, 31.0D, 15.0D, 15.0D);

    public LockableDoubleTreasureChestBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        ItemStack stack = player.getItemInHand(hand);
        TileEntity tile = world.getBlockEntity(pos);

        if (!world.isClientSide) {
            if (tile instanceof LockableDoubleTreasureChestTile) {
                if (player.isCreative()) {
                    ((LockableDoubleTreasureChestTile) tile).onRightClick(player, hand);
                } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getCode(stack).equals(((LockableDoubleTreasureChestTile) tile).keyCode.get()))) {
                    int uses = KeyBaseItem.getUses(stack);

                    if (KeyBaseItem.getUses(stack) >= 0) {
                        world.sendBlockUpdated(pos, state, state, 3);

                        if (uses == 0) {
                            stack.shrink(1);
                            world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BREAK, SoundCategory.BLOCKS, 0.3F, 0.6F);
                            player.sendMessage(new TranslationTextComponent(MODID + ".msg.key_used").withStyle(TextFormatting.RED), Util.NIL_UUID);
                        }
                        uses--;
                        KeyBaseItem.setUses(stack, uses);
                    }
                    ((LockableDoubleTreasureChestTile) tile).onRightClick(player, hand);
                    world.playSound((PlayerEntity) null, pos, SoundEvents.CHEST_OPEN, SoundCategory.BLOCKS, 0.3F, 0.5F);
                } else {
                    player.sendMessage(new TranslationTextComponent(MODID + ".msg.wrong_key").withStyle(TextFormatting.RED), Util.NIL_UUID);
                    world.playSound((PlayerEntity) null, pos, SoundEvents.CHAIN_PLACE, SoundCategory.BLOCKS, 0.3F, 0.5F);
                }
            }
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new LockableDoubleTreasureChestTile();
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.getValue(FACING)) {
            default:
            case NORTH:
                return LOCKABLE_SHAPE_N;
            case SOUTH:
                return LOCKABLE_SHAPE_W;
            case EAST:
                return LOCKABLE_SHAPE_E;
            case WEST:
                return LOCKABLE_SHAPE_S;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, IWorld world, BlockPos pos, BlockPos offset) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return state;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = world.getFluidState(pos);
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}