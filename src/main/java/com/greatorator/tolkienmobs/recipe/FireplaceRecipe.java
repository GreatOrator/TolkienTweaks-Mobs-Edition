package com.greatorator.tolkienmobs.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.init.TolkienItems;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class FireplaceRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final NonNullList<Ingredient> ingredient;
    private final int cookingTime;
    private final float experience;
    private final ItemStack result;

    public FireplaceRecipe(ResourceLocation id, NonNullList<Ingredient> ingredient, float experience, int cookingTime, ItemStack result) {
        this.id = id;
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.result = result;
    }

    @Override
    public boolean matches(Container inventory, @Nonnull Level level) {
        List<ItemStack> inputs = Lists.newArrayList(inventory.getItem(0), inventory.getItem(1));
        for (Ingredient ingredient : getIngredients()) {
            ItemStack match = inputs.stream()
                    .filter(ingredient)
                    .findFirst()
                    .orElse(null);

            if (match == null && !ingredient.isEmpty()) {
                return false;
            }
            inputs.remove(match);
        }
        return true;
    }

    @Nonnull
    @Override
    public ItemStack assemble(@Nonnull Container container) {
        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredient;
    }

    public int getCount() {
        return cookingTime;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getToastSymbol() {
        return TolkienItems.TTMFIREPLACE_ITEM.get().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FireplaceRecipe.Serializer.INSTANCE;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public RecipeType<?> getType(){
        return FireplaceRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<FireplaceRecipe> {
        private Type() { }
        public static final FireplaceRecipe.Type INSTANCE = new FireplaceRecipe.Type();
        public static final String ID = "fireplace";
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<FireplaceRecipe> {
        public static final FireplaceRecipe.Serializer INSTANCE = new FireplaceRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MODID,"fireplace");

        @Override
        public FireplaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int cookingtime = GsonHelper.getAsInt(json, "cookingtime", 300);

            return new FireplaceRecipe(recipeId, inputs, experience, cookingtime, result);
        }

        @Nullable
        @Override
        public FireplaceRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            float experience = buffer.readFloat();
            int cookingtime = buffer.readVarInt();
            return new FireplaceRecipe(recipeId, inputs, experience, cookingtime, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, FireplaceRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookingTime);
        }
    }
}
