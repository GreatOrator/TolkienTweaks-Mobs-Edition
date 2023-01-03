package com.greatorator.tolkienmobs.world.components.feature.templates;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.utils.FeatureUtility;
import com.greatorator.tolkienmobs.world.components.config.FallenLogConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.Random;

public class FallenLogTemplate extends Feature<FallenLogConfig> {
    public FallenLogTemplate(Codec<FallenLogConfig> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenLogConfig> ctx) {
        WorldGenLevel world = ctx.level();
        BlockPos pos = ctx.origin();
        Random rand = ctx.random();
        FallenLogConfig config = ctx.config();
        boolean shouldMakeAllHollow = rand.nextBoolean();

        // determine direction
        boolean goingX = rand.nextBoolean();

        // length
        int length = rand.nextInt(4) + 3;

        // check area clear
        if (goingX) {
            if (!FeatureUtility.isAreaSuitable(world, pos, length, 2, 2, true)) {
                return false;
            }
        } else {
            if (!FeatureUtility.isAreaSuitable(world, pos, 2, 2, length, true)) {
                return false;
            }
        }

        // determine wood type
        BlockState logState = config.normal();
        @Nullable
        BlockState hollowLogState = config.hollow();
        BlockState branchState;

        if(config.hollow().isAir()) hollowLogState = null;

        //sometimes make floating logs
        if(rand.nextInt(5) == 0 && world.getBlockState(pos).getMaterial() == Material.WATER) {
            BlockPos.MutableBlockPos floatingPos = pos.mutable();
            for(int i = 0; i < 10; i++) {
                if(world.getBlockState(floatingPos.above()).isAir()) {
                    pos = floatingPos.immutable();
                    break;
                } else {
                    floatingPos.move(0, 1, 0);
                }
            }
        }

        switch (rand.nextInt(7)) {
            case 0:
            default:
                logState = TolkienBlocks.LOG_MALLORN.get().defaultBlockState();
                break;
            case 1:
                logState = TolkienBlocks.LOG_MIRKWOOD.get().defaultBlockState();
                break;
            case 2:
                logState = TolkienBlocks.LOG_CULUMALDA.get().defaultBlockState();
                break;
            case 3:
                logState = TolkienBlocks.LOG_LEBETHRON.get().defaultBlockState();
                break;
            case 4:
                logState = TolkienBlocks.LOG_FANGORNOAK.get().defaultBlockState();
                break;
            case 5:
                logState = TolkienBlocks.LOG_DEADWOOD.get().defaultBlockState();
                break;
            case 6:
                logState = Blocks.BIRCH_LOG.defaultBlockState();
                break;
        }


        // make log
        if (goingX) {
            logState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);
            branchState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);

            for (int lx = 0; lx < length; lx++) {
                world.setBlock(pos.offset(lx, 0, 1), hollowOrNormal(world, shouldMakeAllHollow, hollowLogState, logState), 3);
                if (rand.nextInt(3) > 0) {
                    world.setBlock(pos.offset(lx, 1, 1), mossOrSeagrass(world, pos.offset(lx, 1, 1)), 3);
                    this.markAboveForPostProcessing(world, pos.offset(lx, 0, 1));
                }
            }
        } else {
            logState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
            branchState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);

            for (int lz = 0; lz < length; lz++) {
                world.setBlock(pos.offset(1, 0, lz), hollowOrNormal(world, shouldMakeAllHollow, hollowLogState, logState), 3);
                if (rand.nextInt(3) > 0) {
                    world.setBlock(pos.offset(1, 1, lz), mossOrSeagrass(world, pos.offset(1, 1, lz)), 3);
                    this.markAboveForPostProcessing(world, pos.offset(1, 0, lz));
                }
            }
        }

        // possibly make branch
        if (rand.nextInt(3) > 0) {
            int bx;
            int bz;
            if (goingX) {
                bx = rand.nextInt(length);
                bz = rand.nextBoolean() ? 2 : 0;
            } else {
                bx = rand.nextBoolean() ? 2 : 0;
                bz = rand.nextInt(length);

            }
            world.setBlock(pos.offset(bx, 0, bz), branchState, 3);
            if (rand.nextBoolean()) {
                world.setBlock(pos.offset(bx, 1, bz), mossOrSeagrass(world, pos.offset(bx, 1, bz)), 3);
                this.markAboveForPostProcessing(world, pos.offset(bx, 0, bz));
            }
        }

        return true;
    }

    private BlockState mossOrSeagrass(WorldGenLevel level, BlockPos pos) {
        if(level.getBlockState(pos.below(2)).getMaterial() == Material.SNOW) {
            return Blocks.AIR.defaultBlockState();
        }
        return level.getBlockState(pos).getMaterial() == Material.WATER ? Blocks.SEAGRASS.defaultBlockState() : Blocks.MOSS_BLOCK.defaultBlockState();
    }

    private BlockState hollowOrNormal(WorldGenLevel level, boolean shouldBeHollow, BlockState hollow, BlockState normal) {
        return ((shouldBeHollow || level.getRandom().nextInt(3) == 0) && hollow != null) ? hollow : normal;
    }
}