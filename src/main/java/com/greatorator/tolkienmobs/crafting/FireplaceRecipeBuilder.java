package com.greatorator.tolkienmobs.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by brandon3055 on 09/12/2021
 */
public class FireplaceRecipeBuilder {

    private final ItemStack result;
    private float experience;
    private int cookingTime;
    private List<Ingredient> ingredients = new ArrayList<>();

    public FireplaceRecipeBuilder(ItemStack result, float experience, int cookingTime) {
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public static FireplaceRecipeBuilder fireplaceRecipe(IItemProvider resultIn, float experience, int cookingTime) {
        return fireplaceRecipe(resultIn, experience, cookingTime, 1);
    }

    public static FireplaceRecipeBuilder fireplaceRecipe(IItemProvider resultIn, float experience, int cookingTime, int countIn) {
        return fireplaceRecipe(new ItemStack(resultIn, countIn), experience, cookingTime);
    }

    public static FireplaceRecipeBuilder fireplaceRecipe(ItemStack result, float experience, int cookingTime) {
        return new FireplaceRecipeBuilder(result, experience, cookingTime);
    }

    public FireplaceRecipeBuilder ingredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        if (ingredients.size() > 2) {
            throw new IllegalStateException("Attempted to add more than 2 ingredients to a FireplaceRecipe!");
        }
        return this;
    }

    public FireplaceRecipeBuilder ingredient(ItemStack... ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public FireplaceRecipeBuilder ingredient(IItemProvider... ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public FireplaceRecipeBuilder ingredient(ITag<Item> ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public void build(Consumer<IFinishedRecipe> consumer) {
        build(consumer, result.getItem().getRegistryName());
    }

    public void build(Consumer<IFinishedRecipe> consumer, String save) {
        ResourceLocation resourcelocation = result.getItem().getRegistryName();
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Fireplace Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumer, new ResourceLocation(save));
        }
    }

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        if (result.isEmpty()) return;
        validate(id);
        consumer.accept(new Result(id, ingredients, result, experience, cookingTime));
    }

    private void validate(ResourceLocation id) {
        if (ingredients.isEmpty()) {
            throw new IllegalStateException("No ingredients are defined for fireplace recipe " + id + "!");
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

    public static class Result implements IFinishedRecipe {
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

        public IRecipeSerializer<?> getType() {
            return TTMContent.TMFIREPLACE_SERIALIZER.get();
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
