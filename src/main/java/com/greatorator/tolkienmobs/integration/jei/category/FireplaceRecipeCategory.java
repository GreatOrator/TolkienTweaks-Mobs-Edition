package com.greatorator.tolkienmobs.integration.jei.category;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.greatorator.tolkienmobs.init.TolkienBlocks;
import com.greatorator.tolkienmobs.integration.jei.RecipeCategoryRegister;
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
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;
import static mezz.jei.api.recipe.RecipeIngredientRole.CATALYST;
import static mezz.jei.api.recipe.RecipeIngredientRole.INPUT;

public class FireplaceRecipeCategory implements IRecipeCategory<FireplaceRecipe> {
    public final static ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/fireplace/fireplace_gui.png");
    public final static ResourceLocation FLAME = new ResourceLocation(MODID, "textures/gui/fireplace/fire_full.png");
    public final static ResourceLocation PROGRESS = new ResourceLocation(MODID, "textures/gui/fireplace/craft_full.png");
    public static final RecipeType<FireplaceRecipe> RECIPE_TYPE = RecipeType.create(MODID, "fireplace", FireplaceRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    protected final IDrawableStatic staticFlame;
    private final IDrawableAnimated animatedFlame;
    private final LoadingCache<Integer, IDrawableAnimated> progress;

    private final Component localizedName;
    private int xSize = 176;
    private int ySize = 85;

    public FireplaceRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(TEXTURE, 0, 0, xSize, ySize);
        localizedName = new TranslatableComponent(TolkienBlocks.TTMFIREPLACE.get().getDescriptionId());
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TolkienBlocks.TTMFIREPLACE.get()));
        this.staticFlame = guiHelper.createDrawable(FLAME, 0, 0, 18, 18);
        this.animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 200, IDrawableAnimated.StartDirection.TOP, true);
        this.progress = CacheBuilder.newBuilder().maximumSize(18).build(new CacheLoader<>() {
            @Override
            public IDrawableAnimated load(Integer cookTime) {
                return guiHelper.drawableBuilder(PROGRESS, 0, 0, 18, 18).buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
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
    public void setRecipe(IRecipeLayoutBuilder builder, FireplaceRecipe recipe, IFocusGroup focusGroup) {
        IRecipeCategory.super.setRecipe(builder, recipe, focusGroup);

        builder.addSlot(INPUT, 39, 16).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(INPUT, 61, 16).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(CATALYST, 50, 56).addIngredients(Ingredient.of(Items.COAL));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 117, 36).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(FireplaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);

        animatedFlame.draw(stack, 52, 36);
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(stack, 80, 34);

        drawExperience(recipe, stack, 12);
        drawCookTime(recipe, stack, 45);
    }

    protected IDrawableAnimated getArrow(FireplaceRecipe recipe) {
        int cookTime = recipe.getCookingTime();
        if (cookTime <= 0) {
            cookTime = 200;
        }
        return this.progress.getUnchecked(cookTime);
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
        return RecipeCategoryRegister.FIREPLACE_CRAFTING;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends FireplaceRecipe> getRecipeClass() {
        return FireplaceRecipe.class;
    }
}
