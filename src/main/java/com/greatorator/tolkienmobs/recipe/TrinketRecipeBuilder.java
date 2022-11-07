package com.greatorator.tolkienmobs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.init.TolkienRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.tags.ITag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by brandon3055 on 09/12/2021
 */
public class TrinketRecipeBuilder {

    private final ItemStack result;
    private float experience;
    private int cookingTime;
    private List<Ingredient> ingredients = new ArrayList<>();

    public TrinketRecipeBuilder(ItemStack result, float experience, int cookingTime) {
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemLike resultIn, float experience, int cookingTime) {
        return trinketRecipe(resultIn, experience, cookingTime, 1);
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemLike resultIn, float experience, int cookingTime, int countIn) {
        return trinketRecipe(new ItemStack(resultIn, countIn), experience, cookingTime);
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemStack result, float experience, int cookingTime) {
        return new TrinketRecipeBuilder(result, experience, cookingTime);
    }

    public TrinketRecipeBuilder ingredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        if (ingredients.size() > 1) {
            throw new IllegalStateException("Attempted to add more than 1 ingredient to a Trinket Recipe!");
        }
        return this;
    }

    public TrinketRecipeBuilder ingredient(ItemStack... ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public TrinketRecipeBuilder ingredient(ItemLike... ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public TrinketRecipeBuilder ingredient(ITag<Item> ingredient) {
        return ingredient(Ingredient.of((ItemLike) ingredient));
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        build(consumer, String.valueOf(result.getItem().getRegistryName()));
    }

    public void build(Consumer<FinishedRecipe> consumer, String save) {
        ResourceLocation resourcelocation = result.getItem().getRegistryName();
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Trinket Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumer, String.valueOf(new ResourceLocation(save)));
        }
    }

    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        if (result.isEmpty()) return;
        validate(id);
        consumer.accept(new Result(id, ingredients, result, experience, cookingTime));
    }

    private void validate(ResourceLocation id) {
        if (ingredients.isEmpty()) {
            throw new IllegalStateException("No ingredients are defined for trinket recipe " + id + "!");
        }
    }

    public static JsonObject writeItemStack(ItemStack stack) {
        JsonObject json = new JsonObject();
        json.addProperty("item", stack.getItem().getRegistryName().toString());
        if (stack.getCount() != 1) {
            json.addProperty("count", stack.getCount());
        }

        if (stack.hasTag()) {
            json.addProperty("nbt", stack.getTag().toString());
        }
        return json;
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final List<Ingredient> ingredients;
        private final ItemStack result;
        private final float experience;
        private final int cookingTime;

        public Result(ResourceLocation id, List<Ingredient> ingredients, ItemStack result, float experience, int cookingTime) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result;
            this.experience = experience;
            this.cookingTime = cookingTime;

        }

        public void serializeRecipeData(JsonObject object) {
            object.add("result", writeItemStack(result));

            JsonArray ingredientArray = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                ingredientArray.add(ingredient.toJson());
            }

            object.add("ingredients", ingredientArray);
            object.addProperty("experience", this.experience);
            object.addProperty("cookingtime", this.cookingTime);
        }

        public RecipeSerializer<?> getType() {
            return TolkienRecipes.TMFIREPLACE_SERIALIZER.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
