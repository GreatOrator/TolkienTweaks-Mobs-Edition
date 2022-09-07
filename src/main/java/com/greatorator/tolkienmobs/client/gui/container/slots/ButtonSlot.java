package com.greatorator.tolkienmobs.client.gui.container.slots;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class ButtonSlot extends ImageButton {
    private static final ResourceLocation toggleTexture = new ResourceLocation(TolkienMobs.MODID, "textures/gui/backpack_sprites.png");
    private boolean state;

    ButtonSlot(int x, int y, boolean state, IPressable onPress) {
        super(x, y, 8, 8, 0, 128, 0, toggleTexture, onPress);
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void toggleState() {
        this.state = !this.state;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bind(toggleTexture);

        RenderSystem.disableDepthTest();

        int texStart = getState() ? 0 : 8;

        blit(matrixStack, this.x, this.y, (float)texStart, (float)128, this.width, this.height, 256, 256);

        RenderSystem.enableDepthTest();
    }

}