package com.greatorator.tolkienmobs.block;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.greatorator.tolkienmobs.init.TolkienTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

import javax.annotation.Nullable;

public class WellBlock extends BlockBCore implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

//    public static final VoxelShape SHAPE_N = Block.box(24.0D, 0.0D, 24.0D, 24.0D, 32.0D, 24.0D);
//    public static final VoxelShape SHAPE_S = Block.box(24.0D, 0.0D, 24.0D, 24.0D, 32.0D, 24.0D);
//    public static final VoxelShape SHAPE_E = Block.box(24.0D, 0.0D, 24.0D, 24.0D, 32.0D, 24.0D);
//    public static final VoxelShape SHAPE_W = Block.box(24.0D, 0.0D, 24.0D, 24.0D, 32.0D, 24.0D);
    protected static final VoxelShape SHAPE_N = Block.box(-7.0D, 0.0D, 1.0D, 23.0D, 32.0D, 15.0D);;
    protected static final VoxelShape SHAPE_S = Block.box(1.0D, 0.0D, -7.0D, 15.0D, 32.0D, 23.0D);
    protected static final VoxelShape SHAPE_E = Block.box(1.0D, 0.0D, -7.0D, 15.0D, 32.0D, 23.0D);
    protected static final VoxelShape SHAPE_W = Block.box(-7.0D, 0.0D, 1.0D, 23.0D, 32.0D, 15.0D);

    public WellBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return TolkienTiles.WELL_TILE.get().create(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        switch((Direction)state.getValue(FACING)) {
            default:
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
        }
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

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = world.getFluidState(pos);
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        ItemStack filledBottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);

        if (heldItem.getItem() == Items.BUCKET) {
            CompoundTag tagCompound = heldItem.getTag();
            ItemStack newItem = Items.WATER_BUCKET.getDefaultInstance();
            if (tagCompound != null) {
                newItem.setTag(tagCompound);
            }
            if (heldItem.getCount() <= 1) {
                player.setItemInHand(hand, newItem);
            } else {
                if (player.getInventory().add(newItem)) {
                    heldItem.shrink(1);
                }
            }
            spawnParticlesAndPlaySound(level, pos, state);
            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1f, level.random.nextFloat() + 0.5f);
            return InteractionResult.SUCCESS;
        }else if (heldItem.getItem() == Items.GLASS_BOTTLE) {
            CompoundTag tagCompound = heldItem.getTag();
            if (tagCompound != null) {
                filledBottle.setTag(tagCompound);
            }
            if (heldItem.getCount() <= 1) {
                player.setItemInHand(hand, filledBottle);
            } else {
                if (player.getInventory().add(filledBottle)) {
                    heldItem.shrink(1);
                }
            }
            spawnParticlesAndPlaySound(level, pos, state);
            level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1f, level.random.nextFloat() + 0.5f);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    private void spawnParticlesAndPlaySound(Level level, BlockPos pos, BlockState state) {
        float dripWaterX = 0f;
        float dripWaterZ = 0f;
        switch (state.getValue(FACING)) {
            case NORTH -> {
                dripWaterZ = 0.25f;
                dripWaterX = -0.05f;
            }
            case SOUTH -> dripWaterX = 0.25f;
            case WEST -> {
                dripWaterX = 0.25f;
                dripWaterZ = 0.25f;
            }
            case EAST -> dripWaterZ = -0.05f;
        }

        float particleX = (float) pos.getX() + 0.5f;
        float particleY = (float) pos.getY() + 1.25f;
        float particleZ = (float) pos.getZ() + 0.5f;
        level.addParticle(ParticleTypes.SPLASH, (double) particleX + dripWaterX, (double) particleY - 0.45f, (double) particleZ + dripWaterZ, 0, 0, 0);
        for (int i = 0; i < 5; i++) {
            level.addParticle(ParticleTypes.SPLASH, (double) particleX + Math.random() - 0.5f, (double) particleY + Math.random() - 0.5f, (double) particleZ + Math.random() - 0.5f, 0, 0, 0);
        }

        level.playSound(null, pos, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, 0.1f, level.random.nextFloat() + 0.5f);
    }
}