package com.greatorator.tolkienmobs.client.gui;

import codechicken.lib.math.MathHelper;
import com.brandon3055.brandonscore.BCConfig;
import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiScreen;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiBorderedRect;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiLabel;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTextField;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.handler.interfaces.IKeyAccessTile;
import com.greatorator.tolkienmobs.network.TolkienNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.LinkedList;

public class BronzeKeyAccessScreen extends ModularGuiScreen implements IGuiEventListener {
    protected GuiToolkit<BronzeKeyAccessScreen> toolkit = new GuiToolkit<>(this, 200, 78).setTranslationPrefix("screen.tolkienmobs.bronze_key");
    private LinkedList<TargetElement> targetElements = new LinkedList();
    private GuiButton consumeKey;
    private GuiButton toggleMode;
    private GuiButton permanent;
    private GuiButton reset;

    private GuiTextField keyCodeField;
    private GuiTextField delayField;

    private final PlayerEntity player;
    private boolean keymode;
    private IKeyAccessTile lockable;
    private int index;
    private int selectedIndex = 0;
    private boolean draggingTarget = false;
    private int lastAdded = -1;
    private boolean editAdded = false;

    public BronzeKeyAccessScreen(PlayerEntity playerIn, ITextComponent title, IKeyAccessTile lockable) {
        super(title);
        this.player = playerIn;
        this.lockable = lockable;
        keymode = lockable == null;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase temp = new TGuiBase(this);
        temp.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        temp.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(temp);
        int bgPad = 5;

        if (keymode) {
            GuiBorderedRect codeBG = new GuiBorderedRect()
                    .set3DGetters(GuiToolkit.Palette.Slot::fill, GuiToolkit.Palette.Slot::accentDark, GuiToolkit.Palette.Slot::accentLight)
                    .setBorderColourL(GuiToolkit.Palette.Slot::border3D)
                    .setSize(150, 12)
                    .setXPos(temp.background.xPos() + bgPad + 1)
                    .setMaxYPos(temp.background.maxYPos() - bgPad - 33, false)
                    .setHoverText(e -> new String[]{
                            TextFormatting.DARK_AQUA + toolkit.i18n("instructions")
                    });
            temp.background.addChild(codeBG);
            GuiLabel keyTitle = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("keycode"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() - 21)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
            temp.background.addChild(codeBG);
            GuiLabel keySaved = codeBG.addChild(new GuiLabel().setAlignment(GuiAlign.LEFT).setShadowStateSupplier(() -> BCConfig.darkMode))
                    .setDisplaySupplier(() -> toolkit.i18n("saved"))
                    .setPos(codeBG.xPos() + 5, codeBG.maxYPos() + 2)
                    .setYSize(8)
                    .setTextColGetter(GuiToolkit.Palette.Slot::text)
                    .setMaxXPos(codeBG.maxXPos() - 1, true);
        }else {

        }
    }

    private class TargetElement extends GuiElement<TargetElement> {
        private int index;
        private GuiTextField keyTitle;
        private GuiButton lock;
        private GuiButton delete;
        private int clickTime = 99;
        private boolean mousePressed = false;
        private boolean dragging = false;

        public TargetElement(int index) {
            this.index = index;
            setYSize(12);
            setHoverText(e -> new String[]{
                    TextFormatting.AQUA + getName(),
                    TextFormatting.GRAY + toolkit.i18n("right_click_tp"),
                    TextFormatting.GRAY + toolkit.i18n("double_click_name"),
                    TextFormatting.GRAY + toolkit.i18n("must_unlock"),
                    TextFormatting.GRAY + toolkit.i18n("drag_to_move")
            });
        }

        private boolean hasTarget() {
            return false;
        }

        private String getName() {
            return "[error]";
        }

        private void setName(String name) {
            TolkienNetwork.sendKeyAccessMessage(2, e -> e.writeVarInt(index).writeString(name));
        }

