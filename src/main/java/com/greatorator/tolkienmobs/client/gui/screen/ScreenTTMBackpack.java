package com.greatorator.tolkienmobs.client.gui.screen;

//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.gui.container.ContainerTTMBackpack;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.client.gui.screen.inventory.ContainerScreen;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.ITextComponent;
//
//public class ScreenTTMBackpack extends ContainerScreen<ContainerTTMBackpack> {
//    private static final ResourceLocation BACKPACK_GUI = new ResourceLocation(TolkienMobs.MODID, "textures/gui/backpack/backpack.png");
//
//    public ScreenTTMBackpack(ContainerTTMBackpack screenContainer, PlayerInventory inventory, ITextComponent titleIn) {
//        super(screenContainer, inventory, titleIn);
//
//        this.leftPos = 0;
//        this.topPos = 0;
//        this.imageWidth = 256;
//        this.imageHeight = 207;
//    }
//
//    protected void render(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
//        this.renderBackground(matrixStack);
//        super.render(matrixStack, mouseX, mouseY, partialTicks);
//        this.renderTooltip(matrixStack, mouseX, mouseY);
//    }
//
//    @Override
//    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
//        this.font.draw(matrixStack, this.inventory.getDisplayName(), (float) this.inventoryLabelX,
//                (float) this.inventoryLabelY, 4210752);
//    }
//
//    @Override
//    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
//        RenderSystem.color4f(1f, 1f, 1f, 1f);
//        this.minecraft.textureManager.bind(BACKPACK_GUI);
//        int x = (this.width - this.getYSize()) / 2;
//        int y = (this.height - this.getYSize()) / 2;
//        this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
//    }
//
//
//}
