package com.greatorator.tolkienmobs.world.components.placements;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.utils.FeaturePlacerUtility;
import com.greatorator.tolkienmobs.world.components.config.BranchesConfig;
import com.greatorator.tolkienmobs.world.components.config.TreeFeatureConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BranchingTrunkPlacement extends TrunkPlacer {
    public static final Codec<BranchingTrunkPlacement> CODEC = RecordCodecBuilder.create(instance ->
        trunkPlacerParts(instance).and(instance.group(
                Codec.intRange(0, 24).fieldOf("branch_start_offset_down").forGetter(o -> o.branchDownwardOffset),
                BranchesConfig.CODEC.fieldOf("branch_config").forGetter(o -> o.branchesConfig),
                Codec.BOOL.fieldOf("perpendicular_branches").forGetter(o -> o.perpendicularBranches)
        )).apply(instance, BranchingTrunkPlacement::new)
);

    private final int branchDownwardOffset;
    private final BranchesConfig branchesConfig;
    private final boolean perpendicularBranches;

    public BranchingTrunkPlacement(int baseHeight, int randomHeightA, int randomHeightB, int branchDownwardOffset, BranchesConfig branchesConfig, boolean perpendicularBranches) {
        super(baseHeight, randomHeightA, randomHeightB);
        this.branchDownwardOffset = branchDownwardOffset;
        this.branchesConfig = branchesConfig;
        this.perpendicularBranches = perpendicularBranches;
    }

    @Override
    protected TrunkPlacerType<BranchingTrunkPlacement> type() {
        return TreeFeatureConfig.TRUNK_BRANCHING.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> consumer, Random random, int height, BlockPos startPos, TreeConfiguration baseTreeFeatureConfig) {
        setDirtAt(world, consumer, random, startPos.below(), baseTreeFeatureConfig);
        List<FoliagePlacer.FoliageAttachment> leafBlocks = Lists.newArrayList();
        BlockPos.MutableBlockPos mutableBoundingBox = new BlockPos.MutableBlockPos();

        for (int y = 0; y <= height; y++) { // Keep building upwards until we cannot, and then adjust height if we run into something
            if (!placeLog(world, consumer, random, mutableBoundingBox, baseTreeFeatureConfig)) {
                height = y;
                break;
            }
        }

        leafBlocks.add(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));

        int numBranches = branchesConfig.branchCount + random.nextInt(branchesConfig.randomAddBranches + 1);
        float offset = random.nextFloat();
        for (int b = 0; b < numBranches; b++) {
            buildBranch(world, startPos, consumer, leafBlocks, height - branchDownwardOffset + b, branchesConfig.length, branchesConfig.spacingYaw * b + offset, branchesConfig.downwardsPitch, random, mutableBoundingBox, baseTreeFeatureConfig, perpendicularBranches);
        }

        return leafBlocks;
    }


    private static void buildBranch(LevelSimulatedReader world, BlockPos pos, BiConsumer<BlockPos, BlockState> trunkBlocks, List<FoliagePlacer.FoliageAttachment> leafBlocks, int height, double length, double angle, double tilt, Random treeRNG, BlockPos.MutableBlockPos mbb, TreeConfiguration config, boolean perpendicularBranches) {
        BlockPos src = pos.above(height);
        BlockPos dest = FeaturePlacerUtility.translate(src, length, angle, tilt);
        BlockPos.MutableBlockPos mutableBoundingBox = new BlockPos.MutableBlockPos();

        if (perpendicularBranches) {
            drawBresenhamBranch(world, treeRNG, src, new BlockPos(dest.getX(), src.getY(), dest.getZ()), trunkBlocks, mbb, config);

            int max = Math.max(src.getY(), dest.getY());

            for (int i = Math.min(src.getY(), dest.getY()); i < max + 1; i++) {
                placeLog(world, trunkBlocks, treeRNG, mutableBoundingBox.set(dest.getX(), i, dest.getZ()), config);
            }
        } else {
            drawBresenhamBranch(world, treeRNG, src, dest, trunkBlocks, mbb, config);
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
