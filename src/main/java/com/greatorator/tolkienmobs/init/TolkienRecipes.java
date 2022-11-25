package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import com.greatorator.tolkienmobs.recipe.TrinketRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static com.greatorator.tolkienmobs.TolkienMobs.NAME;

public class TolkienRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static final RegistryObject<RecipeSerializer<FireplaceRecipe>> TMFIREPLACE_SERIALIZER = RECIPE_SERIALIZER.register("fireplace", () -> FireplaceRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<TrinketRecipe>> TRINKET_SERIALIZER = RECIPE_SERIALIZER.register("trinket", () -> TrinketRecipe.Serializer.INSTANCE);

    public String getName() {
        return NAME + " - Recipes";
    }
}