        @Override
        public void addChildElements() {
            keyTitle = toolkit.createTextField(this, false)
                    .setChangeListener(this::setName)
                    .setPos(xPos() + 1, yPos() + 2)
                    .setYSize(8)
                    .setXSize(145)
                    .setCanFocus(false)
                    .setReturnListener(() -> keyTitle.setFocused(false))
                    .setEnabledCallback(this::hasTarget);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            boolean ret = false;
            if (!hasTarget() || button == 2) return false;
            boolean mouseOver = keyTitle.isMouseOver(mouseX, mouseY);

            ret = ret || super.mouseClicked(mouseX, mouseY, button);
            mousePressed = mouseOver;

            //Select
            if (!ret && mouseOver && index != selectedIndex) {
                TolkienNetwork.sendKeyAccessMessage(3, e -> e.writeVarInt(index));
                return true;
            }
            return ret;
        }

        @Override
        public boolean mouseDragged(double mouseX, double mouseY, int clickedMouseButton, double dragX, double dragY) {
            if (mousePressed && !isMouseOver(mouseX, mouseY)) {
                dragging = draggingTarget = true;
            }
            return super.mouseDragged(mouseX, mouseY, clickedMouseButton, dragX, dragY);
        }

        @Override
        public boolean mouseReleased(double mouseX, double mouseY, int button) {
            if (dragging) {
                TargetElement hovered = getHovered((int)mouseX, (int)mouseY);
                if (hovered != null) {
                    int moveIndex = mouseY > hovered.yPos() + (hovered.ySize() / 2D) ? hovered.index + 1 : hovered.index;
                    TolkienNetwork.sendKeyAccessMessage(4, e -> e.writeVarInt(moveIndex));
                }
            }

            dragging = mousePressed = draggingTarget = false;
            return super.mouseReleased(mouseX, mouseY, button);
        }

        @Override
        public void renderElement(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
            boolean selected = false;
            boolean hovered = false;

            RenderMaterial mat = TTMSprites.get("keys/slot");
            RenderMaterial matSelect = TTMSprites.get("keys/slot_selected");
            IRenderTypeBuffer.Impl getter = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            drawSprite(getter.getBuffer(BCSprites.GUI_TYPE), xPos(), yPos(), (selected ? matSelect : mat).sprite());
            if ((hovered && !selected)) {
                getter.endBatch();
                drawSprite(getter.getBuffer(BCSprites.GUI_TYPE), xPos(), yPos(), matSelect.sprite(), 0x30FFFFFF);
            }
            getter.endBatch();
            super.renderElement(minecraft, mouseX, mouseY, partialTicks);

            if (lock.isMouseOver(mouseX, mouseY) || (delete.isEnabled() && delete.isMouseOver(mouseX, mouseY))) {
                hoverTime = 0;
            }
        }

        @Override
        public boolean renderOverlayLayer(Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
            if (dragging) {
                int yPos = MathHelper.clip(mouseY - 6, getParent().yPos(), getParent().maxYPos() - 12);
                IRenderTypeBuffer.Impl getter = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());

                TargetElement hovered = getHovered(mouseX, mouseY);
                if (hovered != null) {
                    int y = mouseY > hovered.yPos() + (hovered.ySize() / 2) ? hovered.maxYPos() : hovered.yPos() - 1;
                    drawColouredRect(getter, xPos(), y, xSize(), 3, 0x6000FF00);
                    getter.endBatch();
                }

                drawSprite(getter.getBuffer(BCSprites.GUI_TYPE), xPos(), yPos, TTMSprites.get("keys/slot_selected").sprite(), 0x60FFFFFF);
                getter.endBatch();


                return true;
            } else if (draggingTarget) {
                return false;
            }

            return super.renderOverlayLayer(minecraft, mouseX, mouseY, partialTicks);
        }

        private TargetElement getHovered(int mouseX, int mouseY) {
            for (TargetElement element : targetElements) {
                if (element != this && element.isMouseOver(mouseX, mouseY) || element.isMouseOver(mouseX, mouseY + 1) || element.isMouseOver(mouseX, mouseY - 1)) {
                    return element;
                }
            }
            return null;
        }

        @Override
        public boolean onUpdate() {
            if (lastAdded == index && editAdded) {
                lastAdded = -1;
                editAdded = false;
                keyTitle.setFocused(true);
                keyTitle.setCursorPositionEnd();
                keyTitle.setSelectionPos(0);
            }

            return super.onUpdate();
        }

        @Override
        public boolean isHoverTextEnabled() {
            return hasTarget();
        }
    }
}