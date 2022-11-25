package com.greatorator.tolkienmobs.recipe;

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

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TrinketRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final ItemStack result;
    private final NonNullList<Ingredient> ingredient;
    private final int cookingTime;
    private float experience;

    public TrinketRecipe(ResourceLocation id, NonNullList<Ingredient> ingredient, float experience, int cookingTime, ItemStack result) {
        this.id = id;
        this.result = result;
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return ingredient.get(0).test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredient;
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
        return TolkienItems.TRINKET_TABLE_ITEM.get().getDefaultInstance();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    @Override
    public RecipeType<?> getType(){
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<TrinketRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "trinket";
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<TrinketRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(MODID,"trinket");

        @Override
        public TrinketRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int cookingtime = GsonHelper.getAsInt(json, "cookingtime", 300);

            return new TrinketRecipe(id, inputs, experience, cookingtime, result);
        }

        @Override
        public TrinketRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack result = buf.readItem();
            float experience = buf.readFloat();
            int cookingtime = buf.readVarInt();
            return new TrinketRecipe(id, inputs, experience, cookingtime, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, TrinketRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.cookingTime);
        }
    }
}