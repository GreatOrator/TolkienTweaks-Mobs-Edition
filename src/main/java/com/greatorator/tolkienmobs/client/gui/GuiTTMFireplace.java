package com.greatorator.tolkienmobs.client.gui;

import codechicken.lib.math.MathHelper;
import com.brandon3055.brandonscore.api.render.GuiHelper;
import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TBasicMachine;
import com.brandon3055.brandonscore.inventory.ContainerBCTile;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.entity.tile.TTMFireplaceTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static com.brandon3055.brandonscore.client.gui.GuiToolkit.GuiLayout.DEFAULT;
import static com.brandon3055.brandonscore.client.gui.GuiToolkit.LayoutPos.TOP_LEFT;
import static com.brandon3055.brandonscore.inventory.ContainerSlotLayout.SlotType.TILE_INV;
import static com.greatorator.tolkienmobs.client.gui.GuiTTMFireplace.GuiProgressIcon.Rotation.*;

public class GuiTTMFireplace extends ModularGuiContainer<ContainerBCTile<TTMFireplaceTile>> {

    protected GuiToolkit<GuiTTMFireplace> toolkit = new GuiToolkit<>(this, DEFAULT).setTranslationPrefix("gui.tolkienmobs.fireplace");

    private TTMFireplaceTile tile;

    public GuiTTMFireplace(ContainerBCTile<TTMFireplaceTile> container, PlayerInventory inv, ITextComponent title) {
        super(container, inv, title);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TBasicMachine template = toolkit.loadTemplate(new TBasicMachine(this, tile));

        GuiElement<?> inputSlots = toolkit.createSlots(template.background, 2, 1, 4, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, x), null);
        toolkit.placeInside(inputSlots, template.background, TOP_LEFT, 39, 16);

