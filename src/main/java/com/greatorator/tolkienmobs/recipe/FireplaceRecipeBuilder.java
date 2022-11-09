package com.greatorator.tolkienmobs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.init.TolkienRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
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
import java.util.Objects;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkState;

/**
 * Created by brandon3055 on 09/12/2021
 */
public class FireplaceRecipeBuilder {
    private final ItemStack result;
    private float experience;
    private int cookingTime;
    @Nullable
    private String group;
    private List<Ingredient> ingredients = new ArrayList<>();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    public FireplaceRecipeBuilder(ItemStack result, float experience, int cookingTime) {
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public FireplaceRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public FireplaceRecipeBuilder group(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public static FireplaceRecipeBuilder fireplaceRecipe(ItemLike resultIn, float experience, int cookingTime) {
        return fireplaceRecipe(resultIn, experience, cookingTime, 1);
    }

    public static FireplaceRecipeBuilder fireplaceRecipe(ItemLike resultIn, float experience, int cookingTime, int countIn) {
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

    public FireplaceRecipeBuilder ingredient(ItemLike... ingredient) {
        return ingredient(Ingredient.of(ingredient));
    }

    public FireplaceRecipeBuilder ingredient(ITag<Item> ingredient) {
        return ingredient(Ingredient.of((ItemLike) ingredient));
    }

    public void save(Consumer<FinishedRecipe> consumer) {
        this.save(consumer, result.getItem().getRegistryName());
    }

    public void save(Consumer<FinishedRecipe> consumer, String save) {
        ResourceLocation resultLoc = result.getItem().getRegistryName();
        checkState(!new ResourceLocation(save).equals(resultLoc), "Fireplace recipe " + save + " should remove its 'save' argument", save);
        this.save(consumer, new ResourceLocation(save));
    }

    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe",
                        new RecipeUnlockedTrigger.TriggerInstance(EntityPredicate.Composite.ANY, id))
                .rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, ingredients, result, experience, cookingTime, group == null ? "" : group, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + Objects.requireNonNull(result.getItem().getItemCategory()).getRecipeFolderName() + "/" + id.getPath())));
    }

    private void ensureValid(ResourceLocation id) {
        checkState(!ingredients.isEmpty(), "No ingredients are defined for fireplace recipe " + id + "!", id);
        checkState(!advancementBuilder.getCriteria().isEmpty(), "No way of obtaining recipe " + id, id);
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
        private final String group;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, List<Ingredient> ingredients, ItemStack result, float experience, int cookingTime, String groupIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = id;
            this.ingredients = ingredients;
            this.result = result;
            this.experience = experience;
            this.cookingTime = cookingTime;
            this.group = groupIn;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        @Override
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

        @Override
        public RecipeSerializer<?> getType() {
            return TolkienRecipes.TMFIREPLACE_SERIALIZER.get();
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
