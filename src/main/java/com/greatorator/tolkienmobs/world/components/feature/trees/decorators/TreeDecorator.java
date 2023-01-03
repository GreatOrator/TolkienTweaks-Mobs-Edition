package com.greatorator.tolkienmobs.world.components.feature.trees.decorators;

import com.greatorator.tolkienmobs.block.LightningBugBlock;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.world.components.config.TrunkConfig;
import com.greatorator.tolkienmobs.world.components.placements.RootPlacements;
import net.minecraft.core.Direction;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class TreeDecorator {
    public static final BlockStateProvider ROOT_BLEND_PROVIDER = new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(Blocks.OAK_WOOD.defaultBlockState(), 6).add(Blocks.OAK_WOOD.defaultBlockState(), 1).build());
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
    public static final RootPlacements LIVING_ROOTS = new RootPlacements(3, 1, 5, ROOT_BLEND_PROVIDER);
    public static final RootPlacements MIRKWOOD_ROOTS = new RootPlacements(4, 1, 7, MIRKWOOD_ROOT_MAIN, MIRKWOOD_ROOT_ADD);
    public static final RootPlacements MALLORN_ROOTS = new RootPlacements(3, 1, 5, MALLORN_ROOT_MAIN, MALLORN_ROOT_ADD);
    public static final RootPlacements FANGORNOAK_ROOTS = new RootPlacements(4, 1, 8, FANGORNOAK_ROOT_MAIN, FANGORNOAK_ROOT_ADD);
    public static final RootPlacements OLDFOREST_ROOTS = new RootPlacements(5, 2, 9, OLDFOREST_ROOT_MAIN, OLDFOREST_ROOT_ADD);
    public static final RootPlacements CULUMALDA_ROOTS = new RootPlacements(3, 1, 6, CULUMALDA_ROOT_MAIN, CULUMALDA_ROOT_ADD);
    public static final RootPlacements LEBETHRON_ROOTS = new RootPlacements(3, 1, 6, LEBETHRON_ROOT_MAIN, LEBETHRON_ROOT_ADD);
    public static final TrunkConfig LIGHTNINGBUG = new TrunkConfig(1, 1.0f, BlockStateProvider.simple(TolkienBlocks.LIGHTNINGBUG_BLOCK.get().defaultBlockState().setValue(LightningBugBlock.FACING, Direction.NORTH)));
}
