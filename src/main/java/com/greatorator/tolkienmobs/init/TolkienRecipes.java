package com.greatorator.tolkienmobs.init;

import com.greatorator.tolkienmobs.handler.FireplaceRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class TolkienRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static final RegistryObject<FireplaceRecipe.Serializer> TMFIREPLACE_SERIALIZER = RECIPE_SERIALIZER.register("tmfireplace", FireplaceRecipe.Serializer::new);

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition Recipes";
    }
}
