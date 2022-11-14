package com.greatorator.tolkienmobs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class FeaturePlacer {
    public static final BiFunction<LevelSimulatedReader, BlockPos, Boolean> VALID_TREE_POS = TreeFeature::validTreePos;

    public static void putLeafBlock(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> worldPlacer, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, BlockPos pos, BlockStateProvider config, Random random) {
        if (predicate.apply(world, pos)) worldPlacer.accept(pos, config.getState(random, pos));
    }

    public static void makeLeafSpheroid(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, Random random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias, BlockStateProvider config) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        putLeafBlock(world, placer, predicate, centerPos, config, random);

        for (int y = 0; y <= yRadius; y++) {
            if (y > yRadius) continue;

            putLeafBlock(world, placer, predicate, centerPos.offset( 0,  y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0,  y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0,  y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0,  y, 0), config, random);

            putLeafBlock(world, placer, predicate, centerPos.offset( 0, -y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0, -y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0, -y, 0), config, random);
            putLeafBlock(world, placer, predicate, centerPos.offset( 0, -y, 0), config, random);
        }

        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if (x * x + z * z > xzRadiusSquared) continue;

                putLeafBlock(world, placer, predicate, centerPos.offset(  x, 0,  z), config, random);
                putLeafBlock(world, placer, predicate, centerPos.offset( -x, 0, -z), config, random);
                putLeafBlock(world, placer, predicate, centerPos.offset( -z, 0,  x), config, random);
                putLeafBlock(world, placer, predicate, centerPos.offset(  z, 0, -x), config, random);

                for (int y = 1; y <= yRadius; y++) {
                    float xzSquare = ((x * x + z * z) * yRadiusSquared);

                    if (xzSquare + (((y - verticalBias) * (y - verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        putLeafBlock(world, placer, predicate, centerPos.offset(  x,  y,  z), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset( -x,  y, -z), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset( -z,  y,  x), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset(  z,  y, -x), config, random);
                    }

                    if (xzSquare + (((y + verticalBias) * (y + verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        putLeafBlock(world, placer, predicate, centerPos.offset(  x, -y,  z), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset( -x, -y, -z), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset( -z, -y,  x), config, random);
                        putLeafBlock(world, placer, predicate, centerPos.offset(  z, -y, -x), config, random);
                    }
                }
            }
        }
    }

    public static void drawBresenhamBranch(LevelAccessor world, BiConsumer<BlockPos, BlockState> trunkPlacer, Random random, BlockPos start, BlockPos end, BlockStateProvider config) {
        for (BlockPos pixel : new BresenhamIterator(start, end)) {
            placeIfValidTreePos(world, trunkPlacer, random, pixel, config);
        }
    }

    public static void drawBresenhamTree(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, BlockPos from, BlockPos to, BlockStateProvider config, Random random) {
        for (BlockPos pixel : new BresenhamIterator(from, to)) {
            placeProvidedBlock(world, placer, predicate, pixel, config, random);
        }
    }

    public static BlockPos translate(BlockPos pos, double distance, double angle, double tilt) {
        double rangle = angle * 2.0D * Math.PI;
        double rtilt = tilt * Math.PI;

        return pos.offset(
                Math.round(Math.sin(rangle) * Math.sin(rtilt) * distance),
                Math.round(Math.cos(rtilt) * distance),
                Math.round(Math.cos(rangle) * Math.sin(rtilt) * distance)
        );
    }

    public static void placeProvidedBlock(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> worldPlacer, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, BlockPos pos, BlockStateProvider config, Random random) {
        if (predicate.apply(world, pos)) worldPlacer.accept(pos, config.getState(random, pos));
    }

    public static BlockPos[] getBresenhamArrays(BlockPos src, BlockPos dest) {
        return getBresenhamArrays(src.getX(), src.getY(), src.getZ(), dest.getX(), dest.getY(), dest.getZ());
    }

    public static BlockPos[] getBresenhamArrays(int x1, int y1, int z1, int x2, int y2, int z2) {
        int i, dx, dy, dz, absDx, absDy, absDz, x_inc, y_inc, z_inc, err_1, err_2, doubleAbsDx, doubleAbsDy, doubleAbsDz;

        BlockPos pixel = new BlockPos(x1, y1, z1);
        BlockPos lineArray[];

        dx = x2 - x1;
        dy = y2 - y1;
        dz = z2 - z1;
        x_inc = (dx < 0) ? -1 : 1;
        absDx = Math.abs(dx);
        y_inc = (dy < 0) ? -1 : 1;
        absDy = Math.abs(dy);
        z_inc = (dz < 0) ? -1 : 1;
        absDz = Math.abs(dz);
        doubleAbsDx = absDx << 1;
        doubleAbsDy = absDy << 1;
        doubleAbsDz = absDz << 1;

        if ((absDx >= absDy) && (absDx >= absDz)) {
            err_1 = doubleAbsDy - absDx;
            err_2 = doubleAbsDz - absDx;
            lineArray = new BlockPos[absDx + 1];
            for (i = 0; i < absDx; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.above(y_inc);
                    err_1 -= doubleAbsDx;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDx;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDz;
                pixel = pixel.east(x_inc);
            }
        } else if ((absDy >= absDx) && (absDy >= absDz)) {
            err_1 = doubleAbsDx - absDy;
            err_2 = doubleAbsDz - absDy;
            lineArray = new BlockPos[absDy + 1];
            for (i = 0; i < absDy; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.east(x_inc);
                    err_1 -= doubleAbsDy;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDy;
                }
                err_1 += doubleAbsDx;
                err_2 += doubleAbsDz;
                pixel = pixel.above(y_inc);
            }
        } else {
            err_1 = doubleAbsDy - absDz;
            err_2 = doubleAbsDx - absDz;
            lineArray = new BlockPos[absDz + 1];
            for (i = 0; i < absDz; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.above(y_inc);
                    err_1 -= doubleAbsDz;
                }
                if (err_2 > 0) {
                    pixel = pixel.east(x_inc);
                    err_2 -= doubleAbsDz;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDx;
                pixel = pixel.south(z_inc);
            }
        }
        lineArray[lineArray.length - 1] = pixel;

        return lineArray;
    }

    public static void buildRoot(LevelAccessor world, BiConsumer<BlockPos, BlockState> placer, Random rand, BlockPos start, double offset, int b, BlockStateProvider config) {
        BlockPos dest = FeatureLogic.translate(start.below(b + 2), 5, 0.3 * b + offset, 0.8);

        for (BlockPos coord : new BresenhamIterator(start.below(), dest)) {
            if (!placeIfValidRootPos(world, placer, rand, coord, config)) return;
        }
    }

    // Use for trunks with Odd-count widths
    public static void placeCircleOdd(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, BiFunction<LevelSimulatedReader, BlockPos, Boolean> predicate, Random random, BlockPos centerPos, float radius, BlockStateProvider config) {
        // Normally, I'd use mutable pos here but there are multiple bits of logic down the line that force
        // the pos to be immutable causing multiple same BlockPos instances to exist.
        float radiusSquared = radius * radius;
        FeaturePlacer.placeProvidedBlock(world, placer, predicate, centerPos, config, random);

        // trace out a quadrant
        for (int x = 0; x <= radius; x++) {
            for (int z = 1; z <= radius; z++) {
                // if we're inside the blob, fill it
                if (x * x + z * z <= radiusSquared) {
                    // do four at a time for easiness!
                    FeaturePlacer.placeProvidedBlock(world, placer, predicate, centerPos.offset(  x, 0,  z), config, random);
                    FeaturePlacer.placeProvidedBlock(world, placer, predicate, centerPos.offset( -x, 0, -z), config, random);
                    FeaturePlacer.placeProvidedBlock(world, placer, predicate, centerPos.offset( -z, 0,  x), config, random);
                    FeaturePlacer.placeProvidedBlock(world, placer, predicate, centerPos.offset(  z, 0, -x), config, random);
                    // Confused how this circle pixel-filling algorithm works exactly? https://www.desmos.com/calculator/psqynhk21k
                }
            }
        }
    }

    /**
     * Checks an area to see if it consists of flat natural ground below and air above
     */
    public static boolean placeIfValidTreePos(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, Random random, BlockPos pos, BlockStateProvider config) {
        if (TreeFeature.validTreePos(world, pos)) {
            placer.accept(pos, config.getState(random, pos));
            return true;
        } else {
            return false;
        }
    }

    public static boolean placeIfValidRootPos(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, Random random, BlockPos pos, BlockStateProvider config) {
        if (FeatureLogic.canRootGrowIn(world, pos)) {
            placer.accept(pos, config.getState(random, pos));
            return true;
        } else {
            return false;
        }
    }

    public static void traceRoot(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, BlockStateProvider dirtRoot, Iterable<BlockPos> posTracer) {
        // Trace block positions and stop tracing too far into open air
        for (BlockPos rootPos : posTracer) {
            if (worldReader.isStateAtPosition(rootPos, FeatureLogic.ROOT_SHOULD_SKIP))
                continue; // Ignore pos if this block should be checked (root, or one of the protected block IDs)

            // If the block/position cannot be replaced or is detached from ground-mass, stop
            if (!FeaturePlacer.placeIfValidRootPos(worldReader, worldPlacer, random, rootPos, dirtRoot))
                return;
        }
    }

    public static void traceExposedRoot(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, BlockStateProvider exposedRoot, BlockStateProvider dirtRoot, Iterable<BlockPos> posTracer) {
        // Trace block positions and alternate the root tracing once "underground"
        for (BlockPos exposedPos : posTracer) {
            if (worldReader.isStateAtPosition(exposedPos, FeatureLogic.ROOT_SHOULD_SKIP))
                continue;

            // Is the position considered underground?
            if (!FeatureLogic.hasEmptyHorizontalNeighbor(worldReader, exposedPos)) {
                // Retry placement at position as underground root. If successful, continue the tracing as regular root
                if (FeaturePlacer.placeIfValidRootPos(worldReader, worldPlacer, random, exposedPos, dirtRoot))
                    traceRoot(worldReader, worldPlacer, random, dirtRoot, posTracer);
                // Now the outer loop can end. Goodbye!
                return;
            } else { // Not underground
                // Check if the position is not replaceable
                if (!worldReader.isStateAtPosition(exposedPos, FeatureLogic::worldGenReplaceable))
                    return; // Root must stop

                // Good to go!
                worldPlacer.accept(exposedPos, exposedRoot.getState(random, exposedPos));
            }
        }
    }

    public static boolean isNearSolid(LevelAccessor world, BlockPos pos) {
        for (Direction e : Direction.values()) {
            if (world.hasChunkAt(pos.relative(e))
                    && world.getBlockState(pos.relative(e)).getMaterial().isSolid()) {
                return true;
            }
        }

        return false;
    }
}