package com.greatorator.tolkienmobs.world.gen.placers;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.utils.BresenhamIteratorUtility;
import com.greatorator.tolkienmobs.utils.FeatureLogicUtility;
import com.greatorator.tolkienmobs.utils.FeaturePlacerUtility;
import com.greatorator.tolkienmobs.world.gen.feature.config.BranchesConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.RootConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TreeFeatureConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BranchingLargeTrunkPlacer extends TrunkPlacer {
    public static final Codec<BranchingLargeTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
        trunkPlacerParts(instance).and(instance.group(
                Codec.intRange(0, 24).fieldOf("branch_start_offset_down").forGetter(o -> o.branchDownwardOffset),
                BranchesConfig.CODEC.fieldOf("branch_config").forGetter(o -> o.branchesConfig),
                RootConfig.CODEC.fieldOf("root_config").forGetter(o -> o.rootsConfig),
                Codec.BOOL.fieldOf("perpendicular_branches").forGetter(o -> o.perpendicularBranches)
        )).apply(instance, BranchingLargeTrunkPlacer::new)
);

    private final int branchDownwardOffset;
    private final BranchesConfig branchesConfig;
    private final RootConfig rootsConfig;
    private final boolean perpendicularBranches;

    public BranchingLargeTrunkPlacer(int baseHeight, int randomHeightA, int randomHeightB, int branchDownwardOffset, BranchesConfig branchesConfig, RootConfig rootsConfig, boolean perpendicularBranches) {
        super(baseHeight, randomHeightA, randomHeightB);
        this.branchDownwardOffset = branchDownwardOffset;
        this.branchesConfig = branchesConfig;
        this.rootsConfig = rootsConfig;
        this.perpendicularBranches = perpendicularBranches;
    }

    @Override
    protected TrunkPlacerType<BranchingLargeTrunkPlacer> type() {
        return TreeFeatureConfig.TRUNK_BRANCHING_LARGE.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> consumer, Random random, int height, BlockPos startPos, TreeConfiguration baseTreeFeatureConfig) {
        List<FoliagePlacer.FoliageAttachment> leafBlocks = Lists.newArrayList();
        List<BlockPos> rootBlocks = Lists.newArrayList();
        setDirtAt(world, consumer, random, startPos.below(), baseTreeFeatureConfig);
        setDirtAt(world, consumer, random, startPos.below().east(), baseTreeFeatureConfig);
        setDirtAt(world, consumer, random, startPos.below().south(), baseTreeFeatureConfig);
        setDirtAt(world, consumer, random, startPos.below().south().east(), baseTreeFeatureConfig);
        BlockPos.MutableBlockPos mutableBoundingBox = new BlockPos.MutableBlockPos();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
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
                lvt_16_1_ += direction.getStepX();
                lvt_17_1_ += direction.getStepZ();
                --lvt_12_1_;
            }

            lvt_20_2_ = lvt_14_1_ + lvt_19_2_;
            BlockPos lvt_21_1_ = new BlockPos(lvt_16_1_, lvt_20_2_, lvt_17_1_);
            if (TreeFeature.isAirOrLeaves(world, lvt_21_1_)) {
                placeLog(world, consumer, random, lvt_21_1_, baseTreeFeatureConfig);
                placeLog(world, consumer, random, lvt_21_1_.east(), baseTreeFeatureConfig);
                placeLog(world, consumer, random, lvt_21_1_.south(), baseTreeFeatureConfig);
                placeLog(world, consumer, random, lvt_21_1_.east().south(), baseTreeFeatureConfig);
            }
        }

        leafBlocks.add(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
        rootBlocks.add(new BlockPos(startPos));

        int numBranches = branchesConfig.branchCount + random.nextInt(branchesConfig.randomAddBranches + 1);
        float offset = random.nextFloat();
        for (int b = 0; b < numBranches; b++) {
            buildBranch(world, startPos, consumer, leafBlocks, height - branchDownwardOffset + b, branchesConfig.length, branchesConfig.spacingYaw * b + offset, branchesConfig.downwardsPitch, random, mutableBoundingBox, baseTreeFeatureConfig, perpendicularBranches);
        }

        int numRoots = rootsConfig.strands + random.nextInt(rootsConfig.addExtraStrands);
        for (int b = 0; b < numRoots; b++) {
            buildRoot(world, consumer, random, rootBlocks);
        }

        return leafBlocks;
    }

    public void buildRoot(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, List<BlockPos> trunkBlocks) {
        if (trunkBlocks.isEmpty())
            return;

        int numBranches = rootsConfig.strands + random.nextInt(rootsConfig.addExtraStrands + 1);
        float offset = random.nextFloat();
        BlockPos startPos = trunkBlocks.get(0);

        if (rootsConfig.hasSurfaceRoots) {
            for (int i = 0; i < numBranches; i++) {
                BlockPos dest = FeatureLogicUtility.translate(startPos.below(i + 2), rootsConfig.length, 0.3 * i + (double) offset, 0.8);

                FeaturePlacerUtility.traceExposedRoot(worldReader, worldPlacer, random, rootsConfig.surfaceBlock, rootsConfig.rootBlock, new BresenhamIteratorUtility(startPos.below(), dest));
            }
        } else {
            for (int i = 0; i < numBranches; i++) {
                BlockPos dest = FeatureLogicUtility.translate(startPos.below(i + 2), rootsConfig.length, 0.3 * i + (double) offset, 0.8);

                FeaturePlacerUtility.traceRoot(worldReader, worldPlacer, random, rootsConfig.rootBlock, new BresenhamIteratorUtility(startPos.below(), dest));
            }
        }
    }



    private static void buildBranch(LevelSimulatedReader world, BlockPos pos, BiConsumer<BlockPos, BlockState> trunkBlocks, List<FoliagePlacer.FoliageAttachment> leafBlocks, int height, double length, double angle, double tilt, Random treeRNG, BlockPos.MutableBlockPos mbb, TreeConfiguration config, boolean perpendicularBranches) {
        BlockPos src = pos.above(height);
        BlockPos dest = FeaturePlacerUtility.translate(src, length, angle, tilt);
        BlockPos.MutableBlockPos mutableBoundingBox = new BlockPos.MutableBlockPos();

        if (perpendicularBranches) {
            drawBresenhamBranch(world, treeRNG, src, new BlockPos(dest.getX(), src.getY(), dest.getZ()), trunkBlocks, mutableBoundingBox, config);

            int max = Math.max(src.getY(), dest.getY());

            for (int i = Math.min(src.getY(), dest.getY()); i < max + 1; i++) {
                placeLog(world, trunkBlocks, treeRNG, mutableBoundingBox.set(dest.getX(), i, dest.getZ()), config);
            }
        } else {
            drawBresenhamBranch(world, treeRNG, src, dest, trunkBlocks, mutableBoundingBox, config);
        }

        placeLog(world, trunkBlocks, treeRNG, dest.east(), config);
        placeLog(world, trunkBlocks, treeRNG, dest.west(), config);
        placeLog(world, trunkBlocks, treeRNG, dest.south(), config);
        placeLog(world, trunkBlocks, treeRNG, dest.north(), config);

        leafBlocks.add(new FoliagePlacer.FoliageAttachment(dest, 0, false));
    }

    private static void drawBresenhamBranch(LevelSimulatedReader world, Random random, BlockPos from, BlockPos to, BiConsumer<BlockPos, BlockState> state, BlockPos.MutableBlockPos mbb, TreeConfiguration config) {
        for (BlockPos pixel : FeaturePlacerUtility.getBresenhamArrays(from, to)) {
            placeLog(world, state, random, pixel, config);
        }
    }
}
