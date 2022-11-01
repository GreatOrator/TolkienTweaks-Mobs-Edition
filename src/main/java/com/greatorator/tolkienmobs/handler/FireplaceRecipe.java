package com.greatorator.tolkienmobs.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceInventory;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceRecipe;
import com.greatorator.tolkienmobs.init.TolkienRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class FireplaceRecipe implements IFireplaceRecipe {
    private ResourceLocation id;
    private final ItemStack result;
    private NonNullList<Ingredient> ingredients;
    private float experience;
    private int cookingTime;

    public FireplaceRecipe(ResourceLocation id, ItemStack result, NonNullList<Ingredient> ingredients, float experience, int cookingTime) {
        this.id = id;
        this.result = result;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean matches(IFireplaceInventory p_44002_, Level p_44003_) {
        return false;
    }

    @Override
    public ItemStack assemble(IFireplaceInventory inv) {
        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TolkienRecipes.TMFIREPLACE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<FireplaceRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "fireplace_cooking";
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>>implements RecipeSerializer<FireplaceRecipe> {
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

            return new FireplaceRecipe(recipeId, result, inputs, experience, cookingtime);
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
            return new FireplaceRecipe(recipeId, output, inputs, experience, cookingtime);
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
