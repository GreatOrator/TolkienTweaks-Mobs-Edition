package com.greatorator.tolkienmobs.world.gen.feature.config;

import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.world.gen.TTMFeatures;
import com.greatorator.tolkienmobs.world.gen.placers.TTMBranchingLargeTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.TTMBranchingTrunkPlacer;
import com.greatorator.tolkienmobs.world.gen.placers.TTMSpheroidFoliagePlacer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TTMTreeFeatureConfig implements IFeatureConfig {
    public static final Codec<TTMTreeFeatureConfig> codecTFTreeConfig = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((obj) -> obj.trunkProvider),
                    BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter((obj) -> obj.leavesProvider),
                    BlockStateProvider.CODEC.fieldOf("branch_provider").forGetter((obj) -> obj.branchProvider),
                    BlockStateProvider.CODEC.fieldOf("roots_provider").forGetter((obj) -> obj.rootsProvider),
                    Codec.INT.fieldOf("minimum_size").orElse(20).forGetter((obj) -> obj.minHeight),
                    Codec.INT.fieldOf("add_first_five_chance").orElse(1).forGetter((obj) -> obj.chanceAddFiveFirst),
                    Codec.INT.fieldOf("add_second_five_chance").orElse(1).forGetter((obj) -> obj.chanceAddFiveSecond),
                    Codec.BOOL.fieldOf("has_leaves").orElse(true).forGetter((obj) -> obj.hasLeaves),
                    Codec.BOOL.fieldOf("check_water").orElse(false).forGetter((obj) -> obj.checkWater),
                    BlockStateProvider.CODEC.fieldOf("sapling").orElse(new SimpleBlockStateProvider(Blocks.OAK_SAPLING.defaultBlockState())).forGetter((obj) -> obj.sapling))
                    .apply(instance, TTMTreeFeatureConfig::new));

    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider leavesProvider;
    public final BlockStateProvider branchProvider;
    public final BlockStateProvider rootsProvider;
    public final int minHeight;
    public final int chanceAddFiveFirst;
    public final int chanceAddFiveSecond;
    public final boolean hasLeaves;
    public final boolean checkWater;
    public final BlockStateProvider sapling;
    public transient boolean forcePlacement;

    public TTMTreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, BlockStateProvider branch, BlockStateProvider roots, int height, int chanceFiveFirst, int chanceFiveSecond, boolean hasLeaves, boolean checkWater, BlockStateProvider sapling) {
        this.trunkProvider = trunk;
        this.leavesProvider = leaves;
        this.branchProvider = branch;
        this.rootsProvider = roots;
        this.minHeight = height;
        // For some dumb reason this keeps getting -1 so you get `Math.max(x, 0)` for punishment
        this.chanceAddFiveFirst = Math.max(chanceFiveFirst, 1);
        this.chanceAddFiveSecond = Math.max(chanceFiveSecond, 1);
        this.hasLeaves = hasLeaves;
        this.checkWater = checkWater;
        this.sapling = sapling;
    }

    private static final List<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = new ArrayList<>();

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_REGISTER = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, MODID);

    private final static int LEAF_SHAG_FACTOR = 24;
    private static final int canopyDistancing = 5;

    public static final TrunkPlacerType<TTMBranchingTrunkPlacer> TRUNK_BRANCHING = registerTrunk(TolkienMobs.prefix("branching_trunk_placer"), TTMBranchingTrunkPlacer.CODEC);
    public static final TrunkPlacerType<TTMBranchingLargeTrunkPlacer> TRUNK_BRANCHING_LARGE = registerTrunk(TolkienMobs.prefix("branching_large_trunk_placer"), TTMBranchingLargeTrunkPlacer.CODEC);
    public static final RegistryObject<FoliagePlacerType<TTMSpheroidFoliagePlacer>> FOLIAGE_SPHEROID = FOLIAGE_PLACER_REGISTER.register("spheroid_foliage_placer", () -> new FoliagePlacerType<>(TTMSpheroidFoliagePlacer.CODEC));

    public void forcePlacement() {
        this.forcePlacement = true;
    }

    public IPlantable getSapling(Random rand, BlockPos pos) {
        return (IPlantable) sapling.getState(rand, pos).getBlock();
    }

    public static final class TreeConfigurations {
        public static final BaseTreeFeatureConfig MIRKWOOD = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.MIRKWOOD_LOG),
                new SimpleBlockStateProvider(TTMFeatures.States.MIRKWOOD_LEAVES),
                new TTMSpheroidFoliagePlacer(4.5f, 2.25f, FeatureSpread.fixed(0), 1, 0, 0.45f, (int) (LEAF_SHAG_FACTOR * 1.5f)),
                new TTMBranchingTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new TwoLayerFeature(1, 0, 1))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig MALLORN = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.MALLORN_LOG),
                new SimpleBlockStateProvider(TTMFeatures.States.MALLORN_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.fixed(4), FeatureSpread.fixed(4), 4),
                new TTMBranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new ThreeLayerFeature(5, 1, 0, 1, 2, OptionalInt.empty())).maxWaterDepth(Integer.MAX_VALUE).heightmap(Heightmap.Type.MOTION_BLOCKING)
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig CULUMALDA = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.CULUMALDA_LOG),
                new SimpleBlockStateProvider(TTMFeatures.States.CULUMALDA_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                new StraightTrunkPlacer(5, 2, 0),
                new TwoLayerFeature(1, 0, 1))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig LEBETHRON = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.LEBETHRON_LOG),
                new SimpleBlockStateProvider(TTMFeatures.States.LEBETHRON_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig DEADTREE = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.DEADWOOD_LOG),
                new SimpleBlockStateProvider(TTMFeatures.States.AIR),
                new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4),
                new FancyTrunkPlacer(3, 11, 0),
                new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))
                .ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING)
                .build();

        public static final BaseTreeFeatureConfig FANGORNOAK = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.DARK_OAK_LOGS),
                new SimpleBlockStateProvider(TTMFeatures.States.FANGORNOAK_LEAVES),
                new FancyFoliagePlacer(FeatureSpread.fixed(4), FeatureSpread.fixed(4), 4),
                new TTMBranchingLargeTrunkPlacer(6, 3, 3, 5, new TTMBranchesConfig(4, 0, 10, 4, 0.23, 0.23), false),
                new TwoLayerFeature(1, 0, 1))
                .decorators(ImmutableList.of(TrunkVineTreeDecorator.INSTANCE, LeaveVineTreeDecorator.INSTANCE))
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_BLOOM_DECAY = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(TTMFeatures.States.CAP_BLOOM_DECAY),
                new TTMSpheroidFoliagePlacer(4.25f, 0f, FeatureSpread.fixed(1), 1, 0, 0f, 0),
                new TTMBranchingTrunkPlacer(3, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .ignoreVines()
                .build();

        public static final BaseTreeFeatureConfig MUSHROOM_DECAY_BLOOM = new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TTMFeatures.States.MUSHROOM_STEM),
                new SimpleBlockStateProvider(TTMFeatures.States.CAP_DECAY_BLOOM),
                new TTMSpheroidFoliagePlacer(4.25f, 1.75f, FeatureSpread.fixed(1), 0, 0, -0.45f, 0),
                new TTMBranchingTrunkPlacer(4, 5, 4, 6, new TTMBranchesConfig(3, 1, 9, 1, 0.3, 0.2), true),
                new TwoLayerFeature(11, 0, canopyDistancing))
                .ignoreVines()
                .build();
    }

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliage(ResourceLocation name, Codec<P> codec) {
        FoliagePlacerType<P> type = new FoliagePlacerType<>(codec);
        type.setRegistryName(name);
        FOLIAGE_PLACER_TYPES.add(type);
        return type;
    }

    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunk(ResourceLocation name, Codec<P> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, name, new TrunkPlacerType<>(codec));
    }

    public static class Builder {
        private BlockStateProvider trunkProvider;
        private BlockStateProvider leavesProvider;
        private BlockStateProvider branchProvider;
        private BlockStateProvider rootsProvider;
        private int baseHeight;
        private int chanceFirstFive;
        private int chanceSecondFive;
        private boolean hasLeaves;
        private boolean checkWater;
        private BlockStateProvider sapling;

        public Builder(BlockStateProvider trunk, BlockStateProvider leaves, BlockStateProvider branch, BlockStateProvider roots) {
            this.trunkProvider = trunk;
            this.leavesProvider = leaves;
            this.branchProvider = branch;
            this.rootsProvider = roots;
        }

        public TTMTreeFeatureConfig.Builder minHeight(int height) {
            this.baseHeight = height;
            return this;
        }

        public TTMTreeFeatureConfig.Builder chanceFirstFive(int chance) {
            this.chanceFirstFive = chance;
            return this;
        }

        public TTMTreeFeatureConfig.Builder chanceSecondFive(int chance) {
            this.chanceSecondFive = chance;
            return this;
        }

        public TTMTreeFeatureConfig.Builder noLeaves() {
            this.hasLeaves = false;
            return this;
        }

        public TTMTreeFeatureConfig.Builder checksWater() {
            this.checkWater = true;
            return this;
        }

        public TTMTreeFeatureConfig.Builder setSapling(SaplingBlock plant) {
            this.sapling = new SimpleBlockStateProvider(plant.defaultBlockState());
            return this;
        }

        public TTMTreeFeatureConfig build() {
            return new TTMTreeFeatureConfig(trunkProvider, leavesProvider, branchProvider, rootsProvider, baseHeight, chanceFirstFive, chanceSecondFive, hasLeaves, checkWater, sapling);
        }
    }
}
