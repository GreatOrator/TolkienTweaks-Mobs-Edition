package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.init.TolkienTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.RandomSource;

import java.util.function.Predicate;

public class FeatureLogicUtility {
    public static final Predicate<BlockState> IS_REPLACEABLE_AIR = state -> state.getMaterial().isReplaceable() || state.isAir();
    public static final Predicate<BlockState> ROOT_SHOULD_SKIP = state -> state.is(TolkienTags.blocks.ROOT_SKIP);
    public static boolean hasEmptyHorizontalNeighbor(LevelSimulatedReader worldReader, BlockPos pos) {
        return worldReader.isStateAtPosition(pos.north(), IS_REPLACEABLE_AIR)
                || worldReader.isStateAtPosition(pos.south(), IS_REPLACEABLE_AIR)
                || worldReader.isStateAtPosition(pos.west(), IS_REPLACEABLE_AIR)
                || worldReader.isStateAtPosition(pos.east(), IS_REPLACEABLE_AIR);
    }

    // Slight stretch of logic: We check if the block is completely surrounded by air.
    // If it's not completely surrounded by air, then there's a solid
    public static boolean hasSolidNeighbor(LevelSimulatedReader worldReader, BlockPos pos) {
        return !(worldReader.isStateAtPosition(pos.below(), IS_REPLACEABLE_AIR)
                && worldReader.isStateAtPosition(pos.north(), IS_REPLACEABLE_AIR)
                && worldReader.isStateAtPosition(pos.south(), IS_REPLACEABLE_AIR)
                && worldReader.isStateAtPosition(pos.west(), IS_REPLACEABLE_AIR)
                && worldReader.isStateAtPosition(pos.east(), IS_REPLACEABLE_AIR)
                && worldReader.isStateAtPosition(pos.above(), IS_REPLACEABLE_AIR));
    }

    public static boolean canRootGrowIn(LevelSimulatedReader worldReader, BlockPos pos) {
        if (worldReader.isStateAtPosition(pos, IS_REPLACEABLE_AIR)) {
            // roots can grow through air if they are near a solid block
            return hasSolidNeighbor(worldReader, pos);
        } else {
            return worldReader.isStateAtPosition(pos, FeatureLogicUtility::worldGenReplaceable);
        }
    }

    public static boolean worldGenReplaceable(BlockState state) {
        return (state.getMaterial().isReplaceable() || state.is(TolkienTags.blocks.REPLACEABLE)) && !state.is(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    /**
     * Moves distance along the vector.
     * <p>
     * This goofy function takes a float between 0 and 1 for the angle, where 0 is 0 degrees, .5 is 180 degrees and 1 and 360 degrees.
     * For the tilt, it takes a float between 0 and 1 where 0 is straight up, 0.5 is straight out and 1 is straight down.
     */
    public static BlockPos translate(BlockPos pos, double distance, double angle, double tilt) {
        double rangle = angle * 2.0D * Math.PI;
        double rtilt = tilt * Math.PI;

        return pos.offset(
                Math.round(Math.sin(rangle) * Math.sin(rtilt) * distance),
                Math.round(Math.cos(rtilt) * distance),
                Math.round(Math.cos(rangle) * Math.sin(rtilt) * distance)
        );
    }

    /**
     * Gets either cobblestone or mossy cobblestone, randomly.  Used for ruins.
     */
    @Deprecated // Determine if we can actually remove this one and delegate to StructureProcessor
    public static BlockState randStone(RandomSource rand, int howMuch) {
        return rand.nextInt(howMuch) >= 1 ? Blocks.COBBLESTONE.defaultBlockState() : Blocks.MOSSY_COBBLESTONE.defaultBlockState();
    }
}
