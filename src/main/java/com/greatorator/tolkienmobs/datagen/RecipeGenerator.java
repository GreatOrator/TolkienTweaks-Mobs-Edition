package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

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
        machines(consumer);
    }

    private static void components(Consumer<IFinishedRecipe> consumer) {

        // Cooking & Smelting Recipes
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(TTMContent.DUST_MITHRIL.get()),
                TTMContent.INGOT_MITHRIL.get(), 0.35F, 200)
                .addCriterion("has_dust_mithril", InventoryChangeTrigger.Instance.forItems(TTMContent.DUST_MITHRIL.get()))
                .build(consumer);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(TTMContent.DUST_MORGULIRON.get()),
                TTMContent.INGOT_MORGULIRON.get(), 0.35F, 200)
                .addCriterion("has_dust_morguliron", InventoryChangeTrigger.Instance.forItems(TTMContent.DUST_MORGULIRON.get()))
                .build(consumer);

        // Shaped Recipes
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

        ShapedRecipeBuilder.shapedRecipe(TTMContent.BLOCK_MITHRIL.get())
                .patternLine("BBB")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('B', TTMContent.INGOT_MITHRIL.get())
                .addCriterion("has_mithril_ingot", hasItem(TTMContent.INGOT_MITHRIL.get()))
                .build(consumer, "mithril_from_storage");

        ShapedRecipeBuilder.shapedRecipe(TTMContent.BLOCK_MORGULIRON.get())
                .patternLine("BBB")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('B', TTMContent.INGOT_MORGULIRON.get())
                .addCriterion("has_morguliron_ingot", hasItem(TTMContent.INGOT_MORGULIRON.get()))
                .build(consumer, "morguliron_from_storage");

        ShapedRecipeBuilder.shapedRecipe(TTMContent.INGOT_MITHRIL.get())
                .patternLine("BBB")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('B', TTMContent.NUGGET_MITHRIL.get())
                .addCriterion("has_mithril_ingot", hasItem(TTMContent.NUGGET_MITHRIL.get()))
                .build(consumer, "mithril_to_storage");

        ShapedRecipeBuilder.shapedRecipe(TTMContent.INGOT_MORGULIRON.get())
                .patternLine("BBB")
                .patternLine("BBB")
                .patternLine("BBB")
                .key('B', TTMContent.NUGGET_MORGULIRON.get())
                .addCriterion("has_morguliron_ingot", hasItem(TTMContent.NUGGET_MORGULIRON.get()))
                .build(consumer, "morguliron_to_storage");

        // Shapeless Recipes
//        ShapelessRecipeBuilder.shapelessRecipe(TTMContent.INGOT_MITHRIL.get(), 9)
//                .addIngredient(TTMContent.BLOCK_MITHRIL.get())
//                .addCriterion("has_mithril_block", hasItem(TTMContent.BLOCK_MITHRIL.get()))
//                .build(consumer);
//
//        ShapelessRecipeBuilder.shapelessRecipe(TTMContent.INGOT_MORGULIRON.get(), 9)
//                .addIngredient(TTMContent.BLOCK_MORGULIRON.get())
//                .addCriterion("has_morguliron_block", hasItem(TTMContent.BLOCK_MORGULIRON.get()))
//                .build(consumer);
//
    }



    private static void machines(Consumer<IFinishedRecipe> consumer) {
    }


    private static void compress3x3(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ResourceLocation name = output.asItem().getRegistryName();
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, new ResourceLocation(name.getNamespace(), "compress/" + name.getPath()));
    }

    private static void compress3x3(IItemProvider output, ITag<Item> input, String inputName, Consumer<IFinishedRecipe> consumer) {
        ResourceLocation name = output.asItem().getRegistryName();
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + inputName, hasItem(input))
                .build(consumer, new ResourceLocation(name.getNamespace(), "compress/" + name.getPath()));
    }

    private static void compress2x2(IItemProvider output, IItemProvider input, Consumer<IFinishedRecipe> consumer) {
        ResourceLocation name = output.asItem().getRegistryName();
        ShapedRecipeBuilder.shapedRecipe(output)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', input)
                .addCriterion("has_" + input.asItem().getRegistryName().getPath(), hasItem(input))
                .build(consumer, new ResourceLocation(name.getNamespace(), "compress/" + name.getPath()));
    }

    private static void deCompress(IItemProvider output, int count, IItemProvider from, Consumer<IFinishedRecipe> consumer) {
        ResourceLocation name = output.asItem().getRegistryName();
        ShapelessRecipeBuilder.shapelessRecipe(output, count)
                .addIngredient(from)
                .addCriterion("has_" + from.asItem().getRegistryName().getPath(), hasItem(from))
                .build(consumer, new ResourceLocation(name.getNamespace(), "decompress/" + name.getPath()));
    }

    private static void deCompress(IItemProvider output, int count, ITag<Item> from, String hasName, Consumer<IFinishedRecipe> consumer) {
        ResourceLocation name = output.asItem().getRegistryName();
        ShapelessRecipeBuilder.shapelessRecipe(output, count)
                .addIngredient(from)
                .addCriterion("has_" + hasName, hasItem(from))
                .build(consumer, new ResourceLocation(name.getNamespace(), "decompress/" + name.getPath()));
    }

    private static void deCompress(IItemProvider output, IItemProvider from, Consumer<IFinishedRecipe> consumer) {
        deCompress(output, 9, from, consumer);
    }

    private static void deCompress(IItemProvider output, ITag<Item> from, String hasName, Consumer<IFinishedRecipe> consumer) {
        deCompress(output, 9, from, hasName, consumer);
    }

    public static String folder(String folder, IForgeRegistryEntry<?> key) {
        return TolkienMobs.MODID + ":" + folder + "/" + key.getRegistryName().getPath();
    }

    public static String folder(String folder, String name) {
        return TolkienMobs.MODID + ":" + folder + "/" + name;
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
}
