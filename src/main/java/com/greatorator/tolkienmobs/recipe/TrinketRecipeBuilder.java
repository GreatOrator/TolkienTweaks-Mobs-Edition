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
public class TrinketRecipeBuilder {

    private final ItemStack result;
    private float experience;
    private int cookingTime;
    @Nullable
    private String group;
    private List<Ingredient> ingredients = new ArrayList<>();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();

    public TrinketRecipeBuilder(ItemStack result, float experience, int cookingTime) {
        this.result = result;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    public TrinketRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public TrinketRecipeBuilder group(String groupIn) {
        this.group = groupIn;
        return this;
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemLike result, float experience, int cookingTime) {
        return trinketRecipe(result, experience, cookingTime, 1);
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemLike result, float experience, int cookingTime, int countIn) {
        return trinketRecipe(new ItemStack(result, countIn), experience, cookingTime);
    }

    public static TrinketRecipeBuilder trinketRecipe(ItemStack result, float experience, int cookingTime) {
        return new TrinketRecipeBuilder(result, experience, cookingTime);
    }

    public TrinketRecipeBuilder ingredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        if (ingredients.size() > 3) {
            throw new IllegalStateException("Attempted to add more than 3 ingredients to a Trinket Recipe!");
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
        this.build(consumer, String.valueOf(result.getItem().getRegistryName()));
    }

    public void build(Consumer<FinishedRecipe> consumer, String save) {
        ResourceLocation resourcelocation = result.getItem().getRegistryName();
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Trinket Recipe " + save + " should remove its 'save' argument");
        } else {
            this.build(consumer, new ResourceLocation(save));
        }
    }

    public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        this.ensureValid(id);
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe",
                        new RecipeUnlockedTrigger.TriggerInstance(EntityPredicate.Composite.ANY, id))
                .rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        consumer.accept(new Result(id, ingredients, result, experience, cookingTime, group == null ? "" : group, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + Objects.requireNonNull(result.getItem().getItemCategory()).getRecipeFolderName() + "/" + id.getPath())));
    }

    private void ensureValid(ResourceLocation id) {
        checkState(!ingredients.isEmpty(), "No ingredients are defined for trinket recipe " + id + "!", id);
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
            return TolkienRecipes.TRINKET_SERIALIZER.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
