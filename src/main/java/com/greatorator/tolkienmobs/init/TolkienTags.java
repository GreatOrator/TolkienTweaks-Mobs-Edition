package com.greatorator.tolkienmobs.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class TolkienTags {
    public static void init(){
        items.init();
        blocks.init();
        fluids.init();
        tagkeys.init();
    }

    public static class items{
        private static void init(){}

        public static final TagKey<Item> DUSTS_MITHRIL = tag("dusts/mithril");
        public static final TagKey<Item> DUSTS_MORGULIRON = tag("dusts/morguliron");
        public static final TagKey<Item> NUGGETS_MITHRIL = tag("nuggets/mithril");
        public static final TagKey<Item> NUGGETS_MORGULIRON = tag("nuggets/morguliron");
        public static final TagKey<Item> INGOTS_MITHRIL = tag("ingots/mithril");
        public static final TagKey<Item> INGOTS_MORGULIRON = tag("ingots/morguliron");
        public static final TagKey<Item> STORAGE_BLOCKS_MITHRIL = tag("storage_blocks/mithril");
        public static final TagKey<Item> STORAGE_BLOCKS_MORGULIRON = tag("storage_blocks/morguliron");
        public static final TagKey<Item> ORES_MITHRIL = tag("ores/mithril");
        public static final TagKey<Item> ORES_MORGULIRON = tag("ores/morguliron");
        public static final TagKey<Item> ORES_AMMOLITE = tag("ores/ammolite");
        public static final TagKey<Item> FENCES_WOODEN = tag("fences/wooden");
        public static final TagKey<Item> FENCE_GATES_WOODEN = tag("fence_gates/wooden");
        public static final TagKey<Item> MUSHROOMS = tag("mushrooms");
        public static final TagKey<Item> SAPLINGS = tag("saplings");
        public static final TagKey<Item> LEAVES = tag("leaves");
        public static final TagKey<Item> PLANKS = tag("planks");
        public static final TagKey<Item> TORCHES = tag("torches");
        public static final TagKey<Item> STAIRS = tag("stairs");
        public static final TagKey<Item> SLABS = tag("slabs");
        public static final TagKey<Item> LOGS = tag("logs");
        public static final TagKey<Item> FLOWERS = tag("flowers");
        public static final TagKey<Item> DOORS_WOODEN = tag("doors/wooden");
        public static final TagKey<Item> DOORS_IRON = tag("doors/iron");
        public static final TagKey<Item> BARS_IRON = tag("bars/iron");
        public static final TagKey<Item> LEATHER = tag("leather");
        public static final TagKey<Item> GEMS = tag("gems");
        public static final TagKey<Item> FEATHER = tag("feather");
        public static final TagKey<Item> BOTTLE = tag("bottle");
        public static final TagKey<Item> FUR = tag("fur");
        public static final TagKey<Item> COINS = tag("coins");
        public static final TagKey<Item> TOKENS = tag("tokens");
        public static final TagKey<Item> FOOD = tag("food");
        public static final TagKey<Item> DRINKS = tag("drinks");
        public static final TagKey<Item> ARROW = tag("arrow");
        public static final TagKey<Item> ACORNS = tag("acorns");
        public static final TagKey<Item> INSECTS = tag("insects");
        public static final TagKey<Item> SPAWNEGG = tag("spawnegg");
        public static final TagKey<Item> CROP = tag("crops");
        public static final TagKey<Item> SEEDS = tag("seeds");
        public static final TagKey<Item> SLEEPING_BAGS = tag("sleeping_bags");
        public static final TagKey<Item> UPGRADES = tag("upgrades");
        public static final TagKey<Item> MUSIC_DISCS = tag("music_discs");
        public static final TagKey<Item> SIGNS = tag("signs");
        public static final TagKey<Item> TRAPDOORS = tag("trapdoors");
        public static final TagKey<Item> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
        public static final TagKey<Item> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
        public static final TagKey<Item> WOODEN_STAIRS = tag("wooden_stairs");
        public static final TagKey<Item> WOODEN_SLABS = tag("wooden_slabs");
        public static final TagKey<Item> BARRELS_WOODEN = tag("barrels/wooden");
        public static final TagKey<Item> KEYS = tag("keys");
        public static final TagKey<Item> TRINKET = tag("trinket");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class blocks{
        private static void init(){}

        public static final TagKey<Block> STORAGE_BLOCKS_MITHRIL = tag("storage_blocks/mithril");
        public static final TagKey<Block> STORAGE_BLOCKS_MORGULIRON = tag("storage_blocks/morguliron");
        public static final TagKey<Block> ORES_MITHRIL = tag("ores/mithril");
        public static final TagKey<Block> ORES_MORGULIRON = tag("ores/morguliron");
        public static final TagKey<Block> ORES_AMMOLITE = tag("ores/ammolite");
        public static final TagKey<Block> FENCE_GATES_WOODEN = tag("fence_gates/wooden");
        public static final TagKey<Block> SAPLINGS = tag("saplings");
        public static final TagKey<Block> LEAVES = tag("leaves");
        public static final TagKey<Block> PLANKS = tag("planks");
        public static final TagKey<Block> TORCHES = tag("torches");
        public static final TagKey<Block> LOGS = tag("logs");
        public static final TagKey<Block> BARS_IRON = tag("bars/iron");
        public static final TagKey<Block> FLOWERS = tag("flowers");
        public static final TagKey<Block> DECAY_GROW_BLOCK = tag("decay_grow_block");
        public static final TagKey<Block> SLEEPING_BAGS = tag("sleeping_bags");
        public static final TagKey<Block> BARRELS_WOODEN = tag("barrels/wooden");
        public static final TagKey<Block> WOODEN_DOORS = tag("wooden_doors");
        public static final TagKey<Block> WOODEN_STAIRS = tag("wooden_stairs");
        public static final TagKey<Block> WOODEN_SLABS = tag("wooden_slabs");
        public static final TagKey<Block> WOODEN_FENCES = tag("wooden_fences");
        public static final TagKey<Block> PRESSURE_PLATES = tag("pressure_plates");
        public static final TagKey<Block> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
        public static final TagKey<Block> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
        public static final TagKey<Block> DOORS = tag("doors");
        public static final TagKey<Block> TRAPDOORS = tag("trapdoors");
        public static final TagKey<Block> SIGNS = tag("signs");
        public static final TagKey<Block> CROPS = tag("crops");
        public static final TagKey<Block> MUSHROOM_GROW_BLOCK = tag("mushroom_grow_block");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class fluids {
        private static void init() {
        }

        public static final TagKey<Fluid> FLUIDS = tag("lava");

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class tagkeys{
        private static void init(){}

        public static final TagKey<Item> BLACKLISTED_ITEMS = tag("blacklisted_items");
        public static final TagKey<Item> ALLOWED_FLUIDS = tag("allowed_fluids");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
