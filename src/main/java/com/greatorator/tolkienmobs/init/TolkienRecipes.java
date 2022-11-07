package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipeType;
import com.greatorator.tolkienmobs.recipe.TrinketRecipe;
import com.greatorator.tolkienmobs.recipe.TrinketRecipeType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, MODID);

    // Recipe Types
    public static final RegistryObject<RecipeType<FireplaceRecipe>> FIREPLACE_RECIPE_TYPE = RECIPE_TYPES.register("fireplace", () -> new FireplaceRecipeType<>("fireplace"));
    public static final RegistryObject<RecipeType<TrinketRecipe>> TRINKET_RECIPE_TYPE = RECIPE_TYPES.register("trinket", () -> new TrinketRecipeType<>("trinket"));

    // Recipe Serializers
    public static final RegistryObject<FireplaceRecipe.Serializer> TMFIREPLACE_SERIALIZER = RECIPE_SERIALIZER.register("fireplace", FireplaceRecipe.Serializer::new);

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Recipes";
    }
}
