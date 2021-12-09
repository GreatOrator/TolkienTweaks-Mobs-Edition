package com.greatorator.tolkienmobs.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
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
    public ItemStack assemble(IFireplaceInventory inv) {
        return getResultItem();
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
    public IRecipeSerializer<?> getSerializer() {
        return TTMContent.TMFIREPLACE_SERIALIZER.get();
    }

    @Override
    public float getExperience() {
        return experience;
    }

    @Override
    public int getCookingTime() {
        return cookingTime;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>implements IRecipeSerializer<FireplaceRecipe> {
        @Override
        public FireplaceRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack result = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);

            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            float experience = JSONUtils.getAsFloat(json, "experience", 0.0F);
            int cookingtime = JSONUtils.getAsInt(json, "cookingtime", 300);

            return new FireplaceRecipe(recipeId, result, inputs, experience, cookingtime);
        }

        @Nullable
        @Override
        public FireplaceRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
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
        public void toNetwork(PacketBuffer buffer, FireplaceRecipe recipe) {
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