        GuiElement<?> fuelSlot = toolkit.createSlots(template.background, 1, 1, 0, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, 2), BCSprites.get("slots/fuel"));
        toolkit.placeInside(fuelSlot, template.background, TOP_LEFT, 50, 56);

        GuiElement<?> outSlot = toolkit.createSlots(template.background, 1, 1, 0, (x, y) -> container.getSlotLayout().getSlotData(TILE_INV, 3), null);
        toolkit.placeInside(outSlot, template.background, TOP_LEFT, 117, 36);

        GuiProgressIcon fireIcon = template.background.addChild(new GuiProgressIcon(TTMSprites.get("fireplace/fire_empty"), TTMSprites.get("fireplace/fire_full"), UP));//.setMargins(0, 1).setProgressSupplier(() -> (TimeKeeper.getClientTick() % 100) / 100D).setPos(20, 0).setSize(18, 18));
        fireIcon.setSize(18, 18);
        fireIcon.setMargins(0, 1);
        fireIcon.setProgressSupplier(() -> tile.fuelRemaining.get() / (double)Math.max(1, tile.fuelValue.get()));
        toolkit.placeInside(fireIcon, template.background, TOP_LEFT, 50, 36);

        GuiProgressIcon progressIcon = template.background.addChild(new GuiProgressIcon(TTMSprites.get("fireplace/craft_empty"), TTMSprites.get("fireplace/craft_full"), RIGHT));//.setMargins(0, 1).setProgressSupplier(() -> (TimeKeeper.getClientTick() % 100) / 100D).setPos(20, 0).setSize(18, 18));
        progressIcon.setSize(32, 32);
        progressIcon.setProgressSupplier(() -> tile.recipeProgress.get() / (double)Math.max(1, tile.recipeTime.get()));
        toolkit.placeInside(progressIcon, template.background, TOP_LEFT, 80, 34);
    }

    //TODO This will be in the next release of BrandonsCore at which point this can be removed and replaced with the one in BCore
    public static class GuiProgressIcon extends GuiElement<GuiProgressIcon> {
        private RenderMaterial baseTexture;
        private RenderMaterial overlayTexture;
        private Rotation rotation;
        private Supplier<Double> progressSupplier = () -> 0D;
        private int upperMargin = 0;
        private int lowerMargin = 0;

        public GuiProgressIcon(RenderMaterial baseTexture, RenderMaterial overlayTexture, Rotation animRotation) {
            super();
            this.baseTexture = baseTexture;
            this.overlayTexture = overlayTexture;
            this.rotation = animRotation;
        }

        public GuiProgressIcon setProgressSupplier(Supplier<Double> progressSupplier) {
            this.progressSupplier = progressSupplier;
            return this;
        }

        public GuiProgressIcon setProgress(double progress) {
            this.progressSupplier = () -> progress;
            return this;
        }

        /**
         * Use this to account for any empty (transparent) pixels at the lower or upper end of the icon sprite
         * @param lowerMargin The number of empty pixels on the lower side of the sprite
         * @param upperMargin The number of empty pixels on the upper side of the sprite
         */
        public GuiProgressIcon setMargins(int lowerMargin, int upperMargin) {
            this.upperMargin = upperMargin;
            this.lowerMargin = lowerMargin;
            return this;
        }

        public double getProgress() {
            return progressSupplier.get();
        }

        private double getRenderState() {
            double progress = getProgress();
            double axis = rotation == LEFT || rotation == RIGHT ? xSize() : ySize();
            double size = axis - upperMargin - lowerMargin;
            return MathHelper.clip((lowerMargin + Math.ceil((size * progress))) / axis, 0, 1);
        }

        @Override
        public void renderElement(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
            super.renderElement(minecraft, mouseX, mouseY, partialTicks);
            IRenderTypeBuffer.Impl getter = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            drawSprite(baseTexture.buffer(getter, BCSprites::makeType), xPos(), yPos(), xSize(), ySize(), baseTexture.sprite());
            rotation.draw(this, getter);
            getter.endBatch();
        }


        enum Rotation {
            /**
             * Progress bar animated from bottom to top
             * */
            UP((icon, getter) -> GuiHelper.drawPartialSprite(icon.overlayTexture.buffer(getter, BCSprites::makeType), icon.xPos(), icon.yPos() + (icon.ySize() * (1D - icon.getRenderState())), icon.xSize(), icon.ySize() * icon.getRenderState(), icon.overlayTexture.sprite(), 0, 1D - icon.getRenderState(), 1, 1)),
            /**
             * Progress bar animated from top to bottom
             * */
            DOWN((icon, getter) -> GuiHelper.drawPartialSprite(icon.overlayTexture.buffer(getter, BCSprites::makeType), icon.xPos(), icon.yPos(), icon.xSize(), icon.ySize() * icon.getRenderState(), icon.overlayTexture.sprite(), 0, 0, 1, icon.getRenderState())),
            /**
             * Progress bar animated from right to left
             * */
            LEFT((icon, getter) -> GuiHelper.drawPartialSprite(icon.overlayTexture.buffer(getter, BCSprites::makeType), icon.xPos() + (icon.xSize() * (1D - icon.getRenderState())), icon.yPos(), icon.xSize() * icon.getRenderState(), icon.ySize(), icon.overlayTexture.sprite(), 1D - icon.getRenderState(), 0, 1, 1)),
            /**
             * Progress bar animated from left to right
             * */
            RIGHT((icon, getter) -> GuiHelper.drawPartialSprite(icon.overlayTexture.buffer(getter, BCSprites::makeType), icon.xPos(), icon.yPos(), icon.xSize() * icon.getRenderState(), icon.ySize(), icon.overlayTexture.sprite(), 0, 0, icon.getRenderState(), 1));

            private BiConsumer<GuiProgressIcon, IRenderTypeBuffer.Impl> drawFunc;
            Rotation(BiConsumer<GuiProgressIcon, IRenderTypeBuffer.Impl> drawFunc) {
                this.drawFunc = drawFunc;
            }

            private void draw(GuiProgressIcon icon, IRenderTypeBuffer.Impl getter){
                drawFunc.accept(icon, getter);
            }
        }
    }
}