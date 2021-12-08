package com.greatorator.tolkienmobs.handler.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public interface ITTMTrinketRecipe extends IRecipe<IInventory> {

    ResourceLocation TYPE_ID = new ResourceLocation(MODID, "trinket");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }
}