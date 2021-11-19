package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.IItemProvider;
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

        torchRecipe(TTMContent.TORCH_MALLORN.get(), TTMContent.PLANKS_MALLORN.get(), consumer);
        torchRecipe(TTMContent.TORCH_MIRKWOOD.get(), TTMContent.PLANKS_MIRKWOOD.get(), consumer);
        torchRecipe(TTMContent.TORCH_CULUMALDA.get(), TTMContent.PLANKS_CULUMALDA.get(), consumer);
        torchRecipe(TTMContent.TORCH_LEBETHRON.get(), TTMContent.PLANKS_LEBETHRON.get(), consumer);

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
    }

    public static void potions() {
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.MIRUVOR.get(), PotionGenerator.ENT_DRAUGHT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.GEM_AMMOLITE.get(), PotionGenerator.BLESSING_OF_ERU.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.LEMBAS.get(), PotionGenerator.ELVISH_LIFE.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.GOLDEN_INSECT.get(), PotionGenerator.ELF_FLEETFOOT.get()));
        BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(Potions.AWKWARD, TTMContent.BLOCK_MITHRIL_ITEM.get(), PotionGenerator.PORTABLE_REPAIR.get()));
    }

    private static void magic(Consumer<IFinishedRecipe> consumer) {
    }

    // Helper Methods
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
