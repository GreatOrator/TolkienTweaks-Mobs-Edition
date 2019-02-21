package com.greatorator.tolkienmobs.crafting.recipe;

import com.google.gson.JsonObject;
import com.greatorator.tolkienmobs.handler.TTMRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class TrinketUpgradeRecipe extends ShapelessOreRecipe {

    public TrinketUpgradeRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
        super(group, input, result);
    }

    public static class Factory implements IRecipeFactory {

        @Override
        public IRecipe parse(final JsonContext context, final JsonObject json) {
            final String group = JsonUtils.getString(json, "group", "");
            final NonNullList<Ingredient> ingredients = TTMRecipe.parseShapeless(context, json);
            final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

            return new TrinketUpgradeRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
        }
    }
}
