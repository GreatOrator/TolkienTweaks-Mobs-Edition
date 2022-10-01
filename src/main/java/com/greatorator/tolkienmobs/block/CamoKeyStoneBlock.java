package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoKeyStoneTile;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class CamoKeyStoneBlock extends ChameleonBlock<CamoKeyStoneTile> {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public CamoKeyStoneTile keyTile;

    public CamoKeyStoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(ACTIVE, false).setValue(WATERLOGGED, Boolean.FALSE).setValue(POWERED, Boolean.FALSE));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        ItemStack stack = player.getItemInHand(hand);
        TileEntity tile = world.getBlockEntity(pos);
        CamoKeyStoneTile keyStone = (CamoKeyStoneTile) tile;
        if (!world.isClientSide) {

            LOGGER.info("Consume Key: " + keyStone.keyConsume.get());
            LOGGER.info("Redstone Always: " + keyStone.rsAlways.get());
            LOGGER.info("Redstone Delay: " + keyStone.rsDelay.get());
            LOGGER.info("Redstone Pulse: " + keyStone.rsPulse.get());
            LOGGER.info("Tick Delay: " + keyStone.tickDelay.get());
            LOGGER.info("Key Code: " + keyStone.keyCode.get());
            LOGGER.info("Active: " + world.getBlockState(pos).getValue(ACTIVE));
            LOGGER.info("Powered: " + world.getBlockState(pos).getValue(POWERED));

            if (tile != null && player.isCreative() && player.isCrouching()) {
                ((CamoKeyStoneTile) tile).onRightClick(player, hand);
                return ActionResultType.SUCCESS;
            } else if (stack.getItem() instanceof KeyBaseItem && (KeyBaseItem.getKey(stack).equals(keyStone.keyCode.get()))) {
                LOGGER.info("KeyCode matches!");

                if (keyStone.rsAlways.get()){
                    LOGGER.info("Toggle Mode!");

                    this.activate(state, world, pos);
                    world.setBlockAndUpdate(pos, world.getBlockState(pos).cycle(CamoKeyStoneBlock.ACTIVE));

                    world.playSound((PlayerEntity)null, pos, SoundEvents.LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
                    return ActionResultType.CONSUME;

                }else if (keyStone.rsPulse.get()){
                    LOGGER.info("Button Mode!");

                    world.playSound((PlayerEntity)null, pos, SoundEvents.STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
                    world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(ACTIVE, Boolean.TRUE));

                    this.press(state, world, pos);

                    world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(ACTIVE, Boolean.FALSE).setValue(POWERED, Boolean.FALSE));
                    world.playSound((PlayerEntity)null, pos, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
//                    world.setBlock(pos, state.setValue(POWERED, Boolean.FALSE).setValue(ACTIVE, Boolean.FALSE), 3);
                    this.updateNeighbours(state, world, pos);
                    LOGGER.info("Countdown ended. " + world.getBlockState(pos).getValue(POWERED) + ", " + world.getBlockState(pos).getValue(ACTIVE));
                    return ActionResultType.CONSUME;

                }else if (keyStone.rsDelay.get() && keyStone.tickDelay.get() > 0){
                    LOGGER.info("Delay Mode!");

                    return ActionResultType.CONSUME;

                }

                if (keyStone.keyConsume.get() && !player.isCreative()) {
                    stack.shrink(1);
                }

                return ActionResultType.CONSUME;
            }else {
                player.sendMessage(new TranslationTextComponent(MODID + ".msg.wrong_key").withStyle(TextFormatting.RED), Util.NIL_UUID);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public int getSignal(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos, Direction direction) {
        return blockState.getValue(ACTIVE) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState blockState) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CamoKeyStoneTile();
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
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState neighbor, IWorld world, BlockPos pos, BlockPos offset) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE, POWERED);
        super.createBlockStateDefinition(builder);
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

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, World worldIn, BlockPos pos, Random random) {
        if (blockState.getValue(POWERED) && random.nextFloat() < 0.25F) {
            blockState.setValue(ACTIVE, true);
        }
    }

    public BlockState activate(BlockState blockState, World world, BlockPos blockPos) {
        blockState = blockState.cycle(POWERED);
        world.setBlock(blockPos, blockState, 3);
        this.updateNeighbours(blockState, world, blockPos);
        return blockState;
    }

    public BlockState press(BlockState blockState, World world, BlockPos blockPos) {
        TileEntity tile = world.getBlockEntity(blockPos);
        CamoKeyStoneTile keyStone = (CamoKeyStoneTile) tile;
        for (int i = 0; i <= keyStone.timeActive.get(); i++) {
            LOGGER.info("Countdown begins: " + i);

            world.setBlock(blockPos, blockState.setValue(POWERED, Boolean.TRUE), 3);
            this.updateNeighbours(blockState, world, blockPos);
        }

        return blockState;
    }

    private void updateNeighbours(BlockState blockState, World world, BlockPos blockPos) {
        Direction facing = world.getBlockState(blockPos).getValue(CamoKeyStoneBlock.FACING);
        world.updateNeighborsAt(blockPos, this);
        world.updateNeighborsAt(blockPos.relative(facing.getOpposite()), this);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}