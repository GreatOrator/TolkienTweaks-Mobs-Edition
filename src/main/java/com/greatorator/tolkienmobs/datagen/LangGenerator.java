package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.ChatFormatting;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

/**
 * Created by brandon3055 on 21/5/20.
 */
public class LangGenerator extends LanguageProvider {
    public LangGenerator(DataGenerator gen) {
        super(gen, TolkienMobs.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        PrefixHelper helper = new PrefixHelper(this);
        blocks();
        items();
        quest();
        food();
        potion();
        coinToken();
        enchants();
        records();
        tools();
        entities();
        merchants(helper);
        trinket(helper);
        biomes(helper);
        chatMessages(helper);
        creativeTabGroups();
        gui(helper);
        keyCommand();
        TCon();
    }

    private void blocks() {
        add(TolkienBlocks.ORE_MITHRIL.get(), "Mithril Ore");
        add(TolkienBlocks.ORE_END_MITHRIL.get(), "Ender Mithril Ore");
        add(TolkienBlocks.ORE_NETHER_MITHRIL.get(), "Fiery Mithril Ore");
        add(TolkienBlocks.ORE_DEEPSLATE_MITHRIL.get(), "Deepslate Mithril Ore");
        add(TolkienBlocks.BLOCK_MITHRIL.get(), "Mithril Block");
        add(TolkienBlocks.RAW_MITHRIL_BLOCK.get(), "Raw Mithril Block");
        add(TolkienBlocks.MITHRIL_BARS.get(), "Mithril Bars");
        add(TolkienBlocks.WALL_MITHRIL.get(), "Mithril Wall");
        add(TolkienBlocks.DOOR_MITHRIL.get(), "Mithril Door");
        add(TolkienBlocks.TRAPDOOR_MITHRIL.get(), "Mithril Trap Door");
        add(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), "Mithril Pressure Plate");
        add(TolkienBlocks.ORE_MORGULIRON.get(), "Morgul Iron Ore");
        add(TolkienBlocks.ORE_END_MORGULIRON.get(), "Ender Morgul Iron Ore");
        add(TolkienBlocks.ORE_NETHER_MORGULIRON.get(), "Fiery Morgul Iron Ore");
        add(TolkienBlocks.ORE_DEEPSLATE_MORGULIRON.get(), "Deepslate Morgul Iron Ore");
        add(TolkienBlocks.BLOCK_MORGULIRON.get(), "Morgul Iron Block");
        add(TolkienBlocks.RAW_MORGULIRON_BLOCK.get(), "Raw Morgul Iron Block");
        add(TolkienBlocks.MORGULIRON_BARS.get(), "Morgul Iron Bars");
        add(TolkienBlocks.WALL_MORGULIRON.get(), "Morgul Iron Wall");
        add(TolkienBlocks.DOOR_MORGULIRON.get(), "Morgul Iron Door");
        add(TolkienBlocks.TRAPDOOR_MORGULIRON.get(), "Morgul Iron Trap Door");
        add(TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get(), "Morgul Iron Pressure Plate");
        add(TolkienBlocks.ORE_AMMOLITE.get(), "Ammolite Ore");
        add(TolkienBlocks.ORE_END_AMMOLITE.get(), "Ender Ammolite Ore");
        add(TolkienBlocks.ORE_NETHER_AMMOLITE.get(), "Firey Ammolite Ore");
        add(TolkienBlocks.ORE_DEEPSLATE_AMMOLITE.get(), "Deepslate Ammolite Ore");
        add(TolkienBlocks.BLOCK_AMMOLITE.get(), "Ammolite Block");
        add(TolkienBlocks.PANE_AMMOLITE.get(), "Ammolite Pane");
        add(TolkienBlocks.DOOR_DURIN.get(), "Door of Durin");

        // Wood & Foliage
        add(TolkienBlocks.LOG_MALLORN.get(), "Mallorn Log");
        add(TolkienBlocks.LOG_MIRKWOOD.get(), "Mirkwood Log");
        add(TolkienBlocks.LOG_CULUMALDA.get(), "Culumalda Log");
        add(TolkienBlocks.LOG_LEBETHRON.get(), "Lebethron Log");
        add(TolkienBlocks.LOG_DEADWOOD.get(), "Deadwood Log");
        add(TolkienBlocks.LOG_FANGORNOAK.get(), "Fangorn Oak Log");
        add(TolkienBlocks.STRIPPED_MALLORN_LOG.get(), "Stripped Mallorn Log");
        add(TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get(), "Stripped Mirkwood Log");
        add(TolkienBlocks.STRIPPED_CULUMALDA_LOG.get(), "Stripped Culumalda Log");
        add(TolkienBlocks.STRIPPED_LEBETHRON_LOG.get(), "Stripped Lebethron Log");
        add(TolkienBlocks.STRIPPED_DEADWOOD_LOG.get(), "Stripped Deadwood Log");
        add(TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get(), "Stripped Fangorn Oak Log");
        add(TolkienBlocks.WOOD_MALLORN.get(), "Mallorn Wood");
        add(TolkienBlocks.WOOD_MIRKWOOD.get(), "Mirkwood Wood");
        add(TolkienBlocks.WOOD_CULUMALDA.get(), "Culumalda Wood");
        add(TolkienBlocks.WOOD_LEBETHRON.get(), "Lebethron Wood");
        add(TolkienBlocks.WOOD_DEADWOOD.get(), "Deadwood Wood");
        add(TolkienBlocks.WOOD_FANGORNOAK.get(), "Fangorn Oak Wood");
        add(TolkienBlocks.STRIPPED_MALLORN_WOOD.get(), "Stripped Mallorn Wood");
        add(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get(), "Stripped Mirkwood Wood");
        add(TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get(), "Stripped Culumalda Wood");
        add(TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get(), "Stripped Lebethron Wood");
        add(TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get(), "Stripped Deadwood Wood");
        add(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get(), "Stripped Fangorn Oak Wood");
        add(TolkienBlocks.MALLORN_BUTTON.get(), "Mallorn Button");
        add(TolkienBlocks.MIRKWOOD_BUTTON.get(), "Mirkwood Button");
        add(TolkienBlocks.CULUMALDA_BUTTON.get(), "Culumalda Button");
        add(TolkienBlocks.LEBETHRON_BUTTON.get(), "Lebethron Button");
        add(TolkienBlocks.DEADWOOD_BUTTON.get(), "Deadwood Button");
        add(TolkienBlocks.FANGORNOAK_BUTTON.get(), "Fangorn Oak Button");
        add(TolkienBlocks.PLANKS_MALLORN.get(), "Mallorn Planks");
        add(TolkienBlocks.PLANKS_MIRKWOOD.get(), "Mirkwood Planks");
        add(TolkienBlocks.PLANKS_CULUMALDA.get(), "Culumalda Planks");
        add(TolkienBlocks.PLANKS_LEBETHRON.get(), "Lebethron Planks");
        add(TolkienBlocks.PLANKS_DEADWOOD.get(), "Deadwood Planks");
        add(TolkienBlocks.PLANKS_FANGORNOAK.get(), "Fangorn Oak Planks");
        add(TolkienItems.MALLORN_BOAT.get(), "Mallorn Boat");
        add(TolkienItems.MIRKWOOD_BOAT.get(), "Mirkwood Boat");
        add(TolkienItems.CULUMALDA_BOAT.get(), "Culumalda Boat");
        add(TolkienItems.LEBETHRON_BOAT.get(), "Lebethron Boat");
        add(TolkienItems.DEADWOOD_BOAT.get(), "Deadwood Boat");
        add(TolkienItems.FANGORNOAK_BOAT.get(), "Lebethron Boat");
        add(TolkienBlocks.STAIRS_MALLORN.get(), "Mallorn Stairs");
        add(TolkienBlocks.STAIRS_MIRKWOOD.get(), "Mirkwood Stairs");
        add(TolkienBlocks.STAIRS_CULUMALDA.get(), "Culumalda Stairs");
        add(TolkienBlocks.STAIRS_LEBETHRON.get(), "Lebethron Stairs");
        add(TolkienBlocks.STAIRS_DEADWOOD.get(), "Deadwood Stairs");
        add(TolkienBlocks.STAIRS_FANGORNOAK.get(), "Fangorn Oak Stairs");
        add(TolkienBlocks.SLAB_MALLORN.get(), "Mallorn Slab");
        add(TolkienBlocks.SLAB_MIRKWOOD.get(), "Mirkwood Slab");
        add(TolkienBlocks.SLAB_LEBETHRON.get(), "Lebethron Slab");
        add(TolkienBlocks.SLAB_CULUMALDA.get(), "Culumalda Slab");
        add(TolkienBlocks.SLAB_DEADWOOD.get(), "Deadwood Slab");
        add(TolkienBlocks.SLAB_FANGORNOAK.get(), "Fangorn Oak Slab");
        add(TolkienBlocks.DOOR_MALLORN.get(), "Mallorn Door");
        add(TolkienBlocks.DOOR_MIRKWOOD.get(), "Mirkwood Door");
        add(TolkienBlocks.DOOR_CULUMALDA.get(), "Culumalda Door");
        add(TolkienBlocks.DOOR_LEBETHRON.get(), "Lebethron Door");
        add(TolkienBlocks.DOOR_DEADWOOD.get(), "Deadwood Door");
        add(TolkienBlocks.DOOR_FANGORNOAK.get(), "Fangorn Oak Door");
        add(TolkienBlocks.FENCE_GATE_MALLORN.get(), "Mallorn Fence Gate");
        add(TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), "Mirkwood Fence Gate");
        add(TolkienBlocks.FENCE_GATE_CULUMALDA.get(), "Culumalda Fence Gate");
        add(TolkienBlocks.FENCE_GATE_LEBETHRON.get(), "Lebethron Fence Gate");
        add(TolkienBlocks.FENCE_GATE_DEADWOOD.get(), "Deadwood Fence Gate");
        add(TolkienBlocks.FENCE_GATE_FANGORNOAK.get(), "Fangorn Oak Fence Gate");
        add(TolkienBlocks.FENCE_MALLORN.get(), "Mallorn Fence");
        add(TolkienBlocks.FENCE_MIRKWOOD.get(), "Mirkwood Fence");
        add(TolkienBlocks.FENCE_CULUMALDA.get(), "Culumalda Fence");
        add(TolkienBlocks.FENCE_LEBETHRON.get(), "Lebethron Fence");
        add(TolkienBlocks.FENCE_DEADWOOD.get(), "Deadwood Fence");
        add(TolkienBlocks.FENCE_FANGORNOAK.get(), "Fangorn Oak Fence");
        add(TolkienBlocks.TRAPDOOR_MALLORN.get(), "Mallorn Trapdoor");
        add(TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), "Mirkwood Trapdoor");
        add(TolkienBlocks.TRAPDOOR_CULUMALDA.get(), "Culumalda Trapdoor");
        add(TolkienBlocks.TRAPDOOR_LEBETHRON.get(), "Lebethron Trapdoor");
        add(TolkienBlocks.TRAPDOOR_DEADWOOD.get(), "Deadwood Trapdoor");
        add(TolkienBlocks.TRAPDOOR_FANGORNOAK.get(), "Fangorn Oak Trapdoor");
        add(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(), "Mallorn Pressure Plate");
        add(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(), "Mirkwood Pressure Plate");
        add(TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(), "Culumalda Pressure Plate");
        add(TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(), "Lebethron Pressure Plate");
        add(TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get(), "Deadwood Pressure Plate");
        add(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get(), "Fangorn Oak Pressure Plate");
        add(TolkienBlocks.TORCH_MALLORN.get(), "Mallorn Torch");
        add(TolkienBlocks.TORCH_MIRKWOOD.get(), "Mirkwood Torch");
        add(TolkienBlocks.TORCH_CULUMALDA.get(), "Culumalda Torch");
        add(TolkienBlocks.TORCH_LEBETHRON.get(), "Lebethron Torch");
        add(TolkienBlocks.TORCH_DEADWOOD.get(), "Deadwood Torch");
        add(TolkienBlocks.TORCH_FANGORNOAK.get(), "Fangorn Oak Torch");
        add(TolkienBlocks.MALLORN_SIGN.get(), "Mallorn Sign");
        add(TolkienBlocks.MIRKWOOD_SIGN.get(), "Mirkwood Sign");
        add(TolkienBlocks.CULUMALDA_SIGN.get(), "Culumalda Sign");
        add(TolkienBlocks.LEBETHRON_SIGN.get(), "Lebethron Sign");
        add(TolkienBlocks.DEADWOOD_SIGN.get(), "Deadwood Sign");
        add(TolkienBlocks.FANGORNOAK_SIGN.get(), "Fangorn Oak Sign");
        add(TolkienBlocks.LEAVES_MALLORN.get(), "Mallorn Leaves");
        add(TolkienBlocks.LEAVES_MIRKWOOD.get(), "Mirkwood Leaves");
        add(TolkienBlocks.LEAVES_CULUMALDA.get(), "Culumalda Leaves");
        add(TolkienBlocks.LEAVES_LEBETHRON.get(), "Lebethron Leaves");
        add(TolkienBlocks.LEAVES_FANGORNOAK.get(), "Fangorn Oak Leaves");
        add(TolkienBlocks.LEAFPILE_MALLORN.get(), "Pile of Mallorn Leaves");
        add(TolkienBlocks.LEAFPILE_MIRKWOOD.get(), "Pile of Mirkwood Leaves");
        add(TolkienBlocks.LEAFPILE_CULUMALDA.get(), "Pile of Culumalda Leaves");
        add(TolkienBlocks.LEAFPILE_LEBETHRON.get(), "Pile of Lebethron Leaves");
        add(TolkienBlocks.LEAFPILE_FANGORNOAK.get(), "Pile of Fangorn Oak Leaves");
        add(TolkienBlocks.SAPLING_MALLORN.get(), "Mallorn Sapling");
        add(TolkienBlocks.SAPLING_MIRKWOOD.get(), "Mirkwood Sapling");
        add(TolkienBlocks.SAPLING_CULUMALDA.get(), "Culumalda Sapling");
        add(TolkienBlocks.SAPLING_LEBETHRON.get(), "Lebethron Sapling");
        add(TolkienBlocks.SAPLING_DEADWOOD.get(), "Deadwood Sapling");
        add(TolkienBlocks.SAPLING_FANGORNOAK.get(), "Fangorn Oak Sapling");
        add(TolkienBlocks.BARREL_MALLORN.get(), "Mallorn Barrel");
        add(TolkienBlocks.BARREL_MIRKWOOD.get(), "Mirkwood Barrel");
        add(TolkienBlocks.BARREL_CULUMALDA.get(), "Culumalda Barrel");
        add(TolkienBlocks.BARREL_LEBETHRON.get(), "Lebethron Barrel");
        add(TolkienBlocks.BARREL_DEADWOOD.get(), "Deadwood Barrel");
        add(TolkienBlocks.BARREL_FANGORNOAK.get(), "Fangorn Oak Barrel");

        // Plants & Flowers
        add(TolkienBlocks.BLOCK_DECAY_BLOOM.get(), "Bloom of Decay Block");
        add(TolkienBlocks.BLOCK_BLOOM_DECAY.get(), "Bloom of Decay Block");
        add(TolkienBlocks.MUSHROOM_DECAY_BLOOM.get(), "Bloom of Decay");
        add(TolkienBlocks.MUSHROOM_BLOOM_DECAY.get(), "Bloom of Decay");
        add(TolkienBlocks.FLOWER_SIMBELMYNE.get(), "Simbelmyne");
        add(TolkienBlocks.FLOWER_MIRKWOOD.get(), "Seregon");
        add(TolkienBlocks.FLOWER_ALFIRIN.get(), "Alfirin");
        add(TolkienBlocks.FLOWER_ATHELAS.get(), "Kingsfoil");
        add(TolkienBlocks.FLOWER_NIPHREDIL.get(), "Niphredil");
        add(TolkienBlocks.FLOWER_SWAMPMILKWEED.get(), "Swamp Milkweed");
        add(TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get(), "Lilly of the Valley");
        add(TolkienBlocks.POTTED_MUSHROOM_DECAY_BLOOM.get(), "Potted Bloom of Decay");
        add(TolkienBlocks.POTTED_MUSHROOM_BLOOM_DECAY.get(), "Potted Bloom of Decay");
        add(TolkienBlocks.POTTED_FLOWER_SIMBELMYNE.get(), "Potted Simbelmyne");
        add(TolkienBlocks.POTTED_FLOWER_MIRKWOOD.get(), "Potted Seregon");
        add(TolkienBlocks.POTTED_FLOWER_ALFIRIN.get(), "Potted Alfirin");
        add(TolkienBlocks.POTTED_FLOWER_ATHELAS.get(), "Potted Kingsfoil");
        add(TolkienBlocks.POTTED_FLOWER_NIPHREDIL.get(), "Potted Niphredil");
        add(TolkienBlocks.POTTED_FLOWER_SWAMPMILKWEED.get(), "Potted Swamp Milkweed");
        add(TolkienBlocks.POTTED_FLOWER_LILLYOFTHEVALLEY.get(), "Potted Lilly of the Valley");
        add(TolkienBlocks.POTTED_SAPLING_MALLORN.get(), "Potted Mallorn Sapling");
        add(TolkienBlocks.POTTED_SAPLING_MIRKWOOD.get(), "Potted Mirkwood Sapling");
        add(TolkienBlocks.POTTED_SAPLING_CULUMALDA.get(), "Potted Culumalda Sapling");
        add(TolkienBlocks.POTTED_SAPLING_LEBETHRON.get(), "Potted Lebethron Sapling");
        add(TolkienBlocks.POTTED_SAPLING_DEADWOOD.get(), "Potted Deadwood Sapling");
        add(TolkienBlocks.POTTED_SAPLING_FANGORNOAK.get(), "Potted Fangorn Oak Sapling");

        //Custom Blocks
        add(TolkienBlocks.BLOCK_HALLOWED.get(), "Hallowed Earth");
        add(TolkienBlocks.STONE_PATH.get(), "Stone Path");
        add(TolkienBlocks.TTMFIREPLACE.get(), "Fireplace");
        add(TolkienBlocks.PIGGYBANK.get(), "Piggy Bank");
        add(TolkienBlocks.BARREL_MITHRIL.get(), "Mithril Barrel");
        add(TolkienBlocks.BARREL_MORGULIRON.get(), "Morgul Iron Barrel");
        add(TolkienBlocks.BACKPACK.get(), "Adventurer's Backpack");
        add(TolkienBlocks.CHAMELEON_BLOCK.get(), "Chameleon Block");
        add(TolkienBlocks.KEY_STONE_BLOCK.get(), "Camouflage Key Stone");
        add(TolkienBlocks.CAMO_GLOWSTONE_BLOCK.get(), "Camouflage Glowstone");
        add(TolkienBlocks.CAMO_SMOKER_BLOCK.get(), "Camouflage Smoker");
        add(TolkienBlocks.ROCKPILE.get(), "Rockpile");
        add(TolkienBlocks.CAMO_FLUID_BLOCK.get(), "Camouflage Fluid Source");
        addLore(Item.byBlock(TolkienBlocks.CAMO_FLUID_BLOCK.get()), "Right-click with bucket to set fluid");
        add(TolkienBlocks.CAMO_CHEST_BLOCK.get(), "Camouflage Chest");
        add(TolkienBlocks.CAMO_SPAWNER_BLOCK.get(), "Camouflage Spawner");
        add(TolkienBlocks.MILESTONE_BLOCK.get(), "Fast-travel Milestone");
        add(TolkienBlocks.LOCKABLE_CHEST_BLOCK.get(), "Lockable Chest");
        add(TolkienBlocks.LOCKABLE_TREASURE_CHEST_BLOCK.get(), "Lockable Treasure Chest");
        add(TolkienBlocks.LOCKABLE_DOUBLE_CHEST_BLOCK.get(), "Lockable Double Chest");
        add(TolkienBlocks.LOCKABLE_DOUBLE_TREASURE_CHEST_BLOCK.get(), "Lockable Double Treasure Chest");
        add(TolkienBlocks.LIGHTNINGBUG_BLOCK.get(), "Lightning Bug");
        add(TolkienBlocks.ELVEN_LANTERN.get(), "Elven Lantern");
        add(TolkienBlocks.MORGUL_LANTERN.get(), "Morgul Lantern");
        add(TolkienBlocks.PLACARD.get(), "Country Signs");
        addLore(Item.byBlock(TolkienBlocks.PLACARD.get()), "Shift right-click on sign to cycle types");
        add(TolkienBlocks.SLEEPING_BAG_BLACK.get(), "Black Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_BLACK.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_BLUE.get(), "Blue Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_BLUE.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_BROWN.get(), "Brown Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_BROWN.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_CYAN.get(), "Cyan Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_CYAN.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_GRAY.get(), "Gray Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_GRAY.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_GREEN.get(), "Green Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_GREEN.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_LIGHT_BLUE.get(), "Light Blue Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_LIGHT_BLUE.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_LIGHT_GRAY.get(), "Light Gray Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_LIGHT_GRAY.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_LIME.get(), "Lime Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_LIME.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_MAGENTA.get(), "Magenta Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_MAGENTA.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_ORANGE.get(), "Orange Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_ORANGE.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_PINK.get(), "Pink Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_PINK.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_PURPLE.get(), "Purple Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_PURPLE.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_RED.get(), "Red Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_RED.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_WHITE.get(), "White Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_WHITE.get()), ChatFormatting.RED + "Does not reset spawn point.");
        add(TolkienBlocks.SLEEPING_BAG_YELLOW.get(), "Yellow Sleeping Bag");
        addLore(Item.byBlock(TolkienBlocks.SLEEPING_BAG_YELLOW.get()), ChatFormatting.RED + "Does not reset spawn point.");
    }

    private void items() {
        //Components
        add(TolkienItems.RAW_MITHRIL.get(), "Raw Mithril");
        add(TolkienItems.DUST_MITHRIL.get(), "Mithril Dust");
        add(TolkienItems.NUGGET_MITHRIL.get(), "Mithril Nugget");
        add(TolkienItems.INGOT_MITHRIL.get(), "Mithril Ingot");
        add(TolkienItems.RAW_MORGULIRON.get(), "Raw Morgul Iron");
        add(TolkienItems.DUST_MORGULIRON.get(), "Morgul Iron Dust");
        add(TolkienItems.NUGGET_MORGULIRON.get(), "Morgul Iron Nugget");
        add(TolkienItems.INGOT_MORGULIRON.get(), "Morgul Iron Ingot");
        add(TolkienItems.GEM_AMMOLITE.get(), "Star of Elendil");
        add(TolkienItems.CREBAIN_FEATHER.get(), "Crebain Feather");
        add(TolkienItems.BIRD_FEATHER.get(), "Bird Feather");
        add(TolkienItems.MUMAKIL_LEATHER.get(), "Mumakil Hide");
        add(TolkienItems.MONSTER_FUR.get(), "Monster Fur");
        add(TolkienItems.BOTTLE_FANCY.get(), "Fancy Bottle");
        add(TolkienItems.PIPEWEED_SEEDS.get(), "Pipeweed Seeds");
        add(TolkienItems.PIPEWEED_ITEM.get(), "Pipeweed");
        add(TolkienItems.GOLEM_STONE.get(), "Golem Stone");
        add(TolkienItems.GOLEM_STONE_EARTH.get(), ChatFormatting.DARK_GREEN + "Earth " + ChatFormatting.RESET + "Golem Stone");
        add(TolkienItems.GOLEM_STONE_AIR.get(), ChatFormatting.YELLOW + "Air " + ChatFormatting.RESET + "Golem Stone");
        add(TolkienItems.GOLEM_STONE_FIRE.get(), ChatFormatting.DARK_RED + "Fire " + ChatFormatting.RESET + "Golem Stone");
        add(TolkienItems.GOLEM_STONE_WATER.get(), ChatFormatting.BLUE + "Water " + ChatFormatting.RESET + "Golem Stone");
        add(TolkienItems.GOLEM_STONE_SUMMON.get(), "Summon Legendary " + ChatFormatting.AQUA + "Mithril " + ChatFormatting.RESET + "Golem");
        addLore(TolkienItems.GOLEM_STONE_SUMMON.get(), "Ancient artifact to summon a Legendary Golem");
        add(TolkienItems.KEY_RING.get(), "Key Ring");
        addLore(TolkienItems.KEY_RING.get(), "Store your excess keys");
        add(TolkienItems.BRONZE_KEY.get(), "Bronze Key");
        addLore(TolkienItems.BRONZE_KEY.get(), "Uses left: ");
        add(TolkienItems.SILVER_KEY.get(), "Silver Key");
        addLore(TolkienItems.SILVER_KEY.get(), "Uses left: ");
        add(TolkienItems.GOLD_KEY.get(), "Gold Key");
        addLore(TolkienItems.GOLD_KEY.get(), "Uses left: ");
        add(TolkienItems.MITHRIL_KEY.get(), "Mithril Key");
        addLore(TolkienItems.MITHRIL_KEY.get(), "Uses left: ");
        add(TolkienItems.MASTER_KEY.get(), "Master Key");
        addLore(TolkienItems.MASTER_KEY.get(), "Uses left: ");
        add(TolkienItems.HYPE_HORN.get(), "All the HYPE!!");


        add("item_info.tolkienmobs.sneak_right_click_activate", "Shift right-click to activate/deactivate effect");
        add("container.tolkienmobs.ttmfireplace", "Fireplace");
        add("container.tolkienmobs.piggybank", "Piggy Bank");
        add("container.tolkienmobs.backpack", "Adventurer's Backpack");
        add("container.tolkienmobs.upgrade", "Backpack Upgrades");
        add("container.tolkienmobs.coin_pouch", "Coin Pouch");
        add("container.tolkienmobs.key_ring", "Key Ring");
        add("screen.tolkienmobs.bronze_key", "Key Setup");
        add("screen.tolkienmobs.silver_key", "Key Setup");
        add("screen.tolkienmobs.gold_key", "Key Setup");
        add("screen.tolkienmobs.mithril_key", "Key Setup");
        add("screen.tolkienmobs.master_key", "Key Setup");
        add("screen.tolkienmobs.milestone", "Pick your destination");
        add("screen.tolkienmobs.camo_spawner", "Spawner Setup");
    }

    private void quest() {
        add(TolkienItems.ITEM_BERYL.get(), ChatFormatting.DARK_AQUA + "Glorfindel's Beryl");
        addLore(TolkienItems.ITEM_BERYL.get(), "Magical gem loaned to you to help on your search");
        add(TolkienItems.ITEM_FORTRESSMAP.get(), ChatFormatting.DARK_AQUA + "Fortress Plans");
        addLore(TolkienItems.ITEM_FORTRESSMAP.get(), "Seemingly fragile, surprising it survived so long");
        add(TolkienItems.ITEM_WATCHERHEART.get(), ChatFormatting.DARK_PURPLE + "Stone Heart");
        addLore(TolkienItems.ITEM_WATCHERHEART.get(), "This heart has a disturbing and fearful energy");
        add(TolkienItems.ITEM_WATCHERHEART_CRACKED.get(), ChatFormatting.DARK_PURPLE + "Watcher's Heart");
        addLore(TolkienItems.ITEM_WATCHERHEART_CRACKED.get(), "This heart looks like it has seen better days");
        add(TolkienItems.ITEM_KEYSTONE.get(), ChatFormatting.DARK_AQUA + "Key-Stone");
        addLore(TolkienItems.ITEM_KEYSTONE.get(), "Almost forgotten, this is the \"Key\" to opening a secret entrance");
        add(TolkienItems.ITEM_DARKSADDLE.get(), ChatFormatting.BLUE + "Black Steed's Bridle");
        addLore(TolkienItems.ITEM_DARKSADDLE.get(), "Taken from the corpse of an evil Horse");
        add(TolkienItems.ITEM_ARTIFACT.get(), ChatFormatting.GOLD + "Lost Artifact");
        addLore(TolkienItems.ITEM_ARTIFACT.get(), "This objects emits a dark aura");
        add(TolkienItems.ITEM_BLANKPAPER.get(), ChatFormatting.DARK_PURPLE + "Manuscript page");
        addLore(TolkienItems.ITEM_BLANKPAPER.get(), "Lost page from a manuscript");
        add(TolkienItems.ITEM_FANCYARMOR.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Armour " + ChatFormatting.DARK_GREEN + "(Repaired)");
        addLore(TolkienItems.ITEM_FANCYARMOR.get(), "After repairs Cromhes' armour looks amazing");
        add(TolkienItems.ITEM_FANCYCLOTH.get(), ChatFormatting.DARK_GREEN + "Fine Cloth");
        addLore(TolkienItems.ITEM_FANCYCLOTH.get(), "Finely woven cloth for decoration");
        add(TolkienItems.ITEM_FANCYHAMMER.get(), ChatFormatting.BLUE + "Thror's Hammer");
        addLore(TolkienItems.ITEM_FANCYHAMMER.get(), "A hammer especially designed for shaping mithril");
        add(TolkienItems.ITEM_FANCYKEY.get(), ChatFormatting.DARK_AQUA + "Dungeon Key");
        addLore(TolkienItems.ITEM_FANCYKEY.get(), "Key made by Elssuli from the items you collected");
        add(TolkienItems.ITEM_FANCYSHIELD2.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Shield " + ChatFormatting.DARK_GREEN + "(Repaired)");
        addLore(TolkienItems.ITEM_FANCYSHIELD2.get(), "This shield hails from an unknown origin though it belongs to Cromhes");
        add(TolkienItems.ITEM_FANCYSWORD.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Sword " + ChatFormatting.DARK_GREEN + "(Repaired)");
        addLore(TolkienItems.ITEM_FANCYSWORD.get(), "The smith has done an amazing job on this sword");
        add(TolkienItems.ITEM_FANCYSWORD2.get(), ChatFormatting.DARK_RED + "Apostle");
        addLore(TolkienItems.ITEM_FANCYSWORD2.get(), "Hope of Vengeance");
        add(TolkienItems.ITEM_LETTER.get(), ChatFormatting.GOLD + "Thurdan's Letter");
        addLore(TolkienItems.ITEM_LETTER.get(), "Thurdan's letter about Dreulhara");
        add(TolkienItems.ITEM_SCROLL.get(), ChatFormatting.DARK_PURPLE + "Ashen Scroll");
        addLore(TolkienItems.ITEM_SCROLL.get(), "A tattered scroll, covered in small runes");
        add(TolkienItems.ITEM_SCROLL2.get(), ChatFormatting.DARK_PURPLE + "Translated Scroll");
        addLore(TolkienItems.ITEM_SCROLL2.get(), "A scroll translated by Elrond with instructions to get into the dungeons of Moria");
        add(TolkienItems.ITEM_SPECIALFLOWER.get(), ChatFormatting.DARK_GREEN + "Hag Hops");
        addLore(TolkienItems.ITEM_SPECIALFLOWER.get(), "Especially potent flower brewers love to use");
        add(TolkienItems.ITEM_STORYBOOK.get(), ChatFormatting.DARK_AQUA + "Hobbit Story");
        addLore(TolkienItems.ITEM_STORYBOOK.get(), "Story told by a hobbit after Bilbo disappeared on his birthday");
        add(TolkienItems.ITEM_STORYBOOK2.get(), ChatFormatting.GOLD + "Bilbo's Story");
        addLore(TolkienItems.ITEM_STORYBOOK2.get(), "Gift Created by Gaffer Gamgee for Bilbo's Birthday");
        add(TolkienItems.ITEM_STORYBOOK3.get(), ChatFormatting.DARK_AQUA + "Petunia's Manuscript");
        addLore(TolkienItems.ITEM_STORYBOOK3.get(), "Journal of the adventures a young Hobbit has");
        add(TolkienItems.ITEM_STORYBOOK4.get(), ChatFormatting.DARK_GREEN + "Ancient Tome");
        addLore(TolkienItems.ITEM_STORYBOOK4.get(), "This book looks like it has seen better days");
        add(TolkienItems.ITEM_WORNARMOR.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Armour");
        addLore(TolkienItems.ITEM_WORNARMOR.get(), "Item stolen from Cromhes while he was held captive");
        add(TolkienItems.ITEM_WORNHELM.get(), ChatFormatting.DARK_PURPLE + "Broken Helm");
        addLore(TolkienItems.ITEM_WORNHELM.get(), "Item Thonrum wanted repaired");
        add(TolkienItems.ITEM_WORNKEY.get(), ChatFormatting.DARK_PURPLE + "Bandit's Key");
        addLore(TolkienItems.ITEM_WORNKEY.get(), "Ruined key found on the body of a bandit captain");
        add(TolkienItems.ITEM_WORNPICK.get(), ChatFormatting.DARK_PURPLE + "Worn-out Pickaxe");
        addLore(TolkienItems.ITEM_WORNPICK.get(), "Item Thonrum wanted repaired");
        add(TolkienItems.ITEM_WORNSHIELD.get(), ChatFormatting.DARK_PURPLE + "Damaged Shield");
        addLore(TolkienItems.ITEM_WORNSHIELD.get(), "Item Thonrum wanted repaired");
        add(TolkienItems.ITEM_WORNSHIELD2.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Shield");
        addLore(TolkienItems.ITEM_WORNSHIELD2.get(), "Item stolen from Cromhes while he was held captive");
        add(TolkienItems.ITEM_WORNSWORD.get(), ChatFormatting.DARK_PURPLE + "Cromhes' Sword");
        addLore(TolkienItems.ITEM_WORNSWORD.get(), "Item stolen from Cromhes while he was held captive");
        add(TolkienItems.ITEM_WOVENBASKET.get(), ChatFormatting.DARK_GREEN + "Woven Basket");
        addLore(TolkienItems.ITEM_WOVENBASKET.get(), "Baskets woven to pay tribute");
        add(TolkienItems.ITEM_WRITTENPAPER.get(), ChatFormatting.DARK_PURPLE + "Statue Rubbing");
        addLore(TolkienItems.ITEM_WRITTENPAPER.get(), "Charcoal rubbing of the runes on the Watchers near Khazad-dum");
        add(TolkienItems.ITEM_PUNGENTHERB.get(), ChatFormatting.DARK_RED + "Pungent Herb");
        addLore(TolkienItems.ITEM_PUNGENTHERB.get(), "Herb used in powerful potions");
        add(TolkienItems.ITEM_LOCKPICK.get(), "Lock-Pick");
        addLore(TolkienItems.ITEM_LOCKPICK.get(), "Item used to open doors");
        add(TolkienItems.ITEM_BROKENSWORD.get(), ChatFormatting.DARK_PURPLE + "Sword Hilt");
        addLore(TolkienItems.ITEM_BROKENSWORD.get(), "All that was left of the Dwarven sword");
        add(TolkienItems.ITEM_REFORGEDSWORD.get(), "Reforged Sword");
        addLore(TolkienItems.ITEM_REFORGEDSWORD.get(), "Remarkably good Dwarven work");
        add(TolkienItems.ITEM_MAGIC_CLOTH.get(), ChatFormatting.DARK_PURPLE + "Special Magic Cloth");
        addLore(TolkienItems.ITEM_MAGIC_CLOTH.get(), "Fine Elven cloth made into a bag");
        add(TolkienItems.ITEM_MITHRILNUGGET.get(), ChatFormatting.DARK_PURPLE + "Bilbo's Nugget");
        addLore(TolkienItems.ITEM_MITHRILNUGGET.get(), "Token Bilbo took from his adventures in the Lonely Mountain");
        add(TolkienItems.ITEM_REMAINS.get(), ChatFormatting.DARK_GREEN + "Pile of Bones");
        addLore(TolkienItems.ITEM_REMAINS.get(), "Remains of some poor unfortunate soul");
        add(TolkienItems.ITEM_RUNE_STONE.get(), ChatFormatting.DARK_GREEN + "Ancient Rune Stone");
        addLore(TolkienItems.ITEM_RUNE_STONE.get(), "This stone houses very powerful dark magic");
        add(TolkienItems.ITEM_FANCYHELM.get(), "Fancy Helm");
        addLore(TolkienItems.ITEM_FANCYHELM.get(), "");
        add(TolkienItems.ITEM_KEYFRAGMENT.get(), "Key Fragment");
        addLore(TolkienItems.ITEM_KEYFRAGMENT.get(), "");
        add(TolkienItems.ITEM_OILYKEY.get(), "Oil-covered Key");
        addLore(TolkienItems.ITEM_OILYKEY.get(), "");
        add(TolkienItems.ITEM_FANCYPICK.get(), "Fancy Pick");
        addLore(TolkienItems.ITEM_FANCYPICK.get(), "");
        add(TolkienItems.ITEM_FANCYSHIELD.get(), "Fancy Shield");
        addLore(TolkienItems.ITEM_FANCYSHIELD.get(), "");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), "Backpack Upgrade - Base");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), "Used to create backpack upgrades");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE.get(), "Backpack Upgrade - Size");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE.get(), "Increase the storage capacity of your backpack (Max-2)");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID.get(), "Backpack Upgrade - Fluids");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID.get(), "Allows the carrying of 16 buckets of fluid (Max-1)");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), "Backpack Upgrade - Crafting");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), "Allows crating anywhere (Max-1)");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), "Backpack Upgrade - Sleeping Bag");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), "Carry around a sleeping bag (Max-1)");
        add(TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get(), "Backpack Upgrade - Campfire");
        addLore(TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get(), "Quick use campfire (Max-1)");

        add(TolkienItems.ITEM_DEV_TOOL.get(), "Dev Tool");
        addLore(TolkienItems.ITEM_DEV_TOOL.get(), "Use to temporarily reset camo blocks to default texture");
        add(TolkienItems.ITEM_DEV_DEBUG_TOOL.get(), "Dev Tool - Debug");
        addLore(TolkienItems.ITEM_DEV_DEBUG_TOOL.get(), "Used to reset certain blocks");
    }

    private void coinToken() {
        add(TolkienItems.ITEM_COIN_BRONZE.get(), "Bronze Coin");
        addLore(TolkienItems.ITEM_COIN_BRONZE.get(), "64 Can be traded for 1 Silver coin");
        add(TolkienItems.ITEM_COIN_SILVER.get(), "Silver Coin");
        addLore(TolkienItems.ITEM_COIN_SILVER.get(), "64 Can be traded for 1 Gold coin");
        add(TolkienItems.ITEM_COIN_GOLD.get(), "Gold Coin");
        addLore(TolkienItems.ITEM_COIN_GOLD.get(), "64 Can be traded for 1 Mithril coin");
        add(TolkienItems.ITEM_COIN_MITHRIL.get(), "Mithril Coin");
        addLore(TolkienItems.ITEM_COIN_MITHRIL.get(), "Very rare and valuable coin");
        add(TolkienItems.ITEM_DARKSIGIL.get(), ChatFormatting.DARK_PURPLE + "Dark Sigil");
        addLore(TolkienItems.ITEM_DARKSIGIL.get(), "Dark symbol dropped by a minion");
        add(TolkienItems.ITEM_FACTIONCOIN.get(), ChatFormatting.BLUE + "Faction Token");
        addLore(TolkienItems.ITEM_FACTIONCOIN.get(), "Use this to raise faction standing");
        add(TolkienItems.ITEM_FACTIONTOKEN.get(), ChatFormatting.DARK_AQUA + "Faction Coin");
        addLore(TolkienItems.ITEM_FACTIONTOKEN.get(), "This can be traded for unique items");
        add(TolkienItems.ITEM_CAVECOMPLETE.get(), ChatFormatting.DARK_AQUA + "Cave completion Token");
        addLore(TolkienItems.ITEM_CAVECOMPLETE.get(), "Proof of killing the troll");
        add(TolkienItems.ITEM_WATCHERCOMPLETE.get(), ChatFormatting.DARK_AQUA + "Watcher Token");
        addLore(TolkienItems.ITEM_WATCHERCOMPLETE.get(), "Proof of defeating the Watcher");
        add(TolkienItems.ITEM_TOKEN_EASTERN_ALLIANCE.get(), ChatFormatting.DARK_RED + "Eastern Alliance Token");
        addLore(TolkienItems.ITEM_TOKEN_EASTERN_ALLIANCE.get(), "Proof of your decision of who you chose to align with");
        add(TolkienItems.ITEM_TOKEN_WESTERN_ALLIANCE.get(), ChatFormatting.DARK_BLUE + "Western Alliance Token");
        addLore(TolkienItems.ITEM_TOKEN_WESTERN_ALLIANCE.get(), "Proof of your decision of who you chose to align with");
        add(TolkienItems.COIN_POUCH.get(), "Coin Pouch");
        addLore(TolkienItems.COIN_POUCH.get(), ChatFormatting.GOLD + "Store your excess coins");
    }

    private void creativeTabGroups() {
        add("itemGroup.tolkienmobs.tools", "TolkienTweaks Tools");
        add("itemGroup.tolkienmobs.mats", "TolkienTweaks Materials & World Gen");
        add("itemGroup.tolkienmobs.deco", "TolkienTweaks Decorative");
        add("itemGroup.tolkienmobs.spawn", "TolkienTweaks Mobs");
        add("itemGroup.tolkienmobs.food", "TolkienTweaks Food");
        add("itemGroup.tolkienmobs.quest", "TolkienTweaks Creative Items");
        add("itemGroup.tolkienmobs.signs", "TolkienTweaks Placards");
    }

    private void potion() {
        add("effect.tolkienmobs.ent_draught", "Ent Draught");
        add("item.minecraft.potion.effect.ent_draught", "Potion of Ent Draught");
        add("item.minecraft.splash_potion.effect.ent_draught", "Splash Potion of Ent Draught");
        add("item.minecraft.lingering_potion.effect.ent_draught", "Lingering Potion of Ent Draught");
        add("item.minecraft.tipped_arrow.effect.ent_draught", "Arrow of Ent Draught");
        add("effect.tolkienmobs.elf_vitality", "Elvish Life-span");
        add("item.minecraft.potion.effect.elf_vitality", "Potion of Elvish Life-span");
        add("item.minecraft.splash_potion.effect.elf_vitality", "Splash Potion of Elvish Life-span");
        add("item.minecraft.lingering_potion.effect.elf_vitality", "Lingering Potion of Elvish Life-span");
        add("item.minecraft.tipped_arrow.effect.elf_vitality", "Arrow of Elvish Life-span");
        add("effect.tolkienmobs.inventory_corrosion", "Corrosion");
        add("item.minecraft.potion.effect.inventory_corrosion", "Potion of Corrosion");
        add("item.minecraft.splash_potion.effect.inventory_corrosion", "Splash Potion of Corrosion");
        add("item.minecraft.lingering_potion.effect.inventory_corrosion", "Lingering Potion of Corrosion");
        add("item.minecraft.tipped_arrow.effect.inventory_corrosion", "Arrow of Corrosion");
        add("effect.tolkienmobs.personal_blacksmith", "Portable Blacksmith");
        add("item.minecraft.potion.effect.personal_blacksmith", "Potion of Portable Blacksmith");
        add("item.minecraft.splash_potion.effect.personal_blacksmith", "Splash Potion of Portable Blacksmith");
        add("item.minecraft.lingering_potion.effect.personal_blacksmith", "Lingering Potion of Portable Blacksmith");
        add("item.minecraft.tipped_arrow.effect.personal_blacksmith", "Arrow of Portable Blacksmith");
        add("effect.tolkienmobs.dread_aura", "Great Dread");
        add("item.minecraft.potion.effect.dread_aura", "Potion of Great Dread");
        add("item.minecraft.splash_potion.effect.dread_aura", "Splash Potion of Great Dread");
        add("item.minecraft.lingering_potion.effect.dread_aura", "Lingering Potion of Great Dread");
        add("item.minecraft.tipped_arrow.effect.dread_aura", "Arrow of Great Dread");
        add("effect.tolkienmobs.elven_nimbleness", "Elvish Nimbleness");
        add("item.minecraft.potion.effect.elven_nimbleness", "Potion of Elvish Nimbleness");
        add("item.minecraft.splash_potion.effect.elven_nimbleness", "Splash Potion of Elvish Nimbleness");
        add("item.minecraft.lingering_potion.effect.elven_nimbleness", "Lingering Potion of Elvish Nimbleness");
        add("item.minecraft.tipped_arrow.effect.elven_nimbleness", "Arrow of Elvish Nimbleness");
        add("effect.tolkienmobs.sleepnesia", "Sleepnesia");
        add("item.minecraft.potion.effect.sleepnesia", "Potion of Sleepnesia");
        add("item.minecraft.splash_potion.effect.sleepnesia", "Splash Potion of Sleepnesia");
        add("item.minecraft.lingering_potion.effect.sleepnesia", "Lingering Potion of Sleepnesia");
        add("item.minecraft.tipped_arrow.effect.sleepnesia", "Arrow of Sleepnesia");
        add("effect.tolkienmobs.crippling_terror", "Paralysing Fear");
        add("item.minecraft.potion.effect.crippling_terror", "Potion of Paralysing Fear");
        add("item.minecraft.splash_potion.effect.crippling_terror", "Splash Potion of Paralysing Fear");
        add("item.minecraft.lingering_potion.effect.crippling_terror", "Lingering Potion of Paralysing Fear");
        add("item.minecraft.tipped_arrow.effect.crippling_terror", "Arrow of Paralysing Fear");
        add("effect.tolkienmobs.blessing_of_eru", "Eru's Blessing");
        add("item.minecraft.potion.effect.blessing_of_eru", "Potion of Eru's Blessing");
        add("item.minecraft.splash_potion.effect.blessing_of_eru", "Splash Potion of Eru's Blessing");
        add("item.minecraft.lingering_potion.effect.blessing_of_eru", "Lingering Potion of Eru's Blessing");
        add("item.minecraft.tipped_arrow.effect.blessing_of_eru", "Arrow of Eru's Blessing");
        add("effect.tolkienmobs.elemental_drowning", "Elemental Drowning");
        add("item.minecraft.potion.effect.elemental_drowning", "Potion of Elemental Drowning");
        add("item.minecraft.splash_potion.effect.elemental_drowning", "Splash Potion of Elemental Drowning");
        add("item.minecraft.lingering_potion.effect.elemental_drowning", "Lingering Potion of Elemental Drowning");
        add("item.minecraft.tipped_arrow.effect.elemental_drowning", "Arrow of Elemental Drowning");
        add("effect.tolkienmobs.elemental_tornado",                          "Elemental Tornado");
        add("item.minecraft.potion.effect.elemental_tornado",                "Potion of Elemental Tornado");
        add("item.minecraft.splash_potion.effect.elemental_tornado",         "Splash Potion of Elemental Tornado");
        add("item.minecraft.lingering_potion.effect.elemental_tornado",      "Lingering Potion of Elemental Tornado");
        add("item.minecraft.tipped_arrow.effect.elemental_tornado",          "Arrow of Elemental Tornado");
        add("effect.tolkienmobs.elemental_lightning", "Elemental Lightning");
        add("item.minecraft.potion.effect.elemental_lightning", "Potion of Elemental Lightning");
        add("item.minecraft.splash_potion.effect.elemental_lightning", "Splash Potion of Elemental Lightning");
        add("item.minecraft.lingering_potion.effect.elemental_lightning", "Lingering Potion of Elemental Lightning");
        add("item.minecraft.tipped_arrow.effect.elemental_lightning", "Arrow of Elemental Lightning");
        add("effect.tolkienmobs.elemental_flight", "Elemental Flying");
        add("item.minecraft.potion.effect.elemental_flight", "Potion of Elemental Flying");
        add("item.minecraft.splash_potion.effect.elemental_flight", "Splash Potion of Elemental Flying");
        add("item.minecraft.lingering_potion.effect.elemental_flight", "Lingering Potion of Elemental Flying");
        add("item.minecraft.tipped_arrow.effect.elemental_flight", "Arrow of Elemental Flying");
        add("effect.tolkienmobs.elemental_burning", "Elemental Burning");
        add("item.minecraft.potion.effect.elemental_burning", "Potion of Elemental Burning");
        add("item.minecraft.splash_potion.effect.elemental_burning", "Splash Potion of Elemental Burning");
        add("item.minecraft.lingering_potion.effect.elemental_burning", "Lingering Potion of Elemental Burning");
        add("item.minecraft.tipped_arrow.effect.elemental_burning", "Arrow of Elemental Burning");
        add("item.minecraft.potion.effect.istari", "Istari Potion");
        add("item.minecraft.splash_potion.effect.istari", "Istari Splash Potion");
        add("item.minecraft.lingering_potion.effect.istari", "Istari Lingering Potion");
    }

    private void enchants() {
        add("enchantment.tolkienmobs.balrogs_mark", "Balrog's Mark");
        add("enchantment.tolkienmobs.balrogs_mark.desc", "Works similar to frost walker but changes the ground to magma blocks.");
        add("enchantment.tolkienmobs.elven_longevity", "Elven Longevity");
        add("enchantment.tolkienmobs.elven_longevity.desc", "Live like the elves! Adds additional hearts to the player.");
        add("enchantment.tolkienmobs.gondor_resolve", "Gondorian Resolve");
        add("enchantment.tolkienmobs.gondor_resolve.desc", "Stand your ground! Prevents knockback.");
        add("enchantment.tolkienmobs.dwarven_endurance", "Dwarven Endurance");
        add("enchantment.tolkienmobs.dwarven_endurance.desc", "Provides sustenance to journey long from home.");
        add("enchantment.tolkienmobs.hobbit_plow", "Hobbit Plowing");
        add("enchantment.tolkienmobs.hobbit_plow.desc", "Farm like the experts.  Will plow an area up to 9x9.");
        add("enchantment.tolkienmobs.hobbit_harvest", "Hobbit Harvest");
        add("enchantment.tolkienmobs.hobbit_harvest.desc", "Harvest and replant seeds from your bountiful farms. up to 9x9");
    }

    private void food() {
        add(TolkienItems.DRINK_ENT_DRAUGHT.get(), "Ent Draught");
        add(TolkienItems.DRINK_PERSONAL_BLACKSMITH.get(), "Portable Blacksmith");
        add(TolkienItems.DRINK_ELF_FLEETFOOT.get(), "Blessing of the Elves");
        add(TolkienItems.DRINK_ELF_VITALITY.get(), "Vitality");
        add(TolkienItems.DRINK_ERU_BLESSING.get(), "Blessing of Eru Iluvatar");
        add(TolkienItems.MIRUVOR.get(), "Miruvor");
        add(TolkienItems.GROG.get(), "Grog");
        add(TolkienItems.LEMBAS.get(), "Lembas");
        add(TolkienItems.HONEY_CAKE.get(), "Honey Cake");
        add(TolkienItems.CRAM.get(), "Cram");
        add(TolkienItems.MONSTER_FLESH.get(), "Monster Flesh");
        add(TolkienItems.INSECT.get(), "Frog Bait");
        add(TolkienItems.GOLDEN_INSECT.get(), "Fancy Frog Bait");
        add(TolkienItems.TREE_ACORN.get(), "Acorn");
        add(TolkienItems.GOLDEN_TREE_ACORN.get(), "Golden Acorn");
        add(TolkienItems.FOOD_HONEY.get(), "Honey");
    }

    private void records() {
        add(TolkienItems.RECORD_RIVENDELL.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_RIVENDELL.get() + ".desc", "Riders of Rivendell");
        add(TolkienItems.RECORD_LOTHLORIEN.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_LOTHLORIEN.get() + ".desc", "The Light of Lothlorien");
        add(TolkienItems.RECORD_EREBOR.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_EREBOR.get() + ".desc", "All That Glitters in Erebor");
        add(TolkienItems.RECORD_WILLOW.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_WILLOW.get() + ".desc", "Willow Song");
        add(TolkienItems.RECORD_MINASTIRITH.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_MINASTIRITH.get() + ".desc", "White Tree of Gondor");
        add(TolkienItems.RECORD_EDORAS.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_EDORAS.get() + ".desc", "Wake of Edoras");
        add(TolkienItems.RECORD_WBATTLE.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_WBATTLE.get() + ".desc", "Fly My Pretties!");
        add(TolkienItems.RECORD_MURDERFROG.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_MURDERFROG.get() + ".desc", "Ballad of Murder-Frog");
        add(TolkienItems.RECORD_REDER.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_REDER.get() + ".desc", "Evil Incarnate");
        add(TolkienItems.RECORD_FUMBLE.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_FUMBLE.get() + ".desc", "Bumbling Oaf");
        add(TolkienItems.RECORD_BOMBADIL.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_BOMBADIL.get() + ".desc", "Mystery of Tom Bombadil");
        add(TolkienItems.RECORD_HOBBITS.get(), "Travelling Music");
        add("item.tolkienmobs." + TolkienItems.RECORD_HOBBITS.get() + ".desc", "Concerning Hobbits - Remix");
    }

    private void tools() {
        add(TolkienItems.HELMET_MITHRIL.get(), "Mithril Helmet");
        add(TolkienItems.CHESTPLATE_MITHRIL.get(), "Mithril Chestplate");
        add(TolkienItems.LEGGINGS_MITHRIL.get(), "Mithril Leggings");
        add(TolkienItems.BOOTS_MITHRIL.get(), "Mithril Boots");
        add(TolkienItems.MITHRIL_HORSE_ARMOR.get(), "Mithril Horse Armor");
        add(TolkienItems.HELMET_MORGULIRON.get(), "Morgul Iron Helmet");
        add(TolkienItems.CHESTPLATE_MORGULIRON.get(), "Morgul Iron Chestplate");
        add(TolkienItems.LEGGINGS_MORGULIRON.get(), "Morgul Iron Leggings");
        add(TolkienItems.BOOTS_MORGULIRON.get(), "Morgul Iron Boots");
        add(TolkienItems.MORGULIRON_HORSE_ARMOR.get(), "Morguliron Horse Armor");

        add(TolkienItems.AXE_MITHRIL.get(), "Mithril Axe");
        add(TolkienItems.SWORD_MITHRIL.get(), "Mithril Sword");
        add(TolkienItems.HOE_MITHRIL.get(), "Mithril Hoe");
        add(TolkienItems.SHOVEL_MITHRIL.get(), "Mithril Shovel");
        add(TolkienItems.PICKAXE_MITHRIL.get(), "Mithril Pickaxe");
        add(TolkienItems.AXE_MORGULIRON.get(), "Morgul Iron Axe");
        add(TolkienItems.SWORD_MORGULIRON.get(), "Morgul Iron Sword");
        add(TolkienItems.HOE_MORGULIRON.get(), "Morgul Iron Hoe");
        add(TolkienItems.SHOVEL_MORGULIRON.get(), "Morgul Iron Shovel");
        add(TolkienItems.PICKAXE_MORGULIRON.get(), "Morgul Iron Pickaxe");
        add(TolkienItems.ELVEN_BOW.get(), "Elven Bow");
        add(TolkienItems.URUK_BOW.get(), "Uruk Bow");
        add(TolkienItems.SWORD_WITCHKING.get(), ChatFormatting.DARK_RED + "Sword of the Witch-king");
        addLore(TolkienItems.SWORD_WITCHKING.get(), "Forged in fear, the powerful weapon of the Witch-king");
        add(TolkienItems.SWORD_URUK.get(), "Uruk Sword");
        add(TolkienItems.WHIP_FIRE.get(), ChatFormatting.DARK_RED + "Whip of Fire");
        addLore(TolkienItems.WHIP_FIRE.get(), "Primary weapon of the fearsome Balrog");
        add(TolkienItems.CLUB_WOODEN.get(), "Troll Club");
        addLore(TolkienItems.CLUB_WOODEN.get(), "Deadly weapon favoured by Trolls");
        add(TolkienItems.GALADHRIM_ARROW.get(), "Galadhrim Arrows");
        add(TolkienItems.UTUMNO_ARROW.get(), "Utumno Arrows");
        add(TolkienItems.FELLBEAST_FIREBALL.get(), "Fell Beast Fireball");
        add(TolkienItems.BOULDER.get(), "Boulder");
        add(TolkienItems.MORGUL_CRYSTAL.get(), "Morgul Crystal");
    }

    private void trinket(PrefixHelper helper) {
        add(TolkienItems.TRINKET_AMULET.get(), "Magical Amulet of ");
        add(TolkienItems.TRINKET_BELT.get(), "Magical Belt of ");
        add(TolkienItems.TRINKET_CHARM.get(), "Magical Charm of ");
        add(TolkienItems.TRINKET_RING.get(), "Magical Ring of ");
        add(TolkienItems.TRINKET_GLOVE.get(), "Magical Glove of ");
        add(TolkienItems.TRINKET_HAT.get(), "Magical Hat of ");
        add(TolkienItems.TRINKET_CLOAK.get(), "Magical Cloak of ");
    }

    private void biomes(PrefixHelper helper) {
        helper.setPrefix("biome.tolkienmobs.");
        helper.add("biome_lorinand", "Lothlorien");
        helper.add("biome_mirkwood", "Mirkwood");
        helper.add("biome_marshes", "Nindalf Marsh");
        helper.add("biome_mordor", "Mordor");
        helper.add("biome_barrowdowns", "Tyrn Gorthad");
        helper.add("biome_dagorlad", "The Brown Lands");
        helper.add("biome_shire", "The Shire");
        helper.add("biome_fangorn", "Fangorn Forest");
        helper.add("biome_haradwaith", "Haradwaith");
        helper.add("biome_oldforest", "Old Forest");
        helper.add("biome_gladden", "Gladden Fields");
        helper.add("biome_firien", "Firien Wood");
        helper.add("biome_hithaeglir", "Misty Mountains");
        helper.add("biome_ironhills", "Iron Hills");
        add("generator.tolkienmobs.arda", "Middle-earth");
    }

    private void merchants(PrefixHelper helper) {
        helper.setPrefix("entity.tolkienmobs.entityttmhuman.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");
        helper.setPrefix("entity.tolkienmobs.entityttmdwarf.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");
        helper.setPrefix("entity.tolkienmobs.entityttmelves.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");
        helper.setPrefix("entity.tolkienmobs.entityttmhobbit.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");
        helper.setPrefix("entity.tolkienmobs.entityttmdesertdweller.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");
        helper.setPrefix("entity.minecraft.villager.tolkienmobs.");
        helper.add("coin_trader", "Coin Trader");
        helper.add("grocery_store", "Grocery Store");
        helper.add("pet_merchant", "Pet Merchant");
        helper.add("junk_trader", "Junk Trader");
        helper.add("trinket_smith", "Magical Armourer");
        helper.add("trinket_tailor", "Magical Tailor");

    }

    private void entities() {
        // Ambient
//        add(TolkienEntities.ENTITY_TTM_RAT.get(), "Rat");
//        add(TolkienEntities.EGG_TTMRAT.get(), "Rat Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_SQUIRREL.get(), "Squirrel");
//        add(TolkienEntities.EGG_TTMSQUIRREL.get(), "Squirrel Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_FROG.get(), "Frog");
//        add(TolkienEntities.EGG_TTMFROG.get(), "Frog Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_SWARM.get(), "Midge Flies");
//        add(TolkienEntities.EGG_TTMSWARM.get(), "Midge Flies Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_THRUSH.get(), "Thrush");
//        add(TolkienEntities.EGG_TTMTHRUSH.get(), "Thrush Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_CREBAIN.get(), "Crebain");
//        add(TolkienEntities.EGG_TTMCREBAIN.get(), "Crebain Spawn Egg");
//
//        // Merchants
//        add(TolkienEntities.ENTITY_TTM_HUMAN.get(), "Human");
//        add(TolkienEntities.EGG_TTMHUMAN.get(), "Human Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_DWARF.get(), "Dwarf");
//        add(TolkienEntities.EGG_TTMDWARF.get(), "Dwarf Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_ELVES.get(), "Elf");
//        add(TolkienEntities.EGG_TTMELVES.get(), "Elf Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_HOBBIT.get(), "Hobbit");
//        add(TolkienEntities.EGG_TTMHOBBIT.get(), "Hobbit Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_DESERTDWELLER.get(), "Wandering Villager");
//        add(TolkienEntities.EGG_TTMDESERTDWELLER.get(), "Wandering Villager Spawn Egg");
//
//        // Monster
//        add(TolkienEntities.ENTITY_TTM_GOBLIN.get(), "Goblin");
//        add(TolkienEntities.EGG_TTMGOBLIN.get(), "Goblin Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_BARROW.get(), "Barrow Wight");
//        add(TolkienEntities.EGG_TTMBARROW.get(), "Barrow Wight Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_BRIGAND.get(), "Brigand");
//        add(TolkienEntities.EGG_TTMBRIGAND.get(), "Brigand Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_DEEPCLAW.get(), "Deepclaw");
//        add(TolkienEntities.EGG_TTMDEEPCLAW.get(), "Deepclaw Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_TREEENT.get(), "Tree Ent");
//        add(TolkienEntities.EGG_TTMTREEENT.get(), "Tree Ent Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_DUERGAR.get(), "Duergar");
//        add(TolkienEntities.EGG_TTMDUERGAR.get(), "Duergar Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_FELLSPIRIT.get(), "Fell Spirit");
//        add(TolkienEntities.EGG_TTMFELLSPIRIT.get(), "Fell Spirit Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_SWAMPHAG.get(), "Swamp Hag");
//        add(TolkienEntities.EGG_TTMSWAMPHAG.get(), "Swamp Hag Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MIRKWOODSPIDER.get(), "Mirkwood Spider");
//        add(TolkienEntities.EGG_TTMMIRKWOODSPIDER.get(), "Mirkwood Spider Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_HARADRIM.get(), "Haradrim");
//        add(TolkienEntities.EGG_TTMHARADRIM.get(), "Haradrim Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_TROLL.get(), "Cave Troll");
//        add(TolkienEntities.EGG_TTMTROLL.get(), "Cave Troll Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_WARG.get(), "Warg");
//        add(TolkienEntities.EGG_TTMWARG.get(), "Warg Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MORDORORC.get(), "Mordor Orc");
//        add(TolkienEntities.EGG_TTMMORDORORC.get(), "Mordor Orc Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_HURON.get(), "Huron");
//        add(TolkienEntities.EGG_TTMHURON.get(), "Huron Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_OATHBREAKER.get(), "Oath Breaker");
//        add(TolkienEntities.EGG_TTMOATHBREAKER.get(), "Oath Breaker Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_ROMIEWALKER.get(), "Romie Walker");
//        add(TolkienEntities.EGG_TTMROMIEWALKER.get(), "Romie Walker Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_URUKHAI.get(), "Uruk Hai");
//        add(TolkienEntities.EGG_TTMURUKHAI.get(), "Uruk Hai Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_ELEMENTALGOLEM.get(), "Elemental Golem");
//        add(TolkienEntities.EGG_TTMELEMENTALGOLEM.get(), "Elemental Golem Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MINOTAUR.get(), "Minotaur");
//        add(TolkienEntities.EGG_TTMMINOTAUR.get(), "Minotaur Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MIMICCHEST.get(), "Mimic");
//        add(TolkienEntities.EGG_TTMMIMICCHEST.get(), "Mimic Spawn Egg");
//
//        // Boss
//        add(TolkienEntities.ENTITY_TTM_GOBLINKING.get(), "Goblin King");
//        add(TolkienEntities.EGG_TTMGOBLINKING.get(), "Goblin King Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MITHRILGOLEM.get(), "Mithril Golem");
//        add(TolkienEntities.EGG_TTMMITHRILGOLEM.get(), "Mithril Golem Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MORGULIRONGOLEM.get(), "Morgul Iron Golem");
//        add(TolkienEntities.EGG_TTMMORGULIRONGOLEM.get(), "Morgul Iron Golem Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_WITCHKING.get(), "Witch King of Angmar");
//        add(TolkienEntities.EGG_TTMWITCHKING.get(), "Witch King Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_SHELOB.get(), "Shelob");
//        add(TolkienEntities.EGG_TTMSHELOB.get(), "Shelob Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_BALROG.get(), "Balrog");
//        add(TolkienEntities.EGG_TTMBALROG.get(), "Balrog Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_WATCHER.get(), "Watcher of the Water");
//        add(TolkienEntities.EGG_TTMWATCHER.get(), "Watcher Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_GWAHIR.get(), "Gwahir, Lord of the Eagles");
//        add(TolkienEntities.EGG_TTMGWAHIR.get(), "Gwahir Spawn Egg");
//        add(TolkienEntities.ENTITY_FELL_BEAST.get(), "Fell Beast");
//        add(TolkienEntities.EGG_TTMFELLBEAST.get(), "Fell Beast Spawn Egg");
//
//        // Passive
//        add(TolkienEntities.ENTITY_TTM_AUROCH.get(), "Auroch");
//        add(TolkienEntities.EGG_TTMAUROCH.get(), "Auroch Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_MUMAKIL.get(), "Mumakil");
//        add(TolkienEntities.EGG_TTMMUMAKIL.get(), "Mumakil Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_GOAT.get(), "Goat");
//        add(TolkienEntities.EGG_TTMGOAT.get(), "Goat Spawn Egg");
//
//        // Special
//        add(TolkienEntities.ENTITY_TTM_SHADOWFAX.get(), "Shadowfax");
//        add(TolkienEntities.EGG_TTMSHADOWFAX.get(), "Shadowfax Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_GOLLUM.get(), "Gollum");
//        add(TolkienEntities.EGG_TTMGOLLUM.get(), "Gollum Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_NAZGUL.get(), "Nazgul");
//        add(TolkienEntities.EGG_TTMNAZGUL.get(), "Nazgul Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_NAZGULSTEED.get(), "Nazgul Steed");
//        add(TolkienEntities.EGG_TTMNAZGULSTEED.get(), "Nazgul Steed Spawn Egg");
//        add(TolkienEntities.ENTITY_TTM_GREAT_EAGLE.get(), "Great Eagle");
//        add(TolkienEntities.EGG_TTMGREATEAGLE.get(), "Great Eagle Spawn Egg");
    }

    private void chatMessages(PrefixHelper helper) {
        add("tolkienmobs.msg.helpcomming", "Goblin King is attempting to call for help...Reinforcements have arrived!");
        add("tolkienmobs.msg.nohelp", "Goblin King is attempting to call for help, but no help came.");
        add("tolkienmobs.msg.nodrown", "Goblin King doesn't want to drown.");
        add("tolkienmobs.msg.onfire", "Goblin King is protecting himself from fire.");
        add("tolkienmobs.msg.healself", "Goblin King is hurt and is healing.");
        add("tolkienmobs.msg.speedup", "Goblin King is attempting to match your speed.");
        add("tolkienmobs.msg.nodrown.balrog", "The Balrog doesn't want to drown.");
        add("tolkienmobs.msg.onfire.balrog", "The Balrog is protecting himself from fire.");
        add("tolkienmobs.msg.healself.balrog", "The Balrog is hurt and is healing.");
        add("tolkienmobs.msg.speedup.balrog", "The Balrog is attempting to match your speed.");
        add("tolkienmobs.msg.nodrown.gwahir", "Gwahir doesn't want to drown.");
        add("tolkienmobs.msg.onfire.gwahir", "Gwahir is protecting himself from fire.");
        add("tolkienmobs.msg.healself.gwahir", "Gwahir is hurt and is healing.");
        add("tolkienmobs.msg.speedup.gwahir", "Gwahir is attempting to match your speed.");
        add("tolkienmobs.msg.helpcomming.shelob", "Shelob is attempting to call for help...Reinforcements have arrived!");
        add("tolkienmobs.msg.nohelp.shelob", "Shelob is attempting to call for help, but no help came.");
        add("tolkienmobs.msg.nodrown.shelob", "Shelob doesn't want to drown.");
        add("tolkienmobs.msg.onfire.shelob", "Shelob is protecting herself from fire.");
        add("tolkienmobs.msg.healself.shelob", "Shelob is hurt and is healing.");
        add("tolkienmobs.msg.speedup.shelob", "Shelob is attempting to match your speed.");
        add("tolkienmobs.msg.healself.watcher", "The Watcher is hurt and is healing.");
        add("tolkienmobs.msg.speedup.watcher", "The Watcher is attempting to match your speed.");
        add("tolkienmobs.msg.deploy_sleeping_bag", "Something is blocking the Sleepingbag.");
        add("tolkienmobs.msg.deploy_campfire", "Something is blocking the Campfire.");
        add("tolkienmobs.msg.wrong_key", "You have the wrong key.");
        add("tolkienmobs.msg.key_used", "Key has no remaining uses.");
        add("tolkienmobs.msg.cleared.entities", "All existing entities cleared...");
        add("tolkienmobs.msg.added.entities", "Entity added...");
        add("tolkienmobs.msg.payment", "Payment item set to...");
        add("tolkienmobs.msg.payment.insufficient", "Not enough for teleport!");
        add("tolkienmobs.msg.destination", "Destination not found");
    }

    private void gui(PrefixHelper helper) {
        helper.setPrefix("gui.tolkienmobs.backpack.");
        helper.add("fluid_storage",                     "Fluid Storage");
        helper.add("fluid",                             "Fluid:");
        helper.add("bed.remove",                        "Pick up Sleepingbag");
        helper.add("bed.deployed",                      "Lay out Sleepingbag");
        helper.add("campfire.remove",                   "Pick up Campfire");
        helper.add("campfire.deployed",                 "Set up Campfire");
        helper.add("close.upgrade",                     "Close upgrade window");
        helper.add("open.upgrade",                      "Open upgrade window");
        helper.setPrefix("gui.tolkienmobs.coin_pouch.");
        helper.add("title",                             "- store coins");
        helper.setPrefix("gui.tolkienmobs.key_ring.");
        helper.add("title",                             "- store keys");
        helper.setPrefix("gui.tolkienmobs.camo_fluid.");
        helper.add("fluid.instructions",                "Place fluid bucket in slot");
        helper.setPrefix("screen.tolkienmobs.base_key.");
        helper.add("instructions",                      "Set the code for this key. The code can be anything as long as it matches the block.");
        helper.add("keycode",                           "Key Code Field");
        helper.add("saved",                             "Code saved automatically");
        helper.add("instructions2",                     "Set the number of uses for this key. -1 for infinite or if you want to use the KeyStone consume mode.");
        helper.add("keycode2",                          "Number of uses");
        helper.add("saved2",                            "Uses saved automatically");
        helper.setPrefix("screen.tolkienmobs.milestone.");
        helper.add("instructions",                      "Set the name for this Milestone.");
        helper.add("milestonename",                     "Milestone Name");
        helper.add("saved",                             "Save Name");
        helper.setPrefix("screen.tolkienmobs.keystone.");
        helper.add("instructions",                      "Code to set, must match any keys.");
        helper.add("keystonecode",                      "Key Stone Code");
        helper.add("saved",                             "Save Code");
        helper.add("redstone.always",                   "Redstone activated with key");
        helper.add("redstone.always.active",            "Redstone activated with key");
        helper.add("redstone.delay",                    "Redstone stays on for x ticks");
        helper.add("redstone.delay.active",             "Redstone stays on for x ticks");
        helper.add("redstone.pulse",                    "Redstone acts like button when key used");
        helper.add("redstone.pulse.active",             "Redstone acts like button when key used");
        helper.add("key.keep",                          "Player keeps key");
        helper.add("key.consume",                       "Key taken when used");
        helper.add("delaytitle",                        "Tick Delay");
        helper.add("tickdelay",                         "Set the amount of delay for redstone");
        helper.add("savedelay",                         "Set Delay");
        helper.setPrefix("gui.tolkienmobs.lockable_chest.");
        helper.add("instructions",                      "Code to set, must match any keys.");
        helper.add("keystonecode",                      "Key Stone Code");
        helper.add("saved",                             "Save Code");
        helper.setPrefix("gui.tolkienmobs.lockable_double_chest.");
        helper.add("instructions",                      "Code to set, must match any keys.");
        helper.add("keystonecode",                      "Key Stone Code");
        helper.add("saved",                             "Save Code");
        helper.setPrefix("gui.tolkienmobs.lockable_treasure_chest.");
        helper.add("instructions",                      "Code to set, must match any keys.");
        helper.add("keystonecode",                      "Key Stone Code");
        helper.add("saved",                             "Save Code");
        helper.setPrefix("gui.tolkienmobs.lockable_double_treasure_chest.");
        helper.add("instructions",                      "Code to set, must match any keys.");
        helper.add("keystonecode",                      "Key Stone Code");
        helper.add("saved",                             "Save Code");
        helper.setPrefix("screen.tolkienmobs.camo_spawner.");
        helper.add("title",                             "Camo Spawner");
        helper.add("minSpawnDelay",                     "Delay-Min");
        helper.add("maxSpawnDelay",                     "Delay-Max");
        helper.add("activationRange",                   "Activate Range");
        helper.add("spawnRange",                        "Spawn Range");
        helper.add("spawnCount",                        "Spawn Count");
        helper.add("maxCluster",                        "Spawn-Max");
        helper.add("clusterRange",                      "Search Range");
        helper.add("requirePlayer",                     "Disable player required");
        helper.add("ignoreSpawnReq",                    "Ignore Spawn Requirements");
        helper.add("spawnerParticles",                  "Disable spawner particles");
        helper.add("entityTags",                        "Entity List");
        helper.add("saveValue",                         "Save Value");
        helper.add("minDelayValue",                     "Minimum start value for spawn countdown");
        helper.add("maxDelayValue",                     "Maximum start value for spawn countdown");
        helper.add("actRngeValue",                      "How far away player is before becoming active");
        helper.add("spwnRngeValue",                     "Max distance mobs will spawn from spawner");
        helper.add("spwnCountValue",                    "Maximum number of mobs to spawn");
        helper.add("maxClusterValue",                   "Number of mobs allowed before no more will spawn nearby");
        helper.add("clusterRangeValue",                 "Range to search for mobs to decide if anymore will spawn");

    }

    private void keyCommand() {
        add("key.category.tolkienmobs",                 "TolkienTweaks Mobs");
        add("key.tolkienmobs.backpack_key",             "Open Backpack");
    }

    private void TCon() {
        // Modifiers
        add("modifier.tolkienmobs.vingilote", "Vingilote");
        add("modifier.tolkienmobs.vingilote.flavor", "A priceless mineral.");
        add("modifier.tolkienmobs.vingilote.description", "The deeper you delve, the faster you mine with limitless durability!");
        add("modifier.tolkienmobs.gulduril", "Gulduril");
        add("modifier.tolkienmobs.gulduril.flavor", "A Dark Sorcery surrounds this.");
        add("modifier.tolkienmobs.gulduril.description", "Defeat your enemies fast and painfully!");

        // Materials
        add("material.tolkienmobs.shiny", "Mithril");
        add("material.tolkienmobs.uruk", "Morguliron");

        // Fluids
        add("block.tolkienmobs.molten_mithril_fluid", "Molten Mithril");
        add("fluid.tolkienmobs.molten_mithril", "Molten Mithril");
        add("item.tolkienmobs.molten_mithril_bucket", "Molten Mithril Bucket");
        add("block.tolkienmobs.molten_morguliron_fluid", "Molten Morguliron");
        add("fluid.tolkienmobs.molten_morguliron", "Molten Morguliron");
        add("item.tolkienmobs.molten_morguliron_bucket", "Molten Morguliron Bucket");
    }


        //region Helpers

    @Override
    public void add(Block key, String name) {
        if (key != null) super.add(key, name);
    }

    @Override
    public void add(Item key, String name) {
        if (key != null) super.add(key, name);
    }

    public void addLore(Item key, String lore) {
        add(key.getDescriptionId() + ".lore", lore);
    }

    public static class PrefixHelper {
        private LangGenerator generator;
        private String prefix;

        public PrefixHelper(LangGenerator generator) {
            this.generator = generator;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void add(String translationKey, String translation) {
            generator.add(prefix + translationKey, translation);
        }
    }

    //endregion

    @Override
    public String getName() {
        return NAME + " - English Translation";
    }
}
