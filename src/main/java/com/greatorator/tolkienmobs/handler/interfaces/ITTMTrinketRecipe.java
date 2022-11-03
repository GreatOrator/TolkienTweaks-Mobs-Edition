package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public interface ITTMTrinketRecipe extends Recipe<Container> {

    ResourceLocation TYPE_ID = new ResourceLocation(MODID, "trinket");

    @Override
    default RecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }
}