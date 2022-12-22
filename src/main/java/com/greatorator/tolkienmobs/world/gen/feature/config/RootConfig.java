package com.greatorator.tolkienmobs.world.gen.feature.config;

import com.greatorator.tolkienmobs.utils.BresenhamIteratorUtility;
import com.greatorator.tolkienmobs.utils.FeatureLogicUtility;
import com.greatorator.tolkienmobs.utils.FeaturePlacerUtility;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiConsumer;

public class RootConfig extends TreeDecorator {
    private static final SimpleStateProvider EMPTY = BlockStateProvider.simple(Blocks.AIR.defaultBlockState());
    public static final Codec<RootConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.intRange(0, 16).fieldOf("base_strand_count").forGetter(o -> o.strands),
                    Codec.intRange(0, 16).fieldOf("additional_random_strands").forGetter(o -> o.addExtraStrands),
                    Codec.intRange(0, 32).fieldOf("root_length").forGetter(o -> o.length),
                    BlockStateProvider.CODEC.optionalFieldOf("exposed_roots_provider").forGetter(o -> Optional.ofNullable(o.surfaceBlock != EMPTY ? o.surfaceBlock : null)),
                    BlockStateProvider.CODEC.fieldOf("ground_roots_provider").forGetter(o -> o.rootBlock)
            ).apply(instance, RootConfig::new)
    );
    public final int strands;
    public final int addExtraStrands;
    public final int length;
    public final BlockStateProvider surfaceBlock;
    public final BlockStateProvider rootBlock;

    public final boolean hasSurfaceRoots;

    private RootConfig(int count, int addExtraStrands, int length, Optional<BlockStateProvider> surfaceBlock, BlockStateProvider rootBlock) {
        this.strands = count;
        this.addExtraStrands = addExtraStrands;
        this.length = length;
        this.rootBlock = rootBlock;
        this.hasSurfaceRoots = surfaceBlock.isPresent();

        if (this.hasSurfaceRoots) {
            this.surfaceBlock = surfaceBlock.get();
        } else {
            this.surfaceBlock = EMPTY;
        }
    }

    public RootConfig(int count, int addExtraStrands, int length, BlockStateProvider rootBlock) {
        this.strands = count;
        this.addExtraStrands = addExtraStrands;
        this.length = length;
        this.rootBlock = rootBlock;
        this.hasSurfaceRoots = false;
        this.surfaceBlock = EMPTY;
    }

    public RootConfig(int count, int addExtraStrands, int length, BlockStateProvider surfaceBlock, BlockStateProvider rootBlock) {
        this.strands = count;
        this.addExtraStrands = addExtraStrands;
        this.length = length;
        this.rootBlock = rootBlock;
        this.hasSurfaceRoots = true;
        this.surfaceBlock = surfaceBlock;
    }

    @Override
    protected TreeDecoratorType<RootConfig> type() {
        return TreeFeatureConfig.TREE_ROOTS.get();
    }

    @Override
    public void place(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, List<BlockPos> trunkBlocks, List<BlockPos> leafBlocks) {
        if (trunkBlocks.isEmpty())
            return;

        int numBranches = this.strands + random.nextInt(this.addExtraStrands + 1);
        float offset = random.nextFloat();
        BlockPos startPos = trunkBlocks.get(0);

        if (this.hasSurfaceRoots) {
            for (int i = 0; i < numBranches; i++) {
                BlockPos dest = FeatureLogicUtility.translate(startPos.below(i + 2), this.length, 0.3 * i + (double) offset, 0.8);

                FeaturePlacerUtility.traceExposedRoot(worldReader, worldPlacer, random, this.surfaceBlock, this.rootBlock, new BresenhamIteratorUtility(startPos.below(), dest));
            }
        } else {
            for (int i = 0; i < numBranches; i++) {
                BlockPos dest = FeatureLogicUtility.translate(startPos.below(i + 2), this.length, 0.3 * i + (double) offset, 0.8);

                FeaturePlacerUtility.traceRoot(worldReader, worldPlacer, random, this.rootBlock, new BresenhamIteratorUtility(startPos.below(), dest));
            }
        }
    }
}
