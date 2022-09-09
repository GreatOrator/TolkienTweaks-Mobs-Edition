package com.greatorator.tolkienmobs.client.gui.upgrade;

import com.brandon3055.brandonscore.client.BCSprites;
import com.brandon3055.brandonscore.client.gui.GuiToolkit;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElement;
import com.brandon3055.brandonscore.client.gui.modulargui.GuiElementManager;
import com.brandon3055.brandonscore.client.gui.modulargui.ModularGuiContainer;
import com.brandon3055.brandonscore.client.gui.modulargui.baseelements.GuiButton;
import com.brandon3055.brandonscore.client.gui.modulargui.guielements.GuiTexture;
import com.brandon3055.brandonscore.client.gui.modulargui.lib.GuiAlign;
import com.brandon3055.brandonscore.client.gui.modulargui.templates.TGuiBase;
import com.brandon3055.brandonscore.inventory.SlotMover;
import com.greatorator.tolkienmobs.client.TTMSprites;
import com.greatorator.tolkienmobs.container.UpgradeContainer;
import com.greatorator.tolkienmobs.entity.tile.BackpackTile;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class UpgradeScreen extends ModularGuiContainer<UpgradeContainer> {
    protected GuiToolkit<UpgradeScreen> toolkit = new GuiToolkit<>(this, 172, 135).setTranslationPrefix("gui.tolkienmobs.upgrade");
    private final BackpackTile tile;

    public UpgradeScreen(UpgradeContainer container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.tile = container.tile;
    }

    @Override
    public void addElements(GuiElementManager manager) {
        TGuiBase template = new TGuiBase(this);
        template.background = GuiTexture.newDynamicTexture(xSize(), ySize(), () -> BCSprites.getThemed("background_dynamic"));
        template.background.onReload(guiTex -> guiTex.setPos(guiLeft(), guiTop()));
        toolkit.loadTemplate(template);

        template.playerSlots = toolkit.createPlayerSlotsManualMovers(template.background, false, index -> new SlotMover(container.slots.get(index)));
        toolkit.placeInside(template.playerSlots, template.background, GuiToolkit.LayoutPos.BOTTOM_RIGHT, -5, -5);

        int slotColumns = 6;
        int slotRows = 1;
        GuiElement<?> mainSlots = toolkit.createSlots(template.background, slotColumns, slotRows, 0, (x, y) -> new SlotMover(container.mainSlots.get(x + (y * slotColumns))), null);
        toolkit.placeOutside(mainSlots, template.playerSlots, GuiToolkit.LayoutPos.TOP_CENTER, 0, -3);

        template.title.setAlignment(GuiAlign.LEFT);
        template.title.setXPos(mainSlots.xPos() - 25);
        template.title.setMaxYPos(mainSlots.yPos() - 21, false);

        GuiButton upgradeCloseButton = toolkit.createIconButton(template.background, 16, 16, TTMSprites.getter("backpack/close_upgrade"));
        toolkit.placeInside(upgradeCloseButton, template.playerSlots, GuiToolkit.LayoutPos.TOP_LEFT, 1, -20);
        upgradeCloseButton.onPressed(() -> tile.sendPacketToServer(mcDataOutput -> {}, 3));

    }
}
