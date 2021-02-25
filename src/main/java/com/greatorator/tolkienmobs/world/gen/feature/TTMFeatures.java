package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import java.util.OptionalInt;

public class TTMFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MALLORN = register("mallorn", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TTMFeatures.States.MALLORN_LOG), new SimpleBlockStateProvider(TTMFeatures.States.MALLORN_LEAVES), new DarkOakFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0)), new DarkOakTrunkPlacer(6, 2, 1), new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty()))).setMaxWaterDepth(Integer.MAX_VALUE).func_236702_a_(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MIRKWOOD = register("mirkwood", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TTMFeatures.States.MIRKWOOD_LOG), new SimpleBlockStateProvider(TTMFeatures.States.MIRKWOOD_LEAVES), new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0)), new ForkyTrunkPlacer(5, 2, 2), new TwoLayerFeature(1, 0, 2))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CULUMALDA = register("culumalda", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TTMFeatures.States.CULUMALDA_LOG), new SimpleBlockStateProvider(TTMFeatures.States.CULUMALDA_LEAVES), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()));
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LEBETHRON = register("lebethron", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TTMFeatures.States.LEBETHRON_LOG), new SimpleBlockStateProvider(TTMFeatures.States.LEBETHRON_LEAVES), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

    public static final class States {
        protected static final BlockState MALLORN_LOG = TTMContent.LOG_MALLORN.get().getDefaultState();
        protected static final BlockState MALLORN_LEAVES = TTMContent.LEAVES_MALLORN.get().getDefaultState();
        protected static final BlockState MIRKWOOD_LOG = TTMContent.LOG_MIRKWOOD.get().getDefaultState();
        protected static final BlockState MIRKWOOD_LEAVES = TTMContent.LEAVES_MIRKWOOD.get().getDefaultState();
        protected static final BlockState CULUMALDA_LOG = TTMContent.LOG_CULUMALDA.get().getDefaultState();
        protected static final BlockState CULUMALDA_LEAVES = TTMContent.LEAVES_CULUMALDA.get().getDefaultState();
        protected static final BlockState LEBETHRON_LOG = TTMContent.LOG_LEBETHRON.get().getDefaultState();
        protected static final BlockState LEBETHRON_LEAVES = TTMContent.LEAVES_LEBETHRON.get().getDefaultState();
    }

}
