package com.greatorator.tolkienmobs.world.components.feature.templates;

import com.greatorator.tolkienmobs.utils.BresenhamIteratorUtility;
import com.greatorator.tolkienmobs.utils.FeatureLogicUtility;
import com.greatorator.tolkienmobs.utils.FeaturePlacerUtility;
import com.greatorator.tolkienmobs.world.components.config.RootConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Random;

public class RootTemplate extends Feature<RootConfig> {
    public RootTemplate(Codec<RootConfig> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<RootConfig> ctx) {
        WorldGenLevel world = ctx.level();
        BlockPos pos = ctx.origin();
        Random rand = ctx.random();

        if (world.getBlockState(pos).getBlock() != Blocks.STONE) {
            return false;
        }

        float length = rand.nextFloat() * 6.0F + rand.nextFloat() * 6.0F + 4.0F;
        if (length > pos.getY()) {
            length = pos.getY();
        }

        float tilt = 0.6F + rand.nextFloat() * 0.3F;

        return drawRoot(world, rand, pos, pos, length, rand.nextFloat(), tilt, ctx.config().blockRoot(), ctx.config().oreRoot());
    }

    private boolean drawRoot(LevelAccessor world, Random rand, BlockPos oPos, BlockPos pos, float length, float angle, float tilt, BlockStateProvider rootBlock, BlockStateProvider oreBlock) {
        BlockPos dest = FeatureLogicUtility.translate(pos, length, angle, tilt);

        int limit = 6;
        if (oPos.getX() + limit < dest.getX()) {
            dest = new BlockPos(oPos.getX() + limit, dest.getY(), dest.getZ());
        }
        if (oPos.getX() - limit > dest.getX()) {
            dest = new BlockPos(oPos.getX() - limit, dest.getY(), dest.getZ());
        }
        if (oPos.getZ() + limit < dest.getZ()) {
            dest = new BlockPos(dest.getX(), dest.getY(), oPos.getZ() + limit);
        }
        if (oPos.getZ() - limit > dest.getZ()) {
            dest = new BlockPos(dest.getX(), dest.getY(), oPos.getZ() - limit);
        }

        if (world.getBlockState(dest).getBlock() != Blocks.STONE) {
            return false;
        }

        FeaturePlacerUtility.traceRoot(world, (checkedPos, rootPlacement) -> world.setBlock(checkedPos, rootPlacement, 3), rand, rootBlock, new BresenhamIteratorUtility(pos, dest));

        if (length > 8) {
            if (rand.nextInt(3) > 0) {
                BlockPos nextSrc = FeatureLogicUtility.translate(pos, length / 2, angle, tilt);
                float nextAngle = (angle + 0.25F + (rand.nextFloat() * 0.5F)) % 1.0F;
                float nextTilt = 0.6F + rand.nextFloat() * 0.3F;
                drawRoot(world, rand, oPos, nextSrc, length / 2.0F, nextAngle, nextTilt, rootBlock, oreBlock);
            }
        }

        if (length > 6) {
            if (rand.nextInt(4) == 0) {
                BlockPos ballSrc = FeatureLogicUtility.translate(pos, length / 2, angle, tilt);
                BlockPos ballDest = FeatureLogicUtility.translate(ballSrc, 1.5, (angle + 0.5F) % 1.0F, 0.75);

                this.placeRootBlock(world, ballSrc, oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballSrc.getX(), ballSrc.getY(), ballDest.getZ()), oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballDest.getX(), ballSrc.getY(), ballSrc.getZ()), oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballSrc.getX(), ballSrc.getY(), ballDest.getZ()), oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballSrc.getX(), ballDest.getY(), ballSrc.getZ()), oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballSrc.getX(), ballDest.getY(), ballDest.getZ()), oreBlock, rand);
                this.placeRootBlock(world, new BlockPos(ballDest.getX(), ballDest.getY(), ballSrc.getZ()), oreBlock, rand);
                this.placeRootBlock(world, ballDest, oreBlock, rand);
            }
        }

        return true;
    }

    protected boolean placeRootBlock(LevelAccessor world, BlockPos pos, BlockStateProvider state, Random random) {
        return FeatureLogicUtility.canRootGrowIn(world, pos) && world.setBlock(pos, state.getState(random, pos), 3);
    }
}