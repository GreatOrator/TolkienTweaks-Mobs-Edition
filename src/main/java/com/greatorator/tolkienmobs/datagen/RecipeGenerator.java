package com.greatorator.tolkienmobs.datagen;

import com.brandon3055.brandonscore.utils.DataUtils;
import com.google.common.collect.ImmutableList;
import com.greatorator.tolkienmobs.TolkienConfig;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.init.TolkienItems;
import com.greatorator.tolkienmobs.init.TolkienPotions;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

/**
 * Created by brandon3055 on 1/12/20
 */
public class RecipeGenerator extends RecipeProvider {
    protected static final ImmutableList<ItemLike> MITHRIL_SMELTABLES = ImmutableList.of(TolkienItems.ORE_MITHRIL_ITEM.get(), TolkienItems.ORE_END_MITHRIL_ITEM.get(), TolkienItems.ORE_NETHER_MITHRIL_ITEM.get(), TolkienItems.RAW_MITHRIL.get());
    protected static final ImmutableList<ItemLike> MORGULIRON_SMELTABLES = ImmutableList.of(TolkienItems.ORE_MORGULIRON_ITEM.get(), TolkienItems.ORE_END_MORGULIRON_ITEM.get(), TolkienItems.ORE_NETHER_MORGULIRON_ITEM.get(), TolkienItems.RAW_MORGULIRON.get());

