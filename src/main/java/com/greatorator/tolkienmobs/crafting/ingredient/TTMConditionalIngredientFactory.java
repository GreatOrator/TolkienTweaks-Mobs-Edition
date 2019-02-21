package com.greatorator.tolkienmobs.crafting.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
public class TTMConditionalIngredientFactory implements IIngredientFactory {
    @Nonnull
    @Override
    public Ingredient parse(final JsonContext context, final JsonObject json) {
        if (CraftingHelper.processConditions(JsonUtils.getJsonArray(json, "conditions"), context)) {
            return CraftingHelper.getIngredient(json.get("ingredient"), context);
        }

        return TTMIngredientNever.INSTANCE;
    }
}