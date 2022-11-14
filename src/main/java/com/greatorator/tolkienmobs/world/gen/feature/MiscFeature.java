package com.greatorator.tolkienmobs.world.gen.feature;

import com.greatorator.tolkienmobs.block.LightningBugBlock;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.world.gen.feature.config.RootConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TrunkConfig;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider;

public class MiscFeature {
    public static final BlockStateProvider MIRKWOOD_ROOT_MAIN = new RotatedBlockProvider(TolkienBlocks.WOOD_MIRKWOOD.get());
    public static final BlockStateProvider MIRKWOOD_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final BlockStateProvider MALLORN_ROOT_MAIN = new RotatedBlockProvider(TolkienBlocks.WOOD_MALLORN.get());
    public static final BlockStateProvider MALLORN_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final BlockStateProvider FANGORNOAK_ROOT_MAIN = new RotatedBlockProvider(TolkienBlocks.WOOD_FANGORNOAK.get());
    public static final BlockStateProvider FANGORNOAK_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final BlockStateProvider OLDFOREST_ROOT_MAIN = new RotatedBlockProvider(Blocks.DARK_OAK_WOOD);
    public static final BlockStateProvider OLDFOREST_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final BlockStateProvider CULUMALDA_ROOT_MAIN = new RotatedBlockProvider(TolkienBlocks.WOOD_CULUMALDA.get());
    public static final BlockStateProvider CULUMALDA_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final BlockStateProvider LEBETHRON_ROOT_MAIN = new RotatedBlockProvider(TolkienBlocks.WOOD_LEBETHRON.get());
    public static final BlockStateProvider LEBETHRON_ROOT_ADD = new RotatedBlockProvider(TolkienBlocks.WOOD_DEADWOOD.get());
    public static final RootConfig MIRKWOOD_ROOTS = new RootConfig(4, 1, 7, MIRKWOOD_ROOT_MAIN, MIRKWOOD_ROOT_ADD);
    public static final RootConfig MALLORN_ROOTS = new RootConfig(3, 1, 5, MALLORN_ROOT_MAIN, MALLORN_ROOT_ADD);
    public static final RootConfig FANGORNOAK_ROOTS = new RootConfig(4, 1, 8, FANGORNOAK_ROOT_MAIN, FANGORNOAK_ROOT_ADD);
    public static final RootConfig OLDFOREST_ROOTS = new RootConfig(5, 2, 9, OLDFOREST_ROOT_MAIN, OLDFOREST_ROOT_ADD);
    public static final RootConfig CULUMALDA_ROOTS = new RootConfig(3, 1, 6, CULUMALDA_ROOT_MAIN, CULUMALDA_ROOT_ADD);
    public static final RootConfig LEBETHRON_ROOTS = new RootConfig(3, 1, 6, LEBETHRON_ROOT_MAIN, LEBETHRON_ROOT_ADD);
    public static final TrunkConfig LIGHTNINGBUG = new TrunkConfig(1, 1.0f, BlockStateProvider.simple(TolkienBlocks.LIGHTNINGBUG_BLOCK.get().defaultBlockState().setValue(LightningBugBlock.FACING, Direction.NORTH)));

//    public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> FOREST_ROCK = FeatureUtils.register("forest_rock", Feature.FOREST_ROCK, (new BlockStateConfiguration(TolkienFeatures.States.MOSSY_COBBLESTONE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(2));
//    public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> GENERAL_ROCK = FeatureUtils.register("general_rock", Feature.FOREST_ROCK, (new BlockStateConfiguration(TolkienFeatures.States.STONE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).countRandom(2));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> ROCKPILES = FeatureUtils.register("patch_rockpiles", Feature.RANDOM_PATCH, (TolkienFeatures.Configs.ROCKPILES_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
}
