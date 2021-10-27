package com.greatorator.tolkienmobs.world.gen.placers;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.utils.TTMFeatureUtil;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMBranchesConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class TTMBranchingLargeTrunkPlacer extends AbstractTrunkPlacer {public static final Codec<TTMBranchingLargeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
        trunkPlacerParts(instance).and(instance.group(
                Codec.intRange(0, 24).fieldOf("branch_start_offset_down").forGetter(o -> o.branchDownwardOffset),
                TTMBranchesConfig.CODEC.fieldOf("branch_config").forGetter(o -> o.branchesConfig),
                Codec.BOOL.fieldOf("perpendicular_branches").forGetter(o -> o.perpendicularBranches)
        )).apply(instance, TTMBranchingLargeTrunkPlacer::new)
);

    private final int branchDownwardOffset;
    private final TTMBranchesConfig branchesConfig;
    private final boolean perpendicularBranches;

    public TTMBranchingLargeTrunkPlacer(int baseHeight, int randomHeightA, int randomHeightB, int branchDownwardOffset, TTMBranchesConfig branchesConfig, boolean perpendicularBranches) {
        super(baseHeight, randomHeightA, randomHeightB);
        this.branchDownwardOffset = branchDownwardOffset;
        this.branchesConfig = branchesConfig;
        this.perpendicularBranches = perpendicularBranches;
    }

    @Override
    protected TrunkPlacerType<TTMBranchingLargeTrunkPlacer> type() {
        return TTMTreeFeatureConfig.TRUNK_BRANCHING_LARGE;
    }

    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(IWorldGenerationReader world, Random random, int height, BlockPos startPos, Set<BlockPos> trunkBlocks, MutableBoundingBox mutableBoundingBox, BaseTreeFeatureConfig baseTreeFeatureConfig) {
        List<FoliagePlacer.Foliage> leafBlocks = Lists.newArrayList();
        BlockPos lvt_9_1_ = startPos.below();
        setDirtAt(world, lvt_9_1_);
        setDirtAt(world, lvt_9_1_.east());
        setDirtAt(world, lvt_9_1_.south());
        setDirtAt(world, lvt_9_1_.south().east());
        Direction lvt_10_1_ = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int lvt_11_1_ = height - random.nextInt(4);
        int lvt_12_1_ = 2 - random.nextInt(3);
        int lvt_13_1_ = startPos.getX();
        int lvt_14_1_ = startPos.getY();
        int lvt_15_1_ = startPos.getZ();
        int lvt_16_1_ = lvt_13_1_;
        int lvt_17_1_ = lvt_15_1_;

        int lvt_19_2_;
        int lvt_20_2_;
        for(lvt_19_2_ = 0; lvt_19_2_ < height; ++lvt_19_2_) {
            if (lvt_19_2_ >= lvt_11_1_ && lvt_12_1_ > 0) {
                lvt_16_1_ += lvt_10_1_.getStepX();
                lvt_17_1_ += lvt_10_1_.getStepZ();
                --lvt_12_1_;
            }

            lvt_20_2_ = lvt_14_1_ + lvt_19_2_;
            BlockPos lvt_21_1_ = new BlockPos(lvt_16_1_, lvt_20_2_, lvt_17_1_);
            if (TreeFeature.isAirOrLeaves(world, lvt_21_1_)) {
                placeLog(world, random, lvt_21_1_, trunkBlocks, mutableBoundingBox, baseTreeFeatureConfig);
                placeLog(world, random, lvt_21_1_.east(), trunkBlocks, mutableBoundingBox, baseTreeFeatureConfig);
                placeLog(world, random, lvt_21_1_.south(), trunkBlocks, mutableBoundingBox, baseTreeFeatureConfig);
                placeLog(world, random, lvt_21_1_.east().south(), trunkBlocks, mutableBoundingBox, baseTreeFeatureConfig);
            }
        }

        leafBlocks.add(new FoliagePlacer.Foliage(startPos.above(height), 0, false));

        int numBranches = branchesConfig.branchCount + random.nextInt(branchesConfig.randomAddBranches + 1);
        float offset = random.nextFloat();
        for (int b = 0; b < numBranches; b++) {
            buildBranch(world, startPos, trunkBlocks, leafBlocks, height - branchDownwardOffset + b, branchesConfig.length, branchesConfig.spacingYaw * b + offset, branchesConfig.downwardsPitch, random, mutableBoundingBox, baseTreeFeatureConfig, perpendicularBranches);
        }

        return leafBlocks;
    }


    private static void buildBranch(IWorldGenerationReader world, BlockPos pos, Set<BlockPos> trunkBlocks, List<FoliagePlacer.Foliage> leafBlocks, int height, double length, double angle, double tilt, Random treeRNG, MutableBoundingBox mbb, BaseTreeFeatureConfig config, boolean perpendicularBranches) {
        BlockPos src = pos.above(height);
        BlockPos dest = TTMFeatureUtil.translate(src, length, angle, tilt);

        if (perpendicularBranches) {
            drawBresenhamBranch(world, treeRNG, src, new BlockPos(dest.getX(), src.getY(), dest.getZ()), trunkBlocks, mbb, config);

            int max = Math.max(src.getY(), dest.getY());

            for (int i = Math.min(src.getY(), dest.getY()); i < max + 1; i++) {
                placeLog(world, treeRNG, new BlockPos(dest.getX(), i, dest.getZ()), trunkBlocks, mbb, config);
            }
        } else {
            drawBresenhamBranch(world, treeRNG, src, dest, trunkBlocks, mbb, config);
        }

        placeLog(world, treeRNG, dest.east(), trunkBlocks, mbb, config);
        placeLog(world, treeRNG, dest.west(), trunkBlocks, mbb, config);
        placeLog(world, treeRNG, dest.south(), trunkBlocks, mbb, config);
        placeLog(world, treeRNG, dest.north(), trunkBlocks, mbb, config);

        leafBlocks.add(new FoliagePlacer.Foliage(dest, 0, false));
    }

    private static void drawBresenhamBranch(IWorldGenerationReader world, Random random, BlockPos from, BlockPos to, Set<BlockPos> state, MutableBoundingBox mbb, BaseTreeFeatureConfig config) {
        for (BlockPos pixel : TTMFeatureUtil.getBresenhamArrays(from, to)) {
            placeLog(world, random, pixel, state, mbb, config);
        }
    }
}
