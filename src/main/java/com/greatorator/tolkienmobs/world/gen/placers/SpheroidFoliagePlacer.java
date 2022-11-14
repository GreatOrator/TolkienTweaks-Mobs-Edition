package com.greatorator.tolkienmobs.world.gen.placers;

import com.greatorator.tolkienmobs.utils.FeaturePlacer;
import com.greatorator.tolkienmobs.world.gen.feature.config.TreeFeatureConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Random;
import java.util.function.BiConsumer;

public class SpheroidFoliagePlacer extends FoliagePlacer {
    public static final Codec<SpheroidFoliagePlacer> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.floatRange(0, 16f).fieldOf("horizontal_radius").forGetter(o -> o.horizontalRadius),
                    Codec.floatRange(0, 16f).fieldOf("vertical_radius").forGetter(o -> o.verticalRadius),
                    IntProvider.codec(0, 8).fieldOf("offset").forGetter(obj -> obj.offset),
                    Codec.intRange(0, 16).fieldOf("random_add_horizontal").orElse(0).forGetter(o -> o.randomHorizontal),
                    Codec.intRange(0, 16).fieldOf("random_add_vertical").orElse(0).forGetter(o -> o.randomVertical),
                    Codec.floatRange(-0.5f, 0.5f).fieldOf("vertical_filler_bias").orElse(0f).forGetter(o -> o.verticalBias),
                    Codec.intRange(0, 256).fieldOf("shag_factor").orElse(0).forGetter(o -> o.shag_factor) // Shhh
            ).apply(instance, SpheroidFoliagePlacer::new)
    );

    // These two variables are floats to help round out the pixel-snapping of the sphere-filling algorithm
    // n+0.5 numbers seem to work best but messing with it is encouraged to find best results
    private final float horizontalRadius;
    private final float verticalRadius;
    private final float verticalBias;

    private final int randomHorizontal;
    private final int randomVertical;
    private final int shag_factor;

    public SpheroidFoliagePlacer(float horizontalRadius, float verticalRadius, IntProvider yOffset, int randomHorizontal, int randomVertical, float verticalBias, int shag_factor) {
        super(ConstantInt.of((int) horizontalRadius), yOffset);

        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
        this.randomHorizontal = randomHorizontal;
        this.randomVertical = randomVertical;
        this.verticalBias = verticalBias;
        this.shag_factor = shag_factor;
    }

    @Override
    protected FoliagePlacerType<SpheroidFoliagePlacer> type() {
        return TreeFeatureConfig.FOLIAGE_SPHEROID.get();
    }

    private final float TWO_PI = (float) (2.0 * Math.PI);

    @Override // place foliage
    protected void createFoliage(LevelSimulatedReader worldReader, BiConsumer<BlockPos, BlockState> worldPlacer, Random random, TreeConfiguration baseTreeFeatureConfig, int trunkHeight, FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos center = foliage.pos().above(offset); // foliage.getCenter

        //FeatureUtil.makeLeafCircle(world, random, center, radius, baseTreeFeatureConfig.leavesProvider, leavesSet);
        FeaturePlacer.makeLeafSpheroid(worldReader, worldPlacer, FeaturePlacer.VALID_TREE_POS, random, center, foliage.radiusOffset() + this.horizontalRadius + random.nextInt(this.randomHorizontal + 1), foliage.radiusOffset() + this.verticalRadius + random.nextInt(this.randomVertical + 1), this.verticalBias, baseTreeFeatureConfig.foliageProvider);

        if (shag_factor > 0) {
            for (int i = 0; i < shag_factor; i++) {
                float randomYaw = random.nextFloat() * TWO_PI;
                float randomPitch = random.nextFloat() * 2f - 1f; //random.nextFloat() * 0.75f + 0.25f;
                float yUnit = Mth.sqrt(1 - randomPitch * randomPitch); // Inverse Trigonometry identity (sin(arcos(t)) == sqrt(1 - t*t))
                float xCircleOffset = yUnit * Mth.cos(randomYaw) * (horizontalRadius - 1f); // We do radius minus 1 here so the leaf 2x2 generates overlapping the existing foilage
                float zCircleOffset = yUnit * Mth.sin(randomYaw) * (horizontalRadius - 1f); // instead of making awkward 2x2 pieces of foilage jutting out

                BlockPos placement = center.offset(xCircleOffset + ((int) xCircleOffset >> 31), randomPitch * (verticalRadius + 0.25f) + verticalBias, zCircleOffset + ((int) zCircleOffset >> 31));

                placeLeafCluster(worldReader, random, placement.immutable(), baseTreeFeatureConfig.foliageProvider, worldPlacer);
            }
        }
    }

    private void placeLeafCluster(LevelSimulatedReader worldReader, Random random, BlockPos pos, BlockStateProvider state, BiConsumer<BlockPos, BlockState> worldPlacer) {
        FeaturePlacer.putLeafBlock(worldReader, worldPlacer, FeaturePlacer.VALID_TREE_POS, pos, state, random);
        FeaturePlacer.putLeafBlock(worldReader, worldPlacer, FeaturePlacer.VALID_TREE_POS, pos.east(), state, random);
        FeaturePlacer.putLeafBlock(worldReader, worldPlacer, FeaturePlacer.VALID_TREE_POS, pos.south(), state, random);
        FeaturePlacer.putLeafBlock(worldReader, worldPlacer, FeaturePlacer.VALID_TREE_POS, pos.offset(1, 0, 1), state, random);
    }

    @Override // foliage Height
    public int foliageHeight(Random random, int i, TreeConfiguration baseTreeFeatureConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }
}
