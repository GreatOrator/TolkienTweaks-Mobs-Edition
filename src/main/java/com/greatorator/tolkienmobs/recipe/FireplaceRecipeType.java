package com.greatorator.tolkienmobs.recipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class FireplaceRecipeType <T extends Recipe<?>> implements RecipeType<T> {
    private final String identifier;

    public FireplaceRecipeType(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return MODID + ":" + identifier;
    }
}
