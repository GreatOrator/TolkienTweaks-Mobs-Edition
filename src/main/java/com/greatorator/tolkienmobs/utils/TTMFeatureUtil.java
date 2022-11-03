package com.greatorator.tolkienmobs.utils;

import com.greatorator.tolkienmobs.world.gen.feature.TTMTreeGenerator;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.Set;

public class TTMFeatureUtil {
    public static void putLeafBlock(WorldGenLevel world, Random random, BlockPos pos, BlockStateProvider state, Set<BlockPos> leavesPos) {
        if (/*leavesPos.contains(pos) ||*/ !TreeFeature.validTreePos(world, pos))
            return;

        world.setBlock(pos, state.getState(random, pos), 3);
        leavesPos.add(pos.immutable());
    }

    public static void makeLeafCircle(WorldGenLevel world, Random random, BlockPos centerPos, float radius, BlockStateProvider state, Set<BlockPos> leaves) {
        // Normally, I'd use mutable pos here but there are multiple bits of logic down the line that force
        // the pos to be immutable causing multiple same BlockPos instances to exist.
        float radiusSquared = radius * radius;
        putLeafBlock(world, random, centerPos, state, leaves);

        // trace out a quadrant
        for (int x = 0; x <= radius; x++) {
            for (int z = 1; z <= radius; z++) {
                // if we're inside the blob, fill it
                if (x * x + z * z <= radiusSquared) {
                    // do four at a time for easiness!
                    putLeafBlock(world, random, centerPos.offset(  x, 0,  z), state, leaves);
                    putLeafBlock(world, random, centerPos.offset( -x, 0, -z), state, leaves);
                    putLeafBlock(world, random, centerPos.offset( -z, 0,  x), state, leaves);
                    putLeafBlock(world, random, centerPos.offset(  z, 0, -x), state, leaves);
                    // Confused how this circle pixel-filling algorithm works exactly? https://www.desmos.com/calculator/psqynhk21k
                }
            }
        }
    }

    public static void makeLeafSpheroid(WorldGenLevel world, Random random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias, BlockStateProvider state, Set<BlockPos> leaves) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        putLeafBlock(world, random, centerPos, state, leaves);

