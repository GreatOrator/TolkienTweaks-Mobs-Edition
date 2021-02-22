package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.data.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

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
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        //Left in a couple of my builders so you can see how the recipe builders work
        components(consumer);
        specialty(consumer);
        magic(consumer);
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
        ShapedRecipeBuilder.shapedRecipe(TTMContent.GOLEM_STONE_SUMMON.get())
                .patternLine("MEM")
                .patternLine("ASF")
                .patternLine("OWO")
                .key('W', TTMContent.GOLEM_STONE_WATER.get())
                .key('A', TTMContent.GOLEM_STONE_AIR.get())
                .key('M', TTMContent.BLOCK_MITHRIL.get())
                .key('S', TTMContent.GOLEM_STONE.get())
                .key('F', TTMContent.GOLEM_STONE_FIRE.get())
                .key('E', TTMContent.GOLEM_STONE_EARTH.get())
                .key('O', Items.OBSIDIAN)
                .addCriterion("has_golem_stones", hasItem(TTMContent.GOLEM_STONE.get()))
                .build(consumer);
    }

    private static void magic(Consumer<IFinishedRecipe> consumer){
        //
    }

    public static void swordRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("#")
                .patternLine("#")
                .patternLine("-")
                .key('#', input)
                .key('-', Items.STICK)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void axeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("##")
                .patternLine("#-")
                .patternLine(" -")
                .key('#', input)
                .key('-', Items.STICK)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void shovelRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("#")
                .patternLine("-")
                .patternLine("-")
                .key('#', input)
                .key('-', Items.STICK)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void pickaxeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("###")
                .patternLine(" - ")
                .patternLine(" - ")
                .key('#', input)
                .key('-', Items.STICK)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void hoeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("##")
                .patternLine(" -")
                .patternLine(" -")
                .key('#', input)
                .key('-', Items.STICK)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:tools_" + output.asItem().getRegistryName().getPath());
    }

    public static void smeltingRecipe(IItemProvider output, IItemProvider input, float xp, int cook,Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(input),
                output, xp, cook)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:cooked_" + output.asItem().getRegistryName().getPath());
    }

    public static void slabRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 6)
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer);
    }

    public static void stairsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 4)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer);
    }

    public static void barsRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("###")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer);
    }

    public static void storageRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:storage_" + output.asItem().getRegistryName().getPath());
    }

    public static void doorRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 3)
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer);
    }

    public static void fenceRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 3)
                .patternLine("#-#")
                .patternLine("#-#")
                .key('#', input1)
                .key('-', input2)
                .addCriterion("has_" + input1.asItem().getRegistryName().getPath(), hasItem(input1))
                .build(consumer);
    }

    public static void fenceGateRecipe(IItemProvider output, IItemProvider input1, IItemProvider input2, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(output, 1)
                .patternLine("-#-")
                .patternLine("-#-")
                .key('-', input1)
                .key('#', input2)
                .addCriterion("has_" + input1.asItem().getRegistryName().getPath(), hasItem(input1))
                .build(consumer);
    }

    public static void unstorageRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapelessRecipe(output, 9)
                .addIngredient(input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:unstorage_" + output.asItem().getRegistryName().getPath());
    }

    public static void dyeRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapelessRecipe(output, 2)
                .addIngredient(input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, "tolkienmobs:dye_" + output.asItem().getRegistryName().getPath());
    }

    public static void plankRecipe(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder
                .shapelessRecipe(output, 4)
                .addIngredient(input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer);
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        super.act(cache);
    }

    public static class NBTIngredient extends net.minecraftforge.common.crafting.NBTIngredient {
        public NBTIngredient(ItemStack stack) {
            super(stack);
        }
    }

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Recipes";
    }
}
