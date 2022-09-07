package com.greatorator.tolkienmobs.client.gui.screen;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.gui.container.BackpackContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class BackpackScreen extends ContainerScreen<BackpackContainer> {
    private static final ResourceLocation BACKPACK_GUI = new ResourceLocation(TolkienMobs.MODID, "textures/gui/backpack/backpack.png");
    private static final ResourceLocation BACKPACK_SPRITES = new ResourceLocation(TolkienMobs.MODID, "textures/gui/backpack/backpack_sprites.png");

    public BackpackScreen(BackpackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageHeight = 207;
        this.imageWidth = 256;
    }

    @Override
    protected void renderBg(@Nonnull MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f ,1.0f);
        this.getMinecraft().textureManager.bind(BACKPACK_GUI);
        int i = this.leftPos;
        int j = this.topPos;

        this.blit(matrixStack, i, j, 0,0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(@Nonnull MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack, this.title.getString(), 90,4,0x404040);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int p_render_1_, int p_render_2_, float p_render_3_) {
        this.renderBackground(matrixStack);
        super.render(matrixStack,p_render_1_, p_render_2_, p_render_3_);
        this.renderTooltip(matrixStack, p_render_1_, p_render_2_);
    }
}