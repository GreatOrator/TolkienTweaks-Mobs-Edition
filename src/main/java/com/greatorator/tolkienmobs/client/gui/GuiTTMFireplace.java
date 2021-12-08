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

    @Override
    public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(mStack);
        super.render(mStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(mStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
        this.minecraft.textureManager.bind(GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        double fuel = menu.getFuel();
        int flameY = (int)Math.ceil(fuel * 17);
        // GUI Render
        this.blit(mStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        // Fire Icon
        this.blit(mStack, x + 52, y + 36 + (17 - flameY), 176, 17 - flameY, 14, flameY);
        // Progress Icon
        this.blit(mStack, x + 80, y + 34, 176, 17, this.menu.getProcess(), 19);
    }
}