package com.greatorator.tolkienmobs.client.gui;

import com.greatorator.tolkienmobs.tileentity.container.ContainerTTMFireplace;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.greatorator.tolkienmobs.TolkienMobs.MODID;

public class GuiTTMFireplace extends ContainerScreen<ContainerTTMFireplace> {
    private final ResourceLocation GUI = new ResourceLocation(MODID, "textures/gui/tmfireplace_gui.png");

    public GuiTTMFireplace(ContainerTTMFireplace screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public void render(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f , 1f, 1f);
        this.minecraft.textureManager.bind(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // GUI Render
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        // Fire Icon
        this.blit(matrixStack, x + 52, y + 36, 176, 0, 14, this.menu.getFuel() + 1);
        // Progress Icon
        this.blit(matrixStack, x + 80, y + 34, 176, 17, this.menu.getProcess() + 1, 19);
    }
}