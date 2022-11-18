package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.entity.tile.LockableChestTile;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import com.greatorator.tolkienmobs.item.keys.KeyBaseItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class LockableChestBlock extends BlockBCore implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape LOCKABLE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public LockableChestBlock(Properties properties) {
        super(properties);
        setBlockEntity(() -> TolkienTiles.LOCKABLE_CHEST_TILE.get(), true); //<-- The boolean (true) specifies that this tile needs to tick. If your tile implemented ITickableTileEntity in 1.16 then this needs to be true
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        ItemStack stack = player.getItemInHand(hand);
        BlockEntity tile = world.getBlockEntity(pos);

        if (!world.isClientSide) {
            if (tile instanceof LockableChestTile) {
                if (player.isCreative()) {
                    ((LockableChestTile) tile).onRightClick(player, hand);
                } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getCode(stack).equals(((LockableChestTile) tile).keyCode.get()))) {
                    int uses = KeyBaseItem.getUses(stack);

                    if (KeyBaseItem.getUses(stack) >= 0) {
                        world.sendBlockUpdated(pos, state, state, 3);

                        if (uses == 0) {
                            stack.shrink(1);
                            world.playSound((Player) null, pos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 0.3F, 0.6F);
                            player.sendMessage(new TranslatableComponent(MODID + ".msg.key_used").withStyle(ChatFormatting.RED), Util.NIL_UUID);
                        }
                        uses--;
                        KeyBaseItem.setUses(stack, uses);
                    }
                    ((LockableChestTile) tile).onRightClick(player, hand);
                    world.playSound((Player) null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.3F, 0.5F);
                } else {
                    player.sendMessage(new TranslatableComponent(MODID + ".msg.wrong_key").withStyle(ChatFormatting.RED), Util.NIL_UUID);
                    world.playSound((Player) null, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS, 0.3F, 0.5F);
                }
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return LOCKABLE_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, LevelAccessor world, BlockPos pos, BlockPos offset) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = world.getFluidState(pos);
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}