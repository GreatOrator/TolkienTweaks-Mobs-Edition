package com.greatorator.tolkienmobs.integration.jei;

import com.greatorator.tolkienmobs.TolkienMobs;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.util.ErrorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

@JeiPlugin
public class TTMJEI implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(MODID, "jei_plugin");
    public static IJeiHelpers jeiHelpers = null;

    @Nullable
    private FireplaceRecipeCategory fireplaceRecipeCategory;


    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        registration.addRecipeCategories(fireplaceRecipeCategory = new FireplaceRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ErrorUtil.checkNotNull(fireplaceRecipeCategory, "fireplaceRecipeCategory");
        jeiHelpers = registration.getJeiHelpers();

        ClientWorld world = Minecraft.getInstance().level;
        registration.addRecipes(world.getRecipeManager().getAllRecipesFor(TolkienMobs.FIREPLACE_RECIPE_TYPE), FireplaceRecipeCategory.UID);
    }
}
