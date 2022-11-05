package com.greatorator.tolkienmobs.world.gen;

import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.LeavesBlock.PERSISTENT;

public final class TolkienFeatures {

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

    public static final class States {
        public static final BlockState MALLORN_LOG = TolkienBlocks.WOOD_MALLORN.get().defaultBlockState();
        public static final BlockState MALLORN_LEAVES = TolkienBlocks.LEAVES_MALLORN.get().defaultBlockState().setValue(PERSISTENT, Boolean.TRUE);
        public static final BlockState MIRKWOOD_LOG = TolkienBlocks.WOOD_MIRKWOOD.get().defaultBlockState();
        public static final BlockState MIRKWOOD_LEAVES = TolkienBlocks.LEAVES_MIRKWOOD.get().defaultBlockState().setValue(PERSISTENT, Boolean.TRUE);
        public static final BlockState CULUMALDA_LOG = TolkienBlocks.WOOD_CULUMALDA.get().defaultBlockState();
        public static final BlockState CULUMALDA_LEAVES = TolkienBlocks.LEAVES_CULUMALDA.get().defaultBlockState().setValue(PERSISTENT, Boolean.TRUE);
        public static final BlockState LEBETHRON_LOG = TolkienBlocks.WOOD_LEBETHRON.get().defaultBlockState();
        public static final BlockState LEBETHRON_LEAVES = TolkienBlocks.LEAVES_LEBETHRON.get().defaultBlockState().setValue(PERSISTENT, Boolean.TRUE);
        public static final BlockState DEADWOOD_LOG = TolkienBlocks.LOG_DEADWOOD.get().defaultBlockState();
        public static final BlockState CAP_DECAY_BLOOM = TolkienBlocks.BLOCK_DECAY_BLOOM.get().defaultBlockState();
        public static final BlockState CAP_BLOOM_DECAY = TolkienBlocks.BLOCK_BLOOM_DECAY.get().defaultBlockState();
        public static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.defaultBlockState();
        public static final BlockState AIR = Blocks.AIR.defaultBlockState();
        public static final BlockState AMMOLITE_ORE = TolkienBlocks.ORE_AMMOLITE.get().defaultBlockState();
        public static final BlockState MITHRIL_ORE = TolkienBlocks.ORE_MITHRIL.get().defaultBlockState();
        public static final BlockState MORGULIRON_ORE = TolkienBlocks.ORE_MORGULIRON.get().defaultBlockState();
        public static final BlockState FLOWER_ALFIRIN = TolkienBlocks.FLOWER_ALFIRIN.get().defaultBlockState();
        public static final BlockState FLOWER_MIRKWOOD = TolkienBlocks.FLOWER_MIRKWOOD.get().defaultBlockState();
        public static final BlockState FLOWER_SWAMPMILKWEED = TolkienBlocks.FLOWER_SWAMPMILKWEED.get().defaultBlockState();
        public static final BlockState LEAFPILE_MIRKWOOD = TolkienBlocks.LEAFPILE_MIRKWOOD.get().defaultBlockState();
        public static final BlockState LEAFPILE_MALLORN = TolkienBlocks.LEAFPILE_MALLORN.get().defaultBlockState();
        public static final BlockState LEAFPILE_CULUMALDA = TolkienBlocks.LEAFPILE_CULUMALDA.get().defaultBlockState();
        public static final BlockState LEAFPILE_LEBETHRON = TolkienBlocks.LEAFPILE_LEBETHRON.get().defaultBlockState();
        public static final BlockState LEAFPILE_FANGORNOAK = TolkienBlocks.LEAFPILE_FANGORNOAK.get().defaultBlockState();
        public static final BlockState DANDELION = Blocks.DANDELION.defaultBlockState();
        public static final BlockState DEAD_BUSH = Blocks.DEAD_BUSH.defaultBlockState();
        public static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
        public static final BlockState MOSSY_COBBLESTONE = Blocks.MOSSY_COBBLESTONE.defaultBlockState();
        public static final BlockState FANGORNOAK_LOG = TolkienBlocks.WOOD_FANGORNOAK.get().defaultBlockState();
        public static final BlockState FANGORNOAK_LEAVES = TolkienBlocks.LEAVES_FANGORNOAK.get().defaultBlockState();
        public static final BlockState DARK_OAK_LOGS = Blocks.DARK_OAK_LOG.defaultBlockState();
        public static final BlockState DARK_OAK_LEAVES = Blocks.DARK_OAK_LEAVES.defaultBlockState();
        public static final BlockState STONE = Blocks.STONE.defaultBlockState();
        public static final BlockState ROCKPILE = TolkienBlocks.ROCKPILE.get().defaultBlockState();
    }
}