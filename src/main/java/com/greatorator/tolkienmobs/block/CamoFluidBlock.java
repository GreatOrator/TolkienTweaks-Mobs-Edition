package com.greatorator.tolkienmobs.block;

import com.greatorator.tolkienmobs.entity.tile.CamoFluidTile;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CamoFluidBlock extends ChameleonBlock<CamoFluidTile> {

    public CamoFluidBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CamoFluidTile(blockPos, blockState);
    }

    @Override
    public Optional<BlockState> selectBestAdjacentBlock(@Nonnull BlockAndTintGetter world, @Nonnull BlockPos blockPos) {
        TreeMap<Direction, BlockState> adjacentSolidBlocks = new TreeMap<Direction, BlockState>();

        HashMap<BlockState, Integer> adjacentBlockCount = new HashMap<BlockState, Integer>();
        for (Direction facing : Direction.values()) {
            BlockPos adjacentPosition = blockPos.offset(facing.getStepX(),
                    facing.getStepY(),
                    facing.getStepZ());
            BlockState adjacentBS = world.getBlockState(adjacentPosition);
            Block adjacentBlock = adjacentBS.getBlock();
            if (!adjacentBS.isAir() && !(adjacentBlock instanceof LiquidBlock)) {
                adjacentSolidBlocks.put(facing, adjacentBS);
                if (adjacentBlockCount.containsKey(adjacentBS)) {
                    adjacentBlockCount.put(adjacentBS, 1 + adjacentBlockCount.get(adjacentBS));
                } else if (adjacentBS.getBlock() != TolkienBlocks.CHAMELEON_BLOCK.get()
                        && adjacentBS.getBlock() != Blocks.GRASS_BLOCK) {
                    adjacentBlockCount.put(adjacentBS, 1);
                }
            }
        }

        if (adjacentBlockCount.isEmpty()) {
            return Optional.empty();
        }

        if (adjacentSolidBlocks.size() == 1) {
            BlockState singleAdjacentBlock = adjacentSolidBlocks.firstEntry().getValue();
            if (singleAdjacentBlock.getBlock() == TolkienBlocks.CHAMELEON_BLOCK.get()) {
                return Optional.empty();
            } else {
                return Optional.of(singleAdjacentBlock);
            }
        }

        // 2) multiple choices. Look for the one(s) present on the most sides.

        int maxCount = 0;
        ArrayList<BlockState> maxCountIBlockStates = new ArrayList<BlockState>();
        for (Map.Entry<BlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) { // a tie
                maxCountIBlockStates.add(entry.getKey());
            }
        }

        if (maxCountIBlockStates.isEmpty()) throw new AssertionError("maxCountIBlockStates.isEmpty()");
        if (maxCountIBlockStates.size() == 1) {               // one clear winner
            return Optional.of(maxCountIBlockStates.get(0));
        }

        // for each block which has a match on the opposite side, add 10 to its count.
        // exact matches are counted twice --> +20, match with BlockCamouflage only counted once -> +10
        for (Map.Entry<Direction, BlockState> entry : adjacentSolidBlocks.entrySet()) {
            BlockState iBlockState = entry.getValue();
            if (maxCountIBlockStates.contains(iBlockState)) {
                Direction oppositeSide = entry.getKey().getOpposite();
                BlockState oppositeBlock = adjacentSolidBlocks.get(oppositeSide);
                if (oppositeBlock != null && (oppositeBlock == iBlockState || oppositeBlock.getBlock() == TolkienBlocks.CHAMELEON_BLOCK.get()) ) {
                    adjacentBlockCount.put(iBlockState, 10 + adjacentBlockCount.get(iBlockState));
                }
            }
        }

        maxCount = 0;
        maxCountIBlockStates.clear();
        for (Map.Entry<BlockState, Integer> entry : adjacentBlockCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCountIBlockStates.clear();
                maxCountIBlockStates.add(entry.getKey());
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                maxCountIBlockStates.add(entry.getKey());
            }
        }
        if (maxCountIBlockStates.isEmpty()) throw new AssertionError("maxCountIBlockStates.isEmpty()");
        if (maxCountIBlockStates.size() == 1) {  // one clear winner
            return Optional.of(maxCountIBlockStates.get(0));
        }

        Direction[] orderOfPreference = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.EAST,
                Direction.WEST, Direction.DOWN, Direction.UP};

        for (Direction testFace : orderOfPreference) {
            if (adjacentSolidBlocks.containsKey(testFace) &&
                    maxCountIBlockStates.contains(adjacentSolidBlocks.get(testFace))) {
                return Optional.of(adjacentSolidBlocks.get(testFace));
            }
        }
        throw new AssertionError("unreachable code");
    }
}