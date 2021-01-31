package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

//        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(DUSTS_DRACONIUM), ingot_draconium, 0, 200).addCriterion("has_draconium_dust", hasItem(DUSTS_DRACONIUM)).build(consumer, folder("components", ingot_draconium));
//        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(ORES_DRACONIUM), ingot_draconium, 1, 200).addCriterion("has_draconium_ore", hasItem(ORES_DRACONIUM)).build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(core_draconium)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', INGOTS_DRACONIUM)
//                .key('B', INGOTS_GOLD)
//                .key('C', GEMS_DIAMOND)
//                .addCriterion("has_draconium", hasItem(ingot_draconium))
//                .build(consumer, folder("components", core_draconium));
//
//        ShapedRecipeBuilder.shapedRecipe(core_wyvern)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', INGOTS_DRACONIUM)
//                .key('B', core_draconium)
//                .key('C', NETHER_STARS)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer, folder("components", core_wyvern));
//
//        ShapedRecipeBuilder.shapedRecipe(energy_core_wyvern)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', INGOTS_DRACONIUM)
//                .key('B', Tags.Items.STORAGE_BLOCKS_REDSTONE)
//                .key('C', core_draconium)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer, folder("components", energy_core_wyvern));
//
//        ShapedRecipeBuilder.shapedRecipe(energy_core_draconic)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', INGOTS_DRACONIUM_AWAKENED)
//                .key('B', energy_core_wyvern)
//                .key('C', core_wyvern)
//                .addCriterion("has_core_wyvern", hasItem(core_wyvern))
//                .build(consumer, folder("components", energy_core_draconic));
//
//        ShapedRecipeBuilder.shapedRecipe(energy_core_chaotic)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', chaos_frag_medium)
//                .key('B', energy_core_draconic)
//                .key('C', core_awakened)
//                .addCriterion("has_core_awakened", hasItem(core_awakened))
//                .build(consumer, folder("components", energy_core_chaotic));
    }



    private static void machines(Consumer<IFinishedRecipe> consumer) {
//        ShapedRecipeBuilder.shapedRecipe(crafting_core)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', STORAGE_BLOCKS_LAPIS)
//                .key('B', GEMS_DIAMOND)
//                .key('C', core_draconium)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(crafting_injector_basic)
//                .patternLine("ABA")
//                .patternLine("CDC")
//                .patternLine("CCC")
//                .key('A', GEMS_DIAMOND)
//                .key('B', core_draconium)
//                .key('C', Tags.Items.STONE)
//                .key('D', STORAGE_BLOCKS_IRON)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//
//        ShapedRecipeBuilder.shapedRecipe(DEContent.generator)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ADA")
//                .key('A', INGOTS_NETHER_BRICK)
//                .key('B', INGOTS_IRON)
//                .key('C', FURNACE)
//                .key('D', core_draconium)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(grinder)
//                .patternLine("ABA")
//                .patternLine("CDC")
//                .patternLine("AEA")
//                .key('A', INGOTS_IRON)
//                .key('B', INGOTS_DRACONIUM)
//                .key('C', DIAMOND_SWORD)
//                .key('D', core_draconium)
//                .key('E', FURNACE)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(energy_transfuser)
//                .patternLine("ABA")
//                .patternLine("CDC")
//                .patternLine("ACA")
//                .key('A', INGOTS_DRACONIUM)
//                .key('B', energy_core_stabilizer)
//                .key('C', core_draconium)
//                .key('D', ENCHANTING_TABLE)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(particle_generator)
//                .patternLine("ABA")
//                .patternLine("BCB")
//                .patternLine("ABA")
//                .key('A', STORAGE_BLOCKS_REDSTONE)
//                .key('B', BLAZE_ROD)
//                .key('C', core_draconium)
//                .addCriterion("has_core_draconium", hasItem(core_draconium))
//                .build(consumer);
//
//        ShapedRecipeBuilder.shapedRecipe(potentiometer)
//                .patternLine(" A ")
//                .patternLine("BCB")
//                .patternLine("DDD")
//                .key('A', PLANKS)
//                .key('B', DUSTS_REDSTONE)
//                .key('C', DUSTS_DRACONIUM)
//                .key('D', STONE_SLAB)
//                .addCriterion("has_STONE_SLAB", hasItem(STONE_SLAB))
//                .build(consumer);
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