    public RecipeGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        components(consumer);
        specialty(consumer);
        magic(consumer);
        fireplace(consumer);
        trinket(consumer);
    }

    private static void components(Consumer<FinishedRecipe> consumer) {

        // Cooking & Smelting Recipes
        smeltingRecipe(TolkienItems.RAW_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), 0.35F, 200, consumer);
        smeltingRecipe(TolkienItems.RAW_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), 0.35F, 200, consumer);

        oreBlasting(consumer, MITHRIL_SMELTABLES, TolkienItems.INGOT_MITHRIL.get(), 0.7F, 100, "mithril_ingot");
        oreBlasting(consumer, MORGULIRON_SMELTABLES, TolkienItems.INGOT_MORGULIRON.get(), 0.7F, 100, "morguliron_ingot");

        //Common Recipes
        storageRecipe(TolkienBlocks.BLOCK_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        storageRecipe(TolkienItems.INGOT_MITHRIL.get(), TolkienItems.NUGGET_MITHRIL.get(), consumer);
        storageRecipe(TolkienBlocks.RAW_MITHRIL_BLOCK.get(), TolkienItems.RAW_MITHRIL.get(), consumer);
        storageRecipe(TolkienBlocks.BLOCK_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);
        storageRecipe(TolkienItems.INGOT_MORGULIRON.get(), TolkienItems.NUGGET_MORGULIRON.get(), consumer);
        storageRecipe(TolkienBlocks.RAW_MORGULIRON_BLOCK.get(), TolkienItems.RAW_MORGULIRON.get(), consumer);

        smallStorageRecipe(TolkienBlocks.BLOCK_AMMOLITE.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);

        barsRecipe(TolkienBlocks.MITHRIL_BARS.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        barsRecipe(TolkienBlocks.MORGULIRON_BARS.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        stairsRecipe(TolkienBlocks.STAIRS_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        stairsRecipe(TolkienBlocks.STAIRS_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        stairsRecipe(TolkienBlocks.STAIRS_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        stairsRecipe(TolkienBlocks.STAIRS_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);

        slabRecipe(TolkienBlocks.SLAB_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        slabRecipe(TolkienBlocks.SLAB_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        slabRecipe(TolkienBlocks.SLAB_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        slabRecipe(TolkienBlocks.SLAB_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);

        buttonRecipe(TolkienBlocks.MALLORN_BUTTON.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        buttonRecipe(TolkienBlocks.MIRKWOOD_BUTTON.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        buttonRecipe(TolkienBlocks.CULUMALDA_BUTTON.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        buttonRecipe(TolkienBlocks.LEBETHRON_BUTTON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);

        plankRecipe(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.LOG_MALLORN.get(), consumer);
        plankRecipe(TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.LOG_MIRKWOOD.get(), consumer);
        plankRecipe(TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.LOG_CULUMALDA.get(), consumer);
        plankRecipe(TolkienBlocks.PLANKS_LEBETHRON.get(), TolkienBlocks.LOG_LEBETHRON.get(), consumer);
        plankRecipe(TolkienBlocks.PLANKS_DEADWOOD.get(), TolkienBlocks.LOG_DEADWOOD.get(), consumer);
        plankRecipe(TolkienBlocks.PLANKS_FANGORNOAK.get(), TolkienBlocks.LOG_FANGORNOAK.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.WOOD_MALLORN.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.WOOD_MIRKWOOD.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.WOOD_CULUMALDA.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_LEBETHRON.get(), TolkienBlocks.WOOD_LEBETHRON.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_DEADWOOD.get(), TolkienBlocks.WOOD_DEADWOOD.get(), consumer);
        plankWoodRecipe(TolkienBlocks.PLANKS_FANGORNOAK.get(), TolkienBlocks.WOOD_FANGORNOAK.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.STRIPPED_MALLORN_LOG.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.STRIPPED_CULUMALDA_LOG.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_LEBETHRON.get(), TolkienBlocks.STRIPPED_LEBETHRON_LOG.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_DEADWOOD.get(), TolkienBlocks.STRIPPED_DEADWOOD_LOG.get(), consumer);
        plankStrippedRecipe(TolkienBlocks.PLANKS_FANGORNOAK.get(), TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_MALLORN.get(), TolkienBlocks.STRIPPED_MALLORN_WOOD.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_MIRKWOOD.get(), TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_CULUMALDA.get(), TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_LEBETHRON.get(), TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_DEADWOOD.get(), TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get(), consumer);
        plankWoodStrippedRecipe(TolkienBlocks.PLANKS_FANGORNOAK.get(), TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get(), consumer);

        fullBarkRecipe(TolkienBlocks.STRIPPED_MALLORN_WOOD.get(), TolkienBlocks.STRIPPED_MALLORN_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.STRIPPED_MIRKWOOD_WOOD.get(), TolkienBlocks.STRIPPED_MIRKWOOD_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.STRIPPED_CULUMALDA_WOOD.get(), TolkienBlocks.STRIPPED_CULUMALDA_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.STRIPPED_LEBETHRON_WOOD.get(), TolkienBlocks.STRIPPED_LEBETHRON_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.STRIPPED_DEADWOOD_WOOD.get(), TolkienBlocks.STRIPPED_DEADWOOD_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.STRIPPED_FANGORNOAK_WOOD.get(), TolkienBlocks.STRIPPED_FANGORNOAK_LOG.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_MALLORN.get(), TolkienBlocks.LOG_MALLORN.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_MIRKWOOD.get(), TolkienBlocks.LOG_MIRKWOOD.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_CULUMALDA.get(), TolkienBlocks.LOG_CULUMALDA.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_LEBETHRON.get(), TolkienBlocks.LOG_LEBETHRON.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_DEADWOOD.get(), TolkienBlocks.LOG_DEADWOOD.get(), consumer);
        fullBarkRecipe(TolkienBlocks.WOOD_FANGORNOAK.get(), TolkienBlocks.LOG_FANGORNOAK.get(), consumer);

        doorRecipe(TolkienBlocks.DOOR_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_DEADWOOD.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_FANGORNOAK.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        doorRecipe(TolkienBlocks.DOOR_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        fenceGateRecipe(TolkienBlocks.FENCE_GATE_MALLORN.get(), Items.STICK, TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        fenceGateRecipe(TolkienBlocks.FENCE_GATE_MIRKWOOD.get(), Items.STICK, TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        fenceGateRecipe(TolkienBlocks.FENCE_GATE_CULUMALDA.get(), Items.STICK, TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        fenceGateRecipe(TolkienBlocks.FENCE_GATE_LEBETHRON.get(), Items.STICK, TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);
        fenceGateRecipe(TolkienBlocks.FENCE_GATE_DEADWOOD.get(), Items.STICK, TolkienBlocks.PLANKS_DEADWOOD.get(), consumer);
        fenceGateRecipe(TolkienBlocks.FENCE_GATE_FANGORNOAK.get(), Items.STICK, TolkienBlocks.PLANKS_FANGORNOAK.get(), consumer);

        fenceRecipe(TolkienBlocks.FENCE_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), Items.STICK, consumer);
        fenceRecipe(TolkienBlocks.FENCE_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), Items.STICK, consumer);
        fenceRecipe(TolkienBlocks.FENCE_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), Items.STICK, consumer);
        fenceRecipe(TolkienBlocks.FENCE_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), Items.STICK, consumer);
        fenceRecipe(TolkienBlocks.FENCE_DEADWOOD.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), Items.STICK, consumer);
        fenceRecipe(TolkienBlocks.FENCE_FANGORNOAK.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), Items.STICK, consumer);

        trapDoorRecipe(TolkienBlocks.TRAPDOOR_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        trapDoorRecipe(TolkienBlocks.TRAPDOOR_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        trapDoorRecipe(TolkienBlocks.TRAPDOOR_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        trapDoorRecipe(TolkienBlocks.TRAPDOOR_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);
        trapDoorRecipe(TolkienBlocks.TRAPDOOR_DEADWOOD.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), consumer);
        trapDoorRecipe(TolkienBlocks.TRAPDOOR_FANGORNOAK.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), consumer);
        metalTrapDoorRecipe(TolkienBlocks.TRAPDOOR_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        metalTrapDoorRecipe(TolkienBlocks.TRAPDOOR_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_DEADWOOD.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_FANGORNOAK.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        pressurePlateRecipe(TolkienBlocks.PRESSURE_PLATE_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        torchRecipe(TolkienBlocks.TORCH_MALLORN.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        torchRecipe(TolkienBlocks.TORCH_MIRKWOOD.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        torchRecipe(TolkienBlocks.TORCH_CULUMALDA.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        torchRecipe(TolkienBlocks.TORCH_LEBETHRON.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);
        torchRecipe(TolkienBlocks.TORCH_DEADWOOD.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), consumer);
        torchRecipe(TolkienBlocks.TORCH_FANGORNOAK.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), consumer);

        signRecipe(TolkienBlocks.MALLORN_SIGN.get(), TolkienBlocks.PLANKS_MALLORN.get(), Items.STICK, consumer);
        signRecipe(TolkienBlocks.MIRKWOOD_SIGN.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), Items.STICK, consumer);
        signRecipe(TolkienBlocks.CULUMALDA_SIGN.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), Items.STICK, consumer);
        signRecipe(TolkienBlocks.LEBETHRON_SIGN.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), Items.STICK, consumer);
        signRecipe(TolkienBlocks.DEADWOOD_SIGN.get(), TolkienBlocks.PLANKS_DEADWOOD.get(), Items.STICK, consumer);
        signRecipe(TolkienBlocks.FANGORNOAK_SIGN.get(), TolkienBlocks.PLANKS_FANGORNOAK.get(), Items.STICK, consumer);

        helmetRecipe(TolkienItems.HELMET_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        helmetRecipe(TolkienItems.HELMET_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);

        leggingRecipe(TolkienItems.LEGGINGS_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        leggingRecipe(TolkienItems.LEGGINGS_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);

        bootRecipe(TolkienItems.BOOTS_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        bootRecipe(TolkienItems.BOOTS_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);

        chestRecipe(TolkienItems.CHESTPLATE_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        chestRecipe(TolkienItems.CHESTPLATE_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);

        swordRecipe(TolkienItems.SWORD_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        swordRecipe(TolkienItems.SWORD_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        axeRecipe(TolkienItems.AXE_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        axeRecipe(TolkienItems.AXE_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        hoeRecipe(TolkienItems.HOE_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        hoeRecipe(TolkienItems.HOE_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        shovelRecipe(TolkienItems.SHOVEL_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        shovelRecipe(TolkienItems.SHOVEL_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        pickaxeRecipe(TolkienItems.PICKAXE_MITHRIL.get(), TolkienItems.INGOT_MITHRIL.get(), consumer);
        pickaxeRecipe(TolkienItems.PICKAXE_MORGULIRON.get(), TolkienItems.INGOT_MORGULIRON.get(), consumer);

        // Backpack Upgrades
        upgradeRecipe(TolkienItems.ITEM_BACKPACK_UPGRADE_SIZE.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        upgradeRecipe(TolkienItems.ITEM_BACKPACK_UPGRADE_FLUID.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), TolkienItems.BOTTLE_FANCY.get(), consumer);
        upgradeRecipe(TolkienItems.ITEM_BACKPACK_UPGRADE_CRAFTING.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.CRAFTING_TABLE, consumer);
        upgradeRecipe(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.WHITE_WOOL, consumer);
        upgradeRecipe(TolkienItems.ITEM_BACKPACK_UPGRADE_CAMPFIRE.get(), TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), Blocks.CAMPFIRE, consumer);
        upgradeRecipe2(TolkienItems.ITEM_BACKPACK_UPGRADE_SLEEPING.get(), consumer);

        // Sleeping Bags
        sleepingRecipe(TolkienItems.SLEEPING_BAG_RED_ITEM.get(), Blocks.RED_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_BLUE_ITEM.get(), Blocks.BLUE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_BLACK_ITEM.get(), Blocks.BLACK_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_BROWN_ITEM.get(), Blocks.BROWN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_CYAN_ITEM.get(), Blocks.CYAN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_GRAY_ITEM.get(), Blocks.GRAY_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_GREEN_ITEM.get(), Blocks.GREEN_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_LIGHT_BLUE_ITEM.get(), Blocks.LIGHT_BLUE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_LIGHT_GRAY_ITEM.get(), Blocks.LIGHT_GRAY_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_LIME_ITEM.get(), Blocks.LIME_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_MAGENTA_ITEM.get(), Blocks.MAGENTA_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_ORANGE_ITEM.get(), Blocks.ORANGE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_PINK_ITEM.get(), Blocks.PINK_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_PURPLE_ITEM.get(), Blocks.PURPLE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_WHITE_ITEM.get(), Blocks.WHITE_CARPET, Blocks.WHITE_CARPET, consumer);
        sleepingRecipe(TolkienItems.SLEEPING_BAG_YELLOW_ITEM.get(), Blocks.YELLOW_CARPET, Blocks.WHITE_CARPET, consumer);

        // Shapeless Recipes
        unstorageRecipe(TolkienItems.INGOT_MITHRIL.get(), TolkienBlocks.BLOCK_MITHRIL.get(), consumer);
        unstorageRecipe(TolkienItems.RAW_MITHRIL.get(), TolkienBlocks.RAW_MITHRIL_BLOCK.get(), consumer);
        unstorageRecipe(TolkienItems.INGOT_MORGULIRON.get(), TolkienBlocks.BLOCK_MORGULIRON.get(), consumer);
        unstorageRecipe(TolkienItems.RAW_MORGULIRON.get(), TolkienBlocks.RAW_MORGULIRON_BLOCK.get(), consumer);

        // Bow Recipes
        bowRecipe(TolkienItems.ELVEN_BOW.get(), TolkienItems.GEM_AMMOLITE.get(), consumer);
        bowRecipe(TolkienItems.URUK_BOW.get(), TolkienBlocks.BLOCK_MORGULIRON.get(), consumer);

        // Dye Recipes
        dyeRecipe(Items.LIGHT_GRAY_DYE, TolkienBlocks.FLOWER_SIMBELMYNE.get(), consumer);
        dyeRecipe(Items.RED_DYE, TolkienBlocks.FLOWER_MIRKWOOD.get(), consumer);
        dyeRecipe(Items.ORANGE_DYE, TolkienBlocks.FLOWER_ALFIRIN.get(), consumer);
        dyeRecipe(Items.GREEN_DYE, TolkienBlocks.FLOWER_ATHELAS.get(), consumer);
        dyeRecipe(Items.WHITE_DYE, TolkienBlocks.FLOWER_NIPHREDIL.get(), consumer);
        dyeRecipe(Items.CYAN_DYE, TolkienBlocks.FLOWER_SWAMPMILKWEED.get(), consumer);
        dyeRecipe(Items.PINK_DYE, TolkienBlocks.FLOWER_LILLYOFTHEVALLEY.get(), consumer);

        // Boat Recipes
        boatRecipe(TolkienItems.MALLORN_BOAT.get(), TolkienBlocks.PLANKS_MALLORN.get(), consumer);
        boatRecipe(TolkienItems.MIRKWOOD_BOAT.get(), TolkienBlocks.PLANKS_MIRKWOOD.get(), consumer);
        boatRecipe(TolkienItems.CULUMALDA_BOAT.get(), TolkienBlocks.PLANKS_CULUMALDA.get(), consumer);
        boatRecipe(TolkienItems.LEBETHRON_BOAT.get(), TolkienBlocks.PLANKS_LEBETHRON.get(), consumer);

        // Leafpile Recipes
        leafPileRecipe(TolkienBlocks.LEAFPILE_MALLORN.get(), TolkienBlocks.LEAVES_MALLORN.get(), consumer);
        leafPileRecipe(TolkienBlocks.LEAFPILE_MIRKWOOD.get(), TolkienBlocks.LEAVES_MIRKWOOD.get(), consumer);
        leafPileRecipe(TolkienBlocks.LEAFPILE_CULUMALDA.get(), TolkienBlocks.LEAVES_CULUMALDA.get(), consumer);
        leafPileRecipe(TolkienBlocks.LEAFPILE_LEBETHRON.get(), TolkienBlocks.LEAVES_LEBETHRON.get(), consumer);
        leafPileRecipe(TolkienBlocks.LEAFPILE_FANGORNOAK.get(), TolkienBlocks.LEAVES_FANGORNOAK.get(), consumer);
    }

    private static void specialty(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(TolkienItems.GOLEM_STONE_SUMMON.get())
                .pattern("MEM")
                .pattern("ASF")
                .pattern("OWO")
                .define('W', TolkienItems.GOLEM_STONE_WATER.get())
                .define('A', TolkienItems.GOLEM_STONE_AIR.get())
                .define('M', TolkienBlocks.BLOCK_MITHRIL.get())
                .define('S', TolkienItems.GOLEM_STONE.get())
                .define('F', TolkienItems.GOLEM_STONE_FIRE.get())
                .define('E', TolkienItems.GOLEM_STONE_EARTH.get())
                .define('O', Items.OBSIDIAN)
                .unlockedBy("has_golem_stones", has(TolkienItems.GOLEM_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.TTMFIREPLACE.get())
                .pattern("MMM")
                .pattern("A A")
                .pattern("AMA")
                .define('A', Blocks.BRICKS)
                .define('M', Blocks.GRAY_CONCRETE)
                .unlockedBy("has_oaklog", has(Items.BRICKS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.PIGGYBANK.get())
                .pattern("MZM")
                .pattern("M M")
                .pattern("MMM")
                .define('M', Blocks.PINK_CONCRETE)
                .define('Z', TolkienItems.ITEM_COIN_GOLD.get())
                .unlockedBy("has_goldcoin", has(TolkienItems.ITEM_COIN_GOLD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.BARREL_MITHRIL.get())
                .pattern("MZM")
                .pattern("A A")
                .pattern("MZM")
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('M', TolkienBlocks.PLANKS_MALLORN.get())
                .define('Z', TolkienBlocks.SLAB_MALLORN.get())
                .unlockedBy("has_mallornlog", has(TolkienItems.LOG_MALLORN_ITEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.BARREL_MORGULIRON.get())
                .pattern("MZM")
                .pattern("A A")
                .pattern("MZM")
                .define('A', TolkienItems.INGOT_MORGULIRON.get())
                .define('M', TolkienBlocks.PLANKS_MIRKWOOD.get())
                .define('Z', TolkienBlocks.SLAB_MIRKWOOD.get())
                .unlockedBy("has_mirkwoodlog", has(TolkienItems.LOG_MIRKWOOD_ITEM.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.BLOCK_HALLOWED.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('M', Blocks.GRASS_BLOCK)
                .unlockedBy("has_ammolite", has(TolkienItems.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.STONE_PATH.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('A', Items.IRON_PICKAXE)
                .define('M', Blocks.MOSSY_COBBLESTONE)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.GALADHRIM_ARROW.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('M', Items.ARROW)
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .unlockedBy("has_ammolite", has(TolkienItems.GEM_AMMOLITE.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(TolkienItems.UTUMNO_ARROW.get(), 8)
                .pattern("MMM")
                .pattern("MAM")
                .pattern("MMM")
                .define('M', Items.ARROW)
                .define('A', TolkienItems.INGOT_MORGULIRON.get())
                .unlockedBy("has_morguliron", has(TolkienItems.INGOT_MORGULIRON.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.DOOR_DURIN.get())
                .pattern("MZM")
                .pattern("AMA")
                .pattern("MZM")
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('Z', TolkienItems.GEM_AMMOLITE.get())
                .define('M', Blocks.STONE)
                .unlockedBy("has_ammolite", has(TolkienItems.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.BACKPACK.get())
                .pattern("MAM")
                .pattern("AZA")
                .pattern("MAM")
                .define('A', Blocks.WHITE_WOOL)
                .define('M', Items.LEATHER)
                .define('Z', TolkienItems.GEM_AMMOLITE.get())
                .unlockedBy("has_ammolite", has(TolkienItems.GEM_AMMOLITE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get(), 6)
                .pattern("MZM")
                .pattern("ZAZ")
                .pattern("MZM")
                .define('M', TolkienItems.MUMAKIL_LEATHER.get())
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('Z', Items.LEATHER)
                .unlockedBy("has_mumakil_leather", has(TolkienItems.MUMAKIL_LEATHER.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.COIN_POUCH.get(), 1)
                .pattern("ZAZ")
                .pattern("MZM")
                .define('M', TolkienItems.MUMAKIL_LEATHER.get())
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('Z', Items.LEATHER)
                .unlockedBy("has_mumakil_leather", has(TolkienItems.MUMAKIL_LEATHER.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.KEY_RING.get())
                .pattern("AMA")
                .pattern("AMA")
                .define('M', Items.IRON_INGOT)
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienBlocks.PLACARD.get())
                .pattern("AMA")
                .pattern("AMA")
                .pattern("- -")
                .define('M', ItemTags.create(new ResourceLocation("forge", "planks")))
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('-', Items.STICK)
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);
    }

    public static void potions() {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(TolkienPotions.ISTARI.get(), TolkienItems.MIRUVOR.get(), TolkienPotions.ENT_DRAUGHT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(TolkienPotions.ISTARI.get(), TolkienItems.GEM_AMMOLITE.get(), TolkienPotions.BLESSING_OF_ERU.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(TolkienPotions.ISTARI.get(), TolkienItems.LEMBAS.get(), TolkienPotions.ELVISH_LIFE.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(TolkienPotions.ISTARI.get(), TolkienItems.GOLDEN_INSECT.get(), TolkienPotions.ELF_FLEETFOOT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(TolkienPotions.ISTARI.get(), TolkienItems.BLOCK_MITHRIL_ITEM.get(), TolkienPotions.PORTABLE_REPAIR.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TolkienItems.GEM_AMMOLITE.get(), TolkienPotions.ISTARI.get()));
    }

    private static void magic(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_AMULET.get())
                .pattern("GMG")
                .pattern("G G")
                .pattern("MAM")
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('M', TolkienItems.INGOT_MITHRIL.get())
                .define('G', TolkienItems.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_BELT.get())
                .pattern("GGG")
                .pattern("M G")
                .pattern("AMG")
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('M', TolkienItems.INGOT_MITHRIL.get())
                .define('G', TolkienItems.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_CHARM.get())
                .pattern("MMG")
                .pattern("CAC")
                .pattern("MMM")
                .define('A', TolkienItems.GEM_AMMOLITE.get())
                .define('M', TolkienItems.INGOT_MITHRIL.get())
                .define('G', TolkienItems.MUMAKIL_LEATHER.get())
                .define('C', Blocks.TERRACOTTA)
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_RING.get())
                .pattern("GM ")
                .pattern("M M")
                .pattern(" M ")
                .define('G', TolkienItems.GEM_AMMOLITE.get())
                .define('M', TolkienItems.INGOT_MITHRIL.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_GLOVE.get())
                .pattern(" MM")
                .pattern("AGA")
                .pattern("MMM")
                .define('G', TolkienItems.GEM_AMMOLITE.get())
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('M', TolkienItems.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_HAT.get())
                .pattern("AAA")
                .pattern("MGM")
                .pattern("   ")
                .define('G', TolkienItems.GEM_AMMOLITE.get())
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('M', TolkienItems.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TolkienItems.TRINKET_CLOAK.get())
                .pattern("M M")
                .pattern("AGA")
                .pattern("MMM")
                .define('G', TolkienItems.GEM_AMMOLITE.get())
                .define('A', TolkienItems.INGOT_MITHRIL.get())
                .define('M', TolkienItems.MUMAKIL_LEATHER.get())
                .unlockedBy("has_mithril", has(TolkienItems.INGOT_MITHRIL.get()))
                .save(consumer);
    }

    private static void fireplace(Consumer<FinishedRecipe> consumer) {
        fireplaceNormal(Items.COOKED_BEEF, 50, 100, Items.BEEF, consumer);
        fireplaceNormal(Items.COOKED_CHICKEN, 50, 100, Items.CHICKEN, consumer);
        fireplaceNormal(Items.COOKED_COD, 50, 100, Items.COD, consumer);
        fireplaceNormal(Items.DRIED_KELP, 50, 100, Items.KELP, consumer);
        fireplaceNormal(Items.COOKED_MUTTON, 50, 100, Items.MUTTON, consumer);
        fireplaceNormal(Items.COOKED_PORKCHOP, 50, 100, Items.PORKCHOP, consumer);
        fireplaceNormal(Items.BAKED_POTATO, 50, 100, Items.POTATO, consumer);
        fireplaceNormal(Items.COOKED_RABBIT, 50, 100, Items.RABBIT, consumer);
        fireplaceNormal(Items.COOKED_SALMON, 50, 100, Items.SALMON, consumer);
        fireplaceCustom(TolkienItems.LEMBAS.get(), 50, 100, TolkienItems.CRAM.get(), TolkienItems.FOOD_HONEY.get(), consumer);
        fireplaceCustom(Items.GOLDEN_APPLE, 100, 500, Items.APPLE, Items.GOLD_INGOT, consumer);
        fireplaceCustom(Items.ENCHANTED_GOLDEN_APPLE, 500, 200, Items.GOLDEN_APPLE, Items.GOLD_BLOCK, consumer);
        fireplaceMultiple(TolkienItems.MONSTER_FLESH.get(), 50, 100, consumer, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.COOKED_BEEF, Items.COOKED_SALMON, Items.COOKED_COD, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH);
    }

    private static void trinket(Consumer<FinishedRecipe> consumer) {
        for (Potion potion : ForgeRegistries.POTIONS.getValues()) {
            if (potion.getEffects().isEmpty() || potion.getEffects().size() > 1) continue;
            MobEffect effect = potion.getEffects().get(0).getEffect();
            if (DataUtils.contains(TolkienConfig.potionArray, e -> e.equals(effect))) {
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_BELT.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_belt_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_BELT.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_AMULET.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_amulet_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_AMULET.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_BELT.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_belt_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_BELT.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_CHARM.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_charm_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_CHARM.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_RING.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_ring_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_RING.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_GLOVE.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_glove_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_GLOVE.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_HAT.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_hat_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_HAT.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
                consumer.accept(codechicken.lib.datagen.recipe.ShapelessRecipeBuilder.builder(TolkienItems.TRINKET_CLOAK.get().getTrinketForEffect(effect), new ResourceLocation(TolkienMobs.MODID, "trinket_cloak_" + potion.getRegistryName().getPath())).addIngredient(TolkienItems.TRINKET_CLOAK.get()).addIngredient(new NBTIngredient(PotionUtils.setPotion(new ItemStack(Items.POTION), potion))).build());
            }
        }
    }

    // Helper Methods

    public static void upgradeRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#  ")
                .pattern("---")
                .pattern("   ")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:upgrade_" + output.asItem().getRegistryName().getPath());
    }

    public static void upgradeRecipe2(ItemLike output, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#  ")
                .pattern("-  ")
                .pattern("   ")
                .define('-', ItemTags.create(new ResourceLocation("forge", "sleeping_bags")))
                .define('#', TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get())
                .unlockedBy("has_" + TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get().asItem().getRegistryName().getPath(), has(TolkienItems.ITEM_BACKPACK_UPGRADE_BASE.get()))
                .save(consumer, "tolkienmobs:upgrade2_" + output.asItem().getRegistryName().getPath());
    }

    public static void sleepingRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("AAB")
                .pattern("CCC")
                .define('A', input1)
                .define('B', input2)
                .define('C', Items.LEATHER)
                .unlockedBy("has_wool", has(input1))
                .save(consumer, "tolkienmobs:sleepingbag_" + output.asItem().getRegistryName().getPath());
    }

    public static void fireplaceNormal(ItemLike output, int experience, int cookTime, ItemLike input1, Consumer<FinishedRecipe> consumer) {
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(input1))
                .save(consumer);
    }

    public static void fireplaceCustom(ItemLike output, int experience, int cookTime, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .ingredient(input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(input1))
                .save(consumer);
    }

    public static void fireplaceMultiple(ItemLike output, int experience, int cookTime, Consumer<FinishedRecipe> consumer, ItemLike... input1) {
        FireplaceRecipeBuilder.fireplaceRecipe(output, experience, cookTime)
                .ingredient(input1)
                .unlockedBy("has_" + TolkienItems.TTMFIREPLACE_ITEM.get().asItem().getRegistryName().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(input1))
                .save(consumer);
    }

    public static void chestRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#-#")
                .pattern("###")
                .pattern("###")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void leggingRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("#-#")
                .pattern("# #")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void bootRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#-#")
                .pattern("# #")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void helmetRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("#-#")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:armor_" + output.asItem().getRegistryName().getPath());
    }

    public static void swordRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#")
                .pattern("#")
                .pattern("-")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void axeRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .pattern("#-")
                .pattern(" -")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void shovelRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#")
                .pattern("-")
                .pattern("-")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void pickaxeRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern(" - ")
                .pattern(" - ")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void hoeRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .pattern(" -")
                .pattern(" -")
                .define('#', input)
                .define('-', Items.STICK)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void torchRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 16)
                .pattern("#")
                .pattern("-")
                .define('#', Items.COAL)
                .define('-', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:torch_" + output.asItem().getRegistryName().getPath());
    }

    public static void smeltingRecipe(ItemLike output, ItemLike input, float xp, int cook, Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input),
                        output, xp, cook)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:cooked_" + output.asItem().getRegistryName().getPath());
    }

    public static void signRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" - ")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:sign_" + output.asItem().getRegistryName().getPath());
    }

    public static void boatRecipe(ItemLike output, ItemLike input1, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("# #")
                .pattern("###")
                .define('#', input1)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:boat_" + output.asItem().getRegistryName().getPath());
    }

    public static void bowRecipe(ItemLike output, ItemLike input1, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("#")
                .pattern("-")
                .define('#', input1)
                .define('-', Items.BOW)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:bow_" + output.asItem().getRegistryName().getPath());
    }

    public static void slabRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 6)
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void trapDoorRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void metalTrapDoorRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void pressurePlateRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void stairsRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void barsRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void storageRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:storage_" + output.asItem().getRegistryName().getPath());
    }

    public static void doorRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void fenceRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("#-#")
                .pattern("#-#")
                .define('#', input1)
                .define('-', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer);
    }

    public static void fenceGateRecipe(ItemLike output, ItemLike input1, ItemLike input2, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("-#-")
                .pattern("-#-")
                .define('-', input1)
                .define('#', input2)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer);
    }

    public static void buttonRecipe(ItemLike output, ItemLike input1, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 1)
                .requires(input1)
                .unlockedBy("has_" + input1.asItem().getRegistryName().getPath(), has(input1))
                .save(consumer, "tolkienmobs:button_" + output.asItem().getRegistryName().getPath());
    }

    public static void unstorageRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 9)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:unstorage_" + output.asItem().getRegistryName().getPath());
    }

    public static void dyeRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 2)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:dye_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankWoodStrippedRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 4)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:stripped_wood_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankStrippedRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 4)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:stripped_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankWoodRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 4)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer, "tolkienmobs:wood_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapeless(output, 4)
                .requires(input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void smallStorageRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 1)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void fullBarkRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("##")
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
    }

    public static void leafPileRecipe(ItemLike output, ItemLike input, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(output, 3)
                .pattern("##")
                .define('#', input)
                .unlockedBy("has_" + input.asItem().getRegistryName().getPath(), has(input))
                .save(consumer);
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
            if (isInput(input) && isIngredient(ingredient)) {
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
        return NAME + " - Recipes";
    }
}
