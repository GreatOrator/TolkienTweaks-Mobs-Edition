package com.greatorator.tolkienmobs.crafting.ingredient;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class TTMIngredientNever extends Ingredient {
    public static final TTMIngredientNever INSTANCE = new TTMIngredientNever();

    private TTMIngredientNever() {
        super(0);
    }

    @Override
    public boolean apply(@Nullable final ItemStack p_apply_1_) {
        return false;
    }

    public static class Factory implements IIngredientFactory {

        @Nonnull
        @Override
        public Ingredient parse(final JsonContext context, final JsonObject json) {
            return TTMIngredientNever.INSTANCE;
        }
    }
}