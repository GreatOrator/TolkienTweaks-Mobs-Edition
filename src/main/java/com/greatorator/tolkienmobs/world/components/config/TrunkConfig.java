package com.greatorator.tolkienmobs.world.components.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class TrunkConfig extends TreeDecorator {
    public static final Logger LOGGER = LogManager.getLogger("TolkienMobs");

    public static final Codec<TrunkConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.intRange(0, 64).fieldOf("placement_count").forGetter(o -> o.count),
                    Codec.floatRange(0f, 1f).fieldOf("probability_of_placement").forGetter(o -> o.probability),
                    BlockStateProvider.CODEC.fieldOf("deco_provider").forGetter(o -> o.decoration)
            ).apply(instance, TrunkConfig::new)
    );
    private final int count;
    private final float probability;
    private final BlockStateProvider decoration;

    public TrunkConfig(int count, float probability, BlockStateProvider decorator) {
        this.count = count;
        this.probability = probability;
        this.decoration = decorator;
    }

    @Override
    protected TreeDecoratorType<TrunkConfig> type() {
        return TreeFeatureConfig.TRUNK_DECORATOR.get();
    }

    @Override
    public void place(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, List<BlockPos> trunkBlocks, List<BlockPos> leafBlocks) {
        int blockCount = trunkBlocks.size();

        if (blockCount == 0) {
            LOGGER.error("[TrunkFeature] Trunk Blocks were empty! Why?");
            return;
        }

        for (int attempt = 0; attempt < this.count; attempt++) {
            if (random.nextFloat() >= this.probability) continue;

            Rotation rot = Rotation.getRandom(random);
            BlockPos pos = trunkBlocks.get(random.nextInt(blockCount)).relative(rot.rotate(Direction.NORTH));

            if (Feature.isAir(worldReader, pos)) // Checks if block is air
                worldPlacer.accept(pos, this.decoration.getState(random, pos));
        }
    }
}