        for (int y = 0; y <= yRadius; y++) {
            if (y > yRadius) continue;

            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);

            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
        }

        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if (x * x + z * z > xzRadiusSquared) continue;

                putLeafBlock(world, random, centerPos.offset(  x, 0,  z), state, leaves);
                putLeafBlock(world, random, centerPos.offset( -x, 0, -z), state, leaves);
                putLeafBlock(world, random, centerPos.offset( -z, 0,  x), state, leaves);
                putLeafBlock(world, random, centerPos.offset(  z, 0, -x), state, leaves);

                for (int y = 1; y <= yRadius; y++) {
                    float xzSquare = ((x * x + z * z) * yRadiusSquared);

                    if (xzSquare + (((y - verticalBias) * (y - verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        putLeafBlock(world, random, centerPos.offset(  x,  y,  z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -x,  y, -z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -z,  y,  x), state, leaves);
                        putLeafBlock(world, random, centerPos.offset(  z,  y, -x), state, leaves);
                    }

                    if (xzSquare + (((y + verticalBias) * (y + verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        putLeafBlock(world, random, centerPos.offset(  x, -y,  z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -x, -y, -z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -z, -y,  x), state, leaves);
                        putLeafBlock(world, random, centerPos.offset(  z, -y, -x), state, leaves);
                    }
                }
            }
        }
    }

    public static void makeLeafSpheroid(WorldGenLevel world, Random random, BlockPos centerPos, float radius, BlockStateProvider state, Set<BlockPos> leaves) {
        float radiusSquared = radius * radius;
        putLeafBlock(world, random, centerPos, state, leaves);

        for (int y = 0; y <= radius; y++) {
            if (y > radius) continue;

            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0,  y, 0), state, leaves);

            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
            putLeafBlock(world, random, centerPos.offset( 0, -y, 0), state, leaves);
        }

        for (int x = 0; x <= radius; x++) {
            for (int z = 1; z <= radius; z++) {
                float xzSquare = x * x + z * z;

                if (xzSquare > radiusSquared) continue;

                putLeafBlock(world, random, centerPos.offset(  x, 0,  z), state, leaves);
                putLeafBlock(world, random, centerPos.offset( -x, 0, -z), state, leaves);
                putLeafBlock(world, random, centerPos.offset( -z, 0,  x), state, leaves);
                putLeafBlock(world, random, centerPos.offset(  z, 0, -x), state, leaves);

                for (int y = 1; y <= radius; y++) {

                    if (xzSquare + y * y <= radius * radius) {
                        putLeafBlock(world, random, centerPos.offset(  x,  y,  z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -x,  y, -z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -z,  y,  x), state, leaves);
                        putLeafBlock(world, random, centerPos.offset(  z,  y, -x), state, leaves);

                        putLeafBlock(world, random, centerPos.offset(  x, -y,  z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -x, -y, -z), state, leaves);
                        putLeafBlock(world, random, centerPos.offset( -z, -y,  x), state, leaves);
                        putLeafBlock(world, random, centerPos.offset(  z, -y, -x), state, leaves);
                    }
                }
            }
        }
    }

    public static boolean hasAirAround(WorldGenLevel world, BlockPos pos) {
        for (Direction e : directionsExceptDown) {
            if (world.isStateAtPosition(pos, b -> b.getBlock() instanceof AirBlock)) {
                return true;
            }
        }

        return false;
    }

    public static void drawBresenhamBranch(LevelAccessor world, Random random, BlockPos from, BlockPos to, Set<BlockPos> state, BlockPos.MutableBlockPos mbb, TreeConfiguration config) {
        for (BlockPos pixel : getBresenhamArrays(from, to)) {
            TrunkPlacer.placeLog(world, random, pixel, state, mbb, config);
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

    public static void drawBresenhamBranch(TTMTreeGenerator<? extends TTMTreeFeatureConfig> generator, LevelAccessor world, Random random, BlockPos from, BlockPos to, Set<BlockPos> state, BlockPos.MutableBlockPos mbb, TTMTreeFeatureConfig config) {
        for (BlockPos pixel : getBresenhamArrays(from, to)) {
            generator.setBranchBlockState(world, random, pixel, state, mbb, config);
            //world.setBlockState(pixel, state);
        }
    }

    public static void drawBresenhamTree(LevelAccessor world, BlockPos from, BlockPos to, BlockState state, Set<BlockPos> treepos) {
        for (BlockPos pixel : getBresenhamArrays(from, to)) {
            world.setBlock(pixel, state, 3);
            treepos.add(pixel.immutable());
        }
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

    public static void makeLeafCircle(LevelAccessor world, BlockPos pos, int rad, BlockState state, Set<BlockPos> leaves, boolean useHack) {
        // trace out a quadrant
        for (byte dx = 0; dx <= rad; dx++) {
            for (byte dz = 0; dz <= rad; dz++) {
                int dist = Math.max(dx, dz) + (Math.min(dx, dz) >> 1);

                if (useHack && dx == 3 && dz == 3) {
                    dist = 6;
                }

                if (dist <= rad) {
                    putLeafBlock(world, pos.offset(+dx, 0, +dz), state, leaves);
                    putLeafBlock(world, pos.offset(+dx, 0, -dz), state, leaves);
                    putLeafBlock(world, pos.offset(-dx, 0, +dz), state, leaves);
                    putLeafBlock(world, pos.offset(-dx, 0, -dz), state, leaves);
                }
            }
        }
    }

    public static void putLeafBlock(LevelAccessor world, BlockPos pos, BlockState state, Set<BlockPos> leavespos) {
        BlockState whatsThere = world.getBlockState(pos);

        if (whatsThere.isSolidRender(world, pos) && whatsThere.getBlock() != state.getBlock()) {
            world.setBlock(pos, state, 3);
            leavespos.add(pos.immutable());
        }
    }

    public static void makeLeafCircle2(LevelAccessor world, BlockPos pos, int rad, BlockState state, Set<BlockPos> leaves,  boolean useHack) {
        for (byte dx = 0; dx <= rad; dx++) {
            for (byte dz = 0; dz <= rad; dz++) {

                if (dx * dx + dz * dz <= rad * rad) {
                    putLeafBlock(world, pos.offset(1 + dx, 0, 1 + dz), state, leaves);
                    putLeafBlock(world, pos.offset(1 + dx, 0, -dz), state, leaves);
                    putLeafBlock(world, pos.offset(-dx, 0, 1 + dz), state, leaves);
                    putLeafBlock(world, pos.offset(-dx, 0, -dz), state, leaves);
                }
            }
        }
    }

    public static BlockState randStone(Random rand, int howMuch) {
        return rand.nextInt(howMuch) >= 1 ? Blocks.COBBLESTONE.defaultBlockState() : Blocks.MOSSY_COBBLESTONE.defaultBlockState();
    }

    /**
     * Checks an area to see if it consists of flat natural ground below and air above
     */
    public static boolean isAreaSuitable(LevelAccessor world, BlockPos pos, int width, int height, int depth) {
        boolean flag = true;

        // check if there's anything within the diameter
        for (int cx = 0; cx < width; cx++) {
            for (int cz = 0; cz < depth; cz++) {
                BlockPos pos_ = pos.offset(cx, 0, cz);
                // check if the blocks even exist?
                if (world.isEmptyBlock(pos_)) {
                    // is there grass, dirt or stone below?
                    Material m = world.getBlockState(pos_.below()).getMaterial();
                    if (m != Material.GRASS && m != Material.DIRT && m != Material.PLANT && m != Material.STONE) {
                        flag = false;
                    }

                    for (int cy = 0; cy < height; cy++) {
                        // blank space above?
                        if (!world.isEmptyBlock(pos_.above(cy))) {
                            flag = false;
                        }
                    }
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static void drawBlob(LevelAccessor world, BlockPos pos, int rad, BlockState state) {
        for (byte dx = 0; dx <= rad; dx++) {
            for (byte dy = 0; dy <= rad; dy++) {
                for (byte dz = 0; dz <= rad; dz++) {
                    int dist = 0;
                    if (dx >= dy && dx >= dz) {
                        dist = dx + (Math.max(dy, dz) >> 1) + (Math.min(dy, dz) >> 2);
                    } else if (dy >= dx && dy >= dz) {
                        dist = dy + (Math.max(dx, dz) >> 1) + (Math.min(dx, dz) >> 2);
                    } else {
                        dist = dz + (Math.max(dx, dy) >> 1) + (Math.min(dx, dy) >> 2);
                    }


                    if (dist <= rad) {
                        world.setBlock(pos.offset(+dx, +dy, +dz), state, 3);
                        world.setBlock(pos.offset(+dx, +dy, -dz), state, 3);
                        world.setBlock(pos.offset(-dx, +dy, +dz), state, 3);
                        world.setBlock(pos.offset(-dx, +dy, -dz), state, 3);
                        world.setBlock(pos.offset(+dx, -dy, +dz), state, 3);
                        world.setBlock(pos.offset(+dx, -dy, -dz), state, 3);
                        world.setBlock(pos.offset(-dx, -dy, +dz), state, 3);
                        world.setBlock(pos.offset(-dx, -dy, -dz), state, 3);
                    }
                }
            }
        }
    }

    public static void drawLeafBlob(LevelAccessor world, BlockPos pos, int rad, BlockState state, Set<BlockPos> leaves) {
        for (byte dx = 0; dx <= rad; dx++) {
            for (byte dy = 0; dy <= rad; dy++) {
                for (byte dz = 0; dz <= rad; dz++) {
                    int dist;
                    if (dx >= dy && dx >= dz) {
                        dist = dx + (Math.max(dy, dz) >> 1) + (Math.min(dy, dz) >> 2);
                    } else if (dy >= dx && dy >= dz) {
                        dist = dy + (Math.max(dx, dz) >> 1) + (Math.min(dx, dz) >> 2);
                    } else {
                        dist = dz + (Math.max(dx, dy) >> 1) + (Math.min(dx, dy) >> 2);
                    }

                    if (dist <= rad) {
                        putLeafBlock(world, pos.offset(+dx, +dy, +dz), state, leaves);
                        putLeafBlock(world, pos.offset(+dx, +dy, -dz), state, leaves);
                        putLeafBlock(world, pos.offset(-dx, +dy, +dz), state, leaves);
                        putLeafBlock(world, pos.offset(-dx, +dy, -dz), state, leaves);
                        putLeafBlock(world, pos.offset(+dx, -dy, +dz), state, leaves);
                        putLeafBlock(world, pos.offset(+dx, -dy, -dz), state, leaves);
                        putLeafBlock(world, pos.offset(-dx, -dy, +dz), state, leaves);
                        putLeafBlock(world, pos.offset(-dx, -dy, -dz), state, leaves);
                    }
                }
            }
        }
    }

    public static boolean surroundedByAir(LevelAccessor world, BlockPos pos) {
        for (Direction e : Direction.values()) {
            if (!world.isEmptyBlock(pos.relative(e))) {
                return false;
            }
        }

        return true;
    }

    private static final Direction[] directionsExceptDown = new Direction[]{Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public static boolean hasAirAround(LevelAccessor world, BlockPos pos) {
        for (Direction e : directionsExceptDown) {
            if (world.isEmptyBlock(pos.relative(e))) {
                return true;
            }
        }

        return false;
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

    public static void setBlockStateProvider(LevelAccessor world, BlockStateProvider provider, Random rand, BlockPos pos) {
        world.setBlock(pos, provider.getState(rand, pos), 3);
    }
}