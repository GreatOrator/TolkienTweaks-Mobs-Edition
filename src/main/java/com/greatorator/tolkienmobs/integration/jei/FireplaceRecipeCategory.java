package com.greatorator.tolkienmobs.integration.jei;


import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.recipe.FireplaceRecipe;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static mezz.jei.api.recipe.RecipeIngredientRole.*;

public class FireplaceRecipeCategory implements IRecipeCategory<FireplaceRecipe> {
    public final static ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/fireplace_gui.png");
    public static final RecipeType<FireplaceRecipe> RECIPE_TYPE = RecipeType.create(MODID, "fireplace", FireplaceRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    protected final IDrawableStatic staticFire;
    protected final IDrawableStatic staticSoup;
    private final IDrawableAnimated campFire;
    private final IDrawableAnimated soup;

    private final Component localizedName;
    private final int regularCookTime;
    private int xSize = 176;
    private int ySize = 85;

    public FireplaceRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(TEXTURE, 0, 0, xSize, ySize);
        localizedName = new TranslatableComponent(TolkienBlocks.TTMFIREPLACE.get().getDescriptionId());
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TolkienBlocks.TTMFIREPLACE.get()));
        this.staticFire = guiHelper.createDrawable(TEXTURE, 176, 0, 14, 17);
        this.staticSoup = guiHelper.createDrawable(TEXTURE, 176, 0, 14, 17);
        campFire = guiHelper.createAnimatedDrawable(staticFire, 300, IDrawableAnimated.StartDirection.BOTTOM, true);
        soup = guiHelper.createAnimatedDrawable(staticSoup, 300, IDrawableAnimated.StartDirection.LEFT, true);
        this.regularCookTime = 200;
    }

    @Override
    public RecipeType<FireplaceRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return localizedName;
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
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayout, FireplaceRecipe iFireplaceRecipe, IFocusGroup iIngredients) {
        IRecipeCategory.super.setRecipe(iRecipeLayout, iFireplaceRecipe, iIngredients);

        iRecipeLayout.addSlot(INPUT, 38, 15)
                .addIngredients(iFireplaceRecipe.getIngredients().get(0));
        iRecipeLayout.addSlot(INPUT, 60, 15)
                .addIngredients(iFireplaceRecipe.getIngredients().get(1));
        iRecipeLayout.addSlot(CATALYST, 117, 36)
                .addIngredients(iFireplaceRecipe.getIngredients().get(0));

        iRecipeLayout.addSlot(OUTPUT, 116, 35)
                .addItemStack(iFireplaceRecipe.getResultItem());
    }

    @Override
    public void draw(FireplaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);

        campFire.draw(stack, 52, 36);
        soup.draw(stack, 80, 34);

        drawExperience(recipe, stack, 12);
        drawCookTime(recipe, stack, 45);
    }

    protected void drawExperience(FireplaceRecipe recipe, PoseStack poseStack, int y) {
        float experience = recipe.getExperience();
        if (experience > 0) {
            TranslatableComponent experienceString = new TranslatableComponent("gui.jei.category.smelting.experience", experience);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(experienceString);
            fontRenderer.draw(poseStack, experienceString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    protected void drawCookTime(FireplaceRecipe recipe, PoseStack poseStack, int y) {
        int cookTime = recipe.getCookingTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            TranslatableComponent timeString = new TranslatableComponent("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            int stringWidth = fontRenderer.width(timeString);
            fontRenderer.draw(poseStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }

    @Override
    public List<Component> getTooltipStrings(FireplaceRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public boolean handleInput(FireplaceRecipe recipe, double mouseX, double mouseY, InputConstants.Key input) {
        return IRecipeCategory.super.handleInput(recipe, mouseX, mouseY, input);
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return RecipeCategory.FIREPLACE_CRAFTING;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends FireplaceRecipe> getRecipeClass() {
        return FireplaceRecipe.class;
    }
}
