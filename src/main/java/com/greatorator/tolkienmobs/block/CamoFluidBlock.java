package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
import com.greatorator.tolkienmobs.handler.DefaultCamoFluidBehavior;
import com.greatorator.tolkienmobs.handler.FluidPosition;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidHelper;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidPosition;
import com.greatorator.tolkienmobs.handler.interfaces.IFluidSource;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Map;

public class CamoFluidBlock extends ChameleonBlock<CamoFluidTile> {
    public static final Map<Item, IFluidHelper> DISPENSER_REGISTRY = Util.make(new Object2ObjectOpenHashMap<>(), (p_212564_0_) -> {
        p_212564_0_.defaultReturnValue(new DefaultCamoFluidBehavior());
    });

    public CamoFluidBlock(Properties properties) {
        super(properties);
    }

    public static void registerBehavior(IItemProvider bucket, IFluidHelper defaultCamoFluidBehavior) {
        DISPENSER_REGISTRY.put(bucket.asItem(), defaultCamoFluidBehavior);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CamoFluidTile();
    }

    public static IFluidPosition getDispensePosition(IFluidSource fluidSource) {
        Direction direction = fluidSource.getBlockState().getValue(FACING);
        double d0 = fluidSource.x();
        double d1 = fluidSource.y() + (double)direction.getStepY();
        double d2 = fluidSource.z();
        return new FluidPosition(d0, d1, d2);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isClientSide) {
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof CamoFluidTile) {
                ((CamoFluidTile) tile).onRightClick(player, hand);
            }
            return ActionResultType.CONSUME;
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}