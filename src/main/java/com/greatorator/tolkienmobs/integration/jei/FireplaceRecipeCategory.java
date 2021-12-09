package com.greatorator.tolkienmobs.integration.jei;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.handler.interfaces.IFireplaceRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class FireplaceRecipeCategory implements IRecipeCategory<IFireplaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(MODID, "tmfireplace");
    public final static ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/tmfireplace_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableStatic campFire;

    public FireplaceRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(TTMContent.TTMFIREPLACE.get()));
        this.campFire = guiHelper.createDrawable(TEXTURE, 176, 0, 14, 17);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends IFireplaceRecipe> getRecipeClass() {
        return IFireplaceRecipe.class;
    }

    @Override
    public String getTitle() {
        return TTMContent.TTMFIREPLACE.get().getRegistryName().toString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(IFireplaceRecipe iFireplaceRecipe, IIngredients iIngredients) {
        List<Ingredient> recipeIngredients = new ArrayList<>(iFireplaceRecipe.getIngredients());

        iIngredients.setInputIngredients(recipeIngredients);
        iIngredients.setOutput(VanillaTypes.ITEM, iFireplaceRecipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IFireplaceRecipe iFireplaceRecipe, IIngredients iIngredients) {
        iRecipeLayout.getItemStacks().init(0, true, 39, 16);
        iRecipeLayout.getItemStacks().init(1, true, 61, 16);
        iRecipeLayout.getItemStacks().init(3, true, 117, 36);

        iRecipeLayout.getItemStacks().init(3, false, 50, 56);
        iRecipeLayout.getItemStacks().set(iIngredients);
    }

    @Override
    public void draw(IFireplaceRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
            this.campFire.draw(matrixStack, 80, 34);
    }
}
