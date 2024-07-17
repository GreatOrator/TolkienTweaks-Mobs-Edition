package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.init.TolkienParticles;
import com.greatorator.tolkienmobs.init.TolkienSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class LightningBugBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final VoxelShape DOWN_BB = Shapes.or(
            Block.box(7, 0, 5.499999999999998, 8, 0.9999999999999992, 6),
            Block.box(7, 0, 6, 8, 0.9999999999999992, 7.5),
            Block.box(7, 0, 7.5, 8, 0.9999999999999992, 8.000000000000002),
            Block.box(7.899999999999999, 0.9999999999999992, 8.899999999999999, 8.4, 0.9999999999999992, 10.299999999999997),
            Block.box(6.6, 0.9999999999999992, 8.499999999999998, 7.100000000000001, 0.9999999999999992, 9.9),
            Block.box(7.800000000000001, 0.7999999999999997, 4.999999999999997, 7.800000000000001, 1.299999999999999, 5.499999999999999),
            Block.box(7.199999999999999, 0.7999999999999997, 4.9999999999999964, 7.199999999999999, 1.299999999999999, 5.499999999999998),
            Block.box(7.1, -0.3999999999999999, 5.799999999999997, 7.9, 2.220446049250313e-16, 7.799999999999997),
            Block.box(7.399999999999998, -0.19999999999999996, 5.499999999999998, 7.399999999999998, 0.09999999999999964, 5.700000000000001),
            Block.box(7.600000000000002, -0.19999999999999996, 5.499999999999998, 7.600000000000002, 0.09999999999999964, 5.700000000000001));
    private final VoxelShape UP_BB = Shapes.or(
            Block.box(7, 15, 5.499999999999998, 8, 16, 6),
            Block.box(7, 15, 6, 8, 16, 7.5),
            Block.box(7, 15, 7.5, 8, 16, 8.000000000000002),
            Block.box(7.899999999999999, 15, 8.899999999999999, 8.4, 15, 10.299999999999997),
            Block.box(6.6, 15, 8.499999999999998, 7.100000000000001, 15, 9.9),
            Block.box(7.800000000000001, 14.700000000000001, 4.999999999999997, 7.800000000000001, 15.2, 5.499999999999999),
            Block.box(7.199999999999999, 14.700000000000001, 4.9999999999999964, 7.199999999999999, 15.2, 5.499999999999998),
            Block.box(7.1, 16, 5.799999999999997, 7.9, 16.399999999999995, 7.799999999999997),
            Block.box(7.399999999999998, 15.9, 5.499999999999998, 7.399999999999998, 16.199999999999996, 5.700000000000001),
            Block.box(7.600000000000002, 15.9, 5.499999999999998, 7.600000000000002, 16.199999999999996, 5.700000000000001));
    private final VoxelShape NORTH_BB = Shapes.or(
            Block.box(7, 6, 0, 8, 6.5, 1),
            Block.box(7, 4.5, 0, 8, 6, 1),
            Block.box(7, 4, 0, 8, 4.5, 1),
            Block.box(7.799999999999999, 8.300000000000004, 1, 8.3, 9.700000000000003, 1),
            Block.box(6.799999999999999, 7.899999999999999, 1, 7.300000000000001, 9.299999999999994, 1),
            Block.box(7.800000000000001, 6.5, 0.8000000000000007, 7.800000000000001, 7.0000000000000036, 1.2999999999999972),
            Block.box(7.199999999999999, 6.5, 0.8000000000000007, 7.199999999999999, 7.0000000000000036, 1.2999999999999972),
            Block.box(7.1, 4.200000000000003, -0.399999999999995, 7.9, 6.200000000000003, 0),
            Block.box(7.399999999999998, 6.299999999999997, -0.19999999999999574, 7.399999999999998, 6.5, 0.10000000000000142),
            Block.box(7.600000000000002, 6.299999999999997, -0.19999999999999574, 7.600000000000002, 6.5, 0.10000000000000142));
    private final VoxelShape SOUTH_BB = Shapes.or(Block.box(7, 6, 15, 8, 6.5, 16),
            Block.box(7, 4.5, 15, 8, 6, 16),
            Block.box(7, 4, 15, 8, 4.5, 16),
            Block.box(7.799999999999999, 8.300000000000004, 14.8, 8.3, 9.700000000000003, 14.8),
            Block.box(6.799999999999999, 7.899999999999999, 14.9, 7.300000000000001, 9.299999999999994, 14.9),
            Block.box(7.800000000000001, 6.5, 14.700000000000003, 7.800000000000001, 7.0000000000000036, 15.2),
            Block.box(7.199999999999999, 6.5, 14.700000000000003, 7.199999999999999, 7.0000000000000036, 15.2),
            Block.box(7.1, 4.200000000000003, 16, 7.9, 6.200000000000003, 16.399999999999995),
            Block.box(7.399999999999998, 6.299999999999997, 15.899999999999999, 7.399999999999998, 6.5, 16.199999999999996),
            Block.box(7.600000000000002, 6.299999999999997, 15.899999999999999, 7.600000000000002, 6.5, 16.199999999999996));
    private final VoxelShape WEST_BB = Shapes.or(Block.box(0, 6, 7, 1, 6.5, 8),
            Block.box(0, 4.5, 7, 1, 6, 8),
            Block.box(0, 4, 7, 1, 4.5, 8),
            Block.box(1.1999999999999993, 8.300000000000004, 7.799999999999999, 1.1999999999999993, 9.700000000000003, 8.3),
            Block.box(1.0999999999999996, 7.899999999999999, 6.799999999999999, 1.0999999999999996, 9.299999999999994, 7.300000000000001),
            Block.box(0.8000000000000007, 6.5, 7.800000000000001, 1.2999999999999972, 7.0000000000000036, 7.800000000000001),
            Block.box(0.8000000000000007, 6.5, 7.199999999999999, 1.2999999999999972, 7.0000000000000036, 7.199999999999999),
            Block.box(-0.3999999999999986, 4.200000000000003, 7.1, 0, 6.200000000000003, 7.9),
            Block.box(-0.19999999999999574, 6.299999999999997, 7.399999999999999, 0.10000000000000142, 6.5, 7.399999999999999),
            Block.box(-0.19999999999999574, 6.299999999999997, 7.600000000000001, 0.10000000000000142, 6.5, 7.600000000000001));
    private final VoxelShape EAST_BB = Shapes.or(Block.box(15, 6, 7, 16, 6.5, 8),
            Block.box(15, 4.5, 7, 16, 6, 8),
            Block.box(15, 4, 7, 16, 4.5, 8),
            Block.box(14.8, 8.300000000000004, 7.799999999999999, 14.8, 9.700000000000003, 8.3),
            Block.box(14.9, 7.899999999999999, 6.799999999999999, 14.9, 9.299999999999994, 7.300000000000001),
            Block.box(14.700000000000003, 6.5, 7.800000000000001, 15.2, 7.0000000000000036, 7.800000000000001),
            Block.box(14.700000000000003, 6.5, 7.199999999999999, 15.2, 7.0000000000000036, 7.199999999999999),
            Block.box(16, 4.200000000000003, 7.1, 16.4, 6.200000000000003, 7.9),
            Block.box(15.899999999999999, 6.299999999999997, 7.399999999999999, 16.199999999999996, 6.5, 7.399999999999999),
            Block.box(15.899999999999999, 6.299999999999997, 7.600000000000001, 16.199999999999996, 6.5, 7.600000000000001));


    public LightningBugBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level worldIn, BlockPos pos, Random random) {
        double rx = pos.getX() + worldIn.random.nextFloat();
        double ry = pos.getY() + worldIn.random.nextFloat();
        double rz = pos.getZ() + worldIn.random.nextFloat();
        worldIn.addParticle(TolkienParticles.lightningbug, rx, ry, rz, 0, 0, 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_BB;
            default -> UP_BB;
            case NORTH -> NORTH_BB;
            case SOUTH -> SOUTH_BB;
            case WEST -> WEST_BB;
            case EAST -> EAST_BB;
        };
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clicked = context.getClickedFace();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState state = defaultBlockState().setValue(FACING, clicked).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);

        if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
            return state;
        }

        for (Direction dir : context.getNearestLookingDirections()) {
            state = defaultBlockState().setValue(FACING, dir.getOpposite());
            if (canSurvive(state, context.getLevel(), context.getClickedPos())) {
                return state;
            }
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (state.getValue(FACING).getOpposite() == direction && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(DirectionalBlock.FACING);
        BlockPos restingPos = pos.relative(facing.getOpposite());
        return canSupportCenter(level, restingPos, facing);
    }

    public ItemStack getSquishResult() {
        return new ItemStack(Items.GLOWSTONE_DUST);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Projectile || entity instanceof FallingBlockEntity) {
            level.setBlockAndUpdate(pos, state.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState());
            if (level.isClientSide())
                Minecraft.getInstance().getSoundManager().stop(TolkienSounds.lightningbug_ambient.getId(), SoundSource.NEUTRAL);
            level.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            ItemEntity squish = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), this.getSquishResult());
            squish.spawnAtLocation(squish.getItem());
            for (int i = 0; i < 50; i++) {
                boolean wallBug = state.getValue(FACING) != Direction.UP;
                level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SLIME_BLOCK.defaultBlockState()), true,
                        pos.getX() + Mth.nextFloat(level.getRandom(), 0.25F, 0.75F),
                        pos.getY() + (wallBug ? 0.5F : 0.0F),
                        pos.getZ() + Mth.nextFloat(level.getRandom(), 0.25F, 0.75F),
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, WATERLOGGED);
    }
}
