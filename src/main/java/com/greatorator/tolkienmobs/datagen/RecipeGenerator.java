package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.crafting.FireplaceRecipeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by brandon3055 on 1/12/20
 */
public class RecipeGenerator extends RecipeProvider {

    public RecipeGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        //Left in a couple of my builders so you can see how the recipe builders work
        components(consumer);
        specialty(consumer);
        magic(consumer);
        fireplace(consumer);
    }

    private static void components(Consumer<IFinishedRecipe> consumer) {

        // Cooking & Smelting Recipes
        smeltingRecipe(TTMContent.DUST_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), 0.35F, 200, consumer);
        smeltingRecipe(TTMContent.DUST_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), 0.35F, 200, consumer);

        //Common Recipes
        storageRecipe(TTMContent.BLOCK_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        storageRecipe(TTMContent.INGOT_MITHRIL.get(), TTMContent.NUGGET_MITHRIL.get(), consumer);
        storageRecipe(TTMContent.BLOCK_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);
        storageRecipe(TTMContent.INGOT_MORGULIRON.get(), TTMContent.NUGGET_MORGULIRON.get(), consumer);

        barsRecipe(TTMContent.MITHRIL_BARS.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        barsRecipe(TTMContent.MORGULIRON_BARS.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        stairsRecipe(TTMContent.STAIRS_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        stairsRecipe(TTMContent.STAIRS_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        stairsRecipe(TTMContent.STAIRS_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        stairsRecipe(TTMContent.STAIRS_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);

        slabRecipe(TTMContent.SLAB_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        slabRecipe(TTMContent.SLAB_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        slabRecipe(TTMContent.SLAB_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        slabRecipe(TTMContent.SLAB_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);

        plankRecipe(TTMContent.PLANKS_MALLORN.get(), TTMContent.LOG_MALLORN.get(), consumer);
        plankRecipe(TTMContent.PLANKS_MIRKWOOD.get(), TTMContent.LOG_MIRKWOOD.get(), consumer);
        plankRecipe(TTMContent.PLANKS_CULUMALDA.get(), TTMContent.LOG_CULUMALDA.get(), consumer);
        plankRecipe(TTMContent.PLANKS_LEBETHRON.get(), TTMContent.LOG_LEBETHRON.get(), consumer);

        doorRecipe(TTMContent.DOOR_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        doorRecipe(TTMContent.DOOR_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        doorRecipe(TTMContent.DOOR_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        doorRecipe(TTMContent.DOOR_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);
        doorRecipe(TTMContent.DOOR_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        doorRecipe(TTMContent.DOOR_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        fenceGateRecipe(TTMContent.FENCE_GATE_MALLORN.get(), Items.STICK, TTMContent.PLANKS_MALLORN.get(), consumer);
        fenceGateRecipe(TTMContent.FENCE_GATE_MIRKWOOD.get(), Items.STICK, TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        fenceGateRecipe(TTMContent.FENCE_GATE_CULUMALDA.get(), Items.STICK, TTMContent.PLANKS_CULUMALDA.get(), consumer);
        fenceGateRecipe(TTMContent.FENCE_GATE_LEBETHRON.get(), Items.STICK, TTMContent.PLANKS_LEBETHRON.get(), consumer);

        fenceRecipe(TTMContent.FENCE_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), Items.STICK, consumer);
        fenceRecipe(TTMContent.FENCE_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), Items.STICK, consumer);
        fenceRecipe(TTMContent.FENCE_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), Items.STICK, consumer);
        fenceRecipe(TTMContent.FENCE_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), Items.STICK, consumer);

        trapDoorRecipe(TTMContent.TRAPDOOR_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        trapDoorRecipe(TTMContent.TRAPDOOR_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        trapDoorRecipe(TTMContent.TRAPDOOR_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        trapDoorRecipe(TTMContent.TRAPDOOR_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);
        metalTrapDoorRecipe(TTMContent.TRAPDOOR_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        metalTrapDoorRecipe(TTMContent.TRAPDOOR_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_MIRKWOOD.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_CULUMALDA.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_LEBETHRON.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        pressurePlateRecipe(TTMContent.PRESSURE_PLATE_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        torchRecipe(TTMContent.TORCH_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        torchRecipe(TTMContent.TORCH_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        torchRecipe(TTMContent.TORCH_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        torchRecipe(TTMContent.TORCH_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);

        signRecipe(TTMContent.MALLORN_SIGN_WOOD_TYPE.get(), TTMContent.PLANKS_MALLORN.get(), Items.STICK, consumer);
        signRecipe(TTMContent.MIRKWOOD_SIGN_WOOD_TYPE.get(), TTMContent.PLANKS_MIRKWOOD.get(), Items.STICK, consumer);
        signRecipe(TTMContent.CULUMALDA_SIGN_WOOD_TYPE.get(), TTMContent.PLANKS_CULUMALDA.get(), Items.STICK, consumer);
        signRecipe(TTMContent.LEBETHRON_SIGN_WOOD_TYPE.get(), TTMContent.PLANKS_LEBETHRON.get(), Items.STICK, consumer);

        helmetRecipe(TTMContent.HELMET_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), TTMContent.GEM_AMMOLITE.get(), consumer);
        helmetRecipe(TTMContent.HELMET_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), TTMContent.GEM_AMMOLITE.get(), consumer);

        leggingRecipe(TTMContent.LEGGINGS_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), TTMContent.GEM_AMMOLITE.get(), consumer);
        leggingRecipe(TTMContent.LEGGINGS_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), TTMContent.GEM_AMMOLITE.get(), consumer);

        bootRecipe(TTMContent.BOOTS_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), TTMContent.GEM_AMMOLITE.get(), consumer);
        bootRecipe(TTMContent.BOOTS_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), TTMContent.GEM_AMMOLITE.get(), consumer);

        chestRecipe(TTMContent.CHESTPLATE_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), TTMContent.GEM_AMMOLITE.get(), consumer);
        chestRecipe(TTMContent.CHESTPLATE_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), TTMContent.GEM_AMMOLITE.get(), consumer);

        swordRecipe(TTMContent.SWORD_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        swordRecipe(TTMContent.SWORD_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        axeRecipe(TTMContent.AXE_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        axeRecipe(TTMContent.AXE_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        hoeRecipe(TTMContent.HOE_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        hoeRecipe(TTMContent.HOE_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        shovelRecipe(TTMContent.SHOVEL_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        shovelRecipe(TTMContent.SHOVEL_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        pickaxeRecipe(TTMContent.PICKAXE_MITHRIL.get(), TTMContent.INGOT_MITHRIL.get(), consumer);
        pickaxeRecipe(TTMContent.PICKAXE_MORGULIRON.get(), TTMContent.INGOT_MORGULIRON.get(), consumer);

        // Backpack Upgrades
        upgradeRecipe(TTMContent.ITEM_BACKPACK_UPGRADE_SIZE.get(), TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get(), TTMContent.GEM_AMMOLITE.get(), consumer);
        upgradeRecipe(TTMContent.ITEM_BACKPACK_UPGRADE_FLUID.get(), TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get(), TTMContent.BOTTLE_FANCY.get(), consumer);
        upgradeRecipe(TTMContent.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.CRAFTING_TABLE, consumer);
        upgradeRecipe(TTMContent.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.WHITE_WOOL, consumer);
        upgradeRecipe(TTMContent.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get(), TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.CAMPFIRE, consumer);
        upgradeRecipe2(TTMContent.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), consumer);

        // Sleeping Bags
        sleepingRecipe(TTMContent.SLEEPING_BAG_RED_ITEM.get(), Blocks.RED_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_BLUE_ITEM.get(), Blocks.BLUE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_BLACK_ITEM.get(), Blocks.BLACK_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_BROWN_ITEM.get(), Blocks.BROWN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_CYAN_ITEM.get(), Blocks.CYAN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_GRAY_ITEM.get(), Blocks.GRAY_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_GREEN_ITEM.get(), Blocks.GREEN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_LIGHT_BLUE_ITEM.get(), Blocks.LIGHT_BLUE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_LIGHT_GRAY_ITEM.get(), Blocks.LIGHT_GRAY_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_LIME_ITEM.get(), Blocks.LIME_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_MAGENTA_ITEM.get(), Blocks.MAGENTA_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_ORANGE_ITEM.get(), Blocks.ORANGE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_PINK_ITEM.get(), Blocks.PINK_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_PURPLE_ITEM.get(), Blocks.PURPLE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_WHITE_ITEM.get(), Blocks.WHITE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TTMContent.SLEEPING_BAG_YELLOW_ITEM.get(), Blocks.YELLOW_CARPET, Blocks.WHITE_CARPET, consumer);

        // Shapeless Recipes
        unstorageRecipe(TTMContent.INGOT_MITHRIL.get(), TTMContent.BLOCK_MITHRIL.get(), consumer);
        unstorageRecipe(TTMContent.INGOT_MORGULIRON.get(), TTMContent.BLOCK_MORGULIRON.get(), consumer);

        dyeRecipe(Items.LIGHT_GRAY_DYE, TTMContent.FLOWER_SIMBELMYNE.get(), consumer);
        dyeRecipe(Items.RED_DYE, TTMContent.FLOWER_MIRKWOOD.get(), consumer);
        dyeRecipe(Items.ORANGE_DYE, TTMContent.FLOWER_ALFIRIN.get(), consumer);
        dyeRecipe(Items.GREEN_DYE, TTMContent.FLOWER_ATHELAS.get(), consumer);
        dyeRecipe(Items.WHITE_DYE, TTMContent.FLOWER_NIPHREDIL.get(), consumer);
        dyeRecipe(Items.CYAN_DYE, TTMContent.FLOWER_SWAMPMILKWEED.get(), consumer);
        dyeRecipe(Items.PINK_DYE, TTMContent.FLOWER_LILLYOFTHEVALLEY.get(), consumer);
    }

    private static void specialty(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(TTMContent.GOLEM_STONE_SUMMON.get())
                .pattern("MEM")
                .pattern("ASF")
                .pattern("OWO")
                .define('W', TTMContent.GOLEM_STONE_WATER.get())
                .define('A', TTMContent.GOLEM_STONE_AIR.get())
                .define('M', TTMContent.BLOCK_MITHRIL.get())
                .define('S', TTMContent.GOLEM_STONE.get())
                .define('F', TTMContent.GOLEM_STONE_FIRE.get())
                .define('E', TTMContent.GOLEM_STONE_EARTH.get())
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_golem_stones", has(TTMContent.GOLEM_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TTMFIREPLACE.get())
                .pattern("MMM")
                .pattern("A A")
                .pattern("AMA")
                .define('A', Blocks.BRICKS)
                .define('M', Blocks.GRAY_CONCRETE)
                .unlockedBy("has_oaklog", has(Items.BRICKS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.PIGGYBANK.get())
                .pattern("MZM")
                .pattern("M M")
                .pattern("MMM")
                .define('M', Blocks.PINK_CONCRETE)
                .define('Z', TTMContent.ITEM_COIN_GOLD.get())
                .unlockedBy("has_goldcoin", has(TTMContent.ITEM_COIN_GOLD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.BARREL_MITHRIL.get())
                .pattern("MZM")
                .pattern("A A")
                .pattern("MZM")
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('M', TTMContent.PLANKS_MALLORN.get())
                .define('Z', TTMContent.SLAB_MALLORN.get())
                .unlockedBy("has_mallornlog", has(TTMContent.LOG_MALLORN_ITEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.BARREL_MORGULIRON.get())
                .pattern("MZM")
                .pattern("A A")
                .pattern("MZM")
                .define('A', TTMContent.INGOT_MORGULIRON.get())
                .define('M', TTMContent.PLANKS_MIRKWOOD.get())
                .define('Z', TTMContent.SLAB_MIRKWOOD.get())
                .unlockedBy("has_mirkwoodlog", has(TTMContent.LOG_MIRKWOOD_ITEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.BLOCK_HALLOWED.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('A', TTMContent.GEM_AMMOLITE.get())
                .define('M', Blocks.GRASS_BLOCK)
                .unlockedBy("has_ammolite", has(TTMContent.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.STONE_PATH.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('A', Items.IRON_PICKAXE)
                .define('M', Blocks.MOSSY_COBBLESTONE)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.DOOR_DURIN.get())
                .pattern("MZM")
                .pattern("AMA")
                .pattern("MZM")
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('Z', TTMContent.GEM_AMMOLITE.get())
                .define('M', Blocks.STONE)
                .unlockedBy("has_ammolite", has(TTMContent.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.BACKPACK.get())
                .pattern("MAM")
                .pattern("AZA")
                .pattern("MAM")
                .define('A', Blocks.WHITE_WOOL)
                .define('M', Items.LEATHER)
                .define('Z', TTMContent.GEM_AMMOLITE.get())
                .unlockedBy("has_ammolite", has(TTMContent.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get())
                .pattern("MZM")
                .pattern("ZAZ")
                .pattern("MZM")
                .define('M', TTMContent.MUMAKIL_LEATHER.get())
                .define('A', TTMContent.GEM_AMMOLITE.get())
                .define('Z', Items.LEATHER)
                .unlockedBy("has_mumakil_leather", has(TTMContent.MUMAKIL_LEATHER.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.PLACARD.get())
                .pattern("AMA")
                .pattern("AMA")
                .pattern("- -")
                .define('M', ItemTags.createOptional(new ResourceLocation("forge", "planks")))
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('-', Items.STICK)
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);
    }

    public static void potions() {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.MIRUVOR.get(), PotionGenerator.ENT_DRAUGHT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.GEM_AMMOLITE.get(), PotionGenerator.BLESSING_OF_ERU.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.LEMBAS.get(), PotionGenerator.ELVISH_LIFE.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.GOLDEN_INSECT.get(), PotionGenerator.ELF_FLEETFOOT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.BLOCK_MITHRIL_ITEM.get(), PotionGenerator.PORTABLE_REPAIR.get()));
    }

    private static void magic(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_AMULET.get())
                .pattern("GMG")
                .pattern("G G")
                .pattern("MAM")
                .define('A', TTMContent.GEM_AMMOLITE.get())
                .define('M', TTMContent.INGOT_MITHRIL.get())
                .define('G', TTMContent.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_BELT.get())
                .pattern("GGG")
                .pattern("M G")
                .pattern("AMG")
                .define('A', TTMContent.GEM_AMMOLITE.get())
                .define('M', TTMContent.INGOT_MITHRIL.get())
                .define('G', TTMContent.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_CHARM.get())
                .pattern("MMG")
                .pattern("CAC")
                .pattern("MMM")
                .define('A', TTMContent.GEM_AMMOLITE.get())
                .define('M', TTMContent.INGOT_MITHRIL.get())
                .define('G', TTMContent.MUMAKIL_LEATHER.get())
                .define('C', Blocks.TERRACOTTA)
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_RING.get())
                .pattern("GM ")
                .pattern("M M")
                .pattern(" M ")
                .define('G', TTMContent.GEM_AMMOLITE.get())
                .define('M', TTMContent.INGOT_MITHRIL.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_GLOVE.get())
                .pattern(" MM")
                .pattern("AGA")
                .pattern("MMM")
                .define('G', TTMContent.GEM_AMMOLITE.get())
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('M', TTMContent.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_HAT.get())
                .pattern("AAA")
                .pattern("MGM")
                .pattern("   ")
                .define('G', TTMContent.GEM_AMMOLITE.get())
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('M', TTMContent.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TTMContent.TRINKET_CLOAK.get())
                .pattern("M M")
                .pattern("AGA")
                .pattern("MMM")
                .define('G', TTMContent.GEM_AMMOLITE.get())
                .define('A', TTMContent.INGOT_MITHRIL.get())
                .define('M', TTMContent.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TTMContent.INGOT_MITHRIL.get()))
                .save(consumer);
    }

    private static void fireplace(Consumer<IFinishedRecipe> consumer) {
        fireplaceRecipe1(Items.COOKED_BEEF, 50, 100, Items.BEEF, consumer);
        fireplaceRecipe1(Items.COOKED_CHICKEN, 50, 100, Items.CHICKEN, consumer);
        fireplaceRecipe1(Items.COOKED_COD, 50, 100, Items.COD, consumer);
        fireplaceRecipe1(Items.DRIED_KELP, 50, 100, Items.KELP, consumer);
        fireplaceRecipe1(Items.COOKED_MUTTON, 50, 100, Items.MUTTON, consumer);
        fireplaceRecipe1(Items.COOKED_PORKCHOP, 50, 100, Items.PORKCHOP, consumer);
        fireplaceRecipe1(Items.BAKED_POTATO, 50, 100, Items.POTATO, consumer);
        fireplaceRecipe1(Items.COOKED_RABBIT, 50, 100, Items.RABBIT, consumer);
        fireplaceRecipe1(Items.COOKED_SALMON, 50, 100, Items.SALMON, consumer);
        fireplaceRecipe2(TTMContent.LEMBAS.get(), 50, 100, TTMContent.CRAM.get(), TTMContent.FOOD_HONEY.get(), consumer);
        fireplaceRecipe2(Items.GOLDEN_APPLE, 100, 500, Items.APPLE, Items.GOLD_INGOT, consumer);
        fireplaceRecipe2(Items.ENCHANTED_GOLDEN_APPLE, 500, 200, Items.GOLDEN_APPLE, Items.GOLD_BLOCK, consumer);
        fireplaceRecipe3(TTMContent.MONSTER_FLESH.get(), 50, 100, consumer, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.COOKED_BEEF, Items.COOKED_SALMON, Items.COOKED_COD, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH);
    }

    // Helper Methods

    public static void upgradeRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#  ")
                .pattern("---")
                .pattern("   ")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:upgrade_" + output.asItem().getRegistryName().getPath());
    }

    public static void upgradeRecipe2(IItemProvider output, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#  ")
                .pattern("-  ")
                .pattern("   ")
                .define('-', ItemTags.createOptional(new ResourceLocation("forge", "sleeping_bags")))
                .define('#', TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get())
                .unlockedBy("has_" + TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get().asItem().getRegistryName().getPath(), has(TTMContent.ITEM_BACKPACK_UPGRADE_BASE.get()))
                .save(consumer, "tolkienmobs:upgrade2_" + output.asItem().getRegistryName().getPath());
    }

    public static void sleepingRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("AAB")
                .pattern("CCC")
                .define('A', input1)
                .define('B', input2)
                .define('C', Items.LEATHER)
                .unlockedBy("has_wool", has(input1))
                .save(consumer, "tolkienmobs:sleepingbag_" + output.asItem().getRegistryName().getPath());
    }

    public static void fireplaceRecipe1(IItemProvider output, int experience, int cookTime, IItemProvider input1, Consumer<IFinishedRecipe> consumer){
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .build(consumer);
    }

    public static void fireplaceRecipe2(IItemProvider output, int experience, int cookTime, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer){
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .ingredient(input2)
                .build(consumer);
    }

    public static void fireplaceRecipe3(IItemProvider output, int experience, int cookTime, Consumer<IFinishedRecipe> consumer, IItemProvider... input1){
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .build(consumer);
    }

    public static void chestRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#-#")
                .pattern("###")
                .pattern("###")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void leggingRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("#-#")
                .pattern("# #")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void bootRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#-#")
                .pattern("# #")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void helmetRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("#-#")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void swordRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#")
                .pattern("#")
                .pattern("-")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void axeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .pattern("#-")
                .pattern(" -")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void shovelRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#")
                .pattern("-")
                .pattern("-")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void pickaxeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern(" - ")
                .pattern(" - ")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void hoeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .pattern(" -")
                .pattern(" -")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void torchRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 16)
                .pattern("#")
                .pattern("-")
                .define('#', Items.COAL)
                .define('-', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:torch_" + output.asItem().getRegistryName().getPath());
    }

    public static void smeltingRecipe(IItemProvider output, IItemProvider input, float xp, int cook,Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(input),
                output, xp, cook)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:cooked_" + output.asItem().getRegistryName().getPath());
    }

    public static void signRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" - ")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:sign_" + output.asItem().getRegistryName().getPath());
    }

    public static void slabRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 6)
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void trapDoorRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void metalTrapDoorRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void pressurePlateRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void stairsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void barsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void storageRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:storage_" + output.asItem().getRegistryName().getPath());
    }

    public static void doorRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void fenceRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("#-#")
                .pattern("#-#")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer);
    }

    public static void fenceGateRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("-#-")
                .pattern("-#-")
                .define('-', input1)
                .define('#', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer);
    }

    public static void unstorageRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 9)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:unstorage_" + output.asItem().getRegistryName().getPath());
    }

    public static void dyeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 2)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:dye_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 4)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        super.run(cache);
    }

    private static class ModBrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public ModBrewingRecipe(Potion bottleInput, Item itemInput, Potion output) {
            this.bottleInput = bottleInput;
            this.itemInput = itemInput;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION), output);
        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if(isInput(input) && isIngredient(ingredient)) {
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
    }

    public static class NBTIngredient extends net.minecraftforge.common.crafting.NBTIngredient {
        public NBTIngredient(ItemStack stack) {
            super(stack);
        }
    }

    @Override
    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Recipes";
    }
}
