package com.greatorator.tolkienmobs.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienFeatures {
//    private static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerWorldFeature(ResourceLocation rl, ConfiguredFeature<FC, F> feature) {
//        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, rl, feature);
//    }
//
//    public static final class Configs {
//        public static final BlockClusterFeatureConfig DEFAULT_LORINAND_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(States.FLOWER_ALFIRIN, 2).add(States.DANDELION, 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
//        public static final BlockClusterFeatureConfig DEFAULT_MIRKWOOD_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(States.FLOWER_MIRKWOOD, 2).add(States.DEAD_BUSH, 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
//        public static final BlockClusterFeatureConfig MALLORN_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_MALLORN), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
//        public static final BlockClusterFeatureConfig MIRKWOOD_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_MIRKWOOD), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
//        public static final BlockClusterFeatureConfig CULUMALDA_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_CULUMALDA), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
//        public static final BlockClusterFeatureConfig LEBETHRON_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_LEBETHRON), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
//        public static final BlockClusterFeatureConfig FANGORNOAK_LEAFPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.LEAFPILE_FANGORNOAK), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(States.GRASS_BLOCK.getBlock())).noProjection().build();
//        public static final BlockClusterFeatureConfig ROCKPILES_CONFIG = (new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider().add(States.ROCKPILE, 2), SimpleBlockPlacer.INSTANCE)).tries(64).build();
//    }
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

//    public static final RegistryObject<Feature<TFTreeFeatureConfig>> CANOPY_OAK = FEATURES.register("canopy_oak", () -> new OakCanopyTreeFeature(TFTreeFeatureConfig.codecTFTreeConfig));
}
