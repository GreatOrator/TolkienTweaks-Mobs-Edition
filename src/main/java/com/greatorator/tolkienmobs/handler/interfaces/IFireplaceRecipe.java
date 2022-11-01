package com.greatorator.tolkienmobs.handler.interfaces;

import com.google.common.collect.Lists;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

public interface IFireplaceRecipe extends Recipe<IFireplaceInventory> {

    @Override
    default RecipeType<?> getType(){
        return TolkienMobs.FIREPLACE_RECIPE_TYPE;
    }

    @Override
    default ItemStack getToastSymbol() {
        return new ItemStack(TolkienBlocks.TTMFIREPLACE.get());
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default boolean matches(IFireplaceInventory inventory, Level world) {
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

    float getExperience();

    int getCookingTime();
}