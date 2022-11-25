package com.greatorator.tolkienmobs.integration.jei;

import com.greatorator.tolkienmobs.integration.jei.category.FireplaceRecipeCategory;
import com.greatorator.tolkienmobs.integration.jei.category.TrinketRecipeCategory;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import com.greatorator.tolkienmobs.recipe.TrinketRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.util.ErrorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@JeiPlugin
public class JEIIntegration implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(MODID, "jei_plugin");
    public static IJeiHelpers jeiHelpers = null;
    public static IJeiRuntime jeiRuntime = null;

    @Nullable
    private FireplaceRecipeCategory fireplaceRecipeCategory;
    @Nullable
    private TrinketRecipeCategory trinketRecipeCategory;

    public JEIIntegration() {
    }


    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(fireplaceRecipeCategory = new FireplaceRecipeCategory(guiHelper));
        registration.addRecipeCategories(trinketRecipeCategory = new TrinketRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ErrorUtil.checkNotNull(fireplaceRecipeCategory, "fireplaceRecipeCategory");
        ErrorUtil.checkNotNull(trinketRecipeCategory, "trinketRecipeCategory");
        jeiHelpers = registration.getJeiHelpers();
        var recipeManager = Minecraft.getInstance().level.getRecipeManager();

        ClientLevel world = Minecraft.getInstance().level;
        registration.addRecipes(FireplaceRecipeCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(FireplaceRecipe.Type.INSTANCE));
        registration.addRecipes(TrinketRecipeCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(TrinketRecipe.Type.INSTANCE));
    }
}
