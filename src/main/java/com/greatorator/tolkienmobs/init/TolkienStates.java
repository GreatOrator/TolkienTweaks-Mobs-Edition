package com.greatorator.tolkienmobs.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.LeavesBlock.PERSISTENT;

public class TolkienStates {
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
        public static final BlockState AMMOLITE_NETHER_ORE = TolkienBlocks.ORE_NETHER_AMMOLITE.get().defaultBlockState();
        public static final BlockState MITHRIL_NETHER_ORE = TolkienBlocks.ORE_NETHER_MITHRIL.get().defaultBlockState();
        public static final BlockState MORGULIRON_NETHER_ORE = TolkienBlocks.ORE_NETHER_MORGULIRON.get().defaultBlockState();
        public static final BlockState AMMOLITE_END_ORE = TolkienBlocks.ORE_END_AMMOLITE.get().defaultBlockState();
        public static final BlockState MITHRIL_END_ORE = TolkienBlocks.ORE_END_MITHRIL.get().defaultBlockState();
        public static final BlockState MORGULIRON_END_ORE = TolkienBlocks.ORE_END_MORGULIRON.get().defaultBlockState();
        public static final BlockState AMMOLITE_DEEPSLATE_ORE = TolkienBlocks.ORE_DEEPSLATE_AMMOLITE.get().defaultBlockState();
        public static final BlockState MITHRIL_DEEPSLATE_ORE = TolkienBlocks.ORE_DEEPSLATE_MITHRIL.get().defaultBlockState();
        public static final BlockState MORGULIRON_DEEPSLATE_ORE = TolkienBlocks.ORE_DEEPSLATE_MORGULIRON.get().defaultBlockState();
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