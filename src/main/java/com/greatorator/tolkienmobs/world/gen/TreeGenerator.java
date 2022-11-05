package com.greatorator.tolkienmobs.world.gen;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.greatorator.tolkienmobs.world.gen.feature.config.TreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class TreeGenerator<T extends TreeFeatureConfig> extends Feature<T> {

    public TreeGenerator(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public final boolean place(FeaturePlaceContext<T> context) {
        WorldGenLevel contextWorldGenLevel = context.level();
        Random contextRandom = context.random();
        BlockPos contextBlockPos = context.origin();
        T contextConfig = context.config();
        Set<BlockPos> trunkSet = Sets.newHashSet();
        Set<BlockPos> leavesSet = Sets.newHashSet();
        Set<BlockPos> decorationSet = Sets.newHashSet();
        BiConsumer<BlockPos, BlockState> trunkPlacer = (pos, state) -> {
            trunkSet.add(pos.immutable());
            contextWorldGenLevel.setBlock(pos, state, 19);
        };
        BiConsumer<BlockPos, BlockState> leavesPlacer = (pos, state) -> {
            leavesSet.add(pos.immutable());
            contextWorldGenLevel.setBlock(pos, state, 19);
        };
        BiConsumer<BlockPos, BlockState> decorationPlacer = (pos, state) -> {
            decorationSet.add(pos.immutable());
            contextWorldGenLevel.setBlock(pos, state, 19);
        };

        if (this.generate(contextWorldGenLevel, contextRandom, contextBlockPos, trunkPlacer, leavesPlacer, decorationPlacer, contextConfig) && (!trunkSet.isEmpty() || !leavesSet.isEmpty())) {
            if (!contextConfig.decorators.isEmpty()) {
                List<BlockPos> trunkList = Lists.newArrayList(trunkSet);
                List<BlockPos> leavesList = Lists.newArrayList(leavesSet);
                trunkList.sort(Comparator.comparingInt(Vec3i::getY));
                leavesList.sort(Comparator.comparingInt(Vec3i::getY));
                contextConfig.decorators.forEach(treeDecorator -> treeDecorator.place(contextWorldGenLevel, decorationPlacer, contextRandom, trunkList, leavesList));
            }

            return BoundingBox.encapsulatingPositions(Iterables.concat(trunkSet, leavesSet, decorationSet)).map(boundingBox -> {
                DiscreteVoxelShape voxelShape = TreeFeature.updateLeaves(contextWorldGenLevel, boundingBox, trunkSet, decorationSet);
                StructureTemplate.updateShapeAtEdge(contextWorldGenLevel, 3, voxelShape, boundingBox.minX(), boundingBox.minY(), boundingBox.minZ());
                return true;
            }).orElse(false);
        } else {
            return false;
        }
    }
    protected abstract boolean generate(WorldGenLevel world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunkPlacer, BiConsumer<BlockPos, BlockState> leavesPlacer, BiConsumer<BlockPos, BlockState> decorationPlacer, T config);
}
