package com.greatorator.tolkienmobs.init;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TTMTags {
    public static void init(){
        items.init();
        blocks.init();
        fluids.init();
        tagkeys.init();
    }

    public static class items{
        private static void init(){}

        public static final Tags.IOptionalNamedTag<Item> DUSTS_MITHRIL = tag("dusts/mithril");
        public static final Tags.IOptionalNamedTag<Item> DUSTS_MORGULIRON = tag("dusts/morguliron");
        public static final Tags.IOptionalNamedTag<Item> NUGGETS_MITHRIL = tag("nuggets/mithril");
        public static final Tags.IOptionalNamedTag<Item> NUGGETS_MORGULIRON = tag("nuggets/morguliron");
        public static final Tags.IOptionalNamedTag<Item> INGOTS_MITHRIL = tag("ingots/mithril");
        public static final Tags.IOptionalNamedTag<Item> INGOTS_MORGULIRON = tag("ingots/morguliron");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_MITHRIL = tag("storage_blocks/mithril");
        public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS_MORGULIRON = tag("storage_blocks/morguliron");
        public static final Tags.IOptionalNamedTag<Item> ORES_MITHRIL = tag("ores/mithril");
        public static final Tags.IOptionalNamedTag<Item> ORES_MORGULIRON = tag("ores/morguliron");
        public static final Tags.IOptionalNamedTag<Item> ORES_AMMOLITE = tag("ores/ammolite");
        public static final Tags.IOptionalNamedTag<Item> FENCES_WOODEN = tag("fences/wooden");
        public static final Tags.IOptionalNamedTag<Item> FENCE_GATES_WOODEN = tag("fence_gates/wooden");
        public static final Tags.IOptionalNamedTag<Item> MUSHROOMS = tag("mushrooms");
        public static final Tags.IOptionalNamedTag<Item> SAPLINGS = tag("saplings");
        public static final Tags.IOptionalNamedTag<Item> LEAVES = tag("leaves");
        public static final Tags.IOptionalNamedTag<Item> PLANKS = tag("planks");
        public static final Tags.IOptionalNamedTag<Item> TORCHES = tag("torches");
        public static final Tags.IOptionalNamedTag<Item> STAIRS = tag("stairs");
        public static final Tags.IOptionalNamedTag<Item> SLABS = tag("slabs");
        public static final Tags.IOptionalNamedTag<Item> LOGS = tag("logs");
        public static final Tags.IOptionalNamedTag<Item> FLOWERS = tag("flowers");
        public static final Tags.IOptionalNamedTag<Item> DOORS_WOODEN = tag("doors/wooden");
        public static final Tags.IOptionalNamedTag<Item> DOORS_IRON = tag("doors/iron");
        public static final Tags.IOptionalNamedTag<Item> BARS_IRON = tag("bars/iron");
        public static final Tags.IOptionalNamedTag<Item> LEATHER = tag("leather");
        public static final Tags.IOptionalNamedTag<Item> GEMS = tag("gems");
        public static final Tags.IOptionalNamedTag<Item> FEATHER = tag("feather");
        public static final Tags.IOptionalNamedTag<Item> BOTTLE = tag("bottle");
        public static final Tags.IOptionalNamedTag<Item> FUR = tag("fur");
        public static final Tags.IOptionalNamedTag<Item> COINS = tag("coins");
        public static final Tags.IOptionalNamedTag<Item> TOKENS = tag("tokens");
        public static final Tags.IOptionalNamedTag<Item> FOOD = tag("food");
        public static final Tags.IOptionalNamedTag<Item> DRINKS = tag("drinks");
        public static final Tags.IOptionalNamedTag<Item> ARROW = tag("arrow");
        public static final Tags.IOptionalNamedTag<Item> ACORNS = tag("acorns");
        public static final Tags.IOptionalNamedTag<Item> INSECTS = tag("insects");
        public static final Tags.IOptionalNamedTag<Item> SPAWNEGG = tag("spawnegg");
        public static final Tags.IOptionalNamedTag<Item> CROP = tag("crops");
        public static final Tags.IOptionalNamedTag<Item> SEEDS = tag("seeds");
        public static final Tags.IOptionalNamedTag<Item> SLEEPING_BAGS = tag("sleeping_bags");
        public static final Tags.IOptionalNamedTag<Item> UPGRADES = tag("upgrades");
        public static final Tags.IOptionalNamedTag<Item> MUSIC_DISCS = tag("music_discs");
        public static final Tags.IOptionalNamedTag<Item> SIGNS = tag("signs");
        public static final Tags.IOptionalNamedTag<Item> TRAPDOORS = tag("trapdoors");
        public static final Tags.IOptionalNamedTag<Item> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
        public static final Tags.IOptionalNamedTag<Item> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
        public static final Tags.IOptionalNamedTag<Item> WOODEN_STAIRS = tag("wooden_stairs");
        public static final Tags.IOptionalNamedTag<Item> WOODEN_SLABS = tag("wooden_slabs");
        public static final Tags.IOptionalNamedTag<Item> BARRELS_WOODEN = tag("barrels/wooden");
        public static final Tags.IOptionalNamedTag<Item> KEYS = tag("keys");


        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class blocks{
        private static void init(){}

        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_MITHRIL = tag("storage_blocks/mithril");
        public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_MORGULIRON = tag("storage_blocks/morguliron");
        public static final Tags.IOptionalNamedTag<Block> ORES_MITHRIL = tag("ores/mithril");
        public static final Tags.IOptionalNamedTag<Block> ORES_MORGULIRON = tag("ores/morguliron");
        public static final Tags.IOptionalNamedTag<Block> ORES_AMMOLITE = tag("ores/ammolite");
        public static final Tags.IOptionalNamedTag<Block> FENCE_GATES_WOODEN = tag("fence_gates/wooden");
        public static final Tags.IOptionalNamedTag<Block> SAPLINGS = tag("saplings");
        public static final Tags.IOptionalNamedTag<Block> LEAVES = tag("leaves");
        public static final Tags.IOptionalNamedTag<Block> PLANKS = tag("planks");
        public static final Tags.IOptionalNamedTag<Block> TORCHES = tag("torches");
        public static final Tags.IOptionalNamedTag<Block> LOGS = tag("logs");
        public static final Tags.IOptionalNamedTag<Block> BARS_IRON = tag("bars/iron");
        public static final Tags.IOptionalNamedTag<Block> FLOWERS = tag("flowers");
        public static final Tags.IOptionalNamedTag<Block> DECAY_GROW_BLOCK = tag("decay_grow_block");
        public static final Tags.IOptionalNamedTag<Block> SLEEPING_BAGS = tag("sleeping_bags");
        public static final Tags.IOptionalNamedTag<Block> BARRELS_WOODEN = tag("barrels/wooden");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_DOORS = tag("wooden_doors");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_STAIRS = tag("wooden_stairs");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_SLABS = tag("wooden_slabs");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_FENCES = tag("wooden_fences");
        public static final Tags.IOptionalNamedTag<Block> PRESSURE_PLATES = tag("pressure_plates");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_PRESSURE_PLATES = tag("wooden_pressure_plates");
        public static final Tags.IOptionalNamedTag<Block> WOODEN_TRAPDOORS = tag("wooden_trapdoors");
        public static final Tags.IOptionalNamedTag<Block> DOORS = tag("doors");
        public static final Tags.IOptionalNamedTag<Block> TRAPDOORS = tag("trapdoors");
        public static final Tags.IOptionalNamedTag<Block> SIGNS = tag("signs");
        public static final Tags.IOptionalNamedTag<Block> CROPS = tag("crops");
        public static final Tags.IOptionalNamedTag<Block> MUSHROOM_GROW_BLOCK = tag("mushroom_grow_block");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class fluids {
        private static void init() {
        }

        public static final Tags.IOptionalNamedTag<Fluid> FLUIDS = tag("lava");

        private static Tags.IOptionalNamedTag<Fluid> tag(String name) {
            return FluidTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class tagkeys{
        private static void init(){}

        public static final Tags.IOptionalNamedTag<Item> BLACKLISTED_ITEMS = tag("blacklisted_items");
        public static final Tags.IOptionalNamedTag<Item> ALLOWED_FLUIDS = tag("allowed_fluids");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
