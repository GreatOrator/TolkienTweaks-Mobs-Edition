package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.utils.TTMFeatureUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class TTMFallenLogFeature extends Feature<NoFeatureConfig> {
    public TTMFallenLogFeature(Codec<NoFeatureConfig> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        // determine direction
        boolean goingX = rand.nextBoolean();

        // length
        int length = rand.nextInt(4) + 3;

        // check area clear
        if (goingX) {
            if (!TTMFeatureUtil.isAreaSuitable(world, pos, length, 3, 2)) {
                return false;
            }
        } else {
            if (!TTMFeatureUtil.isAreaSuitable(world, pos, 3, length, 2)) {
                return false;
            }
        }

        // determine wood type
        BlockState logState;
        BlockState branchState;

        switch (rand.nextInt(7)) {
            case 0:
            default:
                logState = TTMContent.LOG_MALLORN.get().defaultBlockState();
                break;
            case 1:
                logState = TTMContent.LOG_MIRKWOOD.get().defaultBlockState();
                break;
            case 2:
                logState = TTMContent.LOG_CULUMALDA.get().defaultBlockState();
                break;
            case 3:
                logState = TTMContent.LOG_LEBETHRON.get().defaultBlockState();
                break;
            case 4:
                logState = Blocks.DARK_OAK_LOG.defaultBlockState();
                break;
            case 5:
                logState = TTMContent.LOG_DEADWOOD.get().defaultBlockState();
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
                world.setBlock(pos.offset(lx, 0, 1), logState, 3);
            }
        } else {
            logState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z);
            branchState = logState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X);

            for (int lz = 0; lz < length; lz++) {
                world.setBlock(pos.offset(1, 0, lz), logState, 3);
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
        }

        return true;
    }
}
