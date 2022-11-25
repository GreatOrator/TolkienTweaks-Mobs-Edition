package com.greatorator.tolkienmobs.event;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import com.greatorator.tolkienmobs.recipe.TrinketRecipe;
import com.greatorator.tolkienmobs.utils.BaseTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TolkienMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventTriggers {
    public static final BaseTrigger SLEEP_IN_BAG = CriteriaTriggers.register(new BaseTrigger(prefix("sleep_in_bag")));

    private static ResourceLocation prefix(String name) {
        return new ResourceLocation(TolkienMobs.MODID, name);
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, TrinketRecipe.Type.ID, TrinketRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, FireplaceRecipe.Type.ID, FireplaceRecipe.Type.INSTANCE);
    }
}
